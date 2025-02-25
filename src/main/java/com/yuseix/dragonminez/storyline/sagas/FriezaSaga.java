package com.yuseix.dragonminez.storyline.sagas;

import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.registry.IDRegistry;
import com.yuseix.dragonminez.storyline.Quest;
import com.yuseix.dragonminez.storyline.Saga;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveCollectItem;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveGetToLocation;

import java.util.List;

public class FriezaSaga extends Saga {

	public FriezaSaga() {
		super("frieza_saga", "Frieza Saga");
		addQuests();
		addPrerequisites();
	}

	@Override
	public void addQuests() {
		// Add quests to the saga
	}

	@Override
	public void addPrerequisites() {
		addPrequisite(IDRegistry.sagaRegistry.get("saiyan_saga"));
	}
}
