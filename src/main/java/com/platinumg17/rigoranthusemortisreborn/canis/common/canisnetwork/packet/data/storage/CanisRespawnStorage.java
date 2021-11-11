package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.storage;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.NBTUtilities;
import static net.minecraftforge.common.util.Constants.NBT.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.google.common.collect.Maps;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class CanisRespawnStorage extends WorldSavedData {

    private Map<UUID, CanisRespawnData> respawnDataMap = Maps.newConcurrentMap();

    public CanisRespawnStorage() {
        super(EmortisConstants.STORAGE_CANIS_RESPAWN);
    }

    public static CanisRespawnStorage get(World world) {
        if (!(world instanceof ServerWorld)) {throw new RuntimeException("Tried to access canis respawn data from the client. This should not happen...");}

        ServerWorld overworld = world.getServer().getLevel(World.OVERWORLD);

        DimensionSavedDataManager storage = overworld.getDataStorage();
        return storage.computeIfAbsent(CanisRespawnStorage::new, EmortisConstants.STORAGE_CANIS_RESPAWN);
    }
    public Stream<CanisRespawnData> getCani(@Nonnull UUID ownerId) {return this.respawnDataMap.values().stream().filter(data -> ownerId.equals(data.getOwnerId()));}
    public Stream<CanisRespawnData> getCani(@Nonnull String ownerName) {return this.respawnDataMap.values().stream().filter(data -> ownerName.equals(data.getOwnerName()));}

    @Nullable
    public CanisRespawnData getData(UUID uuid) {
        if (this.respawnDataMap.containsKey(uuid)) {
            return this.respawnDataMap.get(uuid);
        }
        return null;
    }

    @Nullable
    public CanisRespawnData remove(UUID uuid) {
        if (this.respawnDataMap.containsKey(uuid)) {
            CanisRespawnData storage = this.respawnDataMap.remove(uuid);
            // Mark dirty so changes are saved
            this.setDirty();
            return storage;
        }
        return null;
    }

    @Nullable
    public CanisRespawnData putData(CanisEntity canisIn) {
        UUID uuid = canisIn.getUUID();
        CanisRespawnData storage = new CanisRespawnData(this, uuid);
        storage.populate(canisIn);
        this.respawnDataMap.put(uuid, storage);
        // Mark dirty so changes are saved
        this.setDirty();
        return storage;
    }
    public Set<UUID> getAllUUID() {return Collections.unmodifiableSet(this.respawnDataMap.keySet());}
    public Collection<CanisRespawnData> getAll() {return Collections.unmodifiableCollection(this.respawnDataMap.values());}

    @Override
    public void load(CompoundNBT nbt) {
        this.respawnDataMap.clear();
        ListNBT list = nbt.getList("respawnData", TAG_COMPOUND);
        for (int i = 0; i < list.size(); ++i) {
            CompoundNBT respawnCompound = list.getCompound(i);
            UUID uuid = NBTUtilities.getUniqueId(respawnCompound, "uuid");
            CanisRespawnData respawnData = new CanisRespawnData(this, uuid);
            respawnData.read(respawnCompound);
            if (uuid == null) {
                RigoranthusEmortisReborn.LOGGER.info("Failed to load canis respawn data. Please report to mod author...");
                RigoranthusEmortisReborn.LOGGER.info(respawnData);
                continue;
            }
            this.respawnDataMap.put(uuid, respawnData);
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        ListNBT list = new ListNBT();
        for (Map.Entry<UUID, CanisRespawnData> entry : this.respawnDataMap.entrySet()) {
            CompoundNBT respawnCompound = new CompoundNBT();
            CanisRespawnData respawnData = entry.getValue();
            NBTUtilities.putUniqueId(respawnCompound, "uuid", entry.getKey());
            respawnData.write(respawnCompound);
            list.add(respawnCompound);
        }
        compound.put("respawnData", list);
        return compound;
    }
}

/*


import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.google.common.collect.Maps;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

import static net.minecraftforge.common.util.Constants.NBT.*;

public class PidgeonReviveStorage extends WorldSavedData {

    private Map<UUID, PidgeonReviveData> reviveDataMap = Maps.newConcurrentMap();

    public PidgeonReviveStorage() {
        super(HexereiConstants.STORAGE_PIDGEON_REVIVE); // TODO -->  may be STORAGE_PIDGEON_RESPAWN  for you so watch out
    }

    public static PidgeonReviveStorage get(World world) {
        if (!(world instanceof ServerWorld)) {throw new RuntimeException("Tried to access pidgeon revive data from the client. This should not happen...");}

        ServerWorld overworld = world.getServer().getLevel(World.OVERWORLD);

        DimensionSavedDataManager storage = overworld.getDataStorage();
        return storage.computeIfAbsent(PidgeonReviveStorage::new, HexereiConstants.STORAGE_PIDGEON_REVIVE);  // TODO -->  may be STORAGE_PIDGEON_RESPAWN  for you so watch out
    }
    public Stream<PidgeonReviveData> getCani(@Nonnull UUID ownerId) {return this.reviveDataMap.values().stream().filter(data -> ownerId.equals(data.getOwnerId()));}
    public Stream<PidgeonReviveData> getCani(@Nonnull String ownerName) {return this.reviveDataMap.values().stream().filter(data -> ownerName.equals(data.getOwnerName()));}

    @Nullable
    public PidgeonReviveData getData(UUID uuid) {
        if (this.reviveDataMap.containsKey(uuid)) {
            return this.reviveDataMap.get(uuid);
        }
        return null;
    }

    @Nullable
    public PidgeonReviveData remove(UUID uuid) {
        if (this.reviveDataMap.containsKey(uuid)) {
            PidgeonReviveData storage = this.reviveDataMap.remove(uuid);
            // Mark dirty so changes are saved
            this.setDirty();
            return storage;
        }
        return null;
    }

    @Nullable
    public PidgeonReviveData putData(PidgeonEntity pidgeonIn) {
        UUID uuid = pidgeonIn.getUUID();
        PidgeonReviveData storage = new PidgeonReviveData(this, uuid);
        storage.populate(pidgeonIn);
        this.reviveDataMap.put(uuid, storage);
        // Mark dirty so changes are saved
        this.setDirty();
        return storage;
    }
    public Set<UUID> getAllUUID() {return Collections.unmodifiableSet(this.reviveDataMap.keySet());}
    public Collection<PidgeonReviveData> getAll() {return Collections.unmodifiableCollection(this.reviveDataMap.values());}

    @Override
    public void load(CompoundNBT nbt) {
        this.reviveDataMap.clear();
        ListNBT list = nbt.getList("reviveData", TAG_COMPOUND);
        for (int i = 0; i < list.size(); ++i) {
            CompoundNBT reviveCompound = list.getCompound(i);
            UUID uuid = NBTUtilities.getUniqueId(reviveCompound, "uuid");
            PidgeonReviveData reviveData = new PidgeonReviveData(this, uuid);
            reviveData.read(reviveCompound);
            if (uuid == null) {
                Hexerei.LOGGER.info("Failed to load pidgeon revive data. Please complain to Joe...");
                Hexerei.LOGGER.info(reviveData);
                continue;
            }
            this.reviveDataMap.put(uuid, reviveData);
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        ListNBT list = new ListNBT();
        for (Map.Entry<UUID, PidgeonReviveData> entry : this.reviveDataMap.entrySet()) {
            CompoundNBT reviveCompound = new CompoundNBT();
            PidgeonReviveData reviveData = entry.getValue();
            NBTUtilities.putUniqueId(reviveCompound, "uuid", entry.getKey());
            reviveData.write(reviveCompound);
            list.add(reviveCompound);
        }
        compound.put("reviveData", list);
        return compound;
    }
}
 */