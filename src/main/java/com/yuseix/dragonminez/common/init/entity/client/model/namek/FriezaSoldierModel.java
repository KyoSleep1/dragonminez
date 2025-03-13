package com.yuseix.dragonminez.common.init.entity.client.model.namek;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.custom.namek.FriezaSoldierEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class FriezaSoldierModel extends GeoModel<FriezaSoldierEntity> {
    @Override
    public ResourceLocation getModelResource(FriezaSoldierEntity friezaSoldierEntity) {
        return new ResourceLocation(Reference.MOD_ID, "geo/soldado1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FriezaSoldierEntity friezaSoldierEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/namekusei/soldado1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FriezaSoldierEntity friezaSoldierEntity) {
        return new ResourceLocation(Reference.MOD_ID, "animations/soldado1.animation.json");
    }

    @Override
    public void setCustomAnimations(FriezaSoldierEntity animatable, long instanceId, AnimationState<FriezaSoldierEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("Head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
