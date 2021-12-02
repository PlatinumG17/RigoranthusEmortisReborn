package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.DominionTile;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ExtractorBlock extends ModBlock {
    public ExtractorBlock(Properties properties, String registry) {
        super(properties, registry);
    }

    public ExtractorBlock(String registryName) {
        super(registryName);
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_149653_1_) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);
        DominionTile tile = (DominionTile) worldIn.getBlockEntity(pos);
        if(tile == null)
            return;
        tile.doRandomAction();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}