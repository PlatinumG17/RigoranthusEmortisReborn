package com.platinumg17.rigoranthusemortisreborn.world.gen;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.world.biome.EmortisBiomes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID)
public class EmortisBiomeGen {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEarlyBiomeLoad(BiomeLoadingEvent event) {
        ResourceLocation biome = event.getName();
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        MobSpawnInfoBuilder spawns = event.getSpawns();
        if (biome == null) return;

        if (DataUtil.matchesKeys(biome, EmortisBiomes.VERDUROUS_FIELDS.getKey()))
            withVerdurousFieldsFeatures(generation, spawns);
        if (DataUtil.matchesKeys(biome, EmortisBiomes.VERDUROUS_WOODLANDS.getKey()))
            withVerdurousWoodlandsFeatures(generation, spawns);
    }

    public static void withVerdurousSpawns(MobSpawnInfoBuilder spawns) {
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), 100, 1, 6));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.FERAL_CANIS.get(), 100, 1, 4));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.NECRAW_FASCII.get(), 80, 1, 2));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PIG, 10, 4, 4));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.CHICKEN, 10, 4, 4));
        spawns.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.BAT, 10, 8, 8));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIFIED_PIGLIN, 3, 2, 4));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 95, 1, 4));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 1, 4));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 1, 4));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
    }

    public static void withVerdurousTrees(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_PLAIN);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_SUGAR_CANE);
    }
    public static void withVerdurousStructures(BiomeGenerationSettings.Builder builder) {
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
        builder.addStructureStart(StructureFeatures.MINESHAFT);
        builder.addStructureStart(StructureFeatures.RUINED_PORTAL_SWAMP);
        builder.addStructureStart(StructureFeatures.BURIED_TREASURE);
    }

    public static void withVerdurousFieldsFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnInfoBuilder spawns) {
        builder.addStructureStart(StructureFeatures.PILLAGER_OUTPOST);
        builder.addStructureStart(StructureFeatures.VILLAGE_PLAINS);
        DefaultBiomeFeatures.addDefaultLakes(builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultSprings(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addBambooVegetation(builder);
        EmortisBiomeGen.withVerdurousTrees(builder);
        EmortisBiomeGen.withVerdurousStructures(builder);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_PUMPKIN);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_PLAIN);
        EmortisBiomeGen.withVerdurousSpawns(spawns);
        DefaultBiomeFeatures.commonSpawns(spawns);
        DefaultBiomeFeatures.farmAnimals(spawns);
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.HORSE, 5, 2, 4));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.COW, 8, 4, 4));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.SHEEP, 12, 4, 4));
        spawns.setPlayerCanSpawn();
    }

    public static void withVerdurousWoodlandsFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnInfoBuilder spawns) {
        builder.addStructureStart(StructureFeatures.VILLAGE_TAIGA);
        builder.addStructureStart(StructureFeatures.JUNGLE_TEMPLE);
        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addSwampClayDisk(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addBambooVegetation(builder);
        EmortisBiomeGen.withVerdurousTrees(builder);
        EmortisBiomeGen.withVerdurousStructures(builder);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_MELON);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.VINES);
        EmortisBiomeGen.withVerdurousSpawns(spawns);
        DefaultBiomeFeatures.commonSpawns(spawns);
        DefaultBiomeFeatures.farmAnimals(spawns);
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PARROT, 40, 1, 2));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.WOLF, 8, 4, 4));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.FOX, 4, 1, 4));
        spawns.setPlayerCanSpawn();
    }
}