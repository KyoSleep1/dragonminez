package com.yuseix.dragonminez.stats.storymode;

import com.yuseix.dragonminez.init.MainEntity;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class DMZQuest {
	private final String id;
	private final QuestRequirement requirement;
	private final String nextQuestId;
	private final String sagaId; // Ahora usa un String en vez de `Saga`

	public DMZQuest(String id, QuestRequirement requirement, String nextQuestId, String sagaId) {
		this.id = id;
		this.requirement = requirement;
		this.nextQuestId = nextQuestId;
		this.sagaId = sagaId;
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

	public String getSagaId() {
		return sagaId;
	}
}
