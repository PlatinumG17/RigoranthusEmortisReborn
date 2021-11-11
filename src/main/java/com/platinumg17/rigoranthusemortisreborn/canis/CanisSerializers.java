package com.platinumg17.rigoranthusemortisreborn.canis;

import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.serializers.*;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.function.Supplier;

public class CanisSerializers {

    public static final DeferredRegister<DataSerializerEntry> SERIALIZERS = DeferredRegister.create(ForgeRegistries.DATA_SERIALIZERS, EmortisConstants.MOD_ID);

    public static final RegistryObject<DataSerializerEntry> SKILL_SERIALIZER = register2("skills", SkillListSerializer::new);
    public static final RegistryObject<DataSerializerEntry> COLLAR_TYPE_SERIALIZER = register2("collar", CollarSerializer::new);
    public static final RegistryObject<DataSerializerEntry> ACCOUTREMENT_SERIALIZER = register2("accouterments", AccoutrementSerializer::new);
    public static final RegistryObject<DataSerializerEntry> GENDER_SERIALIZER = register2("gender", GenderSerializer::new);
    public static final RegistryObject<DataSerializerEntry> MODE_SERIALIZER = register2("mode", ModeSerializer::new);
    public static final RegistryObject<DataSerializerEntry> CANIS_LEVEL_SERIALIZER = register2("canis_level", CanisLevelSerializer::new);
    public static final RegistryObject<DataSerializerEntry> BED_LOC_SERIALIZER = register2("canis_bed_location", BedLocationsSerializer::new);

    private static <X extends IDataSerializer<?>> RegistryObject<DataSerializerEntry> register2(final String name, final Supplier<X> factory) {
        return register(name, () -> new DataSerializerEntry(factory.get()));
    }

    private static RegistryObject<DataSerializerEntry> register(final String name, final Supplier<DataSerializerEntry> sup) {
        return SERIALIZERS.register(name, sup);
    }
}
