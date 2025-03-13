package com.yuseix.dragonminez.server.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.yuseix.dragonminez.common.config.DMZGeneralConfig;
import com.yuseix.dragonminez.common.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.common.stats.DMZStatsProvider;
import com.yuseix.dragonminez.common.util.DMZDatos;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Collection;
import java.util.Collections;

public class StatsCommand {

    public StatsCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("dmzstats")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))


                .then(Commands.literal("set")
                        .then(Commands.argument("stat", StringArgumentType.string())
                                .suggests((commandContext, suggestionsBuilder) -> {
                                    suggestionsBuilder.suggest("strenght");
                                    suggestionsBuilder.suggest("defense");
                                    suggestionsBuilder.suggest("constitution");
                                    suggestionsBuilder.suggest("kipower");
                                    suggestionsBuilder.suggest("energy");
                                    suggestionsBuilder.suggest("all");
                                    return suggestionsBuilder.buildFuture();
                                })
                                .then(Commands.argument("quantity", StringArgumentType.string())
                                        .suggests((commandContext, suggestionsBuilder) -> {
                                            suggestionsBuilder.suggest("max");
                                            suggestionsBuilder.suggest("5");
                                            suggestionsBuilder.suggest("50");
                                            suggestionsBuilder.suggest("100");
                                            suggestionsBuilder.suggest("500");
                                            return suggestionsBuilder.buildFuture();
                                        })
                                        .executes(commandContext -> setStat(commandContext, StringArgumentType.getString(commandContext, "stat"), StringArgumentType.getString(commandContext, "quantity"), Collections.singleton(commandContext.getSource().getPlayerOrException())))
                                        .then(Commands.argument("player", EntityArgument.players())
                                                .executes(commandContext -> setStat(commandContext, StringArgumentType.getString(commandContext, "stat"), StringArgumentType.getString(commandContext, "quantity"), EntityArgument.getPlayers(commandContext, "player")))
                                        )
                                )
                        )
                )

                .then(Commands.literal("add")
                        .then(Commands.argument("stat", StringArgumentType.string())
                                .suggests((commandContext, suggestionsBuilder) -> {
                                    suggestionsBuilder.suggest("strenght");
                                    suggestionsBuilder.suggest("defense");
                                    suggestionsBuilder.suggest("constitution");
                                    suggestionsBuilder.suggest("kipower");
                                    suggestionsBuilder.suggest("energy");
                                    suggestionsBuilder.suggest("all");
                                    return suggestionsBuilder.buildFuture();
                                })
                                .then(Commands.argument("quantity", IntegerArgumentType.integer())
                                        .executes(commandContext -> addStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "quantity"), Collections.singleton(commandContext.getSource().getPlayerOrException())))
                                        .then(Commands.argument("player", EntityArgument.players())
                                                .executes(commandContext -> addStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "quantity"), EntityArgument.getPlayers(commandContext, "player")))
                                        )
                                )
                        )
                )
                .then(Commands.literal("remove")
                        .then(Commands.argument("stat", StringArgumentType.string())
                                .suggests((commandContext, suggestionsBuilder) -> {
                                    suggestionsBuilder.suggest("strenght");
                                    suggestionsBuilder.suggest("defense");
                                    suggestionsBuilder.suggest("constitution");
                                    suggestionsBuilder.suggest("kipower");
                                    suggestionsBuilder.suggest("energy");
                                    suggestionsBuilder.suggest("all");
                                    return suggestionsBuilder.buildFuture();
                                })
                                .then(Commands.argument("quantity", IntegerArgumentType.integer())
                                        .executes(commandContext -> removeStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "quantity"), Collections.singleton(commandContext.getSource().getPlayerOrException())))
                                        .then(Commands.argument("player", EntityArgument.players())
                                                .executes(commandContext -> removeStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "quantity"), EntityArgument.getPlayers(commandContext, "player")))
                                        )
                                )
                        )
                )
        );

    }

    private int removeStat(CommandContext<CommandSourceStack> context, String stat, int cantidad, Collection<ServerPlayer> players) {
        for (ServerPlayer player : players) {

            DMZDatos dmzdatos = new DMZDatos();


            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {
                var vidaMC = 20;
                var con = stats.getStat("CON");
                var maxVIDA = 0.0;
                var raza = stats.getIntValue("race");
                int cantidadFinal = 0;
                if (cantidad > DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
                    cantidadFinal = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
                } else {
                    cantidadFinal = cantidad;
                }

                switch (stat) {
                    case "strenght":
                        stats.removeStat("STR", cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.strength")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "defense":
                        stats.removeStat("DEF", cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.defense")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "constitution":
                        stats.removeStat("CON", cantidadFinal);

                        stats.setIntValue("curstam", dmzdatos.calcStamina(stats));
                        int nuevaMaxVida = dmzdatos.calcConstitution(stats);
                        player.setHealth((float) nuevaMaxVida);

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.constitution")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        
                        break;
                    case "kipower":
                        stats.removeStat("PWR", cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.kipower")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "energy":
                        stats.removeStat("ENE", cantidadFinal);

                        stats.setIntValue("curenergy", dmzdatos.calcEnergy(stats));
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.energy")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "all":
                        stats.removeStat("STR", cantidadFinal);
                        stats.removeStat("DEF", cantidadFinal);
                        stats.removeStat("CON", cantidadFinal);
                        stats.removeStat("PWR", cantidadFinal);
                        stats.removeStat("ENE", cantidadFinal);

                        stats.setIntValue("curstam", dmzdatos.calcStamina(stats));

                        stats.setIntValue("curenergy", dmzdatos.calcEnergy(stats));

                        nuevaMaxVida = dmzdatos.calcConstitution(stats);
                        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(nuevaMaxVida);
                        player.setHealth((float) nuevaMaxVida);

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.all")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    default:
                        player.sendSystemMessage(Component.translatable("command.dmzstats.error").withStyle(ChatFormatting.RED));
                        break;
                }
            });

        }
        return players.size();
    }

    private int addStat(CommandContext<CommandSourceStack> context, String stat, int cantidad, Collection<ServerPlayer> players) {
        for (ServerPlayer player : players) {

            DMZDatos dmzdatos = new DMZDatos();

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {

                var vidaMC = 20;
                var con = stats.getStat("CON");
                var maxVIDA = 0.0;
                var raza = stats.getIntValue("race");
                int cantidadFinal = 0;
                if (cantidad > DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
                    cantidadFinal = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
                } else {
                    cantidadFinal = cantidad;
                }

                switch (stat) {
                    case "strenght":
                        stats.addStat("STR", cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.strength")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "defense":
                        stats.addStat("DEF", cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.defense")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "constitution":
                        stats.addStat("CON", cantidadFinal);

                        maxVIDA = dmzdatos.calcConstitution(stats);
                        stats.setIntValue("curstam", dmzdatos.calcStamina(stats));
                        player.heal((float) maxVIDA);
                        int nuevaMaxVida = dmzdatos.calcConstitution(stats);
                        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(nuevaMaxVida);
                        player.setHealth((float) nuevaMaxVida);

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.constitution")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "kipower":
                        stats.addStat("PWR", cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.kipower")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "energy":
                        stats.addStat("ENE", cantidadFinal);

                        stats.setIntValue("curenergy", dmzdatos.calcEnergy(stats));

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.energy")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "all":
                        stats.addStat("STR", cantidadFinal);
                        stats.addStat("DEF", cantidadFinal);
                        stats.addStat("CON", cantidadFinal);
                        stats.addStat("PWR", cantidadFinal);
                        stats.addStat("ENE", cantidadFinal);

                        stats.setIntValue("curstam", dmzdatos.calcStamina(stats));
                        nuevaMaxVida = dmzdatos.calcConstitution(stats);
                        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(nuevaMaxVida);
                        player.setHealth((float) nuevaMaxVida);

                        stats.setIntValue("curenergy", dmzdatos.calcEnergy(stats));

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.all")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    default:
                        player.sendSystemMessage(Component.translatable("command.dmzstats.error").withStyle(ChatFormatting.RED));
                        break;
                }
            });

        }
        return players.size();
    }

    private int setStat(CommandContext<CommandSourceStack> context, String stat, String cantidad, Collection<ServerPlayer> players) {
        int cant = 0;

        if (cantidad.equalsIgnoreCase("max")) {
            cant = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            cant = Integer.parseInt(cantidad);
        }

        // Si la cantidad es menor a 5 (Mínimo de Stats), se establece al mínimo. (EJ: Si yo uso /dmzstats set strenght 3, se establecerá en 5, el mínimo)
        if (cant < 5) {
            cant = 5;
        }

        for (ServerPlayer player : players) {

            DMZDatos dmzdatos = new DMZDatos();
            int cantidadFinal = cant;

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {

                int raza = stats.getIntValue("race");
                int energiacurrent = 0;
                var vidaMC = 20;
                var con = stats.getStat("CON");
                var maxVIDA = 0.0;


                switch (stat) {
                    case "strenght":

                        stats.setStat("STR", cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.strength")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "defense":

                        stats.setStat("DEF", cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.defense")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "constitution":

                        stats.setStat("CON", cantidadFinal);

                        maxVIDA = dmzdatos.calcConstitution(stats);
                        stats.setIntValue("curstam", dmzdatos.calcStamina(stats));
                        player.heal((float) maxVIDA);
                        int nuevaMaxVida = dmzdatos.calcConstitution(stats);
                        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(nuevaMaxVida);
                        player.setHealth((float) nuevaMaxVida);

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.constitution")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "kipower":

                        stats.setStat("PWR", cantidadFinal);

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.kipower")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "energy":

                        stats.setStat("ENE", cantidadFinal);

                        stats.setIntValue("curenergy", dmzdatos.calcEnergy(stats));

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.energy")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "all":

                        stats.setStat("STR", cantidadFinal);
                        stats.setStat("DEF", cantidadFinal);
                        stats.setStat("CON", cantidadFinal);
                        stats.setStat("PWR", cantidadFinal);
                        stats.setStat("ENE", cantidadFinal);


                        maxVIDA = dmzdatos.calcConstitution(stats);
                        stats.setIntValue("curstam", dmzdatos.calcStamina(stats));
						nuevaMaxVida = dmzdatos.calcConstitution(stats);
                        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(nuevaMaxVida);
                        player.setHealth((float) nuevaMaxVida);

                        stats.setIntValue("curenergy", dmzdatos.calcEnergy(stats));

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.all")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.noware")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    default:
                        player.sendSystemMessage(Component.translatable("command.dmzstats.error").withStyle(ChatFormatting.RED));
                        break;
                }
            });

        }
        return players.size();
    }
}
