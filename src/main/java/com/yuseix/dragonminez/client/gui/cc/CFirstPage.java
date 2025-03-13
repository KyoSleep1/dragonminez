package com.yuseix.dragonminez.client.gui.cc;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.TextButton;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.MainEntity;
import com.yuseix.dragonminez.common.init.entity.custom.fpcharacters.*;
import com.yuseix.dragonminez.common.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.common.network.ModMessages;
import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.common.stats.DMZStatsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import javax.annotation.Nullable;
import java.util.List;

public class CFirstPage extends Screen {

	private static final ResourceLocation texto = new ResourceLocation(Reference.MOD_ID,
			"textures/gui/menutexto.png");

	//Textura de los panoramas
	private static final ResourceLocation PANORAMA_PATH = new ResourceLocation(Reference.MOD_ID, "textures/gui/background/panorama");
	private static final ResourceLocation PANORAMA_BUU = new ResourceLocation(Reference.MOD_ID, "textures/gui/background/buu_panorama");
	private static final ResourceLocation PANORAMA_BIO = new ResourceLocation(Reference.MOD_ID, "textures/gui/background/bio_panorama");
	private static final ResourceLocation PANORAMA_SAIYAN = new ResourceLocation(Reference.MOD_ID, "textures/gui/background/s_panorama");
	private static final ResourceLocation PANORAMA_NAMEK = new ResourceLocation(Reference.MOD_ID, "textures/gui/background/n_panorama");
	private static final ResourceLocation PANORAMA_COLD = new ResourceLocation(Reference.MOD_ID, "textures/gui/background/c_panorama");

	//Panoramas
	private final PanoramaRenderer customPanorama = new PanoramaRenderer(new CubeMap(PANORAMA_PATH));
	private final PanoramaRenderer panoramaBuu = new PanoramaRenderer(new CubeMap(PANORAMA_BUU));
	private final PanoramaRenderer panoramaBio = new PanoramaRenderer(new CubeMap(PANORAMA_BIO));
	private final PanoramaRenderer panoramaSai = new PanoramaRenderer(new CubeMap(PANORAMA_SAIYAN));
	private final PanoramaRenderer panoramaNam = new PanoramaRenderer(new CubeMap(PANORAMA_NAMEK));
	private final PanoramaRenderer panoramaCold = new PanoramaRenderer(new CubeMap(PANORAMA_COLD));

	private DMZRightButton botonRazaRight, botonRazaLeft;
	private TextButton nextButton;

	private int alturaTexto;
	private int anchoTexto;

	public CFirstPage() {
		super(Component.empty());
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	public void tick() {
		super.tick();

		// Verifica si estamos en el lado cliente
		if (this.minecraft.level.isClientSide) {
			botonesRazasElegir(this.width / 2, (this.height / 2) + 87);

			this.nextButton = this.addRenderableWidget(new TextButton(this.width - 85, this.height - 25, Component.translatable("dmz.ccreation.next").withStyle(ChatFormatting.BOLD), button -> {
				this.minecraft.setScreen(new CCustomizationPage(Component.empty()));

				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {
					if (cap.getIntValue("race") == 0) {
						ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
						ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
						ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
						ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
						ModMessages.sendToServer(new CharacterC2S("hairID", 1));
						ModMessages.sendToServer(new CharacterC2S("auraColor", 8388607));
					} else if (cap.getIntValue("race") == 1) {
						ModMessages.sendToServer(new CharacterC2S("auraColor", 8388607));
					} else if (cap.getIntValue("race") == 2) {
						ModMessages.sendToServer(new CharacterC2S("auraColor", 8388607));
					} else if (cap.getIntValue("race") == 3) {
						ModMessages.sendToServer(new CharacterC2S("auraColor", 1746688));
					} else if (cap.getIntValue("race") == 4) {
						ModMessages.sendToServer(new CharacterC2S("auraColor", 6226175));
					} else {
						ModMessages.sendToServer(new CharacterC2S("auraColor", 16739839));
					}
				});
			}));
		}
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
		panoramas(pGuiGraphics, pPartialTick);
		pagina0(pGuiGraphics, this.width, this.height);
		paginaInfoRazas(pGuiGraphics);
		super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
	}

