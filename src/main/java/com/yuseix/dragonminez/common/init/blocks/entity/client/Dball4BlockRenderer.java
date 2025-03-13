package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.init.blocks.entity.Dball4BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class Dball4BlockRenderer extends GeoBlockRenderer<Dball4BlockEntity> {
	public Dball4BlockRenderer(BlockEntityRendererProvider.Context context) {
		super(new Dball4BlockModel());
	}
}

