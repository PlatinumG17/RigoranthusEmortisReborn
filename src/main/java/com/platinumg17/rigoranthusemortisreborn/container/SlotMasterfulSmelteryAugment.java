package com.platinumg17.rigoranthusemortisreborn.container;
import com.platinumg17.rigoranthusemortisreborn.items.smeltery.ItemAugment;
import com.platinumg17.rigoranthusemortisreborn.tileentity.SmelteryTileEntityBase;

import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotMasterfulSmelteryAugment extends Slot {

    private SmelteryTileEntityBase te;

    public SlotMasterfulSmelteryAugment(SmelteryTileEntityBase te, int slotIndex, int xPosition, int yPosition) {
        super(te, slotIndex, xPosition, yPosition);
        this.te = te;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() instanceof ItemAugment;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public void setChanged() {
        te.onUpdateSent();
    }
}