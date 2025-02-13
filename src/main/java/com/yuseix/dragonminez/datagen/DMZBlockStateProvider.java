package com.yuseix.dragonminez.datagen;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMZBlockStateProvider extends BlockStateProvider {
    public DMZBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DragonMineZ.MOD_ID, existingFileHelper);
    }

    @Override
    public void registerStatesAndModels() {
        //Bloques
        blockWithItem(MainBlocks.NAMEK_BLOCK);
        blockWithItem(MainBlocks.NAMEK_DIRT);
        blockWithItem(MainBlocks.NAMEK_STONE);
        blockWithItem(MainBlocks.NAMEK_COBBLESTONE);
        blockWithItem(MainBlocks.TIME_CHAMBER_BLOCK);
        axisBlock(((RotatedPillarBlock) MainBlocks.NAMEK_DEEPSLATE.get()), blockTexture(MainBlocks.NAMEK_DEEPSLATE.get()), blockTexture(MainBlocks.NAMEK_DEEPSLATE.get()));
        blockWithItem(MainBlocks.TIME_CHAMBER_PORTAL);
        blockWithItem(MainBlocks.OTHERWORLD_CLOUD);

        //Madera de Ajissa de Namek
        blockWithItem(MainBlocks.NAMEK_AJISSA_PLANKS);
        leavesBlock(MainBlocks.NAMEK_AJISSA_LEAVES);
        blockWithItem(MainBlocks.NAMEK_SACRED_PLANKS);
        leavesBlock(MainBlocks.NAMEK_SACRED_LEAVES);

        //Ores Nuevos
        blockWithItem(MainBlocks.GETE_BLOCK);
        blockWithItem(MainBlocks.NAMEK_KIKONO_ORE);
        blockWithItem(MainBlocks.KIKONO_BLOCK);

        //Ores (Default) de Namek
        blockWithItem(MainBlocks.NAMEK_DIAMOND_ORE);
        blockWithItem(MainBlocks.NAMEK_GOLD_ORE);
        blockWithItem(MainBlocks.NAMEK_IRON_ORE);
        blockWithItem(MainBlocks.NAMEK_LAPIS_ORE);
        blockWithItem(MainBlocks.NAMEK_REDSTONE_ORE);
        blockWithItem(MainBlocks.NAMEK_COAL_ORE);
        blockWithItem(MainBlocks.NAMEK_EMERALD_ORE);
        blockWithItem(MainBlocks.NAMEK_COPPER_ORE);

        //Ores (Default) de Deepslate de Namek
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_DIAMOND);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_GOLD);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_IRON);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_LAPIS);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_REDSTONE);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_COAL);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_EMERALD);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_COPPER);

        //BlockEntities
        blockWithItem(MainBlocks.GETE_FURNACE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(DragonMineZ.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                new ResourceLocation("minecraft:block/leaves"), "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
}
