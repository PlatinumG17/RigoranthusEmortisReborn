package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.storage;

import com.google.common.collect.Lists;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.SpecializedEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.NBTUtilities;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.server.ServerWorld;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.EnumMode;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class CanisRespawnData implements ICanisData {

    private final CanisRespawnStorage storage;
    private final UUID uuid;
    private CompoundNBT data;

    //TODO Make it list you can only add too
    private static final List<String> TAGS_TO_REMOVE = Lists.newArrayList(
            "Pos", "Health", "Motion", "Rotation", "FallDistance", "Fire", "Air", "OnGround",
            "Dimension", "PortalCooldown", "Passengers", "Leash", "InLove", "Leash", "HurtTime",
            "HurtByTimestamp", "DeathTime", "AbsorptionAmount", "FallFlying", "Brain", "Sitting"); // Remove canis mode

    protected CanisRespawnData(CanisRespawnStorage storageIn, UUID uuid) {
        this.storage = storageIn;
        this.uuid = uuid;
    }

    @Override
    public UUID getCanisId() {
        return this.uuid;
    }

    @Override
    public String getCanisName() {
        ITextComponent name = NBTUtilities.getTextComponent(this.data, "CustomName");
        return name == null ? "" : name.getString();
    }

    @Override
    public UUID getOwnerId() {
        String str = data.getString("OwnerUUID");
        return "".equals(str) ? null : UUID.fromString(str);
    }

    @Override
    public String getOwnerName() {
        ITextComponent name = NBTUtilities.getTextComponent(this.data, "lastKnownOwnerName");
        return name == null ? "" : name.getString();
    }

    public void populate(CanisEntity canisIn) {
        this.data = new CompoundNBT();
        canisIn.saveWithoutId(this.data);

        // Remove tags that don't need to be saved
        for (String tag : TAGS_TO_REMOVE) {
            this.data.remove(tag);
        }
        this.data.remove("UUID");
        this.data.remove("LoveCause");
    }

    @Nullable
    public CanisEntity respawn(ServerWorld worldIn, PlayerEntity playerIn, BlockPos pos) {
        CanisEntity canis = SpecializedEntityTypes.CANIS.get().spawn(worldIn, null, null, playerIn, pos, SpawnReason.TRIGGERED, true, false);
        // Failed for some reason
        if (canis == null) {
            return null;
        }
        CompoundNBT compoundnbt = canis.saveWithoutId(new CompoundNBT());
        UUID uuid = canis.getUUID();
        compoundnbt.merge(this.data);
        canis.setUUID(uuid);
        canis.load(compoundnbt);

        canis.setMode(EnumMode.DOCILE);
        canis.setOrderedToSit(true);

        return canis;
    }

    public void read(CompoundNBT compound) {
        this.data = compound.getCompound("data");
    }

    public CompoundNBT write(CompoundNBT compound) {
        compound.put("data", this.data);
        return compound;
    }
}