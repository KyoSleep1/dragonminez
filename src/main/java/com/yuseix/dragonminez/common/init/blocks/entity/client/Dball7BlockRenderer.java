package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.init.blocks.entity.Dball7BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class Dball7BlockRenderer extends GeoBlockRenderer<Dball7BlockEntity> {
	public Dball7BlockRenderer(BlockEntityRendererProvider.Context context) {
		super(new Dball7BlockModel());
	}
}

