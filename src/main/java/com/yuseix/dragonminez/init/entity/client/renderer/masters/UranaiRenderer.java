package com.yuseix.dragonminez.init.entity.client.renderer.masters;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.masters.EnmaModel;
import com.yuseix.dragonminez.init.entity.client.model.masters.UranaiModel;
import com.yuseix.dragonminez.init.entity.custom.SagaEntity;
import com.yuseix.dragonminez.init.entity.custom.masters.EnmaEntity;
import com.yuseix.dragonminez.init.entity.custom.masters.UranaiEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class UranaiRenderer extends GeoEntityRenderer<UranaiEntity> {
	public UranaiRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new UranaiModel());
	}

	@Override
	public ResourceLocation getTextureLocation(UranaiEntity animatable) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/masters/uranai.png");
	}

	@Override
	public RenderType getRenderType(UranaiEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityCutout(getTextureLocation(animatable));
	}
}
