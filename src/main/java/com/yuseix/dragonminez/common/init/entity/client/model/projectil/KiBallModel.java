package com.yuseix.dragonminez.common.init.entity.client.model.projectil;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.common.Reference;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class KiBallModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "tech"), "ki_blast");
	private final ModelPart bone;
	private final ModelPart esfera;

	public KiBallModel(ModelPart root) {
		this.bone = root.getChild("bone");
		this.esfera = this.bone.getChild("esfera");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 17.5F, 0.0F));

		PartDefinition esfera = bone.addOrReplaceChild("esfera", CubeListBuilder.create().texOffs(0, 12).addBox(-3.75F, -2.5F, -2.5F, 6.5F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(-3.0F, -2.5F, -3.25F, 5.0F, 5.0F, 6.5F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(-3.25F, -3.0F, -2.75F, 5.5F, 6.0F, 5.5F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(-3.0F, -3.25F, -2.5F, 5.0F, 6.5F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float rotationSpeed = 32.0f;

		this.esfera.zRot = (float) Math.toRadians(ageInTicks * rotationSpeed);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}


}