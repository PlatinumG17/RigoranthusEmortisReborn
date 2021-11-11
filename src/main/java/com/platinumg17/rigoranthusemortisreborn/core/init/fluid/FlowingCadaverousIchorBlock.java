package com.platinumg17.rigoranthusemortisreborn.core.init.fluid;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;

import java.util.function.Supplier;

public class FlowingCadaverousIchorBlock extends FlowingFluidBlock implements IIchorFog {

    protected final Vector3d fogColor;
    protected final float fogDensity;

    public FlowingCadaverousIchorBlock(Supplier<? extends FlowingFluid> fluid, Properties properties) {
        this(fluid, null, 0, properties);
    }

    public FlowingCadaverousIchorBlock(Supplier<? extends FlowingFluid> fluid, Vector3d fogColor, float fogDensity, Properties properties) {
        super(fluid, properties);
        this.fogColor = fogColor;
        this.fogDensity = fogDensity;
    }

    @Override
    public Vector3d getFogColor(BlockState state, IWorldReader world, BlockPos pos, Entity entity, Vector3d originalColor, float partialTicks) {
        return fogColor != null ? fogColor : super.getFogColor(state, world, pos, entity, originalColor, partialTicks);
    }

    @Override
    public float getRigorFogDensity() {
        return fogDensity;
    }

    @Override
    public Vector3d getRigorFogColor(BlockState state, IWorldReader world, BlockPos pos, Entity entity, Vector3d originalColor, float partialTicks) {
        return fogColor;
    }
}