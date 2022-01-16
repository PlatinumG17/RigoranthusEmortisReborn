package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IchorExtractorTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class IchorExtractorBlock extends ExtractorBlock {
    public IchorExtractorBlock() {
        super(defaultProperties().noOcclusion().lightLevel(state -> 12), LibBlockNames.ICHOR_EXTRACTOR);
    }

    protected static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 11.8, 12);

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext) { return SHAPE; }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new IchorExtractorTile();
    }
}