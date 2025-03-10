package com.yuseix.dragonminez.utils;

public class DMZClientConfig {

    private static int maxStats = 5000;
    private static double multiplierZPoints = 1.2;
    private static double majin_multi = 1.5;
    private static double tree_might_multi = 1.3;

    private static int ini_str_human_warrior = 5, ini_str_human_spiritualist = 5;
    private static int ini_def_human_warrior = 5, ini_def_human_spiritualist = 5;
    private static int ini_con_human_warrior = 5, ini_con_human_spiritualist = 5;
    private static int ini_pwr_human_warrior = 5, ini_pwr_human_spiritualist = 5;
    private static int ini_ene_human_warrior = 5, ini_ene_human_spiritualist = 5;

    private static int ini_str_saiyan_warrior = 5, ini_str_saiyan_spiritualist = 5;
    private static int ini_def_saiyan_warrior = 5, ini_def_saiyan_spiritualist = 5;
    private static int ini_con_saiyan_warrior = 5, ini_con_saiyan_spiritualist = 5;
    private static int ini_pwr_saiyan_warrior = 5, ini_pwr_saiyan_spiritualist = 5;
    private static int ini_ene_saiyan_warrior = 5, ini_ene_saiyan_spiritualist = 5;

    private static int ini_str_namek_warrior = 5, ini_str_namek_spiritualist = 5;
    private static int ini_def_namek_warrior = 5, ini_def_namek_spiritualist = 5;
    private static int ini_con_namek_warrior = 5, ini_con_namek_spiritualist = 5;
    private static int ini_pwr_namek_warrior = 5, ini_pwr_namek_spiritualist = 5;
    private static int ini_ene_namek_warrior = 5, ini_ene_namek_spiritualist = 5;

    private static int ini_str_bio_warrior = 5, ini_str_bio_spiritualist = 5;
    private static int ini_def_bio_warrior = 5, ini_def_bio_spiritualist = 5;
    private static int ini_con_bio_warrior = 5, ini_con_bio_spiritualist = 5;
    private static int ini_pwr_bio_warrior = 5, ini_pwr_bio_spiritualist = 5;
    private static int ini_ene_bio_warrior = 5, ini_ene_bio_spiritualist = 5 ;

    private static int ini_str_cold_warrior = 5, ini_str_cold_spiritualist = 5;
    private static int ini_def_cold_warrior = 5, ini_def_cold_spiritualist = 5;
    private static int ini_con_cold_warrior = 5, ini_con_cold_spiritualist = 5;
    private static int ini_pwr_cold_warrior = 5, ini_pwr_cold_spiritualist = 5;
    private static int ini_ene_cold_warrior = 5, ini_ene_cold_spiritualist = 5;

    private static int ini_str_majin_warrior = 5, ini_str_majin_spiritualist = 5;
    private static int ini_def_majin_warrior = 5, ini_def_majin_spiritualist = 5;
    private static int ini_con_majin_warrior = 5, ini_con_majin_spiritualist = 5;
    private static int ini_pwr_majin_warrior = 5, ini_pwr_majin_spiritualist = 5;
    private static int ini_ene_majin_warrior = 5, ini_ene_majin_spiritualist = 5;

    private static int transfTPCost = 50000;

