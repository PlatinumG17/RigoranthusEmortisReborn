package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.Wand;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WandModel extends AnimatedGeoModel<Wand> {

    @Override
    public ResourceLocation getModelLocation(Wand wand) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "geo/wand.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Wand wand) {
        return  new ResourceLocation(EmortisConstants.MOD_ID, "textures/items/wand.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Wand wand) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "animations/wand_animation.json");
    }
}