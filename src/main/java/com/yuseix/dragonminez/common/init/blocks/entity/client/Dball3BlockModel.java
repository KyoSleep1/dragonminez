package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.blocks.entity.Dball3BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class Dball3BlockModel extends GeoModel<Dball3BlockEntity> {
	@Override
	public ResourceLocation getModelResource(Dball3BlockEntity dball3BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/dball1.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Dball3BlockEntity dball3BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/block/custom/dballblock3.png");
	}

	@Override
	public ResourceLocation getAnimationResource(Dball3BlockEntity dball3BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "animations/dball1.animation.json");
	}
}
