package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.BlockUtil;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.SpellUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.*;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

import static com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.BlockUtil.destroyBlockSafely;

public class EffectBreak extends AbstractEffect {
    public static EffectBreak INSTANCE = new EffectBreak();

    private EffectBreak() {
        super(GlyphLib.EffectBreakID, "Break");
    }

    @Override
    public int getDominionCost() {
        return 10;
    }

    public ItemStack getStack(LivingEntity shooter){
        if(isRealPlayer(shooter)){
            ItemStack mainHand = getPlayer(shooter, (ServerWorld)shooter.level).getMainHandItem();
            return (mainHand.isEmpty() ? getPlayer(shooter, (ServerWorld)shooter.level).getOffhandItem() : mainHand).copy();
        }
        return new ItemStack(Items.DIAMOND_PICKAXE);
    }

    @Override
    public void onResolveBlock(BlockRayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {
        BlockPos pos = rayTraceResult.getBlockPos();
        BlockState state;

        int aoeBuff = spellStats.getBuffCount(AugmentAOE.INSTANCE);
        int pierceBuff = spellStats.getBuffCount(AugmentPierce.INSTANCE);
        List<BlockPos> posList = SpellUtil.calcAOEBlocks(shooter, pos, rayTraceResult, aoeBuff, pierceBuff);
        ItemStack stack = spellStats.hasBuff(AugmentSensitive.INSTANCE) ? new ItemStack(Items.SHEARS) : getStack(shooter);

        for(BlockPos pos1 : posList) {
            state = world.getBlockState(pos1);
            if(!canBlockBeHarvested(spellStats, world, pos1) || !BlockUtil.destroyRespectsClaim(getPlayer(shooter, (ServerWorld) world), world, pos1)){
                continue;
            }
            if(spellStats.hasBuff(AugmentExtract.INSTANCE)) {
                stack.enchant(Enchantments.SILK_TOUCH, 1);
                state.getBlock().playerDestroy(world, getPlayer(shooter, (ServerWorld) world), pos1, world.getBlockState(pos1), world.getBlockEntity(pos1), stack);
                destroyBlockSafely(world, pos1, false, shooter);
            }else if(spellStats.hasBuff(AugmentFortune.INSTANCE)) {
                int bonus = spellStats.getBuffCount(AugmentFortune.INSTANCE);
                stack.enchant(Enchantments.BLOCK_FORTUNE, bonus);
                state.getBlock().popExperience((ServerWorld) world, pos1, state.getExpDrop(world, pos1, bonus, 0));
                state.getBlock().playerDestroy(world, getPlayer(shooter, (ServerWorld) world), pos1, world.getBlockState(pos1), world.getBlockEntity(pos1), stack);
                destroyBlockSafely(world, pos1, false, shooter);
            } else {
                state.getBlock().playerDestroy(world, getPlayer(shooter, (ServerWorld) world), pos1, world.getBlockState(pos1), world.getBlockEntity(pos1), stack);
                destroyBlockSafely(world, pos1, false, shooter);
                state.getBlock().popExperience((ServerWorld) world, pos1, state.getExpDrop(world, pos1, 0, 0));
            }
        }
    }

    @Override
    public boolean defaultedStarterGlyph() {
        return true;
    }

    @Override
    public boolean wouldSucceed(RayTraceResult rayTraceResult, World world, LivingEntity shooter, List<AbstractAugment> augments) {
        return rayTraceResult instanceof BlockRayTraceResult && world.getBlockState(((BlockRayTraceResult) rayTraceResult).getBlockPos()).getMaterial() != Material.AIR && canBlockBeHarvested(augments, world, ((BlockRayTraceResult) rayTraceResult).getBlockPos());
    }

    @Override
    public boolean dampenIsAllowed() {
        return true;
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return Items.IRON_PICKAXE;
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(
                AugmentAmplify.INSTANCE, AugmentDampen.INSTANCE,
                AugmentPierce.INSTANCE,
                AugmentAOE.INSTANCE,
                AugmentExtract.INSTANCE,
                AugmentFortune.INSTANCE,
                AugmentSensitive.INSTANCE
        );
    }

    @Override
    public String getBookDescription() {
        return "A spell you start with. Breaks blocks of an average hardness. Can be amplified to increase the harvest level. Sensitive will simulate breaking blocks with Shears instead of a pickaxe.";
    }

    @Nonnull
    @Override
    public Set<SpellSchool> getSchools() {
        return setOf(SpellSchools.ELEMENTAL_EARTH);
    }
}