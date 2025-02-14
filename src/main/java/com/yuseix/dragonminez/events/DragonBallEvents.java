package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.UpdateDragonRadarS2C;
import com.yuseix.dragonminez.network.S2C.UpdateNamekDragonRadarS2C;
import com.yuseix.dragonminez.utils.DebugUtils;
import com.yuseix.dragonminez.world.DragonBallGenProvider;
import com.yuseix.dragonminez.world.DragonBallsCapability;
import com.yuseix.dragonminez.world.NamekDragonBallGenProvider;
import com.yuseix.dragonminez.world.NamekDragonBallsCapability;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class DragonBallEvents {

	@SubscribeEvent
	public static void onItemPickup(EntityItemPickupEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer player)) return;

		ItemStack stack = event.getItem().getItem();
		Level level = player.level();
		Block block = stack.getItem() instanceof BlockItem blockItem ? blockItem.getBlock() : null;

		// Verificar si el ítem es una Dragon Ball
		if (!isDragonBallBlock(block)) return;

		// Actualizar posiciones en el servidor
		List<BlockPos> positionsDBall = null;
		List<BlockPos> positionsNDball = null;
		if (level.getCapability(DragonBallGenProvider.CAPABILITY).isPresent()) {
			DragonBallsCapability capability = level.getCapability(DragonBallGenProvider.CAPABILITY)
					.orElseThrow(() -> new IllegalStateException("DragonBallsCapability not found"));
			capability.updateDragonBallPositions(level);
			positionsDBall = capability.dragonBallPositions;
		} else if (level.getCapability(NamekDragonBallGenProvider.CAPABILITY).isPresent()) {
			NamekDragonBallsCapability capability = level.getCapability(NamekDragonBallGenProvider.CAPABILITY)
					.orElseThrow(() -> new IllegalStateException("NamekDragonBallsCapability not found"));
			capability.updateDragonBallPositions(level);
			positionsNDball = capability.namekDragonBallPositions;
		}

		// Enviar nueva lista al cliente
		if (positionsDBall != null) {
			ModMessages.sendToPlayer(new UpdateDragonRadarS2C(positionsDBall), player);
		}
		if (positionsNDball != null) {
			ModMessages.sendToPlayer(new UpdateNamekDragonRadarS2C(positionsNDball), player);
		}
	}

	@SubscribeEvent
	public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer player)) return;

		Level level = player.level();
		BlockPos pos = event.getPos();
		BlockState blockState = event.getPlacedBlock();
		Block block = blockState.getBlock();

		// Verificar si el bloque colocado es una Dragon Ball
		if (!isDragonBallBlock(block)) return;

		if (level.getCapability(DragonBallGenProvider.CAPABILITY).isPresent() && isEarthDB(block)) {
			level.getCapability(DragonBallGenProvider.CAPABILITY).ifPresent(capability -> {
				// Buscar si ya había una esfera con el mismo tipo en otra ubicación
				capability.dragonBallPositions.stream()
						.filter(existingPos -> mismaDragonBall(level, existingPos, block))
						.findFirst()
						.ifPresent(existingPos -> {
							// Romper el bloque anterior y reemplazarlo con aire
							level.setBlock(existingPos, Blocks.AIR.defaultBlockState(), 3);
							DebugUtils.dmzLog("[DBallEvents] Removed existing Dragon Ball at " + existingPos);
							capability.dragonBallPositions.remove(existingPos);
						});

				// Actualizar la posición actual
				capability.dragonBallPositions.add(pos);

				// Sincronizar con el cliente
				ModMessages.sendToPlayer(new UpdateDragonRadarS2C(capability.dragonBallPositions), player);
			});
		} else if (level.getCapability(NamekDragonBallGenProvider.CAPABILITY).isPresent() && isNamekDB(block)) {
			// Mismo proceso pero para Namek xd
			level.getCapability(NamekDragonBallGenProvider.CAPABILITY).ifPresent(capability -> {
				capability.namekDragonBallPositions.stream()
						.filter(existingPos -> mismaDragonBall(level, existingPos, block))
						.findFirst()
						.ifPresent(existingPos -> {
							level.setBlock(existingPos, Blocks.AIR.defaultBlockState(), 3);
							DebugUtils.dmzLog("[DBallEvents] Removed existing Namek Dragon Ball at " + existingPos);
							capability.namekDragonBallPositions.remove(existingPos);
						});

				capability.namekDragonBallPositions.add(pos);

				ModMessages.sendToPlayer(new UpdateNamekDragonRadarS2C(capability.namekDragonBallPositions), player);
			});
		}
	}

	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		if (!(event.getPlayer() instanceof ServerPlayer player)) return;

		Level level = player.level();
		BlockPos pos = event.getPos();
		Block block = level.getBlockState(pos).getBlock();

		// Verificar si el bloque roto es una Dragon Ball
		if (!isDragonBallBlock(block)) return;

		if (level.getCapability(DragonBallGenProvider.CAPABILITY).isPresent() && isEarthDB(block)) {
			level.getCapability(DragonBallGenProvider.CAPABILITY).ifPresent(capability -> {
				// Eliminar la posición
				capability.dragonBallPositions.remove(pos);

				// Sincronizar con el cliente
				ModMessages.sendToPlayer(new UpdateDragonRadarS2C(capability.dragonBallPositions), player);
			});
		} else if (level.getCapability(NamekDragonBallGenProvider.CAPABILITY).isPresent() && isNamekDB(block)) {
			level.getCapability(NamekDragonBallGenProvider.CAPABILITY).ifPresent(capability -> {
				// Eliminar la posición
				capability.namekDragonBallPositions.remove(pos);

				// Sincronizar con el cliente
				ModMessages.sendToPlayer(new UpdateNamekDragonRadarS2C(capability.namekDragonBallPositions), player);
			});
		}
	}

	private static boolean isDragonBallBlock(Block block) {
		return block == MainBlocks.DBALL1_BLOCK.get() || block == MainBlocks.DBALL2_BLOCK.get() || block == MainBlocks.DBALL3_BLOCK.get() || block == MainBlocks.DBALL4_BLOCK.get() || block == MainBlocks.DBALL5_BLOCK.get() || block == MainBlocks.DBALL6_BLOCK.get() || block == MainBlocks.DBALL7_BLOCK.get() ||
				block == MainBlocks.DBALL1_NAMEK_BLOCK.get() || block == MainBlocks.DBALL2_NAMEK_BLOCK.get() || block == MainBlocks.DBALL3_NAMEK_BLOCK.get() || block == MainBlocks.DBALL4_NAMEK_BLOCK.get() || block == MainBlocks.DBALL5_NAMEK_BLOCK.get() || block == MainBlocks.DBALL6_NAMEK_BLOCK.get() || block == MainBlocks.DBALL7_NAMEK_BLOCK.get();
	}

	private static boolean isEarthDB(Block block) {
		return block == MainBlocks.DBALL1_BLOCK.get() || block == MainBlocks.DBALL2_BLOCK.get() || block == MainBlocks.DBALL3_BLOCK.get() || block == MainBlocks.DBALL4_BLOCK.get() || block == MainBlocks.DBALL5_BLOCK.get() || block == MainBlocks.DBALL6_BLOCK.get() || block == MainBlocks.DBALL7_BLOCK.get();
	}

	private static boolean isNamekDB(Block block) {
		return block == MainBlocks.DBALL1_NAMEK_BLOCK.get() || block == MainBlocks.DBALL2_NAMEK_BLOCK.get() || block == MainBlocks.DBALL3_NAMEK_BLOCK.get() || block == MainBlocks.DBALL4_NAMEK_BLOCK.get() || block == MainBlocks.DBALL5_NAMEK_BLOCK.get() || block == MainBlocks.DBALL6_NAMEK_BLOCK.get() || block == MainBlocks.DBALL7_NAMEK_BLOCK.get();
	}

	private static boolean mismaDragonBall(Level level, BlockPos pos, Block block) {
		Block bloqueActual = level.getBlockState(pos).getBlock();
		return bloqueActual == block;
	}
}
