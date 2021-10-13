package com.platinumg17.rigoranthusemortisreborn.world.biome;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.registry.BiomeRegistration;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.world.gen.RigoranthusConfiguredFeatures;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = RigoranthusEmortisReborn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EmortisBiomes {

    public static final RegistryObject<Biome> VERDUROUS_WOODLANDS = BiomeRegistration.BIOMES.register("verdurous_woodlands",
            () -> makeVerdurousWoodlandsBiome(() -> WorldGenRegistries.CONFIGURED_SURFACE_BUILDER.getOrThrow(
                    EmortisConfiguredSB.VERDUROUS_SURFACE), 0.4F, 0.4F));


    private static Biome makeVerdurousWoodlandsBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale) {
        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
        builder.addStructureStart(StructureFeatures.MINESHAFT);
        builder.addStructureStart(StructureFeatures.RUINED_PORTAL_SWAMP);
        builder.addStructureStart(StructureFeatures.BURIED_TREASURE);
        DefaultBiomeFeatures.addDefaultCarvers(builder);
        DefaultBiomeFeatures.addDefaultLakes(builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);

        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addSwampClayDisk(builder);

        MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder().setPlayerCanSpawn();

        builder.addFeature(GenerationStage.Decoration.LAKES, Features.LAKE_WATER.chance(Config.lakeSpawnWeight.get()));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal(), () -> RigoranthusConfiguredFeatures.TREES_VERDUROUS);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal(), () -> RigoranthusConfiguredFeatures.FLOWERS_VERDUROUS);

        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal(), () -> RigoranthusConfiguredFeatures.JESSIC.chance(Config.jessicSpawnWeight.get()));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal(), () -> RigoranthusConfiguredFeatures.LOOMING_JESSIC.chance(Config.loomingJessicSpawnWeight.get()));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal(), () -> RigoranthusConfiguredFeatures.MEGA_JESSIC.chance(Config.megaJessicSpawnWeight.get()));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal(), () -> RigoranthusConfiguredFeatures.AZULOREAL.chance(Config.azulorealSpawnWeight.get()));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal(), () -> RigoranthusConfiguredFeatures.LOOMING_AZULOREAL.chance(Config.loomingAzulorealSpawnWeight.get()));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal(), () -> RigoranthusConfiguredFeatures.MEGA_AZULOREAL.chance(Config.megaAzulorealSpawnWeight.get()));

        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addForestFlowers(builder);
        DefaultBiomeFeatures.addForestGrass(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);

        DefaultBiomeFeatures.addDefaultSprings(builder);
        DefaultBiomeFeatures.addDefaultExtraVegetation(builder);
        DefaultBiomeFeatures.addLightBambooVegetation(builder);

        MobSpawnInfo.Builder mobbuilder = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.farmAnimals(mobbuilder);
        DefaultBiomeFeatures.commonSpawns(mobbuilder);
        mobbuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), 100, 1, 5));
        mobbuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.CANIS_CHORDATA.get(), 80, 1, 2));
        mobbuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.NECRAW_FASCII.get(), 80, 1, 1));
        mobbuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.RAVAGER, 3, 1, 1));
        mobbuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIFIED_PIGLIN, 3, 2, 4));
        mobbuilder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.SHEEP, 12, 4, 4));
        mobbuilder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PIG, 10, 4, 4));
        mobbuilder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.CHICKEN, 10, 4, 4));
        mobbuilder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.COW, 8, 4, 4));
        mobbuilder.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.BAT, 10, 8, 8));
        mobbuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
        mobbuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 4, 4));
        mobbuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        mobbuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 4, 4));
        mobbuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
        mobbuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
        mobbuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));

        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(depth).scale(scale)
                .temperature(1.5F).downfall(0.25F).specialEffects((new BiomeAmbience.Builder())
                        .waterColor(4445678)
                        .waterFogColor(5613789)
                        .skyColor(getSkyColorWithTemperatureModifier(0.5F))
                        .fogColor(12638463).grassColorOverride(5627304)
                        .foliageColorOverride(8442041).skyColor(11726569)
                        .ambientMoodSound(new MoodSoundAmbience(RigoranthusSoundRegistry.CALM_RIGHT.get(), 6000, 8, 2.0D))
                        .backgroundMusic(BackgroundMusicTracks.createGameMusic(RigoranthusSoundRegistry.UN_DIA_DE_ABRIL.get()))
                        .build())
                .mobSpawnSettings(mobbuilder.build()).generationSettings(builder.build()).build();
    }

    public static void register() {
    }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 0.6F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -0.5F, 0.5F);
        return MathHelper.hsvToRgb(0.2460909F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
}













