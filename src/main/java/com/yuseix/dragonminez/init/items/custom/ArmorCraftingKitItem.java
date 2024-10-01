package com.yuseix.dragonminez.init.items.custom;

import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.init.MainItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ArmorCraftingKitItem extends Item {
    public ArmorCraftingKitItem(Properties pProperties) {
        super(pProperties);
    }

    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
        ItemStack remItem = new ItemStack(this);
        MainItems.ARMOR_CRAFTING_KIT.get();
        return remItem;
    }
}
