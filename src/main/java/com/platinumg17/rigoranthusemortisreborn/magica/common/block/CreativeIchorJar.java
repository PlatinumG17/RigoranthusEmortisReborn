package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.CreativeIchorJarTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class CreativeIchorJar extends IchorJar {
    public CreativeIchorJar(){
        super(defaultProperties().noOcclusion(), LibBlockNames.CREATIVE_ICHOR_JAR);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CreativeIchorJarTile();
    }
}
