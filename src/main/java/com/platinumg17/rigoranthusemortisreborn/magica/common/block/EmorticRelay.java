package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.EmorticRelayTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class EmorticRelay extends ModBlock {

    public EmorticRelay() {
        this(LibBlockNames.EMORTIC_RELAY);
    }

    public EmorticRelay(String registryName){
        this(defaultProperties().lightLevel((blockState) ->8).noOcclusion(), registryName);
    }

    public EmorticRelay(AbstractBlock.Properties properties, String registryName){
        super(properties, registryName);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new EmorticRelayTile();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.box(3, 3, 3, 13, 13, 13);
    }
}