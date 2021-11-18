package com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class ICasingMaterial extends ForgeRegistryEntry<ICasingMaterial> {

    /**
     * Texture location that for material, eg 'minecraft:block/white_wool'
     */
    public abstract ResourceLocation getTexture();

    /**
     * The translation key using for the tooltip
     */
    public abstract ITextComponent getTooltip();

    /**
     * The ingredient used in the crafting recipe of the bed
     */
    public abstract Ingredient getIngredient();
}
