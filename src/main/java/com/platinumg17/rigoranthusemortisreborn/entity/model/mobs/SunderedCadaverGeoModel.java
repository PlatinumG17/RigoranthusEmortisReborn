package com.platinumg17.rigoranthusemortisreborn.entity.model.mobs;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class SunderedCadaverGeoModel extends AnimatedGeoModel<SunderedCadaverEntity> {

//    @Override
//    public void setLivingAnimations(SunderedCadaverEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
//        super.setLivingAnimations(entity, uniqueID, customPredicate);
//        IBone head = this.getAnimationProcessor().getBone("head");
//        if (customPredicate != null) {
//            //noinspection unchecked
//            EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
//            head.setRotationY(MathHelper.clamp(extraData.netHeadYaw * ((float) Math.PI / 180F), -0.8f, 0.8f));
//        }
//    }

    @Override
    public ResourceLocation getModelLocation(SunderedCadaverEntity sunderedCadaverEntity) {
        return new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "geo/sundered_cadaver.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SunderedCadaverEntity sunderedCadaverEntity) {
        return new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "textures/entity/sundered_cadaver.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SunderedCadaverEntity sunderedCadaverEntity) {
        return new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "animations/sundered_cadaver.animation.json");
    }
}