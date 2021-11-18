package com.platinumg17.rigoranthusemortisreborn.magica.setup;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class EntityTags {
    public static final ITag.INamedTag<EntityType<?>> MOB_BLACKLIST = EntityTypeTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "mob_blacklist"));
    public static final ITag.INamedTag<EntityType<?>> DISINTEGRATION_WHITELIST = EntityTypeTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "disintegration_whitelist"));
    public static final ITag.INamedTag<EntityType<?>> DISINTEGRATION_BLACKLIST = EntityTypeTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "disintegration_blacklist"));
}