package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.familiar.FamiliarCadaver;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class FamiliarCadaverRenderer extends GeoEntityRenderer<FamiliarCadaver> {

    private static final ResourceLocation CADAVER_TEXTURE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/sundered_cadaver.png");

    public FamiliarCadaverRenderer(EntityRendererManager manager) {super(manager, new FamiliarCadaverModel());}

    @Override
    protected void applyRotations (FamiliarCadaver entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks){
            super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    public void render (FamiliarCadaver entity,float entityYaw, float p_225623_3_, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer,int p_225623_6_){
            super.render(entity, entityYaw, p_225623_3_, matrixStack, iRenderTypeBuffer, p_225623_6_);
    }

    public ResourceLocation getColor (FamiliarCadaver e){
            return new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/sundered_cadaver.png");
    }

    @Override
    public ResourceLocation getTextureLocation (FamiliarCadaver entity){
            return CADAVER_TEXTURE;
    }

    @Override
    public RenderType getRenderType (FamiliarCadaver animatable, float partialTicks, MatrixStack
            stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation){
            return RenderType.entityCutoutNoCull(textureLocation);
    }

    public static class FamiliarCadaverModel extends AnimatedGeoModel<FamiliarCadaver> {

     private final ResourceLocation CADAVER_TEXTURE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/sundered_cadaver.png");

     @Override
     public void setLivingAnimations(FamiliarCadaver entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
              super.setLivingAnimations(entity, uniqueID, customPredicate);
                IBone head = this.getAnimationProcessor().getBone("head");
                EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
                head.setRotationX(extraData.headPitch * 0.017453292F);
                head.setRotationY(extraData.netHeadYaw * 0.017453292F);
     }
     @Override
     public ResourceLocation getModelLocation(FamiliarCadaver carbuncle) {
         return new ResourceLocation(EmortisConstants.MOD_ID, "geo/sundered_cadaver.geo.json");
     }

     @Override
     public ResourceLocation getTextureLocation(FamiliarCadaver carbuncle) {
         return CADAVER_TEXTURE;
     }

     @Override
     public ResourceLocation getAnimationFileLocation(FamiliarCadaver carbuncle) {
         return new ResourceLocation(EmortisConstants.MOD_ID, "animations/sundered_cadaver.animation.json");
     }
    }
}