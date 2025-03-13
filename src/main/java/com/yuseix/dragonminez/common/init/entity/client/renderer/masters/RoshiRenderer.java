package com.yuseix.dragonminez.common.init.entity.client.renderer.masters;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.client.model.masters.RoshiModel;
import com.yuseix.dragonminez.common.init.entity.custom.masters.RoshiEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RoshiRenderer extends GeoEntityRenderer<RoshiEntity> {
	public RoshiRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new RoshiModel());
	}

	@Override
	public ResourceLocation getTextureLocation(RoshiEntity animatable) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/masters/roshi_master.png");
	}

	@Override
	public RenderType getRenderType(RoshiEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityCutout(texture);
	}
}
