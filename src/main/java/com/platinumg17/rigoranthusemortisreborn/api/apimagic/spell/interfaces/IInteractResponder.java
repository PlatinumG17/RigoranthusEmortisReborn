package com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces;

import net.minecraft.item.ItemStack;

/**
 * Used by spell casters that support the Interact spell
 */
public interface IInteractResponder {
    /**
     * @return the held item used by the fake player.
     */
    ItemStack getHeldItem();
}