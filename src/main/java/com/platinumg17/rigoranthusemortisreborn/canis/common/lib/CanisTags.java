package com.platinumg17.rigoranthusemortisreborn.canis.common.lib;

import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class CanisTags {

    public static ITag.INamedTag<Item> SNACK_ITEMS_TAMED = tag("snack_items_tamed");
    public static ITag.INamedTag<Item> SNACK_ITEMS_UNTAMED = tag("snack_items_untamed");
    public static ITag.INamedTag<Item> BREEDING_ITEMS = tag("breeding_items");
    public static ITag.INamedTag<Item> WAYWARD_TRAVELLER_BLACKLIST = tag("wayward_traveller_blacklist");
    public static ITag.INamedTag<Item> TREATS = tag("treats");
    public static ITag.INamedTag<Item> BONES = tag("bones");

    public static ITag.INamedTag<Item> JESSIC_LOGS = tag("jessic_logs");
    public static ITag.INamedTag<Item> JESSIC = tag("jessic");
    public static ITag.INamedTag<Item> AZULOREAL_LOGS = tag("azuloreal_logs");
    public static ITag.INamedTag<Item> AZULOREAL = tag("azuloreal");

    private static ITag.INamedTag<Item> tag(String name) {
        return ItemTags.bind(REUtil.getResourcePath(name));
    }
}
