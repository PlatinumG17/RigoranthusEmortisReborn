package com.platinumg17.rigoranthusemortisreborn.magica.common.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.LightTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import com.platinumg17.rigoranthusemortisreborn.magica.common.world.feature.SingleBlockFeature;
import com.platinumg17.rigoranthusemortisreborn.magica.common.world.tree.SupplierBlockStateProvider;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.*;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static com.platinumg17.rigoranthusemortisreborn.magica.common.world.FeatureLib.*;
import static net.minecraft.world.gen.feature.Features.*;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID)
public class WorldEvent {
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> AZULOREAL_TREE = Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                    new SupplierBlockStateProvider(LibBlockNames.AZULOREAL_LOG),
                    new SupplierBlockStateProvider(LibBlockNames.AZULOREAL_LEAVES),
                            new FancyFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(4, 2), 4),
                            new FancyTrunkPlacer(3, 11, 0),
                            new TwoLayerFeature(0, 0, 0))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> LOOMING_AZULOREAL_TREE = Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                    new SupplierBlockStateProvider(LibBlockNames.AZULOREAL_LOG),
                    new SupplierBlockStateProvider(LibBlockNames.AZULOREAL_LEAVES),
                            new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)),
                            new ForkyTrunkPlacer(5, 2, 2),
                            new TwoLayerFeature(1, 0, 2))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_AZULOREAL_TREE = Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                    new SupplierBlockStateProvider(LibBlockNames.AZULOREAL_LOG),
                    new SupplierBlockStateProvider(LibBlockNames.AZULOREAL_LEAVES),
                            new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(13, 4)),
                            new GiantTrunkPlacer(12, 2, 14),
                            new TwoLayerFeature(1, 1, 2))).decorators(ImmutableList.of(
                    new AlterGroundTreeDecorator(new SupplierBlockStateProvider(LibBlockNames.FRAGMENTED_COBBLESTONE)))).build());
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> JESSIC_TREE = Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SupplierBlockStateProvider(LibBlockNames.JESSIC_LOG),
                            new SupplierBlockStateProvider(LibBlockNames.JESSIC_LEAVES),
                            new FancyFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(4, 2), 4),
                            new FancyTrunkPlacer(3, 11, 0),
                            new TwoLayerFeature(0, 0, 0))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> LOOMING_JESSIC_TREE = Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SupplierBlockStateProvider(LibBlockNames.JESSIC_LOG),
                            new SupplierBlockStateProvider(LibBlockNames.JESSIC_LEAVES),
                            new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)),
                            new ForkyTrunkPlacer(5, 2, 2),
                            new TwoLayerFeature(1, 0, 2))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_JESSIC_TREE = Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SupplierBlockStateProvider(LibBlockNames.JESSIC_LOG),
                            new SupplierBlockStateProvider(LibBlockNames.JESSIC_LEAVES),
                            new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(13, 4)),
                            new GiantTrunkPlacer(12, 2, 14),
                            new TwoLayerFeature(1, 1, 2))).decorators(ImmutableList.of(
                    new AlterGroundTreeDecorator(new SupplierBlockStateProvider(LibBlockNames.FRAGMENTED_COBBLESTONE)))).build());
    public static final StructureProcessorList VERDUROUS_PLAINS =
            new StructureProcessorList(ImmutableList.of(new RuleStructureProcessor(
                    ImmutableList.of(new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.defaultBlockState()),
                            new RuleEntry(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                            new RuleEntry(new BlockMatchRuleTest(Blocks.TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                            new RuleEntry(new BlockMatchRuleTest(Blocks.WALL_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                            new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.defaultBlockState()),
                            new RuleEntry(new RandomBlockMatchRuleTest(Blocks.MOSSY_COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.defaultBlockState()),
                            new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHITE_TERRACOTTA, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.defaultBlockState()),
                            new RuleEntry(new RandomBlockMatchRuleTest(Blocks.OAK_LOG, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.defaultBlockState()),
                            new RuleEntry(new RandomBlockMatchRuleTest(Blocks.OAK_PLANKS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.defaultBlockState()),
                            new RuleEntry(new RandomBlockMatchRuleTest(Blocks.OAK_STAIRS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.defaultBlockState()),
                            new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STRIPPED_OAK_LOG, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.defaultBlockState()),
                            new RuleEntry(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.defaultBlockState().setValue(PaneBlock.NORTH, Boolean.TRUE).setValue(PaneBlock.SOUTH, Boolean.TRUE)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.defaultBlockState().setValue(PaneBlock.NORTH, Boolean.TRUE).setValue(PaneBlock.SOUTH, Boolean.TRUE)),
                            new RuleEntry(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.defaultBlockState().setValue(PaneBlock.EAST, Boolean.TRUE).setValue(PaneBlock.WEST, Boolean.TRUE)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.defaultBlockState().setValue(PaneBlock.EAST, Boolean.TRUE).setValue(PaneBlock.WEST, Boolean.TRUE)),
                            new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.defaultBlockState()),
                            new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.defaultBlockState()),
                            new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.defaultBlockState())))));
    public static final ResourceLocation EXTRA_RECONDITE_ORE = new ResourceLocation(EmortisConstants.MOD_ID, "recondite_ore_extra");

    public static final Feature<BlockStateFeatureConfig> LIGHTS = new SingleBlockFeature(BlockStateFeatureConfig.CODEC) {
        public void onStatePlace(ISeedReader seed, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
            if(seed instanceof WorldGenRegion){
                WorldGenRegion world = (WorldGenRegion) seed;
                Random random = world.getRandom();
                if(world.getBlockEntity(pos) instanceof LightTile){
                    LightTile tile = (LightTile) world.getBlockEntity(pos);
                    tile.red = Math.max(10, random.nextInt(255));
                    tile.green = Math.max(10, random.nextInt(255));
                    tile.blue = Math.max(10, random.nextInt(255));
                }
            }
        }
    };
    public static void registerFeatures() {
//        int maxVeinClusterSize = Config.maxVeinSize.get();
//        int minOreY = Config.minOreHeight.get();
//        int maxOreY = Config.maxOreHeight.get();
//        int chunkVeinDensity = Config.veinsPerChunk.get();

        float azulorealChance = Config.azulorealSpawnWeight.get().floatValue();
        float jessicChance = Config.jessicSpawnWeight.get().floatValue();
        float loomingAzulorealChance = Config.loomingAzulorealSpawnWeight.get().floatValue();
        float loomingJessicChance = Config.loomingJessicSpawnWeight.get().floatValue();
        float megaAzulorealChance = Config.megaAzulorealSpawnWeight.get().floatValue();
        float megaJessicChance = Config.megaJessicSpawnWeight.get().floatValue();

        BlockClusterFeatureConfig DOMINION_BERRY_BUSH_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BlockRegistry.DOMINION_BERRY_BUSH.defaultBlockState(), 80), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).noProjection().build();
        BlockClusterFeatureConfig BERRY_BUSH_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BlockRegistry.SPECTABILIS_BUSH.defaultBlockState(), 80), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).noProjection().build();
        BlockClusterFeatureConfig ORCHID_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BlockRegistry.AZULOREAL_ORCHID.defaultBlockState(), 80), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).noProjection().build();
        BlockClusterFeatureConfig IRIDESCENT_SPROUT_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BlockRegistry.IRIDESCENT_SPROUTS.defaultBlockState(), 70), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).noProjection().build();
        BlockClusterFeatureConfig LISIANTHUS_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BlockRegistry.LISIANTHUS.defaultBlockState(), 90), DoublePlantBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).noProjection().build();

        ConfiguredFeature<?, ?> JESSIC = JESSIC_TREE
                .decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, jessicChance, 1)));
        ConfiguredFeature<?, ?> LOOMING_JESSIC = LOOMING_JESSIC_TREE
                .decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, loomingJessicChance, 1)));
        ConfiguredFeature<?, ?> MEGA_JESSIC = MEGA_JESSIC_TREE
                .decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, megaJessicChance, 1)));
        ConfiguredFeature<?, ?> AZULOREAL = AZULOREAL_TREE
                .decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, azulorealChance, 1)));
        ConfiguredFeature<?, ?> LOOMING_AZULOREAL = LOOMING_AZULOREAL_TREE
                .decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, loomingAzulorealChance, 1)));
        ConfiguredFeature<?, ?> MEGA_AZULOREAL = MEGA_AZULOREAL_TREE
                .decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, megaAzulorealChance, 1)));

        ConfiguredFeature<?, ?> JESSIC_COMMON = JESSIC_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE)
                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.01f, 1)));
        ConfiguredFeature<?, ?> AZULOREAL_COMMON = AZULOREAL_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE)
                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.01f, 1)));
        ConfiguredFeature<?, ?> LOOMING_JESSIC_COMMON = LOOMING_JESSIC_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE)
                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(8, 0.01f, 1)));
        ConfiguredFeature<?, ?> LOOMING_AZULOREAL_COMMON = LOOMING_AZULOREAL_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE)
                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(8, 0.01f, 1)));
        ConfiguredFeature<?, ?> MEGA_JESSIC_COMMON = MEGA_JESSIC_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE)
                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(6, 0.01f, 1)));
        ConfiguredFeature<?, ?> MEGA_AZULOREAL_COMMON = MEGA_AZULOREAL_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE)
                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(6, 0.01f, 1)));

        ConfiguredFeature<?, ?> RANDOM_LIGHTS = LIGHTS.configured(new BlockStateFeatureConfig(BlockRegistry.LIGHT_BLOCK.defaultBlockState()))
                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.5f, 1)));

