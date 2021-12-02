package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.Adonis;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AdonisModel extends AnimatedGeoModel<Adonis> {

    @Override
    public ResourceLocation getModelLocation(Adonis wand) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "geo/adonis.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Adonis wand) {
        return  new ResourceLocation(EmortisConstants.MOD_ID, "textures/items/adonis.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Adonis wand) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "animations/wand_animation.json");
    }
}