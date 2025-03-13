package com.yuseix.dragonminez.common.init.entity.client.model;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.custom.NubeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class NubeModel extends GeoModel<NubeEntity> {
	@Override
	public ResourceLocation getModelResource(NubeEntity nubeEntity) {
		return new ResourceLocation(Reference.MOD_ID, "geo/cloud.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(NubeEntity nubeEntity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/cloud.png");
	}

	@Override
	public ResourceLocation getAnimationResource(NubeEntity nubeEntity) {
		return null;
	}
}
