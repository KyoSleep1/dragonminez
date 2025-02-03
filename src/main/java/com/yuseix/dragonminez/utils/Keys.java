package com.yuseix.dragonminez.utils;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keys {

    public static final String CATEGORY = "key.categories.dragonminez.main";

    public static final KeyMapping STATS_MENU = new KeyMapping("key.dragonminez.menu_stats",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, CATEGORY);
    public static final KeyMapping KI_CHARGE = new KeyMapping("key.dragonminez.ki_charge",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, CATEGORY);
    public static final KeyMapping DESCEND_KEY = new KeyMapping("key.dragonminez.descend_key",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, CATEGORY);
    public static final KeyMapping TURBO_KEY = new KeyMapping("key.dragonminez.turbo_key",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, CATEGORY);
    public static final KeyMapping FLY_KEY = new KeyMapping("key.dragonminez.fly_key",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F, CATEGORY);
    public static final KeyMapping FUNCTION = new KeyMapping("key.dragonminez.function",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, CATEGORY);
    public static final KeyMapping SELECT_UP = new KeyMapping("key.dragonminez.select_up",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UP, CATEGORY);
    public static final KeyMapping SELECT_DOWN = new KeyMapping("key.dragonminez.select_down",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_DOWN, CATEGORY);

}
