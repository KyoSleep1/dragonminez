package com.yuseix.dragonminez.client.gui.buttons;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class DMZRightButton extends Button {

    private String tipo = "";

    private static final ResourceLocation botones = new ResourceLocation(Reference.MOD_ID,
            "textures/gui/buttons/characterbuttons.png");

    public DMZRightButton(String tipo, int pX, int pY, Component pMessage, OnPress pOnPress) {
        super(pX, pY, 8, 14, pMessage, pOnPress, DEFAULT_NARRATION);

        this.tipo = tipo;

    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        int buttonY = 0;
        int offset = 0;

        if(tipo.equals("right")){
            if (this.isHovered()) {
                //ejemplo para añadir botones mas adelante buttonX += 10;
                buttonY += 14;
            }

            offset = 20;
        } else if(tipo.equals("left")){
            if (this.isHovered()) {
                //ejemplo para añadir botones mas adelante buttonX += 10;
                buttonY += 14;
            }
            offset = 32;
        }

        pGuiGraphics.setColor(1.0f,1.0f,1.0f,1.0f);

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, botones);

        pGuiGraphics.blit(botones, this.getX(), this.getY(), offset, buttonY, 8, 14);


    }
}
