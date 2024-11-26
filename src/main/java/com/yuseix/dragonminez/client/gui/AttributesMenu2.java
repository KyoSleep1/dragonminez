package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
import com.yuseix.dragonminez.client.gui.buttons.CustomButtons;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.network.C2S.StatsC2S;
import com.yuseix.dragonminez.network.C2S.ZPointsC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import com.yuseix.dragonminez.utils.TranslateManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class AttributesMenu2 extends Screen implements RenderEntityInv {

    private int alturaTexto;
    private int anchoTexto;
    private int multiplicadorTP = 1;

    private static final ResourceLocation menucentro = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menulargo2.png");


    private CustomButtons strBoton,defBoton,conBoton,pwrBoton,eneBoton, multiBoton;
    private DMZDatos dmzdatos = new DMZDatos();

    public AttributesMenu2() {
        super(Component.empty());
    }


    @Override
    public void init() {

        super.init();

    }

    @Override
    public void tick() {
        super.tick();

        botonesStats();
        //MenuInicio



    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(graphics);

        //Paneles del menu
        menuPaneles(graphics);

        menu1info(graphics, pMouseX, pMouseY);

        menu2info(graphics, pMouseX, pMouseY);

        menu0info(graphics, pMouseX, pMouseY);
        super.render(graphics, pMouseX, pMouseY, pPartialTick);




    }

    public void botonesStats(){
        this.removeWidget(strBoton);
        this.removeWidget(defBoton);
        this.removeWidget(conBoton);
        this.removeWidget(pwrBoton);
        this.removeWidget(eneBoton);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {
            var tps = playerstats.getZpoints();
            var str = playerstats.getStrength();
            var def = playerstats.getDefense();
            var con = playerstats.getConstitution();
            var kipower = playerstats.getKiPower();
            var energy = playerstats.getEnergy();

            anchoTexto = (this.width/2)-110;
            alturaTexto = (this.height / 2) -15;

            int maxStats = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
            var baseCost =  (int) Math.round(((((str + def + con + kipower + energy) / 2) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get())) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get());
            int adjustedCostSTR, adjustedCostDEF, adjustedCostCON, adjustedCostPWR, adjustedCostENE;
            var multCost = (int) Math.round((str + def + con + kipower + energy) /  2) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get();

            this.multiBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto-3, alturaTexto + 63,Component.empty(), wa -> {
                if(multiplicadorTP == 1){
                    multiplicadorTP *= 10;
                } else if(multiplicadorTP == 10){
                    multiplicadorTP *= 10;
                } else if(multiplicadorTP == 100){
                    multiplicadorTP = 1;
                }
            }));

            if (str >= (maxStats - multiplicadorTP)) {
                // Calcula los puntos restantes para alcanzar el límite de estadísticas y ajusta el costo en base a eso xd
                int puntosNecesarios = maxStats - str;
                adjustedCostSTR = (int) Math.round(baseCost * puntosNecesarios);
            } else {
                adjustedCostSTR = (int) Math.round((baseCost * multiplicadorTP) + multCost * multiplicadorTP);
            }

            if (def >= (maxStats - multiplicadorTP)) {
                int puntosNecesarios = maxStats - def;
                adjustedCostDEF = (int) Math.round(baseCost * puntosNecesarios);
            } else {
                adjustedCostDEF = (int) Math.round((baseCost * multiplicadorTP) + multCost * multiplicadorTP);
            }

            if (con >= (maxStats - multiplicadorTP)) {
                int puntosNecesarios = maxStats - con;
                adjustedCostCON = (int) Math.round(baseCost * puntosNecesarios);
            } else {
                adjustedCostCON = (int) Math.round((baseCost * multiplicadorTP) + multCost * multiplicadorTP);
            }

            if (kipower >= (maxStats - multiplicadorTP)) {
                int puntosNecesarios = maxStats - kipower;
                adjustedCostPWR = (int) Math.round(baseCost * puntosNecesarios);
            } else {
                adjustedCostPWR = (int) Math.round((baseCost * multiplicadorTP) + multCost * multiplicadorTP);
            }

            if (energy >= (maxStats - multiplicadorTP)) {
                int puntosNecesarios = maxStats - energy;
                adjustedCostENE = (int) Math.round(baseCost * puntosNecesarios);
            } else {
                adjustedCostENE = (int) Math.round((baseCost * multiplicadorTP) + multCost * multiplicadorTP);
            }

            if(tps >= baseCost){
                if (str < maxStats) {
                    this.strBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, adjustedCostSTR));
                        ModMessages.sendToServer(new StatsC2S(0,1 * multiplicadorTP));
                    }));}
                if (def < maxStats) {
                    this.defBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto + 12,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, adjustedCostDEF));
                        ModMessages.sendToServer(new StatsC2S(1,1 * multiplicadorTP));
                    }));}
                if (con < maxStats) {
                    this.conBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto + 24,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, adjustedCostCON));
                        ModMessages.sendToServer(new StatsC2S(2,1 * multiplicadorTP));
                    }));}
                if (kipower < maxStats) {
                    this.pwrBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto + 36,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, adjustedCostPWR));
                        ModMessages.sendToServer(new StatsC2S(3,1 * multiplicadorTP));
                    }));}
                if (energy < maxStats) {
                    this.eneBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto + 48,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, adjustedCostENE));
                        ModMessages.sendToServer(new StatsC2S(4,1 * multiplicadorTP));
                    }));}
        }});
    }

    public void menu0info(GuiGraphics guiGraphics, int mouseX, int mouseY){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            alturaTexto = (this.height / 2) - 65;
            anchoTexto = (this.width/2)+50;

            var playername = Minecraft.getInstance().player.getName().getString();
            var alignment = playerstats.getDmzAlignment();
            var raza = playerstats.getRace();
            int namecolor;
            if (alignment > 60) {
                namecolor = 0x63FFFF;
            } else if (alignment > 40) {
                namecolor = 0xeaa8fe;
            } else {
                namecolor = 0xFA5252;
            }
            drawStringWithBorder(guiGraphics, font, Component.literal(playername).withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, namecolor);

            if (mouseX >= anchoTexto - 10 && mouseX <= anchoTexto + 10 && mouseY >= alturaTexto && mouseY <= alturaTexto + font.lineHeight) {
                List<FormattedCharSequence> descriptionLines = new ArrayList<>();
                if (alignment > 60) {
                    descriptionLines.add(Component.translatable("stats.dmz.alignment_good", alignment).withStyle(ChatFormatting.YELLOW).getVisualOrderText());
                } else if (alignment > 40) {
                    descriptionLines.add(Component.translatable("stats.dmz.alignment_neutral", alignment).withStyle(ChatFormatting.YELLOW).getVisualOrderText());
                } else {
                    descriptionLines.add(Component.translatable("stats.dmz.alignment_evil", alignment).withStyle(ChatFormatting.YELLOW).getVisualOrderText());
                }
                guiGraphics.renderTooltip(font, descriptionLines, mouseX, mouseY);
            }

            alturaTexto = (this.height / 2) - 54;

            anchoTexto = (this.width/2)+50;

            if(raza == 0){

                drawStringWithBorder(guiGraphics, font, TranslateManager.Human, anchoTexto, alturaTexto, 0x177CFC);
            } else if(raza == 1){

                drawStringWithBorder(guiGraphics, font, TranslateManager.Saiyan, anchoTexto, alturaTexto, 0xFCB317);
            } else if(raza == 2){

                drawStringWithBorder(guiGraphics, font, TranslateManager.Namek, anchoTexto, alturaTexto, 0x186814);

            } else if(raza == 3){

                drawStringWithBorder(guiGraphics, font, TranslateManager.BioAndroid, anchoTexto, alturaTexto, 0x7DFF76);

            } else if(raza == 4){

                drawStringWithBorder(guiGraphics, font, TranslateManager.ColdDemon, anchoTexto, alturaTexto, 0x6A31EE);

            } else {

                drawStringWithBorder(guiGraphics, font, TranslateManager.Majin, anchoTexto, alturaTexto, 0xFF86FD);

            }
        });
    }

    public void menu1info(GuiGraphics graphics, int mouseX, int mouseY){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            var TPS = playerstats.getZpoints();

            var nivel = (playerstats.getStrength() + playerstats.getDefense() + playerstats.getConstitution()
                    + playerstats.getKiPower() + playerstats.getEnergy()) / 5;

            var clase = playerstats.getDmzClass();

            //VARIABLES:
            //NIVEL TPS
            anchoTexto = (this.width/2)-72;
            alturaTexto = (this.height / 2) - 64;
            drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(nivel)), anchoTexto, alturaTexto, 0xFFFFFF);
            drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(TPS)), anchoTexto, alturaTexto + 11, 0xFFE593);

            //FORMA
            drawStringWithBorder2(graphics, font, Component.literal("Base"), anchoTexto, alturaTexto + 22, 0xC7EAFC);
            //Clase
            if(clase.equals("Warrior")){
                drawStringWithBorder2(graphics, font,Component.literal("Warrior"), anchoTexto, alturaTexto + 33, 0xFC4E2B);
            }else {
                drawStringWithBorder2(graphics, font,Component.literal("Spiritualist"), anchoTexto, alturaTexto + 33, 0x2BFCFC);
            }

            var strdefault = playerstats.getStrength();
            var defdefault = playerstats.getDefense();
            var condefault = playerstats.getConstitution();
            var kipowerdefault = playerstats.getKiPower();
            var energydefault = playerstats.getEnergy();
            var raza = playerstats.getRace();
            var transf = playerstats.getDmzState();

            //Efectos
            var majinOn = playerstats.hasDMZPermaEffect("majin");
            var frutaOn = playerstats.hasDMZTemporalEffect("mightfruit");

            var baseCost =  (int) Math.round((((strdefault + defdefault + condefault + kipowerdefault + energydefault) / 2) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get()) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get());
            var multCost = (int) Math.round((strdefault + defdefault + condefault + kipowerdefault + energydefault) /  2) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get();
            int finalCost = (int) Math.round((baseCost * multiplicadorTP) + multCost * multiplicadorTP);

            var strcompleta = dmzdatos.calcularSTRCompleta(raza, transf, strdefault, majinOn, frutaOn);
            var defcompleta = dmzdatos.calcularDEFCompleta(raza, transf, defdefault, majinOn, frutaOn);
            var pwrcompleta = dmzdatos.calcularPWRCompleta(raza, transf, kipowerdefault, majinOn, frutaOn);

            var STRMulti = Math.round((dmzdatos.calcularMultiStat(raza, transf, "STR", majinOn, frutaOn)) * 100) / 100.0;
            var DEFMulti = Math.round((dmzdatos.calcularMultiStat(raza, transf, "DEF", majinOn, frutaOn)) * 100) / 100.0;
            var KIPOWERMulti = Math.round((dmzdatos.calcularMultiStat(raza, transf, "KIPOWER", majinOn, frutaOn)) * 100) / 100.0;
            var multiTotal = dmzdatos.calcularMultiTotal(raza, transf, majinOn, frutaOn);

            var isMultiOn = majinOn || frutaOn || transf > 0;
            var colorEnForma = isMultiOn ? 0xfebc0d : 0xFFD7AB;


            //WA
            Component STRReal = Component.empty()
                    .append(Component.literal(String.valueOf(strcompleta)))
                    .append(Component.literal(" x")
                            .append(Component.literal(String.valueOf(STRMulti)))
                    );
            Component DEFReal = Component.empty()
                    .append(Component.literal(String.valueOf(defcompleta)))
                    .append(Component.literal(" x")
                            .append(Component.literal(String.valueOf(DEFMulti)))
                    );
            Component PWRReal = Component.empty()
                    .append(Component.literal(String.valueOf(pwrcompleta)))
                    .append(Component.literal(" x")
                            .append(Component.literal(String.valueOf(KIPOWERMulti)))
                    );

            //Titulos
            anchoTexto = (this.width / 2) - 110;
            alturaTexto = (this.height / 2) - 64;

            graphics.drawString(font, Component.literal("Form:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 22, 0xD7FEF5);
            graphics.drawString(font, Component.literal("Class:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 33, 0xD7FEF5);

            String[] stats = { "Level", "TPs", "STR", "DEF", "CON", "PWR", "ENE", "TPC"};
            int[] colors = { 0xD7FEF5, 0xD7FEF5, 0xD71432, 0xD71432, 0xD71432, 0xD71432, 0xD71432, 0x2BFFE2};
            for (int i = 0; i < stats.length; i++) {
                String statKey = stats[i];
                int colores = colors[i];
                int yOffset;
                if (statKey.equals("Level") || statKey.equals("TPs")) {
                    yOffset = alturaTexto + (i * 11); // Valor fijo para "Level" y "TPs"
                } else if (statKey.equals("TPC")) {
                    alturaTexto = (this.height / 2) -14;
                    yOffset = (alturaTexto) + ((i-2) * 12) + 4; // Valor fijo para "TPC"
                } else {
                    alturaTexto = (this.height / 2) -14;
                    yOffset = (alturaTexto) + ((i-2) * 12); // Valor general para otras stats
                }
                if (statKey.equals("Level") || statKey.equals("TPs")) {
                    anchoTexto = (this.width / 2) - 110;
                } else if (statKey.equals("TPC")) {
                    anchoTexto = (this.width/2)-99;
                } else {
                    anchoTexto = (this.width/2)-95;
                }

                Component statComponent = Component.literal(statKey + ":")
                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(colores)).withBold(true));
                graphics.drawString(font, statComponent, anchoTexto, yOffset, colores);

                if (mouseX >= anchoTexto - 10 && mouseX <= anchoTexto + 25 && mouseY >= yOffset && mouseY <= yOffset + font.lineHeight) {
                    List<FormattedCharSequence> descriptionLines = new ArrayList<>();
                    FormattedText descriptionText = Component.translatable("stats.dmz." + statKey.toLowerCase());
                    List<FormattedCharSequence> lines = font.split(descriptionText, 250);
                    descriptionLines.addAll(lines);

                    FormattedText descText = Component.translatable("stats.dmz." + statKey.toLowerCase() + ".desc");
                    List<FormattedCharSequence> descLines = font.split(descText, 250);
                    descriptionLines.addAll(descLines);

                    if (statKey.equals("STR") && multiTotal > 1) {
                        descriptionLines.add(Component.translatable("stats.dmz.original", strdefault).withStyle(ChatFormatting.RED).getVisualOrderText());
                        descriptionLines.add(Component.translatable("stats.dmz.modified", strcompleta).withStyle(ChatFormatting.GOLD).getVisualOrderText());
                    } else if (statKey.equals("DEF") && multiTotal > 1) {
                        descriptionLines.add(Component.translatable("stats.dmz.original", defdefault).withStyle(ChatFormatting.RED).getVisualOrderText());
                        descriptionLines.add(Component.translatable("stats.dmz.modified", defcompleta).withStyle(ChatFormatting.GOLD).getVisualOrderText());
                    } else if (statKey.equals("PWR") && multiTotal > 1) {
                        descriptionLines.add(Component.translatable("stats.dmz.original", kipowerdefault).withStyle(ChatFormatting.RED).getVisualOrderText());
                        descriptionLines.add(Component.translatable("stats.dmz.modified", pwrcompleta).withStyle(ChatFormatting.GOLD).getVisualOrderText());
                    }
                    graphics.renderTooltip(font, descriptionLines, mouseX, mouseY);
                }
            }

            //STATS CAPABILITY
            alturaTexto = (this.height / 2) -14;
            anchoTexto = (this.width/2)-65;

            if(isMultiOn){ //Si alguna forma, estado esta activo.
                drawStringWithBorder2(graphics, font, STRReal, anchoTexto, alturaTexto, colorEnForma);
                drawStringWithBorder2(graphics, font, DEFReal, anchoTexto, alturaTexto + 12, colorEnForma);
                drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(condefault)), anchoTexto, alturaTexto + 24, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, PWRReal, anchoTexto, alturaTexto + 36, colorEnForma);
                drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(energydefault)), anchoTexto, alturaTexto + 48, 0xFFD7AB);
            } else {
                drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(strdefault)), anchoTexto, alturaTexto, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(defdefault)), anchoTexto, alturaTexto + 12, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(condefault)), anchoTexto, alturaTexto + 24, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(kipowerdefault)), anchoTexto, alturaTexto + 36, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(energydefault)), anchoTexto, alturaTexto + 48, 0xFFD7AB);
            }

            anchoTexto = (this.width/2)-65;
            drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(finalCost)), anchoTexto, alturaTexto + 64, 0xFFCE41);
            drawStringWithBorder2(graphics, font, Component.literal("x" + multiplicadorTP), anchoTexto, alturaTexto + 76, 0x2BFFE2);

        });

    }

    public void menu2info(GuiGraphics graphics, int mouseX, int mouseY){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            anchoTexto = (this.width/2+2);
            alturaTexto = (this.height / 2) -37;

            //Information title
            drawStringWithBorder2(graphics, font, Component.literal("STATISTICS"), anchoTexto, alturaTexto, 0xF91E64);

            //Titulos
            anchoTexto = (this.width/2+2);
            alturaTexto = (this.height / 2) -25;

            var color = 0xFBA16A;
            String[] stats = {"Damage", "Defense", "Health", "Stamina", "Ki Damage", "Max Ki"};
            int[] colors = {0xFBA16A, 0xFBA16A, 0xFBA16A, 0xFFBB91, 0xFBA16A, 0xFBA16A};
            for (int i = 0; i < stats.length; i++) {
                String statKey = stats[i];
                int colores = colors[i];
                int yOffset = alturaTexto + (i * 12);
                // Dibujar textos Damage, Health, etc
                Component statComponent = Component.literal(statKey + ":")
                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(colores)).withBold(true));
                if (statKey.equals("Stamina")) {
                    graphics.drawString(font, statComponent, anchoTexto + 4, yOffset, colores);
                } else {
                    graphics.drawString(font, statComponent, anchoTexto, yOffset, colores);
                }
                // Dibujar Hovers
                if (mouseX >= anchoTexto -10 && mouseX <= anchoTexto + 60 && mouseY >= yOffset && mouseY <= yOffset + font.lineHeight) {
                    List<FormattedCharSequence> descriptionLines;
                    if (statKey.equals("Ki Damage")) {
                        descriptionLines = getStatDescription("ki_damage", font);
                    } else if (statKey.equals("Max Ki")) {
                        descriptionLines = getStatDescription("max_ki", font);
                    } else {
                        descriptionLines = getStatDescription(statKey.toLowerCase(), font);
                    }
                    graphics.renderTooltip(font, descriptionLines, mouseX, mouseY);
                }
            }

            anchoTexto = (this.width/2+75);

            //Efectos
            var majinOn = playerstats.hasDMZPermaEffect("majin");
            var frutaOn = playerstats.hasDMZTemporalEffect("mightfruit");

            //Datos
            var raza = playerstats.getRace();
            var str = playerstats.getStrength();
            var def = playerstats.getDefense();
            var con = playerstats.getConstitution();
            var kpw = playerstats.getKiPower();
            var enr = playerstats.getEnergy();

            var clase = playerstats.getDmzClass();
            var transf = playerstats.getDmzState();
            var release = playerstats.getDmzRelease();

            var strMax = dmzdatos.calcularSTR(raza, str, 1, transf,release,clase, majinOn, frutaOn);
            var defMax = dmzdatos.calcularDEF(Minecraft.getInstance().player,raza,def, transf,release, clase, majinOn, frutaOn);
            var conMax = dmzdatos.calcularCON(raza, con, 20, clase);
            var stmMax = dmzdatos.calcularSTM(raza, conMax);
            var KPWMax = dmzdatos.calcularKiPower(raza, kpw, transf, release, clase, majinOn, frutaOn);
            var enrMax = dmzdatos.calcularENE(raza, enr, clase);

            var colorEnForma = majinOn || frutaOn || transf > 0 ? 0xfebc0d : 0xFFD7AB;

            drawStringWithBorder(graphics, font, Component.literal(String.valueOf(strMax)), anchoTexto, alturaTexto, colorEnForma);
            drawStringWithBorder(graphics, font, Component.literal(String.valueOf(defMax)), anchoTexto, alturaTexto + 12, colorEnForma);
            drawStringWithBorder(graphics, font, Component.literal(String.valueOf(conMax)), anchoTexto, alturaTexto + 24, 0xFFD7AB);
            drawStringWithBorder(graphics, font, Component.literal(String.valueOf(stmMax)), anchoTexto, alturaTexto + 36, 0xFFD7AB);
            drawStringWithBorder(graphics, font, Component.literal(String.valueOf(KPWMax)), anchoTexto, alturaTexto + 48, colorEnForma);
            drawStringWithBorder(graphics, font, Component.literal(String.valueOf(enrMax)), anchoTexto, alturaTexto + 60, 0xFFD7AB);

            var MultiTotal = Math.round((dmzdatos.calcularMultiTotal(raza, transf, majinOn, frutaOn)) * 100) / 100.0;

            var multiMajin = DMZGeneralConfig.MULTIPLIER_MAJIN.get();
            var multiFruta = DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get();
            var multiTransf = dmzdatos.calcularMultiTransf(raza, transf);
            var anchoMulti = (this.width /2+2) - 3;
            var altoMulti = (this.height / 2) + 55;

            Component statComponent = Component.literal("Multiplier:")
                    .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xC51D1D)).withBold(true));

            graphics.drawString(font, statComponent, anchoMulti, altoMulti, 0xC51D1D);

            if (mouseX >= anchoMulti -30 && mouseX <= anchoMulti + 50 && mouseY >= altoMulti && mouseY <= altoMulti + font.lineHeight) {
                List<FormattedCharSequence> descriptionLines = new ArrayList<>();
                descriptionLines.add(Component.translatable("stats.dmz.multiplier", multiTransf).withStyle(ChatFormatting.BLUE).getVisualOrderText());
                descriptionLines.add(Component.translatable("stats.dmz.multiplier.desc", multiTransf).getVisualOrderText());
                descriptionLines.add(Component.translatable("stats.dmz.multi.transf", multiTransf).withStyle(ChatFormatting.DARK_AQUA).getVisualOrderText());
                if (majinOn) {
                    descriptionLines.add(Component.translatable("stats.dmz.multi.majin", multiMajin).withStyle(ChatFormatting.LIGHT_PURPLE).getVisualOrderText());
                }
                if (frutaOn) {
                    descriptionLines.add(Component.translatable("stats.dmz.multi.fruta", multiFruta).withStyle(ChatFormatting.RED).getVisualOrderText());
                }
                // Agregar más if luego para ver si está el Kaioken, etc, etc, etc.
                graphics.renderTooltip(font, descriptionLines, mouseX, mouseY);
            }
            drawStringWithBorder2(graphics, font, Component.literal("x"+MultiTotal), anchoTexto-3, alturaTexto + 80, colorEnForma);
        });
    }

    public void menuPaneles(GuiGraphics guiGraphics){
        //INFO GENERAL (Fuerza maxima, energia maxima, stamina, etc)
        alturaTexto = (this.height - 168)/2;
        anchoTexto = (this.width - 250)/2;
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        guiGraphics.blit(menucentro, anchoTexto, alturaTexto, 0, 0, 250, 168);
        RenderSystem.disableBlend();

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
    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private List<FormattedCharSequence> getStatDescription(String statKey, Font font) {
        Component descripcion = Component.translatable("stats.dmz." + statKey);
        int maxWidth = 200;
        return font.split(descripcion, maxWidth);
    }
}
