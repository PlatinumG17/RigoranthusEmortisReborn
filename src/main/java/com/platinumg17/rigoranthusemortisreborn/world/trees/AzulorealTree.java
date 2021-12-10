package com.platinumg17.rigoranthusemortisreborn.world.trees;

import com.platinumg17.rigoranthusemortisreborn.world.WorldEvent;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class AzulorealTree extends BigTree {

    @Override
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean p_225546_2_) {
        return WorldEvent.AZULOREAL_TREE;
    }
    @Override
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredMegaFeature(Random random) {
        return random.nextBoolean() ? WorldEvent.MEGA_AZULOREAL_TREE : WorldEvent.LOOMING_AZULOREAL_TREE;
    }
}