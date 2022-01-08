package com.platinumg17.rigoranthusemortisreborn.magica.common.network;

//import com.platinumg17.rigoranthusemortisreborn.magica.ITimedEvent;
//import net.minecraft.nbt.CompoundNBT;
//import net.minecraft.network.PacketBuffer;
//import net.minecraftforge.fml.network.NetworkEvent;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.function.Supplier;
//
//public class PacketTimedEvent {
//    CompoundNBT tag;
//    //Decoder
//    public PacketTimedEvent(PacketBuffer buf){
//        tag = buf.readNbt();
//    }
//    //Encoder
//    public void toBytes(PacketBuffer buf){
//        buf.writeNbt(tag);
//    }
//
//    public PacketTimedEvent(CompoundNBT tag){
//        this.tag = tag;
//    }
//
//    public PacketTimedEvent(ITimedEvent event){
//        this.tag = new CompoundNBT();
//        event.serialize(tag);
//    }
//    public void handle(Supplier<NetworkEvent.Context> ctx){
//        ctx.get().enqueueWork(()->{
//            if(!methodMap.containsKey(tag.getString("id")))
//                throw new IllegalStateException("No event found for ID or ID missing");
//            methodMap.get(tag.getString("id")).apply(tag);
//        } );
//        ctx.get().setPacketHandled(true);
//    }
//    public static Map<String, Function<CompoundNBT, Void>> methodMap = new HashMap();
//
//    static{
////        methodMap.put(BossSummonEvent.ID, (nbt) -> BossSummonEvent.get(nbt).onPacketHandled());
////        methodMap.put(EruptionEvent.ID, (nbt) -> EruptionEvent.get(nbt).onPacketHandled());
//    }
//}
