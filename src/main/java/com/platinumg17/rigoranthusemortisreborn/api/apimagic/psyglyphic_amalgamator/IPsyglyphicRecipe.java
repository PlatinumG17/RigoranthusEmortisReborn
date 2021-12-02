package com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.PsyglyphicAmalgamatorTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import javax.annotation.Nullable;
import java.util.List;

public interface IPsyglyphicRecipe extends IRecipe<PsyglyphicAmalgamatorTile> {

    boolean isMatch(List<ItemStack> pedestalItems, ItemStack reagent, PsyglyphicAmalgamatorTile psyglyphicAmalgamatorTile, @Nullable PlayerEntity player);

    /**
     * Tile sensitive result
     */
    ItemStack getResult(List<ItemStack> pedestalItems, ItemStack reagent, PsyglyphicAmalgamatorTile psyglyphicAmalgamatorTile);

    default boolean consumesDominion(){
        return dominionCost() > 0;
    }
    int dominionCost();
}