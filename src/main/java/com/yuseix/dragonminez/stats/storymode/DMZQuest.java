package com.yuseix.dragonminez.stats.storymode;

import com.yuseix.dragonminez.init.MainEntity;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class DMZQuest {
	private final String id;
	private final QuestRequirement requirement;
	private final String nextQuestId;
	private final DMZStoryCapability.Saga saga;

	public DMZQuest(String id, QuestRequirement requirement, String nextQuestId, DMZStoryCapability.Saga saga) {
		this.id = id;
		this.requirement = requirement;
		this.nextQuestId = nextQuestId;
		this.saga = saga;
	}

	public String getId() {
		return id;
	}

	public QuestRequirement getRequirement() {
		return requirement;
	}

	public String getNextQuestId() {
		return nextQuestId;
	}

	public DMZStoryCapability.Saga getSaga() {
		return saga;
	}

}

