package com.platinumg17.rigoranthusemortisreborn.magica.common.world;

import com.google.common.collect.ImmutableList;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.LightTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.world.feature.SingleBlockFeature;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.world.biome.EmortisBiomes;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.template.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Objects;
import java.util.Random;

import static net.minecraft.world.gen.feature.Features.*;
import static net.minecraftforge.common.BiomeDictionary.Type.*;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID)
public class WorldEvent {
//    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> AZULOREAL_TREE = Feature.TREE.configured((
//                    new BaseTreeFeatureConfig.Builder(
//                    new SupplierBlockStateProvider(LibBlockNames.AZULOREAL_LOG),
//                    new SupplierBlockStateProvider(LibBlockNames.AZULOREAL_LEAVES),
//                            new FancyFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(4, 2), 4),
//                            new FancyTrunkPlacer(3, 11, 0),
//                            new TwoLayerFeature(0, 0, 0))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());
//    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> LOOMING_AZULOREAL_TREE = Feature.TREE.configured((
//                    new BaseTreeFeatureConfig.Builder(
//                    new SupplierBlockStateProvider(LibBlockNames.AZULOREAL_LOG),
//                    new SupplierBlockStateProvider(LibBlockNames.AZULOREAL_LEAVES),
//                            new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)), //was fixed(2)
//                            new ForkyTrunkPlacer(5, 2, 2), //new ForkyTrunkPlacer(5, 2, 2),
//                            new TwoLayerFeature(1, 0, 2))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());
//    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_AZULOREAL_TREE = Feature.TREE.configured((
//                    new BaseTreeFeatureConfig.Builder(
//                    new SupplierBlockStateProvider(LibBlockNames.AZULOREAL_LOG),
//                    new SupplierBlockStateProvider(LibBlockNames.AZULOREAL_LEAVES),
//                            new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(13, 4)),
//                            new GiantTrunkPlacer(12, 2, 14),
//                            new TwoLayerFeature(1, 1, 2))).decorators(ImmutableList.of(
//                    new AlterGroundTreeDecorator(new SupplierBlockStateProvider(LibBlockNames.FRAGMENTED_COBBLESTONE)))).build());
//    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> JESSIC_TREE = Feature.TREE.configured((
//                    new BaseTreeFeatureConfig.Builder(
//                            new SupplierBlockStateProvider(LibBlockNames.JESSIC_LOG),
//                            new SupplierBlockStateProvider(LibBlockNames.JESSIC_LEAVES),
//                            new FancyFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(4, 2), 4),
//                            new FancyTrunkPlacer(3, 11, 0),
//                            new TwoLayerFeature(0, 0, 0))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());
//    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> LOOMING_JESSIC_TREE = Feature.TREE.configured((
//                    new BaseTreeFeatureConfig.Builder(
//                            new SupplierBlockStateProvider(LibBlockNames.JESSIC_LOG),
//                            new SupplierBlockStateProvider(LibBlockNames.JESSIC_LEAVES),
//                            new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)), //was fixed(2)
//                            new ForkyTrunkPlacer(5, 2, 2),  //new ForkyTrunkPlacer(5, 2, 2),
//                            new TwoLayerFeature(1, 0, 2))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());
//    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_JESSIC_TREE = Feature.TREE.configured((
//                    new BaseTreeFeatureConfig.Builder(
//                            new SupplierBlockStateProvider(LibBlockNames.JESSIC_LOG),
//                            new SupplierBlockStateProvider(LibBlockNames.JESSIC_LEAVES),
//                            new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(13, 4)),
//                            new GiantTrunkPlacer(12, 2, 14),
//                            new TwoLayerFeature(1, 1, 2))).decorators(ImmutableList.of(
//                    new AlterGroundTreeDecorator(new SupplierBlockStateProvider(LibBlockNames.FRAGMENTED_COBBLESTONE)))).build());
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
//    public static final ResourceLocation EXTRA_RECONDITE_ORE = new ResourceLocation(EmortisConstants.MOD_ID, "recondite_ore_extra");
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
//        BlockClusterFeatureConfig BERRY_BUSH_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Registration.SPECTABILIS_BUSH.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).noProjection().build();
//        BlockClusterFeatureConfig ORCHID_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BlockRegistry.AZULOREAL_ORCHID.defaultBlockState(), 80), SimpleBlockPlacer.INSTANCE)).tries(30).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).noProjection().build();
//        BlockClusterFeatureConfig IRIDESCENT_SPROUT_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BlockRegistry.IRIDESCENT_SPROUTS.defaultBlockState(), 50), SimpleBlockPlacer.INSTANCE)).tries(30).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).noProjection().build();
//        BlockClusterFeatureConfig LISIANTHUS_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BuildingBlockInit.LISIANTHUS.get().defaultBlockState(), 10), DoublePlantBlockPlacer.INSTANCE)).tries(10).build();

//        float azulorealChance = Config.azulorealSpawnWeight.get().floatValue();
//        float jessicChance = Config.jessicSpawnWeight.get().floatValue();
//        float loomingAzulorealChance = Config.loomingAzulorealSpawnWeight.get().floatValue();
//        float loomingJessicChance = Config.loomingJessicSpawnWeight.get().floatValue();
//        float megaAzulorealChance = Config.megaAzulorealSpawnWeight.get().floatValue();
//        float megaJessicChance = Config.megaJessicSpawnWeight.get().floatValue();
//
//        ConfiguredFeature<?, ?> JESSIC_COMMON = JESSIC_TREE
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.01f, 1)));
//        ConfiguredFeature<?, ?> AZULOREAL_COMMON = AZULOREAL_TREE
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.01f, 1)));
//        ConfiguredFeature<?, ?> LOOMING_JESSIC_COMMON = LOOMING_JESSIC_TREE
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(8, 0.01f, 1)));
//        ConfiguredFeature<?, ?> LOOMING_AZULOREAL_COMMON = LOOMING_AZULOREAL_TREE
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(8, 0.01f, 1)));
//        ConfiguredFeature<?, ?> MEGA_JESSIC_COMMON = MEGA_JESSIC_TREE
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(6, 0.01f, 1)));
//        ConfiguredFeature<?, ?> MEGA_AZULOREAL_COMMON = MEGA_AZULOREAL_TREE
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(6, 0.01f, 1)));
//
//        ConfiguredFeature<?, ?> JESSIC = JESSIC_TREE
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(
//                        new AtSurfaceWithExtraConfig(0, jessicChance, 1)));
//        ConfiguredFeature<?, ?> LOOMING_JESSIC = LOOMING_JESSIC_TREE
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(
//                        new AtSurfaceWithExtraConfig(0, loomingJessicChance, 1)));
//        ConfiguredFeature<?, ?> MEGA_JESSIC = MEGA_JESSIC_TREE
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(
//                        new AtSurfaceWithExtraConfig(0, megaJessicChance, 1)));
//        ConfiguredFeature<?, ?> AZULOREAL = AZULOREAL_TREE
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(
//                        new AtSurfaceWithExtraConfig(0, azulorealChance, 1)));
//        ConfiguredFeature<?, ?> LOOMING_AZULOREAL = LOOMING_AZULOREAL_TREE
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(
//                        new AtSurfaceWithExtraConfig(0, loomingAzulorealChance, 1)));
//        ConfiguredFeature<?, ?> MEGA_AZULOREAL = MEGA_AZULOREAL_TREE
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(
//                        new AtSurfaceWithExtraConfig(0, megaAzulorealChance, 1)));

        ConfiguredFeature<?, ?> RANDOM_LIGHTS = LIGHTS.configured(new BlockStateFeatureConfig(BlockRegistry.LIGHT_BLOCK.defaultBlockState()))
                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.5f, 1)));

//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Registration.RECONDITE_ORE.get().getRegistryName(), Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
//                Registration.RECONDITE_ORE.get().defaultBlockState(), 5)).range(60).squared().count(5));
//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, EXTRA_RECONDITE_ORE, Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
//                Registration.RECONDITE_ORE.get().defaultBlockState(), 9)).decorated(
//                        Placement.RANGE.configured(new TopSolidRangeConfig(25, 25, 50))).squared().count(10));
//
//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Registration.SPECTABILIS_BUSH.get().getRegistryName(),
//                Feature.RANDOM_PATCH.configured(BERRY_BUSH_PATCH_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
//
//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, BuildingBlockInit.LISIANTHUS.get().getRegistryName(),
//                Feature.RANDOM_PATCH.configured(LISIANTHUS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, BlockRegistry.IRIDESCENT_SPROUTS.getRegistryName(),
//                Feature.RANDOM_PATCH.configured(IRIDESCENT_SPROUT_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(8));
//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, BlockRegistry.AZULOREAL_ORCHID.getRegistryName(),
//                Feature.RANDOM_PATCH.configured(ORCHID_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(8));

//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, DecorativeOrStorageBlocks.AZULOREAL_SAPLING.get().getRegistryName(), AZULOREAL);
//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, DecorativeOrStorageBlocks.JESSIC_SAPLING.get().getRegistryName(), JESSIC);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, FeatureLib.RANDOM_LIGHTS_LOC, RANDOM_LIGHTS);
//
//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, VERDUROUS_AZULOREAL_TREES, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(
//                AZULOREAL_COMMON.weighted(0.15f),
//                MEGA_AZULOREAL_COMMON.weighted(0.12f),
//                LOOMING_AZULOREAL_COMMON.weighted(0.12f)),
//                AZULOREAL_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0, 0))))));
//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, VERDUROUS_JESSIC_TREES, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(
//                JESSIC_COMMON.weighted(0.15f),
//                LOOMING_JESSIC_COMMON.weighted(0.12f),
//                MEGA_JESSIC_COMMON.weighted(0.12f)),
//                JESSIC_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0, 0))))));
//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, VANILLA_VERDUROUS, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(
//                FANCY_OAK.weighted(0.5f),
//                FANCY_OAK_BEES_0002.weighted(0.2f),
//                FANCY_OAK_BEES_005.weighted(0.2f),
//                DARK_OAK.weighted(0.1f)),
//                AZULOREAL_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0, 0))))));
//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, TALL_FLOWERS_VERDUROUS, Feature.RANDOM_PATCH
//                .configured((new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider())
//                        .add(Blocks.TALL_GRASS.defaultBlockState(), 10)
//                        .add(BuildingBlockInit.LISIANTHUS.get().defaultBlockState(), 10), DoublePlantBlockPlacer.INSTANCE))
//                        .tries(10).build())
//                .decorated(Features.Placements.ADD_32)
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .count(2));
//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, FLOWERS_VERDUROUS, Feature.RANDOM_PATCH
//                .configured((new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider())
//                        .add(BlockRegistry.AZULOREAL_ORCHID.defaultBlockState(), 80)
//                        .add(BlockRegistry.IRIDESCENT_SPROUTS.defaultBlockState(), 50)
//                        .add(Registration.SPECTABILIS_BUSH.get().defaultBlockState(), 40)
//                        .add(Blocks.BLUE_ORCHID.defaultBlockState(), 50)
//                        .add(Blocks.PINK_TULIP.defaultBlockState(), 30)
//                        .add(Blocks.WHITE_TULIP.defaultBlockState(), 30)
//                        .add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 25), SimpleBlockPlacer.INSTANCE))
//                        .tries(30).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).noProjection().build())
//                .decorated(Features.Placements.ADD_32)
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .count(8));
//        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, VERDUROUS_PATCH, Feature.DISK
//                .configured(new SphereReplaceConfig(Blocks.GRASS_PATH.defaultBlockState(),
//                        FeatureSpread.of(7, 1), 2, Lists.newArrayList(
//                        Blocks.STONE_BRICKS.defaultBlockState(), Blocks.MOSSY_STONE_BRICKS.defaultBlockState())))
//                .decorated(Features.Placements.HEIGHTMAP_SQUARE)
//                .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.8F, 1))));
    }

