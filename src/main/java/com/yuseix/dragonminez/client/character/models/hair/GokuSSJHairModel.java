package com.yuseix.dragonminez.client.character.models.hair;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;

public class GokuSSJHairModel extends HumanoidModel<AbstractClientPlayer> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DragonMineZ.MOD_ID, "hairs"), "goku");
	private final ModelPart gokussjhair;
	private final ModelPart grupo1;
	private final ModelPart pelo4;
	private final ModelPart pelo5;
	private final ModelPart pelo3;
	private final ModelPart grupo2;
	private final ModelPart pelo2;
	private final ModelPart pelo1;
	private final ModelPart grupo3;
	private final ModelPart pelo6;
	private final ModelPart pelo7;
	private final ModelPart pelo10;
	private final ModelPart pelo11;
	private final ModelPart pelo36;
	private final ModelPart pelo37;
	private final ModelPart pelo38;
	private final ModelPart pelo39;
	private final ModelPart pelo40;
	private final ModelPart grupo4;
	private final ModelPart pelo8;
	private final ModelPart pelo9;
	private final ModelPart grupo5;
	private final ModelPart pelo14;
	private final ModelPart pelo13;
	private final ModelPart pelo12;
	private final ModelPart pelo18;
	private final ModelPart pelo19;
	private final ModelPart pelo20;
	private final ModelPart grupo6;
	private final ModelPart grupo5volteado;
	private final ModelPart pelo15;
	private final ModelPart pelo16;
	private final ModelPart pelo17;
	private final ModelPart grupo5volteado2;
	private final ModelPart pelo21;
	private final ModelPart pelo22;
	private final ModelPart pelo23;
	private final ModelPart grupo7;
	private final ModelPart grupo5volteado3;
	private final ModelPart pelo24;
	private final ModelPart pelo25;
	private final ModelPart pelo26;
	private final ModelPart grupo5volteado4;
	private final ModelPart pelo27;
	private final ModelPart pelo28;
	private final ModelPart pelo29;
	private final ModelPart grupo5volteado5;
	private final ModelPart pelo30;
	private final ModelPart pelo31;
	private final ModelPart pelo32;
	private final ModelPart grupo5volteado6;
	private final ModelPart pelo33;
	private final ModelPart pelo34;
	private final ModelPart pelo35;

	public GokuSSJHairModel(ModelPart root) {
        super(root);
        this.gokussjhair = root.getChild("gokussjhair");
		this.grupo1 = this.gokussjhair.getChild("grupo1");
		this.pelo4 = this.grupo1.getChild("pelo4");
		this.pelo5 = this.grupo1.getChild("pelo5");
		this.pelo3 = this.grupo1.getChild("pelo3");
		this.grupo2 = this.gokussjhair.getChild("grupo2");
		this.pelo2 = this.grupo2.getChild("pelo2");
		this.pelo1 = this.grupo2.getChild("pelo1");
		this.grupo3 = this.gokussjhair.getChild("grupo3");
		this.pelo6 = this.grupo3.getChild("pelo6");
		this.pelo7 = this.grupo3.getChild("pelo7");
		this.pelo10 = this.grupo3.getChild("pelo10");
		this.pelo11 = this.grupo3.getChild("pelo11");
		this.pelo36 = this.grupo3.getChild("pelo36");
		this.pelo37 = this.grupo3.getChild("pelo37");
		this.pelo38 = this.grupo3.getChild("pelo38");
		this.pelo39 = this.grupo3.getChild("pelo39");
		this.pelo40 = this.grupo3.getChild("pelo40");
		this.grupo4 = this.gokussjhair.getChild("grupo4");
		this.pelo8 = this.grupo4.getChild("pelo8");
		this.pelo9 = this.grupo4.getChild("pelo9");
		this.grupo5 = this.gokussjhair.getChild("grupo5");
		this.pelo14 = this.grupo5.getChild("pelo14");
		this.pelo13 = this.grupo5.getChild("pelo13");
		this.pelo12 = this.grupo5.getChild("pelo12");
		this.pelo18 = this.grupo5.getChild("pelo18");
		this.pelo19 = this.grupo5.getChild("pelo19");
		this.pelo20 = this.grupo5.getChild("pelo20");
		this.grupo6 = this.gokussjhair.getChild("grupo6");
		this.grupo5volteado = this.grupo6.getChild("grupo5volteado");
		this.pelo15 = this.grupo5volteado.getChild("pelo15");
		this.pelo16 = this.grupo5volteado.getChild("pelo16");
		this.pelo17 = this.grupo5volteado.getChild("pelo17");
		this.grupo5volteado2 = this.grupo6.getChild("grupo5volteado2");
		this.pelo21 = this.grupo5volteado2.getChild("pelo21");
		this.pelo22 = this.grupo5volteado2.getChild("pelo22");
		this.pelo23 = this.grupo5volteado2.getChild("pelo23");
		this.grupo7 = this.gokussjhair.getChild("grupo7");
		this.grupo5volteado3 = this.grupo7.getChild("grupo5volteado3");
		this.pelo24 = this.grupo5volteado3.getChild("pelo24");
		this.pelo25 = this.grupo5volteado3.getChild("pelo25");
		this.pelo26 = this.grupo5volteado3.getChild("pelo26");
		this.grupo5volteado4 = this.grupo7.getChild("grupo5volteado4");
		this.pelo27 = this.grupo5volteado4.getChild("pelo27");
		this.pelo28 = this.grupo5volteado4.getChild("pelo28");
		this.pelo29 = this.grupo5volteado4.getChild("pelo29");
		this.grupo5volteado5 = this.grupo7.getChild("grupo5volteado5");
		this.pelo30 = this.grupo5volteado5.getChild("pelo30");
		this.pelo31 = this.grupo5volteado5.getChild("pelo31");
		this.pelo32 = this.grupo5volteado5.getChild("pelo32");
		this.grupo5volteado6 = this.grupo7.getChild("grupo5volteado6");
		this.pelo33 = this.grupo5volteado6.getChild("pelo33");
		this.pelo34 = this.grupo5volteado6.getChild("pelo34");
		this.pelo35 = this.grupo5volteado6.getChild("pelo35");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE,0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition gokussjhair = partdefinition.addOrReplaceChild("gokussjhair", CubeListBuilder.create().texOffs(12, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition grupo1 = gokussjhair.addOrReplaceChild("grupo1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition pelo4 = grupo1.addOrReplaceChild("pelo4", CubeListBuilder.create(), PartPose.offsetAndRotation(3.7736F, -7.6795F, -3.711F, -0.5672F, -1.1345F, -0.3491F));

		PartDefinition cube_r1 = pelo4.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -0.5F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.0236F, 0.1795F, -0.289F, -0.0436F, 0.0F, 0.1309F));

		PartDefinition cube_r2 = pelo4.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0236F, 0.1795F, -0.289F, -0.5236F, 0.0F, 0.1309F));

		PartDefinition pelo5 = grupo1.addOrReplaceChild("pelo5", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.1994F, -7.4295F, -3.3111F, -0.2348F, 1.3735F, 0.1576F));

		PartDefinition cube_r3 = pelo5.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -0.5F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.0236F, 0.1795F, -0.289F, -0.0436F, 0.0F, 0.1309F));

		PartDefinition cube_r4 = pelo5.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0236F, 0.1795F, -0.289F, -0.5236F, 0.0F, 0.1309F));

		PartDefinition pelo3 = grupo1.addOrReplaceChild("pelo3", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0236F, -7.6795F, -4.461F, -0.5672F, -0.8727F, 0.0F));

		PartDefinition cube_r5 = pelo3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -0.5F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.0236F, 0.1795F, -0.289F, -0.0436F, 0.0F, 0.1309F));

		PartDefinition cube_r6 = pelo3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0236F, 0.1795F, -0.289F, -0.5236F, 0.0F, 0.1309F));

		PartDefinition grupo2 = gokussjhair.addOrReplaceChild("grupo2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition pelo2 = grupo2.addOrReplaceChild("pelo2", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.9494F, -7.1795F, -4.3111F, -0.3818F, 0.2413F, 0.0317F));

		PartDefinition cube_r7 = pelo2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -0.5F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.0236F, 0.1795F, -0.289F, -0.0436F, 0.0F, 0.1309F));

		PartDefinition cube_r8 = pelo2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0236F, 0.1795F, -0.289F, -0.5236F, 0.0F, 0.1309F));

		PartDefinition pelo1 = grupo2.addOrReplaceChild("pelo1", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.9764F, -7.4295F, -4.711F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r9 = pelo1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -0.5F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.0236F, 0.1795F, -0.289F, -0.0436F, 0.0F, 0.1309F));

		PartDefinition cube_r10 = pelo1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0236F, 0.1795F, -0.289F, -0.5236F, 0.0F, 0.1309F));

		PartDefinition grupo3 = gokussjhair.addOrReplaceChild("grupo3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition pelo6 = grupo3.addOrReplaceChild("pelo6", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -7.0043F, -2.8153F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r11 = pelo6.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo7 = grupo3.addOrReplaceChild("pelo7", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.046F, -7.0978F, -2.8565F, -0.2182F, 0.0F, -0.1309F));

		PartDefinition cube_r12 = pelo7.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -5.3814F, -2.7717F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo10 = grupo3.addOrReplaceChild("pelo10", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -9.5173F, -1.1997F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r13 = pelo10.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo11 = grupo3.addOrReplaceChild("pelo11", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.25F, -7.7673F, -0.9497F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r14 = pelo11.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo36 = grupo3.addOrReplaceChild("pelo36", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -8.7673F, 0.5503F, -0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r15 = pelo36.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo37 = grupo3.addOrReplaceChild("pelo37", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -8.7673F, 0.0503F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r16 = pelo37.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo38 = grupo3.addOrReplaceChild("pelo38", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -8.5173F, 0.3003F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r17 = pelo38.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo39 = grupo3.addOrReplaceChild("pelo39", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -8.5173F, 0.8003F, -0.9163F, 0.0F, 0.0F));

		PartDefinition cube_r18 = pelo39.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo40 = grupo3.addOrReplaceChild("pelo40", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -8.5173F, -0.1997F, -0.9163F, 0.0F, 0.0F));

		PartDefinition cube_r19 = pelo40.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition grupo4 = gokussjhair.addOrReplaceChild("grupo4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition pelo8 = grupo4.addOrReplaceChild("pelo8", CubeListBuilder.create().texOffs(49, 37).addBox(-0.75F, -3.2314F, -1.4217F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5618F, -6.4107F, -2.298F, -0.1309F, 0.5236F, 0.0F));

		PartDefinition cube_r20 = pelo8.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(49, 37).addBox(-0.75F, -3.8814F, -2.5217F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo9 = grupo4.addOrReplaceChild("pelo9", CubeListBuilder.create().texOffs(49, 37).addBox(-0.75F, -3.2314F, -1.4217F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8812F, -6.6523F, -2.6404F, -0.1309F, -0.5672F, 0.0F));

		PartDefinition cube_r21 = pelo9.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(49, 37).addBox(-0.75F, -3.8814F, -2.5217F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition grupo5 = gokussjhair.addOrReplaceChild("grupo5", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition pelo14 = grupo5.addOrReplaceChild("pelo14", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -5.2543F, 2.4347F, -0.2182F, 1.5429F, -0.5271F));

		PartDefinition cube_r22 = pelo14.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo13 = grupo5.addOrReplaceChild("pelo13", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -4.0043F, 2.4347F, -0.2182F, 1.5429F, -1.0507F));

		PartDefinition cube_r23 = pelo13.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo12 = grupo5.addOrReplaceChild("pelo12", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -1.0043F, 2.4347F, 1.7829F, 1.5469F, 0.9501F));

		PartDefinition cube_r24 = pelo12.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo18 = grupo5.addOrReplaceChild("pelo18", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -7.0043F, 0.1847F, -0.2182F, 1.5429F, -0.3525F));

		PartDefinition cube_r25 = pelo18.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo19 = grupo5.addOrReplaceChild("pelo19", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -5.7543F, 0.1847F, -0.2182F, 1.5429F, -0.8761F));

		PartDefinition cube_r26 = pelo19.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo20 = grupo5.addOrReplaceChild("pelo20", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -2.7543F, 0.1847F, 1.7829F, 1.5469F, 1.1246F));

		PartDefinition cube_r27 = pelo20.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition grupo6 = gokussjhair.addOrReplaceChild("grupo6", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition grupo5volteado = grupo6.addOrReplaceChild("grupo5volteado", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, -3.1416F, -0.0436F, 3.1416F));

		PartDefinition pelo15 = grupo5volteado.addOrReplaceChild("pelo15", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -5.2543F, 2.4347F, -0.2182F, 1.5429F, -0.5271F));

		PartDefinition cube_r28 = pelo15.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo16 = grupo5volteado.addOrReplaceChild("pelo16", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -4.0043F, 2.4347F, -0.2182F, 1.5429F, -1.0507F));

		PartDefinition cube_r29 = pelo16.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo17 = grupo5volteado.addOrReplaceChild("pelo17", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -1.0043F, 2.4347F, 1.7829F, 1.5469F, 0.9501F));

		PartDefinition cube_r30 = pelo17.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition grupo5volteado2 = grupo6.addOrReplaceChild("grupo5volteado2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.5F, 1.75F, -3.1416F, -0.0436F, 3.0107F));

		PartDefinition pelo21 = grupo5volteado2.addOrReplaceChild("pelo21", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -5.2543F, 2.4347F, -0.2182F, 1.5429F, -0.5271F));

		PartDefinition cube_r31 = pelo21.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo22 = grupo5volteado2.addOrReplaceChild("pelo22", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -4.0043F, 2.4347F, -0.2182F, 1.5429F, -1.0507F));

		PartDefinition cube_r32 = pelo22.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo23 = grupo5volteado2.addOrReplaceChild("pelo23", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -1.0043F, 2.4347F, 1.7829F, 1.5469F, 0.9501F));

		PartDefinition cube_r33 = pelo23.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition grupo7 = gokussjhair.addOrReplaceChild("grupo7", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition grupo5volteado3 = grupo7.addOrReplaceChild("grupo5volteado3", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.4F, -0.45F, 1.0F, 3.1416F, 1.0036F, 3.1416F));

		PartDefinition pelo24 = grupo5volteado3.addOrReplaceChild("pelo24", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -5.2543F, 2.4347F, -0.2182F, 1.5429F, -0.5271F));

		PartDefinition cube_r34 = pelo24.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo25 = grupo5volteado3.addOrReplaceChild("pelo25", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -4.0043F, 2.4347F, -0.2182F, 1.5429F, -1.0507F));

		PartDefinition cube_r35 = pelo25.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo26 = grupo5volteado3.addOrReplaceChild("pelo26", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -1.0043F, 2.4347F, 1.7829F, 1.5469F, 0.9501F));

		PartDefinition cube_r36 = pelo26.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition grupo5volteado4 = grupo7.addOrReplaceChild("grupo5volteado4", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.15F, -0.2F, -0.75F, 0.0F, 0.8727F, 0.0F));

		PartDefinition pelo27 = grupo5volteado4.addOrReplaceChild("pelo27", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -5.2543F, 2.4347F, -0.2182F, 1.5429F, -0.5271F));

		PartDefinition cube_r37 = pelo27.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo28 = grupo5volteado4.addOrReplaceChild("pelo28", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -4.0043F, 2.4347F, -0.2182F, 1.5429F, -1.0507F));

		PartDefinition cube_r38 = pelo28.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo29 = grupo5volteado4.addOrReplaceChild("pelo29", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -1.0043F, 2.4347F, 1.7829F, 1.5469F, 0.9501F));

		PartDefinition cube_r39 = pelo29.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition grupo5volteado5 = grupo7.addOrReplaceChild("grupo5volteado5", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.9F, -0.45F, 0.25F, -2.3543F, 1.4475F, -2.3581F));

		PartDefinition pelo30 = grupo5volteado5.addOrReplaceChild("pelo30", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -5.2543F, 2.4347F, -0.2182F, 1.5429F, -0.5271F));

		PartDefinition cube_r40 = pelo30.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo31 = grupo5volteado5.addOrReplaceChild("pelo31", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -4.0043F, 2.4347F, -0.2182F, 1.5429F, -1.0507F));

		PartDefinition cube_r41 = pelo31.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo32 = grupo5volteado5.addOrReplaceChild("pelo32", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -1.0043F, 2.4347F, 1.7829F, 1.5469F, 0.9501F));

		PartDefinition cube_r42 = pelo32.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition grupo5volteado6 = grupo7.addOrReplaceChild("grupo5volteado6", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.9F, -0.45F, 0.25F, -2.6772F, 1.4733F, -2.6791F));

		PartDefinition pelo33 = grupo5volteado6.addOrReplaceChild("pelo33", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -5.2543F, 2.4347F, -0.2182F, 1.5429F, -0.5271F));

		PartDefinition cube_r43 = pelo33.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo34 = grupo5volteado6.addOrReplaceChild("pelo34", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -4.0043F, 2.4347F, -0.2182F, 1.5429F, -1.0507F));

		PartDefinition cube_r44 = pelo34.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition pelo35 = grupo5volteado6.addOrReplaceChild("pelo35", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -3.7314F, -1.4217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -1.0043F, 2.4347F, 1.7829F, 1.5469F, 0.9501F));

		PartDefinition cube_r45 = pelo35.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, -4.8814F, -2.5217F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		float random = (float) Math.random();

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

			var auraOn = cap.isAuraOn();
			var turboOn = cap.isTurboOn();
			var transfOn = cap.isTransforming();
			var velocidad = 0.4f;

			if(auraOn || turboOn){
				velocidad = transfOn ? 0.7f : 0.3f;
				this.grupo4.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.01F);
				this.grupo5.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.01F);

				this.grupo6.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.01F);
				this.pelo7.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.01F);

				this.grupo1.xRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.01F);
				this.grupo2.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.01F);

				this.pelo6.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.01F);

			} else {
				velocidad = 0.03f;
				this.grupo4.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.01F);
				this.grupo5.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.01F);

				this.grupo6.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.01F);
				this.pelo7.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.01F);

				this.grupo1.xRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.01F);
				this.grupo2.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.01F);

				this.pelo6.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.01F);

			}

		});
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		gokussjhair.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}