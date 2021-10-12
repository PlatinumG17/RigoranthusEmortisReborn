package com.platinumg17.rigoranthusemortisreborn.world.biome;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;

public class EmortisConfiguredSB
{
    public static RegistryKey<ConfiguredSurfaceBuilder<?>> VERDUROUS_SURFACE
            = RegistryKey.create(Registry.CONFIGURED_SURFACE_BUILDER_REGISTRY,
            new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "verdurous_surface"));
}