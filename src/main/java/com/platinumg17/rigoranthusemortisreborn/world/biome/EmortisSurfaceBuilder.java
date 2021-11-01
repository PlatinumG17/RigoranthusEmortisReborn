package com.platinumg17.rigoranthusemortisreborn.world.biome;

import com.platinumg17.rigoranthusemortisreborn.blocks.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EmortisSurfaceBuilder {

    public static final SurfaceBuilder<SurfaceBuilderConfig> VERDURE = new VerdurousSurfaceBuilder(SurfaceBuilderConfig.CODEC);

    @SubscribeEvent
    public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
        event.getRegistry().registerAll(
                VERDURE.setRegistryName(EmortisConstants.MOD_ID, "verdure")
        );
    }

    public static final class Configs {
        public static final SurfaceBuilderConfig VERDURE = new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.DIRT.defaultBlockState(), BlockInit.FRAGMENTED_COBBLESTONE.get().defaultBlockState());
    }

    public static final class Configured {
        public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> VERDURE = EmortisSurfaceBuilder.VERDURE.configured(Configs.VERDURE);

        private static <SC extends ISurfaceBuilderConfig> void register(String key, ConfiguredSurfaceBuilder<SC> builder) {
            WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(EmortisConstants.MOD_ID, key), builder);
        }
        public static void registerConfiguredSurfaceBuilders() {
            register("verdure", VERDURE);
        }
    }
}