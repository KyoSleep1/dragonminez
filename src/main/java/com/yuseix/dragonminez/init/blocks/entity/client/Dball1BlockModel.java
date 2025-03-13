package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.blocks.entity.Dball1BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class Dball1BlockModel extends GeoModel<Dball1BlockEntity> {
	@Override
	public ResourceLocation getModelResource(Dball1BlockEntity dball1BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/dball1.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Dball1BlockEntity dball1BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/block/custom/dballblock1.png");
	}

	@Override
	public ResourceLocation getAnimationResource(Dball1BlockEntity dball1BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "animations/dball1.animation.json");
	}
}
