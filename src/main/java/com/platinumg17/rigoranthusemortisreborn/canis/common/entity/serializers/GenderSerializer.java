package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.serializers;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import com.platinumg17.rigoranthusemortisreborn.api.feature.EnumGender;

public class GenderSerializer implements IDataSerializer<EnumGender> {

    @Override
    public void write(PacketBuffer buf, EnumGender value) {
        buf.writeByte(value.getIndex());
    }

    @Override
    public EnumGender read(PacketBuffer buf) {
        return EnumGender.byIndex(buf.readByte());
    }

    @Override
    public EnumGender copy(EnumGender value) {
        return value;
    }
}
