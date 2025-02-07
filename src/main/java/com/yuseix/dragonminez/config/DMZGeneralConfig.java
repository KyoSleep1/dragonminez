package com.yuseix.dragonminez.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DMZGeneralConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_ATTRIBUTE_VALUE;

    //CONFIGS GENERALES
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_FALLDMG;
    public static final ForgeConfigSpec.ConfigValue<Integer> PERHIT_ZPOINTS_GAIN;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ZPOINTS_COST;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ZPOINTS_GAIN;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ZPOINTS_HTC;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_TREE_MIGHT;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_MAJIN;

    public static final ForgeConfigSpec.ConfigValue<Integer> SENZU_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> SENZU_GIVE;
    public static final ForgeConfigSpec.ConfigValue<Integer> SENZU_DAILY_COOLDOWN;

    public static final ForgeConfigSpec.ConfigValue<Integer> DBALL_SPAWN_RANGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> SENZU_SHENRON_WISH;
    public static final ForgeConfigSpec.ConfigValue<Integer> CAPSULE_SHENRON_WISH;
    public static final ForgeConfigSpec.ConfigValue<Integer> SENZU_PORUNGA_WISH;
    public static final ForgeConfigSpec.ConfigValue<Integer> CAPSULE_PORUNGA_WISH;
    public static final ForgeConfigSpec.ConfigValue<Integer> GETE_PORUNGA_WISH;

    public static final ForgeConfigSpec.ConfigValue<Integer> JUMP_TP_COST_MASTER;
    public static final ForgeConfigSpec.ConfigValue<Integer> JUMP_TP_COST_LEVELS;
    public static final ForgeConfigSpec.ConfigValue<Integer> FLY_TP_COST_MASTER;
    public static final ForgeConfigSpec.ConfigValue<Integer> FLY_TP_COST_LEVELS;
    public static final ForgeConfigSpec.ConfigValue<Integer> POTUNLOCK_TP_COST_MASTER;
    public static final ForgeConfigSpec.ConfigValue<Integer> POTUNLOCK_TP_COST_LEVELS;
    public static final ForgeConfigSpec.ConfigValue<Integer> MEDITATION_TP_COST_MASTER;
    public static final ForgeConfigSpec.ConfigValue<Integer> MEDITATION_TP_COST_LEVELS;
    public static final ForgeConfigSpec.ConfigValue<Integer> KI_CONTROL_TP_COST_MASTER;
    public static final ForgeConfigSpec.ConfigValue<Integer> KI_CONTROL_TP_COST_LEVELS;
    public static final ForgeConfigSpec.ConfigValue<Integer> KI_MANIPULATION_TP_COST_MASTER;
    public static final ForgeConfigSpec.ConfigValue<Integer> KI_MANIPULATION_TP_COST_LEVELS;



    static {
        BUILDER.push("Configs for Attributes of DragonMineZ");

        MAX_ATTRIBUTE_VALUE = BUILDER.comment("Max Attributes! (Min: 100 / Max: 10000 / Default: 1000)")
                .defineInRange("Attributes: ", 1000, 100, 10000);

        BUILDER.pop();

        BUILDER.push("ZPoints Configs");

        PERHIT_ZPOINTS_GAIN = BUILDER.comment("ZPoints Obtained per Hit (Min: 1 / Max: 100 / Default: 2)")
                .defineInRange("ZPoints per Hit: ", 2, 1, 100);

        MULTIPLIER_ZPOINTS_COST = BUILDER.comment("Multiplier for ZPoints Cost (Min: 1.0 / Max: 20.0 / Default: 1.2)")
                .defineInRange("ZPoints Cost: ", 1.2, 1.0, 20.0);

        MULTIPLIER_ZPOINTS_GAIN = BUILDER.comment("Multiplier for ZPoints Gain (Min: 1.0 / Max: 20.0 / Default: 1.2)")
                .defineInRange("ZPoints Gain: ", 1.2, 1.0, 20.0);

        MULTIPLIER_ZPOINTS_HTC = BUILDER.comment("Multiplier for ZPoints in the Hyperbolic Time Chamber (Min: 1.0 / Max: 20.0 / Default: 8.0)")
                .defineInRange("ZPoints (HTC): ", 8.0, 1.0, 60.0);


        BUILDER.pop();


        BUILDER.push("General Configs");

        MULTIPLIER_FALLDMG = BUILDER.comment("Fall Damage Multiplier Percentage (Min: 0.01 / Max: 1.00 / Default: 0.03)")
                .defineInRange("FallDmg: ", 0.03, 0.01, 1.00);

        SENZU_COOLDOWN = BUILDER.comment("Cooldown for Senzu Beans (Min: 1 / Max: 64 / Default: 10)")
                .defineInRange("Seconds: ", 10, 1, 64);

        SENZU_GIVE = BUILDER.comment("Number of Senzu Beans the Master Korin will give (Min: 1 / Max: 10 / Default: 5)")
                .defineInRange("Number: ", 5, 1, 10);

        SENZU_DAILY_COOLDOWN = BUILDER.comment("Wait time to claim the next Senzu Bean (seconds) (Min: 1 / Max: 36000 / Default: 300)")
                .defineInRange("Time: ", 300, 1, 36000);

        MULTIPLIER_TREE_MIGHT = BUILDER.comment("Multiplier for the Fruit of the Tree of Might Effect (Min: 1.0 / Max: 20.0 / Default: 1.3)")
                .defineInRange("MightFruit Multiplier: ", 1.3, 1.0, 20.0);

        MULTIPLIER_MAJIN = BUILDER.comment("Multiplier for the Majin Mark Effect (Min: 1.0 / Max: 20.0 / Default: 1.5)")
                .defineInRange("Majin Multiplier: ", 1.5, 1.0, 20.0);

        BUILDER.pop();

        BUILDER.push("Shenron / Porunga Wishes");

        DBALL_SPAWN_RANGE = BUILDER.comment("Range in blocks for the Dragon Balls to spawn (Min: 2000 / Max: 20000 / Default: 3000)")
                .defineInRange("Spawn Range: ", 3000, 2000, 20000);

        SENZU_SHENRON_WISH = BUILDER.comment("Number of Senzu Beans Shenron will give (Min: 1 / Max: 64 / Default: 4)")
                .defineInRange("[Shenron] Number of Senzus: ", 4, 1, 64);

        CAPSULE_SHENRON_WISH = BUILDER.comment("Number of Capsules Shenron will give (Min: 1 / Max: 64 / Default: 2)")
                .defineInRange("[Shenron] Number of Capsules: ", 2, 1, 64);

        SENZU_PORUNGA_WISH = BUILDER.comment("Number of Senzu Beans Porunga will give (Min: 1 / Max: 64 / Default: 8)")
                .defineInRange("[Porunga] Number of Senzus: ", 8, 1, 64);

        CAPSULE_PORUNGA_WISH = BUILDER.comment("Number of Capsules Porunga will give (Min: 1 / Max: 64 / Default: 3)")
                .defineInRange("[Porunga] Number of Capsules: ", 3, 1, 64);

        GETE_PORUNGA_WISH = BUILDER.comment("Number of Gete Scraps Porunga will give (Min: 1 / Max: 64 / Default: 3)")
                .defineInRange("[Porunga] Number of Gete Scraps: ", 3, 1, 64);

        BUILDER.pop();

        BUILDER.push("Skills Configs");

        JUMP_TP_COST_MASTER = BUILDER.comment("ZPoints Cost for Buying the Jump Skill from Masters (Min: 1 / Max: 1000000000 / Default: 400)")
                .defineInRange("Jump Buy: ", 400, 1, 1000000000);

        JUMP_TP_COST_LEVELS = BUILDER.comment("ZPoints Cost for Leveling Up the Jump Skill (Min: 1 / Max: 1000000000 / Default: 250) (Formula: Cost * Level * ZPointsCostMultiplier)")
                .defineInRange("Jump Levels: ", 250, 1, 1000000000);

        FLY_TP_COST_MASTER = BUILDER.comment("ZPoints Cost for Buying the Fly Skill from Masters (Min: 1 / Max: 1000000000 / Default: 1750)")
                .defineInRange("Fly Buy: ", 1750, 1, 1000000000);

        FLY_TP_COST_LEVELS = BUILDER.comment("ZPoints Cost for Leveling Up the Fly Skill (Min: 1 / Max: 1000000000 / Default: 750) (Formula: Cost * Level * ZPointsCostMultiplier)")
                .defineInRange("Fly Levels: ", 750, 1, 1000000000);

        POTUNLOCK_TP_COST_MASTER = BUILDER.comment("ZPoints Cost for Buying the Potential Unlock Skill from Masters (Min: 1 / Max: 1000000000 / Default: 3500)")
                .defineInRange("Potential Unlock Buy: ", 3500, 1, 1000000000);

        POTUNLOCK_TP_COST_LEVELS = BUILDER.comment("ZPoints Cost for Leveling Up the Potential Unlock Skill (Min: 1 / Max: 1000000000 / Default: 600) (Formula: Cost * Level * ZPointsCostMultiplier)")
                .defineInRange("Potential Unlock Levels: ", 1300, 1, 1000000000);

        MEDITATION_TP_COST_MASTER = BUILDER.comment("ZPoints Cost for Buying the Meditation Skill from Masters (Min: 1 / Max: 1000000000 / Default: 500)")
                .defineInRange("Meditation Buy: ", 500, 1, 1000000000);

        MEDITATION_TP_COST_LEVELS = BUILDER.comment("ZPoints Cost for Leveling Up the Meditation Skill (Min: 1 / Max: 1000000000 / Default: 150) (Formula: Cost * Level * ZPointsCostMultiplier)")
                .defineInRange("Meditation Levels: ", 150, 1, 1000000000);

        KI_CONTROL_TP_COST_MASTER = BUILDER.comment("ZPoints Cost for Buying the Ki Control Skill from Masters (Min: 1 / Max: 1000000000 / Default: 500)")
                .defineInRange("Ki Control Buy: ", 500, 1, 1000000000);

        KI_CONTROL_TP_COST_LEVELS = BUILDER.comment("ZPoints Cost for Leveling Up the Ki Control Skill (Min: 1 / Max: 1000000000 / Default: 100) (Formula: Cost * Level * ZPointsCostMultiplier)")
                .defineInRange("Ki Control Levels: ", 100, 1, 1000000000);

        KI_MANIPULATION_TP_COST_MASTER = BUILDER.comment("ZPoints Cost for Buying the Ki Manipulation Skill from Masters (Min: 1 / Max: 1000000000 / Default: 12500)")
                .defineInRange("Ki Manipulation Buy: ", 12500, 1, 1000000000);

        KI_MANIPULATION_TP_COST_LEVELS = BUILDER.comment("ZPoints Cost for Leveling Up the Ki Manipulation Skill (Min: 1 / Max: 1000000000 / Default: 5000) (Formula: Cost * Level * ZPointsCostMultiplier)")
                .defineInRange("Ki Manipulation Levels: ", 5000, 1, 1000000000);

        SPEC = BUILDER.build();
    }
}
