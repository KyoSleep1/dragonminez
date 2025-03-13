package com.yuseix.dragonminez.common.init.entity.goals;

import com.yuseix.dragonminez.common.init.entity.custom.namek.NamekWarrior02Entity;
import com.yuseix.dragonminez.common.init.entity.custom.namek.NamekWarriorEntity;
import net.minecraft.world.entity.player.Player;

import java.util.HashSet;
import java.util.Set;

public class VillageAlertSystem {
    private static final Set<NamekWarriorEntity> warriors = new HashSet<>();
    private static final Set<NamekWarrior02Entity> warriors02 = new HashSet<>();

    public static void registerWarrior(NamekWarriorEntity warrior) {
        warriors.add(warrior);
    }

    public static void registerWarrior(NamekWarrior02Entity warrior) {
        warriors02.add(warrior);
    }

    public static void unregisterWarrior(NamekWarriorEntity warrior) {
        warriors.remove(warrior);
    }

    public static void unregisterWarrior(NamekWarrior02Entity warrior) {
        warriors02.remove(warrior);
    }

    public static void alertAll(Player player) {
        for (NamekWarriorEntity warrior : warriors) {
            warrior.setTarget(player);
        }
        for (NamekWarrior02Entity warrior : warriors02) {
            warrior.setTarget(player);
        }
    }
}
