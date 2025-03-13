package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.blocks.entity.Dball4NamekBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball4NamekBlockModel extends GeoModel<Dball4NamekBlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball4NamekBlockEntity dball4NamekBlockEntity) {
        return new ResourceLocation(Reference.MOD_ID, "geo/dballnamek1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball4NamekBlockEntity dball4NamekBlockEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/block/custom/dballnamekblock4.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball4NamekBlockEntity dball4NamekBlockEntity) {
        return new ResourceLocation(Reference.MOD_ID, "animations/dball1.animation.json");
    }
}
