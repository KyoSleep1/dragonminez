package com.yuseix.dragonminez.common.init.entity.client.model.masters;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.custom.masters.DendeEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class DendeModel extends GeoModel<DendeEntity> {
	@Override
	public ResourceLocation getModelResource(DendeEntity dendeEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/masters/dende.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DendeEntity dendeEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/masters/dende_master.png");
	}

	@Override
	public ResourceLocation getAnimationResource(DendeEntity dendeEntity) {
		return new ResourceLocation(Reference.MOD_ID, "animations/dende.animation.json");
	}

	@Override
	public void setCustomAnimations(DendeEntity animatable, long instanceId, AnimationState<DendeEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("Head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}
}
