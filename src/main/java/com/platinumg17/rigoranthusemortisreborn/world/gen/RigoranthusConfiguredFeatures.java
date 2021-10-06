package com.platinumg17.rigoranthusemortisreborn.world.gen;

import com.platinumg17.rigoranthusemortisreborn.core.init.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.BuildingBlockInit;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class RigoranthusConfiguredFeatures {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> AZULOREAL =
            register("azuloreal", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BuildingBlockInit.AZULOREAL_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BuildingBlockInit.AZULOREAL_LEAVES.get().defaultBlockState()),
                            new BlobFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.of(0, 2), 3),
                            new StraightTrunkPlacer(4, 2, 1),
                            new TwoLayerFeature(2, 1, 3))).ignoreVines().build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> JESSIC =
            register("jessic", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BuildingBlockInit.JESSIC_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BuildingBlockInit.JESSIC_LEAVES.get().defaultBlockState()),
                            new BlobFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.of(0, 2), 1),
                            new StraightTrunkPlacer(4, 2, 1),
                            new TwoLayerFeature(1, 0, 1))).ignoreVines().build()));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}