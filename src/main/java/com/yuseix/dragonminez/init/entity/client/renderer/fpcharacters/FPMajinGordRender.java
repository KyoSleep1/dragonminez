package com.yuseix.dragonminez.init.entity.client.renderer.fpcharacters;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.character.layer.FatArmorLayer;
import com.yuseix.dragonminez.client.character.models.majin.MajinGordoModel;
import com.yuseix.dragonminez.init.entity.custom.fpcharacters.FPBase;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.TextureManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Iterator;

@OnlyIn(Dist.CLIENT)
public class FPMajinGordRender extends LivingEntityRenderer<FPBase, PlayerModel<FPBase>> {

    private float colorR, colorG, colorB;

    public FPMajinGordRender(EntityRendererProvider.Context pContext) {
        super(pContext, new MajinGordoModel<>(pContext.bakeLayer(MajinGordoModel.LAYER_LOCATION)), 0.5f);

        this.addLayer(new FatArmorLayer(this,
                new HumanoidArmorModel(pContext.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HumanoidArmorModel(pContext.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), pContext.getModelManager()));


    }

    @Override
    public void render(FPBase pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        var playermodel = this.getModel();

        pPoseStack.pushPose();
        pPoseStack.scale(0.9375F, 0.9375F, 0.9375F);
        playermodel.attackTime = this.getAttackAnim(pEntity, pPartialTicks);
        boolean shouldSit = pEntity.isPassenger() && pEntity.getVehicle() != null && pEntity.getVehicle().shouldRiderSit();
        playermodel.riding = shouldSit;
        playermodel.young = pEntity.isBaby();
        float f = Mth.rotLerp(pPartialTicks, pEntity.yBodyRotO, pEntity.yBodyRot);
        float f1 = Mth.rotLerp(pPartialTicks, pEntity.yHeadRotO, pEntity.yHeadRot);
        float f2 = f1 - f;
        float f7;
        if (shouldSit && pEntity.getVehicle() instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)pEntity.getVehicle();
            f = Mth.rotLerp(pPartialTicks, livingentity.yBodyRotO, livingentity.yBodyRot);
            f2 = f1 - f;
            f7 = Mth.wrapDegrees(f2);
            if (f7 < -85.0F) {
                f7 = -85.0F;
            }

            if (f7 >= 85.0F) {
                f7 = 85.0F;
            }

            f = f1 - f7;
            if (f7 * f7 > 2500.0F) {
                f += f7 * 0.2F;
            }

            f2 = f1 - f;
        }

        float f6 = Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot());
        if (isEntityUpsideDown(pEntity)) {
            f6 *= -1.0F;
            f2 *= -1.0F;
        }

        float f8;
        if (pEntity.hasPose(Pose.SLEEPING)) {
            Direction direction = pEntity.getBedOrientation();
            if (direction != null) {
                f8 = pEntity.getEyeHeight(Pose.STANDING) - 0.1F;
                pPoseStack.translate((float)(-direction.getStepX()) * f8, 0.0F, (float)(-direction.getStepZ()) * f8);
            }
        }

        f7 = this.getBob(pEntity, pPartialTicks);
        this.setupRotations(pEntity, pPoseStack, f7, f, pPartialTicks);
        pPoseStack.scale(-1.0F, -1.0F, 1.0F);
        this.scale(pEntity, pPoseStack, pPartialTicks);
        pPoseStack.translate(0.0F, -1.501F, 0.0F);
        f8 = 0.0F;
        float f5 = 0.0F;
        if (!shouldSit && pEntity.isAlive()) {
            f8 = pEntity.walkAnimation.speed(pPartialTicks);
            f5 = pEntity.walkAnimation.position(pPartialTicks);
            if (pEntity.isBaby()) {
                f5 *= 3.0F;
            }

            if (f8 > 1.0F) {
                f8 = 1.0F;
            }
        }

        playermodel.prepareMobModel(pEntity, f5, f8, pPartialTicks);
        playermodel.setupAnim(pEntity, f5, f8, Minecraft.getInstance().player.tickCount + pPartialTicks, f2, f6);
        Minecraft minecraft = Minecraft.getInstance();
        boolean flag = this.isBodyVisible(pEntity);
        boolean flag1 = !flag && !pEntity.isInvisibleTo(minecraft.player);
        boolean flag2 = minecraft.shouldEntityAppearGlowing(pEntity);

        RenderType rendertype = getRenderType(pEntity,flag,flag1,flag2);

