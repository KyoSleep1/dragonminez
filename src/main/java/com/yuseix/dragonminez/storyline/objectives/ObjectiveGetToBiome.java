package com.yuseix.dragonminez.storyline.objectives;

import com.yuseix.dragonminez.storyline.Objective;
import net.minecraft.network.chat.Component;

public class ObjectiveGetToBiome extends Objective {
	private final String biome;
	private boolean reached;

	public ObjectiveGetToBiome(String biomeName) {

		super(false,
				"get_to_biome",
				"Get to the " + biomeName + " biome",
				Component.translatable("dmz.storyline.objective.get_to_biome", getLocLang(biomeName)));

		this.biome = biomeName;
		this.reached = false;
	}

	private static String getLocLang(String loc) {
		return switch (loc) {
			case "rockybiome" -> "Rocky";
			case "plains" -> "Plains";
			default -> "Rocky";
		};
	}

	public void onPlayerEnterBiome(String enteredBiome) {

		if (enteredBiome.equals(biome)) {
			reached = true;
			checkCompletion();
		}
	}

	@Override
	public void checkCompletion() {
		if (reached) {
			setCompleted(); // Mark the objective as complete
		}
	}
}
