package com.yuseix.dragonminez.stats.storymode;

import com.yuseix.dragonminez.init.MainEntity;

import java.util.HashMap;
import java.util.Map;

public class DMZQuestRegistry {
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
