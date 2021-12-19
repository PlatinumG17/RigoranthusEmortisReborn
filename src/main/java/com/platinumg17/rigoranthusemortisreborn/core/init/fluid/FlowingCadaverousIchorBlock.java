package com.platinumg17.rigoranthusemortisreborn.core.init.fluid;

import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.EmptyFluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.function.Supplier;

public class FlowingCadaverousIchorBlock extends FlowingFluidBlock {//implements IIchorFog {

//    protected final Vector3d fogColor;
//    protected final float fogDensity;

    public FlowingCadaverousIchorBlock(Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid, properties);
    }

//    public FlowingCadaverousIchorBlock(Supplier<? extends FlowingFluid> fluid, Vector3d fogColor, float fogDensity, Properties properties) {
//        super(fluid, properties);
//        this.fogColor = fogColor;
//        this.fogDensity = fogDensity;
//    }
//
//    @Override
//    public Vector3d getFogColor(BlockState state, IWorldReader world, BlockPos pos, Entity entity, Vector3d originalColor, float partialTicks) {
//        return fogColor != null ? fogColor : super.getFogColor(state, world, pos, entity, originalColor, partialTicks);
//    }
//
//    @Override
//    public float getRigorFogDensity() {
//        return fogDensity;
//    }
//
//    @Override
//    public Vector3d getRigorFogColor(BlockState state, IWorldReader world, BlockPos pos, Entity entity, Vector3d originalColor, float partialTicks) {
//        return fogColor;
//    }


    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (this.reactWithNeighbors(worldIn, pos, state)) {
            worldIn.getLiquidTicks().scheduleTick(pos, state.getFluidState().getType(), this.getFluid().getTickDelay(worldIn));
        }
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (this.reactWithNeighbors(worldIn, pos, state)) {
            worldIn.getLiquidTicks().scheduleTick(pos, state.getFluidState().getType(), this.getFluid().getTickDelay(worldIn));
        }
    }

    private boolean reactWithNeighbors(World world, BlockPos pos, BlockState state) {
        for (Direction dir : Direction.values()) {
            FluidState otherState = world.getFluidState(pos.relative(dir));
            Fluid otherFluid = otherState.getType();
            if (otherFluid instanceof FlowingFluid) {
                otherFluid = ((FlowingFluid) otherFluid).getSource();
            }
            if (otherFluid instanceof EmptyFluid || otherFluid.equals(this.getFluid())) {
                continue;
            }
            boolean isHot = otherFluid.getAttributes().getTemperature(world, pos.relative(dir)) > 600;
            if (isHot) {
                world.setBlock(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, BlockRegistry.DOMINION_GEM_BLOCK.defaultBlockState()), 3);
            }
        }
        return true;
    }
}