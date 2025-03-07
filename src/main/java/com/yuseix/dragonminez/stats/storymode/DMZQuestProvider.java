package com.yuseix.dragonminez.stats.storymode;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class DMZQuestProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final ResourceLocation ID = new ResourceLocation(DragonMineZ.MOD_ID, "storymode");

    private final DMZStoryCapability backend;
    private final LazyOptional<DMZStoryCapability> optional;

    public DMZQuestProvider() {
        backend = new DMZStoryCapability("saiyQuest1", "saiyan"); // Ahora usa String
        optional = LazyOptional.of(() -> backend);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return DMZStoryCapability.INSTANCE.orEmpty(cap, this.optional);
    }

    public static @NotNull <T> LazyOptional<T> getCap(Capability<T> cap, Entity entity) {
        return entity.getCapability(cap);
    }

    void invalidate() {
        this.optional.invalidate();
    }

    @Override
    public CompoundTag serializeNBT() {
        return backend.saveNBTData();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        backend.loadNBTData(nbt);
    }
}