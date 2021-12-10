package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.TableTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TableModel extends AnimatedGeoModel<TableTile> {
    @Override
    public ResourceLocation getModelLocation(TableTile extractorTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "geo/table.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TableTile extractorTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/blocks/table.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TableTile extractorTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "animations/dominion_splitter_animation.json");
    }
}
