package com.yuseix.dragonminez.utils;

import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.config.races.*;
import com.yuseix.dragonminez.config.races.transformations.*;
import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class DMZDatos implements IDMZDatos{

    @Override
    public int calcularSTR(DMZStatsAttributes stats) {

        boolean majinOn = stats.hasDMZPermaEffect("majin");
        boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        String clase = stats.getDmzClass();
        int danoJugador = 1;
        int raza = stats.getRace();
        int StatSTR = stats.getStrength();
        int porcentaje = stats.getDmzRelease();
        int transformation = stats.getDmzState();

        double maxStr = 0;
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;

        var efectosTotal = majinDato * frutaDato;

        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        if(transformation == 0){ //FORMA BASE
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else if (transformation == 1) { //FULL POWER
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else { //CUALQUIERWA
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        }
                        break;

                    case 1: // Saiyan
                        if(transformation == 0){ //FORMA BASE
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else if (transformation == 1) { //SUPER SAIYAJIN 1
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else { //OTROS
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        }
                        break;

                    case 2: // Namek
                        if(transformation == 0){ //FORMA BASE
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else if (transformation == 1) { // FULL POWER
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else {
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        }
                        break;

                    case 3: // Bioandroide
                        if(transformation == 0){ //FORMA IMPERFECTA
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * (porcentaje/10));
                        } else if (transformation == 1) { //SEMIPERFECTO
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * (porcentaje/10));
                        } else { //OTROS
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * (porcentaje/10));
                        }
                        break;

                    case 4: // Cold Demon
                        if(transformation == 0){ //FORMA MINIMA
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else if (transformation == 1) { //SEGUNDA FORMA
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/5));
                        } else { //OTROS
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        }
                        break;

                    case 5: // Majin
                        if(transformation == 0){ //FORMA BASE
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else if (transformation == 1) { //FORMA KID
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else { //OTROS OSEA FORMA SUPER
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        }
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            case "Spiritualist":
                switch (raza) {
                    case 0: // Humano
                        if(transformation == 0){
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else if (transformation == 1) {
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else {
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        }
                        break;

                    case 1: // Saiyan
                        if(transformation == 0){
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else if (transformation == 1) {
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else {
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        }
                        break;

                    case 2: // Namek
                        if(transformation == 0){
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else if (transformation == 1) {
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else {
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        }
                        break;

                    case 3: // Bioandroide
                        if(transformation == 0){
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else if (transformation == 1) {
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else {
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        }
                        break;

                    case 4: // Cold Demon
                        if(transformation == 0){
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else if (transformation == 1) {
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else {
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        }
                        break;

                    case 5: // Majin
                        if(transformation == 0){
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else if (transformation == 1) {
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        } else {
                            maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                        }
                        break;

                    default:
                        // Manejar el caso en que la raza no sea valida
                        break;
                }
                break;
        }



        // Fórmula = ((StatSTR * ConfigRaza) * Transf) * Porcentaje
        return (int) maxStr;
    }

    @Override
    public int calcularDEF(Player player, int raza, int StatDEF, int Transformation, int powerRelease, String clase, boolean majinOn, boolean mightfruit) {
        double maxDef = 0;
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;

        int DefensaArmor = player.getArmorValue();
        int DurezaArmor = Mth.floor(player.getAttributeValue(Attributes.ARMOR_TOUGHNESS));

        var efectosTotal = majinDato * frutaDato;

        // Defensa = (((((StatDEF * ConfigRaza) * Transf) * Porcentaje)) / 6) + ((DefensaArmor) + (DurezaArmor))
        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        if(Transformation == 0){ //Forma base
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosTotal) * ((double)powerRelease/10))) / 6)  + ((DefensaArmor) + (DurezaArmor));
                        } else {
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                        }
                        break;

                    case 1: // Saiyan
                        if(Transformation == 0){ //Forma base
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                        } else {
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                        }
                        break;

                    case 2: // Namek
                        if(Transformation == 0){ //Forma base
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                        } else {
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                        }
                        break;

                    case 3: // BioAndroide
                        if(Transformation == 0){ //Forma base
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                        } else {
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                        }
                        break;

                    case 4: // ColdDemon
                        if(Transformation == 0){ //Forma base
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                        } else {
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                        }
                        break;

                    case 5: // Majin
                        if(Transformation == 0){ //Forma base
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                        } else {
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                        }
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            case "Spiritualist":
                switch (raza) {
                    case 0: // Humano
                        if(Transformation == 0){ //Forma base
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrHumanConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6);
                        } else {
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get()* efectosTotal)) * ((double)powerRelease/10)) / 6);
                        }
                        break;

                    case 1: // Saiyan
                        if(Transformation == 0){ //Forma base
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6);
                        } else {
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6);
                        }
                        break;

                    case 2: // Namek
                        if(Transformation == 0){ //Forma base
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6);
                        } else {
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6);
                        }
                        break;

                    case 3: // BioAndroide
                        if(Transformation == 0){ //Forma base
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6);
                        } else {
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6);
                        }
                        break;

                    case 4: // ColdDemon
                        if(Transformation == 0){ //Forma base
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6);
                        } else {
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6);
                        }
                        break;

                    case 5: // Majin
                        if(Transformation == 0){ //Forma base
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6);
                        } else {
                            maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6);
                        }
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            default:
                break;
        }




        return (int) maxDef;
    }

    @Override
    public int calcularCON(int raza, int StatCON, float vidaMC, String clase) {
        double maxCon = 0;

        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        maxCon = Math.round(vidaMC + (1.2 * (double) StatCON * DMZHumanConfig.MULTIPLIER_CON_WARRIOR.get()));
                        break;
                    case 1: // Saiyan
                        maxCon = Math.round(vidaMC + (1.2 * (double) StatCON * DMZSaiyanConfig.MULTIPLIER_CON_WARRIOR.get()));
                        break;

                    case 2: // Namek
                        maxCon = Math.round(vidaMC + (1.2 * (double) StatCON * DMZNamekConfig.MULTIPLIER_CON_WARRIOR.get()));
                        break;

                    case 3: // Bioandroide
                        maxCon = Math.round(vidaMC + (1.2 * (double) StatCON * DMZBioAndroidConfig.MULTIPLIER_CON_WARRIOR.get()));
                        break;

                    case 4: // Cold Demon
                        maxCon = Math.round(vidaMC + (1.2 * (double) StatCON * DMZColdDemonConfig.MULTIPLIER_CON_WARRIOR.get()));
                        break;

                    case 5: // Majin
                        maxCon = Math.round(vidaMC + (1.2 * (double) StatCON * DMZMajinConfig.MULTIPLIER_CON_WARRIOR.get()));
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            case "Spiritualist":
                switch (raza) {
                    case 0: // Humano
                        maxCon = Math.round(vidaMC + (1.2 * (double) StatCON * DMZHumanConfig.MULTIPLIER_CON_SPIRITUALIST.get()));
                        break;
                    case 1: // Saiyan
                        maxCon = Math.round(vidaMC + (1.2 * (double) StatCON * DMZSaiyanConfig.MULTIPLIER_CON_SPIRITUALIST.get()));
                        break;

                    case 2: // Namek
                        maxCon = Math.round(vidaMC + (1.2 * (double) StatCON * DMZNamekConfig.MULTIPLIER_CON_SPIRITUALIST.get()));
                        break;

                    case 3: // Bioandroide
                        maxCon = Math.round(vidaMC + (1.2 * (double) StatCON * DMZBioAndroidConfig.MULTIPLIER_CON_SPIRITUALIST.get()));
                        break;

                    case 4: // Cold Demon
                        maxCon = Math.round(vidaMC + (1.2 * (double) StatCON * DMZColdDemonConfig.MULTIPLIER_CON_SPIRITUALIST.get()));
                        break;

                    case 5: // Majin
                        maxCon = Math.round(vidaMC + (1.2 * (double) StatCON * DMZMajinConfig.MULTIPLIER_CON_SPIRITUALIST.get()));
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            default:
                break;
        }


        return (int) maxCon;
    }

    @Override
    public int calcularSTM(int raza, int maxCON) {
        //Aca lo configuraremos segun raza y si es spiritualista o guerrero
        double maxSTM = 0;

        switch (raza) {
            case 0: // Humano
                maxSTM = Math.round(maxCON * 0.5);
                break;
            case 1: // Saiyan
                maxSTM = Math.round(maxCON * 0.5);
                break;

            case 2: // Namek
                maxSTM = Math.round(maxCON * 0.5);
                break;

            case 3: // Bioandroide
                maxSTM = Math.round(maxCON * 0.5);
                break;

            case 4: // Cold Demon
                maxSTM = Math.round(maxCON * 0.5);
                break;

            case 5: // Majin
                maxSTM = Math.round(maxCON * 0.5);
                break;

            default:
                // Manejar el caso en que la raza no sea válida
                break;
        }

        return (int) maxSTM;
    }

    @Override
    public int calcularKiPower(int raza, int StatPWR, int Transformation, int PowerRelease, String clase, boolean majinOn, boolean mightfruit) {
        double maxPWR = 0;
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;

        var efectosTotal = majinDato * frutaDato;

        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        if(Transformation == 0){
                            maxPWR = Math.ceil((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        } else {
                            maxPWR = Math.ceil((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        }
                        break;
                    case 1: // Saiyan
                        if(Transformation == 0){
                            maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        } else {
                            maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        }
                        break;
                    case 2: // Namek
                        if(Transformation == 0){
                            maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        } else {
                            maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        }
                        break;
                    case 3: // Bioandroide
                        if(Transformation == 0){
                            maxPWR = Math.ceil((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        } else {
                            maxPWR = Math.ceil((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        }
                        break;
                    case 4: // Cold Demon
                        if(Transformation == 0){
                            maxPWR = Math.ceil((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        } else {
                            maxPWR = Math.ceil((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        }
                        break;
                    case 5: // Majin
                        if(Transformation == 0){
                            maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        } else {
                            maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        }
                        break;
                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            case "Spiritualist":
                switch (raza) {
                    case 0: // Humano
                        if(Transformation == 0){
                            maxPWR = Math.ceil((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        } else {
                            maxPWR = Math.ceil((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        }
                        break;
                    case 1: // Saiyan
                        if(Transformation == 0){
                            maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        } else {
                            maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        }
                        break;
                    case 2: // Namek
                        if(Transformation == 0){
                            maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        } else {
                            maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        }
                        break;
                    case 3: // Bioandroide
                        if(Transformation == 0){
                            maxPWR = Math.ceil((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        } else {
                            maxPWR = Math.ceil((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        }
                        break;
                    case 4: // Cold Demon
                        if(Transformation == 0){
                            maxPWR = Math.ceil((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        } else {
                            maxPWR = Math.ceil((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        }
                        break;
                    case 5: // Majin
                        if(Transformation == 0){
                            maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        } else {
                            maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                        }
                        break;
                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
        }


        return (int) maxPWR;
    }

    @Override
    public int calcularENE(int raza, int StatENE, String clase) {
        double maxENE = 0;

        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        maxENE = Math.round(StatENE * DMZHumanConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;
                    case 1: // Saiyan
                        maxENE = Math.round(StatENE * DMZSaiyanConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    case 2: // Namek
                        maxENE = Math.round(StatENE * DMZNamekConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    case 3: // Bioandroide
                        maxENE = Math.round(StatENE * DMZBioAndroidConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    case 4: // Cold Demon
                        maxENE = Math.round(StatENE * DMZColdDemonConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    case 5: // Majin
                        maxENE = Math.round(StatENE * DMZMajinConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            case "Spiritualist":
                switch (raza) {
                    case 0: // Humano
                        maxENE = Math.round(StatENE * DMZHumanConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;
                    case 1: // Saiyan
                        maxENE = Math.round(StatENE * DMZSaiyanConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    case 2: // Namek
                        maxENE = Math.round(StatENE * DMZNamekConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    case 3: // Bioandroide
                        maxENE = Math.round(StatENE * DMZBioAndroidConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    case 4: // Cold Demon
                        maxENE = Math.round(StatENE * DMZColdDemonConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    case 5: // Majin
                        maxENE = Math.round(StatENE * DMZMajinConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            default:
                break;
        }


        return (int) maxENE;
    }

    @Override
    public int calcularKiConsume(int raza, int StatENE, int form) {
        int costo = 0;

        switch (raza){
            case 0:
                if(form == 0){
                    costo = DMZTrHumanConfig.BASE_FORM_KI_COST.get();
                } else if(form == 1){
                    costo = DMZTrHumanConfig.FP_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL HUMANO
                } else {
                    costo = DMZTrHumanConfig.BASE_FORM_KI_COST.get();
                }
                break;
            case 1:
                if(form == 0){
                    costo = DMZTrSaiyanConfig.BASE_FORM_KI_COST.get();
                } else if(form == 1){
                    costo = DMZTrSaiyanConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL SAIYAN
                } else {
                    costo = DMZTrSaiyanConfig.BASE_FORM_KI_COST.get();
                }

                break;
            case 2:
                if(form == 0){
                    costo = DMZTrNamekConfig.BASE_FORM_KI_COST.get();
                } else if(form == 1){
                    costo = DMZTrNamekConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL NAMEK
                } else {
                    costo = DMZTrNamekConfig.BASE_FORM_KI_COST.get();
                }
                break;
            case 3:
                if(form == 0){
                    costo = DMZTrBioAndroidConfig.BASE_FORM_KI_COST.get();
                } else if(form == 1){
                    costo = DMZTrBioAndroidConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL BIO ANDROIDE
                } else {
                    costo = DMZTrBioAndroidConfig.BASE_FORM_KI_COST.get();
                }
                break;
            case 4:
                if(form == 0){
                    costo = DMZTrColdDemonConfig.BASE_FORM_KI_COST.get();
                } else if(form == 1){
                    costo = DMZTrColdDemonConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL COLD DEMON
                } else {
                    costo = DMZTrColdDemonConfig.BASE_FORM_KI_COST.get();
                }
                break;
            case 5:
                if(form == 0){
                    costo = DMZTrMajinConfig.BASE_FORM_KI_COST.get();
                } else if(form == 1){
                    costo = DMZTrMajinConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL MAJIN
                } else {
                    costo = DMZTrMajinConfig.BASE_FORM_KI_COST.get();
                }
                break;
            default:
                break;
        }

        return costo;
    }

    @Override
    public int calcularKiRegen(int raza, int EnergiaTotal, String clase) {
        int regenki = 0;

        switch (clase){
            case "Warrior":
                switch (raza){
                    case 0:
                        regenki = (int) Math.ceil ((EnergiaTotal * DMZHumanConfig.KI_REGEN_WARRIOR.get()));
                        break;
                    case 1:
                        regenki = (int) Math.ceil ((EnergiaTotal * DMZSaiyanConfig.KI_REGEN_WARRIOR.get()));
                        break;
                    case 2:
                        regenki = (int) Math.ceil ((EnergiaTotal * DMZNamekConfig.KI_REGEN_WARRIOR.get()));
                        break;
                    case 3:
                        regenki = (int) Math.ceil ((EnergiaTotal * DMZBioAndroidConfig.KI_REGEN_WARRIOR.get()));
                        break;
                    case 4:
                        regenki = (int) Math.ceil ((EnergiaTotal * DMZColdDemonConfig.KI_REGEN_WARRIOR.get()));
                        break;
                    case 5:
                        regenki = (int) Math.ceil ((EnergiaTotal * DMZMajinConfig.KI_REGEN_WARRIOR.get()));
                        break;
                    default:
                        break;
                }

                break;
            case "Spiritualist":
                switch (raza){
                    case 0:
                        regenki = (int) Math.ceil ((EnergiaTotal * DMZHumanConfig.KI_REGEN_SPIRITUALIST.get()));
                        break;
                    case 1:
                        regenki = (int) Math.ceil ((EnergiaTotal * DMZSaiyanConfig.KI_REGEN_SPIRITUALIST.get()));
                        break;
                    case 2:
                        regenki = (int) Math.ceil ((EnergiaTotal * DMZNamekConfig.KI_REGEN_SPIRITUALIST.get()));
                        break;
                    case 3:
                        regenki = (int) Math.ceil ((EnergiaTotal * DMZBioAndroidConfig.KI_REGEN_SPIRITUALIST.get()));
                        break;
                    case 4:
                        regenki = (int) Math.ceil ((EnergiaTotal * DMZColdDemonConfig.KI_REGEN_SPIRITUALIST.get()));
                        break;
                    case 5:
                        regenki = (int) Math.ceil ((EnergiaTotal * DMZMajinConfig.KI_REGEN_SPIRITUALIST.get()));
                        break;
                    default:
                        break;
                }
                break;
        }

        return regenki;
    }

    @Override
    public double calcularMultiTotal(int raza, int transformacion, boolean majinOn, boolean mightfruit) {
        var multiStr = 0.0;
        var multiDef = 0.0;
        var multiKiPower = 0.0;
        var total = 0.0;

        switch (raza){
            case 0:
                if(transformacion == 0){ //Base
                    multiStr = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                }else if (transformacion == 1){ //Full Power
                    multiStr = DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get();
                    multiDef = DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get();
                    multiKiPower = DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get();
                } else { //En caso de que se use una transformacion no registrada
                    multiStr = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                }
                break;
            case 1:
                if(transformacion == 0){ //Base
                    multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                }else if (transformacion == 1){ //Ozaru
                    multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                } else { //En caso de que se use una transformacion no registrada
                    multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                }
                break;
            case 2:
                if(transformacion == 0){ //Base
                    multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                }else if (transformacion == 1){ //Full Power
                    multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                } else { //En caso de que se use una transformacion no registrada
                    multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                }
                break;
            case 3:
                if(transformacion == 0){ //Base
                    multiStr = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                }else if (transformacion == 1){ //SemiPerfecto
                    multiStr = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                } else { //En caso de que se use una transformacion no registrada
                    multiStr = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                }
                break;
            case 4:
                if(transformacion == 0){ //Base
                    multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                }else if (transformacion == 1){ //Segunda forma
                    multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                } else { //En caso de que se use una transformacion no registrada
                    multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                }
                break;
            case 5:
                if(transformacion == 0){ //Base
                    multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                }else if (transformacion == 1){ //Evil o kid no se
                    multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                } else { //En caso de que se use una transformacion no registrada
                    multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                }
                break;
            default:
                break;
        }

        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;

        var efectosTotal = majinDato * frutaDato;

        total = (multiStr + multiDef + multiKiPower) / 3; // Promedio, pq si se tiene x1 STR, x1 DEF y x1 PWR, debería ser x1 en Total y no x3
        total = total * efectosTotal;

        return total;
    }

    @Override
    public double calcularMultiStat(int raza, int transformacion, String stat, boolean majinOn, boolean mightfruit) {
        var multiStat = 0.0;
        var total = 0.0;
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        var efectosTotal = majinDato * frutaDato;
        if (efectosTotal == 1) efectosTotal = 0;


        switch (raza){
            case 0: //Humano
                switch (stat){
                    case "STR":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //FP
                            multiStat = DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get();
                        } else {
                            multiStat = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                    case "DEF":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //FP
                            multiStat = DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get();
                        } else {
                            multiStat = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                    case "KIPOWER":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //FP
                            multiStat = DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get();
                        } else {
                            multiStat = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                }
                break;
            case 1: //Saiyajin
                switch (stat){
                    case "STR":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //OZARU
                            multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                    case "DEF":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //OZARU
                            multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                    case "KIPOWER":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //OZARU
                            multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                }
                break;
            case 2: //namek
                switch (stat){
                    case "STR":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //FP o giganten ns
                            multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                    case "DEF":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //FP o giganten ns
                            multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                    case "KIPOWER":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //FP o giganten ns
                            multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                }
                break;
            case 3: //bioandroide
                switch (stat){
                    case "STR":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //FP o giganten ns
                            multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                    case "DEF":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //FP o giganten ns
                            multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                    case "KIPOWER":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //FP o giganten ns
                            multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                }
                break;
            case 4:
                switch (stat){
                    case "STR":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //segunda forma
                            multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                    case "DEF":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //segunda forma
                            multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                    case "KIPOWER":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //segunda forma
                            multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                }
                break;
            case 5: //majin
                switch (stat){
                    case "STR":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //evil o kid
                            multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                    case "DEF":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //evil o kid
                            multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                    case "KIPOWER":
                        if(transformacion == 0){ //Base
                            multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        } else if(transformacion == 1){ //evil o kid
                            multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        } else {
                            multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        }
                        break;
                }
                break;
            default:
                break;
        }

        total = multiStat * efectosTotal;

        return total;
    }

    public double calcularMultiTransf(int raza, int transformacion) {
        var multiStr = 0.0;
        var multiDef = 0.0;
        var multiKiPower = 0.0;
        var multiTransf = 0.0;

        switch (raza){
            case 0:
                if(transformacion == 0){ //Base
                    multiStr = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                }else if (transformacion == 1){ //Full Power
                    multiStr = DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get();
                    multiDef = DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get();
                    multiKiPower = DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get();
                } else { //En caso de que se use una transformacion no registrada
                    multiStr = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                }
                break;
            case 1:
                if(transformacion == 0){ //Base
                    multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                }else if (transformacion == 1){ //Ozaru
                    multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                } else { //En caso de que se use una transformacion no registrada
                    multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                }
                break;
            case 2:
                if(transformacion == 0){ //Base
                    multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                }else if (transformacion == 1){ //Full Power
                    multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                } else { //En caso de que se use una transformacion no registrada
                    multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                }
                break;
            case 3:
                if(transformacion == 0){ //Base
                    multiStr = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                }else if (transformacion == 1){ //SemiPerfecto
                    multiStr = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                } else { //En caso de que se use una transformacion no registrada
                    multiStr = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                }
                break;
            case 4:
                if(transformacion == 0){ //Base
                    multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                }else if (transformacion == 1){ //Segunda forma
                    multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                } else { //En caso de que se use una transformacion no registrada
                    multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                }
                break;
            case 5:
                if(transformacion == 0){ //Base
                    multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                }else if (transformacion == 1){ //Evil o kid no se
                    multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                } else { //En caso de que se use una transformacion no registrada
                    multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                    multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                    multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                }
                break;
            default:
                break;
        }
        multiTransf = (multiStr + multiDef + multiKiPower) / 3;

        return multiTransf;
    }

    @Override
    public int calcularSTRCompleta(int raza, int transformacion, int statStr, boolean majinOn, boolean mightfruit) {
        var statfinal = 0;
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;

        var efectosDato = majinDato * frutaDato;

        switch (raza){
            case 0: //Humano
                if (transformacion == 0) { //Base
                    statfinal = (int) (statStr * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else if (transformacion == 1) { //FP
                    statfinal = (int) (statStr * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get() * efectosDato));
                } else {
                    statfinal = (int) (statStr * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosDato));
                }
                break;
            case 1: //Saiyajin
                if (transformacion == 0) { //Base
                    statfinal = (int) (statStr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                } else if (transformacion == 1) { //SSJ
                    statfinal = (int) (statStr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statStr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                }
                break;
            case 2: //namek
                if (transformacion == 0) { //Base
                    statfinal = (int) (statStr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else if (transformacion == 1) { //Gigante o FUll
                    statfinal = (int) (statStr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statStr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                }
                break;
            case 3: //bioandroide
                if (transformacion == 0) { //Base
                    statfinal = (int) (statStr *(DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosDato));
                } else if (transformacion == 1) { //SemiPerfecto
                    statfinal = (int) (statStr * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statStr *(DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosDato));
                }
                break;
            case 4:
                if (transformacion == 0) { //forma minima
                    statfinal = (int) (statStr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else if (transformacion == 1) { //Segunda forma
                    statfinal = (int) (statStr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statStr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                }
                break;
            case 5: //majin
                if (transformacion == 0) { //forma gordo
                    statfinal = (int) (statStr * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosDato));
                } else if (transformacion == 1) { //evil
                    statfinal = (int) (statStr * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statStr * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosDato));
                }
                break;
            default:
                break;
        }


        return (int) statfinal;
    }

    @Override
    public int calcularDEFCompleta(int raza, int transformacion, int statDef, boolean majinOn, boolean mightfruit) {
        var statfinal = 0;
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;

        var efectosDato = majinDato * frutaDato;

        switch (raza){
            case 0: //Humano
                if (transformacion == 0) { //Base
                    statfinal = (int) (statDef * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else if (transformacion == 1) { //FP
                    statfinal = (int) (statDef * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get() * efectosDato));
                } else {
                    statfinal = (int) (statDef * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosDato));
                }
                break;
            case 1: //Saiyajin
                if (transformacion == 0) { //Base
                    statfinal = (int) (statDef * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                } else if (transformacion == 1) { //SSJ
                    statfinal = (int) (statDef * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statDef * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                }
                break;
            case 2: //namek
                if (transformacion == 0) { //Base
                    statfinal = (int) (statDef * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else if (transformacion == 1) { //Gigante o FUll
                    statfinal = (int) (statDef * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statDef * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                }
                break;
            case 3: //bioandroide
                if (transformacion == 0) { //Base
                    statfinal = (int) (statDef *(DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosDato));
                } else if (transformacion == 1) { //SemiPerfecto
                    statfinal = (int) (statDef * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statDef *(DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosDato));
                }
                break;
            case 4:
                if (transformacion == 0) { //forma minima
                    statfinal = (int) (statDef * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else if (transformacion == 1) { //Segunda forma
                    statfinal = (int) (statDef * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statDef * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                }
                break;
            case 5: //majin
                if (transformacion == 0) { //forma gordo
                    statfinal = (int) (statDef * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosDato));
                } else if (transformacion == 1) { //evil
                    statfinal = (int) (statDef * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statDef * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosDato));
                }
                break;
            default:
                break;
        }


        return (int) statfinal;
    }

    @Override
    public int calcularPWRCompleta(int raza, int transformacion, int statPwr, boolean majinOn, boolean mightfruit) {
        var statfinal = 0;
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;

        var efectosDato = majinDato * frutaDato;

        switch (raza){
            case 0: //Humano
                if (transformacion == 0) { //Base
                    statfinal = (int) (statPwr * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else if (transformacion == 1) { //FP
                    statfinal = (int) (statPwr * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get() * efectosDato));
                } else {
                    statfinal = (int) (statPwr * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosDato));
                }
                break;
            case 1: //Saiyajin
                if (transformacion == 0) { //Base
                    statfinal = (int) (statPwr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                } else if (transformacion == 1) { //SSJ
                    statfinal = (int) (statPwr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statPwr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                }
                break;
            case 2: //namek
                if (transformacion == 0) { //Base
                    statfinal = (int) (statPwr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else if (transformacion == 1) { //Gigante o FUll
                    statfinal = (int) (statPwr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statPwr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                }
                break;
            case 3: //bioandroide
                if (transformacion == 0) { //Base
                    statfinal = (int) (statPwr * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else if (transformacion == 1) { //SemiPerfecto
                    statfinal = (int) (statPwr * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statPwr * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosDato));
                }
                break;
            case 4:
                if (transformacion == 0) { //forma minima
                    statfinal = (int) (statPwr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else if (transformacion == 1) { //Segunda forma
                    statfinal = (int) (statPwr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statPwr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                }
                break;
            case 5: //majin
                if (transformacion == 0) { //forma gordo
                    statfinal = (int) (statPwr * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else if (transformacion == 1) { //evil
                    statfinal = (int) (statPwr * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosDato));
                } else {
                    statfinal = (int) (statPwr * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosDato));
                }
                break;
            default:
                break;
        }


        return (int) statfinal;
    }

    @Override
    public int calcularCargaKi(int EnergiaTotal, String clase) {
        var porcentaje = 0;

        switch (clase){
            case "Warrior":
                porcentaje = (int) Math.ceil((EnergiaTotal * 0.02));
                break;
            case "Spiritualist":
                porcentaje = (int) Math.ceil((EnergiaTotal * 0.04));
                break;
        }

        return porcentaje;
    }


}
