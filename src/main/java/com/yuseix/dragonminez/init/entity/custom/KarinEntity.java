package com.yuseix.dragonminez.init.entity.custom;

import com.yuseix.dragonminez.init.menus.screens.KarinMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

public class KarinEntity extends Mob implements GeoEntity {
	private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

	public KarinEntity(EntityType<? extends Mob> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		this.setPersistenceRequired();
		this.setNoGravity(true);

	}

	public static AttributeSupplier setAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 50.0D)
				.add(Attributes.ATTACK_DAMAGE, 10.5f)
				.add(Attributes.ATTACK_SPEED, 0.5f)
				.add(Attributes.MOVEMENT_SPEED, 0.18F).build();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 1.0f));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (this.level().isClientSide) {
			Minecraft.getInstance().setScreen(new KarinMenu());
			return InteractionResult.SUCCESS;
		}
		return super.mobInteract(player, hand);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

	@Nullable
	@Override
	public LivingEntity getControllingPassenger() {
		return null;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public boolean canCollideWith(Entity entity) {
		return !(entity instanceof Player); // Evita colisión con jugadores
	}

	@Override
	public boolean canBeHitByProjectile() {
		return false;
	}


	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean isPersistenceRequired() {
		return true;
	}

	@Override
	public void checkDespawn() {

	}

	@Override
	public boolean hurt(DamageSource pSource, float pAmount) {
		if ("generic_kill".equals(pSource.getMsgId()) || "generic".equals(pSource.getMsgId()) || "out_of_world".equals(pSource.getMsgId()) || "genericKill".equals(pSource.getMsgId())) {
			return super.hurt(pSource, pAmount);
		}
		return false;
	}
}
