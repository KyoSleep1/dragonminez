package com.yuseix.dragonminez.common.init.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DMZWeaponItem extends SwordItem {
	private final String tag;

	public DMZWeaponItem(int damageBase, float attackSpeed, int durability, String tag) {
		super(DMZToolTiers.BLANK_WEAPON_TIER, damageBase, attackSpeed, new Properties().durability(durability));
		this.tag = tag;
	}

	@Override
	public @NotNull Component getName(@NotNull ItemStack pStack) {
		// Usa el "tag" para generar dinámicamente el nombre
		return Component.translatable("dmz.weapons." + this.tag);
	}

	@Override
	public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
		// Usa el "tag" para generar dinámicamente el tooltip
		pTooltipComponents.add(Component.translatable("dmz.weapons." + this.tag + ".tooltip").withStyle(ChatFormatting.GRAY));
	}
}