package com.yuseix.dragonminez.stats.storymode;

import com.yuseix.dragonminez.DragonMineZ;
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
	private Saga currentSaga;
	private final Map<String, Integer> entityKillCounts = new HashMap<>();
	private final Set<String> completedQuests = new HashSet<>();
	private boolean structureFound = false;
	private boolean hasRequiredItem = false;

	public DMZStoryCapability(String startQuestId, Saga startSaga) {
		this.currentQuestId = startQuestId;
		this.currentSaga = startSaga;
	}

	public String getCurrentQuestId() { return currentQuestId; }
	public void setCurrentQuestId(String QuestId) { this.currentQuestId = QuestId; }
	public Saga getCurrentSaga() { return currentSaga; }
	public void setCurrentSaga(Saga saga) { this.currentSaga = saga; }
	public Set<String> getCompletedQuests() { return completedQuests; }
	public boolean isStructureFound() { return structureFound; }
	public void setStructureFound(boolean found) { this.structureFound = found; }
	public boolean hasRequiredItem() { return hasRequiredItem; }
	public void setHasRequiredItem(boolean hasItem) { this.hasRequiredItem = hasItem; }

	public void addKill(String entity) {
		entityKillCounts.put(entity, entityKillCounts.getOrDefault(entity, 0) + 1);
	}

	public boolean isQuestComplete(DMZQuest Quest, Player player) {
		return Quest.getRequirement().isFulfilled(player, entityKillCounts, structureFound, hasRequiredItem);
	}

	public void resetProgress() {
		entityKillCounts.clear();
		structureFound = false;
		hasRequiredItem = false;
	}

	public DMZQuest getAvailableQuest() {
		return DMZQuest.DMZQuestRegistry.getQuest(currentQuestId);
	}

	public DMZQuest getNextQuest() {
		DMZQuest currentQuest = getAvailableQuest();
		return currentQuest != null ? DMZQuest.DMZQuestRegistry.getQuest(currentQuest.getNextQuestId()) : null;
	}

	public boolean isSagaCompleted(Saga saga) {
		return DMZQuest.DMZQuestRegistry.getTotalQuests(saga) == completedQuests.size();
	}

	public enum Saga {
		SAIYAN("Saiyan Saga"),
		NAMEK("Namek Saga"),
		ANDROID("Android Saga"),
		CELL("Cell Saga"),
		BUU("Buu Saga");

		private final String displayName;

		Saga(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

	public CompoundTag saveNBTData() {
		CompoundTag nbt = new CompoundTag();
		nbt.putString("CurrentSaga", currentSaga.name());
		nbt.putString("CurrentQuest", currentQuestId);
		CompoundTag killsTag = new CompoundTag();
		entityKillCounts.forEach(killsTag::putInt);
		nbt.put("Kills", killsTag);
		nbt.putBoolean("StructureFound", structureFound);
		nbt.putBoolean("HasRequiredItem", hasRequiredItem);

		return nbt;
	}

	public void loadNBTData(CompoundTag tag) {
		currentSaga = Saga.valueOf(tag.getString("CurrentSaga"));
		currentQuestId = tag.getString("CurrentQuest");
		CompoundTag killsTag = tag.getCompound("Kills");
		for (String key : killsTag.getAllKeys()) {
			entityKillCounts.put(key, killsTag.getInt(key));
		}
		structureFound = tag.getBoolean("StructureFound");
		hasRequiredItem = tag.getBoolean("HasRequiredItem");
	}
}
