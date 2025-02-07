package com.yuseix.dragonminez.init.entity.custom;


import com.yuseix.dragonminez.init.menus.screens.DendeMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class DendeEntity extends MastersEntity implements GeoEntity {
	private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

	public DendeEntity(EntityType<? extends Mob> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		this.setPersistenceRequired();
	}

	public static AttributeSupplier setAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 150.0D)
				.add(Attributes.ATTACK_DAMAGE, 10.5f)
				.add(Attributes.ATTACK_SPEED, 0.5f)
				.add(Attributes.MOVEMENT_SPEED, 0.18F).build();
	}


	@OnlyIn(Dist.CLIENT)
	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (this.level().isClientSide) {
			Minecraft.getInstance().setScreen(new DendeMenu());
			return InteractionResult.SUCCESS;
		}
		return super.mobInteract(player, hand);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
		controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));

	}

	private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
		if (tAnimationState.isMoving()) {
			tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.dende.walk", Animation.LoopType.LOOP));
		} else {
			tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.dende.idle", Animation.LoopType.LOOP));
		}
		return PlayState.CONTINUE;

	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

}
