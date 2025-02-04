package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.DMZGuiButtons;
import com.yuseix.dragonminez.client.gui.buttons.SwitchButton;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ConfigMenu extends Screen {
	private static final ResourceLocation menutexture = new ResourceLocation(DragonMineZ.MOD_ID,
			"textures/gui/menulargo2.png");

	private final List<AbstractWidget> switchesButtons = new ArrayList<>();
	private int altoTexto, anchoTexto;
	private DMZGuiButtons menuButton;
	private SwitchButton switchButton;

	public ConfigMenu() {
		super(Component.empty());
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	public void tick() {
		super.tick();
		botonesMenus();
		botonesConfigs();
	}

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
		renderBackground(graphics);
		menuPanel(graphics);
		menuConfigs(graphics);
		super.render(graphics, mouseX, mouseY, partialTicks);
	}

	private void menuConfigs(GuiGraphics graphics) {
		Player player = this.minecraft.player;

		altoTexto = (this.height-168) / 2 + 25;
		anchoTexto = (this.width-250) / 2 + 15;
		
		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
			drawStringWithBorder2(graphics, this.font, Component.translatable("config.dmz.menutype"), anchoTexto, altoTexto, 0xFFFFFF);
		});
	}
	
	private void botonesConfigs() {
		switchesButtons.forEach(this::removeWidget);
		switchesButtons.clear();

		altoTexto = (this.height-168) / 2 + 25;
		anchoTexto = (this.width-250) / 2 + 15;
		Player player = this.minecraft.player;

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
			boolean isCompactMenu = cap.isCompactMenu();

			switchButton = new SwitchButton(isCompactMenu, anchoTexto + 80, altoTexto, Component.empty(), button -> {
				boolean newValue = !isCompactMenu;
				int newValueInt = newValue ? 1 : 0;
				ModMessages.sendToServer(new CharacterC2S("isCompactMenu", newValueInt));
			});
			this.addRenderableWidget(switchButton);
			switchesButtons.add(switchButton);
		});
	}

	public void botonesMenus(){
		altoTexto = (this.height + 168)/2;
		anchoTexto = this.width/2;

		if (this.minecraft.level.isClientSide) {
			Player player = this.minecraft.player;
			this.menuButton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 85, altoTexto, "stats", Component.empty(), wa -> {
				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
					if (playerstats.isCompactMenu()) {
						this.minecraft.setScreen(new AttributesMenu2());
					} else {
						this.minecraft.setScreen(new AttributesMenu(Component.translatable("menu.title.dragonminez.menuzmzmzm")));
					}});
			}));
			this.menuButton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 55, altoTexto, "skills", Component.empty(), wa -> {
				this.minecraft.setScreen(new SkillMenu());
			}));
			this.menuButton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 25, altoTexto, "transf", Component.empty(), wa -> {
				// Agregar acá el menú de Transf
				// this.minecraft.setScreen(new TransfMenu());
			}));
			this.menuButton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 5, altoTexto, "storyline", Component.empty(), wa -> {
				// Agregar acá el menú de Story
				// this.minecraft.setScreen(new TransfMenu());
			}));
			this.menuButton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 35, altoTexto, "kitech", Component.empty(), wa -> {
				// Agregar acá el menú de Ki Techniques
				// this.minecraft.setScreen(new TransfMenu());
			}));
			this.menuButton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 65, altoTexto, "settings", Component.empty(), wa -> {
				// Es este menú, no hacer nada.
			}));
		}
	}

	private void menuPanel(GuiGraphics graphics) {
		altoTexto = (this.height - 168)/2;
		anchoTexto = (this.width - 250)/2;

		RenderSystem.enableBlend();
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		graphics.blit(menutexture, anchoTexto, altoTexto, 0, 0, 250, 168);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean isPauseScreen() {
		return false;
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
