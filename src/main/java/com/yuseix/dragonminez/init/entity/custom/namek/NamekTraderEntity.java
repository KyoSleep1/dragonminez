package com.yuseix.dragonminez.init.entity.custom.namek;

import com.yuseix.dragonminez.init.entity.goals.VillageAlertSystem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class NamekTraderEntity extends Villager {

    public NamekTraderEntity(EntityType<? extends Villager> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.setPersistenceRequired();

    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 800.0D)
                .add(Attributes.ATTACK_DAMAGE, 100.5f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.25F).build();
    }

    @Override
    protected void registerGoals() {
        // Permite que la entidad flote en el agua
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 2.5D));

        // Camina de manera aleatoria evitando el agua
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        // Mirada aleatoria
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));

    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }

    @Override
    public void checkDespawn() {
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean isHurt = super.hurt(source, amount);

        if (isHurt && source.getEntity() instanceof Player) {
            Player player = (Player) source.getEntity();
            VillageAlertSystem.alertAll(player); // Alertar a todos los guerreros
        }

        return isHurt;
    }

}
