package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.blocks.entity.Dball5BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class Dball5BlockModel extends GeoModel<Dball5BlockEntity> {
	@Override
	public ResourceLocation getModelResource(Dball5BlockEntity dball5BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/dball1.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Dball5BlockEntity dball5BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/block/custom/dballblock5.png");
	}

	@Override
	public ResourceLocation getAnimationResource(Dball5BlockEntity dball5BlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "animations/dball1.animation.json");
	}
}
