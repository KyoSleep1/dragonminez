package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.StorylineSyncS2C;
import com.yuseix.dragonminez.registry.IDRegistry;
import com.yuseix.dragonminez.storyline.Objective;
import com.yuseix.dragonminez.storyline.Quest;
import com.yuseix.dragonminez.storyline.Saga;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveCollectItem;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveGetToBiome;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveGetToLocation;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveKillEnemy;
import com.yuseix.dragonminez.storyline.player.PlayerStorylineProvider;
import com.yuseix.dragonminez.utils.DebugUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class StorylineEvents {

	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		DebugUtils.dmzLog("StorylineManager initialized");
	}

	@SubscribeEvent
	public void onPlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
		if (event.getEntity() instanceof ServerPlayer player) {
			syncStoryline(player);
		}
	}

	@SubscribeEvent
	public void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (event.getEntity() instanceof ServerPlayer player) {
			syncStoryline(player);
		}
	}

	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		if (event.getEntity() instanceof ServerPlayer player) {
			syncStoryline(player);
		}
	}

	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event) {

		// Check the item that was picked up
		Item collectedItemId = event.getItem().getItem().getItem();

		// Retrieve the player's storyline capability
		event.getEntity().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			// Iterate through the active quests
			for (Saga saga : playerStoryline.getActiveSagas()) {
				for (Quest quest : saga.getAvailableQuests()) {
					// Check each objective in the quest
					for (Objective objective : quest.getAllObjectives()) {
						if (objective instanceof ObjectiveCollectItem collectObjective) {
							// Pass the collected item to the objective
							collectObjective.onItemCollected(collectedItemId);
						}
					}
				}
			}
		});
	}

	@SubscribeEvent
	public void onMobKill(LivingDeathEvent event) {

		if (event.getSource().getEntity() instanceof Player player) {
			//Check the mob that was killed
			Entity mobEntity = event.getEntity();
			//Retrieve the player's storyline capability
			player.getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
				//Iterate through the active quests
				for (Saga saga : playerStoryline.getActiveSagas()) {
					for (Quest quest : saga.getAvailableQuests()) {
						//Check each objective in the quest
						for (Objective objective : quest.getAllObjectives()) {
							if (objective instanceof ObjectiveKillEnemy killObjective) {
								killObjective.onEnemyKilled(mobEntity);
							}
						}
					}
				}
			});
		}
	}

	//Makes it less resource demaning than checking every tick / coordinate or location
	@SubscribeEvent
	public void onAdvancement(AdvancementEvent.AdvancementEarnEvent event) {

		DebugUtils.dmzLog("Advancement: " + event.getAdvancement().getId() + " earned by " + event.getEntity().getName().getString());

		//Retrieve the player's storyline capability
		event.getEntity().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			//Iterate through the active quests
			for (Saga saga : playerStoryline.getActiveSagas()) {
				for (Quest quest : saga.getAvailableQuests()) {
					//Check each objective in the quest
					for (Objective objective : quest.getAllObjectives()) {
						if (objective instanceof ObjectiveGetToLocation locationObjective) {
							locationObjective.advancementTranslator(event.getAdvancement(), "location");
						} else if (objective instanceof ObjectiveGetToBiome biomeObjective) {
							biomeObjective.advancementTranslator(event.getAdvancement(), "biome");
						}
					}
				}
			}
		});
	}

	@SubscribeEvent
	public void onPlayerCloned(PlayerEvent.Clone event) {

		event.getOriginal().reviveCaps();
		IDRegistry.clearAllIds(); // Clear all IDs because then they get re-registered

		event.getEntity().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline ->
				event.getOriginal().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(originalPlayerStoryline ->
						playerStoryline.loadNBTData(originalPlayerStoryline.saveNBTData()))
		);


		event.getOriginal().invalidateCaps();

	}

	@SubscribeEvent
	public static void onTrack(PlayerEvent.StartTracking event) {
		var trackingplayer = event.getEntity();
		if (!(trackingplayer instanceof ServerPlayer serverplayer)) return;

		var tracked = event.getTarget();
		if (tracked instanceof ServerPlayer trackedplayer) {
			tracked.getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(cap -> ModMessages.sendToPlayer(new StorylineSyncS2C(trackedplayer), serverplayer));
		}
	}

	public static void syncStoryline(Player player) {
		ModMessages.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new StorylineSyncS2C(player));

	}

}
