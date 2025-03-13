package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.blocks.entity.Dball4BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class Dball4BlockModel extends GeoModel<Dball4BlockEntity> {
	@Override
	public ResourceLocation getModelResource(Dball4BlockEntity dball4BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/dball1.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Dball4BlockEntity dball4BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/block/custom/dballblock4.png");
	}

	@Override
	public ResourceLocation getAnimationResource(Dball4BlockEntity dball4BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "animations/dball1.animation.json");
	}
}
