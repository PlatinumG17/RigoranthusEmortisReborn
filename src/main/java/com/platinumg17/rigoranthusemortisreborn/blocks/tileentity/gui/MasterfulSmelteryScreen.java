package com.platinumg17.rigoranthusemortisreborn.blocks.tileentity.gui;

import com.platinumg17.rigoranthusemortisreborn.blocks.tileentity.container.MasterfulSmelteryContainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MasterfulSmelteryScreen extends SmelteryScreenBase<MasterfulSmelteryContainer> {

    public MasterfulSmelteryScreen(MasterfulSmelteryContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }
}