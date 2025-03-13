package com.yuseix.dragonminez.common.init.items.custom;

import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.common.stats.DMZStatsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MightTreeFruitItem extends Item {
    private static final double HP_RESTORE_PERCENTAGE = 0.05; // 5%
    private static final double KI_RESTORE_PERCENTAGE = 0.35; // 35%
    private static final int HUNGER = 2;
    private static final float SATURATION = 20;
    public MightTreeFruitItem() {
        super(new Properties().stacksTo(1).food(
                new FoodProperties.Builder()
                        .nutrition(HUNGER)
                        .saturationMod(SATURATION)
                        .alwaysEat()
                        .build()
        ));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return Component.translatable("item.dragonminez.might_tree_fruit");
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.dragonminez.might_tree_fruit.tooltip").withStyle(ChatFormatting.GRAY));
    }

    // Curación Vida/Ki + Mult DMG
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

                // Aplicar efecto temporal del fruto
                int duracionTicks = 20 * 60; // Ticks * Segundos
                stats.addDMZTemporalEffect("mightfruit", duracionTicks);
            });
            player.getFoodData().eat(HUNGER, SATURATION);
            player.displayClientMessage(Component.translatable("item.dragonminez.might_tree_fruit.use"), true);
        }
        if (pLivingEntity instanceof ServerPlayer player && player.isCreative()) {
            pStack.shrink(0);
        } else pStack.shrink(1);
        return pStack;
    }

}
