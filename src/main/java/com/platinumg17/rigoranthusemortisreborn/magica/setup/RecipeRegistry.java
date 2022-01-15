package com.platinumg17.rigoranthusemortisreborn.magica.setup;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.IPsyglyphicRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.PsyglyphicAmalgamatorRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.PsyglyphicEnchantingRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.CraftingPressRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.IIchoricRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RecipeRegistry {

    public static final IRecipeType<IIchoricRecipe> CRAFTING_PRESS_TYPE = new RecipeType();
    public static final IRecipeType<IPsyglyphicRecipe> PSYGLYPHIC_TYPE = new RecipeType();
    public static final IRecipeType<PsyglyphicEnchantingRecipe> ENCHANTMENT_TYPE = new RecipeType();

    public static final IRecipeSerializer<CraftingPressRecipe> CRAFTING_PRESS_SERIALIZER = new CraftingPressRecipe.Serializer();
    public static final IRecipeSerializer<PsyglyphicAmalgamatorRecipe> PSYGLYPHIC_SERIALIZER = new PsyglyphicAmalgamatorRecipe.Serializer();
    public static final IRecipeSerializer<PsyglyphicEnchantingRecipe> ENCHANTMENT_SERIALIZER = new PsyglyphicEnchantingRecipe.Serializer();

    @SubscribeEvent
    public static void register(final RegistryEvent.Register<IRecipeSerializer<?>> evt) {
        Registry.register(Registry.RECIPE_TYPE, RigoranthusEmortisReborn.rl("crafting_press_recipe"), CRAFTING_PRESS_TYPE);
        Registry.register(Registry.RECIPE_TYPE, RigoranthusEmortisReborn.rl("amalgamator_recipe"), PSYGLYPHIC_TYPE);
        Registry.register(Registry.RECIPE_TYPE, RigoranthusEmortisReborn.rl(PsyglyphicEnchantingRecipe.RECIPE_ID), ENCHANTMENT_TYPE);

        evt.getRegistry().register(CRAFTING_PRESS_SERIALIZER.setRegistryName(RigoranthusEmortisReborn.rl("crafting_press_recipe")));
        evt.getRegistry().register(PSYGLYPHIC_SERIALIZER.setRegistryName(RigoranthusEmortisReborn.rl("amalgamator_recipe")));
        evt.getRegistry().register(ENCHANTMENT_SERIALIZER.setRegistryName(RigoranthusEmortisReborn.rl(PsyglyphicEnchantingRecipe.RECIPE_ID)));
    }

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }
}