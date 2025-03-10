package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.*;
import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.stats.storymode.DMZQuest;
import com.yuseix.dragonminez.stats.storymode.DMZStoryCapability;
import com.yuseix.dragonminez.stats.storymode.DMZStoryRegistry;
import com.yuseix.dragonminez.stats.storymode.QuestRequirement;
import net.minecraft.network.chat.Component;
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

			player.getCapability(DMZStoryCapability.INSTANCE).ifPresent(capability -> {
				DMZQuest currentQuest = DMZStoryRegistry.getQuest(capability.getCurrentQuestId());
				if (currentQuest != null && currentQuest.getRequirement().getRequiredKills() != null) {
					Map<String, Integer> requiredKills = currentQuest.getRequirement().getRequiredKills();

					if (requiredKills.containsKey(entityId)) {
						capability.addKill(entityId);

						// Verificar si se ha alcanzado el número requerido de kills
						if (capability.getKillCount(entityId) >= requiredKills.get(entityId)) {
							capability.setKillObjectiveComplete(entityId); // Marcar el objetivo de matar enemigos como completado
							checkQuestCompletion(player);
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
					Optional<ResourceKey<Biome>> biomeKey = player.level().getBiome(player.blockPosition()).unwrapKey();
					if (biomeKey.isPresent()) {
						String currentBiome = biomeKey.get().location().toString();

						if (currentBiome.equals(currentQuest.getRequirement().getRequiredBiome()) && !capability.isBiomeFound()) {
							capability.setBiomeFound(true);
						} else if (capability.isBiomeFound() && !currentBiome.equals(currentQuest.getRequirement().getRequiredBiome())) {
								capability.setBiomeFound(false);
						}
						checkQuestCompletion(player);
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
							checkQuestCompletion(player);
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
				if (killsComplete && requirement.isFulfilled(player, capability.getEntityKillCounts(), capability.isBiomeFound(), capability.hasCollectedItems())) {
					capability.getCompletedQuests().add(currentQuest.getId());

					onQuestComplete(player, currentQuest.getId());
				}
				DMZStoryCapability.syncQuestData(player);
				DMZStoryCapability.syncCompletedQuests(player);
			}
		});
	}

	public static void onQuestComplete(Player player, String questId) {
		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
			int tps = 0;
			switch (questId) {
				case "saiyQuest1" -> {
					tps = 350;
					cap.addIntValue("tps", tps);
					player.sendSystemMessage(Component.translatable("dmz.storyline.rewards.tps", tps, questId));
				}
				case "saiyQuest2" -> {
					tps = 750;
					cap.addIntValue("tps", tps);
					player.sendSystemMessage(Component.translatable("dmz.storyline.rewards.tps", tps, questId));
				}
				case "saiyQuest3" -> {
					tps = 2250;
					cap.addIntValue("tps", tps);
					player.sendSystemMessage(Component.translatable("dmz.storyline.rewards.tps", tps, questId));
				}
				case "saiyQuest4", "saiyQuest5" -> {
					tps = 5000;
					cap.addIntValue("tps", tps);
					player.sendSystemMessage(Component.translatable("dmz.storyline.rewards.tps", tps, questId));
				}
				case "saiyQuest6", "saiyQuest8" -> {
					tps = 7500;
					cap.addIntValue("tps", tps);
					player.sendSystemMessage(Component.translatable("dmz.storyline.rewards.tps", tps, questId));
					if (questId.equals("saiyQuest8")) {
						player.getInventory().add(new ItemStack(MainItems.NAVE_SAIYAN_ITEM.get()));
						player.sendSystemMessage(Component.translatable("dmz.storyline.rewards.item", MainItems.NAVE_SAIYAN_ITEM.get(), questId));
					}
				}
				case "saiyQuest7" -> {
					tps = 12500;
					cap.addIntValue("tps", tps);
					player.sendSystemMessage(Component.translatable("dmz.storyline.rewards.tps", tps, questId));
				}
				case "saiyQuest9" -> {
					tps = 1500;
					cap.addIntValue("tps", tps);
					player.sendSystemMessage(Component.translatable("dmz.storyline.rewards.tps", tps, questId));
				}
			}

			System.out.println("You have completed the quest: " + questId + ". Your reward is: " + cap.getIntValue("tps") + " TPS");
		});
		player.getCapability(DMZStoryCapability.INSTANCE).ifPresent(cap -> {
			// Asignar la siguiente misión (si hay alguna)
			DMZQuest currentQuest = DMZStoryRegistry.getQuest(cap.getCurrentQuestId());
			DMZQuest nextQuest = DMZStoryRegistry.getQuest(currentQuest.getNextQuestId());
			if (currentQuest.getId().equals(questId) && nextQuest != null) {
				cap.setCurrentQuestId(nextQuest.getId());
			}
			DMZStoryCapability.syncQuestData(player);
			DMZStoryCapability.syncCompletedQuests(player);
		});
	}
}
