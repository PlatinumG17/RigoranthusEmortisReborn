package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.GlyphPressTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PressModel extends AnimatedGeoModel<GlyphPressTile> {
    @Override
    public ResourceLocation getModelLocation(GlyphPressTile extractorTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "geo/glyph_press.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GlyphPressTile extractorTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/blocks/glyph_press.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GlyphPressTile extractorTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "animations/press_animations.json");
    }
}