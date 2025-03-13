package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.init.blocks.entity.Dball6BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class Dball6BlockRenderer extends GeoBlockRenderer<Dball6BlockEntity> {
	public Dball6BlockRenderer(BlockEntityRendererProvider.Context context) {
		super(new Dball6BlockModel());
	}
}

