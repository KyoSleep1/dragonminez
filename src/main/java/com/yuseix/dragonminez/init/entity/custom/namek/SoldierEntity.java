package com.yuseix.dragonminez.init.entity.custom.namek;

import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.init.entity.goals.MoveToSurfaceGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;

public class SoldierEntity extends Monster {

	public SoldierEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);

		this.setPersistenceRequired();

	}

	public static AttributeSupplier setAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 1000.0D)
				.add(Attributes.ATTACK_DAMAGE, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.23F).build();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(4, new MoveToSurfaceGoal(this));

		this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, NamekianEntity.class, true));
		this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Villager.class, true));
		this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(10, new HurtByTargetGoal(this));
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor pLevel, MobSpawnType pReason) {
		return true;
	}

	public static boolean canSpawnHere(EntityType<? extends SoldierEntity> entity, ServerLevelAccessor world, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		if (world.getDifficulty() != Difficulty.PEACEFUL) {
			return world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity);
		}
		return false;
	}

	@Override
	public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
		return false;
	}

	@Override
	public boolean isPersistenceRequired() {
		return true;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return MainSounds.FRIEZA_SOLDIER_AMBIENT.get();
	}

	@Override
	protected @NotNull SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
		return MainSounds.FRIEZA_SOLDIER_HURT.get();
	}

	@Override
	protected @NotNull SoundEvent getDeathSound() {
		return MainSounds.FRIEZA_SOLDIER_DEATH.get();
	}

}