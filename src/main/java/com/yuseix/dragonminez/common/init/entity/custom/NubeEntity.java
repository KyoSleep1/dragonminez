package com.yuseix.dragonminez.common.init.entity.custom;

import com.yuseix.dragonminez.common.init.MainItems;
import com.yuseix.dragonminez.common.init.MainSounds;
import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.common.stats.DMZStatsProvider;
import com.yuseix.dragonminez.client.util.Keys;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

import static com.yuseix.dragonminez.common.init.MainParticles.NIMBUS_TRACE_PARTICLE;

public class NubeEntity extends FlyingMob implements GeoEntity {

	private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

	public NubeEntity(EntityType<? extends FlyingMob> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		this.setNoGravity(true);
		this.setPersistenceRequired();

	}

	public static AttributeSupplier createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.05D)
				.add(Attributes.FLYING_SPEED, 3.4D) // velocidad de vuelo
				.build();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
	}

	@Override
	protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
		if (!this.level().isClientSide) {
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pPlayer).ifPresent(playerstats -> {
				if (playerstats.getIntValue("alignment") < 60) {
					pPlayer.displayClientMessage(Component.translatable("dmz.nimbus.fail"), true);
				} else pPlayer.startRiding(this);
			});
		}
		return InteractionResult.sidedSuccess(this.level().isClientSide);
	}

	@Override
	public void travel(Vec3 pTravelVector) {
		if (this.isVehicle() && this.getControllingPassenger() instanceof Player) {
			Player player = (Player) this.getControllingPassenger();

			double flightSpeed = 2.5D;

			float strafe = (float) (player.xxa * flightSpeed);
			float forward = (float) (player.zza * flightSpeed);

			boolean isJumping = Minecraft.getInstance().options.keyJump.isDown();
			float vertical = (float) (isJumping ? flightSpeed : (player.isCrouching() ? -flightSpeed : 0));

			// Crear vector de movimiento
			Vec3 movement = new Vec3(strafe, vertical, forward);

			// Solo actualizar la rotación si el jugador se está moviendo
			if (strafe != 0 || forward != 0 || vertical != 0) {
				this.yRotO = player.yRotO;
				this.setYRot(player.getYRot());
				this.xRotO = player.xRotO;
				this.setXRot(player.getXRot());
			}

			if (this.isControlledByLocalInstance()) {
				this.moveRelative(0.4F, movement);
				this.move(MoverType.SELF, this.getDeltaMovement());
			} else {
				super.travel(pTravelVector);
			}

			if (Keys.DESCEND_KEY.isDown()) {
				//Velocidad de descenso cuando se presiona la tecla
				double descentSpeed = -0.2;
				Vec3 descentMovement = new Vec3(0, descentSpeed, 0);
				this.setDeltaMovement(descentMovement);
				this.move(MoverType.SELF, this.getDeltaMovement());
			} else {
				// Mantener la nube en su posicion cuando no se esta presionando la tecla wa :v
				Vec3 currentMovement = new Vec3(0, 0, 0);
				this.setDeltaMovement(currentMovement);
				this.move(MoverType.SELF, this.getDeltaMovement());
			}

			// Spawn particles
			if (this.level().isClientSide) {
				this.level().addParticle(NIMBUS_TRACE_PARTICLE.get(), this.getX(), this.getY(), this.getZ(), 0, 0, 0);
			}
		} else {
			//Velocidad de descenso de la nube cuando el jugador se baja
			double descentSpeed = -0.02;
			Vec3 descentMovement = new Vec3(0, descentSpeed, 0);
			this.setDeltaMovement(descentMovement);
			this.move(MoverType.SELF, this.getDeltaMovement());
		}
	}

	@Override
	@Nullable
	public LivingEntity getControllingPassenger() {
		if (this.getFirstPassenger() instanceof LivingEntity) {
			return (LivingEntity) this.getFirstPassenger();
		} else {
			return null;
		}
	}

	@Override
	public boolean hurt(DamageSource pSource, float pAmount) {
		if ("player".equals(pSource.getMsgId()) && pSource.getEntity() instanceof Player) {
			if (!this.level().isClientSide && isAlive()) {
				this.spawnAtLocation(MainItems.NUBE_ITEM.get());
				this.remove(RemovalReason.KILLED);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource pSource) {
		// La entidad es invulnerable a todos los tipos de daño excepto player
		return !"player".equals(pSource.getMsgId()) || super.isInvulnerableTo(pSource);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return MainSounds.NUBE.get();
	}

	@Override
	protected float getSoundVolume() {
		return 1.0F;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}
}
