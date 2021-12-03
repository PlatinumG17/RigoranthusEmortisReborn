package com.platinumg17.rigoranthusemortisreborn.blocks.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
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
import net.minecraft.world.IWorldReader;

import java.util.Map;

public class BrainBlock extends Block implements IWaterLoggable {

    public BrainBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    private static final Map<Direction, VoxelShape> AABBS =
            Maps.newEnumMap(ImmutableMap.of(
                    Direction.NORTH, box(5.75, 0, 4.5, 10.25, 3.025, 11.5),
                    Direction.SOUTH, box(5.75, 0, 4.5, 10.25, 3.025, 11.5),
                    Direction.WEST, box(4.5, 0, 5.75, 11.5, 3.025, 10.25),
                    Direction.EAST, box(4.5, 0, 5.75, 11.5, 3.025, 10.25)));
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if (context.getClickedFace().getAxis().isHorizontal()) {
            return this.defaultBlockState().setValue(FACING, context.getClickedFace()).setValue(WATERLOGGED, Boolean.valueOf(false));
        } else {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return super.getStateForPlacement(context).setValue(WATERLOGGED, Boolean.valueOf(flag));
            // The player tried to place on the floor or ceiling. Brains don't have models for those facings.
            //return null; // Block the placement outright
        }
    }

    public VoxelShape getShape(BlockState blockState, IBlockReader reader, BlockPos blockPos, ISelectionContext context) {
        return getShape(blockState);
    }

    public static VoxelShape getShape(BlockState blockState) {
        return AABBS.get(blockState.getValue(FACING));
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState state, IWorld world, BlockPos blockPos1, BlockPos blockPos2) {
        if (!blockState.canSurvive(world, blockPos1)) {
            return direction.getOpposite() == blockState.getValue(FACING) && !blockState.canSurvive(world, blockPos1) ? Blocks.AIR.defaultBlockState() : blockState;
        }else {
            if (blockState.getValue(WATERLOGGED)) {
                world.getLiquidTicks().scheduleTick(blockPos1, Fluids.WATER, Fluids.WATER.getTickDelay(world));
            }
            return super.updateShape(blockState, direction, state, world, blockPos1, blockPos2);
        }
    }
    protected boolean mayPlaceOn(BlockState blockState, IBlockReader reader, BlockPos blockPos) {
        return !blockState.getCollisionShape(reader, blockPos).getFaceShape(Direction.UP).isEmpty() || blockState.isFaceSturdy(reader, blockPos, Direction.UP);
    }

    public boolean canSurvive(BlockState blockState, IWorldReader reader, BlockPos blockPos) {
        BlockPos blockpos = blockPos.below();
        return this.mayPlaceOn(reader.getBlockState(blockpos), reader, blockpos);
    }
    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
    }
    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }
    @Override
    public PushReaction getPistonPushReaction(BlockState blockState) {
        return PushReaction.DESTROY;
    }
    public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }
}