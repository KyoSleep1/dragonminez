package com.yuseix.dragonminez.init.entity.client.model;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.custom.NaveSaiyanEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class NaveSaiyanModel extends GeoModel<NaveSaiyanEntity> {
    @Override
    public ResourceLocation getModelResource(NaveSaiyanEntity naveSaiyanEntity) {
        return new ResourceLocation(Reference.MOD_ID, "geo/navesaiyan.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NaveSaiyanEntity naveSaiyanEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/navesaiyan.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NaveSaiyanEntity naveSaiyanEntity) {
        return new ResourceLocation(Reference.MOD_ID, "animations/navesaiyan.animation.json");
    }
}
