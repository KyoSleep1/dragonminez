package com.yuseix.dragonminez.common.init.entity.client.renderer.masters;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.client.model.masters.GokuMasterModel;
import com.yuseix.dragonminez.common.init.entity.custom.masters.MastersEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class GokuMasterRenderer extends LivingEntityRenderer<MastersEntity, PlayerModel<MastersEntity>> {
    public GokuMasterRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GokuMasterModel<>(pContext.bakeLayer(GokuMasterModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(MastersEntity mastersEntity) {
        return new ResourceLocation(Reference.MOD_ID,"textures/entity/masters/goku_master.png");
    }

    @Override
    protected void renderNameTag(MastersEntity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

    }
}
