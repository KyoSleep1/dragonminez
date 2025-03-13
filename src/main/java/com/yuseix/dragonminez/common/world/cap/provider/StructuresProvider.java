package com.yuseix.dragonminez.common.world.cap.provider;

import com.yuseix.dragonminez.common.world.cap.StructuresCapability;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StructuresProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<StructuresCapability> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    private final LazyOptional<StructuresCapability> optional = LazyOptional.of(this::getStructures);

    private StructuresCapability structuresCapability = null;

    private StructuresCapability getStructures() {
        if (this.structuresCapability == null) {
            this.structuresCapability = new StructuresCapability();
        }
        return this.structuresCapability;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CAPABILITY) {
            return this.optional.cast();
        } else {
            return LazyOptional.empty();
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        getStructures().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        getStructures().loadNBTData(nbt);
    }

}
