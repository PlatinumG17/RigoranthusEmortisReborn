package com.platinumg17.rigoranthusemortisreborn.core.init;

import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.blocks.custom.DecorativeOrStorageBlocks;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusItemGroup;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.core.init.fluid.CadaverousIchorFluid;
import com.platinumg17.rigoranthusemortisreborn.items.armor.RigoranthusArmorMaterial;
import com.platinumg17.rigoranthusemortisreborn.items.RigoranthusSpawnEgg;
import com.platinumg17.rigoranthusemortisreborn.items.armor.armorsets.DwellerThoraxArmor;
import com.platinumg17.rigoranthusemortisreborn.items.ingots.*;
import com.platinumg17.rigoranthusemortisreborn.items.weapons.BoneBow;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RigoranthusEmortisReborn.MOD_ID);
	public static final ItemSubRegistryHelper HELPER = RigoranthusEmortisReborn.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> BOTTLE_OF_ICHOR = ITEMS.register("bottle_of_ichor",
			() -> new GlassBottleItem(//() -> CadaverousIchorFluid.CADAVEROUS_ICHOR_FLUID.get(),
					new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(16)));

	public static final RegistryObject<Item> BUCKET_OF_CADAVEROUS_ICHOR = ITEMS.register("bucket_of_cadaverous_ichor",
			() -> new BucketItem(() -> CadaverousIchorFluid.CADAVEROUS_ICHOR_FLUID.get(),
					new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)));

	public static final RegistryObject<Item> PACT_OF_SERVITUDE = ITEMS.register("pact_of_servitude",
			() -> new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(16)));

	public static final RegistryObject<Item> PACT_OF_MYRMIDON = ITEMS.register("pact_of_myrmidon",
			() -> new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)));

	public static final RegistryObject<Item> ROGUE_PACT_OF_MYRMIDON = ITEMS.register("rogue_pact_of_myrmidon",
			() -> new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)));

	public static final RegistryObject<Item> PACT_OF_PURTURBATION = ITEMS.register("pact_of_purturbation",
			() -> new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)));

	public static final RegistryObject<Item> BONE_FRAGMENT = ITEMS.register("bone_fragment",
			() -> new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> BONE_BOW = ITEMS.register("bone_bow",
			() -> new BoneBow(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1).durability(Config.bone_bow_durability.get())));

	public static final RegistryObject<Item> DWELLER_FLESH = ITEMS.register("dweller_flesh",
			() -> new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> DWELLER_THORAX = ITEMS.register("dweller_thorax",
			() -> new DwellerThoraxArmor(RigoranthusArmorMaterial.DWELLER, EquipmentSlotType.CHEST,
					new Item.Properties().fireResistant().rarity(Rarity.RARE).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> APOGEAN_NETHERITE_INGOT = ITEMS.register("apogean_netherite_ingot",
			() -> new ApogeanIngotItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> AQUEOUS_NETHERITE_INGOT = ITEMS.register("aqueous_netherite_ingot",
			() -> new AqueousIngotItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> ATROPHYING_NETHERITE_INGOT = ITEMS.register("atrophying_netherite_ingot",
			() -> new AtrophyingIngotItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> INCORPOREAL_NETHERITE_INGOT = ITEMS.register("incorporeal_netherite_ingot",
			() -> new IncorporealIngotItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> INFERNAL_NETHERITE_INGOT = ITEMS.register("infernal_netherite_ingot",
			() -> new InfernalIngotItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> OPULENT_NETHERITE_INGOT = ITEMS.register("opulent_netherite_ingot",
			() -> new OpulentIngotItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> PERNICIOUS_NETHERITE_INGOT = ITEMS.register("pernicious_netherite_ingot",
			() -> new PerniciousIngotItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> PHANTASMAL_NETHERITE_INGOT = ITEMS.register("phantasmal_netherite_ingot",
			() -> new PhantasmalIngotItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> REMEX_NETHERITE_INGOT = ITEMS.register("remex_netherite_ingot",
			() -> new RemexIngotItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> FORGOTTEN_RECORD = ITEMS.register("forgotten_record",
			() -> new MusicDiscItem(1, RigoranthusSoundRegistry.FORGOTTEN_RECORD, (new Item.Properties())
					.stacksTo(1).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).fireResistant().rarity(Rarity.RARE)));

	public static final RegistryObject<Item> MUSIC_DISK_KICKSTART = ITEMS.register("music_disk_kickstart",
			() -> new MusicDiscItem(2, RigoranthusSoundRegistry.LEVEL_UP, (new Item.Properties())
					.stacksTo(1).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).fireResistant().rarity(Rarity.RARE)));

	public static final RegistryObject<Item> MUSIC_DISK_NEON_LIGHTS = ITEMS.register("music_disk_neon_lights",
			() -> new MusicDiscItem(3, RigoranthusSoundRegistry.NEON_LIGHTS, (new Item.Properties())
					.stacksTo(1).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).fireResistant().rarity(Rarity.RARE)));

	public static final RegistryObject<Item> JESSIC_BOAT = HELPER.createBoatItem("jessic", DecorativeOrStorageBlocks.JESSIC_PLANKS);
	public static final RegistryObject<Item> AZULOREAL_BOAT = HELPER.createBoatItem("azuloreal", DecorativeOrStorageBlocks.AZULOREAL_PLANKS);

	public static final RegistryObject<RigoranthusSpawnEgg> CANIS_CHORDATA_SPAWN_EGG = ITEMS.register("canis_chordata_spawn_egg",
			() -> new RigoranthusSpawnEgg(RigoranthusEntityTypes.CANIS_CHORDATA, 0x999999, 0xffffff,
					new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<RigoranthusSpawnEgg> SUNDERED_CADAVER_SPAWN_EGG = ITEMS.register("sundered_cadaver_spawn_egg",
			() -> new RigoranthusSpawnEgg(RigoranthusEntityTypes.SUNDERED_CADAVER, -6684673, -39322,
					new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<RigoranthusSpawnEgg> NECRAW_FASCII_SPAWN_EGG = ITEMS.register("necraw_fascii_spawn_egg",
			() -> new RigoranthusSpawnEgg(RigoranthusEntityTypes.NECRAW_FASCII, 0x27640c, 0xffd966,
					new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<RigoranthusSpawnEgg> LANGUID_DWELLER_SPAWN_EGG = ITEMS.register("languid_dweller_spawn_egg",
			() -> new RigoranthusSpawnEgg(RigoranthusEntityTypes.LANGUID_DWELLER, 0x968d81, 0x491919,
					new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static void register(IEventBus bus) {
		ITEMS.register(bus);
	}
}