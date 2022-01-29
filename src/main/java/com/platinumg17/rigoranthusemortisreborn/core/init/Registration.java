package com.platinumg17.rigoranthusemortisreborn.core.init;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.blocks.custom.BlockMasterfulSmeltery;
import com.platinumg17.rigoranthusemortisreborn.blocks.tileentity.container.MasterfulSmelteryContainer;
import com.platinumg17.rigoranthusemortisreborn.blocks.tileentity.smeltery.MasterfulSmelteryTile;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.items.RigoranthusItemTier;
import com.platinumg17.rigoranthusemortisreborn.items.armor.armorsets.*;
import com.platinumg17.rigoranthusemortisreborn.items.specialized.Esotericum;
import com.platinumg17.rigoranthusemortisreborn.items.specialized.smeltery.*;
import com.platinumg17.rigoranthusemortisreborn.items.tooltypes.CrushingHammerItem;
import com.platinumg17.rigoranthusemortisreborn.items.weapons.REWeaponItem;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import com.platinumg17.rigoranthusemortisreborn.world.gen.feature.OreBlockEmortis;
import com.platinumg17.rigoranthusemortisreborn.world.plants.LumiShroomBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.platinumg17.rigoranthusemortisreborn.items.tooltypes.ToolRegistry.AXE_TOOL;
import static com.platinumg17.rigoranthusemortisreborn.items.tooltypes.ToolRegistry.SWORD_TOOL;
import static net.minecraft.inventory.EquipmentSlotType.*;
import static com.platinumg17.rigoranthusemortisreborn.items.RigoranthusItemTier.*;
import static com.platinumg17.rigoranthusemortisreborn.items.armor.RigoranthusArmorMaterial.*;
import static com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry.defaultItemProperties;
import static com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn.MOD_ID;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MOD_ID);
    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static Item makeSword(RigoranthusItemTier tier, int dmg, float speed, String name) { return new REWeaponItem(new REWeaponItem.Builder(tier, dmg, speed).set(SWORD_TOOL), ARMOR_PROP).setRegistryName(name); }
    public static Item makeAxe(RigoranthusItemTier tier, int dmg, float speed, String name) { return new REWeaponItem(new REWeaponItem.Builder(tier, dmg, speed).set(AXE_TOOL), AXE_PROP).setRegistryName(name); }
    public static Item makeSpear(RigoranthusItemTier tier, int dmg, float speed, String name) { return new REWeaponItem(new REWeaponItem.Builder(tier, dmg, speed).set(SWORD_TOOL), BONE_PROP).setRegistryName(name); }
    public static Item makeHammer(int durability, String name) { return new CrushingHammerItem(NO_STACK_PROP.durability(durability)).setRegistryName(name); }
    public static Item makeItem(String name) { return new Item(defaultItemProperties()).setRegistryName(name); }

    public static final Item.Properties ARMOR_PROP = new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC).tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP);
    public static final Item.Properties AXE_PROP = new Item.Properties().stacksTo(1).fireResistant().addToolType(ToolType.AXE, 4).rarity(Rarity.EPIC).tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP);
    public static final Item.Properties SMALL_STACK_PROP = new Item.Properties().stacksTo(16).tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP);
    public static final Item.Properties NO_STACK_PROP = new Item.Properties().stacksTo(16).tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP);
    public static final Item.Properties BONE_PROP = new Item.Properties().rarity(Rarity.UNCOMMON).tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).stacksTo(1);
    public static final Block.Properties LUMI_PROP = AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_YELLOW).noCollission().randomTicks().strength(0).sound(SoundType.GRASS).lightLevel(state -> 11);
    public static final Block.Properties SMELTERY_PROP = AbstractBlock.Properties.of(Material.HEAVY_METAL).noCollission().copy(Blocks.IRON_BLOCK);
    
    public static final RegistryObject<Block> MASTERFUL_SMELTERY = BLOCKS.register(BlockMasterfulSmeltery.MASTERFUL_SMELTERY, () -> new BlockMasterfulSmeltery(SMELTERY_PROP));
    public static final RegistryObject<Item> MASTERFUL_SMELTERY_ITEM = ITEMS.register(BlockMasterfulSmeltery.MASTERFUL_SMELTERY, () -> new BlockItem(MASTERFUL_SMELTERY.get(), NO_STACK_PROP));
    public static final RegistryObject<TileEntityType<MasterfulSmelteryTile>> MASTERFUL_SMELTERY_TILE = TILES.register(BlockMasterfulSmeltery.MASTERFUL_SMELTERY, () -> TileEntityType.Builder.of(MasterfulSmelteryTile::new, MASTERFUL_SMELTERY.get()).build(null));
    public static final RegistryObject<ContainerType<MasterfulSmelteryContainer>> MASTERFUL_SMELTERY_CONTAINER = CONTAINERS.register(BlockMasterfulSmeltery.MASTERFUL_SMELTERY, () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntity().level;
        return new MasterfulSmelteryContainer(windowId, world, pos, inv, inv.player);
    }));

    public static final RegistryObject<Block> RECONDITE_ORE =                BLOCKS.register("recondite_ore", OreBlockEmortis::new);
    public static final RegistryObject<Item> POWDERED_ESOTERICUM =           ITEMS.register("powdered_esotericum", Esotericum::new);
    public static final RegistryObject<Item> RECONDITE_ORE_ITEM =            ITEMS.register("recondite_ore", () -> new BlockItem(RECONDITE_ORE.get(), defaultItemProperties()));
    public static final RegistryObject<ItemAugmentBlast> BLASTING_AUGMENT =  ITEMS.register("augment_blasting", () -> new ItemAugmentBlast(SMALL_STACK_PROP));
    public static final RegistryObject<ItemAugmentSmoke> SMOKING_AUGMENT =   ITEMS.register("augment_smoking", () -> new ItemAugmentSmoke(SMALL_STACK_PROP));
    public static final RegistryObject<ItemAugmentSpeed> SPEED_AUGMENT =     ITEMS.register("augment_speed", () -> new ItemAugmentSpeed(SMALL_STACK_PROP));
    public static final RegistryObject<ItemAugmentFuel> FUEL_AUGMENT =       ITEMS.register("augment_fuel", () -> new ItemAugmentFuel(SMALL_STACK_PROP));
    public static final RegistryObject<ItemSmelteryCopy> ITEM_COPY =         ITEMS.register("item_copy", () -> new ItemSmelteryCopy(SMALL_STACK_PROP));
    public static final RegistryObject<Block> LUMISHROOM =                   BLOCKS.register("lumishroom", () -> new LumiShroomBlock(LUMI_PROP));

    public static final Item APOGEAN_SWORD =          makeSword(APOGEAN, Config.apogean_sword_damage.get(), Config.apogean_sword_speed.get().floatValue(), "apogean_sword");
    public static final Item APOGEAN_AXE =            makeSword(APOGEAN, Config.apogean_axe_damage.get(), Config.apogean_axe_speed.get().floatValue(),"apogean_axe");
    public static final Item APOGEAN_N_HELMET =       new ApogeanArmor(APOGEAN_NETHERITE, HEAD, ARMOR_PROP).setRegistryName("apogean_netherite_helmet");
    public static final Item APOGEAN_N_CHEST =        new ApogeanArmor(APOGEAN_NETHERITE, CHEST, ARMOR_PROP).setRegistryName("apogean_netherite_chestplate");
    public static final Item APOGEAN_N_LEGS =         new ApogeanArmor(APOGEAN_NETHERITE, LEGS, ARMOR_PROP).setRegistryName("apogean_netherite_leggings");
    public static final RegistryObject<ApogeanBootsItem> APOGEAN_N_BOOTS = ITEMS.register("apogean_netherite_boots", () -> new ApogeanBootsItem(APOGEAN_NETHERITE, ARMOR_PROP));