///////    FROM  FAILED  ATTEMPT  AT  ABNORMALS  CORE  INTEGRATION   //////////////

//    private static final BiomeSubRegistryHelper HELPER = RigoranthusEmortisReborn.REGISTRY_HELPER.getBiomeSubHelper();
//    public static final BiomeSubRegistryHelper.KeyedBiome VERDUROUS_FIELDS = HELPER.createBiome("verdurous_fields", () -> makeVerdurousFieldsBiome(-0.05F, 0.0F));
//public static final BiomeSubRegistryHelper.KeyedBiome VERDUROUS_WOODLANDS = HELPER.createBiome("verdurous_woodlands", () -> makeVerdurousWoodlandsBiome(0.375F, 0.15F));


//    public static void registerBiomesToDictionary() {
////        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(VERDUROUS_FIELDS.getKey(), Config.verdurousWoodlandsSpawnWeight.get()));
//        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(VERDUROUS_WOODLANDS.getKey(), Config.verdurousWoodlandsSpawnWeight.get()));
//    }
//
//    public static void addBiomeTypes() {
////        BiomeDictionary.addTypes(VERDUROUS_FIELDS.getKey(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.OVERWORLD);
//        BiomeDictionary.addTypes(VERDUROUS_WOODLANDS.getKey(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.OVERWORLD);
//    }


//    public static void addSubBiomes() {
//        BiomeUtil.addHillBiome(VERDUROUS_FIELDS.getKey(), Pair.of(VERDUROUS_WOODLANDS.getKey(), 1));
//    }

//    private static Biome makeVerdurousFieldsBiome(float depth, float scale) {
//        return (new Biome.Builder())
//                .precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(depth).scale(scale)
//                .temperature(1.5F).downfall(0.25F).specialEffects((new BiomeAmbience.Builder())
//                        .waterColor(4445678)
//                        .waterFogColor(5613789)
//                        .skyColor(getSkyColorWithTemperatureModifier(0.5F))
//                        .fogColor(12638463).grassColorOverride(5627304)
//                        .foliageColorOverride(8442041).skyColor(11726569)
//                        .ambientMoodSound(new MoodSoundAmbience(RigoranthusSoundRegistry.CALM_RIGHT.get(), 6000, 8, 2.0D))
//                        .backgroundMusic(BackgroundMusicTracks.createGameMusic(RigoranthusSoundRegistry.UN_DIA_DE_ABRIL.get()))
//                        .build())
//                .mobSpawnSettings(new MobSpawnInfo.Builder().build())//.copy())
//                .generationSettings((new BiomeGenerationSettings.Builder())
//                        .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
//                        .build()).build();
//    }
//    private static Biome makeVerdurousWoodlandsBiome(float depth, float scale) {
//        return (new Biome.Builder())
//                .precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(depth).scale(scale)
//                .temperature(1.5F).downfall(0.25F).specialEffects((new BiomeAmbience.Builder())
//                        .waterColor(4445678)
//                        .waterFogColor(5613789)
//                        .skyColor(getSkyColorWithTemperatureModifier(0.5F))
//                        .fogColor(12638463).grassColorOverride(5627304)
//                        .foliageColorOverride(8442041).skyColor(11726569)
//                        .ambientMoodSound(new MoodSoundAmbience(RigoranthusSoundRegistry.CALM_RIGHT.get(), 6000, 8, 2.0D))
//                        .backgroundMusic(BackgroundMusicTracks.createGameMusic(RigoranthusSoundRegistry.UN_DIA_DE_ABRIL.get()))
//                        .build())
//                .mobSpawnSettings(new MobSpawnInfo.Builder().build())
//                .generationSettings((new BiomeGenerationSettings.Builder())
//                        .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
//                        .build()).build();
//    }