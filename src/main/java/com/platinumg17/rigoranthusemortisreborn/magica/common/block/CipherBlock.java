package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.canis.common.util.WorldUtil;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.PsyglyphicCipherTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketREEffect;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Random;

import static com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.PsyglyphicCipherTile.UTILIZED;

public class CipherBlock extends ModBlock implements IWaterLoggable {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public CipherBlock() {
        super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).sound(SoundType.STONE).strength(10f, 15f).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().noOcclusion().lightLevel(state -> 10), LibBlockNames.PSYGLYPHIC_CIPHER);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
    @Override public FluidState getFluidState(BlockState state) {return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);}
    @Override public BlockState rotate(BlockState state, Rotation rot) {return state.setValue(FACING, rot.rotate(state.getValue(FACING)));}
    @Override public BlockState mirror(BlockState state, Mirror mirrorIn) {return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));}
    public BlockRenderType getRenderType(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {builder.add(FACING, WATERLOGGED, UTILIZED);}

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState state = super.getStateForPlacement(context);
        CompoundNBT tag = context.getItemInHand().getTag();
        if (tag != null && tag.contains("BlockEntityTag")) {
            tag = tag.getCompound("BlockEntityTag");
            if (tag.contains("transcribed") && tag.getBoolean("transcribed")) {
                return state.setValue(UTILIZED, true);
            }
        }
        if (context.getNearestLookingDirection().getAxis().isHorizontal()) {
            return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite()).setValue(WATERLOGGED, Boolean.valueOf(false));
        } else {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return state.setValue(WATERLOGGED, Boolean.valueOf(flag));
        }
    }
/*    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if (context.getNearestLookingDirection().getAxis().isHorizontal()) {
            return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite()).setValue(WATERLOGGED, Boolean.valueOf(false));
        } else {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return super.getStateForPlacement(context).setValue(WATERLOGGED, Boolean.valueOf(flag));
        }
    }*/

    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState state, IWorld world, BlockPos blockPos1, BlockPos blockPos2) {
//        PsyglyphicCipherTile cipherTile = (PsyglyphicCipherTile)world.getBlockEntity(blockPos1);
//        if(cipherTile.hasBeenUsed){
//            state.setValue(UTILIZED, true);
//        }
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

    @SuppressWarnings("deprecation")
    @Override
    public boolean canSurvive(BlockState blockState, IWorldReader reader, BlockPos blockPos) {
        BlockPos blockpos = blockPos.below();
        return this.mayPlaceOn(reader.getBlockState(blockpos), reader, blockpos);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        PsyglyphicCipherTile cipher = WorldUtil.getTileEntity(world, pos, PsyglyphicCipherTile.class);
        if (cipher != null)
        if (!cipher.hasBeenUsed) {
            double lvt_5_1_ = (double) pos.getX() + 0.5D;
            double lvt_7_1_ = (double) pos.getY();
            double lvt_9_1_ = (double) pos.getZ() + 0.5D;
            if (rand.nextDouble() < 0.1D) {
                world.playLocalSound(lvt_5_1_, lvt_7_1_, lvt_9_1_, SoundEvents.PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.4F, 0.4F, false);
            }
            for (int i = 0; i < 4; ++i) {
                double d0 = (double) pos.getX() + (double) rand.nextFloat();
                double d1 = (double) pos.getY() + (double) rand.nextFloat();
                double d2 = (double) pos.getZ() + (double) rand.nextFloat();
                double d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
                double d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
                double d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
                int j = rand.nextInt(2) * 2 - 1;
                if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
                    d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
                    d3 = rand.nextFloat() * 2.0F * (float) j;
                } else {
                    d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
                    d5 = rand.nextFloat() * 2.0F * (float) j;
                }
                world.addParticle(ParticleTypes.ENCHANT, d0, d1, d2, d3, d4, d5);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(worldIn.isClientSide || handIn != Hand.MAIN_HAND)
            return ActionResultType.SUCCESS;
        ItemStack stack = player.getItemInHand(handIn);
        if (!stack.isEmpty() && stack.getItem() == Items.PAPER) {
            PsyglyphicCipherTile cipher = WorldUtil.getTileEntity(worldIn, pos, PsyglyphicCipherTile.class);
            if (cipher != null) {
                if (cipher.hasBeenUsed) {
                    PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.tooltip.cipher_used"));
                    return ActionResultType.SUCCESS;
                } else {
                    worldIn.playSound(player, pos, SoundEvents.VILLAGER_WORK_CARTOGRAPHER, SoundCategory.BLOCKS, 0.8F, 0.7F);
                    player.inventory.add(ItemInit.PSYGLYPHIC_SCRIPT.get().getDefaultInstance());
                    PortUtil.sendMessage(player, new TranslationTextComponent("tooltip.rigoranthusemortisreborn.cipher.not_used"));
                    state.setValue(UTILIZED, true);
                    if (!player.abilities.instabuild) {
                        stack.shrink(1);
                    }
                    Networking.sendToNearby(worldIn, pos, new PacketREEffect(PacketREEffect.EffectType.BURST, pos, new ParticleColor.IntWrapper(255, 0, 0)));
                }
            }
        }
        worldIn.sendBlockUpdated(pos, state, state, 3);
        return ActionResultType.SUCCESS;
    }
//    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
//        ItemStack stack = player.getItemInHand(handIn);
//        if (!stack.isEmpty() && stack.getItem() == Items.PAPER) {
//            if(!worldIn.isClientSide) {
//                PsyglyphicCipherTile cipher = WorldUtil.getTileEntity(worldIn, pos, PsyglyphicCipherTile.class);
//                if (cipher != null) {
//                    if (!cipher.hasBeenUsed) {
//                        worldIn.playSound(player, pos, SoundEvents.VILLAGER_WORK_CARTOGRAPHER, SoundCategory.BLOCKS, 0.8F, 0.7F);
//                        player.inventory.add(ItemInit.PSYGLYPHIC_SCRIPT.get().getDefaultInstance());
//                        PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.cipher.used"));
//                        state.setValue(UTILIZED, true);
//                        if (!player.abilities.instabuild) {
//                            stack.shrink(1);
//                        }
//                        Networking.sendToNearby(worldIn, pos, new PacketREEffect(PacketREEffect.EffectType.BURST, pos, new ParticleColor.IntWrapper(255, 0, 0)));
//                    }
//                }
//            }
//        }
//        worldIn.sendBlockUpdated(pos, state, state, 2);
//        return ActionResultType.SUCCESS;//else { return ActionResultType.FAIL;
//    }

//    @Override
//    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
//        ItemStack stack = player.getItemInHand(handIn);
//        PsyglyphicCipherTile cipherTile = (PsyglyphicCipherTile)worldIn.getBlockEntity(pos);
//        if (!worldIn.isClientSide && stack.getItem() == Items.PAPER) {
//            if (!(cipherTile.hasBeenUsed)) {
//                state.setValue(UTILIZED, true);
//                PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.cipher.used"));
//                if (!player.abilities.instabuild){
//                    stack.shrink(1);
//                }
//            }
//            return ActionResultType.SUCCESS;
//        } else
//            return ActionResultType.PASS;
////        return super.use(state, worldIn, pos, player, handIn, hit);
//    }
//    @Override
//    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
//        super.setPlacedBy(worldIn, pos, state, placer, stack);
//    } //TODO  is this needed? does this do anything?

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return !state.getValue(WATERLOGGED);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState blockState) {
        return PushReaction.BLOCK;
    }
    public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.box(2, 0, 2, 14, 15, 14);
    }
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PsyglyphicCipherTile();
    }
}