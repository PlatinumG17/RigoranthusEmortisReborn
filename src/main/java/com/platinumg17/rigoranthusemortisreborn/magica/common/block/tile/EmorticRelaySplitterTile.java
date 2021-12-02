package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.AbstractDominionTile;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.NBTUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class EmorticRelaySplitterTile extends EmorticRelayTile {

    ArrayList<BlockPos> toList = new ArrayList<>();
    ArrayList<BlockPos> fromList = new ArrayList<>();

    public EmorticRelaySplitterTile() {
        super(BlockRegistry.EMORTIC_RELAY_SPLITTER_TILE);
    }

    public EmorticRelaySplitterTile(TileEntityType<?> type){
        super(type);
    }

    @Override
    public boolean setTakeFrom(BlockPos pos) {
        return closeEnough(pos) && fromList.add(pos) && update();
    }

    @Override
    public boolean setSendTo(BlockPos pos) {
        return closeEnough(pos) && toList.add(pos) && update();
    }

    @Override
    public void clearPos() {
        this.toList.clear();
        this.fromList.clear();
        update();
    }

    public void processFromList(){
        if(fromList.isEmpty())
            return;
        ArrayList<BlockPos> stale = new ArrayList<>();
        int ratePer = getTransferRate() / fromList.size();
        for(BlockPos fromPos : fromList){
            if(!(level.getBlockEntity(fromPos) instanceof AbstractDominionTile)){
                stale.add(fromPos);
                continue;
            }
            AbstractDominionTile fromTile = (AbstractDominionTile) level.getBlockEntity(fromPos);
            if(transferDominion(fromTile, this, ratePer) > 0){
                createParticles(fromPos, worldPosition);
            }
        }
        for(BlockPos s : stale)
            fromList.remove(s);
    }

    public void createParticles(BlockPos from, BlockPos to){
        ParticleUtil.spawnFollowProjectile(level, from, to);
    }

    public void processToList(){
        if(toList.isEmpty())
            return;
        ArrayList<BlockPos> stale = new ArrayList<>();
        int ratePer = getTransferRate() / toList.size();
        for(BlockPos toPos : toList){
            if(!(level.getBlockEntity(toPos) instanceof AbstractDominionTile)){
                stale.add(toPos);
                continue;
            }
            AbstractDominionTile toTile = (AbstractDominionTile) level.getBlockEntity(toPos);
            int transfer = transferDominion(this, toTile, ratePer);
            if(transfer > 0){
                createParticles(worldPosition, toPos);
            }
        }
        for(BlockPos s : stale)
            toList.remove(s);
    }

    @Override
    public void tick() {
        if(level.getGameTime() % 20 != 0 || toList.isEmpty() || level.isClientSide)
            return;
        processFromList();
        processToList();
        update();
    }

    @Override
    public int getTransferRate() {
        return 2500;
    }

    @Override
    public int getMaxDominion() {
        return 2500;
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        fromList = new ArrayList<>();
        toList = new ArrayList<>();
        int counter = 0;

        while(NBTUtil.hasBlockPos(tag, "from_" + counter)){
            BlockPos pos = NBTUtil.getBlockPos(tag, "from_" + counter);
            if(!this.fromList.contains(pos))
                this.fromList.add(pos);
            counter++;
        }
        counter = 0;
        while(NBTUtil.hasBlockPos(tag, "to_" + counter)){
            BlockPos pos = NBTUtil.getBlockPos(tag, "to_" + counter);
            if(!this.toList.contains(pos))
                this.toList.add(NBTUtil.getBlockPos(tag, "to_" + counter));
            counter++;
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        int counter = 0;
        for(BlockPos p : this.fromList){
            NBTUtil.storeBlockPos(tag, "from_" +counter, p);
            counter++;
        }
        counter = 0;
        for(BlockPos p : this.toList){
            NBTUtil.storeBlockPos(tag, "to_" +counter, p);
            counter ++;
        }
        return super.save(tag);
    }

    @Override
    public List<String> getTooltip() {
        List<String> list = new ArrayList<>();
        if(toList == null || toList.isEmpty()){
            list.add(new TranslationTextComponent("rigoranthusemortisreborn.relay.no_to").getString());
        }else{
            list.add(new TranslationTextComponent("rigoranthusemortisreborn.relay.one_to", toList.size()).getString());
        }
        if(fromList == null || fromList.isEmpty()){
            list.add(new TranslationTextComponent("rigoranthusemortisreborn.relay.no_from").getString());
        }else{
            list.add(new TranslationTextComponent("rigoranthusemortisreborn.relay.one_from", fromList.size()).getString());
        }
        return list;
    }
}