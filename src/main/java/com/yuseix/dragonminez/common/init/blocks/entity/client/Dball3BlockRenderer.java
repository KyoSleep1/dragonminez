package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.init.blocks.entity.Dball3BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class Dball3BlockRenderer extends GeoBlockRenderer<Dball3BlockEntity> {
	public Dball3BlockRenderer(BlockEntityRendererProvider.Context context) {
		super(new Dball3BlockModel());
	}
}

