package com.yuseix.dragonminez.init.entity.client.renderer.namek;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.namek.PinkFrogModel;
import com.yuseix.dragonminez.init.entity.custom.namek.PinkFrogEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PinkFrogRenderer extends GeoEntityRenderer<PinkFrogEntity> {
    public PinkFrogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PinkFrogModel());
    }

    @Override
    public ResourceLocation getTextureLocation(PinkFrogEntity animatable) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/ranas/ranarosa.png");
    }

    @Override
    public void render(PinkFrogEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        poseStack.pushPose();

        poseStack.scale(1.5f, 1.5f, 1.5f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

        poseStack.popPose();
    }
}
