package com.platinumg17.rigoranthusemortisreborn.magica.common.network;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.familiar.FamiliarCap;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.familiar.IFamiliarCap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSyncFamiliars {
    CompoundNBT tag;
    //Decoder
    public PacketSyncFamiliars(PacketBuffer buf){
        tag = buf.readNbt();
    }

    //Encoder
    public void toBytes(PacketBuffer buf){
        buf.writeNbt(tag);
    }

    public PacketSyncFamiliars(CompoundNBT famCaps){
        this.tag = famCaps;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            PlayerEntity playerEntity = RigoranthusEmortisReborn.proxy.getPlayer();
            IFamiliarCap cap = FamiliarCap.getFamiliarCap(playerEntity).orElse(null);
            if(cap != null){
                cap.setUnlockedFamiliars(FamiliarCap.deserializeFamiliars(tag));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}