package com.yuseix.dragonminez.init.items.custom;

import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.common.stats.DMZStatsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MedicinaCorazonItem extends Item {
	private static final double HP_RESTORE_PERCENTAGE = 0.45; // 45%
	private static final double KI_RESTORE_PERCENTAGE = 0.05; // 5%
	private static final int HUNGER = 2;
	private static final float SATURATION = 20;

	public MedicinaCorazonItem() {
		super(new Properties().stacksTo(4).food(
				new FoodProperties.Builder()
						.nutrition(HUNGER)
						.saturationMod(SATURATION)
						.alwaysEat()
						.build()
		));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
		pTooltipComponents.add(Component.translatable("item.dragonminez.heart_medicine.tooltip").withStyle(ChatFormatting.GRAY));
	}

	@Override
	public @NotNull Component getName(@NotNull ItemStack pStack) {
		return Component.translatable("item.dragonminez.heart_medicine");
	}

	@Override
	public UseAnim getUseAnimation(ItemStack pStack) {
		return UseAnim.DRINK;
	}

	@Override
	public int getUseDuration(ItemStack pStack) {
		return 32; // Duración en ticks
	}

	@Override
	public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
		pPlayer.startUsingItem(pUsedHand);
		return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
		if (!pLevel.isClientSide && pLivingEntity instanceof ServerPlayer player) {
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {
				int maxHp = stats.getIntValue("maxhealth");
				int curarVida = (int) (maxHp * HP_RESTORE_PERCENTAGE);
				int maxKi = stats.getIntValue("maxenergy");
				int curarKi = (int) (maxKi * KI_RESTORE_PERCENTAGE);

				player.heal(curarVida);
				stats.addIntValue("curenergy", curarKi);
			});
			player.getFoodData().eat(HUNGER, SATURATION);
			player.displayClientMessage(Component.translatable("item.dragonminez.heart_medicine.use"), true);
		}
		if (pLivingEntity instanceof ServerPlayer player && player.isCreative()) {
			pStack.shrink(0);
		} else pStack.shrink(1);
		return pStack;
	}
}
