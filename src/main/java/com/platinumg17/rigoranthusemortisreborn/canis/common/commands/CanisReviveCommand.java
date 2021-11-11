package com.platinumg17.rigoranthusemortisreborn.canis.common.commands;

import com.platinumg17.rigoranthusemortisreborn.canis.common.commands.arguments.UUIDArgument;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.storage.*;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;

import static net.minecraft.command.Commands.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import com.google.common.base.Strings;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

public class CanisReviveCommand {

    public static final DynamicCommandExceptionType COLOR_INVALID = new DynamicCommandExceptionType((arg) -> {
        return new TranslationTextComponent("command.canisrevive.invalid", arg);
    });
    public static final DynamicCommandExceptionType SPAWN_EXCEPTION = new DynamicCommandExceptionType((arg) -> {
        return new TranslationTextComponent("command.canisrevive.exception", arg);
    });
    public static final Dynamic2CommandExceptionType TOO_MANY_OPTIONS = new Dynamic2CommandExceptionType((arg1, arg2) -> {
        return new TranslationTextComponent("command.canisrevive.imprecise", arg1, arg2);
    });

    public static void register(final CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                literal("canis")
                        .requires(s -> s.hasPermission(2))
                        .then(Commands.literal("locate")
                                .then(Commands.literal("byuuid")
                                        .then(Commands.argument("canis_owner", UUIDArgument.uuid()).suggests(CanisReviveCommand.getOwnerIdSuggestionsLocate())
                                                .then(Commands.argument("canis_uuid", UUIDArgument.uuid()).suggests(CanisReviveCommand.getCanisIdSuggestionsLocate())
                                                        .executes(c -> locate(c)))))
                                .then(Commands.literal("byname")
                                        .then(Commands.argument("owner_name", StringArgumentType.string()).suggests(CanisReviveCommand.getOwnerNameSuggestionsLocate())
                                                .then(Commands.argument("canis_name", StringArgumentType.string()).suggests(CanisReviveCommand.getCanisNameSuggestionsLocate())
                                                        .executes(c -> locate2(c))))))
                        .then(Commands.literal("revive")
                                .then(Commands.literal("byuuid")
                                        .then(Commands.argument("canis_owner", UUIDArgument.uuid()).suggests(CanisReviveCommand.getOwnerIdSuggestionsRevive())
                                                .then(Commands.argument("canis_uuid", UUIDArgument.uuid()).suggests(CanisReviveCommand.getCanisIdSuggestionsRevive())
                                                        .executes(c -> respawn(c)))))
                                .then(Commands.literal("byname")
                                        .then(Commands.argument("owner_name", StringArgumentType.string()).suggests(CanisReviveCommand.getOwnerNameSuggestionsRevive())
                                                .then(Commands.argument("canis_name", StringArgumentType.string()).suggests(CanisReviveCommand.getCanisNameSuggestionsRevive())
                                                        .executes(c -> respawn2(c))))))
        );
    }

    public static void registerSerializers() {
        ArgumentTypes.register(REUtil.getResourcePath("uuid"), UUIDArgument.class, new ArgumentSerializer<>(UUIDArgument::uuid));
    }
    private static <S extends ISuggestionProvider> SuggestionProvider<S> getOwnerIdSuggestionsLocate() {
        return (context, builder) -> getOwnerIdSuggestions(CanisLocationStorage.get(((CommandSource)context.getSource()).getLevel()).getAll(), context, builder);
    }
    private static <S extends ISuggestionProvider> SuggestionProvider<S> getOwnerIdSuggestionsRevive() {
        return (context, builder) -> getOwnerIdSuggestions(CanisRespawnStorage.get(((CommandSource)context.getSource()).getLevel()).getAll(), context, builder);
    }

    private static <S extends ISuggestionProvider> CompletableFuture<Suggestions> getOwnerIdSuggestions(Collection<? extends ICanisData> possibilities, final CommandContext<S> context, final SuggestionsBuilder builder) {
        if (context.getSource() instanceof CommandSource) {
            return ISuggestionProvider.suggest(possibilities.stream()
                    .map(ICanisData::getOwnerId)
                    .filter(Objects::nonNull)
                    .map(Object::toString)
                    .collect(Collectors.toSet()),
                builder);
        } else if (context.getSource() instanceof ISuggestionProvider) {
            return context.getSource().customSuggestion((CommandContext<ISuggestionProvider>) context, builder);
        } else {
            return Suggestions.empty();
        }
    }

    private static <S extends ISuggestionProvider> SuggestionProvider<S> getCanisIdSuggestionsLocate() {
        return (context, builder) -> getCanisIdSuggestions(CanisLocationStorage.get(((CommandSource)context.getSource()).getLevel()).getAll(), context, builder);
    }
    private static <S extends ISuggestionProvider> SuggestionProvider<S> getCanisIdSuggestionsRevive() {
        return (context, builder) -> getCanisIdSuggestions(CanisRespawnStorage.get(((CommandSource)context.getSource()).getLevel()).getAll(), context, builder);
    }

    private static <S extends ISuggestionProvider> CompletableFuture<Suggestions> getCanisIdSuggestions(Collection<? extends ICanisData> possibilities, final CommandContext<S> context, final SuggestionsBuilder builder) {
        if (context.getSource() instanceof CommandSource) {
            UUID ownerId = context.getArgument("canis_owner", UUID.class);
            if (ownerId == null) {
                return Suggestions.empty();
            }
            return ISuggestionProvider.suggest(possibilities.stream()
                    .filter(data -> ownerId.equals(data.getOwnerId()))
                    .map(ICanisData::getCanisId)
                    .map(Object::toString)
                    .collect(Collectors.toSet()),
                builder);
        } else if (context.getSource() instanceof ISuggestionProvider) {
            return context.getSource().customSuggestion((CommandContext<ISuggestionProvider>) context, builder);
        } else {
            return Suggestions.empty();
        }
    }

    private static <S extends ISuggestionProvider> SuggestionProvider<S> getOwnerNameSuggestionsLocate() {
        return (context, builder) -> getOwnerNameSuggestions(CanisLocationStorage.get(((CommandSource)context.getSource()).getLevel()).getAll(), context, builder);
    }
    private static <S extends ISuggestionProvider> SuggestionProvider<S> getOwnerNameSuggestionsRevive() {
        return (context, builder) -> getOwnerNameSuggestions(CanisRespawnStorage.get(((CommandSource)context.getSource()).getLevel()).getAll(), context, builder);
    }

    public static <S extends ISuggestionProvider> CompletableFuture<Suggestions> getOwnerNameSuggestions(Collection<? extends ICanisData> possibilities, final CommandContext<S> context, final SuggestionsBuilder builder) {
        if (context.getSource() instanceof CommandSource) {
            return ISuggestionProvider.suggest(possibilities.stream()
                    .map(ICanisData::getOwnerName)
                    .filter(Objects::nonNull)
                    .map(Object::toString)
                    .collect(Collectors.toSet()),
                builder);

        } else if (context.getSource() instanceof ISuggestionProvider) {
            return context.getSource().customSuggestion((CommandContext<ISuggestionProvider>) context, builder);
        } else {
            return Suggestions.empty();
        }
    }

    private static <S extends ISuggestionProvider> SuggestionProvider<S> getCanisNameSuggestionsLocate() {
        return (context, builder) -> getCanisNameSuggestions(CanisLocationStorage.get(((CommandSource)context.getSource()).getLevel()).getAll(), context, builder);
    }
    private static <S extends ISuggestionProvider> SuggestionProvider<S> getCanisNameSuggestionsRevive() {
        return (context, builder) -> getCanisNameSuggestions(CanisRespawnStorage.get(((CommandSource)context.getSource()).getLevel()).getAll(), context, builder);
    }

    public static <S extends ISuggestionProvider> CompletableFuture<Suggestions> getCanisNameSuggestions(Collection<? extends ICanisData> possibilities, final CommandContext<S> context, final SuggestionsBuilder builder) {
        if (context.getSource() instanceof CommandSource) {
            String ownerName = context.getArgument("owner_name", String.class);

            if (ownerName == null) {
                return Suggestions.empty();
            }
            return ISuggestionProvider.suggest(possibilities.stream()
                    .filter(data -> ownerName.equals(data.getOwnerName()))
                    .map(ICanisData::getCanisName)
                    .filter((str) -> !Strings.isNullOrEmpty(str))
                    .collect(Collectors.toList()),
                builder);
        } else if (context.getSource() instanceof ISuggestionProvider) {
            return context.getSource().customSuggestion((CommandContext<ISuggestionProvider>)context, builder);
        } else {
            return Suggestions.empty();
        }
    }

    private static int respawn(final CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        CommandSource source = ctx.getSource();
        source.getPlayerOrException(); // Check source is a player
        ServerWorld world = source.getLevel();

        UUID ownerUuid = ctx.getArgument("canis_owner", UUID.class);
        UUID uuid = ctx.getArgument("canis_uuid", UUID.class);

        CanisRespawnStorage respawnStorage = CanisRespawnStorage.get(world);
        CanisRespawnData respawnData = respawnStorage.getData(uuid);

        if (respawnData == null) {
            throw COLOR_INVALID.create(uuid.toString());
        }
        return respawn(respawnStorage, respawnData, source);
    }

    private static int respawn2(final CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        CommandSource source = ctx.getSource();
        source.getPlayerOrException(); // Check source is a player
        ServerWorld world = source.getLevel();

        String ownerName = ctx.getArgument("owner_name", String.class);
        String canisName = ctx.getArgument("canis_name", String.class);

        CanisRespawnStorage respawnStorage = CanisRespawnStorage.get(world);
        List<CanisRespawnData> respawnData = respawnStorage.getCani(ownerName).filter((data) -> data.getCanisName().equalsIgnoreCase(canisName)).collect(Collectors.toList());

        if (respawnData.isEmpty()) {
            throw COLOR_INVALID.create(canisName);
        }
        if (respawnData.size() > 1) {
            StringJoiner joiner = new StringJoiner(", ");
            for (CanisRespawnData data : respawnData) {
                joiner.add(Objects.toString(data.getCanisId()));
            }
            throw TOO_MANY_OPTIONS.create(joiner.toString(), respawnData.size());
        }
        return respawn(respawnStorage, respawnData.get(0), source);
    }

    private static int respawn(CanisRespawnStorage respawnStorage, CanisRespawnData respawnData, final CommandSource source) throws CommandSyntaxException {
        CanisEntity canis = respawnData.respawn(source.getLevel(), source.getPlayerOrException(), source.getPlayerOrException().blockPosition().above());
        if (canis != null) {
            respawnStorage.remove(respawnData.getCanisId());
            source.sendSuccess(new TranslationTextComponent("commands.canisrevive.uuid.success", respawnData.getCanisName()), false);
            return 1;
        }
        source.sendSuccess(new TranslationTextComponent("commands.canisrevive.uuid.failure", respawnData.getCanisName()), false);
        return 0;
    }

    private static int locate(final CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        CommandSource source = ctx.getSource();
        source.getPlayerOrException(); // Check source is a player
        ServerWorld world = source.getLevel();

        UUID ownerUuid = ctx.getArgument("canis_owner", UUID.class);
        UUID uuid = ctx.getArgument("canis_uuid", UUID.class);

        CanisLocationStorage locationStorage = CanisLocationStorage.get(world);
        CanisLocationData locationData = locationStorage.getData(uuid);
        if (locationData == null) {
            throw COLOR_INVALID.create(uuid.toString());
        }
        return locate(locationStorage, locationData, source);
    }

    private static int locate2(final CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        CommandSource source = ctx.getSource();
        source.getPlayerOrException(); // Check source is a player
        ServerWorld world = source.getLevel();
        String ownerName = ctx.getArgument("owner_name", String.class);
        String canisName = ctx.getArgument("canis_name", String.class);
        CanisLocationStorage locationStorage = CanisLocationStorage.get(world);
        List<CanisLocationData> locationData = locationStorage.getAll().stream()
                .filter(data -> ownerName.equals(data.getOwnerName())).filter((data) -> data.getCanisName().equalsIgnoreCase(canisName)).collect(Collectors.toList());
        if (locationData.isEmpty()) {
            throw COLOR_INVALID.create(canisName);
        }
        if (locationData.size() > 1) {
            StringJoiner joiner = new StringJoiner(", ");
            for (CanisLocationData data : locationData) {
                joiner.add(Objects.toString(data.getCanisId()));
            }
            throw TOO_MANY_OPTIONS.create(joiner.toString(), locationData.size());
        }
        return locate(locationStorage, locationData.get(0), source);
    }

    private static int locate(CanisLocationStorage respawnStorage, CanisLocationData locationData, final CommandSource source) throws CommandSyntaxException {
        PlayerEntity player = source.getPlayerOrException();
        if (locationData.getDimension().equals(player.level.dimension())) {
//            String translateStr = RadarItem.getDirectionTranslationKey(locationData, player);
            int distance = MathHelper.ceil(locationData.getPos() != null ? locationData.getPos().distanceTo(player.position()) : -1);
//            source.sendSuccess(new TranslationTextComponent(translateStr, locationData.getName(player.level), distance), false);
        } else {
            source.sendSuccess(new TranslationTextComponent("canisradar.notindim", locationData.getDimension()), false); // TODO change message
        }
        return 1;
    }
}