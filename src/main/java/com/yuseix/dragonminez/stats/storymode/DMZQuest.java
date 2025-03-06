package com.yuseix.dragonminez.stats.storymode;

import com.yuseix.dragonminez.init.MainEntity;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class DMZQuest {
	private final String id;
	private final QuestRequirement requirement;
	private final String nextQuestId;
	private final DMZStoryCapability.Saga saga;

	public DMZQuest(String id, QuestRequirement requirement, String nextQuestId, DMZStoryCapability.Saga saga) {
		this.id = id;
		this.requirement = requirement;
		this.nextQuestId = nextQuestId;
		this.saga = saga;
	}

	public String getId() {
		return id;
	}

	public QuestRequirement getRequirement() {
		return requirement;
	}

	public String getNextQuestId() {
		return nextQuestId;
	}

	public DMZStoryCapability.Saga getSaga() {
		return saga;
	}

	public static class QuestRequirement {
		private final Map<String, Integer> requiredKills;
		private final String requiredBiome;
		private final String requiredItem;
		private final String requiredStructure;

		public QuestRequirement(Map<String, Integer> requiredKills, String requiredBiome, String requiredItem, String requiredStructure) {
			this.requiredKills = requiredKills;
			this.requiredBiome = requiredBiome;
			this.requiredItem = requiredItem;
			this.requiredStructure = requiredStructure;
		}

		public boolean isFulfilled(Player player, Map<String, Integer> playerKills, boolean structureFound, boolean hasItem) {
			if (requiredKills != null) {
				for (Map.Entry<String, Integer> entry : requiredKills.entrySet()) {
					if (playerKills.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
						return false;
					}
				}
			}
			if (requiredBiome != null && !player.level().getBiome(player.blockPosition()).equals(requiredBiome)) {
				return false;
			}
			if (requiredItem != null && !hasItem) {
				return false;
			}
			return requiredStructure == null || structureFound;
		}

		public Map<String, Integer> getRequiredKills() {
			return requiredKills;
		}

		public String getRequiredBiome() {
			return requiredBiome;
		}

		public String getRequiredItem() {
			return requiredItem;
		}

		public String getRequiredStructure() {
			return requiredStructure;
		}
	}

	public static class DMZQuestRegistry {
		private static final Map<String, DMZQuest> Quests = new HashMap<>();

		public static void registerQuests() {
			Map<String, Integer> Quest1Enemies = Map.of(MainEntity.RADITZ_SAGA.get().toString(), 1);
			DMZQuest Quest1 = new DMZQuest("battle_with_raditz", new QuestRequirement(Quest1Enemies, null, "minecraft:dragon_ball", null), "battle_with_nappa", DMZStoryCapability.Saga.SAIYAN);

			Map<String, Integer> Quest2Enemies = Map.of(MainEntity.NAPPA_SAGA.get().toString(), 1);
			DMZQuest Quest2 = new DMZQuest("battle_with_nappa", new QuestRequirement(Quest2Enemies, "minecraft:plains", null, "minecraft:stronghold"), "battle_with_cui", DMZStoryCapability.Saga.SAIYAN);

			//DMZQuest Quest3 = new DMZQuest("battle_with_cui", new QuestRequirement(Map.of(MainEntity.CUI.get(), 1), null, null, null), null, DMZStoryCapability.Saga.NAMEK);

			Quests.put(Quest1.getId(), Quest1);
			Quests.put(Quest2.getId(), Quest2);
			//Quests.put(Quest3.getId(), Quest3);
		}

		public static DMZQuest getQuest(String questId) {
			return Quests.get(questId);
		}

		public static int getTotalQuests(DMZStoryCapability.Saga saga) {
			int count = (int) Quests.values().stream().filter(m -> m.getSaga() == saga).count();
			return count;
		}

	}
}

