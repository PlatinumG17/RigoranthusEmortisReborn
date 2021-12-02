package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.DominionTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DominionExtractorModel<T extends DominionTile> extends AnimatedGeoModel<DominionTile> {

    public ResourceLocation modelLocation;
    public ResourceLocation textLoc;
    public ResourceLocation animationLoc = new ResourceLocation(EmortisConstants.MOD_ID, "animations/dominion_extractor_animations.json");

    public DominionExtractorModel(String name){
        this.modelLocation = new ResourceLocation(EmortisConstants.MOD_ID, "geo/" + name + ".geo.json");
        this.textLoc = new ResourceLocation(EmortisConstants.MOD_ID, "textures/blocks/" + name + ".png");
    }

    @Override
    public ResourceLocation getModelLocation(DominionTile dominionExtractor) {
        return modelLocation;
    }

    @Override
    public ResourceLocation getTextureLocation(DominionTile dominionExtractor) {
        return textLoc;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DominionTile dominionExtractor) {
        return animationLoc;
    }
}