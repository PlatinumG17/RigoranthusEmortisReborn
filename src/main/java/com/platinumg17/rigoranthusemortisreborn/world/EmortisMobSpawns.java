package com.platinumg17.rigoranthusemortisreborn.world;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
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
//    public static void EmortisMobSpawns() {
//        EntitySpawnPlacementRegistry.register(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EmortisMobSpawns::sunderedCadaverCondition);
//        EntitySpawnPlacementRegistry.register(RigoranthusEntityTypes.NECRAW_FASCII.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EmortisMobSpawns::sunderedCadaverCondition);
//        EntitySpawnPlacementRegistry.register(RigoranthusEntityTypes.LANGUID_DWELLER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EmortisMobSpawns::languidDwellerCondition);
//        EntitySpawnPlacementRegistry.register(RigoranthusEntityTypes.CANIS_CHORDATA.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EmortisMobSpawns::canisChordataCondition);
//    }
//
//    public static boolean sunderedCadaverCondition(EntityType<? extends MonsterEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
//        if (((World) world).dimension() != World.OVERWORLD) return false;
//        return Config.sunderedCadaverSpawnWeight.get() >= 1;
//    }
//    public static boolean canisChordataCondition(EntityType<? extends CreatureEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
//        if (((World) world).dimension() != World.OVERWORLD) return false;
//        return Config.canisChordataSpawnWeight.get() >= 1;
//    }
//    public static boolean languidDwellerCondition(EntityType<? extends MonsterEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
//        if (((World) world).dimension() != World.OVERWORLD) return false;
//        return pos.getY() <= Config.languidDwellerMaxSpawnHeight.get();
//    }
}
