package com.yuseix.dragonminez.common.init.entity.client.renderer.namek;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.client.model.namek.FriezaSoldierModel;
import com.yuseix.dragonminez.common.init.entity.custom.namek.FriezaSoldierEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FriezaSoldierRenderer extends GeoEntityRenderer<FriezaSoldierEntity> {
    public FriezaSoldierRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FriezaSoldierModel());
    }

    @Override
    public ResourceLocation getTextureLocation(FriezaSoldierEntity animatable) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/namekusei/soldado1.png");
    }

    @Override
    public RenderType getRenderType(FriezaSoldierEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(texture);
    }

    @Override
    protected void renderNameTag(FriezaSoldierEntity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

    }
}
