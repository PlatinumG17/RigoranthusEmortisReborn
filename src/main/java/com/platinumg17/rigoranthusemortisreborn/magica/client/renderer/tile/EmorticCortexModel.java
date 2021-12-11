package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.EmorticCortexTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EmorticCortexModel extends AnimatedGeoModel<EmorticCortexTile> {

    @Override
    public ResourceLocation getModelLocation(EmorticCortexTile emorticCortexTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "geo/emortic_cortex.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EmorticCortexTile emorticCortexTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/blocks/emortic_cortex.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EmorticCortexTile emorticCortexTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "animations/emortic_cortex.json");
    }
}