//    @SubscribeEvent
//    public static void biomeLoad(BiomeLoadingEvent e) {
//        if (e.getCategory() == Biome.Category.NETHER || e.getCategory() == Biome.Category.THEEND)
//            return;
////        addMobSpawns(e);
//
//        if (Config.SPAWN_ORE.get()) {
//            e.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
//                    Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(Registration.RECONDITE_ORE.get().getRegistryName()))).build();
//        }
//        if ((e.getCategory().equals(Biome.Category.FOREST) || e.getCategory().equals(Biome.Category.TAIGA) || e.getName().equals(new ResourceLocation(EmortisConstants.MOD_ID, "verdurous_woodlands")) || e.getName().equals(new ResourceLocation(EmortisConstants.MOD_ID, "verdurous_fields")))  && Config.SPAWN_BERRIES.get()) {
//            e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                    Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(Registration.SPECTABILIS_BUSH.get().getRegistryName()))).build();
//        }
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(DecorativeOrStorageBlocks.JESSIC_SAPLING.get().getRegistryName()))).build();
//
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(DecorativeOrStorageBlocks.AZULOREAL_SAPLING.get().getRegistryName()))).build();
//
//        if(e.getName().equals(EmortisBiomes.VERDUROUS_WOODLANDS.get().getRegistryName())){
//            addVerdurousWoodlandsFeatures(e);
//        }
//        if(e.getName().equals(EmortisBiomes.VERDUROUS_FIELDS.get().getRegistryName())){
//            addVerdurousFieldsFeatures(e);
//        }
//    }

