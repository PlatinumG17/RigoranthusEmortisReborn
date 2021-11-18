package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;

public class CreativeDominionJarTile extends DominionJarTile {

    public CreativeDominionJarTile(){
        super(BlockRegistry.CREATIVE_JAR_TILE);
    }
    @Override
    public int getCurrentDominion() {
        return this.getMaxDominion();
    }
}