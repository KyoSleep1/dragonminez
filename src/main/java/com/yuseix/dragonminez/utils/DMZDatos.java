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

        boolean majinOn = stats.hasDMZPermaEffect("majin"); boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        String clase = stats.getDmzClass(); int raza = stats.getRace(); int danoJugador = 1;
        int StatSTR = stats.getStrength(); int porcentaje = stats.getDmzRelease();
        String transformation = stats.getDmzForm();

        double maxStr = 0;
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;

        var efectosTotal = majinDato * frutaDato;

        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        switch (transformation){
                            case "full_power":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "buffed":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            default:
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                        }
                        break;

                    case 1: // Saiyan
                        switch (transformation){
                            case "oozaru":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "ssgrade1":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "ssgrade2":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "ssgrade3":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            default:
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                        }

                        break;

                    case 2: // Namek
                        switch (transformation){
                            case "full_power":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "power_unleashed":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "giant":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "orange_giant":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            default:
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                        }
                        break;

                    case 3: // Bioandroide
                        switch (transformation){
                            case "semi_perfect":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * (porcentaje/10));
                                break;
                            case "perfect":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * (porcentaje/10));
                                break;
                            default:
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * (porcentaje/10));
                                break;
                        }

                        break;

                    case 4: // Cold Demon
                        switch (transformation){
                            case "second_form":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "third_form":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "final_form":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            default:
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                        }
                        break;

                    case 5: // Majin
                        switch (transformation){
                            case "evil":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "kid":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "super":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "ultra":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            default:
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
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
                        switch (transformation){
                            case "full_power":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "buffed":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            default:
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                        }
                        break;

                    case 1: // Saiyan
                        switch (transformation){
                            case "oozaru":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "ssgrade1":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "ssgrade2":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "ssgrade3":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            default:
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                        }

                        break;

                    case 2: // Namek
                        switch (transformation){
                            case "full_power":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "power_unleashed":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "giant":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "orange_giant":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            default:
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                        }
                        break;

                    case 3: // Bioandroide
                        switch (transformation){
                            case "semi_perfect":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * (porcentaje/10));
                                break;
                            case "perfect":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * (porcentaje/10));
                                break;
                            default:
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * (porcentaje/10));
                                break;
                        }

                        break;

                    case 4: // Cold Demon
                        switch (transformation){
                            case "second_form":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "third_form":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "final_form":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            default:
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                        }
                        break;

                    case 5: // Majin
                        switch (transformation){
                            case "evil":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "kid":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "super":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            case "ultra":
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                            default:
                                maxStr = Math.ceil((((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)porcentaje/10));
                                break;
                        }
                        break;
                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
        }



        // Fórmula = ((StatSTR * ConfigRaza) * Transf) * Porcentaje
        return (int) maxStr;
    }

    @Override
    public int calcularDEF(DMZStatsAttributes stats, Player player) {
        boolean majinOn = stats.hasDMZPermaEffect("majin"); boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        String clase = stats.getDmzClass(); int raza = stats.getRace(); int StatDEF = stats.getDefense();
        int powerRelease = stats.getDmzRelease();
        var transformation = stats.getDmzForm();

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
                        switch (transformation){
                            case "full_power":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "buffed":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            default:
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosTotal) * ((double)powerRelease/10))) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                        }
                        break;

                    case 1: // Saiyan
                        switch (transformation){
                            case "oozaru":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "ssgrade1":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "ssgrade2":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "ssgrade3":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            default:
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                        }
                        break;

                    case 2: // Namek
                        switch (transformation){
                            case "full_power":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "power_unleashed":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "giant":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "orange_giant":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            default:
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                        }
                        break;

                    case 3: // BioAndroide
                        switch (transformation){
                            case "semi_perfect":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "perfect":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            default:
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                        }

                        break;

                    case 4: // ColdDemon
                        switch (transformation){
                            case "second_form":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "third_form":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "final_form":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            default:
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                        }
                        break;

                    case 5: // Majin
                        switch (transformation){
                            case "evil":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "kid":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "super":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "ultra":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            default:
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_WARRIOR.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
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
                        switch (transformation){
                            case "full_power":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "buffed":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            default:
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosTotal) * ((double)powerRelease/10))) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                        }
                        break;

                    case 1: // Saiyan
                        switch (transformation){
                            case "oozaru":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "ssgrade1":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "ssgrade2":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "ssgrade3":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            default:
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                        }
                        break;

                    case 2: // Namek
                        switch (transformation){
                            case "full_power":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "power_unleashed":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "giant":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "orange_giant":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            default:
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrNamekConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                        }
                        break;

                    case 3: // BioAndroide
                        switch (transformation){
                            case "semi_perfect":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "perfect":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            default:
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                        }

                        break;

                    case 4: // ColdDemon
                        switch (transformation){
                            case "second_form":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "third_form":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "final_form":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            default:
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get()* efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                        }
                        break;

                    case 5: // Majin
                        switch (transformation){
                            case "evil":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "kid":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "super":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            case "ultra":
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
                            default:
                                maxDef = Math.ceil(((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)powerRelease/10)) / 6)  + ((DefensaArmor) + (DurezaArmor));
                                break;
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
    public int calcularCON(DMZStatsAttributes stats) {
        int raza = stats.getRace(); int StatCON = stats.getConstitution();
        String clase = stats.getDmzClass(); double maxCon = 0; float vidaMC = 20;

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
    public int calcularSTM(DMZStatsAttributes stats) {
        int raza = stats.getRace(); int maxCON = stats.getMaxHealth();

        //Aca lo configuraremos segun raza y si es spiritualista o guerrero
        double maxSTM = 0;

        switch (raza) {
            case 0: // Humano
                maxSTM = Math.round(maxCON * 0.85);
                break;
            case 1: // Saiyan
                maxSTM = Math.round(maxCON * 0.85);
                break;

            case 2: // Namek
                maxSTM = Math.round(maxCON * 0.85);
                break;

            case 3: // Bioandroide
                maxSTM = Math.round(maxCON * 0.85);
                break;

            case 4: // Cold Demon
                maxSTM = Math.round(maxCON * 0.85);
                break;

            case 5: // Majin
                maxSTM = Math.round(maxCON * 0.85);
                break;

            default:
                // Manejar el caso en que la raza no sea válida
                break;
        }

        return (int) maxSTM;
    }

    @Override
    public int calcularKiPower(DMZStatsAttributes stats) {
        int raza = stats.getRace(); int StatPWR = stats.getKiPower();
        String clase = stats.getDmzClass(); int PowerRelease = stats.getDmzRelease();
        var transformation = stats.getDmzForm();
        boolean majinOn = stats.hasDMZPermaEffect("majin");boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        double maxPWR = 0;

        var efectosTotal = majinDato * frutaDato;

        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        switch (transformation){
                            case "full_power":
                                maxPWR = Math.ceil((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "buffed":
                                maxPWR = Math.ceil((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            default:
                                maxPWR = Math.ceil((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                        }
                        break;

                    case 1: // Saiyan
                        switch (transformation){
                            case "oozaru":
                                maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "ssgrade1":
                                maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "ssgrade2":
                                maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "ssgrade3":
                                maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            default:
                                maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                        }

                        break;

                    case 2: // Namek
                        switch (transformation){
                            case "full_power":
                                maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "power_unleashed":
                                maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "giant":
                                maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "orange_giant":
                                maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            default:
                                maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                        }
                        break;

                    case 3: // Bioandroide
                        switch (transformation){
                            case "semi_perfect":
                                maxPWR = Math.ceil((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "perfect":
                                maxPWR = Math.ceil((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            default:
                                maxPWR = Math.ceil((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                        }

                        break;

                    case 4: // Cold Demon
                        switch (transformation){
                            case "second_form":
                                maxPWR = Math.ceil((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "third_form":
                                maxPWR = Math.ceil((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "final_form":
                                maxPWR = Math.ceil((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            default:
                                maxPWR = Math.ceil((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                        }
                        break;

                    case 5: // Majin
                        switch (transformation){
                            case "evil":
                                maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "kid":
                                maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "super":
                                maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "ultra":
                                maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            default:
                                maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
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
                        switch (transformation){
                            case "full_power":
                                maxPWR = Math.ceil((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "buffed":
                                maxPWR = Math.ceil((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            default:
                                maxPWR = Math.ceil((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                        }
                        break;

                    case 1: // Saiyan
                        switch (transformation){
                            case "oozaru":
                                maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "ssgrade1":
                                maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "ssgrade2":
                                maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "ssgrade3":
                                maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            default:
                                maxPWR = Math.ceil((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                        }

                        break;

                    case 2: // Namek
                        switch (transformation){
                            case "full_power":
                                maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "power_unleashed":
                                maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "giant":
                                maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "orange_giant":
                                maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            default:
                                maxPWR = Math.ceil((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                        }
                        break;

                    case 3: // Bioandroide
                        switch (transformation){
                            case "semi_perfect":
                                maxPWR = Math.ceil((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "perfect":
                                maxPWR = Math.ceil((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            default:
                                maxPWR = Math.ceil((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                        }

                        break;

                    case 4: // Cold Demon
                        switch (transformation){
                            case "second_form":
                                maxPWR = Math.ceil((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "third_form":
                                maxPWR = Math.ceil((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "final_form":
                                maxPWR = Math.ceil((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            default:
                                maxPWR = Math.ceil((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                        }
                        break;

                    case 5: // Majin
                        switch (transformation){
                            case "evil":
                                maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "kid":
                                maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "super":
                                maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            case "ultra":
                                maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
                            default:
                                maxPWR = Math.ceil((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosTotal)) * ((double)PowerRelease/10) );
                                break;
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
    public int calcularENE(DMZStatsAttributes stats) {
        int raza = stats.getRace();
        int StatENE = stats.getEnergy();
        String clase = stats.getDmzClass();
        double maxENE = 0;

        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        maxENE = Math.round(3 * StatENE * DMZHumanConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40 * 3);
                        break;
                    case 1: // Saiyan
                        maxENE = Math.round(3 * StatENE * DMZSaiyanConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40 * 3);
                        break;

                    case 2: // Namek
                        maxENE = Math.round(3 * StatENE * DMZNamekConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40 * 3);
                        break;

                    case 3: // Bioandroide
                        maxENE = Math.round(3 * StatENE * DMZBioAndroidConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40 * 3);
                        break;

                    case 4: // Cold Demon
                        maxENE = Math.round(3 * StatENE * DMZColdDemonConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40 * 3);
                        break;

                    case 5: // Majin
                        maxENE = Math.round(3 * StatENE * DMZMajinConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40 * 3);
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            case "Spiritualist":
                switch (raza) {
                    case 0: // Humano
                        maxENE = Math.round(3 * StatENE * DMZHumanConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40 * 3);
                        break;
                    case 1: // Saiyan
                        maxENE = Math.round(3 * StatENE * DMZSaiyanConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40 * 3);
                        break;

                    case 2: // Namek
                        maxENE = Math.round(3 * StatENE * DMZNamekConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40 * 3);
                        break;

                    case 3: // Bioandroide
                        maxENE = Math.round(3 * StatENE * DMZBioAndroidConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40 * 3);
                        break;

                    case 4: // Cold Demon
                        maxENE = Math.round(3 * StatENE * DMZColdDemonConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40 * 3);
                        break;

                    case 5: // Majin
                        maxENE = Math.round(3 * StatENE * DMZMajinConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40 * 3);
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
    public int calcularKiConsume(DMZStatsAttributes stats) {
        int raza = stats.getRace();
        var form = stats.getDmzForm();
        int StatENE = stats.getEnergy();
        int costo = 0;

        switch (raza) {
            case 0: // Humano
                switch (form){
                    case "full_power":
                        costo = DMZTrHumanConfig.FP_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL HUMANO
                        break;
                    case "buffed":
                        costo = DMZTrHumanConfig.FP_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL HUMANO
                        break;
                    default:
                        costo = DMZTrHumanConfig.BASE_FORM_KI_COST.get();
                        break;
                }
                break;

            case 1: // Saiyan
                switch (form){
                    case "oozaru":
                        costo = DMZTrSaiyanConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL SAIYAN
                        break;
                    case "ssgrade1":
                        costo = DMZTrSaiyanConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL SAIYAN
                        break;
                    case "ssgrade2":
                        costo = DMZTrSaiyanConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL SAIYAN
                        break;
                    case "ssgrade3":
                        costo = DMZTrSaiyanConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL SAIYAN
                        break;
                    default:
                        costo = DMZTrSaiyanConfig.BASE_FORM_KI_COST.get();
                        break;
                }

                break;

            case 2: // Namek
                switch (form){
                    case "full_power":
                        costo = DMZTrNamekConfig.BASE_FORM_KI_COST.get();
                        break;
                    case "power_unleashed":
                        costo = DMZTrNamekConfig.BASE_FORM_KI_COST.get();
                        break;
                    case "giant":
                        costo = DMZTrNamekConfig.BASE_FORM_KI_COST.get();
                        break;
                    case "orange_giant":
                        costo = DMZTrNamekConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL NAMEK
                        break;
                    default:
                        costo = DMZTrNamekConfig.BASE_FORM_KI_COST.get();
                        break;
                }
                break;

            case 3: // Bioandroide
                switch (form){
                    case "semi_perfect":
                        costo = DMZTrBioAndroidConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL BIO ANDROIDE
                        break;
                    case "perfect":
                        costo = DMZTrBioAndroidConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL BIO ANDROIDE
                        break;
                    default:
                        costo = DMZTrBioAndroidConfig.BASE_FORM_KI_COST.get();
                        break;
                }

                break;

            case 4: // Cold Demon
                switch (form){
                    case "second_form":
                        costo = DMZTrColdDemonConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL COLD DEMON
                        break;
                    case "third_form":
                        costo = DMZTrColdDemonConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL COLD DEMON
                        break;
                    case "final_form":
                        costo = DMZTrColdDemonConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL COLD DEMON
                        break;
                    default:
                        costo = DMZTrColdDemonConfig.BASE_FORM_KI_COST.get();
                        break;
                }
                break;

            case 5: // Majin
                switch (form){
                    case "evil":
                        costo = DMZTrMajinConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL MAJIN
                        break;
                    case "kid":
                        costo = DMZTrMajinConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL MAJIN
                        break;
                    case "super":
                        costo = DMZTrMajinConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL MAJIN
                        break;
                    case "ultra":
                        costo = DMZTrMajinConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL MAJIN
                        break;
                    default:
                        costo = DMZTrMajinConfig.BASE_FORM_KI_COST.get();
                        break;
                }
                break;
            default:
                // Manejar el caso en que la raza no sea válida
                break;
        }

        return costo;
    }

    @Override
    public int calcularKiRegen(DMZStatsAttributes stats) {
        int raza = stats.getRace(); int StatENE = stats.getEnergy(); int EnergiaTotal = stats.getEnergy();
        String clase = stats.getDmzClass(); int regenki = 0;

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
    public double calcularMultiTotal(DMZStatsAttributes stats) {
        int raza = stats.getRace();
        var form = stats.getDmzForm();
        var multiStr = 0.0; var multiDef = 0.0; var multiKiPower = 0.0; var total = 0.0;
        boolean majinOn = stats.hasDMZPermaEffect("majin"); boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");

        switch (raza) {
            case 0: // Humano
                switch (form){
                    case "full_power":
                        multiStr = DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get();
                        multiDef = DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get();
                        multiKiPower = DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get();
                        break;
                    case "buffed":
                        multiStr = DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get();
                        multiDef = DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get();
                        multiKiPower = DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get();
                        break;
                    default:
                        multiStr = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                        break;
                }
                break;

            case 1: // Saiyan
                switch (form){
                    case "oozaru":
                        multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        break;
                    case "ssgrade1":
                        multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        break;
                    case "ssgrade2":
                        multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        break;
                    case "ssgrade3":
                        multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        break;
                    default:
                        multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        break;
                }

                break;

            case 2: // Namek
                switch (form){
                    case "full_power":
                        multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        break;
                    case "power_unleashed":
                        multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        break;
                    case "giant":
                        multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        break;
                    case "orange_giant":
                        multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        break;
                    default:
                        multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        break;
                }
                break;

            case 3: // Bioandroide
                switch (form){
                    case "semi_perfect":
                        multiStr = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        break;
                    case "perfect":
                        multiStr = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        break;
                    default:
                        multiStr = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        break;
                }

                break;

            case 4: // Cold Demon
                switch (form){
                    case "second_form":
                        multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        break;
                    case "third_form":
                        multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        break;
                    case "final_form":
                        multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        break;
                    default:
                        multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        break;
                }
                break;

            case 5: // Majin
                switch (form){
                    case "evil":
                        multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        break;
                    case "kid":
                        multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        break;
                    case "super":
                        multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        break;
                    case "ultra":
                        multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        break;
                    default:
                        multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        break;
                }
                break;
            default:
                // Manejar el caso en que la raza no sea válida
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
    public double calcularMultiStat(DMZStatsAttributes stats, String stat) {
        int raza = stats.getRace();
        var transformacion = stats.getDmzForm();
        var multiStat = 0.0; var total = 0.0;
        boolean majinOn = stats.hasDMZPermaEffect("majin"); boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        var efectosTotal = majinDato * frutaDato;

        if (efectosTotal == 1) efectosTotal = 0;

        switch (raza){
            case 0: //Humano
                switch (stat){
                    case "STR":
                        switch (transformacion){
                            case "full_power":
                                multiStat = DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get();
                                break;
                            case "buffed":
                                multiStat = DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get();
                                break;
                            default:
                                multiStat = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                                break;
                        }

                        break;
                    case "DEF":
                        switch (transformacion){
                            case "full_power":
                                multiStat = DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get();
                                break;
                            case "buffed":
                                multiStat = DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get();
                                break;
                            default:
                                multiStat = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                    case "KIPOWER":
                        switch (transformacion){
                            case "full_power":
                                multiStat = DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get();
                                break;
                            case "buffed":
                                multiStat = DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get();
                                break;
                            default:
                                multiStat = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                }
                break;
            case 1: //Saiyajin
                switch (stat){
                    case "STR":
                        switch (transformacion){
                            case "oozaru":
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                            case "ssgrade1":
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                            case "ssgrade2":
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                            case "ssgrade3":
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                    case "DEF":
                        switch (transformacion){
                            case "oozaru":
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                            case "ssgrade1":
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                            case "ssgrade2":
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                            case "ssgrade3":
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                    case "KIPOWER":
                        switch (transformacion){
                            case "oozaru":
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                            case "ssgrade1":
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                            case "ssgrade2":
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                            case "ssgrade3":
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                }
                break;
            case 2: //namek
                switch (stat){
                    case "STR":
                        switch (transformacion){
                            case "full_power":
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                            case "power_unleashed":
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                            case "giant":
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                            case "orange_giant":
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                    case "DEF":
                        switch (transformacion){
                            case "full_power":
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                            case "power_unleashed":
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                            case "giant":
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                            case "orange_giant":
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                    case "KIPOWER":
                        switch (transformacion){
                            case "full_power":
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                            case "power_unleashed":
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                            case "giant":
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                            case "orange_giant":
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                }
                break;
            case 3: //bioandroide
                switch (stat){
                    case "STR":
                        switch (transformacion){
                            case "semi_perfect":
                                multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                                break;
                            case "perfect":
                                multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                    case "DEF":
                        switch (transformacion){
                            case "semi_perfect":
                                multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                                break;
                            case "perfect":
                                multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                    case "KIPOWER":
                        switch (transformacion){
                            case "semi_perfect":
                                multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                                break;
                            case "perfect":
                                multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                }
                break;
            case 4:
                switch (stat){
                    case "STR":
                        switch (transformacion){
                            case "second_form":
                                multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                                break;
                            case "third_form":
                                multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                                break;
                            case "final_form":
                                multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                    case "DEF":
                        switch (transformacion){
                            case "second_form":
                                multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                                break;
                            case "third_form":
                                multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                                break;
                            case "final_form":
                                multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                    case "KIPOWER":
                        switch (transformacion){
                            case "second_form":
                                multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                                break;
                            case "third_form":
                                multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                                break;
                            case "final_form":
                                multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                }
                break;
            case 5: //majin
                switch (stat){
                    case "STR":
                        switch (transformacion){
                            case "evil":
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                            case "kid":
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                            case "super":
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                            case "ultra":
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                    case "DEF":
                        switch (transformacion){
                            case "evil":
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                            case "kid":
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                            case "super":
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                            case "ultra":
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                    case "KIPOWER":
                        switch (transformacion){
                            case "evil":
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                            case "kid":
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                            case "super":
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                            case "ultra":
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                            default:
                                multiStat = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                                break;
                        }
                        break;
                }
                break;
            default:
                break;
        }

        if (efectosTotal > 0) total = multiStat * efectosTotal;

        return total;
    }

    public double calcularMultiTransf(DMZStatsAttributes stats) {
        int raza = stats.getRace();
        var form = stats.getDmzForm();
        var multiStr = 0.0; var multiDef = 0.0; var multiKiPower = 0.0; var multiTransf = 0.0;

        switch (raza) {
            case 0: // Humano
                switch (form){
                    case "full_power":
                        multiStr = DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get();
                        multiDef = DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get();
                        multiKiPower = DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get();
                        break;
                    case "buffed":
                        multiStr = DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get();
                        multiDef = DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get();
                        multiKiPower = DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get();
                        break;
                    default:
                        multiStr = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrHumanConfig.MULTIPLIER_BASE.get();
                        break;
                }
                break;

            case 1: // Saiyan
                switch (form){
                    case "oozaru":
                        multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        break;
                    case "ssgrade1":
                        multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        break;
                    case "ssgrade2":
                        multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        break;
                    case "ssgrade3":
                        multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        break;
                    default:
                        multiStr = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                        break;
                }

                break;

            case 2: // Namek
                switch (form){
                    case "full_power":
                        multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        break;
                    case "power_unleashed":
                        multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        break;
                    case "giant":
                        multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        break;
                    case "orange_giant":
                        multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        break;
                    default:
                        multiStr = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrNamekConfig.MULTIPLIER_BASE.get();
                        break;
                }
                break;

            case 3: // Bioandroide
                switch (form){
                    case "semi_perfect":
                        multiStr = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        break;
                    case "perfect":
                        multiStr = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        break;
                    default:
                        multiStr = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                        break;
                }

                break;

            case 4: // Cold Demon
                switch (form){
                    case "second_form":
                        multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        break;
                    case "third_form":
                        multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        break;
                    case "final_form":
                        multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        break;
                    default:
                        multiStr = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                        break;
                }
                break;

            case 5: // Majin
                switch (form){
                    case "evil":
                        multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        break;
                    case "kid":
                        multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        break;
                    case "super":
                        multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        break;
                    case "ultra":
                        multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        break;
                    default:
                        multiStr = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiDef = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        multiKiPower = DMZTrMajinConfig.MULTIPLIER_BASE.get();
                        break;
                }
                break;
            default:
                // Manejar el caso en que la raza no sea válida
                break;
        }

        multiTransf = (multiStr + multiDef + multiKiPower) / 3;

        return multiTransf;
    }

    @Override
    public int calcularSTRCompleta(DMZStatsAttributes stats) {
        int raza = stats.getRace();
        var form = stats.getDmzForm();
        int statStr = stats.getStrength();
        boolean majinOn = stats.hasDMZPermaEffect("majin"); boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        var statfinal = 0;

        var efectosDato = majinDato * frutaDato;

        switch (raza) {
            case 0: // Humano
                switch (form){
                    case "full_power":
                        statfinal = (int) (statStr * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get() * efectosDato));
                        break;
                    case "buffed":
                        statfinal = (int) (statStr * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get() * efectosDato));
                        break;
                    default:
                        statfinal = (int) (statStr * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                }
                break;

            case 1: // Saiyan
                switch (form){
                    case "oozaru":
                        statfinal = (int) (statStr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "ssgrade1":
                        statfinal = (int) (statStr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "ssgrade2":
                        statfinal = (int) (statStr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "ssgrade3":
                        statfinal = (int) (statStr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    default:
                        statfinal = (int) (statStr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                }

                break;

            case 2: // Namek
                switch (form) {
                    case "full_power":
                        statfinal = (int) (statStr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "power_unleashed":
                        statfinal = (int) (statStr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "giant":
                        statfinal = (int) (statStr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "orange_giant":
                        statfinal = (int) (statStr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    default:
                        statfinal = (int) (statStr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                }
                break;

            case 3: // Bioandroide
                switch (form){
                    case "semi_perfect":
                        statfinal = (int) (statStr *(DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "perfect":
                        statfinal = (int) (statStr *(DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    default:
                        statfinal = (int) (statStr *(DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                }

                break;

            case 4: // Cold Demon
                switch (form){
                    case "second_form":
                        statfinal = (int) (statStr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "third_form":
                        statfinal = (int) (statStr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "final_form":
                        statfinal = (int) (statStr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    default:
                        statfinal = (int) (statStr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                }
                break;

            case 5: // Majin
                switch (form) {
                    case "evil":
                        statfinal = (int) (statStr * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "kid":
                        statfinal = (int) (statStr * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "super":
                        statfinal = (int) (statStr * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "ultra":
                        statfinal = (int) (statStr * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    default:
                        statfinal = (int) (statStr * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                }
                break;
            default:
                // Manejar el caso en que la raza no sea válida
                break;
        }

        return (int) statfinal;
    }

    @Override
    public int calcularDEFCompleta(DMZStatsAttributes stats) {
        int raza = stats.getRace();
        var transformacion = stats.getDmzForm();
        int statDef = stats.getDefense();
        boolean majinOn = stats.hasDMZPermaEffect("majin"); boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        var statfinal = 0;

        var efectosDato = majinDato * frutaDato;

        switch (raza) {
            case 0: // Humano
                switch (transformacion){
                    case "full_power":
                        statfinal = (int) (statDef * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get() * efectosDato));
                        break;
                    case "buffed":
                        statfinal = (int) (statDef * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get() * efectosDato));
                        break;
                    default:
                        statfinal = (int) (statDef * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                }
                break;

            case 1: // Saiyan
                switch (transformacion){
                    case "oozaru":
                        statfinal = (int) (statDef * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "ssgrade1":
                        statfinal = (int) (statDef * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "ssgrade2":
                        statfinal = (int) (statDef * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "ssgrade3":
                        statfinal = (int) (statDef * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    default:
                        statfinal = (int) (statDef * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                }

                break;

            case 2: // Namek
                switch (transformacion) {
                    case "full_power":
                        statfinal = (int) (statDef * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "power_unleashed":
                        statfinal = (int) (statDef * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "giant":
                        statfinal = (int) (statDef * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "orange_giant":
                        statfinal = (int) (statDef * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    default:
                        statfinal = (int) (statDef * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                }
                break;

            case 3: // Bioandroide
                switch (transformacion){
                    case "semi_perfect":
                        statfinal = (int) (statDef *(DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "perfect":
                        statfinal = (int) (statDef *(DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    default:
                        statfinal = (int) (statDef *(DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                }

                break;

            case 4: // Cold Demon
                switch (transformacion){
                    case "second_form":
                        statfinal = (int) (statDef * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "third_form":
                        statfinal = (int) (statDef * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "final_form":
                        statfinal = (int) (statDef * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    default:
                        statfinal = (int) (statDef * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                }
                break;

            case 5: // Majin
                switch (transformacion) {
                    case "evil":
                        statfinal = (int) (statDef * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "kid":
                        statfinal = (int) (statDef * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "super":
                        statfinal = (int) (statDef * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "ultra":
                        statfinal = (int) (statDef * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    default:
                        statfinal = (int) (statDef * (DMZTrMajinConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                }
                break;
            default:
                // Manejar el caso en que la raza no sea válida
                break;
        }

        return (int) statfinal;
    }

    @Override
    public int calcularPWRCompleta(DMZStatsAttributes stats) {
        int raza = stats.getRace();
        var transformacion = stats.getDmzForm(); int statPwr = stats.getKiPower();
        boolean majinOn = stats.hasDMZPermaEffect("majin"); boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        var statfinal = 0;

        var efectosDato = majinDato * frutaDato;

        switch (raza) {
            case 0: // Humano
                switch (transformacion){
                    case "full_power":
                        statfinal = (int) (statPwr * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get() * efectosDato));
                        break;
                    case "buffed":
                        statfinal = (int) (statPwr * (DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get() * efectosDato));
                        break;
                    default:
                        statfinal = (int) (statPwr * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                }
                break;

            case 1: // Saiyan
                switch (transformacion){
                    case "oozaru":
                        statfinal = (int) (statPwr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "ssgrade1":
                        statfinal = (int) (statPwr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "ssgrade2":
                        statfinal = (int) (statPwr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    case "ssgrade3":
                        statfinal = (int) (statPwr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                    default:
                        statfinal = (int) (statPwr * (DMZTrSaiyanConfig.MULTIPLIER_BASE.get()* efectosDato));
                        break;
                }

                break;

            case 2: // Namek
                switch (transformacion) {
                    case "full_power":
                        statfinal = (int) (statPwr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "power_unleashed":
                        statfinal = (int) (statPwr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "giant":
                        statfinal = (int) (statPwr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "orange_giant":
                        statfinal = (int) (statPwr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    default:
                        statfinal = (int) (statPwr * (DMZTrNamekConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                }
                break;

            case 3: // Bioandroide
                switch (transformacion){
                    case "semi_perfect":
                        statfinal = (int) (statPwr * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "perfect":
                        statfinal = (int) (statPwr * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    default:
                        statfinal = (int) (statPwr * (DMZTrBioAndroidConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                }

                break;

            case 4: // Cold Demon
                switch (transformacion){
                    case "second_form":
                        statfinal = (int) (statPwr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "third_form":
                        statfinal = (int) (statPwr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "final_form":
                        statfinal = (int) (statPwr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    default:
                        statfinal = (int) (statPwr * (DMZTrColdDemonConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                }
                break;

            case 5: // Majin
                switch (transformacion) {
                    case "evil":
                        statfinal = (int) (statPwr * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "kid":
                        statfinal = (int) (statPwr * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "super":
                        statfinal = (int) (statPwr * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    case "ultra":
                        statfinal = (int) (statPwr * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                    default:
                        statfinal = (int) (statPwr * (DMZTrMajinConfig.MULTIPLIER_BASE.get() * efectosDato));
                        break;
                }
                break;
            default:
                // Manejar el caso en que la raza no sea válida
                break;
        }

        return (int) statfinal;
    }

    @Override
    public int calcularCargaKi(DMZStatsAttributes stats) {
        String clase = stats.getDmzClass(); int EnergiaTotal = stats.getMaxEnergy();
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
