package com.yuseix.dragonminez.init.menus.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.menus.menutypes.KikonoArmorStationMenuType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class KikonoArmorStationScreen extends AbstractContainerScreen<KikonoArmorStationMenuType> {
	private static final ResourceLocation TEXTURE =
			new ResourceLocation(Reference.MOD_ID, "textures/gui/kikono_armor_station_gui.png");

	public KikonoArmorStationScreen(KikonoArmorStationMenuType pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;

		guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

		renderProgressArrow(guiGraphics, x, y);
	}

	private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
		if (menu.isCrafting()) {
			guiGraphics.blit(TEXTURE, x + 111, y + 35, 177, 2, menu.getScaledProgress(), 17);
		}
	}

	@Override
	public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float delta) {
		renderBackground(guiGraphics);
		super.render(guiGraphics, pMouseX, pMouseY, delta);
		renderTooltip(guiGraphics, pMouseX, pMouseY);
	}
}
