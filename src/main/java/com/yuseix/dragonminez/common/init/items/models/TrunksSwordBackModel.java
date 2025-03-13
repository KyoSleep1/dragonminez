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


public class TrunksSwordBackModel extends HumanoidModel<AbstractClientPlayer> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "armas"), "trunkssword");
	private final ModelPart funda;
	private final ModelPart wardespada;
	private final ModelPart GuardaInferior;
	private final ModelPart GuardaSuperiorEInferior;
	private final ModelPart mangoespada;
	private final ModelPart mangoesp;
	private final ModelPart Mango;
	private final ModelPart nosecomosellama;
	private final ModelPart down;

	public TrunksSwordBackModel(ModelPart root) {
        super(root);
        this.funda = root.getChild("funda");
		this.wardespada = this.funda.getChild("wardespada");
		this.GuardaInferior = this.wardespada.getChild("GuardaInferior");
		this.GuardaSuperiorEInferior = this.GuardaInferior.getChild("GuardaSuperiorEInferior");
		this.mangoespada = root.getChild("mangoespada");
		this.mangoesp = this.mangoespada.getChild("mangoesp");
		this.Mango = this.mangoesp.getChild("Mango");
		this.nosecomosellama = this.mangoesp.getChild("nosecomosellama");
		this.down = this.nosecomosellama.getChild("down");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE,0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition funda = partdefinition.addOrReplaceChild("funda", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition wardespada = funda.addOrReplaceChild("wardespada", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = wardespada.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(27, 5).addBox(-12.5F, -2.0F, 0.75F, 14.0F, 1.0F, 4.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -12.5F, -3.0F, 0.0F, 0.0F, 1.0036F));

		PartDefinition cube_r2 = wardespada.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(39, 36).addBox(-1.0F, -3.325F, -1.0F, 3.0F, 17.325F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.625F, -19.4F, 3.05F, 0.0F, 0.0F, -0.624F));

		PartDefinition GuardaInferior = wardespada.addOrReplaceChild("GuardaInferior", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r3 = GuardaInferior.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(30, 53).addBox(-1.05F, 11.475F, -1.05F, 1.1F, 2.525F, 1.1F, new CubeDeformation(0.0F))
		.texOffs(6, 54).addBox(0.95F, 11.475F, -1.05F, 1.1F, 2.525F, 1.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.625F, -19.4F, 3.05F, 0.0F, 0.0F, -0.624F));

		PartDefinition cube_r4 = GuardaInferior.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(32, 30).addBox(-1.132F, 7.81F, -1.049F, 2.19F, 2.19F, 1.098F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.825F, -9.8F, 3.05F, 0.0F, 0.0F, -1.4094F));

		PartDefinition GuardaSuperiorEInferior = GuardaInferior.addOrReplaceChild("GuardaSuperiorEInferior", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r5 = GuardaSuperiorEInferior.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(43, 26).addBox(-1.05F, -3.375F, -1.05F, 0.6F, 1.375F, 1.1F, new CubeDeformation(0.0F))
		.texOffs(43, 24).addBox(1.45F, -3.375F, -1.05F, 0.6F, 1.375F, 1.1F, new CubeDeformation(0.0F))
		.texOffs(43, 13).addBox(-0.45F, -3.375F, -1.05F, 1.9F, 1.375F, 0.5F, new CubeDeformation(0.0F))
		.texOffs(43, 22).addBox(-0.45F, -3.375F, -0.45F, 1.9F, 1.375F, 0.5F, new CubeDeformation(0.0F))
		.texOffs(31, 41).addBox(0.05F, -2.0F, -1.05F, 0.9F, 0.75F, 1.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.625F, -19.4F, 3.05F, 0.0F, 0.0F, -0.624F));

		PartDefinition cube_r6 = GuardaSuperiorEInferior.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(23, 61).addBox(-1.025F, 5.625F, -1.05F, 3.075F, 1.075F, 1.1F, new CubeDeformation(0.0F))
		.texOffs(47, 60).addBox(-1.025F, -3.375F, -1.05F, 3.075F, 1.075F, 1.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.775F, -16.8F, 3.05F, 0.0F, 0.0F, -0.624F));

		PartDefinition mangoespada = partdefinition.addOrReplaceChild("mangoespada", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition mangoesp = mangoespada.addOrReplaceChild("mangoesp", CubeListBuilder.create(), PartPose.offsetAndRotation(-10.0422F, -5.1054F, 2.4896F, 0.0F, 0.0F, 2.4435F));

		PartDefinition cube_r7 = mangoesp.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(15, 55).addBox(-1.2F, -0.25F, -0.95F, 2.4F, 0.5F, 1.9F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0771F, -0.8683F, 0.0104F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r8 = mangoesp.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(11, 60).addBox(-1.35F, -0.25F, -1.1F, 2.6F, 0.5F, 2.15F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0771F, -0.3683F, 0.0104F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r9 = mangoesp.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(15, 52).addBox(-1.275F, -0.525F, -1.025F, 2.425F, 0.575F, 2.025F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0521F, 0.3067F, 0.0104F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r10 = mangoesp.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(11, 57).addBox(-1.425F, -0.525F, -1.075F, 2.625F, 0.575F, 2.125F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0521F, 0.8817F, 0.0104F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r11 = mangoesp.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(55, 1).addBox(-0.85F, -0.4F, -0.9F, 1.7F, 1.35F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0234F, 0.5958F, -0.0896F, 0.7838F, 0.0323F, 0.1049F));

		PartDefinition cube_r12 = mangoesp.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(57, 15).addBox(-0.9F, -1.9F, -0.9F, 1.7F, 1.9F, 1.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7729F, 1.4317F, 0.0104F, 0.0F, 0.0F, 0.9599F));

		PartDefinition Mango = mangoesp.addOrReplaceChild("Mango", CubeListBuilder.create(), PartPose.offset(1.0771F, -1.1183F, 0.0104F));

		PartDefinition cube_r13 = Mango.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(21, 46).addBox(-1.0F, -0.25F, -0.85F, 2.1F, 0.5F, 1.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -4.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r14 = Mango.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(21, 43).addBox(-1.1F, -0.25F, -0.95F, 2.4F, 0.5F, 1.9F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -4.5F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r15 = Mango.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(13, 42).addBox(-1.2F, -0.25F, -0.95F, 2.4F, 0.5F, 1.9F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -3.5F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r16 = Mango.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(1, 42).addBox(-0.65F, -12.25F, -0.15F, 1.8F, 7.5F, 1.325F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, 4.725F, -0.5F, 0.0F, 0.0F, 0.0873F));

		PartDefinition nosecomosellama = mangoesp.addOrReplaceChild("nosecomosellama", CubeListBuilder.create(), PartPose.offset(1.0771F, -10.8933F, 0.0104F));

		PartDefinition cube_r17 = nosecomosellama.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(57, 38).addBox(-1.25F, 0.5F, -0.45F, 2.5F, 0.75F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r18 = nosecomosellama.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(53, 47).addBox(-1.75F, -0.25F, -0.75F, 3.25F, 0.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.025F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition down = nosecomosellama.addOrReplaceChild("down", CubeListBuilder.create().texOffs(8, 0).addBox(-4.55F, 1.025F, -1.0F, 7.55F, 1.275F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 12).addBox(-2.4F, 0.825F, -2.55F, 3.6F, 1.275F, 1.75F, new CubeDeformation(0.0F))
		.texOffs(14, 3).addBox(-2.5F, 1.025F, 0.85F, 3.65F, 1.275F, 1.65F, new CubeDeformation(0.0F))
		.texOffs(13, 30).addBox(-2.5F, 2.025F, -0.75F, 3.25F, 0.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r19 = down.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(26, 17).addBox(5.2F, -2.0F, -1.0F, 0.6F, 1.275F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.675F, -3.775F, 0.0F, 0.0F, 0.0F, 2.042F));

		PartDefinition cube_r20 = down.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(26, 20).addBox(5.2F, -2.0F, -1.0F, 0.6F, 1.275F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8F, -2.175F, 0.0F, 0.0F, 0.0F, 0.9948F));

		PartDefinition cube_r21 = down.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(14, 6).addBox(1.7F, -1.998F, -1.825F, 1.3F, 1.274F, 2.825F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.675F, 3.025F, -0.675F, 0.0F, -2.3562F, 0.0F));

		PartDefinition cube_r22 = down.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(8, 19).addBox(1.55F, -1.998F, -2.025F, 1.05F, 1.274F, 2.825F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.175F, 3.025F, 0.175F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r23 = down.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 18).addBox(1.55F, -2.2003F, -1.625F, 1.05F, 1.274F, 2.825F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 3.025F, 0.4F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r24 = down.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(8, 15).addBox(1.9515F, -1.9257F, -1.7265F, 1.3F, 1.274F, 2.825F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.125F, 2.775F, -2.825F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.funda.copyFrom(this.body);
		this.mangoespada.copyFrom(this.funda);
		this.mangoesp.x = -10.0f;
		this.mangoesp.y = -7.0f;

		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);

	}

	public void renderCompleto(PoseStack poseStack, AbstractClientPlayer pEntity, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		boolean estaAgachado = pEntity.isCrouching();

		poseStack.pushPose();

		if (estaAgachado) {
			// Si está agachado, se inclina el modelo hacia adelante
			poseStack.mulPose(Axis.XP.rotationDegrees(30.0F));  // Inclinación
			poseStack.translate(0.0F, 0.20F, -0.13F);  // Posición en Y para compensar la inclinación
		}

		// Renderizamos las partes del modelo
		funda.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		mangoespada.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

		poseStack.popPose();
	}

	public void renderWardEspada(PoseStack poseStack, AbstractClientPlayer pEntity, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
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