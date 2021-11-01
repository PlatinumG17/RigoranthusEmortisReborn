package com.platinumg17.rigoranthusemortisreborn.tileentity.container;

import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.IIntArray;

public class MasterfulSmelteryContainer extends MasterfulSmelteryContainerBase {

    public MasterfulSmelteryContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(Registration.MASTERFUL_SMELTERY_CONTAINER.get(), windowId, world, pos, playerInventory, player);
    }

    public MasterfulSmelteryContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IIntArray fields) {
        super(Registration.MASTERFUL_SMELTERY_CONTAINER.get(), windowId, world, pos, playerInventory, player, fields);
    }
    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return stillValid(IWorldPosCallable.create(te.getLevel(), te.getBlockPos()), playerEntity, Registration.MASTERFUL_SMELTERY.get());
    }
}