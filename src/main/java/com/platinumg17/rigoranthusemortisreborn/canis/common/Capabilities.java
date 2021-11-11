package com.platinumg17.rigoranthusemortisreborn.canis.common;

import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.WaywardTravellerItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class Capabilities {

    public static void init() {
        CapabilityManager.INSTANCE.register(WaywardTravellerItemHandler.class, new Capability.IStorage<WaywardTravellerItemHandler>()  {

            @Override
            public INBT writeNBT(Capability<WaywardTravellerItemHandler> capability, WaywardTravellerItemHandler instance, Direction side)  {
                ListNBT nbtTagList = new ListNBT();
                int size = instance.getSlots();
                for (int i = 0; i < size; i++) {
                    ItemStack stack = instance.getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        CompoundNBT itemTag = new CompoundNBT();
                        itemTag.putInt("Slot", i);
                        stack.save(itemTag);
                        nbtTagList.add(itemTag);
                    }
                }
                return nbtTagList;
            }

            @Override
            public void readNBT(Capability<WaywardTravellerItemHandler> capability, WaywardTravellerItemHandler instance, Direction side, INBT base)  {
                ListNBT tagList = (ListNBT) base;
                for (int i = 0; i < tagList.size(); i++) {
                    CompoundNBT itemTags = tagList.getCompound(i);
                    int j = itemTags.getInt("Slot");

                    if (j >= 0 && j < instance.getSlots())  {
                        instance.setStackInSlot(j, ItemStack.of(itemTags));
                    }
                }
            }
        }, WaywardTravellerItemHandler::new);
    }
}
