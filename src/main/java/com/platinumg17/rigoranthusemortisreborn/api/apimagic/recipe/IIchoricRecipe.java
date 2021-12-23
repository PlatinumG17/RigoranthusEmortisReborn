package com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IchorCrystallizerTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import javax.annotation.Nullable;

public interface IIchoricRecipe extends IRecipe<IchorCrystallizerTile> {

    boolean isMatch(ItemStack base, ItemStack reagent, IchorCrystallizerTile ichorCrystallizerTile, @Nullable PlayerEntity player);
    /**
     * Tile sensitive result
     */
    ItemStack getResult(ItemStack base, ItemStack reagent, IchorCrystallizerTile ichorCrystallizerTile);

    default boolean consumesDominion(){
        return dominionCost() > 0;
    }
    int dominionCost();
}