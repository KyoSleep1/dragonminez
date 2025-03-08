package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Collection;
import java.util.Collections;

public class ResetCharacterCommand {

    public ResetCharacterCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("dmzrestart")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                .executes(commandContext -> reiniciarJugador(
                        Collections.singleton(commandContext.getSource().getPlayerOrException()), 0))
                .then(Commands.argument("player", EntityArgument.players())
                        .executes(commandContext -> reiniciarJugador(
                                EntityArgument.getPlayers(commandContext, "player"), 0))
                        .then(Commands.argument("keepPercentage", IntegerArgumentType.integer(0, 100))
                                .executes(commandContext -> reiniciarJugador(
                                        EntityArgument.getPlayers(commandContext, "player"),
                                        IntegerArgumentType.getInteger(commandContext, "keepPercentage")))
                        )
                )
        );
    }

    private static int reiniciarJugador(Collection<ServerPlayer> pPlayers, int porcentaje) {
        for (ServerPlayer player : pPlayers) {
            DMZDatos dmzdatos = new DMZDatos();

            player.sendSystemMessage(
                    Component.translatable("command.dmzrestart.character")
                            .append(" ")
                            .append(player.getName())
                            .append(" ")
                            .append(Component.translatable("command.dmzrestart.character_restarted"))
            );

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
                // Reiniciar vida y atributos básicos
                player.setHealth(20);
                player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
                player.setHealth(20);

                // Aplicar el porcentaje a las estadísticas
                if (porcentaje == 0) {
                    System.out.println("Reiniciando completamente las stats");
                    // Reiniciar completamente las stats
                    playerstats.setStat("STR", 5);
                    playerstats.setStat("DEF", 5);
                    playerstats.setStat("CON", 5);
                    playerstats.setStat("PWR", 5);
                    playerstats.setStat("ENE", 5);
                } else {
                    System.out.println("Reiniciando las stats al " + porcentaje + "%");
                    double factor = (100-porcentaje) / 100.0;
                    playerstats.setStat("STR", (int) (playerstats.getStat("STR") * factor));
                    System.out.println("STR FACTOR: " + (int) (playerstats.getStat("STR") * factor));
                    System.out.println("STR: " + playerstats.getStat("STR"));
                    playerstats.setStat("DEF", (int) (playerstats.getStat("DEF") * factor));
                    playerstats.setStat("CON", (int) (playerstats.getStat("CON") * factor));
                    playerstats.setStat("PWR", (int) (playerstats.getStat("PWR") * factor));
                    playerstats.setStat("ENE", (int) (playerstats.getStat("ENE") * factor));
                }

                // Reiniciar otros datos (independientemente del porcentaje)
                playerstats.setBoolean("dmzuser", false);
                playerstats.setIntValue("tps", 0);
                playerstats.removeAllSkills();
                playerstats.setStringValue("form", "base");
                playerstats.setStringValue("groupform", "");
                playerstats.setBoolean("turbo", false);
                playerstats.setBoolean("aura", false);
                playerstats.setBoolean("kaioplanet", false);
                playerstats.setIntValue("babaalivetimer", 0);
                playerstats.setIntValue("babacooldown", 0);
                playerstats.setIntValue("zenkaitimer", 0);
                playerstats.setIntValue("zenkaicount", 0);
                playerstats.setStringValue("form", "base");
                playerstats.setIntValue("release", 0);
                playerstats.removeTemporalEffect("mightfruit");
                playerstats.removePermanentEffect("majin");
                playerstats.removeFormSkill("super_form");
                playerstats.setIntValue("curenergy", 0);

                // NOTA: Lo de la vida se hace dos veces, pq a veces se buguea la primera vez xd
                player.setHealth(20);
                player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
                player.setHealth(20);
            });
        }
        return pPlayers.size();
    }
}
