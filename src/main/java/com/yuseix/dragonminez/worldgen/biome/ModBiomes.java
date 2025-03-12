package com.yuseix.dragonminez.worldgen.biome;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.worldgen.ModPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {

    public static final ResourceKey<Biome> AJISSA_PLAINS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "ajissa_plains"));

    public static final ResourceKey<Biome> SACRED_LAND = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "sacred_land"));

    public static final ResourceKey<Biome> NAMEKIAN_RIVERS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "namekian_rivers"));

    public static final ResourceKey<Biome> TIME_CHAMBER = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "hyperbolic_time_chamber"));

    public static final ResourceKey<Biome> OTHERWORLD = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "other_world"));

    public static final ResourceKey<Biome> ROCKY = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "rocky"));

    public static void boostrap(BootstapContext<Biome> context){
        context.register(AJISSA_PLAINS, ajisa_plainsBiome(context));
        context.register(SACRED_LAND, sacredBiome(context));
        context.register(NAMEKIAN_RIVERS, namekianRiverBiome(context));
        context.register(TIME_CHAMBER, Time_Chamber_Biome(context));
        context.register(OTHERWORLD, otherworldBiome(context));
        context.register(ROCKY, rockyBiome(context));

    }

    public static Biome ajisa_plainsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        ModBiomes.addNamekCarversAndLakes(biomeBuilder);
        ModBiomes.addNamekSprings(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TREES_AJISSA_PLACED);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NAMEK_PATCH_GRASS_PLAIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NAMEK_PLAINS_FLOWERS);



        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(1479227)
                        .waterFogColor(676390)
                        .skyColor(6530427)
                        .grassColorOverride(0x2AA5EC)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(14217783)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome sacredBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        ModBiomes.addNamekCarversAndLakes(biomeBuilder);
        ModBiomes.addNamekSprings(biomeBuilder);


        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TREES_SACRED_PLACED);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NAMEK_PATCH_SACRED_GRASS_PLAIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NAMEK_SACRED_FLOWERS);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(1479227)
                        .waterFogColor(676390)
                        .skyColor(6530427)
                        .grassColorOverride(0x2AA5EC)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(14217783)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
    public static Biome namekianRiverBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.caveSpawns(spawnBuilder);
        BiomeDefaultFeatures.oceanSpawns(spawnBuilder, 1, 3, 2);
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 1, 1);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        ModBiomes.addNamekCarversAndLakes(biomeBuilder);
        ModBiomes.addNamekSprings(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(1479227)
                        .waterFogColor(676390)
                        .skyColor(6530427)
                        .grassColorOverride(0x2AA5EC)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(14217783)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
    public static Biome Time_Chamber_Biome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));


        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xDCF2FF)
                        .waterFogColor(0xDCF2FF)
                        .skyColor(0xF7FCFF)
                        .grassColorOverride(0xDCF2FF)
                        .foliageColorOverride(0xDCF2FF)
                        .fogColor(0xDCF2FF)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome otherworldBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)  // Sin lluvia ni nieve
                .downfall(0.0f)           // No hay precipitaciones
                .temperature(1.0f)        // Temperatura neutra
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(4214155)
                        .waterFogColor(4214120)
                        .skyColor(0xBE55AA)         // Color del cielo morado
                        .grassColorOverride(0xDCF2FF)
                        .foliageColorOverride(0xDCF2FF)
                        .fogColor(0xCE7EBD)          // Niebla morada
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome rockyBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.desertSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addFossilDecoration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addDesertVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.2f)
                .temperature(2.0f)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .skyColor(7776511)
                        .fogColor(12638463)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static void addNamekCarversAndLakes(BiomeGenerationSettings.Builder pBuilder) {
        pBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
        pBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND);
        pBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);
        pBuilder.addFeature(GenerationStep.Decoration.LAKES, ModPlacedFeatures.NAMEK_LAKE_LAVA_UNDERGROUND);
        pBuilder.addFeature(GenerationStep.Decoration.LAKES, ModPlacedFeatures.NAMEK_LAKE_LAVA_SURFACE);

    }
    public static void addNamekSprings(BiomeGenerationSettings.Builder pBuilder) {
        pBuilder.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, ModPlacedFeatures.NAMEK_SPRING_WATER);
        pBuilder.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, ModPlacedFeatures.NAMEK_SPRING_LAVA);

    }

}
