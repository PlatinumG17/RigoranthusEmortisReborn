package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.client.ITooltipProvider;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.AbstractIchorTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.IchorJar;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class IchorJarTile extends AbstractIchorTile implements ITickableTileEntity, ITooltipProvider {
    public IchorJarTile() {
        super(BlockRegistry.ICHOR_JAR_TILE);
    }

    public IchorJarTile(TileEntityType<? extends IchorJarTile> tileTileEntityType){
        super(tileTileEntityType);
    }

    @Override
    public int getMaxIchor() {
        return 10000;
    }

    @Override
    public void tick() {
        if(level.isClientSide) {
            return;
        }
        BlockState state = level.getBlockState(worldPosition);
        int fillState = 0;
        if(this.getCurrentIchor() > 0 && this.getCurrentIchor() < 1000)
            fillState = 1;
        else if(this.getCurrentIchor() != 0){
            fillState = (this.getCurrentIchor() / 1000) + 1;
        }
        level.setBlock(worldPosition, state.setValue(IchorJar.fill, fillState),3);
    }

    @Override
    public int getTransferRate() {
        return getMaxIchor();
    }

    @Override
    public List<String> getTooltip() {
        List<String> list = new ArrayList<>();
        list.add(new TranslationTextComponent("block.rigoranthusemortisreborn.ichor_jar").getString());
        list.add(new TranslationTextComponent("rigoranthusemortisreborn.ichor_jar.fullness", (getCurrentIchor()*100) / this.getMaxIchor()).getString());
        return list;
    }
}
