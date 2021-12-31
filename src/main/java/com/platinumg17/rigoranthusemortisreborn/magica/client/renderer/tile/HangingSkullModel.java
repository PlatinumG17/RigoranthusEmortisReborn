package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.blocks.tileentity.HangingSkullTile;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HangingSkullModel extends AnimatedGeoModel<HangingSkullTile> {
    @Override
    public ResourceLocation getModelLocation(HangingSkullTile skullTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID,"geo/hanging_cadaver_skull.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HangingSkullTile skullTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/blocks/cadaver_skull.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HangingSkullTile skullTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "animations/cadaver_skull.json");
    }
}