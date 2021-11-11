package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet;

import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.CanisNameData;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CanisNamePacket extends CanisPacket<CanisNameData> {

    @Override
    public void encode(CanisNameData data, PacketBuffer buf) {
        super.encode(data, buf);
        buf.writeUtf(data.name, 64);
    }

    @Override
    public CanisNameData decode(PacketBuffer buf) {
        int entityId = buf.readInt();
        String name = buf.readUtf(64);
        return new CanisNameData(entityId, name);
    }

    @Override
    public void handleCanis(CanisEntity canisIn, CanisNameData data, Supplier<NetworkEvent.Context> ctx) {
        if (!canisIn.canInteract(ctx.get().getSender())) {
            return;
        }
        if (data.name.isEmpty()) {
            canisIn.setCustomName(null);
        }
        else {
            canisIn.setCustomName(new StringTextComponent(data.name));
        }
    }
}
