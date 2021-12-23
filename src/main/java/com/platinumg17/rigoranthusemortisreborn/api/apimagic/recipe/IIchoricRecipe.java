package com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.EmorticCraftingPressTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import javax.annotation.Nullable;

public interface IIchoricRecipe extends IRecipe<EmorticCraftingPressTile> {

    boolean isMatch(ItemStack base, ItemStack reagent, EmorticCraftingPressTile emorticCraftingPressTile, @Nullable PlayerEntity player);
    /**
     * Tile sensitive result
     */
    ItemStack getResult(ItemStack base, ItemStack reagent, EmorticCraftingPressTile emorticCraftingPressTile);

    default boolean consumesDominion(){
        return dominionCost() > 0;
    }
    int dominionCost();
}