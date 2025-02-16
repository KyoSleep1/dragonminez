package com.yuseix.dragonminez.init.entity.custom.namek;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Dynamic;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.init.entity.goals.VillageAlertSystem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.VillagerGoalPackages;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.entity.schedule.Schedule;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NamekianVillagerEntity extends Villager {

	public NamekianVillagerEntity(EntityType<? extends Villager> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		this.setPersistenceRequired();
		this.setVillagerData(this.getVillagerData().setProfession(VillagerProfession.FLETCHER));
	}

	public static AttributeSupplier setAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 800.0D)
				.add(Attributes.ATTACK_DAMAGE, 100.5f)
				.add(Attributes.ATTACK_SPEED, 1.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.25F).build();
	}

	@Override
	protected void registerGoals() {
		// Permite que la entidad flote en el agua
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new PanicGoal(this, 2.0D));

		// Camina de manera aleatoria evitando el agua
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.2D));

		// Mirada aleatoria
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));

	}

	@Override
	public boolean isPersistenceRequired() {
		return true;
	}

	@Override
	public void checkDespawn() {
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		boolean isHurt = super.hurt(source, amount);

		if (isHurt && source.getEntity() instanceof Player) {
			Player player = (Player) source.getEntity();
			VillageAlertSystem.alertAll(player); // Alertar a todos los guerreros
		}

		return isHurt;
	}


	private static final List<CustomTrade> TRADES = new ArrayList<>();

	static {
		// (new ItemStack(ITEM REQUERIDO, CANTIDAD), new ItemStack(ITEM REQUERIDO 2, CANTIDAD), new ItemStack(ITEM COMPRADO, CANTIDAD), USOS MAXIMOS, XP));
		TRADES.add(new CustomTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.CARROT, 3), new ItemStack(MainItems.FROG_LEGS_RAW.get(), 2), 10, 3));
		TRADES.add(new CustomTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(Items.BUCKET, 1), new ItemStack(MainItems.HEALING_BUCKET.get(), 1), 3, 2));
		TRADES.add(new CustomTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.AIR), new ItemStack(MainBlocks.NAMEK_BLOCK.get(), 8), 64, 0));
		TRADES.add(new CustomTrade(new ItemStack(Items.EMERALD, 32), new ItemStack(Items.AIR), new ItemStack(MainItems.T2_RADAR_CHIP.get(), 1), 2, 6));
		TRADES.add(new CustomTrade(new ItemStack(Items.EMERALD, 16), new ItemStack(Items.ANCIENT_DEBRIS, 1), new ItemStack(Items.NETHERITE_SCRAP, 2), 16, 3));
		TRADES.add(new CustomTrade(new ItemStack(Items.EMERALD, 28), new ItemStack(Items.DIAMOND, 4), new ItemStack(Items.NETHERITE_SCRAP, 2), 4, 4));
		TRADES.add(new CustomTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.AIR), new ItemStack(Items.CARROT, 3), 10, 1));
		TRADES.add(new CustomTrade(new ItemStack(Items.CARROT, 8), new ItemStack(Items.AIR), new ItemStack(Items.EMERALD, 2), 10, 2));
		TRADES.add(new CustomTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.AIR), new ItemStack(Items.POTATO, 3), 10, 1));
		TRADES.add(new CustomTrade(new ItemStack(Items.POTATO, 8), new ItemStack(Items.AIR), new ItemStack(Items.EMERALD, 2), 10, 2));
		TRADES.add(new CustomTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.AIR), new ItemStack(Items.BEETROOT, 3), 10, 1));
		TRADES.add(new CustomTrade(new ItemStack(Items.BEETROOT, 8), new ItemStack(Items.AIR), new ItemStack(Items.EMERALD, 2), 10, 2));
	}

	@Override
	public boolean wantsToSpawnGolem(long pGameTime) {
		return false;
	}


	@Override
	protected Brain<?> makeBrain(Dynamic<?> dynamic) {
		Brain<Villager> brain = (Brain<Villager>) super.makeBrain(dynamic);
		this.registerBrainGoals(brain);
		return brain;
	}

	@Override
	public void refreshBrain(ServerLevel serverLevel) {
		Brain<Villager> brain = this.getBrain();
		brain.stopAll(serverLevel, this);
		this.brain = brain.copyWithoutBehaviors();
		brain.setSchedule(Schedule.VILLAGER_DEFAULT);
		brain.addActivity(Activity.CORE, VillagerGoalPackages.getCorePackage(VillagerProfession.FLETCHER, 0.5F));
		brain.addActivity(Activity.IDLE, VillagerGoalPackages.getIdlePackage(VillagerProfession.FLETCHER, 0.5F));
		brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
		brain.setDefaultActivity(Activity.IDLE);
		brain.setActiveActivityIfPossible(Activity.IDLE);
	}

	protected void registerBrainGoals(Brain<Villager> brain) {
		brain.setSchedule(Schedule.VILLAGER_DEFAULT);
		brain.addActivity(Activity.CORE, VillagerGoalPackages.getCorePackage(VillagerProfession.FLETCHER, 0.5F));
		brain.addActivity(Activity.IDLE, VillagerGoalPackages.getIdlePackage(VillagerProfession.FLETCHER, 0.5F));
		brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
		brain.setDefaultActivity(Activity.IDLE);
		brain.setActiveActivityIfPossible(Activity.IDLE);
	}

	@Override
	protected void customServerAiStep() {
		this.level().getProfiler().push("villagerBrain");
		this.getBrain().tick((ServerLevel) this.level(), this);
		this.level().getProfiler().pop();

		super.customServerAiStep();
	}

	@Override
	protected void updateTrades() {
		// Los tradeos NO deben cambiar aleatoriamente
		if (this.offers == null) {
			this.offers = new MerchantOffers();
		}

		// Si el aldeano no tiene intercambios, asigna los primeros 3 intercambios
		if (this.offers.isEmpty()) {
			Random random = new Random();
			List<CustomTrade> availableTrades = new ArrayList<>(TRADES);
			for (int i = 0; i < 3; i++) {
				if (availableTrades.isEmpty()) break;
				CustomTrade randomTrade = availableTrades.remove(random.nextInt(availableTrades.size()));
				this.offers.add(randomTrade.createOffer());
			}
		} else {
			// Si el aldeano ya tiene intercambios (ha subido de nivel), agrega 1 nuevo intercambio no repetido
			Random random = new Random();
			List<CustomTrade> availableTrades = new ArrayList<>(TRADES);

			for (MerchantOffer offer : this.offers) {
				availableTrades.removeIf(trade -> trade.createOffer().equals(offer));
			}

			if (!availableTrades.isEmpty()) {
				CustomTrade randomTrade = availableTrades.get(random.nextInt(availableTrades.size()));
				this.offers.add(randomTrade.createOffer());
			}
		}
	}

	private static class CustomTrade {
		private final ItemStack input;
		private final ItemStack secInput;
		private final ItemStack output;
		private final int maxUses;
		private final int xp;

		public CustomTrade(ItemStack input, ItemStack secInput, ItemStack output, int maxUses, int xp) {
			this.input = input;
			this.secInput = secInput;
			this.output = output;
			this.maxUses = maxUses;
			this.xp = xp;
		}

		public MerchantOffer createOffer() {
			return new MerchantOffer(input, secInput, output, maxUses, xp, 0.15F);
		}
	}

	@Override
	public MerchantOffers getOffers() {
		if (this.offers == null) {
			this.offers = new MerchantOffers();
			updateTrades();
		}
		return this.offers;
	}

	@Override
	public boolean canBreed() {
		return false;
	}

//	@Override
//	protected SoundEvent getAmbientSound() {
//		return MainSounds.NAMEKIAN_VILLAGER_AMBIENT.get();
//	}
//
//	@Override
//	protected @NotNull SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
//		return MainSounds.NAMEKIAN_VILLAGER_HURT.get();
//	}
//
//	@Override
//	protected @NotNull SoundEvent getDeathSound() {
//		return MainSounds.NAMEKIAN_VILLAGER_DEATH.get();
//	}
}
