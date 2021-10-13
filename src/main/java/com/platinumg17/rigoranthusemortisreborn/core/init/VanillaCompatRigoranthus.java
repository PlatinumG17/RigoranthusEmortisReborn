package com.platinumg17.rigoranthusemortisreborn.core.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;

public class VanillaCompatRigoranthus {
    public static void setup() {
        //Flammability
        registerFlammable(BuildingBlockInit.AZULOREAL_LEAVES.get(), 30, 60);
        registerFlammable(BuildingBlockInit.JESSIC_LEAVES.get(), 30, 60);
    }
    public static void registerFlammable(Block blockIn, int encouragement, int flammability)
    {
        FireBlock fireblock = (FireBlock) Blocks.FIRE;
        fireblock.setFlammable(blockIn, encouragement, flammability);
    }
}
