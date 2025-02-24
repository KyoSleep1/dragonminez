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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.Locale;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, value = Dist.CLIENT)
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

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {
			if (!stats.isAcceptCharacter()) return;
		// Acá tendría q hacer, en la textura y los cuadritos
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
				case "teropc":
					ModMessages.sendToServer(new UtilityPanelC2S("teropc", "up"));
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
				case "teropc":
					ModMessages.sendToServer(new UtilityPanelC2S("teropc", "down"));
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
				case "teropc":
					ModMessages.sendToServer(new UtilityPanelC2S("teropc", "left"));
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
				case "teropc":
					ModMessages.sendToServer(new UtilityPanelC2S("teropc", "right"));
					break;
			}
		}
	}

	public static void updateTextAndColors(GuiGraphics guiGraphics, DMZStatsAttributes stats) {
		int dibujoX = 7, posX = 83, ancho = 51, alto = 15;
		var colorTexto = 0xffffff;
		var colorSeleccion = 0xfdbf26;
		var colorActivo = 0x6bf26b;
		var colorInactivo = 0xeb1539;
		var colorAUsar = colorTexto;
		race = stats.getRace();
		String kaioken = "utilitypanel.dmz.kaioken";
		String actualGroup = stats.getDmzGroupForm();
		String langGroup = "groupforms.dmz.general.superform";
		String tercerOpcion = "utilitypanel.dmz.tbd";
		switch (race) {
			case 0:
				actualGroup = stats.getDmzGroupForm().equals("") ? "superform" : actualGroup;
				break;
			case 1:
				actualGroup = stats.getDmzGroupForm().equals("") ? "oozarus" : actualGroup;
				switch (actualGroup) {
					case "oozarus":
						langGroup = "groupforms.dmz.saiyan.oozarus";
						break;
					case "ssgrades":
						langGroup = "groupforms.dmz.saiyan.ssgrades";
						break;
					case "ssj":
						langGroup = "groupforms.dmz.saiyan.ssj";
						break;
				}
				tercerOpcion = "utilitypanel.dmz.tailmode";
				break;
			case 2:
				actualGroup = stats.getDmzGroupForm().equals("") ? "superform" : actualGroup;
				langGroup = "groupforms.dmz.namek.shenronforms";
				break;
			case 3:
				actualGroup = stats.getDmzGroupForm().equals("") ? "superform" : actualGroup;
				langGroup = "groupforms.dmz.bio.evolutionforms";
				tercerOpcion = "utilitypanel.dmz.descend";
				break;
			case 4:
				actualGroup = stats.getDmzGroupForm().equals("") ? "superform" : actualGroup;
				langGroup = "groupforms.dmz.colddemon.involutionforms";
				tercerOpcion = "utilitypanel.dmz.descend";
				break;
			case 5:
				actualGroup = stats.getDmzGroupForm().equals("") ? "superform" : actualGroup;
				langGroup = "groupforms.dmz.majin.majinforms";
				break;
		}

		switch (currentSelection.toLowerCase(Locale.ROOT)) {
			case "kaioken":
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.translatable(kaioken), 13, 95, colorSeleccion);
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.translatable(langGroup), 13, 120, colorTexto);
				if (race == 1) {
					if (!stats.isTailMode()) colorAUsar = colorActivo;
					else colorAUsar = colorInactivo;
					drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
							Component.translatable(tercerOpcion), 13, 145, colorAUsar);
				} else {
					drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
							Component.translatable(tercerOpcion), 13, 145, colorTexto);
				}
				break;
			case "groupforms":
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.translatable(kaioken), 13, 95, colorInactivo);
				switch (race) {
					case 0:
						switch (actualGroup) {
							case "superform":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.general.superform"), 13, 120, colorSeleccion);
								break;
						}
						break;
					case 1:
						switch (actualGroup) {
							case "oozarus":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.saiyan.oozarus"), 13, 120, colorSeleccion);
								//System.out.println(actualGroup);
								break;
							case "ssgrades":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.saiyan.ssgrades"), 13, 120, colorSeleccion);
								//System.out.println(actualGroup);
								break;
							case "ssj":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.saiyan.ssj"), 13, 120, colorSeleccion);
								//System.out.println(actualGroup);
								break;
						}
					case 2:
						switch (actualGroup) {
							case "superform":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.general.superform"), 13, 120, colorSeleccion);
								break;
							case "orange":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.namek.shenronforms"), 13, 120, colorSeleccion);
								break;
						}
						break;
					case 3:
						switch (actualGroup) {
							case "superform":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.bio.evolutionforms"), 13, 120, colorSeleccion);
								break;
						}
						break;
					case 4:
						switch (actualGroup) {
							case "superform":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.colddemon.involutionforms"), 13, 120, colorSeleccion);
								break;
						}
						break;
					case 5:
						switch (actualGroup) {
							case "superform":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.majin.majinforms"), 13, 120, colorSeleccion);
								break;
						}
						break;
				}
				if (race == 1) {
					colorAUsar = stats.isTailMode() ? colorActivo : colorInactivo;
					drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
							Component.translatable(tercerOpcion), 13, 145, colorAUsar);
				} else {
					drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
							Component.translatable(tercerOpcion), 13, 145, colorTexto);
				}
				break;
			case "teropc":
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.translatable(kaioken), 13, 95, colorInactivo);
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.translatable(langGroup), 13, 120, colorTexto);
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.translatable(tercerOpcion), 13, 145, colorSeleccion);
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
								setCurrentSelection("teropc");
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
								setCurrentSelection("teropc");
								break;
						}
						break;
					case "left", "right":
						break;
				}
				break;
			case "teropc":
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
