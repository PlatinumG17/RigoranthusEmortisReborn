package com.platinumg17.rigoranthusemortisreborn.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.entity.DelphicBloomEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.model.DelphicBloomModel;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DelphicBloomRenderer extends GeoEntityRenderer<DelphicBloomEntity> {
    public DelphicBloomRenderer(EntityRendererManager renderManager) {
        super(renderManager, new DelphicBloomModel());
    }

    @Override
    public RenderType getRenderType(DelphicBloomEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}