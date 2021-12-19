package com.platinumg17.rigoranthusemortisreborn.entity.model.mobs;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class SunderedCadaverGeoModel extends AnimatedGeoModel<SunderedCadaverEntity> {

    private static final ResourceLocation CADAVER_TEXTURE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/sundered_cadaver.png");
    public static final ResourceLocation CADAVER_MODEL = new ResourceLocation(EmortisConstants.MOD_ID , "geo/sundered_cadaver.geo.json");
    public static final ResourceLocation ANIMATIONS = new ResourceLocation(EmortisConstants.MOD_ID , "animations/sundered_cadaver.json");

    @Override
    public ResourceLocation getModelLocation(SunderedCadaverEntity cadaver) {
        return CADAVER_MODEL;
    }

    @Override
    public ResourceLocation getTextureLocation(SunderedCadaverEntity cadaver) {
        return CADAVER_TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SunderedCadaverEntity animatable) {
        return ANIMATIONS;
    }

    @Override
    public void setLivingAnimations(SunderedCadaverEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");
        if (customPredicate != null) {
            EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 240F));
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 240F));
        }
    }
}