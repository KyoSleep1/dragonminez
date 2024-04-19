package com.yuseix.dragonminez.character.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.character.models.bioandroid.GeoBioAndroidModel;
import com.yuseix.dragonminez.character.models.bioandroid.GeoBioAndroidPlayer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;
import software.bernie.geckolib.renderer.layer.ItemArmorGeoLayer;

public class GeoBioAndroidRender extends GeoReplacedEntityRenderer<Player, GeoBioAndroidPlayer> {


    private static final String LEFT_HAND = "bipedLeftHand";
    private static final String RIGHT_HAND = "bipedRightHand";
    private static final String LEFT_BOOT = "armorbipedLeftFoot";
    private static final String RIGHT_BOOT = "armorbipedRightFoot";
    private static final String LEFT_BOOT_2 = "armorBipedLeftFoot2";
    private static final String RIGHT_BOOT_2 = "armorBipedRightFoot2";
    private static final String LEFT_ARMOR_LEG = "armorbipedLeftLeg";
    private static final String RIGHT_ARMOR_LEG = "armorbipedRightLeg";
    private static final String LEFT_ARMOR_LEG_2 = "armorbipedLeftLeg2";
    private static final String RIGHT_ARMOR_LEG_2 = "armorbipedRightLeg2";
    private static final String CHESTPLATE = "armorbipedBody";
    private static final String RIGHT_SLEEVE = "armorbipedRightArm";
    private static final String LEFT_SLEEVE = "armorbipedLeftArm";
    private static final String HELMET = "armorbipedHead";

    public GeoBioAndroidRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GeoBioAndroidModel(), new GeoBioAndroidPlayer());

        this.addRenderLayer(new ItemArmorGeoLayer<>(this){

            @Override
            public void preRender(PoseStack poseStack, GeoBioAndroidPlayer animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
                Player player = GeoBioAndroidRender.this.getCurrentEntity();
                this.mainHandStack = player.getItemBySlot(EquipmentSlot.MAINHAND);
                this.offhandStack = player.getItemBySlot(EquipmentSlot.OFFHAND);
                this.helmetStack = player.getItemBySlot(EquipmentSlot.HEAD);
                this.chestplateStack = player.getItemBySlot(EquipmentSlot.CHEST);
                this.leggingsStack = player.getItemBySlot(EquipmentSlot.LEGS);
                this.bootsStack = player.getItemBySlot(EquipmentSlot.FEET);
            }

            @Nullable
            @Override
            protected ItemStack getArmorItemForBone(GeoBone bone, GeoBioAndroidPlayer animatable) {
                return switch (bone.getName()){
                    case LEFT_BOOT, RIGHT_BOOT, LEFT_BOOT_2, RIGHT_BOOT_2 -> this.bootsStack;
                    case LEFT_ARMOR_LEG, RIGHT_ARMOR_LEG, LEFT_ARMOR_LEG_2, RIGHT_ARMOR_LEG_2 -> this.leggingsStack;
                    case CHESTPLATE, RIGHT_SLEEVE, LEFT_SLEEVE -> this.chestplateStack;
                    case HELMET -> this.helmetStack;
                    default -> null;
                };
            }

            @NotNull
            @Override
            protected EquipmentSlot getEquipmentSlotForBone(GeoBone bone, ItemStack stack, GeoBioAndroidPlayer animatable) {
                return switch (bone.getName()) {
                    case LEFT_BOOT, RIGHT_BOOT, LEFT_BOOT_2, RIGHT_BOOT_2 -> EquipmentSlot.FEET;
                    case LEFT_ARMOR_LEG, RIGHT_ARMOR_LEG, LEFT_ARMOR_LEG_2, RIGHT_ARMOR_LEG_2 -> EquipmentSlot.LEGS;
                    case RIGHT_SLEEVE -> EquipmentSlot.MAINHAND;
                    case LEFT_SLEEVE -> EquipmentSlot.OFFHAND;
                    case CHESTPLATE -> EquipmentSlot.CHEST;
                    case HELMET -> EquipmentSlot.HEAD;
                    default -> super.getEquipmentSlotForBone(bone, stack, animatable);
                };
            }

            @NotNull
            @Override
            protected ModelPart getModelPartForBone(GeoBone bone, EquipmentSlot slot, ItemStack stack, GeoBioAndroidPlayer animatable, HumanoidModel<?> baseModel) {
                return switch (bone.getName()) {
                    case LEFT_BOOT, LEFT_BOOT_2, LEFT_ARMOR_LEG, LEFT_ARMOR_LEG_2 -> baseModel.leftLeg;
                    case RIGHT_BOOT, RIGHT_BOOT_2, RIGHT_ARMOR_LEG, RIGHT_ARMOR_LEG_2 -> baseModel.rightLeg;
                    case RIGHT_SLEEVE -> baseModel.rightArm;
                    case LEFT_SLEEVE -> baseModel.leftArm;
                    case CHESTPLATE -> baseModel.body;
                    case HELMET -> baseModel.head;
                    default -> super.getModelPartForBone(bone, slot, stack, animatable, baseModel);
                };
            }
        });

        this.addRenderLayer(new BlockAndItemGeoLayer<>(this){

            @Nullable
            @Override
            protected ItemStack getStackForBone(GeoBone bone, GeoBioAndroidPlayer animatable) {
                Player player = GeoBioAndroidRender.this.getCurrentEntity();
                return switch (bone.getName()) {
                    case LEFT_HAND -> player.getOffhandItem();
                    case RIGHT_HAND -> player.getMainHandItem();
                    default -> null;
                };
            }

            @Override
            protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, GeoBioAndroidPlayer animatable) {
                return switch (bone.getName()) {
                    case LEFT_HAND, RIGHT_HAND -> ItemDisplayContext.FIRST_PERSON_RIGHT_HAND;
                    default -> ItemDisplayContext.NONE;
                };
            }

            @Override
            protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, GeoBioAndroidPlayer animatable, MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {

                Player player = GeoBioAndroidRender.this.getCurrentEntity();

                if(stack == player.getMainHandItem()){
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90f));

                    if(stack.getItem() instanceof ShieldItem){
                        poseStack.translate(0, 0.125, -0.25);
                    }

                } else if (stack == player.getOffhandItem()){
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90f));

                    if (stack.getItem() instanceof ShieldItem) {
                        poseStack.translate(0, 0.125, 0.25);
                        poseStack.mulPose(Axis.YP.rotationDegrees(180));
                    }

                }

                super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
            }
        });


    }

}
