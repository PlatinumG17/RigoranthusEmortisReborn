package com.platinumg17.rigoranthusemortisreborn.world.gen;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.world.biome.EmortisBiomes;
import com.platinumg17.rigoranthusemortisreborn.world.biome.EmortisConfiguredSB;
import com.platinumg17.rigoranthusemortisreborn.world.biome.EmortisSurfaceBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

@Mod.EventBusSubscriber(modid = RigoranthusEmortisReborn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EmortisBiomeGen {
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void register(final RegistryEvent.Register<SurfaceBuilder<?>> event) {
        registerBiome(EmortisConfiguredSB.VERDUROUS_SURFACE.location(),
                Blocks.GRASS.defaultBlockState(), Blocks.STONE.defaultBlockState(), BlockInit.FRAGMENTED_COBBLESTONE.get().defaultBlockState());
    }

    @SubscribeEvent
    public static void setupBiome(final FMLCommonSetupEvent event) {
        event.enqueueWork(() ->
        {
            if (Config.verdurousWoodlandsSpawnWeight.get() > 0) {
                addBiome(EmortisBiomes.VERDUROUS_WOODLANDS_BIOME.get(), BiomeManager.BiomeType.WARM, Config.verdurousWoodlandsSpawnWeight.get(), MAGICAL, WET, DENSE, RARE, FOREST, LUSH);
            }
        });
    }

    private static void registerBiome(ResourceLocation biomeRL, BlockState topBlock, BlockState fillerBlock, BlockState underwaterBlock)
    {
        Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, biomeRL, EmortisSurfaceBuilder.LOGGING_DEFAULT.get().configured(
                new SurfaceBuilderConfig(topBlock, fillerBlock, underwaterBlock)));
    }

    private static void addBiome(Biome biome, BiomeManager.BiomeType type, int weight, BiomeDictionary.Type... types)
    {
        RegistryKey<Biome> key = RegistryKey.create(ForgeRegistries.Keys.BIOMES,
                Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome)));

        BiomeDictionary.addTypes(key, types);
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key, weight));
    }
}