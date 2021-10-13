package com.platinumg17.rigoranthusemortisreborn.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.core.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
//
//public class WorldFeatures {
//
//    public static final ConfiguredFeature<?, ?> FLOWERS_VERDUROUS = register("flowers_verdurous", Feature.RANDOM_PATCH
//            .configured((new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider())
//                    //.add(BlockInit.AZULOREAL_ORCHID.get().defaultBlockState(), 200)
//                    //.add(BlockInit.LISIANTHUS.get().defaultBlockState(), 100)
//                    .add(Blocks.BLUE_ORCHID.defaultBlockState(), 70)
//                    .add(Blocks.OXEYE_DAISY.defaultBlockState(), 50)
//                    .add(Blocks.WHITE_TULIP.defaultBlockState(), 50)
//                    .add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 50), SimpleBlockPlacer.INSTANCE))
//                    .tries(64).build())
//            .decorated(Features.Placements.ADD_32)
//            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//            .count(2));
//    public static final ConfiguredFeature<?, ?> TREES_VERDUROUS = register("trees_verdurous", Feature.RANDOM_SELECTOR
//            .configured(new MultipleRandomFeatureConfig(ImmutableList.of(Features.FANCY_OAK_BEES_0002.weighted(0.1F)), Features.OAK_BEES_0002))
//            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//            .decorated(Placement.COUNT_EXTRA
//                    .configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));
//
//    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature) {
//        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, key), feature);
//    }
//
//    public static void load() {
//    }
//}