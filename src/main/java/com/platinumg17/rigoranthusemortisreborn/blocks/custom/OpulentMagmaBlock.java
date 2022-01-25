package com.platinumg17.rigoranthusemortisreborn.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Random;

public class OpulentMagmaBlock extends Block {

    public OpulentMagmaBlock(String name) {
        super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.GOLD).lightLevel((value) -> { return 15; }).strength(8f, 10f)
                .harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().sound(SoundType.LANTERN));
        setRegistryName(name);
    }

    @Override
    public void stepOn(World world, BlockPos pos, Entity entityIn) {
        if (entityIn != null) {
            if (!entityIn.fireImmune() || !(entityIn instanceof PlayerEntity) || !EnchantmentHelper.hasFrostWalker((LivingEntity) entityIn)) {
                entityIn.hurt(DamageSource.HOT_FLOOR, 1.0F);
            }
        }
        super.stepOn(world, pos, entityIn);
    }

    @Override
    public void tick(BlockState state, ServerWorld server, BlockPos pos, Random random) {
        BubbleColumnBlock.growColumn(server, pos.above(), true);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState otherState, IWorld world, BlockPos pos, BlockPos otherPos) {
        if (direction == Direction.UP && otherState.is(Blocks.WATER)) {
            world.getBlockTicks().scheduleTick(pos, this, 20);
        }

        return super.updateShape(state, direction, otherState, world, pos, otherPos);
    }

    @Override
    public void randomTick(BlockState blockState, ServerWorld server, BlockPos pos, Random random) {
        BlockPos blockpos = pos.above();
        if (server.getFluidState(pos).is(FluidTags.WATER)) {
            server.playSound((PlayerEntity)null, pos, SoundEvents.FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (server.random.nextFloat() - server.random.nextFloat()) * 0.8F);
            server.sendParticles(ParticleTypes.LARGE_SMOKE, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.25D, (double)blockpos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
        }
    }

    @Nullable
    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
        return PathNodeType.DAMAGE_OTHER;
    }

    @Override
    public void onPlace(BlockState blockState, World worldIn, BlockPos pos, BlockState state, boolean bool) {
        worldIn.getBlockTicks().scheduleTick(pos, this, 20);
    }
}