package com.platinumg17.rigoranthusemortisreborn.magica.common.network;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.magica.common.capability.DominionCapability;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateDominion {

    public double dominion;
    public int maxDominion;
    public int glyphBonus;
    public int tierBonus;
    //Decoder
    public PacketUpdateDominion(PacketBuffer buf){
        dominion = buf.readDouble();
        maxDominion = buf.readInt();
        glyphBonus = buf.readInt();
        tierBonus = buf.readInt();
    }

    //Encoder
    public void toBytes(PacketBuffer buf){
        buf.writeDouble(dominion);
        buf.writeInt(maxDominion);
        buf.writeInt(glyphBonus);
        buf.writeInt(tierBonus);
    }

    public PacketUpdateDominion(double dominion, int maxDominion, int glyphBonus, int tierBonus){
        this.dominion = dominion;
        this.maxDominion = maxDominion;
        this.glyphBonus = glyphBonus;
        this.tierBonus = tierBonus;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            if(RigoranthusEmortisReborn.proxy.getPlayer() == null)
                return;
            DominionCapability.getDominion(RigoranthusEmortisReborn.proxy.getPlayer()).ifPresent(dominion ->{
                dominion.setDominion(this.dominion);
                dominion.setMaxDominion(this.maxDominion);
                dominion.setGlyphBonus(this.glyphBonus);
                dominion.setBookTier(this.tierBonus);
            });
        } );
        ctx.get().setPacketHandled(true);
    }
}