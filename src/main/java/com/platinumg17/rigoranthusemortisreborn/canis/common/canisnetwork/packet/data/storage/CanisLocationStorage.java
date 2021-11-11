package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.storage;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.NBTUtilities;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import com.google.common.collect.Maps;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import static net.minecraftforge.common.util.Constants.NBT.*;

public class CanisLocationStorage extends WorldSavedData {

    private Map<UUID, CanisLocationData> locationDataMap = Maps.newConcurrentMap();
    public CanisLocationStorage() {
        super(EmortisConstants.STORAGE_CANIS_LOCATION);
    }

    public static CanisLocationStorage get(World world) {
        if (!(world instanceof ServerWorld)) {
            throw new RuntimeException("Tried to access canis location data from the client. This should not happen...");
        }

        ServerWorld overworld = world.getServer().getLevel(World.OVERWORLD);

        DimensionSavedDataManager storage = overworld.getDataStorage();
        return storage.computeIfAbsent(CanisLocationStorage::new, EmortisConstants.STORAGE_CANIS_LOCATION);
    }

    public Stream<CanisLocationData> getCani(LivingEntity owner) {
        UUID ownerId = owner.getUUID();

        return this.locationDataMap.values().stream()
                .filter(data -> ownerId.equals(data.getOwnerId()));
    }

    public Stream<CanisLocationData> getCani(LivingEntity owner, RegistryKey<World> key) {
        UUID ownerId = owner.getUUID();
        return this.locationDataMap.values().stream()
                .filter(data -> ownerId.equals(data.getOwnerId()))
                .filter(data -> key.equals(data.getDimension()));
    }

    @Nullable
    public CanisLocationData getData(CanisEntity canisIn) {
        return getData(canisIn.getUUID());
    }

    @Nullable
    public CanisLocationData getData(UUID uuid) {
        if (this.locationDataMap.containsKey(uuid)) {
            return this.locationDataMap.get(uuid);
        }
        return null;
    }

    @Nullable
    public CanisLocationData remove(CanisEntity canisIn) {
        return remove(canisIn.getUUID());
    }

    @Nullable
    public CanisLocationData getOrCreateData(CanisEntity canisIn) {
        UUID uuid = canisIn.getUUID();
        return this.locationDataMap.computeIfAbsent(uuid, ($) -> {
            this.setDirty();
            return CanisLocationData.from(this, canisIn);
        });
    }

    @Nullable
    public CanisLocationData remove(UUID uuid) {
        if (this.locationDataMap.containsKey(uuid)) {
            CanisLocationData storage = this.locationDataMap.remove(uuid);
            // Mark dirty so changes are saved
            this.setDirty();
            return storage;
        }
        return null;
    }

    @Nullable
    public CanisLocationData putData(CanisEntity canisIn) {
        UUID uuid = canisIn.getUUID();
        CanisLocationData storage = new CanisLocationData(this, uuid);
        this.locationDataMap.put(uuid, storage);
        // Mark dirty so changes are saved
        this.setDirty();
        return storage;
    }
    public Set<UUID> getAllUUID() {return Collections.unmodifiableSet(this.locationDataMap.keySet());}
    public Collection<CanisLocationData> getAll() {return Collections.unmodifiableCollection(this.locationDataMap.values());}

    @Override
    public void load(CompoundNBT nbt) {
        this.locationDataMap.clear();
        ListNBT list = nbt.getList("locationData", TAG_COMPOUND);
        // Old style
        if (list.isEmpty()) {
            list = nbt.getList("canis_locations", TAG_COMPOUND);
        }

        for (int i = 0; i < list.size(); ++i) {
            CompoundNBT locationCompound = list.getCompound(i);
            UUID uuid = NBTUtilities.getUniqueId(locationCompound, "uuid");

            // Old style
            if (uuid == null) {
                uuid = NBTUtilities.getUniqueId(locationCompound, "entityId");
            }
            CanisLocationData locationData = new CanisLocationData(this, uuid);
            locationData.read(locationCompound);
            if (uuid == null) {
                RigoranthusEmortisReborn.LOGGER.info("Failed to load canis location data. Please report to mod author...");
                RigoranthusEmortisReborn.LOGGER.info(locationData);
                continue;
            }
            this.locationDataMap.put(uuid, locationData);
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        ListNBT list = new ListNBT();
        for (Entry<UUID, CanisLocationData> entry : this.locationDataMap.entrySet()) {
            CompoundNBT locationCompound = new CompoundNBT();
            CanisLocationData locationData = entry.getValue();
            NBTUtilities.putUniqueId(locationCompound, "uuid", entry.getKey());
            locationData.write(locationCompound);
            list.add(locationCompound);
        }
        compound.put("locationData", list);
        return compound;
    }
}