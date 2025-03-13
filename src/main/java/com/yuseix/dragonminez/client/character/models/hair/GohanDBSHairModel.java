package com.yuseix.dragonminez.client.character.models.hair;

import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;

public class GohanDBSHairModel extends HumanoidModel<AbstractClientPlayer> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "hairs"), "gohandbs");
	private final ModelPart Gohandbshair;
	private final ModelPart hair1;
	private final ModelPart hair2;
	private final ModelPart hair3;
	private final ModelPart hair4;
	private final ModelPart hair5;
	private final ModelPart hair6;
	private final ModelPart hair7;
	private final ModelPart hair8;
	private final ModelPart hair9;
	private final ModelPart hair10;
	private final ModelPart hair11;
	private final ModelPart hair12;
	private final ModelPart hair13;
	private final ModelPart hair14;
	private final ModelPart hair15;
	private final ModelPart hair16;
	private final ModelPart hair17;
	private final ModelPart hair18;
	private final ModelPart hair19;
	private final ModelPart hair20;
	private final ModelPart hair21;
	private final ModelPart hair22;
	private final ModelPart hair23;
	private final ModelPart hair24;
	private final ModelPart hair25;
	private final ModelPart hair26;
	private final ModelPart hair27;
	private final ModelPart hair28;
	private final ModelPart hair29;

	public GohanDBSHairModel(ModelPart root) {
		super(root);
		this.Gohandbshair = root.getChild("Gohandbshair");
		this.hair1 = Gohandbshair.getChild("hair1");
		this.hair2 = Gohandbshair.getChild("hair2");
		this.hair3 = Gohandbshair.getChild("hair3");
		this.hair4 = Gohandbshair.getChild("hair4");
		this.hair5 = Gohandbshair.getChild("hair5");
		this.hair6 = Gohandbshair.getChild("hair6");
		this.hair7 = Gohandbshair.getChild("hair7");
		this.hair8 = Gohandbshair.getChild("hair8");
		this.hair9 = Gohandbshair.getChild("hair9");
		this.hair10 = Gohandbshair.getChild("hair10");
		this.hair11 = Gohandbshair.getChild("hair11");
		this.hair12 = Gohandbshair.getChild("hair12");
		this.hair13 = Gohandbshair.getChild("hair13");
		this.hair14 = Gohandbshair.getChild("hair14");
		this.hair15 = Gohandbshair.getChild("hair15");
		this.hair16 = Gohandbshair.getChild("hair16");
		this.hair17 = Gohandbshair.getChild("hair17");
		this.hair18 = Gohandbshair.getChild("hair18");
		this.hair19 = Gohandbshair.getChild("hair19");
		this.hair20 = Gohandbshair.getChild("hair20");
		this.hair21 = Gohandbshair.getChild("hair21");
		this.hair22 = Gohandbshair.getChild("hair22");
		this.hair23 = Gohandbshair.getChild("hair23");
		this.hair24 = Gohandbshair.getChild("hair24");
		this.hair25 = Gohandbshair.getChild("hair25");
		this.hair26 = Gohandbshair.getChild("hair26");
		this.hair27 = Gohandbshair.getChild("hair27");
		this.hair28 = Gohandbshair.getChild("hair28");
		this.hair29 = Gohandbshair.getChild("hair29");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE,0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Gohandbshair = partdefinition.addOrReplaceChild("Gohandbshair", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hair1 = Gohandbshair.addOrReplaceChild("hair1", CubeListBuilder.create(), PartPose.offset(-4.5F, -2.5F, 0.0F));

		PartDefinition cube_r1 = hair1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(28, 47).addBox(-3.0F, -3.0F, -1.5F, 4.0F, 3.0F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9163F));

		PartDefinition hair2 = Gohandbshair.addOrReplaceChild("hair2", CubeListBuilder.create(), PartPose.offset(-4.5F, -0.75F, 0.0F));

		PartDefinition cube_r2 = hair2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(48, 54).addBox(-2.0F, -3.0F, -0.9F, 3.0F, 3.0F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0036F));

		PartDefinition hair3 = Gohandbshair.addOrReplaceChild("hair3", CubeListBuilder.create(), PartPose.offset(-6.0F, -5.5F, 0.0F));

		PartDefinition cube_r3 = hair3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 55).addBox(-0.5F, -2.0F, -1.1F, 1.5F, 2.3F, 3.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2F, -1.85F, 0.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition cube_r4 = hair3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(14, 45).addBox(-1.8F, -3.0F, -1.4F, 2.8F, 3.0F, 4.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, -0.1F, 0.0F, 0.0F, 0.0F, 0.829F));

		PartDefinition hair4 = Gohandbshair.addOrReplaceChild("hair4", CubeListBuilder.create(), PartPose.offset(-1.0F, -7.0F, -3.5F));

		PartDefinition cube_r5 = hair4.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(32, 22).addBox(-1.3F, -2.0F, -0.4F, 1.9F, 1.7F, 1.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8F, 2.2F, -1.5F, 1.1781F, 0.48F, 0.0F));

		PartDefinition cube_r6 = hair4.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(48, 35).addBox(-1.3F, -2.0F, -1.0F, 2.2F, 2.0F, 2.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 1.1F, -1.05F, 0.7854F, 0.48F, 0.0F));

		PartDefinition cube_r7 = hair4.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(22, 10).addBox(-1.5F, -2.4F, -1.0F, 2.5F, 2.4F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, 0.6F, -0.2F, 0.3491F, 0.48F, 0.0F));

		PartDefinition hair5 = Gohandbshair.addOrReplaceChild("hair5", CubeListBuilder.create(), PartPose.offset(-4.3F, -7.75F, -3.1F));

		PartDefinition cube_r8 = hair5.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(48, 29).addBox(-2.0F, -3.0F, -1.5F, 3.0F, 3.0F, 3.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8F, 0.3F, -0.2F, -0.2182F, 0.0F, -0.2182F));

		PartDefinition hair6 = Gohandbshair.addOrReplaceChild("hair6", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.45F, -10.15F, -2.5F, 0.124F, 0.1231F, 1.7966F));

		PartDefinition cube_r9 = hair6.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(38, 54).addBox(-1.5F, -4.5F, -1.3F, 2.1F, 4.5F, 3.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.1F, 0.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition hair7 = Gohandbshair.addOrReplaceChild("hair7", CubeListBuilder.create(), PartPose.offset(1.4F, -8.25F, -3.1F));

		PartDefinition cube_r10 = hair7.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(44, 47).addBox(-2.0F, -3.0F, -1.35F, 3.0F, 3.0F, 4.15F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, 1.4F, -0.5F, -0.1292F, 0.159F, 0.5033F));

		PartDefinition hair8 = Gohandbshair.addOrReplaceChild("hair8", CubeListBuilder.create().texOffs(26, 54).addBox(-2.0F, -3.0F, -0.9F, 3.0F, 4.0F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4F, -9.0F, -2.8F, 0.0456F, 0.1685F, 1.2692F));

		PartDefinition hair9 = Gohandbshair.addOrReplaceChild("hair9", CubeListBuilder.create(), PartPose.offset(-0.45F, -8.25F, -3.0F));

		PartDefinition cube_r11 = hair9.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(46, 40).addBox(-2.0F, -3.0F, -1.4F, 3.0F, 3.0F, 4.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6F, 1.5F, -0.5F, -0.2559F, -0.056F, 0.1817F));

		PartDefinition hair10 = Gohandbshair.addOrReplaceChild("hair10", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, -3.0F, -0.9F, 3.0F, 3.0F, 3.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, -8.0F, -3.0F, -0.0497F, 0.3609F, 1.4596F));

		PartDefinition hair11 = Gohandbshair.addOrReplaceChild("hair11", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.3F, -3.75F, -0.5F, 0.0F, 0.0F, 0.9599F));

		PartDefinition cube_r12 = hair11.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(16, 31).addBox(-4.0F, -3.0F, -1.5F, 5.0F, 3.0F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition hair12 = Gohandbshair.addOrReplaceChild("hair12", CubeListBuilder.create(), PartPose.offset(-4.5F, -6.5F, -3.1F));

		PartDefinition cube_r13 = hair12.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(50, 14).addBox(-2.0F, -3.0F, -1.45F, 3.0F, 3.0F, 3.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, -0.1F, 0.0F, 0.2182F, 1.0472F));

		PartDefinition hair13 = Gohandbshair.addOrReplaceChild("hair13", CubeListBuilder.create().texOffs(46, 40).addBox(-1.7F, -3.4F, -1.35F, 3.1F, 3.0F, 4.15F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9F, -8.2F, -3.1F, 0.1504F, -0.0124F, 2.1305F));

		PartDefinition hair14 = Gohandbshair.addOrReplaceChild("hair14", CubeListBuilder.create(), PartPose.offset(-4.25F, -6.75F, 0.0F));

		PartDefinition cube_r14 = hair14.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(14, 52).addBox(-0.3F, -4.4F, -1.2F, 1.7F, 2.7F, 4.15F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, -2.8F, 0.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition cube_r15 = hair14.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(34, 31).addBox(-1.4F, -4.7F, -1.6F, 2.4F, 3.8F, 4.85F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -1.9F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition cube_r16 = hair14.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 14).addBox(-2.8F, -4.5F, -1.8F, 3.8F, 4.75F, 5.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition hair15 = Gohandbshair.addOrReplaceChild("hair15", CubeListBuilder.create(), PartPose.offset(-4.6F, -0.55F, 2.25F));

		PartDefinition cube_r17 = hair15.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 30).addBox(-3.0F, -3.8F, -1.5F, 4.0F, 3.8F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition hair16 = Gohandbshair.addOrReplaceChild("hair16", CubeListBuilder.create(), PartPose.offset(-5.6F, -2.8F, 2.15F));

		PartDefinition cube_r18 = hair16.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(38, 0).addBox(-3.0F, -3.8F, -1.3F, 4.0F, 3.8F, 3.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition hair17 = Gohandbshair.addOrReplaceChild("hair17", CubeListBuilder.create(), PartPose.offset(1.5F, -1.85F, 0.0F));

		PartDefinition cube_r19 = hair17.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(48, 54).addBox(-2.0F, -3.0F, -0.9F, 3.0F, 3.0F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, -0.6F, 0.0F, 0.0F, 0.0F, 2.2253F));

		PartDefinition hair18 = Gohandbshair.addOrReplaceChild("hair18", CubeListBuilder.create(), PartPose.offset(2.1F, -4.65F, -0.6F));

		PartDefinition cube_r20 = hair18.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(48, 8).addBox(-2.0F, -3.25F, -0.9F, 4.0F, 3.25F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0071F));

		PartDefinition hair19 = Gohandbshair.addOrReplaceChild("hair19", CubeListBuilder.create(), PartPose.offset(2.55F, -1.6F, 2.5F));

		PartDefinition cube_r21 = hair19.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(30, 40).addBox(-3.0F, -3.0F, -1.65F, 4.0F, 3.0F, 3.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -0.7F, 0.0F, 0.0F, 0.0F, 2.2253F));

		PartDefinition hair20 = Gohandbshair.addOrReplaceChild("hair20", CubeListBuilder.create(), PartPose.offset(2.55F, -4.35F, 2.5F));

		PartDefinition cube_r22 = hair20.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(40, 22).addBox(-3.0F, -3.0F, -1.55F, 4.0F, 3.0F, 3.85F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.1F, 0.0F, 0.0F, 0.0F, 2.0944F));

		PartDefinition hair21 = Gohandbshair.addOrReplaceChild("hair21", CubeListBuilder.create(), PartPose.offsetAndRotation(1.45F, -5.25F, -0.75F, 0.0F, 0.0F, 2.138F));

		PartDefinition cube_r23 = hair21.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(16, 31).addBox(-4.0F, -3.0F, -1.5F, 5.0F, 3.0F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition hair22 = Gohandbshair.addOrReplaceChild("hair22", CubeListBuilder.create(), PartPose.offset(3.25F, -7.4F, -0.4F));

		PartDefinition cube_r24 = hair22.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(54, 0).addBox(-2.1F, -3.25F, -0.8F, 2.6F, 2.5F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.65F, -1.45F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition cube_r25 = hair22.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(14, 38).addBox(-2.25F, -3.25F, -0.9F, 4.25F, 3.25F, 3.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.1817F));

		PartDefinition hair23 = Gohandbshair.addOrReplaceChild("hair23", CubeListBuilder.create(), PartPose.offset(-1.0F, -8.5F, 0.0F));

		PartDefinition cube_r26 = hair23.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 38).addBox(-2.8F, -5.1F, -1.35F, 2.3F, 4.65F, 4.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -3.95F, 0.0F, 0.0F, 0.0F, 2.3126F));

		PartDefinition cube_r27 = hair23.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(18, 14).addBox(-2.8F, -5.5F, -1.55F, 2.3F, 5.75F, 5.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.0F, 2.0508F));

		PartDefinition cube_r28 = hair23.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 0).addBox(-2.8F, -5.5F, -1.8F, 3.8F, 6.75F, 6.65F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8762F));

		PartDefinition hair24 = Gohandbshair.addOrReplaceChild("hair24", CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, -3.0F, -1.0F, 8.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.25F, -0.2618F, 0.0F, 0.0F));

		PartDefinition hair25 = Gohandbshair.addOrReplaceChild("hair25", CubeListBuilder.create().texOffs(32, 17).addBox(-3.5F, -3.0F, -1.0F, 7.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.75F, 3.5F, -0.3927F, 0.0F, 0.0F));

		PartDefinition hair26 = Gohandbshair.addOrReplaceChild("hair26", CubeListBuilder.create().texOffs(20, 25).addBox(-3.25F, -3.0F, -2.0F, 6.5F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5F, 3.5F, -0.3927F, 0.0F, 0.0F));

		PartDefinition hair27 = Gohandbshair.addOrReplaceChild("hair27", CubeListBuilder.create(), PartPose.offsetAndRotation(2.25F, -6.35F, 2.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r29 = hair27.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(32, 10).addBox(-3.0F, -3.25F, -1.55F, 4.0F, 3.25F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.5F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition hair28 = Gohandbshair.addOrReplaceChild("hair28", CubeListBuilder.create().texOffs(38, 0).addBox(-3.0F, -3.8F, -1.3F, 4.0F, 3.8F, 3.95F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -6.4F, 2.25F, 0.0F, 0.0F, 1.9199F));

		PartDefinition hair29 = Gohandbshair.addOrReplaceChild("hair29", CubeListBuilder.create().texOffs(22, 0).addBox(-1.4F, -4.6F, -1.6F, 2.4F, 3.7F, 5.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.55F, -6.65F, 1.2F, 0.0F, 0.0F, 0.829F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		//super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
		float random = (float) Math.random();


		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

			var auraOn = cap.getBoolean("aura");

			if(auraOn){
				var cargaVelocidad = 0.4f;
				this.hair14.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.03F);
				this.hair23.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*-0.03F);
				this.hair3.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*-0.02F);
				this.hair22.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.02F);
				this.hair15.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.02F);
				this.hair16.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.02F);
				this.hair19.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*-0.02F);
				this.hair20.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*-0.02F);


				this.hair17.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.06F);
				this.hair2.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.06F);
				this.hair1.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.06F);
				this.hair18.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.06F);

				this.hair4.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.06F);
			} else {
				var velocidad = 0.04f;

				this.hair14.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.03F);
				this.hair23.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.03F);
				this.hair3.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.02F);
				this.hair22.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.02F);
				this.hair15.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.02F);
				this.hair16.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.02F);
				this.hair19.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.02F);
				this.hair20.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.02F);


				this.hair17.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.06F);
				this.hair2.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.06F);
				this.hair1.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.06F);
				this.hair18.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.06F);

				this.hair4.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.06F);
			}

		});
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Gohandbshair.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}