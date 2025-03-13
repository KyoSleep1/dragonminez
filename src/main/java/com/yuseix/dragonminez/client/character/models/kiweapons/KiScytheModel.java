package com.yuseix.dragonminez.client.character.models.kiweapons;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class KiScytheModel extends HumanoidModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "kiweapons"), "scythe");
	public final ModelPart scythe;
	private final ModelPart hoja;

	public KiScytheModel(ModelPart root) {
        super(root);
        this.scythe = root.getChild("scythe");
		this.hoja = this.scythe.getChild("hoja");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition =  HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition scythe = partdefinition.addOrReplaceChild("scythe", CubeListBuilder.create().texOffs(0, 31).addBox(-2.0F, 7.0F, -11.0F, 1.5F, 1.5F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(1, 43).addBox(-1.75F, 7.25F, 20.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(39, 29).addBox(-2.0F, 7.0F, -22.0F, 1.5F, 1.5F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition hoja = scythe.addOrReplaceChild("hoja", CubeListBuilder.create().texOffs(42, 50).addBox(-2.25F, 6.75F, -17.5F, 2.0F, 2.25F, 7.5F, new CubeDeformation(0.0F))
		.texOffs(19, 44).addBox(-2.0F, 7.0F, -19.0F, 1.5F, 1.75F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(17, 39).addBox(-1.75F, 7.25F, -21.0F, 1.0F, 1.25F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -12.0F));

		PartDefinition cube_r1 = hoja.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(18, 3).addBox(-1.0F, -12.0F, 2.25F, 1.0F, 3.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 33.25F, 14.25F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r2 = hoja.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(9, 15).addBox(-1.0F, -12.0F, 1.25F, 1.0F, 5.0F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 36.0F, 8.25F, 1.1781F, 0.0F, 0.0F));

		PartDefinition cube_r3 = hoja.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -12.0F, 0.25F, 1.0F, 7.0F, 4.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 34.5F, 1.0F, 0.9163F, 0.0F, 0.0F));

		PartDefinition cube_r4 = hoja.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(28, 2).addBox(-1.0F, -12.0F, -0.5F, 1.0F, 6.0F, 5.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 32.5F, -5.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r5 = hoja.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(42, 1).addBox(-1.0F, -12.0F, -0.5F, 1.0F, 8.0F, 5.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 26.5F, -10.25F, 0.48F, 0.0F, 0.0F));

		PartDefinition cube_r6 = hoja.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 23).addBox(-1.0F, -12.0F, -1.0F, 1.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 18.25F, -15.25F, 0.0873F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
		this.scythe.copyFrom(this.rightArm);
		this.scythe.x = 0.5f;
		this.scythe.y = 0.2f;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		scythe.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}