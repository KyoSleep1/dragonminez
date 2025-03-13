package com.yuseix.dragonminez.client.datagen;

import com.yuseix.dragonminez.common.datagen.DMZItemModelProvider;
import com.yuseix.dragonminez.datagen.DatagenManager;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

/**
 * Manages client-side data generation for the mod.
 * <p>
 * This class is responsible for handling the registration of client-specific data providers
 * during the data generation process. It uses the {@link DatagenManager} to register providers
 * that generate assets needed by the game client.
 * </p>
 * <p>
 * The currently registered client data provider is:
 * <ul>
 *   <li>{@link DMZItemModelProvider}</li>
 * </ul>
 * Additional providers can be added in the future as required.
 * </p>
 */
public class ClientDatagenManager {

    /**
     * Registers client-side data providers.
     * <p>
     * This method is invoked during the {@link GatherDataEvent} to register all necessary client data providers.
     * It retrieves the required context from the event and delegates provider registration to the
     * {@link DatagenManager}. The following provider is registered:
     * <ul>
     *   <li>{@link DMZItemModelProvider}</li>
     * </ul>
     * </p>
     *
     * @param event              the data generation event containing the generation context.
     * @param packOutput         the output location for the generated data.
     * @param lookupProvider     a provider for performing registry lookups.
     * @param existingFileHelper a helper to validate the existence of asset files.
     */
    public static void register(GatherDataEvent event, PackOutput packOutput,
                                CompletableFuture<HolderLookup.Provider> lookupProvider,
                                ExistingFileHelper existingFileHelper) {
        DatagenManager.register(new DMZItemModelProvider(packOutput, existingFileHelper), event.includeClient());
    }
}
