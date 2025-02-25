package com.yuseix.dragonminez.client.hud.spaceship;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
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
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class SaiyanSpacePodOverlay implements RenderEntityInv {

    private static final ResourceLocation hud = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menuspaceship.png");
    private static int currentPlanetTarget = 0;
    private static boolean kaioAvailable;

    public static final IGuiOverlay HUD_SAIYAN = (forgeGui, guiGraphics, v, i, i1) -> {
        Player player = Minecraft.getInstance().player;

        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, hud);

        if (player == null || !(player.getVehicle() instanceof NaveSaiyanEntity)) return;

        if (Minecraft.getInstance().options.renderDebug || Minecraft.getInstance().options.renderDebugCharts || Minecraft.getInstance().options.renderFpsChart) return;

        int windowWidth = Minecraft.getInstance().getWindow().getScreenWidth();
        int windowHeight = Minecraft.getInstance().getWindow().getScreenHeight();
        // Obtener el tamaño de la ventana
        int screenWidth = guiGraphics.guiWidth();
        int screenHeight = guiGraphics.guiHeight();

        int xPos = 0;
        int yPos;

        if (windowWidth <= 1600 && windowHeight <= 900) {
            // Calcular posición centrada
            yPos = (screenHeight / 2) - 75;
        } else {
            yPos = (screenHeight / 2) - 120;
        }

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(1.2f, 1.2f, 1.0f);

        // Menú con Kaio bloqueado, Tierra y Namek desbloqueados
        guiGraphics.blit(hud,
                xPos,
                yPos,
                155,
                1,
                73,
                136);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
            if (cap.isKaioPlanet()) setKaioAvailable(true);
        });

        // Si habló con Kaio, se le pone el planeta disponible. (Se dibuja KaioDisponible por encima del otro)
        if (isKaioAvailable()) {
            guiGraphics.blit(hud,
                    7,
                    111,
                    7,
                    52,
                    51,
                    15);
        }

        RenderSystem.disableBlend();
        selectTargetPlanet(guiGraphics);
        guiGraphics.pose().popPose();
    };

    public static boolean isKaioAvailable() {
        return kaioAvailable;
    }

    // Métod0 para actualizar el estado
    public static void setKaioAvailable(boolean available) {
        kaioAvailable = available;
    }

    public static void updatePlanetTarget(int selectedPlanet) {
        currentPlanetTarget = selectedPlanet;  // Actualiza el planeta objetivo actual
    }

    public static void selectTargetPlanet(GuiGraphics guiGraphics) {
        int windowWidth = Minecraft.getInstance().getWindow().getScreenWidth();
        int windowHeight = Minecraft.getInstance().getWindow().getScreenHeight();
        // Obtener el tamaño de la ventana
        int screenHeight = guiGraphics.guiHeight();

        int dibujoX = 7;
        int dibujoY;
        int textoY;

        if (windowWidth <= 1600 && windowHeight <= 900) {
            // Calcular posición centrada
            dibujoY = (screenHeight / 2) - 62;
            textoY = (screenHeight / 2) - 62;
        } else {
            dibujoY = (screenHeight / 2) - 107;
            textoY = (screenHeight / 2) - 86;
        }

        int posX = 83, ancho = 51, alto = 15;
        var colorTexto = 0x00ff00;
        var colorSeleccion = 0xfdbf26;



        // Cambia el HUD según el planeta objetivo actual
        switch (currentPlanetTarget) {
            case 0 -> {
                guiGraphics.blit(hud, dibujoX, dibujoY, posX, 14, ancho, alto);
                guiGraphics.pose().scale(0.8f, 0.8f, 0.8f);
                drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                        Component.translatable("ui.dmz.spacepod.overworld"), 16, textoY+20, colorSeleccion);
                drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                        Component.translatable("ui.dmz.spacepod.namek"), 16, textoY+44, colorTexto);
                if (isKaioAvailable())
                    drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                            Component.translatable("ui.dmz.spacepod.kaio"), 16, textoY+68, colorTexto);
            }
            case 1 -> {
                guiGraphics.blit(hud, dibujoX, dibujoY + 19, posX, 33, ancho, alto);
                guiGraphics.pose().scale(0.8f, 0.8f, 0.8f);
                drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                        Component.translatable("ui.dmz.spacepod.overworld"), 16, textoY+20, colorTexto);
                drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                        Component.translatable("ui.dmz.spacepod.namek"), 16, textoY+44, colorSeleccion);
                if (isKaioAvailable())
                    drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                            Component.translatable("ui.dmz.spacepod.kaio"), 16, textoY+68, colorTexto);
            }
            case 2 -> {
                if (isKaioAvailable()) {
                    guiGraphics.blit(hud, dibujoX, dibujoY + 28, posX, 52, ancho, alto);
                    guiGraphics.pose().scale(0.8f, 0.8f, 0.8f);
                    drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                            Component.translatable("ui.dmz.spacepod.overworld"), 16, textoY+20, colorTexto);
                    drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                            Component.translatable("ui.dmz.spacepod.namek"), 16, textoY+44, colorTexto);
                    drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                            Component.translatable("ui.dmz.spacepod.kaio"), 16, textoY+68, colorSeleccion);
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
