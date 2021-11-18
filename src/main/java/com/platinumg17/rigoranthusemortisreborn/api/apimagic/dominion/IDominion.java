package com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion;

public interface IDominion {

    double getCurrentDominion();

    int getMaxDominion();

    void setMaxDominion(int max);

    double setDominion(final double mana);

    double addDominion(final double manaToAdd);

    double removeDominion(final double manaToRemove);

    default int getGlyphBonus(){
        return 0;
    }

    default int getBookTier(){
        return 0;
    }

    default void setGlyphBonus(int bonus){}

    default void setBookTier(int tier){}
}