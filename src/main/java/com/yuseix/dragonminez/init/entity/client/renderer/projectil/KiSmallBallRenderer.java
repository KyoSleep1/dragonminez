package com.yuseix.dragonminez.init.entity.client.renderer.projectil;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.projectil.KiBallPlaneModel;
import com.yuseix.dragonminez.init.entity.custom.projectil.KiSmallBallProjectil;
import com.yuseix.dragonminez.utils.TextureManager;
import com.yuseix.dragonminez.utils.shaders.CustomRenderTypes;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.joml.Quaternionf;

public class KiSmallBallRenderer extends EntityRenderer<KiSmallBallProjectil> {

    private float colorR, colorG, colorB;
    public static final KiBallPlaneModel esferamodel = new KiBallPlaneModel(KiBallPlaneModel.createBodyLayer().bakeRoot());

    public KiSmallBallRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);

    }

    @Override
    public void render(KiSmallBallProjectil pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        // Escala de la entidad
        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        Quaternionf cameraRotation = camera.rotation(); // Obtiene la rotación de la cámara en quaternion

        pPoseStack.pushPose();
        pPoseStack.mulPose(cameraRotation);

        float ageInTicks = pEntity.tickCount + pPartialTick; // Se usa tickCount para animaciones basadas en tiempo
        esferamodel.setupAnim(pEntity, 0.0f, 0.0f, ageInTicks, 0.0f, 0.0f); // 💡 AQUÍ SE LLAMA A setupAnim

        // Ajustar la escala del modelo
        pPoseStack.scale(0.7f, 0.7f, 0.7f);
        pPoseStack.translate(0.0, 0.15, 0.0);

        // Renderizar el borde
        VertexConsumer outlineConsumer = pBuffer.getBuffer(CustomRenderTypes.beaconBeam(TextureManager.KI_BALL_1, true));
        var color = pEntity.getColor();
        float colorR = (color >> 16) / 255.0F;
        float colorG = ((color >> 8) & 0xff) / 255.0f;
        float colorB = (color & 0xff) / 255.0f;

        esferamodel.renderToBuffer(pPoseStack, outlineConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0F);

        // Renderizar el borde
        VertexConsumer mainConsumer = pBuffer.getBuffer(CustomRenderTypes.beaconBeam(TextureManager.KI_BALL_2, true));
        pPoseStack.scale(0.9f, 0.9f, 0.9f);
        pPoseStack.translate(0.0, 0, 0.0);

        var colorBorde = pEntity.getColorBorde();
        colorR = (colorBorde >> 16) / 255.0F;
        colorG = ((colorBorde >> 8) & 0xff) / 255.0f;
        colorB = (colorBorde & 0xff) / 255.0f;

        esferamodel.renderToBuffer(pPoseStack, mainConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

        // Restaurar el estado de transformación
        pPoseStack.popPose();
//        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(KiSmallBallProjectil kiBlastProyectil) {
        return new ResourceLocation(DragonMineZ.MOD_ID,"textures/entity/ki.png");
    }

    @Override
    protected void renderNameTag(KiSmallBallProjectil pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
    }
}
