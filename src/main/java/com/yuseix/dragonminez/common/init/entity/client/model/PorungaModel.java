package com.yuseix.dragonminez.common.init.entity.client.model;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.custom.PorungaEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class PorungaModel extends GeoModel<PorungaEntity> {
    @Override
    public ResourceLocation getModelResource(PorungaEntity shenlongEntity) {
        return new ResourceLocation(Reference.MOD_ID, "geo/porunga.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PorungaEntity shenlongEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/porunga.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PorungaEntity shenlongEntity) {
        return new ResourceLocation(Reference.MOD_ID, "animations/porunga.animation.json");
    }

    @Override
    public void setCustomAnimations(PorungaEntity animatable, long instanceId, AnimationState<PorungaEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

    
}
