package com.platinumg17.rigoranthusemortisreborn.core.registry;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibSoundNames;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RigoranthusSoundRegistry {

	public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
			DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EmortisConstants.MOD_ID);


	//_______________________________  W E A P O N S  _______________________________//

	public static RegistryObject<SoundEvent> FIREBALL = SOUND_EVENTS.register(LibSoundNames.FIREBALL,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.FIREBALL)));
	public static RegistryObject<SoundEvent> SHOOT_BOW = SOUND_EVENTS.register(LibSoundNames.SHOOT_BOW,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.SHOOT_BOW)));
	public static RegistryObject<SoundEvent> SWORD_SWING = SOUND_EVENTS.register(LibSoundNames.SWORD_SWING,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.SWORD_SWING)));
	public static RegistryObject<SoundEvent> DESPERATE_CRIES = SOUND_EVENTS.register(LibSoundNames.DESPERATE_CRIES,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.DESPERATE_CRIES)));
	public static RegistryObject<SoundEvent> RAZORTOOTH_FRISBEE_HIT = SOUND_EVENTS.register(LibSoundNames.RAZORTOOTH_FRISBEE_HIT,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.RAZORTOOTH_FRISBEE_HIT)));
	public static RegistryObject<SoundEvent> RESOLVE_SPELL = SOUND_EVENTS.register(LibSoundNames.RESOLVE_SPELL,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.RESOLVE_SPELL)));
	public static RegistryObject<SoundEvent> CAST_SPELL = SOUND_EVENTS.register(LibSoundNames.CAST_SPELL,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.CAST_SPELL)));

	public static RegistryObject<SoundEvent> ITEM_GRIMOIRE_USE = SOUND_EVENTS.register(LibSoundNames.ITEM_GRIMOIRE_USE,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.ITEM_GRIMOIRE_USE)));
	public static RegistryObject<SoundEvent> WHISTLE_LONG = SOUND_EVENTS.register(LibSoundNames.WHISTLE_LONG,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.WHISTLE_LONG)));
	public static RegistryObject<SoundEvent> WHISTLE_SHORT = SOUND_EVENTS.register(LibSoundNames.WHISTLE_SHORT,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.WHISTLE_SHORT)));


	//_______________________________  M U S I C  _______________________________//

	public static RegistryObject<SoundEvent> NEON_LIGHTS = SOUND_EVENTS.register(LibSoundNames.NEON_LIGHTS,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.NEON_LIGHTS)));
//	public static RegistryObject<SoundEvent> UN_DIA_DE_ABRIL = SOUND_EVENTS.register(LibSoundNames.UN_DIA_DE_ABRIL,
//			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.UN_DIA_DE_ABRIL)));
//	public static RegistryObject<SoundEvent> CALM_RIGHT = SOUND_EVENTS.register(LibSoundNames.CALM_RIGHT,
//			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.CALM_RIGHT)));
	public static RegistryObject<SoundEvent> LEVEL_UP = SOUND_EVENTS.register(LibSoundNames.LEVEL_UP,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.LEVEL_UP)));
	public static RegistryObject<SoundEvent> FORGOTTEN_RECORD = SOUND_EVENTS.register(LibSoundNames.FORGOTTEN_RECORD,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.FORGOTTEN_RECORD)));


	//_______________________________  M O B S  _______________________________//

	public static RegistryObject<SoundEvent> UNDEAD_STEP = SOUND_EVENTS.register(LibSoundNames.UNDEAD_STEP,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.UNDEAD_STEP)));

	public static RegistryObject<SoundEvent> NECRAW_AMBIENT = SOUND_EVENTS.register(LibSoundNames.NECRAW_AMBIENT,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.NECRAW_AMBIENT)));
	public static RegistryObject<SoundEvent> NECRAW_DEATH = SOUND_EVENTS.register(LibSoundNames.NECRAW_DEATH,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.NECRAW_DEATH)));
	public static RegistryObject<SoundEvent> NECRAW_HURT = SOUND_EVENTS.register(LibSoundNames.NECRAW_HURT,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.NECRAW_HURT)));

	public static RegistryObject<SoundEvent> CADAVER_AMBIENT = SOUND_EVENTS.register(LibSoundNames.CADAVER_AMBIENT,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.CADAVER_AMBIENT)));
	public static RegistryObject<SoundEvent> CADAVER_DEATH = SOUND_EVENTS.register(LibSoundNames.CADAVER_DEATH,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.CADAVER_DEATH)));
	public static RegistryObject<SoundEvent> CADAVER_HURT = SOUND_EVENTS.register(LibSoundNames.CADAVER_HURT,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.CADAVER_HURT)));

	public static RegistryObject<SoundEvent> CANIS_AMBIENT = SOUND_EVENTS.register(LibSoundNames.CANIS_AMBIENT,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.CANIS_AMBIENT)));
	public static RegistryObject<SoundEvent> CANIS_DEATH = SOUND_EVENTS.register(LibSoundNames.CANIS_DEATH,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.CANIS_DEATH)));
	public static RegistryObject<SoundEvent> CANIS_HURT = SOUND_EVENTS.register(LibSoundNames.CANIS_HURT,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.CANIS_HURT)));
	public static RegistryObject<SoundEvent> CANIS_HUFF = SOUND_EVENTS.register(LibSoundNames.CANIS_HUFF,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.CANIS_HUFF)));

	public static RegistryObject<SoundEvent> DWELLER_AMBIENT = SOUND_EVENTS.register(LibSoundNames.DWELLER_AMBIENT,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.DWELLER_AMBIENT)));
	public static RegistryObject<SoundEvent> DWELLER_DEATH = SOUND_EVENTS.register(LibSoundNames.DWELLER_DEATH,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.DWELLER_DEATH)));
	public static RegistryObject<SoundEvent> DWELLER_HURT = SOUND_EVENTS.register(LibSoundNames.DWELLER_HURT,
			() -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, LibSoundNames.DWELLER_HURT)));
}














