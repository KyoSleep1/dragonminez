package com.yuseix.dragonminez.stats.storymode;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.DMZCompletedQuestsSyncS2C;
import com.yuseix.dragonminez.network.S2C.StorySyncS2C;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class DMZStoryCapability {
	public static final Capability<DMZStoryCapability> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
	private String currentQuestId;
	private String currentSaga;
	private final Map<String, Integer> entityKillCounts = new HashMap<>();
	private final Map<String, Integer> itemCollectionCounts = new HashMap<>();
	private final Set<String> completedQuests = new HashSet<>();
	private final Set<String> completedKillObjectives = new HashSet<>();
	private boolean structureFound = false;
	private boolean biomeFound = false;
	private boolean hasRequiredItem = false;

	public DMZStoryCapability(String startQuestId, String startSaga) {
		this.currentQuestId = startQuestId;
		this.currentSaga = startSaga;
	}

	public Map<String, Integer> getEntityKillCounts() {
		return entityKillCounts;
	}
	public String getCurrentQuestId() {
		return currentQuestId;
	}
	public void setCurrentQuestId(String QuestId) {
		this.currentQuestId = QuestId;
	}
	public String getCurrentSaga() {
		return currentSaga;
	}
	public void setCurrentSaga(String saga) {
		this.currentSaga = saga;
	}
	public Set<String> getCompletedQuests() {
		return completedQuests;
	}
	public boolean isBiomeFound() {
		return biomeFound;
	}
	public void setBiomeFound(boolean found) {
		this.biomeFound = found;
	}
	public boolean isStructureFound() {
		return structureFound;
	}
	public void setStructureFound(boolean found) {
		this.structureFound = found;
	}
	public void setHasRequiredItem(boolean hasItem) {
		this.hasRequiredItem = hasItem;
	}
	public void addItemCollected(String itemId) {
		itemCollectionCounts.put(itemId, itemCollectionCounts.getOrDefault(itemId, 0) + 1);
	}
	public Map<String, Integer> hasCollectedItems() {
		return itemCollectionCounts;
	}
	public int getItemCollectedCount(String itemId) {
		return itemCollectionCounts.getOrDefault(itemId, 0);
	}
	public void addKill(String entity) {
		entityKillCounts.put(entity, entityKillCounts.getOrDefault(entity, 0) + 1);
	}
	public int getKillCount(String entity) {
		return entityKillCounts.getOrDefault(entity, 0);
	}
	public void setKillObjectiveComplete(String entityId) {
		completedKillObjectives.add(entityId);
	}
	public boolean isKillObjectiveComplete(String entityId) {
		return completedKillObjectives.contains(entityId);
	}

	public boolean isQuestComplete(DMZQuest quest, Player player) {
		if (this.completedQuests.contains(quest.getId())) return true;

		QuestRequirement requirement = quest.getRequirement();

		if (requirement.getRequiredKills() != null) {
			for (Map.Entry<String, Integer> entry : requirement.getRequiredKills().entrySet()) {
				if (!this.isKillObjectiveComplete(entry.getKey())) {
					return false;
				}
			}
		}

		if (requirement.getRequiredItems() != null) {
			for (Map.Entry<String, Integer> entry : requirement.getRequiredItems().entrySet()) {
				if (this.getItemCollectedCount(entry.getKey()) < entry.getValue()) {
					return false;
				}
			}
		}

		if (requirement.getRequiredBiome() != null && !player.level().getBiome(player.blockPosition()).toString().equals(requirement.getRequiredBiome())) {
			return false;
		}

		if (requirement.getRequiredStructure() != null && !this.structureFound) {
			return false;
		}

		return true;
	}

	public void setQuestCompletion(String questId, boolean completed, Player player) {
		if (completed) {
			completedQuests.add(questId);
		} else {
			completedQuests.remove(questId);
		}
		syncQuestData(player);
		syncCompletedQuests(player);
		clearProgress();
	}

	public void clearProgress() {
		if (getNextQuest() != null) {
			getEntityKillCounts().clear();
			completedKillObjectives.clear();
			itemCollectionCounts.clear();
			hasRequiredItem = false;
		}
	}

	public void resetAllProgress() {
		getCompletedQuests().clear();
		getEntityKillCounts().clear();
		completedKillObjectives.clear();
		itemCollectionCounts.clear();
		hasRequiredItem = false;
		setCurrentSaga("saiyan");
		setCurrentQuestId("saiyQuest1");
	}

	public DMZQuest getAvailableQuest() {
		return DMZStoryRegistry.getQuest(currentQuestId);
	}

	public DMZQuest getNextQuest() {
		DMZQuest currentQuest = getAvailableQuest();
		if (currentQuest.getNextQuestId() == null) {
			return null;
		}
		return DMZStoryRegistry.getQuest(currentQuest.getNextQuestId());
	}

	public boolean isSagaCompleted(String saga) {
		return DMZStoryRegistry.getTotalQuests(saga) == completedQuests.size();
	}

	public boolean isObjectiveComplete(Component objective, String questId) {
		DMZQuest quest = DMZStoryRegistry.getQuest(questId);
		if (quest == null) return false;
		if (this.completedQuests.contains(quest.getId())) return true;

		String key = "";
		QuestRequirement requirement = quest.getRequirement();
		if (objective.getContents() instanceof TranslatableContents translatable) {
			key = translatable.getKey();
		}

		if (key.equals("dmz.storyline.objective.kill_enemy")) {
			for (Map.Entry<String, Integer> entry : requirement.getRequiredKills().entrySet()) {
				String langKills = "";
				switch (entry.getKey()) {
					case "entity.dragonminez.saga_raditz" -> langKills = "Raditz";
					case "entity.dragonminez.saibaman" -> langKills = "Saibaman";
					case "entity.dragonminez.tennenman" -> langKills = "Tennenman";
					case "entity.dragonminez.kyukonman" -> langKills = "Kyukonman";
					case "entity.dragonminez.copyman" -> langKills = "Copyman";
					case "entity.dragonminez.jinkouman" -> langKills = "Jinkouman";
					case "entity.dragonminez.kaiwareman" -> langKills = "Kaiwareman";
					case "entity.dragonminez.saga_nappa" -> langKills = "Nappa";
					case "entity.dragonminez.saga_vegetaozaru" -> langKills = "Oozaru Vegeta";
					case "entity.dragonminez.saga_vegetasaiyan" -> langKills = "Vegeta";
				}
				int requiredCount = entry.getValue();
				int playerCount = entityKillCounts.getOrDefault(entry.getKey(), 0);
				if (objective.getString().contains(langKills) && playerCount >= requiredCount) {
					return true;
				}
			}
		}

		if (key.equals("dmz.storyline.objective.get_to_biome")) {
			return biomeFound;
		}

		if (key.equals("dmz.storyline.objective.collect_item")) {
			for (Map.Entry<String, Integer> entry : requirement.getRequiredItems().entrySet()) {
				String itemName = entry.getKey();
				int requiredCount = entry.getValue();
				int playerCount = itemCollectionCounts.getOrDefault(itemName, 0);
				if (objective.getString().contains(itemName) && playerCount >= requiredCount) {
					return true;
				}
			}
		}

		if (key.equals("dmz.storyline.objective.get_to_location")) {
			return structureFound;
		}

		return false;
	}


	public CompoundTag saveNBTData() {
		CompoundTag nbt = new CompoundTag();
		nbt.putString("CurrentSaga", currentSaga);
		nbt.putString("CurrentQuest", currentQuestId);

		CompoundTag killsTag = new CompoundTag();
		entityKillCounts.forEach(killsTag::putInt);
		nbt.put("Kills", killsTag);

		CompoundTag itemsTag = new CompoundTag();
		itemCollectionCounts.forEach(itemsTag::putInt);
		nbt.put("Items", itemsTag);

		nbt.putBoolean("StructureFound", structureFound);
		nbt.putBoolean("BiomeFound", biomeFound);
		nbt.putBoolean("HasRequiredItem", hasRequiredItem);

		ListTag completedQuestsTag = new ListTag();
		completedQuests.stream()
				.sorted()
				.forEach(questId -> completedQuestsTag.add(StringTag.valueOf(questId)));
		nbt.put("CompletedQuests", completedQuestsTag);

		return nbt;
	}

	public void loadNBTData(CompoundTag tag) {
		currentSaga = tag.getString("CurrentSaga");
		currentQuestId = tag.getString("CurrentQuest");

		CompoundTag killsTag = tag.getCompound("Kills");
		for (String key : killsTag.getAllKeys()) {
			entityKillCounts.put(key, killsTag.getInt(key));
		}

		CompoundTag itemsTag = tag.getCompound("Items");
		itemCollectionCounts.clear();
		for (String key : itemsTag.getAllKeys()) {
			itemCollectionCounts.put(key, itemsTag.getInt(key));
		}

		structureFound = tag.getBoolean("StructureFound");
		biomeFound = tag.getBoolean("BiomeFound");
		hasRequiredItem = tag.getBoolean("HasRequiredItem");

		completedQuests.clear();
		ListTag completedQuestsTag = tag.getList("CompletedQuests", Tag.TAG_STRING);
		completedQuestsTag.stream()
				.map(Tag::getAsString)
				.sorted()
				.forEach(completedQuests::add);
	}


	@SubscribeEvent
	public void onPlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
		syncQuestData(event.getEntity());
		syncCompletedQuests(event.getEntity());
		event.getEntity().refreshDimensions();
	}

	@SubscribeEvent
	public void playerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		syncQuestData(event.getEntity());
		syncCompletedQuests(event.getEntity());
		event.getEntity().refreshDimensions();
	}

	@SubscribeEvent
	public void playerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		syncQuestData(event.getEntity());
		syncCompletedQuests(event.getEntity());
	}

	@SubscribeEvent
	public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.register(DMZStoryCapability.class);
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

		syncQuestData(player);
		syncCompletedQuests(player);

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