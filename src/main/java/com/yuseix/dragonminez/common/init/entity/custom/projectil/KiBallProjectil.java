package com.yuseix.dragonminez.common.init.entity.custom.projectil;

import com.yuseix.dragonminez.common.init.particles.particleoptions.KiLargeParticleOptions;
import com.yuseix.dragonminez.common.init.particles.particleoptions.KiSmallParticleOptions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class KiBallProjectil extends KiAttacksEntity {

    public KiBallProjectil(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            int color = this.getColorBorde();
            this.level().addParticle(new KiSmallParticleOptions(color), this.getX(), this.getY(), this.getZ(), 0, 0, 0);
            this.level().addParticle(new KiLargeParticleOptions(color), this.getX(), this.getY(), this.getZ(), 0, 0, 0);

        }
    }
}
