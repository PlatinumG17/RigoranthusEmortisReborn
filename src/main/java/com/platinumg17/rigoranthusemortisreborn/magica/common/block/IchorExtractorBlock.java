package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IchorExtractorTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class IchorExtractorBlock extends AbsorbtionBlock {
    public IchorExtractorBlock() {
        super(defaultProperties().noOcclusion().lightLevel(state -> 15), LibBlockNames.ICHOR_EXTRACTOR);
    }

    public IchorExtractorBlock(Properties properties, String registry) {
        super(properties, registry);
    }

    public IchorExtractorBlock(String registryName) {
        super(registryName);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new IchorExtractorTile();
    }
}