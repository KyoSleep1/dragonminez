package com.yuseix.dragonminez.worldgen;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.utils.DMZTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {

    //Lo que hace el biomeModifiers es poner directamente en los biomas los ores
    //Solo se debe utilizar para el mundo entero, en este caso con namek lo uso porque no hay mucha variedad con los ores.

    public static final ResourceKey<BiomeModifier> ADD_NAMEK_COAL_ORE = registerKey("add_namek_coal_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_COAL_UPPER_ORE = registerKey("add_namek_coal_upper_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_COPPER_ORE = registerKey("add_namek_copper_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_COPPER_LARGE_ORE = registerKey("add_namek_copper_large_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_IRON_ORE = registerKey("add_namek_iron_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_IRON_MIDDLE_ORE = registerKey("add_namek_iron_middle_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_IRON_LARGE_ORE = registerKey("add_namek_iron_large_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_GOLD_ORE = registerKey("add_namek_gold_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_LAPIS_ORE = registerKey("add_namek_lapis_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_LAPIS_LARGE_ORE = registerKey("add_namek_lapis_large_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_REDSTONE_ORE = registerKey("add_namek_redstone_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_REDSTONE_LOWER_ORE = registerKey("add_namek_redstone_lower_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_EMERALD_ORE = registerKey("add_namek_emerald_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_DIAMOND_ORE = registerKey("add_namek_diamond_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_DIAMOND_MIDDLE_ORE = registerKey("add_namek_diamond_middle_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_DIAMOND_LARGE_ORE = registerKey("add_namek_diamond_large_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_KIKONO_ORE = registerKey("add_namek_kikono_ore");
    public static final ResourceKey<BiomeModifier> ADD_NAMEK_KIKONO_LARGE_ORE = registerKey("add_namek_kikono_large_ore");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        //ACA ES PARA AGREGAR EL MINERAL, ES DECIR DONDE LO QUEREMOS
        //CARBON
        context.register(ADD_NAMEK_COAL_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD), //Importante poner que es de namek, para que se genere ahi
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_COAL_ORE_LOWER_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NAMEK_COAL_UPPER_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD), //Importante poner que es de namek, para que se genere ahi
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_COAL_ORE_UPPER_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        //COPPER
        context.register(ADD_NAMEK_COPPER_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_COPPER_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NAMEK_COPPER_LARGE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_COPPER_LARGE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        //HIERRO
        context.register(ADD_NAMEK_IRON_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_IRON_ORE_SMALL_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NAMEK_IRON_MIDDLE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_IRON_ORE_MIDDLE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NAMEK_IRON_LARGE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_IRON_ORE_UPPER_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        //ORO
        context.register(ADD_NAMEK_GOLD_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_GOLD_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        //LAPIS
        context.register(ADD_NAMEK_LAPIS_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_LAPIS_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NAMEK_LAPIS_LARGE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_LAPIS_ORE_LARGE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        //REDSTONE
        context.register(ADD_NAMEK_REDSTONE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_REDSTONE_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NAMEK_REDSTONE_LOWER_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_REDSTONE_LOWER_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        //ESMERALDA
        context.register(ADD_NAMEK_EMERALD_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_EMERALD_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        //DIAMANTE
        context.register(ADD_NAMEK_DIAMOND_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_DIAMOND_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NAMEK_DIAMOND_MIDDLE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_DIAMOND_MIDDLE_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NAMEK_DIAMOND_LARGE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_NAMEKWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_DIAMOND_LARGE_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        //KIKONO - NAMEK
        context.register(ADD_NAMEK_KIKONO_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_SACREDLAND), //Importante poner que es Sacred, para que se genere ahi
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_KIKONO_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NAMEK_KIKONO_LARGE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(DMZTags.Biomes.IS_SACREDLAND), //Importante poner que es Sacred, para que se genere ahi
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NAMEK_KIKONO_ORE_LARGE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));


    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(DragonMineZ.MOD_ID, name));
    }
}
