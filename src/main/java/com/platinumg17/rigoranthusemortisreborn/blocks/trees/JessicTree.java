package com.platinumg17.rigoranthusemortisreborn.blocks.trees;

import com.platinumg17.rigoranthusemortisreborn.world.gen.RigoranthusConfiguredFeatures;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;

import javax.annotation.Nullable;
import java.util.Random;

public class JessicTree extends BigTree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random random, boolean p_225546_2_) {
        return RigoranthusConfiguredFeatures.JESSIC;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredMegaFeature(Random random) {
        return random.nextBoolean() ? RigoranthusConfiguredFeatures.LOOMING_JESSIC : RigoranthusConfiguredFeatures.MEGA_JESSIC;
    }
}