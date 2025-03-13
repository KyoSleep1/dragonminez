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

public class Tail2Model extends HumanoidModel<AbstractClientPlayer> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "tails"), "cinturon");
	private final ModelPart tail;
	private final ModelPart tail1;

	public Tail2Model(ModelPart root) {
        super(root);
        this.tail = root.getChild("tail");
		this.tail1 = this.tail.getChild("tail1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE,0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail1 = tail.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(42, 38).addBox(2.25F, -0.25F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(42, 38).addBox(-4.25F, -0.25F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(42, 38).addBox(-2.25F, -0.25F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(42, 38).addBox(0.25F, -0.25F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(42, 38).addBox(-0.5F, -0.25F, -5.01F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 2.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
		this.tail.copyFrom(body);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}