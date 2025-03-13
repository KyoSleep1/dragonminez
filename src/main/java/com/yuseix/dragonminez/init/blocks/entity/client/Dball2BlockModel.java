package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.blocks.entity.Dball2BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class Dball2BlockModel extends GeoModel<Dball2BlockEntity> {
	@Override
	public ResourceLocation getModelResource(Dball2BlockEntity dball2BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/dball1.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Dball2BlockEntity dball2BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/block/custom/dballblock2.png");
	}

	@Override
	public ResourceLocation getAnimationResource(Dball2BlockEntity dball2BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "animations/dball1.animation.json");
	}
}
