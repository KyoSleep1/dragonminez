package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.blocks.entity.Dball7NamekBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball7NamekBlockModel extends GeoModel<Dball7NamekBlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball7NamekBlockEntity dball7NamekBlockEntity) {
        return new ResourceLocation(Reference.MOD_ID, "geo/dballnamek1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball7NamekBlockEntity dball7NamekBlockEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/block/custom/dballnamekblock7.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball7NamekBlockEntity dball7NamekBlockEntity) {
        return new ResourceLocation(Reference.MOD_ID, "animations/dball1.animation.json");
    }
}
