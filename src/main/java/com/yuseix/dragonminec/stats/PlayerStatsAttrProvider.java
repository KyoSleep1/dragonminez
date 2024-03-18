package com.yuseix.dragonminec.stats;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.events.ModEvents;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerStatsAttrProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final ResourceLocation ID = new ResourceLocation(DragonMineC.MODID, "mod");
        private final Player player;
        private final PlayerStatsAttributes backend;
        private final LazyOptional<PlayerStatsAttributes> optional;

        public PlayerStatsAttrProvider(Player player) {
            this.player = player;
            backend = new PlayerStatsAttributes(this.player);
            optional = LazyOptional.of(() -> backend);
        }
        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return ModEvents.INSTANCE.orEmpty(cap, this.optional);
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

