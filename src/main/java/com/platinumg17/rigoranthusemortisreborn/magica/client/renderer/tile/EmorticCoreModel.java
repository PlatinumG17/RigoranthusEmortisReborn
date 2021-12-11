package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.EmorticCoreTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EmorticCoreModel extends AnimatedGeoModel<EmorticCoreTile> {

    @Override
    public ResourceLocation getModelLocation(EmorticCoreTile emorticCoreTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "geo/emortic_core.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EmorticCoreTile emorticCoreTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/blocks/emortic_core.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EmorticCoreTile emorticCoreTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "animations/emortic_core.json");
    }
}