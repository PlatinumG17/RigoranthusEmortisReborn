package com.platinumg17.rigoranthusemortisreborn.core.registry.effects.weapons;

import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

public interface IPlayerToClientPacket extends ISimplePacket {
    @Override
    default void consume(Supplier<NetworkEvent.Context> ctx) {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            ctx.get().enqueueWork(this::execute);
        ctx.get().setPacketHandled(true);
    }
    void execute();
}
