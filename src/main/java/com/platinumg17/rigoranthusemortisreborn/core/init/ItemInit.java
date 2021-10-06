package com.platinumg17.rigoranthusemortisreborn.core.init;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusItemGroup;

import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.fluid.CadaverousIchorFluid;
import com.platinumg17.rigoranthusemortisreborn.items.CrushingHammerItem;
import com.platinumg17.rigoranthusemortisreborn.items.RigoranthusArmorMaterial;
import com.platinumg17.rigoranthusemortisreborn.items.RigoranthusItemTier;
import com.platinumg17.rigoranthusemortisreborn.items.RigoranthusSpawnEgg;
import com.platinumg17.rigoranthusemortisreborn.items.ingots.*;
import com.platinumg17.rigoranthusemortisreborn.items.weapons.BoneBow;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RigoranthusEmortisReborn.MOD_ID);

	public static final RegistryObject<Item> BOTTLE_OF_ICHOR = ITEMS.register("bottle_of_ichor",
			() -> new GlassBottleItem(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(16)));

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

	public static final RegistryObject<Item> BONE_ARROW = ITEMS.register("bone_arrow",
			() -> new ArrowItem(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> BONE_BOW = ITEMS.register("bone_bow",
			() -> new BoneBow(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)));

	public static final RegistryObject<Item> BONE_SPEAR = ITEMS.register("bone_spear",
			() -> new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)));


	public static final RegistryObject<Item> DWELLER_FLESH = ITEMS.register("dweller_flesh",
			() -> new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<Item> DWELLER_THORAX = ITEMS.register("dweller_thorax",
			() -> new ArmorItem(RigoranthusArmorMaterial.DWELLER, EquipmentSlotType.CHEST,
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

	public static final RegistryObject<Item> JESSIC_SIGN = ITEMS.register("jessic_sign",
			() -> new SignItem(new Item.Properties().stacksTo(16).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP),
					Registration.JESSIC_SIGN.get(), Registration.JESSIC_WALL_SIGN.get()));

	public static final RegistryObject<Item> AZULOREAL_SIGN = ITEMS.register("azuloreal_sign",
			() -> new SignItem(new Item.Properties().stacksTo(16).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP),
					Registration.AZULOREAL_SIGN.get(), Registration.AZULOREAL_WALL_SIGN.get()));

	public static final RegistryObject<RigoranthusSpawnEgg> CANIS_CHORDATA_SPAWN_EGG = ITEMS.register("canis_chordata_spawn_egg",
			() -> new RigoranthusSpawnEgg(RigoranthusEntityTypes.CANIS_CHORDATA, 0x999999, 0xffffff,
					new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

	public static final RegistryObject<RigoranthusSpawnEgg> SUNDERED_CADAVER_SPAWN_EGG = ITEMS.register("sundered_cadaver_spawn_egg",
			() -> new RigoranthusSpawnEgg(RigoranthusEntityTypes.SUNDERED_CADAVER, 0x7e0000, 0x2986cc,
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