//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, BlockRegistry.RECONDITE_ORE.getRegistryName(),
//                Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
//                        BlockRegistry.RECONDITE_ORE.defaultBlockState(), chunkVeinDensity)).decorated(
//                        Placement.RANGE.configured(new TopSolidRangeConfig(minOreY, maxOreY, maxVeinClusterSize))).squared().count(chunkVeinDensity));
//
//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, EXTRA_RECONDITE_ORE,
//                Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
//                        BlockRegistry.RECONDITE_ORE.defaultBlockState(), chunkVeinDensity + 5)).decorated(
//                        Placement.RANGE.configured(new TopSolidRangeConfig(minOreY, maxOreY, maxVeinClusterSize))).squared().count(chunkVeinDensity + 5));

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, BlockRegistry.SPECTABILIS_BUSH.getRegistryName(), Feature.RANDOM_PATCH.configured(BERRY_BUSH_PATCH_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, BlockRegistry.DOMINION_BERRY_BUSH.getRegistryName(), Feature.RANDOM_PATCH.configured(DOMINION_BERRY_BUSH_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, BlockRegistry.LISIANTHUS.getRegistryName(), Feature.RANDOM_PATCH.configured(LISIANTHUS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, BlockRegistry.IRIDESCENT_SPROUTS.getRegistryName(), Feature.RANDOM_PATCH.configured(IRIDESCENT_SPROUT_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(8));
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, BlockRegistry.AZULOREAL_ORCHID.getRegistryName(), Feature.RANDOM_PATCH.configured(ORCHID_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(8));

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, RANDOM_LIGHTS_LOC, RANDOM_LIGHTS);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, AZULOREAL_TREE_LOC, AZULOREAL);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, JESSIC_TREE_LOC, JESSIC);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, LOOMING_AZULOREAL_TREE_LOC, LOOMING_AZULOREAL);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, LOOMING_JESSIC_TREE_LOC, LOOMING_JESSIC);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, MEGA_AZULOREAL_TREE_LOC, MEGA_AZULOREAL);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, MEGA_JESSIC_TREE_LOC, MEGA_JESSIC);

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, VERDUROUS_AZULOREAL_TREES, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(
                AZULOREAL_COMMON.weighted(0.15f),
                MEGA_AZULOREAL_COMMON.weighted(0.12f),
                LOOMING_AZULOREAL_COMMON.weighted(0.12f)),
                AZULOREAL_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE)
                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0, 0))))));

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, VERDUROUS_JESSIC_TREES, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(
                JESSIC_COMMON.weighted(0.15f),
                LOOMING_JESSIC_COMMON.weighted(0.12f),
                MEGA_JESSIC_COMMON.weighted(0.12f)),
                JESSIC_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE)
                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0, 0))))));

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, VANILLA_VERDUROUS, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(
                FANCY_OAK.weighted(0.5f),
                FANCY_OAK_BEES_0002.weighted(0.2f),
                FANCY_OAK_BEES_005.weighted(0.2f),
                DARK_OAK.weighted(0.1f)),
                AZULOREAL_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE)
                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0, 0))))));
    }
}