package com.platinumg17.rigoranthusemortisreborn.world.gen.plants;

import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.items.food.EmortisEdibles;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SpectabilisBushBlock extends SweetBerryBushBlock implements IGrowable {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape SAPLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 12.0D, 7.0D, 12.0D);
    private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 14.0D, 10.0D, 14.0D);
    private static final VoxelShape END_GROWTH_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 13.0D, 15.0D);

    public SpectabilisBushBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
    }
    @Override
    public ItemStack getCloneItemStack(IBlockReader reader, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(ItemInit.BILIS_BERRY.get());
    }

    public VoxelShape getShape(BlockState blockState, IBlockReader reader, BlockPos blockPos, ISelectionContext ctx) {
        if (blockState.getValue(AGE) == 0) {
            return SAPLING_SHAPE;
        } else {
            return blockState.getValue(AGE) < 3 ? MID_GROWTH_SHAPE : super.getShape(blockState, reader, blockPos, ctx);
        }
    }
    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return blockState.getValue(AGE) < 3;
    }
    @Override
    public void randomTick(BlockState blockState, ServerWorld server, BlockPos blockPos, Random random) {
        int i = blockState.getValue(AGE);
        if (i < 3 && server.getRawBrightness(blockPos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(server, blockPos, blockState,random.nextInt(5) == 0)) {
            server.setBlock(blockPos, blockState.setValue(AGE, Integer.valueOf(i + 1)), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(server, blockPos, blockState);
        }

    }
    @Override
    public void entityInside(BlockState blockState, World world, BlockPos blockPos, Entity entityIn) {
        if (entityIn instanceof LivingEntity && entityIn.getType() != EntityType.BEE) {
            entityIn.makeStuckInBlock(blockState, new Vector3d((double)0.95F, 0.95D, (double)0.95F));
//            if (!world.isClientSide && blockState.getValue(AGE) > 2 && (entityIn.xOld != entityIn.getX() || entityIn.zOld != entityIn.getZ())) {
//                double d0 = Math.abs(entityIn.getX() - entityIn.xOld);
//                double d1 = Math.abs(entityIn.getZ() - entityIn.zOld);
//                if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
//                    entityIn.setNoGravity(true);
//                }
//            }
        }
    }
    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult ray) {
        int i = blockState.getValue(AGE);
        boolean flag = i == 3;
        if (!flag && playerIn.getItemInHand(hand).getItem() == Items.BONE_MEAL) {
            return ActionResultType.PASS;
        } else if (i > 1) {
            int j = 1 + world.random.nextInt(2);
            popResource(world, blockPos, new ItemStack(ItemInit.BILIS_BERRY.get(), j + (flag ? 1 : 0)));
            world.playSound((PlayerEntity)null, blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            world.setBlock(blockPos, blockState.setValue(AGE, Integer.valueOf(1)), 2);
            return ActionResultType.sidedSuccess(world.isClientSide);
        } else {
            return super.use(blockState, world, blockPos, playerIn, hand, ray);
        }
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(AGE);
    }
    @Override
    public boolean isValidBonemealTarget(IBlockReader reader, BlockPos blockPos, BlockState blockState, boolean valid) {
        return blockState.getValue(AGE) < 3;
    }
    @Override
    public boolean isBonemealSuccess(World world, Random random, BlockPos blockPos, BlockState blockState) {
        return true;
    }
    @Override
    public void performBonemeal(ServerWorld server, Random random, BlockPos blockPos, BlockState blockState) {
        int i = Math.min(3, blockState.getValue(AGE) + 1);
        server.setBlock(blockPos, blockState.setValue(AGE, Integer.valueOf(i)), 2);
    }
}