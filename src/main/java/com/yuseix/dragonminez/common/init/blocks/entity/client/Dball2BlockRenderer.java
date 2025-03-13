package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.init.blocks.entity.Dball2BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class Dball2BlockRenderer extends GeoBlockRenderer<Dball2BlockEntity> {
	public Dball2BlockRenderer(BlockEntityRendererProvider.Context context) {
		super(new Dball2BlockModel());
	}
}

