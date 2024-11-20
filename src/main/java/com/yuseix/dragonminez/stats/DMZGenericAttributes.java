package com.yuseix.dragonminez.stats;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.mixin.AccessorRangedAttribute;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DMZGenericAttributes {

    @SubscribeEvent
    public static void onLoadComplete(FMLLoadCompleteEvent event) {
        Attribute armorAttribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.armor"));
        Attribute armorToughnessAttribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.armor_toughness"));
        Attribute maxHealth = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("minecraft:generic.max_health"));

        if (armorAttribute instanceof RangedAttribute) {
            AccessorRangedAttribute accessor = (AccessorRangedAttribute) armorAttribute;
            accessor.setMaxValue(30000.0);
        }

        if (armorToughnessAttribute instanceof RangedAttribute) {
            AccessorRangedAttribute accessor = (AccessorRangedAttribute) armorToughnessAttribute;
            accessor.setMaxValue(30000.0);
        }

        if (maxHealth instanceof RangedAttribute) {
            AccessorRangedAttribute accessor = (AccessorRangedAttribute) maxHealth;
            accessor.setMaxValue(10000000.0);
        }
    }
}
