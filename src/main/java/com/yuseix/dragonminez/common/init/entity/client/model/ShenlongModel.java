package com.yuseix.dragonminez.common.init.entity.client.model;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.custom.ShenlongEntity;
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

public class ShenlongModel extends GeoModel<ShenlongEntity> {
	@Override
	public ResourceLocation getModelResource(ShenlongEntity shenlongEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/shenlong.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ShenlongEntity shenlongEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/shenlong.png");
	}

	@Override
	public ResourceLocation getAnimationResource(ShenlongEntity shenlongEntity) {
		return new ResourceLocation(Reference.MOD_ID, "animations/shenlong.animation.json");
	}

	@Override
	public void setCustomAnimations(ShenlongEntity animatable, long instanceId, AnimationState<ShenlongEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}


}
