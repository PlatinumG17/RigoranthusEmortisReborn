package com.platinumg17.rigoranthusemortisreborn.core.registry;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RigoranthusSoundRegistry {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
			DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EmortisConstants.MOD_ID);

	public static final RegistryObject<SoundEvent> RESOLVE_SPELL =
			registerSoundEvent("resolve_spell");
	public static final RegistryObject<SoundEvent> CAST_SPELL =
			registerSoundEvent("cast_spell");
	public static final RegistryObject<SoundEvent> SWORD_SWING =
			registerSoundEvent("sword_swing");
	public static final RegistryObject<SoundEvent> SHOOT_BOW =
			registerSoundEvent("shoot_bow");
	public static final RegistryObject<SoundEvent> FIREBALL =
			registerSoundEvent("fireball");
	public static final RegistryObject<SoundEvent> ITEM_GRIMOIRE_USE =
			registerSoundEvent("grimoire_use");
	public static final RegistryObject<SoundEvent> DESPERATE_CRIES =
			registerSoundEvent("desperate_cries");
	public static final RegistryObject<SoundEvent> RAZORTOOTH_FRISBEE_HIT =
			registerSoundEvent("razortooth_frisbee_hit");

	public static final RegistryObject<SoundEvent> WHISTLE_LONG =
			registerSoundEvent("whistle_long");
	public static final RegistryObject<SoundEvent> WHISTLE_SHORT =
			registerSoundEvent("whistle_short");

	public static final RegistryObject<SoundEvent> UN_DIA_DE_ABRIL =
			registerSoundEvent("un_dia_de_abril");
	public static final RegistryObject<SoundEvent> CALM_RIGHT =
			registerSoundEvent("calm_right");
	public static final RegistryObject<SoundEvent> LEVEL_UP =
			registerSoundEvent("level_up");
	public static final RegistryObject<SoundEvent> NEON_LIGHTS =
			registerSoundEvent("neon_lights");

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
		return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, name)));
	}

	public static void register(IEventBus modEventBus) {
		SOUND_EVENTS.register(modEventBus);
	}
}