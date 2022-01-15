package com.platinumg17.rigoranthusemortisreborn.canis.common.items;

import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SummonedCadaver;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Objects;

public class CadaverSummoningCharmItem extends Item {

    public CadaverSummoningCharmItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        if (world.isClientSide || !(world instanceof ServerWorld)) {
            return ActionResultType.SUCCESS;
        } else {
            PlayerEntity player = context.getPlayer();
            ItemStack itemstack = context.getItemInHand();
            BlockPos blockpos = context.getClickedPos();
            Direction enumfacing = context.getClickedFace();
            BlockState iblockstate = world.getBlockState(blockpos);

            BlockPos blockpos1;
            if (iblockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(enumfacing);
            }

            Entity entity = ModEntities.SUMMONED_CADAVER.spawn((ServerWorld) world, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, !Objects.equals(blockpos, blockpos1) && enumfacing == Direction.UP, false);
            if (entity instanceof SummonedCadaver) {
                SummonedCadaver cadaver = (SummonedCadaver)entity;
                if (player != null) {
                    cadaver.setTame(true);
                    cadaver.setCustomName(player.getDisplayName().copy().append(new TranslationTextComponent("entity.rigoranthusemortisreborn.familiar_cadaver")).setStyle(Style.EMPTY.withColor(TextFormatting.DARK_RED).withItalic(true)));
                    cadaver.setOwnerUUID(player.getUUID());
                }
                itemstack.shrink(1);
            }
            return ActionResultType.SUCCESS;
        }
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (worldIn.isClientSide || !(worldIn instanceof ServerWorld)) {
            return new ActionResult<>(ActionResultType.PASS, itemstack);
        } else {
            BlockRayTraceResult raytraceresult = Item.getPlayerPOVHitResult(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
            if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos blockpos = raytraceresult.getBlockPos();
                if (!(worldIn.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
                    return new ActionResult<>(ActionResultType.PASS, itemstack);
                } else if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos, raytraceresult.getDirection(), itemstack)) {
                    Entity entity = ModEntities.SUMMONED_CADAVER.spawn((ServerWorld) worldIn, itemstack, playerIn, blockpos, SpawnReason.SPAWN_EGG, false, false);
                    if (entity instanceof SummonedCadaver) {
                        SummonedCadaver cadaver = (SummonedCadaver)entity;
                        cadaver.setTame(true);
                        cadaver.setCustomName(playerIn.getDisplayName().copy().append(new TranslationTextComponent("entity.rigoranthusemortisreborn.familiar_cadaver")).setStyle(Style.EMPTY.withColor(TextFormatting.DARK_RED).withItalic(true)));
                        cadaver.setOwnerUUID(playerIn.getUUID());
                        itemstack.shrink(1);

                        playerIn.awardStat(Stats.ITEM_USED.get(this));
                        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
                    } else {
                        return new ActionResult<>(ActionResultType.PASS, itemstack);
                    }
                } else {
                    return new ActionResult<>(ActionResultType.FAIL, itemstack);
                }
            } else {
                return new ActionResult<>(ActionResultType.PASS, itemstack);
            }
        }
    }
}