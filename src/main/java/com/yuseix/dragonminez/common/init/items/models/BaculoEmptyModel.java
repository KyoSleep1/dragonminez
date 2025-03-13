package com.yuseix.dragonminez.common.init.items.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.common.Reference;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;

public class BaculoEmptyModel extends HumanoidModel<AbstractClientPlayer> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "armas"), "baculo");
	private final ModelPart funda;
	private final ModelPart fundaint;
	private final ModelPart baculo;
	private final ModelPart baculoint;

	public BaculoEmptyModel(ModelPart root) {
        super(root);
        this.funda = root.getChild("funda");
		this.fundaint = this.funda.getChild("fundaint");
		this.baculo = root.getChild("baculo");
		this.baculoint = this.baculo.getChild("baculoint");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE,0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition funda = partdefinition.addOrReplaceChild("funda", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition fundaint = funda.addOrReplaceChild("fundaint", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.5F, 1.75F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r1 = fundaint.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(20, 4).addBox(-2.6726F, -0.0937F, -0.1F, 2.5F, 1.75F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(20, 0).addBox(-2.6726F, -8.0937F, -0.1F, 2.5F, 1.75F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.65F, 10.25F, 2.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r2 = fundaint.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(39, 21).addBox(-1.8226F, -6.7937F, -4.65F, 0.7F, 13.55F, 4.5F, new CubeDeformation(0.0F))
		.texOffs(0, 19).addBox(-2.4226F, -7.0937F, 0.0F, 2.0F, 14.75F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, 7.0F, 2.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition baculo = partdefinition.addOrReplaceChild("baculo", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition baculoint = baculo.addOrReplaceChild("baculoint", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.1184F, 2.241F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r3 = baculoint.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(19, 24).addBox(-1.75F, -11.2F, 0.25F, 1.5F, 17.75F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, 8.0F, 2.0F, 0.0F, 0.0F, 0.3927F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
		this.funda.copyFrom(this.body);
		this.baculo.copyFrom(this.funda);
	}

	public void renderToBuffer(PoseStack poseStack, AbstractClientPlayer pEntity, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		boolean estaAgachado = pEntity.isCrouching();

		poseStack.pushPose();

		if (estaAgachado) {
			// Si está agachado, se inclina el modelo hacia adelante
			poseStack.mulPose(Axis.XP.rotationDegrees(30.0F));  // Inclinación
			poseStack.translate(0.0F, 0.20F, -0.13F);  // Posición en Y para compensar la inclinación
		}

		// Renderizamos las partes del modelo
		funda.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		baculo.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

		poseStack.popPose();
	}
	public void renderBaculo(PoseStack poseStack, AbstractClientPlayer pEntity, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		boolean estaAgachado = pEntity.isCrouching();
		poseStack.pushPose();

		if (estaAgachado) {
			poseStack.mulPose(Axis.XP.rotationDegrees(30.0F));
			poseStack.translate(-0.0F, 0.20F, -0.13F);
		}

		funda.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

		poseStack.popPose();
	}
}