	public void pagina0(GuiGraphics pGuiGraphics, int posX, int posY) {
		RenderSystem.enableBlend();
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.depthMask(false);
		pGuiGraphics.blit(texto, (pGuiGraphics.guiWidth() / 2) - 60, (pGuiGraphics.guiHeight() / 2) + 85, 0, 16, 130, 18);
		//pGuiGraphics.blit(texto, (this.width / 2) - 60, 10, 0, 16, 130, 18);
		RenderSystem.disableBlend();

		//TITULO
		alturaTexto = (posY / 2) - 40; anchoTexto = posX / 2;

		CCustomizationPage.drawStringWithBorder(pGuiGraphics, font, Component.translatable("dmz.ccreation.name"), 3, 3, 0xFFFFFF);

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {
			var raza = cap.getIntValue("race");
			alturaTexto = (posY / 2) + 90; anchoTexto = (this.width / 2);

			switch (raza) {
				case 0: //Humano
					pGuiGraphics.drawCenteredString(font, Component.translatable("dmz.races.name.human").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0x31EAFF);
					break;
				case 1: // Saiyan
					pGuiGraphics.drawCenteredString(font, Component.translatable("dmz.races.name.saiyan").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xFFBA35);
					break;
				case 2: // Namek
					pGuiGraphics.drawCenteredString(font, Component.translatable("dmz.races.name.namek").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0x378942);
					break;
				case 3: // BioAndroid
					pGuiGraphics.drawCenteredString(font, Component.translatable("dmz.races.name.bioandroid").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0x72DA58);
					break;
				case 4: // ColdDemon
					pGuiGraphics.drawCenteredString(font, Component.translatable("dmz.races.name.colddemon").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xAC1BEC);
					break;
				case 5: // Majin
					pGuiGraphics.drawCenteredString(font, Component.translatable("dmz.races.name.majin").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xFE7FF4);
					break;
			}
			personajesMenu(pGuiGraphics);
		});
	}

	public void personajesMenu(GuiGraphics pGuiGraphics) {
		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

			if (cap.getIntValue("race") == 0) {//HUMANO
				if (cap.getIntValue("bodytype") == 0) {
					if (Minecraft.getInstance().player.getModelName().equals("default")) {
						LivingEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), this.minecraft.level);
						renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width / 2, alturaTexto - 10, 70, 0, 0, avatar);
					} else {
						LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), this.minecraft.level);
						renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width / 2, alturaTexto - 10, 70, 0, 0, avatar);
					}

				} else {
					if (cap.getStringValue("gender").equals("male")) {
						LivingEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), this.minecraft.level);
						renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width / 2, alturaTexto - 10, 70, 0, 0, avatar);
					} else {
						LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), this.minecraft.level);
						renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width / 2, alturaTexto - 10, 70, 0, 0, avatar);
					}
				}

			} else if (cap.getIntValue("race") == 1) { //SAIYAN
				if (cap.getIntValue("bodytype") == 0) {
					if (Minecraft.getInstance().player.getModelName().equals("default")) {
						LivingEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), this.minecraft.level);
						renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width / 2, alturaTexto - 10, 70, 0, 0, avatar);
					} else {
						LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), this.minecraft.level);
						renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width / 2, alturaTexto - 10, 70, 0, 0, avatar);
					}

				} else {
					if (cap.getStringValue("gender").equals("male")) {
						LivingEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), this.minecraft.level);
						renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width / 2, alturaTexto - 10, 70, 0, 0, avatar);
					} else {
						LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), this.minecraft.level);
						renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width / 2, alturaTexto - 10, 70, 0, 0, avatar);
					}
				}

			} else if (cap.getIntValue("race") == 2) { //NAMEK
				LivingEntity avatar = new FPNamekianEntity(MainEntity.FP_NAMEK.get(), this.minecraft.level);

				renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width / 2, alturaTexto - 10, 70, 0, 0, avatar);

			} else if (cap.getIntValue("race") == 3) { //BIOANDROIDE
				LivingEntity avatar = new FPBioAndroidEntity(MainEntity.FP_BIOANDROIDE.get(), this.minecraft.level);

				renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width / 2, alturaTexto - 10, 70, 0, 0, avatar);

			} else if (cap.getIntValue("race") == 4) { //NARCO OSEA ARCO JEJE
				LivingEntity avatar = new FPDemonColdEntity(MainEntity.FP_DEMONCOLD.get(), this.minecraft.level);

				renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width / 2, alturaTexto - 10, 70, 0, 0, avatar);

			} else { // MAJIN
				if (cap.getStringValue("gender").equals("male")) {
					LivingEntity avatar = new FPMajinGordEntity(MainEntity.FP_MAJINGORDO.get(), this.minecraft.level);

					renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width / 2, alturaTexto - 10, 70, 0, 0, avatar);

				} else {
					LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), this.minecraft.level);
					renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width / 2, alturaTexto - 10, 70, 0, 0, avatar);
				}
			}


		});

	}

	public void paginaInfoRazas(GuiGraphics graphics) {
		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

			var raza = cap.getIntValue("race");

			if (raza == 0) {
				CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("dmz.ccreation.human.title").withStyle(ChatFormatting.BOLD), 10, (this.height / 2) - 75, 0x61C3FE);

				List<FormattedCharSequence> lines = font.split(Component.translatable("dmz.ccreation.human.description"), 130);
				for (int i = 0; i < lines.size(); i++) {
					graphics.drawString(font, lines.get(i), 3, ((this.height / 2) - 60) + i * font.lineHeight, 0xFFFFFF);
				}
			} else if (raza == 1) {
				CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("dmz.ccreation.saiyan.title").withStyle(ChatFormatting.BOLD), 10, (this.height / 2) - 75, 0xFEBA3A);

				List<FormattedCharSequence> lines = font.split(Component.translatable("dmz.ccreation.saiyan.description"), 130);
				for (int i = 0; i < lines.size(); i++) {
					graphics.drawString(font, lines.get(i), 3, ((this.height / 2) - 60) + i * font.lineHeight, 0xFFFFFF);
				}
			} else if (raza == 2) {
				CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("dmz.ccreation.namek.title").withStyle(ChatFormatting.BOLD), 10, (this.height / 2) - 75, 0x269021);

				List<FormattedCharSequence> lines = font.split(Component.translatable("dmz.ccreation.namek.description"), 130);
				for (int i = 0; i < lines.size(); i++) {
					graphics.drawString(font, lines.get(i), 3, ((this.height / 2) - 60) + i * font.lineHeight, 0xFFFFFF);
				}
			} else if (raza == 3) {
				CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("dmz.ccreation.bioandroid.title").withStyle(ChatFormatting.BOLD), 10, (this.height / 2) - 75, 0x69FE90);

				List<FormattedCharSequence> lines = font.split(Component.translatable("dmz.ccreation.bioandroid.description"), 130);
				for (int i = 0; i < lines.size(); i++) {
					graphics.drawString(font, lines.get(i), 3, ((this.height / 2) - 60) + i * font.lineHeight, 0xFFFFFF);
				}
			} else if (raza == 4) {
				CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("dmz.ccreation.colddemon.title").withStyle(ChatFormatting.BOLD), 10, (this.height / 2) - 75, 0x9415B9);

				List<FormattedCharSequence> lines = font.split(Component.translatable("dmz.ccreation.colddemon.description"), 130);
				for (int i = 0; i < lines.size(); i++) {
					graphics.drawString(font, lines.get(i), 3, ((this.height / 2) - 60) + i * font.lineHeight, 0xFFFFFF);
				}
			} else {
				CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("dmz.ccreation.majin.title").withStyle(ChatFormatting.BOLD), 10, (this.height / 2) - 75, 0xE691FF);

				List<FormattedCharSequence> lines = font.split(Component.translatable("dmz.ccreation.majin.description"), 130);
				for (int i = 0; i < lines.size(); i++) {
					graphics.drawString(font, lines.get(i), 3, ((this.height / 2) - 60) + i * font.lineHeight, 0xFFFFFF);
				}
			}
		});
	}


	public static void renderEntityInInventoryFollowsAngle(GuiGraphics p_282802_, int p_275688_, int p_275245_, int p_275535_, float angleXComponent, float angleYComponent, LivingEntity p_275689_) {
		Quaternionf quaternionf = (new Quaternionf()).rotateZ(3.1415927F);
		Quaternionf quaternionf1 = (new Quaternionf()).rotateX(angleYComponent * 20.0F * 0.017453292F);
		quaternionf.mul(quaternionf1);
		float f2 = p_275689_.yBodyRot;
		float f3 = p_275689_.getYRot();
		float f4 = p_275689_.getXRot();
		float f5 = p_275689_.yHeadRotO;
		float f6 = p_275689_.yHeadRot;
		p_275689_.yBodyRot = 180.0F + angleXComponent * 20.0F;
		p_275689_.setYRot(180.0F + angleXComponent * 40.0F);
		p_275689_.setXRot(-angleYComponent * 20.0F);
		p_275689_.yHeadRot = p_275689_.getYRot();
		p_275689_.yHeadRotO = p_275689_.getYRot();
		renderEntityInInv(p_282802_, p_275688_, p_275245_, p_275535_, quaternionf, quaternionf1, p_275689_);
		p_275689_.yBodyRot = f2;
		p_275689_.setYRot(f3);
		p_275689_.setXRot(f4);
		p_275689_.yHeadRotO = f5;
		p_275689_.yHeadRot = f6;
	}

	public static void renderEntityInInv(GuiGraphics pGuiGraphics, int pX, int pY, int pScale, Quaternionf pPose, @Nullable Quaternionf pCameraOrientation, LivingEntity pEntity) {
		RenderSystem.enableBlend();
		RenderSystem.depthMask(true);
		pGuiGraphics.pose().pushPose();
		pGuiGraphics.pose().translate(pX, pY, 50.0);
		pGuiGraphics.pose().mulPoseMatrix((new Matrix4f()).scaling((float) pScale, (float) pScale, (float) (-pScale)));
		pGuiGraphics.pose().mulPose(pPose);
		Lighting.setupForEntityInInventory();
		EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
		if (pCameraOrientation != null) {
			pCameraOrientation.conjugate();
			entityrenderdispatcher.overrideCameraOrientation(pCameraOrientation);
		}
		entityrenderdispatcher.setRenderShadow(false);
		entityrenderdispatcher.render(pEntity, 0.0, 0.0, 0.0, 0.0F, 1.0F, pGuiGraphics.pose(), pGuiGraphics.bufferSource(), 15728880);
		pGuiGraphics.flush();
		entityrenderdispatcher.setRenderShadow(true);
		pGuiGraphics.pose().popPose();
		Lighting.setupFor3DItems();
		RenderSystem.disableBlend();
	}

	public void botonesRazasElegir(int posX, int posY) {
		this.removeWidget(botonRazaRight); this.removeWidget(botonRazaLeft);

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

			if (cap.getIntValue("race") == 0) {
				this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
					ModMessages.sendToServer(new CharacterC2S("setRace", 1));
					this.removeWidget(botonRazaRight);
					this.removeWidget(botonRazaLeft);
					ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
					ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
					ModMessages.sendToServer(new CharacterC2S("hairID", 1));
					ModMessages.sendToServer(new CharacterC2S("auraColor", 8388607));
				}));
			} else if (cap.getIntValue("race") == 1) {
				this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
					ModMessages.sendToServer(new CharacterC2S("setRace", 2));
					this.removeWidget(botonRazaRight);
					this.removeWidget(botonRazaLeft);
					ModMessages.sendToServer(new CharacterC2S("BodyColor1", 2075172));
					ModMessages.sendToServer(new CharacterC2S("BodyColor2", 12263460));
					ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16746150));
					ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("hairID", 0));
					ModMessages.sendToServer(new CharacterC2S("auraColor", 8388607));
				}));
				this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
					ModMessages.sendToServer(new CharacterC2S("setRace", 0));
					this.removeWidget(botonRazaRight);
					this.removeWidget(botonRazaLeft);
					ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
					ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
					ModMessages.sendToServer(new CharacterC2S("hairID", 1));
					ModMessages.sendToServer(new CharacterC2S("auraColor", 8388607));
				}));
			} else if (cap.getIntValue("race") == 2) {
				this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
					ModMessages.sendToServer(new CharacterC2S("setRace", 3));
					this.removeWidget(botonRazaRight);
					this.removeWidget(botonRazaLeft);
					ModMessages.sendToServer(new CharacterC2S("BodyColor1", 1603072));
					ModMessages.sendToServer(new CharacterC2S("BodyColor2", 10478369));
					ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16741888));
					ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("hairID", 0));
					ModMessages.sendToServer(new CharacterC2S("auraColor", 1746688));
				}));
				this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
					ModMessages.sendToServer(new CharacterC2S("setRace", 1));
					this.removeWidget(botonRazaRight);
					this.removeWidget(botonRazaLeft);
					ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
					ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
					ModMessages.sendToServer(new CharacterC2S("hairID", 1));
					ModMessages.sendToServer(new CharacterC2S("auraColor", 8388607));
				}));
			} else if (cap.getIntValue("race") == 3) {
				this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
					ModMessages.sendToServer(new CharacterC2S("setRace", 4));
					this.removeWidget(botonRazaRight);
					this.removeWidget(botonRazaLeft);
					ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16777215));
					ModMessages.sendToServer(new CharacterC2S("BodyColor2", 15246079));
					ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16726441));
					ModMessages.sendToServer(new CharacterC2S("eye1Color", 16711709));
					ModMessages.sendToServer(new CharacterC2S("eye2Color", 16711709));
					ModMessages.sendToServer(new CharacterC2S("hairColor", 7471273));
					ModMessages.sendToServer(new CharacterC2S("hairID", 0));
					ModMessages.sendToServer(new CharacterC2S("auraColor", 6226175));
				}));
				this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
					ModMessages.sendToServer(new CharacterC2S("setRace", 2));
					this.removeWidget(botonRazaRight);
					this.removeWidget(botonRazaLeft);
					ModMessages.sendToServer(new CharacterC2S("BodyColor1", 2075172));
					ModMessages.sendToServer(new CharacterC2S("BodyColor2", 12263460));
					ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16746150));
					ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("hairID", 0));
					ModMessages.sendToServer(new CharacterC2S("auraColor", 8388607));
				}));
			} else if (cap.getIntValue("race") == 4) {
				this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
					ModMessages.sendToServer(new CharacterC2S("setRace", 5));
					this.removeWidget(botonRazaRight);
					this.removeWidget(botonRazaLeft);
					ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16753919));
					ModMessages.sendToServer(new CharacterC2S("eye1Color", 11796480));
					ModMessages.sendToServer(new CharacterC2S("eye2Color", 11796480));
					ModMessages.sendToServer(new CharacterC2S("hairColor", 16753919));
					ModMessages.sendToServer(new CharacterC2S("hairID", 1));
					ModMessages.sendToServer(new CharacterC2S("auraColor", 16739839));
				}));
				this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
					ModMessages.sendToServer(new CharacterC2S("setRace", 3));
					this.removeWidget(botonRazaRight);
					this.removeWidget(botonRazaLeft);
					ModMessages.sendToServer(new CharacterC2S("BodyColor1", 1603072));
					ModMessages.sendToServer(new CharacterC2S("BodyColor2", 10478369));
					ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16741888));
					ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
					ModMessages.sendToServer(new CharacterC2S("hairID", 0));
					ModMessages.sendToServer(new CharacterC2S("auraColor", 1746688));
				}));
			} else {
				this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
					ModMessages.sendToServer(new CharacterC2S("setRace", 4));
					this.removeWidget(botonRazaRight);
					this.removeWidget(botonRazaLeft);
					ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16777215));
					ModMessages.sendToServer(new CharacterC2S("BodyColor2", 15246079));
					ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16726441));
					ModMessages.sendToServer(new CharacterC2S("eye1Color", 16711709));
					ModMessages.sendToServer(new CharacterC2S("eye2Color", 16711709));
					ModMessages.sendToServer(new CharacterC2S("hairColor", 7471273));
					ModMessages.sendToServer(new CharacterC2S("hairID", 0));
					ModMessages.sendToServer(new CharacterC2S("auraColor", 1746688));
				}));
			}
		});
	}

	public void panoramas(GuiGraphics graphics, float partialtick) {
		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {
			var race = cap.getIntValue("race");

			if (race == 0) {
				this.customPanorama.render(partialtick, 1.0f);
			} else if (race == 1) {
				this.panoramaSai.render(partialtick, 1.0f);
			} else if (race == 2) {
				this.panoramaNam.render(partialtick, 1.0f);
			} else if (race == 3) {
				this.panoramaBio.render(partialtick, 1.0f);
			} else if (race == 4) {
				this.panoramaCold.render(partialtick, 1.0f);
			} else {
				this.panoramaBuu.render(partialtick, 1.0f);
			}
		});

	}
}