//	private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
//		return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(EmortisConstants.MOD_ID, name)));
//	}
//
//	public static void register(IEventBus modEventBus) {
//		SOUND_EVENTS.register(modEventBus);
//	}
//	public static final RegistryObject<SoundEvent> RESOLVE_SPELL =
//			registerSoundEvent("resolve_spell");
//	public static final RegistryObject<SoundEvent> CAST_SPELL =
//			registerSoundEvent("cast_spell");
//	public static final RegistryObject<SoundEvent> SWORD_SWING =
//			registerSoundEvent("sword_swing");
//	public static final RegistryObject<SoundEvent> SHOOT_BOW =
//			registerSoundEvent("shoot_bow");
//	public static final RegistryObject<SoundEvent> FIREBALL =
//			registerSoundEvent("fireball");
//	public static final RegistryObject<SoundEvent> ITEM_GRIMOIRE_USE =
//			registerSoundEvent("grimoire_use");
//	public static final RegistryObject<SoundEvent> DESPERATE_CRIES =
//			registerSoundEvent("desperate_cries");
//	public static final RegistryObject<SoundEvent> RAZORTOOTH_FRISBEE_HIT =
//			registerSoundEvent("razortooth_frisbee_hit");
//	public static final RegistryObject<SoundEvent> WHISTLE_LONG =
//			registerSoundEvent("whistle_long");
//	public static final RegistryObject<SoundEvent> WHISTLE_SHORT =
//			registerSoundEvent("whistle_short");
//	public static final RegistryObject<SoundEvent> UN_DIA_DE_ABRIL =
//			registerSoundEvent("un_dia_de_abril");
//	public static final RegistryObject<SoundEvent> CALM_RIGHT =
//			registerSoundEvent("calm_right");
//	public static final RegistryObject<SoundEvent> LEVEL_UP =
//			registerSoundEvent("level_up");
//	public static final RegistryObject<SoundEvent> NEON_LIGHTS =
//			registerSoundEvent("neon_lights");
//	public static final RegistryObject<SoundEvent> CANIS_AMBIENT =
//			registerSoundEvent("canis_ambient");
//	public static final RegistryObject<SoundEvent> CANIS_DEATH =
//			registerSoundEvent("canis_death");
//	public static final RegistryObject<SoundEvent> CANIS_HURT =
//			registerSoundEvent("canis_hurt");
//	public static final RegistryObject<SoundEvent> CANIS_HUFF =
//			registerSoundEvent("canis_huff");
//	public static final RegistryObject<SoundEvent> FORGOTTEN_RECORD =
//			registerSoundEvent("forgotten_record");
//	public static final RegistryObject<SoundEvent> DWELLER_AMBIENT =
//			registerSoundEvent("dweller_ambient");
//	public static final RegistryObject<SoundEvent> DWELLER_DEATH =
//			registerSoundEvent("dweller_death");
//	public static final RegistryObject<SoundEvent> DWELLER_HURT =
//			registerSoundEvent("dweller_hurt");
//	public static final RegistryObject<SoundEvent> UNDEAD_STEP =
//			registerSoundEvent("undead_step");
//	public static final RegistryObject<SoundEvent> NECRAW_AMBIENT =
//			registerSoundEvent("necraw_ambient");
//	public static final RegistryObject<SoundEvent> NECRAW_DEATH =
//			registerSoundEvent("necraw_death");
//	public static final RegistryObject<SoundEvent> NECRAW_HURT =
//			registerSoundEvent("necraw_hurt");
//	public static final RegistryObject<SoundEvent> CADAVER_AMBIENT =
//			registerSoundEvent("cadaver_ambient");
//	public static final RegistryObject<SoundEvent> CADAVER_DEATH =
//			registerSoundEvent("cadaver_death");
//	public static final RegistryObject<SoundEvent> CADAVER_HURT =
//			registerSoundEvent("cadaver_hurt");