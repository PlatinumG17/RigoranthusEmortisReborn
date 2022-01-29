package com.platinumg17.rigoranthusemortisreborn.magica.setup;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.items.ingots.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ModEntities;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.curios.AbstractDominionCurio;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.curios.ConservationRing;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.curios.SummonersStrength;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibItemNames;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import net.minecraft.item.Food;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.HashSet;
import java.util.Set;

@ObjectHolder(EmortisConstants.MOD_ID)
public class MagicItemsRegistry {

    @ObjectHolder(LibItemNames.APOGEAN_NETHERITE_INGOT) public static ApogeanIngotItem ApogeanIngotItem;
    @ObjectHolder(LibItemNames.AQUEOUS_NETHERITE_INGOT) public static AqueousIngotItem AqueousIngotItem;
    @ObjectHolder(LibItemNames.ATROPHYING_NETHERITE_INGOT) public static AtrophyingIngotItem AtrophyingIngotItem;
    @ObjectHolder(LibItemNames.INCORPOREAL_NETHERITE_INGOT) public static IncorporealIngotItem IncorporealIngotItem;
    @ObjectHolder(LibItemNames.INFERNAL_NETHERITE_INGOT) public static InfernalIngotItem InfernalIngotItem;
    @ObjectHolder(LibItemNames.OPULENT_NETHERITE_INGOT) public static OpulentIngotItem OpulentIngotItem;
    @ObjectHolder(LibItemNames.PERNICIOUS_NETHERITE_INGOT) public static PerniciousIngotItem PerniciousIngotItem;
    @ObjectHolder(LibItemNames.PHANTASMAL_NETHERITE_INGOT) public static PhantasmalIngotItem PhantasmalIngotItem;
    @ObjectHolder(LibItemNames.REMEX_NETHERITE_INGOT) public static RemexIngotItem RemexIngotItem;

    @ObjectHolder(LibItemNames.FIRE_SHOT) public static ModItem FIRE_SHOT;
    @ObjectHolder(LibItemNames.BOTTLE_OF_ICHOR) public static GlassBottleItem BOTTLE_OF_ICHOR;
    @ObjectHolder(LibItemNames.DWELLER_FLESH) public static ModItem DWELLER_FLESH;

    @ObjectHolder(LibItemNames.CREATIVE_SPELL_BOOK) public static SpellBook creativeSpellBook;
    @ObjectHolder(LibItemNames.BUCKET_OF_DOMINION) public static ModItem bucketOfDominion;

    @ObjectHolder(LibItemNames.UNADORNED_RING) public  static ModItem unadornedRing;
    @ObjectHolder(LibItemNames.RING_OF_LESSER_CONSERVATION) public static ConservationRing ringOfLesserConservation;
    @ObjectHolder(LibItemNames.RING_OF_GREATER_CONSERVATION) public static ConservationRing ringOfGreaterConservation;
    @ObjectHolder(LibItemNames.AMULET_OF_DOMINION_BOOST) public static AbstractDominionCurio amuletOfDominionBoost;
    @ObjectHolder(LibItemNames.AMULET_OF_DOMINION_REGEN) public static AbstractDominionCurio amuletOfDominionRegen;
    @ObjectHolder(LibItemNames.UNADORNED_AMULET) public static ModItem unadornedAmulet;
    @ObjectHolder(LibItemNames.DOMINION_WAND) public static DominionWand dominionWand;
    @ObjectHolder(LibItemNames.DOMINION_GEM) public static ModItem dominionGem;
    @ObjectHolder(LibItemNames.ADONIS) public static Adonis ADONIS;
    @ObjectHolder(LibItemNames.BONE_ARROW) public static BoneArrow BONE_ARROW;

    @ObjectHolder(LibItemNames.EXP_GEM) public static ExperienceGem EXPERIENCE_GEM;
    @ObjectHolder(LibItemNames.GREATER_EXP_GEM) public static ExperienceGem GREATER_EXPERIENCE_GEM;
    @ObjectHolder(LibItemNames.LUSTERIC_SHIELD) public static LustericShield LUSTERIC_SHIELD;
    @ObjectHolder(LibItemNames.SUMMONERS_STRENGTH) public static SummonersStrength SUMMONERS_STRENGTH;

    public static Food DOMINION_BERRY_FOOD = (new Food.Builder()).nutrition(2).saturationMod(0.1F).effect(() -> new EffectInstance(ModPotions.DOMINION_REGEN_EFFECT, 100), 1.0f).alwaysEat().build();
    public static Food BILIS_BERRY_FOOD = (new Food.Builder()).nutrition(3).saturationMod(0.5F).build();
    public static Food ICHOR_FOOD = (new Food.Builder()).nutrition(5).saturationMod(0.5F).meat().build();

