package com.yuseix.dragonminez.storyline;

import com.yuseix.dragonminez.events.StorylineEvents;
import com.yuseix.dragonminez.init.StorylineManager;
import com.yuseix.dragonminez.registry.IDRegistry;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveGetToBiome;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveKillEnemy;
import com.yuseix.dragonminez.utils.DebugUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quest {
	private final Player player;
	private final String id;
	private final String name;
	private final String description;
	private final List<Objective> objectives;
	private final List<Quest> prerequisites;
	private boolean completed;

	public Quest(Player player, String id, String name, String description, List<Objective> objectives, List<Quest> prerequisites) {
		if (!StorylineManager.hasInitialized) {
			IDRegistry.registerQuestId(id);
		}
		this.player = player;
		this.id = id;
		this.name = name;
		this.description = description;
		this.objectives = objectives;
		this.prerequisites = prerequisites; // List of quest IDs that must be completed before this quest can be started
		this.completed = false;

		for (Objective objective : objectives) {
			objective.setOnCompletion(this::checkQuestCompletion);
		}
		
		DebugUtils.dmzLog("Quest '" + name + "' initialized for " + player.getName().getString());

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Objective> getAllObjectives() {
		return objectives;
	}

	public List<Objective> getKillObjective() {
		List<Objective> killObjectives = new ArrayList<>();
		for (Objective objective : objectives) {
			if (objective instanceof ObjectiveKillEnemy) {
				killObjectives.add(objective);
			}
		}
		return killObjectives;
	}

	public boolean isKillObjective() {
		return objectives.stream().anyMatch(objective -> objective instanceof ObjectiveKillEnemy);
	}

	public boolean isBiomeObjective() {
		return objectives.stream().anyMatch(objective -> objective instanceof ObjectiveGetToBiome);
	}

	public boolean isBiomeLocated() {
		return objectives.stream().anyMatch(objective -> objective instanceof ObjectiveGetToBiome && objective.isCompleted());
	}

	public List<String> getPrerequisites() {
		List<String> QuestIDs = new ArrayList<>();

		for (Quest prerequisite : prerequisites) {
			QuestIDs.add(prerequisite.getId());
		}
		return QuestIDs;
	}

	public void removeAllPrerequisites() {
		prerequisites.clear();
		StorylineEvents.syncStoryline(player);
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;

		StorylineEvents.syncStoryline(player);

		if (completed) {
			DebugUtils.dmzLog("Quest '" + name + "' is now completed! for " + player.getName().getString());
			// Trigger any additional logic for quest completion here
			player.sendSystemMessage(Component.translatable("quest.completed", getName()));
		}
	}

	public Map<String, Object> toSerializable() {
		Map<String, Object> data = new HashMap<>();
		data.put("id", id);
		data.put("completed", completed);
		return data;
	}

	public boolean canStart() {
		return prerequisites.isEmpty() || prerequisites.stream().allMatch(Quest::isCompleted);
	}

	private void checkQuestCompletion() {
		boolean allCompleted = objectives.stream().allMatch(Objective::isCompleted);
		if (allCompleted) {
			setCompleted(true);
		}
	}
}


