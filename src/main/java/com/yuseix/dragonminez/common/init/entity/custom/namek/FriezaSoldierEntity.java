package com.yuseix.dragonminez.common.init.entity.custom.namek;

import com.yuseix.dragonminez.common.init.MainEntity;
import com.yuseix.dragonminez.common.init.entity.custom.projectil.KiBallProjectil;
import com.yuseix.dragonminez.common.init.entity.goals.MoveToSurfaceGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class FriezaSoldierEntity extends SoldierEntity implements GeoEntity, RangedAttackMob {

    private static final EntityDataAccessor<Boolean> IS_CHARGING_ATTACK = SynchedEntityData.defineId(FriezaSoldierEntity.class, EntityDataSerializers.BOOLEAN);
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private int attackAnimationCooldown = 40;
    private int chargeTime = 0;

    private static final RawAnimation DISPAROCARGA = RawAnimation.begin().thenPlay("animation.soldado1.disparo");


    public FriezaSoldierEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPersistenceRequired();

    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 350.0D)
                .add(Attributes.ATTACK_DAMAGE, 75.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23F).build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_CHARGING_ATTACK, false);

    }

    public boolean isChargingAttack() {
        return this.entityData.get(IS_CHARGING_ATTACK);
    }

    public void setChargingAttack(boolean isCharging) {
        this.entityData.set(IS_CHARGING_ATTACK, isCharging);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 15.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new MoveToSurfaceGoal(this));

        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, NamekianEntity.class, true));
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "disparo", this::disparoPredicate));

    }

    private PlayState disparoPredicate(AnimationState<FriezaSoldierEntity> animationState) {
        var cargardisparo = this.entityData.get(IS_CHARGING_ATTACK);

        if (cargardisparo) {
            return animationState.setAndContinue(DISPAROCARGA);
        }
        animationState.getController().forceAnimationReset();

        return PlayState.STOP;
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        AnimationController<?> controller = tAnimationState.getController();

        if (tAnimationState.isMoving()) {
            controller.setAnimation(RawAnimation.begin().then("animation.soldado1.walk", Animation.LoopType.LOOP));
        } else {
            controller.setAnimation(RawAnimation.begin().then("animation.soldado1.idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = this.getTarget();
        if (target != null) {
            double heightDifference = target.getY() - this.getY();

            // Si el jugador está muy alto, activa el modo de vuelo
            if (heightDifference > 1.5) {
                this.setNoGravity(true);

                // Mantén la misma posición horizontal que el jugador
                double targetX = target.getX();
                double targetY = target.getY()-1.5; // Ajusta esto según la altura deseada
                double targetZ = target.getZ();

                // Configura la posición deseada en el aire con control de altura
                this.getMoveControl().setWantedPosition(targetX, targetY, targetZ, 1.0);

                // Aplica movimiento vertical para flotar
                double verticalSpeed = 0.01; // Ajusta este valor para que el movimiento sea más suave
                this.setDeltaMovement(this.getDeltaMovement().add(0, verticalSpeed, 0));

            } else {
                // Si el jugador no está tan alto, mantén a la entidad en el suelo
                this.setNoGravity(false);

            }
        } else {
            this.setNoGravity(false);
        }


        if (attackAnimationCooldown > 0) {
            attackAnimationCooldown--;
        }

        if (isChargingAttack() && chargeTime > 0) {
            chargeTime--;
            if (chargeTime == 0) {
                launchFireball();
                setChargingAttack(false); // Desactiva la carga después del disparo
            }
        }

    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        if (attackAnimationCooldown <= 0 && !isChargingAttack()) {
            attackAnimationCooldown = 40;
            setChargingAttack(true); // Activa la carga de ataque
            chargeTime = 40;
        }
    }

    private void launchFireball() {
        LivingEntity target = this.getTarget();
        if (target == null) return;

        // Calcula la dirección hacia el objetivo
        double dx = target.getX() - this.getX();
        double dy = target.getEyeY() - this.getEyeY();
        double dz = target.getZ() - this.getZ();

        KiBallProjectil kiBlast = new KiBallProjectil(MainEntity.KI_BLAST.get(), this.level());

        //Aplicar el owner normal para que diga que te mato el
        kiBlast.setOwner(this);

        //Aplicar el owner uuid custom q hice para que no danes a tu equipo
        kiBlast.setOwnerUUID(this.getUUID());

        //Color de esfera de adentro
        kiBlast.setColor(13213672);
        //Color de borde
        kiBlast.setColorBorde(13198056);

        kiBlast.setVelocidad(0.7f);

        kiBlast.setDamage(130.0F);
        kiBlast.setTamano(0.7f);

        // Configura la posición inicial del proyectil en el nivel de los ojos del lanzador
        kiBlast.setPos(this.getX(), this.getEyeY() - 0.8, this.getZ());

        // Configura la dirección del movimiento del proyectil hacia el objetivo
        kiBlast.shoot(dx, dy, dz, kiBlast.getVelocidad(), 0); // `1.0F` es la velocidad; ajusta según sea necesario

        // Añade el proyectil al mundo
        this.level().addFreshEntity(kiBlast);
    }

}