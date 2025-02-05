package com.yuseix.dragonminez.world;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class NamekDragonBallsCapability {

    public boolean hasNamekDragonBalls = false;
    public List<BlockPos> namekDragonBallPositions = new ArrayList<>();


    public boolean hasNamekDragonBalls() {
        return hasNamekDragonBalls;
    }


    public void setHasNamekDragonBalls(boolean hasNamekDragonBalls) {
        this.hasNamekDragonBalls = hasNamekDragonBalls;
    }

    public List<BlockPos> namekDragonBallPositions() {
        return new ArrayList<>(namekDragonBallPositions);
    }

    public void setNamekDragonBallPositions(List<BlockPos> dragonBallPositions) {
        this.namekDragonBallPositions.clear();
        this.namekDragonBallPositions = dragonBallPositions;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putBoolean("hasNamekDragonBalls", hasNamekDragonBalls);

        ListTag listTag = new ListTag();
        for (BlockPos pos : namekDragonBallPositions) {
            listTag.add(NbtUtils.writeBlockPos(pos));
        }
        nbt.put("namekDragonBallPositions", listTag);


    }

    public void loadNBTData(CompoundTag nbt) {
        hasNamekDragonBalls = nbt.getBoolean("hasNamekDragonBalls");

        ListTag listTag = nbt.getList("namekDragonBallPositions", 10); // 10 is the NBT type for CompoundTag
        for (int i = 0; i < listTag.size(); i++) {
            namekDragonBallPositions.add(NbtUtils.readBlockPos(listTag.getCompound(i)));
        }
    }

    public void loadFromSavedData(Level level) {
        if (level instanceof ServerLevel serverLevel) {
            NamekDragonBallSavedData data = serverLevel.getDataStorage().computeIfAbsent(
                    NamekDragonBallSavedData::load,
                    NamekDragonBallSavedData::new,
                    "namek_dragon_balls"
            );
            data.getCapability().setHasNamekDragonBalls(hasNamekDragonBalls);
            data.getCapability().setNamekDragonBallPositions(new ArrayList<>(this.namekDragonBallPositions));
            data.setDirty();
        }
    }

    public void saveToSavedData(ServerLevel level) {
        NamekDragonBallSavedData data = level.getDataStorage().computeIfAbsent(
                NamekDragonBallSavedData::load,
                NamekDragonBallSavedData::new,
                "namek_dragon_balls"
        );
        data.getCapability().setHasNamekDragonBalls(hasNamekDragonBalls);
        data.getCapability().setNamekDragonBallPositions(new ArrayList<>(this.namekDragonBallPositions));
        data.setDirty();
    }

    public void updateDragonBallPositions(Level level) {
        namekDragonBallPositions.removeIf(pos -> !level.isLoaded(pos) || level.getBlockEntity(pos) == null);
    }
}
