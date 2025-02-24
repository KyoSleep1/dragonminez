package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
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
                        Collections.singleton(commandContext.getSource().getPlayerOrException())))
                .then(Commands.argument("player", EntityArgument.players())
                        .executes(commandContext -> reiniciarJugador(
                                EntityArgument.getPlayers(commandContext, "player")))
                )
        );

    }

    private static int reiniciarJugador(Collection<ServerPlayer> pPlayers) {
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
                player.setHealth(20);
                player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
                player.setHealth(20);

                playerstats.setAcceptCharacter(false);
                //Luego cambiar cuando decidamos las stats
                playerstats.setStrength(5);
                playerstats.setDefense(5);
                playerstats.setConstitution(5);
                playerstats.setKiPower(5);
                playerstats.setEnergy(5);
                playerstats.setZpoints(0);
                playerstats.removeAllSkills();
                playerstats.setDmzForm("base");
                playerstats.setDmzGroupForm("");
                playerstats.setTurboOn(false);
                playerstats.setAuraOn(false);
                playerstats.setBabaAliveTimer(0);
                playerstats.setBabaCooldown(0);
                playerstats.setSaiyanZenkaiTimer(0);
                playerstats.setZenkaiCount(0);
                playerstats.setDmzForm("base");
                playerstats.setDmzRelease(0);
                playerstats.removeTemporalEffect("mightfruit");
                playerstats.removePermanentEffect("majin");
                playerstats.removeFormSkill("super_form");
                playerstats.setCurrentEnergy(0);

                // NOTA: Lo de la vida se hace dos veces, pq a veces se buguea la primera vez xd

                player.setHealth(20);
                player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
                player.setHealth(20);
            });
        }
        return pPlayers.size();
    }


}
