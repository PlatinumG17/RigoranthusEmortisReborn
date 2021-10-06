package com.platinumg17.rigoranthusemortisreborn.entity;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.CanisChordataEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.LanguidDwellerEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.NecrawFasciiEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RigoranthusEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, RigoranthusEmortisReborn.MOD_ID);

    public static final RegistryObject<EntityType<CanisChordataEntity>> CANIS_CHORDATA =
            ENTITY_TYPES.register("canis_chordata",
                    () -> EntityType.Builder.of(CanisChordataEntity::new,
                                    EntityClassification.MONSTER).sized(1.5f, 2.3f).clientTrackingRange(64)
                            .build(new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "canis_chordata").toString()));

    public static final RegistryObject<EntityType<SunderedCadaverEntity>> SUNDERED_CADAVER =
            ENTITY_TYPES.register("sundered_cadaver",
                    () -> EntityType.Builder.of(SunderedCadaverEntity::new,
                                    EntityClassification.MONSTER).sized(1f, 3f)
                            .build(new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "sundered_cadaver").toString()));

    public static final RegistryObject<EntityType<NecrawFasciiEntity>> NECRAW_FASCII =
            ENTITY_TYPES.register("necraw_fascii",
                    () -> EntityType.Builder.of(NecrawFasciiEntity::new,
                                    EntityClassification.MONSTER).sized(1f, 3f)
                                .build(new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "necraw_fascii").toString()));

    public static final RegistryObject<EntityType<LanguidDwellerEntity>> LANGUID_DWELLER =
            ENTITY_TYPES.register("languid_dweller",
                    () -> EntityType.Builder.of(LanguidDwellerEntity::new,
                                    EntityClassification.CREATURE).sized(0.4f, 0.3f)
                            .build(new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "languid_dweller").toString()));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
