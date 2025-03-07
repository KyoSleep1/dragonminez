package com.yuseix.dragonminez.client.gui.cc;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.*;
import com.yuseix.dragonminez.client.gui.buttons.CustomButtons;
import com.yuseix.dragonminez.client.gui.buttons.DMZGuiButtons;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.TextButton;
import com.yuseix.dragonminez.network.C2S.SummonQuestC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.stats.storymode.*;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.Objective;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class StorylineMenu extends Screen {
	private static final ResourceLocation menu = new ResourceLocation(DragonMineZ.MOD_ID,
			"textures/gui/menulargo2.png");
	private static final ResourceLocation menuinfo = new ResourceLocation(DragonMineZ.MOD_ID,
			"textures/gui/menulargomitad.png");

	private static boolean infoMenu;
	private int altoTexto, anchoTexto;
	private String sagaPage = "", questId = "", sagaId = "";
	private DMZRightButton rightButton, leftButton;

	private List<AbstractWidget> infoButtons = new ArrayList<>();
	private List<DMZGuiButtons> botonesMenus = new ArrayList<>();

	DMZDatos dmzDatos = new DMZDatos();

	private DMZGuiButtons menuButton;
	private DMZGuiButtons infoButton;
	private TextButton startButton;

	public StorylineMenu(boolean infoMenu) {
		super(Component.empty());
		this.infoMenu = infoMenu;
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	public void tick() {
		super.tick();
		botonesMenus();
		botonesQuests();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		renderBackground(guiGraphics);
		menuPanel(guiGraphics);
		menuQuests(guiGraphics);

		super.render(guiGraphics, mouseX, mouseY, partialTicks);
	}

	public void botonesQuests() {
		infoButtons.forEach(this::removeWidget);
		infoButtons.clear();

		this.removeWidget(rightButton);
		this.removeWidget(leftButton);
		this.removeWidget(menuButton);
		this.removeWidget(startButton);

		this.minecraft.player.getCapability(DMZStoryCapability.INSTANCE).ifPresent(capability -> {
			int startX = (this.width - 250) / 2 + 28;
			int startY = (this.height - 168) / 2 + 30;
			int buttonY = (this.height - 168) / 2 + 18;
			int offsetY = 13;

			switch (sagaPage) {
				case "":
					DMZSaga saiyanSaga = DMZStoryRegistry.getSaga("saiyan");
					if (saiyanSaga != null) {
						// Obtener todas las misiones de la saga "saiyan"
						Collection<DMZQuest> saiyanQuests = DMZStoryRegistry.getQuestsBySaga(saiyanSaga.getId());

						// Recorrer todas las misiones de la saga
						for (DMZQuest quest : saiyanQuests) {
							// Verificar si la misión está completada o es la misión actual
							if (capability.getCompletedQuests().contains(quest.getId()) || quest.getId().equals(capability.getCurrentQuestId())) {
								CustomButtons infoButton = new CustomButtons("info", this.infoMenu ? startX + 190 - 72 : startX + 190, startY - 2, Component.empty(), btn -> {
									this.infoMenu = !this.infoMenu;
									this.questId = quest.getId();
									this.sagaId = saiyanSaga.getId();
								});
								this.addRenderableWidget(infoButton);
								infoButtons.add(infoButton);
								startY += offsetY;
							}

							// Mostrar el botón de inicio si la misión está disponible
							if (infoMenu && quest.getId().equals(capability.getCurrentQuestId())) {
								if (quest.getRequirement().isFulfilled(this.minecraft.player, capability.getEntityKillCounts(), capability.isStructureFound(), capability.hasRequiredItem())) {
									startButton = (TextButton) this.addRenderableWidget(new TextButton(startX + 185, altoTexto - 40, Component.translatable("dmz.quests.start"), wa -> {
										ModMessages.sendToServer(new SummonQuestC2S(quest.getId()));
										this.removeWidget(startButton);
										this.minecraft.setScreen(null);
									}));
								}
							}
						}

						// Verificar si la misión final de la saga está completada
						if (capability.getCompletedQuests().contains("saiyQuest9")) {
							this.rightButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", this.infoMenu ? startX + 202 - 72 : startX + 202, buttonY + 122,
									Component.empty(), wa -> {
								sagaPage = "freezer";
							}));
						}
					}
					break;

				case "freezer":
					DMZSaga freezerSaga = DMZStoryRegistry.getSaga("freezer");
					if (freezerSaga != null) {
						// Obtener todas las misiones de la saga "freezer"
						Collection<DMZQuest> freezerQuests = DMZStoryRegistry.getQuestsBySaga(freezerSaga.getId());

						// Recorrer todas las misiones de la saga
						for (DMZQuest quest : freezerQuests) {
							// Verificar si la misión está completada o es la misión actual
							if (capability.getCompletedQuests().contains(quest.getId()) || quest.getId().equals(capability.getCurrentQuestId())) {
								CustomButtons infoButton = new CustomButtons("info", this.infoMenu ? startX + 190 - 72 : startX + 190, startY - 2, Component.empty(), btn -> {
									this.infoMenu = !this.infoMenu;
									this.questId = quest.getId();
									this.sagaId = freezerSaga.getId();
								});
								this.addRenderableWidget(infoButton);
								infoButtons.add(infoButton);
								startY += offsetY;
							}
						}

						this.leftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", this.infoMenu ? startX - 16 - 72 : startX - 16, buttonY + 122,
								Component.empty(), wa -> {
							sagaPage = "";
						}));
					}
					break;
			}
		});
	}

	private void menuQuests(GuiGraphics guiGraphics) {
		this.minecraft.player.getCapability(DMZStoryCapability.INSTANCE).ifPresent(capability -> {
			int startX = (this.width - 250) / 2 + 30;
			int startY = (this.height - 168) / 2 + 17;
			int offsetY = 13;

			switch (sagaPage) {
				case "":
					// Obtener la saga "saiyan" desde el registro
					DMZSaga saiyanSaga = DMZStoryRegistry.getSaga("saiyan");
					if (saiyanSaga != null) {
						int allQuests = DMZStoryRegistry.getTotalQuests(saiyanSaga.getId());
						int completedQuests = (int) capability.getCompletedQuests().stream()
								.filter(questId -> DMZStoryRegistry.getQuest(questId).getSagaId().equals(saiyanSaga.getId()))
								.count();

						drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.storyline.saiyan.saga"), this.infoMenu ? startX - 72 + 86 : startX + 86, startY + 1, 0xffffff);
						drawStringWithBorder2(guiGraphics, this.font, Component.translatable("%s / %s", completedQuests, allQuests), this.infoMenu ? startX - 72 + 84 : startX + 84, startY + 127, 0xffffff);

						// Obtener todas las misiones de la saga "saiyan"
						Collection<DMZQuest> saiyanQuests = DMZStoryRegistry.getQuestsBySaga(saiyanSaga.getId());

						// Recorrer todas las misiones de la saga
						for (DMZQuest quest : saiyanQuests) {
							// Verificar si la misión está completada o es la misión actual
							if (capability.getCompletedQuests().contains(quest.getId()) || quest.getId().equals(capability.getCurrentQuestId())) {
								// Mostrar la misión completada o actual sin obfuscar
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.storyline." + quest.getId() + ".title"), this.infoMenu ? startX - 72 - 13 : startX - 13, startY + offsetY, 0xffc134);

								// Verificar si la misión está completada
								String status = capability.isQuestComplete(quest, this.minecraft.player) ? "✔" : "❌";
								int statusColor = capability.isQuestComplete(quest, this.minecraft.player) ? 0x00ff00 : 0xff0000;
								drawStringWithBorder(guiGraphics, this.font, Component.literal(status).withStyle(ChatFormatting.BOLD), this.infoMenu ? startX - 72 + 160 : startX + 160, startY + offsetY, statusColor);

								startY += offsetY;
							}
						}

						// Obtener la misión actual
						DMZQuest currentQuest = DMZStoryRegistry.getQuest(capability.getCurrentQuestId());

						// Mostrar la misión siguiente (si existe) con texto obfuscado
						if (currentQuest != null) {
							DMZQuest nextQuest = DMZStoryRegistry.getQuest(currentQuest.getNextQuestId());
							if (nextQuest != null && !capability.getCompletedQuests().contains(nextQuest.getId())) {
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable(nextQuest.getId()).withStyle(ChatFormatting.OBFUSCATED), this.infoMenu ? startX - 72 - 13 : startX - 13, startY + offsetY, 0xffc134);
								startY += offsetY;
							}
						}
					}
					break;
			}
		});
	}

	private void menuInfo(GuiGraphics guiGraphics) {
		if (infoMenu) {
			this.minecraft.player.getCapability(DMZStoryCapability.INSTANCE).ifPresent(capability -> {
				int startY = (this.height - 168) / 2 + 18;
				int startX = (this.width - 250) / 2 + 160;
				int objectivesY = startY + 54;

				DMZQuest quest = DMZStoryRegistry.getQuest(questId);
				if (quest == null) return;

				String nombreQuest = quest.getId();

				List<Component> objectives = quest.getRequirement().getAllObjectives();
				for (Component objective : objectives) {
					drawStringWithBorder2(guiGraphics, this.font, objective, startX + 12, objectivesY, 0xFFFFFF);
					objectivesY += 8;
				}

				drawStringWithBorder(guiGraphics, this.font, Component.translatable(nombreQuest), startX + 93, startY, 0x20e0ff);
			});
		}
	}

	public void menuPanel(GuiGraphics guiGraphics) {
		if (infoMenu) {
			altoTexto = (this.height - 168) / 2;
			anchoTexto = ((this.width - 250) / 2) - 72;
			RenderSystem.enableBlend();
			RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
			guiGraphics.blit(menu, anchoTexto, altoTexto, 0, 0, 250, 168);

			anchoTexto = ((this.width - 250) / 2) + 180;
			guiGraphics.blit(menuinfo, anchoTexto, altoTexto, 0, 0, 145, 168);

			int startX = ((this.width - 250) / 2 + 30) - 72;
			int startY = (this.height - 168) / 2 + 18;
			drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.quests.quest"), startX + 2, startY, 0xffc134);
			startX = ((this.width - 250) / 2 + 100) - 72;
			drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.quests.saga"), startX - 10, startY, 0xffffff);
			startX = ((this.width - 250) / 2 + 180) - 62;
			drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.quests.status"), startX, startY, 0x20e0ff);

			menuInfo(guiGraphics);
		} else {
			altoTexto = (this.height - 168) / 2;
			anchoTexto = (this.width - 250) / 2;
			RenderSystem.enableBlend();
			RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
			guiGraphics.blit(menu, anchoTexto, altoTexto, 0, 0, 250, 168);

			int startX = (this.width - 250) / 2 + 30;
			int startY = (this.height - 168) / 2 + 18;
			drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.quests.quest"), startX + 2, startY, 0xffc134);
			startX = (this.width - 250) / 2 + 100;
			drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.quests.saga"), startX - 10, startY, 0xffffff);
			startX = (this.width - 250) / 2 + 190;
			drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.quests.status"), startX, startY, 0x20e0ff);
		}
		RenderSystem.disableBlend();
	}

	public void botonesMenus() {
		this.removeWidget(menuButton);

		for (DMZGuiButtons boton : botonesMenus) {
			this.removeWidget(boton);
		}
		botonesMenus.clear();

		altoTexto = (this.height + 168) / 2;
		anchoTexto = this.infoMenu ? (this.width / 2) - 72 : this.width / 2;

		if (this.minecraft.level.isClientSide) {
			Player player = this.minecraft.player;
			botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 85, altoTexto, "stats", Component.empty(), wa -> {
				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
					if (playerstats.getBoolean("compactmenu")) {
						this.minecraft.setScreen(new AttributesMenu2());
					} else {
						this.minecraft.setScreen(new AttributesMenu());
					}
				});
			})));

			botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 55, altoTexto, "skills", Component.empty(), wa -> {
				this.minecraft.setScreen(new SkillMenu(false));
			})));

			botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 25, altoTexto, "transf", Component.empty(), wa -> {
				this.minecraft.setScreen(new TransfMenu(false));
			})));

			botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 5, altoTexto, "storyline", Component.empty(), wa -> {
				// Es este menú, no hacer nada
			})));

			botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 35, altoTexto, "kitech", Component.empty(), wa -> {
				// Agregar menú de Ki Techniques
				// this.minecraft.setScreen(new KiTechMenu());
			})));

			botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 65, altoTexto, "settings", Component.empty(), wa -> {
				this.minecraft.setScreen(new ConfigMenu());
			})));
		}
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