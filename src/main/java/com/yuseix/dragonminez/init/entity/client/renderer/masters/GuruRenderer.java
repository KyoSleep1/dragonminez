package com.yuseix.dragonminez.init.entity.client.renderer.masters;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
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
	public ResourceLocation getTextureLocation(GuruEntity animatable) {
		return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/masters/guru.png");
	}

}
