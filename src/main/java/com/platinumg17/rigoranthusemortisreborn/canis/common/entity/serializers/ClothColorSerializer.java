package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.serializers;

import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.EnumClothColor;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;

public class ClothColorSerializer implements IDataSerializer<EnumClothColor> {
    @Override
    public void write(PacketBuffer buf, EnumClothColor value) {
        buf.writeByte(value.getIndex());
    }

    @Override
    public EnumClothColor read(PacketBuffer buf) {
        return EnumClothColor.byIndex(buf.readByte());
    }

    @Override
    public EnumClothColor copy(EnumClothColor value) {
        return value;
    }
}