//    public static final Item APOGEAN_N_BOOTS = new ApogeanArmor(APOGEAN_NETHERITE, FEET, ARMOR_PROP).setRegistryName("apogean_netherite_boots");
    public static final Item AQUEOUS_SWORD =          makeSword(AQUEOUS, Config.aqueous_sword_damage.get(), Config.aqueous_sword_speed.get().floatValue(),"aqueous_sword");
    public static final Item AQUEOUS_AXE =            makeAxe(AQUEOUS, Config.aqueous_axe_damage.get(), Config.aqueous_axe_speed.get().floatValue(),"aqueous_axe");
    public static final Item AQUEOUS_N_HELMET =       new AqueousArmor(AQUEOUS_NETHERITE, HEAD, ARMOR_PROP).setRegistryName("aqueous_netherite_helmet");
    public static final Item AQUEOUS_N_CHEST =        new AqueousArmor(AQUEOUS_NETHERITE, CHEST, ARMOR_PROP).setRegistryName("aqueous_netherite_chestplate");
    public static final Item AQUEOUS_N_LEGS =         new AqueousArmor(AQUEOUS_NETHERITE, LEGS, ARMOR_PROP).setRegistryName("aqueous_netherite_leggings");
    public static final Item AQUEOUS_N_BOOTS =        new AqueousArmor(AQUEOUS_NETHERITE, FEET, ARMOR_PROP).setRegistryName("aqueous_netherite_boots");
    public static final Item ATROPHYING_SWORD =       makeSword(ATROPHYING, Config.atrophying_sword_damage.get(), Config.atrophying_sword_speed.get().floatValue(),"atrophying_sword");
    public static final Item ATROPHYING_AXE =         makeAxe(ATROPHYING, Config.atrophying_axe_damage.get(), Config.atrophying_axe_speed.get().floatValue(),"atrophying_axe");
    public static final Item ATROPHYING_N_HELMET =    new AtrophyingArmor(ATROPHYING_NETHERITE, HEAD, ARMOR_PROP).setRegistryName("atrophying_netherite_helmet");
    public static final Item ATROPHYING_N_CHEST =     new AtrophyingArmor(ATROPHYING_NETHERITE, CHEST,	ARMOR_PROP).setRegistryName("atrophying_netherite_chestplate");
    public static final Item ATROPHYING_N_LEGS =      new AtrophyingArmor(ATROPHYING_NETHERITE, LEGS, ARMOR_PROP).setRegistryName("atrophying_netherite_leggings");
    public static final Item ATROPHYING_N_BOOTS =     new AtrophyingArmor(ATROPHYING_NETHERITE, FEET, ARMOR_PROP).setRegistryName("atrophying_netherite_boots");
    public static final Item INCORPOREAL_SWORD =      makeSword(INCORPOREAL, Config.incorporeal_sword_damage.get(), Config.incorporeal_sword_speed.get().floatValue(),"incorporeal_sword");
    public static final Item INCORPOREAL_AXE =        makeAxe(INCORPOREAL, Config.incorporeal_axe_damage.get(), Config.incorporeal_axe_speed.get().floatValue(),"incorporeal_axe");
    public static final Item INCORPOREAL_N_HELMET =   new IncorporealArmor(INCORPOREAL_NETHERITE, HEAD, ARMOR_PROP).setRegistryName("incorporeal_netherite_helmet");
    public static final RegistryObject<IncorporealChestplateItem> INCORPOREAL_N_CHEST = ITEMS.register("incorporeal_netherite_chestplate", () -> new IncorporealChestplateItem(INCORPOREAL_NETHERITE, ARMOR_PROP));
    public static final Item INCORPOREAL_N_LEGS =     new IncorporealArmor(INCORPOREAL_NETHERITE, LEGS, ARMOR_PROP).setRegistryName("incorporeal_netherite_leggings");
    public static final Item INCORPOREAL_N_BOOTS =    new IncorporealArmor(INCORPOREAL_NETHERITE, FEET, ARMOR_PROP).setRegistryName("incorporeal_netherite_boots");
    public static final Item INFERNAL_SWORD =         makeSword(INFERNAL, Config.infernal_sword_damage.get(), Config.infernal_sword_speed.get().floatValue(),"infernal_sword");
    public static final Item INFERNAL_AXE =           makeAxe(INFERNAL, Config.infernal_axe_damage.get(), Config.infernal_axe_speed.get().floatValue(),"infernal_axe");
    public static final Item INFERNAL_N_HELMET =      new InfernalArmor(INFERNAL_NETHERITE, HEAD, ARMOR_PROP).setRegistryName("infernal_netherite_helmet");
    public static final Item INFERNAL_N_CHEST =       new InfernalArmor(INFERNAL_NETHERITE, CHEST, ARMOR_PROP).setRegistryName("infernal_netherite_chestplate");
    public static final Item INFERNAL_N_LEGS =        new InfernalArmor(INFERNAL_NETHERITE, LEGS, ARMOR_PROP).setRegistryName("infernal_netherite_leggings");
    public static final Item INFERNAL_N_BOOTS =       new InfernalArmor(INFERNAL_NETHERITE, FEET, ARMOR_PROP).setRegistryName("infernal_netherite_boots");
    public static final Item OPULENT_SWORD =          makeSword(OPULENT, Config.opulent_sword_damage.get(), Config.opulent_sword_speed.get().floatValue(),"opulent_sword");
    public static final Item OPULENT_AXE =            makeAxe(OPULENT, Config.opulent_axe_damage.get(), Config.opulent_axe_speed.get().floatValue(),"opulent_axe");
    public static final Item OPULENT_N_HELMET =       new OpulentArmor(OPULENT_NETHERITE, HEAD, ARMOR_PROP).setRegistryName("opulent_netherite_helmet");
    public static final Item OPULENT_N_CHEST =        new OpulentArmor(OPULENT_NETHERITE, CHEST, ARMOR_PROP).setRegistryName("opulent_netherite_chestplate");
    public static final Item OPULENT_N_LEGS =         new OpulentArmor(OPULENT_NETHERITE, LEGS, ARMOR_PROP).setRegistryName("opulent_netherite_leggings");
    public static final Item OPULENT_N_BOOTS =        new OpulentArmor(OPULENT_NETHERITE, FEET, ARMOR_PROP).setRegistryName("opulent_netherite_boots");
    public static final Item PERNICIOUS_SWORD =       makeSword(PERNICIOUS, Config.pernicious_sword_damage.get(), Config.pernicious_sword_speed.get().floatValue(),"pernicious_sword");
    public static final Item PERNICIOUS_AXE =         makeAxe(PERNICIOUS, Config.pernicious_axe_damage.get(), Config.pernicious_axe_speed.get().floatValue(),"pernicious_axe");
    public static final Item PERNICIOUS_N_HELMET =    new PerniciousArmor(PERNICIOUS_NETHERITE, HEAD, ARMOR_PROP).setRegistryName("pernicious_netherite_helmet");
    public static final Item PERNICIOUS_N_CHEST =     new PerniciousArmor(PERNICIOUS_NETHERITE, CHEST, ARMOR_PROP).setRegistryName("pernicious_netherite_chestplate");
    public static final Item PERNICIOUS_N_LEGS =      new PerniciousArmor(PERNICIOUS_NETHERITE, LEGS, ARMOR_PROP).setRegistryName("pernicious_netherite_leggings");
    public static final Item PERNICIOUS_N_BOOTS =     new PerniciousArmor(PERNICIOUS_NETHERITE, FEET, ARMOR_PROP).setRegistryName("pernicious_netherite_boots");
    public static final Item PHANTASMAL_SWORD =       makeSword(PHANTASMAL, Config.phantasmal_sword_damage.get(), Config.phantasmal_sword_speed.get().floatValue(),"phantasmal_sword");
    public static final Item PHANTASMAL_AXE =         makeAxe(PHANTASMAL, Config.phantasmal_axe_damage.get(), Config.phantasmal_axe_speed.get().floatValue(),"phantasmal_axe");
    public static final Item PHANTASMAL_N_HELMET =    new PhantasmalArmor(PHANTASMAL_NETHERITE, HEAD, ARMOR_PROP).setRegistryName("phantasmal_netherite_helmet");
    public static final Item PHANTASMAL_N_CHEST =     new PhantasmalArmor(PHANTASMAL_NETHERITE, CHEST, ARMOR_PROP).setRegistryName("phantasmal_netherite_chestplate");
    public static final Item PHANTASMAL_N_LEGS =      new PhantasmalArmor(PHANTASMAL_NETHERITE, LEGS, ARMOR_PROP).setRegistryName("phantasmal_netherite_leggings");
    public static final Item PHANTASMAL_N_BOOTS =     new PhantasmalArmor(PHANTASMAL_NETHERITE, FEET, ARMOR_PROP).setRegistryName("phantasmal_netherite_boots");
    public static final Item REMEX_SWORD =            makeSword(REMEX, Config.remex_sword_damage.get(), Config.remex_sword_speed.get().floatValue(),"remex_sword");
    public static final Item REMEX_AXE =              makeAxe(REMEX, Config.remex_axe_damage.get(), Config.remex_axe_speed.get().floatValue(),"remex_axe");
    public static final Item REMEX_N_HELMET =         new RemexArmor(REMEX_NETHERITE, HEAD, ARMOR_PROP).setRegistryName("remex_netherite_helmet");
    public static final Item REMEX_N_CHEST =          new RemexArmor(REMEX_NETHERITE, CHEST, ARMOR_PROP).setRegistryName("remex_netherite_chestplate");
    public static final Item REMEX_N_LEGS =           new RemexArmor(REMEX_NETHERITE, LEGS, ARMOR_PROP).setRegistryName("remex_netherite_leggings");
    public static final Item REMEX_N_BOOTS =          new RemexArmor(REMEX_NETHERITE, FEET, ARMOR_PROP).setRegistryName("remex_netherite_boots");

    public static final Item STONE_CRUSHING_HAMMER =      makeHammer(Config.stone_hammer_durability.get(),"stone_crushing_hammer");
    public static final Item IRON_CRUSHING_HAMMER =       makeHammer(Config.iron_hammer_durability.get(),"iron_crushing_hammer");
    public static final Item GOLD_CRUSHING_HAMMER =       makeHammer(Config.gold_hammer_durability.get(),"gold_crushing_hammer");
    public static final Item DIAMOND_CRUSHING_HAMMER =    makeHammer(Config.diamond_hammer_durability.get(),"diamond_crushing_hammer");
    public static final Item ABYSSALITE_CRUSHING_HAMMER = makeHammer(Config.abyssalite_hammer_durability.get(),"abyssalite_crushing_hammer");

    public static final ItemSoulCoal SOUL_COAL = new ItemSoulCoal(defaultItemProperties());

    public static final RegistryObject<Item> DEEPSLATE_GOLD_ORE_FRAGMENT = ITEMS.register("deepslate_gold_ore_fragment", () -> new Item(defaultItemProperties()));
