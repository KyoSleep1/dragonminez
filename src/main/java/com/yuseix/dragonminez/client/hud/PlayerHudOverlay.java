package com.yuseix.dragonminez.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.fpcharacters.*;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.Map;

public class PlayerHudOverlay implements RenderEntityInv {

    private static final ResourceLocation efectos = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/hud/efectosperma.png");
    private static final ResourceLocation hud = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/hud/hud.png");

    private static int displayedRelease = 0; // Valor que se mostrará en pantalla, inicializado en 0
    private static int releaseUpdateSpeed = 2 * (20); // Velocidad de actualización en ticks

    public static final IGuiOverlay HUD_PLAYER = (forgeGui, guiGraphics, v, i, i1) -> {

        if (Minecraft.getInstance().options.renderDebug) {
            // Si la pantalla de depuración F3 está activada, no renderizar el HUD
            return;
        }

        int VidaMaxima = (int) Minecraft.getInstance().player.getMaxHealth();
        int vidarestante = (int) Minecraft.getInstance().player.getHealth(); //I'm feeling lonely, oh I wish I had a lover that could hold me
        // Now i'm crying in my room, so sceptical of love, but still I want it more, more, MOOOORE
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {
            var vidaMC = 20;
            var con = playerstats.getConstitution();
            var maxVIDA = 0.0;

            int vidawa = ((190 * vidarestante) / VidaMaxima);
            int vida = Math.min(vidawa, 190);

            int StaminaMax = 0;

            int TransfMax = 100;

            maxVIDA = DMZDatos.calcularCON(playerstats.getRace(), con, vidaMC, playerstats.getDmzClass());
            StaminaMax = DMZDatos.calcularSTM(playerstats.getRace(), (int) maxVIDA);

            int curStamina = playerstats.getCurStam();

            int energiaMax = 0;

            energiaMax = DMZDatos.calcularENE(playerstats.getRace(), playerstats.getEnergy(), playerstats.getDmzClass());
            
            int curEnergia = playerstats.getCurrentEnergy();

            int staminatotal = Math.min(((113 * curStamina) / StaminaMax), 113);

            int energiatotal = Math.min(((132 * curEnergia) / energiaMax), 132);

            int TransfActual = 100;  // TODO: Modificar esto para que vaya aumentando al presionar X botón, hasta llegar al 100% y transformarte.

            RenderSystem.enableBlend();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, hud);

            guiGraphics.pose().pushPose();
            guiGraphics.pose().scale(1.2f, 1.2f, 1.0f);
            //VIDA VACIO
            guiGraphics.blit(hud,
                    40,
                    8,
                    0,
                    1,
                    190,
                    12);
            //Ki vacio
            guiGraphics.blit(hud,
                    51,
                    20,
                    12,
                    13,
                    132,
                    6);
            //Stamina vacio
            guiGraphics.blit(hud,
                    50,
                    27,
                    6,
                    20,
                    101,
                    7);

            //Transformacion vacio
            guiGraphics.blit(hud,
                    5,
                    35,
                    4,
                    37,
                    21,
                    20);

            //Vida llena
            guiGraphics.blit(hud,
                    40,
                    8,
                    0,
                    59,
                    vida,
                    12);

            //Ki Lleno
            /*
            guiGraphics.blit(hud,
                    51,
                    22,
                    6,
                    71,
                    energiatotal,
                    6);

             */


            //Stamina llena
            guiGraphics.blit(hud,
                    50,
                    27,
                    5,
                    79,
                    staminatotal,
                    7);

            //Transformacion llena
            // NOTA: Reemplazar el 47 por la variable de la TransfActual
            guiGraphics.blit(hud,
                    5,
                    35,
                    27,
                    37,
                    10,
                    20);


            guiGraphics.pose().popPose();

            guiGraphics.pose().pushPose();
            guiGraphics.pose().scale(0.85f,0.85f,0.85f);
            renderPermanentEffects(guiGraphics);
            guiGraphics.pose().popPose();

            guiGraphics.drawString(Minecraft.getInstance().font, Component.literal(String.valueOf( (int) Math.round(Minecraft.getInstance().player.getHealth())) + "/" + (int) Math.round(maxVIDA)).withStyle(ChatFormatting.BOLD), 150, 14, 0xfddb1e);


            Component porcentaje = Component.empty();
                   /* .append(Component.translatable("dmz.hud.powerrelease"))
                    .append(Component.literal(": ")); */

            var posXPowerRelease = -80;

            //drawStringWithBorder(guiGraphics, Minecraft.getInstance().font, porcentaje, posXPowerRelease, 44,0x38fff0);
            //drawStringWithBorder(guiGraphics, Minecraft.getInstance().font, Component.empty().append(Component.literal(String.valueOf(playerstats.getDmzRelease()))).append(Component.literal("%")), posXPowerRelease + 115, 44,0xfdbf26);
            renderPowerReleaseAnimation(guiGraphics, playerstats.getDmzRelease(), posXPowerRelease + 115);

        double scaleFactor = Minecraft.getInstance().getWindow().getGuiScale();


        RenderSystem.enableScissor((int) ((5) * scaleFactor),
                (int) (Minecraft.getInstance().getWindow().getHeight() - (20 * 2) * scaleFactor),
                (int) ((25 * 2) * scaleFactor),
                (int) ((23 * 2) * scaleFactor));


        personajesMenu(guiGraphics);

        RenderSystem.disableScissor();

        RenderSystem.disableBlend();

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(1.2f, 1.2f, 1.0f);
        RenderSystem.enableBlend();
        renderKiBarColor(guiGraphics, playerstats.getRace(), playerstats.getDmzState(),energiatotal);
        RenderSystem.disableBlend();

        guiGraphics.pose().popPose();



        });

    };

    public static void renderKiBarColor(GuiGraphics guiGraphics,int raza, int transformacion, int energiatotal){

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {
            RenderSystem.enableBlend();
            
            var colorAura = cap.getAuraColor();

            float colorR = (colorAura >> 16) / 255.0F;
            float colorG = ((colorAura >> 8) & 0xff) / 255.0f;
            float colorB = (colorAura & 0xff) / 255.0f;

            switch (raza){
                case 0: //humano
                        RenderSystem.setShaderColor(colorR, colorG, colorB, 1.0f);
                    break;
                case 1: //saiyan
                    //Ejemplo de si esta en X transformacion jijij9i
                    if(transformacion == 2){
                        RenderSystem.setShaderColor(0.990f, 0.966f, 0.515f, 1.0f);
                    } else {
                        RenderSystem.setShaderColor(colorR, colorG, colorB, 1.0f);
                    }
                    break;
                case 2: //namek
                    RenderSystem.setShaderColor(colorR, colorG, colorB, 1.0f);
                    break;
                case 3: //bio
                    RenderSystem.setShaderColor(colorR, colorG, colorB, 1.0f);
                    break;
                case 4: //cold demon
                    RenderSystem.setShaderColor(colorR, colorG, colorB, 1.0f);
                    break;
                case 5: //majin
                    RenderSystem.setShaderColor(colorR, colorG, colorB, 1.0f);
                    break;
                default: //en caso de que exista otra raza
                    RenderSystem.setShaderColor(colorR, colorG, colorB, 1.0f);
                    break;
            }

            guiGraphics.blit(hud,
                    51,
                    20,
                    6,
                    71,
                    energiatotal,
                    6);
            RenderSystem.disableBlend();

        });


    }
    public static void renderPowerReleaseAnimation(GuiGraphics guiGraphics, int porcentaje, int posXPowerRelease) {
        int targetRelease = porcentaje; // El valor objetivo que queremos mostrar

        // Si el valor mostrado es menor que el objetivo, incrementarlo gradualmente
        if (displayedRelease < targetRelease) {
            displayedRelease += releaseUpdateSpeed;
            if (displayedRelease > targetRelease) {
                displayedRelease = targetRelease; // Asegurarse de no sobrepasar el objetivo
            }
        }

        // Si el valor mostrado es mayor que el objetivo, disminuirlo gradualmente
        if (displayedRelease > targetRelease) {
            displayedRelease -= releaseUpdateSpeed;
            if (displayedRelease < targetRelease) {
                displayedRelease = targetRelease; // Asegurarse de no bajar más del objetivo
            }
        }

        // Renderizar el valor con la animación
        drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                Component.empty().append(Component.literal(String.valueOf(displayedRelease))).append(Component.literal("%")),
                posXPowerRelease, 49, 0xfdbf26);
    }

    private static void renderPermanentEffects(GuiGraphics guiGraphics) {

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {
            int x = 72; // Posición inicial en X
            int y = 50; // Posición inicial en Y
            int textureSize = 20; // Tamaño de cada efecto en la textura

            for (Map.Entry<String, Boolean> entry : cap.getDMZPermanentEffects().entrySet()) {
                if (entry.getValue()) {
                    // Obtén las coordenadas de la textura para el efecto actual
                    int[] textureCoords = getTextureCoordinates(entry.getKey());
                    if (textureCoords != null) {
                        // Dibuja la sección específica de la textura usando GuiGraphics
                        guiGraphics.blit(efectos, x, y, textureCoords[0], textureCoords[1], textureSize, textureSize);
                        x += textureSize+2; // Incrementa para la siguiente textura
                    }
                }
            }
        });

    }

    private static int[] getTextureCoordinates(String effectName) {
        switch (effectName) {
            case "majin": return new int[] {0, 0};       // Coordenadas de la textura
            case "mightfruit": return new int[] {20, 0};
            case "kaioken": return new int[] {40, 0};
            default: return null;
        }
    }

    public static void personajesMenu(GuiGraphics pGuiGraphics){

        var player = Minecraft.getInstance().player;

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            if(cap.getRace() == 0){//HUMANO
                if (cap.getGender().equals("Male")){
                    FPHumanSaiyanEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), Minecraft.getInstance().level);
                    avatar.setOwnerUUID(player.getUUID());

                    RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);

                }else {
                    FPSlimEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), Minecraft.getInstance().level);
                    avatar.setOwnerUUID(player.getUUID());

                    RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);
                }

            }else if(cap.getRace() == 1){ //SAIYAN
                if(cap.getBodytype() == 0){
                    if(Minecraft.getInstance().player.getModelName().equals("default")){
                        FPHumanSaiyanEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), Minecraft.getInstance().level);
                        avatar.setOwnerUUID(player.getUUID());

                        RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);

                    }else {
                        FPSlimEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), Minecraft.getInstance().level);
                        avatar.setOwnerUUID(player.getUUID());

                        RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);
                    }

                } else {
                    if (cap.getGender().equals("Male")){
                        FPHumanSaiyanEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), Minecraft.getInstance().level);
                        avatar.setOwnerUUID(player.getUUID());

                        RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);

                    }else {
                        FPSlimEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), Minecraft.getInstance().level);
                        avatar.setOwnerUUID(player.getUUID());

                        RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);
                    }
                }

            }else if(cap.getRace() == 2){ //NAMEK
                FPNamekianEntity avatar = new FPNamekianEntity(MainEntity.FP_NAMEK.get(), Minecraft.getInstance().level);
                avatar.setOwnerUUID(player.getUUID());

                RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);

            }else if(cap.getRace() == 3){ //BIOANDROIDE
                FPBioAndroidEntity avatar = new FPBioAndroidEntity(MainEntity.FP_BIOANDROIDE.get(), Minecraft.getInstance().level);
                avatar.setOwnerUUID(player.getUUID());

                RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);

            }else if(cap.getRace() == 4){ //NARCO OSEA ARCO JEJE
                FPDemonColdEntity avatar = new FPDemonColdEntity(MainEntity.FP_DEMONCOLD.get(), Minecraft.getInstance().level);
                avatar.setOwnerUUID(player.getUUID());

                RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);

            }else { // MAJIN
                if (cap.getGender().equals("Male")){
                    FPMajinGordEntity avatar = new FPMajinGordEntity(MainEntity.FP_MAJINGORDO.get(), Minecraft.getInstance().level);
                    avatar.setOwnerUUID(player.getUUID());

                    RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);

                }else {
                    FPSlimEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), Minecraft.getInstance().level);
                    avatar.setOwnerUUID(player.getUUID());

                    RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);
                }
            }


        });

    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {

        guiGraphics.drawString(font, texto, x + 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x - 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y + 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y - 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y, ColorTexto, false);
    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto) {
        drawStringWithBorder(guiGraphics, font, texto, x, y, ColorTexto, 0);
    }

}
