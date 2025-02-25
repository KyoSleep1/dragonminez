package com.yuseix.dragonminez.storyline.objectives;

import com.yuseix.dragonminez.storyline.Objective;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;


public class ObjectiveCollectItem extends Objective {
	private final Item itemId;
	private final int requiredAmount;
	private int currentAmount;

	public ObjectiveCollectItem(Item itemId, int requiredAmount) {

		super(false,
				"collect_item",
				"Collect " + requiredAmount + " " + itemId.getDefaultInstance().getDescriptionId(),
				Component.translatable("dmz.storyline.objective.collect_item", itemId.getDescriptionId()));

		this.itemId = itemId;
		this.requiredAmount = requiredAmount;
		this.currentAmount = 0;
	}

	private static String getLocLang(String loc) {
		String lang = loc;
		switch (loc) {
			case "roshihouse":
				lang = "Roshi's House";
				break;
		}
		return lang;
	}

	public void onItemCollected(Item collectedItem) {

		if (collectedItem.equals(itemId)) {
			currentAmount++;
			checkCompletion();
		}
	}


	public int getCurrentAmount() {
		return currentAmount;
	}

	public int getRequiredAmount() {
		return requiredAmount;
	}

	@Override
	public void checkCompletion() {
		if (currentAmount >= requiredAmount) {
			setCompleted(); // Mark the objective as complete
		}
	}
}


