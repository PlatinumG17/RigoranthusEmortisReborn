package com.platinumg17.rigoranthusemortisreborn.magica.common.network;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.StackUtil;
import com.platinumg17.rigoranthusemortisreborn.canis.common.items.CommandWritItem;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSetCommandMode {

    public CompoundNBT tag;
    //Decoder
    public PacketSetCommandMode(PacketBuffer buf){
        tag = buf.readNbt();
    }

    //Encoder
    public void toBytes(PacketBuffer buf){
        buf.writeNbt(tag);
    }

    public PacketSetCommandMode(CompoundNBT tag){
        this.tag = tag;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            ctx.get().enqueueWork(()-> {
                ServerPlayerEntity sender = ctx.get().getSender();
                if (sender == null) return;

                ItemStack stack = StackUtil.getHeldWhistle(ctx.get().getSender());
                if (stack.getItem() instanceof CommandWritItem) {
                    stack.setTag(tag);
                }
            });
        } );
        ctx.get().setPacketHandled(true);
    }
}
