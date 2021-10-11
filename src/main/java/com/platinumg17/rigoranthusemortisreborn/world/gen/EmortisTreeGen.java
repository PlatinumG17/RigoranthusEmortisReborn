package com.platinumg17.rigoranthusemortisreborn.world.gen;

import com.platinumg17.rigoranthusemortisreborn.blocks.trees.AzulorealTree;
import com.platinumg17.rigoranthusemortisreborn.blocks.trees.JessicTree;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class EmortisTreeGen {
    public static void generateTrees(final BiomeLoadingEvent event) {
        JessicTree jessicTree = new JessicTree();
        AzulorealTree azulorealTree = new AzulorealTree();
        Random random = new Random();

        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(Config.enableTreeGeneration.get()) {
            if(types.contains(BiomeDictionary.Type.MOUNTAIN)) { // || types.contains(BiomeDictionary.Type.MODIFIED)) {
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
                                new AtSurfaceWithExtraConfig(1, 0.1f, 2))));
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
