package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item.GenericItemRenderer;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.RitualTile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class RitualRenderer extends TileEntityRenderer<RitualTile> {
    public RitualRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(RitualTile p_225616_1_, float p_225616_2_, MatrixStack p_225616_3_, IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {
    }

    public static GenericItemRenderer getISTER(){
        return new GenericItemRenderer(new RitualVesselModel());
    }
}