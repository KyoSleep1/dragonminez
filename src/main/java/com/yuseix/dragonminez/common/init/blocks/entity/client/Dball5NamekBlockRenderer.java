package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.init.blocks.entity.Dball5NamekBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball5NamekBlockRenderer extends GeoBlockRenderer<Dball5NamekBlockEntity> {
    public Dball5NamekBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball5NamekBlockModel());
    }
}
