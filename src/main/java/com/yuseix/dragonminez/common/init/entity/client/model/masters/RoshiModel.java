package com.yuseix.dragonminez.common.init.entity.client.model.masters;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.custom.masters.RoshiEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class RoshiModel extends GeoModel<RoshiEntity> {
	@Override
	public ResourceLocation getModelResource(RoshiEntity karinEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/masters/roshi.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(RoshiEntity karinEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/masters/roshi_master.png");
	}

	@Override
	public ResourceLocation getAnimationResource(RoshiEntity wa) {
		return new ResourceLocation(Reference.MOD_ID, "animations/roshi.animation.json");
	}

	@Override
	public void setCustomAnimations(RoshiEntity animatable, long instanceId, AnimationState<RoshiEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("Head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}

}
