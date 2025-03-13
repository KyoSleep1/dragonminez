package com.yuseix.dragonminez.init.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.NaveSaiyanModel;
import com.yuseix.dragonminez.init.entity.custom.NaveSaiyanEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NaveSaiyanRenderer extends GeoEntityRenderer<NaveSaiyanEntity> {
    public NaveSaiyanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NaveSaiyanModel());
    }

    @Override
    public void render(NaveSaiyanEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.scale(1.1f,1.1f,1.1f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(NaveSaiyanEntity animatable) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/navesaiyan.png");
    }

    @Override
    public RenderType getRenderType(NaveSaiyanEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}
