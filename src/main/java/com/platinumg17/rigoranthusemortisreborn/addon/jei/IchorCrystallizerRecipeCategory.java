package com.platinumg17.rigoranthusemortisreborn.addon.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.IchorCrystallizerRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.IchorCrystallizerRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ISpellTier;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IchorCrystallizerRecipeCategory implements IRecipeCategory<IchorCrystallizerRecipe> {
    public IDrawable background;
    public IDrawable icon;
    IGuiHelper helper;
    public final static ResourceLocation UID = new ResourceLocation(EmortisConstants.MOD_ID, "ichor_crystallizer_recipe");
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    public IchorCrystallizerRecipeCategory(IGuiHelper helper){
        this.helper = helper;
        background = helper.createBlankDrawable(60,30);
        icon = helper.createDrawableIngredient(new ItemStack(BlockRegistry.CRYSTALLIZER_BLOCK));
        this.cachedArrows = CacheBuilder.newBuilder()
                .maximumSize(25)
                .build(new CacheLoader<Integer, IDrawableAnimated>() {
                    @Override
                    public IDrawableAnimated load(Integer cookTime) {
                        return helper.drawableBuilder(JEIConstants.RECIPE_GUI_VANILLA, 82, 128, 24, 17)
                                .buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
                    }
                });
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends IchorCrystallizerRecipe> getRecipeClass() {
        return IchorCrystallizerRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("block.rigoranthusemortisreborn.ichor_crystallizer").getString();
    }

    @Override
    public IDrawable getBackground() {
        return helper.createBlankDrawable(80,30);
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void draw(IchorCrystallizerRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        IDrawableAnimated arrow = this.cachedArrows.getUnchecked(40);
        arrow.draw( matrixStack,38, 6);
    }

    @Override
    public void setIngredients(IchorCrystallizerRecipe ichorCrystallizerRecipe, IIngredients iIngredients) {
        List<List<ItemStack>> itemStacks = new ArrayList<>();
        itemStacks.add(Arrays.asList(ichorCrystallizerRecipe.base.getItems()));
        itemStacks.add(Arrays.asList(ichorCrystallizerRecipe.reagent.getItems()));
        itemStacks.add(Collections.singletonList(ichorCrystallizerRecipe.output));
        iIngredients.setInputLists(VanillaTypes.ITEM, itemStacks);
        iIngredients.setOutput(VanillaTypes.ITEM, ichorCrystallizerRecipe.output);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IchorCrystallizerRecipe o, IIngredients ingredients) {
        int index = 0;
        recipeLayout.getItemStacks().init(index, true, 0, 4);
        recipeLayout.getItemStacks().set(index, ingredients.getInputs(VanillaTypes.ITEM).get(0));
        index++;
        recipeLayout.getItemStacks().init(index, true, 16, 4);
        recipeLayout.getItemStacks().set(index, ingredients.getInputs(VanillaTypes.ITEM).get(1));
        index++;
        recipeLayout.getItemStacks().init(index, true, 64, 4);
        recipeLayout.getItemStacks().set(index, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
    }
}