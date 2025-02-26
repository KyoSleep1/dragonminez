package com.yuseix.dragonminez.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
import com.yuseix.dragonminez.init.entity.custom.NaveSaiyanEntity;
import com.yuseix.dragonminez.init.entity.custom.NubeEntity;
import com.yuseix.dragonminez.init.entity.custom.NubeNegraEntity;
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
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.Locale;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class UtilityPanelOverlay {
	private static final ResourceLocation hud = new ResourceLocation(DragonMineZ.MOD_ID,
			"textures/gui/utilitypanel.png");
	private static String currentSelection = "kaioken";
	private static int race = 0;

	@SubscribeEvent
	public static void onRenderGameOverlay(RenderGuiOverlayEvent.Pre event) {
		Minecraft mc = Minecraft.getInstance();
		Player player = mc.player;
		GuiGraphics guiGraphics = event.getGuiGraphics();
		double ogGuiScale = 2;

		if (mc.isPaused() || player == null || (player.getVehicle() instanceof NaveSaiyanEntity) || (player.getVehicle() instanceof NubeNegraEntity)
				|| (player.getVehicle() instanceof NubeEntity) || !Keys.UTILITY_PANEL.isDown() || mc.options.renderDebug || mc.options.renderDebugCharts || mc.options.renderFpsChart) {
			mc.getWindow().setGuiScale(ogGuiScale);
			return;
		} else if (mc.getWindow().getWidth() <= 1200 && mc.getWindow().getHeight() <= 700) {
			mc.getWindow().setGuiScale(2);
		} else mc.getWindow().setGuiScale(4);

		int fondoX = 62, fondoY = 73;

		int centroX = mc.getWindow().getGuiScaledWidth() - fondoX + 2;
		int centroY = mc.getWindow().getGuiScaledHeight() - fondoY - 90;

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, hud);

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {
			if (!stats.isAcceptCharacter()) return;

		guiGraphics.blit(hud, centroX, centroY, 3, 4, 62, 73);

		switch (currentSelection) {
			case "kaioken" -> guiGraphics.blit(hud, centroX + 5, centroY + 11, 84, 15, 51, 16);
			case "groupforms" -> guiGraphics.blit(hud, centroX + 5, centroY + 30, 84, 15, 51, 15);
			case "teropc" -> guiGraphics.blit(hud, centroX + 5, centroY + 49, 84, 15, 51, 15);
		}


		updateTextAndColors(guiGraphics, stats);
		});
	}

	public static void setCurrentSelection(String selection) {
		currentSelection = selection;
	}

	public static void updateTextAndColors(GuiGraphics guiGraphics, DMZStatsAttributes stats) {
		Minecraft mc = Minecraft.getInstance();
		int fondoX = 62, fondoY = 73, centroX, centroY;

		if (mc.getWindow().getGuiScale() == 2) {
			centroX = mc.getWindow().getGuiScaledWidth() + fondoX*2 + 26;
			centroY = mc.getWindow().getGuiScaledHeight() - fondoY - 26;
		} else {
			centroX = mc.getWindow().getGuiScaledWidth() + fondoX*2 + 54;
			centroY = mc.getWindow().getGuiScaledHeight() - fondoY - 19;
		}

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
		guiGraphics.pose().pushPose();
		guiGraphics.pose().scale(0.65f, 0.65f, 0.65f);
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
						Component.translatable(kaioken), centroX, centroY, colorSeleccion);
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.translatable(langGroup), centroX, centroY+30, colorTexto);
				if (race == 1) {
					colorAUsar = stats.isTailMode() ? colorInactivo : colorActivo;
					drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
							Component.translatable(tercerOpcion), centroX, centroY+60, colorAUsar);
				} else {
					drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
							Component.translatable(tercerOpcion), centroX, centroY+60, colorTexto);
				}
				break;
			case "groupforms":
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.translatable(kaioken), centroX, centroY, colorInactivo);
				switch (race) {
					case 0:
						switch (actualGroup) {
							case "superform":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.general.superform"), centroX, centroY+30, colorSeleccion);
								break;
						}
						break;
					case 1:
						switch (actualGroup) {
							case "oozarus":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.saiyan.oozarus"), centroX, centroY+30, colorSeleccion);
								//System.out.println(actualGroup);
								break;
							case "ssgrades":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.saiyan.ssgrades"), centroX, centroY+30, colorSeleccion);
								//System.out.println(actualGroup);
								break;
							case "ssj":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.saiyan.ssj"), centroX, centroY+30, colorSeleccion);
								//System.out.println(actualGroup);
								break;
						}
					case 2:
						switch (actualGroup) {
							case "superform":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.general.superform"), centroX, centroY+30, colorSeleccion);
								break;
							case "orange":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.namek.shenronforms"), centroX, centroY+30, colorSeleccion);
								break;
						}
						break;
					case 3:
						switch (actualGroup) {
							case "superform":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.bio.evolutionforms"), centroX, centroY+30, colorSeleccion);
								break;
						}
						break;
					case 4:
						switch (actualGroup) {
							case "superform":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.colddemon.involutionforms"), centroX, centroY+30, colorSeleccion);
								break;
						}
						break;
					case 5:
						switch (actualGroup) {
							case "superform":
								drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
										Component.translatable("groupforms.dmz.majin.majinforms"), centroX, centroY+30, colorSeleccion);
								break;
						}
						break;
				}
				if (race == 1) {
					colorAUsar = stats.isTailMode() ? colorInactivo : colorActivo;
					drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
							Component.translatable(tercerOpcion), centroX, centroY+60, colorAUsar);
				} else {
					drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
							Component.translatable(tercerOpcion), centroX, centroY+60, colorTexto);
				}
				break;
			case "teropc":
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.translatable(kaioken), centroX, centroY, colorInactivo);
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.translatable(langGroup), centroX, centroY+30, colorTexto);
				drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
						Component.translatable(tercerOpcion), centroX, centroY+60, colorSeleccion);
				break;
		}
		guiGraphics.pose().popPose();
	}

	@SubscribeEvent
	public static void onKeyInputEvent(InputEvent.Key event) {
		if (Keys.SELECT_UP.isDown()) {
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
		} else if (Keys.SELECT_DOWN.isDown()) {
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
		} else if (Keys.SELECT_LEFT.isDown()) {
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
		} else if (Keys.SELECT_RIGHT.isDown()) {
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

	public static void updateSelection(String tipo, String direccion) {
		switch (tipo) {
			case "kaioken":
				switch (direccion) {
					case "up":
						switch (race) {
							case 0, 2, 5:
								setCurrentSelection("groupforms");
								break;
							case 1, 3, 4:
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
							case 0, 2, 5:
								setCurrentSelection("kaioken");
								break;
							case 1, 3, 4:
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
