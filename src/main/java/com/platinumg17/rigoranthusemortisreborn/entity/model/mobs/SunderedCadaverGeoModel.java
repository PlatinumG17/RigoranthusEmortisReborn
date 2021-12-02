package com.platinumg17.rigoranthusemortisreborn.entity.model.mobs;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SunderedCadaverGeoModel extends AnimatedGeoModel<SunderedCadaverEntity> {
    public SunderedCadaverGeoModel() {}
    private static final ResourceLocation CADAVER_TEXTURE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/sundered_cadaver.png");
    public static final ResourceLocation NORMAL_MODEL = new ResourceLocation(EmortisConstants.MOD_ID , "geo/sundered_cadaver.geo.json");
    public static final ResourceLocation ANIMATIONS = new ResourceLocation(EmortisConstants.MOD_ID , "animations/sundered_cadaver.animation.json");

    @Override
    public ResourceLocation getModelLocation(SunderedCadaverEntity object) {
        return NORMAL_MODEL;
    }

    @Override
    public ResourceLocation getTextureLocation(SunderedCadaverEntity object) {
        return CADAVER_TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SunderedCadaverEntity animatable) {
        return ANIMATIONS;
    }
}