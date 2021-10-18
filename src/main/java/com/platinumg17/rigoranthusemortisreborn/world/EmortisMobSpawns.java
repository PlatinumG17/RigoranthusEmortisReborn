package com.platinumg17.rigoranthusemortisreborn.world;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = RigoranthusEmortisReborn.MOD_ID)
public class EmortisMobSpawns {
    public static void registerSpawns() {
        EntitySpawnPlacementRegistry.register(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(RigoranthusEntityTypes.NECRAW_FASCII.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(RigoranthusEntityTypes.LANGUID_DWELLER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EmortisMobSpawns::languidDwellerCondition);
        EntitySpawnPlacementRegistry.register(RigoranthusEntityTypes.CANIS_CHORDATA.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
    }

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        if (event.getName() == null) return;
        ResourceLocation biome = event.getName();
        MobSpawnInfoBuilder spawns = event.getSpawns();
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, biome);
        if (event.getCategory() == Biome.Category.DESERT ||
            event.getCategory() == Biome.Category.EXTREME_HILLS ||
            event.getCategory() == Biome.Category.FOREST ||
            event.getCategory() == Biome.Category.JUNGLE ||
            event.getCategory() == Biome.Category.MESA ||
            event.getCategory() == Biome.Category.PLAINS ||
            event.getCategory() == Biome.Category.SAVANNA ||
            event.getCategory() == Biome.Category.SWAMP ||
            event.getCategory() == Biome.Category.TAIGA ||
            event.getCategory() == Biome.Category.RIVER ||
            event.getCategory() == Biome.Category.OCEAN)
        {
            spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.LANGUID_DWELLER.get(), Config.languidDwellerSpawnWeight.get(), 1, 5));
        }
        if (BiomeDictionary.hasType(key, BiomeDictionary.Type.OVERWORLD)) {
            spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), Config.sunderedCadaverSpawnWeight.get(), 1, 6));
            spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.NECRAW_FASCII.get(), Config.necrawFasciiSpawnWeight.get(), 1, 2));
            spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.CANIS_CHORDATA.get(), Config.canisChordataSpawnWeight.get(), 1, 1));
        }
    }
//    public static boolean sunderedCadaverCondition(EntityType<? extends MonsterEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
//        if (((World) world).dimension() != World.OVERWORLD) return false;
//        return Config.sunderedCadaverSpawnWeight.get() > 0;
//    }
//    public static boolean necrawFasciiCondition(EntityType<? extends MonsterEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
//        if (((World) world).dimension() != World.OVERWORLD) return false;
//        return Config.sunderedCadaverSpawnWeight.get() > 0;
//    }
//    public static boolean canisChordataCondition(EntityType<? extends CreatureEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
//        if (((World) world).dimension() != World.OVERWORLD) return false;
//        return Config.canisChordataSpawnWeight.get() > 0;
//    }
    public static boolean languidDwellerCondition(EntityType<? extends MonsterEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
        if (((World) world).dimension() != World.OVERWORLD) return false;
        return pos.getY() <= Config.languidDwellerMaxSpawnHeight.get();
    }
}
//    if (Config.sunderedCadaverSpawnWeight.get() > 0) {spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), Config.sunderedCadaverSpawnWeight.get(), Config.sunderedCadaverMinGroupSize.get(), Config.sunderedCadaverMaxGroupSize.get()));}
//    if (Config.necrawFasciiSpawnWeight.get() > 0) {spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.NECRAW_FASCII.get(), Config.necrawFasciiSpawnWeight.get(), Config.necrawFasciiMinGroupSize.get(), Config.necrawFasciiMaxGroupSize.get()));}
//    if (Config.canisChordataSpawnWeight.get() > 0) {spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.CANIS_CHORDATA.get(), Config.canisChordataSpawnWeight.get(), Config.canisChordataMinGroupSize.get(), Config.canisChordataMaxGroupSize.get()));}