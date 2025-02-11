package com.yuseix.dragonminez.client.character.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainItems;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;

public class CustomCapeLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

	private static ResourceLocation CUSTOM_CAPE_TEXTURE;

	public CustomCapeLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!player.isInvisible()) {
			if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == MainItems.GOKU_ARMOR_CHESTPLATE.get()) {
				 CUSTOM_CAPE_TEXTURE = new ResourceLocation(DragonMineZ.MOD_ID, "textures/armor/cape/cape1.png");
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

			// 🔹 Obtener la velocidad y la posición de la capa
			double dX = Mth.lerp(partialTicks, player.xCloakO, player.xCloak) - Mth.lerp(partialTicks, player.xo, player.getX());
			double dY = Mth.lerp(partialTicks, player.yCloakO, player.yCloak) - Mth.lerp(partialTicks, player.yo, player.getY());
			double dZ = Mth.lerp(partialTicks, player.zCloakO, player.zCloak) - Mth.lerp(partialTicks, player.zo, player.getZ());

			float bodyRotY = Mth.lerp(partialTicks, player.yBodyRotO, player.yBodyRot);
			double sinYaw = Mth.sin(bodyRotY * ((float) Math.PI / 180F));
			double cosYaw = -Mth.cos(bodyRotY * ((float) Math.PI / 180F));

			float capeSwing = (float) dY * 10.0F;
			capeSwing = Mth.clamp(capeSwing, -6.0F, 32.0F);

			float forwardMotion = (float) (dX * sinYaw + dZ * cosYaw) * 100.0F;
			float sideMotion = (float) (dX * cosYaw - dZ * sinYaw) * 100.0F;

			if (forwardMotion < 0.0F) forwardMotion = 0.0F;
			float capeBend = Mth.lerp(partialTicks, player.oBob, player.bob);

			capeSwing += Mth.sin(Mth.lerp(partialTicks, player.walkDistO, player.walkDist) * 6.0F) * 32.0F * capeBend;
			if (player.isCrouching()) capeSwing += 25.0F;

			poseStack.pushPose();
			poseStack.translate(0.0D, 0.0D, 0.225D);

			// 🔹 Aplicar rotaciones y movimientos
			poseStack.mulPose(Axis.XP.rotationDegrees(6.0F + capeSwing / 2.0F + forwardMotion / 2.0F));
			poseStack.mulPose(Axis.ZP.rotationDegrees(sideMotion / 2.0F));

			// 🔹 Obtener el modelo y renderizar la capa
			this.getParentModel().renderCloak(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);

			poseStack.popPose();
		}
	}
}