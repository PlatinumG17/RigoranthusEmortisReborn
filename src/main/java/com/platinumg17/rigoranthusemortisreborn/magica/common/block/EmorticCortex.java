package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.EmorticCortexTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class EmorticCortex extends ModBlock {

    public EmorticCortex() {
        super(defaultProperties().noOcclusion().lightLevel((state) -> 15), "emortic_cortex");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    public static final VoxelShape CORTEX_SHAPE = Stream.of(
            Block.box(1, 1.775, 1, 2, 10.775, 15),
            Block.box(14, 1.775, 1, 15, 10.775, 15),
            Block.box(1, 1.775, 1, 15, 10.775, 2),
            Block.box(1, 1.775, 14, 15, 10.775, 15),
            Block.box(0, 1.025, 0, 2.75, 11.725, 2.75),
            Block.box(13.25, 1.025, 0, 16, 11.725, 2.75),
            Block.box(13.25, 1.025, 13.25, 16, 11.725, 16),
            Block.box(0, 1.025, 13.25, 2.75, 11.725, 16),
            Block.box(0, 0.025, 0, 16, 1.025, 16)
            ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return CORTEX_SHAPE;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new EmorticCortexTile();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}