package com.yuseix.dragonminez.client.character.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.MainItems;
import com.yuseix.dragonminez.common.init.items.models.BaculoEmptyModel;
import com.yuseix.dragonminez.common.init.items.models.TrunksSwordBackModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ArmasLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private BaculoEmptyModel baculoEmptyModel;
    private TrunksSwordBackModel trunksSwordBackModel;
    public ArmasLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);
        this.baculoEmptyModel = new BaculoEmptyModel(BaculoEmptyModel.createBodyLayer().bakeRoot());
        this.trunksSwordBackModel = new TrunksSwordBackModel(TrunksSwordBackModel.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer abstractClientPlayer, float v, float v1, float v2, float v3, float v4, float v5) {
        List<ItemStack> armasRender = obtenerArmasEnHotbar(abstractClientPlayer);
        ItemStack armaMasCercana = ItemStack.EMPTY;
        ItemStack katanaYajirobe = ItemStack.EMPTY;

        this.trunksSwordBackModel.setupAnim(abstractClientPlayer,v1,v2,v3,v4,v5);

        // Buscar la Katana y el arma más cercana al Slot 1
        for (int slot = 0; slot < armasRender.size(); slot++) {
            ItemStack arma = armasRender.get(slot);
            if (arma.getItem() == MainItems.KATANA_YAJIROBE.get()) {
                katanaYajirobe = arma;
            } else if (armaMasCercana.isEmpty()) {
                armaMasCercana = arma;
            }
        }

        // Renderizar la Katana de Yajirobe en la cintura si no está en la mano
        if (!katanaYajirobe.isEmpty() && !isHoldingItem(abstractClientPlayer, katanaYajirobe)) {
            renderYajirobeKatana(poseStack, abstractClientPlayer, multiBufferSource, i);
        }

        // Renderizar el arma más cercana al slot 1 en la espalda
        if (!armaMasCercana.isEmpty()) {
            if (isHoldingItem(abstractClientPlayer, armaMasCercana) && (armaMasCercana.is(MainItems.BACULO_SAGRADO.get()) || armaMasCercana.is(MainItems.TRUNKS_SWORD.get()))) {
                renderFundaEspalda(poseStack, abstractClientPlayer, multiBufferSource, i, armaMasCercana);
            } else if (!isHoldingItem(abstractClientPlayer, armaMasCercana) && armaMasCercana.is(MainItems.Z_SWORD.get())) {
                renderArmaEspalda(poseStack, abstractClientPlayer, multiBufferSource, i, armaMasCercana);
            } else if (isHoldingItem(abstractClientPlayer, armaMasCercana) && armaMasCercana.is(MainItems.Z_SWORD.get())) {
                // No renderizarla en la espalda.
            } else if (!isHoldingItem(abstractClientPlayer, armaMasCercana) && !armaMasCercana.is(MainItems.Z_SWORD.get())) {
                renderArmaEspalda(poseStack, abstractClientPlayer, multiBufferSource, i, armaMasCercana);
            }
        }
    }

    private List<ItemStack> obtenerArmasEnHotbar(Player player) {
        List<ItemStack> armas = new ArrayList<>();
        // Revisa solo la hotbar del jugador (primeras 9 ranuras del inventario)
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().items.get(i);
            if (stack.getItem() == MainItems.BACULO_SAGRADO.get() || stack.getItem() == MainItems.KATANA_YAJIROBE.get() || stack.getItem() == MainItems.Z_SWORD.get()
                    || stack.getItem() == MainItems.TRUNKS_SWORD.get()) {
                armas.add(stack);
            }
        }
        return armas;
    }

    private boolean isHoldingItem(Player player, ItemStack item) {
        return player.getMainHandItem() == item || player.getOffhandItem() == item;
    }

    private void renderYajirobeKatana(PoseStack poseStack, AbstractClientPlayer player, MultiBufferSource bufferSource, int light) {
        Minecraft mc = Minecraft.getInstance();
        ItemRenderer itemRenderer = mc.getItemRenderer();

        poseStack.pushPose();

        if (player.isCrouching()) {
            // Posiciona la espada en la cintura del jugador agachado
            poseStack.translate(0.17, 0.65, -0.32);
            poseStack.mulPose(Axis.YP.rotationDegrees(55.0F));
            poseStack.mulPose(Axis.XP.rotationDegrees(105.0F));
            poseStack.mulPose(Axis.ZP.rotationDegrees(50.0F));
            poseStack.translate(0.0F, 0.20F, -0.13F);
        } else {
            // Posiciona la espada en la cintura del jugador normal
            poseStack.translate(0.17, 0.65, -0.32);
            poseStack.mulPose(Axis.YP.rotationDegrees(55.0F));
            poseStack.mulPose(Axis.XP.rotationDegrees(75.0F));
            poseStack.mulPose(Axis.ZP.rotationDegrees(50.0F));
        }

        // Renderiza la Katana de Yajirobe
        itemRenderer.renderStatic(new ItemStack(MainItems.KATANA_YAJIROBE.get()), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, light, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, player.level(), 0);

        poseStack.popPose();
    }

    private void renderEspadaZ(PoseStack poseStack, AbstractClientPlayer player, MultiBufferSource bufferSource, int light) {
        Minecraft mc = Minecraft.getInstance();
        ItemRenderer itemRenderer = mc.getItemRenderer();
        poseStack.pushPose();
        if (player.isCrouching()) {
            poseStack.translate(-0.45F, 0.1F, 0.1F);
            poseStack.mulPose(Axis.YP.rotationDegrees(95.0F));
            poseStack.mulPose(Axis.XP.rotationDegrees(50.0F));
            poseStack.mulPose(Axis.ZP.rotationDegrees(20.0F));
        } else {
            // Posición normal (sin agacharse)
            poseStack.translate(-0.45F, -0.20F, 0.2F);
            poseStack.mulPose(Axis.YP.rotationDegrees(65.0F));
            poseStack.mulPose(Axis.XP.rotationDegrees(35.0F));
            poseStack.mulPose(Axis.ZP.rotationDegrees(-25.0F));
        }

        itemRenderer.renderStatic(new ItemStack(MainItems.Z_SWORD.get()), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, light, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, player.level(), 0);
        poseStack.popPose();
    }

    private void renderArmaEspalda(PoseStack poseStack, AbstractClientPlayer player, MultiBufferSource bufferSource, int light, ItemStack arma) {
        VertexConsumer textura = bufferSource.getBuffer(RenderType.entityTranslucent(getTextura(arma)));

        // Renderiza el modelo completo del arma (arma + funda)
        if (arma.getItem() == MainItems.BACULO_SAGRADO.get()) {
            baculoEmptyModel.renderToBuffer(poseStack, player, textura, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        } else if (arma.getItem() == MainItems.Z_SWORD.get()) { //Espada Z
            renderEspadaZ(poseStack, player, bufferSource, light);
        } else if(arma.getItem() == MainItems.TRUNKS_SWORD.get()){
            //Aca se renderiza mangoespada + funda
            trunksSwordBackModel.renderCompleto(poseStack, player, textura, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);

        }

    }

    private void renderFundaEspalda(PoseStack poseStack, AbstractClientPlayer player, MultiBufferSource bufferSource, int light, ItemStack arma) {
        VertexConsumer textura = bufferSource.getBuffer(RenderType.entityTranslucent(getTextura(arma)));

        // Renderiza solo la funda del arma
        if (arma.getItem() == MainItems.BACULO_SAGRADO.get()) {
            baculoEmptyModel.renderBaculo(poseStack, player, textura, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        } else if(arma.getItem() == MainItems.TRUNKS_SWORD.get()){
            trunksSwordBackModel.renderWardEspada(poseStack, player, textura, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        }

    }

    private ResourceLocation getTextura(ItemStack arma) {
        if (arma.getItem() == MainItems.BACULO_SAGRADO.get()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/item/armas/baculosaco.png");
        } else if (arma.getItem() == MainItems.Z_SWORD.get()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/item/armas/baculosaco.png"); // Espada Z no necesita textura, pero no puede dar Null asi que pongo relleno xd
        } else if (arma.getItem() == MainItems.TRUNKS_SWORD.get()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/item/armas/trunkssword_layer.png");
        }
        // Agregar más armas con textura completa aquí
        return null;
    }
}
