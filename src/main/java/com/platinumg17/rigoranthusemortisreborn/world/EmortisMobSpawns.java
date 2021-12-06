package com.platinumg17.rigoranthusemortisreborn.world;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ModEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID)
public class EmortisMobSpawns {

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
                spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.LANGUID_DWELLER, Config.languidDwellerSpawnWeight.get(), 1, 1));
        }

        if (!event.getCategory().equals(Biome.Category.MUSHROOM) && !event.getCategory().equals(Biome.Category.NONE)) {
            if (Config.sunderedCadaverSpawnWeight.get() > 0)
                spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.SUNDERED_CADAVER, Config.sunderedCadaverSpawnWeight.get(), 1, 6));
            if (Config.necrawFasciiSpawnWeight.get() > 0)
                spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.NECRAW_FASCII, Config.necrawFasciiSpawnWeight.get(), 1, 2));
            if (Config.feralCanisChordataSpawnWeight.get() > 0)
                spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.FERAL_CANIS, Config.feralCanisChordataSpawnWeight.get(), 1, 1));
        }
    }
}