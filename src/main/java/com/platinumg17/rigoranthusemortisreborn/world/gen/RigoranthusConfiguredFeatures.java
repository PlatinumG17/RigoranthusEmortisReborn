package com.platinumg17.rigoranthusemortisreborn.world.gen;

import com.google.common.collect.ImmutableList;
import com.platinumg17.rigoranthusemortisreborn.core.init.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.BuildingBlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;

import java.util.OptionalInt;

public class RigoranthusConfiguredFeatures {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> AZULOREAL =
            register("azuloreal", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BuildingBlockInit.AZULOREAL_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BuildingBlockInit.AZULOREAL_LEAVES.get().defaultBlockState()),
                            new FancyFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(4, 2), 4),
                            new FancyTrunkPlacer(3, 11, 0),
                            new TwoLayerFeature(0, 0, 0,
                            OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> LOOMING_AZULOREAL =
            register("looming_azuloreal", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BuildingBlockInit.AZULOREAL_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BuildingBlockInit.AZULOREAL_LEAVES.get().defaultBlockState()),
                            new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)),
                            new ForkyTrunkPlacer(5, 2, 2),
                            new TwoLayerFeature(1, 0, 2,
                            OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_AZULOREAL =
            register("mega_azuloreal", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BuildingBlockInit.AZULOREAL_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BuildingBlockInit.AZULOREAL_LEAVES.get().defaultBlockState()),
                            new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(13, 4)),
                            new GiantTrunkPlacer(13, 2, 14),
                            new TwoLayerFeature(1, 1, 2))).decorators(ImmutableList.of(
                            new AlterGroundTreeDecorator(new SimpleBlockStateProvider(States.FRAGMENTED_COBBLESTONE)))).build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> JESSIC =
            register("jessic", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BuildingBlockInit.JESSIC_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BuildingBlockInit.JESSIC_LEAVES.get().defaultBlockState()),
                            new FancyFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(4, 2), 4),
                            new FancyTrunkPlacer(3, 11, 0),
                            new TwoLayerFeature(0, 0, 0,
                            OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> LOOMING_JESSIC =
            register("looming_jessic", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BuildingBlockInit.JESSIC_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BuildingBlockInit.JESSIC_LEAVES.get().defaultBlockState()),
                            new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)),
                            new ForkyTrunkPlacer(5, 2, 2),
                            new TwoLayerFeature(1, 0, 2,
                            OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_JESSIC =
            register("mega_jessic", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BuildingBlockInit.JESSIC_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BuildingBlockInit.JESSIC_LEAVES.get().defaultBlockState()),
                            new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(13, 4)),
                            new GiantTrunkPlacer(13, 2, 14),
                            new TwoLayerFeature(1, 1, 2))).decorators(ImmutableList.of(
                            new AlterGroundTreeDecorator(new SimpleBlockStateProvider(States.FRAGMENTED_COBBLESTONE)))).build()));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

    public static final class States {
        protected static final BlockState FRAGMENTED_COBBLESTONE = BlockInit.FRAGMENTED_COBBLESTONE.get().defaultBlockState();
    }
}