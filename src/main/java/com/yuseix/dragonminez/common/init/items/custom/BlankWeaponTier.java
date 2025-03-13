package com.yuseix.dragonminez.common.init.items.custom;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class BlankWeaponTier implements Tier {
	private final int uses;                 // Durabilidad
	private final float speed;              // Velocidad de minado (irrelevante para armas)
	private final float attackDamageBonus;  // Daño base
	private final int level;                // Nivel de minería (irrelevante para armas)
	private final int enchantmentValue;     // Facilidad para encantar
	private final Ingredient repairIngredient; // Material de reparación

	public BlankWeaponTier(int uses, float speed, float attackDamageBonus, int level, int enchantmentValue, Ingredient repairIngredient) {
		this.uses = uses;
		this.speed = speed;
		this.attackDamageBonus = attackDamageBonus;
		this.level = level;
		this.enchantmentValue = enchantmentValue;
		this.repairIngredient = repairIngredient;
	}

	@Override
	public int getUses() {
		return this.uses; // Durabilidad
	}

	@Override
	public float getSpeed() {
		return this.speed; // Velocidad de minado (irrelevante para armas)
	}

	@Override
	public float getAttackDamageBonus() {
		return this.attackDamageBonus; // Daño base
	}

	@Override
	public int getLevel() {
		return this.level; // Nivel de minería (irrelevante para armas)
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantmentValue; // Facilidad para encantar
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient; // Material de reparación
	}
}
