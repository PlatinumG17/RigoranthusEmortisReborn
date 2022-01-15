package com.platinumg17.rigoranthusemortisreborn.entity.model.mobs.canis;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.FeralCanisEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import javax.annotation.Nullable;

public class FeralCanisGeoModel extends AnimatedGeoModel<FeralCanisEntity> {
    private static final ResourceLocation TEXTURE_CANIS = RigoranthusEmortisReborn.rl("textures/entity/canis/feral_canis_chordata.png");
    private static final ResourceLocation TEXTURE_KYPHOS = RigoranthusEmortisReborn.rl("textures/entity/kyphos.png");
    private static final ResourceLocation TEXTURE_CAVALIER = RigoranthusEmortisReborn.rl("textures/entity/cavalier.png");
    private static final ResourceLocation TEXTURE_HOMINI = RigoranthusEmortisReborn.rl("textures/entity/homini.png");

    private static final ResourceLocation ANIMATION_CANIS = RigoranthusEmortisReborn.rl("animations/canis/feral_canis_chordata.json");
    private static final ResourceLocation ANIMATION_KYPHOS = RigoranthusEmortisReborn.rl("animations/kyphos.animation.json");
    private static final ResourceLocation ANIMATION_CAVALIER = RigoranthusEmortisReborn.rl("animations/cavalier.animation.json");
    private static final ResourceLocation ANIMATION_HOMINI = RigoranthusEmortisReborn.rl("animations/homini.animation.json");

    private static final ResourceLocation MODEL_CANIS = RigoranthusEmortisReborn.rl("geo/canis/feral_canis_chordata.geo.json");
    private static final ResourceLocation MODEL_KYPHOS = RigoranthusEmortisReborn.rl("geo/kyphos.geo.json");
    private static final ResourceLocation MODEL_CAVALIER = RigoranthusEmortisReborn.rl("geo/cavalier.geo.json");
    private static final ResourceLocation MODEL_HOMINI = RigoranthusEmortisReborn.rl("geo/homini.geo.json");

    @Override
    public ResourceLocation getModelLocation(FeralCanisEntity canisChordataEntity) {
//        if (canisChordataEntity.isCanis()) {
            return MODEL_CANIS;
//        } else if (canisChordataEntity.isKyphos()) {
//            return MODEL_KYPHOS;
//        } else if (canisChordataEntity.isCavalier()) {
//            return MODEL_CAVALIER;
//        } else if (canisChordataEntity.isHomini()) {
//            return MODEL_HOMINI;
//        }
//        return null;
    }

    @Override
    public ResourceLocation getTextureLocation(FeralCanisEntity canisChordataEntity) {
//        if (canisChordataEntity.isCanis()) {
            return TEXTURE_CANIS;
//        } else if (canisChordataEntity.isKyphos()) {
//            return TEXTURE_KYPHOS;
//        } else if (canisChordataEntity.isCavalier()) {
//            return TEXTURE_CAVALIER;
//        } else if (canisChordataEntity.isHomini()) {
//            return TEXTURE_HOMINI;
//        }
//        return null;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FeralCanisEntity canisChordataEntity) {
//        if (canisChordataEntity.isCanis()) {
            return ANIMATION_CANIS;
//        } else if (canisChordataEntity.isKyphos()) {
//            return ANIMATION_KYPHOS;
//        } else if (canisChordataEntity.isCavalier()) {
//            return ANIMATION_CAVALIER;
//        } else if (canisChordataEntity.isHomini()) {
//            return ANIMATION_HOMINI;
//        }
//        return null;
    }
    @Override
    public void setLivingAnimations(FeralCanisEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");
        if (customPredicate != null) {
            EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 120F));
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 120F));
        }
    }
}