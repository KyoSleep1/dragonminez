package com.yuseix.dragonminez.init.entity.client.renderer.saiyansaga;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.saiyansaga.NappaModel;
import com.yuseix.dragonminez.init.entity.client.model.saiyansaga.OzaruVegetaModel;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.NappaEntity;
import com.yuseix.dragonminez.init.entity.custom.saiyansaga.OzaruVegetaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OzaruVegetaRenderer extends GeoEntityRenderer<OzaruVegetaEntity> {
	public OzaruVegetaRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new OzaruVegetaModel());
	}

	@Override
	public void render(OzaruVegetaEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
		poseStack.pushPose();
		poseStack.scale(5f,5f,5f);
		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		poseStack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(OzaruVegetaEntity animatable) {
		return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/sagas/saiyan/vegeta_ozaru.png");
	}

}
