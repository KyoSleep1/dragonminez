package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
import com.yuseix.dragonminez.client.gui.buttons.CustomButtons;
import com.yuseix.dragonminez.client.gui.buttons.DMZGuiButtons;
import com.yuseix.dragonminez.client.gui.cc.StorylineMenu;
import com.yuseix.dragonminez.network.C2S.StatsC2S;
import com.yuseix.dragonminez.network.C2S.ZPointsC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZClientConfig;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.*;

@OnlyIn(Dist.CLIENT)
public class AttributesMenu2 extends Screen implements RenderEntityInv {

    private int alturaTexto; private int anchoTexto; private int multiplicadorTP = 1;

    private static final ResourceLocation menucentro = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menulargo2.png");


    private CustomButtons multiBoton, strBoton, defBoton, conBoton, pwrBoton, eneBoton; private DMZDatos dmzdatos = new DMZDatos();
    private DMZGuiButtons newMenuBoton;

    // Formateador de números con separadores (por ejemplo, "10.000.000")
    NumberFormat numberFormatter = NumberFormat.getInstance(Locale.US);

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
        botonesMenus();
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
            var tps = playerstats.getIntValue("tps"); var str = playerstats.getStat("STR"); var def = playerstats.getStat("DEF");
            var con = playerstats.getStat("CON"); var kipower = playerstats.getStat("PWR"); var energy = playerstats.getStat("ENE");

            anchoTexto = (this.width/2)-110;alturaTexto = (this.height / 2) -15;

            int maxStats = DMZClientConfig.getMaxStats();
            int baseCost = (int) Math.round(((((str + def + con + kipower + energy) / 2)
                    * DMZClientConfig.getMultiplierZPoints()))
                    * DMZClientConfig.getMultiplierZPoints() * 1.5);
            int upgradeStatSTR, upgradeStatDEF, upgradeStatCON, upgradeStatPWR, upgradeStatENE;
            int finalCostSTR, finalCostDEF, finalCostCON, finalCostPWR, finalCostENE;

