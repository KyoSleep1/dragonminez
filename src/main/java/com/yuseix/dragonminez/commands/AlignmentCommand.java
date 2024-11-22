package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.Collections;

public class AlignmentCommand {

    public AlignmentCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("dmzalignment")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                .then(Commands.literal("set")
                        .then(Commands.argument("points", IntegerArgumentType.integer())
                                .executes(commandContext -> setPuntos(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                        IntegerArgumentType.getInteger(commandContext, "points")))
                                .then(Commands.argument("player", EntityArgument.players())
                                        .executes(commandContext -> setPuntos(
                                                EntityArgument.getPlayers(commandContext, "player"),
                                                IntegerArgumentType.getInteger(commandContext, "points")))
                                )

                        ))
                .then(Commands.literal("add")
                        .then(Commands.argument("points", IntegerArgumentType.integer())
                                .executes(commandContext -> darPuntos(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                        IntegerArgumentType.getInteger(commandContext, "points")))
                                .then(Commands.argument("player", EntityArgument.players())
                                        .executes(commandContext -> darPuntos(
                                                EntityArgument.getPlayers(commandContext, "player"),
                                                IntegerArgumentType.getInteger(commandContext, "points")))
                                )

                        ))
                .then(Commands.literal("remove")
                        .then(Commands.argument("points", IntegerArgumentType.integer())
                                .executes(commandContext -> removePuntos(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                        IntegerArgumentType.getInteger(commandContext, "points")))
                                .then(Commands.argument("player", EntityArgument.players())
                                        .executes(commandContext -> removePuntos(
                                                EntityArgument.getPlayers(commandContext, "player"),
                                                IntegerArgumentType.getInteger(commandContext, "points")))
                                )

                        ))

        );

    }

    private static int setPuntos(Collection<ServerPlayer> pPlayers, int puntos) {
        for (ServerPlayer player : pPlayers) {

            if (puntos > 100) {
                player.sendSystemMessage(
                        Component.translatable("command.dmzpoints.set", player.getName(), 100)
                );
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.setDmzAlignment(100));
            } else {
                player.sendSystemMessage(
                        Component.translatable("command.dmzpoints.set", player.getName(), puntos)
                );
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.setDmzAlignment(puntos));
            }


        }
        return pPlayers.size();
    }
    private static int darPuntos(Collection<ServerPlayer> pPlayers, int puntos) {
        for (ServerPlayer player : pPlayers) {

            if (puntos > 100) {
                player.sendSystemMessage(
                        Component.translatable("command.dmzpoints.add", player.getName(), 100)
                );
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.addDmzAlignment(100));
            } else {
                player.sendSystemMessage(
                        Component.translatable("command.dmzpoints.add", player.getName(), puntos)
                );
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.addDmzAlignment(puntos));
            }


        }
        return pPlayers.size();
    }
    private static int removePuntos(Collection<ServerPlayer> pPlayers, int puntos) {
        for (ServerPlayer player : pPlayers) {

            if (puntos > 100) {
                player.sendSystemMessage(
                        Component.translatable("command.dmzpoints.remove", player.getName(), 100)
                );
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.removeDmzAlignment(100));
            } else {
                player.sendSystemMessage(
                        Component.translatable("command.dmzpoints.remove", player.getName(), puntos)
                );
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.removeDmzAlignment(puntos));
            }


        }
        return pPlayers.size();
    }
}
