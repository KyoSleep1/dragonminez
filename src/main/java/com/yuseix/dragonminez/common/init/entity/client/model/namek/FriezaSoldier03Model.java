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

public class FriezaSoldier03Model<T extends LivingEntity> extends PlayerModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "friezasoldiers"), "kappa");
	private final ModelPart head;
	private final ModelPart cabello;
	private final ModelPart cabello2;
	private final ModelPart cabello3;
	private final ModelPart cabello4;
	private final ModelPart cabello5;
	private final ModelPart cabello6;
	private final ModelPart cabello7;
	private final ModelPart orejas;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public FriezaSoldier03Model(ModelPart root) {
		super(root, false);
		this.head = root.getChild("head");
		this.cabello = this.head.getChild("cabello");
		this.cabello2 = this.cabello.getChild("cabello2");
		this.cabello3 = this.cabello2.getChild("cabello3");
		this.cabello4 = this.cabello3.getChild("cabello4");
		this.cabello5 = this.cabello4.getChild("cabello5");
		this.cabello6 = this.cabello5.getChild("cabello6");
		this.cabello7 = this.cabello6.getChild("cabello7");
		this.orejas = this.head.getChild("orejas");
		this.body = root.getChild("body");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = PlayerModel.createMesh(CubeDeformation.NONE, false);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cabello = head.addOrReplaceChild("cabello", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = cabello.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -7.7F, -0.9F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r2 = cabello.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.85F, -6.95F, -0.9F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r3 = cabello.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.85F, -6.95F, -2.4F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r4 = cabello.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.35F, -6.2F, -0.65F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r5 = cabello.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.35F, -6.2F, -2.4F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r6 = cabello.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -2.7F, -2.65F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r7 = cabello.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -0.95F, -2.65F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r8 = cabello.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, 0.3F, -2.9F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r9 = cabello.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -0.2F, -3.4F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r10 = cabello.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -2.2F, -3.15F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r11 = cabello.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -4.2F, -2.65F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r12 = cabello.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -5.7F, -3.4F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r13 = cabello.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6F, -6.2F, -3.4F, -0.4349F, 0.0368F, 0.0791F));

		PartDefinition cube_r14 = cabello.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.45F, 0.5F, -3.4F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r15 = cabello.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, -1.5F, -3.4F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r16 = cabello.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.95F, 0.0F, -2.65F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r17 = cabello.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.95F, -2.5F, -2.65F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r18 = cabello.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.95F, -4.25F, -2.65F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r19 = cabello.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -7.6F, -2.4F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r20 = cabello.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.45F, -7.0F, -0.65F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r21 = cabello.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.45F, -7.0F, -2.4F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r22 = cabello.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2F, -6.25F, -0.9F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r23 = cabello.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2F, -6.25F, -2.4F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r24 = cabello.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, -5.5F, -3.4F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r25 = cabello.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.45F, -6.25F, -3.4F, -0.4349F, -0.0368F, -0.0791F));

		PartDefinition cube_r26 = cabello.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.5F, -3.4F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cabello2 = cabello.addOrReplaceChild("cabello2", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.1154F, -8.4449F, 0.6767F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r27 = cabello2.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2846F, 0.911F, 0.3076F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r28 = cabello2.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9654F, 1.661F, 0.3076F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r29 = cabello2.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9654F, 1.661F, -1.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r30 = cabello2.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4654F, 2.411F, 0.5576F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r31 = cabello2.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4654F, 2.411F, -1.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r32 = cabello2.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7154F, 2.911F, -2.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r33 = cabello2.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7154F, 2.411F, -2.1924F, -0.4349F, 0.0368F, 0.0791F));

		PartDefinition cube_r34 = cabello2.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8346F, 4.361F, -1.4424F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r35 = cabello2.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1154F, 1.011F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r36 = cabello2.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3346F, 1.611F, 0.5576F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r37 = cabello2.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3346F, 1.611F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r38 = cabello2.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0846F, 2.361F, 0.3076F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r39 = cabello2.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0846F, 2.361F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r40 = cabello2.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5846F, 3.111F, -2.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r41 = cabello2.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3346F, 2.361F, -2.1924F, -0.4349F, -0.0368F, -0.0791F));

		PartDefinition cube_r42 = cabello2.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1154F, 2.111F, -2.1924F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cabello3 = cabello2.addOrReplaceChild("cabello3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.25F, 2.25F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r43 = cabello3.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2846F, 0.911F, 0.3076F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r44 = cabello3.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9654F, 1.661F, 0.3076F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r45 = cabello3.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9654F, 1.661F, -1.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r46 = cabello3.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4654F, 2.411F, 0.5576F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r47 = cabello3.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4654F, 2.411F, -1.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r48 = cabello3.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7154F, 2.911F, -2.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r49 = cabello3.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7154F, 2.411F, -2.1924F, -0.4349F, 0.0368F, 0.0791F));

		PartDefinition cube_r50 = cabello3.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8346F, 4.361F, -1.4424F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r51 = cabello3.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1154F, 1.011F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r52 = cabello3.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3346F, 1.611F, 0.5576F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r53 = cabello3.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3346F, 1.611F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r54 = cabello3.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0846F, 2.361F, 0.3076F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r55 = cabello3.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0846F, 2.361F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r56 = cabello3.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5846F, 3.111F, -2.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r57 = cabello3.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3346F, 2.361F, -2.1924F, -0.4349F, -0.0368F, -0.0791F));

		PartDefinition cube_r58 = cabello3.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1154F, 2.111F, -2.1924F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cabello4 = cabello3.addOrReplaceChild("cabello4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.75F, 1.5F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r59 = cabello4.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2846F, 0.911F, 0.3076F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r60 = cabello4.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9654F, 1.661F, 0.3076F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r61 = cabello4.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9654F, 1.661F, -1.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r62 = cabello4.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4654F, 2.411F, 0.5576F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r63 = cabello4.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4654F, 2.411F, -1.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r64 = cabello4.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7154F, 2.911F, -2.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r65 = cabello4.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7154F, 2.411F, -2.1924F, -0.4349F, 0.0368F, 0.0791F));

		PartDefinition cube_r66 = cabello4.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8346F, 4.361F, -1.4424F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r67 = cabello4.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1154F, 1.011F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r68 = cabello4.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3346F, 1.611F, 0.5576F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r69 = cabello4.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3346F, 1.611F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r70 = cabello4.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0846F, 2.361F, 0.3076F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r71 = cabello4.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0846F, 2.361F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r72 = cabello4.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5846F, 3.111F, -2.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r73 = cabello4.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3346F, 2.361F, -2.1924F, -0.4349F, -0.0368F, -0.0791F));

		PartDefinition cube_r74 = cabello4.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1154F, 2.111F, -2.1924F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cabello5 = cabello4.addOrReplaceChild("cabello5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r75 = cabello5.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2846F, 0.911F, 0.3076F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r76 = cabello5.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9654F, 1.661F, 0.3076F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r77 = cabello5.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9654F, 1.661F, -1.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r78 = cabello5.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4654F, 2.411F, 0.5576F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r79 = cabello5.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4654F, 2.411F, -1.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r80 = cabello5.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7154F, 2.911F, -2.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r81 = cabello5.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7154F, 2.411F, -2.1924F, -0.4349F, 0.0368F, 0.0791F));

		PartDefinition cube_r82 = cabello5.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8346F, 4.361F, -1.4424F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r83 = cabello5.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1154F, 1.011F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r84 = cabello5.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3346F, 1.611F, 0.5576F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r85 = cabello5.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3346F, 1.611F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r86 = cabello5.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0846F, 2.361F, 0.3076F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r87 = cabello5.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0846F, 2.361F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r88 = cabello5.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5846F, 3.111F, -2.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r89 = cabello5.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3346F, 2.361F, -2.1924F, -0.4349F, -0.0368F, -0.0791F));

		PartDefinition cube_r90 = cabello5.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1154F, 2.111F, -2.1924F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cabello6 = cabello5.addOrReplaceChild("cabello6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.25F, 2.75F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r91 = cabello6.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2846F, 0.911F, 0.3076F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r92 = cabello6.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9654F, 1.661F, 0.3076F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r93 = cabello6.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9654F, 1.661F, -1.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r94 = cabello6.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4654F, 2.411F, 0.5576F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r95 = cabello6.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4654F, 2.411F, -1.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r96 = cabello6.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7154F, 2.911F, -2.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r97 = cabello6.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7154F, 2.411F, -2.1924F, -0.4349F, 0.0368F, 0.0791F));

		PartDefinition cube_r98 = cabello6.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8346F, 4.361F, -1.4424F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r99 = cabello6.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1154F, 1.011F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r100 = cabello6.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3346F, 1.611F, 0.5576F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r101 = cabello6.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3346F, 1.611F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r102 = cabello6.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0846F, 2.361F, 0.3076F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r103 = cabello6.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0846F, 2.361F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r104 = cabello6.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5846F, 3.111F, -2.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r105 = cabello6.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3346F, 2.361F, -2.1924F, -0.4349F, -0.0368F, -0.0791F));

		PartDefinition cube_r106 = cabello6.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1154F, 2.111F, -2.1924F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cabello7 = cabello6.addOrReplaceChild("cabello7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 3.25F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r107 = cabello7.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2846F, 0.911F, 0.3076F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r108 = cabello7.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9654F, 1.661F, 0.3076F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r109 = cabello7.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9654F, 1.661F, -1.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r110 = cabello7.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4654F, 2.411F, 0.5576F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r111 = cabello7.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4654F, 2.411F, -1.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r112 = cabello7.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7154F, 2.911F, -2.1924F, -0.4232F, 0.1096F, 0.2382F));

		PartDefinition cube_r113 = cabello7.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7154F, 2.411F, -2.1924F, -0.4349F, 0.0368F, 0.0791F));

		PartDefinition cube_r114 = cabello7.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8346F, 4.361F, -1.4424F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r115 = cabello7.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1154F, 1.011F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r116 = cabello7.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3346F, 1.611F, 0.5576F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r117 = cabello7.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3346F, 1.611F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r118 = cabello7.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0846F, 2.361F, 0.3076F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r119 = cabello7.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0846F, 2.361F, -1.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r120 = cabello7.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5846F, 3.111F, -2.1924F, -0.4232F, -0.1096F, -0.2382F));

		PartDefinition cube_r121 = cabello7.addOrReplaceChild("cube_r121", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3346F, 2.361F, -2.1924F, -0.4349F, -0.0368F, -0.0791F));

		PartDefinition cube_r122 = cabello7.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(51, 5).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1154F, 2.111F, -2.1924F, -0.3927F, 0.0F, 0.0F));

		PartDefinition orejas = head.addOrReplaceChild("orejas", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r123 = orejas.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(2, 2).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.25F, -3.5F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition cube_r124 = orejas.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(2, 2).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.75F, -3.5F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r125 = orejas.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(35, 1).addBox(0.0F, -2.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -6.0F, -4.0F, -0.2506F, -0.51F, 0.1243F));

		PartDefinition cube_r126 = orejas.addOrReplaceChild("cube_r126", CubeListBuilder.create().texOffs(34, 1).addBox(-1.0F, -2.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -6.0F, -4.0F, -0.2506F, 0.51F, -0.1243F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r127 = body.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(14, 32).addBox(0.0F, -2.0F, -2.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, 10.25F, 0.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r128 = body.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(1, 32).addBox(-1.0F, -2.0F, -2.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.35F, 10.65F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r129 = right_arm.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(42, 37).addBox(-2.0F, -2.0F, -2.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, -0.75F, -0.5F, 0.0F, 0.0F, 0.1309F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition cube_r130 = left_arm.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(25, 33).addBox(-1.0F, -2.0F, -2.5F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.35F, 0.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
	}

	@Override
	protected void setupAttackAnimation(T pLivingEntity, float pAgeInTicks) {
		super.setupAttackAnimation(pLivingEntity, pAgeInTicks);
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