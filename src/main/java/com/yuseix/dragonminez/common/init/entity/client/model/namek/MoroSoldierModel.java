package com.yuseix.dragonminez.common.init.entity.client.model.namek;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.custom.namek.MoroSoldierEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MoroSoldierModel extends GeoModel<MoroSoldierEntity> {
    @Override
    public ResourceLocation getModelResource(MoroSoldierEntity friezaSoldierEntity) {
        return new ResourceLocation(Reference.MOD_ID, "geo/soldado1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MoroSoldierEntity friezaSoldierEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/namekusei/soldado1_moro.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MoroSoldierEntity friezaSoldierEntity) {
        return new ResourceLocation(Reference.MOD_ID, "animations/soldado1.animation.json");
    }

    @Override
    public void setCustomAnimations(MoroSoldierEntity animatable, long instanceId, AnimationState<MoroSoldierEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("Head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
