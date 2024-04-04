package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.init.blocks.entity.Dball4BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball4BlockRenderer extends GeoBlockRenderer<Dball4BlockEntity> {
    public Dball4BlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball4BlockModel());
    }
}

