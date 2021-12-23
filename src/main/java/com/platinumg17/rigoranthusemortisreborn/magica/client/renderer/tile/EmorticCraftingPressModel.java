package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.EmorticCraftingPressTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EmorticCraftingPressModel extends AnimatedGeoModel<EmorticCraftingPressTile> {
    @Override
    public ResourceLocation getModelLocation(EmorticCraftingPressTile crystallizerTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "geo/emortic_crafting_press.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EmorticCraftingPressTile crystallizerTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/blocks/emortic_crafting_press.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EmorticCraftingPressTile crystallizerTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "animations/emortic_crafting_press_animations.json");
    }
}