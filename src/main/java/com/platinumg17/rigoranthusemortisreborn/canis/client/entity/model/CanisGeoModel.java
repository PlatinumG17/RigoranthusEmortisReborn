package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model;

//import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
//import net.minecraft.util.ResourceLocation;
//import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
//import software.bernie.geckolib3.core.processor.IBone;
//import software.bernie.geckolib3.model.AnimatedGeoModel;
//import software.bernie.geckolib3.model.provider.data.EntityModelData;
//
//import javax.annotation.Nullable;
//
//public class CanisGeoModel extends AnimatedGeoModel<CanisEntity> {
//    String type;
//
//    public CanisGeoModel(String type){
//        super();
//        this.type = type;
//    }
//
//    private static final ResourceLocation SUNGLASSES_TEXTURE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/evolutions/canis_chordata_sunglasses.png");
//    private static final ResourceLocation TAMED_TEXTURE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/evolutions/canis_chordata.png");
//
//    @Override
//    public ResourceLocation getModelLocation(CanisEntity canis) {
//        return canis.isBaby() ? new ResourceLocation(EmortisConstants.MOD_ID , "geo/" + type + "_sunglasses.geo.json") : new ResourceLocation(EmortisConstants.MOD_ID , "geo/" + type + "_canis.geo.json");
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(CanisEntity canis) {
//        return canis.isBaby() ? new ResourceLocation(EmortisConstants.MOD_ID , "textures/entity/" + type + "_waddler.png") :new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/" + type + "_canis.png");
//    }
//
//    @Override
//    public ResourceLocation getAnimationFileLocation(CanisEntity canis) {
//        return canis.isBaby() ? new ResourceLocation(EmortisConstants.MOD_ID , "animations/chordata.animation.json") :new ResourceLocation(EmortisConstants.MOD_ID , "animations/chordata.animation.json");
//    }
//
////    @Override
////    public ResourceLocation getModelLocation(CanisEntity canis) {
////        return new ResourceLocation(EmortisConstants.MOD_ID , "geo/chordata.geo.json");
////    }
////
////    @Override
////    public ResourceLocation getTextureLocation(CanisEntity canis) {
////        return canis.hasSunglasses() ? TAMED_TEXTURE : SUNGLASSES_TEXTURE;
////    }
////
////    @Override
////    public ResourceLocation getAnimationFileLocation(CanisEntity canis) {
////        return new ResourceLocation(EmortisConstants.MOD_ID , "animations/chordata.animation.json");
////    }
//
//    //    @Override
//    //    public void setLivingAnimations(CanisEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
//    //        super.setLivingAnimations(entity, uniqueID, customPredicate);
//    //        if(entity.hasSunglasses())
//    //            return;
//    //        IBone head = this.getAnimationProcessor().getBone("head");
//    //        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
//    //        head.setRotationX(extraData.headPitch * 0.017453292F);
//    //        head.setRotationY(extraData.netHeadYaw * 0.017453292F);
//    //    }
//}







//    public static int getId(CanisEvolutionLevels currentLevel) {
//        if (currentLevel == CanisEvolutionLevels.CHORDATA) {
//            return 1;
//        } else if (currentLevel == CanisEvolutionLevels.KYPHOS) {
//            return 2;
//        } else if (currentLevel == CanisEvolutionLevels.CAVALIER) {
//            return 3;
//        } else if (currentLevel == CanisEvolutionLevels.HOMINI) {
//            return 4;
//        }
//        return 1;
//    }