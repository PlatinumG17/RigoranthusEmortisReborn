package com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.bedding;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.ICasingMaterial;
import javax.annotation.Nullable;
import java.util.function.Supplier;
import net.minecraft.block.Block;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CasingMaterial extends ICasingMaterial {

    private final Supplier<Block> block;
    protected ResourceLocation texture;
    @Nullable private String translationKey;

    public CasingMaterial(Supplier<Block> blockIn) {
        this.block = blockIn;
    }

    public CasingMaterial(Supplier<Block> blockIn, ResourceLocation texture) {
        this.block = blockIn;
        this.texture = texture;
    }
    /**
     * Texture location that for material, eg 'minecraft:block/oak_planks'
     */
    @Override
    public ResourceLocation getTexture() {
        if (this.texture == null) {
            ResourceLocation loc = this.block.get().getRegistryName();
            this.texture = new ResourceLocation(loc.getNamespace(), "block/" + loc.getPath());
        }
        return this.texture;
    }
    /**
     * The translation key using for the tooltip
     */
    @Override
    public ITextComponent getTooltip() {
        if (this.translationKey == null) {
            this.translationKey = Util.makeDescriptionId("canisbed.casing", RigoranthusEmortisRebornAPI.CASING_MATERIAL.getKey(this));
        }
        return new TranslationTextComponent(this.translationKey);
    }
    /**
     * The ingredient used in the crafting recipe of the bed
     */
    @Override
    public Ingredient getIngredient() {
        return Ingredient.of(this.block.get());
    }
}