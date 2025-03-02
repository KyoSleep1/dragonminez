package com.yuseix.dragonminez.client.character.models.demoncold;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class DemonColdModel<T extends LivingEntity> extends PlayerModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DragonMineZ.MOD_ID, "races"), "cd_minim");
	private final ModelPart Head;
	private final ModelPart orejas;
	private final ModelPart cuernos;
	private final ModelPart Body;
	private final ModelPart tail1bio;
	private final ModelPart tail2bio;
	private final ModelPart tail3bio;
	private final ModelPart tail4bio;
	private final ModelPart tail5bio;
	private final ModelPart tail6bio;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;

	public DemonColdModel(ModelPart root) {
        super(root, false);
        this.Head = root.getChild("head");
		this.orejas = this.Head.getChild("orejas");
		this.cuernos = this.Head.getChild("cuernos");
		this.Body = root.getChild("body");
		this.tail1bio = this.Body.getChild("tail1bio");
		this.tail2bio = this.tail1bio.getChild("tail2bio");
		this.tail3bio = this.tail2bio.getChild("tail3bio");
		this.tail4bio = this.tail3bio.getChild("tail4bio");
		this.tail5bio = this.tail4bio.getChild("tail5bio");
		this.tail6bio = this.tail5bio.getChild("tail6bio");
		this.RightArm = root.getChild("right_arm");
		this.LeftArm = root.getChild("left_arm");
		this.RightLeg = root.getChild("right_leg");
		this.LeftLeg = root.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = PlayerModel.createMesh(CubeDeformation.NONE, false);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition orejas = Head.addOrReplaceChild("orejas", CubeListBuilder.create().texOffs(42, 0).addBox(3.75F, -5.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(42, 0).addBox(-4.75F, -5.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cuernos = Head.addOrReplaceChild("cuernos", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = cuernos.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(55, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(4.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r2 = cuernos.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(55, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -7.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r3 = cuernos.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(55, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-4.0F, -8.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r4 = cuernos.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(55, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -7.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition Body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail1bio = Body.addOrReplaceChild("tail1bio", CubeListBuilder.create().texOffs(1, 32).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.5F, 3.2F));

		PartDefinition tail2bio = tail1bio.addOrReplaceChild("tail2bio", CubeListBuilder.create().texOffs(1, 32).addBox(-1.5F, -1.5F, -0.3F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.8F));

		PartDefinition tail3bio = tail2bio.addOrReplaceChild("tail3bio", CubeListBuilder.create().texOffs(1, 32).addBox(-1.5F, -1.5F, -1.1F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition tail4bio = tail3bio.addOrReplaceChild("tail4bio", CubeListBuilder.create().texOffs(1, 32).addBox(-1.5F, -1.5F, -0.6F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition tail5bio = tail4bio.addOrReplaceChild("tail5bio", CubeListBuilder.create().texOffs(1, 32).addBox(-1.5F, -1.5F, -0.2F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition tail6bio = tail5bio.addOrReplaceChild("tail6bio", CubeListBuilder.create().texOffs(1, 32).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.75F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap ->{
			var formRelease = cap.getIntValue("formrelease");
			var isTransfOn = cap.getBoolean("transform");
			var groupForm = cap.getStringValue("groupform");
			var dmzform = cap.getStringValue("form");
			var race = cap.getIntValue("race");

			switch (race){
				case 4:
					if(dmzform.equals("base") && isTransfOn && formRelease > 1){
						float scaleFactor = 1.0F + 0.03F * (float) Math.sin(pAgeInTicks * 0.4F);
						body.xScale = scaleFactor;
						body.yScale = scaleFactor;
						body.zScale = scaleFactor;

						rightArm.xScale = scaleFactor;
						rightArm.yScale = scaleFactor;
						rightArm.zScale = scaleFactor;

						leftArm.xScale = scaleFactor;
						leftArm.yScale = scaleFactor;
						leftArm.zScale = scaleFactor;

						leftArm.zRot = -0.2f;
						rightArm.zRot = 0.2f;

					} else {
						body.xScale = 1.0f;
						body.yScale = 1.0f;
						body.zScale = 1.0f;
						rightArm.xScale = 1.0f;
						rightArm.yScale = 1.0f;
						rightArm.zScale = 1.0f;
						leftArm.xScale = 1.0f;
						leftArm.yScale = 1.0f;
						leftArm.zScale = 1.0f;
					}

					if(dmzform.equals("full_power")){
						rightArm.xScale = 1.3f;
						leftArm.xScale = 1.3f;
					} else {
						rightArm.xScale = 1.0f;
						leftArm.xScale = 1.0f;
					}

					break;
				default:
					break;
			}


		});

		this.tail1bio.yRot = (float) (Math.sin((pEntity.tickCount)*0.08f)*0.15F);
		this.tail1bio.xRot = (float) (Math.sin((pEntity.tickCount)*0.05f)*0.05F);

		this.tail2bio.yRot = (float) (Math.sin((pEntity.tickCount)*0.08f)*0.075F);
		this.tail2bio.xRot = (float) (Math.sin((pEntity.tickCount)*0.05f)*0.085F);

		this.tail3bio.yRot = (float) (Math.sin((pEntity.tickCount)*0.08f)*0.08F);
		this.tail3bio.xRot = (float) (Math.sin((pEntity.tickCount)*0.05f)*0.09F);

		this.tail4bio.yRot = (float) (Math.sin((pEntity.tickCount)*0.08f)*0.085F);
		this.tail4bio.xRot = (float) (Math.sin((pEntity.tickCount)*0.05f)*0.095F);

		this.tail5bio.yRot = (float) (Math.sin((pEntity.tickCount)*0.08f)*0.09F);
		this.tail5bio.xRot = (float) (Math.sin((pEntity.tickCount)*0.05f)*0.1F);

		this.tail6bio.yRot = (float) (Math.sin((pEntity.tickCount)*0.08f)*0.095F);
		this.tail6bio.xRot = (float) (Math.sin((pEntity.tickCount)*0.05f)*0.15F);
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