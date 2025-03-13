package com.yuseix.dragonminez.common.init.entity.client.renderer.projectil;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.custom.projectil.KiSmallWaveProjectil;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class KiSmallWaveRenderer extends EntityRenderer<KiSmallWaveProjectil> {

    public KiSmallWaveRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);

    }

    @Override
    public void render(KiSmallWaveProjectil pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
    }

    @Override
    public ResourceLocation getTextureLocation(KiSmallWaveProjectil kiBlastProyectil) {
        return new ResourceLocation(Reference.MOD_ID,"textures/entity/ki.png");
    }

    @Override
    protected void renderNameTag(KiSmallWaveProjectil pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
    }
}
