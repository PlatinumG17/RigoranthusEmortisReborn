package com.platinumg17.rigoranthusemortisreborn.core.registry;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.forgespi.language.ModFileScanData;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.network.PacketBuffer;
import net.minecraft.item.Item;
import net.minecraft.entity.EntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.util.HashMap;
import java.util.Map;

public class RigoranthusSoundRegistry {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
			DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RigoranthusEmortisReborn.MOD_ID);

	public static final RegistryObject<SoundEvent> CANIS_AMBIENT =
			registerSoundEvent("canis_ambient");
	public static final RegistryObject<SoundEvent> CANIS_DEATH =
			registerSoundEvent("canis_death");
	public static final RegistryObject<SoundEvent> CANIS_HURT =
			registerSoundEvent("canis_hurt");
	public static final RegistryObject<SoundEvent> CANIS_HUFF =
			registerSoundEvent("canis_huff");

	public static final RegistryObject<SoundEvent> FORGOTTEN_RECORD =
			registerSoundEvent("forgotten_record");
	public static final RegistryObject<SoundEvent> DWELLER_AMBIENT =
			registerSoundEvent("dweller_ambient");
	public static final RegistryObject<SoundEvent> DWELLER_DEATH =
			registerSoundEvent("dweller_death");
	public static final RegistryObject<SoundEvent> DWELLER_HURT =
			registerSoundEvent("dweller_hurt");


	public static final RegistryObject<SoundEvent> UNDEAD_STEP =
			registerSoundEvent("undead_step");


	public static final RegistryObject<SoundEvent> NECRAW_AMBIENT =
			registerSoundEvent("necraw_ambient");
	public static final RegistryObject<SoundEvent> NECRAW_DEATH =
			registerSoundEvent("necraw_death");
	public static final RegistryObject<SoundEvent> NECRAW_HURT =
			registerSoundEvent("necraw_hurt");

	public static final RegistryObject<SoundEvent> CADAVER_AMBIENT =
			registerSoundEvent("cadaver_ambient");
	public static final RegistryObject<SoundEvent> CADAVER_DEATH =
			registerSoundEvent("cadaver_death");
	public static final RegistryObject<SoundEvent> CADAVER_HURT =
			registerSoundEvent("cadaver_hurt");


	private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
		return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, name)));
	}

	public static void register(IEventBus bus) {
		SOUND_EVENTS.register(bus);
	}
}
