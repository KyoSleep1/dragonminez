package com.yuseix.dragonminez.client.character.models.hair;


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

public class TrunksHairModel extends HumanoidModel<AbstractClientPlayer> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DragonMineZ.MOD_ID, "hairs"), "trunks");
	private final ModelPart Head;
	private final ModelPart der;
	private final ModelPart arribader;
	private final ModelPart bone;
	private final ModelPart bone3;
	private final ModelPart bone5;
	private final ModelPart bone7;
	private final ModelPart bone9;
	private final ModelPart bone12;
	private final ModelPart bone14;
	private final ModelPart bone16;
	private final ModelPart bone17;
	private final ModelPart izq;
	private final ModelPart arribaizq;
	private final ModelPart bone2;
	private final ModelPart bone4;
	private final ModelPart bone6;
	private final ModelPart bone8;
	private final ModelPart bone10;
	private final ModelPart bone11;
	private final ModelPart bone13;
	private final ModelPart bone15;
	private final ModelPart bone18;

	public TrunksHairModel(ModelPart root) {
		super(root);
		this.Head = root.getChild("Head");
		this.der = this.Head.getChild("der");
		this.arribader = this.der.getChild("arribader");
		this.bone = this.arribader.getChild("bone");
		this.bone3 = this.arribader.getChild("bone3");
		this.bone5 = this.arribader.getChild("bone5");
		this.bone7 = this.arribader.getChild("bone7");
		this.bone9 = this.arribader.getChild("bone9");
		this.bone12 = this.arribader.getChild("bone12");
		this.bone14 = this.arribader.getChild("bone14");
		this.bone16 = this.arribader.getChild("bone16");
		this.bone17 = this.arribader.getChild("bone17");
		this.izq = this.Head.getChild("izq");
		this.arribaizq = this.izq.getChild("arribaizq");
		this.bone2 = this.arribaizq.getChild("bone2");
		this.bone4 = this.arribaizq.getChild("bone4");
		this.bone6 = this.arribaizq.getChild("bone6");
		this.bone8 = this.arribaizq.getChild("bone8");
		this.bone10 = this.arribaizq.getChild("bone10");
		this.bone11 = this.arribaizq.getChild("bone11");
		this.bone13 = this.arribaizq.getChild("bone13");
		this.bone15 = this.arribaizq.getChild("bone15");
		this.bone18 = this.arribaizq.getChild("bone18");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE,0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(40, 47).addBox(-4.0F, -6.95F, 3.9F, 8.0F, 5.0F, 0.2F, new CubeDeformation(0.1F))
		.texOffs(30, 11).addBox(-4.1F, -3.95F, -3.6F, 0.2F, 2.0F, 7.6F, new CubeDeformation(0.1F))
		.texOffs(30, 25).addBox(3.9F, -3.95F, -3.6F, 0.2F, 2.0F, 7.6F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition der = Head.addOrReplaceChild("der", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition arribader = der.addOrReplaceChild("arribader", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition bone = arribader.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-3.1347F, -5.3693F, -3.35F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -3.9F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 6).addBox(-0.55F, -1.5F, -1.0F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r3 = bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 17).addBox(-1.15F, 0.25F, -1.01F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r4 = bone.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(1, 27).addBox(-1.75F, 1.75F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone3 = arribader.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.3709F, -4.7434F, -5.518F, -0.2182F, 0.3491F, 0.0F));

		PartDefinition cube_r5 = bone3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(24, 44).addBox(-0.6842F, -0.6012F, -3.7886F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.671F, -1.7798F, -0.0662F, 1.4776F, 0.3189F, 0.2333F));

		PartDefinition cube_r6 = bone3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(24, 37).addBox(-0.6842F, 0.9957F, -1.659F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.671F, -1.7798F, -0.0662F, 0.6485F, 0.3189F, 0.2333F));

		PartDefinition bone5 = arribader.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.1347F, -5.3693F, -1.35F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r7 = bone5.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9546F, -3.9521F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r8 = bone5.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2728F, -1.3852F, -1.0F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r9 = bone5.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0598F, 0.5361F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r10 = bone5.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(1, 27).addBox(-1.75F, 2.05F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone7 = arribader.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(-3.1347F, -5.3693F, 0.65F));

		PartDefinition cube_r11 = bone7.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -3.9F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r12 = bone7.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 6).addBox(-0.55F, -1.5F, -1.0F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r13 = bone7.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 17).addBox(-1.15F, 0.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r14 = bone7.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(1, 27).addBox(-1.75F, 1.75F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone9 = arribader.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.1347F, -5.3693F, 2.65F, 0.0F, 0.0F, 0.1309F));

		PartDefinition cube_r15 = bone9.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9571F, -3.9649F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r16 = bone9.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2681F, -1.3974F, -1.0F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r17 = bone9.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0474F, 0.5319F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r18 = bone9.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(1, 27).addBox(-1.7369F, 2.0497F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone12 = arribader.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.1347F, -5.3693F, 3.45F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r19 = bone12.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9602F, -3.2776F, -1.0F, 2.0F, 2.3F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r20 = bone12.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2639F, -1.4098F, -1.0F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r21 = bone12.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0352F, 0.5272F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r22 = bone12.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(1, 27).addBox(-1.7239F, 2.0489F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone14 = arribader.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.1347F, -5.3693F, 4.45F, 0.0F, 0.6109F, 0.1745F));

		PartDefinition cube_r23 = bone14.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9594F, -3.9683F, -0.9701F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r24 = bone14.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2682F, -1.4014F, -0.9701F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r25 = bone14.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0444F, 0.5292F, -0.9701F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r26 = bone14.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(1, 27).addBox(-1.7332F, 2.048F, -0.9701F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone16 = arribader.addOrReplaceChild("bone16", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.6347F, -5.3693F, 5.2F, 0.0F, 0.9163F, 0.1745F));

		PartDefinition cube_r27 = bone16.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9584F, -3.2573F, -0.9587F, 2.0F, 2.3F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r28 = bone16.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2733F, -1.3917F, -0.9587F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r29 = bone16.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0551F, 0.5316F, -0.9587F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r30 = bone16.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(1, 27).addBox(-1.7442F, 2.0471F, -0.9587F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone17 = arribader.addOrReplaceChild("bone17", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.8847F, -5.8693F, -4.6F, -0.0718F, -0.3864F, 0.1886F));

		PartDefinition cube_r31 = bone17.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9602F, -3.9776F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r32 = bone17.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2639F, -1.4098F, -1.0F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r33 = bone17.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0352F, 0.5272F, -1.01F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r34 = bone17.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(1, 27).addBox(-1.7239F, 2.0489F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition izq = Head.addOrReplaceChild("izq", CubeListBuilder.create(), PartPose.offset(0.0F, -7.5F, 0.0F));

		PartDefinition arribaizq = izq.addOrReplaceChild("arribaizq", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 7.5F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition bone2 = arribaizq.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0653F, -8.3693F, -3.35F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r35 = bone2.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9511F, -3.9261F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r36 = bone2.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2839F, -1.3615F, -1.01F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r37 = bone2.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0851F, 0.5429F, -0.99F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r38 = bone2.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(1, 27).addBox(-1.7761F, 2.0489F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone4 = arribaizq.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.4594F, -7.8394F, -5.2594F, 0.3593F, -0.7574F, -0.7589F));

		PartDefinition cube_r39 = bone4.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(14, 44).addBox(-0.8316F, -0.65F, -3.7887F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.671F, -1.7798F, -0.0662F, 1.4776F, 0.3189F, 0.2333F));

		PartDefinition cube_r40 = bone4.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(13, 36).addBox(-0.8316F, 0.9628F, -1.6951F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.671F, -1.7798F, -0.0662F, 0.6485F, 0.3189F, 0.2333F));

		PartDefinition bone6 = arribaizq.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0653F, -8.3693F, -1.35F, 3.1416F, 0.0F, 3.0107F));

		PartDefinition cube_r41 = bone6.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9571F, -3.9649F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r42 = bone6.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2681F, -1.3974F, -1.0F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r43 = bone6.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0474F, 0.5319F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r44 = bone6.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(1, 27).addBox(-1.7369F, 2.0497F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone8 = arribaizq.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0653F, -8.3693F, 0.65F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r45 = bone8.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9511F, -3.9261F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r46 = bone8.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2839F, -1.3615F, -1.0F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r47 = bone8.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0851F, 0.5429F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r48 = bone8.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(1, 27).addBox(-1.7761F, 2.0489F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone10 = arribaizq.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0653F, -8.3693F, 2.65F, 3.1416F, 0.0F, 3.0543F));

		PartDefinition cube_r49 = bone10.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9546F, -3.9521F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r50 = bone10.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2728F, -1.3852F, -1.0F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r51 = bone10.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0598F, 0.5361F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r52 = bone10.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(1, 27).addBox(-1.75F, 2.05F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone11 = arribaizq.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0653F, -8.3693F, 3.4F, 3.1416F, 0.0F, 2.9671F));

		PartDefinition cube_r53 = bone11.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9602F, -3.2776F, -1.0F, 2.0F, 2.3F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r54 = bone11.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2639F, -1.4098F, -1.0F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r55 = bone11.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0352F, 0.5272F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r56 = bone11.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(1, 27).addBox(-1.7239F, 2.0489F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone13 = arribaizq.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offsetAndRotation(2.2653F, -8.3693F, 4.6F, 3.1416F, 0.5672F, 3.0543F));

		PartDefinition cube_r57 = bone13.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9542F, -3.248F, -1.014F, 2.0F, 2.3F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r58 = bone13.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2747F, -1.3816F, -1.014F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r59 = bone13.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0638F, 0.537F, -1.014F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r60 = bone13.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(1, 27).addBox(-1.7541F, 2.0496F, -1.014F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone15 = arribaizq.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offsetAndRotation(0.7653F, -8.3693F, 5.1F, -3.0483F, 1.1324F, -3.1259F));

		PartDefinition cube_r61 = bone15.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9519F, -3.2241F, -1.0237F, 2.0F, 2.3F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r62 = bone15.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2857F, -1.3601F, -1.0237F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r63 = bone15.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0872F, 0.5424F, -1.0237F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r64 = bone15.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(1, 27).addBox(-1.7781F, 2.0477F, -1.0237F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone18 = arribaizq.addOrReplaceChild("bone18", CubeListBuilder.create(), PartPose.offsetAndRotation(2.6653F, -8.6193F, -4.6F, -3.0963F, -0.478F, 3.0433F));

		PartDefinition cube_r65 = bone18.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9546F, -3.9521F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition cube_r66 = bone18.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(0, 6).addBox(-0.2728F, -1.3852F, -1.01F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r67 = bone18.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0598F, 0.5361F, -0.99F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r68 = bone18.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(1, 27).addBox(-1.75F, 2.05F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		//super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
		float random = (float) Math.random();


		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

			var auraOn = cap.getBoolean("aura");
			var turboOn = cap.getBoolean("turbo");

			if(auraOn || turboOn){
				var cargaVelocidad = 0.4f;
				this.der.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.03F);
				this.izq.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*-0.03F);


			} else {
				var velocidad = 0.04f;

				this.der.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.03F);
				this.izq.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.03F);

			}

		});
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}