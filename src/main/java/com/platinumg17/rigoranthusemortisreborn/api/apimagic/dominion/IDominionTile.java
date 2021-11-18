package com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion;

/**
 * Interface for a generic tile that holds mana.
 */
public interface IDominionTile {

    int getTransferRate();

    boolean canAcceptDominion();

    int getCurrentDominion();

    int getMaxDominion();

    void setMaxDominion(int max);

    int setDominion(final int mana);

    int addDominion(final int manaToAdd);

    int removeDominion(final int manaToRemove);
}