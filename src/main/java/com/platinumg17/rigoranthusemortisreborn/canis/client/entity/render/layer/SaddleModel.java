package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer;

import com.platinumg17.rigoranthusemortisreborn.canis.CanisSkills;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model.CanisModel;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;

public class SaddleModel extends AnimatedGeoModel<CanisEntity> {
    public GeoModelProvider<CanisEntity> modelProvider;

    public SaddleModel(GeoModelProvider<CanisEntity> geoModelProvider) {
        this.modelProvider = geoModelProvider;
    }

    @Override
    public ResourceLocation getModelLocation(CanisEntity canis) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "geo/canis/saddle.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CanisEntity canis) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/accessories/saddle.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CanisEntity canis) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "animations/canis/tame_canis_chordata.json");
    }

    @Override
    public void setLivingAnimations(CanisEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("saddle");
        IBone canis = ((CanisModel) modelProvider).getBone("canis");
        IBone parentBody = modelProvider.getModel(modelProvider.getModelLocation(entity)).getBone("whole_body").get();
//        body.setPivotX(parentBody.getPivotX());
//        body.setPivotY(parentBody.getPivotY());
//        body.setPivotZ(parentBody.getPivotZ());
//        body.setRotationX(parentBody.getRotationX());
//        body.setRotationY(parentBody.getRotationY());
//        body.setRotationZ(parentBody.getRotationZ());
        float scale = 11f;
//        body.setPositionY(canis.getPositionY() / 16f);
//        body.setPositionZ(canis.getPositionZ() * -1.2f);
        body.setHidden(!(entity.getLevel(CanisSkills.CAVALIER.get()) >= 1));
    }
}