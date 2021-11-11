package com.platinumg17.rigoranthusemortisreborn.canis.common.util;

import java.util.function.Function;
import java.util.function.Supplier;

import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container.recipe.CanisBedRecipe;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CanisRecipeSerializers {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, EmortisConstants.MOD_ID);
    public static final RegistryObject<SpecialRecipeSerializer<CanisBedRecipe>> CANIS_BED = register("canis_bed", CanisBedRecipe::new);

    private static <R extends IRecipe<?>, T extends IRecipeSerializer<R>> RegistryObject<SpecialRecipeSerializer<R>> register(final String name, Function<ResourceLocation, R> factory) {
        return register(name, () -> new SpecialRecipeSerializer<>(factory));
    }
    private static <T extends IRecipeSerializer<?>> RegistryObject<T> register(final String name, final Supplier<T> sup) {
        return RECIPE_SERIALIZERS.register(name, sup);
    }
}