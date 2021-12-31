package com.platinumg17.rigoranthusemortisreborn.blocks.custom.skull;

import com.platinumg17.rigoranthusemortisreborn.blocks.tileentity.HangingSkullTile;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class HangingSkullBlock extends Block implements IWaterLoggable {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public HangingSkullBlock(String registry) {
        super(AbstractBlock.Properties.of(Material.DECORATION).sound(SoundType.CHAIN).strength(1.0f, 6.0f).noOcclusion().noCollission().lightLevel(state -> 11).harvestLevel(0).harvestTool(ToolType.PICKAXE));
        setRegistryName(registry);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = context.getLevel().getBlockState(blockpos);
        return this.defaultBlockState().setValue(WATERLOGGED, context.getLevel().getFluidState(blockpos).getType() == Fluids.WATER).setValue(FACING, this.getDirectionForPlacement(context));
    }

    private Direction getDirectionForPlacement(BlockItemUseContext context) {
        Direction face = context.getClickedFace();
        Direction playerAim = context.getNearestLookingDirection().getOpposite();
        if (face.getAxis() != Direction.Axis.Y) {
            return face;
        } else {
            Vector3d difference = context.getClickLocation().add(Vector3d.atCenterOf(context.getClickedPos())).add(0.5D, 0.0D, 0.5D);
            return Direction.fromYRot(-Math.toDegrees(Math.atan2(difference.x(), difference.z()))).getOpposite();
        }
    }
    public VoxelShape getOcclusionShape(BlockState state, IBlockReader blockReader, BlockPos pos) {
        return VoxelShapes.empty();
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.box(4, 4, 4, 12, 12, 12);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) {
        return Block.canSupportCenter(reader, pos.above(), Direction.DOWN);
    }
    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState state, IWorld world, BlockPos currentPos, BlockPos newPos) {
        return direction == Direction.UP && !this.canSurvive(blockState, world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, state, world, currentPos, newPos);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
    @Override public FluidState getFluidState(BlockState state) {return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);}
    @Override protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) { builder.add(FACING, WATERLOGGED); }
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rot) { return state.setValue(FACING, rot.rotate(state.getValue(FACING))); }
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirrorIn) { return state.rotate(mirrorIn.getRotation(state.getValue(FACING))); }
    @SuppressWarnings("deprecation")
    @Override public PushReaction getPistonPushReaction(BlockState blockState) {return PushReaction.DESTROY;}
    @SuppressWarnings("deprecation")
    @Override public boolean isPathfindable(BlockState blockState, IBlockReader blockReader, BlockPos pos, PathType pathType) {return false;}
    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return !state.getValue(WATERLOGGED);
    }
    @Override public boolean hasTileEntity(BlockState state) { return true; }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new HangingSkullTile();
    }
}