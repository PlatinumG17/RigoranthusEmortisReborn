package com.platinumg17.rigoranthusemortisreborn.magica.common.datagen;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.IchorCrystallizerRecipe;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class IchorCrystallizerRecipeBuilder {

    IchorCrystallizerRecipe recipe;
    public IchorCrystallizerRecipeBuilder() { this.recipe = new IchorCrystallizerRecipe(); }

    public static IchorCrystallizerRecipeBuilder builder() { return new IchorCrystallizerRecipeBuilder(); }

    public IchorCrystallizerRecipeBuilder withResult(IItemProvider output) {
        this.recipe.output = new ItemStack(output);
        return this;
    }
    public IchorCrystallizerRecipeBuilder withResult(ItemStack output){
        this.recipe.output = output;
        return this;
    }

    public IchorCrystallizerRecipeBuilder withCategory(RigoranthusEmortisRebornAPI.PatchouliCategories category){
        this.recipe.category = category.name();
        return this;
    }

    public IchorCrystallizerRecipeBuilder withReagent(IItemProvider reagentProvider){
        this.recipe.reagent = Ingredient.of(reagentProvider);
        return this;
    }

    public IchorCrystallizerRecipeBuilder withReagent(Ingredient reagentItem){
        this.recipe.reagent = reagentItem;
        return this;
    }

    public IchorCrystallizerRecipeBuilder withBaseItem(Ingredient baseItem){
        this.recipe.base = baseItem;
        return this;
    }

    public IchorCrystallizerRecipeBuilder withBaseItem(IItemProvider baseProvider){
        this.recipe.base = Ingredient.of(baseProvider);
        return this;
    }

    public IchorCrystallizerRecipe build(){
        if(recipe.id.getPath().equals("empty"))
            recipe.id = new ResourceLocation(EmortisConstants.MOD_ID, recipe.output.getItem().getRegistryName().getPath());
        return recipe;
    }
}
