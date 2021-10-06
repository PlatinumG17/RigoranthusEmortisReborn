package com.platinumg17.rigoranthusemortisreborn.gui;

import com.platinumg17.rigoranthusemortisreborn.container.MasterfulSmelteryContainer;

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