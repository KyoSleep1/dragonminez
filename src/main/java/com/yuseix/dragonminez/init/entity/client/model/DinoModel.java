package com.yuseix.dragonminez.init.entity.client.model;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.custom.DinoEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class DinoModel extends GeoModel<DinoEntity> {
    @Override
    public ResourceLocation getModelResource(DinoEntity dinoEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/dino.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DinoEntity dinoEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entities/dinos/dino1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DinoEntity dinoEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "animations/dino1.animation.json");
    }

    @Override
    public void setCustomAnimations(DinoEntity animatable, long instanceId, AnimationState<DinoEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
