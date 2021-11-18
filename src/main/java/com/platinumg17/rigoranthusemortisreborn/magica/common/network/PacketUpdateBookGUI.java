package com.platinumg17.rigoranthusemortisreborn.magica.common.network;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.magica.client.gui.book.GuiSpellBook;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateBookGUI {

    public CompoundNBT tag;
    //Decoder
    public PacketUpdateBookGUI(PacketBuffer buf){
        tag = buf.readNbt();
    }

    //Encoder
    public void toBytes(PacketBuffer buf){
        buf.writeNbt(tag);
    }

    public PacketUpdateBookGUI(CompoundNBT tag){
        this.tag = tag;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            if(RigoranthusEmortisReborn.proxy.getMinecraft().screen instanceof GuiSpellBook)
                ((GuiSpellBook) RigoranthusEmortisReborn.proxy.getMinecraft().screen).spell_book_tag = tag;
        } );
        ctx.get().setPacketHandled(true);
    }
}