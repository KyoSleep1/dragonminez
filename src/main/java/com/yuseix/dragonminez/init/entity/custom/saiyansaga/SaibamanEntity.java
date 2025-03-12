package com.yuseix.dragonminez.init.entity.custom.saiyansaga;

import com.yuseix.dragonminez.init.entity.custom.SagaEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekianEntity;
import com.yuseix.dragonminez.init.entity.goals.MoveToSurfaceGoal;
import com.yuseix.dragonminez.utils.DebugUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Locale;

public class SaibamanEntity extends SagaEntity {

	private int talkCooldown = getRandomTalkCooldown(); // Cooldown de frases aleatorias

	public SaibamanEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	public static AttributeSupplier setAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 750.0D)
				.add(Attributes.ATTACK_DAMAGE, 85.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.28F).build();
	}

	@Override
	public void tick() {
		super.tick();

		LivingEntity target = this.getTarget();

		if (target != null) {

			if (talkCooldown > 0) {
				talkCooldown--;
			} else {
				sayRandomPhrase();
				talkCooldown = getRandomTalkCooldown();
			}

		}
	}

	@Override
	public void die(DamageSource pDamageSource) {
		super.die(pDamageSource);

		if (!(this.level() instanceof ServerLevel serverLevel)) return;

		// 10% de probabilidad de explotar al morir
		if (this.random.nextFloat() < 0.1F) {
			serverLevel.explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, Level.ExplosionInteraction.MOB);
		}

		// Buscar jugadores en un radio de 15 bloques
		String entityName = this.getName().getString();
		for (Player player : serverLevel.players()) {
			if (player.distanceTo(this) <= 15) {
				switch (entityName.toLowerCase(Locale.ROOT)) {
					case "saibaman":
						player.sendSystemMessage(Component.literal("» §aSaibaman§f › Kiiehh..."));
						break;
					case "copyman":
						player.sendSystemMessage(Component.literal("» §8Copyman§f › Kiiehh..."));
						break;
					case "kaiwareman":
						player.sendSystemMessage(Component.literal("» §bKaiwareman§f › Kiiehh..."));
						break;
					case "kyukonman":
						player.sendSystemMessage(Component.literal("» §eKyukonman§f › Kiiehh..."));
						break;
					case "tennenman":
						player.sendSystemMessage(Component.literal("» §dTennenman§f › Kiiehh..."));
						break;
					case "jinkouman":
						player.sendSystemMessage(Component.literal("» §7Jinkouman§f › Kiiehh..."));
						break;
				}
			}
		}
	}

	@Override
	protected void sayRandomPhrase() {
		if (!(this.level() instanceof ServerLevel serverLevel)) return;

		String entityName = this.getName().getString();

		String[] phrases;
		switch (entityName.toLowerCase(Locale.ROOT)) {
			case "saibaman":
				phrases = new String[]{
						"» §aSaibaman§f › Gi gi!",
						"» §aSaibaman§f › Gyah!"
				};
				break;
			case "copyman":
				phrases = new String[]{
						"» §8Copyman§f › Gi gi!",
						"» §8Copyman§f › Gyah!"
				};
				break;
			case "kaiwareman":
				phrases = new String[]{
						"» §bKaiwareman§f › Gi gi!",
						"» §bKaiwareman§f › Gyah!"
				};
				break;
			case "kyukonman":
				phrases = new String[]{
						"» §eKyukonman§f › Gi gi!",
						"» §eKyukonman§f › Gyah!"
				};
				break;
			case "tennenman":
				phrases = new String[]{
						"» §dTennenman§f › Gi gi!",
						"» §dTennenman§f › Gyah!"
				};
				break;
			case "jinkouman":
				phrases = new String[]{
						"» §7Jinkouman§f › Gi gi!",
						"» §7Jinkouman§f › Gyah!"
				};
				break;
			default:
				phrases = new String[]{
						"» §aSaibaman§f › Gi gi!",
						"» §aSaibaman§f › Gyah!"
				};
		}

		String selectedPhrase = phrases[this.random.nextInt(phrases.length)];

		// Buscar jugadores en un radio de 15 bloques
		for (Player player : serverLevel.players()) {
			if (player.distanceTo(this) <= 15) {
				player.sendSystemMessage(Component.literal(selectedPhrase));
			}
		}
	}


	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 15.0F));
		this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new MoveToSurfaceGoal(this));

		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, NamekianEntity.class, true));
		this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, Villager.class, true));
		this.targetSelector.addGoal(10, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}


}
