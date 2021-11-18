package com.platinumg17.rigoranthusemortisreborn.magica.common.command;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.platinumg17.rigoranthusemortisreborn.magica.common.capability.DominionCapability;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.familiar.FamiliarCap;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.Collection;

public class ResetCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("ars-reset").
                requires(sender -> sender.hasPermission(2))
                .executes(context -> resetPlayers(context.getSource(), ImmutableList.of(context.getSource().getEntityOrException())))
                .then(Commands.argument("targets", EntityArgument.entities())
                        .executes(context -> resetPlayers(context.getSource(), EntityArgument.getEntities(context, "targets")))));
    }

    private static int resetPlayers(CommandSource source, Collection<? extends Entity> entities) {
        for(Entity e : entities){
            if(!(e instanceof LivingEntity))
                continue;
            DominionCapability.getDominion((LivingEntity) e).ifPresent(iMana -> {
                iMana.setBookTier(0);
                iMana.setGlyphBonus(0);
            });
            FamiliarCap.getFamiliarCap((LivingEntity) e).ifPresent(ifam -> ifam.setUnlockedFamiliars(new ArrayList<>()));
        }
        source.sendSuccess(new TranslationTextComponent("rigoranthusemortisreborn.reset.cleared"), true);
        return 1;
    }
}