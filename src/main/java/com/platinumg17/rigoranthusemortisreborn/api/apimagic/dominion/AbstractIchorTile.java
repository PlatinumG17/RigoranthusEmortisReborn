package com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

import static com.platinumg17.rigoranthusemortisreborn.api.apimagic.MagicFluidTags.ICHOR_TAG;
import static com.platinumg17.rigoranthusemortisreborn.api.apimagic.MagicFluidTags.MAX_ICHOR_TAG;

public abstract class AbstractIchorTile extends TileEntity implements IIchorTile, ITickableTileEntity {
    private int ichor = 0;
    private int maxIchor = 0;
    public AbstractIchorTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        ichor = tag.getInt(ICHOR_TAG);
        maxIchor = tag.getInt(MAX_ICHOR_TAG);
        super.load(state, tag);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag.putInt(ICHOR_TAG, getCurrentIchor());
        tag.putInt(MAX_ICHOR_TAG, getMaxIchor());
        return super.save(tag);
    }

    @Override
    public int setIchor(int ichor) {
        this.ichor = ichor;
        if(this.ichor > this.getMaxIchor())
            this.ichor = this.getMaxIchor();
        if(this.ichor < 0)
            this.ichor = 0;
        update();
        return this.ichor;
    }

    @Override
    public int addIchor(int ichorToAdd) {
        return this.setIchor(this.getCurrentIchor() + ichorToAdd);
    }

    @Override
    public int getCurrentIchor() {
        return this.ichor;
    }

    @Override
    public int removeIchor(int ichorToRemove) {
        this.setIchor(this.getCurrentIchor() - ichorToRemove);
        update();
        return this.getCurrentIchor();
    }

    @Override
    public void setMaxIchor(int max) {
        this.maxIchor = max;
        update();
    }

    public boolean update(){
        if(this.worldPosition != null && this.level != null){
            level.sendBlockUpdated(this.worldPosition, level.getBlockState(worldPosition),  level.getBlockState(worldPosition), 2);
            return true;
        }
        return false;
    }

    @Override
    public int getMaxIchor() {
        return maxIchor;
    }

    public boolean canAcceptIchor(){ return this.getCurrentIchor() < this.getMaxIchor(); }

    public int ichorCanAccept(IIchorTile tile){return tile.getMaxIchor() - tile.getCurrentIchor();}

    public int transferIchor(IIchorTile from, IIchorTile to){
        int transferRate = getTransferRate(from, to);
        from.removeIchor(transferRate);
        to.addIchor(transferRate);
        return transferRate;
    }

    public int getTransferRate(IIchorTile from, IIchorTile to){
        return Math.min(Math.min(from.getTransferRate(), from.getCurrentIchor()), to.getMaxIchor() - to.getCurrentIchor());
    }

    public int transferIchor(IIchorTile from, IIchorTile to, int fromTransferRate){
        int transferRate = getTransferRate(from, to, fromTransferRate);
        if(transferRate == 0)
            return 0;
        from.removeIchor(transferRate);
        to.addIchor(transferRate);
        return transferRate;
    }

    public int getTransferRate(IIchorTile from, IIchorTile to, int fromTransferRate){
        return Math.min(Math.min(fromTransferRate, from.getCurrentIchor()), to.getMaxIchor() - to.getCurrentIchor());
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 3, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(level.getBlockState(worldPosition),pkt.getTag());
    }
}