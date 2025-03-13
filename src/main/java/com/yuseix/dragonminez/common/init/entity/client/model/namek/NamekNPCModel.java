package com.yuseix.dragonminez.common.init.entity.client.model.namek;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.common.Reference;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class NamekNPCModel<T extends LivingEntity> extends PlayerModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "nameknpc"), "base");
	private final ModelPart Head;
	private final ModelPart orejas1;
	private final ModelPart antenas;
	private final ModelPart Body;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;

	public NamekNPCModel(ModelPart root) {
        super(root, false);
        this.Head = root.getChild("head");
		this.orejas1 = this.Head.getChild("orejas1");
		this.antenas = this.Head.getChild("antenas");
		this.Body = root.getChild("body");
		this.RightArm = root.getChild("right_arm");
		this.LeftArm = root.getChild("left_arm");
		this.RightLeg = root.getChild("right_leg");
		this.LeftLeg = root.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = PlayerModel.createMesh(CubeDeformation.NONE, false);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition orejas1 = Head.addOrReplaceChild("orejas1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = orejas1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(1, 2).mirror().addBox(-2.0F, -5.0F, -1.0F, 3.0F, 5.0F, 0.25F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.25F, -2.25F, 0.25F, 0.0F, -0.4363F, 0.1745F));

		PartDefinition cube_r2 = orejas1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(1, 2).addBox(-2.0F, -5.0F, -1.0F, 3.0F, 5.0F, 0.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -2.5F, 0.25F, 0.0F, 0.4363F, -0.1745F));

		PartDefinition antenas = Head.addOrReplaceChild("antenas", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r3 = antenas.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(39, 37).addBox(0.0F, -1.0F, -0.25F, 0.75F, 0.75F, 1.25F, new CubeDeformation(0.0F))
		.texOffs(39, 37).addBox(-4.75F, -1.0F, -0.25F, 0.75F, 0.75F, 1.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -5.4F, -5.0F, 0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r4 = antenas.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(39, 37).addBox(0.0F, -1.0F, -0.25F, 0.75F, 0.75F, 1.25F, new CubeDeformation(0.0F))
		.texOffs(39, 37).addBox(-4.75F, -1.0F, -0.25F, 0.75F, 0.75F, 1.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -6.0F, -4.6F, 0.3491F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

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
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}