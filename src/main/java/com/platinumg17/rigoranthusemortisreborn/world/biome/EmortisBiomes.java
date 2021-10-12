package com.platinumg17.rigoranthusemortisreborn.world.biome;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.registry.BiomeRegistration;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.world.gen.RigoranthusConfiguredFeatures;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class EmortisBiomes
{
    public static final RegistryObject<Biome> VERDUROUS_WOODLANDS_BIOME = BiomeRegistration.BIOMES.register("verdurous_woodlands_biome",
            () -> makeVerdurousWoodlandsBiome(() -> WorldGenRegistries.CONFIGURED_SURFACE_BUILDER.getOrThrow(
                    EmortisConfiguredSB.VERDUROUS_SURFACE), -0.1F, 0.1F));

    public static void register(){ }

    private static Biome makeVerdurousWoodlandsBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale) {
        MobSpawnInfo.Builder mobspawns = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.farmAnimals(mobspawns);
        DefaultBiomeFeatures.commonSpawns(mobspawns);
        mobspawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), 100, 1, 5));
        mobspawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.CANIS_CHORDATA.get(), 80, 1, 2));
        mobspawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(RigoranthusEntityTypes.NECRAW_FASCII.get(), 80, 1, 1));
        mobspawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.RAVAGER, 3, 1, 1));
        mobspawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIFIED_PIGLIN, 3, 2, 4));
        mobspawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.SHEEP, 12, 4, 4));
        mobspawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PIG, 10, 4, 4));
        mobspawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.CHICKEN, 10, 4, 4));
        mobspawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.COW, 8, 4, 4));
        mobspawns.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.BAT, 10, 8, 8));
        mobspawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
        mobspawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 4, 4));
        mobspawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        mobspawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 4, 4));
        mobspawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
        mobspawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
        mobspawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
        BiomeGenerationSettings.Builder biomebuilder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(surfaceBuilder);

        biomebuilder.addStructureStart(StructureFeatures.MINESHAFT);
        biomebuilder.addStructureStart(StructureFeatures.RUINED_PORTAL_SWAMP);
        biomebuilder.addStructureStart(StructureFeatures.BURIED_TREASURE);


        DefaultBiomeFeatures.addDefaultCarvers(biomebuilder);
        DefaultBiomeFeatures.addDefaultLakes(biomebuilder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(biomebuilder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomebuilder);
        DefaultBiomeFeatures.addDefaultOres(biomebuilder);
        DefaultBiomeFeatures.addSwampClayDisk(biomebuilder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomebuilder);
        DefaultBiomeFeatures.addPlainVegetation(biomebuilder);
        DefaultBiomeFeatures.addDefaultSprings(biomebuilder);
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(biomebuilder);
        MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder().setPlayerCanSpawn();
        biomebuilder.addFeature(GenerationStage.Decoration.LAKES, Features.LAKE_WATER.chance(Config.lakeSpawnWeight.get()));
        biomebuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RigoranthusConfiguredFeatures.JESSIC.chance(Config.jessicSpawnWeight.get()));
        biomebuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RigoranthusConfiguredFeatures.LOOMING_JESSIC.chance(Config.loomingJessicSpawnWeight.get()));
        biomebuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RigoranthusConfiguredFeatures.MEGA_JESSIC.chance(Config.megaJessicSpawnWeight.get()));
        biomebuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RigoranthusConfiguredFeatures.AZULOREAL.chance(Config.azulorealSpawnWeight.get()));
        biomebuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RigoranthusConfiguredFeatures.LOOMING_AZULOREAL.chance(Config.loomingAzulorealSpawnWeight.get()));
        biomebuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RigoranthusConfiguredFeatures.MEGA_AZULOREAL.chance(Config.megaAzulorealSpawnWeight.get()));

        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.FOREST).depth(depth).scale(scale)
                .temperature(1.5F).downfall(0.25F).specialEffects((new BiomeAmbience.Builder()).waterColor(4445678).waterFogColor(5613789)
                        .fogColor(12638463).skyColor(getSkyColorWithTemperatureModifier(0.5F)).fogColor(12638463).grassColorOverride(5627304).foliageColorOverride(8442041)
                        .ambientParticle(new ParticleEffectAmbience(ParticleTypes.LAVA, 0.003f)).skyColor(11726569)
                        //.ambientLoopSound(RigoranthusSoundRegistry.UN_DIA_DE_ABRIL.get())
                        .ambientMoodSound(new MoodSoundAmbience(RigoranthusSoundRegistry.CALM_RIGHT.get(), 6000, 8, 2.0D))
                        //.ambientAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D))
                        .backgroundMusic(BackgroundMusicTracks.createGameMusic(RigoranthusSoundRegistry.UN_DIA_DE_ABRIL.get()))
                        .build())
                .mobSpawnSettings(mobspawns.build()).generationSettings(biomebuilder.build()).build();
    }

    private static int getSkyColorWithTemperatureModifier(float temperature)
    {
        float lvt_1_1_ = temperature / 0.6F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -0.5F, 0.5F);
        return MathHelper.hsvToRgb(0.2460909F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
}