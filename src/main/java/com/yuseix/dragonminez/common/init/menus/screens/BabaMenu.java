package com.yuseix.dragonminez.common.init.menus.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.yuseix.dragonminez.client.gui.buttons.DMZButton;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.GlowButton;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.MainEntity;
import com.yuseix.dragonminez.common.init.entity.custom.masters.UranaiEntity;
import com.yuseix.dragonminez.common.network.C2S.OtroMundoC2S;
import com.yuseix.dragonminez.common.network.ModMessages;
import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.common.stats.DMZStatsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class BabaMenu extends Screen {

	private static final ResourceLocation textoCuadro = new ResourceLocation(Reference.MOD_ID,
			"textures/gui/texto.png");

	private GlowButton revive, hablar;
	private DMZButton AcceptButton, DeclineButton;
	private DMZRightButton rightButton, leftButton;

	private String PageOption = "";

	public BabaMenu() {
		super(Component.literal("babawa"));
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	public void tick() {
		super.tick();

		paginaBotones();
		paginaOpciones();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

		int centerX = (this.width / 2);
		int centerY = (this.height);

		LivingEntity babaEntity = new UranaiEntity(MainEntity.URANAI.get(), this.minecraft.level);


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
		guiGraphics.drawString(font, Component.literal(babaEntity.getName().getString()).withStyle(ChatFormatting.BOLD), centerX - 120, centerY - 88, 0xFFFFFF);

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, this.minecraft.player).ifPresent(cap -> {
			int remainingTicks = cap.getIntValue("babacooldown");
			int remainingMinutes = (remainingTicks / 1200); // 1200 ticks = 1 minuto
			int remainingSeconds = (remainingTicks / 20) % 60; // Convertimos a segundos y obtenemos los restantes

			// Formatear el texto para mostrar el tiempo restante
			String formattedTime = String.format("%dmin %ds", remainingMinutes, remainingSeconds);

			//TEXTO QUE DIRA LA ENTIDAD
			if (PageOption.equals("")) {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.baba.menu"), 250);
				for (int i = 0; i < lines.size(); i++) {
					guiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			} else if (PageOption.equals("talk")) {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.baba.talk"), 250);
				for (int i = 0; i < lines.size(); i++) {
					guiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			} else if (PageOption.equals("revive")) {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.baba.revive"), 250);
				for (int i = 0; i < lines.size(); i++) {
					guiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			} else if (PageOption.equals("norevive")) {
				// Ahora pasamos el tiempo formateado en lugar de los arrays
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.baba.norevive", formattedTime), 250);
				for (int i = 0; i < lines.size(); i++) {
					guiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			}
		});




		//Botones
		super.render(guiGraphics, mouseX, mouseY, partialTick);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	private void removeButtons() {
		this.removeWidget(revive);
		this.removeWidget(hablar);
		this.removeWidget(AcceptButton);
		this.removeWidget(DeclineButton);
		this.removeWidget(rightButton);
		this.removeWidget(leftButton);
	}

	private void paginaBotones() {
		removeButtons();

		this.hablar = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) - 105, (this.height - 23),
				Component.translatable("lines.baba.option.talk"), (button) -> {
					PageOption = "talk";
				}));
		this.revive = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) + 5, (this.height - 23),
				Component.translatable("lines.baba.option.revive"), (button) -> {
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, this.minecraft.player).ifPresent(playerstats -> {
				if (playerstats.getIntValue("babacooldown") <= 0 && !playerstats.getBoolean("alive")) {
					PageOption = "revive";
				} else PageOption = "norevive";
			});
				}));
	}

	public void paginaOpciones() {
		if (this.minecraft.level.isClientSide()) {
			switch (PageOption) {
				case "talk", "norevive":
					this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47), Component.translatable("lines.menu.back"), wa -> {
						PageOption = "";
					}));
					break;

				case "revive":
					this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47), Component.translatable("lines.menu.accept"), wa -> {
						ModMessages.INSTANCE.sendToServer(new OtroMundoC2S("baba"));
						this.minecraft.setScreen(null);
					}));
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