    public static int getInit_StrStat(String race, String clase) {
        int initStat = 0;
        switch (clase){
            case "warrior":
                switch (race){
                    case "saiyan"-> initStat = ini_str_saiyan_warrior;
                    case "namek"->initStat = ini_str_namek_warrior;
                    case "bio" -> initStat = ini_str_bio_warrior;
                    case "cold" -> initStat = ini_str_cold_warrior;
                    case "majin" -> initStat = ini_str_majin_warrior;
                    default ->initStat = ini_str_human_warrior;
                }
            break;
            default:
                switch (race){
                    case "saiyan"-> initStat = ini_str_saiyan_spiritualist;
                    case "namek"->initStat = ini_str_namek_spiritualist;
                    case "bio" -> initStat = ini_str_bio_spiritualist;
                    case "cold" -> initStat = ini_str_cold_spiritualist;
                    case "majin" -> initStat = ini_str_majin_spiritualist;
                    default ->initStat = ini_str_human_spiritualist;
                }
                break;
        }

        return initStat;
    }
    public static int getInit_DefStat(String race, String clase) {
        int initStat = 0;
        switch (clase){
            case "warrior":
                switch (race){
                    case "saiyan" -> initStat = ini_def_saiyan_warrior;
                    case "namek" -> initStat = ini_def_namek_warrior;
                    case "bio" -> initStat = ini_def_bio_warrior;
                    case "cold" -> initStat = ini_def_cold_warrior;
                    case "majin" -> initStat = ini_def_majin_warrior;
                    default -> initStat = ini_def_human_warrior;
                }
                break;
            default:
                switch (race){
                    case "saiyan"->initStat = ini_def_saiyan_spiritualist;
                    case "namek"->initStat = ini_def_namek_spiritualist;
                    case "bio"->initStat = ini_def_bio_spiritualist;
                    case "cold"->initStat = ini_def_cold_spiritualist;
                    case "majin"->initStat = ini_def_majin_spiritualist;
                    default->initStat = ini_def_human_spiritualist;
                }
                break;
        }


        return initStat;
    }
    public static int getInit_ConStat(String race, String clase) {
        int initStat = 0;
        switch (clase){
            case "warrior":
                switch (race){
                    case "saiyan"->initStat = ini_con_saiyan_warrior;
                    case "namek"->initStat = ini_con_namek_warrior;
                    case "bio"->initStat = ini_con_bio_warrior;
                    case "cold"->initStat = ini_con_cold_warrior;
                    case "majin"->initStat = ini_con_majin_warrior;
                    default -> initStat = ini_con_human_warrior;
                }
                break;
            default:
                switch (race){
                    case "saiyan"->initStat = ini_con_saiyan_spiritualist;
                    case "namek"->initStat = ini_con_namek_spiritualist;
                    case "bio"->initStat = ini_con_bio_spiritualist;
                    case "cold"->initStat = ini_con_cold_spiritualist;
                    case "majin"->initStat = ini_con_majin_spiritualist;
                    default -> initStat = ini_con_human_spiritualist;
                }
                break;
        }

        return initStat;
    }
    public static int getInit_PWRStat(String race, String clase) {
        int initStat = 0;
        switch (clase){
            case "warrior":
                switch (race){
                    case "saiyan"->initStat = ini_pwr_saiyan_warrior;
                    case "namek"->initStat = ini_pwr_namek_warrior;
                    case "bio"->initStat = ini_pwr_bio_warrior;
                    case "cold"->initStat = ini_pwr_cold_warrior;
                    case "majin"->initStat = ini_pwr_majin_warrior;
                    default->initStat = ini_pwr_human_warrior;
                }
                break;
            default:
                switch (race){
                    case "saiyan"->initStat = ini_pwr_saiyan_spiritualist;
                    case "namek"->initStat = ini_pwr_namek_spiritualist;
                    case "bio"->initStat = ini_pwr_bio_spiritualist;
                    case "cold"->initStat = ini_pwr_cold_spiritualist;
                    case "majin"->initStat = ini_pwr_majin_spiritualist;
                    default->initStat = ini_pwr_human_spiritualist;
                }
                break;
        }

        return initStat;
    }
    public static int getInit_ENEStat(String race, String clase) {
        int initStat = 0;
        switch (clase){
            case "warrior":
                switch (race){
                    case "saiyan" -> initStat = ini_ene_saiyan_warrior;
                    case "namek" -> initStat = ini_ene_namek_warrior;
                    case "bio" -> initStat = ini_ene_bio_warrior;
                    case "cold" -> initStat = ini_ene_cold_warrior;
                    case "majin" -> initStat = ini_ene_majin_warrior;
                    default -> initStat = ini_ene_human_warrior;
                }
                break;
            default:
                switch (race){
                    case "saiyan" -> initStat = ini_ene_saiyan_spiritualist;
                    case "namek" -> initStat = ini_ene_namek_spiritualist;
                    case "bio" -> initStat = ini_ene_bio_spiritualist;
                    case "cold" -> initStat = ini_ene_cold_spiritualist;
                    case "majin" -> initStat = ini_ene_majin_spiritualist;
                    default -> initStat = ini_ene_human_spiritualist;
                }
                break;
        }

        return initStat;
    }
    public static void setInit_StrStat(String race, String clase, int value) {
        switch (clase){
            case "warrior":
                switch (race){
                    case "saiyan" -> DMZClientConfig.ini_str_saiyan_warrior = value;
                    case "namek" -> DMZClientConfig.ini_str_namek_warrior = value;
                    case "bio" -> DMZClientConfig.ini_str_bio_warrior = value;
                    case "cold" -> DMZClientConfig.ini_str_cold_warrior = value;
                    case "majin" -> DMZClientConfig.ini_str_majin_warrior = value;
                    default ->DMZClientConfig.ini_str_human_warrior = value;
                }
                break;
            default:
                switch (race){
                    case "saiyan" -> DMZClientConfig.ini_str_saiyan_spiritualist = value;
                    case "namek" -> DMZClientConfig.ini_str_namek_spiritualist = value;
                    case "bio" -> DMZClientConfig.ini_str_bio_spiritualist = value;
                    case "cold" -> DMZClientConfig.ini_str_cold_spiritualist = value;
                    case "majin" -> DMZClientConfig.ini_str_majin_spiritualist = value;
                    default ->DMZClientConfig.ini_str_human_spiritualist = value;
                }
                break;
        }

    }

