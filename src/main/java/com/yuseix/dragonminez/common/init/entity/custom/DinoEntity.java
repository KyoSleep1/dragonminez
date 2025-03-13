package com.yuseix.dragonminez.common.init.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.common.ForgeMod;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

public class DinoEntity extends Monster implements GeoEntity {

	private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

	public DinoEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 75.0D)
				.add(Attributes.ATTACK_DAMAGE, 40.0f)
				.add(Attributes.ATTACK_SPEED, 1.0f)
				.add(ForgeMod.ENTITY_REACH.get(), 3.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.22F).build();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Creeper.class, true));
		this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
		controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
		controllerRegistrar.add(new AnimationController<>(this, "attackcontroller", 0, this::attackpredicate));
	}

	public static boolean checkDinoSpawnRules(EntityType<? extends DinoEntity> entity, ServerLevelAccessor world, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		if (world.getDifficulty() != Difficulty.PEACEFUL) {
			return world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity);
		}
		return false;
	}


	private <T extends GeoAnimatable> PlayState attackpredicate(AnimationState<T> event) {
		if (this.swinging) {
			event.getController().setAnimation(RawAnimation.begin().then("animation.dino1.attack", Animation.LoopType.HOLD_ON_LAST_FRAME));
			this.swinging = false;
		}
		return PlayState.STOP;
	}

	private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
		if (tAnimationState.isMoving()) {
			tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.dino1.walk", Animation.LoopType.LOOP));
		} else {
			tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.dino1.idle", Animation.LoopType.LOOP));
		}
		return PlayState.CONTINUE;

	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor pLevel, MobSpawnType pReason) {
		return true;
	}
}
