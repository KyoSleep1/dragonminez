package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.events.StorylineEvents;
import com.yuseix.dragonminez.registry.IDRegistry;
import com.yuseix.dragonminez.storyline.Quest;
import com.yuseix.dragonminez.storyline.Saga;
import com.yuseix.dragonminez.storyline.player.PlayerStorylineProvider;
import com.yuseix.dragonminez.storyline.sagas.FriezaSaga;
import com.yuseix.dragonminez.storyline.sagas.SaiyanSaga;
import com.yuseix.dragonminez.utils.DebugUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

import java.util.*;
import java.util.stream.Collectors;

import static com.yuseix.dragonminez.registry.IDRegistry.sagaRegistry;

public class StorylineManager {
	private Hashtable<String, Saga> sagas = new Hashtable<>();
	public static volatile boolean hasInitialized; //Thanks Gecko for the idea
	private Player player;

	public StorylineManager(Player player) {
		//this.player = player;
		//initializeSagas();
	}

	public void resetProgress() {
		for (Saga saga : sagas.values()) {
			for (Quest quest : saga.getQuests()) {
				quest.setCompleted(false);
			}
		}
		StorylineEvents.syncStoryline(player);
	}
	public Saga getSaga(String id) {
		return sagas.get(id);
	}

	public boolean isSagaCompleted(String sagaId) {
		Saga saga = sagas.get(sagaId);
		return saga != null && saga.isCompleted();
	}

	public Hashtable<String, Saga> getAllSagas() {
		return sagas;
	}

	public List<Saga> getActiveSagas() {
		return sagas.values().stream().filter(saga -> saga.canStart() && !saga.isCompleted()).toList();
	}

	public Map<String, Object> toSerializable() {
		Map<String, Object> data = new HashMap<>();
		List<Map<String, Object>> serializedSagas = new ArrayList<>();

		for (Saga saga : sagas.values()) {
			Map<String, Object> sagaData = new HashMap<>();
			sagaData.put("id", saga.getId());
			sagaData.put("completed", saga.isCompleted());
			sagaData.put("quests", saga.getQuests().stream()
					.map(Quest::toSerializable) // Use Quest's toSerializable method
					.collect(Collectors.toList()));

			serializedSagas.add(sagaData);
		}
		data.put("sagas", serializedSagas);
		return data;
	}

	// Initialize predefined sagas
	private void initializeSagas() {
		//Predefined Sagas by the mod
		Saga saiyanSaga = new SaiyanSaga(player);
		addSaga(saiyanSaga);
		Saga friezaSaga = new FriezaSaga(player);
		addSaga(friezaSaga);

		//Register sagas from registry, no need for a throws exception as the registry should be checked before
		for (Saga saga : sagaRegistry.values()) {
			addSaga(saga);
		}

		hasInitialized = true; //No addSaga calls after this point PLEASE
	}

	// Add a saga to the manager, local use only.
	private void addSaga(Saga saga) {
		//Adds saga to the manager
		sagas.put(saga.getId(), saga);
		//Adds saga to the registry if it's not already there
		if (!sagaRegistry.containsKey(saga.getId()))
			IDRegistry.registerSaga(saga);
	}

	//Must be public for the sake of the capability
	public CompoundTag saveNBTData() {

		CompoundTag nbt = new CompoundTag();

		ListTag sagasTag = new ListTag(); // Main container for all sagas
		for (Saga saga : sagas.values()) {
			if (!saga.canStart()) continue;
			CompoundTag sagaTag = new CompoundTag();
			sagaTag.putString("id", saga.getId());
			sagaTag.putBoolean("completed", saga.isCompleted());
			sagasTag.add(sagaTag);
			if (saga.isCompleted()) continue;
			// Save quests in the saga
			ListTag questsTag = new ListTag(); // List for storing quests
			for (Quest quest : saga.getQuests()) {
				if (quest.isCompleted()) {
					CompoundTag questTag = new CompoundTag();
					questTag.putString("id", quest.getId());
					questTag.putBoolean("completed", quest.isCompleted()); // Save completion state
					questsTag.add(questTag);
				}
			}
			sagaTag.put("quests", questsTag); // Add quests to saga
		}

		nbt.put("sagas", sagasTag); // Add all sagas to the provided NBT

		return nbt;
	}

	//Must be public for the sake of the capability
	public void loadNBTData(CompoundTag nbt) {
		if (!nbt.contains("sagas")) return; // Ensure sagas data exists

		ListTag sagasTag = nbt.getList("sagas", 10); // 10 = CompoundTag type

		for (int i = 0; i < sagasTag.size(); i++) {
			CompoundTag sagaTag = sagasTag.getCompound(i);
			String sagaId = sagaTag.getString("id");

			// Get the existing Saga by ID
			Saga saga = getSaga(sagaId);

			// Update quests
			if (sagaTag.contains("quests")) {
				ListTag questsTag = sagaTag.getList("quests", 10); // 10 = CompoundTag type
				for (int j = 0; j < questsTag.size(); j++) {
					CompoundTag questTag = questsTag.getCompound(j);
					String questId = questTag.getString("id");

					// Get the existing Quest by ID
					for (Quest quest : saga.getQuests()) {
						if (quest.getId().equals(questId)) {
							quest.setCompleted(questTag.getBoolean("completed")); // Update the completed state
							break;
						}
					}
				}
			}
		}
	}
}
