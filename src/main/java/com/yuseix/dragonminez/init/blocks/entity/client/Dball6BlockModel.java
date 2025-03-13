package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.blocks.entity.Dball6BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class Dball6BlockModel extends GeoModel<Dball6BlockEntity> {
	@Override
	public ResourceLocation getModelResource(Dball6BlockEntity dball6BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/dball1.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Dball6BlockEntity dball6BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/block/custom/dballblock6.png");
	}

	@Override
	public ResourceLocation getAnimationResource(Dball6BlockEntity dball6BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "animations/dball1.animation.json");
	}
}
