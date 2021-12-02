package com.platinumg17.rigoranthusemortisreborn.entity.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item.FixedGeoItemRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class GhastlyScepterRenderer extends FixedGeoItemRenderer<GhastlyScepterItem> {
    public GhastlyScepterRenderer() {
        super(new GhastlyScepterModel());
    }
    @Override
    public RenderType getRenderType(Object animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}
