package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.yuseix.dragonminez.world.StructuresCapability;
import com.yuseix.dragonminez.world.StructuresProvider;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.TextColor;

import java.util.concurrent.atomic.AtomicReference;

public class LocationsCommand {

    public LocationsCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("dmzlocate")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2)) // Permiso requerido
                .then(Commands.argument("location", StringArgumentType.word())
                        .suggests((context, builder) -> { // Sugerencias automáticas
                            builder.suggest("KamiLookout");
                            builder.suggest("HyperbolicTimeChamber");
                            builder.suggest("KorinTower");
                            return builder.buildFuture();
                        })
                        .executes(context -> {
                            String location = StringArgumentType.getString(context, "location");
                            CommandSourceStack source = context.getSource();
                            return showLocationMessage(source, location);
                        }))
        );
    }

    /**
     * Muestra un mensaje con la ubicación de una estructura específica.
     */
    private int showLocationMessage(CommandSourceStack source, String location) {
        ServerLevel level = source.getLevel();
        ResourceKey<Level> playerDimension = level.dimension();
        AtomicReference<Component> messageRef = new AtomicReference<>(Component.translatable("command.dmzlocate.unknown_location", location));

        level.getCapability(StructuresProvider.CAPABILITY).ifPresent(structures -> {
            BlockPos pos = null;
            boolean canTeleport = false;

            // Determinar la posición según la ubicación solicitada
            switch (location) {
                case "KamiLookout" -> {
                    pos = structures.getTorreKamisamaPosition();
                    canTeleport = validateDimension(playerDimension, Level.OVERWORLD, source);
                    messageRef.set(createLocationMessage("command.dmzlocate.kamilookout", pos, canTeleport));
                }
                case "HyperbolicTimeChamber" -> {
                    pos = structures.getHabTiempoPos();
                    canTeleport = validateDimension(playerDimension, ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY, source);
                    messageRef.set(createLocationMessage("command.dmzlocate.hyperbolictc", pos, canTeleport));
                }
                case "KorinTower" -> {
                    pos = structures.getTorreKarinPosition();
                    canTeleport = validateDimension(playerDimension, Level.OVERWORLD, source);
                    messageRef.set(createLocationMessage("command.dmzlocate.korintower", pos, canTeleport));
                }
                default -> {
                    messageRef.set(Component.translatable("command.dmzlocate.unknown_location", location));
                }
            }
        });

        // Enviar el mensaje al jugador
        source.sendSuccess(() -> messageRef.get(), false);
        return 1;
    }

    /**
     * Valida si el jugador está en la dimensión correcta.
     */
    private boolean validateDimension(ResourceKey<Level> currentDimension, ResourceKey<Level> requiredDimension, CommandSourceStack source) {
        if (!currentDimension.equals(requiredDimension)) {
            source.sendSuccess(() -> Component.translatable("command.dmzlocate.wrong_dimension").withStyle(ChatFormatting.RED), false);
            return false;
        }
        return true;
    }

    /**
     * Crea un mensaje con la ubicación y un comando de teletransportación.
     */
    private Component createLocationMessage(String structureTranslationKey, BlockPos pos, boolean canTeleport) {
        if (pos == null) {
            return Component.translatable("command.dmzlocate.no_location_found").withStyle(ChatFormatting.RED);
        }

        String coordsText = String.format("X %d Y %d Z %d", pos.getX(), pos.getY(), pos.getZ());
        String teleportCommand = String.format("/teleport @s %d %d %d", pos.getX(), pos.getY(), pos.getZ());

        ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, teleportCommand);
        HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.translatable("command.dmzlocate.teleport_click"));

        Style greenStyle = Style.EMPTY
                .withColor(TextColor.fromRgb(0x00FF00))  // Verde brillante
                .withClickEvent(clickEvent)
                .withHoverEvent(hoverEvent);

        Component coordsComponent = Component.literal(coordsText).setStyle(greenStyle).withStyle(ChatFormatting.UNDERLINE);

        if (canTeleport) {
            Component teleportMessage = Component.translatable("command.dmzlocate.teleport_click")
                    .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFFF00))); // Amarillo
            return Component.translatable(structureTranslationKey + ".location")
                    .append(Component.literal(" "))
                    .append(coordsComponent)
                    .append(Component.literal(" "))
                    .append(teleportMessage);
        }

        return Component.translatable(structureTranslationKey + ".location")
                .append(coordsComponent);
    }
}