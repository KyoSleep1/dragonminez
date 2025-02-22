package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.CustomButtons;
import com.yuseix.dragonminez.client.gui.buttons.DMZGuiButtons;
import com.yuseix.dragonminez.client.gui.buttons.TextButton;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.config.races.transformations.*;
import com.yuseix.dragonminez.network.C2S.SuperFormsC2S;
import com.yuseix.dragonminez.network.C2S.ZPointsC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.stats.forms.FormsData;
import com.yuseix.dragonminez.stats.skills.DMZSkill;
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
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class TransfMenu extends Screen {
	private static final ResourceLocation menu = new ResourceLocation(DragonMineZ.MOD_ID,
			"textures/gui/menulargo2.png");
	private static final ResourceLocation menuinfo = new ResourceLocation(DragonMineZ.MOD_ID,
			"textures/gui/menulargomitad.png");

	private static boolean infoMenu;
	private static String groupId = "superform";
	private int altoTexto, anchoTexto;

	private List<AbstractWidget> groupButtons = new ArrayList<>();
	private List<DMZGuiButtons> botonesMenus = new ArrayList<>();

	private DMZGuiButtons menuButton;
	private CustomButtons infoButton;
	private TextButton upgradeButton;

	public TransfMenu(boolean infoMenu) {
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
		botonesGroups();
	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTicks) {
		renderBackground(pGuiGraphics);
		menuPanel(pGuiGraphics);
		menuTransf(pGuiGraphics);

		super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTicks);
	}

	public void botonesGroups() {
		Player player = this.minecraft.player;

		groupButtons.forEach(this::removeWidget);
		groupButtons.clear();

		this.removeWidget(upgradeButton);
		this.removeWidget(menuButton);

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
			var tps = cap.getZpoints();

			Map<String, FormsData> forms = cap.getAllDMZForms();
			int raza = cap.getRace();
			boolean buyableTP = DMZGeneralConfig.TRANSFORMATIONS_WITH_TP.get();

			int startX = (this.width - 250) / 2 + 15;
			int startY = (this.height + 168) / 2 + 45;
			int offsetY = 13;

			for (Map.Entry<String, FormsData> entry : forms.entrySet()) {
				String formId = entry.getKey();
				FormsData form = entry.getValue();
				double mult = DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get();
				int formsCost = DMZGeneralConfig.TPCOST_TRANSFORMATIONS.get();

				switch (formId) {
					case "super_form":
						if (this.infoMenu) {
							switch (raza) {
								case 0, 2, 3, 4, 5: // Human, Namekian, Cold Demon, Majin, todas son iguales pq "maxLevel" es 5 en todas.
									if (groupId.equals("superform")) {
										int currentLevel = form.getLevel();
										int maxLevel = 5;

										Map<Integer, Integer> levelCosts = Map.of(
												1, (int) (formsCost * mult),
												2, (int) (formsCost * mult * 2),
												3, (int) (formsCost * mult * 3),
												4, (int) (formsCost * mult * 4),
												5, (int) (formsCost * mult * 5)
										);
										int nextLevel = currentLevel + 1;

										if (buyableTP) {
											if (currentLevel < maxLevel) {
												int cost = levelCosts.getOrDefault(nextLevel, Integer.MAX_VALUE);

												if (tps >= cost) {
													upgradeButton = (TextButton) this.addRenderableWidget(new TextButton(startX + 195, altoTexto -40, Component.translatable("dmz.skills.upgrade", cost), wa -> {
														ModMessages.sendToServer(new SuperFormsC2S("super_form", nextLevel));
														ModMessages.sendToServer(new ZPointsC2S(1, cost));
														this.removeWidget(upgradeButton);
													}));
												}
											}

										} else if (currentLevel >= 1 && currentLevel < maxLevel) {
											int cost = levelCosts.getOrDefault(nextLevel, Integer.MAX_VALUE);

											if (tps >= cost) {
												upgradeButton = (TextButton) this.addRenderableWidget(new TextButton(startX + 195, altoTexto -40, Component.translatable("dmz.skills.upgrade", cost), wa -> {
													ModMessages.sendToServer(new SuperFormsC2S("super_form", nextLevel));
													ModMessages.sendToServer(new ZPointsC2S(1, cost));
													this.removeWidget(upgradeButton);
												}));
											}

										}
									}
									break;
								case 1:
									if (groupId.equals("oozarus") || groupId.equals("ssgrades") || groupId.equals("ssj")) {
										int currentLevel = form.getLevel();
										int maxLevel = 8;

										Map<Integer, Integer> levelCosts = Map.of(
												1, (int) (formsCost * mult),
												2, (int) (formsCost * mult * 2),
												3, (int) (formsCost * mult * 3),
												4, (int) (formsCost * mult * 4),
												5, (int) (formsCost * mult * 5),
												6, (int) (formsCost * mult * 6),
												7, (int) (formsCost * mult * 7),
												8, (int) (formsCost * mult * 8)
										);
										int nextLevel = currentLevel + 1;

										if (buyableTP) {
											if (currentLevel < maxLevel) {
												int cost = levelCosts.getOrDefault(nextLevel, Integer.MAX_VALUE);

												if (tps >= cost) {
													upgradeButton = (TextButton) this.addRenderableWidget(new TextButton(startX + 195, altoTexto -40, Component.translatable("dmz.skills.upgrade", cost), wa -> {
														ModMessages.sendToServer(new SuperFormsC2S("super_form", nextLevel));
														ModMessages.sendToServer(new ZPointsC2S(1, cost));
														this.removeWidget(upgradeButton);
													}));
												}
											}

										} else if (currentLevel >= 1 && currentLevel < maxLevel) {
											int cost = levelCosts.getOrDefault(nextLevel, Integer.MAX_VALUE);

											if (tps >= cost) {
												upgradeButton = (TextButton) this.addRenderableWidget(new TextButton(startX + 195, altoTexto -40, Component.translatable("dmz.skills.upgrade", cost), wa -> {
													ModMessages.sendToServer(new SuperFormsC2S("super_form", nextLevel));
													ModMessages.sendToServer(new ZPointsC2S(1, cost));
													this.removeWidget(upgradeButton);
												}));
											}

										}
									}
									break;
							}
						}
						break;
				}

				startX = (this.width - 250) / 2 + 28;
				startY = (this.height - 168) / 2 + 30;

				switch (raza) {
					case 0, 2, 3, 4, 5:
						CustomButtons buttonSF = new CustomButtons("info", this.infoMenu ? startX + 200 - 72 : startX + 200, startY - 2, Component.empty(), btn -> {
							this.infoMenu = !this.infoMenu;
							this.groupId = "superform";
						});

						this.addRenderableWidget(buttonSF);
						groupButtons.add(buttonSF);
						break;
					case 1:
						if (buyableTP) {

						}
						if (form.getLevel() >= 0) {
						CustomButtons buttonOozaru = new CustomButtons("info", this.infoMenu ? startX + 200 - 72 : startX + 200, startY - 2, Component.empty(), btn -> {
							this.infoMenu = !this.infoMenu;
							this.groupId = "oozarus";
						});

						this.addRenderableWidget(buttonOozaru);
						groupButtons.add(buttonOozaru);
					}
						if (form.getLevel() >= 2) {
							CustomButtons buttonSSG = new CustomButtons("info", this.infoMenu ? startX + 200 - 72 : startX + 200, startY + offsetY - 2, Component.empty(), btn -> {
								this.infoMenu = !this.infoMenu;
								this.groupId = "ssgrades";
							});


							this.addRenderableWidget(buttonSSG);
							groupButtons.add(buttonSSG);
						}
						if (form.getLevel() >= 5) {
							CustomButtons buttonSSJ = new CustomButtons("info", this.infoMenu ? startX + 200 - 72 : startX + 200, startY + offsetY * 2 - 2, Component.empty(), btn -> {
								this.infoMenu = !this.infoMenu;
								this.groupId = "ssj";
							});

							this.addRenderableWidget(buttonSSJ);
							groupButtons.add(buttonSSJ);
						}
						break;
				}

				startY += offsetY;
			}
		});

	}

	public void menuPanel(GuiGraphics guiGraphics) {
		if (infoMenu) {
			altoTexto = (this.height - 168)/2;
			anchoTexto = ((this.width - 250)/2) - 72;
			RenderSystem.enableBlend();
			RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
			guiGraphics.blit(menu, anchoTexto, altoTexto, 0, 0, 250, 168);

			anchoTexto = ((this.width - 250)/2) + 180;
			guiGraphics.blit(menuinfo, anchoTexto, altoTexto, 0, 0, 145, 168);

			int startX = ((this.width - 250) / 2 + 30) - 72;
			int startY = (this.height - 168) / 2 + 18;
			drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.level"),startX, startY, 0xffffff);
			startX = ((this.width - 250) / 2 + 100) - 72;
			drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.transf.form"), startX, startY, 0xffc134);
			startX = ((this.width - 250) / 2 + 180) - 72;
			drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.transf.group"), startX, startY, 0x20e0ff);

			menuGrupos(guiGraphics);
		} else {
			altoTexto = (this.height - 168)/2;
			anchoTexto = (this.width - 250)/2;
			RenderSystem.enableBlend();
			RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
			guiGraphics.blit(menu, anchoTexto, altoTexto, 0, 0, 250, 168);

			int startX = (this.width - 250) / 2 + 30;
			int startY = (this.height - 168) / 2 + 18;
			drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.level"),startX, startY, 0xffffff);
			startX = (this.width - 250) / 2 + 100;
			drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.transf.form"), startX, startY, 0xffc134);
			startX = (this.width - 250) / 2 + 180;
			drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.transf.group"), startX, startY, 0x20e0ff);
		}
		RenderSystem.disableBlend();
	}

	private void menuTransf(GuiGraphics guiGraphics) {
		// Obtener las habilidades desde la capability del jugador
		Player player = this.minecraft.player;

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
			Map<String, FormsData> forms = cap.getAllDMZForms();

			int startX = (this.width - 250) / 2 + 15;
			int startY = (this.height - 168) / 2 + 30;
			int offsetY = 13;

			for (Map.Entry<String, FormsData> entry : forms.entrySet()) {
				int level = cap.getFormSkillLevel("super_form");
				switch (cap.getRace()) {
					case 0:
						switch (entry.getKey()) {
							case "super_form":
								// Nivel
								drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(level)), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xffffff);
								// Skill
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.dmzforms.super_form.name"), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xffc134);
								// Grupo
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.general.superform"), this.infoMenu ? startX + 165 - 72: startX + 165, startY, 0x20e0ff);
								break;
						}
						break;
					case 1:
						switch (entry.getKey()) {
							case "super_form":
								// Nivel
								drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(level)), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xffffff);
								// Skill
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.dmzforms.super_form.name"), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xffc134);
								// Grupo
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.saiyan.oozarus"), this.infoMenu ? startX + 165 - 72: startX + 165, startY, 0x20e0ff);
								if (level >= 2) {
									drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.saiyan.ssgrades"), this.infoMenu ? startX + 165 - 72: startX + 165, startY + offsetY, 0x20e0ff);
								}
								if (level >= 5) {
									drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.saiyan.ssj"), this.infoMenu ? startX + 165 - 72: startX + 165, startY + offsetY * 2, 0x20e0ff);
								}
								break;
						}
						break;
					case 2:
						switch (entry.getKey()) {
							case "super_form":
								// Nivel
								drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(level)), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xffffff);
								// Skill
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.dmzforms.super_form.name"), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xffc134);
								// Grupo
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.general.superform"), this.infoMenu ? startX + 165 - 72: startX + 165, startY, 0x20e0ff);
								break;
						}
						break;
					case 3:
						switch (entry.getKey()) {
							case "super_form":
								// Nivel
								drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(level)), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xffffff);
								// Skill
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.dmzforms.super_form.name"), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xffc134);
								// Grupo
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.bio.evolutionforms"), this.infoMenu ? startX + 165 - 72: startX + 165, startY, 0x20e0ff);
								break;
						}
						break;
					case 4:
						switch (entry.getKey()) {
							case "super_form":
								// Nivel
								drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(level)), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xffffff);
								// Skill
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.dmzforms.super_form.name"), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xffc134);
								// Grupo
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.colddemon.involutionforms"), this.infoMenu ? startX + 165 - 72: startX + 165, startY, 0x20e0ff);
								break;
						}
						break;
					case 5:
						switch (entry.getKey()) {
							case "super_form":
								// Nivel
								drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(level)), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xffffff);
								// Skill
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.dmzforms.super_form.name"), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xffc134);
								// Grupo
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.majin.majinforms"), this.infoMenu ? startX + 165 - 72: startX + 165, startY, 0x20e0ff);
								break;
						}
						break;

				}

			}
		});
	}

	private void menuGrupos(GuiGraphics guiGraphics) {
		Player player = this.minecraft.player;

		if (infoMenu) {
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
				int startY = (this.height - 168) / 2 + 18;
				int startX = (this.width - 250) / 2 + 160;
				int raza = cap.getRace();
				Map<String, FormsData> forms = cap.getAllDMZForms();

				for (Map.Entry<String, FormsData> entry : forms.entrySet()) {
					FormsData form = entry.getValue();
					int currentLevel = form.getLevel();
					int maxLevel = 5;

					if (this.groupId.equals("superform")) {
						switch (raza) {
							case 0:
								double multHumanBuffed = ((DMZTrHumanConfig.MULTIPLIER_BUFFED_FORM_STR.get() + DMZTrHumanConfig.MULTIPLIER_BUFFED_FORM_DEF.get() + DMZTrHumanConfig.MULTIPLIER_BUFFED_FORM_PWR.get()) / 3);
								double multHumanFP = ((DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get() + DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get() + DMZTrHumanConfig.MULTIPLIER_FP_FORM_PWR.get()) / 3);
								double multHumanPU = ((DMZTrHumanConfig.MULTIPLIER_PU_FORM_STR.get() + DMZTrHumanConfig.MULTIPLIER_PU_FORM_DEF.get() + DMZTrHumanConfig.MULTIPLIER_PU_FORM_PWR.get()) / 3);

								//Nombre de la habilidad
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.general.superform"), startX + 93, startY, 0x20e0ff);
								//Tipo y aca pongo lo de skill
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.type"), startX + 37, startY+ 13, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.transf"), startX + 68, startY+ 13, 0xffc134);
								//Aca pongo lo de nivel
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.level"), startX + 37, startY+24, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.literal(String.valueOf(currentLevel)), startX + 78, startY+24, 0xFFFFFF);
								// Lista de transformaciones
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.transflist"), startX + 93, startY+ 35, 0xffc134);
								//Acá van las transformaciones y sus mults
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.human.buffed").append(" | x").append(String.format("%.2f", multHumanBuffed)), startX + 37, startY+46, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.human.fullpower").append(" | x").append(String.format("%.2f", multHumanFP)), startX + 37, startY+57, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.human.potunleashed").append(" | x").append(String.format("%.2f", multHumanPU)), startX + 37, startY+68, 0xFFFFFF);
								if (currentLevel >= maxLevel) {
									drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.maxlevel"), startX + 90, startY+116, 0xffc134);
								}
								break;
							case 2:
								double multNamekGiant = ((DMZTrNamekConfig.MULTIPLIER_GIANT_FORM_STR.get() + DMZTrNamekConfig.MULTIPLIER_GIANT_FORM_DEF.get() + DMZTrNamekConfig.MULTIPLIER_GIANT_FORM_PWR.get()) / 3);
								double multNamekFP = ((DMZTrNamekConfig.MULTIPLIER_FP_FORM_STR.get() + DMZTrNamekConfig.MULTIPLIER_FP_FORM_DEF.get() + DMZTrNamekConfig.MULTIPLIER_FP_FORM_PWR.get()) / 3);
								double multNamekSuperNamek = ((DMZTrNamekConfig.MULTIPLIER_SUPER_NAMEK_FORM_STR.get() + DMZTrNamekConfig.MULTIPLIER_SUPER_NAMEK_FORM_DEF.get() + DMZTrNamekConfig.MULTIPLIER_SUPER_NAMEK_FORM_PWR.get()) / 3);

								//Nombre de la habilidad
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.general.superform"), startX + 93, startY, 0x20e0ff);
								//Tipo y aca pongo lo de skill
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.type"), startX + 37, startY+ 13, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.transf"), startX + 68, startY+ 13, 0xffc134);
								//Aca pongo lo de nivel
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.level"), startX + 37, startY+24, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.literal(String.valueOf(currentLevel)), startX + 78, startY+24, 0xFFFFFF);
								// Lista de transformaciones
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.transflist"), startX + 93, startY+ 35, 0xffc134);
								//Acá van las transformaciones y sus mults
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.namek.giant").append(" | x").append(String.format("%.2f", multNamekGiant)), startX + 37, startY+46, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.namek.fullpower").append(" | x").append(String.format("%.2f", multNamekFP)), startX + 37, startY+57, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.namek.supernamek").append(" | x").append(String.format("%.2f", multNamekSuperNamek)), startX + 37, startY+68, 0xFFFFFF);
								if (currentLevel >= maxLevel) {
									drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.maxlevel"), startX + 90, startY+116, 0xffc134);
								}
								break;
							case 3:
								double multBioSemiPerfect = ((DMZTrBioAndroidConfig.MULTIPLIER_SEMIPERFECT_FORM_STR.get() + DMZTrBioAndroidConfig.MULTIPLIER_SEMIPERFECT_FORM_DEF.get() + DMZTrBioAndroidConfig.MULTIPLIER_SEMIPERFECT_FORM_PWR.get()) / 3);
								double multBioPerfect = ((DMZTrBioAndroidConfig.MULTIPLIER_PERFECT_FORM_STR.get() + DMZTrBioAndroidConfig.MULTIPLIER_PERFECT_FORM_DEF.get() + DMZTrBioAndroidConfig.MULTIPLIER_PERFECT_FORM_PWR.get()) / 3);

								//Nombre de la habilidad
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.bio.evolutionforms"), startX + 93, startY, 0x20e0ff);
								//Tipo y aca pongo lo de skill
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.type"), startX + 37, startY+ 13, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.transf"), startX + 68, startY+ 13, 0xffc134);
								//Aca pongo lo de nivel
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.level"), startX + 37, startY+24, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.literal(String.valueOf(currentLevel)), startX + 78, startY+24, 0xFFFFFF);
								// Lista de transformaciones
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.transflist"), startX + 93, startY+ 35, 0xffc134);
								//Acá van las transformaciones y sus mults
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.bio.semiperfect").append(" | x").append(String.format("%.2f", multBioSemiPerfect)), startX + 37, startY+46, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.bio.perfect").append(" | x").append(String.format("%.2f", multBioPerfect)), startX + 37, startY+57, 0xFFFFFF);
								if (currentLevel >= maxLevel) {
									drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.maxlevel"), startX + 90, startY+116, 0xffc134);
								}
								break;
							case 4:
								double multColdDemonSecondForm = ((DMZTrColdDemonConfig.MULTIPLIER_SECOND_FORM_STR.get() + DMZTrColdDemonConfig.MULTIPLIER_SECOND_FORM_DEF.get() + DMZTrColdDemonConfig.MULTIPLIER_SECOND_FORM_PWR.get()) / 3);
								double multColdDemonThirdForm = ((DMZTrColdDemonConfig.MULTIPLIER_THIRD_FORM_STR.get() + DMZTrColdDemonConfig.MULTIPLIER_THIRD_FORM_DEF.get() + DMZTrColdDemonConfig.MULTIPLIER_THIRD_FORM_PWR.get()) / 3);
								double multColdDemonFourthForm = ((DMZTrColdDemonConfig.MULTIPLIER_FOURTH_FORM_STR.get() + DMZTrColdDemonConfig.MULTIPLIER_FOURTH_FORM_DEF.get() + DMZTrColdDemonConfig.MULTIPLIER_FOURTH_FORM_PWR.get()) / 3);
								double multColdDemonFullPower = ((DMZTrColdDemonConfig.MULTIPLIER_FULL_POWER_FORM_STR.get() + DMZTrColdDemonConfig.MULTIPLIER_FULL_POWER_FORM_DEF.get() + DMZTrColdDemonConfig.MULTIPLIER_FULL_POWER_FORM_PWR.get()) / 3);

								//Nombre de la habilidad
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.colddemon.involutionforms"), startX + 93, startY, 0x20e0ff);
								//Tipo y aca pongo lo de skill
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.type"), startX + 37, startY+ 13, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.transf"), startX + 68, startY+ 13, 0xffc134);
								//Aca pongo lo de nivel
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.level"), startX + 37, startY+24, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.literal(String.valueOf(currentLevel)), startX + 78, startY+24, 0xFFFFFF);
								// Lista de transformaciones
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.transflist"), startX + 93, startY+ 35, 0xffc134);
								//Acá van las transformaciones y sus mults
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.colddemon.second").append(" | x").append(String.format("%.2f", multColdDemonSecondForm)), startX + 37, startY+46, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.colddemon.third").append(" | x").append(String.format("%.2f", multColdDemonThirdForm)), startX + 37, startY+57, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.colddemon.final").append(" | x").append(String.format("%.2f", multColdDemonFourthForm)), startX + 37, startY+68, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.colddemon.fullpower").append(" | x").append(String.format("%.2f", multColdDemonFullPower)), startX + 37, startY+79, 0xFFFFFF);
								if (currentLevel >= maxLevel) {
									drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.maxlevel"), startX + 90, startY+116, 0xffc134);
								}
								break;
							case 5:
								double multMajinEvil = ((DMZTrMajinConfig.MULTIPLIER_EVIL_FORM_STR.get() + DMZTrMajinConfig.MULTIPLIER_EVIL_FORM_DEF.get() + DMZTrMajinConfig.MULTIPLIER_EVIL_FORM_PWR.get()) / 3);
								double multMajinKid = ((DMZTrMajinConfig.MULTIPLIER_KID_FORM_STR.get() + DMZTrMajinConfig.MULTIPLIER_KID_FORM_DEF.get() + DMZTrMajinConfig.MULTIPLIER_KID_FORM_PWR.get()) / 3);
								double multMajinSuper = ((DMZTrMajinConfig.MULTIPLIER_SUPER_FORM_STR.get() + DMZTrMajinConfig.MULTIPLIER_SUPER_FORM_DEF.get() + DMZTrMajinConfig.MULTIPLIER_SUPER_FORM_PWR.get()) / 3);
								double multMajinUltra = ((DMZTrMajinConfig.MULTIPLIER_ULTRA_FORM_STR.get() + DMZTrMajinConfig.MULTIPLIER_ULTRA_FORM_DEF.get() + DMZTrMajinConfig.MULTIPLIER_ULTRA_FORM_PWR.get()) / 3);

								//Nombre de la habilidad
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.majin.majinforms"), startX + 93, startY, 0x20e0ff);
								//Tipo y aca pongo lo de skill
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.type"), startX + 37, startY+ 13, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.transf"), startX + 68, startY+ 13, 0xffc134);
								//Aca pongo lo de nivel
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.level"), startX + 37, startY+24, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.literal(String.valueOf(currentLevel)), startX + 78, startY+24, 0xFFFFFF);
								// Lista de transformaciones
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.transflist"), startX + 93, startY+ 35, 0xffc134);
								//Acá van las transformaciones y sus mults
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.majin.evil").append(" | x").append(String.format("%.2f", multMajinEvil)), startX + 37, startY+46, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.majin.kid").append(" | x").append(String.format("%.2f", multMajinKid)), startX + 37, startY+57, 0xFFFFFF);
								if (cap.getGender().equals("Male") || cap.getGender().equals("male")) {
									drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.majin.male.super").append(" | x").append(String.format("%.2f", multMajinSuper)), startX + 37, startY+68, 0xFFFFFF);
									drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.majin.male.ultra").append(" | x").append(String.format("%.2f", multMajinUltra)), startX + 37, startY+79, 0xFFFFFF);
								} else {
									drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.majin.female.super").append(" | x").append(String.format("%.2f", multMajinSuper)), startX + 37, startY+68, 0xFFFFFF);
									drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.majin.female.ultra").append(" | x").append(String.format("%.2f", multMajinUltra)), startX + 37, startY+79, 0xFFFFFF);
								}
								if (currentLevel >= maxLevel) {
									drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.maxlevel"), startX + 90, startY+116, 0xffc134);
								}
								break;
						}
					} else if (this.groupId.equals("oozarus") || this.groupId.equals("ssgrades") || this.groupId.equals("ssj")) {
						double multSaiyanOozaru = ((DMZTrSaiyanConfig.MULTIPLIER_OOZARU_FORM_STR.get() + DMZTrSaiyanConfig.MULTIPLIER_OOZARU_FORM_DEF.get() + DMZTrSaiyanConfig.MULTIPLIER_OOZARU_FORM_PWR.get()) / 3);
						double multSaiyanGoldenOozaru = ((DMZTrSaiyanConfig.MULTIPLIER_GOLDENOOZARU_FORM_STR.get() + DMZTrSaiyanConfig.MULTIPLIER_GOLDENOOZARU_FORM_DEF.get() + DMZTrSaiyanConfig.MULTIPLIER_GOLDENOOZARU_FORM_PWR.get()) / 3);
						double multSaiyanSSJ = ((DMZTrSaiyanConfig.MULTIPLIER_SSJ_FORM_STR.get() + DMZTrSaiyanConfig.MULTIPLIER_SSJ_FORM_DEF.get() + DMZTrSaiyanConfig.MULTIPLIER_SSJ_FORM_PWR.get()) / 3);
						double multSaiyanSSG2 = ((DMZTrSaiyanConfig.MULTIPLIER_SSGRADE2_FORM_STR.get() + DMZTrSaiyanConfig.MULTIPLIER_SSGRADE2_FORM_DEF.get() + DMZTrSaiyanConfig.MULTIPLIER_SSGRADE2_FORM_PWR.get()) / 3);
						double multSaiyanSSG3 = ((DMZTrSaiyanConfig.MULTIPLIER_SSGRADE3_FORM_STR.get() + DMZTrSaiyanConfig.MULTIPLIER_SSGRADE3_FORM_DEF.get() + DMZTrSaiyanConfig.MULTIPLIER_SSGRADE3_FORM_PWR.get()) / 3);
						double multSaiyanMSSJ = ((DMZTrSaiyanConfig.MULTIPLIER_MSSJ_FORM_STR.get() + DMZTrSaiyanConfig.MULTIPLIER_MSSJ_FORM_DEF.get() + DMZTrSaiyanConfig.MULTIPLIER_MSSJ_FORM_PWR.get()) / 3);
						double multSaiyanSSJ2 = ((DMZTrSaiyanConfig.MULTIPLIER_SSJ2_FORM_STR.get() + DMZTrSaiyanConfig.MULTIPLIER_SSJ2_FORM_DEF.get() + DMZTrSaiyanConfig.MULTIPLIER_SSJ2_FORM_PWR.get()) / 3);
						double multSaiyanSSJ3 = ((DMZTrSaiyanConfig.MULTIPLIER_SSJ3_FORM_STR.get() + DMZTrSaiyanConfig.MULTIPLIER_SSJ3_FORM_DEF.get() + DMZTrSaiyanConfig.MULTIPLIER_SSJ3_FORM_PWR.get()) / 3);
						maxLevel = 8;

						//Tipo y aca pongo lo de skill
						drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.type"), startX + 37, startY+ 13, 0xFFFFFF);
						drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.transf"), startX + 68, startY+ 13, 0xffc134);
						//Aca pongo lo de nivel
						drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.level"), startX + 37, startY+24, 0xFFFFFF);
						drawStringWithBorder2(guiGraphics, this.font, Component.literal(String.valueOf(currentLevel)), startX + 78, startY+24, 0xFFFFFF);
						// Lista de transformaciones
						drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.transflist"), startX + 93, startY+ 35, 0xffc134);

						switch (groupId) {
							case "oozarus":
								//Nombre de la habilidad
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.saiyan.oozarus"), startX + 93, startY, 0x20e0ff);
								//Acá van las transformaciones y sus mults
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.saiyan.oozaru").append(" | x").append(String.format("%.2f", multSaiyanOozaru)), startX + 37, startY+46, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.saiyan.goldenoozaru").append(" | x").append(String.format("%.2f", multSaiyanGoldenOozaru)), startX + 37, startY+57, 0xFFFFFF);
								break;
							case "ssgrades":
								//Nombre de la habilidad
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.saiyan.ssgrades"), startX + 93, startY, 0x20e0ff);
								//Acá van las transformaciones y sus mults
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.saiyan.ssj1").append(" | x").append(String.format("%.2f", multSaiyanSSJ)), startX + 37, startY+46, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.saiyan.ssgrade2").append(" | x").append(String.format("%.2f", multSaiyanSSG2)), startX + 37, startY+57, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.saiyan.ssgrade3").append(" | x").append(String.format("%.2f", multSaiyanSSG3)), startX + 37, startY+68, 0xFFFFFF);
								break;
							case "ssj":
								//Nombre de la habilidad
								drawStringWithBorder(guiGraphics, this.font, Component.translatable("groupforms.dmz.saiyan.ssj"), startX + 93, startY, 0x20e0ff);
								//Acá van las transformaciones y sus mults
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.saiyan.mssj").append(" | x").append(String.format("%.2f", multSaiyanMSSJ)), startX + 37, startY+46, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.saiyan.ssj2").append(" | x").append(String.format("%.2f", multSaiyanSSJ2)), startX + 37, startY+57, 0xFFFFFF);
								drawStringWithBorder2(guiGraphics, this.font, Component.translatable("forms.dmz.saiyan.ssj3").append(" | x").append(String.format("%.2f", multSaiyanSSJ3)), startX + 37, startY+68, 0xFFFFFF);
								break;
						}

						if (currentLevel >= maxLevel) {
							drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.maxlevel"), startX + 90, startY+116, 0xffc134);
						}
					}
				}
			});
		}
	}

	public void botonesMenus() {
		this.removeWidget(menuButton);

		for (DMZGuiButtons boton : botonesMenus) {
			this.removeWidget(boton);
		}
		botonesMenus.clear();

		altoTexto = (this.height + 168) / 2;
		anchoTexto = this.infoMenu ? (this.width/2) - 72 : this.width/2;

		if (this.minecraft.level.isClientSide) {
			Player player = this.minecraft.player;
			botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 85, altoTexto, "stats", Component.empty(), wa -> {
				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
					if (playerstats.isCompactMenu()) {
						this.minecraft.setScreen(new AttributesMenu2());
					} else {
						this.minecraft.setScreen(new AttributesMenu(Component.translatable("menu.title.dragonminez.menuzmzmzm")));
					}
				});
			})));

			botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 55, altoTexto, "skills", Component.empty(), wa -> {
				this.minecraft.setScreen(new SkillMenu(false));
			})));

			botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 25, altoTexto, "transf", Component.empty(), wa -> {
				// Es este menú, no hacer nada
			})));

			botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 5, altoTexto, "storyline", Component.empty(), wa -> {
				// Agregar menú de historia
				// this.minecraft.setScreen(new StoryMenu());
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
