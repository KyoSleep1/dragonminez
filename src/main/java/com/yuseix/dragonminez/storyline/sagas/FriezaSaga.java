package com.yuseix.dragonminez.storyline.sagas;

import com.yuseix.dragonminez.registry.IDRegistry;
import com.yuseix.dragonminez.storyline.Saga;
import net.minecraft.world.entity.player.Player;

public class FriezaSaga extends Saga {

	public FriezaSaga(Player player) {
		super("frieza_saga", "Frieza Saga");
		addQuests(player);
		addPrerequisites();
	}

	@Override
	public void addQuests(Player player) {
		// Add quests to the saga
	}

	@Override
	public void addPrerequisites() {
		addPrequisite(IDRegistry.sagaRegistry.get("saiyan_saga"));
	}
}
