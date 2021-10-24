package com.platinumg17.rigoranthusemortisreborn.entity;

import com.minecraftabnormals.abnormals_core.core.util.registry.EntitySubRegistryHelper;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.entity.item.BoneArrowEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.canis.CanisChordataEntity;
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
    public static final EntitySubRegistryHelper HELPER = RigoranthusEmortisReborn.REGISTRY_HELPER.getEntitySubHelper();

    public static final RegistryObject<EntityType<BoneArrowEntity>> BONE_ARROW =
            HELPER.createEntity("bone_arrow_entity",
                    BoneArrowEntity::new, BoneArrowEntity::new,
                    EntityClassification.MISC, 0.25F, 0.25F);

    public static final RegistryObject<EntityType<CanisChordataEntity>> CANIS_CHORDATA =
            ENTITY_TYPES.register("canis_chordata",
                    () -> EntityType.Builder.of(CanisChordataEntity::new,
                                    EntityClassification.MONSTER).sized(1.4f, 2f).clientTrackingRange(64)
                            .build(new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "canis_chordata").toString()));

    public static final RegistryObject<EntityType<SunderedCadaverEntity>> SUNDERED_CADAVER =
            ENTITY_TYPES.register("sundered_cadaver",
                    () -> EntityType.Builder.of(SunderedCadaverEntity::new,
                                    EntityClassification.MONSTER).sized(1f, 0.9f)
                            .build(new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "sundered_cadaver").toString()));

    public static final RegistryObject<EntityType<NecrawFasciiEntity>> NECRAW_FASCII =
            ENTITY_TYPES.register("necraw_fascii",
                    () -> EntityType.Builder.of(NecrawFasciiEntity::new,
                                    EntityClassification.MONSTER).sized(1f, 1.3f)
                                .build(new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "necraw_fascii").toString()));

    public static final RegistryObject<EntityType<LanguidDwellerEntity>> LANGUID_DWELLER =
            ENTITY_TYPES.register("languid_dweller",
                    () -> EntityType.Builder.of(LanguidDwellerEntity::new,
                                    EntityClassification.CREATURE).sized(1.6f, 1.8f)
                            .build(new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "languid_dweller").toString()));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
