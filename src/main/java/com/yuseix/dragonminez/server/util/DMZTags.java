package com.yuseix.dragonminez.server.util;

import com.yuseix.dragonminez.common.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class DMZTags {
    public static class Blocks {
        public static final TagKey<Block> NAMEK_STONE_ORE_REPLACEABLES = tag("namek_stone_ore_replaceables");
        public static final TagKey<Block> NAMEK_DEEPSLATE_ORE_REPLACEABLES = tag("namek_deepslate_ore_replaceables");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Reference.MOD_ID, name));
        }
    }
    //No tocar, esto solo hace referencia, no genera nada pq da error xd
    public static class Biomes {
        public static final TagKey<Biome> IS_NAMEKWORLD = tag("is_namekworld");
        public static final TagKey<Biome> IS_SACREDLAND = tag("is_sacredland");

        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME, new ResourceLocation(Reference.MOD_ID, name));
        }


    }


}
