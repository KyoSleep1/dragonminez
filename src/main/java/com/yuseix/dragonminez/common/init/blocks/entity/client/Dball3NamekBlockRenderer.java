package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.init.blocks.entity.Dball3NamekBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball3NamekBlockRenderer extends GeoBlockRenderer<Dball3NamekBlockEntity> {
    public Dball3NamekBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball3NamekBlockModel());
    }
}
