package com.platinumg17.rigoranthusemortisreborn.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.platinumg17.rigoranthusemortisreborn.blocks.DecorativeOrStorageBlocks;
import com.platinumg17.rigoranthusemortisreborn.blocks.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.blocks.BuildingBlockInit;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;

public class RigoranthusConfiguredFeatures {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> AZULOREAL =
            register("azuloreal", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(States.AZULOREAL_LOG),
                            new SimpleBlockStateProvider(States.AZULOREAL_LEAVES),
                            new FancyFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(4, 2), 4),
                            new FancyTrunkPlacer(3, 11, 0),
                            new TwoLayerFeature(0, 0, 0))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> LOOMING_AZULOREAL =
            register("looming_azuloreal", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(States.AZULOREAL_LOG),
                            new SimpleBlockStateProvider(States.AZULOREAL_LEAVES),
                            new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)),
                            new ForkyTrunkPlacer(5, 2, 2),
                            new TwoLayerFeature(1, 0, 2))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_AZULOREAL =
            register("mega_azuloreal", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(States.AZULOREAL_LOG),
                            new SimpleBlockStateProvider(States.AZULOREAL_LEAVES),
                            new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(13, 4)),
                            new GiantTrunkPlacer(12, 2, 14),
                            new TwoLayerFeature(1, 1, 2))).decorators(ImmutableList.of(
                    new AlterGroundTreeDecorator(new SimpleBlockStateProvider(States.FRAGMENTED_COBBLESTONE)))).build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> JESSIC =
            register("jessic", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(States.JESSIC_LOG),
                            new SimpleBlockStateProvider(States.JESSIC_LEAVES),
                            new FancyFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(4, 2), 4),
                            new FancyTrunkPlacer(3, 11, 0),
                            new TwoLayerFeature(0, 0, 0))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> LOOMING_JESSIC =
            register("looming_jessic", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(States.JESSIC_LOG),
                            new SimpleBlockStateProvider(States.JESSIC_LEAVES),
                            new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)),
                            new ForkyTrunkPlacer(5, 2, 2),
                            new TwoLayerFeature(1, 0, 2))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_JESSIC =
            register("mega_jessic", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(States.JESSIC_LOG),
                            new SimpleBlockStateProvider(States.JESSIC_LEAVES),
                            new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(13, 4)),
                            new GiantTrunkPlacer(12, 2, 14),
                            new TwoLayerFeature(1, 1, 2))).decorators(ImmutableList.of(
                    new AlterGroundTreeDecorator(new SimpleBlockStateProvider(States.FRAGMENTED_COBBLESTONE)))).build()));

    public static final ConfiguredFeature<?, ?> FLOWERS_VERDUROUS = register("flowers_verdurous", Feature.RANDOM_PATCH
            .configured((new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider())
                    .add(States.AZULOREAL_ORCHID, 80)
                    .add(States.IRIDESCENT_SPROUTS, 50)
                    .add(States.SPECTABILIS_BUSH, 40)
                    .add(States.BLUE_ORCHID, 50)
                    .add(States.PINK_TULIP, 30)
                    .add(States.WHITE_TULIP, 30)
                    .add(States.LILY_OF_THE_VALLEY, 25), SimpleBlockPlacer.INSTANCE))
                    .tries(30).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).noProjection().build()) // recently added ".whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).noProjection()"
            .decorated(Features.Placements.ADD_32)
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .count(8));

    public static final ConfiguredFeature<?, ?> TALL_FLOWERS_VERDUROUS = register("tall_flowers_verdurous", Feature.RANDOM_PATCH
            .configured((new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider())
                    .add(States.TALL_GRASS, 10)
                    .add(States.LISIANTHUS, 10), DoublePlantBlockPlacer.INSTANCE))
                    .tries(10).build())
            .decorated(Features.Placements.ADD_32)
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .count(2));

    public static final ConfiguredFeature<?, ?> TREES_VERDUROUS = register("trees_verdurous", Feature.RANDOM_SELECTOR
            .configured(new MultipleRandomFeatureConfig(ImmutableList.of(Features.FANCY_OAK_BEES_0002.weighted(0.1F)), Features.OAK_BEES_0002))
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .decorated(Placement.COUNT_EXTRA
                    .configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));

    public static final ConfiguredFeature<?, ?> VERDUROUS_PATCH = register("verdurous_patch", Feature.DISK
            .configured(new SphereReplaceConfig(Blocks.GRASS_PATH.defaultBlockState(),
                    FeatureSpread.of(7, 1), 2, Lists.newArrayList(
            Blocks.STONE_BRICKS.defaultBlockState(), Blocks.MOSSY_STONE_BRICKS.defaultBlockState())))
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.8F, 1))));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

    public static final class States {
        public static final BlockState JESSIC_LOG = DecorativeOrStorageBlocks.JESSIC_LOG.get().defaultBlockState();
        public static final BlockState JESSIC_LEAVES = DecorativeOrStorageBlocks.JESSIC_LEAVES.get().defaultBlockState();
        public static final BlockState AZULOREAL_LOG = DecorativeOrStorageBlocks.AZULOREAL_LOG.get().defaultBlockState();
        public static final BlockState AZULOREAL_LEAVES = DecorativeOrStorageBlocks.AZULOREAL_LEAVES.get().defaultBlockState();
        public static final BlockState IRIDESCENT_SPROUTS = DecorativeOrStorageBlocks.IRIDESCENT_SPROUTS.get().defaultBlockState();
        public static final BlockState AZULOREAL_ORCHID = DecorativeOrStorageBlocks.AZULOREAL_ORCHID.get().defaultBlockState();
        public static final BlockState LISIANTHUS = BuildingBlockInit.LISIANTHUS.get().defaultBlockState();
        public static final BlockState SPECTABILIS_BUSH = Registration.SPECTABILIS_BUSH.get().defaultBlockState();
        public static final BlockState FRAGMENTED_COBBLESTONE = BlockInit.FRAGMENTED_COBBLESTONE.get().defaultBlockState();
        public static final BlockState TALL_GRASS = Blocks.TALL_GRASS.defaultBlockState();
        public static final BlockState BLUE_ORCHID = Blocks.BLUE_ORCHID.defaultBlockState();
        public static final BlockState PINK_TULIP = Blocks.PINK_TULIP.defaultBlockState();
        public static final BlockState LILY_OF_THE_VALLEY = Blocks.LILY_OF_THE_VALLEY.defaultBlockState();
        public static final BlockState WHITE_TULIP = Blocks.WHITE_TULIP.defaultBlockState();
        public static final BlockState GRASS = Blocks.GRASS.defaultBlockState();
    }
}