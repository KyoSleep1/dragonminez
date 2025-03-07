package com.yuseix.dragonminez.stats.storymode;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestRequirement {
    private final Map<String, Integer> requiredKills;
    private final String requiredBiome;
    private final Map<String, Integer>  requiredItems;
    private final String requiredStructure;

    public QuestRequirement(Map<String, Integer> requiredKills, String requiredBiome, Map<String, Integer> requiredItems, String requiredStructure) {
        this.requiredKills = requiredKills;
        this.requiredBiome = requiredBiome;
        this.requiredItems = requiredItems;
		this.requiredStructure = requiredStructure;
    }

    public boolean isFulfilled(Player player, Map<String, Integer> entityKillCounts, boolean structureFound, Map<String, Integer> itemCollectionCounts) {
        if (requiredKills != null) {
            for (Map.Entry<String, Integer> entry : requiredKills.entrySet()) {
                if (entityKillCounts.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                    return false;
                }
            }
        }

        if (requiredBiome != null && !player.level().getBiome(player.blockPosition()).toString().equals(requiredBiome)) {
            return false;
        }

        if (requiredItems != null) {
            for (Map.Entry<String, Integer> entry : requiredItems.entrySet()) {
                if (itemCollectionCounts.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                    return false;
                }
            }
        }

        if (requiredStructure != null && !structureFound) {
            return false;
        }

        return true;
    }

    public Map<String, Integer> getRequiredKills() {
        return requiredKills;
    }

    public String getRequiredBiome() {
        return requiredBiome;
    }

    public Map<String, Integer> getRequiredItems() {
        return requiredItems;
    }

    public String getRequiredStructure() {
        return requiredStructure;
    }

    public List<Component> getAllObjectives() {
        List<Component> objectives = new ArrayList<>();

        if (requiredKills != null && !requiredKills.isEmpty()) {
            for (Map.Entry<String, Integer> entry : requiredKills.entrySet()) {
                String mobName = entry.getKey();
                int killCount = entry.getValue();
                objectives.add(Component.translatable("dmz.storyline.objective.kill_enemy", mobName, killCount));
            }
        }

        if (requiredBiome != null) {
            String langBiome = "";
            switch (requiredBiome) {
                case "minecraft:plains" -> langBiome = "Plains";
                case "dragonminez:rocky" -> langBiome = "Rocky";
                case "dragonminez:ajissa_plains" -> langBiome = "Ajissa";
            }
            objectives.add(Component.translatable("dmz.storyline.objective.get_to_biome", langBiome));
        }

        if (requiredItems != null) {
            for (Map.Entry<String, Integer> entry : requiredItems.entrySet()) {
                String itemName = entry.getKey();
                int itemCount = entry.getValue();
                objectives.add(Component.translatable("dmz.storyline.objective.collect_item", itemName, itemCount));
            }
        }

        if (requiredStructure != null) {
            objectives.add(Component.translatable("dmz.storyline.objective.get_to_location", requiredStructure));
        }

        return objectives;
    }
}
