package com.yuseix.dragonminez.init.entity.client.model.namek;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.custom.namek.GinyuFrogEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekFrogEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GinyuFrogModel extends GeoModel<GinyuFrogEntity> {
    @Override
    public ResourceLocation getModelResource(GinyuFrogEntity dinoEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/namekfrog.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GinyuFrogEntity dinoEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/ranas/ranadefault.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GinyuFrogEntity dinoEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "animations/namekfrog.animation.json");
    }

    @Override
    public void setCustomAnimations(GinyuFrogEntity animatable, long instanceId, AnimationState<GinyuFrogEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
