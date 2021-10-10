package com.platinumg17.rigoranthusemortisreborn.world.gen;

import com.platinumg17.rigoranthusemortisreborn.core.init.BuildingBlockInit;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;

import java.util.OptionalInt;

public class RigoranthusConfiguredFeatures {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> AZULOREAL =
            register("azuloreal", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BuildingBlockInit.AZULOREAL_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BuildingBlockInit.AZULOREAL_LEAVES.get().defaultBlockState()),
                            new FancyFoliagePlacer(FeatureSpread.of(2, 0), FeatureSpread.of(4, 2), 4),
                            new FancyTrunkPlacer(3, 11, 0),
                            new TwoLayerFeature(0, 0, 0,
                            OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
//                            new BlobFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.of(0, 2), 3),
//                            new StraightTrunkPlacer(4, 2, 1),
//                            new TwoLayerFeature(2, 1, 3))).ignoreVines().build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> JESSIC =
            register("jessic", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BuildingBlockInit.JESSIC_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BuildingBlockInit.JESSIC_LEAVES.get().defaultBlockState()),
                            new FancyFoliagePlacer(FeatureSpread.of(2, 0), FeatureSpread.of(4, 2), 4),
                            new FancyTrunkPlacer(3, 11, 0),
                            new TwoLayerFeature(0, 0, 0,
                            OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
//                            new BlobFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.of(0, 2), 1),
//                            new StraightTrunkPlacer(4, 2, 1),
//                            new TwoLayerFeature(1, 0, 1))).ignoreVines().build()));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}