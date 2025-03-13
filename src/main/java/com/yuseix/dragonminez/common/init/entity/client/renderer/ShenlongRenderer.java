package com.yuseix.dragonminez.common.init.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.client.model.ShenlongModel;
import com.yuseix.dragonminez.common.init.entity.custom.ShenlongEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import org.joml.Matrix4f;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/*
 * This file uses GeckoLib, licensed under the MIT License.
 * Copyright © 2024 GeckoThePecko.
 */

public class ShenlongRenderer extends GeoEntityRenderer<ShenlongEntity> {

	private static final float HALF_SQRT_3 = (float) (Math.sqrt(3.0) / 2.0);

	public ShenlongRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new ShenlongModel());
	}

	@Override
	public ResourceLocation getTextureLocation(ShenlongEntity animatable) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/shenlong.png");
	}

	@Override
	public void render(ShenlongEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
		// Llama al renderizado básico de la entidad
		poseStack.pushPose();
		poseStack.scale(2.1f, 2.1f, 2.1f);
		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		poseStack.popPose();

		// Código para generar el destello siempre que el ShenlongEntity esté vivo
		float scale = 2.5F;
		RandomSource random = RandomSource.create(432L);
		VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lightning());
		poseStack.pushPose();

		poseStack.translate(0.0F, 1.0F, 0.0F);

		for (int i = 0; i < 60; ++i) {
			// Aplicar rotaciones aleatorias para que los rayos giren
			poseStack.mulPose(Axis.XP.rotationDegrees(random.nextFloat() * 360.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 360.0F));
			poseStack.mulPose(Axis.ZP.rotationDegrees(random.nextFloat() * 360.0F));

			Matrix4f matrix = poseStack.last().pose();
			int alpha = 255;

			float length = 1.5F * scale; // Longitud del rayo
			float width = 0.3F * scale; // Grosor del rayo

			vertex01(vertexConsumer, matrix, alpha);
			vertex2(vertexConsumer, matrix, length, width);
			vertex3(vertexConsumer, matrix, length, width);
			vertex01(vertexConsumer, matrix, alpha);
			vertex3(vertexConsumer, matrix, length, width);
			vertex4(vertexConsumer, matrix, length, width);
			vertex01(vertexConsumer, matrix, alpha);
			vertex4(vertexConsumer, matrix, length, width);
			vertex2(vertexConsumer, matrix, length, width);
		}

		poseStack.popPose();
	}


	private static void vertex01(VertexConsumer pConsumer, Matrix4f pMatrix, int pAlpha) {
		pConsumer.vertex(pMatrix, 0.0F, 0.0F, 0.0F).color(249, 241, 189, pAlpha).endVertex();
	}

	private static void vertex2(VertexConsumer pConsumer, Matrix4f pMatrix, float p_253704_, float p_253701_) {
		pConsumer.vertex(pMatrix, -HALF_SQRT_3 * p_253701_, p_253704_, -0.5F * p_253701_).color(249, 217, 13, 0).endVertex();
	}

	private static void vertex3(VertexConsumer pConsumer, Matrix4f pMatrix, float p_253729_, float p_254030_) {
		pConsumer.vertex(pMatrix, HALF_SQRT_3 * p_254030_, p_253729_, -0.5F * p_254030_).color(249, 217, 13, 0).endVertex();
	}

	private static void vertex4(VertexConsumer pConsumer, Matrix4f pMatrix, float p_253649_, float p_253694_) {
		pConsumer.vertex(pMatrix, 0.0F, p_253649_, 1.0F * p_253694_).color(249, 217, 13, 0).endVertex();
	}

}
