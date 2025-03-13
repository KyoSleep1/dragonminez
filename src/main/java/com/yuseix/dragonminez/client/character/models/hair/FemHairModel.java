package com.yuseix.dragonminez.client.character.models.hair;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;

public class FemHairModel extends HumanoidModel<AbstractClientPlayer> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "hairs"), "femhair");

	private final ModelPart femalehair;
	private final ModelPart pelo1;
	private final ModelPart pelo2;
	private final ModelPart pelo3;
	private final ModelPart pelo4;
	private final ModelPart pelo5;
	private final ModelPart pelo6;
	private final ModelPart pelo7;
	private final ModelPart pelo8;
	private final ModelPart pelo9;
	private final ModelPart pelo10;
	private final ModelPart pelo11;
	private final ModelPart pelo12;
	private final ModelPart pelo13;
	private final ModelPart pelo14;
	private final ModelPart pelo15;
	private final ModelPart pelo16;
	private final ModelPart pelo17;
	private final ModelPart pelo18;
	private final ModelPart pelo19;
	private final ModelPart pelo20;
	private final ModelPart pelo21;
	private final ModelPart pelo22;
	private final ModelPart pelo23;
	private final ModelPart pelo24;

	public FemHairModel(ModelPart root) {
        super(root);
        this.femalehair = root.getChild("femalehair");
		this.pelo1 = femalehair.getChild("pelo1");
		this.pelo2 = femalehair.getChild("pelo2");
		this.pelo3 = femalehair.getChild("pelo3");
		this.pelo4 = femalehair.getChild("pelo4");
		this.pelo5 = femalehair.getChild("pelo5");
		this.pelo6 = femalehair.getChild("pelo6");
		this.pelo7 = femalehair.getChild("pelo7");
		this.pelo8 = femalehair.getChild("pelo8");
		this.pelo9 = femalehair.getChild("pelo9");
		this.pelo10 = femalehair.getChild("pelo10");
		this.pelo11 = femalehair.getChild("pelo11");
		this.pelo12 = femalehair.getChild("pelo12");
		this.pelo13 = femalehair.getChild("pelo13");
		this.pelo14 = femalehair.getChild("pelo14");
		this.pelo15 = femalehair.getChild("pelo15");
		this.pelo16 = femalehair.getChild("pelo16");
		this.pelo17 = femalehair.getChild("pelo17");
		this.pelo18 = femalehair.getChild("pelo18");
		this.pelo19 = femalehair.getChild("pelo19");
		this.pelo20 = femalehair.getChild("pelo20");
		this.pelo21 = femalehair.getChild("pelo21");
		this.pelo22 = femalehair.getChild("pelo22");
		this.pelo23 = femalehair.getChild("pelo23");
		this.pelo24 = femalehair.getChild("pelo24");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE,0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition femalehair = partdefinition.addOrReplaceChild("femalehair", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition pelo1 = femalehair.addOrReplaceChild("pelo1", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.0F, -1.0F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5646F, -1.4634F, -7.6637F, -0.5858F, 0.7137F, 0.1947F));

		PartDefinition cube_r1 = pelo1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.0F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r2 = pelo1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 1.5F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r3 = pelo1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -1.0F, -1.0F, 2.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.5F, 0.25F, -0.3927F, 0.0F, 0.0F));

		PartDefinition pelo2 = femalehair.addOrReplaceChild("pelo2", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.75F, -1.0F, 2.5F, 3.75F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.8243F, -3.5815F, -8.4203F, -0.6808F, -0.6056F, -0.4559F));

		PartDefinition cube_r4 = pelo2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.0F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r5 = pelo2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 1.5F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo3 = femalehair.addOrReplaceChild("pelo3", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.0F, -1.0F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.9445F, -1.4217F, -4.1334F, -2.1456F, 1.39F, -1.5346F));

		PartDefinition cube_r6 = pelo3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.0F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r7 = pelo3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 1.5F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo4 = femalehair.addOrReplaceChild("pelo4", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.0F, -1.0F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.4445F, -1.9217F, -1.6334F, -1.3643F, 1.5619F, -0.5793F));

		PartDefinition cube_r8 = pelo4.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.0F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r9 = pelo4.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 1.75F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0081F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo5 = femalehair.addOrReplaceChild("pelo5", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.0F, -1.0F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.4445F, -1.9217F, 1.1166F, 0.7069F, 1.357F, 1.5346F));

		PartDefinition cube_r10 = pelo5.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.0F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r11 = pelo5.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 1.75F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0081F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo6 = femalehair.addOrReplaceChild("pelo6", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.0F, -1.0F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.9445F, 0.5783F, 3.1166F, 1.0213F, 1.4902F, 1.5005F));

		PartDefinition cube_r12 = pelo6.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.0F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r13 = pelo6.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 1.75F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0081F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo7 = femalehair.addOrReplaceChild("pelo7", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.0F, -1.0F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.3243F, -3.0815F, -5.4203F, -0.6413F, -1.051F, -0.3165F));

		PartDefinition cube_r14 = pelo7.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.0F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r15 = pelo7.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 1.5F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo8 = femalehair.addOrReplaceChild("pelo8", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.0F, -1.0F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5743F, -2.0815F, -1.6703F, -1.9795F, -1.4483F, 1.1358F));

		PartDefinition cube_r16 = pelo8.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.0F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r17 = pelo8.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(9, 25).addBox(-1.35F, -1.0F, -0.75F, 1.8F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo9 = femalehair.addOrReplaceChild("pelo9", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.0F, -1.0F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5743F, -2.0815F, 0.5797F, -1.9795F, -1.4483F, 1.1358F));

		PartDefinition cube_r18 = pelo9.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.25F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r19 = pelo9.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0419F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo10 = femalehair.addOrReplaceChild("pelo10", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.0F, -1.0F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.3243F, -0.0815F, 4.5797F, 0.5537F, -1.2847F, -1.1255F));

		PartDefinition cube_r20 = pelo10.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.25F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r21 = pelo10.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0419F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo11 = femalehair.addOrReplaceChild("pelo11", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.0F, -1.0F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5743F, 0.9185F, 7.5797F, 2.7367F, -0.1318F, 2.8868F));

		PartDefinition cube_r22 = pelo11.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.25F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r23 = pelo11.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0419F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo12 = femalehair.addOrReplaceChild("pelo12", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.0F, -1.0F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8243F, 0.9185F, 7.5797F, 2.7367F, -0.1318F, 3.105F));

		PartDefinition cube_r24 = pelo12.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.25F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r25 = pelo12.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0419F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo13 = femalehair.addOrReplaceChild("pelo13", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.0F, -1.0F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6757F, 1.1685F, 7.5797F, 2.7367F, -0.1318F, -3.0909F));

		PartDefinition cube_r26 = pelo13.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.25F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r27 = pelo13.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0419F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo14 = femalehair.addOrReplaceChild("pelo14", CubeListBuilder.create().texOffs(5, 23).addBox(-1.5F, -10.0F, -1.0F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9257F, 0.9185F, 7.8297F, 2.7367F, -0.1318F, -2.9164F));

		PartDefinition cube_r28 = pelo14.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.25F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1419F, -4.5779F, 0.9255F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r29 = pelo14.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0419F, -4.5779F, 0.9255F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo15 = femalehair.addOrReplaceChild("pelo15", CubeListBuilder.create().texOffs(5, 23).addBox(-1.1779F, -3.641F, -1.4383F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5709F, -8.246F, -1.6499F, -2.1456F, 1.39F, -0.8365F));

		PartDefinition cube_r30 = pelo15.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.0F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.464F, 1.7811F, 0.4871F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r31 = pelo15.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 1.5F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.464F, 1.7811F, 0.4871F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo16 = femalehair.addOrReplaceChild("pelo16", CubeListBuilder.create().texOffs(5, 23).addBox(-1.1779F, -3.641F, -1.4383F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4905F, -8.3027F, -2.2861F, -1.2038F, -1.3303F, -0.2384F));

		PartDefinition cube_r32 = pelo16.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.0F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.464F, 1.7811F, 0.4871F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r33 = pelo16.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 1.5F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.464F, 1.7811F, 0.4871F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo17 = femalehair.addOrReplaceChild("pelo17", CubeListBuilder.create().texOffs(5, 23).addBox(-1.1696F, -3.641F, -1.4383F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4646F, -7.7349F, 1.6341F, 2.323F, 1.1421F, -2.6507F));

		PartDefinition cube_r34 = pelo17.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.0F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4723F, 1.7811F, 0.4871F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r35 = pelo17.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 1.75F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3223F, 1.7811F, 0.4871F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo18 = femalehair.addOrReplaceChild("pelo18", CubeListBuilder.create().texOffs(5, 23).addBox(-1.2696F, -3.641F, -1.4383F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0879F, -8.2177F, 0.2928F, -0.2227F, -1.3605F, -1.2192F));

		PartDefinition cube_r36 = pelo18.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.25F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3723F, 1.7811F, 0.4871F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r37 = pelo18.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2723F, 1.7811F, 0.4871F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo19 = femalehair.addOrReplaceChild("pelo19", CubeListBuilder.create().texOffs(5, 23).addBox(-1.1696F, -3.641F, -1.4383F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.3146F, -7.1349F, 4.0341F, 2.0931F, 0.7802F, -2.9286F));

		PartDefinition cube_r38 = pelo19.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.0F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4723F, 1.7811F, 0.4871F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r39 = pelo19.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 1.75F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3223F, 1.7811F, 0.4871F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo20 = femalehair.addOrReplaceChild("pelo20", CubeListBuilder.create().texOffs(5, 23).addBox(-1.2696F, -3.641F, -1.4383F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0879F, -8.2177F, 3.0428F, 1.2306F, -1.1598F, -2.6289F));

		PartDefinition cube_r40 = pelo20.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.25F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3723F, 1.7811F, 0.4871F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r41 = pelo20.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2723F, 1.7811F, 0.4871F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo21 = femalehair.addOrReplaceChild("pelo21", CubeListBuilder.create().texOffs(5, 23).addBox(-1.2696F, -3.641F, -1.4383F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2462F, -7.0953F, 4.5818F, 2.2151F, -0.241F, 2.724F));

		PartDefinition cube_r42 = pelo21.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.25F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3723F, 1.7811F, 0.4871F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r43 = pelo21.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2723F, 1.7811F, 0.4871F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo22 = femalehair.addOrReplaceChild("pelo22", CubeListBuilder.create().texOffs(5, 23).addBox(-1.2696F, -3.641F, -1.4383F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7538F, -6.5953F, 4.5818F, 2.2751F, 0.3231F, -3.033F));

		PartDefinition cube_r44 = pelo22.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.25F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3723F, 1.7811F, 0.4871F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r45 = pelo22.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2723F, 1.7811F, 0.4871F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo23 = femalehair.addOrReplaceChild("pelo23", CubeListBuilder.create().texOffs(5, 23).addBox(-1.2696F, -3.641F, -1.4383F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2538F, -6.5953F, 5.0818F, 2.247F, 0.0628F, -3.0384F));

		PartDefinition cube_r46 = pelo23.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.25F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3723F, 1.7811F, 0.4871F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r47 = pelo23.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2723F, 1.7811F, 0.4871F, 0.5672F, 0.0F, 0.0F));

		PartDefinition pelo24 = femalehair.addOrReplaceChild("pelo24", CubeListBuilder.create().texOffs(5, 23).addBox(-1.2696F, -3.641F, -1.4383F, 2.5F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3462F, -6.5953F, 5.0818F, 2.247F, 0.0628F, 3.1139F));

		PartDefinition cube_r48 = pelo24.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(7, 24).addBox(-1.5F, -3.0F, -1.1F, 2.25F, 2.5F, 1.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3723F, 1.7811F, 0.4871F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r49 = pelo24.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(9, 25).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 2.5F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2723F, 1.7811F, 0.4871F, 0.5672F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		femalehair.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}