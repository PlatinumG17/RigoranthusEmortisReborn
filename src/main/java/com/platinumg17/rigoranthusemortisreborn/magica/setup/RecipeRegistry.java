package com.platinumg17.rigoranthusemortisreborn.magica.setup;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.IPsyglyphicRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.PsyglyphicAmalgamatorRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.PsyglyphicEnchantingRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.GlyphPressRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.IIchoricRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.IchorCrystallizerRecipe;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.crafting.CrushRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RecipeRegistry {

    public static final IRecipeType<IIchoricRecipe> CRYSTAL_TYPE = new RecipeType();
    public static final IRecipeType<GlyphPressRecipe> GLYPH_TYPE = new RecipeType();
    public static final IRecipeType<IPsyglyphicRecipe> PSYGLYPHIC_TYPE = new RecipeType();
    public static final IRecipeType<PsyglyphicEnchantingRecipe> ENCHANTMENT_TYPE = new RecipeType();
    public static final IRecipeType<CrushRecipe> CRUSH_TYPE = new RecipeType();

    public static final IRecipeSerializer<IchorCrystallizerRecipe> CRYSTALLIZER_SERIALIZER = new IchorCrystallizerRecipe.Serializer();
    public static final IRecipeSerializer<GlyphPressRecipe> PRESS_SERIALIZER = new GlyphPressRecipe.Serializer();
    public static final IRecipeSerializer<PsyglyphicAmalgamatorRecipe> PSYGLYPHIC_SERIALIZER = new PsyglyphicAmalgamatorRecipe.Serializer();
    public static final IRecipeSerializer<PsyglyphicEnchantingRecipe> ENCHANTMENT_SERIALIZER = new PsyglyphicEnchantingRecipe.Serializer();
    public static final IRecipeSerializer<CrushRecipe> CRUSH_SERIALIZER = new CrushRecipe.Serializer();

    @SubscribeEvent
    public static void register(final RegistryEvent.Register<IRecipeSerializer<?>> evt) {
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(EmortisConstants.MOD_ID, "ichor_crystallizer_recipe"), CRYSTAL_TYPE);
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(EmortisConstants.MOD_ID, "glyph_recipe"), GLYPH_TYPE);
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(EmortisConstants.MOD_ID, "amalgamator_recipe"), PSYGLYPHIC_TYPE);
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(EmortisConstants.MOD_ID, PsyglyphicEnchantingRecipe.RECIPE_ID), ENCHANTMENT_TYPE);
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(EmortisConstants.MOD_ID, CrushRecipe.RECIPE_ID), CRUSH_TYPE);

        evt.getRegistry().register(CRYSTALLIZER_SERIALIZER.setRegistryName(new ResourceLocation(EmortisConstants.MOD_ID, "ichor_crystallizer_recipe")));
        evt.getRegistry().register(PRESS_SERIALIZER.setRegistryName(new ResourceLocation(EmortisConstants.MOD_ID, "glyph_recipe")));
        evt.getRegistry().register(PSYGLYPHIC_SERIALIZER.setRegistryName(new ResourceLocation(EmortisConstants.MOD_ID, "amalgamator_recipe")));
        evt.getRegistry().register(ENCHANTMENT_SERIALIZER.setRegistryName(new ResourceLocation(EmortisConstants.MOD_ID, PsyglyphicEnchantingRecipe.RECIPE_ID)));
        evt.getRegistry().register(CRUSH_SERIALIZER.setRegistryName(new ResourceLocation(EmortisConstants.MOD_ID, CrushRecipe.RECIPE_ID)));
    }

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }
}
