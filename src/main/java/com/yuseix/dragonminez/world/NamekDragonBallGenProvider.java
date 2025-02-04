package com.yuseix.dragonminez.world;

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

public class NamekDragonBallGenProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<NamekDragonBallsCapability> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    private final LazyOptional<NamekDragonBallsCapability> optional = LazyOptional.of(this::getNamekDballsBackend);

    private NamekDragonBallsCapability NDballs = null;

    private NamekDragonBallsCapability getNamekDballsBackend() {
        if (this.NDballs == null) {
            this.NDballs = new NamekDragonBallsCapability();
        }
        return this.NDballs;
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
        getNamekDballsBackend().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        getNamekDballsBackend().loadNBTData(nbt);
    }

}
