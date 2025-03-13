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
public class SwitchButton extends Button {

    private boolean isActive;

    private static final ResourceLocation botones = new ResourceLocation(Reference.MOD_ID,
            "textures/gui/buttons/characterbuttons.png");

    public SwitchButton(boolean active, int pX, int pY, Component pMessage, OnPress pOnPress) {
        super(pX, pY, 20, 10, pMessage, pOnPress, DEFAULT_NARRATION);

        isActive = active;

    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

        int buttonX = 122;
        int buttonY = 0;

        if(isActive){
            buttonY = 0;
        } else {
            buttonY = 10;
        }


        pGuiGraphics.blit(botones, this.getX(), this.getY(), buttonX, buttonY, 20, 10);
    }
}
