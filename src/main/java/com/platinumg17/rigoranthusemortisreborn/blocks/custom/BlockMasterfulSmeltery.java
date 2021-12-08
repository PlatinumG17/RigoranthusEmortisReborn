package com.platinumg17.rigoranthusemortisreborn.blocks.custom;

import com.platinumg17.rigoranthusemortisreborn.blocks.tileentity.MasterfulSmelteryTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.ModBlock;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.stream.Stream;

import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.items.specialized.smeltery.ItemAugment;
import com.platinumg17.rigoranthusemortisreborn.items.specialized.smeltery.ItemSmelteryCopy;

public class BlockMasterfulSmeltery extends ModBlock {
    public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 2);

    public BlockMasterfulSmeltery() {
        super(AbstractBlock.Properties.of(Material.HEAVY_METAL).noCollission().copy(Blocks.IRON_BLOCK), LibBlockNames.MASTERFUL_SMELTERY);
        this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.LIT, false).setValue(TYPE, 0));
    }
    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    public static final VoxelShape SHAPE_NORTH = Stream.of(Block.box(3, 0, 16, 13, 7, 17), Block.box(-1, 0, 0, 2, 3, 3), Block.box(-1, 0, 12, 3, 6, 17), Block.box(13, 0, 12, 17, 6, 17), Block.box(14, 0, 0, 17, 3, 3), Block.box(14, 3, 0, 16, 4, 2), Block.box(14, 6, 6, 16, 8, 16), Block.box(0, 6, 6, 2, 8, 16), Block.box(0, 3, 0, 2, 4, 2), Block.box(2, 0, 0, 14, 6, 1), Block.box(3, 1, 5, 13, 6, 7), Block.box(14, 0, 2, 16, 6, 13), Block.box(0, 0, 2, 2, 6, 13), Block.box(1, 0, 1, 15, 1, 16), Block.box(13, 1, 1, 14, 6, 16), Block.box(2, 1, 1, 3, 6, 16), Block.box(2, 6, 6, 14, 15, 16), Block.box(4, 15, 6, 12, 16, 12)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape SHAPE_EAST = Stream.of(Block.box(-1, 0, 3, 0, 7, 13), Block.box(13, 0, -1, 16, 3, 2), Block.box(-1, 0, -1, 4, 6, 3), Block.box(-1, 0, 13, 4, 6, 17), Block.box(13, 0, 14, 16, 3, 17), Block.box(14, 3, 14, 16, 4, 16), Block.box(0, 6, 14, 10, 8, 16), Block.box(0, 6, -0, 10, 8, 2), Block.box(14, 3, -0, 16, 4, 2), Block.box(15, 0, 2, 16, 6, 14), Block.box(9, 1, 3, 11, 6, 13), Block.box(3, 0, 14, 14, 6, 16), Block.box(3, 0, -0, 14, 6, 2), Block.box(0, 0, 1, 15, 1, 15), Block.box(0, 1, 13, 15, 6, 14), Block.box(0, 1, 2, 15, 6, 3), Block.box(0, 6, 2, 10, 15, 14), Block.box(4, 15, 4, 10, 16, 12)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape SHAPE_SOUTH = Stream.of(Block.box(3, 0, -1, 13, 7, 0), Block.box(14, 0, 13, 17, 3, 16), Block.box(13, 0, -1, 17, 6, 4), Block.box(-1, 0, -1, 3, 6, 4), Block.box(-1, 0, 13, 2, 3, 16), Block.box(0, 3, 14, 2, 4, 16), Block.box(0, 6, 0, 2, 8, 10), Block.box(14, 6, 0, 16, 8, 10), Block.box(14, 3, 14, 16, 4, 16), Block.box(2, 0, 15, 14, 6, 16), Block.box(3, 1, 9, 13, 6, 11), Block.box(0, 0, 3, 2, 6, 14), Block.box(14, 0, 3, 16, 6, 14), Block.box(1, 0, 0, 15, 1, 15), Block.box(2, 1, 0, 3, 6, 15), Block.box(13, 1, 0, 14, 6, 15), Block.box(2, 6, 0, 14, 15, 10), Block.box(4, 15, 4, 12, 16, 10)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    public static final VoxelShape SHAPE_WEST = Stream.of(Block.box(16, 0, 3, 17, 7, 13), Block.box(-0, 0, 14, 3, 3, 17), Block.box(12, 0, 13, 17, 6, 17), Block.box(12, 0, -1, 17, 6, 3), Block.box(-0, 0, -1, 3, 3, 2), Block.box(-0, 3, -0, 2, 4, 2), Block.box(6, 6, -0, 16, 8, 2), Block.box(6, 6, 14, 16, 8, 16), Block.box(-0, 3, 14, 2, 4, 16), Block.box(-0, 0, 2, 1, 6, 14), Block.box(5, 1, 3, 7, 6, 13), Block.box(2, 0, -0, 13, 6, 2), Block.box(2, 0, 14, 13, 6, 16), Block.box(1, 0, 1, 16, 1, 15), Block.box(1, 1, 2, 16, 6, 3), Block.box(1, 1, 13, 16, 6, 14), Block.box(6, 6, 2, 16, 15, 14), Block.box(6, 15, 4, 12, 16, 12)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    public boolean useShapeForLightOcclusion(BlockState p_220074_1_) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        switch(p_220053_1_.getValue(FACING)) {
            case NORTH:
                return SHAPE_NORTH;
            case SOUTH:
                return SHAPE_SOUTH;
            case EAST:
                return SHAPE_EAST;
            case WEST:
                return SHAPE_WEST;
            default:
                return SHAPE_NORTH;
        }
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return 1;
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState state) {
        return ToolType.PICKAXE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.getValue(BlockStateProperties.LIT) ? 14 : 0;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState p_180633_3_, @Nullable LivingEntity entity, ItemStack stack) {
        if (entity != null) {
            MasterfulSmelteryTile te = (MasterfulSmelteryTile) world.getBlockEntity(pos);
            if (stack.hasCustomHoverName()) {
                te.setCustomName(stack.getDisplayName());
            }
            te.totalCookTime = te.getCookTimeConfig();
            te.placeConfig();
        }
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
        ItemStack stack = player.getItemInHand(handIn).copy();
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            if (player.getItemInHand(handIn).getItem() instanceof ItemAugment && !(player.isCrouching())) {
                return this.interactAugment(world, pos, player, handIn, stack);
            } else if (player.getItemInHand(handIn).getItem() instanceof ItemSmelteryCopy && !(player.isCrouching())) {
                return this.interactCopy(world, pos, player, handIn);
            } else {
                this.interactWith(world, pos, player);
            }
            return ActionResultType.SUCCESS;
        }
    }

    private ActionResultType interactCopy(World world, BlockPos pos, PlayerEntity player, Hand handIn) {
        int j = player.inventory.selected;
        ItemStack stack = player.inventory.getItem(j);
        if (!(stack.getItem() instanceof ItemSmelteryCopy)) {
            return ActionResultType.SUCCESS;
        }
        TileEntity te = world.getBlockEntity(pos);
        if (!(te instanceof MasterfulSmelteryTile)) {
            return ActionResultType.SUCCESS;
        }

        int[] settings = new int[((MasterfulSmelteryTile) te).smelterySettings.size()];
        for (int i = 0; i < ((MasterfulSmelteryTile) te).smelterySettings.size(); i++)
        {
            settings[i] = ((MasterfulSmelteryTile) te).smelterySettings.get(i);
        }
        stack.getOrCreateTag().putIntArray("settings", settings);

        ((MasterfulSmelteryTile)te).onUpdateSent();
        player.sendMessage(new StringTextComponent("Settings copied"), player.getUUID());
        return ActionResultType.SUCCESS;
    }
    private ActionResultType interactAugment(World world, BlockPos pos, PlayerEntity player, Hand handIn, ItemStack stack) {
        if (!(player.getItemInHand(handIn).getItem() instanceof ItemAugment)) {
            return ActionResultType.SUCCESS;
        }
        TileEntity te = world.getBlockEntity(pos);
        if (!(te instanceof MasterfulSmelteryTile)) {
            return ActionResultType.SUCCESS;
        }
        if (!(((IInventory) te).getItem(3).isEmpty())) {
            if (!player.isCreative()) {
                InventoryHelper.dropItemStack(world, pos.getX(), pos.getY() + 1, pos.getZ(), ((IInventory) te).getItem(3));
            }
        }
        ItemStack newStack = new ItemStack(stack.getItem(), 1);
        newStack.setTag(stack.getTag());
        ((IInventory) te).setItem(3, newStack);
        world.playSound(null, te.getBlockPos(), SoundEvents.ANVIL_USE, SoundCategory.BLOCKS, 0.9F, 0.9F);
        if (!player.isCreative()) {
            player.getItemInHand(handIn).shrink(1);
        }
        ((MasterfulSmelteryTile)te).onUpdateSent();
        return ActionResultType.SUCCESS;
    }

    private void interactWith(World world, BlockPos pos, PlayerEntity player) {
        TileEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof INamedContainerProvider) {
            NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getBlockPos());
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        if (state.getValue(BlockStateProperties.LIT)) {
            if (world.getBlockEntity(pos) == null) {
                return;
            }
            if (!(world.getBlockEntity(pos) instanceof MasterfulSmelteryTile)) {
                return;
            }
            MasterfulSmelteryTile tile = ((MasterfulSmelteryTile) world.getBlockEntity(pos));
            if (tile.getItem(3).getItem() == MagicItemsRegistry.SMOKING_AUGMENT) {
                double lvt_5_1_ = (double)pos.getX() + 0.5D;
                double lvt_7_1_ = (double)pos.getY();
                double lvt_9_1_ = (double)pos.getZ() + 0.5D;
                if (rand.nextDouble() < 0.1D) {
                    world.playLocalSound(lvt_5_1_, lvt_7_1_, lvt_9_1_, SoundEvents.SMOKER_SMOKE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                }
                world.addParticle(ParticleTypes.SMOKE, lvt_5_1_, lvt_7_1_ + 1.1D, lvt_9_1_, 0.0D, 0.0D, 0.0D);
            }
            else if (tile.getItem(3).getItem() == MagicItemsRegistry.BLASTING_AUGMENT) {
                double lvt_5_1_ = (double)pos.getX() + 0.5D;
                double lvt_7_1_ = (double)pos.getY();
                double lvt_9_1_ = (double)pos.getZ() + 0.5D;
                if (rand.nextDouble() < 0.1D) {
                    world.playLocalSound(lvt_5_1_, lvt_7_1_, lvt_9_1_, SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 0.9F, 0.9F, false);
                }
                Direction lvt_11_1_ = (Direction)state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                Direction.Axis lvt_12_1_ = lvt_11_1_.getAxis();
                double lvt_13_1_ = 0.52D;
                double lvt_15_1_ = rand.nextDouble() * 0.6D - 0.3D;
                double lvt_17_1_ = lvt_12_1_ == Direction.Axis.X ? (double)lvt_11_1_.getStepX() * 0.52D : lvt_15_1_;
                double lvt_19_1_ = rand.nextDouble() * 9.0D / 16.0D;
                double lvt_21_1_ = lvt_12_1_ == Direction.Axis.Z ? (double)lvt_11_1_.getStepZ() * 0.52D : lvt_15_1_;
                world.addParticle(ParticleTypes.SMOKE, lvt_5_1_ + lvt_17_1_, lvt_7_1_ + lvt_19_1_, lvt_9_1_ + lvt_21_1_, 0.0D, 0.0D, 0.0D);
            }
            else
            {
                double d0 = (double) pos.getX() + 0.5D;
                double d1 = (double) pos.getY() + 0.2D;
                double d2 = (double) pos.getZ() + 0.5D;
                if (rand.nextDouble() < 0.1D) {
                    world.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 0.9F, 0.9F, false);
                }
                Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                Direction.Axis direction$axis = direction.getAxis();
                double d3 = 0.52D;
                double d4 = rand.nextDouble() * 0.6D - 0.3D;
                double d5 = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : d4;
                double d6 = rand.nextDouble() * 6.0D / 16.0D;
                double d7 = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.26D : d4;
                world.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState oldState, boolean p_196243_5_) {
    }

    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState oldState, boolean p_196243_5_) {
        if (state.getBlock() != oldState.getBlock()) {
            TileEntity te = world.getBlockEntity(pos);
            if (te instanceof MasterfulSmelteryTile) {
                InventoryHelper.dropContents(world, pos, (MasterfulSmelteryTile) te);
                ((MasterfulSmelteryTile)te).grantStoredRecipeExperience(world, Vector3d.atCenterOf(pos));
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, world, pos, oldState, p_196243_5_);
        }
    }

    public boolean hasComparatorInputOverride(BlockState p_149740_1_) {
        return true;
    }

    public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
        return Container.getRedstoneSignalFromContainer((IInventory) world.getBlockEntity(pos));
    }

    public BlockRenderType getRenderType(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return (BlockState)p_185499_1_.setValue(BlockStateProperties.HORIZONTAL_FACING, p_185499_2_.rotate((Direction)p_185499_1_.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }

    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
        return p_185471_1_.rotate(p_185471_2_.getRotation((Direction)p_185471_1_.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }

    private int calculateOutput(World worldIn, BlockPos pos, BlockState state) {
        MasterfulSmelteryTile tile = ((MasterfulSmelteryTile)worldIn.getBlockEntity(pos));
        int i = this.getComparatorInputOverride(state, worldIn, pos);
        if (tile != null) {
            int j = tile.smelterySettings.get(9);
            return tile.smelterySettings.get(8) == 4 ? Math.max(i - j, 0) : i;
        }
        return 0;
    }

    @Override
    public boolean isSignalSource(BlockState p_149744_1_) {
        return true;
    }

    @Override
    public int getSignal(BlockState p_180656_1_, IBlockReader p_180656_2_, BlockPos p_180656_3_, Direction p_180656_4_) {
        return super.getDirectSignal(p_180656_1_, p_180656_2_, p_180656_3_, p_180656_4_);
    }

    @Override
    public int getDirectSignal(BlockState blockState, IBlockReader world, BlockPos pos, Direction direction) {
        MasterfulSmelteryTile furnace = ((MasterfulSmelteryTile) world.getBlockEntity(pos));
        if (furnace != null) {
            int mode = furnace.smelterySettings.get(8);
            if (mode == 0) {
                return 0;
            }
            else if (mode == 1) {
                return 0;
            }
            else if (mode == 2) {
                return 0;
            }
            else {
                return calculateOutput(furnace.getLevel(), pos, blockState);
            }
        }
        return 0;
    }
    @Nullable
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new MasterfulSmelteryTile();
    }
    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.LIT, TYPE);
    }
}