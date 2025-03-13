package com.yuseix.dragonminez.init.entity.client.renderer.namek;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.namek.FriezaSoldier02Model;
import com.yuseix.dragonminez.init.entity.client.model.namek.NamekNPCModel;
import com.yuseix.dragonminez.init.entity.custom.namek.FriezaSoldier02Entity;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekWarriorEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class FriezaSoldier02Renderer extends LivingEntityRenderer<FriezaSoldier02Entity, PlayerModel<FriezaSoldier02Entity>> {

    public FriezaSoldier02Renderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FriezaSoldier02Model<>(pContext.bakeLayer(FriezaSoldier02Model.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(FriezaSoldier02Entity namekWarriorEntity) {
        return new ResourceLocation(Reference.MOD_ID,"textures/entity/namekusei/soldado2.png");
    }

    @Override
    protected @Nullable RenderType getRenderType(FriezaSoldier02Entity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityCutout(getTextureLocation(pLivingEntity));
    }

    @Override
    protected void renderNameTag(FriezaSoldier02Entity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

    }
}
