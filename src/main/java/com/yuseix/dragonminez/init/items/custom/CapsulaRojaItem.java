package com.yuseix.dragonminez.init.items.custom;

import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CapsulaRojaItem extends Item {
    public CapsulaRojaItem() {
        super(new Item.Properties());
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return Component.translatable("item.dragonminez.red_capsule");
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.dragonminez.red_capsule.tooltip").withStyle(ChatFormatting.GRAY));
        pTooltipComponents.add(Component.translatable("item.dragonminez.red_capsule.tooltip2").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack capsula = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.NEUTRAL, 1.5F, 1.0F);

        if (!pLevel.isClientSide) {
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pPlayer).ifPresent(stats -> stats.addStrength(5));

            pPlayer.displayClientMessage(Component.translatable("item.dragonminez.red_capsule.str.use").withStyle(ChatFormatting.GREEN), true);
        }

        capsula.shrink(1);
        return InteractionResultHolder.sidedSuccess(capsula, pLevel.isClientSide());
    }
}
