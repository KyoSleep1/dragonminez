package com.yuseix.dragonminez.client.character.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.character.models.hair.*;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;

public class HairsLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    //TEXTURAS CABELLO
    private static final ResourceLocation SUIT_TEX = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/hairtexture.png");
    public static final ResourceLocation GOKUHAIR_TEXT1 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/hair/goku/gokubasehair1.png");
    public static final ResourceLocation GOKUHAIR_TEXT2 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/hair/hairprueba.png");
    public static final ResourceLocation VEGETAHAIR_TEXT1 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/hair/vegeta/vegetahair1.png");
    public static final ResourceLocation VEGETAHAIR_TEXT2 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/hair/vegeta/vegetahair2.png");
    public static final ResourceLocation GOHANDBSHAIR_TEXT1 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/hair/gohandbs/gohandbshair.png");
    public static final ResourceLocation GOHANDBSHAIR_TEXT2 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/hair/gohandbs/gohandbshair2.png");
    public static final ResourceLocation GOHAN_TEEN_HAIR_TEXT1 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/hair/gohandbs/gohanteen1.png");
    public static final ResourceLocation GOHAN_TEEN_HAIR_TEXT2 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/hair/gohandbs/gohanteen2.png");
    public static final ResourceLocation TRUNKS_HAIR_TEXT1 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/hair/trunks/trunks.png");
    public static final ResourceLocation TRUNKS_HAIR_TEXT2 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/hair/trunks/trunks2.png");

    private static final ResourceLocation EARS = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/namek/body/ears.png");
    private static final ResourceLocation HALO_TEX = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/halo.png");
    private static final ResourceLocation MAJIN_ACCES = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/majin/body/tevil_bodytype.png");

    private float colorR, colorG, colorB;

    private GokuHairModel gokuhair;
    private FemHairModel femhair;
    private VegetaHairModel vegetahair;
    private GohanDBSHairModel gohandbshair;
    private GohanTeenHairModel gohanteenhair;
    private TrunksHairModel trunkshair;
    private GokuSSJHairModel gokussjhair;

    private EarsNamek earsNamek;
    private TailModel cola;
    private Tail2Model cola_cinturon;
    private HaloModel haloModel;
    private MajinAccesModel majinModelAcces;

    public HairsLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);
        this.gokuhair = new GokuHairModel(GokuHairModel.createBodyLayer().bakeRoot());
        this.gokussjhair = new GokuSSJHairModel(GokuSSJHairModel.createBodyLayer().bakeRoot());
        this.earsNamek = new EarsNamek(EarsNamek.createBodyLayer().bakeRoot());
        this.femhair = new FemHairModel(FemHairModel.createBodyLayer().bakeRoot());
        this.cola = new TailModel(TailModel.createBodyLayer().bakeRoot());
        this.cola_cinturon = new Tail2Model(Tail2Model.createBodyLayer().bakeRoot());
        this.haloModel = new HaloModel(HaloModel.createBodyLayer().bakeRoot());
        this.majinModelAcces = new MajinAccesModel(MajinAccesModel.createBodyLayer().bakeRoot());
        this.vegetahair = new VegetaHairModel(VegetaHairModel.createBodyLayer().bakeRoot());
        this.gohandbshair = new GohanDBSHairModel(GohanDBSHairModel.createBodyLayer().bakeRoot());
        this.gohanteenhair = new GohanTeenHairModel(GohanTeenHairModel.createBodyLayer().bakeRoot());
        this.trunkshair = new TrunksHairModel(TrunksHairModel.createBodyLayer().bakeRoot());

    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, AbstractClientPlayer abstractClientPlayer, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        poseStack.pushPose();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE,abstractClientPlayer).ifPresent(cap -> {

            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucent(SUIT_TEX));

            var raza = cap.getRace();
            var hairColor = 0;
            var hairId = cap.getHairID();
            var bodyColor = cap.getBodyColor();
            var genero = cap.getGender();
            var transformation = cap.getDmzForm();
            var tailmode = cap.isTailMode();

            colorR = (hairColor >> 16) / 255.0F;
            colorG = ((hairColor >> 8) & 0xff) / 255.0f;
            colorB = (hairColor & 0xff) / 255.0f;

            // Si el jugador tiene invisibilidad, no renderizamos nada.
            if (abstractClientPlayer.hasEffect(MobEffects.INVISIBILITY)) return;
            // Si el jugador tiene un casco, no renderizamos nada.

            poseStack.pushPose();

            this.getParentModel().getHead().translateAndRotate(poseStack);

            if(cap.isDmzAlive()){
                VertexConsumer tex = multiBufferSource.getBuffer(RenderType.entityTranslucent(HALO_TEX));
                this.haloModel.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                this.haloModel.renderToBuffer(poseStack,tex, packedLight, OverlayTexture.NO_OVERLAY, 1.0f,1.0f,1.0f,1.0f);

            }
            poseStack.popPose();

            switch (raza){
                case 0: //Humano
                    hairColor = cap.getHairColor();

                    colorR = (hairColor >> 16) / 255.0F;
                    colorG = ((hairColor >> 8) & 0xff) / 255.0f;
                    colorB = (hairColor & 0xff) / 255.0f;

                    renderhairSaiyan(poseStack,multiBufferSource,packedLight,abstractClientPlayer,limbSwing,limbSwingAmount,partialTick,ageInTicks,netHeadYaw,headPitch,hairColor);

                    break;
                case 1: //Saiyan

                    switch (transformation){
                        case "ssj1","ssgrade2","ssgrade3","ssjfp","ssj2","ssj3" -> hairColor = 16773525;
                        default -> hairColor = cap.getHairColor();
                    }

                    colorR = (hairColor >> 16) / 255.0F;
                    colorG = ((hairColor >> 8) & 0xff) / 255.0f;
                    colorB = (hairColor & 0xff) / 255.0f;

                    renderhairSaiyan(poseStack,multiBufferSource,packedLight,abstractClientPlayer,limbSwing,limbSwingAmount,partialTick,ageInTicks,netHeadYaw,headPitch,hairColor);

                    poseStack.pushPose();

                    colorR = (5515271 >> 16) / 255.0F;
                    colorG = ((5515271 >> 8) & 0xff) / 255.0f;
                    colorB = (5515271 & 0xff) / 255.0f;

                    vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucent(SUIT_TEX));

                    if(tailmode){
                        this.getParentModel().body.translateAndRotate(poseStack);
                        this.cola_cinturon.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount,  ageInTicks, netHeadYaw,  headPitch);
                        this.cola_cinturon.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                    } else { //Tail Free
                        this.getParentModel().body.translateAndRotate(poseStack);
                        this.cola.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount,  ageInTicks, netHeadYaw,  headPitch);
                        this.cola.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY,colorR,colorG,colorB,1.0f);
                    }
                    poseStack.popPose();

                    break;
                case 2: //Namek
                    if(hairId == 0){
                        colorR = (bodyColor >> 16) / 255.0F;
                        colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                        colorB = (bodyColor & 0xff) / 255.0f;
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.earsNamek.renderEars1(poseStack, multiBufferSource.getBuffer(RenderType.entityCutout(EARS)), packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    } else if (hairId == 1){
                        colorR = (bodyColor >> 16) / 255.0F;
                        colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                        colorB = (bodyColor & 0xff) / 255.0f;
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.earsNamek.renderEars2(poseStack, multiBufferSource.getBuffer(RenderType.entityTranslucent(EARS)), packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    }
                    break;
                case 5: //Majin
                    switch (transformation){
                        case "evil":
                            if(!genero.equals("Female")){
                                VertexConsumer orejas = multiBufferSource.getBuffer(RenderType.entityTranslucent(MAJIN_ACCES));
                                colorR = (11314334 >> 16) / 255.0F;
                                colorG = ((11314334 >> 8) & 0xff) / 255.0f;
                                colorB = (11314334 & 0xff) / 255.0f;
                                this.getParentModel().getHead().translateAndRotate(poseStack);
                                this.majinModelAcces.renderToBuffer(poseStack,orejas, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);

                            } else {
                                hairColor = 11314334; //Color EVIL

                                colorR = (hairColor >> 16) / 255.0F;
                                colorG = ((hairColor >> 8) & 0xff) / 255.0f;
                                colorB = (hairColor & 0xff) / 255.0f;

                                renderhairSaiyan(poseStack,multiBufferSource,packedLight,abstractClientPlayer,limbSwing,limbSwingAmount,partialTick,ageInTicks,netHeadYaw,headPitch,hairColor);

                            }
                            break;
                        case "kid", "super":
                            VertexConsumer orejas = multiBufferSource.getBuffer(RenderType.entityTranslucent(MAJIN_ACCES));
                            colorR = (bodyColor >> 16) / 255.0F;
                            colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                            colorB = (bodyColor & 0xff) / 255.0f;
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.majinModelAcces.renderToBuffer(poseStack,orejas, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                            break;

                        default:
                            if(genero.equals("Female")){
                                hairColor = cap.getHairColor();

                                colorR = (hairColor >> 16) / 255.0F;
                                colorG = ((hairColor >> 8) & 0xff) / 255.0f;
                                colorB = (hairColor & 0xff) / 255.0f;

                                renderhairSaiyan(poseStack,multiBufferSource,packedLight,abstractClientPlayer,limbSwing,limbSwingAmount,partialTick,ageInTicks,netHeadYaw,headPitch,hairColor);

                            }
                            break;
                    }
                    break;
                default:
                    break;

            }



        });

        poseStack.popPose();
    }

    public void renderhairSaiyan(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, AbstractClientPlayer abstractClientPlayer, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch, int colorHair) {
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE,abstractClientPlayer).ifPresent(cap -> {

            VertexConsumer hairtex = multiBufferSource.getBuffer(RenderType.entityTranslucent(SUIT_TEX));

            var transformation = cap.getDmzForm();
            var hairId = cap.getHairID();

            colorR = (colorHair >> 16) / 255.0F;
            colorG = ((colorHair >> 8) & 0xff) / 255.0f;
            colorB = (colorHair & 0xff) / 255.0f;

            if (!(abstractClientPlayer.getItemBySlot(EquipmentSlot.HEAD).isEmpty())) return;

            poseStack.pushPose();

            switch (transformation) {
                case "oozaru","goldenoozaru","buffed","full_power","potential_unleashed":
                    break;
                case "ssj1":
                    this.getParentModel().getHead().translateAndRotate(poseStack);

                    if(hairId == 1){
                        hairtex = multiBufferSource.getBuffer(RenderType.entityTranslucent(GOKUHAIR_TEXT2));
                        this.gokussjhair.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        this.gokussjhair.renderToBuffer(poseStack,hairtex, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 2){
                        this.femhair.renderToBuffer(poseStack,hairtex, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 3){
                        this.vegetahair.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        this.vegetahair.renderToBuffer(poseStack,hairtex, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 4){
                        this.gohandbshair.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        this.gohandbshair.renderToBuffer(poseStack,hairtex, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 5){
                        this.gohanteenhair.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        this.gohanteenhair.renderToBuffer(poseStack,hairtex, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 6){
                        this.trunkshair.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        this.trunkshair.renderToBuffer(poseStack,hairtex, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else {

                    }
                    break;
                default:
                    this.getParentModel().getHead().translateAndRotate(poseStack);

                    if(hairId == 1){
                        this.gokuhair.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        this.gokuhair.renderToBuffer(poseStack,hairtex, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 2){
                        this.femhair.renderToBuffer(poseStack,hairtex, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 3){
                        this.vegetahair.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        this.vegetahair.renderToBuffer(poseStack,hairtex, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 4){
                        this.gohandbshair.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        this.gohandbshair.renderToBuffer(poseStack,hairtex, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 5){
                        this.gohanteenhair.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        this.gohanteenhair.renderToBuffer(poseStack,hairtex, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 6){
                        this.trunkshair.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        this.trunkshair.renderToBuffer(poseStack,hairtex, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else {

                    }
                    break;
            }

            poseStack.popPose();




        });


    }

}
