package com.yuseix.dragonminez.common.util;

import com.yuseix.dragonminez.common.stats.DMZStatsAttributes;
import net.minecraft.world.entity.player.Player;

public interface IDMZDatos {

    int calcStrength(DMZStatsAttributes stats);

    int calcDefense(DMZStatsAttributes stats, Player player);
    int calcConstitution(DMZStatsAttributes stats);

    int calcStamina(DMZStatsAttributes stats);

    int calcKiPower(DMZStatsAttributes stats);

    int calcEnergy(DMZStatsAttributes stats);

    int calcKiConsume(DMZStatsAttributes stats);

    int calcKiRegen(DMZStatsAttributes stats);

    double calcTotalMultiplier(DMZStatsAttributes stats);

    double calcStatMultiplier(DMZStatsAttributes stats, String stat);

    int calcMultipliedStrength(DMZStatsAttributes stats);
    int calcMultipliedDefense(DMZStatsAttributes stats);

    int calcMultipliedKiPower(DMZStatsAttributes stats);

    int calcKiCharge(DMZStatsAttributes stats);

}
