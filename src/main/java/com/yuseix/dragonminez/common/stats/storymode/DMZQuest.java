package com.yuseix.dragonminez.common.stats.storymode;

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
