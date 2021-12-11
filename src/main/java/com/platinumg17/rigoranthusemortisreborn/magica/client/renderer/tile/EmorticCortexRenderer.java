package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item.FixedGeoBlockItemRenderer;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.EmorticCortexTile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class EmorticCortexRenderer extends GeoBlockRenderer<EmorticCortexTile> {

    public EmorticCortexRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new EmorticCortexModel());
    }

    @Override
    public RenderType getRenderType(EmorticCortexTile animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    public static FixedGeoBlockItemRenderer getISTER(){
        return new FixedGeoBlockItemRenderer(new EmorticCortexModel());
    }
}