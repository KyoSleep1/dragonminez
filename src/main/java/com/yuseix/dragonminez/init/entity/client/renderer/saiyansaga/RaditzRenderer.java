package com.yuseix.dragonminez.init.entity.client.renderer.saiyansaga;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.saiyansaga.RaditzModel;
import com.yuseix.dragonminez.init.entity.custom.SagaEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RaditzRenderer extends LivingEntityRenderer<SagaEntity, PlayerModel<SagaEntity>> {
    public RaditzRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RaditzModel<>(pContext.bakeLayer(RaditzModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(SagaEntity pEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID,"textures/entity/sagas/saiyan/raditz.png");
    }

    @Override
    protected @Nullable RenderType getRenderType(SagaEntity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityCutout(getTextureLocation(pLivingEntity));
    }

    @Override
    protected void renderNameTag(SagaEntity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

    }
}
