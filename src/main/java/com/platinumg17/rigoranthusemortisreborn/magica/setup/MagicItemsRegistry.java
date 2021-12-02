package com.platinumg17.rigoranthusemortisreborn.magica.setup;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.curios.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.armor.ApprenticeArmor;
import com.platinumg17.rigoranthusemortisreborn.magica.common.armor.MasterArmor;
import com.platinumg17.rigoranthusemortisreborn.magica.common.armor.NoviceArmor;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibItemNames;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentAmplify;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentPierce;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentSplit;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.ModItem;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ISpellTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.HashSet;
import java.util.Set;

import static com.platinumg17.rigoranthusemortisreborn.magica.setup.InjectionUtil.Null;

@ObjectHolder(EmortisConstants.MOD_ID)
public class MagicItemsRegistry {
    @ObjectHolder(LibItemNames.NOVICE_SPELL_BOOK) public static SpellBook noviceSpellBook;
    @ObjectHolder(LibItemNames.APPRENTICE_SPELL_BOOK) public static SpellBook apprenticeSpellBook;
    @ObjectHolder(LibItemNames.EMORTIC_SPELL_BOOK) public static SpellBook emorticSpellBook;
    @ObjectHolder(LibItemNames.CREATIVE_SPELL_BOOK) public static SpellBook creativeSpellBook;

    @ObjectHolder(LibItemNames.BLANK_GLYPH) public static  Item blankGlyph;
    @ObjectHolder(LibItemNames.BUCKET_OF_DOMINION) public static ModItem bucketOfDominion;

    @ObjectHolder(LibItemNames.DOMINION_FIBER) public static ModItem DOMINION_FIBER;

    @ObjectHolder(LibItemNames.PROSAIC_BELT) public static ModItem prosaicBelt;
    @ObjectHolder(LibItemNames.EMORTIC_ORIGINS) public static EmorticOrigins emorticOrigins = Null();
    @ObjectHolder(LibItemNames.RING_OF_POTENTIAL) public  static ModItem ringOfPotential;
    @ObjectHolder(LibItemNames.RING_OF_LESSER_DISCOUNT) public static DiscountRing ringOfLesserDiscount;
    @ObjectHolder(LibItemNames.RING_OF_GREATER_DISCOUNT) public static DiscountRing ringOfGreaterDiscount;
    
    @ObjectHolder(LibItemNames.SPELL_PARCHMENT) public static SpellParchment spellParchment;
    @ObjectHolder(LibItemNames.DOMINION_WAND) public static DominionWand DOMINION_ROD;
    @ObjectHolder(LibItemNames.AMULET_OF_DOMINION_BOOST)public static AbstractDominionCurio amuletOfDominionBoost;
    @ObjectHolder(LibItemNames.AMULET_OF_DOMINION_REGEN)public static AbstractDominionCurio amuletOfDominionRegen;
    @ObjectHolder(LibItemNames.DULL_TRINKET)public static ModItem dullTrinket;
    @ObjectHolder(LibItemNames.DOMINION_WAND)public static DominionWand dominionWand;
    @ObjectHolder("debug")public static REItemDebug debug;
    @ObjectHolder(LibItemNames.DOMINION_GEM)public static ModItem dominionGem;

    @ObjectHolder(LibItemNames.BLANK_PARCHMENT)public static ModItem BLANK_PARCHMENT;
    @ObjectHolder(LibItemNames.WAND)public static Wand WAND;
    @ObjectHolder(LibItemNames.ADONIS)public static Adonis ADONIS;

    @ObjectHolder(LibItemNames.AMPLIFY_ARROW)public static SpellArrow AMPLIFY_ARROW;
    @ObjectHolder(LibItemNames.SPLIT_ARROW)public static SpellArrow SPLIT_ARROW;
    @ObjectHolder(LibItemNames.PIERCE_ARROW)public static SpellArrow PIERCE_ARROW;

    @ObjectHolder(LibItemNames.EXP_GEM)public static ExperienceGem EXPERIENCE_GEM;
    @ObjectHolder(LibItemNames.GREATER_EXP_GEM)public static ExperienceGem GREATER_EXPERIENCE_GEM;

    @ObjectHolder(LibItemNames.ENCHANTERS_SWORD)public static EnchantersSword ENCHANTERS_SWORD;
    @ObjectHolder(LibItemNames.LUSTERIC_SHIELD)public static LustericShield LUSTERIC_SHIELD;

    @ObjectHolder(LibItemNames.CASTER_TOME)public static CasterTome CASTER_TOME;
    @ObjectHolder(LibItemNames.SUMMON_FOCUS)public static SummoningFocus SUMMONING_FOCUS;

    public static Food DOMINION_BERRY_FOOD = (new Food.Builder()).nutrition(2).saturationMod(0.1F).effect(() -> new EffectInstance(ModPotions.DOMINION_REGEN_EFFECT, 100), 1.0f).alwaysEat().build();

