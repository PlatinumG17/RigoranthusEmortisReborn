package com.platinumg17.rigoranthusemortisreborn.world.gen;

import com.platinumg17.rigoranthusemortisreborn.blocks.trees.AzulorealTree;
import com.platinumg17.rigoranthusemortisreborn.blocks.trees.JessicTree;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.world.biome.EmortisBiomes;
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

        if (Config.enableTreeGeneration.get()) {
            if (new ResourceLocation(EmortisBiomes.VERDUROUS_WOODLANDS.get().toString()).equals(event.getName())) {
                List<Supplier<ConfiguredFeature<?, ?>>> base =
                        event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

                base.add(() -> RigoranthusConfiguredFeatures.JESSIC
                        .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                        .decorated(Placement.COUNT_EXTRA.configured(
                                new AtSurfaceWithExtraConfig(1, 0.1f, 2))));
                base.add(() -> RigoranthusConfiguredFeatures.LOOMING_JESSIC
                        .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                        .decorated(Placement.COUNT_EXTRA.configured(
                                new AtSurfaceWithExtraConfig(1, 0.1f, 2))));
                base.add(() -> RigoranthusConfiguredFeatures.MEGA_JESSIC
                        .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                        .decorated(Placement.COUNT_EXTRA.configured(
                                new AtSurfaceWithExtraConfig(0, 0.1f, 2))));
                base.add(() -> RigoranthusConfiguredFeatures.AZULOREAL
                        .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                        .decorated(Placement.COUNT_EXTRA.configured(
                                new AtSurfaceWithExtraConfig(1, 0.1f, 2))));
                base.add(() -> RigoranthusConfiguredFeatures.LOOMING_AZULOREAL
                        .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                        .decorated(Placement.COUNT_EXTRA.configured(
                                new AtSurfaceWithExtraConfig(1, 0.1f, 2))));
                base.add(() -> RigoranthusConfiguredFeatures.MEGA_AZULOREAL
                        .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                        .decorated(Placement.COUNT_EXTRA.configured(
                                new AtSurfaceWithExtraConfig(1, 0.1f, 2))));
            }
        }
    }
}
