package com.yuseix.dragonminez.world;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class DragonBallsCapability {

    public boolean hasDragonBalls = false;
    public List<BlockPos> dragonBallPositions = new ArrayList<>();

    public boolean hasDragonBalls() {
        return hasDragonBalls;
    }

    public void setHasDragonBalls(boolean hasDragonBalls) {
        this.hasDragonBalls = hasDragonBalls;
    }

    public List<BlockPos> DragonBallPositions() {
        return new ArrayList<>(dragonBallPositions);
    }

    public void setDragonBallPositions(List<BlockPos> dragonBallPositions) {
        this.dragonBallPositions.clear();
        this.dragonBallPositions.addAll(dragonBallPositions);
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putBoolean("hasDragonBalls", hasDragonBalls);

        ListTag listTag = new ListTag();
        for (BlockPos pos : dragonBallPositions) {
            listTag.add(NbtUtils.writeBlockPos(pos));
        }
        nbt.put("dragonBallPositions", listTag);
    }

    public void loadNBTData(CompoundTag nbt) {
        hasDragonBalls = nbt.getBoolean("hasDragonBalls");

        ListTag listTag = nbt.getList("dragonBallPositions", 10); // 10 is the NBT type for CompoundTag
        for (int i = 0; i < listTag.size(); i++) {
            dragonBallPositions.add(NbtUtils.readBlockPos(listTag.getCompound(i)));
        }
    }

    public void loadFromSavedData(Level level) {
        if (level instanceof ServerLevel serverLevel) {
            DragonBallSavedData data = serverLevel.getDataStorage().computeIfAbsent(
                    DragonBallSavedData::load,
                    DragonBallSavedData::new,
                    "dragon_balls"
            );
            data.getCapability().setHasDragonBalls(hasDragonBalls);
            data.getCapability().setDragonBallPositions(new ArrayList<>(this.dragonBallPositions));
            data.setDirty();
        }
    }

    public void saveToSavedData(ServerLevel level) {
        DragonBallSavedData data = level.getDataStorage().computeIfAbsent(
                DragonBallSavedData::load,
                DragonBallSavedData::new,
                "dragon_balls"
        );
        data.getCapability().setHasDragonBalls(hasDragonBalls);
        data.getCapability().setDragonBallPositions(new ArrayList<>(this.dragonBallPositions));
        data.setDirty();
    }

    public void updateDragonBallPositions(Level level) {
        dragonBallPositions.removeIf(pos -> !level.isLoaded(pos) || level.getBlockEntity(pos) == null);
        if (level instanceof ServerLevel serverLevel) {
            saveToSavedData(serverLevel);
        }
    }
}
