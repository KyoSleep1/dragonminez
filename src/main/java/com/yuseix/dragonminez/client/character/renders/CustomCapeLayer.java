package com.yuseix.dragonminez.client.character.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainItems;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;

public class CustomCapeLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

	private static ResourceLocation CUSTOM_CAPE_TEXTURE;

	public CustomCapeLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!player.isInvisible()) {
			if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == MainItems.PICCOLO_ARMOR_CHESTPLATE_CAPE.get()) {
				 CUSTOM_CAPE_TEXTURE = new ResourceLocation(DragonMineZ.MOD_ID, "textures/armor/cape/piccolo_cape.png");
			} else if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == MainItems.MAJIN_BUU_ARMOR_CHESTPLATE.get()) {
				CUSTOM_CAPE_TEXTURE = new ResourceLocation(DragonMineZ.MOD_ID, "textures/armor/saiyan_cape_layer1.png");
			} else if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == MainItems.GREAT_SAIYAMAN_ARMOR_CHESTPLATE.get()) {
				CUSTOM_CAPE_TEXTURE = new ResourceLocation(DragonMineZ.MOD_ID, "textures/armor/saiyan_cape_layer1.png");
			} else if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == MainItems.GAMMA1_ARMOR_CHESTPLATE.get()) {
				CUSTOM_CAPE_TEXTURE = new ResourceLocation(DragonMineZ.MOD_ID, "textures/armor/saiyan_cape_layer1.png");
			} else if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == MainItems.GAMMA2_ARMOR_CHESTPLATE.get()) {
				CUSTOM_CAPE_TEXTURE = new ResourceLocation(DragonMineZ.MOD_ID, "textures/armor/saiyan_cape_layer1.png");
			} else return;

			VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(CUSTOM_CAPE_TEXTURE));

			poseStack.pushPose();

			// 🔹 Ajustar la posición inicial
			poseStack.translate(0.0D, 0.0D, 0.125D);

			// 🔹 Obtener la rotación del cuerpo desde el modelo (en lugar de `player.yBodyRot`)
			float bodyRotation = this.getParentModel().body.yRot; // ⚠ Esta es la clave

			// 🔹 Simular efecto del viento o movimiento
			float movementSpeed = (float) player.getDeltaMovement().lengthSqr();
			float windEffect = (float) Math.sin(player.tickCount * 0.1) * 0.02F;

			// 🔹 Aplicar transformaciones
			poseStack.mulPose(Axis.YP.rotation(bodyRotation)); // Alinear con el torso del jugador
			poseStack.mulPose(Axis.XP.rotationDegrees(6.0F + windEffect));

			if (player.isCrouching()) {
				poseStack.translate(0.0D, 0.2D, 0.0D);
				poseStack.mulPose(Axis.XP.rotationDegrees(10.0F));
			}
			if (movementSpeed > 0.01D) {
				poseStack.mulPose(Axis.XP.rotationDegrees(15.0F));
			}

			// 🔹 Renderizar la capa con las nuevas transformaciones
			this.getParentModel().body.render(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);

			poseStack.popPose();
		}
	}
}