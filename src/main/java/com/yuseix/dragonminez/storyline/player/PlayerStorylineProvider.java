package com.yuseix.dragonminez.storyline.player;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.events.StorylineEvents;
import com.yuseix.dragonminez.init.StorylineManager;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerStorylineProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

	public static final ResourceLocation ID = new ResourceLocation(DragonMineZ.MOD_ID, "storyline");
	private final Player player;
	private final StorylineManager backend;
	private final LazyOptional<StorylineManager> optional;

	public PlayerStorylineProvider(Player player) {
		this.player = player;
		backend = new StorylineManager(this.player);
		optional = LazyOptional.of(() -> backend);
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return StorylineEvents.INSTANCE.orEmpty(cap, this.optional);
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
