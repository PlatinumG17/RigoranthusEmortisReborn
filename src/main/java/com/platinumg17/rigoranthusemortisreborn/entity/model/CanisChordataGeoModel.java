package com.platinumg17.rigoranthusemortisreborn.entity.model;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.canis.CanisChordataEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CanisChordataGeoModel extends AnimatedGeoModel<CanisChordataEntity> {
    private static final ResourceLocation TEXTURE_CANIS = new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "textures/entity/chordata.png");
    private static final ResourceLocation TEXTURE_KYPHOS = new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "textures/entity/kyphos.png");
    private static final ResourceLocation TEXTURE_CAVALIER = new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "textures/entity/cavalier.png");
    private static final ResourceLocation TEXTURE_HOMINI = new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "textures/entity/homini.png");

    private static final ResourceLocation ANIMATION_CANIS = new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "animations/chordata.animation.json");
    private static final ResourceLocation ANIMATION_KYPHOS = new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "animations/kyphos.animation.json");
    private static final ResourceLocation ANIMATION_CAVALIER = new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "animations/cavalier.animation.json");
    private static final ResourceLocation ANIMATION_HOMINI = new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "animations/homini.animation.json");

    private static final ResourceLocation MODEL_CANIS = new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "geo/chordata.geo.json");
    private static final ResourceLocation MODEL_KYPHOS = new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "geo/kyphos.geo.json");
    private static final ResourceLocation MODEL_CAVALIER = new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "geo/cavalier.geo.json");
    private static final ResourceLocation MODEL_HOMINI = new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "geo/homini.geo.json");

    @Override
    public ResourceLocation getModelLocation(CanisChordataEntity canisChordataEntity){
        if (canisChordataEntity.isCanis()) {
            return MODEL_CANIS;
        } else if (canisChordataEntity.isKyphos()) {
            return MODEL_KYPHOS;
        } else if (canisChordataEntity.isCavalier()) {
            return MODEL_CAVALIER;
        } else if (canisChordataEntity.isHomini()) {
            return MODEL_HOMINI;
        }
        return null;
    }

    @Override
    public ResourceLocation getTextureLocation(CanisChordataEntity canisChordataEntity){
        if (canisChordataEntity.isCanis()) {
            return TEXTURE_CANIS;
        } else if (canisChordataEntity.isKyphos()) {
            return TEXTURE_KYPHOS;
        } else if (canisChordataEntity.isCavalier()) {
            return TEXTURE_CAVALIER;
        } else if (canisChordataEntity.isHomini()) {
            return TEXTURE_HOMINI;
        }
        return null;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CanisChordataEntity canisChordataEntity){
        if (canisChordataEntity.isCanis()) {
            return ANIMATION_CANIS;
        } else if (canisChordataEntity.isKyphos()) {
            return ANIMATION_KYPHOS;
        } else if (canisChordataEntity.isCavalier()) {
            return ANIMATION_CAVALIER;
        } else if (canisChordataEntity.isHomini()) {
            return ANIMATION_HOMINI;
        }
        return null;
    }
}
