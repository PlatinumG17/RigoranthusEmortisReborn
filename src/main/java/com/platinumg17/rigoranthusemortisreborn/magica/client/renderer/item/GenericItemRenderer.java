package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.AnimBlockItem;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

import javax.annotation.Nullable;

public class GenericItemRenderer extends GeoItemRenderer<AnimBlockItem> {

    public boolean isTranslucent;
    public GenericItemRenderer(AnimatedGeoModel modelProvider) {
        super(new GenericItemModel(modelProvider));
    }

    public GenericItemRenderer withTranslucency(){
        this.isTranslucent = true;
        return this;
    }

    @Override
    public RenderType getRenderType(AnimBlockItem animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return this.isTranslucent ? RenderType.entityTranslucent(textureLocation) : super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}