package com.platinumg17.rigoranthusemortisreborn.world.gen;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.world.biome.EmortisBiomes;
import com.platinumg17.rigoranthusemortisreborn.world.gen.feature.RigoranthusConfiguredFeatures;
import com.platinumg17.rigoranthusemortisreborn.world.trees.AzulorealTree;
import com.platinumg17.rigoranthusemortisreborn.world.trees.JessicTree;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class EmortisTreeGen {
    public static void generateTrees(final BiomeLoadingEvent event) {
        JessicTree jessicTree = new JessicTree();
        AzulorealTree azulorealTree = new AzulorealTree();
        Random random = new Random();
//        float azulorealChance = Config.azulorealSpawnWeight.get().floatValue();
//        float jessicChance = Config.jessicSpawnWeight.get().floatValue();
//        float loomingAzulorealChance = Config.loomingAzulorealSpawnWeight.get().floatValue();
//        float loomingJessicChance = Config.loomingJessicSpawnWeight.get().floatValue();
//        float megaAzulorealChance = Config.megaAzulorealSpawnWeight.get().floatValue();
//        float megaJessicChance = Config.megaJessicSpawnWeight.get().floatValue();
        if ((new ResourceLocation(EmortisBiomes.verdurousWoodlands.toString()).equals(event.getName())) || (new ResourceLocation(EmortisBiomes.verdurousFields.toString()).equals(event.getName()))) {
            List<Supplier<ConfiguredFeature<?, ?>>> base = event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

            base.add(() -> RigoranthusConfiguredFeatures.VERDUROUS_PATCH);
            base.add(() -> RigoranthusConfiguredFeatures.FLOWERS_VERDUROUS);
            base.add(() -> RigoranthusConfiguredFeatures.TREES_VERDUROUS);
            base.add(() -> RigoranthusConfiguredFeatures.TALL_FLOWERS_VERDUROUS);
            base.add(() -> RigoranthusConfiguredFeatures.JESSIC
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(0, Config.jessicSpawnWeight.get(), 1))));
            base.add(() -> RigoranthusConfiguredFeatures.LOOMING_JESSIC
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(0, Config.loomingJessicSpawnWeight.get(), 1))));
            base.add(() -> RigoranthusConfiguredFeatures.MEGA_JESSIC
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(0, Config.megaJessicSpawnWeight.get(), 1))));
            base.add(() -> RigoranthusConfiguredFeatures.AZULOREAL
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(0, Config.azulorealSpawnWeight.get(), 1))));
            base.add(() -> RigoranthusConfiguredFeatures.LOOMING_AZULOREAL
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(0, Config.loomingAzulorealSpawnWeight.get(), 1))));
            base.add(() -> RigoranthusConfiguredFeatures.MEGA_AZULOREAL
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(0, Config.megaAzulorealSpawnWeight.get(), 1))));
        }
    }
}
