package com.yuseix.dragonminez.stats.storymode;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.events.StoryEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class DMZStoryCapability {
	public static final Capability<DMZStoryCapability> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
	private String currentQuestId;
	private String currentSaga;
	private final Map<String, Integer> entityKillCounts = new HashMap<>();
	private final Set<String> completedQuests = new HashSet<>();
	private boolean structureFound = false;
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
	public boolean isStructureFound() {
		return structureFound;
	}
	public void setStructureFound(boolean found) {
		this.structureFound = found;
	}
	public boolean hasRequiredItem() {
		return hasRequiredItem;
	}
	public void setHasRequiredItem(boolean hasItem) {
		this.hasRequiredItem = hasItem;
	}

	public void addKill(String entity) {
		entityKillCounts.put(entity, entityKillCounts.getOrDefault(entity, 0) + 1);
	}

	public boolean isQuestComplete(DMZQuest quest, Player player) {
		if (this.completedQuests.contains(quest.getId())) {
			return true;
		}

		return quest.getRequirement().isFulfilled(player, this.entityKillCounts, this.structureFound, this.hasRequiredItem);
	}

	public void setQuestCompletion(String questId, boolean completed, Player player) {
		if (completed) {
			completedQuests.add(questId);
		} else {
			completedQuests.remove(questId);
		}
		StoryEvents.syncQuestData(player);
	}

	public void resetProgress() {
		entityKillCounts.clear();
		structureFound = false;
		hasRequiredItem = false;
	}

	public void resetAllProgress() {
		getCompletedQuests().clear();
		setCurrentSaga("saiyan");
		setCurrentQuestId("saiyQuest1");
	}

	public DMZQuest getAvailableQuest() {
		return DMZStoryRegistry.getQuest(currentQuestId);
	}

	public DMZQuest getNextQuest() {
		DMZQuest currentQuest = getAvailableQuest();
		return currentQuest != null ? DMZStoryRegistry.getQuest(currentQuest.getNextQuestId()) : null;
	}

	public boolean isSagaCompleted(String saga) {
		return DMZStoryRegistry.getTotalQuests(saga) == completedQuests.size();
	}

	public CompoundTag saveNBTData() {
		CompoundTag nbt = new CompoundTag();
		nbt.putString("CurrentSaga", currentSaga);
		nbt.putString("CurrentQuest", currentQuestId);
		CompoundTag killsTag = new CompoundTag();
		entityKillCounts.forEach(killsTag::putInt);
		nbt.put("Kills", killsTag);
		nbt.putBoolean("StructureFound", structureFound);
		nbt.putBoolean("HasRequiredItem", hasRequiredItem);

		return nbt;
	}

	public void loadNBTData(CompoundTag tag) {
		currentSaga = tag.getString("CurrentSaga");
		currentQuestId = tag.getString("CurrentQuest");
		CompoundTag killsTag = tag.getCompound("Kills");
		for (String key : killsTag.getAllKeys()) {
			entityKillCounts.put(key, killsTag.getInt(key));
		}
		structureFound = tag.getBoolean("StructureFound");
		hasRequiredItem = tag.getBoolean("HasRequiredItem");
	}
}