    @Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        public static final Set<Item> ITEMS = new HashSet<>();

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            Item[] items = {

                    new ApogeanIngotItem(),
                    new AqueousIngotItem(),
                    new AtrophyingIngotItem(),
                    new IncorporealIngotItem(),
                    new InfernalIngotItem(),
                    new OpulentIngotItem(),
                    new PerniciousIngotItem(),
                    new PhantasmalIngotItem(),
                    new RemexIngotItem(),

                    new GlassBottleItem(defaultItemProperties().stacksTo(16).food(ICHOR_FOOD)).setRegistryName(LibItemNames.BOTTLE_OF_ICHOR),
                    new ModItem(defaultItemProperties().stacksTo(1), LibItemNames.BUCKET_OF_DOMINION),
                    new DominionWand(),
                    new ModItem(LibItemNames.DWELLER_FLESH),
                    new ModItem(LibItemNames.UNADORNED_AMULET).withTooltip(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.unadorned")),
                    new ModItem(LibItemNames.UNADORNED_RING).withTooltip(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.unadorned")),
                    new ConservationRing(LibItemNames.RING_OF_LESSER_CONSERVATION) { @Override public int getDominionDiscount() { return 10; }},
                    new ConservationRing(LibItemNames.RING_OF_GREATER_CONSERVATION) { @Override public int getDominionDiscount() { return 20; }},
                    new AbstractDominionCurio(LibItemNames.AMULET_OF_DOMINION_BOOST){ @Override public int getMaxDominionBoost() { return 50; }},
                    new AbstractDominionCurio(LibItemNames.AMULET_OF_DOMINION_REGEN){ @Override public int getDominionRegenBonus() { return 3; }},
                    new SummonersStrength(defaultItemProperties().stacksTo(1)),
                    new ModItem(LibItemNames.DOMINION_GEM).withTooltip(new TranslationTextComponent("tooltip.dominion_gem")),
                    new LustericShield(),
                    new Adonis(),
                    new BoneArrow(),
                    new ModItem(LibItemNames.FIRE_SHOT),
                    new SpawnEggItem(ModEntities.FERAL_CANIS, 0x999999, 0xffffff, defaultItemProperties()).setRegistryName(LibItemNames.CANIS_CHORDATA_SPAWN_EGG),
                    new SpawnEggItem(ModEntities.SUNDERED_CADAVER, -6684673, -39322, defaultItemProperties()).setRegistryName(LibItemNames.SUNDERED_CADAVER_SPAWN_EGG),
                    new SpawnEggItem(ModEntities.NECRAW_FASCII, 0x27640c, 0xffd966, defaultItemProperties()).setRegistryName(LibItemNames.NECRAW_FASCII_SPAWN_EGG),
                    new SpawnEggItem(ModEntities.LANGUID_DWELLER, 0x968d81, 0x491919, defaultItemProperties()).setRegistryName(LibItemNames.LANGUID_DWELLER_SPAWN_EGG),
                    new ExperienceGem(defaultItemProperties(), LibItemNames.EXP_GEM) { @Override public int getValue() { return 3; }},
                    new ExperienceGem(defaultItemProperties(), LibItemNames.GREATER_EXP_GEM) { @Override public int getValue() { return 12; }}
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final Item item : items) {
                registry.register(item);
                ITEMS.add(item);
            }
        }
    }
    public static Item.Properties defaultItemProperties() {
        return new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP);
    }
}
























//    @ObjectHolder("debug") public static REItemDebug debug;
//
//    @ObjectHolder(LibItemNames.AZULOREAL_BOAT) public static REBoatItemAzuloreal AZULOREAL_BOAT;
//    @ObjectHolder(LibItemNames.JESSIC_BOAT) public static REBoatItemJessic JESSIC_BOAT;
//
//    @ObjectHolder(LibItemNames.NOVICE_SPELL_BOOK) public static SpellBook noviceSpellBook;
//    @ObjectHolder(LibItemNames.APPRENTICE_SPELL_BOOK) public static SpellBook apprenticeSpellBook;
//    @ObjectHolder(LibItemNames.EMORTIC_SPELL_BOOK) public static SpellBook emorticSpellBook;
//    @ObjectHolder(LibItemNames.EMORTIC_ORIGINS) public static EmorticOrigins emorticOrigins = Null();
//                    new REItemDebug(),
//                    new EmorticOrigins().withTooltip(new TranslationTextComponent("tooltip.emortic_origins")),

//                    new REBoatItemAzuloreal(new Item.Properties().stacksTo(1).tab(ItemGroup.TAB_TRANSPORTATION), "azuloreal").setRegistryName(LibItemNames.AZULOREAL_BOAT),
//                    new REBoatItemJessic(new Item.Properties().stacksTo(1).tab(ItemGroup.TAB_TRANSPORTATION), "jessic").setRegistryName(LibItemNames.JESSIC_BOAT),
//                    new SpellBook(ISpellTier.Tier.THREE).setRegistryName(LibItemNames.EMORTIC_SPELL_BOOK),
//                    new SpellBook(ISpellTier.Tier.THREE).setRegistryName(LibItemNames.CREATIVE_SPELL_BOOK),
//                    new LostTome(defaultItemProperties().stacksTo(1), LibItemNames.LOST_TOME),
//
//
//            for(Glyph glyph : RigoranthusEmortisRebornAPI.getInstance().getGlyphMap().values()){
//                registry.register(glyph);
//                ITEMS.add(glyph);
//            }
//            for(RitualOffering ritualParchment : RigoranthusEmortisRebornAPI.getInstance().getRitualItemMap().values()){
//                registry.register(ritualParchment);
//                ITEMS.add(ritualParchment);
//            }
//            for(FamiliarScript script : RigoranthusEmortisRebornAPI.getInstance().getFamiliarScriptMap().values()){
//                registry.register(script);
//                ITEMS.add(script);
//            }



