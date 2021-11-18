package com.platinumg17.rigoranthusemortisreborn.api.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface ICanisItem {
    /**
     * Implement on item class called when player interacts with a canis
     * @param canisIn The canis the item is being used on
     * @param worldIn The world the canis is in
     * @param playerIn The player who clicked
     * @param handIn The hand used
     * @return The result of the interaction
     */
    public ActionResultType processInteract(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn);

    @Deprecated
    default ActionResultType onInteractWithCanis(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn) {
        return processInteract(canisIn, worldIn, playerIn, handIn);
    }
}