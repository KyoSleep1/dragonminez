package com.yuseix.dragonminez.init.entity.client.model.characters;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.character.layer.HairsLayer;
import com.yuseix.dragonminez.client.character.models.hair.*;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class FPHairsLayer<T extends LivingEntity, M extends PlayerModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation SUIT_TEX = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/hairtexture.png");
    private static final ResourceLocation EARS = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/namek/body/ears.png");

    private float colorR, colorG, colorB;

    private GokuHairModel gokuhair;
    private FemHairModel femhair;
    private VegetaHairModel vegetahair;
    private GohanDBSHairModel gohandbshair;
    private GohanTeenHairModel gohanteenhair;
    private TrunksHairModel trunkshair;
    private EarsNamek earsNamek;
    private TailModel cola;

    public FPHairsLayer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
            this.gokuhair = new GokuHairModel(GokuHairModel.createBodyLayer().bakeRoot());
            this.earsNamek = new EarsNamek(EarsNamek.createBodyLayer().bakeRoot());
            this.femhair = new FemHairModel(FemHairModel.createBodyLayer().bakeRoot());
            this.cola = new TailModel(TailModel.createBodyLayer().bakeRoot());
            this.vegetahair = new VegetaHairModel(VegetaHairModel.createBodyLayer().bakeRoot());
            this.gohandbshair = new GohanDBSHairModel(GohanDBSHairModel.createBodyLayer().bakeRoot());
        this.gohanteenhair = new GohanTeenHairModel(GohanTeenHairModel.createBodyLayer().bakeRoot());
        this.trunkshair = new TrunksHairModel(TrunksHairModel.createBodyLayer().bakeRoot());
    }


    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T t, float v, float v1, float v2, float v3, float v4, float v5) {
        poseStack.pushPose();
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucent(SUIT_TEX));

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {
            var raza = cap.getRace();
            var hairColor = cap.getHairColor();
            var hairId = cap.getHairID();
            var bodyColor = cap.getBodyColor();
            var genero = cap.getGender();
            var transformacion = cap.getDmzState();

            colorR = (hairColor >> 16) / 255.0F;
            colorG = ((hairColor >> 8) & 0xff) / 255.0f;
            colorB = (hairColor & 0xff) / 255.0f;

            switch (raza){
                case 0: //Humano
                    if(hairId == 0){
                        VertexConsumer gokubase = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.GOKUHAIR_TEXT1));
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.gokuhair.renderToBuffer(poseStack,gokubase, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 1){
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.femhair.renderToBuffer(poseStack,vertexConsumer, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 2){
                        VertexConsumer vegetabase = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.VEGETAHAIR_TEXT1));
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.vegetahair.renderToBuffer(poseStack,vegetabase, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 3){
                        VertexConsumer gohandbs = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.GOHANDBSHAIR_TEXT1));
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.gohandbshair.renderToBuffer(poseStack,gohandbs, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 4){
                        VertexConsumer tex = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.GOHAN_TEEN_HAIR_TEXT1));
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.gohanteenhair.renderToBuffer(poseStack,tex, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } else if(hairId == 5){
                        VertexConsumer tex = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.TRUNKS_HAIR_TEXT1));
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.trunkshair.renderToBuffer(poseStack,tex, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    }
                    break;
                case 1: //Saiyan

                    //Cola
                    this.cola.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY,0.410f,0.119f,0.00410f,1.0f);

                    //Cabellos

                    if(transformacion == 0){ //Base
                        if(hairId == 0){
                            VertexConsumer gokubase = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.GOKUHAIR_TEXT1));
                            this.gokuhair.renderToBuffer(poseStack,gokubase, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);

                        } else if(hairId == 1){
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.femhair.renderToBuffer(poseStack,vertexConsumer, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);

                        } else if(hairId == 2){
                            VertexConsumer cabello = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.VEGETAHAIR_TEXT1));
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.vegetahair.renderToBuffer(poseStack,cabello, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);

                        } else if(hairId == 3){
                            VertexConsumer cabello = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.GOHANDBSHAIR_TEXT1));
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.gohandbshair.renderToBuffer(poseStack,cabello, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                        } else if(hairId == 4){
                            VertexConsumer tex = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.GOHAN_TEEN_HAIR_TEXT1));
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.gohanteenhair.renderToBuffer(poseStack,tex, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                        } else if(hairId == 5){
                            VertexConsumer tex = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.TRUNKS_HAIR_TEXT1));
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.trunkshair.renderToBuffer(poseStack,tex, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                        }
                    } else if(transformacion == 1){ //Ozaru osea aqui no hacemos nada

                    } else if(transformacion == 2){ //Super saiyajin 1
                        //Color del ssj (Obvio no?)
                        var colorSSJ1 = 16777114;
                        colorR = (colorSSJ1 >> 16) / 255.0F;
                        colorG = ((colorSSJ1 >> 8) & 0xff) / 255.0f;
                        colorB = (colorSSJ1 & 0xff) / 255.0f;

                        if(hairId == 0){
                            //Goku ssj
                        } else if(hairId == 1){
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.femhair.renderToBuffer(poseStack,vertexConsumer, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);

                        } else if(hairId == 2){
                            VertexConsumer cabello = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.VEGETAHAIR_TEXT1));
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.vegetahair.renderToBuffer(poseStack,cabello, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);

                        } else if(hairId == 3){
                            //Gohan dbs ssj
                        }
                    } else if(transformacion == 3){ //Super Saiyajin grado 2

                    } else if(transformacion == 4){ //Super saiyajin grado 3

                    } else if(transformacion == 5){ //Super saiyajin full power osea el ssj1

                    } else if(transformacion == 6){ //Supa saiyajin 2

                    } else { //Mas transformaciones pero me dio paja seguir jeje


                    }


                    break;
                case 2: //Namek
                    if(hairId == 0){
                        colorR = (bodyColor >> 16) / 255.0F;
                        colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                        colorB = (bodyColor & 0xff) / 255.0f;
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.earsNamek.renderEars1(poseStack, multiBufferSource.getBuffer(RenderType.entityCutout(EARS)), i, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    } else if (hairId == 1){
                        colorR = (bodyColor >> 16) / 255.0F;
                        colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                        colorB = (bodyColor & 0xff) / 255.0f;
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.earsNamek.renderEars2(poseStack, multiBufferSource.getBuffer(RenderType.entityTranslucent(EARS)), i, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    }
                    break;
                case 5: //Majin
                    if(genero.equals("Female")){
                        if(hairId == 0){
                            colorR = (bodyColor >> 16) / 255.0F;
                            colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                            colorB = (bodyColor & 0xff) / 255.0f;
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.femhair.renderToBuffer(poseStack,vertexConsumer, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                        } else if(hairId == 1){
                            colorR = (bodyColor >> 16) / 255.0F;
                            colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                            colorB = (bodyColor & 0xff) / 255.0f;
                            VertexConsumer gokubase = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.GOKUHAIR_TEXT1));
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.gokuhair.renderToBuffer(poseStack,gokubase, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                        } else if(hairId == 2){
                            colorR = (bodyColor >> 16) / 255.0F;
                            colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                            colorB = (bodyColor & 0xff) / 255.0f;
                            VertexConsumer cabello = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.VEGETAHAIR_TEXT1));
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.vegetahair.renderToBuffer(poseStack,cabello, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                        } else if(hairId == 3){
                            colorR = (bodyColor >> 16) / 255.0F;
                            colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                            colorB = (bodyColor & 0xff) / 255.0f;
                            VertexConsumer cabello = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.GOHANDBSHAIR_TEXT1));
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.gohandbshair.renderToBuffer(poseStack,cabello, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                        } else if(hairId == 4){
                            colorR = (bodyColor >> 16) / 255.0F;
                            colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                            colorB = (bodyColor & 0xff) / 255.0f;
                            VertexConsumer tex = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.GOHAN_TEEN_HAIR_TEXT1));
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.gohanteenhair.renderToBuffer(poseStack,tex, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                        } else if(hairId == 5){
                            colorR = (bodyColor >> 16) / 255.0F;
                            colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                            colorB = (bodyColor & 0xff) / 255.0f;
                            VertexConsumer tex = multiBufferSource.getBuffer(RenderType.entityTranslucent(HairsLayer.TRUNKS_HAIR_TEXT1));
                            this.getParentModel().getHead().translateAndRotate(poseStack);
                            this.trunkshair.renderToBuffer(poseStack,tex, i, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                        }
                    }
                    break;
                default:
                    break;

            }

        });

        poseStack.popPose();
    }
}
