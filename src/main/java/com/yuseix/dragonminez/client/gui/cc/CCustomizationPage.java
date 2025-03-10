package com.yuseix.dragonminez.client.gui.cc;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.*;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.fpcharacters.*;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZClientConfig;
import com.yuseix.dragonminez.utils.DMZDatos;
import com.yuseix.dragonminez.utils.TranslateManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CCustomizationPage extends Screen {

    private int alturaTexto;
    private int anchoTexto;

    //Textura de los panoramas
    private static final ResourceLocation PANORAMA_PATH = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/panorama");
    private static final ResourceLocation PANORAMA_BUU = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/buu_panorama");
    private static final ResourceLocation PANORAMA_BIO = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/bio_panorama");
    private static final ResourceLocation PANORAMA_SAIYAN = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/s_panorama");
    private static final ResourceLocation PANORAMA_NAMEK = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/n_panorama");
    private static final ResourceLocation PANORAMA_COLD = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/c_panorama");

    //Panoramas
    private final PanoramaRenderer customPanorama = new PanoramaRenderer(new CubeMap(PANORAMA_PATH));
    private final PanoramaRenderer panoramaBuu = new PanoramaRenderer(new CubeMap(PANORAMA_BUU));
    private final PanoramaRenderer panoramaBio = new PanoramaRenderer(new CubeMap(PANORAMA_BIO));
    private final PanoramaRenderer panoramaSai = new PanoramaRenderer(new CubeMap(PANORAMA_SAIYAN));
    private final PanoramaRenderer panoramaNam = new PanoramaRenderer(new CubeMap(PANORAMA_NAMEK));
    private final PanoramaRenderer panoramaCold = new PanoramaRenderer(new CubeMap(PANORAMA_COLD));


    private static final ResourceLocation menu1 = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menugrande.png");

    private static final ResourceLocation texto = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menutexto.png");

    private final List<ColorButton2> botonColorDefecto = new ArrayList<>();

    private DMZRightButton eyesTypeRight, eyesTypeLeft, bodyTypeRightButton, bodyTypeLeftButton, gendersRigthButton, gendersLeftButton, hairRigthButton, hairLeftButton, claseRigthButton,claseLeftButton;
    private DMZRightButton botonAlignmentRight, botonAlignmentLeft;
    private TextButton nextButton, backButton;
    private ColorButton eyesButtonColor, eyesButtonColor2, bodyButtonColor1, bodyButtonColor2, bodyButtonColor3, hairButtonColor, auraButtonColor;
    private CustomButtons igualarButton;

    private int currentPage = 0;

    private float angleXComponent;

    private DMZDatos dmzdatos = new DMZDatos();


    public CCustomizationPage(Component pTitle) {
        super(pTitle);
        this.angleXComponent = 0.0f;  // Inicia en 0
    }

    @Override
    protected void init() {

        //MenuInicio
        int posY = (this.minecraft.getWindow().getGuiScaledHeight()) / 2;

        if (currentPage == 0) {

            botonesRazaColores(72, posY);


        } else if (currentPage == 1) {

            botonAuraColor(72, posY);

        }


        this.addRenderableWidget(new DMZCustomButton(this.width / 2 + 20, this.height - 25, 20, 20, Component.literal("->"), (button) -> {
            this.angleXComponent -= 1.0f; // Incrementa el valor en 5 grados
        }));

        this.addRenderableWidget(new DMZCustomButton(this.width / 2 - 40, this.height - 25, 20, 20, Component.literal("<-"), (button) -> {
            this.angleXComponent += 1.0f; // Decrementa el valor en 5 grados
        }));

        this.addRenderableWidget(new DMZCustomButton(this.width / 2 - 10, this.height - 25, 20, 20, Component.literal("0"), (button) -> {
            this.angleXComponent = 0.0f; // Resetea a 0
        }));

        super.init();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        //MenuInicio
        int ancho = (this.width / 2);
        int alto = (this.minecraft.getWindow().getGuiScaledHeight()) / 2;


        botonNextBack(ancho, this.height - 25);

        if (currentPage == 0) {
            botonesBodyType(113, alto - 44);
            botonesGeneros(113, alto + 3);
            botonesOjos(113,alto - 76 );
            botonesCabellos(113, alto + 3);
            botonIgualar();

        } else if (currentPage == 1) {
            botonesClases(113, alto - 76);
            botonesAlignment(113,alto - 39);

        } else if (currentPage == 2) {

        } else {

        }

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        //renderBackground(pGuiGraphics);

        panoramas(pGuiGraphics, pPartialTick);

        if (currentPage == 0) {
            for (ColorButton2 button : botonColorDefecto) {
                button.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
            }

            pagina1(pGuiGraphics);


        } else if (currentPage == 1) {

            pagina2(pGuiGraphics);

        } else if (currentPage == 2) {



        } else {

        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);


        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

    }


    private void botonIgualar(){
        this.removeWidget(igualarButton);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {
            int posX = 72;
            int posY = (this.height) / 2;

            if (cap.getIntValue("race") == 0 || cap.getIntValue("race") == 1) {
                //Boton para igualar los ojos
                this.igualarButton = (CustomButtons) this.addRenderableWidget(new CustomButtons("igual", posX + 5, posY - 61, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", cap.getIntValue("eye1color")));
                }));
            } else if (cap.getIntValue("race") == 2) {
                //Boton para igualar los ojos
                this.igualarButton = (CustomButtons) this.addRenderableWidget(new CustomButtons("igual", posX + 5, posY - 61, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", cap.getIntValue("eye1color")));
                }));
            } else if (cap.getIntValue("race") == 4) {
                //Boton para igualar los ojos
                this.igualarButton = (CustomButtons) this.addRenderableWidget(new CustomButtons("igual", posX + 5, posY - 61, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", cap.getIntValue("eye1color")));
                }));
            }

        });

    }


    public void botonNextBack(int posX, int posY) {
        if (this.minecraft.level.isClientSide()) {
            this.removeWidget(backButton);
            this.removeWidget(nextButton);

            if (currentPage == 0) {
                //BOTON VOLVER
                this.backButton = (TextButton) this.addRenderableWidget(new TextButton(20, posY, TranslateManager.BACK.withStyle(ChatFormatting.BOLD), button -> {

                    ModMessages.sendToServer(new CharacterC2S("hairID", 0));
                    ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                    ModMessages.sendToServer(new CharacterC2S("Gender", 0));
                    this.minecraft.setScreen(new CFirstPage());

                }));

                //BOTON SIGUIENTE
                this.nextButton = (TextButton) this.addRenderableWidget(new TextButton(this.width - 85, posY, TranslateManager.NEXT.withStyle(ChatFormatting.BOLD), button -> {
                    this.removeWidget(eyesButtonColor);
                    this.removeWidget(eyesButtonColor2);
                    this.removeWidget(bodyButtonColor1);
                    this.removeWidget(bodyButtonColor2);
                    this.removeWidget(bodyButtonColor3);
                    this.removeWidget(hairButtonColor);
                    this.removeWidget(eyesTypeLeft);
                    this.removeWidget(eyesTypeRight);
                    this.removeWidget(bodyTypeRightButton);
                    this.removeWidget(bodyTypeLeftButton);
                    this.removeWidget(gendersRigthButton);
                    this.removeWidget(gendersLeftButton);
                    this.removeWidget(hairRigthButton);
                    this.removeWidget(hairLeftButton);
                    this.removeWidget(auraButtonColor);
                    this.removeWidget(nextButton);
                    this.removeWidget(botonAlignmentRight);
                    this.removeWidget(botonAlignmentLeft);
                    this.removeWidget(igualarButton);
                    clearAllButtons();
                    currentPage = 1;

                    botonAuraColor(72, this.height / 2);
                }));
            } else if (currentPage == 1) {
                //BOTON VOLVER
                this.backButton = (TextButton) this.addRenderableWidget(new TextButton(20, posY, TranslateManager.BACK.withStyle(ChatFormatting.BOLD), button -> {
                    currentPage = 0;
                    this.removeWidget(eyesButtonColor);
                    this.removeWidget(eyesButtonColor2);
                    this.removeWidget(bodyButtonColor1);
                    this.removeWidget(bodyButtonColor2);
                    this.removeWidget(bodyButtonColor3);
                    this.removeWidget(hairButtonColor);
                    this.removeWidget(eyesTypeLeft);
                    this.removeWidget(eyesTypeRight);
                    this.removeWidget(bodyTypeRightButton);
                    this.removeWidget(bodyTypeLeftButton);
                    this.removeWidget(gendersRigthButton);
                    this.removeWidget(gendersLeftButton);
                    this.removeWidget(hairRigthButton);
                    this.removeWidget(hairLeftButton);
                    this.removeWidget(auraButtonColor);
                    this.removeWidget(claseRigthButton);
                    this.removeWidget(claseLeftButton);
                    this.removeWidget(botonAlignmentRight);
                    this.removeWidget(botonAlignmentLeft);
                    this.removeWidget(igualarButton);
                    clearAllButtons();

                    botonesRazaColores(72, this.height / 2);

                }));

                //BOTON CONFIRMAR
                this.nextButton = (TextButton) this.addRenderableWidget(new TextButton(this.width - 85, posY, Component.literal("Confirm").withStyle(ChatFormatting.BOLD), button -> {
                    this.removeWidget(auraButtonColor);
                    this.removeWidget(nextButton);
                    clearAllButtons();

                    ModMessages.sendToServer(new CharacterC2S("isConfirm", 1));
                    DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {
                        if (cap.getStat("STR") <= 10) {
                            initialStats(cap.getIntValue("race"),cap.getStringValue("class"));
                        }
                    });
                    this.minecraft.setScreen(null);

                }));

            } else {}
        }
    }

    public void initialStats(int raza, String clase){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            switch(clase){
                case "warrior":
                    switch (raza){
                        case 0: //Humano
                            ModMessages.sendToServer(new CharacterC2S("str", DMZClientConfig.getInit_StrStat("human", "warrior")));
                            ModMessages.sendToServer(new CharacterC2S("def", DMZClientConfig.getInit_DefStat("human", "warrior")));
                            ModMessages.sendToServer(new CharacterC2S("con", DMZClientConfig.getInit_ConStat("human","warrior")));
                            ModMessages.sendToServer(new CharacterC2S("pwr", DMZClientConfig.getInit_PWRStat("human", "warrior")));
                            ModMessages.sendToServer(new CharacterC2S("ene", DMZClientConfig.getInit_ENEStat("human", "warrior")));

                            break;
                        case 1: //Saiyan
                            ModMessages.sendToServer(new CharacterC2S("str", DMZClientConfig.getInit_StrStat("saiyan","warrior")));
                            ModMessages.sendToServer(new CharacterC2S("def", DMZClientConfig.getInit_DefStat("saiyan", "warrior")));
                            ModMessages.sendToServer(new CharacterC2S("con", DMZClientConfig.getInit_ConStat("saiyan","warrior")));
                            ModMessages.sendToServer(new CharacterC2S("pwr", DMZClientConfig.getInit_PWRStat("saiyan", "warrior")));
                            ModMessages.sendToServer(new CharacterC2S("ene", DMZClientConfig.getInit_ENEStat("saiyan", "warrior")));

                            break;
                        case 2: //Namek
                            ModMessages.sendToServer(new CharacterC2S("str", DMZClientConfig.getInit_StrStat("namek","warrior")));
                            ModMessages.sendToServer(new CharacterC2S("def", DMZClientConfig.getInit_DefStat("namek", "warrior")));
                            ModMessages.sendToServer(new CharacterC2S("con", DMZClientConfig.getInit_ConStat("namek","warrior")));
                            ModMessages.sendToServer(new CharacterC2S("pwr", DMZClientConfig.getInit_PWRStat("namek", "warrior")));
                            ModMessages.sendToServer(new CharacterC2S("ene", DMZClientConfig.getInit_ENEStat("namek", "warrior")));

                            break;
                        case 3: //BioAndroide
                            ModMessages.sendToServer(new CharacterC2S("str", DMZClientConfig.getInit_StrStat("bio","warrior")));
                            ModMessages.sendToServer(new CharacterC2S("def", DMZClientConfig.getInit_DefStat("bio", "warrior")));
                            ModMessages.sendToServer(new CharacterC2S("con", DMZClientConfig.getInit_ConStat("bio","warrior")));
                            ModMessages.sendToServer(new CharacterC2S("pwr", DMZClientConfig.getInit_PWRStat("bio", "warrior")));
                            ModMessages.sendToServer(new CharacterC2S("ene", DMZClientConfig.getInit_ENEStat("bio", "warrior")));

                            break;
                        case 4: //Cold Demon
                                ModMessages.sendToServer(new CharacterC2S("str", DMZClientConfig.getInit_StrStat("cold","warrior")));
                            ModMessages.sendToServer(new CharacterC2S("def", DMZClientConfig.getInit_DefStat("cold","warrior")));
                            ModMessages.sendToServer(new CharacterC2S("con", DMZClientConfig.getInit_ConStat("cold","warrior")));
                            ModMessages.sendToServer(new CharacterC2S("pwr", DMZClientConfig.getInit_PWRStat("cold", "warrior")));
                            ModMessages.sendToServer(new CharacterC2S("ene", DMZClientConfig.getInit_ENEStat("cold", "warrior")));

                            break;
                        case 5: //Majin
                            ModMessages.sendToServer(new CharacterC2S("str", DMZClientConfig.getInit_StrStat("majin","warrior")));
                            ModMessages.sendToServer(new CharacterC2S("def", DMZClientConfig.getInit_DefStat("majin","warrior")));
                            ModMessages.sendToServer(new CharacterC2S("con", DMZClientConfig.getInit_ConStat("majin","warrior")));
                            ModMessages.sendToServer(new CharacterC2S("pwr", DMZClientConfig.getInit_PWRStat("majin", "warrior")));
                            ModMessages.sendToServer(new CharacterC2S("ene", DMZClientConfig.getInit_ENEStat("majin", "warrior")));

                            break;
                        default:
                            break;
                    }
                    break;
                case "spiritualist":
                    switch (raza){
                        case 0: //Humano
                            ModMessages.sendToServer(new CharacterC2S("str", DMZClientConfig.getInit_StrStat("human", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("def", DMZClientConfig.getInit_DefStat("human", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("con", DMZClientConfig.getInit_ConStat("human","spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("pwr", DMZClientConfig.getInit_PWRStat("human", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("ene", DMZClientConfig.getInit_ENEStat("human", "spiritualist")));
                            break;
                        case 1: //Saiyan
                            ModMessages.sendToServer(new CharacterC2S("str", DMZClientConfig.getInit_StrStat("saiyan", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("def", DMZClientConfig.getInit_DefStat("saiyan", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("con", DMZClientConfig.getInit_ConStat("saiyan","spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("pwr", DMZClientConfig.getInit_PWRStat("saiyan", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("ene", DMZClientConfig.getInit_ENEStat("saiyan", "spiritualist")));
                            break;
                        case 2: //Namek
                            ModMessages.sendToServer(new CharacterC2S("str", DMZClientConfig.getInit_StrStat("namek", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("def", DMZClientConfig.getInit_DefStat("namek", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("con", DMZClientConfig.getInit_ConStat("namek","spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("pwr", DMZClientConfig.getInit_PWRStat("namek", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("ene", DMZClientConfig.getInit_ENEStat("namek", "spiritualist")));
                            break;
                        case 3: //BioAndroide
                            ModMessages.sendToServer(new CharacterC2S("str", DMZClientConfig.getInit_StrStat("bio", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("def", DMZClientConfig.getInit_DefStat("bio", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("con", DMZClientConfig.getInit_ConStat("bio","spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("pwr", DMZClientConfig.getInit_PWRStat("bio", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("ene", DMZClientConfig.getInit_ENEStat("bio", "spiritualist")));
                            break;
                        case 4: //Cold Demon
                            ModMessages.sendToServer(new CharacterC2S("str", DMZClientConfig.getInit_StrStat("cold", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("def", DMZClientConfig.getInit_DefStat("cold", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("con", DMZClientConfig.getInit_ConStat("cold","spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("pwr", DMZClientConfig.getInit_PWRStat("cold", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("ene", DMZClientConfig.getInit_ENEStat("cold", "spiritualist")));
                            break;
                        case 5: //Majin
                            ModMessages.sendToServer(new CharacterC2S("str", DMZClientConfig.getInit_StrStat("majin", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("def", DMZClientConfig.getInit_DefStat("majin", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("con", DMZClientConfig.getInit_ConStat("majin","spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("pwr", DMZClientConfig.getInit_PWRStat("majin", "spiritualist")));
                            ModMessages.sendToServer(new CharacterC2S("ene", DMZClientConfig.getInit_ENEStat("majin", "spiritualist")));
                            break;
                        default:
                            break;
                    }
                    break;
                default: //Poner algo por si spiritualist
                    break;
            }

        });
    }

    public void botonesRazaColores(int posX, int posY) {
        if (this.minecraft.level.isClientSide()) {
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

            this.removeWidget(eyesButtonColor);
            this.removeWidget(eyesButtonColor2);
            this.removeWidget(bodyButtonColor1);
            this.removeWidget(bodyButtonColor2);
            this.removeWidget(bodyButtonColor3);
            this.removeWidget(hairButtonColor);
            this.removeWidget(igualarButton);

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

                switch (cap.getIntValue("race")) {
                    case 0,1:
                        //BOTON COLOR OJO 1
                        this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX - 15, posY - 63, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("eye1Color"));
                        }));
                        //BOTON COLOR OJO 2
                        this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY - 63, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("eye2Color"));
                        }));

                        this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX, posY- 18, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor1"));
                        }));

                        this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX, posY + 64, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("hairColor"));
                        }));
                        break;
                    case 2:
                        //BOTON COLOR OJO 1
                        this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX - 15, posY - 63, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("eye1Color"));
                        }));
                        //BOTON COLOR OJO 2
                        this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY - 63, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("eye2Color"));
                        }));

                        this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX - 33, posY - 18, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor1"));
                        }));

                        this.bodyButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor2", posX - 11, posY - 18, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor2"));
                        }));

                        this.bodyButtonColor3 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor3", posX + 11, posY - 18, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor3"));
                        }));

                        this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX + 33, posY - 18, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("hairColor"));
                        }));
                        break;
                    case 3:

                        //BOTON COLOR OJO 1
                        this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX, posY - 63, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("eye1Color"));
                        }));

                        this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX - 25, posY - 17, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor1"));
                        }));

                        this.bodyButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor2", posX, posY - 17, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor2"));
                        }));

                        this.bodyButtonColor3 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor3", posX + 25, posY - 17, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor3"));
                        }));


                        break;
                    case 4:
                        //BOTON COLOR OJO 1
                        this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX - 15, posY - 63, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("eye1Color"));
                        }));
                        //BOTON COLOR OJO 2
                        this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY - 63, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("eye2Color"));
                        }));

                        this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX - 33, posY - 14, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor1"));
                        }));

                        this.bodyButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor2", posX - 11, posY - 14, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor2"));
                        }));

                        this.bodyButtonColor3 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor3", posX + 11, posY - 14, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor3"));
                        }));

                        this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX + 33, posY - 14, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("hairColor"));
                        }));
                        break;
                    case 5:
                        //BOTON COLOR OJO 1
                        this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX, posY - 63, Component.empty(), button -> {

                            Minecraft.getInstance().setScreen(new ColorPickerScreen("eye1Color"));
                        }));

                        this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX-11, posY- 18, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor1"));
                        }));
                        this.bodyButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor2", posX + 11, posY - 18, Component.empty(), button -> {
                            Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor2"));
                        }));

                        break;
                    default:
                        break;

                }

            });
            RenderSystem.disableBlend();
        }
    }

    public void botonAuraColor(int posX, int posY) {
        if (this.minecraft.level.isClientSide()) {
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

            this.removeWidget(eyesButtonColor);
            this.removeWidget(eyesButtonColor2);
            this.removeWidget(bodyButtonColor1);
            this.removeWidget(bodyButtonColor2);
            this.removeWidget(bodyButtonColor3);
            this.removeWidget(hairButtonColor);
            this.removeWidget(auraButtonColor);

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

                this.auraButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("auraColor", posX, posY + 3, Component.empty(), button -> {
                    Minecraft.getInstance().setScreen(new ColorPickerScreen("auraColor"));

                }));

                RenderSystem.disableBlend();
            });
        }
    }

    public void botonesCabellos(int posX, int posY) {

        this.removeWidget(hairRigthButton);
        this.removeWidget(hairLeftButton);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            switch (cap.getIntValue("race")) {
                case 0,1:
                    if (cap.getIntValue("hairid") == 0) {
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));

                    } else if (cap.getIntValue("hairid") == 1) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 0));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    } else if (cap.getIntValue("hairid") == 2) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 3));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    } else if (cap.getIntValue("hairid") == 3) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 4));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    } else if (cap.getIntValue("hairid") == 4) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 3));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 5));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    } else if (cap.getIntValue("hairid") == 5) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 4));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 6));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    } else if (cap.getIntValue("hairid") == 6) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 5));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
}
                    break;
                case 2:
                    if (cap.getIntValue("hairid") == 0) {
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY+11, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));

                    } else if (cap.getIntValue("hairid") == 1) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+11, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 0));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY+11, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    }else if (cap.getIntValue("hairid") == 2) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+11, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    }

                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    if (cap.getStringValue("gender").equals("male")) {
                        if (cap.getIntValue("hairid") == 0) {
                            this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                        } else if (cap.getIntValue("hairid") == 1) {
                            this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY + 47, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 0));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                        }
                    } else {
                        if (cap.getIntValue("hairid") == 0) {
                            // Forzar cambio a 1 si detecta 0 siendo mujer.
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                        } else if (cap.getIntValue("hairid") == 1) {
                            this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                        } else if (cap.getIntValue("hairid") == 2) {
                            this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY + 47, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                            this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 3));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                        } else if (cap.getIntValue("hairid") == 3) {
                            this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                            this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 4));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                        } else if (cap.getIntValue("hairid") == 4) {
                            this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 3));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                            this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 5));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                        } else if (cap.getIntValue("hairid") == 5) {
                            this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY + 47, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 4));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                            this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 6));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                        } else if (cap.getIntValue("hairid") == 6) {
                            this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY + 47, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 5));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                        }
                    }
                    break;
                default:
                    break;
            }

        });
    }

    public void botonesOjos(int posX, int posY) {

        this.removeWidget(eyesTypeRight);
        this.removeWidget(eyesTypeLeft);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            switch (cap.getIntValue("race")) {
                case 0,1:
                    if (cap.getIntValue("bodytype") > 0) {
                        if (cap.getIntValue("eyestype") == 0) {
                            this.eyesTypeRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 1));
                                this.removeWidget(eyesTypeRight);
                                this.removeWidget(eyesTypeLeft);
                            }));

                        } else if (cap.getIntValue("eyestype") == 1) {
                            this.eyesTypeLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 0));
                                this.removeWidget(eyesTypeRight);
                                this.removeWidget(eyesTypeLeft);
                            }));
                        }
                    }

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    if (cap.getIntValue("eyestype") == 0) {
                        this.eyesTypeRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("EyeType", 1));
                            this.removeWidget(eyesTypeRight);
                            this.removeWidget(eyesTypeLeft);
                        }));

                    } else {
                        this.eyesTypeLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("EyeType", 0));
                            this.removeWidget(eyesTypeRight);
                            this.removeWidget(eyesTypeLeft);
                        }));
                    }

                    break;
                default:
                    break;
            }

        });
    }
    public void botonesClases(int posX, int posY) {

        this.removeWidget(claseLeftButton);
        this.removeWidget(claseRigthButton);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            if(cap.getStringValue("class").equals("warrior")){
                this.claseRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("dmzClass", 1));
                    this.removeWidget(claseRigthButton);
                    this.removeWidget(claseLeftButton);
                }));
            }else {
                this.claseLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("dmzClass", 0));
                    this.removeWidget(claseRigthButton);
                    this.removeWidget(claseLeftButton);
                }));
            }

        });
    }
    public void botonesAlignment(int posX, int posY) {

        this.removeWidget(botonAlignmentLeft);
        this.removeWidget(botonAlignmentRight);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            if(cap.getIntValue("alignment") > 50){
                this.botonAlignmentRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("dmzAlignment", 50));
                    this.removeWidget(botonAlignmentRight);
                    this.removeWidget(botonAlignmentLeft);
                }));
            }else if(cap.getIntValue("alignment") == 50){
                this.botonAlignmentLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("dmzAlignment", 100));
                    this.removeWidget(botonAlignmentRight);
                    this.removeWidget(botonAlignmentLeft);
                }));
                this.botonAlignmentRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("dmzAlignment", 0));
                    this.removeWidget(botonAlignmentRight);
                    this.removeWidget(botonAlignmentLeft);
                }));
            } else {
                this.botonAlignmentLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("dmzAlignment", 50));
                    this.removeWidget(botonAlignmentRight);
                    this.removeWidget(botonAlignmentLeft);
                }));
            }

        });
    }
    public void botonesBodyType(int posX, int posY) {

        this.removeWidget(bodyTypeRightButton);
        this.removeWidget(bodyTypeLeftButton);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            switch (cap.getIntValue("race")) {
                case 0,1:
                    if (cap.getIntValue("bodytype") == 0) {
                        this.bodyTypeRightButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY+12, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    } else if (cap.getIntValue("bodytype") == 1) {
                        this.bodyTypeLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+12, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    }
                    break;
                case 2:
                    if (cap.getIntValue("bodytype") == 0) {
                        this.bodyTypeRightButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY+12, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    } else if (cap.getIntValue("bodytype") == 1) {
                        this.bodyTypeLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+12, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    }
                    break;
                case 3:
                    break;
                case 4:
                    if (cap.getIntValue("bodytype") == 0) {
                        this.bodyTypeRightButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY+14, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    } else if (cap.getIntValue("bodytype") == 1) {
                        this.bodyTypeLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+14, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                        this.bodyTypeRightButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY+14, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 2));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    } else if(cap.getIntValue("bodytype") == 2){
                        this.bodyTypeLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+14, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    }
                    break;
                case 5:
                    if (cap.getIntValue("bodytype") == 0) {
                        this.bodyTypeRightButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY+14, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    } else {
                        this.bodyTypeLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+14, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    }
                    break;
                default:
                    break;
            }


        });
    }

    public void botonesGeneros(int posX, int posY) {

        this.removeWidget(gendersRigthButton);
        this.removeWidget(gendersLeftButton);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            if (cap.getIntValue("race") == 0 || cap.getIntValue("race") == 1 || cap.getIntValue("race") == 5) {
                if (cap.getStringValue("gender").equals("female")) {
                    this.gendersLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+11, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("hairID", 0));
                        ModMessages.sendToServer(new CharacterC2S("Gender", 0));
                        this.removeWidget(gendersRigthButton);
                        this.removeWidget(gendersLeftButton);
                    }));
                } else if (cap.getStringValue("gender").equals("male")) {
                    this.gendersRigthButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY+11, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                        ModMessages.sendToServer(new CharacterC2S("Gender", 1));
                        this.removeWidget(gendersRigthButton);
                        this.removeWidget(gendersLeftButton);
                    }));
                }
            }
        });
    }


    public void pagina1(GuiGraphics pGuiGraphics) {

        //MENU CARACTERISTICAS
        alturaTexto = (pGuiGraphics.guiHeight() / 2);
        anchoTexto = 10;
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        pGuiGraphics.blit(menu1, anchoTexto, alturaTexto - 110, 0, 0, 148, 222);
        RenderSystem.disableBlend();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            //EYES TITTLE
            alturaTexto = (pGuiGraphics.guiHeight() / 2);
            anchoTexto = 84;
            drawStringWithBorder2(pGuiGraphics,font,Component.translatable("dmz.ccreation.eyes.types"),anchoTexto, alturaTexto - 89,0xFF9B9B);

            //TIPOS DE OJOS
            alturaTexto = (pGuiGraphics.guiHeight() / 2);
            anchoTexto = 84;

            if (cap.getIntValue("eyestype") == 0) {
                drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto - 73, 0xFFFFFF);
            } else if (cap.getIntValue("eyestype") == 1) {
                drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto - 73, 0xFFFFFF);
            } else if (cap.getIntValue("eyestype") == 2){
                drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 03"), anchoTexto, alturaTexto - 73, 0xFFFFFF);
            }

            //TIPO DE CUERPO
            anchoTexto = 47;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, texto);
            pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 48, 0, 0, 73, 15);
            RenderSystem.disableBlend();

            //CUERPO TITULO
            anchoTexto = 84;
            drawStringWithBorder2(pGuiGraphics,font,Component.translatable("dmz.ccreation.body.types"),anchoTexto, alturaTexto - 44,0xFF9B9B);


            switch (cap.getIntValue("race")) {
                case 0,1:
                    //TIPO DE CUERPO
                    if (cap.getIntValue("bodytype") == 0) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Default"), 67, alturaTexto - 29, 0xFFFFFF);
                    } else if (cap.getIntValue("bodytype") == 1) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Custom"), 67, alturaTexto - 29, 0xFFFFFF);

                    }

                    //GENERO TIPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 2, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    anchoTexto = 84;
                    drawStringWithBorder2(pGuiGraphics,font,Component.translatable("dmz.ccreation.gender"),anchoTexto, alturaTexto + 2,0xFF9B9B);

                    switch (cap.getStringValue("gender")) {
                        case "male":
                            anchoTexto = 84;
                            drawStringWithBorder2(pGuiGraphics, font, Component.literal("Male"), anchoTexto, alturaTexto + 18, 0xFFFFFF, 0x2133A6);
                            break;
                        case "female":
                            anchoTexto = 84;
                            drawStringWithBorder2(pGuiGraphics, font, Component.literal("Female"), anchoTexto, alturaTexto + 18, 0xFFFFFF, 0xFC63D9);
                            break;
                    }

                    //HAIR COLOR
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto + 34, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    //HAIR TITULO
                    anchoTexto = 84;
                    drawStringWithBorder2(pGuiGraphics,font,Component.translatable("dmz.ccreation.hairs"),anchoTexto, alturaTexto + 38,0xFF9B9B);

                    anchoTexto = 84;
                    if (cap.getIntValue("hairid") == 0) {
                        drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getIntValue("hairid") == 1) {
                        drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getIntValue("hairid") == 2) {
                        drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 03"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getIntValue("hairid") == 3) {
                        drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 04"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getIntValue("hairid") == 4) {
                        drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 05"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getIntValue("hairid") == 5) {
                        drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 06"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getIntValue("hairid") == 6) {
                        drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 07"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    }

                    break;
                case 2:
                    //TIPO DE OJOS
                    alturaTexto = (pGuiGraphics.guiHeight() / 2);
                    anchoTexto = 84;

                    //TIPO DE CUERPO
                    if (cap.getIntValue("bodytype") == 0) {
                        drawStringWithBorder2(pGuiGraphics, font, Component.literal("Default"), anchoTexto, alturaTexto - 29, 0xFFFFFF);
                    } else if (cap.getIntValue("bodytype") == 1) {
                        drawStringWithBorder2(pGuiGraphics, font, Component.literal("Custom"), anchoTexto, alturaTexto - 29, 0xFFFFFF);

                    }
                    //TIPO DE CABELLO EN ESTE CASO OREJAS PARA EL NAMEK
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 2, 0, 0, 73, 15);
                    RenderSystem.disableBlend();
                    //HAIR TITULO
                    anchoTexto = 84;
                    drawStringWithBorder2(pGuiGraphics,font,Component.translatable("dmz.ccreation.hairs"),anchoTexto, alturaTexto + 2,0xFF9B9B);

                    if (cap.getIntValue("hairid") == 0) {
                        drawStringWithBorder2(pGuiGraphics, font, Component.literal("Default"), anchoTexto, alturaTexto + 18, 0xFFFFFF);
                    } else if (cap.getIntValue("hairid") == 1) {
                        drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto + 18, 0xFFFFFF);
                    } else if (cap.getIntValue("hairid") == 2) {
                        drawStringWithBorder2(pGuiGraphics, font, Component.literal("None"), anchoTexto, alturaTexto + 18, 0xFFFFFF);
                    }

                    break;
                case 3:
                    //TIPO DE CUERPO
                    alturaTexto = (pGuiGraphics.guiHeight() / 2);

                    anchoTexto = 84;
                    drawStringWithBorder2(pGuiGraphics, font, Component.translatable("dmz.ccreation.biotype"), anchoTexto, alturaTexto - 28, 0xFFFFFF);

                    break;
                case 4:
                    //TIPO DE OJOS
                    alturaTexto = (pGuiGraphics.guiHeight() / 2);

                    anchoTexto = 64;
                    if(cap.getIntValue("bodytype") == 0){
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto - 26, 0xFFFFFF);
                    } else if(cap.getIntValue("bodytype") == 1){
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto - 26, 0xFFFFFF);
                    } else if(cap.getIntValue("bodytype") == 2){
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 03"), anchoTexto, alturaTexto - 26, 0xFFFFFF);
                    }

                    break;
                case 5:
                    //TIPO DE CUERPO
                    if (cap.getIntValue("bodytype") == 0) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Default"), 67, alturaTexto - 29, 0xFFFFFF);
                    } else if (cap.getIntValue("bodytype") == 1) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Custom"), 67, alturaTexto - 29, 0xFFFFFF);
                    }

                    //GENERO TIPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 2, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    anchoTexto = 84;
                    drawStringWithBorder2(pGuiGraphics,font,Component.translatable("dmz.ccreation.gender"),anchoTexto, alturaTexto + 2,0xFF9B9B);

                    switch (cap.getStringValue("gender")) {
                        case "male":
                            anchoTexto = 84;
                            drawStringWithBorder2(pGuiGraphics, font, Component.literal("Male"), anchoTexto, alturaTexto + 18, 0xFFFFFF, 0x2133A6);
                            break;
                        case "female":
                            anchoTexto = 84;
                            drawStringWithBorder2(pGuiGraphics, font, Component.literal("Female"), anchoTexto, alturaTexto + 18, 0xFFFFFF, 0xFC63D9);
                            break;
                    }

                    //HAIR COLOR
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto + 34, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    //HAIR TITULO
                    anchoTexto = 84;
                    drawStringWithBorder2(pGuiGraphics,font,Component.translatable("dmz.ccreation.hairs"),anchoTexto, alturaTexto + 38,0xFF9B9B);

                    anchoTexto = 84;
                    if (cap.getStringValue("gender").equals("male")) {
                        if (cap.getIntValue("hairid") == 0) {
                            drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                        } else if (cap.getIntValue("hairid") == 1)
                            drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    } else {
                        if (cap.getIntValue("hairid") == 1) {
                            drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                        } else if (cap.getIntValue("hairid") == 2) {
                            drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                        } else if (cap.getIntValue("hairid") == 3) {
                            drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 03"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                        } else if (cap.getIntValue("hairid") == 4) {
                            drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 04"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                        } else if (cap.getIntValue("hairid") == 5) {
                            drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 05"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                        } else if (cap.getIntValue("hairid") == 6) {
                            drawStringWithBorder2(pGuiGraphics, font, Component.literal("Type 06"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                        }
                    }

                    break;
                default:
                    break;
            }
        });

        personajesMenu(pGuiGraphics);


    }

    public void pagina2(GuiGraphics pGuiGraphics) {

        //MENU CARACTERISTICAS
        alturaTexto = (pGuiGraphics.guiHeight() / 2);
        anchoTexto = 10;
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        pGuiGraphics.blit(menu1, anchoTexto, alturaTexto - 110, 0, 0, 148, 222);
        //Aca sera la info
        pGuiGraphics.blit(menu1, this.width - 158, alturaTexto - 110, 0, 0, 148, 222);

        RenderSystem.disableBlend();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            //CLASE DEL JUGADOR
            alturaTexto = (pGuiGraphics.guiHeight() / 2);
            anchoTexto = 51;
            pGuiGraphics.drawString(font, Component.literal("Class Type").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 88, 0xFF9B9B);

            if(cap.getStringValue("class").equals("warrior")){
                anchoTexto = 65;
                drawStringWithBorder(pGuiGraphics, font, Component.literal("Warrior"), anchoTexto, alturaTexto - 72, 0xFC4E2B);

            } else {
                anchoTexto = 60;
                drawStringWithBorder(pGuiGraphics, font, Component.literal("Spiritualist"), anchoTexto, alturaTexto - 72, 0x2BFCFC);

            }

            //ALINEAMIENTO
            anchoTexto = 47;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, texto);
            pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 56, 0, 0, 73, 15);
            RenderSystem.disableBlend();

            anchoTexto = 56;
            pGuiGraphics.drawString(font, Component.literal("Alignment").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 52, 0xFFDEDE);

            if(cap.getIntValue("alignment") > 60){
                anchoTexto = 70;
                drawStringWithBorder(pGuiGraphics, font, Component.literal("Good"), anchoTexto, alturaTexto - 35, 0x1EFFD9, 0x1E6CFF);

            } else if (cap.getIntValue("alignment") > 40){
                anchoTexto = 67;
                drawStringWithBorder(pGuiGraphics, font, Component.literal("Neutral"), anchoTexto, alturaTexto - 35, 0xeaa8fe,0x561f66);

            } else {
                anchoTexto = 74;
                drawStringWithBorder(pGuiGraphics, font, Component.literal("Evil"), anchoTexto, alturaTexto - 35, 0xFF3D72, 0xF61414);
            }

            //COLOR DE KI
            anchoTexto = 47;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, texto);
            pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 16, 0, 0, 73, 15);
            RenderSystem.disableBlend();

            anchoTexto = 52;
            pGuiGraphics.drawString(font, Component.literal("Aura Color").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 12, 0xFFCA9B);

            //INFO FINAL
            anchoTexto = this.width-118;
            pGuiGraphics.drawString(font, Component.literal("Information").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 88, 0xfd6644);

            anchoTexto = this.width-137;
            drawStringWithBorder(pGuiGraphics,font, Component.literal("Race:").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 72, 0xFFFFFF);
            drawStringWithBorder(pGuiGraphics,font, Component.literal("Class:").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 60, 0xFFFFFF);

            anchoTexto = this.width-95;
            if(cap.getIntValue("race") == 0){
                drawStringWithBorder(pGuiGraphics, font, Component.translatable("dmz.races.name.human"), anchoTexto, alturaTexto - 72, 0x31EAFF);
            } else if(cap.getIntValue("race") == 1){
                drawStringWithBorder(pGuiGraphics, font, Component.translatable("dmz.races.name.saiyan"), anchoTexto, alturaTexto - 72, 0xFFBA35);
            } else if(cap.getIntValue("race") == 2){
                drawStringWithBorder(pGuiGraphics, font, Component.translatable("dmz.races.name.namek"), anchoTexto, alturaTexto - 72, 0x378942);
            } else if(cap.getIntValue("race") == 3){
                drawStringWithBorder(pGuiGraphics, font, Component.translatable("dmz.races.name.bioandroid"), anchoTexto, alturaTexto - 72, 0x72DA58);
            } else if(cap.getIntValue("race") == 4){
                drawStringWithBorder(pGuiGraphics, font, Component.translatable("dmz.races.name.colddemon"), anchoTexto, alturaTexto - 72, 0xAC1BEC);
            } else if(cap.getIntValue("race") == 5){
                drawStringWithBorder(pGuiGraphics, font, Component.translatable("dmz.races.name.majin"), anchoTexto, alturaTexto - 72, 0xFE7FF4);
            }

            anchoTexto = this.width-95;

            if(cap.getStringValue("class").equals("warrior")){
                drawStringWithBorder(pGuiGraphics, font, Component.literal("Warrior"), anchoTexto, alturaTexto - 60, 0xFC4E2B);

            } else {
                drawStringWithBorder(pGuiGraphics, font, Component.literal("Spiritualist"), anchoTexto, alturaTexto - 60, 0x2BFCFC);

            }

            //STATS
            anchoTexto = this.width-120;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, texto);
            pGuiGraphics.blit(texto, anchoTexto - 2, alturaTexto - 44, 0, 0, 73, 15);
            RenderSystem.disableBlend();

            pGuiGraphics.drawString(font, Component.literal("Initial Stats").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 40, 0x39c3ff);

            //ATRIBUTOS
            anchoTexto = this.width-139;
            alturaTexto = (pGuiGraphics.guiHeight() / 2) - 25;
            drawStringWithBorder(pGuiGraphics,font, Component.literal("Damage:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto, 0xFFFFFF);
            drawStringWithBorder(pGuiGraphics,font, Component.literal("Defense:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 12, 0xFFFFFF);
            drawStringWithBorder(pGuiGraphics,font, Component.literal("Stamina:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 24, 0xFFFFFF);
            drawStringWithBorder(pGuiGraphics,font, Component.literal("Health:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 36, 0xFFFFFF);
            drawStringWithBorder(pGuiGraphics,font, Component.literal("Ki Damage:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 48, 0xFFFFFF);
            drawStringWithBorder(pGuiGraphics,font, Component.literal("Max Ki:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 60, 0xFFFFFF);

            var strMax = dmzdatos.calcStrength(cap); var defMax = dmzdatos.calcDefense(cap, minecraft.player);
            var conMax = dmzdatos.calcConstitution(cap); var stmMax = dmzdatos.calcStamina(cap);
            var KPWMax = dmzdatos.calcKiPower(cap); var enrMax = dmzdatos.calcEnergy(cap);

            drawStringWithBorder(pGuiGraphics, font, Component.literal(String.valueOf(strMax)), this.width-67, alturaTexto, 0xfdbf26);
            drawStringWithBorder(pGuiGraphics, font, Component.literal(String.valueOf(defMax)), this.width-67, alturaTexto + 12, 0xfdbf26);
            drawStringWithBorder(pGuiGraphics, font, Component.literal(String.valueOf(stmMax)), this.width-67, alturaTexto + 24, 0xfdbf26);
            drawStringWithBorder(pGuiGraphics, font, Component.literal(String.valueOf(conMax)), this.width-67, alturaTexto + 36, 0xfdbf26);
            drawStringWithBorder(pGuiGraphics, font, Component.literal(String.valueOf(KPWMax)), this.width-67, alturaTexto + 48, 0xfdbf26);
            drawStringWithBorder(pGuiGraphics, font, Component.literal(String.valueOf(enrMax)), this.width-67, alturaTexto + 60, 0xfdbf26);


        });

        personajesMenu(pGuiGraphics);

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
    public static void drawStringWithBorder2(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {
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
    public static void drawStringWithBorder2(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto) {
        drawStringWithBorder2(guiGraphics, font, texto, x, y, ColorTexto, 0);
    }

    public static void renderEntityInInventoryFollowsAngle(GuiGraphics guiGraphics, int x, int y, int scale, float angleXComponent, float angleYComponent, LivingEntity livingEntity) {
        Quaternionf quaternionf = (new Quaternionf()).rotateZ(3.1415927F);
        Quaternionf quaternionf1 = (new Quaternionf()).rotateX(angleYComponent * 20.0F * 0.017453292F);
        quaternionf.mul(quaternionf1);

        // Guardar las rotaciones actuales de la entidad
        float f2 = livingEntity.yBodyRot;
        float f3 = livingEntity.getYRot();
        float f4 = livingEntity.getXRot();
        float f5 = livingEntity.yHeadRotO;
        float f6 = livingEntity.yHeadRot;

        // Ajustar la rotación del cuerpo y de la cabeza
        livingEntity.yBodyRot = 180.0F + angleXComponent * 20.0F;
        livingEntity.yHeadRot = livingEntity.yBodyRot;
        livingEntity.yHeadRotO = livingEntity.yBodyRot;

        // Renderizar la entidad
        renderEntityInInv(guiGraphics, x, y, scale, quaternionf, quaternionf1, livingEntity);

        // Restaurar las rotaciones originales de la entidad
        livingEntity.yBodyRot = f2;
        livingEntity.setYRot(f3);
        livingEntity.setXRot(f4);
        livingEntity.yHeadRotO = f5;
        livingEntity.yHeadRot = f6;
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

    private void clearAllButtons() {
        for (ColorButton2 button : botonColorDefecto) {
            this.removeWidget(button);
        }
        botonColorDefecto.clear();
    }

    public void panoramas(GuiGraphics graphics, float partialtick){

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {
            var race = cap.getIntValue("race");

            if(race == 0){
                this.customPanorama.render(partialtick, 1.0f);

            }else if(race == 1){
                this.panoramaSai.render(partialtick, 1.0f);

            }else if(race == 2){
                this.panoramaNam.render(partialtick, 1.0f);

            }else if(race == 3){
                this.panoramaBio.render(partialtick, 1.0f);

            }else if(race == 4){
                this.panoramaCold.render(partialtick, 1.0f);

            }else {
                this.panoramaBuu.render(partialtick, 1.0f);

            }
        });

    }

    public void personajesMenu(GuiGraphics pGuiGraphics){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            if(cap.getIntValue("race") == 0){//HUMANO
                if(cap.getIntValue("bodytype") == 0){
                    if(Minecraft.getInstance().player.getModelName().equals("default")){
                        LivingEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), this.minecraft.level);

                        renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, this.height/2 + 80, 82, angleXComponent, 0, avatar);

                    }else {
                        LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), this.minecraft.level);

                        renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, this.height/2 + 80, 82, angleXComponent, 0, avatar);
                    }

                } else {
                    if (cap.getStringValue("gender").equals("male")){
                        LivingEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), this.minecraft.level);

                        renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, this.height/2 + 80, 82, angleXComponent, 0, avatar);

                    }else {
                        LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), this.minecraft.level);

                        renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, this.height/2 + 80, 82, angleXComponent, 0, avatar);
                    }
                }

            }else if(cap.getIntValue("race") == 1){ //SAIYAN
                if(cap.getIntValue("bodytype") == 0){
                    if(Minecraft.getInstance().player.getModelName().equals("default")){
                        LivingEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), this.minecraft.level);

                        renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, this.height/2 + 80, 82, angleXComponent, 0, avatar);

                    }else {
                        LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), this.minecraft.level);

                        renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, this.height/2 + 80, 82, angleXComponent, 0, avatar);
                    }

                } else {
                    if (cap.getStringValue("gender").equals("male")){
                        LivingEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), this.minecraft.level);

                        renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, this.height/2 + 80, 82, angleXComponent, 0, avatar);

                    }else {
                        LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), this.minecraft.level);

                        renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, this.height/2 + 80, 82, angleXComponent, 0, avatar);
                    }
                }

            }else if(cap.getIntValue("race") == 2){ //NAMEK
                LivingEntity avatar = new FPNamekianEntity(MainEntity.FP_NAMEK.get(), this.minecraft.level);

                renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, this.height/2 + 80, 82, angleXComponent, 0, avatar);

            }else if(cap.getIntValue("race") == 3){ //BIOANDROIDE
                LivingEntity avatar = new FPBioAndroidEntity(MainEntity.FP_BIOANDROIDE.get(), this.minecraft.level);

                renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, this.height/2 + 80, 82, angleXComponent, 0, avatar);

            }else if(cap.getIntValue("race") == 4){ //NARCO OSEA ARCO JEJE
                LivingEntity avatar = new FPDemonColdEntity(MainEntity.FP_DEMONCOLD.get(), this.minecraft.level);

                renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, this.height/2 + 80, 82, angleXComponent, 0, avatar);

            }else { // MAJIN
                if (cap.getStringValue("gender").equals("male")){
                    LivingEntity avatar = new FPMajinGordEntity(MainEntity.FP_MAJINGORDO.get(), this.minecraft.level);

                    renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, this.height/2 + 80, 82, angleXComponent, 0, avatar);

                }else {
                    LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), this.minecraft.level);

                    renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, this.height/2 + 80, 82, angleXComponent, 0, avatar);
                }
            }


        });

    }


}