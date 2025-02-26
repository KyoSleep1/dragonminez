package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;

public class DMZReviveCommand {

	public DMZReviveCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("dmzrevive")
				.requires(commandSourceStack -> commandSourceStack.hasPermission(2))
				.then(Commands.argument("player", EntityArgument.players())
						.executes(commandContext -> revive(
								EntityArgument.getPlayers(commandContext, "player"),
								commandContext.getSource()))
				));
	}

	private static int revive(Collection<ServerPlayer> players, CommandSourceStack source) {
		for (ServerPlayer player : players) {
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
				cap.setDmzAlive(true);
				cap.setBabaCooldown(0);
				cap.setBabaAliveTimer(0);
				player.sendSystemMessage(Component.translatable("command.dmzrevive.revived", source));
				source.sendSystemMessage(Component.translatable("command.dmzrevive.success", player));
			});
		}

		return players.size();
	}
}
