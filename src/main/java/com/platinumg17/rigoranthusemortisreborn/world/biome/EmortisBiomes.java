package com.platinumg17.rigoranthusemortisreborn.world.biome;

import com.minecraftabnormals.abnormals_core.core.util.BiomeUtil;
import com.minecraftabnormals.abnormals_core.core.util.registry.BiomeSubRegistryHelper;
import com.mojang.datafixers.util.Pair;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EmortisBiomes {

    private static final BiomeSubRegistryHelper HELPER = RigoranthusEmortisReborn.REGISTRY_HELPER.getBiomeSubHelper();
    public static final BiomeSubRegistryHelper.KeyedBiome VERDUROUS_FIELDS = HELPER.createBiome("verdurous_fields", () -> makeVerdurousFieldsBiome(0.125F, 0.05F));
    public static final BiomeSubRegistryHelper.KeyedBiome VERDUROUS_WOODLANDS = HELPER.createBiome("verdurous_woodlands", () -> makeVerdurousWoodlandsBiome(0.4F, 0.425F));
//    public static void registerBiomesToDictionary() {
//        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(VERDUROUS_FIELDS.getKey(), Config.verdurousFieldsSpawnWeight.get()));
//        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(VERDUROUS_WOODLANDS.getKey(), Config.verdurousWoodlandsSpawnWeight.get()));
//    }
    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(VERDUROUS_FIELDS.getKey(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.MAGICAL);
        BiomeDictionary.addTypes(VERDUROUS_WOODLANDS.getKey(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.MAGICAL);
    }
    public static void addBiomeVariants() {
        BiomeUtil.addHillBiome(VERDUROUS_FIELDS.getKey(), Pair.of(VERDUROUS_WOODLANDS.getKey(), 1));
    }

    private static Biome makeVerdurousFieldsBiome(float depth, float scale) {
        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.PLAINS).depth(depth).scale(scale)
                .temperature(1.5F).downfall(0.25F).specialEffects((new BiomeAmbience.Builder())
                        .waterColor(4445678)
                        .waterFogColor(5613789)
                        .skyColor(getSkyColorWithTemperatureModifier(0.5F))
                        .fogColor(12638463).grassColorOverride(5627304)
                        .foliageColorOverride(8442041).skyColor(11726569)
                        .ambientMoodSound(new MoodSoundAmbience(RigoranthusSoundRegistry.CALM_RIGHT.get(), 6000, 8, 2.0D))
                        .backgroundMusic(BackgroundMusicTracks.createGameMusic(RigoranthusSoundRegistry.UN_DIA_DE_ABRIL.get()))
                        .build())
                .mobSpawnSettings(new MobSpawnInfo.Builder().build())//.copy())
                .generationSettings((new BiomeGenerationSettings.Builder())
                        .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
                        //.surfaceBuilder(EmortisSurfaceBuilder.Configured.VERDURE)
                        .build()).build();
    }
    private static Biome makeVerdurousWoodlandsBiome(float depth, float scale) {
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
                .mobSpawnSettings(new MobSpawnInfo.Builder().build())
                .generationSettings((new BiomeGenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
                        .build()).build();
    }
    public static void register() {}
    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 0.6F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -0.5F, 0.5F);
        return MathHelper.hsvToRgb(0.2460909F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
}