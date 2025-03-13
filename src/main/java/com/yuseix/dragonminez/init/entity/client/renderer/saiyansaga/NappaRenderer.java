package com.yuseix.dragonminez.init.entity.client.renderer.saiyansaga;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.masters.KarinModel;
import com.yuseix.dragonminez.init.entity.client.model.saiyansaga.NappaModel;
import com.yuseix.dragonminez.init.entity.custom.masters.KarinEntity;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.NappaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NappaRenderer extends GeoEntityRenderer<NappaEntity> {
	public NappaRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new NappaModel());
	}

	@Override
	public void render(NappaEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
		poseStack.pushPose();
		poseStack.scale(1.1f,1.2f,1.14f);
		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		poseStack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(NappaEntity animatable) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/sagas/saiyan/nappa_1.png");
	}

}
