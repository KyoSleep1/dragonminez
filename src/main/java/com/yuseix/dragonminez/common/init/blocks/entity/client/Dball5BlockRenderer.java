package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.init.blocks.entity.Dball5BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class Dball5BlockRenderer extends GeoBlockRenderer<Dball5BlockEntity> {
	public Dball5BlockRenderer(BlockEntityRendererProvider.Context context) {
		super(new Dball5BlockModel());
	}
}

