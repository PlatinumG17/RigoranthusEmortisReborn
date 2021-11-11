package com.platinumg17.rigoranthusemortisreborn.api.interfaces;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;

public interface ICanisFoodHandler extends ICanisFoodPredicate {
    /**
     * Checks if the canis can eat the food item
     * used by some food items to apply potion effects
     * @param canisIn The canis eating the item
     * @param stackIn The stack that is being eaten, DO NOT alter the start in this method
     * @param entityIn The entity who fed the canis, usually the player. Can be null probably meaning the canis ate on its own
     * @return If the canis can eat the stack, {@link #consume} is called to eat the stack
     */
    public boolean canConsume(AbstractCanisEntity canisIn, ItemStack stackIn, @Nullable Entity entityIn);

    /**
     * Actually eat the stack,
     * @param canisIn The canis eating the item
     * @param stackIn The stack that is being eaten
     * @param entityIn The entity who fed the canis, usually the player. Can be null probably meaning the canis ate on its own
     * @return
     */
    public ActionResultType consume(AbstractCanisEntity canisIn, ItemStack stackIn, @Nullable Entity entityIn);
}