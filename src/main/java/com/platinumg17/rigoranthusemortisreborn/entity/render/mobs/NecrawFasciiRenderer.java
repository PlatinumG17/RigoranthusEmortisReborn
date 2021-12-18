package com.platinumg17.rigoranthusemortisreborn.entity.render.mobs;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.NecrawFasciiEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.model.mobs.NecrawFasciiGeoModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class NecrawFasciiRenderer extends GeoEntityRenderer<NecrawFasciiEntity> {

    public NecrawFasciiRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NecrawFasciiGeoModel());
        this.shadowRadius = 0.7F;
    }

    @Override
    public RenderType getRenderType(NecrawFasciiEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(this.getTextureLocation(animatable));
    }
}