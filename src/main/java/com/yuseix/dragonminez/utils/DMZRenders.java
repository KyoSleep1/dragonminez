package com.yuseix.dragonminez.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.character.models.AuraModel;
import com.yuseix.dragonminez.init.entity.client.model.projectil.KiBallModel;
import com.yuseix.dragonminez.init.entity.client.model.projectil.KiTrailModel;
import com.yuseix.dragonminez.init.entity.custom.projectil.KiSmallBallProjectil;
import com.yuseix.dragonminez.init.entity.custom.projectil.KiSmallWaveProjectil;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.shaders.CustomRenderTypes;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class DMZRenders {
    private static final ResourceLocation textura_ki = new ResourceLocation(DragonMineZ.MOD_ID,"textures/entity/ki.png");
    private static final AuraModel AURA_MODEL = new AuraModel(AuraModel.createBodyLayer().bakeRoot());

    public static void renderKiSmallBall(KiSmallBallProjectil pEntity, float pPartialTick, PoseStack pPoseStack, double camX, double camY, double camZ) {
        Minecraft minecraft = Minecraft.getInstance();
        MultiBufferSource pBuffer = minecraft.renderBuffers().bufferSource();
        int pPackedLight = 15728880; // Iluminación máxima

        KiBallModel<KiSmallBallProjectil> model = new KiBallModel<>(minecraft.getEntityModels().bakeLayer(KiBallModel.LAYER_LOCATION));

        // Interpolación para suavizar el movimiento
        double interpX = Mth.lerp(pPartialTick, pEntity.xOld, pEntity.getX()) - camX;
        double interpY = Mth.lerp(pPartialTick, pEntity.yOld, pEntity.getY()) - camY;
        double interpZ = Mth.lerp(pPartialTick, pEntity.zOld, pEntity.getZ()) - camZ;

        pPoseStack.pushPose();
        pPoseStack.translate(interpX, interpY, interpZ);
        pPoseStack.scale(1.3f, 1.3f, 1.3f);

        // Renderizado del borde (outline)
        VertexConsumer outlineConsumer = pBuffer.getBuffer(CustomRenderTypes.energy(textura_ki));

        pPoseStack.pushPose();
        pPoseStack.scale(1.5f, 1.5f, 1.5f);
        pPoseStack.translate(0.0, -0.8, 0.0);

        float rotationAngle = (pEntity.tickCount + pPartialTick) * 180;
        pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle));

        int colorBorde = pEntity.getColorBorde();
        float colorR = (colorBorde >> 16 & 0xFF) / 255.0F;
        float colorG = (colorBorde >> 8 & 0xFF) / 255.0F;
        float colorB = (colorBorde & 0xFF) / 255.0F;

        model.renderToBuffer(pPoseStack, outlineConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 0.7F);
        pPoseStack.popPose();

        // Renderizar el modelo principal
        VertexConsumer mainConsumer = pBuffer.getBuffer(CustomRenderTypes.glow(textura_ki));
        pPoseStack.translate(-0.01, -0.65, 0.0);

        pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle));

        int color = pEntity.getColor();
        colorR = (color >> 16 & 0xFF) / 255.0F;
        colorG = (color >> 8 & 0xFF) / 255.0F;
        colorB = (color & 0xFF) / 255.0F;

        model.renderToBuffer(pPoseStack, mainConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

        pPoseStack.popPose();
    }

    public static void renderKiSmallWave(KiSmallWaveProjectil pEntity, float pPartialTick, PoseStack pPoseStack, double camX, double camY, double camZ) {
        Minecraft minecraft = Minecraft.getInstance();
        MultiBufferSource pBuffer = minecraft.renderBuffers().bufferSource();
        int pPackedLight = 15728880; // Iluminación máxima

        KiBallModel<KiSmallWaveProjectil> model = new KiBallModel<>(minecraft.getEntityModels().bakeLayer(KiBallModel.LAYER_LOCATION));

        float ageInTicks = pEntity.tickCount + pPartialTick; // Se usa tickCount para animaciones basadas en tiempo
        model.setupAnim(pEntity, 0.0f, 0.0f, ageInTicks, 0.0f, 0.0f); // 💡 AQUÍ SE LLAMA A setupAnim

        // Interpolación para suavizar el movimiento
        double interpX = Mth.lerp(pPartialTick, pEntity.xOld, pEntity.getX()) - camX;
        double interpY = Mth.lerp(pPartialTick, pEntity.yOld, pEntity.getY()) - camY;
        double interpZ = Mth.lerp(pPartialTick, pEntity.zOld, pEntity.getZ()) - camZ;

        pPoseStack.pushPose();
        pPoseStack.translate(interpX, interpY, interpZ);
        pPoseStack.scale(1.3f, 1.3f, 1.3f);

        // Renderizado del borde (outline)
        VertexConsumer outlineConsumer = pBuffer.getBuffer(CustomRenderTypes.energy(textura_ki));
        pPoseStack.pushPose();
        pPoseStack.scale(1.5f, 1.5f, 1.5f);
        pPoseStack.translate(0.0, -0.8, 0.0);

        float rotationAngle = (pEntity.tickCount + pPartialTick) * 180;
        //pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle));

        int colorBorde = pEntity.getColorBorde();
        float colorR = (colorBorde >> 16 & 0xFF) / 255.0F;
        float colorG = (colorBorde >> 8 & 0xFF) / 255.0F;
        float colorB = (colorBorde & 0xFF) / 255.0F;

        model.renderToBuffer(pPoseStack, outlineConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 0.7F);
        pPoseStack.popPose();

        // Renderizar el modelo principal
        VertexConsumer mainConsumer = pBuffer.getBuffer(CustomRenderTypes.glow(textura_ki));
        pPoseStack.translate(-0.01, -0.65, 0.0);
        //pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle));

        int color = pEntity.getColor();
        colorR = (color >> 16 & 0xFF) / 255.0F;
        colorG = (color >> 8 & 0xFF) / 255.0F;
        colorB = (color & 0xFF) / 255.0F;

        model.renderToBuffer(pPoseStack, mainConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

        pPoseStack.popPose();

        //TRIAL

        Vec3 startPos = pEntity.getStartPosition();
        if (startPos != null && !startPos.equals(Vec3.ZERO)) {
            Vec3 currentPos = new Vec3(
                    Mth.lerp(pPartialTick, pEntity.xOld, pEntity.getX()),
                    Mth.lerp(pPartialTick, pEntity.yOld, pEntity.getY()),
                    Mth.lerp(pPartialTick, pEntity.zOld, pEntity.getZ())
            );

            double trailLength = startPos.distanceTo(currentPos);
            if (trailLength > 0.1) {
                pPoseStack.pushPose();

                Camera camera = minecraft.gameRenderer.getMainCamera();
                Vec3 camPos = camera.getPosition();

                Vec3 relativeStart = startPos.subtract(camPos);
                Vec3 relativeEnd = currentPos.subtract(camPos);

                Vec3 direction = relativeEnd.subtract(relativeStart).normalize();

                double speedFactor = Mth.clamp(pEntity.getVelocidad() * 3.009, 0.5, 100.0); // Evita valores extremos

                double adjustedTrailLength = trailLength * speedFactor;

                float yaw = (float) Math.toDegrees(Math.atan2(direction.x, direction.z));
                float pitch = (float) Math.toDegrees(Math.asin(direction.y));

                pPoseStack.translate(relativeStart.x, relativeStart.y, relativeStart.z);

                pPoseStack.mulPose(Axis.YP.rotationDegrees(yaw));
                pPoseStack.mulPose(Axis.XP.rotationDegrees(-pitch));

                pPoseStack.translate(0.0f, 0.0f, 0.0f); // Asegurar que esté centrado en (0,0,0)

                pPoseStack.translate(0.0f, -0.85f, adjustedTrailLength * 0.32f);

                pPoseStack.scale(1.3f, 1.3f, (float) adjustedTrailLength);

                VertexConsumer trailConsumer = pBuffer.getBuffer(CustomRenderTypes.glow(textura_ki));
                KiTrailModel<KiSmallWaveProjectil> trailModel = new KiTrailModel<>(minecraft.getEntityModels().bakeLayer(KiTrailModel.LAYER_LOCATION));

                trailModel.setupAnim(pEntity, 0.0f, 0.0f, ageInTicks, 0.0f, 0.0f); // 💡 AQUÍ SE LLAMA A setupAnim

                colorR = (colorBorde >> 16 & 0xFF) / 255.0F;
                colorG = (colorBorde >> 8 & 0xFF) / 255.0F;
                colorB = (colorBorde & 0xFF) / 255.0F;

                trailModel.renderToBuffer(pPoseStack, trailConsumer, 15728880, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 0.5f);

                pPoseStack.popPose();
            }
        }
    }
    public static void renderAuraBase(AbstractClientPlayer player, PoseStack poseStack, MultiBufferSource buffer, int packedLight, float partialTicks, float transparencia, int colorAura) {
        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();

        // Obtener posición de la cámara
        double camX = camera.getPosition().x;
        double camY = camera.getPosition().y;
        double camZ = camera.getPosition().z;


        // Obtener posición interpolada del jugador
        double interpX = Mth.lerp(partialTicks, player.xOld, player.getX());
        double interpY = Mth.lerp(partialTicks, player.yOld, player.getY());
        double interpZ = Mth.lerp(partialTicks, player.zOld, player.getZ());


        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {

            var race = cap.getIntValue("race");
            var transf = cap.getStringValue("form");
            var colorAuraFin = 0;
            var colorssj = 16773748;

            if(!transf.equals("base")){
                switch (race){
                    case 1:
                        switch (transf){
                            case "ssj1","ssgrade2","ssgrade3","ssjfp","ssj2","ssj3","goldenoozaru" -> colorAuraFin = colorssj;
                            default -> colorAuraFin = colorAura;
                        }
                    break;
                    default:
                        colorAuraFin = colorAura;
                        break;
                }
            } else {
                colorAuraFin = colorAura;
            }

            // Descomponer el color en sus componentes RGBA
            float red = (colorAuraFin >> 16 & 255) / 255.0f;
            float green = (colorAuraFin >> 8 & 255) / 255.0f;
            float blue = (colorAuraFin & 255) / 255.0f;

            //ACA YA FUNCIONA
            poseStack.pushPose();


            //Ajustar posición del aura en el jugador
            poseStack.translate(interpX - camX, interpY - camY + player.getEyeHeight(), interpZ - camZ);

            poseStack.mulPose(Axis.XP.rotationDegrees(180f));

            float rotationAngle = 0.0F;
            rotationAngle = (player.tickCount + partialTicks) * 5.0F; // Ajusta la velocidad aquí

            float rotationAngle2 = 0.0F;
            rotationAngle2 = (player.tickCount + partialTicks) * -7.0F; // Ajusta la velocidad aquí

            VertexConsumer vertexConsumer = buffer.getBuffer(CustomRenderTypes.energy(TextureManager.AURA_BASE));

            switch (race){
                case 1:
                    switch (transf){
                        case "oozaru", "goldenoozaru":
                            poseStack.scale(2.3f,2.5f,2.3f);
                            break;
                        case "ssgrade3":
                            poseStack.scale(1.3f,1.3f,1.3f);
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    switch (transf){
                        case "giant":
                            poseStack.scale(2.3f,2.5f,2.3f);
                            break;
                        case "super_namek":
                            poseStack.scale(1.3f,1.3f,1.3f);
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    break;
                case 4:
                    switch (transf){
                        case "second_form":
                            poseStack.scale(1.3f,1.3f,1.3f);
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    break;
                default:
                    switch (transf){
                        case "buffed":
                            poseStack.scale(1.3f,1.3f,1.3f);
                            break;
                        default:
                            break;
                    }
                    break;
            }
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

        });


    }

}
