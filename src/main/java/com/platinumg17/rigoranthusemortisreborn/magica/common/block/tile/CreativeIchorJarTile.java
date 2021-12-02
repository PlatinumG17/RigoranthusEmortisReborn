package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;

public class CreativeIchorJarTile extends IchorJarTile {
    public CreativeIchorJarTile(){
        super(BlockRegistry.CREATIVE_ICHOR_JAR_TILE);
    }
    @Override
    public int getCurrentIchor() {
        return this.getMaxIchor();
    }
}