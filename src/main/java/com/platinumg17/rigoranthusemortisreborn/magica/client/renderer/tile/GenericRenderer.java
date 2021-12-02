package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item.GenericItemRenderer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
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
}