package com.yuseix.dragonminez.client.gui.buttons;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
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
public class ColorButton2 extends Button {

    private int colorBoton;

    private static final ResourceLocation botones = new ResourceLocation(Reference.MOD_ID,
            "textures/gui/buttons/characterbuttons.png");

    public ColorButton2(int pX, int pY, int colorBoton, Component pMessage, OnPress pOnPress) {
        super(pX, pY, 18, 14, pMessage, pOnPress, DEFAULT_NARRATION);

        this.colorBoton = colorBoton;
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            int color;
            float r, g, b;

            color = this.colorBoton;
            r = (color >> 16) / 255.0F;
            g = ((color >> 8) & 0xff) / 255.0f;
            b = (color & 0xff) / 255.0f;

            pGuiGraphics.setColor(r, g, b, 1.0f);


            RenderSystem.setShaderTexture(0, botones);

            pGuiGraphics.blit(botones, this.getX(), this.getY(), 41, 0, 18, 14);

        });


    }
}
