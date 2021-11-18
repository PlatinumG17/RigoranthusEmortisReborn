package com.platinumg17.rigoranthusemortisreborn.canis.common.block.tileentity;

import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.storage.CanisLocationData;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisTileEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.storage.CanisLocationStorage;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.NBTUtilities;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.WorldUtil;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.IBeddingMaterial;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.ICasingMaterial;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import javax.annotation.Nullable;
import java.util.UUID;

public class CanisBedTileEntity extends PlacedTileEntity {

    private ICasingMaterial casingType = null;
    private IBeddingMaterial beddingType = null;

    public static ModelProperty<ICasingMaterial> CASING = new ModelProperty<>();
    public static ModelProperty<IBeddingMaterial> BEDDING = new ModelProperty<>();
    public static ModelProperty<Direction> FACING = new ModelProperty<>();

    private @Deprecated @Nullable CanisEntity canis;
    private @Nullable UUID canisUUID;

    private @Nullable ITextComponent name;
    private @Nullable ITextComponent ownerName;

    public CanisBedTileEntity() {
        super(CanisTileEntityTypes.CANIS_BED.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        this.casingType = NBTUtilities.getRegistryValue(compound, "casingId", RigoranthusEmortisRebornAPI.CASING_MATERIAL);
        this.beddingType = NBTUtilities.getRegistryValue(compound, "beddingId", RigoranthusEmortisRebornAPI.BEDDING_MATERIAL);
        this.canisUUID = NBTUtilities.getUniqueId(compound, "ownerId");
        this.name = NBTUtilities.getTextComponent(compound, "name");
        this.ownerName = NBTUtilities.getTextComponent(compound, "ownerName");
        this.requestModelDataUpdate();
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        NBTUtilities.putRegistryValue(compound, "casingId", this.casingType);
        NBTUtilities.putRegistryValue(compound, "beddingId", this.beddingType);
        NBTUtilities.putUniqueId(compound, "ownerId", this.canisUUID);
        NBTUtilities.putTextComponent(compound, "name", this.name);
        NBTUtilities.putTextComponent(compound, "ownerName", this.ownerName);
        return compound;
    }

    public void setCasing(ICasingMaterial casingType) {
        this.casingType = casingType;
        this.setChanged();
        this.requestModelDataUpdate();
    }

    public void setBedding(IBeddingMaterial beddingType) {
        this.beddingType = beddingType;
        this.setChanged();
        this.requestModelDataUpdate();
    }
    public ICasingMaterial getCasing() {return this.casingType;}
    public IBeddingMaterial getBedding() {return this.beddingType;}

    @Override
    public IModelData getModelData() {
        return new ModelDataMap.Builder()
            .withInitial(CASING, this.casingType)
            .withInitial(BEDDING, this.beddingType)
            .withInitial(FACING, Direction.NORTH)
            .build();
    }

    public void setOwner(@Nullable CanisEntity owner) {
        this.setOwner(owner == null ? null : owner.getUUID());
        this.canis = owner;
    }

    public void setOwner(@Nullable UUID owner) {
        this.canis = null;
        this.canisUUID = owner;
        this.setChanged();
    }

    @Nullable
    public UUID getOwnerUUID() {
        return this.canisUUID;
    }

    @Nullable
    public CanisEntity getOwner() {
        return WorldUtil.getCachedEntity(this.level, CanisEntity.class, this.canis, this.canisUUID);
    }

    @Nullable
    public ITextComponent getBedName() {
        return this.name;
    }

    @Nullable
    public ITextComponent getOwnerName() {
        if (this.canisUUID == null || this.level == null) { return null; }
        CanisLocationData locData = CanisLocationStorage
                .get(this.level)
                .getData(this.canisUUID);
        if (locData != null) {
            ITextComponent text = locData.getName(this.level);
            if (text != null) {
                this.ownerName = name;
            }
        }
        return this.ownerName;
    }

    public boolean shouldDisplayName(LivingEntity camera) {
        return true;
    }

    public void setBedName(@Nullable ITextComponent nameIn) {
        this.name = nameIn;
        this.setChanged();
    }
}