package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.init.blocks.entity.Dball2NamekBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball2NamekBlockRenderer extends GeoBlockRenderer<Dball2NamekBlockEntity> {
    public Dball2NamekBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball2NamekBlockModel());
    }
}
