package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.EmorticCoreTile;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class EmorticCore extends ModBlock {

    public EmorticCore() {
        super(defaultProperties().noOcclusion().lightLevel((state) -> 15), "emortic_core");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new EmorticCoreTile();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}