package com.platinumg17.rigoranthusemortisreborn.magica.common.potions;

import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.PotionIngredient;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.EffectNecrotizingFasciitis;
import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.EmortisBrewing;
import com.platinumg17.rigoranthusemortisreborn.items.armor.bonuses.*;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibPotions;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.*;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;

@ObjectHolder(EmortisConstants.MOD_ID)
public class ModPotions {

    public static final ApogeanSetBonus APOGEAN_SET_BONUS = new ApogeanSetBonus();
    public static final AqueousSetBonus AQUEOUS_SET_BONUS = new AqueousSetBonus();
    public static final AtrophyingSetBonus ATROPHYING_SET_BONUS = new AtrophyingSetBonus();
    public static final IncorporealSetBonus INCORPOREAL_SET_BONUS = new IncorporealSetBonus();
    public static final InfernalSetBonus INFERNAL_SET_BONUS = new InfernalSetBonus();
    public static final OpulentSetBonus OPULENT_SET_BONUS = new OpulentSetBonus();
    public static final PerniciousSetBonus PERNICIOUS_SET_BONUS = new PerniciousSetBonus();
    public static final PhantasmalSetBonus PHANTASMAL_SET_BONUS = new PhantasmalSetBonus();
    public static final RemexSetBonus REMEX_SET_BONUS = new RemexSetBonus();
    public static final EffectNecrotizingFasciitis NECROTIZING_FASCIITIS_EFFECT = new EffectNecrotizingFasciitis();
    public static final ShieldEffect SHIELD_POTION = new ShieldEffect();
    public static final ShockedEffect SHOCKED_EFFECT = new ShockedEffect();
    public static final DominionRegenEffect DOMINION_REGEN_EFFECT = new DominionRegenEffect();
    public static final SummoningCooldownEffect SUMMONING_COOLDOWN = new SummoningCooldownEffect();
    public static final HexEffect HEX_EFFECT = new HexEffect();
//    public static final HerbVisionEffect HERB_VISION_EFFECT = new HerbVisionEffect();
    public static final GliderEffect GLIDER_EFFECT = new GliderEffect();
    public static final SnareEffect SNARE_EFFECT = new SnareEffect();
    public static final GravityEffect GRAVITY_EFFECT = new GravityEffect();
    public static final Effect SPELL_DAMAGE_EFFECT = new PublicEffect(EffectType.BENEFICIAL, new ParticleColor(30, 200, 200).getColor()).setRegistryName(EmortisConstants.MOD_ID, LibPotions.SPELL_DAMAGE);
    public static final Effect FAMILIAR_COOLDOWN_EFFECT = new PublicEffect(EffectType.NEUTRAL, new ParticleColor(30, 200, 200).getColor(), new ArrayList<>()).setRegistryName(EmortisConstants.MOD_ID, LibPotions.FAMILIAR_COOLDOWN);
    public static final BounceEffect BOUNCE_EFFECT = new BounceEffect();

    @ObjectHolder(LibPotions.NECROTIZING_FASCIITIS) public static Potion NECROTIZING_FASCIITIS;
    @ObjectHolder(LibPotions.NECROTIZING_FASCIITIS_II) public static Potion NECROTIZING_FASCIITIS_II;

    @ObjectHolder(LibPotions.DOMINION_REGEN) public static Potion DOMINION_REGEN_POTION;
    @ObjectHolder(LibPotions.DOMINION_REGEN_LONG) public static Potion LONG_DOMINION_REGEN_POTION;
    @ObjectHolder(LibPotions.DOMINION_REGEN_STRONG) public static Potion STRONG_DOMINION_REGEN_POTION;

    @ObjectHolder(LibPotions.SPELL_DAMAGE) public static Potion SPELL_DAMAGE_POTION;
    @ObjectHolder(LibPotions.SPELL_DAMAGE_LONG) public static Potion SPELL_DAMAGE_POTION_LONG;
    @ObjectHolder(LibPotions.SPELL_DAMAGE_STRONG) public static Potion SPELL_DAMAGE_POTION_STRONG;

    public static void addRecipes() {
        ItemStack AWKWARD = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD);

