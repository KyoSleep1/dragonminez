package com.yuseix.dragonminez.storyline;

import com.yuseix.dragonminez.init.StorylineManager;
import com.yuseix.dragonminez.registry.IDRegistry;

import java.util.ArrayList;
import java.util.List;

public abstract class Saga {
	private final String id;
	private final String name;
	private final List<Quest> quests;
	private final List<Saga> sagaPrerequisites;

	public Saga(String id, String name) {
		if (!StorylineManager.hasInitialized) {
			IDRegistry.registerSagaId(id);
		}
		this.id = id;
		this.name = name;
		this.quests = new ArrayList<>();
		this.sagaPrerequisites = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Quest> getQuests() {
		return quests;
	}

	public List<Quest> getAvailableQuests() {
		return quests.stream().filter(quest -> quest.canStart() && !quest.isCompleted()).toList();
	}

	public Quest getQuestbyId(String id) {
		return quests.stream().filter(quest -> quest.getId().equals(id)).findFirst().orElse(null);
	}

	public void addQuest(Quest quest) {
		quests.add(quest);
	}

	public void addPrequisite(Saga saga) {
		sagaPrerequisites.add(saga);
	}

	public void removeAllPrerequisites() {
		sagaPrerequisites.clear();
	}

	public boolean canStart() {
		return sagaPrerequisites.isEmpty() || sagaPrerequisites.stream().allMatch(prerequisite -> prerequisite == null || prerequisite.isCompleted());
	}

	public boolean isCompleted() {
		// Checks if all quests are completed, in the future maybe exclude optional quests?
		return quests.stream().allMatch(Quest::isCompleted);
	}

	public abstract void addQuests();

	public abstract void addPrerequisites();

}
