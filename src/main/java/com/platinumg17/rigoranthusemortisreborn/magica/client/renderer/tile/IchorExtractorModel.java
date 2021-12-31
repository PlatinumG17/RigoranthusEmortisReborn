package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IchorTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class IchorExtractorModel<T extends IchorTile> extends AnimatedGeoModel<IchorTile> {

    public ResourceLocation modelLocation;
    public ResourceLocation textLoc;
    public ResourceLocation animationLoc = new ResourceLocation(EmortisConstants.MOD_ID, "animations/ichor_extractor_animations.json");

    public IchorExtractorModel(String name){
        this.modelLocation = new ResourceLocation(EmortisConstants.MOD_ID, "geo/" + name + ".geo.json");
        this.textLoc = new ResourceLocation(EmortisConstants.MOD_ID, "textures/blocks/" + "ichor_crystallizer" + ".png");
    }

    @Override
    public ResourceLocation getModelLocation(IchorTile ichorExtractor) {
        return modelLocation;
    }

    @Override
    public ResourceLocation getTextureLocation(IchorTile ichorExtractor) {
        return textLoc;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(IchorTile ichorExtractor) {
        return animationLoc;
    }
}