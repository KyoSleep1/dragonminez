package com.yuseix.dragonminez.storyline;

import com.yuseix.dragonminez.storyline.objectives.ObjectiveGetToBiome;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveGetToLocation;
import com.yuseix.dragonminez.utils.DebugUtils;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.network.chat.Component;

import java.util.Map;

import static com.yuseix.dragonminez.storyline.RegisterAdvancementCriterion.criterionList;

public abstract class Objective {

	private boolean completed;
	private Runnable onCompletion;
	private final String name;
	private final String description;
	private final Component lang;

	public Objective(boolean completed, String name, String description, Component lang) {
		this.completed = completed;
		this.name = name;
		this.description = description;
		this.lang = lang;
	}

	public boolean isCompleted() {
		return this.completed; // Check completion status
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public Component getLang() {
		return this.lang;
	}

	public void advancementTranslator(Advancement advancement, String objective) {

		Map<String, Criterion> criteria = advancement.getCriteria();

		for (String criterion : criteria.keySet()) {
			DebugUtils.dmzLog("AdvancementTranslator detected: " + criterion);

			if (criterionList.contains(criterion)) {

				DebugUtils.dmzLog("AdvancementTranslator found: " + criterion);

				if (objective.equals("location")) {
					//Assumes that the instance of the objective is ObjectiveGetToLocation
					((ObjectiveGetToLocation) this).onReachingLoc(criterion);
				} else if (objective.equals("biome")) {
					//Assumes that the instance of the objective is ObjectiveGetToBiome
					((ObjectiveGetToBiome) this).onPlayerEnterBiome(criterion);
				} else if (criterionList.contains(objective)) {
					//Making it for other users, if their custom criterion is registered, then it will try to call
					//whatever instance of the objective is and try to find a method that requires the criterion
					try {
						//Format of the method name is on{Objective} where {Objective} is the name of the objective (e.g. "location")
						//So If I have a class that extends Objective and has a method called onLocation(String location) then it will call that method
						this.getClass().getMethod("on" + objective.substring(0, 1).toUpperCase() + objective.substring(1), String.class).invoke(this, criterion);
					} catch (Exception e) {
						DebugUtils.dmzLog("AdvancementTranslator failed to find method for: " + objective);
						throw new IllegalArgumentException("The advancement criterion [" + criterion + "] was not found in the objective.");
					}
				}
			}
		}
	}


	public void setCompleted() {
		this.completed = true; // Update completion status

		if (onCompletion != null) {
			onCompletion.run();
		}
	}

	public void setCompleted(boolean completed) {
		this.completed = completed; // Update completion status

		if (completed) {
			if (onCompletion != null) {
				onCompletion.run();
			}
		}
	}

	public void setOnCompletion(Runnable onCompletion) {
		this.onCompletion = onCompletion;
	}

	public abstract void checkCompletion();
}
