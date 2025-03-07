package com.yuseix.dragonminez.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.yuseix.dragonminez.events.StoryEvents;
import com.yuseix.dragonminez.stats.storymode.DMZQuest;
import com.yuseix.dragonminez.stats.storymode.DMZSaga;
import com.yuseix.dragonminez.stats.storymode.DMZStoryCapability;
import com.yuseix.dragonminez.stats.storymode.DMZStoryRegistry;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.GameProfileArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class StoryModeCommand {
	public StoryModeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("dmzstorymode")
				.requires(commandSourceStack -> commandSourceStack.hasPermission(2))
				.then(Commands.literal("set")
						.then(Commands.argument("questID", StringArgumentType.string())
								.suggests(this::suggestQuestsIds)
								.then(Commands.argument("completed", BoolArgumentType.bool())
										.suggests((commandContext, suggestionsBuilder) -> {
											suggestionsBuilder.suggest("true");
											suggestionsBuilder.suggest("false");
											return suggestionsBuilder.buildFuture();
										})
										.executes(commandContext -> {
											String questId = StringArgumentType.getString(commandContext, "questID");
											boolean completed = BoolArgumentType.getBool(commandContext, "completed");

											return setQuestCompletion(commandContext.getSource(), questId, completed, null);
										})
										.then(Commands.argument("target", GameProfileArgument.gameProfile())
												.executes(commandContext -> {
													String questId = StringArgumentType.getString(commandContext, "questID");
													boolean completed = BoolArgumentType.getBool(commandContext, "completed");

													Collection<GameProfile> profiles = GameProfileArgument.getGameProfiles(commandContext, "target");
													if (profiles.isEmpty()) {
														commandContext.getSource().sendFailure(Component.literal("No se encontró el jugador especificado."));
														return 0;
													}

													GameProfile targetProfile = profiles.iterator().next();
													return setQuestCompletion(commandContext.getSource(), questId, completed, targetProfile.getName());
												})
										)
								)
						)
				)
				.then(Commands.literal("reset")
						.then(Commands.argument("type", StringArgumentType.string())
								.suggests((commandContext, suggestionsBuilder) -> {
									suggestionsBuilder.suggest("quest");
									suggestionsBuilder.suggest("everything");
									return suggestionsBuilder.buildFuture();
								})
								.then(Commands.argument("id", StringArgumentType.string())
										.suggests(this::suggestQuestsIds)
										.executes(commandContext -> {
											String type = StringArgumentType.getString(commandContext, "type");
											String questId = StringArgumentType.getString(commandContext, "id");

											return resetProgress(commandContext.getSource(), type, questId, null);
										})
										.then(Commands.argument("target", GameProfileArgument.gameProfile())
												.executes(commandContext -> {
													String type = StringArgumentType.getString(commandContext, "type");
													String questId = StringArgumentType.getString(commandContext, "id");

													Collection<GameProfile> profiles = GameProfileArgument.getGameProfiles(commandContext, "target");
													if (profiles.isEmpty()) {
														commandContext.getSource().sendFailure(Component.literal("No se encontró el jugador especificado."));
														return 0;
													}

													GameProfile targetProfile = profiles.iterator().next();
													return resetProgress(commandContext.getSource(), type, questId, targetProfile.getName());
												})
										)
								)
								.executes(commandContext -> {
									String everything = "everything";
									return resetProgress(commandContext.getSource(), everything, null, null);
								})
								.then(Commands.argument("target", GameProfileArgument.gameProfile())
										.executes(commandContext -> {
											String everything = "everything";

											Collection<GameProfile> profiles = GameProfileArgument.getGameProfiles(commandContext, "target");
											if (profiles.isEmpty()) {
												commandContext.getSource().sendFailure(Component.literal("No se encontró el jugador especificado."));
												return 0;
											}

											GameProfile targetProfile = profiles.iterator().next();
											return resetProgress(commandContext.getSource(), everything, null, targetProfile.getName());
										})
								)
						)
				)
		);
	}

	private int setQuestCompletion(CommandSourceStack source, String questId, boolean completed, @Nullable String targetNick) {
		DMZQuest quest = DMZStoryRegistry.getQuest(questId);
		if (quest == null) {
			source.sendFailure(Component.literal("La misión con ID " + questId + " no existe."));
			return 0;
		}

		Player targetPlayer;
		if (targetNick == null) {
			if (!(source.getEntity() instanceof Player player)) {
				return 0;
			}
			targetPlayer = player;
		} else {
			targetPlayer = source.getServer().getPlayerList().getPlayerByName(targetNick);
			if (targetPlayer == null) {
				source.sendFailure(Component.translatable("command.dmz.unknown_player", targetNick));
				return 0;
			}
		}

		targetPlayer.getCapability(DMZStoryCapability.INSTANCE).ifPresent(capability -> {
			if (completed) {
				capability.getCompletedQuests().add(questId);
			} else {
				capability.getCompletedQuests().remove(questId);
			}
		});

		if (targetNick == null) {
			source.sendSuccess(() -> completed ? Component.translatable("command.dmzstoryline.quest_completed", questId) : Component.translatable("command.dmzstoryline.quest_incompleted", questId), true);
		} else {
			source.sendSuccess(() -> completed ? Component.translatable("command.dmzstoryline.quest_completed", questId, targetNick) : Component.translatable("command.dmzstoryline.quest_incompleted", questId, targetNick), true);
		}

		StoryEvents.syncQuestData(targetPlayer);
		return 1;
	}

	private int resetProgress(CommandSourceStack source, String type, @Nullable String questId, @Nullable String targetNick) {
		Player targetPlayer;
		if (targetNick == null) {
			if (!(source.getEntity() instanceof Player player)) {
				return 0;
			}
			targetPlayer = player;
		} else {
			targetPlayer = source.getServer().getPlayerList().getPlayerByName(targetNick);
			if (targetPlayer == null) {
				source.sendFailure(Component.translatable("command.dmz.unknown_player", targetNick));
				return 0;
			}
		}

		targetPlayer.getCapability(DMZStoryCapability.INSTANCE).ifPresent(capability -> {
			if (type.equalsIgnoreCase("quest")) {
				if (questId != null) {
					capability.getCompletedQuests().remove(questId);
				}
			} else if (type.equalsIgnoreCase("everything")) {
				capability.resetAllProgress();
			}
		});

		if (targetNick == null) {
			if (type.equalsIgnoreCase("quest")) {
				source.sendSuccess(() -> Component.translatable("command.dmzstoryline.quest_reset"), true);
			} else {
				source.sendSuccess(() -> Component.translatable("command.dmzstoryline.all_reset"), true);
			}
		} else {
			if (type.equalsIgnoreCase("quest")) {
				source.sendSuccess(() -> Component.translatable("command.dmzstoryline.quest_reset_other", targetNick), true);
			} else {
				source.sendSuccess(() -> Component.translatable("command.dmzstoryline.all_reset_other", targetNick), true);
			}
		}

		StoryEvents.syncQuestData(targetPlayer);
		return 1;
	}

	private CompletableFuture<Suggestions> suggestQuestsIds(CommandContext<CommandSourceStack> context, SuggestionsBuilder suggestionsBuilder) {
		Collection<DMZSaga> sagas = DMZStoryRegistry.getAllSagas();

		for (DMZSaga saga : sagas) {
			String sagaId = saga.getId();

			Collection<DMZQuest> quests = DMZStoryRegistry.getQuestsBySaga(sagaId);

			for (DMZQuest quest : quests) {
				suggestionsBuilder.suggest(quest.getId());
			}
		}

		return suggestionsBuilder.buildFuture();
	}
}
