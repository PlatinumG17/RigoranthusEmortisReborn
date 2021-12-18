package com.platinumg17.rigoranthusemortisreborn.entity.model.mobs;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.NecrawFasciiEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class NecrawFasciiGeoModel extends AnimatedGeoModel<NecrawFasciiEntity> {

    private static final ResourceLocation NECRAW_TEXTURE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/necraw_fascii.png");
    public static final ResourceLocation NECRAW_MODEL = new ResourceLocation(EmortisConstants.MOD_ID , "geo/necraw_fascii.geo.json");
    public static final ResourceLocation ANIMATIONS = new ResourceLocation(EmortisConstants.MOD_ID , "animations/necraw_fascii.json");

    @Override
    public ResourceLocation getModelLocation(NecrawFasciiEntity necraw) {
        return NECRAW_MODEL;
    }

    @Override
    public ResourceLocation getTextureLocation(NecrawFasciiEntity necraw) {
        return NECRAW_TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NecrawFasciiEntity animatable) {
        return ANIMATIONS;
    }

    @Override
    public void setLivingAnimations(NecrawFasciiEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        if (customPredicate != null) {
            EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
            head.setRotationY(-(extraData.netHeadYaw * ((float) Math.PI / 240F)));
            head.setRotationX(-(extraData.headPitch * ((float) Math.PI / 240F)));
        }
    }
}