package com.yuseix.dragonminez.common.datagen;

import com.yuseix.dragonminez.client.datagen.ClientDatagenManager;
import com.yuseix.dragonminez.common.Reference;
import com.yuseix.dragonminez.common.util.LogUtil;
import com.yuseix.dragonminez.server.datagen.ServerDatagenManager;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

/**
 * Manages the mod's data generation process.
 * <p>
 * This class listens for the {@link GatherDataEvent} to obtain a {@link DataGenerator} instance,
 * which is then used to register data providers for both the client and server sides. It also
 * provides utility methods to access the generator and add providers, ensuring that access occurs
 * only during the valid data generation phase. If the generator is accessed after data generation,
 * a fatal error is logged and the game is terminated.
 * </p>
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DatagenManager {

    /** Cached DataGenerator for registering data providers. */
    private static DataGenerator generator;

    /**
     * Invoked during the data generation event to capture the DataGenerator and delegate
     * provider registration.
     * <p>
     * This method retrieves the {@link DataGenerator} and its associated components (such as
     * {@link PackOutput}, a lookup provider, and an {@link ExistingFileHelper}) from the event,
     * and passes them to the client and server registration managers. Once registration is complete,
     * the generator reference is cleared to prevent further access.
     * </p>
     *
     * @param event the data generation event containing the necessary context and configuration.
     */
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        generator = event.getGenerator();

        final PackOutput packOutput = generator.getPackOutput();
        final CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        ClientDatagenManager.register(event, packOutput, lookupProvider, existingFileHelper);
        ServerDatagenManager.register(event, packOutput, lookupProvider, existingFileHelper);

        // Prevent further access by clearing the generator after registration.
        generator = null;
    }

    /**
     * Registers a data provider with the current DataGenerator.
     * <p>
     * If the generator is no longer available (i.e. accessed after data generation), this method
     * logs a fatal error and terminates the game.
     * </p>
     *
     * @param provider the data provider to register.
     * @param include  whether the provider should be included in data generation.
     * @param <T>      the type of data provider.
     * @return the registered provider instance, or {@code null} if the generator is unavailable.
     */
    @SuppressWarnings("unchecked")
    public static <T extends DataProvider> T register(DataProvider provider, boolean include) {
        if (generator == null) {
            crash();
            return null;
        }
        return (T) generator.addProvider(include, provider);
    }

    /**
     * Retrieves the active DataGenerator.
     * <p>
     * This method returns the current DataGenerator if available. If the generator is already
     * cleared (i.e. data generation has ended), a fatal error is logged and the game terminates.
     * </p>
     *
     * @return the active DataGenerator, or {@code null} if unavailable.
     */
    public static DataGenerator generator() {
        if (generator == null) {
            crash();
            return null;
        }
        return generator;
    }

    /**
     * Logs a fatal error and terminates the game.
     * <p>
     * This method is called when the DataGenerator is accessed after the valid data generation
     * phase, indicating a critical issue. The error is logged and the game is terminated to prevent
     * further problems.
     * </p>
     */
    private static void crash() {
        LogUtil.crash("FATAL ERROR: Attempted to access the DataGenerator after the data generation phase. " +
                "The game will now terminate. Please report this issue with the complete crash log.");
    }
}
