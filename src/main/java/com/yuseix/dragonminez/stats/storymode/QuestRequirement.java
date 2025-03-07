package com.yuseix.dragonminez.stats.storymode;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestRequirement {
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

    public List<Component> getAllObjectives() {
        List<Component> objectives = new ArrayList<>();

        if (requiredKills != null && !requiredKills.isEmpty()) {
            for (Map.Entry<String, Integer> entry : requiredKills.entrySet()) {
                String mobName = entry.getKey();
                int killCount = entry.getValue();
                objectives.add(Component.translatable("dmz.storyline.objective.kill_enemy", killCount, mobName));
            }
        }

        if (requiredBiome != null) {
            objectives.add(Component.translatable("dmz.storyline.objective.get_to_biome", requiredBiome));
        }

        if (requiredItem != null) {
            objectives.add(Component.translatable("dmz.storyline.objective.collect_item", requiredItem));
        }

        if (requiredStructure != null) {
            objectives.add(Component.translatable("dmz.storyline.objective.get_to_location", requiredStructure));
        }

        return objectives;
    }
}
