package com.platinumg17.rigoranthusemortisreborn.entity.model.mobs;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.LanguidDwellerEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class LanguidDwellerGeoModel extends AnimatedGeoModel<LanguidDwellerEntity> {

    private static final ResourceLocation DWELLER_TEXTURE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/languid_dweller.png");
    public static final ResourceLocation DWELLER_MODEL = new ResourceLocation(EmortisConstants.MOD_ID , "geo/languid_dweller.geo.json");
    public static final ResourceLocation ANIMATIONS = new ResourceLocation(EmortisConstants.MOD_ID , "animations/languid_dweller.json");

    @Override
    public ResourceLocation getModelLocation(LanguidDwellerEntity dweller) {
        return DWELLER_MODEL;
    }

    @Override
    public ResourceLocation getTextureLocation(LanguidDwellerEntity dweller) {
        return DWELLER_TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LanguidDwellerEntity animatable) {
        return ANIMATIONS;
    }

    @Override
    public void setLivingAnimations(LanguidDwellerEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone whole_head = this.getAnimationProcessor().getBone("whole_head");
        if (customPredicate != null) {
            EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
            whole_head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
            whole_head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        }
    }
}