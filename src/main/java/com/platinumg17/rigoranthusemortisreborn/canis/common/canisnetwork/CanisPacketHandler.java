package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.*;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.*;
import net.minecraftforge.fml.network.PacketDistributor;

public class CanisPacketHandler {

    private static int disc = 0;

    public static void init() {
        registerPacket(new CanisModePacket(), CanisModeData.class);
        registerPacket(new CanisNamePacket(), CanisNameData.class);
        registerPacket(new CanisObeyPacket(), CanisObeyData.class);
        registerPacket(new CanisSkillPacket(), CanisSkillData.class);
        registerPacket(new FriendlyFirePacket(), FriendlyFireData.class);
        registerPacket(new OpenCanisScreenPacket(), OpenCanisScreenData.class);
        registerPacket(new CanisInventoryPagePacket(), CanisInventoryPageData.class);
        registerPacket(new CanisDisplaySaddleClothPacket(), CanisSaddleClothData.class);
//        registerPacket(new SendSkinPacket(), SendSkinData.class);
//        registerPacket(new RequestSkinPacket(), RequestSkinData.class);
//        registerPacket(new CanisTexturePacket(), CanisTextureData.class);
    }

    public static <MSG> void send(PacketDistributor.PacketTarget target, MSG message) {
        RigoranthusEmortisReborn.HANDLER.send(target, message);
    }

    public static <D> void registerPacket(IPacket<D> packet, Class<D> dataClass) {
        RigoranthusEmortisReborn.HANDLER.registerMessage(CanisPacketHandler.disc++, dataClass, packet::encode, packet::decode, packet::handle);
    }
}
