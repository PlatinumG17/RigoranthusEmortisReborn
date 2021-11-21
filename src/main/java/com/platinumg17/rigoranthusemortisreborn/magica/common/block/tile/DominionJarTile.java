package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.client.ITooltipProvider;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.DominionJar;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.AbstractDominionTile;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class DominionJarTile extends AbstractDominionTile implements ITickableTileEntity, ITooltipProvider {

    public DominionJarTile() {
        super(BlockRegistry.DOMINION_JAR_TILE);
    }

    public DominionJarTile(TileEntityType<? extends DominionJarTile> tileTileEntityType){
        super(tileTileEntityType);
    }

    @Override
    public int getMaxDominion() {
        return 10000;
    }

    @Override
    public void tick() {
        if(level.isClientSide) {
            return;
        }
        BlockState state = level.getBlockState(worldPosition);
        int fillState = 0;
        if(this.getCurrentDominion() > 0 && this.getCurrentDominion() < 1000)
            fillState = 1;
        else if(this.getCurrentDominion() != 0){
            fillState = (this.getCurrentDominion() / 1000) + 1;
        }
        level.setBlock(worldPosition, state.setValue(DominionJar.fill, fillState),3);
    }

    @Override
    public int getTransferRate() {
        return getMaxDominion();
    }

    @Override
    public List<String> getTooltip() {
        List<String> list = new ArrayList<>();
        list.add(new TranslationTextComponent("rigoranthusemortisreborn.dominion_jar.fullness", (getCurrentDominion()*100) / this.getMaxDominion()).getString());
        return list;
    }
}