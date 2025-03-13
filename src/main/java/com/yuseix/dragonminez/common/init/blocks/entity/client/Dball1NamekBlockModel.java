package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.blocks.entity.Dball1NamekBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class Dball1NamekBlockModel extends GeoModel<Dball1NamekBlockEntity> {
	@Override
	public ResourceLocation getModelResource(Dball1NamekBlockEntity dball1NamekBlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/dballnamek1.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Dball1NamekBlockEntity dball1NamekBlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/block/custom/dballnamekblock1.png");
	}

	@Override
	public ResourceLocation getAnimationResource(Dball1NamekBlockEntity dball1NamekBlockEntity) {
		return new ResourceLocation(Reference.MOD_ID, "animations/dball1.animation.json");
	}
}
