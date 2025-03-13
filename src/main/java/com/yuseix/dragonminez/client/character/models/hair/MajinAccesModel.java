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

public class MajinAccesModel extends HumanoidModel<AbstractClientPlayer> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "addons"), "majinacces");
	private final ModelPart majinacces;
	private final ModelPart orejas;
	private final ModelPart antena;

	public MajinAccesModel(ModelPart root) {
        super(root);
        this.majinacces = root.getChild("majinacces");
		this.orejas = this.majinacces.getChild("orejas");
		this.antena = this.majinacces.getChild("antena");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE,0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition majinacces = partdefinition.addOrReplaceChild("majinacces", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition orejas = majinacces.addOrReplaceChild("orejas", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = orejas.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(9, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(9, 0).addBox(6.5F, -2.0F, -1.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -4.0F, -0.5F, -0.7854F, 0.0F, 0.0F));

		PartDefinition antena = majinacces.addOrReplaceChild("antena", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = antena.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 1).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, -9.8F, 4.05F, -1.9199F, 0.0F, 0.0F));

		PartDefinition cube_r3 = antena.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(8, 1).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -8.9F, 2.0F, -1.2654F, 0.0F, 0.0F));

		PartDefinition cube_r4 = antena.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(8, 1).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.15F, 0.25F, -0.8727F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
		this.majinacces.copyFrom(head);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		majinacces.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}