package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GenericModel<T extends IAnimatable> extends AnimatedGeoModel<T> {
    public String path;

    public ResourceLocation modelLocation;
    public ResourceLocation textLoc;
    public ResourceLocation animationLoc;
    public String textPathRoot = "blocks";
    public GenericModel(String name){
        this.modelLocation = new ResourceLocation(EmortisConstants.MOD_ID , "geo/" + name + ".geo.json");
        this.textLoc = new ResourceLocation(EmortisConstants.MOD_ID, "textures/" + textPathRoot + "/" + name + ".png");
        this.animationLoc = new ResourceLocation(EmortisConstants.MOD_ID , "animations/" + name + "_animations.json");
    }

    public GenericModel(String name, String textPath){
        this(name);
        this.textPathRoot = textPath;
        this.textLoc = new ResourceLocation(EmortisConstants.MOD_ID, "textures/" + textPathRoot + "/" + name + ".png");
    }

    @Override
    public ResourceLocation getModelLocation(T iAnimatable) {
        return modelLocation;
    }

    @Override
    public ResourceLocation getTextureLocation(T iAnimatable) {
        return textLoc;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(T iAnimatable) {
        return animationLoc;
    }
}