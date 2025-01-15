package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.storyline.Objective;
import com.yuseix.dragonminez.storyline.Quest;
import com.yuseix.dragonminez.storyline.Saga;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveCollectItem;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveGetToBiome;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveGetToLocation;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveKillEnemy;
import com.yuseix.dragonminez.storyline.player.PlayerStorylineProvider;
import com.yuseix.dragonminez.utils.DebugUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StorylineEvents {

	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		DebugUtils.dmzLog("StorylineManager initialized");
	}

	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event) {

		// Check the item that was picked up
		Item collectedItemId = event.getItem().getItem().getItem();

		// Retrieve the player's storyline capability
		event.getEntity().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			// Iterate through the active quests
			for (Saga saga : playerStoryline.getAllSagas().values()) {
				for (Quest quest : saga.getQuests()) {
					if (!quest.isCompleted()) {
						// Check each objective in the quest
						for (Objective objective : quest.getObjectives()) {
							if (objective instanceof ObjectiveCollectItem collectObjective) {
								// Pass the collected item to the objective
								collectObjective.onItemCollected(collectedItemId);
							}
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
				for (Saga saga : playerStoryline.getAllSagas().values()) {
					for (Quest quest : saga.getQuests()) {
						if (!quest.isCompleted()) {
							//Check each objective in the quest
							for (Objective objective : quest.getObjectives()) {
								if (objective instanceof ObjectiveKillEnemy killObjective) {
									killObjective.onEnemyKilled(mobEntity);
								}
							}
						}
					}
				}
			});
		}
	}

	//Makes it less resource demaning than checking every tick / coordinate or location
	@SubscribeEvent
	public void onAdvancement(AdvancementEvent event) {

		//Retrieve the player's storyline capability
		event.getEntity().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			//Iterate through the active quests
			for (Saga saga : playerStoryline.getAllSagas().values()) {
				for (Quest quest : saga.getQuests()) {
					if (!quest.isCompleted()) {
						//Check each objective in the quest
						for (Objective objective : quest.getObjectives()) {
							if (objective instanceof ObjectiveGetToLocation locationObjective) {
								locationObjective.advancementTranslator(event.getAdvancement(), "location");
							} else if (objective instanceof ObjectiveGetToBiome biomeObjective) {
								biomeObjective.advancementTranslator(event.getAdvancement(), "biome");
							}
						}
					}
				}
			}
		});
	}

	@SubscribeEvent
	public void onPlayerCloned(PlayerEvent.Clone event) {

		CompoundTag nbt = new CompoundTag();

		event.getOriginal().reviveCaps();

		event.getEntity().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline ->
				event.getOriginal().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(originalPlayerStoryline ->
						playerStoryline.loadNBTData(originalPlayerStoryline.saveNBTData(nbt)))
		);


		event.getOriginal().invalidateCaps();

	}

}
