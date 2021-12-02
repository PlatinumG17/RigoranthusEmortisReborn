package com.platinumg17.rigoranthusemortisreborn.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class BrainBlock extends Block {
    public BrainBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape NORTH = Block.box(5.75, 0, 4.5, 10.25, 3.025, 11.5);
    protected static final VoxelShape SOUTH = Block.box(5.75, 0, 4.5, 10.25, 3.025, 11.5);
    protected static final VoxelShape EAST = Block.box(4.5, 0, 5.75, 11.5, 3.025, 10.25);
    protected static final VoxelShape WEST = Block.box(4.5, 0, 5.75, 11.5, 3.025, 10.25);
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.getValue(FACING)) {
            case NORTH:
                return NORTH;
            case SOUTH:
                return SOUTH;
            case EAST:
                return EAST;
            case WEST:
                return WEST;
            default:
                return NORTH;
        }
    }
    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }
        return facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
    @Override public FluidState getFluidState(BlockState state) {return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);}
    @Override public BlockState rotate(BlockState state, Rotation rot) {return state.setValue(FACING, rot.rotate(state.getValue(FACING)));}
    @Override public BlockState mirror(BlockState state, Mirror mirrorIn) {return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));}
    public BlockRenderType getRenderType(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }
    @Override protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {builder.add(FACING, WATERLOGGED);}
}