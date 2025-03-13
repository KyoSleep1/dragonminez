package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.blocks.entity.Dball2NamekBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball2NamekBlockModel extends GeoModel<Dball2NamekBlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball2NamekBlockEntity dball2NamekBlockEntity) {
        return new ResourceLocation(Reference.MOD_ID, "geo/dballnamek1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball2NamekBlockEntity dball2NamekBlockEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/block/custom/dballnamekblock2.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball2NamekBlockEntity dball2NamekBlockEntity) {
        return new ResourceLocation(Reference.MOD_ID, "animations/dball1.animation.json");
    }
}
