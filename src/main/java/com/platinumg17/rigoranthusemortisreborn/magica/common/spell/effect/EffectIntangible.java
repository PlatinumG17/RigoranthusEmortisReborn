package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.BlockUtil;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.SpellUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IntangibleAirTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.*;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class EffectIntangible extends AbstractEffect {
    public static EffectIntangible INSTANCE = new EffectIntangible();

    private EffectIntangible() {
        super(GlyphLib.EffectIntangibleID, "Intangible");
    }

    @Override
    public void onResolveBlock(BlockRayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {
        BlockPos pos = rayTraceResult.getBlockPos();
        int duration = (int) (GENERIC_INT.get() + EXTEND_TIME.get() * spellStats.getDurationMultiplier());

        List<BlockPos> posList = SpellUtil.calcAOEBlocks(shooter, pos, rayTraceResult, spellStats);
        for(BlockPos pos1 : posList) {
            if (world.getBlockEntity(pos1) != null || world.getBlockState(pos1).getMaterial() == Material.AIR
                    || world.getBlockState(pos1).getBlock() == Blocks.BEDROCK || !canBlockBeHarvested(spellStats, world, pos) || !BlockUtil.destroyRespectsClaim(getPlayer(shooter, (ServerWorld) world), world, pos1))
                continue;
            BlockState state = world.getBlockState(pos1);
            int id = Block.getId(state);
            world.setBlockAndUpdate(pos1, BlockRegistry.INTANGIBLE_AIR.defaultBlockState());
            IntangibleAirTile tile = ((IntangibleAirTile) world.getBlockEntity(pos1));
            tile.stateID = id;
            tile.maxLength = duration * 20;
        }
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addGenericInt(builder, 3, "Base duration, in seconds", "base");
        addExtendTimeConfig(builder, 1);
    }

    @Override
    public Item getCraftingReagent() {
        return Items.PHANTOM_MEMBRANE;
    }

    @Override
    public Tier getTier() {
        return Tier.THREE;
    }

    @Override
    public int getDominionCost() {
        return 30;
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(
                AugmentAmplify.INSTANCE, AugmentDampen.INSTANCE,
                AugmentExtendTime.INSTANCE,
                AugmentPierce.INSTANCE,
                AugmentAOE.INSTANCE, AugmentDurationDown.INSTANCE
        );
    }

    @Override
    public String getBookDescription() {
        return "Causes blocks to temporarily turn into air. Can be modified with Amplify for blocks of higher hardness, AOE, Duration Down, and Extend Time.";
    }

    @Nonnull
    @Override
    public Set<SpellSchool> getSchools() {
        return setOf(SpellSchools.MANIPULATION);
    }
}