package com.yuseix.dragonminez.init.entity.custom.saiyansaga;

import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.init.entity.custom.SagaEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekianEntity;
import com.yuseix.dragonminez.init.entity.custom.projectil.KiSmallBallProjectil;
import com.yuseix.dragonminez.init.entity.goals.MoveToSurfaceGoal;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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

public class RaditzEntity extends SagaEntity {

    private int cooldownKiAttack = 60; //ticks
    private int talkCooldown = getRandomTalkCooldown(); // Cooldown de frases aleatorias

    public RaditzEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0D)
                .add(Attributes.ATTACK_DAMAGE, 80.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28F).build();
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = this.getTarget();
        if (target != null) {
            double distance = this.distanceTo(target);

            // Si el jugador está a más de 6 bloques, el cooldown baja
            if (distance > 3) {
                if (cooldownKiAttack > 0) {
                    cooldownKiAttack--;
                }
            }

            // Si el cooldown llega a 0, lanza el ataque
            if (cooldownKiAttack == 0) {
                launchKiAttack();
                cooldownKiAttack = 120;
            }
        }

        if (talkCooldown > 0) {
            talkCooldown--;
        } else {
            sayRandomPhrase();
            talkCooldown = getRandomTalkCooldown();
        }
    }

    @Override
    public void die(DamageSource pDamageSource) {
        super.die(pDamageSource);

        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        // Buscar jugadores en un radio de 15 bloques
        for (Player player : serverLevel.players()) {
            if (player.distanceTo(this) <= 15) {
                player.sendSystemMessage(Component.translatable("entity.dragonminez.saga_raditz.die_line"));
            }
        }

    }

    @Override
    protected void sayRandomPhrase() {
        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        String[] phrases = {
                "entity.dragonminez.saga_raditz.line1",
                "entity.dragonminez.saga_raditz.line2"
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
        kiBlast.setColor(4402840);
        //Color de borde
        kiBlast.setColorBorde(3741278);

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
}
