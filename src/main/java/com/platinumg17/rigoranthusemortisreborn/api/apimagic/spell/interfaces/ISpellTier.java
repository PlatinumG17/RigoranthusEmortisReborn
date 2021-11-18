package com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces;

//TODO 1.17 Redo/remove this interface for better addon support
public interface ISpellTier {
    enum Tier{
        ONE,
        TWO,
        THREE
    }
    /**
     * Get the tier of spells this thing can access
     */
    Tier getTier();
}