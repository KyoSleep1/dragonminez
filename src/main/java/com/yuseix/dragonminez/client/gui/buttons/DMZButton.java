package com.yuseix.dragonminez.client.gui.buttons;

import com.yuseix.dragonminez.common.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DMZButton extends Button {

    private static final ResourceLocation botones = new ResourceLocation(Reference.MOD_ID,
            "textures/gui/buttons/guibuttons.png");


    public DMZButton(int pX, int pY, Component pMessage, OnPress pOnPress) {
        super(pX, pY, 61, 18, pMessage, pOnPress, DEFAULT_NARRATION);

    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        Minecraft minecraft = Minecraft.getInstance();

        int buttonY = 0;
        if(this.isHovered){
            buttonY += 18;
        }

        // Dibujar la textura del botón
        pGuiGraphics.blit(botones, this.getX(), this.getY(), 0, buttonY, 61, 18);


        // Dibujar el texto del botón
        int textColor = this.isHovered ? 0x7CFDD6 : 0xFFFFFF;
        pGuiGraphics.drawCenteredString(minecraft.font, this.getMessage(), this.getX() + this.width / 2, this.getY() + (this.height - 8) / 2, textColor);
    }
}
