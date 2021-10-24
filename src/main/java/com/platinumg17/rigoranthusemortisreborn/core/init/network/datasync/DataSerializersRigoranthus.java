package com.platinumg17.rigoranthusemortisreborn.core.init.network.datasync;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.canis.CanisEvolutionData;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DataSerializerEntry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = RigoranthusEmortisReborn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataSerializersRigoranthus {
    private static final List<DataSerializerEntry> DATA_SERIALIZER_ENTRIES = new ArrayList<>();
    public static final IDataSerializer<CanisEvolutionData> CANIS_EVOLUTION_DATA = new IDataSerializer<CanisEvolutionData>() {

        @Override
        public void write(PacketBuffer buffer, CanisEvolutionData value) {
            buffer.writeVarInt(value.getLevel());
        }

        @Override
        public CanisEvolutionData read(PacketBuffer buffer) {
            return new CanisEvolutionData(buffer.readVarInt());
        }

        @Override
        public CanisEvolutionData copy(CanisEvolutionData value) {
            return value;
        }
    };
    private static final DataSerializerEntry CANIS_EVOLUTION_DATA_ENTRY = register("canis_evolution_data", new DataSerializerEntry(CANIS_EVOLUTION_DATA));

    public static DataSerializerEntry register(String name, DataSerializerEntry dataSerializerEntry) {
        ResourceLocation id = new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, name);
        dataSerializerEntry.setRegistryName(id);
        DATA_SERIALIZER_ENTRIES.add(dataSerializerEntry);
        return dataSerializerEntry;
    }

    @SubscribeEvent
    public static void registerDataSerializers(RegistryEvent.Register<DataSerializerEntry> event) {
        for (DataSerializerEntry dataSerializerEntry : DATA_SERIALIZER_ENTRIES) {
            event.getRegistry().register(dataSerializerEntry);
        }
    }
}