    @Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        public static final Set<Item> ITEMS = new HashSet<>();

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {

            Item[] items = {
                    new REItemDebug(),
                    new DominionWand(),
                    new ModItem(LibItemNames.BLANK_GLYPH),
                    new ModItem(LibItemNames.DULL_TRINKET).withTooltip(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.dull")),
                    new ModItem(LibItemNames.DOMINION_FIBER),
                    new ModItem(LibItemNames.PROSAIC_BELT).withTooltip(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.dull")),
                    new ModItem(LibItemNames.RING_OF_POTENTIAL).withTooltip(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.dull")),
                    new ModItem(defaultItemProperties().stacksTo(1), LibItemNames.BUCKET_OF_DOMINION),
                    new NoviceArmor(EquipmentSlotType.FEET).setRegistryName("novice_boots"),
                    new NoviceArmor(EquipmentSlotType.LEGS).setRegistryName("novice_leggings"),
                    new NoviceArmor(EquipmentSlotType.CHEST).setRegistryName("novice_robes"),
                    new NoviceArmor(EquipmentSlotType.HEAD).setRegistryName("novice_hood"),
                    new ApprenticeArmor(EquipmentSlotType.FEET).setRegistryName("apprentice_boots"),
                    new ApprenticeArmor(EquipmentSlotType.LEGS).setRegistryName("apprentice_leggings"),
                    new ApprenticeArmor(EquipmentSlotType.CHEST).setRegistryName("apprentice_robes"),
                    new ApprenticeArmor(EquipmentSlotType.HEAD).setRegistryName("apprentice_hood"),
                    new MasterArmor(EquipmentSlotType.FEET).setRegistryName("emortic_boots"),
                    new MasterArmor(EquipmentSlotType.LEGS).setRegistryName("emortic_leggings"),
                    new MasterArmor(EquipmentSlotType.CHEST).setRegistryName("emortic_robes"),
                    new MasterArmor(EquipmentSlotType.HEAD).setRegistryName("emortic_hood"),
                    new SpellBook(ISpellTier.Tier.ONE).setRegistryName(LibItemNames.NOVICE_SPELL_BOOK), // TODO --> Nix the while Tier thing [probably]
                    new SpellBook(ISpellTier.Tier.TWO).setRegistryName(LibItemNames.APPRENTICE_SPELL_BOOK),
                    new SpellBook(ISpellTier.Tier.THREE).setRegistryName(LibItemNames.EMORTIC_SPELL_BOOK),
                    new SpellBook(ISpellTier.Tier.THREE).setRegistryName(LibItemNames.CREATIVE_SPELL_BOOK),
                    new EmorticOrigins().withTooltip(new TranslationTextComponent("tooltip.emortic_origins")),
                    new DiscountRing(LibItemNames.RING_OF_LESSER_DISCOUNT) {
                        @Override
                        public int getDominionDiscount() {
                            return 10;
                        }
                    },
                    new DiscountRing(LibItemNames.RING_OF_GREATER_DISCOUNT) {
                        @Override
                        public int getDominionDiscount() {
                            return 20;
                        }
                    },
                    new SpellParchment(),
                    new AbstractDominionCurio(LibItemNames.AMULET_OF_DOMINION_BOOST){
                        @Override
                        public int getMaxDominionBoost() {
                            return 50;
                        }
                    },
                    new AbstractDominionCurio(LibItemNames.AMULET_OF_DOMINION_REGEN){
                        @Override
                        public int getDominionRegenBonus() {
                            return 3;
                        }
                    },
                    new ModItem(LibItemNames.DOMINION_GEM).withTooltip(new TranslationTextComponent("tooltip.dominion_gem")),
                    new ModItem(LibItemNames.BLANK_PARCHMENT),
                    new Wand(),
                    new Adonis().setRegistryName(LibItemNames.ADONIS),
                    new FormSpellArrow(LibItemNames.PIERCE_ARROW, AugmentPierce.INSTANCE, 2),
                    new FormSpellArrow(LibItemNames.SPLIT_ARROW, AugmentSplit.INSTANCE, 2),
                    new SpellArrow(LibItemNames.AMPLIFY_ARROW, AugmentAmplify.INSTANCE, 2),
                    
                    new ExperienceGem(defaultItemProperties(), LibItemNames.EXP_GEM) {
                        @Override
                        public int getValue() {
                            return 3;
                        }
                    }.withTooltip(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.exp_gem")),
                    new ExperienceGem(defaultItemProperties(), LibItemNames.GREATER_EXP_GEM) {
                        @Override
                        public int getValue() {
                            return 12;
                        }
                    }.withTooltip(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.exp_gem")),
                    new LustericShield(),
                    new EnchantersSword(ItemTier.NETHERITE, 3, -2.4F).setRegistryName(LibItemNames.ENCHANTERS_SWORD),
                    new CasterTome(defaultItemProperties().stacksTo(1), LibItemNames.CASTER_TOME),
                    new SummoningFocus(defaultItemProperties().stacksTo(1), LibItemNames.SUMMON_FOCUS),
            };

            final IForgeRegistry<Item> registry = event.getRegistry();
            for(Glyph glyph : RigoranthusEmortisRebornAPI.getInstance().getGlyphMap().values()){
                registry.register(glyph);
                ITEMS.add(glyph);
            }
            for(RitualTablet ritualParchment : RigoranthusEmortisRebornAPI.getInstance().getRitualItemMap().values()){
                registry.register(ritualParchment);
                ITEMS.add(ritualParchment);
            }
            for(FamiliarScript script : RigoranthusEmortisRebornAPI.getInstance().getFamiliarScriptMap().values()){
                registry.register(script);
                ITEMS.add(script);
            }
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