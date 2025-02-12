package com.yuseix.dragonminez.init.entity.custom.saiyansaga;

import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.init.entity.custom.SagaEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.FriezaSoldierEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekianEntity;
import com.yuseix.dragonminez.init.entity.custom.projectil.KiSmallBallProjectil;
import com.yuseix.dragonminez.init.entity.goals.MoveToSurfaceGoal;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import org.joml.Vector3f;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class NappaEntity extends SagaEntity implements GeoEntity {

    private static final EntityDataAccessor<Boolean> IS_CHARGING_ATTACK = SynchedEntityData.defineId(NappaEntity.class, EntityDataSerializers.BOOLEAN);
    private int cooldownKiAttack = 60; //ticks
    private int talkCooldown = getRandomTalkCooldown(); // Cooldown de frases aleatorias
    private int chargeTicks = 0; // Contador para la carga del ataque

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final RawAnimation DISPAROCARGA = RawAnimation.begin().thenPlay("animation.nappa.shoot");

    public NappaEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.entityData.define(IS_CHARGING_ATTACK, false);
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0D)
                .add(Attributes.ATTACK_DAMAGE, 80.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28F).build();
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
            double heightDifference = target.getY() - this.getY();

            double distance = this.distanceTo(target);

            // Si el jugador está a más de 3 bloques, el cooldown baja
            if (distance > 3 && cooldownKiAttack > 0) {
                cooldownKiAttack--;
            }

            if(cooldownKiAttack < 30){
                spawnPurpleParticles();
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

            if (heightDifference > 1.9) {
                this.setNoGravity(true);

                double targetX = target.getX();
                double targetY = target.getY()-1.2; // Ajusta esto según la altura deseada
                double targetZ = target.getZ();

                double horizontalSpeedFactor = 0.003;
                double horizontalSpeedX = (targetX - this.getX()) * horizontalSpeedFactor;
                double horizontalSpeedZ = (targetZ - this.getZ()) * horizontalSpeedFactor;

                this.getMoveControl().setWantedPosition(targetX, targetY, targetZ, 1.0);

                double verticalSpeed = 0.01;
                this.setDeltaMovement(this.getDeltaMovement().add(horizontalSpeedX, verticalSpeed, horizontalSpeedZ));

            } else {
                this.setNoGravity(false);
                double verticalSpeedDown = -0.01;
                this.setDeltaMovement(this.getDeltaMovement().add(0, verticalSpeedDown, 0));
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
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 15.0F));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
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

        KiSmallBallProjectil kiBlast = new KiSmallBallProjectil(MainEntity.KI_BLAST.get(), this.level());

        //Aplicar el owner normal para que diga que te mato el
        kiBlast.setOwner(this);

        //Aplicar el owner uuid custom q hice para que no danes a tu equipo
        kiBlast.setOwnerUUID(this.getUUID());

        //Color de esfera de adentro
        kiBlast.setColor(16773779);
        //Color de borde
        kiBlast.setColorBorde(16771668);

        kiBlast.setVelocidad(1.5f);

        kiBlast.setDamage(80.0F);

        // Configura la posición inicial del proyectil en el nivel de los ojos del lanzador
        kiBlast.setPos(this.getX(), this.getEyeY() - 0.8, this.getZ());

        // Configura la dirección del movimiento del proyectil hacia el objetivo
        kiBlast.shoot(dx, dy, dz, kiBlast.getVelocidad(), 0);
        this.playSound(MainSounds.KIBLAST_ATTACK.get(), 1.0F, 1.0F);

        // Añade el proyectil al mundo
        this.level().addFreshEntity(kiBlast);
    }

    private void spawnPurpleParticles() {
        ServerLevel serverLevel = (ServerLevel) this.level();
        for (int i = 0; i < 10; i++) {

            double offsetX = (this.getRandom().nextDouble() - 0.5) * 2.0; // Movimiento aleatorio en el eje X
            double offsetY = (this.getRandom().nextDouble() - 0.5) * 2.0; // Movimiento aleatorio en el eje Y
            double offsetZ = (this.getRandom().nextDouble() - 0.5) * 2.0; // Movimiento aleatorio en el eje Z


            DustParticleOptions dustOptions = new DustParticleOptions(
                    new Vector3f(255f /255f, 115f /255f, 253f /255f), // Color morado (RGB)
                    1.0f  // Tamaño de la partícula
            );
            serverLevel.sendParticles((ServerPlayer) this.getTarget(),
                    dustOptions,
                    true,
                    this.getX() + offsetX,
                    this.getY() + offsetY + 1.0,  // Asegúrate de que las partículas sean visibles
                    this.getZ() + offsetZ,
                    10,
                    0.0, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "disparo", this::disparoPredicate));

    }

    private PlayState disparoPredicate(AnimationState<NappaEntity> animationState) {
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
            controller.setAnimation(RawAnimation.begin().then("animation.nappa.walk", Animation.LoopType.LOOP));
        } else {
            controller.setAnimation(RawAnimation.begin().then("animation.nappa.idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
