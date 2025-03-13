package com.yuseix.dragonminez.init.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.RedRibbonSoldierModel;
import com.yuseix.dragonminez.init.entity.client.model.namek.FriezaSoldier02Model;
import com.yuseix.dragonminez.init.entity.custom.RedRibbonSoldierEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.FriezaSoldier02Entity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RedRibbonSoldierRenderer extends LivingEntityRenderer<RedRibbonSoldierEntity, PlayerModel<RedRibbonSoldierEntity>> {

    private static final ResourceLocation[] TEXTURES = new ResourceLocation[]{
            new ResourceLocation(Reference.MOD_ID, "textures/entity/patreon/patreon.png"),
            new ResourceLocation(Reference.MOD_ID, "textures/entity/patreon/patreon1.png"),
            new ResourceLocation(Reference.MOD_ID, "textures/entity/patreon/patreon2.png"),
            new ResourceLocation(Reference.MOD_ID, "textures/entity/patreon/patreon3.png"),
            new ResourceLocation(Reference.MOD_ID, "textures/entity/patreon/patreon4.png"),
            new ResourceLocation(Reference.MOD_ID, "textures/entity/patreon/patreon5.png")
    };

    public RedRibbonSoldierRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RedRibbonSoldierModel<>(pContext.bakeLayer(RedRibbonSoldierModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RedRibbonSoldierEntity redRibbonSoldierEntity) {
        int textureIndex = redRibbonSoldierEntity.getTextureType();

        if (textureIndex >= 0 && textureIndex < TEXTURES.length) {
            return TEXTURES[textureIndex];
        } else {
            return TEXTURES[0];
        }
    }

    @Override
    protected @Nullable RenderType getRenderType(RedRibbonSoldierEntity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityCutout(getTextureLocation(pLivingEntity));
    }

    @Override
    protected void renderNameTag(RedRibbonSoldierEntity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

    }
}
