package com.platinumg17.rigoranthusemortisreborn.magica.setup;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;

public class EntityTags {
    public static final ITag.INamedTag<EntityType<?>> MOB_BLACKLIST = EntityTypeTags.createOptional(RigoranthusEmortisReborn.rl("mob_blacklist"));
}