            this.multiBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat", anchoTexto - 3, alturaTexto + 63, Component.empty(), wa -> {
                switch (multiplicadorTP) {
                    case 1 -> multiplicadorTP = 10;
                    case 10 -> multiplicadorTP = 100;
                    case 100 -> multiplicadorTP = 1;
                }
            }));

            int[] cantStats = {str, def, con, kipower, energy};
            Arrays.sort(cantStats);
            int maxStat = cantStats[4];

            // Calcula el número de niveles a aumentar de forma uniforme
            upgradeStatSTR = calcularNivelesAumentar(tps, baseCost, multiplicadorTP, maxStats);
            upgradeStatDEF = calcularNivelesAumentar(tps, baseCost, multiplicadorTP, maxStats);
            upgradeStatCON = calcularNivelesAumentar(tps, baseCost, multiplicadorTP, maxStats);
            upgradeStatPWR = calcularNivelesAumentar(tps, baseCost, multiplicadorTP, maxStats);
            upgradeStatENE = calcularNivelesAumentar(tps, baseCost, multiplicadorTP, maxStats);

            // Calcula el costo ajustado para cada stat
            finalCostSTR = calcularCostoRecursivo(maxStat, upgradeStatSTR, baseCost, maxStats);
            finalCostDEF = calcularCostoRecursivo(maxStat, upgradeStatDEF, baseCost, maxStats);
            finalCostCON = calcularCostoRecursivo(maxStat, upgradeStatCON, baseCost, maxStats);
            finalCostPWR = calcularCostoRecursivo(maxStat, upgradeStatPWR, baseCost, maxStats);
            finalCostENE = calcularCostoRecursivo(maxStat, upgradeStatENE, baseCost, maxStats);

            int costoRecursivo = calcularCostoRecursivo(maxStat, multiplicadorTP, baseCost, DMZClientConfig.getMaxStats());
            if(tps >= costoRecursivo){
                if (str < maxStats) {
                    this.strBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, finalCostSTR));
                        ModMessages.sendToServer(new StatsC2S(0, upgradeStatSTR));
                    }));}
                if (def < maxStats) {
                    this.defBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto + 12,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, finalCostDEF));
                        ModMessages.sendToServer(new StatsC2S(1,upgradeStatDEF));
                    }));}
                if (con < maxStats) {
                    this.conBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto + 24,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, finalCostCON));
                        ModMessages.sendToServer(new StatsC2S(2,upgradeStatCON));
                    }));}
                if (kipower < maxStats) {
                    this.pwrBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto + 36,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, finalCostPWR));
                        ModMessages.sendToServer(new StatsC2S(3,upgradeStatPWR));
                    }));}
                if (energy < maxStats) {
                    this.eneBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto + 48,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, finalCostENE));
                        ModMessages.sendToServer(new StatsC2S(4,upgradeStatENE));
                    }));
                }
            }
        });
    }

    public void botonesMenus(){
        alturaTexto = (this.height + 168)/2;
        anchoTexto = (this.width)/2;

        if (this.minecraft.level.isClientSide) {
            Player player = this.minecraft.player;
            this.newMenuBoton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 85, alturaTexto, "stats", Component.empty(), wa -> {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
                    if (!playerstats.getBoolean("compactmenu")) {
                        this.minecraft.setScreen(new AttributesMenu());
                    }});
            }));
            this.newMenuBoton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 55, alturaTexto, "skills", Component.empty(), wa -> {
                this.minecraft.setScreen(new SkillMenu(false));
            }));
            this.newMenuBoton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 25, alturaTexto, "transf", Component.empty(), wa -> {
                this.minecraft.setScreen(new TransfMenu(false));
            }));
            this.newMenuBoton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 5, alturaTexto, "storyline", Component.empty(), wa -> {
                this.minecraft.setScreen(new StorylineMenu(false));
            }));
            this.newMenuBoton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 35, alturaTexto, "kitech", Component.empty(), wa -> {
                // Agregar acá el menú de Ki Techniques
                // this.minecraft.setScreen(new TransfMenu());
            }));
            this.newMenuBoton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 65, alturaTexto, "settings", Component.empty(), wa -> {
                this.minecraft.setScreen(new ConfigMenu());
            }));
        }
    }

    private int calcularCostoRecursivo(int statActual, int nivelesAumentar, int baseCost, int maxStats) {
        int costoTotal = 0;
        for (int i = 0; i < nivelesAumentar; i++) {
            if (statActual + i >= maxStats) break; // No exceder el límite máximo de estadísticas
            costoTotal += baseCost + (int) Math.round(DMZClientConfig.getMultiplierZPoints() * (statActual + i));
        }
        return costoTotal;
    }

    private int calcularNivelesAumentar(int statActual, int tps, int baseCost, int maxStats) {
        int costoTotalEsperado = multiplicadorTP * baseCost;
        int nivelesAumentar = 0;
        int costoAcumulado = 0;

        while (nivelesAumentar < multiplicadorTP && costoAcumulado + baseCost <= costoTotalEsperado) {
            costoAcumulado += baseCost;
            nivelesAumentar++;
        }

        return nivelesAumentar;
    }

    public void menu0info(GuiGraphics guiGraphics, int mouseX, int mouseY){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            alturaTexto = (this.height / 2) - 65; anchoTexto = (this.width/2)+50;

            var playername = Minecraft.getInstance().player.getName().getString(); var alignment = playerstats.getIntValue("alignment");
            var raza = playerstats.getIntValue("race"); int namecolor;
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

            alturaTexto = (this.height / 2) - 54; anchoTexto = (this.width/2)+50;
            String[] razasString = {"human", "saiyan", "namek", "bioandroid", "colddemon", "majin"};
            int[] razasInt = {0, 1, 2, 3, 4, 5};
            int[] colors = {0x177CFC, 0xFCB317, 0x186814, 0x7DFF76, 0x6A31EE, 0xFF86FD};
            for (int i = 0; i < razasString.length; i++) {
                String razaActual = razasString[i];
                int razas = razasInt[i];
                int color = colors[i];
                if (raza == razas) {
                    drawStringWithBorder(guiGraphics, font, Component.translatable("dmz.races.name." + razaActual), anchoTexto, alturaTexto, color);
                }
            }
        });
    }

    public void menu1info(GuiGraphics graphics, int mouseX, int mouseY){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {
            var TPS = playerstats.getIntValue("tps");
            var nivel = (playerstats.getStat("STR") + playerstats.getStat("DEF") + playerstats.getStat("CON")
                    + playerstats.getStat("PWR") + playerstats.getStat("ENE")) / 5;
            var clase = playerstats.getStringValue("class");

            //VARIABLES:
            //NIVEL TPS
            anchoTexto = (this.width/2)-72; alturaTexto = (this.height / 2) - 64;
            drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(nivel)), anchoTexto, alturaTexto, 0xFFFFFF);
            drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(TPS)), anchoTexto, alturaTexto + 11, 0xFFE593);

            //FORMA
            drawStringWithBorder2(graphics, font, Component.translatable(obtenerFormaLang(playerstats.getStringValue("form"), playerstats.getIntValue("race"), playerstats.getStringValue("gender"))), anchoTexto, alturaTexto + 22, 0xC7EAFC);
            //Clase
            if(clase.equals("warrior")){
                drawStringWithBorder2(graphics, font,Component.literal("Warrior"), anchoTexto, alturaTexto + 33, 0xFC4E2B);
            }else {
                drawStringWithBorder2(graphics, font,Component.literal("Spiritualist"), anchoTexto, alturaTexto + 33, 0x2BFCFC);
            }

            var strdefault = playerstats.getStat("STR"); var defdefault = playerstats.getStat("DEF"); var condefault = playerstats.getStat("CON");
            var kipowerdefault = playerstats.getStat("PWR"); var energydefault = playerstats.getStat("ENE"); var raza = playerstats.getIntValue("race");
            var transf = playerstats.getStringValue("form");

            int[] cantStats = {strdefault, defdefault, condefault, kipowerdefault, energydefault};
            Arrays.sort(cantStats);
            int minStat = cantStats[0];

            //Efectos
            var majinOn = playerstats.hasDMZPermaEffect("majin"); var frutaOn = playerstats.hasDMZTemporalEffect("mightfruit");

            var baseCost =  (int) Math.round((((((strdefault + defdefault + condefault + kipowerdefault + energydefault) / 2) * DMZClientConfig.getMultiplierZPoints() )) * DMZClientConfig.getMultiplierZPoints() ) * 1.5);
            int costoRecursivo = calcularCostoRecursivo(minStat, multiplicadorTP, baseCost, DMZClientConfig.getMaxStats());

            var strcompleta = dmzdatos.calcMultipliedStrength(playerstats);
            var defcompleta = dmzdatos.calcMultipliedDefense(playerstats);
            var pwrcompleta = dmzdatos.calcMultipliedKiPower(playerstats);

            var STRMulti = Math.round((dmzdatos.calcStatMultiplier(playerstats, "STR")) * 100) / 100.0;
            var DEFMulti = Math.round((dmzdatos.calcStatMultiplier(playerstats, "DEF")) * 100) / 100.0;
            var KIPOWERMulti = Math.round((dmzdatos.calcStatMultiplier(playerstats, "PWR")) * 100) / 100.0;
            var multiTotal = dmzdatos.calcTotalMultiplier(playerstats);

            var isMultiOn = majinOn || frutaOn || !Objects.equals(transf, "base");
            var colorEnForma = isMultiOn ? 0xfebc0d : 0xFFD7AB;


            //WA
            Component STRReal = Component.empty()
                    .append(Component.literal(numberFormatter.format(strcompleta)))
                    .append(Component.literal(" x")
                            .append(Component.literal(numberFormatter.format(STRMulti)))
                    );
            Component DEFReal = Component.empty()
                    .append(Component.literal(numberFormatter.format(defcompleta)))
                    .append(Component.literal(" x")
                            .append(Component.literal(numberFormatter.format(DEFMulti)))
                    );
            Component PWRReal = Component.empty()
                    .append(Component.literal(numberFormatter.format(pwrcompleta)))
                    .append(Component.literal(" x")
                            .append(Component.literal(numberFormatter.format(KIPOWERMulti)))
                    );

            //Titulos
            anchoTexto = (this.width / 2) - 110; alturaTexto = (this.height / 2) - 64;

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

                    if (statKey.equals("STR") && multiTotal > 1 || playerstats.getIntValue("race") == 4) {
                        descriptionLines.add(Component.translatable("stats.dmz.original", numberFormatter.format(strdefault)).withStyle(ChatFormatting.RED).getVisualOrderText());
                        descriptionLines.add(Component.translatable("stats.dmz.modified", numberFormatter.format(strcompleta)).withStyle(ChatFormatting.GOLD).getVisualOrderText());
                    } else if (statKey.equals("DEF") && multiTotal > 1 || playerstats.getIntValue("race") == 4) {
                        descriptionLines.add(Component.translatable("stats.dmz.original", numberFormatter.format(defdefault)).withStyle(ChatFormatting.RED).getVisualOrderText());
                        descriptionLines.add(Component.translatable("stats.dmz.modified", numberFormatter.format(defcompleta)).withStyle(ChatFormatting.GOLD).getVisualOrderText());
                    } else if (statKey.equals("PWR") && multiTotal > 1 || playerstats.getIntValue("race") == 4) {
                        descriptionLines.add(Component.translatable("stats.dmz.original", numberFormatter.format(kipowerdefault)).withStyle(ChatFormatting.RED).getVisualOrderText());
                        descriptionLines.add(Component.translatable("stats.dmz.modified", numberFormatter.format(pwrcompleta)).withStyle(ChatFormatting.GOLD).getVisualOrderText());
                    }
                    graphics.renderTooltip(font, descriptionLines, mouseX, mouseY);
                }
            }
            //STATS CAPABILITY
            alturaTexto = (this.height / 2) -14; anchoTexto = (this.width/2)-65;

            if(isMultiOn || playerstats.getIntValue("race") == 4){ //Si alguna forma, estado esta activo.
                drawStringWithBorder2(graphics, font, STRReal, anchoTexto, alturaTexto, colorEnForma);
                drawStringWithBorder2(graphics, font, DEFReal, anchoTexto, alturaTexto + 12, colorEnForma);
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(condefault)), anchoTexto, alturaTexto + 24, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, PWRReal, anchoTexto, alturaTexto + 36, colorEnForma);
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(energydefault)), anchoTexto, alturaTexto + 48, 0xFFD7AB);
            } else {
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(strdefault)), anchoTexto, alturaTexto, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(defdefault)), anchoTexto, alturaTexto + 12, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(condefault)), anchoTexto, alturaTexto + 24, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(kipowerdefault)), anchoTexto, alturaTexto + 36, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(energydefault)), anchoTexto, alturaTexto + 48, 0xFFD7AB);
            }

            anchoTexto = (this.width/2)-65;
            drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(costoRecursivo)), anchoTexto, alturaTexto + 64, 0xFFCE41);
            drawStringWithBorder2(graphics, font, Component.literal("x" + multiplicadorTP), anchoTexto, alturaTexto + 76, 0x2BFFE2);

        });

    }

    public void menu2info(GuiGraphics graphics, int mouseX, int mouseY){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            anchoTexto = (this.width/2+2); alturaTexto = (this.height / 2) -37;

            //Information title
            drawStringWithBorder2(graphics, font, Component.literal("STATISTICS"), anchoTexto, alturaTexto, 0xF91E64);

            //Titulos
            anchoTexto = (this.width/2+2); alturaTexto = (this.height / 2) -25;

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
            var majinOn = playerstats.hasDMZPermaEffect("majin"); var frutaOn = playerstats.hasDMZTemporalEffect("mightfruit");
            //Datos
            var transf = playerstats.getStringValue("form");

            var strMax = dmzdatos.calcStrength(playerstats);
            var defMax = dmzdatos.calcDefense(playerstats, Minecraft.getInstance().player);
            var conMax = dmzdatos.calcConstitution(playerstats);
            var stmMax = dmzdatos.calcStamina(playerstats);
            var KPWMax = dmzdatos.calcKiPower(playerstats);
            var enrMax = dmzdatos.calcEnergy(playerstats);

            var colorEnForma = majinOn || frutaOn || !Objects.equals(transf, "base") ? 0xfebc0d : 0xFFD7AB;

            drawStringWithBorder(graphics, font, Component.literal(numberFormatter.format(strMax)), anchoTexto, alturaTexto, colorEnForma);
            drawStringWithBorder(graphics, font, Component.literal(numberFormatter.format(defMax)), anchoTexto, alturaTexto + 12, colorEnForma);
            drawStringWithBorder(graphics, font, Component.literal(numberFormatter.format(conMax)), anchoTexto, alturaTexto + 24, 0xFFD7AB);
            drawStringWithBorder(graphics, font, Component.literal(numberFormatter.format(stmMax)), anchoTexto, alturaTexto + 36, 0xFFD7AB);
            drawStringWithBorder(graphics, font, Component.literal(numberFormatter.format(KPWMax)), anchoTexto, alturaTexto + 48, colorEnForma);
            drawStringWithBorder(graphics, font, Component.literal(numberFormatter.format(enrMax)), anchoTexto, alturaTexto + 60, 0xFFD7AB);

            var MultiTotal = Math.round((dmzdatos.calcTotalMultiplier(playerstats)) * 100) / 100.0;

            var multiMajin = DMZClientConfig.getMajin_multi();
            var multiFruta = DMZClientConfig.getTree_might_multi();
            var multiTransf = dmzdatos.calcularMultiTransf(playerstats);
            var anchoMulti = (this.width /2+2) - 3; var altoMulti = (this.height / 2) + 55;

            Component statComponent = Component.literal("Multiplier:")
                    .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xC51D1D)).withBold(true));

            graphics.drawString(font, statComponent, anchoMulti, altoMulti, 0xC51D1D);

            if (mouseX >= anchoMulti -30 && mouseX <= anchoMulti + 50 && mouseY >= altoMulti && mouseY <= altoMulti + font.lineHeight) {
                List<FormattedCharSequence> descriptionLines = new ArrayList<>();
                descriptionLines.add(Component.translatable("stats.dmz.multiplier", MultiTotal).withStyle(ChatFormatting.BLUE).getVisualOrderText());
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
        alturaTexto = (this.height - 168)/2; anchoTexto = (this.width - 250)/2;
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        guiGraphics.blit(menucentro, anchoTexto, alturaTexto, 0, 0, 250, 168);
        RenderSystem.disableBlend();

    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {
        // Calcular la posición centrada
        int textWidth = font.width(texto); int centeredX = x - (textWidth / 2);
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

    private String obtenerFormaLang(String forma, int race, String gender){
        return switch (race) {
            case 0 -> ("forms.dmz.human." + forma);
            case 1 -> ("forms.dmz.saiyan." + forma);
            case 2 -> ("forms.dmz.namek." + forma);
            case 3 -> ("forms.dmz.bioandroid." + forma);
            case 4 -> ("forms.dmz.colddemon." + forma);
            case 5 -> {
                String result = "";
                if (forma.equals("super") || forma.equals("ultra")) {
                    if (gender.equals("female")) result = ("forms.dmz.majin.female." + forma);
                    if (gender.equals("male")) result = ("forms.dmz.majin.male." + forma);
                } else result = ("forms.dmz.majin." + forma);
                yield result;
            }
            default ->("forms.dmz.human.base");
        };
    }
}
