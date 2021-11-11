package com.platinumg17.rigoranthusemortisreborn.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.platinumg17.rigoranthusemortisreborn.api.registry.*;
import net.minecraftforge.registries.IForgeRegistry;

public class RigoranthusEmortisRebornAPI {

    public static IForgeRegistry<Skill> SKILLS;
    public static IForgeRegistry<Accoutrement> ACCOUTERMENTS;
    public static IForgeRegistry<AccoutrementType> ACCOUTREMENT_TYPE;
    public static IForgeRegistry<IBeddingMaterial> BEDDING_MATERIAL;
    public static IForgeRegistry<ICasingMaterial> CASING_MATERIAL;

    public static final Logger LOGGER = LogManager.getLogger("rigoranthusemortisreborn");
}
