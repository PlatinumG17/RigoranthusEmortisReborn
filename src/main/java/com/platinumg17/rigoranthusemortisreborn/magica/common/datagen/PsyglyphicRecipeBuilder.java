package com.platinumg17.rigoranthusemortisreborn.magica.common.datagen;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.PsyglyphicAmalgamatorRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.PsyglyphicEnchantingRecipe;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class PsyglyphicRecipeBuilder {
    PsyglyphicAmalgamatorRecipe recipe;
    public PsyglyphicRecipeBuilder(){
        this.recipe = new PsyglyphicAmalgamatorRecipe();
    }

    public static PsyglyphicRecipeBuilder builder(){
        return new PsyglyphicRecipeBuilder();
    }
    public PsyglyphicRecipeBuilder withResult(IItemProvider result){
        this.recipe.result = new ItemStack(result);
        return this;
    }
    public PsyglyphicRecipeBuilder withResult(ItemStack result){
        this.recipe.result = result;
        return this;
    }

//    public PsyglyphicRecipeBuilder withCategory(RigoranthusEmortisRebornAPI.PatchouliCategories category){
//        this.recipe.category = category.name();
//        return this;
//    }
    public PsyglyphicRecipeBuilder withReagent(IItemProvider provider){
        this.recipe.reagent = Ingredient.of(provider);
        return this;
    }

    public PsyglyphicRecipeBuilder withReagent(Ingredient ingredient){
        this.recipe.reagent = ingredient;
        return this;
    }

    public PsyglyphicRecipeBuilder withPedestalItem(Ingredient i){
        this.recipe.pedestalItems.add(i);
        return this;
    }

    public PsyglyphicRecipeBuilder withPedestalItem(IItemProvider i){
        return this.withPedestalItem(Ingredient.of(i));
    }

    public PsyglyphicRecipeBuilder withPedestalItem(int count, IItemProvider item){
        for(int i = 0; i < count; i++)
            this.withPedestalItem(item);
        return this;
    }

    public PsyglyphicRecipeBuilder withPedestalItem(int count, Ingredient ingred){
        for(int i = 0; i < count; i++)
            this.withPedestalItem(ingred);
        return this;
    }

    public PsyglyphicAmalgamatorRecipe build(){
        if(recipe.id.getPath().equals("empty"))
            recipe.id = new ResourceLocation(EmortisConstants.MOD_ID, recipe.result.getItem().getRegistryName().getPath());
        return recipe;
    }

    public PsyglyphicEnchantingRecipe buildPsyglyphicRecipe(Enchantment enchantment, int level, int dominion){
        return new PsyglyphicEnchantingRecipe(this.recipe.pedestalItems, enchantment, level, dominion);
    }
}