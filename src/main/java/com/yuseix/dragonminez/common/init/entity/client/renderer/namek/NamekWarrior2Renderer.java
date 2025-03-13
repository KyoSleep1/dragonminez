package com.yuseix.dragonminez.common.init.entity.client.renderer.namek;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.client.model.namek.NamekNPCModel;
import com.yuseix.dragonminez.common.init.entity.custom.namek.NamekWarrior02Entity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class NamekWarrior2Renderer extends LivingEntityRenderer<NamekWarrior02Entity, PlayerModel<NamekWarrior02Entity>> {

    public NamekWarrior2Renderer(EntityRendererProvider.Context pContext) {
        super(pContext, new NamekNPCModel<>(pContext.bakeLayer(NamekNPCModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(NamekWarrior02Entity namekWarriorEntity) {
        return new ResourceLocation(Reference.MOD_ID,"textures/entity/namekusei/namek_random4.png");
    }

    @Override
    protected void renderNameTag(NamekWarrior02Entity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

    }
}
