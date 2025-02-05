package com.yuseix.dragonminez.init.entity.client.renderer.projectil;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.projectil.KiBallModel;
import com.yuseix.dragonminez.init.entity.custom.projectil.KiSmallBallProjectil;
import com.yuseix.dragonminez.utils.shaders.CustomRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class KiSmallBallRenderer extends EntityRenderer<KiSmallBallProjectil> {
    private float colorR, colorG, colorB;

    private final KiBallModel<KiSmallBallProjectil> model;

    public KiSmallBallRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new KiBallModel<>(pContext.bakeLayer(KiBallModel.LAYER_LOCATION));

    }

    @Override
    public void render(KiSmallBallProjectil pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        // Escala de la entidad
        pPoseStack.pushPose();

        pPoseStack.scale(1.0f,1.0f,1.0f);

        // Ajusta la rotación para que apunte en la dirección de movimiento (no recomendable)
        //pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        //pPoseStack.mulPose(Axis.XP.rotationDegrees(180));

        VertexConsumer outlineConsumer = pBuffer.getBuffer(CustomRenderTypes.energy(this.getTextureLocation(pEntity)));

        pPoseStack.pushPose();
        pPoseStack.scale(1.5f, 1.5f, 1.5f); // Cambia el tamaño aquí si deseas escalar el modelo
        pPoseStack.translate(0.0, -0.8, 0.0); // Ajuste en el eje Z para acercar el borde al modelo

        float rotationAngle = (pEntity.tickCount + pPartialTick) * 180; // Ajusta el multiplicador para cambiar la velocidad
        pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle));

        var colorBorde = pEntity.getColorBorde();

        colorR = (colorBorde >> 16) / 255.0F;
        colorG = ((colorBorde >> 8) & 0xff) / 255.0f;
        colorB = (colorBorde & 0xff) / 255.0f;

        this.model.renderToBuffer(pPoseStack, outlineConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 0.7F); // Cambia el color del borde aquí
        pPoseStack.popPose();

        // Renderizar el modelo principal
        VertexConsumer mainConsumer = pBuffer.getBuffer(CustomRenderTypes.glow(this.getTextureLocation(pEntity)));
        pPoseStack.translate(-0.01, -0.65, 0.0); // Ajuste en el eje Z para acercar el borde al modelo

        float rotationAngle2 = (pEntity.tickCount + pPartialTick) * 180; // Ajusta el multiplicador para cambiar la velocidad
        pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2));

        var color = pEntity.getColor();

        colorR = (color >> 16) / 255.0F;
        colorG = ((color >> 8) & 0xff) / 255.0f;
        colorB = (color & 0xff) / 255.0f;

        this.model.renderToBuffer(pPoseStack, mainConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

        pPoseStack.popPose();
        //super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(KiSmallBallProjectil kiBlastProyectil) {
        return new ResourceLocation(DragonMineZ.MOD_ID,"textures/entity/ki.png");
    }

    @Override
    protected void renderNameTag(KiSmallBallProjectil pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
    }
}