//    public static void addVerdurousFieldsFeatures(BiomeLoadingEvent e){
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.JESSIC.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.LOOMING_AZULOREAL.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.LOOMING_JESSIC.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.MEGA_AZULOREAL.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.MEGA_JESSIC.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.AZULOREAL.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RANDOM_LIGHTS_LOC))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.FLOWERS_VERDUROUS.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.TALL_FLOWERS_VERDUROUS.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.TREES_VERDUROUS.feature().getRegistryName()))).build();
//        if (Config.SPAWN_ORE.get()) {
//            e.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
//                    Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(EXTRA_RECONDITE_ORE))).build();
//        }
//    }
//
//    public static void addVerdurousWoodlandsFeatures(BiomeLoadingEvent e){
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.JESSIC.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.LOOMING_AZULOREAL.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.LOOMING_JESSIC.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.MEGA_AZULOREAL.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.MEGA_JESSIC.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.AZULOREAL.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RANDOM_LIGHTS_LOC))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.FLOWERS_VERDUROUS.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.TALL_FLOWERS_VERDUROUS.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.TREES_VERDUROUS.feature().getRegistryName()))).build();
//        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RigoranthusConfiguredFeatures.VERDUROUS_PATCH.feature().getRegistryName()))).build();
//
//        if (Config.SPAWN_ORE.get()) {
//            e.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
//                    Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(EXTRA_RECONDITE_ORE))).build();
//        }
//    }
}