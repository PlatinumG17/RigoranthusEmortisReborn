package com.platinumg17.rigoranthusemortisreborn.api.apimagic.util;

import com.platinumg17.rigoranthusemortisreborn.canis.common.items.CommandWritItem;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.SpellBook;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class StackUtil {
    public static @Nonnull ItemStack getHeldSpellbook(PlayerEntity playerEntity){
        ItemStack book = playerEntity.getMainHandItem().getItem() instanceof SpellBook ? playerEntity.getMainHandItem() : null;
        return book == null ? (playerEntity.getOffhandItem().getItem() instanceof SpellBook ? playerEntity.getOffhandItem() : ItemStack.EMPTY) : book;
    }
    public static @Nonnull ItemStack getHeldWhistle(PlayerEntity playerEntity){
        ItemStack whistle = playerEntity.getMainHandItem().getItem() instanceof CommandWritItem ? playerEntity.getMainHandItem() : null;
        return whistle == null ? (playerEntity.getOffhandItem().getItem() instanceof CommandWritItem ? playerEntity.getOffhandItem() : ItemStack.EMPTY) : whistle;
    }
}