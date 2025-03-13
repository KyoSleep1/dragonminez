package com.yuseix.dragonminez.worldgen;

import com.google.common.collect.ImmutableList;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.MainBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.heightproviders.VeryBiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {

    //ACA DEFINIMOS LAS KEYS MIENTRAS QUE EN ModBiomeModifiers elegimos donde aparecen.
    //esta es la parte de registro
    public static final ResourceKey<PlacedFeature> NAMEK_COAL_ORE_LOWER_KEY = registerKey("namek_coal_ore_lower_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_COAL_ORE_UPPER_KEY = registerKey("namek_coal_ore_upper_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_COPPER_ORE_KEY = registerKey("namek_copper_ore_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_COPPER_LARGE_KEY = registerKey("namek_copper_ore_large_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_IRON_ORE_SMALL_KEY = registerKey("namek_iron_ore_small_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_IRON_ORE_MIDDLE_KEY = registerKey("namek_iron_ore_middle_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_IRON_ORE_UPPER_KEY = registerKey("namek_iron_ore_upper_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_GOLD_ORE_KEY = registerKey("namek_gold_ore_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_LAPIS_ORE_KEY = registerKey("namek_lapis_ore_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_LAPIS_ORE_LARGE_KEY = registerKey("namek_lapis_ore_large_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_REDSTONE_LOWER_ORE_KEY = registerKey("namek_redstone_lower_ore_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_REDSTONE_ORE_KEY = registerKey("namek_redstone_ore_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_EMERALD_ORE_KEY = registerKey("namek_emerald_ore_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_DIAMOND_ORE_KEY = registerKey("namek_diamond_ore_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_DIAMOND_MIDDLE_ORE_KEY = registerKey("namek_diamond_middle_ore_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_DIAMOND_LARGE_ORE_KEY = registerKey("namek_diamond_large_ore_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_KIKONO_ORE_KEY = registerKey("namek_kikono_ore_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_KIKONO_ORE_LARGE_KEY = registerKey("namek_kikono_ore_large_placed");

    public static final ResourceKey<PlacedFeature> NAMEK_PATCH_GRASS_PLAIN = registerKey("namek_patch_grass_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_PATCH_SACRED_GRASS_PLAIN = registerKey("namek_patch_sacred_grass_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_PLAINS_FLOWERS = registerKey("namek_plains_flowers_placed");
    public static final ResourceKey<PlacedFeature> NAMEK_SACRED_FLOWERS = registerKey("namek_sacred_flowers_placed");

    public static final ResourceKey<PlacedFeature> NAMEK_LAKE_LAVA_UNDERGROUND = registerKey("namek_lake_lava_underground");
    public static final ResourceKey<PlacedFeature> NAMEK_LAKE_LAVA_SURFACE = registerKey("namek_lake_lava_surface");

    public static final ResourceKey<PlacedFeature> NAMEK_SPRING_LAVA = registerKey("namek_spring_lava");
    public static final ResourceKey<PlacedFeature> NAMEK_SPRING_WATER = registerKey("namek_spring_water");


    public static final ResourceKey<PlacedFeature> AJISSA_TREE_PLACED = registerKey("ajissa_tree_placed");
    public static final ResourceKey<PlacedFeature> SACRED_TREE_PLACED = registerKey("sacred_tree_placed");

    public static final ResourceKey<PlacedFeature> TREES_AJISSA_PLACED = registerKey("trees_ajissa_placed");
    public static final ResourceKey<PlacedFeature> TREES_SACRED_PLACED = registerKey("trees_sacred_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        //Ores
        //CARBON
        register(context, NAMEK_COAL_ORE_LOWER_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_COAL_ORE_BURIED_KEY),
                ModOrePlacement.commonOrePlacement(20,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(192))));
        register(context, NAMEK_COAL_ORE_UPPER_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_COAL_ORE_NORMAL_KEY),
                ModOrePlacement.commonOrePlacement(30,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(136))));
        //COPPER
        register(context, NAMEK_COPPER_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_COPPER_ORE_SMALL_KEY),
                ModOrePlacement.commonOrePlacement(16,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))));
        register(context, NAMEK_COPPER_LARGE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_COPPER_ORE_LARGE_KEY),
                ModOrePlacement.commonOrePlacement(16,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))));
        //HIERRO
        register(context, NAMEK_IRON_ORE_SMALL_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_IRON_ORE_SMALL_KEY),
                ModOrePlacement.commonOrePlacement(10,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(72))));
        register(context, NAMEK_IRON_ORE_MIDDLE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_IRON_ORE_LARGE_KEY),
                ModOrePlacement.commonOrePlacement(10,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
        register(context, NAMEK_IRON_ORE_UPPER_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_IRON_ORE_LARGE_KEY),
                ModOrePlacement.commonOrePlacement(90,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))));
        //ORO
        register(context, NAMEK_GOLD_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_GOLD_ORE_BURIED_KEY),
                ModOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))));
        //LAPIS LAZULI
        register(context, NAMEK_LAPIS_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_LAPIS_ORE_KEY),
                ModOrePlacement.commonOrePlacement(2,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))));
        register(context, NAMEK_LAPIS_ORE_LARGE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_LAPIS_ORE_LARGE_KEY),
                ModOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64))));
        //REDSTONE
        register(context, NAMEK_REDSTONE_LOWER_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_REDSTONE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))));
        register(context, NAMEK_REDSTONE_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_REDSTONE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(15))));
        //ESMERALDA
        register(context, NAMEK_EMERALD_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_EMERALD_ORE_KEY),
                ModOrePlacement.commonOrePlacement(100,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480))));
        //DIAMANTE
        register(context, NAMEK_DIAMOND_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_DIAMOND_ORE_KEY),
                ModOrePlacement.commonOrePlacement(7,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));
        register(context, NAMEK_DIAMOND_MIDDLE_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_DIAMOND_ORE_MIDDLE_KEY),
                ModOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));
        register(context, NAMEK_DIAMOND_LARGE_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_DIAMOND_ORE_LARGE_KEY),
                ModOrePlacement.commonOrePlacement(9,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));
        //KIKONO - NAMEK
        register(context, NAMEK_KIKONO_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_KIKONO_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));
        register(context, NAMEK_KIKONO_ORE_LARGE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_KIKONO_ORE_LARGE_KEY),
                ModOrePlacement.commonOrePlacement(10,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));


        //PLAINS
        register(context, NAMEK_PATCH_GRASS_PLAIN, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_PATCH_GRASS_KEY),
                ImmutableList.<PlacementModifier>builder()
                        .add(NoiseThresholdCountPlacement.of(-0.8f, 5, 10)) // Equivalente a "minecraft:noise_threshold_count"
                        .add(InSquarePlacement.spread()) // Equivalente a "minecraft:in_square"
                        .add(HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG)) // Equivalente a "minecraft:heightmap"
                        .add(BiomeFilter.biome()) // Equivalente a "minecraft:biome"
                        .build());
        register(context, NAMEK_PLAINS_FLOWERS, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_FLOWERS_KEY),
                ImmutableList.<PlacementModifier>builder()
                        .add(NoiseThresholdCountPlacement.of(-0.8f, 15, 4)) // Equivalente a "minecraft:noise_threshold_count"
                        .add(RarityFilter.onAverageOnceEvery(12)) //Menor valor, menor rareza, más frecuencia
                        .add(InSquarePlacement.spread()) // Equivalente a "minecraft:in_square"
                        .add(HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG)) // Equivalente a "minecraft:heightmap"
                        .add(BiomeFilter.biome()) // Equivalente a "minecraft:biome"
                        .build());
        //SACRED
        register(context, NAMEK_PATCH_SACRED_GRASS_PLAIN, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_PATCH_SACRED_GRASS_KEY),
                ImmutableList.<PlacementModifier>builder()
                        .add(NoiseThresholdCountPlacement.of(-0.8f, 5, 10))
                        .add(InSquarePlacement.spread())
                        .add(HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG))
                        .add(BiomeFilter.biome())
                        .build());
        register(context, NAMEK_SACRED_FLOWERS, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_SACRED_FLOWERS_KEY),
                ImmutableList.<PlacementModifier>builder()
                        .add(NoiseThresholdCountPlacement.of(-0.8f, 15, 4)) // Equivalente a "minecraft:noise_threshold_count"
                        .add(RarityFilter.onAverageOnceEvery(12)) //Más alto el valor, mayor rareza, menor frecuencia
                        .add(InSquarePlacement.spread()) // Equivalente a "minecraft:in_square"
                        .add(HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG)) // Equivalente a "minecraft:heightmap"
                        .add(BiomeFilter.biome()) // Equivalente a "minecraft:biome"
                        .build());


        //ARBOLES
        //Ejemplo de arbol
        register(context, AJISSA_TREE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.AJISSA_TREE_KEY),
                List.of(
                       BlockPredicateFilter.forPredicate(
                                BlockPredicate.wouldSurvive(MainBlocks.NAMEK_AJISSA_SAPLING.get().defaultBlockState(), BlockPos.ZERO)  // Verifica si el sapling puede sobrevivir
                                )
                        )
                );
        register(context, SACRED_TREE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.SACRED_TREE_KEY),
                List.of(
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.wouldSurvive(MainBlocks.NAMEK_SACRED_SAPLING.get().defaultBlockState(), BlockPos.ZERO)  // Verifica si el sapling puede sobrevivir
                                )
                        )
                );
        register(context, TREES_AJISSA_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.TREES_AJISSA_KEY),
                List.of(
                        PlacementUtils.countExtra(1, 0.1f, 2),  // Controla la cantidad de árboles generados
                        InSquarePlacement.spread(),  // Para dispersar los árboles en un área cuadrada
                        SurfaceWaterDepthFilter.forMaxDepth(0),  // Limita la profundidad del agua a 0
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE),  // Usa WORLD_SURFACE para generar en la superficie
                        BiomeFilter.biome()  // Asegura que se genere en el bioma correcto
                )
        );
        register(context, TREES_SACRED_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.TREES_SACRED_KEY),
                List.of(
                        PlacementUtils.countExtra(1, 0.1f, 2),  // Controla la cantidad de árboles generados
                        InSquarePlacement.spread(),  // Para dispersar los árboles en un área cuadrada
                        SurfaceWaterDepthFilter.forMaxDepth(0),  // Limita la profundidad del agua a 0
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE),  // Usa WORLD_SURFACE para generar en la superficie
                        BiomeFilter.biome()  // Asegura que se genere en el bioma correcto
                )
        );

        //LAKES
        //PlacementUtils.register(context, NAMEK_LAKE_LAVA_UNDERGROUND, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_LAKE_LAVA), new PlacementModifier[]{RarityFilter.onAverageOnceEvery(9), InSquarePlacement.spread(), HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.top())), EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE), BlockPredicate.insideWorld(new BlockPos(0, -5, 0))), 32), SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5), BiomeFilter.biome()});
        //PlacementUtils.register(context, NAMEK_LAKE_LAVA_SURFACE, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_LAKE_LAVA), new PlacementModifier[]{RarityFilter.onAverageOnceEvery(200), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()});

        register(context, NAMEK_LAKE_LAVA_UNDERGROUND,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_LAKE_LAVA),
                List.of(
                        RarityFilter.onAverageOnceEvery(9),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.top())),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.allOf(
                                        BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE),
                                        BlockPredicate.insideWorld(new BlockPos(0, -5, 0))
                                ),
                                32
                        ),
                        SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5),
                        BiomeFilter.biome()
                )
        );

        register(context, NAMEK_LAKE_LAVA_SURFACE,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_LAKE_LAVA),
                List.of(
                        RarityFilter.onAverageOnceEvery(200),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )
        );

        //Springs (Que será esa wbd)
        //PlacementUtils.register(context, NAMEK_SPRING_LAVA, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_SPRING_LAVA), new PlacementModifier[]{CountPlacement.of(20), InSquarePlacement.spread(), HeightRangePlacement.of(VeryBiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.belowTop(8), 8)), BiomeFilter.biome()});
        //PlacementUtils.register(context, NAMEK_SPRING_WATER, configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_SPRING_WATER), new PlacementModifier[]{CountPlacement.of(25), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(192)), BiomeFilter.biome()});
        register(context, NAMEK_SPRING_LAVA,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_SPRING_LAVA),
                List.of(
                        CountPlacement.of(20),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(VeryBiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.belowTop(8), 8)),
                        BiomeFilter.biome()
                )
        );

        register(context, NAMEK_SPRING_WATER,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.NAMEK_SPRING_WATER),
                List.of(
                        CountPlacement.of(25),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(192)),
                        BiomeFilter.biome()
                )
        );

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Reference.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
