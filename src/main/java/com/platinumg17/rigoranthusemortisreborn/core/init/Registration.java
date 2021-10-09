package com.platinumg17.rigoranthusemortisreborn.core.init;

import com.platinumg17.rigoranthusemortisreborn.blocks.custom.RigoranthusStandingSignBlock;
import com.platinumg17.rigoranthusemortisreborn.blocks.custom.RigoranthusWallSignBlock;
import com.platinumg17.rigoranthusemortisreborn.blocks.custom.RigoranthusWoodTypes;
import com.platinumg17.rigoranthusemortisreborn.items.*;
import com.platinumg17.rigoranthusemortisreborn.items.smeltery.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import com.platinumg17.rigoranthusemortisreborn.blocks.BlockMasterfulSmeltery;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.container.MasterfulSmelteryContainer;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusItemGroup;
import com.platinumg17.rigoranthusemortisreborn.tileentity.MasterfulSmelteryTile;

import static com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn.MOD_ID;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MOD_ID);
    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static final RegistryObject<BlockMasterfulSmeltery> MASTERFUL_SMELTERY = BLOCKS.register(BlockMasterfulSmeltery.MASTERFUL_SMELTERY, () -> new BlockMasterfulSmeltery(AbstractBlock.Properties.of(Material.HEAVY_METAL).noCollission().copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Item> MASTERFUL_SMELTERY_ITEM = ITEMS.register(BlockMasterfulSmeltery.MASTERFUL_SMELTERY, () -> new BlockItem(MASTERFUL_SMELTERY.get(), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)));
    public static final RegistryObject<TileEntityType<MasterfulSmelteryTile>> MASTERFUL_SMELTERY_TILE = TILES.register(BlockMasterfulSmeltery.MASTERFUL_SMELTERY, () -> TileEntityType.Builder.of(MasterfulSmelteryTile::new, MASTERFUL_SMELTERY.get()).build(null));
    public static final RegistryObject<ContainerType<MasterfulSmelteryContainer>> MASTERFUL_SMELTERY_CONTAINER = CONTAINERS.register(BlockMasterfulSmeltery.MASTERFUL_SMELTERY, () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntity().level;
        return new MasterfulSmelteryContainer(windowId, world, pos, inv, inv.player);}));
    public static final RegistryObject<Block> JESSIC_SIGN = BLOCKS.register("jessic_sign",
            () -> new RigoranthusStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD), RigoranthusWoodTypes.JESSIC));

    public static final RegistryObject<Block> JESSIC_WALL_SIGN = BLOCKS.register("jessic_wall_sign",
            () -> new RigoranthusWallSignBlock(AbstractBlock.Properties.of(Material.WOOD), RigoranthusWoodTypes.JESSIC));

    public static final RegistryObject<Block> AZULOREAL_SIGN = BLOCKS.register("azuloreal_sign",
            () -> new RigoranthusStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD), RigoranthusWoodTypes.AZULOREAL));

    public static final RegistryObject<Block> AZULOREAL_WALL_SIGN = BLOCKS.register("azuloreal_wall_sign",
            () -> new RigoranthusWallSignBlock(AbstractBlock.Properties.of(Material.WOOD), RigoranthusWoodTypes.AZULOREAL));

    // public static final RegistryObject<Block> POTTED_JESSIC_SAPLING = BLOCKS.register("potted_jessic_sapling", () -> new FlowerPotBlock(JESSIC_SAPLING, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    // public static final RegistryObject<Block> POTTED_AZULOREAL_SAPLING = BLOCKS.register("potted_azuloreal_sapling", () -> new FlowerPotBlock(AZULOREAL_SAPLING, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));

    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
    //    if (Config.enableNewWoodTypes.get())
    //    {
    //        event.getRegistry().register();
    //    }
    }

    public static final RegistryObject<ItemAugmentBlasting> BLASTING_AUGMENT = ITEMS.register("augment_blasting", () -> new ItemAugmentBlasting(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(16)));
    public static final RegistryObject<ItemAugmentSmoking> SMOKING_AUGMENT = ITEMS.register("augment_smoking", () -> new ItemAugmentSmoking(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(16)));
    public static final RegistryObject<ItemAugmentSpeed> SPEED_AUGMENT = ITEMS.register("augment_speed", () -> new ItemAugmentSpeed(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(16)));
    public static final RegistryObject<ItemAugmentFuel> FUEL_AUGMENT = ITEMS.register("augment_fuel", () -> new ItemAugmentFuel(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(16)));
    public static final RegistryObject<ItemSmelteryCopy> ITEM_COPY = ITEMS.register("item_copy", () -> new ItemSmelteryCopy(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(16)));

    public static final Item BONE_SPEAR = new SwordItem(RigoranthusItemTier.BONE, Config.bone_spear_damage.get(), Config.bone_spear_speed.get().floatValue(), new Item.Properties().rarity(Rarity.COMMON).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("bone_spear");


//    public static Item APOGEAN_NETHERITE_HELMET = new RigoranthusArmorItem(RigoranthusArmorMaterial.APOGEAN_NETHERITE, EquipmentSlotType.HEAD, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("apogean_netherite_helmet");


    public static final Item APOGEAN_SWORD = new SwordItem(RigoranthusItemTier.APOGEAN, Config.apogean_sword_damage.get(), Config.apogean_sword_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("apogean_sword");
    public static final Item APOGEAN_AXE = new AxeItem(RigoranthusItemTier.APOGEAN, Config.apogean_axe_damage.get(), Config.apogean_axe_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("apogean_axe");
    public static final Item APOGEAN_NETHERITE_HELMET = new ArmorItem(RigoranthusArmorMaterial.APOGEAN_NETHERITE, EquipmentSlotType.HEAD, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("apogean_netherite_helmet");
    public static final Item APOGEAN_NETHERITE_CHESTPLATE = new ArmorItem(RigoranthusArmorMaterial.APOGEAN_NETHERITE, EquipmentSlotType.CHEST, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("apogean_netherite_chestplate");
    public static final Item APOGEAN_NETHERITE_LEGGINGS = new ArmorItem(RigoranthusArmorMaterial.APOGEAN_NETHERITE, EquipmentSlotType.LEGS, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("apogean_netherite_leggings");
    public static final Item APOGEAN_NETHERITE_BOOTS = new ArmorItem(RigoranthusArmorMaterial.APOGEAN_NETHERITE, EquipmentSlotType.FEET, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("apogean_netherite_boots");
    public static final Item AQUEOUS_SWORD = new SwordItem(RigoranthusItemTier.AQUEOUS, Config.aqueous_sword_damage.get(), Config.aqueous_sword_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("aqueous_sword");
    public static final Item AQUEOUS_AXE = new AxeItem(RigoranthusItemTier.AQUEOUS, Config.aqueous_axe_damage.get(), Config.aqueous_axe_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("aqueous_axe");
    public static final Item AQUEOUS_NETHERITE_HELMET = new ArmorItem(RigoranthusArmorMaterial.AQUEOUS_NETHERITE, EquipmentSlotType.HEAD, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("aqueous_netherite_helmet");
    public static final Item AQUEOUS_NETHERITE_CHESTPLATE = new ArmorItem(RigoranthusArmorMaterial.AQUEOUS_NETHERITE, EquipmentSlotType.CHEST, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("aqueous_netherite_chestplate");
    public static final Item AQUEOUS_NETHERITE_LEGGINGS = new ArmorItem(RigoranthusArmorMaterial.AQUEOUS_NETHERITE, EquipmentSlotType.LEGS, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("aqueous_netherite_leggings");
    public static final Item AQUEOUS_NETHERITE_BOOTS = new ArmorItem(RigoranthusArmorMaterial.AQUEOUS_NETHERITE, EquipmentSlotType.FEET, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("aqueous_netherite_boots");
    public static final Item ATROPHYING_SWORD = new SwordItem(RigoranthusItemTier.ATROPHYING, Config.atrophying_sword_damage.get(), Config.atrophying_sword_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("atrophying_sword");
    public static final Item ATROPHYING_AXE = new AxeItem(RigoranthusItemTier.ATROPHYING, Config.atrophying_axe_damage.get(), Config.atrophying_axe_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("atrophying_axe");
    public static final Item ATROPHYING_NETHERITE_HELMET = new ArmorItem(RigoranthusArmorMaterial.ATROPHYING_NETHERITE, EquipmentSlotType.HEAD, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("atrophying_netherite_helmet");
    public static final Item ATROPHYING_NETHERITE_CHESTPLATE = new ArmorItem(RigoranthusArmorMaterial.ATROPHYING_NETHERITE, EquipmentSlotType.CHEST,	new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("atrophying_netherite_chestplate");
    public static final Item ATROPHYING_NETHERITE_LEGGINGS = new ArmorItem(RigoranthusArmorMaterial.ATROPHYING_NETHERITE, EquipmentSlotType.LEGS, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("atrophying_netherite_leggings");
    public static final Item ATROPHYING_NETHERITE_BOOTS = new ArmorItem(RigoranthusArmorMaterial.ATROPHYING_NETHERITE, EquipmentSlotType.FEET, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("atrophying_netherite_boots");
    public static final Item INCORPOREAL_SWORD = new SwordItem(RigoranthusItemTier.INCORPOREAL, Config.incorporeal_sword_damage.get(), Config.incorporeal_sword_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("incorporeal_sword");
    public static final Item INCORPOREAL_AXE = new AxeItem(RigoranthusItemTier.INCORPOREAL, Config.incorporeal_axe_damage.get(), Config.incorporeal_axe_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("incorporeal_axe");
    public static final Item INCORPOREAL_NETHERITE_HELMET = new ArmorItem(RigoranthusArmorMaterial.INCORPOREAL_NETHERITE, EquipmentSlotType.HEAD, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("incorporeal_netherite_helmet");
    public static final Item INCORPOREAL_NETHERITE_CHESTPLATE = new ArmorItem(RigoranthusArmorMaterial.INCORPOREAL_NETHERITE, EquipmentSlotType.CHEST, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("incorporeal_netherite_chestplate");
    public static final Item INCORPOREAL_NETHERITE_LEGGINGS = new ArmorItem(RigoranthusArmorMaterial.INCORPOREAL_NETHERITE, EquipmentSlotType.LEGS, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("incorporeal_netherite_leggings");
    public static final Item INCORPOREAL_NETHERITE_BOOTS = new ArmorItem(RigoranthusArmorMaterial.INCORPOREAL_NETHERITE, EquipmentSlotType.FEET, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("incorporeal_netherite_boots");
    public static final Item INFERNAL_SWORD = new SwordItem(RigoranthusItemTier.INFERNAL, Config.infernal_sword_damage.get(), Config.infernal_sword_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("infernal_sword");
    public static final Item INFERNAL_AXE = new AxeItem(RigoranthusItemTier.INFERNAL, Config.infernal_axe_damage.get(), Config.infernal_axe_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("infernal_axe");
    public static final Item INFERNAL_NETHERITE_HELMET = new ArmorItem(RigoranthusArmorMaterial.INFERNAL_NETHERITE, EquipmentSlotType.HEAD, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("infernal_netherite_helmet");
    public static final Item INFERNAL_NETHERITE_CHESTPLATE = new ArmorItem(RigoranthusArmorMaterial.INFERNAL_NETHERITE, EquipmentSlotType.CHEST, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("infernal_netherite_chestplate");
    public static final Item INFERNAL_NETHERITE_LEGGINGS = new ArmorItem(RigoranthusArmorMaterial.INFERNAL_NETHERITE, EquipmentSlotType.LEGS, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("infernal_netherite_leggings");
    public static final Item INFERNAL_NETHERITE_BOOTS = new ArmorItem(RigoranthusArmorMaterial.INFERNAL_NETHERITE, EquipmentSlotType.FEET, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("infernal_netherite_boots");
    public static final Item OPULENT_SWORD = new SwordItem(RigoranthusItemTier.OPULENT, Config.opulent_sword_damage.get(), Config.opulent_sword_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("opulent_sword");
    public static final Item OPULENT_AXE = new AxeItem(RigoranthusItemTier.OPULENT, Config.opulent_axe_damage.get(), Config.opulent_axe_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("opulent_axe");
    public static final Item OPULENT_NETHERITE_HELMET = new ArmorItem(RigoranthusArmorMaterial.OPULENT_NETHERITE, EquipmentSlotType.HEAD, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("opulent_netherite_helmet");
    public static final Item OPULENT_NETHERITE_CHESTPLATE = new ArmorItem(RigoranthusArmorMaterial.OPULENT_NETHERITE, EquipmentSlotType.CHEST, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("opulent_netherite_chestplate");
    public static final Item OPULENT_NETHERITE_LEGGINGS = new ArmorItem(RigoranthusArmorMaterial.OPULENT_NETHERITE, EquipmentSlotType.LEGS, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("opulent_netherite_leggings");
    public static final Item OPULENT_NETHERITE_BOOTS = new ArmorItem(RigoranthusArmorMaterial.OPULENT_NETHERITE, EquipmentSlotType.FEET, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("opulent_netherite_boots");
    public static final Item PERNICIOUS_SWORD = new SwordItem(RigoranthusItemTier.PERNICIOUS, Config.pernicious_sword_damage.get(), Config.pernicious_sword_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("pernicious_sword");
    public static final Item PERNICIOUS_AXE = new AxeItem(RigoranthusItemTier.PERNICIOUS, Config.pernicious_axe_damage.get(), Config.pernicious_axe_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("pernicious_axe");
    public static final Item PERNICIOUS_NETHERITE_HELMET = new ArmorItem(RigoranthusArmorMaterial.PERNICIOUS_NETHERITE, EquipmentSlotType.HEAD, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("pernicious_netherite_helmet");
    public static final Item PERNICIOUS_NETHERITE_CHESTPLATE = new ArmorItem(RigoranthusArmorMaterial.PERNICIOUS_NETHERITE, EquipmentSlotType.CHEST, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("pernicious_netherite_chestplate");
    public static final Item PERNICIOUS_NETHERITE_LEGGINGS = new ArmorItem(RigoranthusArmorMaterial.PERNICIOUS_NETHERITE, EquipmentSlotType.LEGS, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("pernicious_netherite_leggings");
    public static final Item PERNICIOUS_NETHERITE_BOOTS = new ArmorItem(RigoranthusArmorMaterial.PERNICIOUS_NETHERITE, EquipmentSlotType.FEET, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("pernicious_netherite_boots");
    public static final Item PHANTASMAL_SWORD = new SwordItem(RigoranthusItemTier.PHANTASMAL, Config.phantasmal_sword_damage.get(), Config.phantasmal_sword_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("phantasmal_sword");
    public static final Item PHANTASMAL_AXE = new AxeItem(RigoranthusItemTier.PHANTASMAL, Config.phantasmal_axe_damage.get(), Config.phantasmal_axe_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("phantasmal_axe");
    public static final Item PHANTASMAL_NETHERITE_HELMET = new ArmorItem(RigoranthusArmorMaterial.PHANTASMAL_NETHERITE, EquipmentSlotType.HEAD, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("phantasmal_netherite_helmet");
    public static final Item PHANTASMAL_NETHERITE_CHESTPLATE = new ArmorItem(RigoranthusArmorMaterial.PHANTASMAL_NETHERITE, EquipmentSlotType.CHEST, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("phantasmal_netherite_chestplate");
    public static final Item PHANTASMAL_NETHERITE_LEGGINGS = new ArmorItem(RigoranthusArmorMaterial.PHANTASMAL_NETHERITE, EquipmentSlotType.LEGS, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("phantasmal_netherite_leggings");
    public static final Item PHANTASMAL_NETHERITE_BOOTS = new ArmorItem(RigoranthusArmorMaterial.PHANTASMAL_NETHERITE, EquipmentSlotType.FEET, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("phantasmal_netherite_boots");
    public static final Item REMEX_SWORD = new SwordItem(RigoranthusItemTier.REMEX, Config.remex_sword_damage.get(), Config.remex_sword_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("remex_sword");
    public static final Item REMEX_AXE = new AxeItem(RigoranthusItemTier.REMEX, Config.remex_axe_damage.get(), Config.remex_axe_speed.get().floatValue(), new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1)).setRegistryName("remex_axe");
    public static final Item REMEX_NETHERITE_HELMET = new ArmorItem(RigoranthusArmorMaterial.REMEX_NETHERITE, EquipmentSlotType.HEAD, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("remex_netherite_helmet");
    public static final Item REMEX_NETHERITE_CHESTPLATE = new ArmorItem(RigoranthusArmorMaterial.REMEX_NETHERITE, EquipmentSlotType.CHEST, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("remex_netherite_chestplate");
    public static final Item REMEX_NETHERITE_LEGGINGS = new ArmorItem(RigoranthusArmorMaterial.REMEX_NETHERITE, EquipmentSlotType.LEGS, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("remex_netherite_leggings");
    public static final Item REMEX_NETHERITE_BOOTS = new ArmorItem(RigoranthusArmorMaterial.REMEX_NETHERITE, EquipmentSlotType.FEET, new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("remex_netherite_boots");

    public static final Item STONE_CRUSHING_HAMMER = new CrushingHammerItem(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1).durability(Config.stone_hammer_durability.get())).setRegistryName("stone_crushing_hammer");
    public static final Item IRON_CRUSHING_HAMMER = new CrushingHammerItem(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1).durability(Config.iron_hammer_durability.get())).setRegistryName("iron_crushing_hammer");
    public static final Item GOLD_CRUSHING_HAMMER = new CrushingHammerItem(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1).durability(Config.gold_hammer_durability.get())).setRegistryName("gold_crushing_hammer");
    public static final Item DIAMOND_CRUSHING_HAMMER = new CrushingHammerItem(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1).durability(Config.diamond_hammer_durability.get())).setRegistryName("diamond_crushing_hammer");
    public static final Item ABYSSALITE_CRUSHING_HAMMER = new CrushingHammerItem(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1).durability(Config.abyssalite_hammer_durability.get())).setRegistryName("abyssalite_crushing_hammer");
    public static final Item IRON_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("iron_ore_fragment");
    public static final Item GOLD_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("gold_ore_fragment");

    public static final ItemSoulCoal SOUL_COAL = new ItemSoulCoal(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP));
    public static final Item DEEPSLATE_IRON_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("deepslate_iron_ore_fragment");
    public static final Item DEEPSLATE_GOLD_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("deepslate_gold_ore_fragment");
    public static final Item DEEPSLATE_COPPER_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("deepslate_copper_ore_fragment");
    public static final Item TIN_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("tin_ore_fragment");
    public static final Item ZINC_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("zinc_ore_fragment");
    public static final Item COPPER_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("copper_ore_fragment");
    public static final Item SILVER_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("silver_ore_fragment");
    public static final Item OSMIUM_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("osmium_ore_fragment");
    public static final Item NICKEL_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("nickel_ore_fragment");
    public static final Item LEAD_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("lead_ore_fragment");
    public static final Item PLATINUM_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("platinum_ore_fragment");
    public static final Item QUICKSILVER_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("quicksilver_ore_fragment");
    public static final Item URANIUM_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("uranium_ore_fragment");
    public static final Item ALUMINUM_ORE_FRAGMENT = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("aluminum_ore_fragment");

    public static final Item MUD_GLOB = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("mud_glob");
    public static final Item UNFIRED_MUD_BRICK = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("unfired_mud_brick");
    public static final Item MUD_BRICK = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("mud_brick");
    public static final Item UNFIRED_BRICK = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("unfired_brick");
    public static final Item UNFIRED_NETHER_BRICK = new Item(new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("unfired_nether_brick");

    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        if (Config.enableUnfiredBricks.get())
        {
            event.getRegistry().register(MUD_GLOB);
            event.getRegistry().register(UNFIRED_MUD_BRICK);
            event.getRegistry().register(MUD_BRICK);
            event.getRegistry().register(UNFIRED_BRICK);
            event.getRegistry().register(UNFIRED_NETHER_BRICK);
        }
        if (Config.enableNetheriteAdditions.get())
        {
            event.getRegistry().register(APOGEAN_SWORD);
            event.getRegistry().register(APOGEAN_AXE);
            event.getRegistry().register(APOGEAN_NETHERITE_HELMET);
            event.getRegistry().register(APOGEAN_NETHERITE_CHESTPLATE);
            event.getRegistry().register(APOGEAN_NETHERITE_LEGGINGS);
            event.getRegistry().register(APOGEAN_NETHERITE_BOOTS);
            event.getRegistry().register(AQUEOUS_SWORD);
            event.getRegistry().register(AQUEOUS_AXE);
            event.getRegistry().register(AQUEOUS_NETHERITE_HELMET);
            event.getRegistry().register(AQUEOUS_NETHERITE_CHESTPLATE);
            event.getRegistry().register(AQUEOUS_NETHERITE_LEGGINGS);
            event.getRegistry().register(AQUEOUS_NETHERITE_BOOTS);
            event.getRegistry().register(ATROPHYING_SWORD);
            event.getRegistry().register(ATROPHYING_AXE);
            event.getRegistry().register(ATROPHYING_NETHERITE_HELMET);
            event.getRegistry().register(ATROPHYING_NETHERITE_CHESTPLATE);
            event.getRegistry().register(ATROPHYING_NETHERITE_LEGGINGS);
            event.getRegistry().register(ATROPHYING_NETHERITE_BOOTS);
            event.getRegistry().register(INCORPOREAL_SWORD);
            event.getRegistry().register(INCORPOREAL_AXE);
            event.getRegistry().register(INCORPOREAL_NETHERITE_HELMET);
            event.getRegistry().register(INCORPOREAL_NETHERITE_CHESTPLATE);
            event.getRegistry().register(INCORPOREAL_NETHERITE_LEGGINGS);
            event.getRegistry().register(INCORPOREAL_NETHERITE_BOOTS);
            event.getRegistry().register(INFERNAL_SWORD);
            event.getRegistry().register(INFERNAL_AXE);
            event.getRegistry().register(INFERNAL_NETHERITE_HELMET);
            event.getRegistry().register(INFERNAL_NETHERITE_CHESTPLATE);
            event.getRegistry().register(INFERNAL_NETHERITE_LEGGINGS);
            event.getRegistry().register(INFERNAL_NETHERITE_BOOTS);
            event.getRegistry().register(OPULENT_SWORD);
            event.getRegistry().register(OPULENT_AXE);
            event.getRegistry().register(OPULENT_NETHERITE_HELMET);
            event.getRegistry().register(OPULENT_NETHERITE_CHESTPLATE);
            event.getRegistry().register(OPULENT_NETHERITE_LEGGINGS);
            event.getRegistry().register(OPULENT_NETHERITE_BOOTS);
            event.getRegistry().register(PERNICIOUS_SWORD);
            event.getRegistry().register(PERNICIOUS_AXE);
            event.getRegistry().register(PERNICIOUS_NETHERITE_HELMET);
            event.getRegistry().register(PERNICIOUS_NETHERITE_CHESTPLATE);
            event.getRegistry().register(PERNICIOUS_NETHERITE_LEGGINGS);
            event.getRegistry().register(PERNICIOUS_NETHERITE_BOOTS);
            event.getRegistry().register(PHANTASMAL_SWORD);
            event.getRegistry().register(PHANTASMAL_AXE);
            event.getRegistry().register(PHANTASMAL_NETHERITE_HELMET);
            event.getRegistry().register(PHANTASMAL_NETHERITE_CHESTPLATE);
            event.getRegistry().register(PHANTASMAL_NETHERITE_LEGGINGS);
            event.getRegistry().register(PHANTASMAL_NETHERITE_BOOTS);
            event.getRegistry().register(REMEX_SWORD);
            event.getRegistry().register(REMEX_AXE);
            event.getRegistry().register(REMEX_NETHERITE_HELMET);
            event.getRegistry().register(REMEX_NETHERITE_CHESTPLATE);
            event.getRegistry().register(REMEX_NETHERITE_LEGGINGS);
            event.getRegistry().register(REMEX_NETHERITE_BOOTS);
        }
        if (Config.enableSoulCoal.get()) { event.getRegistry().register(SOUL_COAL); }
        if (Config.enableHammersAndVanillaOreFragments.get())
        {
            event.getRegistry().register(STONE_CRUSHING_HAMMER);
            event.getRegistry().register(IRON_CRUSHING_HAMMER);
            event.getRegistry().register(GOLD_CRUSHING_HAMMER);
            event.getRegistry().register(DIAMOND_CRUSHING_HAMMER);
            event.getRegistry().register(ABYSSALITE_CRUSHING_HAMMER);
            event.getRegistry().register(IRON_ORE_FRAGMENT);
            event.getRegistry().register(GOLD_ORE_FRAGMENT);
        }
        if (Config.enableModdedOreFragments.get())
        {
            event.getRegistry().register(DEEPSLATE_IRON_ORE_FRAGMENT);
            event.getRegistry().register(DEEPSLATE_GOLD_ORE_FRAGMENT);
            event.getRegistry().register(DEEPSLATE_COPPER_ORE_FRAGMENT);
            event.getRegistry().register(TIN_ORE_FRAGMENT);
            event.getRegistry().register(ZINC_ORE_FRAGMENT);
            event.getRegistry().register(COPPER_ORE_FRAGMENT);
            event.getRegistry().register(SILVER_ORE_FRAGMENT);
            event.getRegistry().register(OSMIUM_ORE_FRAGMENT);
            event.getRegistry().register(NICKEL_ORE_FRAGMENT);
            event.getRegistry().register(LEAD_ORE_FRAGMENT);
            event.getRegistry().register(PLATINUM_ORE_FRAGMENT);
            event.getRegistry().register(QUICKSILVER_ORE_FRAGMENT);
            event.getRegistry().register(URANIUM_ORE_FRAGMENT);
            event.getRegistry().register(ALUMINUM_ORE_FRAGMENT);
        }
    }
}