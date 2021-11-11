package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet;

//import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.IPacket;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.SendSkinData;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
//import com.platinumg17.rigoranthusemortisreborn.canis.client.CanisTextureManager;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.texture.CanisTextureServer;
//import net.minecraft.entity.Entity;
//import net.minecraft.network.PacketBuffer;
//import net.minecraftforge.fml.LogicalSide;
//import net.minecraftforge.fml.network.NetworkEvent;
//import org.apache.commons.io.IOUtils;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.util.function.Supplier;
//
//public class SendSkinPacket implements IPacket<SendSkinData> {
//
//    @Override
//    public void encode(SendSkinData data, PacketBuffer buf) {
//        buf.writeInt(data.entityId);
//        buf.writeInt(data.image.length);
//        buf.writeBytes(data.image);
//    }
//
//    @Override
//    public SendSkinData decode(PacketBuffer buf) {
//    int entityId = buf.readInt();
//    byte[] targetArray = new byte[buf.readInt()];
//        buf.readBytes(targetArray);
//        return new SendSkinData(entityId, targetArray);
//    }
//
//    @Override
//    public void handle(SendSkinData data, Supplier<NetworkEvent.Context> ctx) {
//    ctx.get().enqueueWork(() -> {
//    LogicalSide side = ctx.get().getDirection().getReceptionSide();
//    if (side.isClient()) {
//        RigoranthusEmortisReborn.LOGGER.debug("Client: Received canis texture to save and load");
//        String hash = "";
//    try {
//        hash = CanisTextureManager.INSTANCE.saveTextureAndLoad(CanisTextureManager.INSTANCE.getClientFolder(), data.image);
//        CanisTextureManager.INSTANCE.setRequestHandled(hash);
//    } catch (IOException e) {
//        RigoranthusEmortisReborn.LOGGER.error("Canis skin failed to load");
//            CanisTextureManager.INSTANCE.setRequestFailed(hash);
//        }
//    } else if (side.isServer()) {
//    Entity target = ctx.get().getSender().level.getEntity(data.entityId);
//    if (!(target instanceof CanisEntity)) {
//        return;
//    }
//
//    CanisEntity canis = (CanisEntity) target;
//    if (!canis.canInteract(ctx.get().getSender())) {
//        return;
//    }
//
//    try {
//        if (ctx.get().getSender().getServer().isDedicatedServer()) {
//        // Sanitise the data
//    ByteArrayInputStream bis = new ByteArrayInputStream(data.image);
//    BufferedImage bImage2 = ImageIO.read(bis);
//
//    CanisTextureServer.INSTANCE.saveTexture(CanisTextureServer.INSTANCE.getServerFolder(), IOUtils.toByteArray(bis));
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//
//        String hash = CanisTextureServer.INSTANCE.getHash(data.image);
//        canis.setSkinHash(hash);
//    }
//    });
//
//        ctx.get().setPacketHandled(true);
//
//    }
//}