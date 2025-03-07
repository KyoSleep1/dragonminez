package com.yuseix.dragonminez.stats.storymode;

import com.yuseix.dragonminez.init.MainEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DMZStoryRegistry {
    private static final Map<String, DMZQuest> QUESTS = new HashMap<>();
    private static final Map<String, DMZSaga> SAGAS = new HashMap<>();

    public static void registerSagas() {
        registerSaga("saiyan", "Saiyan Saga");
        registerSaga("namek", "Namek Saga");
        registerSaga("android", "Android Saga");
    }

    public static void registerQuests() {
        registerQuest(new DMZQuest(
                "battle_with_raditz",
                new QuestRequirement(Map.of(MainEntity.RADITZ_SAGA.get().toString(), 1), null, "minecraft:dragon_ball", null),
                "battle_with_nappa",
                "saiyan"
        ));

        registerQuest(new DMZQuest(
                "battle_with_nappa",
                new QuestRequirement(Map.of(MainEntity.NAPPA_SAGA.get().toString(), 1), "minecraft:plains", null, "minecraft:stronghold"),
                "battle_with_cui",
                "saiyan"
        ));
    }

    public static void registerAll(){
        registerQuests();
        registerSagas();
    }

    public static void registerSaga(String id, String displayName) {
        SAGAS.put(id, new DMZSaga(id, displayName));
    }

    public static void registerQuest(DMZQuest quest) {
        QUESTS.put(quest.getId(), quest);
    }

    public static DMZSaga getSaga(String id) {
        return SAGAS.get(id);
    }

    public static Collection<DMZSaga> getAllSagas() {
        return SAGAS.values();
    }

    public static DMZQuest getQuest(String questId) {
        return QUESTS.get(questId);
    }

    public static int getTotalQuests(String sagaId) {
        return (int) QUESTS.values().stream().filter(q -> q.getSagaId().equals(sagaId)).count();
    }
}
