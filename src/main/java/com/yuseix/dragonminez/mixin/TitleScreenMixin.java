package com.yuseix.dragonminez.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.init.MainSounds;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraftforge.internal.BrandingControl;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Unique
    private int currentFrame = 0;
    @Unique
    private long lastFrameTime = 0;
    @Unique
    private static final int FRAME_DURATION = 42;

    @Shadow
    private long fadeInStart;
    @Shadow
    private boolean fading;
    @Shadow
    @Nullable
    private SplashRenderer splash;
    @Shadow
    private LogoRenderer logoRenderer;


    @Unique
    private static boolean dragonminez$hasPlayedMusic = false;

    @Inject(method = "init", at = @At("HEAD"))
    private void onInit(CallbackInfo info) {


        if (!dragonminez$hasPlayedMusic) {
            SoundManager soundManager = Minecraft.getInstance().getSoundManager();

            // Stop any existing menu music
            soundManager.stop(null, SoundSource.MUSIC);
            dragonminez$hasPlayedMusic = true;

            soundManager.play(SimpleSoundInstance.forMusic(MainSounds.MENU_MUSIC.get()));
            //System.out.println("Music Custom del Menu ha iniciado!");
        }
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void modifyRender(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick, CallbackInfo ci) {
        TitleScreen screen = (TitleScreen) (Object) this;
        Minecraft mc = Minecraft.getInstance();

        // 🔹 Actualizar el fotograma actual
//        long currentTime = Util.getMillis();
//        if (currentTime - lastFrameTime >= FRAME_DURATION) {
//            currentFrame = (currentFrame + 1) % 192; // NUM_FRAMES es el número total de fotogramas
//            lastFrameTime = currentTime;
//            if (currentFrame >= 192) currentFrame = 0;
//        }

        // Dibujar el fotograma actual
        ResourceLocation background = new ResourceLocation(Reference.MOD_ID, "textures/gui/title/saiyantitle" + currentFrame + ".png");

        RenderSystem.enableBlend();
        // Renderizar un fondo negro antes, por si acaso
        pGuiGraphics.fill(0, 0, screen.width, screen.height, 0);
        mc.getTextureManager().bindForSetup(background);
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        pGuiGraphics.blit(background, 0, 0, 0, 0, screen.width, screen.height, screen.width, screen.height);
        pGuiGraphics.pose().popPose();

        // Evitar que el panorama se renderice
        ci.cancel();

        // Renderizar el resto de la UI manualmente
        if (fadeInStart == 0L && fading) {
            fadeInStart = Util.getMillis();
        }

        float f = fading ? (float) (Util.getMillis() - fadeInStart) / 1000.0F : 1.0F;
        RenderSystem.enableBlend();
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, fading ? (float) Mth.clamp(f, 0.0F, 1.0F) : 1.0F);

        int alpha = Mth.ceil((fading ? Mth.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F) * 255.0F) << 24;
        if ((alpha & -67108864) != 0) {
            // Imágen del Título
            if (logoRenderer != null) {
                logoRenderer.renderLogo(pGuiGraphics, screen.width, fading ? Mth.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F);
            }

            // Splashes
            if (splash != null) {
                splash.render(pGuiGraphics, screen.width, screen.getMinecraft().screen.getMinecraft().font, alpha);
            }

            // Información, versión, mods, etc
            String versionText = "Minecraft " + SharedConstants.getCurrentVersion().getName();
            if (mc.isDemo()) {
                versionText += " Demo";
            } else {
                versionText += ("release".equalsIgnoreCase(mc.getVersionType()) ? "" : "/" + mc.getVersionType());
            }

            if (Minecraft.checkModStatus().shouldReportAsModified()) {
                versionText += " " + I18n.get("menu.modded");
            }

            // Líneas de branding
            BrandingControl.forEachLine(false, true, (line, text) -> {
                int yOffset = screen.height - (10 + line * (screen.getMinecraft().font.lineHeight + 1));
                pGuiGraphics.drawString(screen.getMinecraft().font, text, 2, yOffset, 16777215 | alpha);
            });

            // Texto de copyright
            BrandingControl.forEachAboveCopyrightLine((line, text) -> {
                int yOffset = screen.height - (10 + (line + 1) * (screen.getMinecraft().font.lineHeight + 1));
                int xOffset = screen.width - screen.getMinecraft().font.width(text) - 2;
                pGuiGraphics.drawString(screen.getMinecraft().font, text, xOffset, yOffset, 16777215 | alpha);
            });

            // Botones y otros elementos de la UI
            screen.renderables.forEach(widget -> {
                if (widget instanceof AbstractWidget) {
                    ((AbstractWidget) widget).render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
                }
            });
        }
    }
}