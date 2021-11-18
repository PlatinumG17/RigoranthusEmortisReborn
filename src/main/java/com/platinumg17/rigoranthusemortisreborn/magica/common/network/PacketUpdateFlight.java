package com.platinumg17.rigoranthusemortisreborn.magica.common.network;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateFlight {

    public boolean canFly;
    public boolean wasFlying;
    //Decoder
    public PacketUpdateFlight(PacketBuffer buf){
        canFly = buf.readBoolean();
        wasFlying = buf.readBoolean();
    }

    //Encoder
    public void toBytes(PacketBuffer buf){
        buf.writeBoolean(canFly);
        buf.writeBoolean(wasFlying);
    }

    public PacketUpdateFlight(boolean canFly){
        this.canFly = canFly;
    }

    public PacketUpdateFlight(boolean canFly, boolean wasFlying){
        this.canFly = canFly;
        this.wasFlying = wasFlying;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            RigoranthusEmortisReborn.proxy.getPlayer().abilities.mayfly = canFly;
            RigoranthusEmortisReborn.proxy.getPlayer().abilities.flying = wasFlying;
        });
        ctx.get().setPacketHandled(true);
    }
}