package com.yuseix.dragonminez.init.entity.custom;

import com.yuseix.dragonminez.events.RadarEvents;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.init.menus.screens.PorungaMenu;
import com.yuseix.dragonminez.world.NamekDragonBallGenProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PorungaEntity extends Mob implements GeoEntity {
	private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
	private long invokingTime;

	private int tiempo = 20 * 5; //Tiempo de desaparicion despues de que los deseos sean 0 (20 ticks = 1 segundo)
	private static final EntityDataAccessor<String> OWNER_NAME = SynchedEntityData.defineId(PorungaEntity.class, EntityDataSerializers.STRING);
	private static final EntityDataAccessor<Integer> DESEOS = SynchedEntityData.defineId(PorungaEntity.class, EntityDataSerializers.INT);

	public PorungaEntity(EntityType<? extends Mob> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);

		this.entityData.define(OWNER_NAME, "");
		this.entityData.define(DESEOS, 0); // deseos
	}

	public static AttributeSupplier setAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 25000.0D)
				.add(Attributes.ATTACK_DAMAGE, 10.5f)
				.add(Attributes.ATTACK_SPEED, 0.5f)
				.add(Attributes.MOVEMENT_SPEED, 0.18F).build();
	}

	public void setOwnerName(String name) {
		this.entityData.set(OWNER_NAME, name);
	}

	public String getOwnerName() {
		return this.entityData.get(OWNER_NAME);
	}

	public int getDeseos() {
		return this.entityData.get(DESEOS);
	}

	public void setDeseos(int deseos) {
		this.entityData.set(DESEOS, deseos);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 35.0f));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (this.level().isClientSide) {
			if (this.getOwnerName().equals(player.getName().getString())) {
				if (getDeseos() > 0 && Minecraft.getInstance().player.equals(player)) {
					Minecraft.getInstance().setScreen(new PorungaMenu(0));
				}
			}
		}
		return super.mobInteract(player, hand);
	}


	public void setInvokingTime(long time) {
		this.invokingTime = time;
	}

	public long getInvokingTime() {
		return invokingTime;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
		controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));

	}

	@Override
	public void tick() {
		super.tick();
		//System.out.println("[P] Deseos del Jugador: " + getDeseos());
		//System.out.println("[P] Nombre del jugador: " + getOwnerName());


		if (this.getDeseos() == 0) {
			tiempo--;
		}

		if (this.tickCount == 1) namekDragonBallPositions.clear();

		if (tiempo == 0) {
			this.discard();
		}
	}

	private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {

		tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.porunga.idle", Animation.LoopType.LOOP));

		return PlayState.CONTINUE;

	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public boolean canCollideWith(Entity pEntity) {
		return false;
	}

	@Override
	public boolean canBeHitByProjectile() {
		return false;
	}

	@Override
	public void remove(RemovalReason reason) {
		if (!this.level().isClientSide && reason == RemovalReason.DISCARDED) {
			onDespawn();
		}
		super.remove(reason);
	}

	private static final List<BlockPos> namekDragonBallPositions = new ArrayList<>();

	private void onDespawn() {
		if (this.level() instanceof ServerLevel serverWorld) {
			serverWorld.getCapability(NamekDragonBallGenProvider.CAPABILITY).ifPresent(namekDragonBallsCapability -> {

				if (namekDragonBallsCapability.hasNamekDragonBalls()) {
					namekDragonBallsCapability.setHasNamekDragonBalls(false);
				}

				boolean hasNamekDragonBalls = namekDragonBallsCapability.hasNamekDragonBalls();

				if (!hasNamekDragonBalls) {
					spawnNamekDragonBall(serverWorld, MainBlocks.DBALL1_NAMEK_BLOCK.get().defaultBlockState());
					spawnNamekDragonBall(serverWorld, MainBlocks.DBALL2_NAMEK_BLOCK.get().defaultBlockState());
					spawnNamekDragonBall(serverWorld, MainBlocks.DBALL3_NAMEK_BLOCK.get().defaultBlockState());
					spawnNamekDragonBall(serverWorld, MainBlocks.DBALL4_NAMEK_BLOCK.get().defaultBlockState());
					spawnNamekDragonBall(serverWorld, MainBlocks.DBALL5_NAMEK_BLOCK.get().defaultBlockState());
					spawnNamekDragonBall(serverWorld, MainBlocks.DBALL6_NAMEK_BLOCK.get().defaultBlockState());
					spawnNamekDragonBall(serverWorld, MainBlocks.DBALL7_NAMEK_BLOCK.get().defaultBlockState());

					namekDragonBallsCapability.setNamekDragonBallPositions(namekDragonBallPositions);
					RadarEvents.updateDragonBallsPositions(namekDragonBallPositions);
					namekDragonBallsCapability.setHasNamekDragonBalls(true);
				}
			});
		}
	}

	private void spawnNamekDragonBall(ServerLevel serverWorld, BlockState dragonBall) {
		BlockPos spawnPos = serverWorld.getSharedSpawnPos();
		Random random = new Random();

		int x = spawnPos.getX() + random.nextInt(10000) - 5000;
		int z = spawnPos.getZ() + random.nextInt(10000) - 5000;

		serverWorld.getChunk(x >> 4, z >> 4);


		int y = serverWorld.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);

		BlockPos pos = new BlockPos(x, y, z);

		serverWorld.setBlock(pos, dragonBall, 2);
		System.out.println("Namekian Dragon Ball spawned at " + pos);

		namekDragonBallPositions.add(pos);
	}

	@Override
	public boolean hurt(DamageSource pSource, float pAmount) {
		if ("discarded".equals(pSource.getMsgId()) || "generic".equals(pSource.getMsgId()) || "out_of_world".equals(pSource.getMsgId()) || "genericKill".equals(pSource.getMsgId())) {
			return super.hurt(pSource, pAmount);
		}
		return false;
	}
}
