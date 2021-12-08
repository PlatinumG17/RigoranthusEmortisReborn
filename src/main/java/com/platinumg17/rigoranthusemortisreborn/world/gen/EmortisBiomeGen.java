package com.platinumg17.rigoranthusemortisreborn.world.gen;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ModEntities;
import com.platinumg17.rigoranthusemortisreborn.magica.common.world.FeatureLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.world.WorldEvent;
import com.platinumg17.rigoranthusemortisreborn.magica.common.world.feature.BiomeMusic;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Objects;
import java.util.function.Supplier;

import static com.platinumg17.rigoranthusemortisreborn.magica.common.world.FeatureLib.*;
import static com.platinumg17.rigoranthusemortisreborn.magica.common.world.FeatureLib.VANILLA_VERDUROUS;
import static net.minecraftforge.common.BiomeDictionary.Type.*;
import static net.minecraftforge.common.BiomeDictionary.Type.PLAINS;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID)
public class EmortisBiomeGen {

    //___________________  W O O D L A N D S  ____________________//

    private static Biome makeVerdurousWoodlandsBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale) {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
        mobspawninfo$builder.setPlayerCanSpawn();
        EmortisBiomeGen.withVerdurousSpawns(mobspawninfo$builder);
        EmortisBiomeGen.withWoodlandsSpawns(mobspawninfo$builder);

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(surfaceBuilder);

        DefaultBiomeFeatures.addDefaultOverworldLandStructures(biomegenerationsettings$builder);
        biomegenerationsettings$builder.addStructureStart(StructureFeatures.VILLAGE_TAIGA);
        biomegenerationsettings$builder.addStructureStart(StructureFeatures.JUNGLE_TEMPLE);
        biomegenerationsettings$builder.addStructureStart(StructureFeatures.RUINED_PORTAL_JUNGLE);

        EmortisBiomeGen.withWoodlandsFeatures(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addSurfaceFreezing(biomegenerationsettings$builder);

        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(depth).scale(scale).temperature(0.95F).downfall(0.25F)
                .specialEffects((new BiomeAmbience.Builder())
                        .waterColor(4445678)
                        .waterFogColor(5613789)
                        .fogColor(12638463)
                        .skyColor(getSkyColorWithTemperatureModifier(0.5F))
                        .grassColorOverride(5627304)
                        .foliageColorOverride(8442041).skyColor(11726569)
                        .ambientMoodSound(BiomeMusic.CALM_RIGHT)
                        //.backgroundMusic(BiomeMusic.UN_DIA_DE_ABRIL_MUSIC)
                        .build())
                .mobSpawnSettings(mobspawninfo$builder.build()).generationSettings(biomegenerationsettings$builder.build()).build();
    }

    //___________________  F I E L D S  ____________________//

    private static Biome makeVerdurousFieldsBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale) {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
        mobspawninfo$builder.setPlayerCanSpawn();
        EmortisBiomeGen.withVerdurousSpawns(mobspawninfo$builder);
        EmortisBiomeGen.withFieldsSpawns(mobspawninfo$builder);

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(surfaceBuilder);

        DefaultBiomeFeatures.addDefaultOverworldLandStructures(biomegenerationsettings$builder);
        biomegenerationsettings$builder.addStructureStart(StructureFeatures.VILLAGE_PLAINS);
        biomegenerationsettings$builder.addStructureStart(StructureFeatures.PILLAGER_OUTPOST);
        biomegenerationsettings$builder.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);

        EmortisBiomeGen.withFieldsFeatures(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addSurfaceFreezing(biomegenerationsettings$builder);

        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.PLAINS).depth(depth).scale(scale).temperature(0.95F).downfall(0.25F)
                .specialEffects((new BiomeAmbience.Builder())
                        .waterColor(4445678)
                        .waterFogColor(5613789)
                        .fogColor(12638463)
                        .skyColor(getSkyColorWithTemperatureModifier(0.5F))
                        .grassColorOverride(5627304)
                        .foliageColorOverride(8442041).skyColor(11726569)
                        //.ambientMoodSound(BiomeMusic.CALM_RIGHT)
                        .backgroundMusic(BiomeMusic.UN_DIA_DE_ABRIL_MUSIC)
                        .build())
                .mobSpawnSettings(mobspawninfo$builder.build()).generationSettings(biomegenerationsettings$builder.build()).build();
    }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -0.75F, 0.75F);
        return MathHelper.hsvToRgb(0.2460909F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(verdurousFieldsKey, PLAINS, LUSH, OVERWORLD, SPARSE, MAGICAL);
        BiomeDictionary.addTypes(verdurousWoodlandsKey, FOREST, LUSH, OVERWORLD, DENSE, MAGICAL);
    }
    public static Biome verdurousWoodlands =
            makeVerdurousWoodlandsBiome(() -> ConfiguredSurfaceBuilders.GIANT_TREE_TAIGA, 0.4F, 0.425F).setRegistryName(EmortisConstants.MOD_ID, "verdurous_woodlands");

    public static Biome verdurousFields =
            makeVerdurousFieldsBiome(() -> ConfiguredSurfaceBuilders.GRASS, 0.125F, 0.05F).setRegistryName(EmortisConstants.MOD_ID, "verdurous_fields");

    public static RegistryKey<Biome> verdurousWoodlandsKey = BiomeRegistry.key(verdurousWoodlands);
    public static RegistryKey<Biome> verdurousFieldsKey = BiomeRegistry.key(verdurousFields);

    @ObjectHolder(EmortisConstants.MOD_ID)
    @Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class BiomeRegistry {
        @SubscribeEvent
        public static void biomeRegistry(final RegistryEvent.Register<Biome> biomeRegistryEvent) {
            biomeRegistryEvent.getRegistry().registerAll(verdurousWoodlands, verdurousFields);
            BiomeDictionary.addTypes(verdurousWoodlandsKey, FOREST);
            BiomeDictionary.addTypes(verdurousFieldsKey, PLAINS);
        }
        @SubscribeEvent
        public static void featureRegistry(final RegistryEvent.Register<Feature<?>> registryEvent) {
            registryEvent.getRegistry().register(WorldEvent.LIGHTS.setRegistryName(FeatureLib.LIGHTS));
        }
        private static RegistryKey<Biome> key(Biome b) {
            return RegistryKey.create(Registry.BIOME_REGISTRY, Objects.requireNonNull(b.getRegistryName()));
        }
    }

    @SubscribeEvent
    public static void biomeLoad(BiomeLoadingEvent e) {
        if (e.getCategory() == Biome.Category.NETHER || e.getCategory() == Biome.Category.THEEND)
            return;

        if (Config.SPAWN_ORE.get()) {
            EmortisOreGen.generateOres(e);
        }
//            e.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
//                    Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(BlockRegistry.RECONDITE_ORE.getRegistryName()))).build();
        if ((e.getCategory().equals(Biome.Category.FOREST) || e.getCategory().equals(Biome.Category.TAIGA) || e.getName().equals(verdurousWoodlands.getRegistryName()) || e.getName().equals(verdurousWoodlands.getRegistryName())  && Config.SPAWN_BERRIES.get())) {
            e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                    Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(BlockRegistry.SPECTABILIS_BUSH.getRegistryName()))).build();
            e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                    Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(BlockRegistry.DOMINION_BERRY_BUSH.getRegistryName()))).build();
        }
        if(e.getName().equals(verdurousWoodlands.getRegistryName())){
            addVerdurousWoodlandsFeatures(e);
        }
        if(e.getName().equals(verdurousFields.getRegistryName())){
            addVerdurousFieldsFeatures(e);
        }
    }

    public static void addVerdurousFieldsFeatures(BiomeLoadingEvent e){
        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(BlockRegistry.SPECTABILIS_BUSH.getRegistryName()))).build();
        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(BlockRegistry.DOMINION_BERRY_BUSH.getRegistryName()))).build();

        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(BlockRegistry.LISIANTHUS.getRegistryName()))).build();
        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(BlockRegistry.AZULOREAL_ORCHID.getRegistryName()))).build();
        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(BlockRegistry.IRIDESCENT_SPROUTS.getRegistryName()))).build();

        e.getGeneration().addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RANDOM_LIGHTS_LOC))).build();
        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(VERDUROUS_AZULOREAL_TREES))).build();
        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(VERDUROUS_JESSIC_TREES))).build();
        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(VANILLA_VERDUROUS))).build();
