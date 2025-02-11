package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

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
                var con = stats.getConstitution();
                var maxVIDA = 0.0;
                var raza = stats.getRace();
                int cantidadFinal = 0;
                if (cantidad > DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
                    cantidadFinal = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
                } else {
                    cantidadFinal = cantidad;
                }

                switch (stat) {
                    case "strenght":
                        stats.removeStrenght(cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.strength")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "defense":
                        stats.removeDefense(cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.defense")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "constitution":
                        stats.removeConstitution(cantidadFinal);

                        stats.setCurStam(dmzdatos.calcularSTM(stats));
                        int nuevaMaxVida = dmzdatos.calcularCON(stats);
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
                        stats.removeKiPower(cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.kipower")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "energy":
                        stats.removeEnergy(cantidadFinal);

                        stats.setCurrentEnergy(dmzdatos.calcularENE(stats));
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.energy")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "all":
                        stats.removeStrenght(cantidadFinal);
                        stats.removeDefense(cantidadFinal);
                        stats.removeConstitution(cantidadFinal);
                        stats.removeKiPower(cantidadFinal);
                        stats.removeEnergy(cantidadFinal);

                        stats.setCurStam(dmzdatos.calcularSTM(stats));

                        stats.setCurrentEnergy(dmzdatos.calcularENE(stats));

                        nuevaMaxVida = dmzdatos.calcularCON(stats);
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
                var con = stats.getConstitution();
                var maxVIDA = 0.0;
                var raza = stats.getRace();
                int cantidadFinal = 0;
                if (cantidad > DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
                    cantidadFinal = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
                } else {
                    cantidadFinal = cantidad;
                }

                switch (stat) {
                    case "strenght":
                        stats.addStrength(cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.strength")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "defense":
                        stats.addDefense(cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.defense")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "constitution":
                        stats.addCon(cantidadFinal);

                        maxVIDA = dmzdatos.calcularCON(stats);
                        stats.setCurStam(dmzdatos.calcularSTM(stats));
                        player.heal((float) maxVIDA);
                        int nuevaMaxVida = dmzdatos.calcularCON(stats);
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
                        stats.addKipwr(cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.kipower")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "energy":
                        stats.addEnergy(cantidadFinal);

                        stats.setCurrentEnergy(dmzdatos.calcularENE(stats));

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.energy")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "all":
                        stats.addStrength(cantidadFinal);
                        stats.addDefense(cantidadFinal);
                        stats.addCon(cantidadFinal);
                        stats.addKipwr(cantidadFinal);
                        stats.addEnergy(cantidadFinal);

                        stats.setCurStam(dmzdatos.calcularSTM(stats));
                        nuevaMaxVida = dmzdatos.calcularCON(stats);
                        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(nuevaMaxVida);
                        player.setHealth((float) nuevaMaxVida);

                        stats.setCurrentEnergy(dmzdatos.calcularENE(stats));

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

                int raza = stats.getRace();
                int energiacurrent = 0;
                var vidaMC = 20;
                var con = stats.getConstitution();
                var maxVIDA = 0.0;


                switch (stat) {
                    case "strenght":

                        stats.setStrength(cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.strength")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "defense":

                        stats.setDefense(cantidadFinal);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.defense")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "constitution":

                        stats.setConstitution(cantidadFinal);

                        maxVIDA = dmzdatos.calcularCON(stats);
                        stats.setCurStam(dmzdatos.calcularSTM(stats));
                        player.heal((float) maxVIDA);
                        int nuevaMaxVida = dmzdatos.calcularCON(stats);
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

                        stats.setKiPower(cantidadFinal);

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.kipower")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "energy":

                        stats.setEnergy(cantidadFinal);

                        stats.setCurrentEnergy(dmzdatos.calcularENE(stats));

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.energy")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidadFinal)).append(Component.literal("."))
                        );
                        break;
                    case "all":

                        stats.setStrength(cantidadFinal);
                        stats.setDefense(cantidadFinal);
                        stats.setConstitution(cantidadFinal);
                        stats.setKiPower(cantidadFinal);
                        stats.setEnergy(cantidadFinal);


                        maxVIDA = dmzdatos.calcularCON(stats);
                        stats.setCurStam(dmzdatos.calcularSTM(stats));
						nuevaMaxVida = dmzdatos.calcularCON(stats);
                        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(nuevaMaxVida);
                        player.setHealth((float) nuevaMaxVida);

                        stats.setCurrentEnergy(dmzdatos.calcularENE(stats));

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
