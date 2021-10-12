package com.platinumg17.rigoranthusemortisreborn.core.registry;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeRegistration {
        public static final DeferredRegister<Biome> BIOMES
                = DeferredRegister.create(ForgeRegistries.BIOMES, RigoranthusEmortisReborn.MOD_ID);

        public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS
                = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, RigoranthusEmortisReborn.MOD_ID);

        public static void init()
        {
            IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
            BIOMES.register(bus);
            SURFACE_BUILDERS.register(bus);
        }
//    public static ResourceKey<Biome> alps = register("alps");
//
//    private static ResourceKey<Biome> register(String name)
//    {
//        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, name));
//    }
}
