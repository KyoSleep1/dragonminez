package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.init.blocks.entity.Dball7NamekBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball7NamekBlockRenderer extends GeoBlockRenderer<Dball7NamekBlockEntity> {
    public Dball7NamekBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball7NamekBlockModel());
    }
}
