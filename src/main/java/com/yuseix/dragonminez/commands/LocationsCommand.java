package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
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

import java.util.Locale;
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
                            builder.suggest("GokuHouse");
                            builder.suggest("KameHouse");
                            builder.suggest("ElderGuru");
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
            switch (location.toLowerCase(Locale.ROOT)) {
                case "kamilookout" -> {
                    pos = structures.getTorreKamisamaPosition();
                    canTeleport = validateDimension(playerDimension, Level.OVERWORLD, source);
                    messageRef.set(createLocationMessage("command.dmzlocate.kamilookout", pos, canTeleport, source));
                }
                case "hyperbolictimechamber" -> {
                    if (playerDimension.equals(ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY)) {
                        pos = new BlockPos(structures.getHabTiempoPos().getX()+70, structures.getHabTiempoPos().getY()+3, structures.getHabTiempoPos().getZ()+7);
                        canTeleport = validateDimension(playerDimension, ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY, source);
                    } else if (playerDimension.equals(Level.OVERWORLD)) {
                        pos = structures.getPortalHabTiempoPosition();
                        canTeleport = validateDimension(playerDimension, Level.OVERWORLD, source);
                    }
                    messageRef.set(createLocationMessage("command.dmzlocate.hyperbolictc", pos, canTeleport, source));
                }
                case "korintower" -> {
                    pos = structures.getTorreKarinPosition();
                    canTeleport = validateDimension(playerDimension, Level.OVERWORLD, source);
                    messageRef.set(createLocationMessage("command.dmzlocate.korintower", pos, canTeleport, source));
                }
                case "gokuhouse" -> {
                    pos = structures.getGokuHousePosition();
                    canTeleport = validateDimension(playerDimension, Level.OVERWORLD, source);
                    messageRef.set(createLocationMessage("command.dmzlocate.gokuhouse", pos, canTeleport, source));
                }
                case "kamehouse" -> {
                    pos = structures.getRoshiHousePosition();
                    canTeleport = validateDimension(playerDimension, Level.OVERWORLD, source);
                    messageRef.set(createLocationMessage("command.dmzlocate.kamehouse", pos, canTeleport, source));
                }
                case "elderguru" -> {
                    pos = structures.getElderGuruPosition();
                    canTeleport = validateDimension(playerDimension, ModDimensions.NAMEK_DIM_LEVEL_KEY, source);
                    messageRef.set(createLocationMessage("command.dmzlocate.grandelderguru", pos, canTeleport, source));
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
    private Component createLocationMessage(String structureTranslationKey, BlockPos pos, boolean canTeleport, CommandSourceStack source) {
        // Coordenadas del jugador
        BlockPos playerPos = new BlockPos(source.getPlayer().getBlockX(), source.getPlayer().getBlockY(), source.getPlayer().getBlockZ());

        // Calcular distancia
        int distance = (int) Math.sqrt(playerPos.distSqr(pos));
        String coordsText = String.format("[%d, %d, %d]", pos.getX(), pos.getY(), pos.getZ());
        String teleportCommand = String.format("/teleport @s %d %d %d", pos.getX(), pos.getY(), pos.getZ());

        ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, teleportCommand);
        HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.translatable("command.dmzlocate.teleport_click"));

        Style greenStyle = Style.EMPTY
                .withColor(ChatFormatting.GREEN)  // Verde brillante
                .withClickEvent(clickEvent)
                .withHoverEvent(hoverEvent);

        Component coordsComponent = Component.literal(coordsText).setStyle(greenStyle);

        if (canTeleport) {
            Component coordsMessage = Component.translatable("command.dmzlocate.distance_coords", distance);
            return Component.translatable(structureTranslationKey + ".location")
                    .append(Component.literal(" "))
                    .append(coordsComponent)
                    .append(Component.literal(" "))
                    .append(coordsMessage);
        }
        return Component.translatable(structureTranslationKey + ".location")
                .append(coordsComponent);
    }
}