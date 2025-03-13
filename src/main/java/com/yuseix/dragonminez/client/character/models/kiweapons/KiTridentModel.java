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

public class KiTridentModel extends HumanoidModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "kiweapons"), "trident");
	public final ModelPart trident;
	private final ModelPart kisword;
	private final ModelPart kisword2;

	public KiTridentModel(ModelPart root) {
        super(root);
        this.trident = root.getChild("trident");
		this.kisword = this.trident.getChild("kisword");
		this.kisword2 = this.kisword.getChild("kisword2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition =  HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition trident = partdefinition.addOrReplaceChild("trident", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition kisword = trident.addOrReplaceChild("kisword", CubeListBuilder.create().texOffs(31, 44).addBox(-1.05F, 1.0F, -1.05F, 2.1F, 8.95F, 2.1F, new CubeDeformation(0.0F))
		.texOffs(48, 29).addBox(-0.85F, -6.0F, -0.85F, 1.7F, 6.95F, 1.7F, new CubeDeformation(0.0F))
		.texOffs(41, 18).addBox(-0.65F, -12.9F, -0.65F, 1.3F, 6.95F, 1.3F, new CubeDeformation(0.0F))
		.texOffs(54, 15).addBox(-0.45F, -19.85F, -0.45F, 0.9F, 6.95F, 0.9F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 8.0F, -5.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r1 = kisword.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 46).addBox(-3.0F, -1.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9591F, 9.7004F, -0.004F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r2 = kisword.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(20, 39).addBox(-3.0F, -1.7F, -0.7F, 4.0F, 1.4F, 1.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9591F, 5.7004F, -0.004F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r3 = kisword.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(39, 41).addBox(-4.9F, -1.3F, -0.3F, 5.9F, 0.6F, 0.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1093F, 16.8091F, 5.9155F, -1.5708F, 0.0873F, -1.5708F));

		PartDefinition cube_r4 = kisword.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(45, 47).addBox(-4.9F, -1.5F, -0.5F, 5.9F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1093F, 10.949F, 5.402F, -1.5708F, 0.0873F, -1.5708F));

		PartDefinition cube_r5 = kisword.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(24, 33).addBox(-4.9F, -1.7F, -0.7F, 5.9F, 1.4F, 1.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1093F, 5.0715F, 4.8878F, -1.5708F, 0.0873F, -1.5708F));

		PartDefinition cube_r6 = kisword.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(4, 11).addBox(-6.0F, -2.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1093F, 9.8501F, 2.396F, 1.5708F, 0.7418F, 1.5708F));

		PartDefinition cube_r7 = kisword.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(4, 11).addBox(-6.0F, -2.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1093F, 8.8501F, -2.004F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r8 = kisword.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(23, 5).addBox(-7.0F, -2.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0093F, 9.6501F, -0.004F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r9 = kisword.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(45, 8).addBox(-1.55F, -2.0F, -1.85F, 3.2F, 2.35F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3F, 10.0F, 0.2F, 0.0436F, -0.7854F, 0.0F));

		PartDefinition kisword2 = kisword.addOrReplaceChild("kisword2", CubeListBuilder.create(), PartPose.offsetAndRotation(12.0F, 0.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r10 = kisword2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(3, 3).mirror().addBox(-1.0F, -1.3F, -0.3F, 6.9F, 0.6F, 0.6F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-12.1093F, 15.2534F, -7.2131F, -1.5708F, 0.0436F, 1.5708F));

		PartDefinition cube_r11 = kisword2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(25, 17).mirror().addBox(-1.0F, -1.5F, -0.5F, 5.7F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-12.1093F, 10.0584F, -6.9863F, -1.5708F, 0.0436F, 1.5708F));

		PartDefinition cube_r12 = kisword2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(1, 38).mirror().addBox(-1.0F, -1.7F, -0.7F, 5.7F, 1.4F, 1.4F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-12.1093F, 4.3638F, -6.7377F, -1.5708F, 0.0436F, 1.5708F));

		PartDefinition cube_r13 = kisword2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(1, 19).mirror().addBox(-1.0F, -2.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-9.9907F, 9.6501F, -0.004F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r14 = kisword2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(20, 28).mirror().addBox(-1.0F, -1.4F, -0.3F, 7.0F, 0.7F, 0.6F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-19.9901F, 12.78F, -0.004F, 0.0F, 0.0F, 1.5272F));

		PartDefinition cube_r15 = kisword2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(20, 28).mirror().addBox(-1.0F, -1.3F, -0.3F, 7.0F, 0.6F, 0.6F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.6901F, 12.78F, -0.004F, 0.0F, 0.0F, 1.5272F));

		PartDefinition cube_r16 = kisword2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(2, 47).mirror().addBox(-1.0F, -1.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.8209F, 9.7829F, -0.004F, 0.0F, 0.0F, 1.5272F));

		PartDefinition cube_r17 = kisword2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(5, 27).mirror().addBox(-1.0F, -1.7F, -0.7F, 4.0F, 1.4F, 1.4F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.9954F, 5.7867F, -0.004F, 0.0F, 0.0F, 1.5272F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
		this.trident.copyFrom(this.rightArm);
		this.trident.x = 0.5f;
		this.trident.y = 0.2f;
		this.trident.z = -5.2f;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		trident.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}