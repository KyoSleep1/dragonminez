package com.yuseix.dragonminez.common.stats.storymode;

import com.yuseix.dragonminez.common.init.MainEntity;
import com.yuseix.dragonminez.common.init.MainItems;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DMZStoryRegistry {
    private static final Map<String, DMZQuest> QUESTS = new HashMap<>();
    private static final Map<String, DMZSaga> SAGAS = new HashMap<>();

    public static void registerSagas() {
        registerSaga("saiyan");
        registerSaga("namek");
        registerSaga("android");
    }

    public static void registerQuests() {
        registerQuest(new DMZQuest(
                "saiyQuest1",
                new QuestRequirement(null, null, null, "Kame House"),
                "saiyQuest2",
                "saiyan"
        ));

        registerQuest(new DMZQuest(
                "saiyQuest2",
                new QuestRequirement(Map.of(MainEntity.RADITZ_SAGA.get().toString(), 1), "minecraft:plains", null, null),
                "saiyQuest3",
                "saiyan"
        ));

        registerQuest(new DMZQuest(
                "saiyQuest3",
                new QuestRequirement(Map.of(MainEntity.SAIBAMAN.get().toString(), 1, MainEntity.KAIWAREMAN.get().toString(), 1, MainEntity.COPYMAN.get().toString(), 1,
                        MainEntity.JINKOUMAN.get().toString(), 1, MainEntity.KYUKONMAN.get().toString(), 1, MainEntity.TENNENMAN.get().toString(), 1),
                        "minecraft:plains", null, null),
                "saiyQuest4",
                "saiyan"
        ));

        registerQuest(new DMZQuest(
                "saiyQuest4",
                new QuestRequirement(Map.of(MainEntity.NAPPA_SAGA.get().toString(), 1), "minecraft:plains", null, null),
                "saiyQuest5",
                "saiyan"
        ));

        registerQuest(new DMZQuest(
                "saiyQuest5",
                new QuestRequirement(Map.of(MainEntity.NAPPA_SAGA.get().toString(), 1), "minecraft:plains", null, null),
                "saiyQuest6",
                "saiyan"
        ));

        registerQuest(new DMZQuest(
                "saiyQuest6",
                new QuestRequirement(Map.of(MainEntity.VEGETA_SAIYAN.get().toString(), 1), "dragonminez:rocky", null, null),
                "saiyQuest7",
                "saiyan"
        ));

        registerQuest(new DMZQuest(
                "saiyQuest7",
                new QuestRequirement(Map.of(MainEntity.VEGETA_OZARU.get().toString(), 1), "dragonminez:rocky", null, null),
                "saiyQuest8",
                "saiyan"
        ));

        registerQuest(new DMZQuest(
                "saiyQuest8",
                new QuestRequirement(Map.of(MainEntity.VEGETA_SAIYAN.get().toString(), 1), "dragonminez:rocky", null, null),
                "saiyQuest9",
                "saiyan"
        ));

        registerQuest(new DMZQuest(
                "saiyQuest9",
                new QuestRequirement(null, "dragonminez:ajissa_plains", null, null),
                "namekQuest1", // Se asigna la siguiente quest aunque la saga no esté hecha ya q si no, se repite en bucle la quest 9 por alguna razón kekw
                "saiyan"
        ));

        registerQuest(new DMZQuest(
                 "namekQuest1", // Los objetivos son intencionales, para q la misión no pueda ser completada por error.
                new QuestRequirement(Map.of(MainEntity.FRIEZA_SOLDIER01.get().toString(), 3, MainEntity.FRIEZA_SOLDIER02.get().toString(), 3,
                        MainEntity.FRIEZA_SOLDIER03.get().toString(), 3), "minecraft:the_end", Map.of(MainItems.INVENCIBLE_ARMOR_CHESTPLATE.get().toString(), 3), null),
                "namekQuest2",
                "namek"
        ));
    }

    public static void registerAll(){
        registerQuests();
        registerSagas();
    }

    public static void registerSaga(String id) {
        SAGAS.put(id, new DMZSaga(id));
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

    public static Collection<DMZQuest> getQuestsBySaga(String sagaId) {
        return QUESTS.values().stream()
                .filter(q -> q.getSagaId().equals(sagaId))
                .collect(Collectors.toList());
    }
}
