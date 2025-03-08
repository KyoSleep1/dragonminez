package com.yuseix.dragonminez.init.entity.custom.projectil;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class KiAttacksEntity  extends ThrowableProjectile {

    private int lifetime = 0; // Lleva la cuenta del tiempo en ticks
    private int tickCounter = 0;

    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(KiAttacksEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> COLOR_BORDE = SynchedEntityData.defineId(KiAttacksEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DANO = SynchedEntityData.defineId(KiAttacksEntity.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Float> VELOCIDAD = SynchedEntityData.defineId(KiAttacksEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> TAMANO = SynchedEntityData.defineId(KiAttacksEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(KiAttacksEntity.class, EntityDataSerializers.OPTIONAL_UUID);

    private static final EntityDataAccessor<Float> START_X = SynchedEntityData.defineId(KiAttacksEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> START_Y = SynchedEntityData.defineId(KiAttacksEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> START_Z = SynchedEntityData.defineId(KiAttacksEntity.class, EntityDataSerializers.FLOAT);


    public KiAttacksEntity(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.setNoGravity(true);
        this.entityData.define(COLOR, 11403263); // Color predeterminado (negro)
        this.entityData.define(COLOR_BORDE, 3145727); // Color predeterminado (negro)
        this.entityData.define(DANO, 15.0f);
        this.entityData.define(VELOCIDAD, 0.15f);
        this.entityData.define(TAMANO, 0.7F);
        this.entityData.define(OWNER_UUID, Optional.empty());

        this.entityData.define(START_X, 0.0f);
        this.entityData.define(START_Y, 0.0f);
        this.entityData.define(START_Z, 0.0f);
    }

    public int getColor() {
        return this.entityData.get(COLOR);
    }
    public void setColor(int color) {
        this.entityData.set(COLOR, color);
    }
    public int getColorBorde() {
        return this.entityData.get(COLOR_BORDE);
    }
    public void setColorBorde(int color) {
        this.entityData.set(COLOR_BORDE, color);
    }
    public float getDamage() {
        return this.entityData.get(DANO);
    }
    public void setDamage(float damage) {
        this.entityData.set(DANO, damage);
    }
    public float getVelocidad() {
        return this.entityData.get(VELOCIDAD);
    }
    public void setVelocidad(float velocidad) {
        this.entityData.set(VELOCIDAD, velocidad);
    }
    public float getTamano() {
        return this.entityData.get(TAMANO);
    }
    public void setTamano(float tamano) {
        this.entityData.set(TAMANO, tamano);
    }


    public void setStartPosition(Vec3 pos) {
        this.entityData.set(START_X, (float) pos.x);
        this.entityData.set(START_Y, (float) pos.y);
        this.entityData.set(START_Z, (float) pos.z);
    }

    public Vec3 getStartPosition() {
        return new Vec3(
                this.entityData.get(START_X),
                this.entityData.get(START_Y),
                this.entityData.get(START_Z)
        );
    }

    public void setOwnerUUID(UUID uuid) {
        this.entityData.set(OWNER_UUID, Optional.of(uuid));
    }
    public Optional<UUID> getOwnerUUID() {
        return this.entityData.get(OWNER_UUID);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
    }

    @Override
    public void tick() {
        super.tick();

        // Solo ejecutar en el servidor
        if (!this.level().isClientSide) {

            aplicarDanioEnRadio();

            // Incrementa el contador de vida
            lifetime++;

            if (this.getOwner() == null || this.getOwnerUUID() == null) {
                this.discard(); // Destruye la entidad
                return; // Termina el tick para evitar más ejecución
            }

            // Verifica si han pasado 5 segundos (100 ticks)
            if (lifetime >= 100) {
                this.discard(); // Destruye la entidad
            }
        }
    }

    private void aplicarDanioEnRadio() {
        tickCounter++;

        if (tickCounter % 5 == 0) { // Aplica daño cada 10 ticks (0.5s)
            float radio = this.getTamano() + 0.7f; // Radio de daño
            List<LivingEntity> entidades = level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(radio));

            for (LivingEntity entidad : entidades) {
                if (entidad == this.getOwner()) {
                    continue; // No dañar al dueño
                }

                // Verifica si el dueño y el objetivo están en el mismo equipo
                Entity owner = this.getOwner();
                if (owner instanceof LivingEntity ownerLiving) {
                    if (ownerLiving.getTeam() != null && entidad.getTeam() != null &&
                            ownerLiving.getTeam().equals(entidad.getTeam())) {
                        continue; // No dañar a miembros del mismo equipo
                    }
                }

                // Aplica el daño
                DamageSource damageSource = (owner != null)
                        ? level().damageSources().indirectMagic(this, owner)
                        : level().damageSources().magic();

                entidad.hurt(damageSource, this.getDamage());
            }
        }
    }

    @Override
    protected void defineSynchedData() {

    }
}