//    @ObjectHolder(LibItemNames.POWDERED_ESOTERICUM) public static Esotericum POWDERED_ESOTERICUM;
//    @ObjectHolder(LibItemNames.BLASTING_AUGMENT) public static ItemAugmentBlast BLASTING_AUGMENT;
//    @ObjectHolder(LibItemNames.SMOKING_AUGMENT) public static ItemAugmentSmoke SMOKING_AUGMENT;
//    @ObjectHolder(LibItemNames.SPEED_AUGMENT) public static ItemAugmentSpeed SPEED_AUGMENT;
//    @ObjectHolder(LibItemNames.FUEL_AUGMENT) public static ItemAugmentFuel FUEL_AUGMENT;
//    @ObjectHolder(LibItemNames.ITEM_COPY) public static ItemSmelteryCopy ITEM_COPY;
//    @ObjectHolder(LibItemNames.BLANK_GLYPH) public static  Item blankGlyph;
//    @ObjectHolder(LibItemNames.PROSAIC_BELT) public static ModItem prosaicBelt;

//    @ObjectHolder(LibItemNames.WAND) public static Wand WAND;
//    @ObjectHolder(LibItemNames.A_ARROW) public static SpellArrow A_ARROW;
//    @ObjectHolder(LibItemNames.S_ARROW) public static SpellArrow S_ARROW;
//    @ObjectHolder(LibItemNames.P_ARROW) public static SpellArrow P_ARROW;

//                    new ModItem(LibItemNames.PROSAIC_BELT).withTooltip(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.unadorned")),
//                    new NoviceArmor(EquipmentSlotType.FEET).setRegistryName("novice_boots"),
//                    new NoviceArmor(EquipmentSlotType.LEGS).setRegistryName("novice_leggings"),
//                    new NoviceArmor(EquipmentSlotType.CHEST).setRegistryName("novice_robes"),
//                    new NoviceArmor(EquipmentSlotType.HEAD).setRegistryName("novice_hood"),
//                    new ApprenticeArmor(EquipmentSlotType.FEET).setRegistryName("apprentice_boots"),
//                    new ApprenticeArmor(EquipmentSlotType.LEGS).setRegistryName("apprentice_leggings"),
//                    new ApprenticeArmor(EquipmentSlotType.CHEST).setRegistryName("apprentice_robes"),
//                    new ApprenticeArmor(EquipmentSlotType.HEAD).setRegistryName("apprentice_hood"),
//                    new MasterArmor(EquipmentSlotType.FEET).setRegistryName("emortic_boots"),
//                    new MasterArmor(EquipmentSlotType.LEGS).setRegistryName("emortic_leggings"),
//                    new MasterArmor(EquipmentSlotType.CHEST).setRegistryName("emortic_robes"),
//                    new MasterArmor(EquipmentSlotType.HEAD).setRegistryName("emortic_hood"),
//                    new SpellBook(ISpellTier.Tier.ONE).setRegistryName(LibItemNames.NOVICE_SPELL_BOOK), // TODO --> Nix the whole Tier thing [probably]
//                    new SpellBook(ISpellTier.Tier.TWO).setRegistryName(LibItemNames.APPRENTICE_SPELL_BOOK),
//                    new SpellParchment(),
//                    new Wand(),
//                    new FormSpellArrow(LibItemNames.P_ARROW, Pierce.INSTANCE, 2),
//                    new FormSpellArrow(LibItemNames.S_ARROW, Split.INSTANCE, 2),
//                    new SpellArrow(LibItemNames.A_ARROW, Amplify.INSTANCE, 2),
//                    new ItemAugmentBlast(new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).stacksTo(16)).setRegistryName(LibItemNames.BLASTING_AUGMENT),
//                    new ItemAugmentSmoke(new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).stacksTo(16)).setRegistryName(LibItemNames.SMOKING_AUGMENT),
//                    new ItemAugmentSpeed(new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).stacksTo(16)).setRegistryName(LibItemNames.SPEED_AUGMENT),
//                    new ItemAugmentFuel(new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).stacksTo(16)).setRegistryName(LibItemNames.FUEL_AUGMENT),
//                    new ItemSmelteryCopy(new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).stacksTo(16)).setRegistryName(LibItemNames.ITEM_COPY),