        if (rendertype != null) {
            int i = getOverlayCoords(pEntity, this.getWhiteOverlayProgress(pEntity, pPartialTicks));

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

                int bodyType = cap.getBodytype();
                boolean isMajinOn = cap.hasDMZPermaEffect("majin");

                if (bodyType == 0) {
                    renderBodyType0(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);

                    //RENDER EYES
                    renderEyes(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);

                    if(isMajinOn){
                        renderMajinMarca(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                    }

                } else {
                    renderBodyType1(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);

                    //RENDER EYES
                    renderEyes(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);

                    if(isMajinOn){
                        renderMajinMarca(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                    }
                }



            });

        }

        if (!pEntity.isSpectator()) {
            Iterator var24 = this.layers.iterator();

            while(var24.hasNext()) {
                RenderLayer<AbstractClientPlayer, EntityModel<AbstractClientPlayer>> renderlayer = (RenderLayer)var24.next();
                renderlayer.render(pPoseStack, pBuffer, pPackedLight, Minecraft.getInstance().player, f5, f8, pPartialTicks, f7, f2, f6);
            }
        }

        pPoseStack.popPose();



    }

    private void renderMajinMarca(FPBase pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        var playermodel = this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            if(cap.hasDMZPermaEffect("majin")){
                //Renderizamos la marca majin para todos
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJINMARCA_RM)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

            }

        });
    }
    private void renderBodyType0(FPBase pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        var playermodel = this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            int bodyColor1 = cap.getBodyColor();
            int hair = cap.getHairID();

            colorR = (bodyColor1 >> 16) / 255.0F;
            colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor1 & 0xff) / 255.0f;
            //RENDERIZAR EL CUERPO ENTERO
            playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJIN_BASE_TYPE1_MALE)), pPackedLight, i, colorR, colorG, colorB, flag1 ? 0.15F : 1.0F);


            if(hair == 0){
                playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityCutout(TextureManager.MAJIN_EARS1)), pPackedLight, i, colorR, colorG, colorB, flag1 ? 0.15F : 1.0F);
            } else {
                playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityCutout(TextureManager.MAJIN_EARS2)), pPackedLight, i, colorR, colorG, colorB, flag1 ? 0.15F : 1.0F);
            }

        });

    }

    private void renderBodyType1(FPBase pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        var playermodel = this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            int bodyColor1 = cap.getBodyColor();
            int hair = cap.getHairID();

            colorR = (bodyColor1 >> 16) / 255.0F;
            colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor1 & 0xff) / 255.0f;
            //RENDERIZAR EL CUERPO ENTERO
            //BODYCOLOR1
            playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJIN_BASE_TYPE2_MALE_1)), pPackedLight, i, colorR, colorG, colorB, flag1 ? 0.15F : 1.0F);
            //BODYCOLOR2
            int bodyColor2 = cap.getBodyColor2();
            colorR = (bodyColor2 >> 16) / 255.0F;
            colorG = ((bodyColor2 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor2 & 0xff) / 255.0f;
            playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJIN_BASE_TYPE2_MALE_2)), pPackedLight, i, colorR, colorG, colorB, flag1 ? 0.15F : 1.0F);


            //OREJAS
            colorR = (bodyColor1 >> 16) / 255.0F;
            colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor1 & 0xff) / 255.0f;
            if(hair == 0){
                playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityCutout(TextureManager.MAJIN_EARS1)), pPackedLight, i, colorR, colorG, colorB, flag1 ? 0.15F : 1.0F);
            } else {
                playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityCutout(TextureManager.MAJIN_EARS2)), pPackedLight, i, colorR, colorG, colorB, flag1 ? 0.15F : 1.0F);
            }

        });

    }
    private void renderEyes(FPBase pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        var playermodel = this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            int bodyColor1 = cap.getBodyColor();
            int ojoscolorbase = cap.getEye1Color();
            int ojostype = cap.getEyesType();

            colorR = (bodyColor1 >> 16) / 255.0F;
            colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor1 & 0xff) / 255.0f;

            if(ojostype == 0){
                //OJOS
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJIN_BASE_MALE_EYES)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);
            } else {
                //OJOS BLANCOS
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJIN_BASE_FEMALE_EYES_BASE)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                //IRIS COLORES
                colorR = (ojoscolorbase >> 16) / 255.0F;
                colorG = ((ojoscolorbase >> 8) & 0xff) / 255.0f;
                colorB = (ojoscolorbase & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJIN_BASE_FEMALE_EYES_IRIS)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

            }
        });
    }


    @Override
    public ResourceLocation getTextureLocation(FPBase abstractClientPlayer) {
        return new ResourceLocation(DragonMineZ.MOD_ID,"textures/entity/prueba.png");
    }

}