    public static void setInit_DefStat(String race, String clase, int value) {
        switch (clase){
            case "warrior":
                switch (race){
                    case "saiyan" -> DMZClientConfig.ini_def_saiyan_warrior = value;
                    case "namek" -> DMZClientConfig.ini_def_namek_warrior = value;
                    case "bio" -> DMZClientConfig.ini_def_bio_warrior = value;
                    case "cold" -> DMZClientConfig.ini_def_cold_warrior = value;
                    case "majin" -> DMZClientConfig.ini_def_majin_warrior = value;
                    default -> DMZClientConfig.ini_def_human_warrior = value;
                }
                break;
            default:
                switch (race){
                    case "saiyan" -> DMZClientConfig.ini_def_saiyan_spiritualist = value;
                    case "namek" -> DMZClientConfig.ini_def_namek_spiritualist = value;
                    case "bio" -> DMZClientConfig.ini_def_bio_spiritualist = value;
                    case "cold" -> DMZClientConfig.ini_def_cold_spiritualist = value;
                    case "majin" -> DMZClientConfig.ini_def_majin_spiritualist = value;
                    default -> DMZClientConfig.ini_def_human_spiritualist = value;
                }
                break;
        }

    }
    public static void setInit_ConStat(String race, String clase, int value) {
        switch (clase){
            case "warrior":
                switch (race){
                    case "saiyan" -> DMZClientConfig.ini_con_saiyan_warrior = value;
                    case "namek" -> DMZClientConfig.ini_con_namek_warrior = value;
                    case "bio" -> DMZClientConfig.ini_con_bio_warrior = value;
                    case "cold" -> DMZClientConfig.ini_con_cold_warrior = value;
                    case "majin" -> DMZClientConfig.ini_con_majin_warrior = value;
                    default -> DMZClientConfig.ini_con_human_warrior = value;
                }
                break;
            default:
                switch (race){
                    case "saiyan" -> DMZClientConfig.ini_con_saiyan_spiritualist = value;
                    case "namek" -> DMZClientConfig.ini_con_namek_spiritualist = value;
                    case "bio" -> DMZClientConfig.ini_con_bio_spiritualist = value;
                    case "cold" -> DMZClientConfig.ini_con_cold_spiritualist = value;
                    case "majin" -> DMZClientConfig.ini_con_majin_spiritualist = value;
                    default -> DMZClientConfig.ini_con_human_spiritualist = value;
                }
                break;
        }

    }
    public static void setInit_PWRStat(String race, String clase, int value) {
        switch (clase){
            case "warrior":
                switch (race){
                    case "saiyan"->DMZClientConfig.ini_pwr_saiyan_warrior = value;
                    case "namek"->DMZClientConfig.ini_pwr_namek_warrior = value;
                    case "bio"->DMZClientConfig.ini_pwr_bio_warrior = value;
                    case "cold"->DMZClientConfig.ini_pwr_cold_warrior = value;
                    case "majin"->DMZClientConfig.ini_pwr_majin_warrior = value;
                    default->DMZClientConfig.ini_pwr_human_warrior = value;
                }
                break;
            default:
                switch (race){
                    case "saiyan"->DMZClientConfig.ini_pwr_saiyan_spiritualist = value;
                    case "namek"->DMZClientConfig.ini_pwr_namek_spiritualist = value;
                    case "bio"->DMZClientConfig.ini_pwr_bio_spiritualist = value;
                    case "cold"->DMZClientConfig.ini_pwr_cold_spiritualist = value;
                    case "majin"->DMZClientConfig.ini_pwr_majin_spiritualist = value;
                    default->DMZClientConfig.ini_pwr_human_spiritualist = value;
                }
                break;
        }

    }
    public static void setInit_ENEStat(String race, String clase, int value) {
        switch (clase){
            case "warrior":
                switch (race){
                    case "saiyan" -> DMZClientConfig.ini_ene_saiyan_warrior = value;
                    case "namek" -> DMZClientConfig.ini_ene_namek_warrior = value;
                    case "bio" -> DMZClientConfig.ini_ene_bio_warrior = value;
                    case "cold" -> DMZClientConfig.ini_ene_cold_warrior = value;
                    case "majin" -> DMZClientConfig.ini_ene_majin_warrior = value;
                    default -> DMZClientConfig.ini_ene_human_warrior = value;
                }
                break;
            default:
                switch (race){
                    case "saiyan" -> DMZClientConfig.ini_ene_saiyan_spiritualist= value;
                    case "namek" -> DMZClientConfig.ini_ene_namek_spiritualist = value;
                    case "bio" -> DMZClientConfig.ini_ene_bio_spiritualist = value;
                    case "cold" -> DMZClientConfig.ini_ene_cold_spiritualist = value;
                    case "majin" -> DMZClientConfig.ini_ene_majin_spiritualist = value;
                    default -> DMZClientConfig.ini_ene_human_spiritualist = value;
                }
                break;
        }

    }
    public static double getMajin_multi() {
        return majin_multi;
    }

    public static void setMajin_multi(double majin_multi) {
        DMZClientConfig.majin_multi = majin_multi;
    }

    public static double getTree_might_multi() {
        return tree_might_multi;
    }

    public static void setTree_might_multi(double tree_might_multi) {
        DMZClientConfig.tree_might_multi = tree_might_multi;
    }

    public static int getMaxStats() {
        return maxStats;
    }

    public static void setMaxStats(int value) {
        maxStats = value;
    }

    public static double getMultiplierZPoints() {
        return multiplierZPoints;
    }

    public static void setMultiplierZPoints(double value) {
        multiplierZPoints = value;
    }

    public static int getTransfTPCost() {
        return transfTPCost;
    }

    public static void setTransfTPCost(int value) {
        transfTPCost = value;
    }

}
