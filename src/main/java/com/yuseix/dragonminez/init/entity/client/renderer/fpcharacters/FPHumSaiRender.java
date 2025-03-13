package com.yuseix.dragonminez.init.entity.client.renderer.fpcharacters;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.character.models.HumanSaiyanModel;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.entity.client.model.characters.FPHairsLayer;
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
public class FPHumSaiRender extends LivingEntityRenderer<FPBase, PlayerModel<FPBase>> {

    private float colorR, colorG, colorB;

    public FPHumSaiRender(EntityRendererProvider.Context pContext, PlayerModel<FPBase>model) {
        super(pContext,model, 0.5f);
        this.addLayer(new HumanoidArmorLayer(this, new HumanoidArmorModel(pContext.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidArmorModel(pContext.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), pContext.getModelManager()));
        this.addLayer(new FPHairsLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(FPBase fpHumanSaiyanEntity) {
        return new ResourceLocation(Reference.MOD_ID,"textures/entity/prueba.png");
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

                int bodyType = cap.getIntValue("bodytype");
                var genero = cap.getStringValue("gender");
                boolean isMajinOn = cap.hasDMZPermaEffect("majin");

                if (bodyType == 0) {

                    if(Minecraft.getInstance().player.getModelName().equals("default")){
                        renderBodyType0(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                    } else {
                        renderFEMBodyType0(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                    }

                } else if (bodyType > 0) {
                    pPoseStack.translate(0f,0f,0f);

                    //CUERPO CUSTOM 1
                    if (bodyType == 1) {
                        if(genero.equals("male")){
                            renderBodyType1(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                        } else {
                            renderFEMBodyType1(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                        }
                    }

                    //RENDER EYES
                    if(genero.equals("male")){
                        renderEyes(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                    } else {
                        renderFEMALEEyes(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                    }
                }

                if(isMajinOn){
                    renderMajinMarca(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
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

        var delineado1 = new ResourceLocation(Reference.MOD_ID, "textures/entity/races/humansaiyan/eyes/mmarca_eyestype1.png");
        var delineado2 = new ResourceLocation(Reference.MOD_ID, "textures/entity/races/humansaiyan/eyes/mmarca_eyestype2.png");

        HumanSaiyanModel<AbstractClientPlayer> playermodel = (HumanSaiyanModel)this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            if(cap.hasDMZPermaEffect("majin")){
                //Renderizamos la marca majin
                pPoseStack.translate(0f,0f,-0.002f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJINMARCA)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                //Comprobamos si no es la skin por defecto de mc, si no lo es se renderiza los delineados
                if(cap.getIntValue("bodytype") > 0){
                    if(cap.getIntValue("eyestype") == 0){

                        //DELINEADO
                        pPoseStack.translate(0f,0f,-0.002f);
                        playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(delineado1)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                    } else if(cap.getIntValue("eyestype") == 1){
                        //DELINEADO
                        pPoseStack.translate(0f,0f,-0.002f);
                        playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(delineado2)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                    }
                }


            }

        });
    }

    private void renderEyes(FPBase pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        var playermodel = this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {


            int eye1color = cap.getIntValue("eye1color");
            int eye2color = cap.getIntValue("eye2color");
            int cabellocolor = cap.getIntValue("haircolor");

            if(cap.getIntValue("eyestype") == 0){

                //OJOS BLANCOS
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_EYES1)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                //CEJAS Y COLOR DE CEJAS
                colorR = (cabellocolor >> 16) / 255.0F;
                colorG = ((cabellocolor >> 8) & 0xff) / 255.0f;
                colorB = (cabellocolor & 0xff) / 255.0f;
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_EYES1_CEJAS)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

                //IRIS 1 Y COLOR DE IRIS
                colorR = (eye1color >> 16) / 255.0F;
                colorG = ((eye1color >> 8) & 0xff) / 255.0f;
                colorB = (eye1color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_IRIS1)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

                //IRIS 2 Y COLOR DE IRIS
                colorR = (eye2color >> 16) / 255.0F;
                colorG = ((eye2color >> 8) & 0xff) / 255.0f;
                colorB = (eye2color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_IRIS2)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

            } else if(cap.getIntValue("eyestype") == 1){
                //OJOS BLANCOS
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_2_EYES1)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                //CEJAS Y COLOR DE CEJAS
                colorR = (cabellocolor >> 16) / 255.0F;
                colorG = ((cabellocolor >> 8) & 0xff) / 255.0f;
                colorB = (cabellocolor & 0xff) / 255.0f;
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_EYES1_CEJAS)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

                //IRIS 1 Y COLOR DE IRIS
                colorR = (eye1color >> 16) / 255.0F;
                colorG = ((eye1color >> 8) & 0xff) / 255.0f;
                colorB = (eye1color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_2_IRIS1)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

                //IRIS 2 Y COLOR DE IRIS
                colorR = (eye2color >> 16) / 255.0F;
                colorG = ((eye2color >> 8) & 0xff) / 255.0f;
                colorB = (eye2color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_2_IRIS2)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

            }


        });
    }

    private void renderFEMALEEyes(FPBase pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        var playermodel = this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {


            int eye1color = cap.getIntValue("eye1color");
            int eye2color = cap.getIntValue("eye2color");
            int cabellocolor = cap.getIntValue("haircolor");

            if(cap.getIntValue("eyestype") == 0){

                //OJOS BLANCOS
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_EYES1)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                //CEJAS Y COLOR DE CEJAS
                colorR = (cabellocolor >> 16) / 255.0F;
                colorG = ((cabellocolor >> 8) & 0xff) / 255.0f;
                colorB = (cabellocolor & 0xff) / 255.0f;
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_EYES1_CEJAS)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

                //IRIS 1 Y COLOR DE IRIS
                colorR = (eye1color >> 16) / 255.0F;
                colorG = ((eye1color >> 8) & 0xff) / 255.0f;
                colorB = (eye1color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_IRIS1)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

                //IRIS 2 Y COLOR DE IRIS
                colorR = (eye2color >> 16) / 255.0F;
                colorG = ((eye2color >> 8) & 0xff) / 255.0f;
                colorB = (eye2color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_IRIS2)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

            } else if(cap.getIntValue("eyestype") == 1){
                //OJOS BLANCOS
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_2_EYES1)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                //CEJAS Y COLOR DE CEJAS
                colorR = (cabellocolor >> 16) / 255.0F;
                colorG = ((cabellocolor >> 8) & 0xff) / 255.0f;
                colorB = (cabellocolor & 0xff) / 255.0f;
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_EYES1_CEJAS)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

                //IRIS 1 Y COLOR DE IRIS
                colorR = (eye1color >> 16) / 255.0F;
                colorG = ((eye1color >> 8) & 0xff) / 255.0f;
                colorB = (eye1color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_2_IRIS1)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

                //IRIS 2 Y COLOR DE IRIS
                colorR = (eye2color >> 16) / 255.0F;
                colorG = ((eye2color >> 8) & 0xff) / 255.0f;
                colorB = (eye2color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_2_IRIS2)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

            }


        });
    }
    private void renderBodyType0(FPBase pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        var playermodel = this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            //RENDERIZAR EL CUERPO ENTERO
            playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(Minecraft.getInstance().player.getSkinTextureLocation())), pPackedLight, i, 1.0f, 1.0f, 1.0f, flag1 ? 0.15F : 1.0F);

        });
    }
    private void renderFEMBodyType0(FPBase pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        var playermodel = this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            //RENDERIZAR EL CUERPO ENTERO
            playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(Minecraft.getInstance().player.getSkinTextureLocation())), pPackedLight, i, 1.0f, 1.0f, 1.0f, flag1 ? 0.15F : 1.0F);

        });
    }

    private void renderBodyType1(FPBase pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        var playermodel = this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            int bodyColor1 = cap.getIntValue("bodycolor");

                colorR = (bodyColor1 >> 16) / 255.0F;
                colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
                colorB = (bodyColor1 & 0xff) / 255.0f;
                //RENDERIZAR EL CUERPO ENTERO
                playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_BODY1)), pPackedLight, i, colorR, colorG, colorB, flag1 ? 0.15F : 1.0F);



        });

    }
    private void renderFEMBodyType1(FPBase pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        var playermodel = this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            int bodyColor1 = cap.getIntValue("bodycolor");

                colorR = (bodyColor1 >> 16) / 255.0F;
                colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
                colorB = (bodyColor1 & 0xff) / 255.0f;
                //RENDERIZAR EL CUERPO ENTERO
                playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_BODY1_FEM)), pPackedLight, i, colorR, colorG, colorB, flag1 ? 0.15F : 1.0F);

        });

    }

}
