package com.platinumg17.rigoranthusemortisreborn.world.gen;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ModEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID)
public class EmortisBiomeGen {

//    @SubscribeEvent(priority = EventPriority.HIGHEST)
//    public static void onEarlyBiomeLoad(BiomeLoadingEvent event) {
//        ResourceLocation biome = event.getName();
//        BiomeGenerationSettingsBuilder generation = event.getGeneration();
//        MobSpawnInfoBuilder spawns = event.getSpawns();
//        if (biome == null) return;
//
//        if (DataUtil.matchesKeys(biome, WorldEvent.verdurousFieldsKey))
//            withFieldsFeatures(generation);
//            withFieldsSpawns(spawns);
//        if (DataUtil.matchesKeys(biome, WorldEvent.verdurousWoodlandsKey))
//            withWoodlandsFeatures(generation);
//            withWoodlandsSpawns(spawns);
//    }

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
                                                        // TODO -->    ADD CUSTOM TREE GEN HERE
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
                                                        // TODO -->    ADD CUSTOM TREE GEN HERE
        withVerdurousFeatures(feature);
        DefaultBiomeFeatures.addSurfaceFreezing(feature);
    }
}