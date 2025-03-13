package com.yuseix.dragonminez.common.world.cap.nbt;

import com.yuseix.dragonminez.common.world.cap.NamekDragonBallsCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class NamekDragonBallSavedData extends SavedData {
	private final NamekDragonBallsCapability capability = new NamekDragonBallsCapability();

	public static NamekDragonBallSavedData load(CompoundTag tag) {
		NamekDragonBallSavedData data = new NamekDragonBallSavedData();
		data.capability.loadNBTData(tag);
		return data;
	}

	@Override
	public CompoundTag save(CompoundTag tag) {
		capability.saveNBTData(tag);
		return tag;
	}

	public NamekDragonBallsCapability getCapability() {
		return capability;
	}
}
