package com.yuseix.dragonminez.storyline.objectives;

import com.yuseix.dragonminez.storyline.Objective;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class ObjectiveKillEnemy extends Objective {
	private final EntityType<? extends Entity> enemyType;
	private final int requiredAmount;
	private int currentAmount;

	public ObjectiveKillEnemy(EntityType<? extends Entity> enemyType, int requiredAmount) {
		super(false,
				"kill_enemy",
				"Kill " + requiredAmount + " " + enemyType.toString(),
				Component.translatable("dmz.storyline.objective.kill_enemy", getLocLang(enemyType), requiredAmount));

		this.enemyType = enemyType;
		this.requiredAmount = requiredAmount;
		this.currentAmount = 0;
	}

	private static String getLocLang(EntityType<? extends Entity> enemyType) {
		String id = BuiltInRegistries.ENTITY_TYPE.getKey(enemyType).toString();
		return switch (id) {
			case "dragonminez:saga_raditz" -> "Raditz";
			case "dragonminez:saibaman" -> "Saibaman";
			case "dragonminez:kaiwareman" -> "Kaiwareman";
			case "dragonminez:kyukonman" -> "Kyukonman";
			case "dragonminez:copyman" -> "Copyman";
			case "dragonminez:jinkouman" -> "Jinkouman";
			case "dragonminez:tennenman" -> "Tennenman";
			case "dragonminez:saga_nappa" -> "Nappa";
			case "dragonminez:saga_vegetasaiyan" -> "Vegeta";
			default -> id; // Si no está en la lista, usa el ID original
		};
	}

	public void onEnemyKilled(Entity killedEnemy) {
		if (killedEnemy.getType().equals(enemyType)) {
			currentAmount++;
			checkCompletion();
		}
	}

	@Override
	public void checkCompletion() {
		if (currentAmount >= requiredAmount) {
			setCompleted(); // Mark the objective as complete
		}
	}
}
