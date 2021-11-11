package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet;

//import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.IPacket;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.RequestSkinData;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.SendSkinData;
////import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.texture.CanisTextureServer;
//import net.minecraft.network.PacketBuffer;
//import net.minecraftforge.fml.LogicalSide;
//import net.minecraftforge.fml.network.NetworkEvent;
//
//import java.util.function.Supplier;
//
//public class RequestSkinPacket implements IPacket<RequestSkinData> {
//
//    @Override
//    public void encode(RequestSkinData data, PacketBuffer buf) {
//        buf.writeUtf(data.hash, 128);
//    }
//
//    @Override
//    public RequestSkinData decode(PacketBuffer buf) {
//        return new RequestSkinData(buf.readUtf(128));
//    }
//
//    @Override
//    public void handle(RequestSkinData data, Supplier<NetworkEvent.Context> ctx) {
//        ctx.get().enqueueWork(() -> {
//            LogicalSide side = ctx.get().getDirection().getReceptionSide();
//
//            if (side.isServer()) {
//                byte[] stream = CanisTextureServer.INSTANCE.getCachedBytes(CanisTextureServer.INSTANCE.getServerFolder(), data.hash);
//                if (stream != null) {
//                    RigoranthusEmortisReborn.HANDLER.reply(new SendSkinData(0, stream), ctx.get());
//
//                    RigoranthusEmortisReborn.LOGGER.debug("Client requested skin for hash  {}", data.hash);
//                } else {
//                    RigoranthusEmortisReborn.LOGGER.warn("Client requested skin but no cache was available {}", data.hash);
//                }
//            }
//        });
//        ctx.get().setPacketHandled(true);
//    }
//}
