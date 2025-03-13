package com.yuseix.dragonminez.common.world.cap.nbt;

import com.yuseix.dragonminez.common.world.cap.DragonBallsCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class DragonBallSavedData extends SavedData {
	private final DragonBallsCapability capability = new DragonBallsCapability();

	public static DragonBallSavedData load(CompoundTag tag) {
		DragonBallSavedData data = new DragonBallSavedData();
		data.capability.loadNBTData(tag);
		return data;
	}

	@Override
	public CompoundTag save(CompoundTag tag) {
		capability.saveNBTData(tag);
		return tag;
	}

	public DragonBallsCapability getCapability() {
		return capability;
	}
}
