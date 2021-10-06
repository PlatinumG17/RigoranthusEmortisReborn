package com.platinumg17.rigoranthusemortisreborn.core.events;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.CanisChordataEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.LanguidDwellerEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.NecrawFasciiEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
import com.platinumg17.rigoranthusemortisreborn.items.RigoranthusSpawnEgg;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RigoranthusEmortisReborn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RigoranthusEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(RigoranthusEntityTypes.CANIS_CHORDATA.get(), CanisChordataEntity.setCustomAttributes().build());
        event.put(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), SunderedCadaverEntity.setCustomAttributes().build());
        event.put(RigoranthusEntityTypes.NECRAW_FASCII.get(), NecrawFasciiEntity.setCustomAttributes().build());
        event.put(RigoranthusEntityTypes.LANGUID_DWELLER.get(), LanguidDwellerEntity.setCustomAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        RigoranthusSpawnEgg.initSpawnEggs();
    }
}