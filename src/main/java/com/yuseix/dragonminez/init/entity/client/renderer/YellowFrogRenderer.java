package com.yuseix.dragonminez.init.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.YellowFrogModel;
import com.yuseix.dragonminez.init.entity.custom.YellowFrogEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class YellowFrogRenderer extends GeoEntityRenderer<YellowFrogEntity> {
    public YellowFrogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new YellowFrogModel());
    }

    @Override
    public ResourceLocation getTextureLocation(YellowFrogEntity animatable) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/ranas/ranaamarilla.png");
    }

    @Override
    public void render(YellowFrogEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        poseStack.pushPose();

        poseStack.scale(1.5f, 1.5f, 1.5f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

        poseStack.popPose();
    }
}
