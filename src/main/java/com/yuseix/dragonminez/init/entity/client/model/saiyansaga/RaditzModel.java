package com.yuseix.dragonminez.init.entity.client.model.saiyansaga;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class RaditzModel<T extends LivingEntity> extends PlayerModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "sagas"), "raditz");
	private final ModelPart head;
	private final ModelPart hairss3;
	private final ModelPart hairright;
	private final ModelPart part1;
	private final ModelPart part3;
	private final ModelPart part5;
	private final ModelPart part4;
	private final ModelPart part9;
	private final ModelPart part10;
	private final ModelPart part17;
	private final ModelPart part21;
	private final ModelPart part24;
	private final ModelPart part41;
	private final ModelPart part40;
	private final ModelPart part42;
	private final ModelPart part46;
	private final ModelPart hairleft;
	private final ModelPart part2;
	private final ModelPart part6;
	private final ModelPart part7;
	private final ModelPart part8;
	private final ModelPart part13;
	private final ModelPart part43;
	private final ModelPart part44;
	private final ModelPart part45;
	private final ModelPart part47;
	private final ModelPart part11;
	private final ModelPart part12;
	private final ModelPart part14;
	private final ModelPart part15;
	private final ModelPart part16;
	private final ModelPart part18;
	private final ModelPart part19;
	private final ModelPart part20;
	private final ModelPart part22;
	private final ModelPart part23;
	private final ModelPart part25;
	private final ModelPart part26;
	private final ModelPart part27;
	private final ModelPart part28;
	private final ModelPart part29;
	private final ModelPart part30;
	private final ModelPart part31;
	private final ModelPart part32;
	private final ModelPart part33;
	private final ModelPart part34;
	private final ModelPart part35;
	private final ModelPart part36;
	private final ModelPart part37;
	private final ModelPart part38;
	private final ModelPart part39;
	private final ModelPart part48;
	private final ModelPart part49;
	private final ModelPart part50;
	private final ModelPart part51;
	private final ModelPart part52;
	private final ModelPart part53;
	private final ModelPart part54;
	private final ModelPart part55;
	private final ModelPart part56;
	private final ModelPart part57;
	private final ModelPart part58;
	private final ModelPart radar;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public RaditzModel(ModelPart root) {
        super(root, false);
        this.head = root.getChild("head");
		this.hairss3 = this.head.getChild("hairss3");
		this.hairright = this.hairss3.getChild("hairright");
		this.part1 = this.hairright.getChild("part1");
		this.part3 = this.hairright.getChild("part3");
		this.part5 = this.hairright.getChild("part5");
		this.part4 = this.hairright.getChild("part4");
		this.part9 = this.hairright.getChild("part9");
		this.part10 = this.hairright.getChild("part10");
		this.part17 = this.hairright.getChild("part17");
		this.part21 = this.hairright.getChild("part21");
		this.part24 = this.hairright.getChild("part24");
		this.part41 = this.hairright.getChild("part41");
		this.part40 = this.hairright.getChild("part40");
		this.part42 = this.hairright.getChild("part42");
		this.part46 = this.hairright.getChild("part46");
		this.hairleft = this.hairss3.getChild("hairleft");
		this.part2 = this.hairleft.getChild("part2");
		this.part6 = this.hairleft.getChild("part6");
		this.part7 = this.hairleft.getChild("part7");
		this.part8 = this.hairleft.getChild("part8");
		this.part13 = this.hairleft.getChild("part13");
		this.part43 = this.hairleft.getChild("part43");
		this.part44 = this.hairleft.getChild("part44");
		this.part45 = this.hairleft.getChild("part45");
		this.part47 = this.hairleft.getChild("part47");
		this.part11 = this.hairss3.getChild("part11");
		this.part12 = this.hairss3.getChild("part12");
		this.part14 = this.hairss3.getChild("part14");
		this.part15 = this.hairss3.getChild("part15");
		this.part16 = this.hairss3.getChild("part16");
		this.part18 = this.hairss3.getChild("part18");
		this.part19 = this.hairss3.getChild("part19");
		this.part20 = this.hairss3.getChild("part20");
		this.part22 = this.hairss3.getChild("part22");
		this.part23 = this.hairss3.getChild("part23");
		this.part25 = this.hairss3.getChild("part25");
		this.part26 = this.hairss3.getChild("part26");
		this.part27 = this.hairss3.getChild("part27");
		this.part28 = this.hairss3.getChild("part28");
		this.part29 = this.hairss3.getChild("part29");
		this.part30 = this.hairss3.getChild("part30");
		this.part31 = this.hairss3.getChild("part31");
		this.part32 = this.hairss3.getChild("part32");
		this.part33 = this.hairss3.getChild("part33");
		this.part34 = this.hairss3.getChild("part34");
		this.part35 = this.hairss3.getChild("part35");
		this.part36 = this.hairss3.getChild("part36");
		this.part37 = this.hairss3.getChild("part37");
		this.part38 = this.hairss3.getChild("part38");
		this.part39 = this.hairss3.getChild("part39");
		this.part48 = this.hairss3.getChild("part48");
		this.part49 = this.hairss3.getChild("part49");
		this.part50 = this.hairss3.getChild("part50");
		this.part51 = this.hairss3.getChild("part51");
		this.part52 = this.hairss3.getChild("part52");
		this.part53 = this.hairss3.getChild("part53");
		this.part54 = this.hairss3.getChild("part54");
		this.part55 = this.hairss3.getChild("part55");
		this.part56 = this.hairss3.getChild("part56");
		this.part57 = this.hairss3.getChild("part57");
		this.part58 = this.hairss3.getChild("part58");
		this.radar = this.head.getChild("radar");
		this.body = root.getChild("body");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = PlayerModel.createMesh(CubeDeformation.NONE, false);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hairss3 = head.addOrReplaceChild("hairss3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hairright = hairss3.addOrReplaceChild("hairright", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition part1 = hairright.addOrReplaceChild("part1", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1698F, -7.7735F, -3.0946F, -0.5796F, -0.0021F, -0.4019F));

		PartDefinition cube_r1 = part1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r2 = part1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part3 = hairright.addOrReplaceChild("part3", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4456F, -8.0384F, -3.4913F, -0.6932F, 0.0F, 0.0F));

		PartDefinition cube_r3 = part3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r4 = part3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r5 = part3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part5 = hairright.addOrReplaceChild("part5", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.6002F, -0.5016F, -4.362F, -0.3123F, 0.1586F, -1.1158F));

		PartDefinition cube_r6 = part5.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r7 = part5.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r8 = part5.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part4 = hairright.addOrReplaceChild("part4", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.7002F, -3.2016F, -3.612F, 0.0F, 0.0F, -0.7418F));

		PartDefinition cube_r9 = part4.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r10 = part4.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r11 = part4.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part9 = hairright.addOrReplaceChild("part9", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.9578F, -7.4406F, -3.4783F, -0.0598F, -0.1051F, -0.4584F));

		PartDefinition cube_r12 = part9.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4061F, -4.4065F, 1.7628F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r13 = part9.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4061F, -4.4065F, 1.7628F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r14 = part9.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9311F, 3.446F, 0.2259F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part10 = hairright.addOrReplaceChild("part10", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1698F, -9.0235F, -0.5946F, -1.3676F, -0.294F, -0.2781F));

		PartDefinition cube_r15 = part10.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r16 = part10.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part17 = hairright.addOrReplaceChild("part17", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, -10.3383F, 2.4345F, -1.8683F, 0.0157F, 0.0669F));

		PartDefinition cube_r17 = part17.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r18 = part17.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part21 = hairright.addOrReplaceChild("part21", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.15F, -10.3383F, 0.4345F, -1.3458F, -0.0169F, 0.0666F));

		PartDefinition cube_r19 = part21.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r20 = part21.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part24 = hairright.addOrReplaceChild("part24", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9198F, -7.2735F, -2.0946F, -1.1655F, 0.4861F, -1.533F));

		PartDefinition cube_r21 = part24.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r22 = part24.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part41 = hairright.addOrReplaceChild("part41", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.6002F, -2.5016F, -4.362F, -0.3123F, 0.1586F, -1.1158F));

		PartDefinition cube_r23 = part41.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r24 = part41.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r25 = part41.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part40 = hairright.addOrReplaceChild("part40", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.6002F, -0.5016F, -1.612F, -0.3123F, 0.1586F, -1.1158F));

		PartDefinition cube_r26 = part40.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r27 = part40.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r28 = part40.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part42 = hairright.addOrReplaceChild("part42", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.6002F, -3.0016F, -2.112F, -0.3123F, 0.1586F, -1.1158F));

		PartDefinition cube_r29 = part42.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r30 = part42.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r31 = part42.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part46 = hairright.addOrReplaceChild("part46", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8698F, -9.9735F, -1.2946F, -0.8785F, -0.0686F, -0.1277F));

		PartDefinition cube_r32 = part46.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r33 = part46.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition hairleft = hairss3.addOrReplaceChild("hairleft", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition part2 = hairleft.addOrReplaceChild("part2", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5934F, -7.9284F, -3.4366F, -0.5149F, 0.0478F, 0.1225F));

		PartDefinition cube_r34 = part2.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r35 = part2.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part6 = hairleft.addOrReplaceChild("part6", CubeListBuilder.create(), PartPose.offsetAndRotation(1.4498F, -3.0016F, -4.012F, -0.162F, 0.1468F, 0.7298F));

		PartDefinition cube_r36 = part6.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r37 = part6.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r38 = part6.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part7 = hairleft.addOrReplaceChild("part7", CubeListBuilder.create(), PartPose.offsetAndRotation(1.3998F, -0.2516F, -4.212F, -0.5271F, -0.2806F, 1.4494F));

		PartDefinition cube_r39 = part7.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r40 = part7.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r41 = part7.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part8 = hairleft.addOrReplaceChild("part8", CubeListBuilder.create(), PartPose.offsetAndRotation(3.3934F, -7.7284F, -3.5366F, 0.0239F, 0.0954F, 0.3812F));

		PartDefinition cube_r42 = part8.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4061F, -4.4065F, 1.7628F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r43 = part8.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4061F, -4.4065F, 1.7628F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r44 = part8.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9311F, 3.446F, 0.2259F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part13 = hairleft.addOrReplaceChild("part13", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, -11.0883F, -0.3155F, -1.3023F, -0.0227F, 0.0649F));

		PartDefinition cube_r45 = part13.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r46 = part13.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part43 = hairleft.addOrReplaceChild("part43", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5998F, -2.6516F, -3.912F, -0.3389F, -0.2655F, 1.3167F));

		PartDefinition cube_r47 = part43.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r48 = part43.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r49 = part43.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part44 = hairleft.addOrReplaceChild("part44", CubeListBuilder.create(), PartPose.offsetAndRotation(1.7998F, -2.1516F, -5.412F, -0.7637F, -0.1258F, 1.5167F));

		PartDefinition cube_r50 = part44.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r51 = part44.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r52 = part44.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part45 = hairleft.addOrReplaceChild("part45", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.3434F, -9.9284F, -1.4866F, -1.0607F, 0.2214F, 0.2653F));

		PartDefinition cube_r53 = part45.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r54 = part45.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part47 = hairleft.addOrReplaceChild("part47", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3302F, -9.9735F, -1.2946F, -0.9028F, 0.0989F, 0.1294F));

		PartDefinition cube_r55 = part47.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r56 = part47.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part11 = hairss3.addOrReplaceChild("part11", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8302F, -9.0235F, -2.0946F, -1.0944F, 0.0696F, 0.1634F));

		PartDefinition cube_r57 = part11.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r58 = part11.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part12 = hairss3.addOrReplaceChild("part12", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8302F, -9.5235F, 3.1554F, -2.4762F, 0.14F, 0.0851F));

		PartDefinition cube_r59 = part12.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(54, 7).addBox(-2.25F, -5.1F, -0.85F, 2.55F, 2.1F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -6.2116F, 1.3414F, -0.8269F, 0.0184F, 0.0606F));

		PartDefinition cube_r60 = part12.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(53, 7).addBox(-2.45F, -5.5F, -0.85F, 2.75F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -4.4616F, -0.4086F, -0.784F, 0.0151F, 0.0543F));

		PartDefinition cube_r61 = part12.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r62 = part12.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part14 = hairss3.addOrReplaceChild("part14", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.35F, -10.3383F, 1.6845F, -1.9119F, 0.0186F, 0.0662F));

		PartDefinition cube_r63 = part14.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(54, 7).addBox(-2.25F, -5.1F, -0.85F, 2.55F, 2.1F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -6.2116F, 1.3414F, -0.8269F, 0.0184F, 0.0606F));

		PartDefinition cube_r64 = part14.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(53, 7).addBox(-2.45F, -5.5F, -0.85F, 2.75F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -4.4616F, -0.4086F, -0.784F, 0.0151F, 0.0543F));

		PartDefinition cube_r65 = part14.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r66 = part14.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part15 = hairss3.addOrReplaceChild("part15", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.15F, -10.3383F, 1.6845F, -2.2604F, 0.0401F, 0.0559F));

		PartDefinition cube_r67 = part15.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(54, 7).addBox(-2.25F, -5.1F, -0.85F, 2.55F, 2.1F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -6.2116F, 1.3414F, -0.8269F, 0.0184F, 0.0606F));

		PartDefinition cube_r68 = part15.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(53, 7).addBox(-2.45F, -5.5F, -0.85F, 2.75F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -4.4616F, -0.4086F, -0.784F, 0.0151F, 0.0543F));

		PartDefinition cube_r69 = part15.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r70 = part15.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part16 = hairss3.addOrReplaceChild("part16", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, -9.0883F, 3.4345F, -2.3476F, 0.0448F, 0.0521F));

		PartDefinition cube_r71 = part16.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(54, 7).addBox(-2.25F, -5.1F, -0.85F, 2.55F, 2.1F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -6.2116F, 1.3414F, -0.8269F, 0.0184F, 0.0606F));

		PartDefinition cube_r72 = part16.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(53, 7).addBox(-2.45F, -5.5F, -0.85F, 2.75F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -4.4616F, -0.4086F, -0.784F, 0.0151F, 0.0543F));

		PartDefinition cube_r73 = part16.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r74 = part16.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part18 = hairss3.addOrReplaceChild("part18", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.85F, -10.3383F, 0.4345F, -1.3894F, -0.0169F, 0.0666F));

		PartDefinition cube_r75 = part18.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r76 = part18.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part19 = hairss3.addOrReplaceChild("part19", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.35F, -5.0883F, 6.1845F, -2.8717F, 0.0649F, 0.0227F));

		PartDefinition cube_r77 = part19.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r78 = part19.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part20 = hairss3.addOrReplaceChild("part20", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.35F, -8.3383F, 5.4345F, -2.5222F, 0.0532F, 0.0436F));

		PartDefinition cube_r79 = part20.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(54, 7).addBox(-2.25F, -5.1F, -0.85F, 2.55F, 2.1F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -6.2116F, 1.3414F, -0.8269F, 0.0184F, 0.0606F));

		PartDefinition cube_r80 = part20.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(53, 7).addBox(-2.45F, -5.5F, -0.85F, 2.75F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -4.4616F, -0.4086F, -0.784F, 0.0151F, 0.0543F));

		PartDefinition cube_r81 = part20.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r82 = part20.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part22 = hairss3.addOrReplaceChild("part22", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.35F, -6.0883F, -1.3155F, -1.0356F, -0.3309F, 1.09F));

		PartDefinition cube_r83 = part22.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r84 = part22.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part23 = hairss3.addOrReplaceChild("part23", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.35F, -3.2383F, 8.5345F, -3.0028F, 0.0673F, 0.0141F));

		PartDefinition cube_r85 = part23.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(53, 7).addBox(-2.45F, -5.5F, -0.85F, 2.75F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -4.4616F, -0.4086F, -0.784F, 0.0151F, 0.0543F));

		PartDefinition cube_r86 = part23.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r87 = part23.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part25 = hairss3.addOrReplaceChild("part25", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.15F, -9.0883F, 4.9345F, -2.2604F, 0.0425F, 0.0541F));

		PartDefinition cube_r88 = part25.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r89 = part25.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part26 = hairss3.addOrReplaceChild("part26", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.15F, -7.0883F, 3.6845F, -2.304F, 0.0448F, 0.0521F));

		PartDefinition cube_r90 = part26.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r91 = part26.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part27 = hairss3.addOrReplaceChild("part27", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.65F, -6.5883F, 0.4345F, -2.0453F, -0.0127F, 0.0714F));

		PartDefinition cube_r92 = part27.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r93 = part27.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part28 = hairss3.addOrReplaceChild("part28", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.15F, -4.5883F, -0.8155F, -2.0327F, 0.1614F, 0.0723F));

		PartDefinition cube_r94 = part28.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r95 = part28.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part29 = hairss3.addOrReplaceChild("part29", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.15F, -6.3383F, 7.6845F, -2.6111F, 0.0276F, 0.067F));

		PartDefinition cube_r96 = part29.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r97 = part29.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part30 = hairss3.addOrReplaceChild("part30", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, -1.5883F, 4.1845F, -2.5676F, 0.0247F, 0.0682F));

		PartDefinition cube_r98 = part30.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r99 = part30.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part31 = hairss3.addOrReplaceChild("part31", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, -1.5883F, 8.1845F, -2.9598F, 0.0489F, 0.0536F));

		PartDefinition cube_r100 = part31.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r101 = part31.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part32 = hairss3.addOrReplaceChild("part32", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.15F, -3.5883F, 8.1845F, -2.6983F, 0.0334F, 0.0644F));

		PartDefinition cube_r102 = part32.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r103 = part32.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part33 = hairss3.addOrReplaceChild("part33", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9F, 0.9117F, 8.1845F, -2.9598F, 0.0489F, 0.0536F));

		PartDefinition cube_r104 = part33.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r105 = part33.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part34 = hairss3.addOrReplaceChild("part34", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.15F, -6.0883F, 4.9345F, -2.6111F, 0.0276F, 0.067F));

		PartDefinition cube_r106 = part34.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r107 = part34.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part35 = hairss3.addOrReplaceChild("part35", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.35F, 3.9117F, 9.1845F, 3.1051F, 0.0593F, 0.0417F));

		PartDefinition cube_r108 = part35.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r109 = part35.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part36 = hairss3.addOrReplaceChild("part36", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.35F, -0.5883F, 9.1845F, -2.9598F, 0.0489F, 0.0536F));

		PartDefinition cube_r110 = part36.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r111 = part36.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part37 = hairss3.addOrReplaceChild("part37", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, 1.9117F, 9.1845F, -3.0034F, 0.0512F, 0.0514F));

		PartDefinition cube_r112 = part37.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r113 = part37.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part38 = hairss3.addOrReplaceChild("part38", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.35F, 2.1617F, 8.6845F, 3.0615F, 0.0611F, 0.0391F));

		PartDefinition cube_r114 = part38.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r115 = part38.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part39 = hairss3.addOrReplaceChild("part39", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6F, -3.0883F, -1.3155F, -1.3586F, -0.3284F, 1.6317F));

		PartDefinition cube_r116 = part39.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r117 = part39.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part48 = hairss3.addOrReplaceChild("part48", CubeListBuilder.create(), PartPose.offsetAndRotation(1.3998F, -0.2516F, -2.212F, -0.5271F, -0.2806F, 1.4494F));

		PartDefinition cube_r118 = part48.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r119 = part48.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r120 = part48.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part49 = hairss3.addOrReplaceChild("part49", CubeListBuilder.create(), PartPose.offsetAndRotation(1.3998F, 0.7484F, -0.462F, -0.5271F, -0.2806F, 1.4494F));

		PartDefinition cube_r121 = part49.addOrReplaceChild("cube_r121", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r122 = part49.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r123 = part49.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part50 = hairss3.addOrReplaceChild("part50", CubeListBuilder.create(), PartPose.offsetAndRotation(1.3998F, -1.0016F, -1.212F, -0.5271F, -0.2806F, 1.4494F));

		PartDefinition cube_r124 = part50.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r125 = part50.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r126 = part50.addOrReplaceChild("cube_r126", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part51 = hairss3.addOrReplaceChild("part51", CubeListBuilder.create(), PartPose.offsetAndRotation(1.3998F, -2.3516F, -2.762F, -0.5271F, -0.2806F, 1.4494F));

		PartDefinition cube_r127 = part51.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(49, 4).addBox(-1.625F, 1.0F, -0.95F, 2.25F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r128 = part51.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(50, 4).addBox(-1.275F, -0.45F, -0.5F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.025F, -7.564F, 1.8283F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r129 = part51.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part52 = hairss3.addOrReplaceChild("part52", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1F, -6.8383F, 1.9345F, -2.0861F, 0.0298F, 0.062F));

		PartDefinition cube_r130 = part52.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r131 = part52.addOrReplaceChild("cube_r131", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part53 = hairss3.addOrReplaceChild("part53", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1F, -8.3383F, 2.6845F, -2.0861F, 0.0298F, 0.062F));

		PartDefinition cube_r132 = part53.addOrReplaceChild("cube_r132", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r133 = part53.addOrReplaceChild("cube_r133", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part54 = hairss3.addOrReplaceChild("part54", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4F, -1.8383F, 5.6845F, -2.5658F, 0.0551F, 0.0412F));

		PartDefinition cube_r134 = part54.addOrReplaceChild("cube_r134", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r135 = part54.addOrReplaceChild("cube_r135", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part55 = hairss3.addOrReplaceChild("part55", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.45F, -7.0883F, 2.4345F, -2.304F, 0.0448F, 0.0521F));

		PartDefinition cube_r136 = part55.addOrReplaceChild("cube_r136", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r137 = part55.addOrReplaceChild("cube_r137", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part56 = hairss3.addOrReplaceChild("part56", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, 4.0117F, 7.1845F, 3.1051F, 0.0593F, 0.0417F));

		PartDefinition cube_r138 = part56.addOrReplaceChild("cube_r138", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r139 = part56.addOrReplaceChild("cube_r139", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part57 = hairss3.addOrReplaceChild("part57", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4F, 4.4117F, 7.8845F, 3.1051F, 0.0593F, 0.0417F));

		PartDefinition cube_r140 = part57.addOrReplaceChild("cube_r140", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r141 = part57.addOrReplaceChild("cube_r141", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition part58 = hairss3.addOrReplaceChild("part58", CubeListBuilder.create().texOffs(46, 3).addBox(-2.5F, -2.9616F, -0.5586F, 4.0F, 3.25F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 6.4117F, 8.4845F, 2.7992F, 0.0691F, 0.0219F));

		PartDefinition cube_r142 = part58.addOrReplaceChild("cube_r142", CubeListBuilder.create().texOffs(50, 5).addBox(-2.55F, -5.5F, -1.1F, 3.05F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.9616F, -0.0586F, -0.3897F, 0.0079F, 0.0346F));

		PartDefinition cube_r143 = part58.addOrReplaceChild("cube_r143", CubeListBuilder.create().texOffs(47, 3).addBox(-2.65F, -5.5F, -1.35F, 3.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 0.2884F, 0.2914F, -0.1745F, 0.0F, 0.0F));

		PartDefinition radar = head.addOrReplaceChild("radar", CubeListBuilder.create().texOffs(56, 57).addBox(4.15F, -4.75F, -3.75F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(50, 59).addBox(3.9F, -4.1F, -4.75F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(51, 52).addBox(0.75F, -4.3F, -5.0F, 3.3F, 2.4F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2F))
		.texOffs(1, 49).addBox(-4.25F, 9.25F, -2.5F, 8.5F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(1, 49).addBox(-4.25F, 9.25F, 1.5F, 8.5F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(-2, 46).addBox(3.35F, 9.25F, -2.25F, 1.0F, 1.0F, 4.5F, new CubeDeformation(0.0F))
		.texOffs(-2, 46).addBox(-4.35F, 9.25F, -2.25F, 1.0F, 1.0F, 4.5F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r144 = body.addOrReplaceChild("cube_r144", CubeListBuilder.create().texOffs(14, 32).addBox(0.0F, -2.0F, -2.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, 10.25F, 0.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r145 = body.addOrReplaceChild("cube_r145", CubeListBuilder.create().texOffs(1, 32).addBox(-1.0F, -2.0F, -2.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.35F, 10.65F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.18F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r146 = right_arm.addOrReplaceChild("cube_r146", CubeListBuilder.create().texOffs(42, 37).addBox(-2.0F, -2.0F, -2.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-2.7F, -0.75F, -0.5F, 0.0F, 0.0F, 0.1309F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.18F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition cube_r147 = left_arm.addOrReplaceChild("cube_r147", CubeListBuilder.create().texOffs(25, 33).addBox(-1.0F, -2.0F, -2.5F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.25F, -0.35F, 0.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.18F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.18F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		hat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}