package com.platinumg17.rigoranthusemortisreborn.core.init.network;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.weapons.ISimplePacket;
import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.weapons.MagicEffectPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class REPacketHandler {

    public static void setupChannel() {
        nextIndex = 0;
//        registerMessage(DataCheckerPermissionPacket.class, DataCheckerPermissionPacket::decode);
//        registerMessage(AspectProgressBarDataPacket.class, AspectProgressBarDataPacket::decode);
//        registerMessage(ColorDataPacket.class, ColorDataPacket::decode);
//        registerMessage(PrimevalCoinDataPacket.class, PrimevalCoinDataPacket::decode);
//        registerMessage(TitleDataPacket.class, TitleDataPacket::decode);
//
//        registerMessage(ColorSelectPacket.class, ColorSelectPacket::decode);
//        registerMessage(TitleSelectPacket.class, TitleSelectPacket::decode);
//        registerMessage(ClearMessagePacket.class, ClearMessagePacket::decode);
//        registerMessage(DataCheckerPacket.class, DataCheckerPacket::decode);
//        registerMessage(ClientEditPacket.class, ClientEditPacket::decode);
//        registerMessage(ServerEditPacket.class, ServerEditPacket::decode);
//        registerMessage(AspectTogglePacket.class, AspectTogglePacket::decode);
        registerMessage(MagicEffectPacket.class, MagicEffectPacket::decode);
    }
    private static int nextIndex;

    private static <MSG extends ISimplePacket> void registerMessage(Class<MSG> messageType, Function<PacketBuffer, MSG> decoder) {
        registerMessage(messageType, ISimplePacket::encode, decoder, ISimplePacket::consume);
    }

    private static <MSG> void registerMessage(Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) {
        RigoranthusEmortisReborn.HANDLER.registerMessage(nextIndex++, messageType, encoder, decoder, messageConsumer);
    }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayerEntity player) {RigoranthusEmortisReborn.HANDLER.send(PacketDistributor.PLAYER.with(() -> player), message);}
    public static <MSG> void sendToAll(MSG message) {RigoranthusEmortisReborn.HANDLER.send(PacketDistributor.ALL.noArg(), message);}
    public static <MSG> void sendToNear(MSG message, PacketDistributor.TargetPoint point) {RigoranthusEmortisReborn.HANDLER.send(PacketDistributor.NEAR.with(() -> point), message);}
    public static <MSG> void sendToServer(MSG message) {RigoranthusEmortisReborn.HANDLER.sendToServer(message);}
    public static <MSG> void sendToTracking(MSG message, Entity entity) {RigoranthusEmortisReborn.HANDLER.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);}
}
