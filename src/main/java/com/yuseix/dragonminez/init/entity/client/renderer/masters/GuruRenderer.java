package com.yuseix.dragonminez.init.entity.client.renderer.masters;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.masters.EnmaModel;
import com.yuseix.dragonminez.init.entity.client.model.masters.GuruModel;
import com.yuseix.dragonminez.init.entity.custom.masters.EnmaEntity;
import com.yuseix.dragonminez.init.entity.custom.masters.GuruEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GuruRenderer extends GeoEntityRenderer<GuruEntity> {

	public GuruRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new GuruModel());
	}

	@Override
	public void render(GuruEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
		poseStack.pushPose();
		poseStack.scale(1.5f,1.5f,1.5f);
		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		poseStack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(GuruEntity animatable) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/masters/guru.png");
	}

}
