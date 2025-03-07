package com.yuseix.dragonminez.init.entity.custom.saiyansaga;

import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.init.entity.custom.SagaEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekianEntity;
import com.yuseix.dragonminez.init.entity.custom.projectil.KiBallProjectil;
import com.yuseix.dragonminez.init.entity.goals.MoveToSurfaceGoal;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class OzaruVegetaEntity extends SagaEntity implements GeoEntity {

    private static final EntityDataAccessor<Boolean> IS_CHARGING_ATTACK = SynchedEntityData.defineId(OzaruVegetaEntity.class, EntityDataSerializers.BOOLEAN);
    private int cooldownKiAttack = 60; //ticks
    private int talkCooldown = getRandomTalkCooldown(); // Cooldown de frases aleatorias
    private int chargeTicks = 0; // Contador para la carga del ataque

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final RawAnimation DISPAROCARGA = RawAnimation.begin().thenPlay("animation.ozaruvegeta.gun");

    public OzaruVegetaEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.entityData.define(IS_CHARGING_ATTACK, false);
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0D)
                .add(Attributes.ATTACK_DAMAGE, 80.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5F).build();
    }

    public boolean isChargingAttack() {
        return this.entityData.get(IS_CHARGING_ATTACK);
    }

    public void setChargingAttack(boolean isCharging) {
        this.entityData.set(IS_CHARGING_ATTACK, isCharging);
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = this.getTarget();

        if (target != null) {
            double distance = this.distanceTo(target);

            // Si el jugador está a más de 3 bloques, el cooldown baja
            if (distance > 3 && cooldownKiAttack > 0) {
                cooldownKiAttack--;
            }

            // Si el cooldown llega a 0, lanza el ataque
            if (cooldownKiAttack == 0) {
                if (!isChargingAttack()) {
                    setChargingAttack(true);
                    chargeTicks = 40; // 2 segundos (40 ticks)
                }
            }

            if (isChargingAttack() && chargeTicks > 0) {
                chargeTicks--;
                if (chargeTicks == 0) {
                    launchKiAttack();
                    cooldownKiAttack = 120;
                    setChargingAttack(false);
                }
            }

        }

        if (talkCooldown > 0) {
            talkCooldown--;
        } else {
            sayRandomPhrase();
            talkCooldown = getRandomTalkCooldown();
            this.setNoGravity(false);
        }
    }

    @Override
    public void die(DamageSource pDamageSource) {
        super.die(pDamageSource);

        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        // Buscar jugadores en un radio de 15 bloques
        for (Player player : serverLevel.players()) {
            if (player.distanceTo(this) <= 15) {
                player.sendSystemMessage(Component.translatable("entity.dragonminez.saga_nappa.die_line"));
            }
        }

    }

    @Override
    protected void sayRandomPhrase() {
        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        String[] phrases = {
                "entity.dragonminez.saga_nappa.line1",
                "entity.dragonminez.saga_nappa.line2"
        };

        String selectedPhrase = phrases[this.random.nextInt(phrases.length)];

        // Buscar jugadores en un radio de 15 bloques
        for (Player player : serverLevel.players()) {
            if (player.distanceTo(this) <= 15) {
                player.sendSystemMessage(Component.translatable(selectedPhrase));
            }
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.5F, false));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 55.0F));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.5f));
        this.goalSelector.addGoal(6, new MoveToSurfaceGoal(this));

        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, NamekianEntity.class, true));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(10, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));}

    private void launchKiAttack() {
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
        kiBlast.setColor(15382015);
        //Color de borde
        kiBlast.setColorBorde(13262837);

        kiBlast.setVelocidad(1.2f);

        kiBlast.setDamage(80.0F);
        kiBlast.setTamano(3.5f);

        // Configura la posición inicial del proyectil en el nivel de los ojos del lanzador
        kiBlast.setPos(this.getX(), this.getEyeY() - 0.8, this.getZ());

        // Configura la dirección del movimiento del proyectil hacia el objetivo
        kiBlast.shoot(dx, dy, dz, kiBlast.getVelocidad(), 1);
        this.playSound(MainSounds.KIBLAST_ATTACK.get(), 1.0F, 1.0F);

        // Añade el proyectil al mundo
        this.level().addFreshEntity(kiBlast);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "disparo", 0, this::disparoPredicate));
        controllerRegistrar.add(new AnimationController<>(this, "ataque", 0, this::ataquePredicate));
        controllerRegistrar.add(new AnimationController<>(this, "cola", 0, this::colaPredicate));

    }

    private <T extends GeoAnimatable> PlayState colaPredicate(AnimationState<T> tAnimationState) {
        AnimationController<?> controller = tAnimationState.getController();

        controller.setAnimation(RawAnimation.begin().then("animation.ozaruvegeta.tail1", Animation.LoopType.LOOP));

        return PlayState.CONTINUE;
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof Player) {
            this.swinging = true;
        }
        return super.hurt(source, amount);
    }

    @Override
    public void swing(InteractionHand hand, boolean updateSelf) {
        super.swing(hand, updateSelf);
        this.swinging = true;
    }


    private PlayState disparoPredicate(AnimationState<OzaruVegetaEntity> animationState) {
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
            controller.setAnimation(RawAnimation.begin().then("animation.ozaruvegeta.walk", Animation.LoopType.LOOP));
        } else {
            controller.setAnimation(RawAnimation.begin().then("animation.ozaruvegeta.idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }

    private PlayState ataquePredicate(AnimationState<OzaruVegetaEntity> animationState) {
        if (this.swinging) {
            return animationState.setAndContinue(RawAnimation.begin().then("animation.ozaruvegeta.attack", Animation.LoopType.PLAY_ONCE));
        }

        animationState.getController().forceAnimationReset();
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
