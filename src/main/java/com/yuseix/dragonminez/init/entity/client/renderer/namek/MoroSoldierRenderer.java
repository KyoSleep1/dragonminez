package com.yuseix.dragonminez.init.entity.client.renderer.namek;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.namek.MoroSoldierModel;
import com.yuseix.dragonminez.init.entity.custom.namek.MoroSoldierEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MoroSoldierRenderer extends GeoEntityRenderer<MoroSoldierEntity> {
    public MoroSoldierRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MoroSoldierModel());
    }

    @Override
    public ResourceLocation getTextureLocation(MoroSoldierEntity animatable) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/namekusei/soldado1_moro.png");
    }

    @Override
    public RenderType getRenderType(MoroSoldierEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(texture);
    }

    @Override
    protected void renderNameTag(MoroSoldierEntity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

    }
}
