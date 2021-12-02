package com.platinumg17.rigoranthusemortisreborn.magica.common.world.feature;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;


//@ObjectHolder(EmortisConstants.MOD_ID)
//public class BiomeMusic {
//
//    @ObjectHolder(LibMusicNames.UN_DIA_DE_ABRIL) public static SoundEvent UN_DIA_DE_ABRIL;
//    @ObjectHolder(LibMusicNames.CALM_RIGHT) public static SoundEvent CALM_RIGHT;
//
//    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
//    public static class RegistryEvents {
//        @SubscribeEvent
//        public static void onSoundRegistry(final RegistryEvent.Register<SoundEvent> soundRegistry) {
//            IForgeRegistry<SoundEvent> registry = soundRegistry.getRegistry();
//            registry.register(new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, "un_dia_de_abril")));
//            registry.register(new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, "calm_right")));
//        }
//    }
////    public static final SoundEvent UN_DIA_DE_ABRIL = register("un_dia_de_abril");
////    private static SoundEvent register(String nameAndLocation) {
////        return Registry.register(Registry.SOUND_EVENT, nameAndLocation, new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, nameAndLocation)));
////    }
//}