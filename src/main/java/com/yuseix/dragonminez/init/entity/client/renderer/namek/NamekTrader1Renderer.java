package com.yuseix.dragonminez.init.entity.client.renderer.namek;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.namek.NamekNPCModel;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekTraderEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekWarriorEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class NamekTrader1Renderer extends LivingEntityRenderer<NamekTraderEntity, PlayerModel<NamekTraderEntity>> {

    public NamekTrader1Renderer(EntityRendererProvider.Context pContext) {
        super(pContext, new NamekNPCModel<>(pContext.bakeLayer(NamekNPCModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(NamekTraderEntity namekWarriorEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID,"textures/entity/namekusei/namek_random.png");
    }

    @Override
    protected void renderNameTag(NamekTraderEntity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

    }
}
