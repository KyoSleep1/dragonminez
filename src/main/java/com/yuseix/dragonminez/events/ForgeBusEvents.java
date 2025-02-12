package com.yuseix.dragonminez.events;

import com.mojang.logging.LogUtils;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.commands.*;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.storyline.player.PlayerStorylineProvider;
import com.yuseix.dragonminez.world.*;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.DebugLevelSource;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//Anteriormente llamado ForgeListener ya que los eventos forman parte del bus de MinecraftForge
//ACTUALMENTE LOS ModEvents son eventos que se ejecutan en el bus de Forge **(DIFERENTE al IModBusEvent)**
//Si una clase extiende "Event" se considera un evento del bus de Forge y TIENE que estar acá.
//O también si es parte del paquete "net.minecraftforge.eventbus.api"
@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeBusEvents {

	public static final Capability<DMZStatsCapabilities> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {
	});


	private static final List<BlockPos> dragonBallPositions = new ArrayList<>();
	private static final List<BlockPos> namekDragonBallPositions = new ArrayList<>();
	private static boolean hasSpawnedDragonBalls = false, hasSpawnedNamekDragonBalls = false;

	private static final Logger LOGGER = LogUtils.getLogger();

	private static final List<String> ALLOWED_USERNAMES = Arrays.asList(
			// Staff
			"Dev",
			"ImYuseix",
			"ezShokkoh",
			"MrBrunoh",
			"Toji71",
			// Testers
			"ThiagoHanagaki",
			"Gabrielololo",
			"InYourHeart_",
			"Im_Lu_",
			"Ducco123",
			"Rev_zy", //Mazu
			"grillo78",
			"TheWildBoss",
			"EsePibe01",
			"Pokimons123",
			"bbysixty",
			"Onashi",
			// Patreon
			"Baby_Poop12311", // Cyanea capillata
			"Robberto10",
			"SpaceCarp"
	);

	// Recordar comentar esto antes de Buildear una versión Pública.
	// y Descomentar para el buildeo de versiones de Testing.
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        String username = player.getGameProfile().getName();

        if (!ALLOWED_USERNAMES.contains(username)) {
            LOGGER.error("The user {} is not allowed to play the mod. The game session will now be terminated.", username);
            throw new IllegalStateException("DMZ: Username not allowed to start gameplay!");
        }

		if (event.getEntity().level() instanceof ServerLevel serverLevel) {
			if (serverLevel.dimension() == Level.OVERWORLD) {
				serverLevel.getCapability(DragonBallGenProvider.CAPABILITY).ifPresent(cap -> {
					cap.loadFromSavedData(serverLevel);
				});
			}
			if (serverLevel.dimension() == ModDimensions.NAMEK_DIM_LEVEL_KEY) {
				serverLevel.getCapability(NamekDragonBallGenProvider.CAPABILITY).ifPresent(cap -> {
					cap.loadFromSavedData(serverLevel);
				});
			}
		}
    }

	@SubscribeEvent
	public void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		Player player = event.getEntity();

		if (player.level() instanceof ServerLevel serverLevel) {
			if (serverLevel.dimension() == Level.OVERWORLD) {
				serverLevel.getCapability(DragonBallGenProvider.CAPABILITY).ifPresent(cap -> {
					cap.loadFromSavedData(serverLevel);
				});
			}
			if (serverLevel.dimension() == ModDimensions.NAMEK_DIM_LEVEL_KEY) {
				serverLevel.getCapability(NamekDragonBallGenProvider.CAPABILITY).ifPresent(cap -> {
					cap.loadFromSavedData(serverLevel);
				});
			}
		}
	}

	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		ServerLevel serverOverworld = event.getServer().getLevel(Level.OVERWORLD);
		ServerLevel serverNamek = event.getServer().getLevel(ModDimensions.NAMEK_DIM_LEVEL_KEY);

		if (serverOverworld == null) return;
		if (!serverOverworld.isClientSide()) {
			LazyOptional<StructuresCapability> capability = serverOverworld.getCapability(StructuresProvider.CAPABILITY);
			serverOverworld.getCapability(DragonBallGenProvider.CAPABILITY).ifPresent(dragonBallsCapability -> {
				dragonBallsCapability.loadFromSavedData(serverNamek);

				if (!dragonBallsCapability.hasDragonBalls()) {
					spawnDragonBall(serverOverworld, MainBlocks.DBALL1_BLOCK.get().defaultBlockState(), 1);
					spawnDragonBall(serverOverworld, MainBlocks.DBALL2_BLOCK.get().defaultBlockState(), 2);
					spawnDragonBall(serverOverworld, MainBlocks.DBALL3_BLOCK.get().defaultBlockState(), 3);
					// La primer vez que se generen las DragonBalls, guarda la posición de la Esfera de 4 Estrellas que está dentro de la casa de Goku
					capability.ifPresent(cap -> {
						if (cap.getHasGokuHouse()) {
							BlockPos db4pos = cap.getDB4Position();
							dragonBallPositions.add(db4pos);
							System.out.println("[FirstSpawn] Dragon Ball [4] spawned at " + db4pos);
						} else {
							spawnDragonBall(serverOverworld, MainBlocks.DBALL4_BLOCK.get().defaultBlockState(), 4);
						}
					});
					spawnDragonBall(serverOverworld, MainBlocks.DBALL5_BLOCK.get().defaultBlockState(), 5);
					spawnDragonBall(serverOverworld, MainBlocks.DBALL6_BLOCK.get().defaultBlockState(), 6);
					spawnDragonBall(serverOverworld, MainBlocks.DBALL7_BLOCK.get().defaultBlockState(), 7);

					dragonBallsCapability.setDragonBallPositions(dragonBallPositions);
					dragonBallsCapability.setHasDragonBalls(true);
					dragonBallsCapability.saveToSavedData(serverOverworld);
				}
			});
		}

		if (serverNamek == null) return;
		if (!serverNamek.isClientSide()) {
			serverNamek.getCapability(NamekDragonBallGenProvider.CAPABILITY).ifPresent(namekDragonBallsCapability -> {
				namekDragonBallsCapability.loadFromSavedData(serverNamek);

				// Verifica si ya se han generado las Dragon Balls
				if (!namekDragonBallsCapability.hasNamekDragonBalls()) {
					spawnNamekDragonBall(serverNamek, MainBlocks.DBALL1_NAMEK_BLOCK.get().defaultBlockState(), 1);
					spawnNamekDragonBall(serverNamek, MainBlocks.DBALL2_NAMEK_BLOCK.get().defaultBlockState(), 2);
					spawnNamekDragonBall(serverNamek, MainBlocks.DBALL3_NAMEK_BLOCK.get().defaultBlockState(), 3);
					spawnNamekDragonBall(serverNamek, MainBlocks.DBALL4_NAMEK_BLOCK.get().defaultBlockState(), 4); // Reemplazar por el Gran Patriarca luego, igual que Goku
					spawnNamekDragonBall(serverNamek, MainBlocks.DBALL5_NAMEK_BLOCK.get().defaultBlockState(), 5);
					spawnNamekDragonBall(serverNamek, MainBlocks.DBALL6_NAMEK_BLOCK.get().defaultBlockState(), 6);
					spawnNamekDragonBall(serverNamek, MainBlocks.DBALL7_NAMEK_BLOCK.get().defaultBlockState(), 7);

					// Indica que las Dragon Balls de Namek han sido generadas
					namekDragonBallsCapability.setNamekDragonBallPositions(namekDragonBallPositions);
					namekDragonBallsCapability.setHasNamekDragonBalls(true);
					namekDragonBallsCapability.saveToSavedData(serverNamek);
				}
			});
		}
	}

	@SubscribeEvent
	public void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player player) {
			if (event.getObject().getCapability(INSTANCE).isPresent() || event.getObject().getCapability(PlayerStorylineProvider.CAPABILITY).isPresent()) return;

			final DMZStatsProvider provider = new DMZStatsProvider(player);
			final PlayerStorylineProvider storylineprovider = new PlayerStorylineProvider();

			event.addCapability(DMZStatsProvider.ID, provider);
			event.addCapability(PlayerStorylineProvider.ID, storylineprovider);
		}
	}

	@SubscribeEvent
	public void onAttachCapabilitiesWorld(AttachCapabilitiesEvent<Level> event) {
		if (event.getObject() instanceof ServerLevel) {
			if (!event.getObject().getCapability(DragonBallGenProvider.CAPABILITY).isPresent())
				event.addCapability(new ResourceLocation(DragonMineZ.MOD_ID, "dragon_balls"), new DragonBallGenProvider());

			if (!event.getObject().getCapability(NamekDragonBallGenProvider.CAPABILITY).isPresent())
				event.addCapability(new ResourceLocation(DragonMineZ.MOD_ID, "namek_dragon_balls"), new NamekDragonBallGenProvider());

			if (!event.getObject().getCapability(StructuresProvider.CAPABILITY).isPresent())
				event.addCapability(new ResourceLocation(DragonMineZ.MOD_ID, "structures"), new StructuresProvider());
		}
	}

	@SubscribeEvent
	public void onCommandsRegister(RegisterCommandsEvent event) {
		new ZPointsCommand(event.getDispatcher());
		new StatsCommand(event.getDispatcher());
		new ResetCharacterCommand(event.getDispatcher());
		new AlignmentCommand(event.getDispatcher());
		new LocationsCommand(event.getDispatcher());
		new DMZPermaEffectsCommand(event.getDispatcher());
		new DMZTempEffectsCommand(event.getDispatcher());
		new StorylineCommand(event.getDispatcher());
		new DMZSkillsCommand(event.getDispatcher());
		new DMZSuperFormCommand(event.getDispatcher());

		ConfigCommand.register(event.getDispatcher());
	}

	@SubscribeEvent
	public void onWorldLoad(LevelEvent.Load event) {
		if (event.getLevel() instanceof ServerLevel serverLevel && !serverLevel.isClientSide()) {
			if (serverLevel.dimension() == Level.OVERWORLD) {
				ChunkGenerator generator = serverLevel.getChunkSource().getGenerator();
				BiomeSource biomeSource = generator.getBiomeSource();
				boolean isSuperflat = generator instanceof FlatLevelSource;
				boolean isSingleBiome = biomeSource.possibleBiomes().size() == 1;
				boolean isSingleBiomePlains = isSingleBiome && biomeSource.possibleBiomes().size() == 1 && biomeSource.possibleBiomes().equals(Biomes.PLAINS);

				LazyOptional<StructuresCapability> capability = serverLevel.getCapability(StructuresProvider.CAPABILITY);
				capability.ifPresent(cap -> {
					// SIEMPRE generamos la Torre de Kami, independientemente del tipo de mundo
					cap.generateKamisamaStructure(serverLevel);

					// Si es un mundo normal, extraplano, o solo de Plains, generamos la casa de Goku
					if (!isSingleBiome || isSingleBiomePlains || isSuperflat) {
						cap.generateGokuHouseStructure(serverLevel);
					}

					// Si no es un mundo extraplano y tampoco es de un solo bioma, generamos la casa de Roshi
					if (!isSuperflat && !isSingleBiome) {
						cap.generateRoshiHouseStructure(serverLevel);
					}
				});

				serverLevel.getCapability(DragonBallGenProvider.CAPABILITY).ifPresent(cap -> cap.loadFromSavedData(serverLevel));
			}
			if (serverLevel.dimension() == ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY) {
				LazyOptional<StructuresCapability> capability = serverLevel.getCapability(StructuresProvider.CAPABILITY);
				capability.ifPresent(cap -> cap.generateHabTiempoStructure(serverLevel));
			}
			if (serverLevel.dimension() == ModDimensions.NAMEK_DIM_LEVEL_KEY) {
				serverLevel.getCapability(NamekDragonBallGenProvider.CAPABILITY).ifPresent(cap -> cap.loadFromSavedData(serverLevel));
			}
		}
	}


	private void spawnDragonBall(ServerLevel serverWorld, BlockState dragonBall, int dBallNum) {
		//Spawn the dragon balls
		BlockPos spawnPos = serverWorld.getSharedSpawnPos();
		Random random = new Random();
		int range = DMZGeneralConfig.DBALL_SPAWN_RANGE.get();

		BlockPos posicionValida = new BlockPos(0, 0, 0); // Posición válida inicializada a 0, 0, 0

		while (posicionValida.equals(new BlockPos(0, 0, 0))) {
			// Generar posición aleatoria dentro de un rango de Xk bloques desde el spawn
			int x = spawnPos.getX() + random.nextInt(range * 2) - range;
			int z = spawnPos.getZ() + random.nextInt(range * 2) - range;

			serverWorld.getChunk(x >> 4, z >> 4); // Cargar el chunk

			// Obtener la altura del terreno en esa posición
			int y = serverWorld.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
			BlockPos posiblePos = new BlockPos(x, y, z);

			BlockState belowBlockState = serverWorld.getBlockState(posiblePos.below()); // Bloque debajo de la posición
			BlockState belowBelowBlockState = serverWorld.getBlockState(posiblePos.below().below()); // Bloque debajo del bloque anterior

			// Validar que la posición no esté en agua ni aire
			if (!belowBlockState.isAir() && !(belowBlockState.getBlock() == Blocks.WATER) &&
					!belowBelowBlockState.isAir() && !(belowBelowBlockState.getBlock() == Blocks.WATER)) {
				posicionValida = posiblePos; // Si es válida, asignamos la posición
			}
		}

		// Place a Dragon Ball block at the generated position
		serverWorld.setBlock(posicionValida, dragonBall, 2);
		System.out.println("[FirstSpawn] Dragon Ball [" + dBallNum + "] spawned at " + posicionValida);

		dragonBallPositions.add(posicionValida);
	}

	private void spawnNamekDragonBall(ServerLevel serverWorld, BlockState namekDragonBall, int dBallNum) {
		//Spawn the dragon balls
		BlockPos spawnPos = serverWorld.getSharedSpawnPos();
		Random random = new Random();
		int range = DMZGeneralConfig.DBALL_SPAWN_RANGE.get();

		BlockPos posicionValida = new BlockPos(0, 0, 0); // Posición válida inicializada a 0, 0, 0

		while (posicionValida.equals(new BlockPos(0, 0, 0))) {
			// Generar posición aleatoria dentro de un rango de Xk bloques desde el spawn
			int x = spawnPos.getX() + random.nextInt(range * 2) - range;
			int z = spawnPos.getZ() + random.nextInt(range * 2) - range;

			serverWorld.getChunk(x >> 4, z >> 4); // Cargar el chunk

			// Obtener la altura del terreno en esa posición
			int y = serverWorld.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
			BlockPos posiblePos = new BlockPos(x, y, z);

			BlockState belowBlockState = serverWorld.getBlockState(posiblePos.below()); // Bloque debajo de la posición
			BlockState belowBelowBlockState = serverWorld.getBlockState(posiblePos.below().below()); // Bloque debajo del bloque anterior

			// Validar que la posición no esté en agua ni aire
			if (!belowBlockState.isAir() && !(belowBlockState.getBlock() == Blocks.WATER) && !(belowBelowBlockState.getBlock() == MainBlocks.NAMEK_WATER_LIQUID.get()) &&
					!belowBelowBlockState.isAir() && !(belowBelowBlockState.getBlock() == Blocks.WATER) && !(belowBelowBlockState.getBlock() == MainBlocks.NAMEK_WATER_LIQUID.get())) {
				posicionValida = posiblePos; // Si es válida, asignamos la posición
			}
		}

		// Place a Dragon Ball block at the generated position
		serverWorld.setBlock(posicionValida, namekDragonBall, 2);
		System.out.println("[FirstSpawn] Namekian Dragon Ball [" + dBallNum + "] spawned at " + posicionValida);

		namekDragonBallPositions.add(posicionValida);
	}
}
