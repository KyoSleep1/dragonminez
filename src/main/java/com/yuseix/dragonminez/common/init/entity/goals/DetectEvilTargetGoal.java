package com.yuseix.dragonminez.common.init.entity.goals;

import com.yuseix.dragonminez.common.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

import java.util.EnumSet;

public class DetectEvilTargetGoal extends TargetGoal {
    private final Mob mob;
    private LivingEntity target;

    public DetectEvilTargetGoal(Mob pMob) {
        super(pMob, false, true);
        this.mob = pMob;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }
    @Override
    public boolean canUse() {
        // Busca un jugador cercano
        LivingEntity potentialTarget = this.mob.level().getNearestPlayer(this.mob, 10.0); // Rango de búsqueda de 10 bloques

        if (potentialTarget instanceof Player) {
            Player player = (Player) potentialTarget;

            // Obtén la capability del jugador
            LazyOptional<DMZStatsAttributes> capability = player.getCapability(DMZStatsCapabilities.INSTANCE);

            // Verifica si el jugador tiene la capability y cumple con la condición específica
            if (capability.isPresent()) {
                DMZStatsAttributes cap = capability.orElseThrow(IllegalArgumentException::new);

                if(cap.getIntValue("alignment") <= 40){
                    this.target = player; // Establece el jugador como objetivo si tiene la capability con la condición deseada
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public void start() {
        this.mob.setTarget(this.target);
        super.start();
    }
}
