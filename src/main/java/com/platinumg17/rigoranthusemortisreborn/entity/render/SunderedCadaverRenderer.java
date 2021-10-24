package com.platinumg17.rigoranthusemortisreborn.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.model.SunderedCadaverGeoModel;
import com.platinumg17.rigoranthusemortisreborn.entity.model.SunderedCadaverModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SunderedCadaverRenderer extends GeoEntityRenderer<SunderedCadaverEntity> {

    public SunderedCadaverRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SunderedCadaverGeoModel());
        this.shadowRadius = 0.7F;
    }

    @Override
    public RenderType getRenderType(SunderedCadaverEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(this.getTextureLocation(animatable));
    }
}


//public class SunderedCadaverRenderer extends MobRenderer<SunderedCadaverEntity, SunderedCadaverModel<SunderedCadaverEntity>>
//{
//    protected static final ResourceLocation TEXTURE =
//            new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "textures/entity/sundered_cadaver.png");
//
//    public SunderedCadaverRenderer(EntityRendererManager renderManagerIn) {
//        super(renderManagerIn, new SunderedCadaverModel<>(), 0.7F);
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(SunderedCadaverEntity entity) {
//        return TEXTURE;
//    }
//}