package com.yuseix.dragonminez.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.SwitchButton;
import com.yuseix.dragonminez.network.C2S.UtilityPanelC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.yuseix.dragonminez.common.Reference;


@OnlyIn(Dist.CLIENT)
public class UtilityPanelOverlay extends Screen {
	private static final ResourceLocation menu = new ResourceLocation(Reference.MOD_ID,
			"textures/gui/menulargomitad.png");
	private int altoTexto, anchoTexto;
	private DMZRightButton rb1, lb1, rb2, lb2;
	private SwitchButton switchButton;

	public UtilityPanelOverlay() {
		super(Component.empty());
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	public void tick() {
		super.tick();
		botonesOpciones();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		renderBackground(guiGraphics);
		menuPanel(guiGraphics);

		super.render(guiGraphics, mouseX, mouseY, partialTicks);
	}

	public void botonesOpciones() {
		Player player = this.minecraft.player;

		this.removeWidget(rb1);
		this.removeWidget(lb1);
		this.removeWidget(rb2);
		this.removeWidget(lb2);
		this.removeWidget(switchButton);

		if (player != null) {
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
				int centroX = (this.width/ 2) - 3;
				int centroY = (this.height/ 2) - 48;
				int levelSF = cap.getFormSkillLevel("super_form");

				switch (cap.getIntValue("race")) { // Human, Saiyan, Namek, Bio, Cold, Majin
					case 0 -> {}
					case 1 -> {
						if (levelSF >= 2) dibujarBotonesForms(cap);
					}
					case 2 -> {}
					case 3 -> {}
					case 4 -> {}
					case 5 -> {}
				}

				switch (cap.getIntValue("race")) {
					case 0 -> {}
					case 1 -> {
						lb2 = new DMZRightButton("left", centroX - 53, centroY + 77, Component.empty(), wa -> {
							ModMessages.sendToServer(new UtilityPanelC2S("teropc", ""));
						});
						this.addRenderableWidget(lb2);

						rb2 = new DMZRightButton("right", centroX + 45, centroY + 77, Component.empty(), wa -> {
							ModMessages.sendToServer(new UtilityPanelC2S("teropc", ""));
						});
						this.addRenderableWidget(rb2);
					}
					case 2 -> {}
					case 3, 4 -> {
						if (!cap.getStringValue("form").equals("base")) {
							lb2 = new DMZRightButton("left", centroX - 53, centroY + 77, Component.empty(), wa -> {
								ModMessages.sendToServer(new UtilityPanelC2S("teropc", ""));
							});
							this.addRenderableWidget(lb2);

							rb2 = new DMZRightButton("right", centroX + 45, centroY + 77, Component.empty(), wa -> {
								ModMessages.sendToServer(new UtilityPanelC2S("teropc", ""));
							});
							this.addRenderableWidget(rb2);
						}
					}
					case 5 -> {}
				}
			});
		}
	}

	private void dibujarBotonesForms(DMZStatsAttributes cap) {
		int centroX = (this.width/ 2) - 3;
		int centroY = (this.height/ 2) - 48;

		if (getNextGroup(cap.getIntValue("race"), cap.getStringValue("groupform"), cap.getFormSkillLevel("super_form")) != null) {
			rb1 = new DMZRightButton("right", centroX + 45, centroY + 37, Component.empty(), wa -> {
				ModMessages.sendToServer(new UtilityPanelC2S("group", getNextGroup(cap.getIntValue("race"), cap.getStringValue("groupform"), cap.getFormSkillLevel("super_form"))));
			});
			this.addRenderableWidget(rb1);
		}

		if (getPrevGroup(cap.getIntValue("race"), cap.getStringValue("groupform")) != null) {
			lb1 = new DMZRightButton("left", centroX - 53, centroY + 37, Component.empty(), wa -> {
				ModMessages.sendToServer(new UtilityPanelC2S("group", getPrevGroup(cap.getIntValue("race"), cap.getStringValue("groupform"))));
			});
			this.addRenderableWidget(lb1);
		}
	}

	private String getNextGroup(int race, String group, int level) {
		String nextGroup = null;
		switch (race) {
			case 0:
				break;
			case 1:
				switch (group) {
					case "" ->  nextGroup = "ssgrades";
					case "ssgrades" ->  {
						if (level >= 5) nextGroup = "ssj";
						else nextGroup = "";
					}
					case "ssj" ->  nextGroup = "";
				}
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
		}
		return nextGroup;
	}

	private String getPrevGroup(int race, String group) {
		String prevGroup = null;
		switch (race) {
			case 0:
				break;
			case 1:
				switch (group) {
					case "" ->  prevGroup = "ssj";
					case "ssgrades" ->  prevGroup = "";
					case "ssj" ->  prevGroup = "ssgrades";
				}
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
		}
		return prevGroup;
	}

	public void menuPanel(GuiGraphics guiGraphics) {
		Player player = this.minecraft.player;
		altoTexto = (this.height/2) - 85;
		anchoTexto = (this.width/2) - 75;

		RenderSystem.enableBlend();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		guiGraphics.blit(menu, anchoTexto, altoTexto, 0, 0, 145, 168);

		if (player != null) {
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
				updateTextAndColors(guiGraphics, cap);
			});
		}

		RenderSystem.disableBlend();
	}

	public void updateTextAndColors(GuiGraphics guiGraphics, DMZStatsAttributes stats) {
		int centroX = (this.width/ 2) - 3;
		int centroY = (this.height/ 2) - 48;
		var colorTexto = 0x20e0ff;
		var colorActivo = 0x6bf26b;
		var colorInactivo = 0xeb1539;
		var colorAUsar = colorTexto;
		String kaioken = "utilitypanel.dmz.kaioken";
		String langGroup = "groupforms.dmz.general.superform";
		String tercerOpcion = "utilitypanel.dmz.tbd";
		switch (stats.getIntValue("race")) {
			case 0:
				if (!(stats.getFormSkillLevel("super_form") >= 1)) {
					langGroup = "forms.dmz.human.base";
				}
				break;
			case 1:
				switch (stats.getStringValue("groupform")) {
					case "":
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
				colorAUsar = stats.getBoolean("tailmode") ? colorInactivo : colorActivo;
				break;
			case 2:
				//langGroup = "groupforms.dmz.namek.shenronforms";
				break;
			case 3:
				langGroup = "groupforms.dmz.bio.evolutionforms";
				tercerOpcion = "utilitypanel.dmz.descend";
				break;
			case 4:
				langGroup = "groupforms.dmz.colddemon.involutionforms";
				tercerOpcion = "utilitypanel.dmz.descend";
				break;
			case 5:
				langGroup = "groupforms.dmz.majin.majinforms";
				break;
		}

		drawStringWithBorder(guiGraphics, this.font, Component.translatable(kaioken).withStyle(ChatFormatting.BOLD), centroX, centroY, colorInactivo);
		drawStringWithBorder(guiGraphics, this.font, Component.translatable(langGroup).withStyle(ChatFormatting.BOLD), centroX, centroY + 40, colorTexto);
		drawStringWithBorder(guiGraphics, this.font, Component.translatable(tercerOpcion).withStyle(ChatFormatting.BOLD), centroX, centroY + 80, colorAUsar);
	}

	public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {
		// Calcular la posición centrada
		int textWidth = font.width(texto);
		int centeredX = x - (textWidth / 2);

		// Dibujar el texto con el borde
		guiGraphics.drawString(font, texto, centeredX + 1, y, ColorBorde, false);
		guiGraphics.drawString(font, texto, centeredX - 1, y, ColorBorde, false);
		guiGraphics.drawString(font, texto, centeredX, y + 1, ColorBorde, false);
		guiGraphics.drawString(font, texto, centeredX, y - 1, ColorBorde, false);
		guiGraphics.drawString(font, texto, centeredX, y, ColorTexto);
	}

	public static void drawStringWithBorder2(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {

		guiGraphics.drawString(font, texto, x + 1, y, ColorBorde, false);
		guiGraphics.drawString(font, texto, x - 1, y, ColorBorde, false);
		guiGraphics.drawString(font, texto, x, y + 1, ColorBorde, false);
		guiGraphics.drawString(font, texto, x, y - 1, ColorBorde, false);
		guiGraphics.drawString(font, texto, x, y, ColorTexto, false);
	}

	public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto) {
		drawStringWithBorder(guiGraphics, font, texto, x, y, ColorTexto, 0);
	}
	public static void drawStringWithBorder2(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto) {
		drawStringWithBorder2(guiGraphics, font, texto, x, y, ColorTexto, 0);
	}

}
