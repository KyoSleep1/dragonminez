package com.yuseix.dragonminez.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
import com.yuseix.dragonminez.network.C2S.UtilityPanelC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.Keys;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.Locale;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class UtilityPanelOverlay implements RenderEntityInv {
	private static final ResourceLocation hud = new ResourceLocation(DragonMineZ.MOD_ID,
			"textures/gui/utilitypanel.png");
	private static String currentSelection = "kaioken";
	private static int race = 0;

	public static final IGuiOverlay HUD_UTILITY = (forgeGui, guiGraphics, v, i, i1) -> {
		Player player = Minecraft.getInstance().player;

		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, hud);

		if (player == null || !Keys.UTILITY_PANEL.isDown() || Minecraft.getInstance().options.renderDebug || Minecraft.getInstance().options.renderDebugCharts || Minecraft.getInstance().options.renderFpsChart) return;

		guiGraphics.pose().pushPose();
		guiGraphics.pose().scale(1.2f, 1.2f, 1.0f);

		// Acá tendría q hacer, en la textura, los cuadritos y todo eso para las opciones xd
		// Kaioken
		guiGraphics.blit(hud,
				0,
				0,
				0,
				0,
				0,
				0);

		//GroupForm
		guiGraphics.blit(hud,
				0,
				0,
				0,
				0,
				0,
				0);

		//Cola
		guiGraphics.blit(hud,
				0,
				0,
				0,
				0,
				0,
				0);

		RenderSystem.disableBlend();
		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {
			updateTextAndColors(guiGraphics, stats);
		});
		guiGraphics.pose().popPose();
	};

	public static void setCurrentSelection(String selection) {
		currentSelection = selection;
	}

	@SubscribeEvent
	public static void onKeyInputEvent(InputEvent.Key event) {
		if (Keys.SELECT_UP.consumeClick()) {
			switch (currentSelection.toLowerCase(Locale.ROOT)) {
				case "kaioken":
					ModMessages.sendToServer(new UtilityPanelC2S("kaioken", "up"));
					break;
				case "groupforms":
					ModMessages.sendToServer(new UtilityPanelC2S("groupforms", "up"));
					break;
				case "cola":
					ModMessages.sendToServer(new UtilityPanelC2S("cola", "up"));
					break;
			}
		} else if (Keys.SELECT_DOWN.consumeClick()) {
			switch (currentSelection.toLowerCase(Locale.ROOT)) {
				case "kaioken":
					ModMessages.sendToServer(new UtilityPanelC2S("kaioken", "down"));
					break;
				case "groupforms":
					ModMessages.sendToServer(new UtilityPanelC2S("groupforms", "down"));
					break;
				case "cola":
					ModMessages.sendToServer(new UtilityPanelC2S("cola", "down"));
					break;
			}
		} else if (Keys.SELECT_LEFT.consumeClick()) {
			switch (currentSelection.toLowerCase(Locale.ROOT)) {
				case "kaioken":
					ModMessages.sendToServer(new UtilityPanelC2S("kaioken", "left"));
					break;
				case "groupforms":
					ModMessages.sendToServer(new UtilityPanelC2S("groupforms", "left"));
					break;
				case "cola":
					ModMessages.sendToServer(new UtilityPanelC2S("cola", "left"));
					break;
			}
		} else if (Keys.SELECT_RIGHT.consumeClick()) {
			switch (currentSelection.toLowerCase(Locale.ROOT)) {
				case "kaioken":
					ModMessages.sendToServer(new UtilityPanelC2S("kaioken", "right"));
					break;
				case "groupforms":
					ModMessages.sendToServer(new UtilityPanelC2S("groupforms", "right"));
					break;
				case "cola":
					ModMessages.sendToServer(new UtilityPanelC2S("cola", "right"));
					break;
			}
		}
	}

	public static void updateTextAndColors(GuiGraphics guiGraphics, DMZStatsAttributes stats) {
		int dibujoX = 7, posX = 83, ancho = 51, alto = 15;
		var colorTexto = 0x00ff00;
		var colorSeleccion = 0xfdbf26;
		race = stats.getRace();
		String actualGroup = stats.getDmzGroupForm();
		switch (race) {
			case 0, 2, 3, 4, 5:
				actualGroup = stats.getDmzGroupForm().equals("") ? "superform" : actualGroup;
				break;
			case 1:
				actualGroup = stats.getDmzGroupForm().equals("") ? "oozarus" : actualGroup;
				break;
		}

		// Cambia el HUD según el planeta objetivo actual
		switch (currentSelection.toLowerCase(Locale.ROOT)) {
			case "kaioken":
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.literal("kaioken"), 13, 95, colorSeleccion);
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.literal(actualGroup), 16, 120, colorTexto);
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.literal("cola"), 16, 145, colorTexto);
				break;
			case "groupforms":
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.literal("kaioken"), 13, 95, colorTexto);
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.literal("cola"), 16, 145, colorTexto);
				switch (race) {
					case 0:
						switch (actualGroup) {
							case "":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.literal("superform"), 16, 120, colorSeleccion);
								break;
						}
						break;
					case 1:
						switch (actualGroup) {
							case "oozarus":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.literal("oozarus"), 16, 120, colorSeleccion);
								//System.out.println(actualGroup);
								break;
							case "ssgrades":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.literal("ssgrades"), 16, 120, colorSeleccion);
								//System.out.println(actualGroup);
								break;
							case "ssj":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.literal("ssj"), 16, 120, colorSeleccion);
								//System.out.println(actualGroup);
								break;
						}
				}
				break;
			case "cola":
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.literal("kaioken"), 13, 95, colorTexto);
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.literal(actualGroup), 16, 120, colorTexto);
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.literal("cola"), 16, 145, colorSeleccion);
				break;
		}
	}

	public static void updateSelection(String tipo, String direccion) {
		switch (tipo) {
			case "kaioken":
				switch (direccion) {
					case "up":
						switch (race) {
							case 0, 2, 3, 4, 5:
								setCurrentSelection("groupforms");
								break;
							case 1:
								setCurrentSelection("cola");
								break;
						}
						break;
					case "down":
						setCurrentSelection("groupforms");
						break;
					case "left", "right":
						break;
				}
				break;
			case "groupforms":
				switch (direccion) {
					case "up":
						setCurrentSelection("kaioken");
						break;
					case "down":
						switch (race) {
							case 0, 2, 3, 4, 5:
								setCurrentSelection("kaioken");
								break;
							case 1:
								setCurrentSelection("cola");
								break;
						}
						break;
					case "left", "right":
						break;
				}
				break;
			case "cola":
				switch (direccion) {
					case "up":
						setCurrentSelection("groupforms");
						break;
					case "down":
						setCurrentSelection("kaioken");
						break;
					case "left", "right":
						break;
				}
				break;
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
