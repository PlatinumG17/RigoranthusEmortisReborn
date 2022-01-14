package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet;

import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.CanisSaddleClothData;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CanisDisplaySaddleClothPacket extends CanisPacket<CanisSaddleClothData> {

    @Override
    public void encode(CanisSaddleClothData data, PacketBuffer buf) {
        super.encode(data, buf);
        buf.writeBoolean(data.displayCloth);
    }

    @Override
    public CanisSaddleClothData decode(PacketBuffer buf) {
        int entityId = buf.readInt();
        boolean displayCloth = buf.readBoolean();
        return new CanisSaddleClothData(entityId, displayCloth);
    }

    @Override
    public void handleCanis(CanisEntity canisIn, CanisSaddleClothData data, Supplier<NetworkEvent.Context> ctx) {
        if (!canisIn.canInteract(ctx.get().getSender())) {
            return;
        }
        canisIn.setDisplayCloth(data.displayCloth);
    }
}