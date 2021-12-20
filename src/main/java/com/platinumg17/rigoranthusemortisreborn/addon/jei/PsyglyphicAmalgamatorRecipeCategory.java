package com.platinumg17.rigoranthusemortisreborn.addon.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.PsyglyphicAmalgamatorRecipe;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PsyglyphicAmalgamatorRecipeCategory implements IRecipeCategory<PsyglyphicAmalgamatorRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(EmortisConstants.MOD_ID, "amalgamator");

    IGuiHelper helper;
    public IDrawable background;
    public IDrawable icon;
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    public PsyglyphicAmalgamatorRecipeCategory(IGuiHelper helper){
        this.helper = helper;
        background = helper.createDrawable(JEIConstants.RECIPE_GUI_AMALGAMATOR, 0, 0, 132, 84);
        icon = helper.createDrawableIngredient(new ItemStack(BlockRegistry.PSYGLYPHIC_AMALG_BLOCK));
        this.cachedArrows = CacheBuilder.newBuilder()
                .maximumSize(25)
                .build(new CacheLoader<Integer, IDrawableAnimated>() {
                    @Override
                    public IDrawableAnimated load(Integer cookTime) {
                        return helper.drawableBuilder(JEIConstants.RECIPE_GUI_AMALGAMATOR, 132, 0, 22, 15)
                                .buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
                    }
                });
    }
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class getRecipeClass() {
        return PsyglyphicAmalgamatorRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Psyglyphic Amalgamator";
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void draw(PsyglyphicAmalgamatorRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        int recipeWidth = this.background.getWidth();
        int recipeHeight = this.background.getHeight();
        IDrawableAnimated arrow = this.cachedArrows.getUnchecked(40);
        arrow.draw( matrixStack, 72, 27);
        FontRenderer renderer = Minecraft.getInstance().font;
        if(recipe.consumesDominion())
            //renderer.draw(matrixStack, new TranslationTextComponent("rigoranthusemortisreborn.dominion", recipe.dominionCost), 0.0f, 3, 71);//65, 10);
            renderer.draw(matrixStack, new TranslationTextComponent("rigoranthusemortisreborn.dominion", recipe.dominionCost), 12f, 6f, 4210752);
    }

    @Override
    public void setIngredients(PsyglyphicAmalgamatorRecipe o, IIngredients iIngredients) {
        List<List<ItemStack>> itemStacks = new ArrayList<>();
        itemStacks.add(Arrays.asList(o.reagent.getItems()));
        itemStacks.add(Collections.singletonList(o.result));
        for(Ingredient i : o.pedestalItems){
            itemStacks.add(Arrays.asList(i.getItems()));
        }
        iIngredients.setInputLists(VanillaTypes.ITEM, itemStacks);
        iIngredients.setOutput(VanillaTypes.ITEM, o.result);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, PsyglyphicAmalgamatorRecipe o, IIngredients ingredients) {
        int index = 0;
        recipeLayout.getItemStacks().init(index, true, 26, 26);
        recipeLayout.getItemStacks().set(index, ingredients.getInputs(VanillaTypes.ITEM).get(0));
        index++;
        recipeLayout.getItemStacks().init(index, true, 105, 26);
        recipeLayout.getItemStacks().set(index, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
        try {
            index++;
            recipeLayout.getItemStacks().init(index, true, 6, 6);
            recipeLayout.getItemStacks().set(index, ingredients.getInputs(VanillaTypes.ITEM).get(2));
            index++;
            recipeLayout.getItemStacks().init(index, true, 26, 6);
            recipeLayout.getItemStacks().set(index, ingredients.getInputs(VanillaTypes.ITEM).get(3));
            index++;
            recipeLayout.getItemStacks().init(index, true, 46, 6);
            recipeLayout.getItemStacks().set(index, ingredients.getInputs(VanillaTypes.ITEM).get(4));
            index++;
            recipeLayout.getItemStacks().init(index, true, 6, 26);
            recipeLayout.getItemStacks().set(index, ingredients.getInputs(VanillaTypes.ITEM).get(5));
            index++;
            recipeLayout.getItemStacks().init(index, true, 46, 26);
            recipeLayout.getItemStacks().set(index, ingredients.getInputs(VanillaTypes.ITEM).get(6));
            index++;
            recipeLayout.getItemStacks().init(index, true, 6, 46);
            recipeLayout.getItemStacks().set(index, ingredients.getInputs(VanillaTypes.ITEM).get(7));
            index++;
            recipeLayout.getItemStacks().init(index, true, 26, 46);
            recipeLayout.getItemStacks().set(index, ingredients.getInputs(VanillaTypes.ITEM).get(8));
            index++;
            recipeLayout.getItemStacks().init(index, true, 46, 46);
            recipeLayout.getItemStacks().set(index, ingredients.getInputs(VanillaTypes.ITEM).get(9));
        }catch (Exception e){}
    }
}