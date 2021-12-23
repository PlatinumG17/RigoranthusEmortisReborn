package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.RelaySplitterTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RelaySplitterModel extends AnimatedGeoModel<RelaySplitterTile> {

    @Override
    public ResourceLocation getModelLocation(RelaySplitterTile relaySplitterTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "geo/relays/relay_splitter.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RelaySplitterTile relaySplitterTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/blocks/relay_splitter.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RelaySplitterTile relaySplitterTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "animations/relays/relay_splitter_animations.json");
    }
}