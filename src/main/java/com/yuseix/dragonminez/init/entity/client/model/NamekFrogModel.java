package com.yuseix.dragonminez.init.entity.client.model;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.custom.NamekFrogEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class NamekFrogModel extends GeoModel<NamekFrogEntity> {
    @Override
    public ResourceLocation getModelResource(NamekFrogEntity dinoEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/namekfrog.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NamekFrogEntity dinoEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/ranas/ranadefault.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NamekFrogEntity dinoEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "animations/namekfrog.animation.json");
    }

    @Override
    public void setCustomAnimations(NamekFrogEntity animatable, long instanceId, AnimationState<NamekFrogEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
