package com.yuseix.dragonminez.client.hud.spaceship;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.events.ClientEvents;
import com.yuseix.dragonminez.common.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.common.stats.DMZStatsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SaiyanSpacePodOverlay extends Screen {
    private static final ResourceLocation menu = new ResourceLocation(Reference.MOD_ID,
            "textures/gui/menulargomitad.png");
    private static final ResourceLocation icons = new ResourceLocation(Reference.MOD_ID,
            "textures/gui/spaceshipicons.png");
    private int altoTexto, anchoTexto;
    private DMZRightButton tierra, namek, kaio;

    public SaiyanSpacePodOverlay() {
        super(Component.empty());
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void tick() {
        super.tick();
        botonesOpciones();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(guiGraphics);
        menuPanel(guiGraphics);

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    public void botonesOpciones() {
        Player player = this.minecraft.player;

        removeWidget(tierra);
        removeWidget(namek);
        removeWidget(kaio);

        if (player != null) {
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                int centroX = (this.width/ 2) - 3 + 35;
                int centroY = (this.height/ 2) - 41;

                tierra = new DMZRightButton("right", centroX, centroY - 18, Component.empty(), wa -> {
                    System.out.println("Teleportando a la tierra");
                    ClientEvents.setTeleporting(true, 0);
                    this.minecraft.setScreen(null);
                });
                this.addRenderableWidget(tierra);


                namek = new DMZRightButton("right", centroX, centroY + 3, Component.empty(), wa -> {
                    ClientEvents.setTeleporting(true, 1);
                    this.minecraft.setScreen(null);
                });
                this.addRenderableWidget(namek);


                if (cap.getBoolean("kaioplanet")) {
                    kaio = new DMZRightButton("right", centroX, centroY + 24, Component.empty(), wa -> {
                        ClientEvents.setTeleporting(true, 3);
                        this.minecraft.setScreen(null);
                    });
                    this.addRenderableWidget(kaio);
                }
            });
        }
    }

    public void menuPanel(GuiGraphics guiGraphics) {
        Player player = this.minecraft.player;
        altoTexto = (this.height/2) - 85;
        anchoTexto = (this.width/2) - 75;

        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.blit(menu, anchoTexto, altoTexto, 0, 0, 145, 168);

        if (player != null) {
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                updateTextAndColors(guiGraphics, cap);
                updateIcons(guiGraphics, cap);
            });
        }

        RenderSystem.disableBlend();
    }

    public void updateTextAndColors(GuiGraphics guiGraphics, DMZStatsAttributes stats) {
        int centroX = (this.width/ 2) - 3;
        int centroY = (this.height/ 2) - 57;
        var colorTexto = 0x20e0ff;
        var colorAUsar = stats.getBoolean("kaioplanet") ? colorTexto : 0x747678;
        drawStringWithBorder(guiGraphics, this.font, Component.translatable("ui.dmz.spacepod.overworld").withStyle(ChatFormatting.BOLD), centroX, centroY, colorTexto);
        drawStringWithBorder(guiGraphics, this.font, Component.translatable("ui.dmz.spacepod.namek").withStyle(ChatFormatting.BOLD), centroX, centroY + 21, colorTexto);
        drawStringWithBorder(guiGraphics, this.font, Component.translatable("ui.dmz.spacepod.kaio").withStyle(ChatFormatting.BOLD), centroX, centroY + 42, colorAUsar);
        drawStringWithBorder(guiGraphics, this.font, Component.literal("???").withStyle(ChatFormatting.BOLD), centroX, centroY + 63, 0x747678);
        drawStringWithBorder(guiGraphics, this.font, Component.literal("???").withStyle(ChatFormatting.BOLD), centroX, centroY + 84, 0x747678);
        drawStringWithBorder(guiGraphics, this.font, Component.literal("???").withStyle(ChatFormatting.BOLD), centroX, centroY + 105, 0x747678);
    }

    public void updateIcons(GuiGraphics guiGraphics, DMZStatsAttributes stats) {
        int centroX = (this.width/ 2) - 42;
        int centroY = (this.height/ 2) - 58;
        int iconX = 3;
        int iconY = 3;
        int size = 11;

        guiGraphics.pose().pushPose();

        guiGraphics.blit(icons, centroX, centroY, iconX, iconY, size, size); // Tierra
        guiGraphics.blit(icons, centroX, centroY+21, iconX, iconY+14, size, size); // Namek
        if (stats.getBoolean("kaioplanet")) {
            guiGraphics.blit(icons, centroX, centroY + 42, iconX, iconY+28, size, size); // Kaiosama (Disponible)
        } else {
            guiGraphics.blit(icons, centroX, centroY + 42, iconX+17, iconY+28, size, size); // Kaiosama (Bloqueado)
        }
        guiGraphics.blit(icons, centroX, centroY + 63, iconX+17, iconY+42, size, size); // Supremo (Bloqueado)
        guiGraphics.blit(icons, centroX, centroY + 84, iconX+17, iconY+56, size, size); // Cereal (Bloqueado)
        guiGraphics.blit(icons, centroX, centroY + 105, iconX+17, iconY+70, size, size); // Bills (Bloqueado)

        guiGraphics.pose().popPose();
    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {
        // Calcular la posición centrada
        int textWidth = font.width(texto);
        int centeredX = x - (textWidth / 2);

        // Dibujar el texto con el borde
        guiGraphics.drawString(font, texto, centeredX + 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, centeredX - 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, centeredX, y + 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, centeredX, y - 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, centeredX, y, ColorTexto);
    }

    public static void drawStringWithBorder2(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {

        guiGraphics.drawString(font, texto, x + 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x - 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y + 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y - 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y, ColorTexto, false);
    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto) {
        drawStringWithBorder(guiGraphics, font, texto, x, y, ColorTexto, 0);
    }
    public static void drawStringWithBorder2(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto) {
        drawStringWithBorder2(guiGraphics, font, texto, x, y, ColorTexto, 0);
    }

}