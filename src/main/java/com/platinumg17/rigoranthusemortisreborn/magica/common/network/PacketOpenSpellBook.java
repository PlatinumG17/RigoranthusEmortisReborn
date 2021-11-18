package com.platinumg17.rigoranthusemortisreborn.magica.common.network;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.magica.client.gui.book.GuiSpellBook;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketOpenSpellBook {
    public CompoundNBT tag;
    public int tier;
    public String unlockedSpells;

    //Decoder
    public PacketOpenSpellBook(PacketBuffer buf){
        tag = buf.readNbt();
        tier = buf.readInt();
        unlockedSpells = buf.readUtf(32767);
    }

    //Encoder
    public void toBytes(PacketBuffer buf){
        buf.writeNbt(tag);
        buf.writeInt(tier);
        buf.writeUtf(unlockedSpells);
    }

    public PacketOpenSpellBook(CompoundNBT tag, int tier, String unlockedSpells){
        this.tag = tag;
        this.tier = tier;
        this.unlockedSpells = unlockedSpells;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()-> GuiSpellBook.open(RigoranthusEmortisRebornAPI.getInstance(), tag, tier, unlockedSpells));
        ctx.get().setPacketHandled(true);
    }
}