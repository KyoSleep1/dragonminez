package com.yuseix.dragonminez.init.menus.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.yuseix.dragonminez.client.gui.buttons.DMZButton;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.GlowButton;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.masters.GuruEntity;
import com.yuseix.dragonminez.network.C2S.GuruC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.common.stats.DMZStatsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class GuruMenu extends Screen {

	private static final ResourceLocation textoCuadro = new ResourceLocation(Reference.MOD_ID,
			"textures/gui/texto.png");

	private GlowButton potUnlock, curar;
	private DMZButton AcceptButton, DeclineButton;
	private DMZRightButton rightButton, leftButton;

	private String PageOption = "";
	private int PageButtons;

	public GuruMenu() {
		super(Component.literal("guruwa"));
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	public void tick() {
		super.tick();

		paginaBotones();
		PaginaOpciones();

	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

		int centerX = (this.width / 2);
		int centerY = (this.height);

		LivingEntity guruEntity = new GuruEntity(MainEntity.GURU.get(), this.minecraft.level);


		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, textoCuadro);


		BufferBuilder buffer = Tesselator.getInstance().getBuilder();
		buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

		buffer.vertex(centerX - 140, centerY + 250, 0.0D).uv(0.0F, 1.0F).endVertex();
		buffer.vertex(centerX + 140, centerY + 250, 0.0D).uv(1.0F, 1.0F).endVertex();
		buffer.vertex(centerX + 140, centerY - 90, 0.0D).uv(1.0F, 0.0F).endVertex();
		buffer.vertex(centerX - 140, centerY - 90, 0.0D).uv(0.0F, 0.0F).endVertex();
		Tesselator.getInstance().end();

		RenderSystem.disableBlend();


		//NOMBRE DE LA ENTIDAD
		pGuiGraphics.drawString(font, Component.literal(guruEntity.getName().getString()).withStyle(ChatFormatting.BOLD), centerX - 120, centerY - 88, 0xFFFFFF);

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {
			 int raza = playerstats.getIntValue("race");
			 String razaName = "";
			 switch (raza) {
				 case 0 -> razaName = "lines.elder_guru.menu.human";
				 case 1 -> razaName = "lines.elder_guru.menu.saiyan";
				 case 2 -> razaName = "lines.elder_guru.menu.namek";
				 case 3 -> razaName = "lines.elder_guru.menu.bio";
				 case 4 -> razaName = "lines.elder_guru.menu.colddemon";
				 case 5 -> razaName = "lines.elder_guru.menu.majin";

			 }
			 //TEXTO QUE DIRA LA ENTIDAD
			 if (PageOption.equals("")) {
				 if (playerstats.getIntValue("alignment") <= 60) {
					 List<FormattedCharSequence> lines = font.split(Component.translatable("lines.elder_guru.menu.evil"), 250);
					 for (int i = 0; i < lines.size(); i++) {
						 pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
					 }
				 } else {
					 List<FormattedCharSequence> lines = font.split(Component.translatable(razaName), 250);
					 for (int i = 0; i < lines.size(); i++) {
						 pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
					 }
				 }
			 } else if (PageOption.equals("potunlock")) {
				 if (playerstats.getSkillLevel("potential_unlock") < 10) {
					 List<FormattedCharSequence> lines = font.split(Component.translatable("lines.elder_guru.potunlock.fail"), 250);
					 for (int i = 0; i < lines.size(); i++) {
						 pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
					 }
				 } else if (playerstats.getSkillLevel("potential_unlock") == 10) {
					 List<FormattedCharSequence> lines = font.split(Component.translatable("lines.elder_guru.potunlock.success"), 250);
					 for (int i = 0; i < lines.size(); i++) {
						 pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
					 }
				 } else if (playerstats.getSkillLevel("potential_unlock") > 10) {
					 List<FormattedCharSequence> lines = font.split(Component.translatable("lines.elder_guru.potunlock.max"), 250);
					 for (int i = 0; i < lines.size(); i++) {
						 pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
					 }
				 }
			 } else if (PageOption.equals("curar")) {
				 List<FormattedCharSequence> lines = font.split(Component.translatable("lines.elder_guru.heal"), 250);
				 for (int i = 0; i < lines.size(); i++) {
					 pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				 }
			 }

		});

		//Botones
		super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	private void removerBotones() {
		removeWidget(this.potUnlock);
		removeWidget(this.curar);
		removeWidget(this.AcceptButton);
		removeWidget(this.DeclineButton);
		removeWidget(this.rightButton);
		removeWidget(this.leftButton);
	}

	private void paginaBotones() {
		if (PageButtons == 0) {
			removerBotones();
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {
				if (playerstats.getIntValue("alignment") >= 61) {
					this.curar = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) - 105, (this.height - 23), Component.translatable("lines.elder_guru.option.potunlock"), wa -> {
						PageOption = "potunlock";
					}));

					this.potUnlock = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) + 5, (this.height - 23), Component.translatable("lines.dende.option.heal"), wa -> {
						PageOption = "curar";
					}));
				}
			});
		}
	}

	public void PaginaOpciones() {
		if (this.minecraft.level.isClientSide()) {

				switch (PageOption) {
					case "curar":
						//Aceptar
						this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47), Component.translatable("lines.menu.accept"), wa -> {
							ModMessages.sendToServer(new GuruC2S(2));
							this.minecraft.setScreen(null);

						}));
						//Rechazar
						this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47), Component.translatable("lines.menu.decline"), wa -> {
							this.minecraft.setScreen(null);
						}));
						break;

					case "potunlock":
						DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {
							if (cap.getSkillLevel("potential_unlock") == 10) {
								//Aceptar
								this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47), Component.translatable("lines.menu.accept"), wa -> {
									ModMessages.sendToServer(new GuruC2S(1));
									this.minecraft.setScreen(null);
								}));
							}
						});

						//Rechazar
						this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47), Component.translatable("lines.menu.decline"), wa -> {
							this.minecraft.setScreen(null);
						}));
						break;

					default:
						break;
				}
		}
	}
}