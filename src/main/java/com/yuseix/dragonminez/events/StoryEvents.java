package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.*;
import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.stats.storymode.DMZQuest;
import com.yuseix.dragonminez.stats.storymode.DMZStoryCapability;
import com.yuseix.dragonminez.stats.storymode.DMZStoryRegistry;
import com.yuseix.dragonminez.stats.storymode.QuestRequirement;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import java.util.Map;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class StoryEvents {

	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		LivingEntity entity = event.getEntity();
		DamageSource source = event.getSource();

		if (source.getEntity() instanceof ServerPlayer player) {
			String entityId = entity.getType().toString();
			System.out.println("onEntityDeath: entityId: " + entityId + ", source: " + player);

			player.getCapability(DMZStoryCapability.INSTANCE).ifPresent(capability -> {
				DMZQuest currentQuest = DMZStoryRegistry.getQuest(capability.getCurrentQuestId());
				if (currentQuest != null && currentQuest.getRequirement().getRequiredKills() != null) {
					Map<String, Integer> requiredKills = currentQuest.getRequirement().getRequiredKills();

					if (requiredKills.containsKey(entityId)) {
						System.out.println("onEntityDeath: requiredKills contiene el entity");
						capability.addKill(entityId);

						// Verificar si se ha alcanzado el número requerido de kills
						if (capability.getKillCount(entityId) >= requiredKills.get(entityId)) {
							System.out.println("onEntityDeath: killCount >= requiredKills.get(entityId)");
							capability.setKillObjectiveComplete(entityId); // Marcar el objetivo de matar enemigos como completado
							syncQuestData(player);
						}
					}
				}
			});
		}
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END && event.player.tickCount % 100 == 0 && !event.player.level().isClientSide()) { // Cada 5 segundos (100 ticks)
			Player player = event.player;
			player.getCapability(DMZStoryCapability.INSTANCE).ifPresent(capability -> {
				DMZQuest currentQuest = DMZStoryRegistry.getQuest(capability.getCurrentQuestId());
				// Verificar si el jugador está en el bioma requerido
				if (currentQuest != null && currentQuest.getRequirement().getRequiredBiome() != null) {
					System.out.println("onPlayerTick: currentQuest: " + currentQuest.getId());
					Optional<ResourceKey<Biome>> biomeKey = player.level().getBiome(player.blockPosition()).unwrapKey();
					if (biomeKey.isPresent()) {
						String currentBiome = biomeKey.get().location().toString();
						System.out.println("onPlayerTick: currentBiome: " + currentBiome + ", requiredBiome: " + currentQuest.getRequirement().getRequiredBiome());

						if (currentBiome.equals(currentQuest.getRequirement().getRequiredBiome())) {
							System.out.println("onPlayerTick: Bioma encontrado");
							capability.setStructureFound(true);
							checkQuestCompletion(player);
							syncQuestData(player);
						}
					}
				}

				// Verificar si el jugador ha recolectado los ítems requeridos
				if (currentQuest != null && currentQuest.getRequirement().getRequiredItems() != null) {
					Map<String, Integer> requiredItems = currentQuest.getRequirement().getRequiredItems();

					for (Map.Entry<String, Integer> entry : requiredItems.entrySet()) {
						String itemId = entry.getKey();
						int requiredAmount = entry.getValue();

						int count = 0;
						for (ItemStack stack : player.getInventory().items) {
							if (stack.getItem().toString().equals(itemId)) {
								count += stack.getCount();
							}
						}

						capability.hasCollectedItems().put(itemId, count);

						if (count >= requiredAmount) {
							capability.setHasRequiredItem(true);
							syncQuestData(player);
						}
					}
				}
			});
		}
	}

	public static void checkQuestCompletion(Player player) {
		player.getCapability(DMZStoryCapability.INSTANCE).ifPresent(capability -> {
			DMZQuest currentQuest = DMZStoryRegistry.getQuest(capability.getCurrentQuestId());
			if (currentQuest != null) {
				System.out.println("checkQuestCompletion: currentQuest no es null, es: " + currentQuest.getId());
				QuestRequirement requirement = currentQuest.getRequirement();

				boolean killsComplete = true;
				if (requirement.getRequiredKills() != null) {
					for (Map.Entry<String, Integer> entry : requirement.getRequiredKills().entrySet()) {
						if (!capability.isKillObjectiveComplete(entry.getKey())) {
							killsComplete = false;
							break;
						}
					}
				}

				// Verificar si todos los objetivos están completos
				if (killsComplete && requirement.isFulfilled(player, capability.getEntityKillCounts(), capability.isStructureFound(), capability.hasCollectedItems())) {
					System.out.println("checkQuestCompletion: killsComplete y requirement.isFulfilled son true");
					capability.getCompletedQuests().add(currentQuest.getId());

					onQuestComplete(player, currentQuest.getId());

					syncQuestData(player);
				}
			}
		});
	}

	public static void onQuestComplete(Player player, String questId) {
		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {

			switch (questId) {
				case "saiyQuest1" -> cap.addIntValue("tps", 350);
				case "saiyQuest2" -> cap.addIntValue("tps", 550);
				case "saiyQuest3" -> cap.addIntValue("tps", 750);
			}
			System.out.println("onQuestComplete: recompensa otorgada, misión completada: " + questId);
		});
		player.getCapability(DMZStoryCapability.INSTANCE).ifPresent(cap -> {
			// Asignar la siguiente misión (si hay alguna)
			DMZQuest currentQuest = DMZStoryRegistry.getQuest(cap.getCurrentQuestId());
			DMZQuest nextQuest = DMZStoryRegistry.getQuest(currentQuest.getNextQuestId());
			System.out.println("onQuestComplete: currentQuest: " + currentQuest.getId() + ", nextQuest: " + (nextQuest != null ? nextQuest.getId() : "null"));
			if (currentQuest.getId().equals(questId) && nextQuest != null) {
				cap.setCurrentQuestId(nextQuest.getId());
			}
		});
	}

	@SubscribeEvent
	public void onPlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
		syncQuestData(event.getEntity());
		event.getEntity().refreshDimensions();
		syncCompletedQuests(event.getEntity());

	}

	@SubscribeEvent
	public void playerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		syncQuestData(event.getEntity());
		syncCompletedQuests(event.getEntity());
	}

	@SubscribeEvent
	public void playerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		syncQuestData(event.getEntity());
		syncCompletedQuests(event.getEntity());
	}

	@SubscribeEvent
	public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.register(DMZStatsAttributes.class);
	}

	@SubscribeEvent
	public void onPlayerCloned(PlayerEvent.Clone event) {
		var player = event.getEntity();
		var original = event.getOriginal();

		original.reviveCaps();

		player.getCapability(DMZStoryCapability.INSTANCE).ifPresent(
				cap -> player.getCapability(DMZStoryCapability.INSTANCE).ifPresent(originalcap ->
						cap.loadNBTData(originalcap.saveNBTData())));


		original.invalidateCaps();

	}

	@SubscribeEvent
	public static void onTrack(PlayerEvent.StartTracking event) {
		var trackingplayer = event.getEntity();
		if (!(trackingplayer instanceof ServerPlayer serverplayer)) return;

		var tracked = event.getTarget();
		if (tracked instanceof ServerPlayer trackedplayer) {
			tracked.getCapability(DMZStoryCapability.INSTANCE).ifPresent(cap -> {

				ModMessages.sendToPlayer(new StorySyncS2C(trackedplayer), serverplayer);

				ModMessages.sendToPlayer(
						new DMZCompletedQuestsSyncS2C(trackedplayer, cap.getCompletedQuests()),
						serverplayer
				);

			});

		}
	}

	public static void syncQuestData(Player player) {
		ModMessages.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new StorySyncS2C(player));
	}

	public static void syncCompletedQuests(Player player) {
		DMZStatsProvider.getCap(DMZStoryCapability.INSTANCE, player).ifPresent(cap -> {
			ModMessages.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
					new DMZCompletedQuestsSyncS2C(player, cap.getCompletedQuests()));
		});
	}
}
