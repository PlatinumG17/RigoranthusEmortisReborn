package com.platinumg17.rigoranthusemortisreborn.core.registry.effects;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.items.armor.bonuses.*;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.*;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = RigoranthusEmortisReborn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RigoranthusEffectRegistry {
    public static final Effect APOGEAN_SET_BONUS = new ApogeanSetBonus();
    public static final Effect AQUEOUS_SET_BONUS = new AqueousSetBonus();
    public static final Effect ATROPHYING_SET_BONUS = new AtrophyingSetBonus();
    public static final Effect INCORPOREAL_SET_BONUS = new IncorporealSetBonus();
    public static final Effect INFERNAL_SET_BONUS = new InfernalSetBonus();
    public static final Effect OPULENT_SET_BONUS = new OpulentSetBonus();
    public static final Effect PERNICIOUS_SET_BONUS = new PerniciousSetBonus();
    public static final Effect PHANTASMAL_SET_BONUS = new PhantasmalSetBonus();
    public static final Effect REMEX_SET_BONUS = new RemexSetBonus();
    public static final Effect NECROTIZING_FASCIITIS = new EffectNecrotizingFasciitis();
    public static final Potion NECROTIZING_FASCIITIS_POTION = new Potion(new EffectInstance(NECROTIZING_FASCIITIS, 3600)).setRegistryName("rigoranthusemortis:necrotizing_fasciitis");
    public static final Potion NECROTIZING_FASCIITIS_II_POTION = new Potion(new EffectInstance(NECROTIZING_FASCIITIS, 9600)).setRegistryName("rigoranthusemortis:necrotizing_fasciitis_ii");

    @SubscribeEvent
    public static void registerEffects(RegistryEvent.Register<Effect> event) {
        try {
            for (Field f : RigoranthusEffectRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Effect) {
                    event.getRegistry().register((Effect) obj);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event) {
        try {
            for (Field f : RigoranthusEffectRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Potion) {
                    event.getRegistry().register((Potion) obj);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        RigoranthusEffectRegistry.onInitItems();
    }

    public static ItemStack createPotion(Potion potion){
        return  PotionUtils.setPotion(new ItemStack(Items.POTION), potion);
    }

    public static void onInitItems(){
        BrewingRecipeRegistry.addRecipe(Ingredient.of(createPotion(Potions.STRENGTH)), Ingredient.of(ItemInit.DWELLER_FLESH.get()), createPotion(NECROTIZING_FASCIITIS_POTION));
        BrewingRecipeRegistry.addRecipe(new EmortisBrewing(Ingredient.of(createPotion(NECROTIZING_FASCIITIS_II_POTION)), Ingredient.of(Items.REDSTONE), createPotion(NECROTIZING_FASCIITIS_II_POTION)));
    }

}
