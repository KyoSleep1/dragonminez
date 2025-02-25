package com.yuseix.dragonminez.storyline.sagas;

import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.RaditzEntity;
import com.yuseix.dragonminez.storyline.Quest;
import com.yuseix.dragonminez.storyline.Saga;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveCollectItem;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveGetToBiome;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveGetToLocation;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveKillEnemy;

import java.util.List;

public class SaiyanSaga extends Saga {

	public SaiyanSaga() {
		super("saiyan_saga", "dmz.storyline.saiyan.saga");
		addQuests();
		addPrerequisites();
	}

	@Override
	public void addQuests() {
		// Add quests to the saga
		Quest quest1 = new Quest(
				"saiyQuest1",
				"dmz.storyline.saiyan.quest1.title",
				"dmz.storyline.saiyan.quest1.desc",
				List.of(new ObjectiveGetToLocation("roshihouse")),
				List.of() // No prerequisites
		);

		Quest quest2 = new Quest(
				"saiyQuest2",
				"dmz.storyline.saiyan.quest2.title",
				"dmz.storyline.saiyan.quest2.desc",
				List.of(new ObjectiveKillEnemy(MainEntity.RADITZ_SAGA.get(), 1)),
				List.of(quest1) // Prerequisite is the first quest
		);

		Quest quest3 = new Quest(
				"saiyQuest3",
				"dmz.storyline.saiyan.quest3.title",
				"dmz.storyline.saiyan.quest3.desc",
				List.of(new ObjectiveKillEnemy(MainEntity.SAIBAMAN.get(), 1),
				new ObjectiveKillEnemy(MainEntity.KAIWAREMAN.get(), 1), new ObjectiveKillEnemy(MainEntity.KYUKONMAN.get(), 1),
				new ObjectiveKillEnemy(MainEntity.COPYMAN.get(), 1), new ObjectiveKillEnemy(MainEntity.JINKOUMAN.get(), 1),
				new ObjectiveKillEnemy(MainEntity.TENNENMAN.get(), 1)),
				List.of(quest2) // Prerequisite is the second quest
		);

		Quest quest4 = new Quest(
				"saiyQuest4",
				"dmz.storyline.saiyan.quest4.title",
				"dmz.storyline.saiyan.quest4.desc",
				List.of(new ObjectiveKillEnemy(MainEntity.NAPPA_SAGA.get(), 1)),
				List.of(quest3) // Prerequisite is the third quest
		);

		Quest quest5 = new Quest(
				"saiyQuest5",
				"dmz.storyline.saiyan.quest5.title",
				"dmz.storyline.saiyan.quest5.desc",
				List.of(new ObjectiveKillEnemy(MainEntity.NAPPA_SAGA.get(), 1)),
				List.of(quest4) // Prerequisite is the fourth quest
		);

		Quest quest6 = new Quest(
				"saiyQuest6",
				"dmz.storyline.saiyan.quest6.title",
				"dmz.storyline.saiyan.quest6.desc",
				List.of(new ObjectiveGetToBiome("rockybiome"), new ObjectiveKillEnemy(MainEntity.VEGETA_SAIYAN.get(), 1)),
				List.of(quest5) // Prerequisite is the fourth quest
		);

		Quest quest7 = new Quest(
				"saiyQuest7",
				"dmz.storyline.saiyan.quest7.title",
				"dmz.storyline.saiyan.quest7.desc",
				List.of(new ObjectiveKillEnemy(MainEntity.VEGETA_SAIYAN.get(), 1)), //Este sería el oozaru
				List.of(quest6) // Prerequisite is the fifth quest
		);

		Quest quest8 = new Quest(
				"saiyQuest8",
				"dmz.storyline.saiyan.quest8.title",
				"dmz.storyline.saiyan.quest8.desc",
				List.of(new ObjectiveKillEnemy(MainEntity.VEGETA_SAIYAN.get(), 1)),
				List.of(quest7) // Prerequisite is the sixth quest
		);

		Quest quest9 = new Quest(
				"saiyQuest9",
				"dmz.storyline.saiyan.quest9.title",
				"dmz.storyline.saiyan.quest9.desc",
				List.of(new ObjectiveGetToLocation("namekdim")),
				List.of(quest8) // Prerequisite is the seventh quest
		);

		this.addQuest(quest1);
		this.addQuest(quest2);
		this.addQuest(quest3);
		this.addQuest(quest4);
		this.addQuest(quest5);
		this.addQuest(quest6);
		this.addQuest(quest7);
		this.addQuest(quest8);
		this.addQuest(quest9);
	}

	@Override
	public void addPrerequisites() {
		// No prerequisites for the Saiyan Saga, as it is the first saga
	}
}
