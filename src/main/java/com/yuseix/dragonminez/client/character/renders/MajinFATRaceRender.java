package com.yuseix.dragonminez.client.character.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.character.RenderManos;
import com.yuseix.dragonminez.client.character.layer.ArmasLayer;
import com.yuseix.dragonminez.client.character.layer.ArmorCapeLayer;
import com.yuseix.dragonminez.client.character.layer.FatArmorLayer;
import com.yuseix.dragonminez.client.character.layer.HairsLayer;
import com.yuseix.dragonminez.client.character.models.AuraModel;
import com.yuseix.dragonminez.client.character.models.kiweapons.KiScytheModel;
import com.yuseix.dragonminez.client.character.models.kiweapons.KiTridentModel;
import com.yuseix.dragonminez.client.character.models.majin.MajinGordoModel;
import com.yuseix.dragonminez.client.util.shader.CustomRenderTypes;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.TextureManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.eventbus.api.Event;

import java.util.Iterator;

@OnlyIn(Dist.CLIENT)
public class MajinFATRaceRender extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> implements DmzRenderer{

    private float colorR, colorG, colorB;
    public static final KiScytheModel kiScytheModel = new KiScytheModel(KiScytheModel.createBodyLayer().bakeRoot());
    public static final AuraModel AURA_MODEL = new AuraModel(AuraModel.createBodyLayer().bakeRoot());
    public static final KiTridentModel kiTridentModel = new KiTridentModel(KiTridentModel.createBodyLayer().bakeRoot());

