package com.yuseix.dragonminez.client.character.models.bioandroid;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class BioAndroidModel<T extends LivingEntity> extends PlayerModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DragonMineZ.MOD_ID, "races"), "bio_imperfect");
	private final ModelPart Head;
	private final ModelPart orejas;
	private final ModelPart cabeza2;
	private final ModelPart boca;
	private final ModelPart Body;
	private final ModelPart alas;
	private final ModelPart tail1bio;
	private final ModelPart tail2bio;
	private final ModelPart tail3bio;
	private final ModelPart tail4bio;
	private final ModelPart tail5bio;
	private final ModelPart tail6bio;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;

	public BioAndroidModel(ModelPart root) {
		super(root, false);
		this.Head = root.getChild("head");
		this.orejas = this.Head.getChild("orejas");
		this.cabeza2 = this.Head.getChild("cabeza2");
		this.boca = this.Head.getChild("boca");
		this.Body = root.getChild("body");
		this.alas = this.Body.getChild("alas");
		this.tail1bio = this.Body.getChild("tail1bio");
		this.tail2bio = this.tail1bio.getChild("tail2bio");
		this.tail3bio = this.tail2bio.getChild("tail3bio");
		this.tail4bio = this.tail3bio.getChild("tail4bio");
		this.tail5bio = this.tail4bio.getChild("tail5bio");
		this.tail6bio = this.tail5bio.getChild("tail6bio");
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

		PartDefinition cube_r1 = orejas.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(38, 42).addBox(2.25F, -5.75F, -3.25F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.75F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r2 = orejas.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(38, 42).addBox(-4.25F, -5.75F, -3.25F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.75F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cabeza2 = Head.addOrReplaceChild("cabeza2", CubeListBuilder.create().texOffs(24, 0).addBox(-0.5F, -5.9F, -4.502F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(33, 0).addBox(-4.0F, -8.9F, -4.499F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(38, 6).addBox(-2.0F, -9.65F, -4.4998F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 0.25F));

		PartDefinition cube_r3 = cabeza2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(48, 6).addBox(-5.05F, -11.6F, -4.501F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r4 = cabeza2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(48, 6).addBox(3.05F, -12.1F, -4.501F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition cube_r5 = cabeza2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(54, 40).addBox(-0.15F, -9.85F, -4.4997F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r6 = cabeza2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(54, 40).addBox(-0.75F, -9.85F, -4.4997F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r7 = cabeza2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 55).addBox(1.55F, -10.85F, -4.5F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r8 = cabeza2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(54, 40).addBox(1.591F, -13.9F, -4.503F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition cube_r9 = cabeza2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(54, 40).addBox(-2.55F, -13.8F, -4.503F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r10 = cabeza2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(52, 32).addBox(-3.55F, -10.8F, -4.5F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r11 = cabeza2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(38, 37).addBox(2.0F, -7.03F, -4.501F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r12 = cabeza2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(38, 32).addBox(-6.0F, -7.0F, -4.503F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition boca = Head.addOrReplaceChild("boca", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -3.25F, -4.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.2F, 0.1309F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition alas = Body.addOrReplaceChild("alas", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition cube_r13 = alas.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(1, 34).addBox(0.0F, 0.5F, 2.0F, 6.0F, 19.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.8F, -0.9F, 0.0F, 0.1302F, -0.0076F, 0.1745F));

		PartDefinition cube_r14 = alas.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(1, 34).addBox(0.0F, 0.5F, 2.0F, 6.0F, 19.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.1F, 0.0F, 0.1302F, 0.0076F, -0.1745F));

		PartDefinition tail1bio = Body.addOrReplaceChild("tail1bio", CubeListBuilder.create().texOffs(20, 33).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.5F, 3.2F));

		PartDefinition tail2bio = tail1bio.addOrReplaceChild("tail2bio", CubeListBuilder.create().texOffs(20, 33).addBox(-1.5F, -1.5F, -0.3F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.8F));

		PartDefinition tail3bio = tail2bio.addOrReplaceChild("tail3bio", CubeListBuilder.create().texOffs(20, 33).addBox(-1.5F, -1.5F, -1.1F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition tail4bio = tail3bio.addOrReplaceChild("tail4bio", CubeListBuilder.create().texOffs(20, 33).addBox(-1.5F, -1.5F, -0.6F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition tail5bio = tail4bio.addOrReplaceChild("tail5bio", CubeListBuilder.create().texOffs(20, 33).addBox(-1.5F, -1.5F, -0.2F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition tail6bio = tail5bio.addOrReplaceChild("tail6bio", CubeListBuilder.create().texOffs(20, 40).addBox(-1.5F, -2.0F, -0.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(51, 50).addBox(-1.0F, -1.5F, 3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 3.75F));

//		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(-5.0F, 2.5F, 0.0F));
//
//		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(5.0F, 2.5F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition LeftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);

		this.tail1bio.yRot = (float) (Math.sin((pAgeInTicks)*0.08f)*0.20F);
		this.tail1bio.xRot = (float) (Math.sin((pAgeInTicks)*0.05f)*0.20F);

		this.tail2bio.yRot = (float) (Math.sin((pAgeInTicks)*0.08f)*0.20F);
		this.tail2bio.xRot = (float) (Math.sin((pAgeInTicks)*0.05f)*0.20F);

		this.tail3bio.yRot = (float) (Math.sin((pAgeInTicks)*0.08f)*0.20F);
		this.tail3bio.xRot = (float) (Math.sin((pAgeInTicks)*0.05f)*0.20F);

		this.tail4bio.yRot = (float) (Math.sin((pAgeInTicks)*0.08f)*0.20F);
		this.tail4bio.xRot = (float) (Math.sin((pAgeInTicks)*0.05f)*0.20F);

		this.tail5bio.yRot = (float) (Math.sin((pAgeInTicks)*0.08f)*0.20F);
		this.tail5bio.xRot = (float) (Math.sin((pAgeInTicks)*0.05f)*0.20F);

		this.tail6bio.yRot = (float) (Math.sin((pAgeInTicks)*0.08f)*0.20F);
		this.tail6bio.xRot = (float) (Math.sin((pAgeInTicks)*0.05f)*0.20F);

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