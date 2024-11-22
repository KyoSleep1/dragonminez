package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.blocks.entity.Dball5NamekBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball5NamekBlockModel extends GeoModel<Dball5NamekBlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball5NamekBlockEntity dball5NamekBlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/dballnamek1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball5NamekBlockEntity dball5NamekBlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/block/custom/dballnamekblock5.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball5NamekBlockEntity dball5NamekBlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "animations/dball1.animation.json");
    }
}