//            if (Config.SPAWN_ORE.get()) {
//                e.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
//                        Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(WorldEvent.EXTRA_RECONDITE_ORE))).build();
//            }
    }

    public static void addVerdurousWoodlandsFeatures(BiomeLoadingEvent e){

        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(BlockRegistry.SPECTABILIS_BUSH.getRegistryName()))).build();
        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(BlockRegistry.DOMINION_BERRY_BUSH.getRegistryName()))).build();

        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(BlockRegistry.LISIANTHUS.getRegistryName()))).build();
        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(BlockRegistry.AZULOREAL_ORCHID.getRegistryName()))).build();
        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(BlockRegistry.IRIDESCENT_SPROUTS.getRegistryName()))).build();

        e.getGeneration().addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(RANDOM_LIGHTS_LOC))).build();
        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(VERDUROUS_AZULOREAL_TREES))).build();
        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(VERDUROUS_JESSIC_TREES))).build();
        e.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(VANILLA_VERDUROUS))).build();
//        if (Config.SPAWN_ORE.get()) {
//            e.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
//                    Objects.requireNonNull(WorldGenRegistries.CONFIGURED_FEATURE.get(WorldEvent.EXTRA_RECONDITE_ORE))).build();
//        }
    }

    public static void withVerdurousSpawns(MobSpawnInfo.Builder spawns) {
        DefaultBiomeFeatures.commonSpawns(spawns);
        spawns
            .addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.FERAL_CANIS, 100, 1, 4))
            .addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.SUNDERED_CADAVER, 100, 1, 6))
            .addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.NECRAW_FASCII, 80, 1, 2))
            .addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIFIED_PIGLIN, 3, 2, 4));
    }
    public static void withWoodlandsSpawns(MobSpawnInfo.Builder spawns) {
        spawns
            .addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PARROT, 40, 1, 3))
            .addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.WOLF, 10, 2, 3))
            .addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.FOX, 8, 1, 2))
            .addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PANDA, 80, 1, 2));
    }
    public static void withFieldsSpawns(MobSpawnInfo.Builder spawns) {
        DefaultBiomeFeatures.farmAnimals(spawns);
        DefaultBiomeFeatures.plainsSpawns(spawns);
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.SLIME, 10, 2, 3));
    }

    public static void withVerdurousFeatures(BiomeGenerationSettings.Builder feature) {
        DefaultBiomeFeatures.addDefaultCarvers(feature);
        DefaultBiomeFeatures.addDefaultLakes(feature);
        DefaultBiomeFeatures.addDefaultMonsterRoom(feature);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(feature);
        DefaultBiomeFeatures.addDefaultOres(feature);
        DefaultBiomeFeatures.addDefaultSoftDisks(feature);
        DefaultBiomeFeatures.addDefaultMushrooms(feature);
        DefaultBiomeFeatures.addDefaultExtraVegetation(feature);
        DefaultBiomeFeatures.addDefaultSprings(feature);
    }
    public static void withWoodlandsFeatures(BiomeGenerationSettings.Builder feature) {
        DefaultBiomeFeatures.addMossyStoneBlock(feature);
        DefaultBiomeFeatures.addLightBambooVegetation(feature);
//                                                         TODO  -->  CONSIDER ADDING CUSTOM TREE GEN HERE
        DefaultBiomeFeatures.addJungleTrees(feature);
        DefaultBiomeFeatures.addWarmFlowers(feature);
        DefaultBiomeFeatures.addJungleGrass(feature);
        withVerdurousFeatures(feature);
        DefaultBiomeFeatures.addJungleExtraVegetation(feature);
        DefaultBiomeFeatures.addSurfaceFreezing(feature);
    }
    public static void withFieldsFeatures(BiomeGenerationSettings.Builder feature) {
        DefaultBiomeFeatures.addPlainGrass(feature);
        DefaultBiomeFeatures.addPlainVegetation(feature);
//                                                         TODO  -->  CONSIDER ADDING CUSTOM TREE GEN HERE
        withVerdurousFeatures(feature);
        DefaultBiomeFeatures.addSurfaceFreezing(feature);
    }
}