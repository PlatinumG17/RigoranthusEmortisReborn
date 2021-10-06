package com.platinumg17.rigoranthusemortisreborn.util;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;

public class SmelteryTagUtil {
    public static Item getOreDict(String oreDic) {
        ResourceLocation tag = new ResourceLocation("forge", oreDic);
        ITag<Item> t = ItemTags.getAllTags().getTag(tag);
        if (t == null) {
            return null;
        }
        Collection<Item> tagCollection = t.getValues();
        for (Item item : tagCollection) {
            return item;
        }
        return null;
    }
}