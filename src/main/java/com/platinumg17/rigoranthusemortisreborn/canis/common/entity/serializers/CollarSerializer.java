package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.serializers;

import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.registry.Accoutrement;
import com.platinumg17.rigoranthusemortisreborn.api.registry.AccoutrementInstance;

import java.util.Optional;

public class CollarSerializer implements IDataSerializer<Optional<AccoutrementInstance>> {

    @Override
    public void write(PacketBuffer buf, Optional<AccoutrementInstance> value) {
        REUtil.acceptOrElse(value, (inst) -> {
            buf.writeBoolean(true);
            buf.writeRegistryIdUnsafe(RigoranthusEmortisRebornAPI.ACCOUTERMENTS, inst.getAccoutrement());
            inst.getAccoutrement().write(inst, buf);
        }, () -> {
            buf.writeBoolean(false);
        });
    }

    @Override
    public Optional<AccoutrementInstance> read(PacketBuffer buf) {
        if (buf.readBoolean()) {
            Accoutrement type = buf.readRegistryIdUnsafe(RigoranthusEmortisRebornAPI.ACCOUTERMENTS);
            return Optional.of(type.createInstance(buf));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AccoutrementInstance> copy(Optional<AccoutrementInstance> value) {
        if (value.isPresent()) {
            return Optional.of(value.get().copy());
        }
        return Optional.empty();
    }
}