package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.DominionExtractorTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class DominionExtractorBlock extends ExtractorBlock {
    public DominionExtractorBlock() {
        super(defaultProperties().noOcclusion().lightLevel(state -> 15), LibBlockNames.DOMINION_EXTRACTOR);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DominionExtractorTile();
    }
}