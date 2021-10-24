package com.platinumg17.rigoranthusemortisreborn.blocks.trees;

import com.platinumg17.rigoranthusemortisreborn.world.gen.feature.RigoranthusConfiguredFeatures;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class JessicTree extends BigTree {
    @Override
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean p_225546_2_) {
        return RigoranthusConfiguredFeatures.JESSIC;
    }
    @Override
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredMegaFeature(Random random) {
        return random.nextBoolean() ? RigoranthusConfiguredFeatures.MEGA_JESSIC : RigoranthusConfiguredFeatures.LOOMING_JESSIC;
    }
}