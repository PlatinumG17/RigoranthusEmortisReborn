package com.platinumg17.rigoranthusemortisreborn.world.biome;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.world.FeatureLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.world.WorldEvent;
import com.platinumg17.rigoranthusemortisreborn.magica.common.world.feature.BiomeMusic;
import com.platinumg17.rigoranthusemortisreborn.world.gen.EmortisBiomeGen;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Objects;
import java.util.function.Supplier;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EmortisBiomes {


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
                        .backgroundMusic(BiomeMusic.UN_DIA_DE_ABRIL_MUSIC)
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
                        .ambientMoodSound(BiomeMusic.CALM_RIGHT)
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
            EmortisBiomes.makeVerdurousWoodlandsBiome(() -> ConfiguredSurfaceBuilders.GIANT_TREE_TAIGA, 0.4F, 0.425F).setRegistryName(EmortisConstants.MOD_ID, "verdurous_woodlands");

    public static Biome verdurousFields =
            EmortisBiomes.makeVerdurousFieldsBiome(() -> ConfiguredSurfaceBuilders.GRASS, 0.125F, 0.05F).setRegistryName(EmortisConstants.MOD_ID, "verdurous_fields");

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
}