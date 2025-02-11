package com.yuseix.dragonminez.utils;

import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import net.minecraft.world.entity.player.Player;

public interface IDMZDatos {

    int calcularSTR(DMZStatsAttributes stats);

    int calcularDEF(DMZStatsAttributes stats, Player player);
    int calcularCON(DMZStatsAttributes stats);

    int calcularSTM(DMZStatsAttributes stats);

    int calcularKiPower(DMZStatsAttributes stats);

    int calcularENE(DMZStatsAttributes stats);

    int calcularKiConsume(DMZStatsAttributes stats);

    int calcularKiRegen(DMZStatsAttributes stats);

    double calcularMultiTotal(DMZStatsAttributes stats);

    double calcularMultiStat(DMZStatsAttributes stats, String stat);

    int calcularSTRCompleta(DMZStatsAttributes stats);
    int calcularDEFCompleta(DMZStatsAttributes stats);

    int calcularPWRCompleta(DMZStatsAttributes stats);

    int calcularCargaKi(DMZStatsAttributes stats);

}
