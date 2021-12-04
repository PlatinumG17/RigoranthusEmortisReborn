package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item.GenericItemRenderer;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.PsyglyphicCipherTile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class PsyglyphicRenderer extends GeoBlockRenderer<PsyglyphicCipherTile> {

    public PsyglyphicRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new PsyglyphicCipherModel());
    }

    @Override
    public RenderType getRenderType(PsyglyphicCipherTile animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public boolean shouldRenderOffScreen(TileEntity p_188185_1_) {
        return false;
    }

    public static GenericItemRenderer getISTER(){
        return new GenericItemRenderer(new PsyglyphicCipherModel());
    }
}