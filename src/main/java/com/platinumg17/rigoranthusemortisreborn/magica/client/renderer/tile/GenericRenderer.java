package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item.GenericItemRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import java.util.concurrent.Callable;

public class GenericRenderer extends GeoBlockRenderer {

    public static GenericModel model = new GenericModel("emortic_relay");

    public GenericRenderer(TileEntityRendererDispatcher rendererDispatcherIn, String loc) {
        super(rendererDispatcherIn, new GenericModel(loc));
    }

    public static Callable<ItemStackTileEntityRenderer> getISTER(String loc){
        return () -> new GenericItemRenderer(new GenericModel(loc));
    }
    @Override
    public RenderType getRenderType(Object animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}