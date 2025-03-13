package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.init.blocks.entity.Dball4NamekBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball4NamekBlockRenderer extends GeoBlockRenderer<Dball4NamekBlockEntity> {
    public Dball4NamekBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball4NamekBlockModel());
    }
}
