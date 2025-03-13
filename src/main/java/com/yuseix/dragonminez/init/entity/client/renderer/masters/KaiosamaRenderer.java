package com.yuseix.dragonminez.init.entity.client.renderer.masters;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.masters.KaiosamaModel;
import com.yuseix.dragonminez.init.entity.client.model.masters.RoshiModel;
import com.yuseix.dragonminez.init.entity.custom.masters.KaiosamaEntity;
import com.yuseix.dragonminez.init.entity.custom.masters.RoshiEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KaiosamaRenderer extends GeoEntityRenderer<KaiosamaEntity> {
	public KaiosamaRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new KaiosamaModel());
	}

	@Override
	public void render(KaiosamaEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
		poseStack.pushPose();
		poseStack.scale(1.3f,1.2f,1.3f);
		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		poseStack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(KaiosamaEntity animatable) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/masters/kaiosama_master.png");
	}

	@Override
	public RenderType getRenderType(KaiosamaEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityCutout(texture);
	}
}
