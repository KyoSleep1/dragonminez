package com.yuseix.dragonminez.server.worldgen.feature;

import com.yuseix.dragonminez.common.Reference;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CustomFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES_REGISTER
            = DeferredRegister.create(ForgeRegistries.FEATURES, Reference.MOD_ID);

    /*public static final RegistryObject<Feature<NoneFeatureConfiguration>> GETE_STAR
            = FEATURES_REGISTER.register("gete_star", () -> new GeteStarFeature(NoneFeatureConfiguration.CODEC));*/
}
