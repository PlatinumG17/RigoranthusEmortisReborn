package com.platinumg17.rigoranthusemortisreborn.core.init.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;

public interface IIchorFog {
        float getRigorFogDensity();
        Vector3d getRigorFogColor(BlockState state, IWorldReader world, BlockPos pos, Entity entity, Vector3d originalColor, float partialTicks);
}
