package com.yuseix.dragonminez.mixin;

import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class ItemStackMixin {

	@Inject(method = "hurtAndBreak", at = @At("HEAD"), cancellable = true)
	public <T extends LivingEntity> void onHurtAndBreak(int pAmount, T pEntity, Consumer<T> pOnBroken, CallbackInfo ci) {
		// Verificar si el ítem es una pieza de armadura
		if (((ItemStack) (Object) this).getItem() instanceof ArmorItem) {
			// Reducir la durabilidad de 1 en 1, ignorando el valor de "amount"
			int unbreakingLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, (ItemStack) (Object) this);

			// Aplicar lógica de Unbreaking
			if (unbreakingLevel > 0) {
				Random rand = new Random();
				// Probabilidad de no aplicar daño: unbreakingLevel / (unbreakingLevel + 1)
				if (rand.nextInt(unbreakingLevel + 1) < unbreakingLevel) {
					ci.cancel(); // Cancelar el daño esta vez
					return;
				}
			}

			// Aplicar daño de 1 en 1
			((ItemStack) (Object) this).setDamageValue(((ItemStack) (Object) this).getDamageValue() + 1);

			// Si la durabilidad llega a 0, romper el ítem
			if (((ItemStack) (Object) this).getDamageValue() >= ((ItemStack) (Object) this).getMaxDamage()) {
				((Player)pEntity).awardStat(Stats.ITEM_BROKEN.get(((ItemStack) (Object) this).getItem()));
			}

			ci.cancel();
		}
	}
}
