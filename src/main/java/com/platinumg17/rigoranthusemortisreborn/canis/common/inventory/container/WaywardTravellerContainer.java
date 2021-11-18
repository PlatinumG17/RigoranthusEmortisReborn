package com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container;

import com.platinumg17.rigoranthusemortisreborn.canis.CanisContainerTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisSkills;
import com.platinumg17.rigoranthusemortisreborn.canis.common.skill.WaywardTravellerSkill;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;

/**
 * @author ProPercivalalb
 */
public class WaywardTravellerContainer extends Container {

    private AbstractCanisEntity canis;
    private ItemStackHandler packInventory;
    private int level;

    public WaywardTravellerContainer(int windowId, PlayerInventory playerInventory, AbstractCanisEntity canisIn) {
        super(CanisContainerTypes.WAYWARD_TRAVELLER.get(), windowId);
        this.canis = canisIn;
        this.level = MathHelper.clamp(canisIn.getLevel(CanisSkills.WAYWARD_TRAVELLER), 0, 5);
        this.packInventory = canisIn.getCapability(WaywardTravellerSkill.WAYWARD_TRAVELLER_CAPABILITY).orElseThrow(() -> new RuntimeException("Item handler not present."));

        for (int j = 0; j < 3; j++) {
            for (int i1 = 0; i1 < this.level; i1++) {
                this.addSlot(new SlotItemHandler(this.packInventory, i1 * 3 + j, 79 + 18 * i1, 1 + 18 * j + 24));
            }
        }
        int var3;
        int var4;

        for (var3 = 0; var3 < 3; ++var3) {
            for (var4 = 0; var4 < 9; ++var4) {
                this.addSlot(new Slot(playerInventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }
        for (var3 = 0; var3 < 9; ++var3) {
            this.addSlot(new Slot(playerInventory, var3, 8 + var3 * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int i) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);
        int waywardtravellerLevel = MathHelper.clamp(this.level, 0, 5);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (i < 3 * waywardtravellerLevel) {
                if (!this.moveItemStackTo(itemstack1, 3 * waywardtravellerLevel, this.slots.size(), true))
                    return ItemStack.EMPTY;
            }
            else if (!this.moveItemStackTo(itemstack1, 0, 3 * waywardtravellerLevel, false))
                return ItemStack.EMPTY;

            if (itemstack1.isEmpty())
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();

            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.canis.distanceToSqr(player) < 144D;
    }

    @Override
    public void removed(PlayerEntity player) {
        super.removed(player);
    }

    public AbstractCanisEntity getCanis() {
        return this.canis;
    }

    public int getCanisLevel() {
        return this.level;
    }
}
