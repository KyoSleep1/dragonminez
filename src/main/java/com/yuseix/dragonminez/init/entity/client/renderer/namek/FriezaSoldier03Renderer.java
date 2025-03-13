package com.yuseix.dragonminez.init.entity.client.renderer.namek;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.namek.FriezaSoldier02Model;
import com.yuseix.dragonminez.init.entity.client.model.namek.FriezaSoldier03Model;
import com.yuseix.dragonminez.init.entity.custom.namek.FriezaSoldier02Entity;
import com.yuseix.dragonminez.init.entity.custom.namek.FriezaSoldier03Entity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class FriezaSoldier03Renderer extends LivingEntityRenderer<FriezaSoldier03Entity, PlayerModel<FriezaSoldier03Entity>> {

    public FriezaSoldier03Renderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FriezaSoldier03Model<>(pContext.bakeLayer(FriezaSoldier03Model.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(FriezaSoldier03Entity namekWarriorEntity) {
        return new ResourceLocation(Reference.MOD_ID,"textures/entity/namekusei/soldado3.png");
    }

    @Override
    protected @Nullable RenderType getRenderType(FriezaSoldier03Entity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityCutout(getTextureLocation(pLivingEntity));
    }

    @Override
    protected void renderNameTag(FriezaSoldier03Entity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

    }
}
