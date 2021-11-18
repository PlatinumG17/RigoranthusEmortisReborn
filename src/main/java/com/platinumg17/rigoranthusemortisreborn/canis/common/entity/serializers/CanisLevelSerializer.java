package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.serializers;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.CanisLevel;

public class CanisLevelSerializer implements IDataSerializer<CanisLevel> {

    @Override
    public void write(PacketBuffer buf, CanisLevel value) {
        buf.writeInt(value.getLevel(CanisLevel.Type.CHORDATA));
//        buf.writeInt(value.getLevel(CanisLevel.Type.KYPHOS));
//        buf.writeInt(value.getLevel(CanisLevel.Type.CAVALIER));
        buf.writeInt(value.getLevel(CanisLevel.Type.HOMINI));
    }

    @Override
    public CanisLevel read(PacketBuffer buf) {
        return new CanisLevel(buf.readInt(), buf.readInt());
    }

    @Override
    public CanisLevel copy(CanisLevel value) {
        return value.copy();
    }
}
