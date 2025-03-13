package com.yuseix.dragonminez.common.init.entity.custom.fpcharacters;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import java.util.Optional;
import java.util.UUID;

public class FPBase extends Mob {

    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(FPBase.class, EntityDataSerializers.OPTIONAL_UUID);


    public FPBase(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);


        this.entityData.define(OWNER_UUID, Optional.empty());

    }


    public void setOwnerUUID(UUID uuid) {
        this.entityData.set(OWNER_UUID, Optional.of(uuid));
    }
    public Optional<UUID> getOwnerUUID() {
        return this.entityData.get(OWNER_UUID);
    }
    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.5f)
                .add(Attributes.ATTACK_SPEED, 0.5f)
                .add(Attributes.MOVEMENT_SPEED, 0.18F).build();
    }

    @Override
    public void tick() {
        super.tick();

        // Verifica si la entidad tiene un ownerUUID
        if (this.getOwnerUUID() == null) {
            // Si no tiene ownerUUID, elimina la entidad del mundo
            this.discard();
        }
    }

}