    public MajinFATRaceRender(EntityRendererProvider.Context pContext, PlayerModel<AbstractClientPlayer> pModel) {
        super(pContext, pModel, 0.5f);

        this.addLayer(new FatArmorLayer(this,
                new HumanoidArmorModel(pContext.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HumanoidArmorModel(pContext.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), pContext.getModelManager()));

        this.addLayer(new PlayerItemInHandLayer(this, pContext.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer(this, pContext.getModelSet()));
        this.addLayer(new ParrotOnShoulderLayer(this, pContext.getModelSet()));
        this.addLayer(new SpinAttackEffectLayer(this, pContext.getModelSet()));
        this.addLayer(new BeeStingerLayer(this));
        this.addLayer(new ArmasLayer(this));
        this.addLayer(new ArmorCapeLayer(this));
        this.addLayer(new HairsLayer(this));


    }

    @Override
    public void render(AbstractClientPlayer pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.setModelProperties(pEntity);

        PlayerModel<AbstractClientPlayer> playermodel = (PlayerModel)this.getModel();

        RenderNameTagEvent renderNameTagEvent = new RenderNameTagEvent(pEntity, pEntity.getDisplayName(), this, pPoseStack, pBuffer, pPackedLight, pPartialTicks);

        pPoseStack.pushPose();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {
                pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano default de jugador
        });

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
        playermodel.setupAnim(pEntity, f5, f8, f7, f2, f6);
        Minecraft minecraft = Minecraft.getInstance();
        boolean flag = this.isBodyVisible(pEntity);
        boolean flag1 = !flag && !pEntity.isInvisibleTo(minecraft.player);
        boolean flag2 = minecraft.shouldEntityAppearGlowing(pEntity);

        RenderType rendertype = getRenderType(pEntity,flag,flag1,flag2);

        if (!pEntity.isSpectator()) {
            Iterator var24 = this.layers.iterator();

            while(var24.hasNext()) {
                RenderLayer<AbstractClientPlayer, EntityModel<AbstractClientPlayer>> renderlayer = (RenderLayer)var24.next();
                renderlayer.render(pPoseStack, pBuffer, pPackedLight, pEntity, f5, f8, pPartialTicks, f7, f2, f6);
            }
        }

        if (rendertype != null) {
            int i = getOverlayCoords(pEntity, this.getWhiteOverlayProgress(pEntity, pPartialTicks));

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

                int bodyType = cap.getIntValue("bodytype");
                boolean isMajinOn = cap.hasDMZPermaEffect("majin");

                if (bodyType == 0) {
                    renderBodyType0(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                } else {
                    renderBodyType1(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                }

                renderEyes(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);

                if(isMajinOn){
                    renderMajinMarca(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                }
            });

        }



        pPoseStack.popPose();

        if (renderNameTagEvent.getResult() != Event.Result.DENY && (renderNameTagEvent.getResult() == Event.Result.ALLOW || this.shouldShowName(pEntity))) {
            this.renderNameTag(pEntity, renderNameTagEvent.getContent(), pPoseStack, pBuffer, pPackedLight);
        }


    }

    public void renderOnWorld(AbstractClientPlayer entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

        poseStack.pushPose();

        boolean isLocalPlayer = entity == Minecraft.getInstance().player;
        boolean isFirstPerson = Minecraft.getInstance().options.getCameraType().isFirstPerson();

        float f = Mth.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);

        setupRotations(entity, poseStack, getBob(entity, partialTicks), f, partialTicks);
        poseStack.scale(-1, -1, 1);
        poseStack.translate(0.0F, -1.501F, 0.0F);

        // A partir de acá se puede renderizar cualquier cosa
        if (!isLocalPlayer || !isFirstPerson) {
            renderKiWeapons(entity, poseStack, buffer, packedLight, partialTicks);

        }

        poseStack.popPose();
    }

    private void renderKiWeapons(AbstractClientPlayer player, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float partialTicks) {


        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {

            var ki_control = cap.hasSkill("ki_control");
            var ki_manipulation = cap.hasSkill("ki_manipulation");
            var meditation = cap.hasSkill("meditation");

            var is_kimanipulation = cap.isActiveSkill("ki_manipulation");
            var kiweapon_id = cap.getStringValue("kiweapon");

            var raza = cap.getIntValue("race");
            var transf = cap.getStringValue("form");
            var auraColor = 0;

            switch (raza){
                case 1:
                    switch (transf){
                        case "ssj1","ssgrade2","ssgrade3" -> auraColor = 16773525;
                        case "ssjfp", "ssj2","ssj3" -> auraColor = 16770889; // El SSJFP tiene un color más pastel (Visto en la saga de Cell cuando Goku sale de la Hab del Tiempo)
                        default -> auraColor = cap.getIntValue("auracolor");
                    }
                    break;
                case 2:
                    auraColor = cap.getIntValue("auracolor");
                    break;
                case 3:
                    switch (transf){
                        case "perfect" -> auraColor = 16773525;
                        default -> auraColor = cap.getIntValue("auracolor");
                    }
                    break;
                case 4:
                    auraColor = cap.getIntValue("auracolor");
                    break;
                case 5:
                    auraColor = cap.getIntValue("auracolor");
                    break;
                default:
                    auraColor = cap.getIntValue("auracolor");
                    break;
            }

            var colorR = (auraColor >> 16) / 255.0F;
            var colorG = ((auraColor >> 8) & 0xff) / 255.0f;
            var colorB = (auraColor & 0xff) / 255.0f;

            if(ki_control && ki_manipulation && meditation && is_kimanipulation){
                switch (kiweapon_id){
                    case "scythe":
                        kiScytheModel.translateToHand(player.getMainArm(), poseStack);
                        getModel().rightArm.translateAndRotate(poseStack);

                        // Renderizar el modelo personalizado
                        kiScytheModel.scythe.x = 6.0f;
                        kiScytheModel.scythe.y = -1.0f;
                        VertexConsumer vertexScythe = bufferSource.getBuffer(CustomRenderTypes.energy2(RenderManos.SCYTHE_TEX));
                        kiScytheModel.renderToBuffer(poseStack, vertexScythe, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

                        break;
                    case "trident":
                        kiTridentModel.translateToHand(player.getMainArm(), poseStack);
                        getModel().rightArm.translateAndRotate(poseStack);

                        kiTridentModel.trident.x = 5.5f;
                        kiTridentModel.trident.y = -1f;
                        VertexConsumer vertexTrident = bufferSource.getBuffer(CustomRenderTypes.energy2(RenderManos.TRIDENT_TEX));
                        kiTridentModel.renderToBuffer(poseStack, vertexTrident, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

                        break;
                    default:
                        renderKiSword(player,poseStack,bufferSource,packedLight,OverlayTexture.NO_OVERLAY,0.5f,auraColor);
                        break;
                }

            }


        });
    }

    private void renderKiSword(AbstractClientPlayer player, PoseStack poseStack, MultiBufferSource buffer, int packedLight, float partialTicks, float transparencia, int colorAura) {
        // Descomponer el color en sus componentes RGBA
        float red = (colorAura >> 16 & 255) / 255.0f;
        float green = (colorAura >> 8 & 255) / 255.0f;
        float blue = (colorAura & 255) / 255.0f;

        //ACA YA FUNCIONA
        poseStack.pushPose();

        //Ajustar posición del aura en el jugador
        AURA_MODEL.translateToHand(player.getMainArm(), poseStack);
        getModel().rightArm.translateAndRotate(poseStack);

        poseStack.scale(0.15f,0.23f,0.2f);
        poseStack.translate(1.8f,2.5f,0.0f);
        poseStack.mulPose(Axis.XP.rotationDegrees(180f));

        float rotationAngle = 0.0F;
        rotationAngle = (player.tickCount + partialTicks) * 5.0F; // Ajusta la velocidad aquí

        float rotationAngle2 = 0.0F;
        rotationAngle2 = (player.tickCount + partialTicks) * -7.0F; // Ajusta la velocidad aquí

        VertexConsumer vertexConsumer = buffer.getBuffer(CustomRenderTypes.energy2(TextureManager.AURA_BASE));


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
            poseStack.scale(1.1F, 1.8F, 1.1F);

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


        poseStack.popPose();
    }


    private void renderMajinMarca(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        MajinGordoModel<AbstractClientPlayer> playermodel = (MajinGordoModel)this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

            if(cap.hasDMZPermaEffect("majin")){
                //Renderizamos la marca majin para todos
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJINMARCA_RM)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

            }

        });
    }

    private void renderBodyType0(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        MajinGordoModel<AbstractClientPlayer> playermodel = (MajinGordoModel)this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

            int bodyColor1 = cap.getIntValue("bodycolor");
            int hair = cap.getIntValue("hairid");

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

    private void renderBodyType1(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        MajinGordoModel<AbstractClientPlayer> playermodel = (MajinGordoModel)this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

            int bodyColor1 = cap.getIntValue("bodycolor");
            int hair = cap.getIntValue("hairid");

            colorR = (bodyColor1 >> 16) / 255.0F;
            colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor1 & 0xff) / 255.0f;
            //RENDERIZAR EL CUERPO ENTERO
            //BODYCOLOR1
            playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJIN_BASE_TYPE2_MALE_1)), pPackedLight, i, colorR, colorG, colorB, flag1 ? 0.15F : 1.0F);
            //BODYCOLOR2
            int bodyColor2 = cap.getIntValue("bodycolor2");
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

    private void renderEyes(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        MajinGordoModel<AbstractClientPlayer> playermodel = (MajinGordoModel)this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

            int bodyColor1 = cap.getIntValue("bodycolor");
            var ojoscolorbase = cap.getIntValue("eye1color");
            int tipoojos = cap.getIntValue("eyestype");

            colorR = (bodyColor1 >> 16) / 255.0F;
            colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor1 & 0xff) / 255.0f;

            if(tipoojos == 0){
                //OJOS
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucentCull(TextureManager.MAJIN_BASE_MALE_EYES)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);
            } else {

                //OJOS NEGROS
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucentCull(TextureManager.MAJIN_BASE_FEMALE_EYES_BASE)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                //IRIS COLORES
                colorR = (ojoscolorbase >> 16) / 255.0F;
                colorG = ((ojoscolorbase >> 8) & 0xff) / 255.0f;
                colorB = (ojoscolorbase & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucentCull(TextureManager.MAJIN_BASE_FEMALE_EYES_IRIS)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

            }

        });
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayer abstractClientPlayer) {
        return new ResourceLocation(Reference.MOD_ID,"textures/entity/prueba.png");
    }


    private void setModelProperties(AbstractClientPlayer pClientPlayer) {
        PlayerModel<AbstractClientPlayer> playermodel = this.getModel();
        if (pClientPlayer.isSpectator()) {
            playermodel.setAllVisible(false);
            playermodel.hat.visible = true;
            playermodel.head.visible = true;
        } else {
            playermodel.setAllVisible(true);
            playermodel.hat.visible = pClientPlayer.isModelPartShown(PlayerModelPart.HAT);
            playermodel.jacket.visible = pClientPlayer.isModelPartShown(PlayerModelPart.JACKET);
            playermodel.leftPants.visible = pClientPlayer.isModelPartShown(PlayerModelPart.LEFT_PANTS_LEG);
            playermodel.rightPants.visible = pClientPlayer.isModelPartShown(PlayerModelPart.RIGHT_PANTS_LEG);
            playermodel.leftSleeve.visible = pClientPlayer.isModelPartShown(PlayerModelPart.LEFT_SLEEVE);
            playermodel.rightSleeve.visible = pClientPlayer.isModelPartShown(PlayerModelPart.RIGHT_SLEEVE);
            playermodel.crouching = pClientPlayer.isCrouching();
            HumanoidModel.ArmPose humanoidmodel$armpose = getArmPose(pClientPlayer, InteractionHand.MAIN_HAND);
            HumanoidModel.ArmPose humanoidmodel$armpose1 = getArmPose(pClientPlayer, InteractionHand.OFF_HAND);
            if (humanoidmodel$armpose.isTwoHanded()) {
                humanoidmodel$armpose1 = pClientPlayer.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
            }

            if (pClientPlayer.getMainArm() == HumanoidArm.RIGHT) {
                playermodel.rightArmPose = humanoidmodel$armpose;
                playermodel.leftArmPose = humanoidmodel$armpose1;
            } else {
                playermodel.rightArmPose = humanoidmodel$armpose1;
                playermodel.leftArmPose = humanoidmodel$armpose;
            }
        }

    }

    private static HumanoidModel.ArmPose getArmPose(AbstractClientPlayer pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.isEmpty()) {
            return HumanoidModel.ArmPose.EMPTY;
        } else {
            if (pPlayer.getUsedItemHand() == pHand && pPlayer.getUseItemRemainingTicks() > 0) {
                UseAnim useanim = itemstack.getUseAnimation();
                if (useanim == UseAnim.BLOCK) {
                    return HumanoidModel.ArmPose.BLOCK;
                }

                if (useanim == UseAnim.BOW) {
                    return HumanoidModel.ArmPose.BOW_AND_ARROW;
                }

                if (useanim == UseAnim.SPEAR) {
                    return HumanoidModel.ArmPose.THROW_SPEAR;
                }

                if (useanim == UseAnim.CROSSBOW && pHand == pPlayer.getUsedItemHand()) {
                    return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
                }

                if (useanim == UseAnim.SPYGLASS) {
                    return HumanoidModel.ArmPose.SPYGLASS;
                }

                if (useanim == UseAnim.TOOT_HORN) {
                    return HumanoidModel.ArmPose.TOOT_HORN;
                }

                if (useanim == UseAnim.BRUSH) {
                    return HumanoidModel.ArmPose.BRUSH;
                }
            } else if (!pPlayer.swinging && itemstack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(itemstack)) {
                return HumanoidModel.ArmPose.CROSSBOW_HOLD;
            }

            HumanoidModel.ArmPose forgeArmPose = IClientItemExtensions.of(itemstack).getArmPose(pPlayer, pHand, itemstack);
            return forgeArmPose != null ? forgeArmPose : HumanoidModel.ArmPose.ITEM;
        }
    }

    @Override
    protected void setupRotations(AbstractClientPlayer pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        float f = pEntityLiving.getSwimAmount(pPartialTicks);
        float f3;
        float f2;
        if (pEntityLiving.isFallFlying()) {
            super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
            f3 = (float)pEntityLiving.getFallFlyingTicks() + pPartialTicks;
            f2 = Mth.clamp(f3 * f3 / 100.0F, 0.0F, 1.0F);
            if (!pEntityLiving.isAutoSpinAttack()) {
                pPoseStack.mulPose(Axis.XP.rotationDegrees(f2 * (-90.0F - pEntityLiving.getXRot())));
            }

            Vec3 vec3 = pEntityLiving.getViewVector(pPartialTicks);
            Vec3 vec31 = pEntityLiving.getDeltaMovementLerped(pPartialTicks);
            double d0 = vec31.horizontalDistanceSqr();
            double d1 = vec3.horizontalDistanceSqr();
            if (d0 > 0.0 && d1 > 0.0) {
                double d2 = (vec31.x * vec3.x + vec31.z * vec3.z) / Math.sqrt(d0 * d1);
                double d3 = vec31.x * vec3.z - vec31.z * vec3.x;
                pPoseStack.mulPose(Axis.YP.rotation((float)(Math.signum(d3) * Math.acos(d2))));
            }
        } else if (f > 0.0F) {
            super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
            f3 = !pEntityLiving.isInWater() && !pEntityLiving.isInFluidType((fluidType, height) -> {
                return pEntityLiving.canSwimInFluidType(fluidType);
            }) ? -90.0F : -90.0F - pEntityLiving.getXRot();
            f2 = Mth.lerp(f, 0.0F, f3);
            pPoseStack.mulPose(Axis.XP.rotationDegrees(f2));
            if (pEntityLiving.isVisuallySwimming()) {
                pPoseStack.translate(0.0F, -1.0F, 0.3F);
            }
        } else {
            super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        }
    }

}
