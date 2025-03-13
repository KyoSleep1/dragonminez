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

public class KiSwordModel extends HumanoidModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "kiweapons"), "sword");
	public final ModelPart kisword;

	public KiSwordModel(ModelPart root) {
        super(root);
        this.kisword = root.getChild("kisword");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition =  HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition kisword = partdefinition.addOrReplaceChild("kisword", CubeListBuilder.create().texOffs(2, 2).addBox(-3.25F, 7.0F, -2.25F, 4.5F, 3.25F, 4.5F, new CubeDeformation(0.0F))
		.texOffs(4, 29).addBox(-3.1F, 6.25F, -2.15F, 4.25F, 3.25F, 4.3F, new CubeDeformation(0.0F))
		.texOffs(13, 18).addBox(-2.75F, 10.25F, -1.75F, 3.5F, 3.0F, 3.5F, new CubeDeformation(0.0F))
		.texOffs(48, 21).addBox(-2.25F, 13.25F, -1.25F, 2.5F, 2.75F, 2.5F, new CubeDeformation(0.0F))
		.texOffs(30, 27).addBox(-1.75F, 15.5F, -1.0F, 1.5F, 2.75F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(37, 37).addBox(-1.5F, 18.25F, -0.75F, 1.0F, 2.0F, 1.5F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
		this.kisword.copyFrom(this.rightArm);
		this.kisword.x = 0.5f;
		this.kisword.y = 0.2f;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		kisword.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}