package com.yuseix.dragonminez.init.menus.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.DMZButton;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.GlowButton;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.masters.DendeEntity;
import com.yuseix.dragonminez.network.C2S.DendeC2S;
import com.yuseix.dragonminez.network.ModMessages;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class DendeMenu extends Screen {

	private static final ResourceLocation textoCuadro = new ResourceLocation(Reference.MOD_ID,
			"textures/gui/texto.png");

	private GlowButton reset, heal;
	private DMZButton AcceptButton, DeclineButton;
	private DMZRightButton rightButton, leftButton;

	private String PageOption = "";
	private int PageButtons;

	public DendeMenu() {
		super(Component.literal("dendewa"));
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

		LivingEntity dendeEntity = new DendeEntity(MainEntity.MASTER_DENDE.get(), this.minecraft.level);


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
		pGuiGraphics.drawString(font, Component.literal(dendeEntity.getName().getString()).withStyle(ChatFormatting.BOLD), centerX - 120, centerY - 88, 0xFFFFFF);

		//TEXTO QUE DIRA LA ENTIDAD
		if (PageOption.equals("")) {
			List<FormattedCharSequence> lines = font.split(Component.translatable("lines.dende.menu"), 250);
			for (int i = 0; i < lines.size(); i++) {
				pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
			}
		} else if (PageOption.equals("reset")) {
			List<FormattedCharSequence> lines = font.split(Component.translatable("lines.dende.reset"), 250);
			for (int i = 0; i < lines.size(); i++) {
				pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
			}
		} else if (PageOption.equals("heal")) {
			List<FormattedCharSequence> lines = font.split(Component.translatable("lines.dende.heal"), 250);
			for (int i = 0; i < lines.size(); i++) {
				pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
			}
		}

		//Botones
		super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);


	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	private void removerBotones() {
		removeWidget(this.reset);
		removeWidget(this.heal);
		removeWidget(this.AcceptButton);
		removeWidget(this.DeclineButton);
		removeWidget(this.rightButton);
		removeWidget(this.leftButton);
	}

	private void paginaBotones() {
		if (PageButtons == 0) {
			removerBotones();
			this.reset = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) - 105, (this.height - 23),
					Component.translatable("lines.dende.option.reset"), wa -> {
				PageOption = "reset";
			}));

			this.heal = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) + 5, (this.height - 23),
					Component.translatable("lines.dende.option.heal"), wa -> {
				PageOption = "heal";
			}));

		} /*else if(PageButtons == 1){
            removerBotones();
            Más opciones

        }*/
	}

	public void PaginaOpciones() {
		if (this.minecraft.level.isClientSide()) {

			switch (PageOption) {
				case "reset":
					this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47), Component.translatable("lines.menu.accept"), wa -> {
						ModMessages.sendToServer(new DendeC2S(1));
						this.minecraft.setScreen(null);

					}));
					this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47), Component.translatable("lines.menu.decline"), wa -> {
						this.minecraft.setScreen(null);
					}));
					break;

				case "heal":
					this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47), Component.translatable("lines.menu.accept"), wa -> {
						ModMessages.sendToServer(new DendeC2S(2));
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
