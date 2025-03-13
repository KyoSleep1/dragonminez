package com.yuseix.dragonminez.common.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;

public class GenAttRegistry {

    public Attribute get(ResourceLocation id) {
        return ForgeRegistries.ATTRIBUTES.getValue(id);
    }

    public ResourceLocation getId(Attribute value) {
        return ForgeRegistries.ATTRIBUTES.getKey(value);
    }

    public boolean isRegistered(Attribute value) {
        return ForgeRegistries.ATTRIBUTES.containsValue(value);
    }

    public boolean exists(ResourceLocation id) {
        return ForgeRegistries.ATTRIBUTES.containsKey(id);
    }

    public Collection<Attribute> getValues() {
        return ForgeRegistries.ATTRIBUTES.getValues();
    }
}
