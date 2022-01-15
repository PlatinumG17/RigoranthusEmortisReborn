package com.platinumg17.rigoranthusemortisreborn.entity.render.mobs;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SummonedCadaver;
import com.platinumg17.rigoranthusemortisreborn.entity.model.mobs.SummonedCadaverGeoModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SummonedCadaverRenderer extends GeoEntityRenderer<SummonedCadaver> {

    public SummonedCadaverRenderer(EntityRendererManager manager) {
        super(manager, new SummonedCadaverGeoModel());
        this.shadowRadius = 0.5F;
    }
    @Override
    public RenderType getRenderType(SummonedCadaver animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(this.getTextureLocation(animatable));
    }
}