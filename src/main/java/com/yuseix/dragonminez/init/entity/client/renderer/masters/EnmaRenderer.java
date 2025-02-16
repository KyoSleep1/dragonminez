package com.yuseix.dragonminez.init.entity.client.renderer.masters;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.masters.EnmaModel;
import com.yuseix.dragonminez.init.entity.client.model.masters.KarinModel;
import com.yuseix.dragonminez.init.entity.custom.masters.EnmaEntity;
import com.yuseix.dragonminez.init.entity.custom.masters.KarinEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EnmaRenderer extends GeoEntityRenderer<EnmaEntity> {
	public EnmaRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new EnmaModel());
	}

	@Override
	public void render(EnmaEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
		poseStack.pushPose();
		poseStack.scale(2.0f,2.0f,2.0f);
		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		poseStack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(EnmaEntity animatable) {
		return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/masters/enma.png");
	}

}
