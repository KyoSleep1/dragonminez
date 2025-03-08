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

public class DragonBallGenProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<DragonBallsCapability> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    private final LazyOptional<DragonBallsCapability> optional = LazyOptional.of(this::getDballsBackend);

    private DragonBallsCapability Dballs = null;

    private DragonBallsCapability getDballsBackend() {
        if (this.Dballs == null) this.Dballs = new DragonBallsCapability();
        return this.Dballs;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return cap == CAPABILITY ? this.optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        getDballsBackend().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        getDballsBackend().loadNBTData(nbt);
    }
}