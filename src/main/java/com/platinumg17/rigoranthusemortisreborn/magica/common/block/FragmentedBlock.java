package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.GravelBlock;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class FragmentedBlock extends GravelBlock {
    public List<ITextComponent> tooltip;
    public FragmentedBlock(AbstractBlock.Properties properties) {
        super(properties);
        setRegistryName(LibBlockNames.FRAGMENTED_COBBLESTONE);
    }
}