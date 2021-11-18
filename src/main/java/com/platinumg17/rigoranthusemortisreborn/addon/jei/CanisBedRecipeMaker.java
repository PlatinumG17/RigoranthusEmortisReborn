package com.platinumg17.rigoranthusemortisreborn.addon.jei;

import com.platinumg17.rigoranthusemortisreborn.canis.common.util.CanisBedUtil;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IShapedRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.IBeddingMaterial;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.ICasingMaterial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CanisBedRecipeMaker {
    public static List<IShapedRecipe<? extends IInventory>> createCanisBedRecipes() {
        Collection<IBeddingMaterial> beddingMaterials = RigoranthusEmortisRebornAPI.BEDDING_MATERIAL.getValues();
        Collection<ICasingMaterial>  casingMaterials  = RigoranthusEmortisRebornAPI.CASING_MATERIAL.getValues();

        List<IShapedRecipe<? extends IInventory>> recipes = new ArrayList<>(beddingMaterials.size() * casingMaterials.size());
        String group = "rigoranthusemortisreborn.canisbed";
        for (IBeddingMaterial beddingId : RigoranthusEmortisRebornAPI.BEDDING_MATERIAL.getValues()) {
            for (ICasingMaterial casingId : RigoranthusEmortisRebornAPI.CASING_MATERIAL.getValues()) {

                Ingredient beddingIngredient = beddingId.getIngredient();
                Ingredient casingIngredient = casingId.getIngredient();
                NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY,
                        casingIngredient, beddingIngredient, casingIngredient,
                        casingIngredient, beddingIngredient, casingIngredient,
                        casingIngredient, casingIngredient, casingIngredient
                );
                ItemStack output = CanisBedUtil.createItemStack(casingId, beddingId);

                ResourceLocation id = REUtil.getResource("" + output.getDescriptionId()); //TODO update resource location
                ShapedRecipe recipe = new ShapedRecipe(id, group, 3, 3, inputs, output);
                recipes.add(recipe);
            }
        }
        return recipes;
    }
}
