package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.PsyglyphicCipherTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

public class PsyglyphicBlock extends ModBlock implements IWaterLoggable {
//    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public PsyglyphicBlock() {
        super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).sound(SoundType.STONE).strength(10f, 15f).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().noOcclusion().lightLevel(state -> 10), LibBlockNames.PSYGLYPHIC_CIPHER);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)));
    }
    public PsyglyphicBlock(AbstractBlock.Properties properties, String registryName){
        super(properties, registryName);
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override public FluidState getFluidState(BlockState state) {return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);}
//    @Override public BlockState rotate(BlockState state, Rotation rot) {return state.setValue(FACING, rot.rotate(state.getValue(FACING)));}
//    @Override public BlockState mirror(BlockState state, Mirror mirrorIn) {return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));}
    public BlockRenderType getRenderType(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
    @Override protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {builder.add(/*FACING,*/ WATERLOGGED);}

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
//        if (context.getClickedFace().getAxis().isHorizontal()) {
//            return this.defaultBlockState().setValue(FACING, context.getClickedFace()).setValue(WATERLOGGED, Boolean.valueOf(false));
//        } else {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return super.getStateForPlacement(context).setValue(WATERLOGGED, Boolean.valueOf(flag));
            // The player tried to place on the floor or ceiling. Ciphers don't have models for those facings.
            //return null; // Block the placement outright
//        }
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.box(2, 0, 2, 14, 15, 14);
    }
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState state, IWorld world, BlockPos blockPos1, BlockPos blockPos2) {
        if (!blockState.canSurvive(world, blockPos1)) {
            return /*direction.getOpposite() == blockState.getValue(FACING) && */!blockState.canSurvive(world, blockPos1) ? Blocks.AIR.defaultBlockState() : blockState;
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

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        double lvt_5_1_ = (double)pos.getX() + 0.5D;
        double lvt_7_1_ = (double)pos.getY();
        double lvt_9_1_ = (double)pos.getZ() + 0.5D;
        if (rand.nextDouble() < 0.1D) {
            world.playLocalSound(lvt_5_1_, lvt_7_1_, lvt_9_1_, SoundEvents.PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.4F, 0.4F, false);
        }
        for(int i = 0; i < 4; ++i) {
            double d0 = (double)pos.getX() + (double)rand.nextFloat();
            double d1 = (double)pos.getY() + (double)rand.nextFloat();
            double d2 = (double)pos.getZ() + (double)rand.nextFloat();
            double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            int j = rand.nextInt(2) * 2 - 1;
            if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                d3 = rand.nextFloat() * 2.0F * (float)j;
            } else {
                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = rand.nextFloat() * 2.0F * (float)j;
            }
            world.addParticle(ParticleTypes.ENCHANT, d0, d1, d2, d3, d4, d5);
        }
    }
    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getItemInHand(handIn);

        if(!worldIn.isClientSide && stack.getItem() == Items.PAPER){
            if(!((PsyglyphicCipherTile)worldIn.getBlockEntity(pos)).hasBeenUsed){
                ((PsyglyphicCipherTile)worldIn.getBlockEntity(pos)).hasBeenUsed = true;
                PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.cipher.used"));
                return ActionResultType.SUCCESS;
            }
        }
//        ((PsyglyphicCipherTile)worldIn.getBlockEntity(pos)).save(this.hasBeenUsed);
//        PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.spell_set"));
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
    } //TODO  is this needed? does this do anything?
    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PsyglyphicCipherTile();
    }
    @Override
    public PushReaction getPistonPushReaction(BlockState blockState) {
        return PushReaction.IGNORE;
    }
    public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }
}