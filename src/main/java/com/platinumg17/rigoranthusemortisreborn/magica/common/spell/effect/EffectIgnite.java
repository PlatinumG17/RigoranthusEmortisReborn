package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.BlockUtil;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.SpellUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.*;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class EffectIgnite  extends AbstractEffect {
    public static EffectIgnite INSTANCE = new EffectIgnite();

    private EffectIgnite() {
        super(GlyphLib.EffectIgniteID, "Ignite");
    }

    @Override
    public void onResolveEntity(EntityRayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {

        int duration = (int) (POTION_TIME.get() + EXTEND_TIME.get() * spellStats.getDurationMultiplier());
        rayTraceResult.getEntity().setSecondsOnFire(duration);
    }

    @Override
    public void onResolveBlock(BlockRayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {
        if(spellStats.hasBuff(AugmentSensitive.INSTANCE))
            return;
        if(world.getBlockState((rayTraceResult).getBlockPos().above()).getMaterial().isReplaceable()) {
            Direction face = (rayTraceResult).getDirection();
            for (BlockPos pos : SpellUtil.calcAOEBlocks(shooter, (rayTraceResult).getBlockPos(), rayTraceResult, spellStats)) {
                BlockPos blockpos1 = pos.relative(face);
                if (AbstractFireBlock.canBePlacedAt(world, blockpos1, face) && BlockUtil.destroyRespectsClaim(getPlayer(shooter, (ServerWorld) world), world, blockpos1)) {
                    BlockState blockstate1 = AbstractFireBlock.getState(world, blockpos1);
                    world.setBlock(blockpos1, blockstate1, 11);
                }
            }
        }
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addExtendTimeConfig(builder, 2);
        addPotionConfig(builder, 3);
    }

    @Override
    public boolean wouldSucceed(RayTraceResult rayTraceResult, World world, LivingEntity shooter, List<AbstractAugment> augments) {
        return livingEntityHitSuccess(rayTraceResult) || rayTraceResult instanceof BlockRayTraceResult && world.getBlockState(((BlockRayTraceResult) rayTraceResult).getBlockPos().above()).getMaterial() == Material.AIR;
    }

    @Override
    public int getDominionCost() {
        return 15;
    }

    @Override
    public Tier getTier() {
        return Tier.ONE;
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return Items.FLINT_AND_STEEL;
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentExtendTime.INSTANCE , AugmentAOE.INSTANCE, AugmentPierce.INSTANCE, AugmentDurationDown.INSTANCE, AugmentSensitive.INSTANCE);
    }

    @Override
    public String getBookDescription() {
        return "Sets blocks and mobs on fire for a short time. Sensitive will stop this spell from igniting blocks.";
    }

    @Nonnull
    @Override
    public Set<SpellSchool> getSchools() {
        return setOf(SpellSchools.ELEMENTAL_FIRE);
    }
}