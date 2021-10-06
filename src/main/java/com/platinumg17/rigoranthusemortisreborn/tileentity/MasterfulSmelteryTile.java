package com.platinumg17.rigoranthusemortisreborn.tileentity;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.container.MasterfulSmelteryContainer;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.common.ForgeConfigSpec;

public class MasterfulSmelteryTile extends SmelteryTileEntityBase {
    public MasterfulSmelteryTile() {
        super(Registration.MASTERFUL_SMELTERY_TILE.get());
    }

    @Override
    public ForgeConfigSpec.IntValue getCookTimeConfig() {
        return Config.masterfulSmelterySpeed;
    }

    @Override
    public String IgetName() {
        return "container.rigoranthusemortisreborn.masterful_smeltery";
    }

    @Override
    public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new MasterfulSmelteryContainer(i, level, worldPosition, playerInventory, playerEntity, this.fields);
    }
}