package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.*;
import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.stats.storymode.DMZQuest;
import com.yuseix.dragonminez.stats.storymode.DMZStoryCapability;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class StoryEvents {

	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (!(event.getSource().getEntity() instanceof ServerPlayer player)) return;

		player.getCapability(DMZStoryCapability.INSTANCE).ifPresent(cap -> {
			String entity = EntityType.getKey(event.getEntity().getType()).toString();
			cap.addKill(entity);

			DMZQuest quest = cap.getAvailableQuest();
			if (quest != null && cap.isQuestComplete(quest, player)) {
				cap.getCompletedQuests().add(quest.getId());
				System.out.println("Se ha completado la quest " + quest.getId());

				if (quest.getNextQuestId() != null) {
					cap.setCurrentQuestId(quest.getNextQuestId());
					cap.setCurrentSaga(quest.getSagaId()); // Ahora es un String en vez de un enum
				}

				DMZQuest nextQuest = cap.getAvailableQuest();
				if (nextQuest != null) {
					System.out.println("La siguiente quest es " + nextQuest.getId());
				} else {
					System.out.println("No hay más quests disponibles en esta saga.");
				}

				onQuestComplete(player, quest.getId());
				cap.resetProgress();
				syncQuestData(player); // Sincronizamos datos con el cliente
			}
		});
	}

	public static void onQuestComplete(Player player, String questId) {
		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
		switch (questId) {
			case "saiyQuest1" -> {
				cap.addIntValue("tps", 350);
			}
			case "saiyQuest2" -> {
				cap.addIntValue("tps", 550);
			}
			case "saiyQuest3" -> {
				cap.addIntValue("tps", 750);
			}
		}
		});
	}

	@SubscribeEvent
	public void onPlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
		syncQuestData(event.getEntity());
		event.getEntity().refreshDimensions();
	}

	@SubscribeEvent
	public void playerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		syncQuestData(event.getEntity());
	}

	@SubscribeEvent
	public void playerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		syncQuestData(event.getEntity());
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

			});

		}
	}

	public static void syncQuestData(Player player) {
		ModMessages.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new StorySyncS2C(player));
	}
}
