package com.yuseix.dragonminez.utils;

import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import org.slf4j.Logger;

public final class DebugUtils {
	private static final Logger LOGGER = LogUtils.getLogger();

	private static final String PREFIX = ChatFormatting.YELLOW + "[DragonMine Z] " + ChatFormatting.RESET;

	public static void dmzSout(String message) {
		System.out.println("[DMZ-DEBUG] " + message);
	}

	public static void dmzLog(String message) {
		LOGGER.info(message);
	}

}
