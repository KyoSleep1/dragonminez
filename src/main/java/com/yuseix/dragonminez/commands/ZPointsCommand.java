package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.Collections;

public class ZPointsCommand {

    public ZPointsCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("dmzpoints")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                .then(Commands.literal("set")
                        .then(Commands.argument("points", IntegerArgumentType.integer()).
                                executes(commandContext -> setPuntos(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                        IntegerArgumentType.getInteger(commandContext, "points"),
                                        commandContext.getSource()))
                                .then(Commands.argument("player", EntityArgument.players())
                                        .executes(commandContext -> setPuntos(
                                                EntityArgument.getPlayers(commandContext, "player"),
                                                IntegerArgumentType.getInteger(commandContext, "points"),
                                                commandContext.getSource()))
                                )

                        ))
                .then(Commands.literal("add")
                        .then(Commands.argument("points", IntegerArgumentType.integer()).
                                executes(commandContext -> darPuntos(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                        IntegerArgumentType.getInteger(commandContext, "points"),
                                        commandContext.getSource()))
                                .then(Commands.argument("player", EntityArgument.players())
                                        .executes(commandContext -> darPuntos(
                                                EntityArgument.getPlayers(commandContext, "player"),
                                                IntegerArgumentType.getInteger(commandContext, "points"),
                                                commandContext.getSource()))
                                )

                        ))
                .then(Commands.literal("remove")
                        .then(Commands.argument("points", IntegerArgumentType.integer()).
                                executes(commandContext -> removePuntos(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                        IntegerArgumentType.getInteger(commandContext, "points"),
                                        commandContext.getSource()))
                                .then(Commands.argument("player", EntityArgument.players())
                                        .executes(commandContext -> removePuntos(
                                                EntityArgument.getPlayers(commandContext, "player"),
                                                IntegerArgumentType.getInteger(commandContext, "points"),
                                                commandContext.getSource()))
                                )

                        ))

        );

    }

    private static int setPuntos(Collection<ServerPlayer> pPlayers, int puntos, CommandSourceStack source) throws CommandSyntaxException {
        for (ServerPlayer player : pPlayers) {
            if (pPlayers.size() == 1 && player == source.getPlayerOrException()) {
                source.sendSystemMessage(Component.translatable("command.dmzpoints.self.set", puntos));
            } else {
                source.sendSystemMessage(Component.translatable("command.dmzpoints.set", player.getName(), puntos));
            }

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.setIntValue("tps", puntos));
        }
        return pPlayers.size();
    }

    private static int darPuntos(Collection<ServerPlayer> pPlayers, int puntos, CommandSourceStack source) throws CommandSyntaxException {
        for (ServerPlayer player : pPlayers) {
            if (pPlayers.size() == 1 && player == source.getPlayerOrException()) {
                source.sendSystemMessage(Component.translatable("command.dmzpoints.self.add", puntos));
            } else {
                source.sendSystemMessage(Component.translatable("command.dmzpoints.add", puntos, player.getName()));
            }

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.addIntValue("tps", puntos));
        }
        return pPlayers.size();
    }

    private static int removePuntos(Collection<ServerPlayer> pPlayers, int puntos, CommandSourceStack source) throws CommandSyntaxException {
        for (ServerPlayer player : pPlayers) {
            if (pPlayers.size() == 1 && player == source.getPlayerOrException()) {
                source.sendSystemMessage(Component.translatable("command.dmzpoints.self.remove", puntos));
            } else {
                source.sendSystemMessage(Component.translatable("command.dmzpoints.remove", puntos, player.getName()));
            }

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.removeIntValue("tps", puntos));
        }
        return pPlayers.size();
    }
}
