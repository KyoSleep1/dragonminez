package com.yuseix.dragonminez.init.entity.client.renderer.namek;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.namek.NamekNPCModel;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekTrader02Entity;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekTraderEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class NamekTrader2Renderer extends LivingEntityRenderer<NamekTrader02Entity, PlayerModel<NamekTrader02Entity>> {

    public NamekTrader2Renderer(EntityRendererProvider.Context pContext) {
        super(pContext, new NamekNPCModel<>(pContext.bakeLayer(NamekNPCModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(NamekTrader02Entity namekWarriorEntity) {
        return new ResourceLocation(Reference.MOD_ID,"textures/entity/namekusei/namek_random2.png");
    }

    @Override
    protected void renderNameTag(NamekTrader02Entity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

    }
}
