package com.platinumg17.rigoranthusemortisreborn.magica.common.network;

import com.platinumg17.rigoranthusemortisreborn.magica.common.event.ReflexSummonEvents;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketReflexSummon {

    public PacketReflexSummon(){}

    //Decoder
    public PacketReflexSummon(PacketBuffer buf){
    }

    //Encoder
    public void toBytes(PacketBuffer buf){}

    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            ServerPlayerEntity serverPlayerEntity = ctx.get().getSender();
            if(serverPlayerEntity!= null){
                ItemStack stack = serverPlayerEntity.getMainHandItem();
                ReflexSummonEvents.castSummon(ctx.get().getSender(), stack);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}