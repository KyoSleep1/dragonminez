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
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.ShenlongEntity;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.C2S.ShenlongC2S;
import com.yuseix.dragonminez.network.ModMessages;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class ShenlongMenu extends Screen {

	private static final ResourceLocation textoCuadro = new ResourceLocation(DragonMineZ.MOD_ID,
			"textures/gui/texto.png");

	private GlowButton capSTR, capDEF, capCON, capENE, capKIPW, senzu, radar, revive, reviveother;
	private DMZButton AcceptButton, DeclineButton;
	private DMZRightButton rightButton, leftButton;

	private String PageOption = "";
	private int PageButtons;

	public ShenlongMenu() {
		super(Component.literal("shenlongwa"));
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

		LivingEntity shenlongEntity = new ShenlongEntity(MainEntity.SHENLONG.get(), this.minecraft.level);


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
		pGuiGraphics.drawString(font, Component.literal(shenlongEntity.getName().getString()).withStyle(ChatFormatting.BOLD), centerX - 120, centerY - 88, 0xFFFFFF);

		// TEXTO QUE DIRÁ LA ENTIDAD
		switch (PageOption) {
			case "" -> {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.shenron.menu"), 250);
				for (int i = 0; i < lines.size(); i++) {
					pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			}
			case "capstr" -> {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.shenron.capstr"), 250);
				for (int i = 0; i < lines.size(); i++) {
					pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			}
			case "capdef" -> {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.shenron.capdef"), 250);
				for (int i = 0; i < lines.size(); i++) {
					pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			}
			case "capcon" -> {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.shenron.capcon"), 250);
				for (int i = 0; i < lines.size(); i++) {
					pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			}
			case "capene" -> {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.shenron.capene"), 250);
				for (int i = 0; i < lines.size(); i++) {
					pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			}
			case "capkipw" -> {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.shenron.capkipw"), 250);
				for (int i = 0; i < lines.size(); i++) {
					pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			}
			case "senzu" -> {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.shenron.senzu"), 250);
				for (int i = 0; i < lines.size(); i++) {
					pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			}
			case "radar" -> {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.shenron.radar"), 250);
				for (int i = 0; i < lines.size(); i++) {
					pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			}
			case "revive" -> {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.shenron.revive"), 250);
				for (int i = 0; i < lines.size(); i++) {
					pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			}
			case "revive_other" -> {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.shenron.revive_other"), 250);
				for (int i = 0; i < lines.size(); i++) {
					pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
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
		removeWidget(this.capSTR);
		removeWidget(this.capDEF);
		removeWidget(this.capCON);
		removeWidget(this.capENE);
		removeWidget(this.capKIPW);
		removeWidget(this.senzu);
		removeWidget(this.radar);
		removeWidget(this.revive);
		removeWidget(this.reviveother);
		removeWidget(this.AcceptButton);
		removeWidget(this.DeclineButton);
		removeWidget(this.rightButton);
		removeWidget(this.leftButton);
	}

	private void paginaBotones() {
		switch (PageButtons) {
			case 0 -> {
				removerBotones();
				// Capsula STR
				this.capSTR = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) - 105, (this.height - 23),
						Component.translatable("lines.shenron.wish.capstr"), wa -> {
					PageOption = "capstr";
				}));
				// Capsula DEX
				this.capDEF = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) + 5, (this.height - 23),
						Component.translatable("lines.shenron.wish.capdef"), wa -> {
					PageOption = "capdef";
				}));

				this.rightButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", (this.width / 2) + 120, (this.height - 22),
						Component.empty(), wa -> {
					PageButtons = 1;
				}));
			}
			case 1 -> {
				removerBotones();
				this.leftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", (this.width / 2) - 120, (this.height - 22),
						Component.empty(), wa -> {
					PageButtons = 0;
				}));

				// Capsula CON
				this.capCON = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) - 105, (this.height - 23),
						Component.translatable("lines.shenron.wish.capcon"), wa -> {
					PageOption = "capcon";
				}));
				// Capsula ENE
				this.capENE = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) + 5, (this.height - 23),
						Component.translatable("lines.shenron.wish.capene"), wa -> {
					PageOption = "capene";
				}));

				this.rightButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", (this.width / 2) + 120, (this.height - 22),
						Component.empty(), wa -> {
					PageButtons = 2;
				}));
			}
			case 2 -> {
				removerBotones();
				this.leftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", (this.width / 2) - 120, (this.height - 22),
						Component.empty(), wa -> {
					PageButtons = 1;
				}));

				// Capsula KIPW
				this.capKIPW = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) - 105, (this.height - 23),
						Component.translatable("lines.shenron.wish.capkipw"), wa -> {
					PageOption = "capkipw";
				}));

				// Senzus
				this.senzu = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) + 5, (this.height - 23),
						Component.translatable("lines.shenron.wish.senzu"), wa -> {
					PageOption = "senzu";
				}));
				this.rightButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", (this.width / 2) + 120, (this.height - 22),
						Component.empty(), wa -> {
					PageButtons = 3;
				}));
			}
			case 3 -> {
				removerBotones();
				this.leftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", (this.width / 2) - 120, (this.height - 22),
						Component.empty(), wa -> {
					PageButtons = 2;
				}));
				// Radar
				this.radar = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) - 105, (this.height - 23),
						Component.translatable("lines.shenron.wish.radar"), wa -> {
					PageOption = "radar";
				}));
				// Revivir
				this.revive = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) + 5, (this.height - 23),
						Component.translatable("lines.shenron.wish.revive"), wa -> {
					PageOption = "revive";
				}));
				this.rightButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", (this.width / 2) + 120, (this.height - 22),
						Component.empty(), wa -> {
					PageButtons = 4;
				}));
			}
			case 4 -> {
				removerBotones();
				this.leftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", (this.width / 2) - 120, (this.height - 22),
						Component.empty(), wa -> {
					PageButtons = 3;
				}));
				// Revivir a otros
				this.reviveother = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) - 50, (this.height - 23),
						Component.translatable("lines.shenron.wish.revive_other"), wa -> {
					PageOption = "revive_other";
				}));
			}
		}
	}

	public void PaginaOpciones() {
		if (this.minecraft.level.isClientSide()) {
			switch (PageOption) {
				case "capstr":
					//Aceptar
					this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47),
							Component.translatable("lines.menu.accept"), wa -> {

						ModMessages.sendToServer(new ShenlongC2S(1)); //Recibir capsula STR

						this.minecraft.setScreen(null);

					}));
					//Rechazar
					this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47),
							Component.translatable("lines.menu.decline"), wa -> {
						this.minecraft.setScreen(null);
					}));
					break;

				case "capdef":
					//Aceptar
					this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47),
							Component.translatable("lines.menu.accept"), wa -> {

						ModMessages.sendToServer(new ShenlongC2S(2)); //Recibir capsula DEF

						this.minecraft.setScreen(null);

					}));
					//Rechazar
					this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47),
							Component.translatable("lines.menu.decline"), wa -> {
						this.minecraft.setScreen(null);
					}));
					break;

				case "capcon":
					//Aceptar
					this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47),
							Component.translatable("lines.menu.accept"), wa -> {

						ModMessages.sendToServer(new ShenlongC2S(3)); //Recibir capsula CON

						this.minecraft.setScreen(null);

					}));
					//Rechazar
					this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47),
							Component.translatable("lines.menu.decline"), wa -> {
						this.minecraft.setScreen(null);
					}));
					break;

				case "capene":
					//Aceptar
					this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47),
							Component.translatable("lines.menu.accept"), wa -> {

						ModMessages.sendToServer(new ShenlongC2S(4)); //Recibir capsula ENE

						this.minecraft.setScreen(null);

					}));
					//Rechazar
					this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47),
							Component.translatable("lines.menu.decline"), wa -> {
						this.minecraft.setScreen(null);
					}));
					break;

				case "capkipw":
					//Aceptar
					this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47),
							Component.translatable("lines.menu.accept"), wa -> {

						ModMessages.sendToServer(new ShenlongC2S(5)); //Recibir capsula KI PW

						this.minecraft.setScreen(null);

					}));
					//Rechazar
					this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47),
							Component.translatable("lines.menu.decline"), wa -> {
						this.minecraft.setScreen(null);
					}));
					break;

				case "senzu":
					//Aceptar
					this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47),
							Component.translatable("lines.menu.accept"), wa -> {

						ModMessages.sendToServer(new ShenlongC2S(6)); //Recibir senzus

						this.minecraft.setScreen(null);

					}));
					//Rechazar
					this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47),
							Component.translatable("lines.menu.decline"), wa -> {
						this.minecraft.setScreen(null);
					}));
					break;

				case "radar":
					//Aceptar
					this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47),
							Component.translatable("lines.menu.accept"), wa -> {

						ModMessages.sendToServer(new ShenlongC2S(7)); //Recibir pieza radar

						this.minecraft.setScreen(null);

					}));
					//Rechazar
					this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47),
							Component.translatable("lines.menu.decline"), wa -> {
						this.minecraft.setScreen(null);
					}));
				case "revive":
					//Aceptar
					this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47),
							Component.translatable("lines.menu.accept"), wa -> {

						ModMessages.sendToServer(new ShenlongC2S(8)); //Revivir

						this.minecraft.setScreen(null);

					}));
					//Rechazar
					this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47),
							Component.translatable("lines.menu.decline"), wa -> {
						this.minecraft.setScreen(null);
					}));
					break;

				case "revive_other":
					//Aceptar
					this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47),
							Component.translatable("lines.menu.accept"), wa -> {
						ModMessages.sendToServer(new CharacterC2S("setShenronRevive", 1));
						this.minecraft.player.sendSystemMessage(Component.translatable("lines.shenron.revive_others_chat"));
						this.minecraft.setScreen(null);

					}));
					//Rechazar
					this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47),
							Component.translatable("lines.menu.decline"), wa -> {
						this.minecraft.setScreen(null);
					}));
					break;

				default:
					break;
			}
		}
	}
}
