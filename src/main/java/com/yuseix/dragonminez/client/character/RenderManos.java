package com.yuseix.dragonminez.client.character;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.character.models.AuraModel;
import com.yuseix.dragonminez.client.character.models.kiweapons.KiScytheModel;
import com.yuseix.dragonminez.client.character.models.kiweapons.KiTridentModel;
import com.yuseix.dragonminez.client.util.shader.CustomRenderTypes;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.armor.DbzArmorItem;
import com.yuseix.dragonminez.init.armor.SaiyanArmorItem;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.TextureManager;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

@OnlyIn(Dist.CLIENT)
public class RenderManos extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public static final ResourceLocation SCYTHE_TEX = new ResourceLocation(Reference.MOD_ID, "textures/weapons/kiweapons/scytheweapon.png");
    public static final ResourceLocation TRIDENT_TEX = new ResourceLocation(Reference.MOD_ID, "textures/weapons/kiweapons/tridentweapon.png");

    private float colorR, colorG, colorB;

    public static final KiScytheModel kiScytheModel = new KiScytheModel(KiScytheModel.createBodyLayer().bakeRoot());
    public static final KiTridentModel kiTridentModel = new KiTridentModel(KiTridentModel.createBodyLayer().bakeRoot());
    public static final AuraModel AURA_MODEL = new AuraModel(AuraModel.createBodyLayer().bakeRoot());


    public RenderManos(EntityRendererProvider.Context pContext) {
        super(pContext, new PlayerModel(pContext.bakeLayer(ModelLayers.PLAYER), false), 0.5f);
        this.addLayer(new HumanoidArmorLayer(this, new HumanoidArmorModel(pContext.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidArmorModel(pContext.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), pContext.getModelManager()));
        this.addLayer(new PlayerItemInHandLayer(this, pContext.getItemInHandRenderer()));
        this.addLayer(new ArrowLayer(pContext, this));
        this.addLayer(new Deadmau5EarsLayer(this));
        this.addLayer(new CapeLayer(this));
        this.addLayer(new CustomHeadLayer(this, pContext.getModelSet(), pContext.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer(this, pContext.getModelSet()));
        this.addLayer(new ParrotOnShoulderLayer(this, pContext.getModelSet()));
        this.addLayer(new SpinAttackEffectLayer(this, pContext.getModelSet()));
        this.addLayer(new BeeStingerLayer(this));



    }

    public void renderRightHand(PoseStack pPoseStack, MultiBufferSource pBuffer, int pCombinedLight, AbstractClientPlayer pPlayer) {
        this.renderHand(pPoseStack, pBuffer, pCombinedLight, pPlayer, (this.model).rightArm, (this.model).rightSleeve);

        //Aca renderizar las armas
        KiWeapons(pPlayer, pPoseStack, pBuffer, pCombinedLight);


    }

    public void KiWeapons(AbstractClientPlayer player, PoseStack poseStack, MultiBufferSource bufferSource, int pCombinedLight){

        PlayerModel<AbstractClientPlayer> playerModel = this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {

            var colorKi = cap.getIntValue("auracolor");
            var ki_control = cap.hasSkill("ki_control");
            var ki_manipulation = cap.hasSkill("ki_manipulation");
            var meditation = cap.hasSkill("meditation");

            var is_kimanipulation = cap.isActiveSkill("ki_manipulation");

            var kiweapon_id = cap.getStringValue("kiweapon");

            var auraColor = 0;
            var transf = cap.getStringValue("form");
            var raza = cap.getIntValue("race");

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
                if(kiweapon_id.equals("scythe")){
                    poseStack.pushPose();

                    playerModel.rightArm.translateAndRotate(poseStack);
                    kiScytheModel.setupAnim(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
                    VertexConsumer vertexScythe = bufferSource.getBuffer(CustomRenderTypes.energy2(SCYTHE_TEX));
                    this.kiScytheModel.renderToBuffer(poseStack,vertexScythe, pCombinedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    poseStack.popPose();

                } else if(kiweapon_id.equals("trident")) {
                    poseStack.pushPose();

                    playerModel.rightArm.translateAndRotate(poseStack);
                    kiTridentModel.setupAnim(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
                    VertexConsumer vertexScythe = bufferSource.getBuffer(CustomRenderTypes.energy2(TRIDENT_TEX));
                    this.kiTridentModel.renderToBuffer(poseStack,vertexScythe, pCombinedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    poseStack.popPose();

                } else { //espada
                    poseStack.pushPose();
                    renderKiSword(player,poseStack,bufferSource,pCombinedLight,OverlayTexture.NO_OVERLAY,0.5f,auraColor);
                    poseStack.popPose();
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

        poseStack.scale(0.15f,0.23f,0.25f);
            poseStack.translate(2f,2.5f,0.2f);
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



    public void renderLeftHand(PoseStack pPoseStack, MultiBufferSource pBuffer, int pCombinedLight, AbstractClientPlayer pPlayer) {
        this.renderHand(pPoseStack, pBuffer, pCombinedLight, pPlayer, (this.model).leftArm, (this.model).leftSleeve);
    }

        @Override
    protected void setupRotations(AbstractClientPlayer pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
    }

    @Override
    public void render(AbstractClientPlayer pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    private void renderHand(PoseStack pPoseStack, MultiBufferSource pBuffer, int pCombinedLight, AbstractClientPlayer pPlayer, ModelPart pRendererArm, ModelPart pRendererArmwear) {
        PlayerModel<AbstractClientPlayer> playermodel = (PlayerModel) this.getModel();
        this.setModelProperties(pPlayer);
        playermodel.attackTime = 0.0F;
        playermodel.crouching = false;
        playermodel.swimAmount = 0.0F;
        playermodel.setupAnim(pPlayer, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        pRendererArm.xRot = 0.0F;

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE,pPlayer).ifPresent(cap -> {

            var raza = cap.getIntValue("race");
            var bodytype = cap.getIntValue("bodytype");
            var color1body = cap.getIntValue("bodycolor");
            var color2body = cap.getIntValue("bodycolor2");
            var color3body = cap.getIntValue("bodycolor3");
            var form = cap.getStringValue("form");

            switch (raza){
                case 0:
                    //HUMANO
                    if(bodytype == 0){
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entitySolid(pPlayer.getSkinTextureLocation())), pCombinedLight, OverlayTexture.NO_OVERLAY);
                    } else if(bodytype == 1){
                        colorR = (color1body >> 16) / 255.0F;
                        colorG = ((color1body >> 8) & 0xff) / 255.0f;
                        colorB = (color1body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entitySolid(TextureManager.SH_BODY1)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                    }

                    break;
                case 1:
                    switch (form){
                        case "oozaru":

                            var layer1 = 6888961;

                            colorR = (layer1 >> 16) / 255.0F;
                            colorG = ((layer1 >> 8) & 0xff) / 255.0f;
                            colorB = (layer1 & 0xff) / 255.0f;

                            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.OOZARU_1)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);

                            colorR = (14922657 >> 16) / 255.0F;
                            colorG = ((14922657 >> 8) & 0xff) / 255.0f;
                            colorB = (14922657 & 0xff) / 255.0f;
                            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.OOZARU_2)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);

                            break;
                        default:
                            //SAIYAN
                            if(bodytype == 0){
                                pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entitySolid(pPlayer.getSkinTextureLocation())), pCombinedLight, OverlayTexture.NO_OVERLAY);
                            } else if(bodytype == 1){
                                colorR = (color1body >> 16) / 255.0F;
                                colorG = ((color1body >> 8) & 0xff) / 255.0f;
                                colorB = (color1body & 0xff) / 255.0f;
                                pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entitySolid(TextureManager.SH_BODY1)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                            }
                            break;
                    }


                    break;
                case 2:
                    //NAMEK
                    colorR = (color1body >> 16) / 255.0F;
                    colorG = ((color1body >> 8) & 0xff) / 255.0f;
                    colorB = (color1body & 0xff) / 255.0f;
                    pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.N_BASE_BODY1_PART1)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                    colorR = (color2body >> 16) / 255.0F;
                    colorG = ((color2body >> 8) & 0xff) / 255.0f;
                    colorB = (color2body & 0xff) / 255.0f;
                    pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.N_BASE_BODY1_PART2)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                    colorR = (color3body >> 16) / 255.0F;
                    colorG = ((color3body >> 8) & 0xff) / 255.0f;
                    colorB = (color3body & 0xff) / 255.0f;
                    pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.N_BASE_BODY1_PART3)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);

                    break;
                case 3:
                    switch (form){
                        case "semi_perfect":
                            //BIOANDROID
                            colorR = (color1body >> 16) / 255.0F;
                            colorG = ((color1body >> 8) & 0xff) / 255.0f;
                            colorB = (color1body & 0xff) / 255.0f;
                            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.B_SEMI_BODY1)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                            colorR = (color2body >> 16) / 255.0F;
                            colorG = ((color2body >> 8) & 0xff) / 255.0f;
                            colorB = (color2body & 0xff) / 255.0f;
                            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.B_SEMI_BODY2)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                            colorR = (color3body >> 16) / 255.0F;
                            colorG = ((color3body >> 8) & 0xff) / 255.0f;
                            colorB = (color3body & 0xff) / 255.0f;
                            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.B_SEMI_BODY3)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                            break;
                        case "perfect":
                            colorR = (color1body >> 16) / 255.0F;
                            colorG = ((color1body >> 8) & 0xff) / 255.0f;
                            colorB = (color1body & 0xff) / 255.0f;
                            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.B_PERFECT_BODY1)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                            colorR = (16383998 >> 16) / 255.0F;
                            colorG = ((16383998 >> 8) & 0xff) / 255.0f;
                            colorB = (16383998 & 0xff) / 255.0f;
                            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.B_PERFECT_BODY2)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                            colorR = (color3body >> 16) / 255.0F;
                            colorG = ((color3body >> 8) & 0xff) / 255.0f;
                            colorB = (color3body & 0xff) / 255.0f;
                            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.B_PERFECT_BODY3)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);

                            break;
                        default:
                            //BIOANDROID
                            colorR = (color1body >> 16) / 255.0F;
                            colorG = ((color1body >> 8) & 0xff) / 255.0f;
                            colorB = (color1body & 0xff) / 255.0f;
                            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.B_IMPERFECT_BODY1)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                            colorR = (color2body >> 16) / 255.0F;
                            colorG = ((color2body >> 8) & 0xff) / 255.0f;
                            colorB = (color2body & 0xff) / 255.0f;
                            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.B_IMPERFECT_BODY2)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                            colorR = (color3body >> 16) / 255.0F;
                            colorG = ((color3body >> 8) & 0xff) / 255.0f;
                            colorB = (color3body & 0xff) / 255.0f;
                            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.B_IMPERFECT_BODY3)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);

                            break;
                    }
                    break;
                case 4:
                    //DEMON COLD
                    DEMONCOLD_ARMS(pPlayer, pPoseStack, pBuffer, pCombinedLight, pRendererArm);
                break;
                case 5:
                    switch (form){
                        case "evil":
                        colorR = (11314334 >> 16) / 255.0F;
                        colorG = ((11314334 >> 8) & 0xff) / 255.0f;
                        colorB = (11314334 & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entitySolid(TextureManager.SH_BODY1)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                        break;
                        default:
                            colorR = (color1body >> 16) / 255.0F;
                            colorG = ((color1body >> 8) & 0xff) / 255.0f;
                            colorB = (color1body & 0xff) / 255.0f;
                            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entitySolid(TextureManager.SH_BODY1)), pCombinedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                            break;
                    }
                    break;
                default:
                    break;
            }

            armadurasRender(pPlayer, pPoseStack, pBuffer, pCombinedLight,pRendererArm);

        });



        pRendererArmwear.xRot = 0.0F;

    }
    private void DEMONCOLD_ARMS(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, ModelPart pRendererArm){

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

            var bodytype = cap.getIntValue("bodytype");
            var color1body = cap.getIntValue("bodycolor");
            var color2body = cap.getIntValue("bodycolor2");
            var color3body = cap.getIntValue("bodycolor3");
            var color4body = cap.getIntValue("haircolor");
            var transf = cap.getStringValue("form");

            switch (transf){
                case "final_form":
                    if(bodytype == 0){
                        colorR = (color1body >> 16) / 255.0F;
                        colorG = ((color1body >> 8) & 0xff) / 255.0f;
                        colorB = (color1body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_TF_BODY1_PART1)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                        colorR = (color4body >> 16) / 255.0F;
                        colorG = ((color4body >> 8) & 0xff) / 255.0f;
                        colorB = (color4body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_TF_BODY1_PART2)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                    } else if(bodytype == 1){
                        colorR = (color1body >> 16) / 255.0F;
                        colorG = ((color1body >> 8) & 0xff) / 255.0f;
                        colorB = (color1body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_TF_BODY2_PART1)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                        colorR = (color2body >> 16) / 255.0F;
                        colorG = ((color2body >> 8) & 0xff) / 255.0f;
                        colorB = (color2body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_TF_BODY2_PART2)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                        colorR = (color3body >> 16) / 255.0F;
                        colorG = ((color3body >> 8) & 0xff) / 255.0f;
                        colorB = (color3body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_TF_BODY2_PART3)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                    } else if(bodytype == 2){
                        colorR = (color1body >> 16) / 255.0F;
                        colorG = ((color1body >> 8) & 0xff) / 255.0f;
                        colorB = (color1body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_TF_BODY3_PART1)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                        colorR = (color2body >> 16) / 255.0F;
                        colorG = ((color2body >> 8) & 0xff) / 255.0f;
                        colorB = (color2body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_TF_BODY3_PART2)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                        colorR = (color3body >> 16) / 255.0F;
                        colorG = ((color3body >> 8) & 0xff) / 255.0f;
                        colorB = (color3body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_TF_BODY3_PART3)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                    }
                    break;
                default:
                    if(bodytype == 0){
                        colorR = (color1body >> 16) / 255.0F;
                        colorG = ((color1body >> 8) & 0xff) / 255.0f;
                        colorB = (color1body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_MINIMAL_BODY1_PART1)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_MINIMAL_BODY1_PART1_DECO)), pPackedLight, OverlayTexture.NO_OVERLAY,1.0f,1.0f,1.0f,1.0f);
                        colorR = (color2body >> 16) / 255.0F;
                        colorG = ((color2body >> 8) & 0xff) / 255.0f;
                        colorB = (color2body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_MINIMAL_BODY1_PART2)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                        colorR = (color3body >> 16) / 255.0F;
                        colorG = ((color3body >> 8) & 0xff) / 255.0f;
                        colorB = (color3body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_MINIMAL_BODY1_PART3)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                    } else if(bodytype == 1){
                        colorR = (color1body >> 16) / 255.0F;
                        colorG = ((color1body >> 8) & 0xff) / 255.0f;
                        colorB = (color1body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_MINIMAL_BODY2_PART1)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                        colorR = (color2body >> 16) / 255.0F;
                        colorG = ((color2body >> 8) & 0xff) / 255.0f;
                        colorB = (color2body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_MINIMAL_BODY2_PART2)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                        colorR = (color3body >> 16) / 255.0F;
                        colorG = ((color3body >> 8) & 0xff) / 255.0f;
                        colorB = (color3body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_MINIMAL_BODY2_PART3)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                    } else if(bodytype == 2){
                        colorR = (color1body >> 16) / 255.0F;
                        colorG = ((color1body >> 8) & 0xff) / 255.0f;
                        colorB = (color1body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_MINIMAL_BODY3_PART1)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                        colorR = (color2body >> 16) / 255.0F;
                        colorG = ((color2body >> 8) & 0xff) / 255.0f;
                        colorB = (color2body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_MINIMAL_BODY3_PART2)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                        colorR = (color3body >> 16) / 255.0F;
                        colorG = ((color3body >> 8) & 0xff) / 255.0f;
                        colorB = (color3body & 0xff) / 255.0f;
                        pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DC_MINIMAL_BODY3_PART3)), pPackedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                    }
                    break;
            }

        });

    }

    private void armadurasRender(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, ModelPart pRendererArm){

        pPoseStack.pushPose();

        pPoseStack.scale(1.01f,1.01f,1.01f);
        //pPoseStack.translate(0.015f,0.0f,0.0f);
        var chestplate = pEntity.getItemBySlot(EquipmentSlot.CHEST);

        // Obtener la durabilidad de la armadura
        int maxDamage = chestplate.getMaxDamage();
        int currentDamage = chestplate.getDamageValue();

        // Comprobar si la durabilidad es menor que la mitad de la durabilidad máxima
        boolean isDamaged = currentDamage > maxDamage / 2;

        if(chestplate.getItem() instanceof DbzArmorItem armorItem){

            var textureArmor = new ResourceLocation(Reference.MOD_ID, "textures/armor/" + armorItem.getItemId() + "_layer1.png");
            var textureArmorDamaged = new ResourceLocation(Reference.MOD_ID, "textures/armor/" + armorItem.getItemId() + "_damaged_layer1.png");

            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(armorItem.isDamageOn() && isDamaged ? textureArmorDamaged : textureArmor)), pPackedLight, OverlayTexture.NO_OVERLAY);

        } else if(chestplate.getItem() instanceof SaiyanArmorItem armorItem){

            var textureArmor = new ResourceLocation(Reference.MOD_ID, "textures/armor/saiyans/" + armorItem.getItemId() + "_layer1.png");
            var textureArmorDamaged = new ResourceLocation(Reference.MOD_ID, "textures/armor/saiyans/" + armorItem.getItemId() + "_damaged_layer1.png");

            pRendererArm.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(armorItem.isDamageOn() && isDamaged ? textureArmorDamaged : textureArmor)), pPackedLight, OverlayTexture.NO_OVERLAY);

        }

        pPoseStack.popPose();
    }



    private void setModelProperties(AbstractClientPlayer pClientPlayer) {
        PlayerModel<AbstractClientPlayer> playermodel = (PlayerModel)this.getModel();
        if (pClientPlayer.isSpectator()) {
            playermodel.setAllVisible(false);
            playermodel.head.visible = true;
            playermodel.hat.visible = true;
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
    public ResourceLocation getTextureLocation(AbstractClientPlayer abstractClientPlayer) {
        return null;
    }
}
