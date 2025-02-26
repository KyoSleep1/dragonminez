package com.yuseix.dragonminez.events.characters;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.config.races.DMZColdDemonConfig;
import com.yuseix.dragonminez.init.MainFluids;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekianEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.SoldierEntity;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.world.*;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class EntityEvents {

	private static int soundTimer = 200;

	@SubscribeEvent
	public static void mobDeath(LivingDeathEvent event) {
		// Verificar si la entidad muerta es un aldeano, namekianentity,etc
		if (event.getEntity() instanceof NamekianEntity ||
				event.getEntity() instanceof Villager ||
				event.getEntity() instanceof Player) {

			// Verificar si el atacante es un jugador
			if (event.getSource().getEntity() instanceof Player) {
				Player player = (Player) event.getSource().getEntity();


				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
					boolean isDmzUser = cap.isAcceptCharacter();

					if (isDmzUser) { cap.removeDmzAlignment(5); } //Remover puntos te hace maligno
				});

				player.displayClientMessage(Component.translatable("lines.alignment.evil"), true);

			}
		}
		//Aca es para ganar puntos de bondad xxx claro pe tilin
		if (event.getEntity() instanceof SoldierEntity || event.getEntity() instanceof Monster) {

			if (event.getSource().getEntity() instanceof Player) {
				Player player = (Player) event.getSource().getEntity();

				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
					boolean isDmzUser = cap.isAcceptCharacter();

					if (isDmzUser) { cap.addDmzAlignment(2); } //Agregar puntos te hace bueno
				});

			}
		}

		//Pa ganar tps cuando mates algo claro pes papeto
		if (esEnemigo(event.getEntity())) {
			if (event.getSource().getEntity() instanceof Player) {
				Player player = (Player) event.getSource().getEntity();
				var vidaTps = (int) (event.getEntity().getMaxHealth() * 0.5); // 50% hp enemigo
				var calculoTps = (int) Math.round((10 + vidaTps) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_GAIN.get()); // (10 + 50% hp) * config

				// multiplicar si está en la hab del tiempo pes
				if (player.level().dimension().equals(ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY)) {
					calculoTps *= DMZGeneralConfig.MULTIPLIER_ZPOINTS_HTC.get();
				}

				int finalCalculoTps = calculoTps;

				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
					boolean isDmzUser = cap.isAcceptCharacter();
					int finalTps = Math.round(finalCalculoTps);

					if (cap.getRace() == 4)  finalTps *= DMZColdDemonConfig.TP_MULTIPLER_PASSIVE.get();

					if (isDmzUser) cap.addZpoints(finalTps);
					// Testing
              /* if (player.level().dimension().equals(ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY)) {
                    player.sendSystemMessage(Component.literal("TPS: " + finalTps + " (HTC)")); }
                    else {player.sendSystemMessage(Component.literal("TPS: " + finalTps)); } */
				});
			}
		}

		// Eliminar la marca majin
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
				cap.removePermanentEffect("majin");
			});
		}
	}

	@SubscribeEvent
	public static void onEntityHit(LivingHurtEvent event) {

		if (event.getSource().getEntity() instanceof Player) {
			Player player = (Player) event.getSource().getEntity();

			double baseTps = DMZGeneralConfig.PERHIT_ZPOINTS_GAIN.get() * DMZGeneralConfig.MULTIPLIER_ZPOINTS_GAIN.get();

			// multiplicar si está en la hab del tiempo pes
			if (player.level().dimension().equals(ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY)) {
				baseTps *= DMZGeneralConfig.MULTIPLIER_ZPOINTS_HTC.get();
			}

			double finalBaseTps = baseTps;

			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
				boolean isDmzUser = cap.isAcceptCharacter();
				int finalTps = (int) Math.round(finalBaseTps);
				if (cap.getRace() == 4) finalTps = (int) (finalTps * DMZColdDemonConfig.TP_MULTIPLER_PASSIVE.get());
				if (isDmzUser) { cap.addZpoints(finalTps); }

				// Testing
              /* if (player.level().dimension().equals(ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY)) {
                    player.sendSystemMessage(Component.literal("TPS: " + finalTps + " (HTC)")); }
                    else {player.sendSystemMessage(Component.literal("TPS: " + finalTps)); } */
			});
		}

		// Reducir durabilidad armadura de 1 en 1 xd
		LivingEntity entity = event.getEntity();

		// No verifico que sea un jugador para que funcione en zombies, npcs, etc que utilice armaduras y no se les haga instabreak

		for (EquipmentSlot slot : EquipmentSlot.values()) {
			if (slot.getType() == EquipmentSlot.Type.ARMOR) continue;

			ItemStack armorStack = entity.getItemBySlot(slot);

			if (armorStack.getItem() instanceof ArmorItem) {
				int unbreakingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, entity);

				if (unbreakingLevel > 0) {
					// Usar un número aleatorio para verificar si el daño se aplica
					Random rand = new Random();
					// La probabilidad es 1/(unbreakingLevel + 1) de que no se aplique el daño
					if (rand.nextInt(unbreakingLevel + 1) != 0) {
						// Si el número aleatorio no es 0, aplicar el daño
						armorStack.hurtAndBreak(1, entity, (e) -> e.broadcastBreakEvent(slot));
					}
				} else {
					// Si no tiene el encantamiento, aplicar el daño directamente
					armorStack.hurtAndBreak(1, entity, (e) -> e.broadcastBreakEvent(slot));
				}
			}
		}
	}

	private static boolean esEnemigo(Entity entity) {
		List<Class<?>> listaEnemigos = List.of(
				Monster.class,
				Animal.class,
				Player.class,
				NamekianEntity.class,
				SoldierEntity.class,
				FlyingMob.class,
				Mob.class
		);
		return listaEnemigos.stream().anyMatch(clase -> clase.isInstance(entity));
	}

	private static final double HEAL_PERCENTAGE = 0.05; // 5% por segundo
	private static final int HEAL_TICKS = 3 * 20; // 3 segundos

	private static final Map<Player, Long> lastHealingTime = new WeakHashMap<>();

	@SubscribeEvent
	public static void onLivingTick(TickEvent.PlayerTickEvent event) {
		Player player = event.player;

		if (player.level().isClientSide || event.phase != TickEvent.Phase.END) return;

		ServerPlayer serverPlayer = (ServerPlayer) player;
		BlockPos playerPos = serverPlayer.blockPosition();
		ServerLevel level = (ServerLevel) player.level();

		if (player.level().dimension().equals(ServerLevel.OVERWORLD)) {
			level.getCapability(StructuresProvider.CAPABILITY).ifPresent(structures -> {
				BlockPos posKami = structures.getTorreKarinPosition(); // La torre de Karin está más abajo, asi que es más factible xd
				if (playerPos.distSqr(posKami) < 10000) grantAdvancement(serverPlayer, "kamilookout");

				if (structures.getHasGokuHouse()) {
					BlockPos posGoku = structures.getGokuHousePosition();
					if (playerPos.distSqr(posGoku) < 10000) grantAdvancement(serverPlayer, "gokuhouse");
				}
				if (structures.getHasRoshiHouse()) {
					BlockPos posRoshi = structures.getRoshiHousePosition();
					if (playerPos.distSqr(posRoshi) < 10000) grantAdvancement(serverPlayer, "roshihouse");
				}

				//BlockPos posKaio = structures.getKaioPlanetPosition();
				//if (playerPos.distSqr(posKaio) < 10000) grantAdvancement(serverPlayer, "kaioplanet");
			});
		}

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
			if (cap.getDmzForm().equals("oozaru")) {
				soundTimer--;
				if (soundTimer == 0) {
					reproducirSonidoIdle(MainSounds.OOZARU_GROWL_PLAYER.get());
				} else if (soundTimer < 0) {
					Random random = new Random();
					soundTimer = random.nextInt(200) + 400;
				}
			}
		});

		FluidState fluidState = player.level().getFluidState(player.blockPosition());

		if (fluidState.isEmpty()) {
			return;
		}

		if (fluidState.is(MainFluids.SOURCE_HEALING.get()) || fluidState.is(MainFluids.FLOWING_HEALING.get())) {
			long currentTime = player.level().getGameTime(); // Tiempo actual en ticks
			long lastHealTime = lastHealingTime.getOrDefault(player, 0L);

			if (currentTime - lastHealTime >= HEAL_TICKS) {
				funcLiqCurativo(player);
				lastHealingTime.put(player, currentTime); // Actualizar el último tiempo de curación
			}
		} else if (fluidState.is(MainFluids.SOURCE_NAMEK.get()) || fluidState.is(MainFluids.FLOWING_NAMEK.get())) {
			funcAguaNamek(player);
		}
	}

	private static void grantAdvancement(ServerPlayer player, String advancementPath) {
		Advancement advancementToGive = player.getServer().getAdvancements().getAdvancement(new ResourceLocation(DragonMineZ.MOD_ID, advancementPath));
		if (advancementToGive != null) {
			AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancementToGive);
			if (!progress.isDone()) {
				for (String criteria : progress.getRemainingCriteria()) {
					player.getAdvancements().award(advancementToGive, criteria);
				}
			}
		}
	}

	private static void funcLiqCurativo(Player player) {
		if (player instanceof ServerPlayer serverPlayer) {
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, serverPlayer).ifPresent(stats -> {
				int maxHp = stats.getMaxHealth();
				int healHp = (int) (maxHp * HEAL_PERCENTAGE);
				int maxKi = stats.getMaxEnergy();
				int healKi = (int) (maxKi * HEAL_PERCENTAGE);
				boolean isDmzUser = stats.isAcceptCharacter();

				if (isDmzUser) {
					serverPlayer.heal(healHp);
					stats.addCurEnergy(healKi);
				}

			});
		}

		// Apagar el fuego
		if (player.isOnFire()) {
			player.clearFire();
		}
	}

	private static void funcAguaNamek(Player player) {
		if (player.isOnFire()) {
			player.clearFire();
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static void reproducirSonidoIdle(SoundEvent soundEvent) {
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			LocalPlayer player = Minecraft.getInstance().player;
			if (player != null) {
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(),
						soundEvent, SoundSource.PLAYERS, 1.0F, 1.0F, false);
			}
		});
	}
}




