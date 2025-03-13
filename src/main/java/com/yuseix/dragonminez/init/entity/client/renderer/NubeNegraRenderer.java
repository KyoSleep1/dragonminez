package com.yuseix.dragonminez.init.entity.client.renderer;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.NubeNegraModel;
import com.yuseix.dragonminez.init.entity.custom.NubeNegraEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class NubeNegraRenderer extends GeoEntityRenderer<NubeNegraEntity> {
	public NubeNegraRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new NubeNegraModel());
	}

	@Override
	public ResourceLocation getTextureLocation(NubeNegraEntity animatable) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/black_cloud.png");
	}
}
