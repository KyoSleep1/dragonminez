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
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        var efectosTotal = majinDato * frutaDato;

        double multRaza = obtenerStatRaza(stats.getRace(), stats.getDmzClass(), "STR");
        double multTransf = obtenerStatRaza(stats.getRace(), stats.getDmzForm(), "STR");

        // Fórmula = ((((1 + (StatSTR / 10)) * ConfigRaza) * (Transf * Efectos)) * (Porcentaje / 10))
        return (int) Math.ceil((((1 + ((double) stats.getStrength() / 10)) * multRaza) * (multTransf * efectosTotal)) * ((double)stats.getDmzRelease()/10));
    }

    @Override
    public int calcularDEF(DMZStatsAttributes stats, Player player) {
        boolean majinOn = stats.hasDMZPermaEffect("majin"); boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        double efectosTotal = majinDato * frutaDato;

        int DefensaArmor = player.getArmorValue(); int DurezaArmor = Mth.floor(player.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
        int armorTotal = (DefensaArmor + DurezaArmor) * 3;

        double multRaza = obtenerStatRaza(stats.getRace(), stats.getDmzClass(), "DEF");
        double multTransf = obtenerStatRaza(stats.getRace(), stats.getDmzForm(), "DEF");

        // Fórmula = (((((StatDEF * ConfigRaza) * (Transf * Efectos)) * Porcentaje)) / 6) + ((DefensaArmor) + (DurezaArmor))
        return (int) Math.ceil((((((double) stats.getDefense() / 4) * multRaza) * (multTransf * efectosTotal)) * ((double)stats.getDmzRelease()/10)) / 5)  + armorTotal;
    }

    @Override
    public int calcularCON(DMZStatsAttributes stats) {

        double multRaza = obtenerStatRaza(stats.getRace(), stats.getDmzClass(), "CON");

        // Fórmula = Math.round(20 + (1.2 * (StatCON * ConfigRaza)))
        return (int) Math.round(20 + (1.2 * (stats.getConstitution() * multRaza) * 1.6));
    }

    @Override
    public int calcularSTM(DMZStatsAttributes stats) {
        double multRaza = obtenerStatRaza(stats.getRace(), stats.getDmzClass(), "STM");

        // Fórmula = Math.round((MaxCON * 0.85) * multRaza)
        return (int) Math.round((stats.getMaxHealth() * 0.85) * multRaza);
    }

    @Override
    public int calcularKiPower(DMZStatsAttributes stats) {
        boolean majinOn = stats.hasDMZPermaEffect("majin");boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        var efectosTotal = majinDato * frutaDato;

        double multRaza = obtenerStatRaza(stats.getRace(), stats.getDmzClass(), "PWR");
        double multTransf = obtenerStatRaza(stats.getRace(), stats.getDmzForm(), "PWR");

        // Fórmula = Math.ceil((StatPWR * ConfigRaza * (Transf * Efectos)) * (Porcentaje / 10))
        return (int) Math.ceil((stats.getKiPower() * multRaza * (multTransf * efectosTotal)) * ((double)stats.getDmzRelease()/10));
    }

    @Override
    public int calcularENE(DMZStatsAttributes stats) {
        double multRaza = obtenerStatRaza(stats.getRace(), stats.getDmzClass(), "ENE");

        // Fórmula = Math.round(3 * StatENE * ConfigRaza + 40 * 3)
        return (int) Math.round(3 * stats.getEnergy() * multRaza + 40 * 3);
    }

    @Override
    public int calcularKiConsume(DMZStatsAttributes stats) {
		// Fórmula = No hay, es consumo directo. Si en la config SSJ_KI_COST.get() = 50, se consume 50 de ki por segundo.
        return (int) obtenerStatTransf(stats.getRace(), stats.getDmzForm(), "COST");
    }

    @Override
    public int calcularKiRegen(DMZStatsAttributes stats) {
        double multRaza = obtenerStatRaza(stats.getRace(), stats.getDmzClass(), "REGEN");

        // Fórmula = Math.ceil(EnergiaTotal * ConfigRaza)

        return (int) Math.ceil(stats.getEnergy() * multRaza);
    }

    @Override
    public double calcularMultiTotal(DMZStatsAttributes stats) {
        boolean majinOn = stats.hasDMZPermaEffect("majin"); boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        var efectosTotal = majinDato * frutaDato;

        double multiSTR = obtenerStatTransf(stats.getRace(), stats.getDmzForm(), "STR");
        double multiDEF = obtenerStatTransf(stats.getRace(), stats.getDmzForm(), "DEF");
        double multiPWR = obtenerStatTransf(stats.getRace(), stats.getDmzForm(), "PWR");

        // Promedio, pq si se tiene x1 STR, x1 DEF y x1 PWR, debería ser x1 en Total y no x3
        return ((multiSTR + multiDEF + multiPWR) / 3) * efectosTotal;
    }

    @Override
    public double calcularMultiStat(DMZStatsAttributes stats, String stat) {
        boolean majinOn = stats.hasDMZPermaEffect("majin"); boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        var efectosTotal = majinDato * frutaDato;

        double multiTransf = obtenerStatTransf(stats.getRace(), stats.getDmzForm(), stat);

        return multiTransf * efectosTotal;
    }

    public double calcularMultiTransf(DMZStatsAttributes stats) {
        double multiStr = obtenerStatTransf(stats.getRace(), stats.getDmzForm(), "STR");
        double multiDef = obtenerStatTransf(stats.getRace(), stats.getDmzForm(), "DEF");
        double multiKiPower = obtenerStatTransf(stats.getRace(), stats.getDmzForm(), "PWR");

        return (multiStr + multiDef + multiKiPower) / 3;
    }

    @Override
    public int calcularSTRCompleta(DMZStatsAttributes stats) {
        boolean majinOn = stats.hasDMZPermaEffect("majin"); boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        var efectosDato = majinDato * frutaDato;

        double multForma = obtenerStatTransf(stats.getRace(), stats.getDmzForm(), "STR");

        // Fórmula = (statStr * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosDato))
        return (int) (stats.getStrength() * multForma * efectosDato);
    }

    @Override
    public int calcularDEFCompleta(DMZStatsAttributes stats) {
        boolean majinOn = stats.hasDMZPermaEffect("majin"); boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        var efectosDato = majinDato * frutaDato;

        double multForma = obtenerStatTransf(stats.getRace(), stats.getDmzForm(), "DEF");

        // Fórmula = (statDef * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosDato))
        return (int) (stats.getDefense() * multForma * efectosDato);
    }

    @Override
    public int calcularPWRCompleta(DMZStatsAttributes stats) {
        boolean majinOn = stats.hasDMZPermaEffect("majin"); boolean mightfruit = stats.hasDMZTemporalEffect("mightfruit");
        double majinDato = majinOn ? DMZGeneralConfig.MULTIPLIER_MAJIN.get() : 1; // 1 si no está activo para que no afecte
        double frutaDato = mightfruit ? DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get() : 1;
        var efectosDato = majinDato * frutaDato;

        double multForma = obtenerStatTransf(stats.getRace(), stats.getDmzForm(), "PWR");

        // Fórmula = (statPwr * (DMZTrHumanConfig.MULTIPLIER_BASE.get() * efectosDato))
        return (int) (stats.getKiPower() * multForma * efectosDato);
    }

    @Override
    public int calcularCargaKi(DMZStatsAttributes stats) {
		return switch (stats.getDmzClass()) {
			case "Warrior" -> (int) Math.ceil((stats.getMaxEnergy() * 0.02));
			case "Spiritualist" -> (int) Math.ceil((stats.getMaxEnergy() * 0.04));
			default -> 0;
		};
    }

    public double obtenerStatRaza(int raza, String clase, String stat) {
        return switch (stat) {
            case "STR" -> switch (raza) {
                case 0 -> clase.equals("Warrior") ? DMZHumanConfig.MULTIPLIER_STR_WARRIOR.get()
                        : DMZHumanConfig.MULTIPLIER_STR_SPIRITUALIST.get();
                case 1 -> clase.equals("Warrior") ? DMZSaiyanConfig.MULTIPLIER_STR_WARRIOR.get()
                        : DMZSaiyanConfig.MULTIPLIER_STR_SPIRITUALIST.get();
                case 2 -> clase.equals("Warrior") ? DMZNamekConfig.MULTIPLIER_STR_WARRIOR.get()
                        : DMZNamekConfig.MULTIPLIER_STR_SPIRITUALIST.get();
                case 3 -> clase.equals("Warrior") ? DMZBioAndroidConfig.MULTIPLIER_STR_WARRIOR.get()
                        : DMZBioAndroidConfig.MULTIPLIER_STR_SPIRITUALIST.get();
                case 4 -> clase.equals("Warrior") ? DMZColdDemonConfig.MULTIPLIER_STR_WARRIOR.get()
                        : DMZColdDemonConfig.MULTIPLIER_STR_SPIRITUALIST.get();
                case 5 -> clase.equals("Warrior") ? DMZMajinConfig.MULTIPLIER_STR_WARRIOR.get()
                        : DMZMajinConfig.MULTIPLIER_STR_SPIRITUALIST.get();
                default -> 1.0;
            };
            case "DEF" -> switch (raza) {
                case 0 -> clase.equals("Warrior") ? DMZHumanConfig.MULTIPLIER_DEF_WARRIOR.get()
                        : DMZHumanConfig.MULTIPLIER_DEF_SPIRITUALIST.get();
                case 1 -> clase.equals("Warrior") ? DMZSaiyanConfig.MULTIPLIER_DEF_WARRIOR.get()
                        : DMZSaiyanConfig.MULTIPLIER_DEF_SPIRITUALIST.get();
                case 2 -> clase.equals("Warrior") ? DMZNamekConfig.MULTIPLIER_DEF_WARRIOR.get()
                        : DMZNamekConfig.MULTIPLIER_DEF_SPIRITUALIST.get();
                case 3 -> clase.equals("Warrior") ? DMZBioAndroidConfig.MULTIPLIER_DEF_WARRIOR.get()
                        : DMZBioAndroidConfig.MULTIPLIER_DEF_SPIRITUALIST.get();
                case 4 -> clase.equals("Warrior") ? DMZColdDemonConfig.MULTIPLIER_DEF_WARRIOR.get()
                        : DMZColdDemonConfig.MULTIPLIER_DEF_SPIRITUALIST.get();
                case 5 -> clase.equals("Warrior") ? DMZMajinConfig.MULTIPLIER_DEF_WARRIOR.get()
                        : DMZMajinConfig.MULTIPLIER_DEF_SPIRITUALIST.get();
                default -> 1.0;
            };
            case "CON" -> switch (raza) {
                case 0 -> clase.equals("Warrior") ? DMZHumanConfig.MULTIPLIER_CON_WARRIOR.get()
                        : DMZHumanConfig.MULTIPLIER_CON_SPIRITUALIST.get();
                case 1 -> clase.equals("Warrior") ? DMZSaiyanConfig.MULTIPLIER_CON_WARRIOR.get()
                        : DMZSaiyanConfig.MULTIPLIER_CON_SPIRITUALIST.get();
                case 2 -> clase.equals("Warrior") ? DMZNamekConfig.MULTIPLIER_CON_WARRIOR.get()
                        : DMZNamekConfig.MULTIPLIER_CON_SPIRITUALIST.get();
                case 3 -> clase.equals("Warrior") ? DMZBioAndroidConfig.MULTIPLIER_CON_WARRIOR.get()
                        : DMZBioAndroidConfig.MULTIPLIER_CON_SPIRITUALIST.get();
                case 4 -> clase.equals("Warrior") ? DMZColdDemonConfig.MULTIPLIER_CON_WARRIOR.get()
                        : DMZColdDemonConfig.MULTIPLIER_CON_SPIRITUALIST.get();
                case 5 -> clase.equals("Warrior") ? DMZMajinConfig.MULTIPLIER_CON_WARRIOR.get()
                        : DMZMajinConfig.MULTIPLIER_CON_SPIRITUALIST.get();
                default -> 1.0;
            };
            case "PWR" -> switch (raza) {
                case 0 -> clase.equals("Warrior") ? DMZHumanConfig.MULTIPLIER_KIPOWER_WARRIOR.get()
                        : DMZHumanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get();
                case 1 -> clase.equals("Warrior") ? DMZSaiyanConfig.MULTIPLIER_KIPOWER_WARRIOR.get()
                        : DMZSaiyanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get();
                case 2 -> clase.equals("Warrior") ? DMZNamekConfig.MULTIPLIER_KIPOWER_WARRIOR.get()
                        : DMZNamekConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get();
                case 3 -> clase.equals("Warrior") ? DMZBioAndroidConfig.MULTIPLIER_KIPOWER_WARRIOR.get()
                        : DMZBioAndroidConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get();
                case 4 -> clase.equals("Warrior") ? DMZColdDemonConfig.MULTIPLIER_KIPOWER_WARRIOR.get()
                        : DMZColdDemonConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get();
                case 5 -> clase.equals("Warrior") ? DMZMajinConfig.MULTIPLIER_KIPOWER_WARRIOR.get()
                        : DMZMajinConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get();
                default -> 1.0;
            };
            case "ENE" -> switch (raza) {
              case 0 -> clase.equals("Warrior") ? DMZHumanConfig.MULTIPLIER_ENERGY_WARRIOR.get()
                      : DMZHumanConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get();
              case 1 -> clase.equals("Warrior") ? DMZSaiyanConfig.MULTIPLIER_ENERGY_WARRIOR.get()
                      : DMZSaiyanConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get();
              case 2 -> clase.equals("Warrior") ? DMZNamekConfig.MULTIPLIER_ENERGY_WARRIOR.get()
                      : DMZNamekConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get();
              case 3 -> clase.equals("Warrior") ? DMZBioAndroidConfig.MULTIPLIER_ENERGY_WARRIOR.get()
                      : DMZBioAndroidConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get();
              case 4 -> clase.equals("Warrior") ? DMZColdDemonConfig.MULTIPLIER_ENERGY_WARRIOR.get()
                      : DMZColdDemonConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get();
              case 5 -> clase.equals("Warrior") ? DMZMajinConfig.MULTIPLIER_ENERGY_WARRIOR.get()
                      : DMZMajinConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get();
              default -> 1.0;
            };
            case "REGEN" -> switch (raza) {
                case 0 -> clase.equals("Warrior") ? DMZHumanConfig.KI_REGEN_WARRIOR.get()
                        : DMZHumanConfig.KI_REGEN_SPIRITUALIST.get();
                case 1 -> clase.equals("Warrior") ? DMZSaiyanConfig.KI_REGEN_WARRIOR.get()
                        : DMZSaiyanConfig.KI_REGEN_SPIRITUALIST.get();
                case 2 -> clase.equals("Warrior") ? DMZNamekConfig.KI_REGEN_WARRIOR.get()
                        : DMZNamekConfig.KI_REGEN_SPIRITUALIST.get();
                case 3 -> clase.equals("Warrior") ? DMZBioAndroidConfig.KI_REGEN_WARRIOR.get()
                        : DMZBioAndroidConfig.KI_REGEN_SPIRITUALIST.get();
                case 4 -> clase.equals("Warrior") ? DMZColdDemonConfig.KI_REGEN_WARRIOR.get()
                        : DMZColdDemonConfig.KI_REGEN_SPIRITUALIST.get();
                case 5 -> clase.equals("Warrior") ? DMZMajinConfig.KI_REGEN_WARRIOR.get()
                        : DMZMajinConfig.KI_REGEN_SPIRITUALIST.get();
                default -> 1.0;
            };
            default -> 1.0;
        };
    }

    public double obtenerStatTransf(int raza, String transformation, String stat) {
        return switch (stat) {
            case "STR" -> switch (raza) {
                case 0 -> switch (transformation) { // Humanos
                    case "buffed" -> DMZTrHumanConfig.MULTIPLIER_BUFFED_FORM_STR.get();
                    case "full_power" -> DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get();
                    case "potential_unleashed" -> DMZTrHumanConfig.MULTIPLIER_PU_FORM_STR.get();
                    default -> DMZTrHumanConfig.MULTIPLIER_BASE.get();
                };
                case 1 -> switch (transformation) { // Saiyans
                    case "oozaru" -> DMZTrSaiyanConfig.MULTIPLIER_OOZARU_FORM_STR.get();
                    case "ssj" -> DMZTrSaiyanConfig.MULTIPLIER_SSJ_FORM_STR.get();
                    case "ssgrade2" -> DMZTrSaiyanConfig.MULTIPLIER_SSGRADE2_FORM_STR.get();
                    case "ssgrade3" -> DMZTrSaiyanConfig.MULTIPLIER_SSGRADE3_FORM_STR.get();
                    case "mssj" -> DMZTrSaiyanConfig.MULTIPLIER_MSSJ_FORM_STR.get();
                    case "ssj2" -> DMZTrSaiyanConfig.MULTIPLIER_SSJ2_FORM_STR.get();
                    case "ssj3" -> DMZTrSaiyanConfig.MULTIPLIER_SSJ3_FORM_STR.get();
                    case "golden_oozaru" -> DMZTrSaiyanConfig.MULTIPLIER_GOLDENOOZARU_FORM_STR.get();
                    default -> DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                };
                case 2 -> switch (transformation) { // Namekianos
                    case "giant" -> DMZTrNamekConfig.MULTIPLIER_GIANT_FORM_STR.get();
                    case "full_power" -> DMZTrNamekConfig.MULTIPLIER_FP_FORM_STR.get();
                    case "super_namek" -> DMZTrNamekConfig.MULTIPLIER_SUPER_NAMEK_FORM_STR.get();
                    default -> DMZTrNamekConfig.MULTIPLIER_BASE.get();
                };
                case 3 -> switch (transformation) { // BioAndroides
                    case "semi_perfect" -> DMZTrBioAndroidConfig.MULTIPLIER_SEMIPERFECT_FORM_STR.get();
                    case "perfect" -> DMZTrBioAndroidConfig.MULTIPLIER_PERFECT_FORM_STR.get();
                    default -> DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                };
                case 4 -> switch (transformation) { // Cold Demons
                    case "second_form" -> DMZTrColdDemonConfig.MULTIPLIER_SECOND_FORM_STR.get();
                    case "third_form" -> DMZTrColdDemonConfig.MULTIPLIER_THIRD_FORM_STR.get();
                    case "final_form" -> DMZTrColdDemonConfig.MULTIPLIER_FOURTH_FORM_STR.get();
                    case "full_power" -> DMZTrColdDemonConfig.MULTIPLIER_FULL_POWER_FORM_STR.get();
                    default -> DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                };
                case 5 -> switch (transformation) { // Majin
                    case "evil" -> DMZTrMajinConfig.MULTIPLIER_EVIL_FORM_STR.get();
                    case "kid" -> DMZTrMajinConfig.MULTIPLIER_KID_FORM_STR.get();
                    case "super" -> DMZTrMajinConfig.MULTIPLIER_SUPER_FORM_STR.get();
                    case "ultra" -> DMZTrMajinConfig.MULTIPLIER_ULTRA_FORM_STR.get();
                    default -> DMZTrMajinConfig.MULTIPLIER_BASE.get();
                };
                default -> 1.0;
            };
            case "DEF" -> switch (raza) {
                case 0 -> switch (transformation) { // Humanos
                    case "buffed" -> DMZTrHumanConfig.MULTIPLIER_BUFFED_FORM_DEF.get();
                    case "full_power" -> DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get();
                    case "potential_unleashed" -> DMZTrHumanConfig.MULTIPLIER_PU_FORM_DEF.get();
                    default -> DMZTrHumanConfig.MULTIPLIER_BASE.get();
                };
                case 1 -> switch (transformation) { // Saiyans
                    case "oozaru" -> DMZTrSaiyanConfig.MULTIPLIER_OOZARU_FORM_DEF.get();
                    case "ssj" -> DMZTrSaiyanConfig.MULTIPLIER_SSJ_FORM_DEF.get();
                    case "ssgrade2" -> DMZTrSaiyanConfig.MULTIPLIER_SSGRADE2_FORM_DEF.get();
                    case "ssgrade3" -> DMZTrSaiyanConfig.MULTIPLIER_SSGRADE3_FORM_DEF.get();
                    case "mssj" -> DMZTrSaiyanConfig.MULTIPLIER_MSSJ_FORM_DEF.get();
                    case "ssj2" -> DMZTrSaiyanConfig.MULTIPLIER_SSJ2_FORM_DEF.get();
                    case "ssj3" -> DMZTrSaiyanConfig.MULTIPLIER_SSJ3_FORM_DEF.get();
                    case "golden_oozaru" -> DMZTrSaiyanConfig.MULTIPLIER_GOLDENOOZARU_FORM_DEF.get();
                    default -> DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                };
                case 2 -> switch (transformation) { // Namekianos
                    case "giant" -> DMZTrNamekConfig.MULTIPLIER_GIANT_FORM_DEF.get();
                    case "full_power" -> DMZTrNamekConfig.MULTIPLIER_FP_FORM_DEF.get();
                    case "super_namek" -> DMZTrNamekConfig.MULTIPLIER_SUPER_NAMEK_FORM_DEF.get();
                    default -> DMZTrNamekConfig.MULTIPLIER_BASE.get();
                };
                case 3 -> switch (transformation) { // BioAndroides
                    case "semi_perfect" -> DMZTrBioAndroidConfig.MULTIPLIER_SEMIPERFECT_FORM_DEF.get();
                    case "perfect" -> DMZTrBioAndroidConfig.MULTIPLIER_PERFECT_FORM_DEF.get();
                    default -> DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                };
                case 4 -> switch (transformation) { // Cold Demons
                    case "second_form" -> DMZTrColdDemonConfig.MULTIPLIER_SECOND_FORM_DEF.get();
                    case "third_form" -> DMZTrColdDemonConfig.MULTIPLIER_THIRD_FORM_DEF.get();
                    case "final_form" -> DMZTrColdDemonConfig.MULTIPLIER_FOURTH_FORM_DEF.get();
                    case "full_power" -> DMZTrColdDemonConfig.MULTIPLIER_FULL_POWER_FORM_DEF.get();
                    default -> DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                };
                case 5 -> switch (transformation) { // Majin
                    case "evil" -> DMZTrMajinConfig.MULTIPLIER_EVIL_FORM_DEF.get();
                    case "kid" -> DMZTrMajinConfig.MULTIPLIER_KID_FORM_DEF.get();
                    case "super" -> DMZTrMajinConfig.MULTIPLIER_SUPER_FORM_DEF.get();
                    case "ultra" -> DMZTrMajinConfig.MULTIPLIER_ULTRA_FORM_DEF.get();
                    default -> DMZTrMajinConfig.MULTIPLIER_BASE.get();
                };
                default -> 1.0;
            };
            case "PWR" -> switch (raza) {
                case 0 -> switch (transformation) { // Humanos
                    case "buffed" -> DMZTrHumanConfig.MULTIPLIER_BUFFED_FORM_PWR.get();
                    case "full_power" -> DMZTrHumanConfig.MULTIPLIER_FP_FORM_PWR.get();
                    case "potential_unleashed" -> DMZTrHumanConfig.MULTIPLIER_PU_FORM_PWR.get();
                    default -> DMZTrHumanConfig.MULTIPLIER_BASE.get();
                };
                case 1 -> switch (transformation) { // Saiyans
                    case "oozaru" -> DMZTrSaiyanConfig.MULTIPLIER_OOZARU_FORM_PWR.get();
                    case "ssj" -> DMZTrSaiyanConfig.MULTIPLIER_SSJ_FORM_PWR.get();
                    case "ssgrade2" -> DMZTrSaiyanConfig.MULTIPLIER_SSGRADE2_FORM_PWR.get();
                    case "ssgrade3" -> DMZTrSaiyanConfig.MULTIPLIER_SSGRADE3_FORM_PWR.get();
                    case "mssj" -> DMZTrSaiyanConfig.MULTIPLIER_MSSJ_FORM_PWR.get();
                    case "ssj2" -> DMZTrSaiyanConfig.MULTIPLIER_SSJ2_FORM_PWR.get();
                    case "ssj3" -> DMZTrSaiyanConfig.MULTIPLIER_SSJ3_FORM_PWR.get();
                    case "golden_oozaru" -> DMZTrSaiyanConfig.MULTIPLIER_GOLDENOOZARU_FORM_PWR.get();
                    default -> DMZTrSaiyanConfig.MULTIPLIER_BASE.get();
                };
                case 2 -> switch (transformation) { // Namekianos
                    case "giant" -> DMZTrNamekConfig.MULTIPLIER_GIANT_FORM_PWR.get();
                    case "full_power" -> DMZTrNamekConfig.MULTIPLIER_FP_FORM_PWR.get();
                    case "super_namek" -> DMZTrNamekConfig.MULTIPLIER_SUPER_NAMEK_FORM_PWR.get();
                    default -> DMZTrNamekConfig.MULTIPLIER_BASE.get();
                };
                case 3 -> switch (transformation) { // BioAndroides
                    case "semi_perfect" -> DMZTrBioAndroidConfig.MULTIPLIER_SEMIPERFECT_FORM_PWR.get();
                    case "perfect" -> DMZTrBioAndroidConfig.MULTIPLIER_PERFECT_FORM_PWR.get();
                    default -> DMZTrBioAndroidConfig.MULTIPLIER_BASE.get();
                };
                case 4 -> switch (transformation) { // Cold Demons
                    case "second_form" -> DMZTrColdDemonConfig.MULTIPLIER_SECOND_FORM_PWR.get();
                    case "third_form" -> DMZTrColdDemonConfig.MULTIPLIER_THIRD_FORM_PWR.get();
                    case "final_form" -> DMZTrColdDemonConfig.MULTIPLIER_FOURTH_FORM_PWR.get();
                    case "full_power" -> DMZTrColdDemonConfig.MULTIPLIER_FULL_POWER_FORM_PWR.get();
                    default -> DMZTrColdDemonConfig.MULTIPLIER_BASE.get();
                };
                case 5 -> switch (transformation) { // Majin
                    case "evil" -> DMZTrMajinConfig.MULTIPLIER_EVIL_FORM_PWR.get();
                    case "kid" -> DMZTrMajinConfig.MULTIPLIER_KID_FORM_PWR.get();
                    case "super" -> DMZTrMajinConfig.MULTIPLIER_SUPER_FORM_PWR.get();
                    case "ultra" -> DMZTrMajinConfig.MULTIPLIER_ULTRA_FORM_PWR.get();
                    default -> DMZTrMajinConfig.MULTIPLIER_BASE.get();
                };
                default -> 1.0;
            };
            case "COST" -> switch (raza) {
                case 0 -> switch (transformation) { // Humanos
                    case "buffed" -> (double) DMZTrHumanConfig.BUFFED_FORM_KI_COST.get();
                    case "full_power" -> (double) DMZTrHumanConfig.FP_FORM_KI_COST.get();
                    case "potential_unleashed" -> (double) DMZTrHumanConfig.PU_FORM_KI_COST.get();
                    default -> (double) DMZTrHumanConfig.BASE_FORM_KI_COST.get();
                };
                case 1 -> switch (transformation) {
                    case "oozaru" -> (double) DMZTrSaiyanConfig.OOZARU_FORM_KI_COST.get();
                    case "ssj" -> (double) DMZTrSaiyanConfig.SSJ_FORM_KI_COST.get();
                    case "ssgrade2" -> (double) DMZTrSaiyanConfig.SSGRADE2_FORM_KI_COST.get();
                    case "ssgrade3" -> (double) DMZTrSaiyanConfig.SSGRADE3_FORM_KI_COST.get();
                    case "mssj" -> (double) DMZTrSaiyanConfig.MSSJ_FORM_KI_COST.get();
                    case "ssj2" -> (double) DMZTrSaiyanConfig.SSJ2_FORM_KI_COST.get();
                    case "ssj3" -> (double) DMZTrSaiyanConfig.SSJ3_FORM_KI_COST.get();
                    case "golden_oozaru" -> (double) DMZTrSaiyanConfig.GOLDENOOZARU_FORM_KI_COST.get();
                    default -> (double) DMZTrSaiyanConfig.BASE_FORM_KI_COST.get();
                };
                case 2 -> switch (transformation) {
                    case "giant" -> (double) DMZTrNamekConfig.GIANT_FORM_KI_COST.get();
                    case "full_power" -> (double) DMZTrNamekConfig.FP_FORM_KI_COST.get();
                    case "super_namek" -> (double) DMZTrNamekConfig.SUPER_NAMEK_FORM_KI_COST.get();
                    default -> (double) DMZTrNamekConfig.BASE_FORM_KI_COST.get();
                };
                case 3 -> switch (transformation) {
                    case "semi_perfect" -> (double) DMZTrBioAndroidConfig.SEMIPERFECT_FORM_KI_COST.get();
                    case "perfect" -> (double) DMZTrBioAndroidConfig.PERFECT_FORM_KI_COST.get();
                    default -> (double) DMZTrBioAndroidConfig.BASE_FORM_KI_COST.get();
                };
                case 4 -> switch (transformation) {
                    case "second_form" -> (double) DMZTrColdDemonConfig.SECOND_FORM_KI_COST.get();
                    case "third_form" -> (double) DMZTrColdDemonConfig.THIRD_FORM_KI_COST.get();
                    case "final_form" -> (double) DMZTrColdDemonConfig.FOURTH_FORM_KI_COST.get();
                    case "full_power" -> (double) DMZTrColdDemonConfig.FULL_POWER_FORM_KI_COST.get();
                    default -> (double) DMZTrColdDemonConfig.BASE_FORM_KI_COST.get();
                };
                case 5 -> switch (transformation) {
                    case "evil" -> (double) DMZTrMajinConfig.EVIL_FORM_KI_COST.get();
                    case "kid" -> (double) DMZTrMajinConfig.KID_FORM_KI_COST.get();
                    case "super" -> (double) DMZTrMajinConfig.SUPER_FORM_KI_COST.get();
                    case "ultra" -> (double) DMZTrMajinConfig.ULTRA_FORM_KI_COST.get();
                    default -> (double) DMZTrMajinConfig.BASE_FORM_KI_COST.get();
                };
                default -> 1.0;
            };
            default -> 1.0;
        };
    }

    public double transfMultMenu(DMZStatsAttributes stats, String transformation) {
        double str = obtenerStatTransf(stats.getRace(), transformation, "STR");
        double def = obtenerStatTransf(stats.getRace(), transformation, "DEF");
        double pwr = obtenerStatTransf(stats.getRace(), transformation, "PWR");

        return (str + def + pwr) / 3;
    }
}
