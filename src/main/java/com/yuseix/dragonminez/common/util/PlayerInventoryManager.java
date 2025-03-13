package com.yuseix.dragonminez.common.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerInventoryManager extends SavedData {
	private static final String NAME = "player_inventory_data";
	private final Map<UUID, ListTag> mainInventory = new HashMap<>();
	private final Map<UUID, ListTag> otherworldInventory = new HashMap<>();

	public static PlayerInventoryManager get(ServerLevel level) {
		return level.getDataStorage().computeIfAbsent(PlayerInventoryManager::load, PlayerInventoryManager::new, NAME);
	}

	public static PlayerInventoryManager load(CompoundTag tag) {
		PlayerInventoryManager data = new PlayerInventoryManager();
		CompoundTag mainInventoryTag = tag.getCompound("main_inventory");
		CompoundTag otherworldInventoryTag = tag.getCompound("otherworld_inventory");

		mainInventoryTag.getAllKeys().forEach(key -> {
			data.mainInventory.put(UUID.fromString(key), mainInventoryTag.getList(key, Tag.TAG_COMPOUND));
		});

		otherworldInventoryTag.getAllKeys().forEach(key -> {
			data.otherworldInventory.put(UUID.fromString(key), otherworldInventoryTag.getList(key, Tag.TAG_COMPOUND));
		});

		return data;
	}

	@Override
	public CompoundTag save(CompoundTag tag) {
		CompoundTag mainInventoryTag = new CompoundTag();
		CompoundTag otherworldInventoryTag = new CompoundTag();

		mainInventory.forEach((uuid, inventory) -> mainInventoryTag.put(uuid.toString(), inventory));
		otherworldInventory.forEach((uuid, inventory) -> otherworldInventoryTag.put(uuid.toString(), inventory));

		tag.put("main_inventory", mainInventoryTag);
		tag.put("otherworld_inventory", otherworldInventoryTag);

		return tag;
	}

	public ListTag getMainInventory(UUID uuid) {
		return mainInventory.getOrDefault(uuid, new ListTag());
	}

	public void setMainInventory(UUID uuid, ListTag inventory) {
		mainInventory.put(uuid, inventory);
		setDirty();
	}

	public ListTag getOtherworldInventory(UUID uuid) {
		return otherworldInventory.getOrDefault(uuid, new ListTag());
	}

	public void setOtherworldInventory(UUID uuid, ListTag inventory) {
		otherworldInventory.put(uuid, inventory);
		setDirty();
	}
}
