package com.platinumg17.rigoranthusemortisreborn.world.biome;

import com.platinumg17.rigoranthusemortisreborn.core.registry.BiomeRegistration;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;

public class EmortisSurfaceBuilder {
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> LOGGING_DEFAULT =
        BiomeRegistration.SURFACE_BUILDERS.register("logging_default", () -> new SurfaceBuildLog<>(() -> SurfaceBuilder.DEFAULT, SurfaceBuilderConfig.CODEC));
    public static void register() {
    }
}
