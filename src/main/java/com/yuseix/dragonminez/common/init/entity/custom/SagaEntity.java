package com.yuseix.dragonminez.common.init.entity.custom;

import com.yuseix.dragonminez.common.init.entity.custom.namek.NamekianEntity;
import com.yuseix.dragonminez.common.init.entity.goals.MoveToSurfaceGoal;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SagaEntity extends Monster {


    public SagaEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPersistenceRequired();
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0D)
                .add(Attributes.ATTACK_DAMAGE, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23F).build();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 15.0F));
        this.goalSelector.addGoal(4, new MoveToSurfaceGoal(this));

        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, NamekianEntity.class, true));
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(10, new HurtByTargetGoal(this));
    }

    protected int getRandomTalkCooldown() {
        return this.random.nextInt(100) + 300; // Entre 100 y 300 ticks (~5 a 15 segundos)
    }

    protected void sayRandomPhrase() {
        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        // Buscar jugadores en un radio de 15 bloques
        for (Player player : serverLevel.players()) {
            if (player.distanceTo(this) <= 15) {
                player.sendSystemMessage(Component.literal("This is a line."));
            }
        }
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }
}
