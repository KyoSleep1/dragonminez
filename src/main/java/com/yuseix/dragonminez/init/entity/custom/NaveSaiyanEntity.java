package com.yuseix.dragonminez.init.entity.custom;

import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.utils.Keys;
import net.minecraft.client.Minecraft;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
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
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class NaveSaiyanEntity extends Mob implements GeoEntity {

	private static final EntityDataAccessor<Boolean> IS_OPEN = SynchedEntityData.defineId(NaveSaiyanEntity.class, EntityDataSerializers.BOOLEAN);
	private static final RawAnimation ANIM_ABIERTO = RawAnimation.begin().then("animation.navesaiyan.open", Animation.LoopType.HOLD_ON_LAST_FRAME);
	private static final RawAnimation ANIM_CERRADO = RawAnimation.begin().then("animation.navesaiyan.close", Animation.LoopType.HOLD_ON_LAST_FRAME);

	private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

	public NaveSaiyanEntity(EntityType<? extends Mob> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		//this.setNoAi(true);
		this.entityData.define(IS_OPEN, false);
	}

	public static AttributeSupplier setAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 50.0D)
				.add(Attributes.ATTACK_DAMAGE, 50.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.5F)
				.add(Attributes.FLYING_SPEED, 2.4F).build();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (!this.level().isClientSide) { // Solo ejecuta en el servidor
			if (!isOpen()) { // Abre la nave al interactuar (Ahora esta cerrada)
				if (!player.isPassenger()) {
					setOpenNave(true);
					//Sonido epicardo de la nave abriendose
					player.level().playSound(null, this.getOnPos(), MainSounds.NAVE_OPEN.get(), SoundSource.NEUTRAL, 0.5F, 1.0F);
				}
			} else { // Si la nave está abierta, el jugador se puede montar y cierra la nave
				setOpenNave(false);
				if (!player.isPassenger()) {
					player.startRiding(this);
				}
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void travel(Vec3 travelVector) {
		LivingEntity controllingPassenger = this.getControllingPassenger();
		float upward = 0.0f; // Movimiento vertical

		if (controllingPassenger instanceof Player player) {
			this.setNoGravity(true);  // Desactiva la gravedad para volar

			// Movimiento hacia adelante y lateral (según la cámara del jugador)
			float forward = player.zza;  // Movimiento hacia adelante y atrás (W/S)
			float strafe = player.xxa;   // Movimiento lateral (izquierda/derecha - A/D)

			if (Minecraft.getInstance().options.keyJump.isDown()) {
				upward = 0.3f;
			} else if (Keys.DESCEND_KEY.isDown()) {
				upward = -0.3f;
			} else if (!this.onGround()) { //Descenso lento
				upward = -0.00f;
			}


			Vec3 cameraDirection = player.getLookAngle().normalize();

			cameraDirection = new Vec3(cameraDirection.x, 0, cameraDirection.z).normalize();

			Vec3 forwardMovement = cameraDirection.scale(forward * 0.5);  // Movimiento hacia adelante y atrás

			Vec3 rightDirection = new Vec3(cameraDirection.z, 0, -cameraDirection.x).normalize(); // Dirección derecha
			Vec3 strafeMovement = rightDirection.scale(strafe * 0.5);  // Movimiento lateral

			Vec3 movement = forwardMovement.add(strafeMovement).add(0, upward, 0);

			if (cameraDirection.length() > 0.1f) {  // Solo gira si hay movimiento
				double angle = Math.atan2(cameraDirection.z, cameraDirection.x);  // Calcula el ángulo de rotación
				this.setYRot((float) Math.toDegrees(angle) - 90);  // Ajusta la rotación de la nave (invirtiendo el ángulo)
			}

			// Aplica el movimiento a la nave
			this.setDeltaMovement(movement);
			this.move(MoverType.SELF, movement);  // Aplica el movimiento
			this.hasImpulse = true;

		} else {
			this.setNoGravity(false);
			super.travel(travelVector);
		}
	}

	@Override
	public LivingEntity getControllingPassenger() {
		// Busca entre los pasajeros de la nave al primero que sea una instancia de Player
		for (Entity passenger : this.getPassengers()) {
			if (passenger instanceof Player) {
				return (LivingEntity) passenger;  // Devuelve el jugador que controla la nave
			}
		}

		return null;  // Devuelve null si no hay ningún jugador controlando la nave
	}

	@Override
	protected void positionRider(Entity pPassenger, MoveFunction pCallback) {
		// Verifica que el pasajero sea un jugador
		if (pPassenger instanceof Player) {
			double offsetX = 0.0;  // Ajuste lateral (déjalo en 0 para evitar desplazamiento lateral indeseado)
			double offsetY = 0.001; // Ajuste vertical menor para que esté ligeramente sobre la nave
			double offsetZ = 0.0;  // Ajuste hacia adelante (opcional)

			// Calcula la posición del asiento con relación a la rotación de la nave
			Vec3 seatPosition = new Vec3(offsetX, offsetY, offsetZ).yRot((float) Math.toRadians(-this.getYRot()));

			// Aplica la posición ajustada al jugador
			pCallback.accept(pPassenger, this.getX() + seatPosition.x, this.getY() + seatPosition.y, this.getZ() + seatPosition.z);
		} else {
			super.positionRider(pPassenger, pCallback);
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
		controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

	public boolean isOpen() {
		return this.entityData.get(IS_OPEN);
	}

	public void setOpenNave(boolean open) {
		this.entityData.set(IS_OPEN, open);
	}

	@Override
	public boolean hurt(DamageSource pSource, float pAmount) {
		if ("player".equals(pSource.getMsgId()) && pSource.getEntity() instanceof Player) {
			if (!this.level().isClientSide && isAlive()) {
				this.spawnAtLocation(MainItems.NAVE_SAIYAN_ITEM.get());
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

	@Override
	public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
		return false;
	}

	private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
		AnimationController<?> controller = tAnimationState.getController();

		if (isOpen()) {
			controller.setAnimation(ANIM_ABIERTO);
		} else {
			controller.setAnimation(ANIM_CERRADO);
		}

		return PlayState.CONTINUE;
	}
}
