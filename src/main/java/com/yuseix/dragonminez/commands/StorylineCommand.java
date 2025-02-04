package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.yuseix.dragonminez.init.StorylineManager;
import com.yuseix.dragonminez.registry.IDRegistry;
import com.yuseix.dragonminez.storyline.Objective;
import com.yuseix.dragonminez.storyline.Quest;
import com.yuseix.dragonminez.storyline.Saga;
import com.yuseix.dragonminez.storyline.player.PlayerStorylineProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class StorylineCommand {

	/*TODO: Add translations:
	 * - command.dmzstoryline.saga_completed
	 * - command.dmzstoryline.quest_completed
	 * - command.dmzstoryline.objective_completed
	 * - command.dmzstoryline.saga_info
	 * - command.dmzstoryline.quest_info
	 * - command.dmzstoryline.objective_info
	 * - command.dmzstoryline.no_found_saga
	 * - command.dmzstoryline.no_found_quest
	 * - command.dmzstoryline.no_quest
	 * - command.dmzstoryline.no_objective
	 * - command.dmzstoryline.saga_reset
	 * - command.dmzstoryline.quest_reset
	 * - command.dmzstoryline.all_reset
	 * - command.dmzstoryline.saga_list
	 * - command.dmzstoryline.quest_list
	 * - command.dmzstoryline.objective_list
	 * - command.dmzstoryline.quest_incompleted
	 * - command.dmzstoryline.saga_started
	 * - command.dmzstoryline.quest_started
	 */

	public StorylineCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("dmzstoryline")
				// /dmzstoryline set quest <id> <true|false>
				.then(Commands.literal("set")
						.then(Commands.literal("saga")
								.then(Commands.argument("id", StringArgumentType.string())
										.suggests(this::suggestSagaIds)
										.then(Commands.argument("completed", BoolArgumentType.bool())
												.executes(context -> {
													String sagaId = StringArgumentType.getString(context, "id");
													boolean completed = BoolArgumentType.getBool(context, "completed");

													return setSagaCompletion(context.getSource(), sagaId, completed);
												})
										)
								)
						)
						.then(Commands.literal("quest")
								.then(Commands.argument("id", StringArgumentType.string())
										.suggests(this::suggestQuestIds)
										.then(Commands.argument("completed", BoolArgumentType.bool())
												.executes(context -> {
													String questId = StringArgumentType.getString(context, "id");
													boolean completed = BoolArgumentType.getBool(context, "completed");

													return setQuestCompletion(context.getSource(), questId, completed);
												})
										)
								)
						)
						.then(Commands.literal("objective")
								.then(Commands.argument("quest_id", StringArgumentType.string())
										.suggests(this::suggestQuestIds)
										.then(Commands.argument("objective", StringArgumentType.string())
												.then(Commands.argument("completed", BoolArgumentType.bool())
														.suggests(this::suggestObjectivesFromQuest)
														.executes(context -> {
															String questId = StringArgumentType.getString(context, "quest_id");
															String objectiveId = StringArgumentType.getString(context, "objective");
															boolean completed = BoolArgumentType.getBool(context, "completed");

															return setObjectiveCompletion(context.getSource(), questId, objectiveId, completed);
														})
												)
										)
								)
						)
				)

				// /dmzstoryline get saga <id>
				.then(Commands.literal("get")

						.then(Commands.literal("saga")
								.then(Commands.argument("id", StringArgumentType.string())
										.suggests(this::suggestSagaIds)
										.executes(context -> {
											String sagaId = StringArgumentType.getString(context, "id");
											return getSagaInfo(context.getSource(), sagaId);
										})
								)
						)
						.then(Commands.literal("quest")
								.then(Commands.argument("id", StringArgumentType.string())
										.suggests(this::suggestQuestIds)
										.executes(context -> {
											String questId = StringArgumentType.getString(context, "id");
											return getQuestInfo(context.getSource(), questId);
										})
								)
						)
						.then(Commands.literal("progress")
								.executes(context -> getProgress(context.getSource()))
						)
						.then(Commands.literal("objective")
								.then(Commands.argument("quest_id", StringArgumentType.string())
										.suggests(this::suggestQuestIds)
										.executes(context -> {
											String objectiveId = StringArgumentType.getString(context, "quest_id");
											return getObjectivesFromQuest(context.getSource(), objectiveId);
										})
								)
						)
				)

				//Other than "all", the other subcommands execute with any saga/quest independent of the player's progress. Maybe change later.
				.then(Commands.literal("reset")
						.then(Commands.literal("saga")
								.then(Commands.argument("id", StringArgumentType.string())
										.suggests(this::suggestSagaIds)
										.executes(context -> {
											if (context.getSource().getPlayer() == null) {
												return 0;
											}
											context.getSource().getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline ->
													playerStoryline.getSaga(StringArgumentType.getString(context, "id")).getQuests().forEach(quest -> quest.setCompleted(false)));
											context.getSource().sendSuccess(() -> Component.translatable("command.dmzstoryline.saga_reset", StringArgumentType.getString(context, "id")), true);
											return 1;
										})
								)
						)
						.then(Commands.literal("quest")
								.then(Commands.argument("id", StringArgumentType.string())
										.suggests(this::suggestQuestIds)
										.executes(context -> {
											if (context.getSource().getPlayer() == null) {
												return 0;
											}
											context.getSource().getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline ->
													playerStoryline.getAllSagas().values().forEach(saga -> saga.getQuestbyId(StringArgumentType.getString(context, "id")).setCompleted(false)));
											context.getSource().sendSuccess(() -> Component.translatable("command.dmzstoryline.quest_reset", StringArgumentType.getString(context, "id")), true);
											return 1;
										})
								)
						)
						//Cautius with this one. It resets all progress in all sagas + quests. (Damn)
						.then(Commands.literal("all")
								.executes(context -> {
									if (context.getSource().getPlayer() == null) {
										return 0;
									}
									context.getSource().getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(StorylineManager::resetProgress);
									context.getSource().sendSuccess(() -> Component.translatable("command.dmzstoryline.all_reset"), true);
									return 1;
								})
						)
				)
				.then(Commands.literal("list")
						.then(Commands.literal("sagas")
								.executes(context -> {
									if (context.getSource().getPlayer() == null) {
										return 0;
									}
									context.getSource().getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline ->
											playerStoryline.getAllSagas().values().forEach(saga ->
													context.getSource().sendSuccess(() ->
															Component.translatable("command.dmzstoryline.saga_list", saga.getName(), saga.getId()), false)));
									return 1;
								})
						)
						.then(Commands.literal("quests")
								.executes(context -> {
									if (context.getSource().getPlayer() == null) {
										return 0;
									}
									context.getSource().getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline ->
											playerStoryline.getAllSagas().values().forEach(saga ->
													saga.getQuests().forEach(quest -> context.getSource().sendSuccess(() ->
															Component.translatable("command.dmzstoryline.quest_list", quest.getId(), quest.getDescription()), false))));
									return 1;
								})
						)
						.then(Commands.literal("objectives")
								.executes(context -> {
									if (context.getSource().getPlayer() == null) {
										return 0;
									}
									context.getSource().getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline ->
											playerStoryline.getAllSagas().values().forEach(saga ->
													saga.getQuests().forEach(quest ->
															quest.getObjectives().forEach(objective -> context.getSource().sendSuccess(() ->
																	Component.translatable("command.dmzstoryline.objective_list", objective.getName(), objective.getDescription()), false)))));
									return 1;
								})
						)
				)

				.then(Commands.literal("debug")
						.then(Commands.literal("data")
								.executes(context -> {
									if (context.getSource().getPlayer() == null) {
										return 0;
									}
									context.getSource().getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
										String data = playerStoryline.toSerializable().toString();
										//String url = uploadToHastebin(data);
										String url = "https://hastebin.com/raw/abuqoqoqog";
										context.getSource().sendSuccess(() -> Component.literal("Storyline data has some info to show you: " + url), true);
									});
									return 1;
								})
						)
						.then(Commands.literal("start")
								.then(Commands.literal("saga")
										.then(Commands.argument("id", StringArgumentType.string())
												.suggests(this::suggestSagaIds)
												.executes(context -> {
													if (context.getSource().getPlayer() == null) {
														return 0;
													}
													context.getSource().getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline ->
															playerStoryline.getSaga(StringArgumentType.getString(context, "id")).removeAllPrerequisites());
													context.getSource().sendSuccess(() -> Component.translatable("command.dmzstoryline.saga_started", StringArgumentType.getString(context, "id")), true);
													return 1;
												})
										)
								)
								.then(Commands.literal("quest")
										.then(Commands.argument("id", StringArgumentType.string())
												.suggests(this::suggestQuestIds)
												.executes(context -> {
													if (context.getSource().getPlayer() == null) {
														return 0;
													}
													context.getSource().getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline ->
															playerStoryline.getAllSagas().values().forEach(saga ->
																	saga.getQuestbyId(StringArgumentType.getString(context, "id")).removeAllPrerequisites()));
													context.getSource().sendSuccess(() -> Component.translatable("command.dmzstoryline.quest_started", StringArgumentType.getString(context, "id")), true);
													return 1;
												})
										)
								)
						)
				)
		);
	}

	private int setSagaCompletion(CommandSourceStack source, String sagaId, boolean completed) {
		AtomicInteger result = new AtomicInteger(0);

		if (source.getPlayer() == null) {
			return 0;
		}

		source.getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			Saga saga = playerStoryline.getSaga(sagaId);

			if (saga == null) {
				source.sendFailure(Component.translatable("command.dmzstoryline.no_found_saga", sagaId));
				result.set(0);
				return;
			}

			if (completed) {
				saga.getQuests().forEach(quest -> quest.setCompleted(true));
				source.sendSuccess(() -> Component.translatable("command.dmzstoryline.saga_completed", sagaId), true);
			}

			result.set(1);
		});

		return result.get();
	}

	private int setQuestCompletion(CommandSourceStack source, String questId, boolean completed) {
		AtomicInteger result = new AtomicInteger(0);

		if (source.getPlayer() == null) {
			return 0;
		}

		// Access the capability of the player
		source.getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			// Iterate through all sagas to find the quest
			for (Saga saga : playerStoryline.getAllSagas().values()) {
				Quest quest = saga.getAvailableQuests().stream().filter(q -> q.getId().equals(questId)).findFirst().orElse(null);

				if (quest != null) {
					// Set completion status
					if (completed) {
						quest.setCompleted(true);
						source.sendSuccess(() -> Component.translatable("command.dmzstoryline.quest_completed", questId), true);
					} else {
						quest.setCompleted(false);
						source.sendSuccess(() -> Component.translatable("command.dmzstoryline.quest_incompleted", questId), true);
					}

					result.set(1); // Indicate success
					return;
				} else {
					// Check if the quest exists in getQuests()
					quest = saga.getQuests().stream().filter(q -> q.getId().equals(questId)).findFirst().orElse(null);
					if (quest != null) {
						source.sendFailure(Component.translatable("command.dmzstoryline.no_quest", questId, source.getPlayer().getDisplayName().getString()));
						result.set(0);
						return;
					}
				}
			}

			// If no quest was found
			source.sendFailure(Component.translatable("command.dmzstoryline.no_found_quest", questId));
			result.set(0); // Indicate failure
		});

		return result.get(); // Return the result
	}

	//Please CHECKEAR esto dos veces. Sé que le falta pero qué laziness.
	private int setObjectiveCompletion(CommandSourceStack source, String questId, String objective, boolean completed) {
		AtomicInteger result = new AtomicInteger(0);

		if (source.getPlayer() == null) {
			return 0;
		}

		source.getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			for (Saga saga : playerStoryline.getAllSagas().values()) {
				Quest quest = saga.getAvailableQuests().stream().filter(q -> q.getId().equals(questId)).findFirst().orElse(null);

				if (quest != null) { // Quest is found in available quests
					Objective target_objective = quest.getObjectives().stream().filter(o -> o.getName().equals(objective)).findFirst().orElse(null);
					if (target_objective != null) {
						target_objective.setCompleted(completed);
						source.sendSuccess(() -> Component.translatable("command.dmzstoryline.objective_completed", objective, questId), true);
						result.set(1);
					} else {
						source.sendFailure(Component.translatable("command.dmzstoryline.no_objective", objective, questId));
						result.set(0);
					}
					return;
				} else { // Quest is not active
					quest = saga.getQuests().stream().filter(q -> q.getId().equals(questId)).findFirst().orElse(null);
					if (quest != null) { // Quest is found somewhere, if not then it doesn't exist
						source.sendFailure(Component.translatable("command.dmzstoryline.no_objective", objective, questId));
						result.set(0);
						return;
					}
				}
			}
		});

		return result.get();
	}


	private int getSagaInfo(CommandSourceStack source, String sagaId) {

		AtomicInteger result = new AtomicInteger(0);

		if (source.getPlayer() == null) {
			return 0;
		}

		source.getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			Saga saga = playerStoryline.getSaga(sagaId);

			if (saga == null) {
				source.sendFailure(Component.translatable("command.dmzstoryline.no_found_saga", sagaId));
				result.set(0); // Set the result to 0 if the saga is not found
				return;
			}

			source.sendSuccess(() -> Component.translatable("command.dmzstoryline.saga_info", saga.getName(), sagaId), false);

			for (Quest quest : saga.getQuests()) {
				String status = quest.isCompleted() ? "COMPLETED" : "INCOMPLETE";
				source.sendSuccess(() -> Component.translatable("command.dmzstoryline.quest_info", quest.getId(), quest.getDescription(), status), false);
			}

			result.set(1); // Set the result to 1 if everything is successful
		});

		return result.get(); // Return the stored result
	}

	private int getAllQuests(CommandSourceStack source) {

		AtomicInteger result = new AtomicInteger(0);

		if (source.getPlayer() == null) {
			return 0;
		}

		source.getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			for (Saga saga : playerStoryline.getAllSagas().values()) {
				for (Quest quest : saga.getQuests()) {
					String status = quest.isCompleted() ? "COMPLETED" : "INCOMPLETE";
					source.sendSuccess(() -> Component.translatable("command.dmzstoryline.quest_info", quest.getId(), quest.getDescription(), status), false);
				}
			}
			result.set(1);
		});

		return result.get();
	}

	private int getQuestInfo(CommandSourceStack source, String questId) {

		AtomicInteger result = new AtomicInteger(0);

		if (source.getPlayer() == null) {
			return 0;
		}

		source.getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {

			for (Saga saga : playerStoryline.getAllSagas().values()) {
				Quest quest = saga.getQuestbyId(questId);
				if (quest != null) {
					String status = quest.isCompleted() ? "COMPLETED" : "INCOMPLETE";
					source.sendSuccess(() -> Component.translatable("command.dmzstoryline.quest_info", quest.getId(), quest.getDescription(), status), false);
					result.set(1);
					return;
				}

			}
		});

		return result.get(); // Return the stored result
	}

	private int getProgress(CommandSourceStack source) {
		AtomicInteger result = new AtomicInteger(0);

		if (source.getPlayer() == null) {
			return 0;
		}

		source.getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			for (Saga saga : playerStoryline.getActiveSagas()) {
				for (Quest quest : saga.getAvailableQuests()) {
					String status = quest.isCompleted() ? "COMPLETED" : "INCOMPLETE";
					source.sendSuccess(() -> Component.translatable("command.dmzstoryline.quest_info", quest.getId(), quest.getDescription(), status), false);
				}
			}
			result.set(1);
		});

		return result.get();
	}

	private int getObjectivesFromQuest(CommandSourceStack source, String questId) {
		AtomicInteger result = new AtomicInteger(0);

		if (source.getPlayer() == null) {
			return 0;
		}

		source.getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			for (Saga saga : playerStoryline.getAllSagas().values()) {
				Quest quest = saga.getQuestbyId(questId);
				if (quest != null) {
					for (Objective objective : quest.getObjectives()) {
						source.sendSuccess(() -> Component.translatable("command.dmzstoryline.objective_info", objective.getName(), objective.getDescription(), objective.isCompleted() ? "COMPLETED" : "INCOMPLETE"), false);
					}
					result.set(1);
					return;
				}
			}
		});

		return result.get();
	}

	// Provide suggestions for quest IDs
	private CompletableFuture<Suggestions> suggestQuestIds(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
		// El Uso de IDRegistry.sagaRegistry no crea una instance de Storyline. Pero si tiene acceso a todas las sagas registradas
		// (y por ende a todas las quests y objetivos) en el juego.
		for (Saga saga : IDRegistry.sagaRegistry.values()) {
			for (Quest quest : saga.getQuests()) {
				builder.suggest(quest.getId());
			}
		}
		return builder.buildFuture();
	}

	private CompletableFuture<Suggestions> suggestSagaIds(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
		for (Saga saga : IDRegistry.sagaRegistry.values()) {
			builder.suggest(saga.getId());
		}
		return builder.buildFuture();
	}

	private CompletableFuture<Suggestions> suggestObjectivesFromQuest(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
		String questId = context.getArgument("quest_id", String.class);
		for (Saga saga : IDRegistry.sagaRegistry.values()) {
			Quest quest = saga.getQuestbyId(questId);
			if (quest != null) {
				for (Objective objective : quest.getObjectives()) {
					builder.suggest(objective.getName());
				}
			}
		}
		return builder.buildFuture();
	}

}
