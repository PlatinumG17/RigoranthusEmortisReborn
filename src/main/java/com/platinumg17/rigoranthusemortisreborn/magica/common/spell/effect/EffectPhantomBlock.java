package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.REFakePlayer;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.BlockUtil;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.SpellUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.DecayingBlockTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.*;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class EffectPhantomBlock extends AbstractEffect {
    public static EffectPhantomBlock INSTANCE = new EffectPhantomBlock();

    private EffectPhantomBlock() {
        super(GlyphLib.EffectPhantomBlockID, "Summon Phantasmal Block");
    }

    @Override
    public void onResolveBlock(BlockRayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {
        REFakePlayer fakePlayer = REFakePlayer.getPlayer((ServerWorld) world);
        for(BlockPos pos : SpellUtil.calcAOEBlocks(shooter, rayTraceResult.getBlockPos(), rayTraceResult, spellStats)) {
            pos =  rayTraceResult.isInside() ? pos : pos.relative(( rayTraceResult).getDirection());
            if(!BlockUtil.destroyRespectsClaim(getPlayer(shooter, (ServerWorld) world), world, pos))
                continue;
            BlockState state = world.getBlockState(pos);
            if (state.getMaterial().isReplaceable() && world.isUnobstructed(BlockRegistry.DECAYING_BLOCK.defaultBlockState(), pos, ISelectionContext.of(fakePlayer))) {
                world.setBlockAndUpdate(pos, BlockRegistry.DECAYING_BLOCK.defaultBlockState());
                if(world.getBlockEntity(pos) instanceof DecayingBlockTile) {
                    DecayingBlockTile tile = (DecayingBlockTile) world.getBlockEntity(pos);
                    tile.color = spellContext.colors.toParticleColor();
                    tile.lengthModifier = spellStats.getDurationMultiplier();
                    tile.isPermanent = spellStats.hasBuff(AugmentAmplify.INSTANCE);
                    world.sendBlockUpdated(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
                }
            }
        }
    }

    @Override
    public int getDominionCost() {
        return 5;
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return Items.GLASS;
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAOE.INSTANCE, AugmentPierce.INSTANCE, AugmentAmplify.INSTANCE, AugmentExtendTime.INSTANCE, AugmentDurationDown.INSTANCE);
    }

    @Override
    public String getBookDescription() {
        return "Creates a temporary block that will disappear after a short time. Amplify will cause the block to be permanent. ";
    }

    @Nonnull
    @Override
    public Set<SpellSchool> getSchools() {
        return setOf(SpellSchools.CONJURATION);
    }
}