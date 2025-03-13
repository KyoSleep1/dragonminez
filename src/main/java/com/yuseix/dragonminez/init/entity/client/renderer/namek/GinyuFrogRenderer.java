package com.yuseix.dragonminez.init.entity.client.renderer.namek;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.namek.GinyuFrogModel;
import com.yuseix.dragonminez.init.entity.client.model.namek.NamekFrogModel;
import com.yuseix.dragonminez.init.entity.custom.namek.GinyuFrogEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GinyuFrogRenderer extends GeoEntityRenderer<GinyuFrogEntity> {
    public GinyuFrogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GinyuFrogModel());
    }

    @Override
    public ResourceLocation getTextureLocation(GinyuFrogEntity animatable) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/ranas/ranadefault.png");
    }

    @Override
    public void render(GinyuFrogEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        poseStack.pushPose();

        poseStack.scale(1.5f, 1.5f, 1.5f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

        poseStack.popPose();
    }
}
