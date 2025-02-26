package com.yuseix.dragonminez.client.hud.spaceship;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.custom.NaveSaiyanEntity;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class SaiyanSpacePodOverlay {
    private static final ResourceLocation hud = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menuspaceship.png");
    private static int currentPlanetTarget = 0;
    private static boolean kaioAvailable = false;

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGuiOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        GuiGraphics guiGraphics = event.getGuiGraphics();
        double ogGuiScale = 2;

        if (player == null || mc.isPaused() || !(player.getVehicle() instanceof NaveSaiyanEntity) || mc.options.renderDebug
                || mc.options.renderDebugCharts || mc.options.renderFpsChart) {
            mc.getWindow().setGuiScale(ogGuiScale);
            return;
        } else if (mc.getWindow().getWidth() <= 1200 && mc.getWindow().getHeight() <= 700 && mc.getWindow().getGuiScale() != 2) {
            mc.getWindow().setGuiScale(2);
        } else if (mc.getWindow().getGuiScale() != 4) mc.getWindow().setGuiScale(4);

        int textureX = 73, textureY = 136;

        int centroY = (mc.getWindow().getGuiScaledHeight()/2) + textureX/3;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, hud);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
            if (cap.isKaioPlanet()) kaioAvailable = true;
        });
        // Menú con Kaio bloqueado, Tierra y Namek desbloqueados
        guiGraphics.blit(hud, -1, centroY, 155, 1, textureX, textureY);

        // Si habló con Kaio, se le pone el planeta disponible. (Se dibuja KaioDisponible por encima del otro)
        if (isKaioAvailable()) {
            guiGraphics.blit(hud, 7, 111, 7, 52, 51, 15);
        }

        selectTargetPlanet(guiGraphics);
    }

    public static boolean isKaioAvailable() {
        return kaioAvailable;
    }

    public static void updatePlanetTarget(int selectedPlanet) {
        currentPlanetTarget = selectedPlanet;  // Actualiza el planeta objetivo actual
    }

    public static void selectTargetPlanet(GuiGraphics guiGraphics) {
        Minecraft mc = Minecraft.getInstance();

        int ancho = 51, alto = 15;
        int centroX = mc.getWindow().getGuiScaledWidth() - ancho*9 - 10;
        int centroY = mc.getWindow().getGuiScaledHeight() - alto + 60;

        int posX = 83;
        var colorTexto = 0x00ff00;
        var colorSeleccion = 0xfdbf26;

        // Cambia el HUD según el planeta objetivo actual
        switch (currentPlanetTarget) {
            case 0 -> {
                guiGraphics.blit(hud, centroX, centroY, posX, 14, ancho, alto);
                guiGraphics.pose().pushPose();
                guiGraphics.pose().scale(0.8f, 0.8f, 0.8f);
                drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                        Component.translatable("ui.dmz.spacepod.overworld"), 16, centroY+20, colorSeleccion);
                drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                        Component.translatable("ui.dmz.spacepod.namek"), 16, centroY+44, colorTexto);
                if (isKaioAvailable())
                    drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                            Component.translatable("ui.dmz.spacepod.kaio"), 16, centroY+68, colorTexto);
                guiGraphics.pose().popPose();
            }
            case 1 -> {
                guiGraphics.blit(hud, centroX, centroY + 19, posX, 33, ancho, alto);
                guiGraphics.pose().pushPose();
                guiGraphics.pose().scale(0.8f, 0.8f, 0.8f);
                drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                        Component.translatable("ui.dmz.spacepod.overworld"), 16, centroY+20, colorTexto);
                drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                        Component.translatable("ui.dmz.spacepod.namek"), 16, centroY+44, colorSeleccion);
                if (isKaioAvailable())
                    drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                            Component.translatable("ui.dmz.spacepod.kaio"), 16, centroY+68, colorTexto);
                guiGraphics.pose().popPose();
            }
            case 2 -> {
                if (isKaioAvailable()) {
                    guiGraphics.blit(hud, centroX, centroY + 28, posX, 52, ancho, alto);
                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().scale(0.8f, 0.8f, 0.8f);
                    drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                            Component.translatable("ui.dmz.spacepod.overworld"), 16, centroY+20, colorTexto);
                    drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                            Component.translatable("ui.dmz.spacepod.namek"), 16, centroY+44, colorTexto);
                    drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                            Component.translatable("ui.dmz.spacepod.kaio"), 16, centroY+68, colorSeleccion);
                    guiGraphics.pose().popPose();
                } else {
                    currentPlanetTarget = 1;
                }
            }
        }
    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {
        guiGraphics.drawString(font, texto, x + 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x - 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y + 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y - 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y, ColorTexto, false);
    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto) {
        drawStringWithBorder(guiGraphics, font, texto, x, y, ColorTexto, 0);
    }
}
