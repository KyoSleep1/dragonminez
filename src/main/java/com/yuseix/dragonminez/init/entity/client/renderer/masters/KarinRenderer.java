package com.yuseix.dragonminez.init.entity.client.renderer.masters;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.masters.KarinModel;
import com.yuseix.dragonminez.init.entity.custom.masters.KarinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class KarinRenderer extends GeoEntityRenderer<KarinEntity> {
	public KarinRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new KarinModel());
	}

	@Override
	public ResourceLocation getTextureLocation(KarinEntity animatable) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/masters/karin_master.png");
	}

}
