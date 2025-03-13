package com.yuseix.dragonminez.client.gui.buttons;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CustomButtons extends Button {

    private String tipo;

    private static final ResourceLocation botones = new ResourceLocation(Reference.MOD_ID,
            "textures/gui/buttons/characterbuttons.png");

    public CustomButtons(String tipo,int pX, int pY, Component pMessage, OnPress pOnPress) {
        super(pX, pY, 10, 10, pMessage, pOnPress, DEFAULT_NARRATION);

        this.tipo = tipo;

    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

        int buttonX = 0;
        int buttonY = 0;

        switch (tipo){
            case "stat":
                if (this.isHovered()) {
                    //ejemplo para añadir botones mas adelante buttonX += 10;
                    buttonY += 10;
                }
                break;
            case "igual":
                buttonX = 102;
                if (this.isHovered()) {
                    buttonY += 10;
                }
                break;
            case "info":
                buttonX = 112;
                if (this.isHovered()) {
                    buttonY += 10;
                }
                break;
            case "delete":
                buttonX = 10;
                if (this.isHovered()) {
                    buttonY += 10;
                }
                break;
            default:
                break;
        }


        pGuiGraphics.blit(botones, this.getX(), this.getY(), buttonX, buttonY, 10, 10);
    }
}
