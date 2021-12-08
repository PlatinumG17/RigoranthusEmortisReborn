package com.platinumg17.rigoranthusemortisreborn.magica.common.world.feature;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibSoundNames;
import net.minecraft.client.audio.BackgroundMusicSelector;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Objects;

public class BiomeMusic {

    public static ResourceLocation unDiaDeAbril_LOACATION = new  ResourceLocation(EmortisConstants.MOD_ID, "un_dia_de_abril");
    public static ResourceLocation calmRight_LOACATION = new  ResourceLocation(EmortisConstants.MOD_ID, "calm_right");

    public static SoundEvent diaDeAbril = new SoundEvent(unDiaDeAbril_LOACATION).setRegistryName(LibSoundNames.UN_DIA_DE_ABRIL);
    public static SoundEvent calmRight = new SoundEvent(calmRight_LOACATION).setRegistryName(LibSoundNames.CALM_RIGHT);

    public static RegistryKey<SoundEvent> diaDeAbrilKey = BiomeMusicRegistry.key(diaDeAbril);
    public static RegistryKey<SoundEvent> calmRightKey = BiomeMusicRegistry.key(calmRight);

    @ObjectHolder(EmortisConstants.MOD_ID)
    @Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class BiomeMusicRegistry {
        @SubscribeEvent
        public static void soundRegistry(final RegistryEvent.Register<SoundEvent> soundRegistryEvent) {
            soundRegistryEvent.getRegistry().registerAll(diaDeAbril, calmRight);
        }
        private static RegistryKey<SoundEvent> key(SoundEvent soundEvent) {
            return RegistryKey.create(Registry.SOUND_EVENT_REGISTRY, Objects.requireNonNull(soundEvent.getRegistryName()));
        }
    }

    public static final BackgroundMusicSelector UN_DIA_DE_ABRIL_MUSIC = createGameMusic(diaDeAbril);
    public static final BackgroundMusicSelector CALM_RIGHT_MUSIC = createGameMusic(calmRight);

    public static final MoodSoundAmbience CALM_RIGHT = new MoodSoundAmbience(calmRight, 6000, 8, 2.0D);
    public static final MoodSoundAmbience UN_DIA_DE_ABRIL = new MoodSoundAmbience(diaDeAbril, 6000, 8, 2.0D);

    public static BackgroundMusicSelector createGameMusic(SoundEvent soundEvent) {
        return new BackgroundMusicSelector(soundEvent, 12000, 24000, true);
    }
}