        ItemStack necrotizingFasiitisPot = PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.NECROTIZING_FASCIITIS);
        ItemStack necrotizingFasiitisPotII = PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.NECROTIZING_FASCIITIS_II);

        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new PotionIngredient(AWKWARD), Ingredient.of(MagicItemsRegistry.DWELLER_FLESH), necrotizingFasiitisPot));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new PotionIngredient(necrotizingFasiitisPot), Ingredient.of(MagicItemsRegistry.BOTTLE_OF_ICHOR),  necrotizingFasiitisPotII));

        ItemStack dominionPot = PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.DOMINION_REGEN_POTION);
        ItemStack dominionPotLong = PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.LONG_DOMINION_REGEN_POTION);
        ItemStack dominionPotStrong = PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.STRONG_DOMINION_REGEN_POTION);

        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new PotionIngredient(AWKWARD), Ingredient.of(BlockRegistry.DOMINION_BERRY_BUSH),  dominionPot));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new PotionIngredient(dominionPot), Ingredient.of(Items.GLOWSTONE_DUST),  dominionPotStrong));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new PotionIngredient(dominionPot), Ingredient.of(Items.REDSTONE),  dominionPotLong));

        ItemStack sDamagePot = PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.SPELL_DAMAGE_POTION);
        ItemStack sDamagePotLong = PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.SPELL_DAMAGE_POTION_LONG);
        ItemStack sDamagePotStrong = PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.SPELL_DAMAGE_POTION_STRONG);

        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new PotionIngredient(AWKWARD), Ingredient.of(MagicItemsRegistry.BOTTLE_OF_ICHOR),  sDamagePot));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new PotionIngredient(sDamagePot), Ingredient.of(Items.GLOWSTONE_DUST),  sDamagePotStrong));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new PotionIngredient(sDamagePot), Ingredient.of(Items.REDSTONE),  sDamagePotLong));

        ItemStack water = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);
//        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(water), Ingredient.of(ItemInit.BOTTLE_OF_ICHOR.get()), PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LEAPING)));
//        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(water), Ingredient.of(ItemInit.DWELLER_FLESH.get()), PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH)));
//        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(water), Ingredient.of(ItemInit.), PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_WATER_BREATHING)));
    }

    @Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerEffects(final RegistryEvent.Register<Effect> event) {
            final IForgeRegistry<Effect> registry = event.getRegistry();
            registry.registerAll(
                    APOGEAN_SET_BONUS,
                    AQUEOUS_SET_BONUS,
                    ATROPHYING_SET_BONUS,
                    INCORPOREAL_SET_BONUS,
                    INFERNAL_SET_BONUS,
                    OPULENT_SET_BONUS,
                    PERNICIOUS_SET_BONUS,
                    PHANTASMAL_SET_BONUS,
                    REMEX_SET_BONUS,
                    NECROTIZING_FASCIITIS_EFFECT,

                    SHIELD_POTION,
                    DOMINION_REGEN_EFFECT,
                    SUMMONING_COOLDOWN,
                    SHOCKED_EFFECT,
                    HEX_EFFECT,
                    GLIDER_EFFECT,
                    SNARE_EFFECT,
                    GRAVITY_EFFECT,
                    SPELL_DAMAGE_EFFECT,
                    FAMILIAR_COOLDOWN_EFFECT,
                    BOUNCE_EFFECT
            );
        }
        @SubscribeEvent
        public static void registerPotions(final RegistryEvent.Register<Potion> event) {
            final IForgeRegistry<Potion> registry = event.getRegistry();
            registry.register(new Potion(new EffectInstance(NECROTIZING_FASCIITIS_EFFECT, 5000)).setRegistryName(LibPotions.NECROTIZING_FASCIITIS));
            registry.register(new Potion(new EffectInstance(NECROTIZING_FASCIITIS_EFFECT, 10000)).setRegistryName(LibPotions.NECROTIZING_FASCIITIS_II));
            registry.register(new Potion(new EffectInstance(DOMINION_REGEN_EFFECT, 3600)).setRegistryName(LibPotions.DOMINION_REGEN));
            registry.register(new Potion(new EffectInstance(DOMINION_REGEN_EFFECT, 9600)).setRegistryName(LibPotions.DOMINION_REGEN_LONG));
            registry.register(new Potion(new EffectInstance(DOMINION_REGEN_EFFECT, 3600, 1)).setRegistryName(LibPotions.DOMINION_REGEN_STRONG));
            registry.register(new Potion(new EffectInstance(SPELL_DAMAGE_EFFECT, 3600)).setRegistryName(LibPotions.SPELL_DAMAGE));
            registry.register(new Potion(new EffectInstance(SPELL_DAMAGE_EFFECT, 9600)).setRegistryName(LibPotions.SPELL_DAMAGE_LONG));
            registry.register(new Potion(new EffectInstance(SPELL_DAMAGE_EFFECT, 3600, 1)).setRegistryName(LibPotions.SPELL_DAMAGE_STRONG));
        }
    }
}