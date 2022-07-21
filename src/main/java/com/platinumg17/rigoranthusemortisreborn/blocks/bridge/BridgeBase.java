package com.platinumg17.rigoranthusemortisreborn.blocks.bridge;
//
//import net.minecraft.block.*;
//import net.minecraft.block.material.Material;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.fluid.FluidState;
//import net.minecraft.fluid.Fluids;
//import net.minecraft.item.BlockItemUseContext;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.Items;
//import net.minecraft.pathfinding.PathType;
//import net.minecraft.state.*;
//import net.minecraft.state.properties.BlockStateProperties;
//import net.minecraft.util.*;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.BlockRayTraceResult;
//import net.minecraft.util.math.shapes.IBooleanFunction;
//import net.minecraft.util.math.shapes.ISelectionContext;
//import net.minecraft.util.math.shapes.VoxelShape;
//import net.minecraft.util.math.shapes.VoxelShapes;
//import net.minecraft.world.IBlockReader;
//import net.minecraft.world.IWorld;
//import net.minecraft.world.World;
//import net.minecraftforge.common.ToolType;
//
//import java.util.function.ToIntFunction;
//import java.util.stream.Stream;
//
//public class BridgeBase extends Block implements IWaterLoggable {
//    private static final EnumProperty<PartDefinition> PART = EnumProperty.create("part", PartDefinition.class);
//    public static final BooleanProperty WATERLOGGED;
//    public static final DirectionProperty FACING;
//    public static final BooleanProperty LIT;
//    private static final VoxelShape X_AXIS_AABB;
//    private static final VoxelShape Z_AXIS_AABB;
//    public static ToIntFunction<BlockState> lightLevel;
//
//    public static ToIntFunction<BlockState> getLightLevel(int lightValue) {
//        return (state) -> {
//            if (BlockStateProperties.LIT == LIT) {
//                return (Boolean)state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
//            } else {
//                return (Boolean)state.getValue(BlockStateProperties.LIT) ? lightValue : 15;
//            }
//        };
//    }
//
//    public BridgeBase(Properties properties) {
//        super(properties);
//        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false).setValue(PART, PartDefinition.MIDDLE).setValue(WATERLOGGED, false));
//    }
//
//    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos newPos) {
//        return this.getStateforBridge(state, world, pos, (Direction)state.getValue(FACING));
//    }
//
//    private boolean BridgeDirection(IWorld world, BlockPos source, Direction direction, Direction facing) {
//        BlockState state = world.getBlockState(source.relative(direction));
//        if (state.getBlock() == this) {
//            Direction bridgedir = (Direction)state.getValue(FACING);
//            return bridgedir.equals(facing);
//        } else {
//            return false;
//        }
//    }
//
//    private BlockState getStateforBridge(BlockState state, IWorld world, BlockPos pos, Direction dir) {
//        boolean front = this.BridgeDirection(world, pos, dir.getClockWise(), dir);
//        boolean back = this.BridgeDirection(world, pos, dir.getOpposite(), dir);
//        if (front && back) {
//            return state.setValue(PART, PartDefinition.MIDDLE);
//        } else if (front) {
//            return state.setValue(PART, PartDefinition.END);
//        } else {
//            return back ? state.setValue(PART, PartDefinition.END2) : state.setValue(PART, PartDefinition.MIDDLE);
//        }
//    }
//
//    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext selectionContext) {
//        PartDefinition part = (PartDefinition)state.getValue(PART);
//        switch((Direction)state.getValue(FACING)) {
//            case WEST:
//                if (part == PartDefinition.MIDDLE) {return X_AXIS_AABB;}
//                return X_AXIS_AABB;
//            case EAST:
//                if (part == PartDefinition.MIDDLE) {return X_AXIS_AABB;}
//                return X_AXIS_AABB;
//            case NORTH:
//                if (part == PartDefinition.MIDDLE) {return Z_AXIS_AABB;}
//                return Z_AXIS_AABB;
//            case SOUTH:
//                if (part == PartDefinition.MIDDLE) {return Z_AXIS_AABB;}
//                return Z_AXIS_AABB;
//            default:
//                return null;
//        }
//    }
//
//    public static ResourceLocation location(String name) {
//        return new ResourceLocation("mcwbridges", name);
//    }
//
//    public ToolType getHarvestTool(BlockState state) {
//        return this.material != Material.STONE && this.material != Material.METAL ? ToolType.PICKAXE : ToolType.AXE;
//    }
//
//    public void onBroken(World worldIn, BlockPos pos) {
//        worldIn.destroyBlockProgress(1029, pos, 0);
//    }
//
//    public int getHarvestLevel(BlockState state) {
//        return 1;
//    }
//
//    public boolean hasTileEntity(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
//        return false;
//    }
//
//    public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
//        super.playerWillDestroy(world, pos, state, player);
//        Boolean i = (Boolean)state.getValue(LIT);
//        if (i) {
//            dropTorch(world, pos);
//        }
//    }
//
//    public BlockRenderType getRenderType(BlockState state) {
//        return BlockRenderType.MODEL;
//    }
//
//    public static void dropTorch(World world, BlockPos pos) {
//        popResource(world, pos, new ItemStack(Items.TORCH, 1));
//    }
//
//    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
//        ItemStack itemstack = player.getItemInHand(handIn);
//        Item item = itemstack.getItem();
//        Boolean i = (Boolean)state.getValue(LIT);
//        if (i) {
//            state = (BlockState)state.cycle(LIT);
//            worldIn.setBlock(pos, state, 2);
//            dropTorch(worldIn, pos);
//        }
//
//        if (!i && item != Items.LANTERN) {
//            return ActionResultType.PASS;
//        } else {
//            if (item == Items.TORCH && !i) {
//                state = (BlockState)state.cycle(LIT);
//                worldIn.setBlock(pos, state, 2);
//                if (!player.abilities.instabuild) {
//                    itemstack.shrink(1);
//                }
//            }
//            if ((Boolean)state.getValue(WATERLOGGED)) {
//                worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
//            }
//            return ActionResultType.sidedSuccess(worldIn.isClientSide);
//        }
//    }
//
//    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
//        Boolean i = (Boolean)state.getValue(LIT);
//        return i ? 14 : 0;
//    }
//
//    public FluidState getFluidState(BlockState state) {
//        return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
//    }
//
//    public float lightValue(BlockState p_220080_1_, IBlockReader p_220080_2_, BlockPos p_220080_3_) {
//        return 1.0F;
//    }
//
//    public BlockState getStateForPlacement(BlockItemUseContext context) {
//        return (BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
//    }
//
//    public BlockState rotate(BlockState state, Rotation rot) {
//        return (BlockState)state.setValue(FACING, rot.rotate((Direction)state.getValue(FACING)));
//    }
//
//    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
//        builder.add(new Property[]{FACING, WATERLOGGED, PART, LIT});
//    }
//
//    static {
//        WATERLOGGED = BlockStateProperties.WATERLOGGED;
//        FACING = HorizontalBlock.FACING;
//        LIT = BlockStateProperties.LIT;
//        X_AXIS_AABB = (VoxelShape) Stream.of(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)).reduce((v1, v2) -> {
//            return VoxelShapes.join(v1, v2, IBooleanFunction.OR);
//        }).get();
//        Z_AXIS_AABB = (VoxelShape)Stream.of(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)).reduce((v1, v2) -> {
//            return VoxelShapes.join(v1, v2, IBooleanFunction.OR);
//        }).get();
//        lightLevel = (BlockState) -> {
//            return 5;
//        };
//    }
//}