package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.BlockUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.RitualTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class RitualBlock extends ModBlock {
    public RitualBlock(String registryName) {
        super(defaultProperties().noOcclusion().lightLevel((b) -> b.getValue(LIT) ? 15 : 0), registryName);
    }

    public static final Property<Boolean> LIT = BooleanProperty.create("lit");

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!(worldIn.getBlockEntity(pos) instanceof RitualTile) || handIn != Hand.MAIN_HAND || !player.getMainHandItem().isEmpty())
            return super.use(state, worldIn, pos, player, handIn, hit);
        RitualTile tile = (RitualTile) worldIn.getBlockEntity(pos);
        if (tile.ritual != null && !tile.isRitualDone()) {
            tile.startRitual();
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState p_149656_1_) {
        return PushReaction.BLOCK;
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, world, pos, blockIn, fromPos, isMoving);
        if (!world.isClientSide() && world.getBlockEntity(pos) instanceof RitualTile) {
            ((RitualTile) world.getBlockEntity(pos)).isOff = world.hasNeighborSignal(pos);
            BlockUtil.safelyUpdateState(world, pos);
        }
    }

    @Override
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.playerWillDestroy(worldIn, pos, state, player);
        if (worldIn.getBlockEntity(pos) instanceof RitualTile) {
            RitualTile tile = (RitualTile) worldIn.getBlockEntity(pos);
            if (tile.ritual != null && !tile.ritual.isRunning() && !tile.ritual.isDone()) {
                worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(RigoranthusEmortisRebornAPI.getInstance().getRitualItemMap().get(tile.ritual.getID()))));
            }
        }
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if (entity != null) {
            world.setBlock(pos, state.setValue(LIT, false), 2);
        }
    }

    private static final VoxelShape VESSEL_SHAPE = Stream.of(
            Block.box(4, 14.5, 4, 12, 14.7, 12),
            Block.box(2, 0, 2, 4, 3, 4),
            Block.box(12, 0, 2, 14, 3, 4),
            Block.box(2, 0, 12, 4, 3, 14),
            Block.box(12, 0, 12, 14, 3, 14),
            Block.box(12, 13, 12, 14, 16, 14),
            Block.box(2, 13, 12, 4, 16, 14),
            Block.box(2, 13, 2, 4, 16, 4),
            Block.box(12, 13, 2, 14, 16, 4),
            Block.box(2, 0, 10, 3, 1, 12),
            Block.box(2, 0, 4, 3, 1, 6),
            Block.box(13, 0, 4, 14, 1, 6),
            Block.box(13, 0, 10, 14, 1, 12),
            Block.box(4, 0, 2, 6, 1, 3),
            Block.box(10, 0, 2, 12, 1, 3),
            Block.box(10, 0, 13, 12, 1, 14),
            Block.box(4, 0, 13, 6, 1, 14),
            Block.box(13, 14, 10, 14, 15, 12),
            Block.box(13, 14, 4, 14, 15, 6),
            Block.box(12, 14, 10, 13, 15, 11),
            Block.box(12, 14, 5, 13, 15, 6),
            Block.box(4, 14, 13, 6, 15, 14),
            Block.box(2, 14, 10, 3, 15, 12),
            Block.box(4, 14.7, 5, 5, 16, 11),
            Block.box(11, 14.7, 5, 12, 16, 11),
            Block.box(4, 14, 2, 6, 15, 3),
            Block.box(10, 14, 2, 12, 15, 3),
            Block.box(4, 14.7, 4, 12, 16, 5),
            Block.box(4, 14.7, 11, 12, 16, 12),
            Block.box(10, 14, 3, 11, 15, 4),
            Block.box(5, 14, 3, 6, 15, 4),
            Block.box(10, 14, 13, 12, 15, 14),
            Block.box(10, 14, 12, 11, 15, 13),
            Block.box(5, 14, 12, 6, 15, 13),
            Block.box(2, 14, 4, 3, 15, 6),
            Block.box(3, 14, 10, 4, 15, 11),
            Block.box(3, 14, 5, 4, 15, 6)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    public VoxelShape getShape(BlockState blockState, IBlockReader reader, BlockPos blockPos, ISelectionContext context) {
        return VESSEL_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new RitualTile();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}