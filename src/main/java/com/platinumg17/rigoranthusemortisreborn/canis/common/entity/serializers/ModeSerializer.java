package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.serializers;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import com.platinumg17.rigoranthusemortisreborn.api.feature.EnumMode;

public class ModeSerializer implements IDataSerializer<EnumMode> {
    @Override
    public void write(PacketBuffer buf, EnumMode value) {
        buf.writeByte(value.getIndex());
    }

    @Override
    public EnumMode read(PacketBuffer buf) {
        return EnumMode.byIndex(buf.readByte());
    }

    @Override
    public EnumMode copy(EnumMode value) {
        return value;
    }
}
