package com.yuseix.dragonminez.common.util;

import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.ModLoadingStage;
import net.minecraftforge.fml.ModLoadingWarning;
import net.minecraftforge.forgespi.language.IModInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for handling mod logging at various levels.
 * <p>
 * This class provides methods for logging messages at different levels, such as
 * INFO and WARNING. It integrates with Forge's logging system and can also issue
 * mod loading warnings when necessary.
 * </p>
 * <p>
 * Since this class only contains utility methods, it should not be instantiated.
 * </p>
 */
public final class LogUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Logs an informational message.
     *
     * @param message The message to log.
     */
    public static void info(String message) {
        LOGGER.info(message);
    }

    /**
     * Logs a warning message.
     *
     * @param message The warning message to log.
     */
    public static void warn(String message) {
        LOGGER.warn(message);
        logModWarning(message);
    }

    /**
     * Logs an error message.
     *
     * @param message The error message to log.
     */
    public static void error(String message) {
        LOGGER.error(message);
    }

    /**
     * Logs a debug message (only appears if debug logging is enabled).
     *
     * @param message The debug message to log.
     */
    public static void debug(String message) {
        LOGGER.debug(message);
    }

    /**
     * Logs a mod loading warning in addition to the standard log warning.
     * <p>
     * This method issues a warning using Forge's {@link ModLoader}, which can be
     * useful for notifying users of potential issues during mod initialization.
     * </p>
     *
     * @param message The warning message to display.
     */
    private static void logModWarning(String message) {
        final IModInfo modInfo = ModLoadingContext.get().getActiveContainer().getModInfo();
        ModLoadingWarning modLoadingWarning = new ModLoadingWarning(modInfo, ModLoadingStage.CONSTRUCT, message);
        ModLoader.get().addWarning(modLoadingWarning);
    }

    // Private constructor to prevent instantiation
    private LogUtil() {}
}
