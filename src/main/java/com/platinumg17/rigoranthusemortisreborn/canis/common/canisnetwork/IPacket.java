package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface IPacket<D> {

    void encode(D data, PacketBuffer buf);

    D decode(PacketBuffer buf);

    void handle(D data, Supplier<NetworkEvent.Context> ctx);
}
