package com.yuseix.dragonminez.utils;

import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import net.minecraft.world.entity.player.Player;

public interface IDMZDatos {

    int calcularSTR(DMZStatsAttributes stats);

    int calcularDEF(Player player, int raza, int StatDEF, int Transformation, int powerRelease, String clase, boolean majinOn,
                    boolean mightfruit);
    int calcularCON(int raza, int StatCON, float vidaMC, String clase);

    int calcularSTM(int raza, int maxCON);

    int calcularKiPower(int raza, int StatPWR, int Transformation, int PowerRelease, String clase,
                        boolean majinOn, boolean mightfruit);

    int calcularENE(int raza, int StatENE, String clase);

    int calcularKiConsume(int raza, int StatENE, int form);

    int calcularKiRegen(int raza, int EnergiaTotal, String clase);

    double calcularMultiTotal(int raza, int transformacion, boolean majinOn, boolean mightfruit);

    double calcularMultiStat(int raza, int transformacion, String stat, boolean majinOn,
                             boolean mightfruit);

    int calcularSTRCompleta(int raza, int transformacion, int statStr, boolean majinOn,
                            boolean mightfruit);
    int calcularDEFCompleta(int raza, int transformacion, int statDef, boolean majinOn,
                            boolean mightfruit);

    int calcularPWRCompleta(int raza, int transformacion, int statPwr, boolean majinOn,
                            boolean mightfruit);

    int calcularCargaKi(int EnergiaTotal, String clase);

}
