package com.platinumg17.rigoranthusemortisreborn.world.trees;

import com.platinumg17.rigoranthusemortisreborn.world.gen.feature.RigoranthusConfiguredFeatures;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class AzulorealTree extends BigTree {
    @Override
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean p_225546_2_) {
        return RigoranthusConfiguredFeatures.AZULOREAL;
    }
    @Override
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredMegaFeature(Random random) {
        return random.nextBoolean() ? RigoranthusConfiguredFeatures.MEGA_AZULOREAL : RigoranthusConfiguredFeatures.LOOMING_AZULOREAL;
    }
}