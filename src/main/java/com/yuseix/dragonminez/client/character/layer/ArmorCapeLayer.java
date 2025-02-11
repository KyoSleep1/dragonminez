package com.yuseix.dragonminez.client.character.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.init.armor.DbzArmorItem;
import com.yuseix.dragonminez.init.armor.SaiyanArmorItem;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ArmorCapeLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

	private static ResourceLocation TEXTURA_CAPA;

	public ArmorCapeLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (player.isInvisible() && player.isSpectator()) return;

		Item pechera = player.getItemBySlot(EquipmentSlot.CHEST).getItem();

		if (pechera instanceof DbzArmorItem armorItem && armorItem.hasCape()) {
			TEXTURA_CAPA = new ResourceLocation(DragonMineZ.MOD_ID, "textures/armor/cape/" + armorItem.getItemId() + "_cape.png");
		} else if (pechera instanceof SaiyanArmorItem saiyArItem && saiyArItem.hasCape()) {
			TEXTURA_CAPA = new ResourceLocation(DragonMineZ.MOD_ID, "textures/armor/cape/" + saiyArItem.getItemId() + "_cape.png");
		} else if (TEXTURA_CAPA == null) {
			return;
		} else return;

		VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURA_CAPA));

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

		double yOffset = 0.035D;
		double zOffset = 0.24D - (forwardMotion * 0.00000015D);
		float extraRotation = 0.0F;

		capeSwing += Mth.sin(Mth.lerp(partialTicks, player.walkDistO, player.walkDist) * 6.0F) * 32.0F * capeBend;
		if (player.isCrouching()) {
			capeSwing += 25.0F;
			yOffset += 0.05D;
			zOffset -= 0.15D;
			extraRotation = 10.0F;
		}

		float clampedSideMotion = Mth.clamp(sideMotion / 2.0F, -10.0F, 10.0F);

		poseStack.pushPose();
		poseStack.translate(0.0D, yOffset, zOffset);

		poseStack.mulPose(Axis.XP.rotationDegrees(6.0F + capeSwing / 2.0F + forwardMotion / 2.0F + extraRotation));
		poseStack.mulPose(Axis.ZP.rotationDegrees(clampedSideMotion));

		this.getParentModel().renderCloak(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);

		poseStack.popPose();
	}
}