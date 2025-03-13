package com.yuseix.dragonminez.server.datagen;

import com.yuseix.dragonminez.server.datagen.impl.AdvancementsProvider;
import com.yuseix.dragonminez.common.datagen.impl.BlockStateProvider;
import com.yuseix.dragonminez.server.datagen.impl.BlockTagGenerator;
import com.yuseix.dragonminez.server.datagen.impl.ItemTagGenerator;
import com.yuseix.dragonminez.server.datagen.impl.RecipeProvider;
import com.yuseix.dragonminez.server.datagen.impl.LootTableProvider;
import com.yuseix.dragonminez.server.datagen.impl.ModWorldGenProvider;
import com.yuseix.dragonminez.common.datagen.DatagenManager;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Manages server-side data generation for the mod.
 * <p>
 * This class is responsible for registering all server-related data providers during the data generation process.
 * It handles the registration of providers for recipes, loot tables, block states, block and item tags, advancements,
 * and world generation. The providers are registered using the {@link DatagenManager} during the {@link GatherDataEvent}.
 * </p>
 * <p>
 * The providers registered include:
 * <ul>
 *   <li>{@link RecipeProvider}</li>
 *   <li>{@link LootTableProvider}</li>
 *   <li>{@link BlockStateProvider}</li>
 *   <li>{@link BlockTagGenerator} and corresponding {@link ItemTagGenerator}</li>
 *   <li>{@link AdvancementsProvider}</li>
 *   <li>{@link ModWorldGenProvider}</li>
 * </ul>
 * </p>
 */
public class ServerDatagenManager {

    /**
     * Registers the necessary data providers for server-side data generation.
     * <p>
     * This method retrieves the {@link DataGenerator} from the event and uses it to register various data providers.
     * If the generator is unavailable, no providers are registered.
     * </p>
     *
     * @param event              the data generation event containing the context and configuration.
     * @param packOutput         the output destination for generated data.
     * @param lookupProvider     a provider for performing registry lookups for blocks, items, and entities.
     * @param existingFileHelper a helper to verify the existence of asset files.
     */
    public static void register(GatherDataEvent event, PackOutput packOutput,
                                CompletableFuture<HolderLookup.Provider> lookupProvider,
                                ExistingFileHelper existingFileHelper) {
        final DataGenerator generator = event.getGenerator();
        if (generator == null) return;

        DatagenManager.register(new RecipeProvider(packOutput), event.includeServer());
        DatagenManager.register(LootTableProvider.create(packOutput), event.includeServer());
        DatagenManager.register(new BlockStateProvider(packOutput, existingFileHelper), event.includeServer());

        final BlockTagGenerator blockTagGenerator = DatagenManager.register(
                new BlockTagGenerator(packOutput, lookupProvider, existingFileHelper), event.includeServer());
        DatagenManager.register(new ItemTagGenerator(packOutput, lookupProvider,
                Objects.requireNonNull(blockTagGenerator).contentsGetter(), existingFileHelper), event.includeServer());

        DatagenManager.register(new AdvancementsProvider(packOutput, lookupProvider), event.includeServer());
        DatagenManager.register(new ModWorldGenProvider(packOutput, lookupProvider), event.includeServer());
    }
}
