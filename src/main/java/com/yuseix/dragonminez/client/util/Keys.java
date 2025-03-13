package com.yuseix.dragonminez.client.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keys {

    public static final String CATEGORY = "key.categories.dragonminez.main";

    public static final KeyMapping STATS_MENU = new KeyMapping("key.dragonminez.menu_stats",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, CATEGORY);
    public static final KeyMapping UTILITY_PANEL = new KeyMapping("key.dragonminez.utility_panel",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, CATEGORY);
    public static final KeyMapping TRANSFORM = new KeyMapping("key.dragonminez.transform",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, CATEGORY);
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
    public static final KeyMapping SELECT_LEFT = new KeyMapping("key.dragonminez.select_left",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT, CATEGORY);
    public static final KeyMapping SELECT_RIGHT = new KeyMapping("key.dragonminez.select_right",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT, CATEGORY);
}
