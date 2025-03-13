package com.yuseix.dragonminez.common.init.blocks.entity.client;

import com.yuseix.dragonminez.common.init.blocks.entity.Dball6NamekBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball6NamekBlockRenderer extends GeoBlockRenderer<Dball6NamekBlockEntity> {
    public Dball6NamekBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball6NamekBlockModel());
    }
}
