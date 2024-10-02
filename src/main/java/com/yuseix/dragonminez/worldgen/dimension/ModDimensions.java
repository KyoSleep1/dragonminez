package com.yuseix.dragonminez.worldgen.dimension;

import com.yuseix.dragonminez.DragonMineZ;
import com.mojang.datafixers.util.Pair;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions extends NoiseRouterData{

    /*

    LEVEL STEM = Complemento del level, ambos sirven xd
    LEVEL = Esto si lo necesitamos porque basicamente es el mundo
    DIMENSIONTYPE = Esto es para establecer reglas en nuestro mundo

     */

    //Namek
    public static final ResourceKey<Level> NAMEK_DIM_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DragonMineZ.MOD_ID, "namek"));
    public static final ResourceKey<LevelStem> NAMEK_DIM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DragonMineZ.MOD_ID, "namek"));
    public static final ResourceKey<DimensionType> NAMEK_DIM_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE,
                    new ResourceLocation(DragonMineZ.MOD_ID, "namek_type"));

    //Habitación del Tiempo
    public static final ResourceKey<Level> TIME_CHAMBER_DIM_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DragonMineZ.MOD_ID, "time_chamber"));
    public static final ResourceKey<LevelStem> TIME_CHAMBER_DIM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DragonMineZ.MOD_ID, "time_chamber"));
    public static final ResourceKey<DimensionType> TIME_CHAMBER_DIM_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE,
                    new ResourceLocation(DragonMineZ.MOD_ID, "time_chamber_type"));

    //NOISE SETTINGS CUSTOM
    public static final ResourceKey<NoiseGeneratorSettings> NAMEK_NOISE_SETTINGS = ResourceKey.create(Registries.NOISE_SETTINGS, new ResourceLocation(DragonMineZ.MOD_ID, "nameknoisegen"));
    public static final ResourceKey<NoiseGeneratorSettings> TIME_CHAMBER_NOISE_SETTINGS = ResourceKey.create(Registries.NOISE_SETTINGS, new ResourceLocation(DragonMineZ.MOD_ID, "time_chamber_noisegen"));


    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(NAMEK_DIM_TYPE, new DimensionType(
                OptionalLong.of(7500), // fixedTime
                true, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                3.0, // coordinateScale
                true, // bedWorks
                true, // respawnAnchorWorks
                -64, // minY
                384, // height
                384, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                0.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));

        context.register(TIME_CHAMBER_DIM_TYPE, new DimensionType(
                OptionalLong.of(7500), // fixedTime
                true, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                true, // bedWorks
                true, // respawnAnchorWorks
                0, // minY
                64, // height
                64, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                0.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        //Noise custom (Ya esta hecho en json pq en codigo es una japa
        //Aca es para poner un solo bioma en toda la dimension
        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.TIME_CHAMBER)),
                noiseGenSettings.getOrThrow(TIME_CHAMBER_NOISE_SETTINGS));


        //Varios Biomas
        NoiseBasedChunkGenerator noiseNamekMultiBiomes = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(Pair.of(
                                        Climate.parameters(0.0F, 0.0F, 0.1F, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.AJISSA_PLAINS)),
                                Pair.of(
                                        Climate.parameters(0.0F, 0.0F, 0.6F, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.SACRED_LAND)),
                                Pair.of(
                                        Climate.parameters(0.0F, 0.0F, -0.45f, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.NAMEKIAN_RIVERS))
                        ))),
                noiseGenSettings.getOrThrow(NAMEK_NOISE_SETTINGS)); //Aca es poner nuestro namek noise

        LevelStem namek_stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.NAMEK_DIM_TYPE), noiseNamekMultiBiomes);
        LevelStem timechamber_stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.TIME_CHAMBER_DIM_TYPE), wrappedChunkGenerator);

        context.register(NAMEK_DIM_KEY, namek_stem);
        context.register(TIME_CHAMBER_DIM_KEY, timechamber_stem);

    }

    public static void bootstrapNoise(BootstapContext<NoiseGeneratorSettings> context) {
        //HolderGetter<NoiseGeneratorSettings> noiseGeneratorSettingsHolder = context.lookup(Registries.NOISE_SETTINGS);

        HolderGetter<DensityFunction> densityFunctions = context.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<NormalNoise.NoiseParameters> noiseParameters = context.lookup(Registries.NOISE);

        SurfaceRules.RuleSource time_chamber_surfaceRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(MainBlocks.TIME_CHAMBER_BLOCK.get().defaultBlockState()))
        );


        NoiseSettings namek_noiseSettings = NoiseSettings.create(
                -64,
                256,
                1,
                2);

        NoiseSettings time_chamber_noiseSettings = NoiseSettings.create(
                -16,
                384,
                1,
                2);

        NoiseGeneratorSettings namek_noisegen = new NoiseGeneratorSettings(
                namek_noiseSettings,
                MainBlocks.NAMEK_STONE.get().defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                NoiseRouterData.overworld(context.lookup(Registries.DENSITY_FUNCTION), context.lookup(Registries.NOISE), false, false),
                namekSurfaceRules(),
                List.of(),
                63,
                false,
                true,
                true,
                false);

        context.register(NAMEK_NOISE_SETTINGS, namek_noisegen);

        NoiseGeneratorSettings time_chamber_noisegen = new NoiseGeneratorSettings(
                time_chamber_noiseSettings,
                MainBlocks.TIME_CHAMBER_BLOCK.get().defaultBlockState(),
                Blocks.AIR.defaultBlockState(),
                TimeChamber_noiseRouter(densityFunctions, noiseParameters),
                time_chamber_surfaceRule,
                List.of(),
                0,
                false,
                false,
                false,
                false);

        context.register(TIME_CHAMBER_NOISE_SETTINGS, time_chamber_noisegen);
    }

    private static NoiseRouter TimeChamber_noiseRouter(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noise) {
        // Densidad constante negativa por defecto para indicar aire/vacío
        DensityFunction constantNegative = DensityFunctions.constant(-1.0);
        // Densidad constante para bloques sólidos
        DensityFunction constantPositive = DensityFunctions.constant(1.0);
        // Genera una transición abrupta entre terreno sólido y vacío a la altura y = 4
        DensityFunction depthFunction = DensityFunctions.yClampedGradient(0, 24, 1.0, -1.0); // Cambia el valor de y para ajustar la altura del terreno

        return new NoiseRouter(
                constantNegative, // barrierNoise: No necesitamos barreras
                constantNegative, // fluidLevelFloodednessNoise: No necesitamos fluidos
                constantNegative, // fluidLevelSpreadNoise: No necesitamos propagación de fluidos
                constantNegative, // lavaNoise: Sin lava
                constantNegative, // temperature: Constante
                constantNegative, // vegetation: Constante
                constantNegative, // continents: No variaciones continentales
                constantNegative, // erosion: Sin erosión
                depthFunction,    // depth: Sólido hasta y = 4
                constantNegative, // ridges: Sin crestas
                depthFunction,    // initialDensityWithoutJaggedness: Sólido hasta y = 4
                depthFunction,    // finalDensity: Sólido hasta y = 4
                constantNegative, // veinToggle: Sin venas
                constantNegative, // veinRidged: Sin venas
                constantNegative  // veinGap: Sin venas
        );
    }

    public static SurfaceRules.RuleSource namekSurfaceRules() {
        // Regla para generar la capa de Bedrock
        SurfaceRules.RuleSource bedrockRule = SurfaceRules.ifTrue(
                SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(5)),
                SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())
        );

        // Regla para la superficie en los biomas ajissa_plains y namekian_rivers
        SurfaceRules.RuleSource namekSurfaceRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.AJISSA_PLAINS, ModBiomes.NAMEKIAN_RIVERS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.abovePreliminarySurface(),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(
                                                                SurfaceRules.waterBlockCheck(-1, 0),
                                                                SurfaceRules.state(MainBlocks.NAMEK_GRASS_BLOCK.get().defaultBlockState())
                                                        ),
                                                        SurfaceRules.state(MainBlocks.NAMEK_DIRT.get().defaultBlockState())
                                                )
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.yStartCheck(VerticalAnchor.absolute(50), 2),
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.stoneDepthCheck(0, false, 5, CaveSurface.FLOOR),
                                                        SurfaceRules.state(MainBlocks.NAMEK_DIRT.get().defaultBlockState())
                                                )
                                        )
                                )
                        )
                )
        );

        // Regla para el bioma sacred_land
        SurfaceRules.RuleSource sacredLandSurfaceRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.SACRED_LAND),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.abovePreliminarySurface(),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(
                                                                SurfaceRules.waterBlockCheck(0, 0),
                                                                SurfaceRules.state(MainBlocks.NAMEK_SACRED_GRASS_BLOCK.get().defaultBlockState())
                                                        ),
                                                        SurfaceRules.state(MainBlocks.NAMEK_DIRT.get().defaultBlockState())
                                                )
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.yStartCheck(VerticalAnchor.absolute(50), 2),
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.stoneDepthCheck(0, false, 5, CaveSurface.FLOOR),
                                                        SurfaceRules.state(MainBlocks.NAMEK_DIRT.get().defaultBlockState())
                                                )
                                        )
                                )
                        )
                )
        );

        // Regla para generar deepslate en ciertas profundidades
        SurfaceRules.RuleSource deepslateRule = SurfaceRules.ifTrue(
                SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)),
                SurfaceRules.state(MainBlocks.NAMEK_DEEPSLATE.get().defaultBlockState())
        );

        // Secuencia final de reglas de superficie
        return SurfaceRules.sequence(
                bedrockRule,            // Capa de bedrock
                namekSurfaceRule,       // Reglas de superficie para biomas de Namek
                sacredLandSurfaceRule,  // Reglas de superficie para el bioma Sacred Land
                deepslateRule           // Regla de deepslate en profundidad
        );
    }
}
