package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet;

//import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.CanisTextureData;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
//import net.minecraft.network.PacketBuffer;
//import net.minecraftforge.fml.network.NetworkEvent;
//
//import java.util.function.Supplier;
//
//public class CanisTexturePacket extends CanisPacket<CanisTextureData> {
//
//    @Override
//    public void encode(CanisTextureData data, PacketBuffer buf) {
//        super.encode(data, buf);
//        buf.writeUtf(data.hash);
//    }
//
//    @Override
//    public CanisTextureData decode(PacketBuffer buf) {
//        int entityId = buf.readInt();
//        String texture = buf.readUtf(128);
//        return new CanisTextureData(entityId, texture);
//    }
//
//    @Override
//    public void handleCanis(CanisEntity canisIn, CanisTextureData data, Supplier<NetworkEvent.Context> ctx) {
//        if (!canisIn.canInteract(ctx.get().getSender())) {
//            return;
//        }
//        canisIn.setSkinHash(data.hash);
//    }
//}
