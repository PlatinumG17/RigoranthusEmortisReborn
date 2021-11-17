package com.platinumg17.rigoranthusemortisreborn.api.client;

import net.minecraft.item.ItemStack;

public interface IDisplayMana {
    /**
     * If the held itemstack should display the mana bar
     */
    default boolean shouldDisplay(ItemStack stack){
        return true;
    }
}