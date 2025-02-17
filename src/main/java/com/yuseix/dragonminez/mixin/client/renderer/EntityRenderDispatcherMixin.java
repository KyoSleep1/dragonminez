package com.yuseix.dragonminez.mixin.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.yuseix.dragonminez.client.character.models.*;
import com.yuseix.dragonminez.client.character.models.bioandroid.BioAndroidModel;
import com.yuseix.dragonminez.client.character.models.demoncold.DemonColdModel;
import com.yuseix.dragonminez.client.character.models.majin.MajinFemaleModel;
import com.yuseix.dragonminez.client.character.models.majin.MajinGordoModel;
import com.yuseix.dragonminez.client.character.renders.*;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {

    private Map<String, LivingEntityRenderer> dmzRendererersV2 = ImmutableMap.of();

    @Shadow
    public Map<EntityType<?>, EntityRenderer<?>> renderers;
    @Shadow
    private Map<String, EntityRenderer<? extends Player>> playerRenderers;

    @Inject(at = @At("HEAD"), method = "getRenderer(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/client/renderer/entity/EntityRenderer;", cancellable = true)
    public void dmz$getRenderer(Entity entity, CallbackInfoReturnable<EntityRenderer<? super Entity>> cir) {
        if (entity instanceof Player player) {

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {

                if (player instanceof AbstractClientPlayer abstractClientPlayer) {
                    String modelname = abstractClientPlayer.getModelName();
                    var transf = cap.getDmzForm();


                    switch (cap.getRace()) {
                        //HUMANO RENDER
                        case 0:
                            if (cap.getBodytype() == 0) {
                                if ("default".equals(modelname)) {
                                    cir.setReturnValue(dmzRendererersV2.get(modelname));
                                } else if ("slim".equals(modelname)) {
                                    cir.setReturnValue(dmzRendererersV2.get(modelname));
                                }
                            } else if (cap.getBodytype() == 1) {
                                if (cap.getGender().equals("Male")) {
                                    cir.setReturnValue(dmzRendererersV2.get("default"));
                                } else {
                                    cir.setReturnValue(dmzRendererersV2.get("slim"));
                                }
                            }


                            break;
                        //SAIYAJIN RENDER
                        case 1:
                            switch (transf){
                                case "oozaru":
                                    cir.setReturnValue(dmzRendererersV2.get("oozaru"));
                                    break;
                                default:
                                    if (cap.getBodytype() == 0) {
                                        if ("default".equals(modelname)) {
                                            cir.setReturnValue(dmzRendererersV2.get(modelname));
                                        } else if ("slim".equals(modelname)) {
                                            cir.setReturnValue(dmzRendererersV2.get(modelname));
                                        }
                                    } else if (cap.getBodytype() == 1) {
                                        if (cap.getGender().equals("Male")) {
                                            cir.setReturnValue(dmzRendererersV2.get("default"));
                                        } else {
                                            cir.setReturnValue(dmzRendererersV2.get("slim"));
                                        }
                                    }
                                    break;
                            }

                            break;

                        //NAMEKIANO RENDER
                        case 2:
                            cir.setReturnValue(dmzRendererersV2.get("namek"));
                            break;
                        //BIOANDROIDE RENDER
                        case 3:
                            if(cap.getDmzState() == 0){
                                cir.setReturnValue(dmzRendererersV2.get("bio_imperfect"));
                            } else if(cap.getDmzState() == 1){
                                cir.setReturnValue(dmzRendererersV2.get("bio_semiperfect"));
                            }
                            break;
                        //DEMONCOLD
                        case 4:
                            cir.setReturnValue(dmzRendererersV2.get("demon_cold"));
                            break;
                        //MAJIN RENDER
                        case 5:
                            if (cap.getGender().equals("Male")) {
                                cir.setReturnValue(dmzRendererersV2.get("majin_gordo"));
                            } else {
                                cir.setReturnValue(dmzRendererersV2.get("majin_female"));
                            }
                        default:
                            break;
                    }
                }

            });
        }

    }

    @Inject(at = @At("TAIL"), method = "onResourceManagerReload(Lnet/minecraft/server/packs/resources/ResourceManager;)V", locals = LocalCapture.CAPTURE_FAILHARD)
    public void dmz$reload(ResourceManager resourceManager, CallbackInfo ci, EntityRendererProvider.Context entityrendererprovider$context) {
        dmzRendererersV2 = reloadDMZRenderersV2(entityrendererprovider$context);
    }

    private static Map<String, LivingEntityRenderer> reloadDMZRenderersV2(EntityRendererProvider.Context ctx) {
        ImmutableMap.Builder<String, LivingEntityRenderer> builder = ImmutableMap.builder();
        //HUMANO Y SAIYAJIN
        builder.put("default", new HumanSaiyanRender(ctx, new HumanSaiyanModel<>(ctx.bakeLayer(HumanSaiyanModel.LAYER_LOCATION))));
        builder.put("slim", new SlimHumanSMajinRender(ctx, new SlimHumanSaiyanModel<>(ctx.bakeLayer(SlimHumanSaiyanModel.LAYER_LOCATION))));
        builder.put("oozaru", new HumanSaiyanRender(ctx, new OzaruModel<>(ctx.bakeLayer(OzaruModel.LAYER_LOCATION))));
        //NAMEK
        builder.put("namek", new NamekianRender(ctx));
        //BIO ANDROIDE
        builder.put("bio_imperfect", new BioAndroidRender(ctx, new BioAndroidModel<>(ctx.bakeLayer(BioAndroidModel.LAYER_LOCATION))));
        builder.put("bio_semiperfect", new BioAndroidRender(ctx, new BioAndroidModel<>(ctx.bakeLayer(BioAndroidModel.LAYER_LOCATION))));
        //MAJIN
        builder.put("majin_gordo", new MajinFATRaceRender(ctx, new MajinGordoModel<>(ctx.bakeLayer(MajinGordoModel.LAYER_LOCATION))));
        builder.put("majin_female", new SlimHumanSMajinRender(ctx, new MajinFemaleModel<>(ctx.bakeLayer(MajinFemaleModel.LAYER_LOCATION))));
        //DEMON COLD
        builder.put("demon_cold", new DemonColdRender(ctx, new DemonColdModel<>(ctx.bakeLayer(DemonColdModel.LAYER_LOCATION))));

        return builder.build();
    }

}
