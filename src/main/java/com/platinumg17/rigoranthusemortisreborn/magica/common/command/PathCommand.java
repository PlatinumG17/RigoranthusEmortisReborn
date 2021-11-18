package com.platinumg17.rigoranthusemortisreborn.magica.common.command;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketTogglePathing;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;

public class PathCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("ars-pathing").
                requires(sender -> sender.hasPermission(2))
                .executes(context -> setPathing(context.getSource(), ImmutableList.of(context.getSource().getEntityOrException()))));
    }

    private static int setPathing(CommandSource source, ImmutableList<? extends Entity> of) {
        Networking.INSTANCE.send(PacketDistributor.PLAYER.with(()-> (ServerPlayerEntity) of.get(0)),
                new PacketTogglePathing());
        return 1;
    }
}