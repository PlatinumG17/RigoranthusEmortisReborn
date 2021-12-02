package com.platinumg17.rigoranthusemortisreborn.world;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Difficulty;
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

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID)
public class EmortisMobSpawns {
    public static void registerSpawns() {
        EntitySpawnPlacementRegistry.register(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(RigoranthusEntityTypes.NECRAW_FASCII.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(RigoranthusEntityTypes.LANGUID_DWELLER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EmortisMobSpawns::languidDwellerCondition);
        EntitySpawnPlacementRegistry.register(RigoranthusEntityTypes.FERAL_CANIS.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EmortisMobSpawns::canisChordataCondition);
    }
    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        if (event.getName() == null) return;
        ResourceLocation biome = event.getName();
        MobSpawnInfoBuilder spawns = event.getSpawns();
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, biome);
        List<Biome.Category> categories = Arrays.asList(Biome.Category.FOREST, Biome.Category.EXTREME_HILLS, Biome.Category.JUNGLE,
                Biome.Category.PLAINS, Biome.Category.SWAMP, Biome.Category.SAVANNA, Biome.Category.MESA, Biome.Category.TAIGA);

        if (categories.contains(event.getCategory())) {
            if (Config.languidDwellerSpawnWeight.get() > 0)
                spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.LANGUID_DWELLER.get(), Config.languidDwellerSpawnWeight.get(), 1, 1));
        }

        if (!event.getCategory().equals(Biome.Category.MUSHROOM) && !event.getCategory().equals(Biome.Category.NONE)) {
            if (Config.sunderedCadaverSpawnWeight.get() > 0)
                spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), Config.sunderedCadaverSpawnWeight.get(), 1, 6));
            if (Config.necrawFasciiSpawnWeight.get() > 0)
                spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.NECRAW_FASCII.get(), Config.necrawFasciiSpawnWeight.get(), 1, 2));
            if (Config.feralCanisChordataSpawnWeight.get() > 0)
                spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.FERAL_CANIS.get(), Config.feralCanisChordataSpawnWeight.get(), 1, 1));
        }
    }
    public static boolean canisChordataCondition(EntityType<? extends MonsterEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
        if (((World) world).dimension() != World.OVERWORLD) return false;
        return world.getDifficulty() != Difficulty.PEACEFUL && (world.getMaxLocalRawBrightness(pos)) <= 10;
    }

    public static boolean languidDwellerCondition(EntityType<? extends MonsterEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
        if (((World) world).dimension() != World.OVERWORLD) return false;
        return world.getDifficulty() != Difficulty.PEACEFUL && pos.getY() <= Config.languidDwellerMaxSpawnHeight.get();
    }
}