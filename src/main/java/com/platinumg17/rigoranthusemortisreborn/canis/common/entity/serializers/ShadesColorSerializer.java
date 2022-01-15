package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.serializers;

import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.EnumShadesColor;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;

public class ShadesColorSerializer implements IDataSerializer<EnumShadesColor> {
    @Override
    public void write(PacketBuffer buf, EnumShadesColor value) {
        buf.writeByte(value.getIndex());
    }

    @Override
    public EnumShadesColor read(PacketBuffer buf) {
        return EnumShadesColor.byIndex(buf.readByte());
    }

    @Override
    public EnumShadesColor copy(EnumShadesColor value) {
        return value;
    }
}