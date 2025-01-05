package com.yuseix.dragonminez.init.items.custom;

import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class FrogLegsCookedItem extends Item {
    private static final double HP_RESTORE_PERCENTAGE = 0.07; // 7%
    private static final double KI_RESTORE_PERCENTAGE = 0.04; // 4%
    private static final int HUNGER = 5;
    private static final float SATURATION = 6;
    public FrogLegsCookedItem() {
        super(new Properties().food(
                new FoodProperties.Builder()
                        .nutrition(HUNGER)
                        .saturationMod(SATURATION)
                        .alwaysEat()
                        .build()
        ));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return Component.translatable("item.dragonminez.frog_legs_cooked");
    }

    // Curación Vida/Ki
    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide && pLivingEntity instanceof ServerPlayer player) {
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {
                int maxHp = stats.getMaxHealth();
                int curarVida = (int) (maxHp * HP_RESTORE_PERCENTAGE);
                int maxKi = stats.getMaxEnergy();
                int curarKi = (int) (maxKi * KI_RESTORE_PERCENTAGE);

                player.heal(curarVida);
                stats.addCurEnergy(curarKi);
            });
            player.getFoodData().eat(HUNGER, SATURATION);
        }
        if (pLivingEntity instanceof ServerPlayer player && player.isCreative()) {
            pStack.shrink(0);
        } else pStack.shrink(1);
        return pStack;
    }
}
