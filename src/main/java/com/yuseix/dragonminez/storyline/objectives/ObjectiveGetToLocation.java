package com.yuseix.dragonminez.storyline.objectives;

import com.yuseix.dragonminez.storyline.Objective;
import net.minecraft.network.chat.Component;

public class ObjectiveGetToLocation extends Objective {
	private final String loc;
	private boolean reached;

	public ObjectiveGetToLocation(String loc) {

		super(false,
				"get_to_location",
				"Get to the " + loc + " location",
				(Component.translatable("dmz.storyline.objective.get_to_location", getLocLang(loc))));

		this.loc = loc;
		this.reached = false;
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


	public void onReachingLoc(String loc) {

		if (loc.equals(this.loc)) {
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
