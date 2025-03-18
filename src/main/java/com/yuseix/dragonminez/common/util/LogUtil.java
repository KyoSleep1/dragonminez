package com.yuseix.dragonminez.common.util;

import com.yuseix.dragonminez.common.Reference;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
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
     * Logs an informational message with one parameter.
     *
     * @param message The message to log.
     */
    public static void info(String message, Object p0) {
        LOGGER.info(message, p0);
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
    public static void logModWarning(String message) {
        final IModInfo modInfo = ModLoadingContext.get().getActiveContainer().getModInfo();
        ModLoadingWarning modLoadingWarning = new ModLoadingWarning(modInfo, ModLoadingStage.CONSTRUCT, message);
        ModLoader.get().addWarning(modLoadingWarning);
    }

    /**
     * Creates a custom crash report and terminates the game.
     * <p>
     * This method generates a detailed crash report with mod-specific information
     * and forces the game to close.
     * </p>
     *
     * @param errorMessage The message to include in the crash report.
     */
    public static void crash(String errorMessage) {

        // Create a custom crash report
        final CrashReport crashReport = new CrashReport("Critical Mod Error", new Throwable(errorMessage));
        final CrashReportCategory category = crashReport.addCategory("Mod Information");
        category.setDetail("Mod ID", Reference.MOD_ID);
        category.setDetail("Cause", errorMessage + ". Please report this issue via ticket with " +
                "the complete crash report file!");

        // Terminate the game
        throw new ReportedException(crashReport);
    }

    // Private constructor to prevent instantiation
    private LogUtil() {}
}
