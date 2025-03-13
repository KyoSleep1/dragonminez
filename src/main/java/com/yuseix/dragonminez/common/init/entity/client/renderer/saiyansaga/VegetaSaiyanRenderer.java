package com.yuseix.dragonminez.common.init.entity.client.renderer.saiyansaga;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.client.character.models.AuraModel;
import com.yuseix.dragonminez.client.util.shader.CustomRenderTypes;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.entity.client.model.saiyansaga.VegetaModel;
import com.yuseix.dragonminez.common.init.entity.custom.saiyansaga.VegetaEntity;
import com.yuseix.dragonminez.client.util.TextureManager;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class VegetaSaiyanRenderer extends LivingEntityRenderer<VegetaEntity, PlayerModel<VegetaEntity>> {

    private static final AuraModel AURA_MODEL = new AuraModel(AuraModel.createBodyLayer().bakeRoot());

    public VegetaSaiyanRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new VegetaModel<>(pContext.bakeLayer(VegetaModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public void render(VegetaEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

        if(pEntity.getisKiCharge()){
            renderAuraBase(pEntity, pPoseStack, pBuffer, pPackedLight, pPartialTicks, 0.15f, 11748327);
        }

    }

    public static void renderAuraBase(VegetaEntity player, PoseStack poseStack, MultiBufferSource buffer, int packedLight, float partialTicks, float transparencia, int colorAura) {
        // Descomponer el color en sus componentes RGBA
        float red = (colorAura >> 16 & 255) / 255.0f;
        float green = (colorAura >> 8 & 255) / 255.0f;
        float blue = (colorAura & 255) / 255.0f;
        //ACA YA FUNCIONA
        poseStack.pushPose();


        poseStack.mulPose(Axis.XP.rotationDegrees(180f));

        poseStack.translate(0.0f, -1.9f, 0.0f);

        float rotationAngle = 0.0F;
        rotationAngle = (player.tickCount + partialTicks) * 5.0F; // Ajusta la velocidad aquí

        float rotationAngle2 = 0.0F;
        rotationAngle2 = (player.tickCount + partialTicks) * -7.0F; // Ajusta la velocidad aquí

        VertexConsumer vertexConsumer = buffer.getBuffer(CustomRenderTypes.energy(TextureManager.AURA_BASE));


        // PARTE BAJA 1
        for (int i = 0; i < 8; i++) {  // Ajusta el número de planos
            poseStack.pushPose();
            poseStack.scale(1.2F, 1.7F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
            poseStack.mulPose(Axis.XP.rotationDegrees(40));

            // Posicionar el aura un poco más arriba o abajo
            poseStack.translate(0.0D, -1.0D, -0.7D);

            // Renderizar cada plano
            AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

            poseStack.popPose();
        }

        //PARTE BAJA 2
        for (int i = 0; i < 8; i++) {
            poseStack.pushPose();
            poseStack.scale(1.4F, 1.9F, 1.4F);

            // Rotar cada plano un poco más en Y y X
            poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2 + i * 45F));  // Cambia 30F por el ángulo que desees
            poseStack.mulPose(Axis.XP.rotationDegrees(40));

            // Posicionar el aura un poco más arriba o abajo
            poseStack.translate(0.0D, -1.0D, -0.5D);

            // Renderizar cada plano
            AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

            poseStack.popPose();
        }
        //PARTE MEDIO 1 interior
        for (int i = 0; i < 10; i++) {
            poseStack.pushPose();
            poseStack.scale(1.2F, 1.7F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2 + i * 45F));  // Cambia 30F por el ángulo que desees
            poseStack.mulPose(Axis.XP.rotationDegrees(0));

            // Posicionar el aura un poco más arriba o abajo
            poseStack.translate(0.0D, -0.6D, -0.2D);

            // Renderizar cada plano
            AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

            poseStack.popPose();
        }
        //parte medio 2 exterior
        for (int i = 0; i < 10; i++) {
            poseStack.pushPose();
            poseStack.scale(1.2F, 1.7F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
            poseStack.mulPose(Axis.XP.rotationDegrees(15f));

            // Posicionar el aura un poco más arriba o abajo
            poseStack.translate(0.0D, -1.0D, -0.4D);

            // Renderizar cada plano
            AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

            poseStack.popPose();
        }
        //parte medio 3 exterior
        for (int i = 0; i < 10; i++) {
            poseStack.pushPose();
            poseStack.scale(1.2F, 1.9F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
            poseStack.mulPose(Axis.XP.rotationDegrees(15f));

            // Posicionar el aura un poco más arriba o abajo
            poseStack.translate(0.0D, -1.0D, -0.6D);

            // Renderizar cada plano
            AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

            poseStack.popPose();
        }
        //PARTE ARRIBA 1 interior
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            poseStack.pushPose();
            poseStack.scale(1.2F, 1.6F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2 + i * 45F));  // Cambia 30F por el ángulo que desees
            poseStack.mulPose(Axis.XP.rotationDegrees(-35F));

            // Posicionar el aura un poco más arriba o abajo
            poseStack.translate(0.0D, -1.1D, -0.38D);

            // Renderizar cada plano
            AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

            poseStack.popPose();
        }
        //Parte 2 arriba exterior
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            poseStack.pushPose();
            poseStack.scale(1.2F, 1.6F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
            poseStack.mulPose(Axis.XP.rotationDegrees(25F));

            // Posicionar el aura un poco más arriba o abajo
            poseStack.translate(0.0D, -0.8D, -0.4D);

            // Renderizar cada plano
            AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

            poseStack.popPose();
        }
        //Parte 3 arriba exterior
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            poseStack.pushPose();
            poseStack.scale(1.2F, 1.6F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2 + i * 45F));  // Cambia 30F por el ángulo que desees
            poseStack.mulPose(Axis.XP.rotationDegrees(-15F));

            // Posicionar el aura un poco más arriba o abajo
            poseStack.translate(0.0D, -1.2D, -0.4D);

            // Renderizar cada plano
            AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

            poseStack.popPose();
        }
        //PARTE ARRIBA 4 interior
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            poseStack.pushPose();
            poseStack.scale(1.2F, 1.6F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
            poseStack.mulPose(Axis.XP.rotationDegrees(5F));

            // Posicionar el aura un poco más arriba o abajo
            poseStack.translate(0.0D, -1.5D, -0.38D);

            // Renderizar cada plano
            AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

            poseStack.popPose();
        }

        poseStack.popPose();
    }


        @Override
    public ResourceLocation getTextureLocation(VegetaEntity pEntity) {
        return new ResourceLocation(Reference.MOD_ID,"textures/entity/sagas/saiyan/vegeta_saiyan.png");
    }

    @Override
    protected @Nullable RenderType getRenderType(VegetaEntity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityCutout(getTextureLocation(pLivingEntity));
    }

    @Override
    protected void renderNameTag(VegetaEntity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

    }
}
