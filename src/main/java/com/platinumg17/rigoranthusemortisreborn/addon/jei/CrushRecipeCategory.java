package com.platinumg17.rigoranthusemortisreborn.addon.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.crafting.CrushRecipe;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect.EffectCrush;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CrushRecipeCategory implements IRecipeCategory<CrushRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(EmortisConstants.MOD_ID, "crush");

    IGuiHelper helper;
    public IDrawable background;
    public IDrawable icon;
    public List<CrushRecipe.CrushOutput> outputs = new ArrayList<>();
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    public CrushRecipeCategory(IGuiHelper helper){
        this.helper = helper;
        background = helper.createBlankDrawable(60,50);
        icon = helper.createDrawableIngredient(new ItemStack(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectCrush.INSTANCE)));
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
    public Class getRecipeClass() {
        return CrushRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("rigoranthusemortisreborn.crush_recipe").getString();
    }

    @Override
    public IDrawable getBackground() {
        return helper.createBlankDrawable(120,8 + 16 * outputs.size());
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void draw(CrushRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        IDrawableAnimated arrow = this.cachedArrows.getUnchecked(40);
        arrow.draw( matrixStack,30, 6);
        FontRenderer renderer = Minecraft.getInstance().font;
        for(int i = 0; i < recipe.outputs.size(); i++){
            renderer.draw(matrixStack, Math.round(100 * recipe.outputs.get(i).chance - 0.5f) + "%", 85f,11f + 17f * i, 10);
        }
    }

    @Override
    public void setIngredients(CrushRecipe o, IIngredients iIngredients) {
        List<List<ItemStack>> inputs = new ArrayList<>();
        inputs.add( Arrays.asList(o.input.getItems()));
        iIngredients.setInputLists(VanillaTypes.ITEM,inputs);
        iIngredients.setOutputs(VanillaTypes.ITEM, o.outputs.stream().map(c -> c.stack).collect(Collectors.toList()));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CrushRecipe o, IIngredients ingredients) {
        int index = 0;
        this.outputs = o.outputs;
        recipeLayout.getItemStacks().init(index, true, 6, 5);
        recipeLayout.getItemStacks().set(index, ingredients.getInputs(VanillaTypes.ITEM).get(0));
        index++;

        for(int i = 0; i < ingredients.getOutputs(VanillaTypes.ITEM).size(); i++){
            recipeLayout.getItemStacks().init(index, true, 60, 5+ 16 * i);
            recipeLayout.getItemStacks().set(index, ingredients.getOutputs(VanillaTypes.ITEM).get(i));
            index++;
        }
    }
}