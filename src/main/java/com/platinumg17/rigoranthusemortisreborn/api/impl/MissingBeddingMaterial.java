package com.platinumg17.rigoranthusemortisreborn.api.impl;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import com.platinumg17.rigoranthusemortisreborn.api.registry.IBeddingMaterial;

public class MissingBeddingMaterial extends IBeddingMaterial {

    public static final IBeddingMaterial NULL = new MissingBeddingMaterial();
    private static final ResourceLocation MISSING_TEXTURE = new ResourceLocation("missingno");

    @Override
    public ResourceLocation getTexture() {
        return MissingBeddingMaterial.MISSING_TEXTURE;
    }

    @Override
    public ITextComponent getTooltip() {
        return new TranslationTextComponent("canisbed.bedding.missing", this.getRegistryName());
    }

    @Override
    public Ingredient getIngredient() {
        return Ingredient.EMPTY;
    }
}