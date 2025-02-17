package com.yuseix.dragonminez.client.character.models;
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

public class OzaruModel<T extends LivingEntity> extends PlayerModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DragonMineZ.MOD_ID, "races"), "ozaru");
	private final ModelPart head;
	private final ModelPart ozaruhair;
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
	private final ModelPart hair30;
	private final ModelPart hair31;
	private final ModelPart hair32;
	private final ModelPart hair33;
	private final ModelPart hair34;
	private final ModelPart hair35;
	private final ModelPart hair36;
	private final ModelPart hair37;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public OzaruModel(ModelPart root) {
        super(root, false);
        this.head = root.getChild("head");
		this.ozaruhair = this.head.getChild("ozaruhair");
		this.hair1 = this.ozaruhair.getChild("hair1");
		this.hair2 = this.ozaruhair.getChild("hair2");
		this.hair3 = this.ozaruhair.getChild("hair3");
		this.hair4 = this.ozaruhair.getChild("hair4");
		this.hair5 = this.ozaruhair.getChild("hair5");
		this.hair6 = this.ozaruhair.getChild("hair6");
		this.hair7 = this.ozaruhair.getChild("hair7");
		this.hair8 = this.ozaruhair.getChild("hair8");
		this.hair9 = this.ozaruhair.getChild("hair9");
		this.hair10 = this.ozaruhair.getChild("hair10");
		this.hair11 = this.ozaruhair.getChild("hair11");
		this.hair12 = this.ozaruhair.getChild("hair12");
		this.hair13 = this.ozaruhair.getChild("hair13");
		this.hair14 = this.ozaruhair.getChild("hair14");
		this.hair15 = this.ozaruhair.getChild("hair15");
		this.hair16 = this.ozaruhair.getChild("hair16");
		this.hair17 = this.ozaruhair.getChild("hair17");
		this.hair18 = this.ozaruhair.getChild("hair18");
		this.hair19 = this.ozaruhair.getChild("hair19");
		this.hair20 = this.ozaruhair.getChild("hair20");
		this.hair21 = this.ozaruhair.getChild("hair21");
		this.hair22 = this.ozaruhair.getChild("hair22");
		this.hair23 = this.ozaruhair.getChild("hair23");
		this.hair24 = this.ozaruhair.getChild("hair24");
		this.hair25 = this.ozaruhair.getChild("hair25");
		this.hair26 = this.ozaruhair.getChild("hair26");
		this.hair27 = this.ozaruhair.getChild("hair27");
		this.hair28 = this.ozaruhair.getChild("hair28");
		this.hair29 = this.ozaruhair.getChild("hair29");
		this.hair30 = this.ozaruhair.getChild("hair30");
		this.hair31 = this.ozaruhair.getChild("hair31");
		this.hair32 = this.ozaruhair.getChild("hair32");
		this.hair33 = this.ozaruhair.getChild("hair33");
		this.hair34 = this.ozaruhair.getChild("hair34");
		this.hair35 = this.ozaruhair.getChild("hair35");
		this.hair36 = this.ozaruhair.getChild("hair36");
		this.hair37 = this.ozaruhair.getChild("hair37");
		this.body = root.getChild("body");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = PlayerModel.createMesh(CubeDeformation.NONE, false);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 34).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ozaruhair = head.addOrReplaceChild("ozaruhair", CubeListBuilder.create(), PartPose.offset(0.0F, 0.2F, -0.3F));

		PartDefinition hair1 = ozaruhair.addOrReplaceChild("hair1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = hair1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(42, 3).addBox(-1.3F, -2.0F, -1.0F, 2.3F, 2.2F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8F, -0.6F, 0.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition hair2 = ozaruhair.addOrReplaceChild("hair2", CubeListBuilder.create(), PartPose.offset(-1.5F, -0.75F, 0.1F));

		PartDefinition cube_r2 = hair2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(42, 3).addBox(-1.6F, -2.0F, -1.0F, 3.6F, 2.9F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.55F, -1.85F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition hair3 = ozaruhair.addOrReplaceChild("hair3", CubeListBuilder.create(), PartPose.offset(-1.75F, -3.5F, 0.1F));

		PartDefinition cube_r3 = hair3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(43, 4).addBox(-1.0F, -2.5F, -0.9F, 3.0F, 3.3F, 2.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.05F, -2.05F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition hair4 = ozaruhair.addOrReplaceChild("hair4", CubeListBuilder.create(), PartPose.offset(-0.75F, -1.25F, -0.9F));

		PartDefinition cube_r4 = hair4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(42, 3).addBox(-1.0F, -2.0F, -1.75F, 3.0F, 2.3F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.3F, -1.6F, 0.0F, 0.0F, 0.0F, 1.0036F));

		PartDefinition hair5 = ozaruhair.addOrReplaceChild("hair5", CubeListBuilder.create(), PartPose.offset(-1.25F, -3.25F, -1.15F));

		PartDefinition cube_r5 = hair5.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(42, 3).addBox(-1.0F, -2.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.05F, -2.7F, 0.0F, 0.0F, 0.0F, 1.0472F));

		PartDefinition hair6 = ozaruhair.addOrReplaceChild("hair6", CubeListBuilder.create(), PartPose.offset(-0.75F, -3.25F, -2.9F));

		PartDefinition hair7 = ozaruhair.addOrReplaceChild("hair7", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, -3.75F, -3.25F, 0.0F, 0.1309F, 0.0F));

		PartDefinition cube_r6 = hair7.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(43, 3).addBox(-0.5F, -3.1F, -0.8F, 3.0F, 3.2F, 3.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -5.2F, 1.15F, 0.0F, 0.2182F, 2.0508F));

		PartDefinition hair8 = ozaruhair.addOrReplaceChild("hair8", CubeListBuilder.create(), PartPose.offset(-3.8938F, -1.8295F, 2.75F));

		PartDefinition cube_r7 = hair8.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(42, 3).addBox(-2.0F, -2.5F, -1.6F, 3.0F, 2.5F, 3.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0643F, 1.2961F, -0.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition hair9 = ozaruhair.addOrReplaceChild("hair9", CubeListBuilder.create(), PartPose.offset(-4.3938F, -3.8295F, 2.75F));

		PartDefinition cube_r8 = hair9.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(43, 4).addBox(-2.0F, -2.5F, -1.25F, 3.0F, 2.5F, 3.15F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1643F, 0.7961F, -0.5F, 0.0F, 0.0F, 1.0036F));

		PartDefinition hair10 = ozaruhair.addOrReplaceChild("hair10", CubeListBuilder.create(), PartPose.offset(-4.3938F, -6.3295F, 2.75F));

		PartDefinition cube_r9 = hair10.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(45, 6).addBox(-2.0F, -2.5F, -0.95F, 4.0F, 2.5F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4357F, -0.2039F, -0.5F, 0.0F, 0.0F, -1.3963F));

		PartDefinition hair11 = ozaruhair.addOrReplaceChild("hair11", CubeListBuilder.create(), PartPose.offset(0.0F, -7.25F, -3.0F));

		PartDefinition cube_r10 = hair11.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(44, 3).addBox(-1.95F, -2.3F, -1.1F, 3.4F, 2.5F, 3.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.55F, 1.35F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r11 = hair11.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(43, 3).addBox(-2.2F, -2.3F, -1.1F, 4.0F, 2.5F, 3.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, -0.3491F, 0.0F, 0.0F));

		PartDefinition hair12 = ozaruhair.addOrReplaceChild("hair12", CubeListBuilder.create(), PartPose.offset(-3.25F, -4.5F, -0.75F));

		PartDefinition cube_r12 = hair12.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(43, 4).addBox(-0.9F, -3.95F, -1.0F, 5.1F, 4.15F, 3.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8F, -8.0F, 0.85F, 0.0F, 0.0F, 2.2689F));

		PartDefinition cube_r13 = hair12.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(42, 3).addBox(-1.0F, -3.1F, -1.0F, 3.0F, 3.1F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.55F, -5.95F, 0.35F, 0.0F, 0.0F, 2.0071F));

		PartDefinition cube_r14 = hair12.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(42, 3).addBox(-1.0F, -3.1F, -1.0F, 4.7F, 3.4F, 4.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.85F, -4.25F, 0.0F, 0.0F, 0.0F, 1.7017F));

		PartDefinition hair13 = ozaruhair.addOrReplaceChild("hair13", CubeListBuilder.create().texOffs(43, 3).addBox(-3.0F, -3.0F, -0.7F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 3.1F, -0.4363F, 0.0F, 0.0F));

		PartDefinition hair14 = ozaruhair.addOrReplaceChild("hair14", CubeListBuilder.create().texOffs(42, 3).addBox(-2.9F, -4.0F, -1.0F, 5.8F, 4.0F, 2.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 3.8F, -0.1745F, 0.0F, 0.0F));

		PartDefinition hair15 = ozaruhair.addOrReplaceChild("hair15", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.25F, -0.45F, 3.4F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r15 = hair15.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(42, 3).addBox(-3.3F, -3.0F, -1.25F, 2.3F, 3.0F, 2.45F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.35F, 0.0F, -0.1745F, 0.0F));

		PartDefinition hair16 = ozaruhair.addOrReplaceChild("hair16", CubeListBuilder.create().texOffs(42, 3).addBox(-3.4F, -3.0F, -1.25F, 2.4F, 3.0F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.25F, -2.7F, 4.15F, -0.2618F, 0.0F, 0.0F));

		PartDefinition hair17 = ozaruhair.addOrReplaceChild("hair17", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.25F, -5.2F, 4.15F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r16 = hair17.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(42, 3).addBox(-3.3F, -3.2F, -1.25F, 2.3F, 3.8F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, 0.3F, 0.1745F, 0.0F, 0.0F));

		PartDefinition hair18 = ozaruhair.addOrReplaceChild("hair18", CubeListBuilder.create().texOffs(42, 3).addBox(-3.0F, -4.4F, -1.25F, 2.0F, 3.4F, 2.45F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3F, -1.65F, 3.3F, -0.5394F, -0.1848F, -0.2978F));

		PartDefinition hair19 = ozaruhair.addOrReplaceChild("hair19", CubeListBuilder.create(), PartPose.offsetAndRotation(2.55F, 2.9F, 0.0F, 0.0F, 0.0F, 1.5272F));

		PartDefinition cube_r17 = hair19.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(42, 3).addBox(-1.5F, -2.2F, -1.0F, 2.5F, 2.2F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8F, -0.6F, 0.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition hair20 = ozaruhair.addOrReplaceChild("hair20", CubeListBuilder.create(), PartPose.offsetAndRotation(7.95F, -3.45F, 0.1F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r18 = hair20.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(42, 3).addBox(-1.1F, -2.5F, -1.0F, 3.5F, 2.9F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8F, -0.6F, 0.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition hair21 = ozaruhair.addOrReplaceChild("hair21", CubeListBuilder.create(), PartPose.offsetAndRotation(8.0F, -5.85F, 0.1F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r19 = hair21.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(43, 4).addBox(-1.0F, -2.5F, -0.9F, 3.3F, 2.8F, 2.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8F, -0.6F, 0.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition hair22 = ozaruhair.addOrReplaceChild("hair22", CubeListBuilder.create(), PartPose.offsetAndRotation(4.75F, -5.0F, -0.75F, -0.0503F, 0.1209F, 0.6951F));

		PartDefinition cube_r20 = hair22.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(42, 3).addBox(-1.0F, -3.1F, -1.0F, 3.0F, 3.1F, 4.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8F, -0.6F, 0.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition hair23 = ozaruhair.addOrReplaceChild("hair23", CubeListBuilder.create(), PartPose.offsetAndRotation(4.2F, -9.75F, -0.4F, -0.1298F, 0.017F, -0.3502F));

		PartDefinition cube_r21 = hair23.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(42, 3).addBox(-1.0F, -3.1F, -1.0F, 4.0F, 3.1F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.45F, -0.25F, 0.0F, 0.0F, 0.0F, 1.5272F));

		PartDefinition hair24 = ozaruhair.addOrReplaceChild("hair24", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.2F, -9.05F, -0.2F, 0.0503F, 0.1209F, 1.4866F));

		PartDefinition cube_r22 = hair24.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(44, 4).addBox(-0.9F, -3.45F, -0.75F, 5.6F, 3.65F, 3.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.1F, 0.0F, 0.0F, 0.0F, 0.9163F));

		PartDefinition hair25 = ozaruhair.addOrReplaceChild("hair25", CubeListBuilder.create(), PartPose.offset(3.25F, -1.5F, -2.9F));

		PartDefinition hair26 = ozaruhair.addOrReplaceChild("hair26", CubeListBuilder.create(), PartPose.offset(3.0F, 0.5F, -0.9F));

		PartDefinition cube_r23 = hair26.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(42, 3).addBox(-1.5F, -2.1F, -1.75F, 3.5F, 2.1F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, -4.45F, 0.0F, 0.0F, 0.0F, 2.0508F));

		PartDefinition hair27 = ozaruhair.addOrReplaceChild("hair27", CubeListBuilder.create(), PartPose.offset(4.2F, -2.05F, -0.65F));

		PartDefinition cube_r24 = hair27.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(42, 3).addBox(-1.0F, -2.2F, -1.75F, 3.0F, 2.2F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -4.8F, 0.0F, 0.0F, 0.0F, 1.9199F));

		PartDefinition hair28 = ozaruhair.addOrReplaceChild("hair28", CubeListBuilder.create(), PartPose.offset(6.0F, -3.75F, -2.9F));

		PartDefinition cube_r25 = hair28.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(42, 3).addBox(-1.25F, -2.0F, -1.0F, 3.05F, 3.75F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.65F, -3.7F, 0.95F, -0.2618F, 0.0F, -0.5672F));

		PartDefinition hair29 = ozaruhair.addOrReplaceChild("hair29", CubeListBuilder.create(), PartPose.offset(2.5F, 2.95F, 2.25F));

		PartDefinition cube_r26 = hair29.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(44, 6).addBox(-2.75F, -2.0F, -0.8F, 3.75F, 3.15F, 2.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4F, -8.55F, 0.0F, 0.0F, 0.0F, 1.2654F));

		PartDefinition cube_r27 = hair29.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(43, 4).addBox(-2.75F, -2.0F, -0.9F, 3.75F, 3.15F, 2.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, -6.2F, 0.0F, 0.0F, 0.0F, 1.8326F));

		PartDefinition cube_r28 = hair29.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(42, 3).addBox(-1.75F, -2.3F, -1.0F, 2.95F, 3.05F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.35F, -4.1F, 0.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition hair30 = ozaruhair.addOrReplaceChild("hair30", CubeListBuilder.create(), PartPose.offsetAndRotation(5.2F, -0.45F, 3.4F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r29 = hair30.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(42, 3).addBox(-3.0F, -3.0F, -1.25F, 2.4F, 3.0F, 2.45F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.4F, 0.0F, 0.2182F, 0.0F));

		PartDefinition hair31 = ozaruhair.addOrReplaceChild("hair31", CubeListBuilder.create().texOffs(42, 3).addBox(-2.9F, -3.0F, -1.25F, 2.4F, 3.0F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.15F, -2.7F, 4.15F, -0.2618F, 0.0F, 0.0F));

		PartDefinition hair32 = ozaruhair.addOrReplaceChild("hair32", CubeListBuilder.create(), PartPose.offsetAndRotation(5.05F, -5.2F, 4.15F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r30 = hair32.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(42, 3).addBox(-2.8F, -3.2F, -1.25F, 2.4F, 3.8F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, 0.3F, 0.1745F, 0.0F, 0.0F));

		PartDefinition hair33 = ozaruhair.addOrReplaceChild("hair33", CubeListBuilder.create().texOffs(42, 3).addBox(-2.9F, -4.0F, -2.0F, 5.8F, 4.0F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 3.8F, -0.1745F, 0.0F, 0.0F));

		PartDefinition hair34 = ozaruhair.addOrReplaceChild("hair34", CubeListBuilder.create().texOffs(42, 3).addBox(-2.45F, -3.5F, -2.0F, 4.85F, 3.5F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, 3.8F, 0.2182F, 0.0F, 0.0F));

		PartDefinition hair35 = ozaruhair.addOrReplaceChild("hair35", CubeListBuilder.create().texOffs(43, 4).addBox(-3.0F, -3.9F, -1.45F, 2.0F, 3.9F, 2.35F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.95F, -1.45F, 2.15F, -0.6577F, 0.8293F, -0.0998F));

		PartDefinition cube_r31 = hair35.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(44, 4).addBox(-2.1F, -3.9F, -1.25F, 1.1F, 4.2F, 2.35F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, -2.85F, -1.25F, 0.6109F, 0.0F, 0.0F));

		PartDefinition hair36 = ozaruhair.addOrReplaceChild("hair36", CubeListBuilder.create(), PartPose.offsetAndRotation(4.35F, -7.7F, 3.15F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r32 = hair36.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(42, 3).addBox(-2.8F, -3.0F, -1.25F, 2.5F, 3.6F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, 0.3F, 0.1745F, 0.0F, 0.0F));

		PartDefinition hair37 = ozaruhair.addOrReplaceChild("hair37", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.45F, -7.7F, 3.4F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r33 = hair37.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(42, 3).addBox(-3.4F, -3.1F, -1.25F, 2.4F, 3.7F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, 0.3F, 0.1745F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

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
	}
}