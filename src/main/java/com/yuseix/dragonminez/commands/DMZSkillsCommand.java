package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.stats.skills.DMZSkill;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class DMZSkillsCommand {

	// Lista de habilidades válidas con sus límites de nivel
	private static final Map<String, Integer> VALID_SKILLS_LIST = Map.of(
			"potential_unlock", 13,
			"jump", 10,
			"fly", 10,
			"ki_control",10,
			"ki_manipulation",10,
			"meditation",10
	);

	public DMZSkillsCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

		dispatcher.register(Commands.literal("dmzskill")
				.requires(commandSourceStack -> commandSourceStack.hasPermission(2))
				.then(Commands.literal("give")
						.then(Commands.argument("skill", StringArgumentType.string())
								.suggests((commandContext, suggestionsBuilder) -> {
									for (String skill : VALID_SKILLS_LIST.keySet()) {
										suggestionsBuilder.suggest(skill);
									}
									return suggestionsBuilder.buildFuture();
								})
								.executes(commandContext -> giveSkill(
										Collections.singleton(commandContext.getSource().getPlayerOrException()),
										StringArgumentType.getString(commandContext, "skill"),
										1 // Nivel por defecto
								))
								.then(Commands.argument("level", IntegerArgumentType.integer(1))
										.executes(commandContext -> giveSkill(
												Collections.singleton(commandContext.getSource().getPlayerOrException()),
												StringArgumentType.getString(commandContext, "skill"),
												IntegerArgumentType.getInteger(commandContext, "level")
										))
										.then(Commands.argument("player", EntityArgument.players())
												.executes(commandContext -> giveSkill(
														EntityArgument.getPlayers(commandContext, "player"),
														StringArgumentType.getString(commandContext, "skill"),
														IntegerArgumentType.getInteger(commandContext, "level")
												))
										)
								)
						)
				)
				.then(Commands.literal("set")
						.then(Commands.argument("skill", StringArgumentType.string())
								.suggests((commandContext, suggestionsBuilder) -> {
									for (String skill : VALID_SKILLS_LIST.keySet()) {
										suggestionsBuilder.suggest(skill);
									}
									return suggestionsBuilder.buildFuture();
								})
								.then(Commands.argument("level", IntegerArgumentType.integer(1))
										.executes(commandContext -> setSkill(
												Collections.singleton(commandContext.getSource().getPlayerOrException()),
												StringArgumentType.getString(commandContext, "skill"),
												IntegerArgumentType.getInteger(commandContext, "level")
										))
										.then(Commands.argument("player", EntityArgument.players())
												.executes(commandContext -> setSkill(
														EntityArgument.getPlayers(commandContext, "player"),
														StringArgumentType.getString(commandContext, "skill"),
														IntegerArgumentType.getInteger(commandContext, "level")
												))
										)
								)
						)
				)
				.then(Commands.literal("take")
						.then(Commands.argument("skill", StringArgumentType.string())
								.suggests((commandContext, suggestionsBuilder) -> {
									for (String skill : VALID_SKILLS_LIST.keySet()) {
										suggestionsBuilder.suggest(skill);
									}
									return suggestionsBuilder.buildFuture();
								})
								.executes(commandContext -> takeSkill(
										Collections.singleton(commandContext.getSource().getPlayerOrException()),
										StringArgumentType.getString(commandContext, "skill")
								))
								.then(Commands.argument("player", EntityArgument.players())
										.executes(commandContext -> takeSkill(
												EntityArgument.getPlayers(commandContext, "player"),
												StringArgumentType.getString(commandContext, "skill")
										))
								)
						)
				)
		);
	}

	// Comando para dar habilidades
	private static int giveSkill(Collection<ServerPlayer> players, String skillName, int level) {
		if (!VALID_SKILLS_LIST.containsKey(skillName)) {
			for (ServerPlayer player : players) {
				player.sendSystemMessage(Component.translatable("command.dmzskills.invalid_skill").append(skillName).append("\n")
						.append(Component.translatable("command.dmzskills.valid_skills").append(String.join(", ", VALID_SKILLS_LIST.keySet()))));
			}
			return 0;
		}

		int maxLevel = VALID_SKILLS_LIST.get(skillName);
		level = Math.max(1, Math.min(level, maxLevel)); // Limita el nivel al rango [1, maxLevel]

		for (ServerPlayer player : players) {
			int finalLevel = level;
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
				DMZSkill skill = new DMZSkill("dmz.skill." + skillName + ".name",
						"dmz.skill." + skillName + ".desc",
						finalLevel, true);

				playerstats.addSkill(skillName, skill);

				player.sendSystemMessage(Component.translatable("command.dmzskills.give")
						.append(Component.translatable(skill.getName())).append(" ") // Solo muestra el nombre de la habilidad
						.append(Component.translatable("command.dmz.to")).append(player.getName()));
			});
		}
		return players.size();
	}

	// Comando para establecer nivel de habilidades
	private static int setSkill(Collection<ServerPlayer> players, String skillName, int level) {
		if (!VALID_SKILLS_LIST.containsKey(skillName)) {
			for (ServerPlayer player : players) {
				player.sendSystemMessage(Component.translatable("command.dmzskills.invalid_skill").append(skillName).append("\n")
						.append(Component.translatable("command.dmzskills.valid_skills").append(String.join(", ", VALID_SKILLS_LIST.keySet()))));
			}
			return 0;
		}

		int maxLevel = VALID_SKILLS_LIST.get(skillName);
		level = Math.max(1, Math.min(level, maxLevel)); // Limita el nivel al rango [1, maxLevel]

		for (ServerPlayer player : players) {
			int finalLevel = level;
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
				// Si la habilidad ya existe, se actualiza el nivel, si no se crea una nueva
				DMZSkill skill = playerstats.getSkill(skillName);
				if (skill != null) {
					skill.setLevel(finalLevel);
					player.sendSystemMessage(Component.translatable("command.dmzskills.set")
							.append(Component.translatable(skill.getName()))
							.append(" (Nivel ").append(Component.literal(String.valueOf(finalLevel))).append(") ") // Muestra el nivel
							.append(Component.translatable("command.dmz.to")).append(player.getName()));
				} else {
					// Si la habilidad no existe, la creamos y le asignamos el nivel
					skill = new DMZSkill(
							"dmz.skill." + skillName + ".name",
							"dmz.skill." + skillName + ".desc", finalLevel,
							true);

					playerstats.addSkill(skillName, skill);

					player.sendSystemMessage(Component.translatable("command.dmzskills.give")
							.append(Component.translatable(skill.getName())) // Solo muestra el nombre
							.append(" (Nivel ").append(Component.literal(String.valueOf(finalLevel))).append(") ") // Muestra el nivel
							.append(Component.translatable("command.dmz.to")).append(player.getName())
							.append(": ").append(Component.translatable(skill.getDesc()))); // Incluye la descripción de la habilidad
				}
			});
		}
		return players.size();
	}

	// Comando para quitar habilidades
	private static int takeSkill(Collection<ServerPlayer> players, String skillName) {
		if (!VALID_SKILLS_LIST.containsKey(skillName)) {
			for (ServerPlayer player : players) {
				player.sendSystemMessage(Component.translatable("command.dmzskills.invalid_skill").append(skillName).append("\n")
						.append(Component.translatable("command.dmzskills.valid_skills").append(String.join(", ", VALID_SKILLS_LIST.keySet()))));
			}
			return 0;
		}

		for (ServerPlayer player : players) {
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
				DMZSkill skill = playerstats.getSkill(skillName);
				if (skill != null) {
					player.sendSystemMessage(Component.translatable("command.dmzskills.take")
							.append(Component.translatable(skill.getName())).append(" ") // Solo muestra el nombre de la habilidad
							.append(Component.translatable("command.dmz.to")).append(player.getName()));
					playerstats.removeSkill(skillName);
				} else {
					player.sendSystemMessage(Component.translatable("command.dmzskills.not_found").append(skillName));
				}
			});
		}
		return players.size();
	}
}