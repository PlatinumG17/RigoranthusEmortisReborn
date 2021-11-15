package com.platinumg17.rigoranthusemortisreborn.entity.item;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GhastlyScepterModel extends AnimatedGeoModel<GhastlyScepterItem> {
    @Override
    public ResourceLocation getModelLocation(GhastlyScepterItem object) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "geo/ghastly_scepter.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GhastlyScepterItem object) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/items/ghastly_scepter.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GhastlyScepterItem animatable) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "animations/ghastly_scepter.animation.json");
    }
}
