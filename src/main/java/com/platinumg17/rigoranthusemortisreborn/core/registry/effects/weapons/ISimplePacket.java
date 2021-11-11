package com.platinumg17.rigoranthusemortisreborn.core.registry.effects.weapons;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

public interface ISimplePacket {
    void encode(PacketBuffer buffer);
    void consume(Supplier<NetworkEvent.Context> ctx);
}
