package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.LustericShield;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShieldModel extends AnimatedGeoModel<LustericShield> {

    @Override
    public ResourceLocation getModelLocation(LustericShield wand) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "geo/shield.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LustericShield wand) {
        return  new ResourceLocation(EmortisConstants.MOD_ID, "textures/items/lusteric_shield.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LustericShield wand) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "animations/shield.json");
    }
}