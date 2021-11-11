package com.platinumg17.rigoranthusemortisreborn.canis.common.block.tileentity;

import com.platinumg17.rigoranthusemortisreborn.canis.common.util.NBTUtilities;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.WorldUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;
import java.util.UUID;

public class PlacedTileEntity extends TileEntity {

    private @Deprecated @Nullable LivingEntity placer;
    private @Nullable UUID placerUUID;

    public PlacedTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        this.placerUUID = NBTUtilities.getUniqueId(compound, "placerId");
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        NBTUtilities.putUniqueId(compound, "placerId", this.placerUUID);
        return compound;
    }

    public void setPlacer(@Nullable LivingEntity placer) {
        this.placer = placer;
        this.placerUUID = placer == null ? null : placer.getUUID();
        this.setChanged();
    }

    @Nullable
    public UUID getPlacerId() {
        return this.placerUUID;
    }

    @Nullable
    public LivingEntity getPlacer() {
        return WorldUtil.getCachedEntity(this.level, LivingEntity.class, this.placer, this.placerUUID);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 0, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT compound = this.save(new CompoundNBT());
        return compound;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.load(null, pkt.getTag());
    }
}
