package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer;

import com.platinumg17.rigoranthusemortisreborn.canis.CanisSkills;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.Resources;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;

import static com.platinumg17.rigoranthusemortisreborn.canis.common.lib.Resources.*;

public class SaddleModel extends AnimatedGeoModel<CanisEntity> {
    public GeoModelProvider<CanisEntity> modelProvider;

    public SaddleModel(GeoModelProvider<CanisEntity> geoModelProvider) {
        this.modelProvider = geoModelProvider;
    }

    @Override public ResourceLocation getModelLocation(CanisEntity canis) { return SADDLE_MODEL; }
    @Override public ResourceLocation getAnimationFileLocation(CanisEntity canis) { return TAME_CANIS_ANIMATION; }

    @Override public ResourceLocation getTextureLocation(CanisEntity canis) {
        return getColor(canis);
    }

    public ResourceLocation getColor(CanisEntity e) {
        String color = e.getClothColor().getSaveName().toLowerCase();
        if(color.isEmpty())
            return CLOTH_RED;
        return Resources.getClothColor(color);
    }

    @Override
    public void setLivingAnimations(CanisEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone saddle = this.getAnimationProcessor().getBone("saddle");
        IBone cloth = this.getAnimationProcessor().getBone("cloth");

        saddle.setHidden(!(entity.getLevel(CanisSkills.CAVALIER.get()) >= 1) || entity.isInSittingPose() || entity.isBegging() || entity.isLying());
        cloth.setHidden(!entity.doDisplayCloth());
    }
}