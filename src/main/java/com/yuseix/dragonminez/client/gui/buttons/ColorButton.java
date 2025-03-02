package com.yuseix.dragonminez.client.gui.buttons;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ColorButton extends Button {


    private String tipo = "";

    private static final ResourceLocation botones = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/buttons/characterbuttons.png");

    public ColorButton(String partePJ, int pX, int pY, Component pMessage, OnPress pOnPress) {
        super(pX, pY, 20, 14, pMessage, pOnPress, DEFAULT_NARRATION);
        this.tipo = partePJ;
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            int color;
            float r, g, b;
            switch (tipo) {
                case "eyeColor1":
                    //CONVERTIR DE DECIMAL A FLOAT
                    color = cap.getIntValue("eye1color"); // blanco
                    r = (color >> 16) / 255.0F;
                    g = ((color >> 8) & 0xff) / 255.0f;
                    b = (color & 0xff) / 255.0f;

                    pGuiGraphics.setColor(r, g, b, 1.0f);
                    break;
                case "eyeColor2":
                    //CONVERTIR DE DECIMAL A FLOAT
                    color = cap.getIntValue("eye2color"); // blanco
                    r = (color >> 16) / 255.0F;
                    g = ((color >> 8) & 0xff) / 255.0f;
                    b = (color & 0xff) / 255.0f;

                    pGuiGraphics.setColor(r, g, b, 1.0f);
                    break;
                case "bodyColor1":
                    //CONVERTIR DE DECIMAL A FLOAT
                    color = cap.getIntValue("bodycolor"); // blanco
                    r = (color >> 16) / 255.0F;
                    g = ((color >> 8) & 0xff) / 255.0f;
                    b = (color & 0xff) / 255.0f;

                    pGuiGraphics.setColor(r, g, b, 1.0f);
                    break;
                case "bodyColor2":
                    //CONVERTIR DE DECIMAL A FLOAT
                    color = cap.getIntValue("bodycolor2"); // blanco
                    r = (color >> 16) / 255.0F;
                    g = ((color >> 8) & 0xff) / 255.0f;
                    b = (color & 0xff) / 255.0f;

                    pGuiGraphics.setColor(r, g, b, 1.0f);
                    break;
                case "bodyColor3":
                    //CONVERTIR DE DECIMAL A FLOAT
                    color = cap.getIntValue("bodycolor3"); // blanco
                    r = (color >> 16) / 255.0F;
                    g = ((color >> 8) & 0xff) / 255.0f;
                    b = (color & 0xff) / 255.0f;

                    pGuiGraphics.setColor(r, g, b, 1.0f);
                    break;
                case "hairColor":
                    //CONVERTIR DE DECIMAL A FLOAT
                    color = cap.getIntValue("haircolor"); // blanco
                    r = (color >> 16) / 255.0F;
                    g = ((color >> 8) & 0xff) / 255.0f;
                    b = (color & 0xff) / 255.0f;

                    pGuiGraphics.setColor(r, g, b, 1.0f);
                    break;
                case "auraColor":
                    //CONVERTIR DE DECIMAL A FLOAT
                    color = cap.getIntValue("auracolor"); // blanco
                    r = (color >> 16) / 255.0F;
                    g = ((color >> 8) & 0xff) / 255.0f;
                    b = (color & 0xff) / 255.0f;

                    pGuiGraphics.setColor(r, g, b, 1.0f);
                    break;
            }

            RenderSystem.setShaderTexture(0, botones);

            pGuiGraphics.blit(botones, this.getX(), this.getY(), 41, 0, 20, 14);

        });


    }
}
