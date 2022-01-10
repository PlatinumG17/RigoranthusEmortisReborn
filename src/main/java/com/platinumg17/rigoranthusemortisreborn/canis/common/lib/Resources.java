package com.platinumg17.rigoranthusemortisreborn.canis.common.lib;

import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;
import net.minecraft.util.ResourceLocation;

public class Resources {

    public static final ResourceLocation GUI_FOOD_BOWL = getGui("food_bowl");
    public static final ResourceLocation GUI_WAYWARD_TRAVELLER = getGui("wayward_traveller");
    public static final ResourceLocation GUI_TREAT_BAG = getGui("treat_bag");

    public static final ResourceLocation CANIS_INVENTORY = getGui("canis_inventory");
    public static final ResourceLocation SMALL_WIDGETS = getGui("small_widgets");
    public static final ResourceLocation PAGE_SELECTION = getGui("page_selection");
    public static final ResourceLocation SKILL_BUTTON = getGui("skill_button");
    public static final ResourceLocation MODE_BUTTON = getGui("mode_button");
    public static final ResourceLocation BOOLEAN_BUTTON = getGui("boolean_button");

    public static final ResourceLocation CHORDATA_TEXTURE = getEntity("canis/evolutions", "tame_canis_chordata");
    public static final ResourceLocation KYPHOS_TEXTURE = getEntity("canis/evolutions", "canis_kyphos");
    public static final ResourceLocation CAVALIER_TEXTURE = getEntity("canis/evolutions", "canis_cavalier");
    public static final ResourceLocation HOMINI_TEXTURE = getEntity("canis/evolutions", "canis_homini");

    public static final ResourceLocation COLLAR_DEFAULT = getEntity("canis", "canis_collar");
    public static final ResourceLocation COLLAR_GOLDEN = getEntity("canis", "canis_collar_gold");
    public static final ResourceLocation GLASSES_SUNGLASSES = getEntity("canis", "cool_guy_sunglasses");
    public static final ResourceLocation BOW_TIE = getEntity("canis", "canis_bowtie");
    public static final ResourceLocation CAPE = getEntity("canis", "canis_cape");
    public static final ResourceLocation SKILL_SAVIOR = getEntity("canis/skills", "savior");
    public static final ResourceLocation SKILL_CHEST = getEntity("canis", "canis_satchel");

    public static final ResourceLocation IRON_HELMET = getEntity("canis", "armor/iron_helmet");
    public static final ResourceLocation DIAMOND_HELMET = getEntity("canis", "armor/diamond_helmet");
    public static final ResourceLocation GOLDEN_HELMET = getEntity("canis", "armor/golden_helmet");
    public static final ResourceLocation CHAINMAIL_HELMET = getEntity("canis", "armor/chainmail_helmet");
    public static final ResourceLocation LEATHER_HELMET = getEntity("canis", "armor/leather_helmet");
    public static final ResourceLocation TURTLE_HELMET = getEntity("canis", "armor/turtle_helmet");
    public static final ResourceLocation NETHERITE_HELMET = getEntity("canis", "armor/netherite_helmet");

    public static final ResourceLocation IRON_BOOTS = getEntity("canis", "armor/iron_boots");
    public static final ResourceLocation DIAMOND_BOOTS = getEntity("canis", "armor/diamond_boots");
    public static final ResourceLocation GOLDEN_BOOTS = getEntity("canis", "armor/golden_boots");
    public static final ResourceLocation CHAINMAIL_BOOTS = getEntity("canis", "armor/chainmail_boots");
    public static final ResourceLocation LEATHER_BOOTS = getEntity("canis", "armor/leather_boots");
    public static final ResourceLocation NETHERITE_BOOTS = getEntity("canis", "armor/netherite_boots");

    public static final ResourceLocation IRON_BODY_PIECE = getEntity("canis", "armor/iron_body");
    public static final ResourceLocation DIAMOND_BODY_PIECE = getEntity("canis", "armor/diamond_body");
    public static final ResourceLocation GOLDEN_BODY_PIECE = getEntity("canis", "armor/golden_body");
    public static final ResourceLocation CHAINMAIL_BODY_PIECE = getEntity("canis", "armor/chainmail_body");
    public static final ResourceLocation LEATHER_BODY_PIECE = getEntity("canis", "armor/leather_body");
    public static final ResourceLocation NETHERITE_BODY_PIECE = getEntity("canis", "armor/netherite_body");

    public static ResourceLocation getEntity(String type, String textureFileName) {
        return REUtil.getResource("textures/entity/" + type + "/" + textureFileName + ".png");
    }

    public static ResourceLocation getGui(String textureFileName) {
        return REUtil.getResource("textures/gui/" + textureFileName + ".png");
    }
}