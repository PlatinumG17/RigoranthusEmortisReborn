package com.platinumg17.rigoranthusemortisreborn.core.registry;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.Tags.IOptionalNamedTag;

public class RigoranthusTagRegistry {
    public static class Blocks {



        public static final Tags.IOptionalNamedTag<Block> JESSIC_LOGS =
                createTag("jessic_logs");

        public static final Tags.IOptionalNamedTag<Block> JESSIC =
                createTag("jessic");

        public static final Tags.IOptionalNamedTag<Block> AZULOREAL_LOGS =
                createTag("azuloreal_logs");

        public static final Tags.IOptionalNamedTag<Block> AZULOREAL =
                createTag("azuloreal");

        public static final Tags.IOptionalNamedTag<Block> MASTERFUL =
                createForgeTag("furnaces/masterful");

        public static final Tags.IOptionalNamedTag<Block> JESSIC_LOG =
                createForgeTag("logs/jessic");

        public static final Tags.IOptionalNamedTag<Block> JESSIC_FENCE =
                createForgeTag("wooden_fences/jessic");

        public static final Tags.IOptionalNamedTag<Block> JESSIC_FENCE_GATE =
                createForgeTag("fence_gates/jessic");

        public static final Tags.IOptionalNamedTag<Block> AZULOREAL_LOG =
                createForgeTag("logs/azuloreal");

        public static final Tags.IOptionalNamedTag<Block> AZULOREAL_FENCE =
                createForgeTag("wooden_fences/azuloreal");

        public static final Tags.IOptionalNamedTag<Block> AZULOREAL_FENCE_GATE =
                createForgeTag("fence_gates/azuloreal");

        public static IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, name));
        }

        public static IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }
    }

    public static class Items {

        public static final Tags.IOptionalNamedTag<Item> CRUSHING_HAMMER =
                createTag("crushing_hammer");

        public static final Tags.IOptionalNamedTag<Item> NETHERITE_INGOTS =
                createForgeTag("ingots/netherite");

        public static final Tags.IOptionalNamedTag<Item> JESSIC_LOGS =
                createTag("jessic_logs");

        public static final Tags.IOptionalNamedTag<Item> JESSIC =
                createTag("jessic");

        public static final Tags.IOptionalNamedTag<Item> AZULOREAL_LOGS =
                createTag("azuloreal_logs");

        public static final Tags.IOptionalNamedTag<Item> AZULOREAL =
                createTag("azuloreal");

        public static final Tags.IOptionalNamedTag<Item> AZULOREAL_LOG =
                createForgeTag("logs/azuloreal");

        public static final Tags.IOptionalNamedTag<Item> AZULOREAL_FENCE =
                createForgeTag("wooden_fences/azuloreal");

        public static final Tags.IOptionalNamedTag<Item> AZULOREAL_FENCE_GATE =
                createForgeTag("fence_gates/azuloreal");

        public static final Tags.IOptionalNamedTag<Item> CRUSHING_HAMMERS =
                createTag("crushing_hammers");

        public static final Tags.IOptionalNamedTag<Item> RIGORANTHUS_INGOTS =
                createTag("rigoranthus_ingots");

        public static final Tags.IOptionalNamedTag<Item> APOGEAN_ARMOR_SET =
                createTag("armor/apogean_armor_set");

        public static final Tags.IOptionalNamedTag<Item> AQUEOUS_ARMOR_SET =
                createTag("armor/aqueous_armor_set");

        public static final Tags.IOptionalNamedTag<Item> ATROPHYING_ARMOR_SET =
                createTag("armor/atrophying_armor_set");

        public static final Tags.IOptionalNamedTag<Item> INCORPOREAL_ARMOR_SET =
                createTag("armor/incorporeal_armor_set");

        public static final Tags.IOptionalNamedTag<Item> INFERNAL_ARMOR_SET =
                createTag("armor/infernal_armor_set");

        public static final Tags.IOptionalNamedTag<Item> OPULENT_ARMOR_SET =
                createTag("armor/opulent_armor_set");

        public static final Tags.IOptionalNamedTag<Item> PERNICIOUS_ARMOR_SET =
                createTag("armor/pernicious_armor_set");

        public static final Tags.IOptionalNamedTag<Item> PHANTASMAL_ARMOR_SET =
                createTag("armor/phantasmal_armor_set");

        public static final Tags.IOptionalNamedTag<Item> REMEX_ARMOR_SET =
                createTag("armor/remex_armor_set");

        public static final Tags.IOptionalNamedTag<Item> MASTERFUL =
                createForgeTag("furnaces/masterful");

        public static final Tags.IOptionalNamedTag<Item> JESSIC_LOG =
                createForgeTag("logs/jessic");

        public static final Tags.IOptionalNamedTag<Item> JESSIC_FENCE =
                createForgeTag("wooden_fences/jessic");

        public static final Tags.IOptionalNamedTag<Item> JESSIC_FENCE_GATE =
                createForgeTag("fence_gates/jessic");

        public static final Tags.IOptionalNamedTag<Item> NETHERITE_ARMOR =
                createForgeTag("armor/netherite");

        public static final Tags.IOptionalNamedTag<Item> NETHERITE_TOOLS =
                createForgeTag("tools/netherite");

        public static final Tags.IOptionalNamedTag<Item> AXES =
                createForgeTag("axes");

        public static final Tags.IOptionalNamedTag<Item> BOOTS =
                createForgeTag("boots");

        public static final Tags.IOptionalNamedTag<Item> CHESTPLATES =
                createForgeTag("chestplates");

        public static final Tags.IOptionalNamedTag<Item> COAL =
                createForgeTag("coal");

        public static final Tags.IOptionalNamedTag<Item> HELMETS =
                createForgeTag("helmets");

        public static final Tags.IOptionalNamedTag<Item> INGOTS =
                createForgeTag("ingots");

        public static final Tags.IOptionalNamedTag<Item> LEATHER =
                createForgeTag("leather");

        public static final Tags.IOptionalNamedTag<Item> LEGGINGS =
                createForgeTag("leggings");

        public static final Tags.IOptionalNamedTag<Item> MUD_BALL =
                createForgeTag("mud_ball");

        public static final Tags.IOptionalNamedTag<Item> SWORDS =
                createForgeTag("swords");

        public static final Tags.IOptionalNamedTag<Item> TOOLS =
                createForgeTag("tools");

        public static IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, name));
        }

        public static IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }
}