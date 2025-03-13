package com.yuseix.dragonminez.client.character.models.bioandroid;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class PerfectModel<T extends LivingEntity> extends PlayerModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "races"), "bio_perfect");
	private final ModelPart Head;
	private final ModelPart orejas;
	private final ModelPart cabeza2;
	private final ModelPart boca;
	private final ModelPart Body;
	private final ModelPart tail1bio;
	private final ModelPart alas;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;

	public PerfectModel(ModelPart root) {
        super(root, false);
        this.Head = root.getChild("head");
		this.orejas = this.Head.getChild("orejas");
		this.cabeza2 = this.Head.getChild("cabeza2");
		this.boca = this.Head.getChild("boca");
		this.Body = root.getChild("body");
		this.tail1bio = this.Body.getChild("tail1bio");
		this.alas = this.Body.getChild("alas");
		this.RightArm = root.getChild("right_arm");
		this.LeftArm = root.getChild("left_arm");
		this.LeftLeg = root.getChild("left_leg");
		this.RightLeg = root.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = PlayerModel.createMesh(CubeDeformation.NONE, false);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition orejas = Head.addOrReplaceChild("orejas", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = orejas.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(54, 4).addBox(2.25F, -5.75F, -3.25F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.25F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r2 = orejas.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(54, 4).addBox(-4.25F, -5.75F, -3.25F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.25F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cabeza2 = Head.addOrReplaceChild("cabeza2", CubeListBuilder.create().texOffs(24, 0).addBox(-0.5F, -6.15F, -4.801F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(33, 0).addBox(-4.0F, -8.9F, -4.499F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.8F, 0.25F));

		PartDefinition cube_r3 = cabeza2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(54, 29).addBox(-0.5F, -1.5F, -2.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5654F, -8.4026F, -2.6997F, 0.0F, 0.0F, 0.48F));

		PartDefinition cube_r4 = cabeza2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(54, 29).addBox(-0.5F, -1.5F, -2.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5678F, -8.3743F, -2.6997F, 0.0F, 0.0F, -0.48F));

		PartDefinition cube_r5 = cabeza2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(17, 32).mirror().addBox(1.55F, -10.85F, -4.8F, 2.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.35F, 0.25F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r6 = cabeza2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(39, 36).mirror().addBox(0.55F, -13.9F, -4.8F, 2.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.35F, 0.4F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r7 = cabeza2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(39, 36).addBox(-2.55F, -13.8F, -5.45F, 2.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.35F, 0.3F, 0.65F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r8 = cabeza2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(17, 32).addBox(-3.55F, -10.8F, -4.5F, 2.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.35F, 0.2F, -0.3F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r9 = cabeza2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(40, 6).mirror().addBox(-2.0F, -1.5F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.8674F, -6.1146F, -3.81F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r10 = cabeza2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(40, 6).addBox(-2.0F, -1.5F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8742F, -6.0958F, -3.81F, 0.0F, 0.0F, 0.2618F));

		PartDefinition boca = Head.addOrReplaceChild("boca", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.3F, 0.2F, 0.1309F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail1bio = Body.addOrReplaceChild("tail1bio", CubeListBuilder.create().texOffs(50, 50).addBox(-1.5F, -1.5F, -3.75F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(51, 58).addBox(-1.0F, -1.0F, -0.25F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.25F, 2.25F, -1.1781F, 0.0F, 0.0F));

		PartDefinition alas = Body.addOrReplaceChild("alas", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition cube_r11 = alas.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 44).mirror().addBox(0.0F, 0.5F, 2.0F, 6.0F, 19.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.8F, -0.9F, 0.0F, 0.1302F, -0.0076F, 0.1745F));

		PartDefinition cube_r12 = alas.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 44).addBox(0.0F, 0.5F, 2.0F, 6.0F, 19.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.1F, 0.0F, 0.1302F, 0.0076F, -0.1745F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}