//    public static final Item DEEPSLATE_GOLD_ORE_FRAGMENT = makeItem("deepslate_gold_ore_fragment");
    public static final Item DEEPSLATE_IRON_ORE_FRAGMENT =   makeItem("deepslate_iron_ore_fragment");
    public static final Item DEEPSLATE_COPPER_ORE_FRAGMENT = makeItem("deepslate_copper_ore_fragment");
    public static final Item IRON_ORE_FRAGMENT =             makeItem("iron_ore_fragment");
    public static final Item GOLD_ORE_FRAGMENT =             makeItem("gold_ore_fragment");
    public static final Item TIN_ORE_FRAGMENT =              makeItem("tin_ore_fragment");
    public static final Item ZINC_ORE_FRAGMENT =             makeItem("zinc_ore_fragment");
    public static final Item COPPER_ORE_FRAGMENT =           makeItem("copper_ore_fragment");
    public static final Item SILVER_ORE_FRAGMENT =           makeItem("silver_ore_fragment");
    public static final Item OSMIUM_ORE_FRAGMENT =           makeItem("osmium_ore_fragment");
    public static final Item NICKEL_ORE_FRAGMENT =           makeItem("nickel_ore_fragment");
    public static final Item LEAD_ORE_FRAGMENT =             makeItem("lead_ore_fragment");
    public static final Item PLATINUM_ORE_FRAGMENT =         makeItem("platinum_ore_fragment");
    public static final Item QUICKSILVER_ORE_FRAGMENT =      makeItem("quicksilver_ore_fragment");
    public static final Item URANIUM_ORE_FRAGMENT =          makeItem("uranium_ore_fragment");
    public static final Item ALUMINUM_ORE_FRAGMENT =         makeItem("aluminum_ore_fragment");

    public static final Item MUD_GLOB =                      makeItem("mud_glob");
    public static final Item UNFIRED_MUD_BRICK =             makeItem("unfired_mud_brick");
    public static final Item MUD_BRICK =                     makeItem("mud_brick");
    public static final Item UNFIRED_BRICK =                 makeItem("unfired_brick");
    public static final Item UNFIRED_NETHER_BRICK =          makeItem("unfired_nether_brick");

    public static final Item BONE_SPEAR = makeSpear(BONE, Config.bone_spear_damage.get(), Config.bone_spear_speed.get().floatValue(),"bone_spear");

    public static void registerItemProperties() {
        ItemModelsProperties.register(Items.CROSSBOW, RigoranthusEmortisReborn.rl("bone_arrow"), (stack, world, entity) -> {
            return entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.containsChargedProjectile(stack, MagicItemsRegistry.BONE_ARROW.getItem()) ? 1.0F : 0.0F;
        });
    }

    public static void registerItems(RegistryEvent.Register<Item> event) {
        if (Config.enableBoneWeapons.get()) {event.getRegistry().register(BONE_SPEAR);}
        if (Config.enableSoulCoal.get()) { event.getRegistry().register(SOUL_COAL); }
        if (Config.enableUnfiredBricks.get()) {
            event.getRegistry().register(MUD_GLOB);
            event.getRegistry().register(UNFIRED_MUD_BRICK);              event.getRegistry().register(MUD_BRICK);
            event.getRegistry().register(UNFIRED_BRICK);                  event.getRegistry().register(UNFIRED_NETHER_BRICK);
        }
        if (Config.enableNetheriteAdditions.get()) {
            event.getRegistry().register(APOGEAN_SWORD);                  event.getRegistry().register(APOGEAN_AXE);
            event.getRegistry().register(APOGEAN_N_HELMET);       event.getRegistry().register(APOGEAN_N_CHEST);
            event.getRegistry().register(APOGEAN_N_LEGS);     //event.getRegistry().register(APOGEAN_N_BOOTS);
            event.getRegistry().register(AQUEOUS_SWORD);                  event.getRegistry().register(AQUEOUS_AXE);
            event.getRegistry().register(AQUEOUS_N_HELMET);       event.getRegistry().register(AQUEOUS_N_CHEST);
            event.getRegistry().register(AQUEOUS_N_LEGS);     event.getRegistry().register(AQUEOUS_N_BOOTS);
            event.getRegistry().register(ATROPHYING_SWORD);               event.getRegistry().register(ATROPHYING_AXE);
            event.getRegistry().register(ATROPHYING_N_HELMET);    event.getRegistry().register(ATROPHYING_N_CHEST);
            event.getRegistry().register(ATROPHYING_N_LEGS);  event.getRegistry().register(ATROPHYING_N_BOOTS);
            event.getRegistry().register(INCORPOREAL_SWORD);              event.getRegistry().register(INCORPOREAL_AXE);
            event.getRegistry().register(INCORPOREAL_N_HELMET);   //event.getRegistry().register(INCORPOREAL_N_CHEST);
            event.getRegistry().register(INCORPOREAL_N_LEGS); event.getRegistry().register(INCORPOREAL_N_BOOTS);
            event.getRegistry().register(INFERNAL_SWORD);                 event.getRegistry().register(INFERNAL_AXE);
            event.getRegistry().register(INFERNAL_N_HELMET);      event.getRegistry().register(INFERNAL_N_CHEST);
            event.getRegistry().register(INFERNAL_N_LEGS);    event.getRegistry().register(INFERNAL_N_BOOTS);
            event.getRegistry().register(OPULENT_SWORD);                  event.getRegistry().register(OPULENT_AXE);
            event.getRegistry().register(OPULENT_N_HELMET);       event.getRegistry().register(OPULENT_N_CHEST);
            event.getRegistry().register(OPULENT_N_LEGS);     event.getRegistry().register(OPULENT_N_BOOTS);
            event.getRegistry().register(PERNICIOUS_SWORD);               event.getRegistry().register(PERNICIOUS_AXE);
            event.getRegistry().register(PERNICIOUS_N_HELMET);    event.getRegistry().register(PERNICIOUS_N_CHEST);
            event.getRegistry().register(PERNICIOUS_N_LEGS);  event.getRegistry().register(PERNICIOUS_N_BOOTS);
            event.getRegistry().register(PHANTASMAL_SWORD);               event.getRegistry().register(PHANTASMAL_AXE);
            event.getRegistry().register(PHANTASMAL_N_HELMET);    event.getRegistry().register(PHANTASMAL_N_CHEST);
            event.getRegistry().register(PHANTASMAL_N_LEGS);  event.getRegistry().register(PHANTASMAL_N_BOOTS);
            event.getRegistry().register(REMEX_SWORD);                    event.getRegistry().register(REMEX_AXE);
            event.getRegistry().register(REMEX_N_HELMET);         event.getRegistry().register(REMEX_N_CHEST);
            event.getRegistry().register(REMEX_N_LEGS);       event.getRegistry().register(REMEX_N_BOOTS);
        }
        if (Config.enableHammersAndVanillaOreFragments.get()) {
            event.getRegistry().register(GOLD_ORE_FRAGMENT);              event.getRegistry().register(IRON_ORE_FRAGMENT);
            event.getRegistry().register(STONE_CRUSHING_HAMMER);          event.getRegistry().register(IRON_CRUSHING_HAMMER);
            event.getRegistry().register(GOLD_CRUSHING_HAMMER);           event.getRegistry().register(DIAMOND_CRUSHING_HAMMER);
            event.getRegistry().register(ABYSSALITE_CRUSHING_HAMMER);
        }
        if (Config.enableModdedOreFragments.get()) {
            event.getRegistry().register(DEEPSLATE_IRON_ORE_FRAGMENT);    //event.getRegistry().register(DEEPSLATE_GOLD_ORE_FRAGMENT);
            event.getRegistry().register(DEEPSLATE_COPPER_ORE_FRAGMENT);  event.getRegistry().register(TIN_ORE_FRAGMENT);
            event.getRegistry().register(COPPER_ORE_FRAGMENT);            event.getRegistry().register(ZINC_ORE_FRAGMENT);
            event.getRegistry().register(SILVER_ORE_FRAGMENT);            event.getRegistry().register(OSMIUM_ORE_FRAGMENT);
            event.getRegistry().register(NICKEL_ORE_FRAGMENT);            event.getRegistry().register(LEAD_ORE_FRAGMENT);
            event.getRegistry().register(PLATINUM_ORE_FRAGMENT);          event.getRegistry().register(QUICKSILVER_ORE_FRAGMENT);
            event.getRegistry().register(URANIUM_ORE_FRAGMENT);           event.getRegistry().register(ALUMINUM_ORE_FRAGMENT);
        }
    }
}