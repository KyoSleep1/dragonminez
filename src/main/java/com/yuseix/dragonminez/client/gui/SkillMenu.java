package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.CustomButtons;
import com.yuseix.dragonminez.client.gui.buttons.DMZGuiButtons;
import com.yuseix.dragonminez.client.gui.buttons.SwitchButton;
import com.yuseix.dragonminez.client.gui.buttons.TextButton;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.config.races.*;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.C2S.SkillActivateC2S;
import com.yuseix.dragonminez.network.C2S.ZPointsC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.stats.skills.DMZSkill;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class SkillMenu extends Screen {

    private static final ResourceLocation menucentro = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menulargo2.png");
    private static final ResourceLocation menuinfo = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menulargomitad.png");

    private static boolean infoMenu = false;
    private static String skillsId = "";
    private int alturaTexto, anchoTexto;

    private final List<AbstractWidget> skillButtons = new ArrayList<>();
    private final List<AbstractWidget> botonesArmas = new ArrayList<>();;
    private List<DMZGuiButtons> botonesMenus = new ArrayList<>();

    private CustomButtons infoButton, deleteButton, armasBoton, passiveButton;
    private DMZGuiButtons menuButton;
    private TextButton upgradeButton;
    private SwitchButton switchButton;

    public SkillMenu() {
        super(Component.empty());
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void tick() {
        super.tick();
        botonesMenus();
        botonesSkills();
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        menuPaneles(pGuiGraphics);
        menuSkills(pGuiGraphics);


        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

    }

    public void menuPaneles(GuiGraphics guiGraphics){

        if(infoMenu){
            alturaTexto = (this.height - 168)/2;
            anchoTexto = ((this.width - 250)/2) - 72;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            guiGraphics.blit(menucentro, anchoTexto, alturaTexto, 0, 0, 250, 168);

            anchoTexto = ((this.width - 250)/2) + 180;
            guiGraphics.blit(menuinfo, anchoTexto, alturaTexto, 0, 0, 145, 168);

            int startX = ((this.width - 250) / 2 + 30) - 72;
            int startY = (this.height - 168) / 2 + 18;
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.level"), startX, startY, 0xe6fffd);
            startX = ((this.width - 250) / 2 + 100) - 72;
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.skill"), startX, startY, 0xffc134);
            startX = ((this.width - 250) / 2 + 170) - 72;
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.active"), startX, startY, 0x20e0ff);

            //Cargar el menu 2 con la informacion completa de las habilidades
            menuSkillsDesc(guiGraphics);

        } else {
            alturaTexto = (this.height - 168)/2;
            anchoTexto = (this.width - 250)/2;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            guiGraphics.blit(menucentro, anchoTexto, alturaTexto, 0, 0, 250, 168);

            int startX = (this.width - 250) / 2 + 30;
            int startY = (this.height - 168) / 2 + 18;
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.level"), startX, startY, 0xe6fffd);
            startX = (this.width - 250) / 2 + 100;
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.skill"), startX, startY, 0xffc134);
            startX = (this.width - 250) / 2 + 170;
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.active"), startX, startY, 0x20e0ff);

        }

        RenderSystem.disableBlend();
    }

    public void botonesMenus(){
        this.removeWidget(infoButton);

        for (DMZGuiButtons boton : botonesMenus) {
            this.removeWidget(boton);
        }
        botonesMenus.clear();

        alturaTexto = (this.height + 168)/2;
        anchoTexto = this.infoMenu ? (this.width/2) - 72 : this.width/2;

        if (this.minecraft.level.isClientSide) {

            Player player = this.minecraft.player;
            botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 85, alturaTexto, "stats", Component.empty(), wa -> {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
                    if (playerstats.isCompactMenu()) {
                        this.minecraft.setScreen(new AttributesMenu2());
                    } else {
                        this.minecraft.setScreen(new AttributesMenu(Component.translatable("menu.title.dragonminez.menuzmzmzm")));
                    }
                });
            })));

            botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 55, alturaTexto, "skills", Component.empty(), wa -> {
                // Es este menú, no hacer nada.
            })));

            botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 25, alturaTexto, "transf", Component.empty(), wa -> {
                // Agregar menú de transformación
                // this.minecraft.setScreen(new TransfMenu());
            })));

            botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 5, alturaTexto, "storyline", Component.empty(), wa -> {
                // Agregar menú de historia
                // this.minecraft.setScreen(new StoryMenu());
            })));

            botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 35, alturaTexto, "kitech", Component.empty(), wa -> {
                // Agregar menú de Ki Techniques
                // this.minecraft.setScreen(new KiTechMenu());
            })));

            botonesMenus.add(this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 65, alturaTexto, "settings", Component.empty(), wa -> {
                this.minecraft.setScreen(new ConfigMenu());
            })));
        }
    }

    private void botonesSkills(){

        Player player = this.minecraft.player;

        skillButtons.forEach(this::removeWidget);
        skillButtons.clear();

        botonesArmas.forEach(this::removeWidget);
        botonesArmas.clear();

        this.removeWidget(deleteButton);
        this.removeWidget(upgradeButton);
        this.removeWidget(menuButton);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {

            var tps = cap.getZpoints();

            Map<String, DMZSkill> skills = cap.getDMZSkills();

            int startX = (this.width - 250) / 2 + 13;
            int startY = (this.height - 168) / 2 + 45;
            int offsetY = 13; // Espacio vertical entre cada habilidad

            // Renderizar cada habilidad
            for (Map.Entry<String, DMZSkill> entry : skills.entrySet()) {
                String skillId = entry.getKey();
                DMZSkill skill = entry.getValue();
                double mult = DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get();
                int jumpCost = DMZGeneralConfig.JUMP_TP_COST_LEVELS.get();
                int flyCost = DMZGeneralConfig.FLY_TP_COST_LEVELS.get();
                int pUnlockCost = DMZGeneralConfig.POTUNLOCK_TP_COST_LEVELS.get();
                int meditationCost = DMZGeneralConfig.MEDITATION_TP_COST_LEVELS.get();
                int kiControlCost = DMZGeneralConfig.KI_CONTROL_TP_COST_LEVELS.get();
                int kiManipulationCost = DMZGeneralConfig.KI_MANIPULATION_TP_COST_LEVELS.get();

                switch (skillId) { //Aca pondremos que habilidades tendran el boton de activo y eso
                    case "passive":
                        break;
                    case "potential_unlock":
                        if(this.infoMenu){
                            if(skillId.equals(skillsId)){
                                // Subir de nivel
                                int currentLevel = skill.getLevel();
                                int maxLevel = 10; // maximo nivel

                                // Nivel, (Costo * Nivel * MultiplicadorTPS)
                                Map<Integer, Integer> levelCosts = Map.of(
                                        2, (int) (pUnlockCost * 2 * mult),
                                        3, (int) (pUnlockCost * 3 * mult),
                                        4, (int) (pUnlockCost * 4 * mult),
                                        5, (int) (pUnlockCost * 5 * mult),
                                        6, (int) (pUnlockCost * 6 * mult),
                                        7, (int) (pUnlockCost * 7 * mult),
                                        8, (int) (pUnlockCost * 8 * mult),
                                        9, (int) (pUnlockCost * 9 * mult),
                                        10, (int) (pUnlockCost * 10 * mult)
                                );

                                if (currentLevel < maxLevel) {
                                    int nextLevel = currentLevel + 1;
                                    int cost = levelCosts.getOrDefault(nextLevel, Integer.MAX_VALUE); // Obtener el costo para el siguiente nivel

                                    if (tps >= cost) { // Comprueba si el costo se cumple
                                        this.upgradeButton = (TextButton) this.addRenderableWidget(new TextButton(startX + 195, alturaTexto-40, Component.translatable("dmz.skills.upgrade", cost), wa -> {
                                            ModMessages.sendToServer(new SkillActivateC2S("setlevel", skillId, nextLevel));
                                            ModMessages.sendToServer(new ZPointsC2S(1, cost));
                                            this.removeWidget(upgradeButton);
                                        }));
                                    }
                                }
                            }

                        }
                        break;
                    case "jump":
                        //boton switch aca
                        switchButton = new SwitchButton(skill.isActive(), this.infoMenu ? startX + 147 - 72 : startX + 147, startY - 2, Component.empty(), btn -> {
                            boolean newState = !skill.isActive();
                            int newStateint = newState ? 1 : 0;
                            ModMessages.sendToServer(new SkillActivateC2S("active",skillId, newStateint));
                        });

                        this.addRenderableWidget(switchButton);
                        skillButtons.add(switchButton);

                        if(this.infoMenu){
                            if(skillId.equals(skillsId)){
                                // Subir de nivel
                                int currentLevel = skill.getLevel();
                                int maxLevel = 10; // maximo nivel

                                // Nivel, (Costo * Nivel * MultiplicadorTPS)
                                Map<Integer, Integer> levelCosts = Map.of(
                                        2, (int) (jumpCost * 2 * mult),
                                        3, (int) (jumpCost * 3 * mult),
                                        4, (int) (jumpCost * 4 * mult),
                                        5, (int) (jumpCost * 5 * mult),
                                        6, (int) (jumpCost * 6 * mult),
                                        7, (int) (jumpCost * 7 * mult),
                                        8, (int) (jumpCost * 8 * mult),
                                        9, (int) (jumpCost * 9 * mult),
                                        10, (int) (jumpCost * 10 * mult)
                                );

                                if (currentLevel < maxLevel) {
                                    int nextLevel = currentLevel + 1;
                                    int cost = levelCosts.getOrDefault(nextLevel, Integer.MAX_VALUE); // Obtener el costo para el siguiente nivel

                                    if (tps >= cost) { // Comprueba si el costo se cumple
                                        this.upgradeButton = (TextButton) this.addRenderableWidget(new TextButton(startX + 195, alturaTexto-40, Component.translatable("dmz.skills.upgrade", cost), wa -> {
                                            ModMessages.sendToServer(new SkillActivateC2S("setlevel", skillId, nextLevel));
                                            ModMessages.sendToServer(new ZPointsC2S(1, cost));
                                            this.removeWidget(upgradeButton);
                                        }));
                                    }
                                }
                            }

                        }

                        break;
                    case "fly":
                        if(this.infoMenu){
                            if(skillId.equals(skillsId)){
                                // Subir de nivel
                                int currentLevel = skill.getLevel();
                                int maxLevel = 10; // maximo nivel

                                // Nivel, (Costo * Nivel * MultiplicadorTPS)
                                Map<Integer, Integer> levelCosts = Map.of(
                                        2, (int) (flyCost * 2 * mult),
                                        3, (int) (flyCost * 3 * mult),
                                        4, (int) (flyCost * 4 * mult),
                                        5, (int) (flyCost * 5 * mult),
                                        6, (int) (flyCost * 6 * mult),
                                        7, (int) (flyCost * 7 * mult),
                                        8, (int) (flyCost * 8 * mult),
                                        9, (int) (flyCost * 9 * mult),
                                        10, (int) (flyCost * 10 * mult)
                                );

                                if (currentLevel < maxLevel) {
                                    int nextLevel = currentLevel + 1;
                                    int cost = levelCosts.getOrDefault(nextLevel, Integer.MAX_VALUE); // Obtener el costo para el siguiente nivel

                                    if (tps >= cost) { // Comprueba si el costo se cumple
                                        this.upgradeButton = (TextButton) this.addRenderableWidget(new TextButton(startX + 195, alturaTexto-40, Component.translatable("dmz.skills.upgrade", cost), wa -> {
                                            ModMessages.sendToServer(new SkillActivateC2S("setlevel", skillId, nextLevel));
                                            ModMessages.sendToServer(new ZPointsC2S(1, cost));
                                            this.removeWidget(upgradeButton);
                                        }));
                                    }
                                }
                            }

                        }
                        break;
                    case "ki_control":
                        if(this.infoMenu){
                            if(skillId.equals(skillsId)){
                                // Subir de nivel
                                int currentLevel = skill.getLevel();
                                int maxLevel = 10; // maximo nivel

                                // Nivel, (Costo * Nivel * MultiplicadorTPS)
                                Map<Integer, Integer> levelCosts = Map.of(
                                        2, (int) (kiControlCost * 2 * mult),
                                        3, (int) (kiControlCost * 3 * mult),
                                        4, (int) (kiControlCost * 4 * mult),
                                        5, (int) (kiControlCost * 5 * mult),
                                        6, (int) (kiControlCost * 6 * mult),
                                        7, (int) (kiControlCost * 7 * mult),
                                        8, (int) (kiControlCost * 8 * mult),
                                        9, (int) (kiControlCost * 9 * mult),
                                        10, (int) (kiControlCost * 10 * mult)
                                );

                                if (currentLevel < maxLevel) {
                                    int nextLevel = currentLevel + 1;
                                    int cost = levelCosts.getOrDefault(nextLevel, Integer.MAX_VALUE); // Obtener el costo para el siguiente nivel

                                    if (tps >= cost) { // Comprueba si el costo se cumple
                                        this.upgradeButton = (TextButton) this.addRenderableWidget(new TextButton(startX + 195, alturaTexto-40, Component.translatable("dmz.skills.upgrade", cost), wa -> {
                                            ModMessages.sendToServer(new SkillActivateC2S("setlevel", skillId, nextLevel));
                                            ModMessages.sendToServer(new ZPointsC2S(1, cost));
                                            this.removeWidget(upgradeButton);
                                        }));
                                    }
                                }
                            }

                        }
                        break;
                    case "meditation":
                        if(this.infoMenu){
                            if(skillId.equals(skillsId)){
                                // Subir de nivel
                                int currentLevel = skill.getLevel();
                                int maxLevel = 10; // maximo nivel

                                // Nivel, (Costo * Nivel * MultiplicadorTPS)
                                Map<Integer, Integer> levelCosts = Map.of(
                                        2, (int) (meditationCost * 2 * mult),
                                        3, (int) (meditationCost * 3 * mult),
                                        4, (int) (meditationCost * 4 * mult),
                                        5, (int) (meditationCost * 5 * mult),
                                        6, (int) (meditationCost * 6 * mult),
                                        7, (int) (meditationCost * 7 * mult),
                                        8, (int) (meditationCost * 8 * mult),
                                        9, (int) (meditationCost * 9 * mult),
                                        10, (int) (meditationCost * 10 * mult)
                                );

                                if (currentLevel < maxLevel) {
                                    int nextLevel = currentLevel + 1;
                                    int cost = levelCosts.getOrDefault(nextLevel, Integer.MAX_VALUE); // Obtener el costo para el siguiente nivel

                                    if (tps >= cost) { // Comprueba si el costo se cumple
                                        this.upgradeButton = (TextButton) this.addRenderableWidget(new TextButton(startX + 195, alturaTexto-40, Component.translatable("dmz.skills.upgrade", cost), wa -> {
                                            ModMessages.sendToServer(new SkillActivateC2S("setlevel", skillId, nextLevel));
                                            ModMessages.sendToServer(new ZPointsC2S(1, cost));
                                            this.removeWidget(upgradeButton);
                                        }));
                                    }
                                }
                            }

                        }
                        break;
                    case "ki_manipulation":
                        //boton switch aca
                        switchButton = new SwitchButton(skill.isActive(), this.infoMenu ? startX + 147 - 72 : startX + 147, startY - 2, Component.empty(), btn -> {
                            boolean newState = !skill.isActive();
                            int newStateint = newState ? 1 : 0;
                            ModMessages.sendToServer(new SkillActivateC2S("active",skillId, newStateint));
                        });
                        armasBoton = new CustomButtons("igual", this.infoMenu ? startX + 170 - 72 : startX + 170, startY - 2, Component.empty(), btn -> {
                            if(cap.getKiWeaponId().equals("sword")){
                                ModMessages.sendToServer(new CharacterC2S("dmzskiweapon",0));
                            } else if(cap.getKiWeaponId().equals("scythe")){
                                ModMessages.sendToServer(new CharacterC2S("dmzskiweapon",1));
                            } else {
                                ModMessages.sendToServer(new CharacterC2S("dmzskiweapon",2));
                            }

                        });

                        this.addRenderableWidget(armasBoton);
                        botonesArmas.add(armasBoton);

                        this.addRenderableWidget(switchButton);
                        skillButtons.add(switchButton);

                        if(this.infoMenu){
                            if(skillId.equals(skillsId)){
                                // Subir de nivel
                                int currentLevel = skill.getLevel();
                                int maxLevel = 10; // maximo nivel

                                // Nivel, (Costo * Nivel * MultiplicadorTPS)
                                Map<Integer, Integer> levelCosts = Map.of(
                                        2, (int) (kiManipulationCost * 2 * mult),
                                        3, (int) (kiManipulationCost * 3 * mult),
                                        4, (int) (kiManipulationCost * 4 * mult),
                                        5, (int) (kiManipulationCost * 5 * mult),
                                        6, (int) (kiManipulationCost * 6 * mult),
                                        7, (int) (kiManipulationCost * 7 * mult),
                                        8, (int) (kiManipulationCost * 8 * mult),
                                        9, (int) (kiManipulationCost * 9 * mult),
                                        10, (int) (kiManipulationCost * 10 * mult)
                                );

                                if (currentLevel < maxLevel) {
                                    int nextLevel = currentLevel + 1;
                                    int cost = levelCosts.getOrDefault(nextLevel, Integer.MAX_VALUE); // Obtener el costo para el siguiente nivel

                                    if (tps >= cost) { // Comprueba si el costo se cumple
                                        this.upgradeButton = (TextButton) this.addRenderableWidget(new TextButton(startX + 195, alturaTexto-40, Component.translatable("dmz.skills.upgrade", cost), wa -> {
                                            ModMessages.sendToServer(new SkillActivateC2S("setlevel", skillId, nextLevel));
                                            ModMessages.sendToServer(new ZPointsC2S(1, cost));
                                            this.removeWidget(upgradeButton);
                                        }));
                                    }
                                }
                            }

                        }
                        break;
                    default:
                        // Si no necesita botones extra, no se hace nada
                        break;
                }

                if (this.infoMenu && skillId.equals(this.skillsId)) {
                    this.deleteButton = (CustomButtons) this.addRenderableWidget(new CustomButtons("delete", startX + 285, alturaTexto - 30, Component.empty(), wa -> {
                        // Cambiar la pantalla solo en el cliente
                        ModMessages.sendToServer(new SkillActivateC2S("remove", skillId, 0));
                        this.removeWidget(deleteButton);
                    }));
                }

                // Crear un botón base para todos
                CustomButtons button = new CustomButtons("info", this.infoMenu ? startX + 200 - 72 : startX + 200, startY - 2, Component.empty(), btn -> {
                    this.infoMenu = !infoMenu; // Alternar infoMenu
                    this.skillsId = skillId;
                });

                CustomButtons passiveButton = new CustomButtons("info", this.infoMenu ? startX + 200 - 72 : startX + 200, startY - 15, Component.empty(), btn -> {
                    this.infoMenu = !infoMenu; // Alternar infoMenu
                    this.skillsId = "passive";
                });
                this.addRenderableWidget(button);
                this.addRenderableWidget(passiveButton);
                skillButtons.add(button);
                skillButtons.add(passiveButton);

                // Mover hacia abajo para la próxima habilidad
                startY += offsetY;
            }

        });
    }

    private void menuSkills(GuiGraphics guiGraphics) {
        // Obtener las habilidades desde la capability del jugador
        Player player = this.minecraft.player;

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
            Map<String, DMZSkill> skills = cap.getDMZSkills();

            int startX = (this.width - 250) / 2 + 15;
            int startY = (this.height - 168) / 2 + 45;
            int offsetY = 13; // Espacio vertical entre cada habilidad

            // Pasiva
            drawStringWithBorder(guiGraphics, this.font, Component.literal("1"), this.infoMenu ? startX + 16 - 72 : startX + 16, startY - 13, 0xFFFFFF);
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skill.passive.name"), this.infoMenu ? startX + 85 - 72 : startX + 85, startY - 13, 0xFFFFFF);
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.on"), this.infoMenu ? startX + 155 - 72: startX + 155, startY - 13, 0x60fb58);

            // Renderizar cada habilidad
            for (Map.Entry<String, DMZSkill> entry : skills.entrySet()) {
                String skillId = entry.getKey();
                DMZSkill skill = entry.getValue();

                switch (skillId) {
                    case "potential_unlock":
                        // Mostrar el texto de la habilidad
                        drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(skill.getLevel())), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xFFFFFF);
                        //Nombre de la habilidad
                        //guiGraphics.drawString(this.font, Component.translatable(skill.getName().getString()).withStyle(ChatFormatting.BOLD), startX + 40, startY, 0xFFFFFF);
                        drawStringWithBorder(guiGraphics, this.font, Component.translatable(skill.getName()), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xFFFFFF);
                        //Activo o inactivo
                        if(skill.isActive()){
                            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.on"), this.infoMenu ? startX + 155 - 72: startX + 155, startY, 0x60fb58);
                        } else {
                            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.off"), this.infoMenu ? startX + 155 - 72: startX + 155, startY, 0xfb5858);
                        }
                        break;
                    case "jump":
                        drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(skill.getLevel())), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xFFFFFF);
                        drawStringWithBorder(guiGraphics, this.font, Component.translatable(skill.getName()), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xFFFFFF);
                        break;
                    case "fly":
                        drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(skill.getLevel())), this.infoMenu ? startX + 16 - 72: startX + 16, startY, 0xFFFFFF);
                        drawStringWithBorder(guiGraphics, this.font, Component.translatable(skill.getName()), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xFFFFFF);
                        //Activo o inactivo
                        if(skill.isActive()){
                            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.on"), this.infoMenu ? startX + 155 - 72: startX + 155, startY, 0x60fb58);
                        } else {
                            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.off"), this.infoMenu ? startX + 155 - 72: startX + 155, startY, 0xfb5858);
                        }
                        break;
                    case "ki_manipulation":
                        drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(skill.getLevel())), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xFFFFFF);
                        drawStringWithBorder(guiGraphics, this.font, Component.translatable(skill.getName()), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xFFFFFF);
                        break;
                    default:
                        drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(skill.getLevel())), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xFFFFFF);
                        drawStringWithBorder(guiGraphics, this.font, Component.translatable(skill.getName()), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xFFFFFF);
                        //Activo o inactivo
                        if(skill.isActive()){
                            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.on"), this.infoMenu ? startX + 155 - 72: startX + 155, startY, 0x60fb58);
                        } else {
                            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.off"), this.infoMenu ? startX + 155 - 72: startX + 155, startY, 0xfb5858);
                        }
                        // Si no necesita botones extra, no se hace nada
                        break;
                }

                // Mover hacia abajo para la próxima habilidad
                startY += offsetY;
            }
        });
    }

    private void menuSkillsDesc(GuiGraphics guiGraphics) {
        // Obtener las habilidades desde la capability del jugador
        Player player = this.minecraft.player;

        if(infoMenu){
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                var race = cap.getRace();
                int humanPassive = DMZHumanConfig.KICHARGE_REGEN_BOOST.get();
                int zenkaiCant = DMZSaiyanConfig.ZENKAI_CANT.get() - cap.getZenkaiCount();
                int zenkaiHeal = DMZSaiyanConfig.ZENKAI_HEALTH_REGEN.get();
                int zenkaiBoost = DMZSaiyanConfig.ZENKAI_STAT_BOOST.get();
                int remainingTicks = cap.getSaiyanZenkaiTimer();
                int remainingMinutes = (remainingTicks / 1200); // 1200 ticks = 1 minuto
                int remainingSeconds = (remainingTicks / 20) % 60; // Convertimos a segundos y obtenemos los restantes
                int namekPassive = DMZNamekConfig.PASSIVE_REGEN.get();
                double colddemonPassive = DMZColdDemonConfig.TP_MULTIPLER_PASSIVE.get();
                int bioPassive1 = DMZBioAndroidConfig.HALF_HEALTH_LIFESTEAL.get();
                int bioPassive2 = DMZBioAndroidConfig.QUARTER_HEALTH_LIFESTEAL.get();
                double majinPassive = DMZMajinConfig.PASSIVE_HEALTH_REGEN.get();

                int startY = (this.height - 168) / 2 + 18;
                int startX = (this.width - 250) / 2 + 160;

                Map<String, DMZSkill> skills = cap.getDMZSkills();

                if (this.skillsId == "passive") {
                    drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skill.passive.name"), startX + 93, startY, 0xFFFFFF);
                    drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.type"), startX + 37, startY+ 13, 0xFFFFFF);
                    drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.skill2"), startX + 78, startY+ 13, 0xffc134);
                    drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.active"), startX + 37, startY+24, 0xFFFFFF);
                    drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.on"), startX + 78, startY+24, 0x60fb58);
                    drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.maxlevel"), startX + 90, startY+116, 0xffc134);

                    switch (race) {
                        case 0:
                            List<FormattedCharSequence> humanDesc = font.split(Component.translatable("dmz.skill.passive.desc.human", humanPassive).withStyle(ChatFormatting.AQUA), 120);
                            for (int i = 0; i < humanDesc.size(); i++) {
                                guiGraphics.drawString(font, humanDesc.get(i), startX + 37, (startY+36) + i * font.lineHeight, 0xFFFFFF);
                            }
                            break;
                        case 1:
                            List<FormattedCharSequence> saiyanDesc = font.split(Component.translatable("dmz.skill.passive.desc.saiyan", zenkaiHeal, zenkaiBoost, zenkaiCant, remainingMinutes, remainingSeconds).withStyle(ChatFormatting.AQUA), 120);
                            for (int i = 0; i < saiyanDesc.size(); i++) {
                                guiGraphics.drawString(font, saiyanDesc.get(i), startX + 37, (startY+36) + i * font.lineHeight, 0xFFFFFF);
                            }
                            break;
                        case 2:
                            List<FormattedCharSequence> namekianDesc = font.split(Component.translatable("dmz.skill.passive.desc.namek", namekPassive).withStyle(ChatFormatting.AQUA), 120);
                            for (int i = 0; i < namekianDesc.size(); i++) {
                                guiGraphics.drawString(font, namekianDesc.get(i), startX + 37, (startY+36) + i * font.lineHeight, 0xFFFFFF);
                            }
                            break;
                        case 3:
                            List<FormattedCharSequence> bioDesc = font.split(Component.translatable("dmz.skill.passive.desc.bio", bioPassive1, bioPassive2).withStyle(ChatFormatting.AQUA), 120);
                            for (int i = 0; i < bioDesc.size(); i++) {
                                guiGraphics.drawString(font, bioDesc.get(i), startX + 37, (startY+36) + i * font.lineHeight, 0xFFFFFF);
                            }
                            break;
                        case 4:
                            List<FormattedCharSequence> colddemonDesc = font.split(Component.translatable("dmz.skill.passive.desc.colddemon", colddemonPassive).withStyle(ChatFormatting.AQUA), 120);
                            for (int i = 0; i < colddemonDesc.size(); i++) {
                                guiGraphics.drawString(font, colddemonDesc.get(i), startX + 37, (startY+36) + i * font.lineHeight, 0xFFFFFF);
                            }
                            break;
                        case 5:
                            List<FormattedCharSequence> majinDesc = font.split(Component.translatable("dmz.skill.passive.desc.majin", majinPassive).withStyle(ChatFormatting.AQUA), 120);
                            for (int i = 0; i < majinDesc.size(); i++) {
                                guiGraphics.drawString(font, majinDesc.get(i), startX + 37, (startY+36) + i * font.lineHeight, 0xFFFFFF);
                            }
                            break;
                    }
                    return;
                }

                // Renderizar cada habilidad
                for (Map.Entry<String, DMZSkill> entry : skills.entrySet()) {
                    String skillId = entry.getKey();
                    DMZSkill skill = entry.getValue();

                    if(skillId.equals(this.skillsId)){
                        int currentLevel = skill.getLevel();
                        int maxLevel = 10; // maximo nivel
                        //Nombre de la habilidad
                        drawStringWithBorder(guiGraphics, this.font, Component.translatable(skill.getName()), startX + 93, startY, 0xFFFFFF);
                        //Tipo y aca pongo lo de skill
                        drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.type"), startX + 37, startY+ 13, 0xFFFFFF);
                        drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.skill2"), startX + 78, startY+ 13, 0xffc134);
                        //Aca pongo lo de nivel
                        drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.level"), startX + 37, startY+24, 0xFFFFFF);
                        drawStringWithBorder2(guiGraphics, this.font, Component.literal(String.valueOf(skill.getLevel())), startX + 78, startY+24, 0xFFFFFF);
                        //Activo o no
                        drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.active"), startX + 37, startY+36, 0xFFFFFF);
                        //Activo o inactivo
                        if(skill.isActive()){
                            drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.on"), startX + 78, startY+36, 0x60fb58);
                        } else {
                            drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.off"), startX + 78, startY+36, 0xfb5858);
                        }
                        //drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.cost"), startX + 37, startY+48, 0xFFFFFF);

                        //descripcion
                        List<FormattedCharSequence> lines = font.split(Component.translatable(skill.getDesc()), 120);
                        for (int i = 0; i < lines.size(); i++) {
                            guiGraphics.drawString(font, lines.get(i), startX + 37, (startY+48) + i * font.lineHeight, 0xFFFFFF);
                        }

                        if (currentLevel >= maxLevel) {
                            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.maxlevel"), startX + 90, startY+116, 0xffc134);
                        }

                    }

                }
            });
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
