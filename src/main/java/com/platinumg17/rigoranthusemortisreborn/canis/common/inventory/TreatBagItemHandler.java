package com.platinumg17.rigoranthusemortisreborn.canis.common.inventory;

import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.FoodHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.CanisTags;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class TreatBagItemHandler extends ItemStackHandler {
    private ItemStack bag;
    public TreatBagItemHandler(ItemStack bag) {
        super(5);
        this.bag = bag;
        CompoundNBT inventoryNBT = bag.getTagElement("inventory");
        if (inventoryNBT != null) {
            this.deserializeNBT(inventoryNBT);
        }
    }
    @Override
    protected void onContentsChanged(int slot) {
        this.bag.getOrCreateTagElement("inventory").merge(this.serializeNBT());
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return ((FoodHandler.isFood(stack).isPresent()) || (stack.getItem().is(CanisTags.TREATS)));
    }
}