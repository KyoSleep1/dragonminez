package com.yuseix.dragonminez.common.init.entity.client.renderer.masters;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.client.model.masters.DendeModel;
import com.yuseix.dragonminez.common.init.entity.custom.masters.DendeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class DendeRenderer extends GeoEntityRenderer<DendeEntity> {
	public DendeRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new DendeModel());
	}

	@Override
	public ResourceLocation getTextureLocation(DendeEntity animatable) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/masters/dende_master.png");
	}
}
