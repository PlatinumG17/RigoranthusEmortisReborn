package com.platinumg17.rigoranthusemortisreborn.world.gen;

import com.google.common.collect.ImmutableList;
import com.platinumg17.rigoranthusemortisreborn.core.init.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.BuildingBlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
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
                            new AcaciaFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.fixed(0)), //was fixed(2)
                            new ForkyTrunkPlacer(5, 4, 4), //new ForkyTrunkPlacer(5, 2, 2),
                            new TwoLayerFeature(1, 0, 2,
                            OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_AZULOREAL =
            register("mega_azuloreal", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BuildingBlockInit.AZULOREAL_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BuildingBlockInit.AZULOREAL_LEAVES.get().defaultBlockState()),
                            new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(13, 4)),
                            new GiantTrunkPlacer(12, 2, 14),
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
                            new AcaciaFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.fixed(0)), //was fixed(2)
                            new ForkyTrunkPlacer(5, 4, 4),  //new ForkyTrunkPlacer(5, 2, 2),
                            new TwoLayerFeature(1, 0, 2,
                            OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_JESSIC =
            register("mega_jessic", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BuildingBlockInit.JESSIC_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BuildingBlockInit.JESSIC_LEAVES.get().defaultBlockState()),
                            new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(13, 4)),
                            new GiantTrunkPlacer(12, 2, 14),
                            new TwoLayerFeature(1, 1, 2))).decorators(ImmutableList.of(
                            new AlterGroundTreeDecorator(new SimpleBlockStateProvider(States.FRAGMENTED_COBBLESTONE)))).build()));

    public static final ConfiguredFeature<?, ?> FLOWERS_VERDUROUS = register("flowers_verdurous", Feature.RANDOM_PATCH
            .configured((new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider())
                    //.add(BlockInit.AZULOREAL_ORCHID.get().defaultBlockState(), 200)
                    //.add(BlockInit.LISIANTHUS.get().defaultBlockState(), 100)
                    .add(Blocks.BLUE_ORCHID.defaultBlockState(), 70)
                    .add(Blocks.OXEYE_DAISY.defaultBlockState(), 50)
                    .add(Blocks.WHITE_TULIP.defaultBlockState(), 50)
                    .add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 50), SimpleBlockPlacer.INSTANCE))
                    .tries(64).build())
            .decorated(Features.Placements.ADD_32)
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .count(2));
    public static final ConfiguredFeature<?, ?> TREES_VERDUROUS = register("trees_verdurous", Feature.RANDOM_SELECTOR
            .configured(new MultipleRandomFeatureConfig(ImmutableList.of(Features.FANCY_OAK_BEES_0002.weighted(0.1F)), Features.OAK_BEES_0002))
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .decorated(Placement.COUNT_EXTRA
                    .configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));


    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

    public static final class States {
        protected static final BlockState FRAGMENTED_COBBLESTONE = BlockInit.FRAGMENTED_COBBLESTONE.get().defaultBlockState();
    }
}