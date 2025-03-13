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

public class GohanTeenHairModel extends HumanoidModel<AbstractClientPlayer> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "hairs"), "gohanteen");
	private final ModelPart Head;
	private final ModelPart inicio;
	private final ModelPart izqini;
	private final ModelPart bone52;
	private final ModelPart bone7;
	private final ModelPart bone;
	private final ModelPart derini;
	private final ModelPart bone3;
	private final ModelPart bone9;
	private final ModelPart bone8;
	private final ModelPart bone4;
	private final ModelPart mid;
	private final ModelPart bone6;
	private final ModelPart bone10;
	private final ModelPart bone2;
	private final ModelPart bone12;
	private final ModelPart bone11;
	private final ModelPart bone50;
	private final ModelPart bone51;
	private final ModelPart bajo;
	private final ModelPart bone5;
	private final ModelPart bone13;
	private final ModelPart bone14;
	private final ModelPart bone15;
	private final ModelPart bone16;
	private final ModelPart bone17;
	private final ModelPart bone18;
	private final ModelPart bone19;
	private final ModelPart bone20;
	private final ModelPart bone21;
	private final ModelPart bone22;
	private final ModelPart bone23;
	private final ModelPart bone24;
	private final ModelPart bone25;
	private final ModelPart bone26;
	private final ModelPart bajoizq;
	private final ModelPart bone27;
	private final ModelPart bone28;
	private final ModelPart bone29;
	private final ModelPart bone30;
	private final ModelPart bone31;
	private final ModelPart bone32;
	private final ModelPart bone33;
	private final ModelPart bone34;
	private final ModelPart bone35;
	private final ModelPart bone36;
	private final ModelPart bone37;
	private final ModelPart bone38;
	private final ModelPart bone39;
	private final ModelPart bone40;
	private final ModelPart bone41;
	private final ModelPart bajocentro;
	private final ModelPart bone42;
	private final ModelPart bone43;
	private final ModelPart bone44;
	private final ModelPart bone45;
	private final ModelPart bone46;
	private final ModelPart bone47;
	private final ModelPart bone48;
	private final ModelPart bone49;

	public GohanTeenHairModel(ModelPart root) {
		super(root);
		this.Head = root.getChild("Head");
		this.inicio = this.Head.getChild("inicio");
		this.izqini = this.inicio.getChild("izqini");
		this.bone52 = this.izqini.getChild("bone52");
		this.bone7 = this.izqini.getChild("bone7");
		this.bone = this.izqini.getChild("bone");
		this.derini = this.inicio.getChild("derini");
		this.bone3 = this.derini.getChild("bone3");
		this.bone9 = this.derini.getChild("bone9");
		this.bone8 = this.derini.getChild("bone8");
		this.bone4 = this.derini.getChild("bone4");
		this.mid = this.Head.getChild("mid");
		this.bone6 = this.mid.getChild("bone6");
		this.bone10 = this.mid.getChild("bone10");
		this.bone2 = this.mid.getChild("bone2");
		this.bone12 = this.mid.getChild("bone12");
		this.bone11 = this.mid.getChild("bone11");
		this.bone50 = this.mid.getChild("bone50");
		this.bone51 = this.mid.getChild("bone51");
		this.bajo = this.Head.getChild("bajo");
		this.bone5 = this.bajo.getChild("bone5");
		this.bone13 = this.bajo.getChild("bone13");
		this.bone14 = this.bajo.getChild("bone14");
		this.bone15 = this.bajo.getChild("bone15");
		this.bone16 = this.bajo.getChild("bone16");
		this.bone17 = this.bajo.getChild("bone17");
		this.bone18 = this.bajo.getChild("bone18");
		this.bone19 = this.bajo.getChild("bone19");
		this.bone20 = this.bajo.getChild("bone20");
		this.bone21 = this.bajo.getChild("bone21");
		this.bone22 = this.bajo.getChild("bone22");
		this.bone23 = this.bajo.getChild("bone23");
		this.bone24 = this.bajo.getChild("bone24");
		this.bone25 = this.bajo.getChild("bone25");
		this.bone26 = this.bajo.getChild("bone26");
		this.bajoizq = this.Head.getChild("bajoizq");
		this.bone27 = this.bajoizq.getChild("bone27");
		this.bone28 = this.bajoizq.getChild("bone28");
		this.bone29 = this.bajoizq.getChild("bone29");
		this.bone30 = this.bajoizq.getChild("bone30");
		this.bone31 = this.bajoizq.getChild("bone31");
		this.bone32 = this.bajoizq.getChild("bone32");
		this.bone33 = this.bajoizq.getChild("bone33");
		this.bone34 = this.bajoizq.getChild("bone34");
		this.bone35 = this.bajoizq.getChild("bone35");
		this.bone36 = this.bajoizq.getChild("bone36");
		this.bone37 = this.bajoizq.getChild("bone37");
		this.bone38 = this.bajoizq.getChild("bone38");
		this.bone39 = this.bajoizq.getChild("bone39");
		this.bone40 = this.bajoizq.getChild("bone40");
		this.bone41 = this.bajoizq.getChild("bone41");
		this.bajocentro = this.Head.getChild("bajocentro");
		this.bone42 = this.bajocentro.getChild("bone42");
		this.bone43 = this.bajocentro.getChild("bone43");
		this.bone44 = this.bajocentro.getChild("bone44");
		this.bone45 = this.bajocentro.getChild("bone45");
		this.bone46 = this.bajocentro.getChild("bone46");
		this.bone47 = this.bajocentro.getChild("bone47");
		this.bone48 = this.bajocentro.getChild("bone48");
		this.bone49 = this.bajocentro.getChild("bone49");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE,0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition inicio = Head.addOrReplaceChild("inicio", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition izqini = inicio.addOrReplaceChild("izqini", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition bone52 = izqini.addOrReplaceChild("bone52", CubeListBuilder.create(), PartPose.offsetAndRotation(3.6086F, 0.5545F, -3.4614F, -0.3217F, -0.2525F, -1.0224F));

		PartDefinition cube_r1 = bone52.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(30, 28).addBox(-1.0F, -0.25F, -1.0F, 2.0F, 2.1F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, 0.0477F, 0.0376F, 0.215F));

		PartDefinition cube_r2 = bone52.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(30, 24).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, -0.1705F, 0.0376F, 0.215F));

		PartDefinition bone7 = izqini.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offsetAndRotation(3.197F, 0.7281F, -4.0096F, -0.3986F, -0.0803F, -0.5673F));

		PartDefinition cube_r3 = bone7.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(30, 28).addBox(-1.0F, -0.25F, -1.0F, 2.0F, 2.1F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, 0.0477F, 0.0376F, 0.215F));

		PartDefinition cube_r4 = bone7.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(30, 24).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, -0.1705F, 0.0376F, 0.215F));

		PartDefinition bone = izqini.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.7441F, 0.7463F, -3.8111F, -0.4792F, -0.0189F, -0.0852F));

		PartDefinition cube_r5 = bone.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -0.25F, -1.0F, 2.0F, 2.4F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, 0.0477F, 0.0376F, 0.215F));

		PartDefinition cube_r6 = bone.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(22, 26).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, -0.1705F, 0.0376F, 0.215F));

		PartDefinition derini = inicio.addOrReplaceChild("derini", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition bone3 = derini.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.1629F, -1.4667F, -2.9578F, -1.9727F, 0.8721F, 0.1395F));

		PartDefinition cube_r7 = bone3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(32, 10).addBox(-1.0F, -0.45F, -0.9F, 2.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, 0.2659F, 0.0376F, 0.215F));

		PartDefinition cube_r8 = bone3.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(14, 26).addBox(-1.0F, -4.0F, -1.0F, 2.5F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, -0.1705F, 0.0376F, 0.215F));

		PartDefinition bone9 = derini.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.2264F, 0.1111F, -2.5316F, -2.7262F, 1.2581F, -1.7091F));

		PartDefinition cube_r9 = bone9.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(8, 32).addBox(-1.0F, -0.5F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, 0.2659F, 0.0376F, 0.215F));

		PartDefinition cube_r10 = bone9.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(8, 32).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, -0.1705F, 0.0376F, 0.215F));

		PartDefinition bone8 = derini.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.4229F, 0.6491F, -3.7896F, -0.6589F, 0.79F, -0.0895F));

		PartDefinition cube_r11 = bone8.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(32, 6).addBox(-1.0F, -0.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, 0.0477F, 0.0376F, 0.215F));

		PartDefinition cube_r12 = bone8.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, -0.1705F, 0.0376F, 0.215F));

		PartDefinition bone4 = derini.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.4401F, 0.9039F, -3.8461F, -0.4345F, 0.0283F, 0.1278F));

		PartDefinition cube_r13 = bone4.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(22, 30).addBox(-1.0F, -0.25F, -1.0F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, 0.0477F, 0.0376F, 0.215F));

		PartDefinition cube_r14 = bone4.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(30, 20).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, -0.1705F, 0.0376F, 0.215F));

		PartDefinition mid = Head.addOrReplaceChild("mid", CubeListBuilder.create().texOffs(0, 0).addBox(-3.7F, -8.2F, -0.85F, 7.9F, 1.3F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone6 = mid.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5135F, -7.7469F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r15 = bone6.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 22).addBox(0.1549F, -4.2552F, -1.9171F, 3.2F, 2.15F, 4.85F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.0F, 0.0F, 0.0194F, -0.0032F, -1.5686F));

		PartDefinition cube_r16 = bone6.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(16, 20).addBox(-0.9189F, -3.0492F, -1.9171F, 3.55F, 2.4F, 4.85F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.0F, 0.0F, 0.0176F, -0.0089F, -1.2632F));

		PartDefinition cube_r17 = bone6.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(16, 14).addBox(-1.8565F, -1.5548F, -1.95F, 4.0F, 2.25F, 4.9F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition cube_r18 = bone6.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 6).addBox(-2.095F, -0.4566F, -2.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition bone10 = mid.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.364F, -8.5613F, 0.2652F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r19 = bone10.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(18, 6).addBox(0.033F, -4.9797F, -1.9F, 3.35F, 3.75F, 4.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.0F, 0.0F, 0.0F, 0.0F, -0.9163F));

		PartDefinition cube_r20 = bone10.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(24, 0).addBox(-1.1745F, -2.7821F, -1.95F, 3.8F, 2.65F, 4.9F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.0F, 0.0F, 0.0F, 0.0F, -0.48F));

		PartDefinition cube_r21 = bone10.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 14).addBox(-1.1495F, -0.675F, -2.0F, 3.75F, 3.8F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition bone2 = mid.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(2.7486F, -9.4113F, -1.2336F, 1.5144F, -1.2528F, 2.3618F));

		PartDefinition cube_r22 = bone2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -0.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.221F, -0.1354F, 1.3974F, -0.4759F, 0.0376F, 0.215F));

		PartDefinition cube_r23 = bone2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, -0.1705F, 0.0376F, 0.215F));

		PartDefinition bone12 = mid.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offsetAndRotation(4.3888F, -8.1568F, -2.4439F, -3.0799F, -1.4023F, 1.1274F));

		PartDefinition cube_r24 = bone12.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -0.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, 0.0477F, 0.0376F, 0.215F));

		PartDefinition cube_r25 = bone12.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, -0.1705F, 0.0376F, 0.215F));

		PartDefinition bone11 = mid.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(4.1845F, -7.899F, -1.2476F, 2.8234F, -1.2528F, 2.3618F));

		PartDefinition cube_r26 = bone11.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -0.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, 0.0477F, 0.0376F, 0.215F));

		PartDefinition cube_r27 = bone11.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, -0.1705F, 0.0376F, 0.215F));

		PartDefinition bone50 = mid.addOrReplaceChild("bone50", CubeListBuilder.create(), PartPose.offsetAndRotation(4.5121F, -9.2386F, -1.1701F, 2.8543F, -1.4023F, 1.1274F));

		PartDefinition cube_r28 = bone50.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -0.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, 0.0477F, 0.0376F, 0.215F));

		PartDefinition cube_r29 = bone50.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, -0.1705F, 0.0376F, 0.215F));

		PartDefinition bone51 = mid.addOrReplaceChild("bone51", CubeListBuilder.create(), PartPose.offsetAndRotation(2.4001F, -9.3417F, -1.0001F, 2.8543F, -1.4023F, 1.1274F));

		PartDefinition cube_r30 = bone51.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -0.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, 0.0477F, 0.0376F, 0.215F));

		PartDefinition cube_r31 = bone51.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, -0.1705F, 0.0376F, 0.215F));

		PartDefinition bajo = Head.addOrReplaceChild("bajo", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone5 = bajo.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.2521F, -7.3694F, -1.4178F, -1.1141F, 1.4321F, -0.0752F));

		PartDefinition cube_r32 = bone5.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -0.5F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, 0.2659F, 0.0376F, 0.215F));

		PartDefinition cube_r33 = bone5.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.029F, 0.1146F, -0.1026F, -0.1705F, 0.0376F, 0.215F));

		PartDefinition bone13 = bajo.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offset(-3.4448F, -1.5384F, -2.25F));

		PartDefinition cube_r34 = bone13.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, -4.75F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone14 = bajo.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offset(-3.3448F, -1.5384F, -2.25F));

		PartDefinition cube_r35 = bone14.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, -4.75F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone15 = bajo.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.3448F, -3.5384F, -2.15F, 0.0F, 0.0F, 0.3491F));

		PartDefinition cube_r36 = bone15.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(30, 32).addBox(-1.2F, -0.6F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8144F, -1.2498F, 5.35F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r37 = bone15.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(30, 32).addBox(-3.3266F, -4.6147F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone16 = bajo.addOrReplaceChild("bone16", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.917F, -5.8834F, -2.25F, 0.0F, 0.0F, 0.3491F));

		PartDefinition cube_r38 = bone16.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(30, 32).addBox(-1.2F, -0.6F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8144F, -1.2498F, 5.35F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r39 = bone16.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, -4.75F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone17 = bajo.addOrReplaceChild("bone17", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0576F, -1.4026F, -4.15F, 0.0F, 0.0F, 0.2618F));

		PartDefinition cube_r40 = bone17.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(30, 32).addBox(-1.2F, -0.6F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8144F, -1.2498F, 5.35F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r41 = bone17.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, -4.75F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone18 = bajo.addOrReplaceChild("bone18", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.1533F, -3.7486F, -4.05F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r42 = bone18.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(30, 32).addBox(-1.2F, -0.6F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8144F, -1.2498F, 5.35F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r43 = bone18.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, -4.75F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone19 = bajo.addOrReplaceChild("bone19", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.9572F, -5.99F, -4.15F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r44 = bone19.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(30, 32).addBox(-1.2F, -0.6F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8144F, -1.2498F, 5.35F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r45 = bone19.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, -4.75F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone20 = bajo.addOrReplaceChild("bone20", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.9282F, -1.8856F, -5.65F, 0.0F, 0.0F, 0.2618F));

		PartDefinition cube_r46 = bone20.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(30, 32).addBox(-1.2F, -0.6F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8144F, -1.2498F, 5.35F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r47 = bone20.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, -4.75F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone21 = bajo.addOrReplaceChild("bone21", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.2944F, -5.3483F, -5.75F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r48 = bone21.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(30, 32).addBox(-1.2F, -0.6F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8144F, -1.2498F, 5.35F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r49 = bone21.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, -4.75F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone22 = bajo.addOrReplaceChild("bone22", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.1493F, -3.2919F, -6.75F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r50 = bone22.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(30, 32).addBox(-1.2F, -0.6F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8144F, -1.2498F, 5.35F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r51 = bone22.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, -4.75F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone23 = bajo.addOrReplaceChild("bone23", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.9323F, -4.5229F, -7.5F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r52 = bone23.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(30, 32).addBox(-1.2F, -0.6F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8144F, -1.2498F, 5.35F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r53 = bone23.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, -4.75F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone24 = bajo.addOrReplaceChild("bone24", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.8786F, -4.063F, -5.8F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r54 = bone24.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, -4.75F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone25 = bajo.addOrReplaceChild("bone25", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0094F, -2.5687F, -3.55F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r55 = bone25.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, -4.75F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone26 = bajo.addOrReplaceChild("bone26", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0623F, -4.8319F, -2.8F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r56 = bone26.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(30, 32).addBox(-1.2F, -0.6F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8144F, -1.2498F, 5.35F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r57 = bone26.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, -4.75F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5448F, 1.6384F, 2.25F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bajoizq = Head.addOrReplaceChild("bajoizq", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone27 = bajoizq.addOrReplaceChild("bone27", CubeListBuilder.create(), PartPose.offset(4.6828F, -1.8729F, 3.1F));

		PartDefinition cube_r58 = bone27.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.15F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1828F, 2.7229F, -0.05F, 0.0F, 0.0F, -1.0036F));

		PartDefinition bone28 = bajoizq.addOrReplaceChild("bone28", CubeListBuilder.create(), PartPose.offsetAndRotation(4.6436F, -4.4464F, 3.3F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r59 = bone28.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6828F, 1.9729F, -0.05F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone29 = bajoizq.addOrReplaceChild("bone29", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.0817F, -3.9637F, 3.25F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r60 = bone29.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(16, 34).addBox(1.1955F, 1.7699F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, -1.45F, 0.0F, 0.0F, 0.0F, -1.2217F));

		PartDefinition cube_r61 = bone29.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, 0.0F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone30 = bajoizq.addOrReplaceChild("bone30", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5438F, 1.8026F, 1.25F, 0.0F, 0.0F, -0.48F));

		PartDefinition cube_r62 = bone30.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(16, 34).addBox(2.8F, 0.55F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, -1.45F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r63 = bone30.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, 0.0F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone31 = bajoizq.addOrReplaceChild("bone31", CubeListBuilder.create(), PartPose.offsetAndRotation(3.9117F, -4.035F, 1.3F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r64 = bone31.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(16, 34).addBox(2.8F, 0.55F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3828F, 0.4229F, -0.05F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r65 = bone31.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6828F, 1.9729F, -0.05F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone32 = bajoizq.addOrReplaceChild("bone32", CubeListBuilder.create(), PartPose.offsetAndRotation(3.8788F, -6.7274F, 1.3F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r66 = bone32.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(16, 34).addBox(2.8F, 0.55F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3828F, 0.4229F, -0.05F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r67 = bone32.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6828F, 1.9729F, -0.05F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone33 = bajoizq.addOrReplaceChild("bone33", CubeListBuilder.create(), PartPose.offsetAndRotation(0.4192F, 1.0219F, -0.5F, 0.0F, 0.0F, -0.48F));

		PartDefinition cube_r68 = bone33.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(16, 34).addBox(2.8F, 0.55F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, -1.45F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r69 = bone33.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, 0.0F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone34 = bajoizq.addOrReplaceChild("bone34", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.1264F, -2.0228F, -0.5F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r70 = bone34.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(16, 34).addBox(2.8F, 0.55F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, -1.45F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r71 = bone34.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, 0.0F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone35 = bajoizq.addOrReplaceChild("bone35", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.1264F, -4.0228F, -0.5F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r72 = bone35.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(16, 34).addBox(2.8F, 0.55F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, -1.45F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r73 = bone35.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, 0.0F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone36 = bajoizq.addOrReplaceChild("bone36", CubeListBuilder.create(), PartPose.offsetAndRotation(4.1967F, -3.1418F, 1.55F, 0.0F, 0.0F, 0.1309F));

		PartDefinition cube_r74 = bone36.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6828F, 1.9729F, -0.05F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone37 = bajoizq.addOrReplaceChild("bone37", CubeListBuilder.create(), PartPose.offsetAndRotation(4.1967F, -3.8918F, -0.2F, 0.0F, 0.0F, 0.1309F));

		PartDefinition cube_r75 = bone37.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(16, 34).addBox(2.8F, 0.55F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3828F, 0.4229F, -0.05F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r76 = bone37.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6828F, 1.9729F, -0.05F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone38 = bajoizq.addOrReplaceChild("bone38", CubeListBuilder.create(), PartPose.offsetAndRotation(4.1967F, -3.1418F, -1.7F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r77 = bone38.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(16, 34).addBox(2.8F, 0.55F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3828F, 0.4229F, -0.05F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r78 = bone38.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6828F, 1.9729F, -0.05F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone39 = bajoizq.addOrReplaceChild("bone39", CubeListBuilder.create(), PartPose.offsetAndRotation(4.1967F, -5.8918F, -2.45F, 0.0F, 0.0F, 0.1309F));

		PartDefinition cube_r79 = bone39.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(16, 34).addBox(2.8F, 0.55F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3828F, 0.4229F, -0.05F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r80 = bone39.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6828F, 1.9729F, -0.05F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone40 = bajoizq.addOrReplaceChild("bone40", CubeListBuilder.create(), PartPose.offsetAndRotation(3.6967F, -4.3918F, -2.2F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r81 = bone40.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(16, 34).addBox(2.8F, 0.55F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3828F, 0.4229F, -0.05F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r82 = bone40.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6828F, 1.9729F, -0.05F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone41 = bajoizq.addOrReplaceChild("bone41", CubeListBuilder.create(), PartPose.offsetAndRotation(4.9467F, -5.1418F, 1.55F, 0.0F, 0.0F, 0.1309F));

		PartDefinition cube_r83 = bone41.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(16, 34).addBox(3.0F, 0.65F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6828F, 1.9729F, -0.05F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bajocentro = Head.addOrReplaceChild("bajocentro", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone42 = bajocentro.addOrReplaceChild("bone42", CubeListBuilder.create(), PartPose.offset(3.025F, -2.1361F, 3.6907F));

		PartDefinition cube_r84 = bone42.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(0, 36).addBox(-1.2F, -2.0F, -1.0F, 2.15F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, -0.0139F, -0.1407F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r85 = bone42.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(16, 34).addBox(-1.25F, -2.0F, -1.0F, 2.25F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, 1.8861F, -0.5407F, -0.2618F, 0.0F, 0.0F));

		PartDefinition bone43 = bajocentro.addOrReplaceChild("bone43", CubeListBuilder.create(), PartPose.offset(-2.975F, -2.1361F, 3.6907F));

		PartDefinition cube_r86 = bone43.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(0, 36).addBox(-1.2F, -2.0F, -1.0F, 2.15F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, -0.0139F, -0.1407F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r87 = bone43.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(16, 34).addBox(-1.25F, -2.0F, -1.0F, 2.25F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, 1.8861F, -0.5407F, -0.2618F, 0.0F, 0.0F));

		PartDefinition bone44 = bajocentro.addOrReplaceChild("bone44", CubeListBuilder.create(), PartPose.offsetAndRotation(1.025F, -2.1361F, 4.0907F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r88 = bone44.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(0, 36).addBox(-1.2F, -2.0F, -1.0F, 2.15F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, -0.0139F, -0.1407F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r89 = bone44.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(16, 34).addBox(-1.25F, -2.0F, -1.0F, 2.25F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, 1.8861F, -0.5407F, -0.2618F, 0.0F, 0.0F));

		PartDefinition bone45 = bajocentro.addOrReplaceChild("bone45", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.125F, -2.1361F, 4.3907F, -0.3316F, 0.0F, 0.0F));

		PartDefinition cube_r90 = bone45.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(0, 36).addBox(-1.2F, -2.0F, -1.0F, 2.15F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, -0.0139F, -0.1407F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r91 = bone45.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(16, 34).addBox(-1.25F, -2.0F, -1.0F, 2.25F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, 1.8861F, -0.5407F, -0.2618F, 0.0F, 0.0F));

		PartDefinition bone46 = bajocentro.addOrReplaceChild("bone46", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.775F, -5.1361F, 3.4907F, 0.0175F, 0.0F, 0.0F));

		PartDefinition cube_r92 = bone46.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(0, 36).addBox(-1.2F, -2.0F, -1.0F, 2.15F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, -0.0139F, -0.1407F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r93 = bone46.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(16, 34).addBox(-1.25F, -2.0F, -1.0F, 2.25F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, 1.8861F, -0.5407F, -0.2618F, 0.0F, 0.0F));

		PartDefinition bone47 = bajocentro.addOrReplaceChild("bone47", CubeListBuilder.create(), PartPose.offsetAndRotation(2.625F, -5.1361F, 3.6407F, 0.0175F, 0.0F, 0.0F));

		PartDefinition cube_r94 = bone47.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(0, 36).addBox(-1.2F, -2.0F, -1.0F, 2.15F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, -0.0139F, -0.1407F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r95 = bone47.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(16, 34).addBox(-1.25F, -2.0F, -1.0F, 2.25F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, 1.8861F, -0.5407F, -0.2618F, 0.0F, 0.0F));

		PartDefinition bone48 = bajocentro.addOrReplaceChild("bone48", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.875F, -4.7861F, 4.0407F, -0.1134F, 0.0F, 0.0F));

		PartDefinition cube_r96 = bone48.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(0, 36).addBox(-1.2F, -2.0F, -1.0F, 2.15F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, -0.0139F, -0.1407F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r97 = bone48.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(16, 34).addBox(-1.25F, -2.0F, -1.0F, 2.25F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, 1.8861F, -0.5407F, -0.2618F, 0.0F, 0.0F));

		PartDefinition bone49 = bajocentro.addOrReplaceChild("bone49", CubeListBuilder.create(), PartPose.offsetAndRotation(0.875F, -5.2361F, 3.7907F, -0.3316F, 0.0F, 0.0F));

		PartDefinition cube_r98 = bone49.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(0, 36).addBox(-1.2F, -2.0F, -1.0F, 2.15F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, -0.0139F, -0.1407F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r99 = bone49.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(16, 34).addBox(-1.25F, -2.0F, -1.0F, 2.25F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.125F, 1.8861F, -0.5407F, -0.2618F, 0.0F, 0.0F));

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
				this.izqini.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.015F);
				this.derini.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*-0.015F);
				this.mid.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*-0.015F);


			} else {
				var velocidad = 0.04f;

				this.izqini.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.01F);
				this.mid.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.01F);
				this.derini.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.01F);

			}

		});
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}