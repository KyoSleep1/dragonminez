package com.yuseix.dragonminez.init.entity.client.model.namek;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.custom.namek.YellowFrogEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class YellowFrogModel extends GeoModel<YellowFrogEntity> {
    @Override
    public ResourceLocation getModelResource(YellowFrogEntity dinoEntity) {
        return new ResourceLocation(Reference.MOD_ID, "geo/namekfrog.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(YellowFrogEntity dinoEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/ranas/ranaamarilla.png");
    }

    @Override
    public ResourceLocation getAnimationResource(YellowFrogEntity dinoEntity) {
        return new ResourceLocation(Reference.MOD_ID, "animations/namekfrog.animation.json");
    }

    @Override
    public void setCustomAnimations(YellowFrogEntity animatable, long instanceId, AnimationState<YellowFrogEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
