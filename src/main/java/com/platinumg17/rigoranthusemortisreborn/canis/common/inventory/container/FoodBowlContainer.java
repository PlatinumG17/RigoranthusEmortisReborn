package com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container;

import com.platinumg17.rigoranthusemortisreborn.canis.CanisBlocks;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * @author ProPercivalalb
 */
public class FoodBowlContainer extends Container {

    private TileEntity tileEntity;

    //Server method
    public FoodBowlContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(CanisContainerTypes.FOOD_BOWL.get(), windowId);
        this.tileEntity = world.getBlockEntity(pos);
        IItemHandler inventory = this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(() -> new RuntimeException("Item handler not present."));

        for (int i = 0; i < 1; i++) {
            for (int l = 0; l < 5; l++) {
                this.addSlot(new SlotItemHandler(inventory, l + i * 9, 44 + l * 18, 22 + i * 18));
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int i1 = 0; i1 < 9; i1++) {
                this.addSlot(new Slot(playerInventory, i1 + j * 9 + 9, 8 + i1 * 18, 45 + j * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 103));
        }
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(IWorldPosCallable.create(this.tileEntity.getLevel(), this.tileEntity.getBlockPos()), player, CanisBlocks.FOOD_BOWL.get());
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int i) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (i < 5) {
                if (!moveItemStackTo(itemstack1, 5, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!moveItemStackTo(itemstack1, 0, 5, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
        }
        return itemstack;
    }
}
