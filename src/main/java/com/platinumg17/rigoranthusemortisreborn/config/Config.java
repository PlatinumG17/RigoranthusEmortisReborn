package com.platinumg17.rigoranthusemortisreborn.config;

import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.world.gen.OreType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.UUID;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_SMELTERY = "smeltery";
    public static final String CATEGORY_ORES = "ores";
    public static final String CATEGORY_WEAPONS = "weapons";
    public static final String CATEGORY_DWELLER_ARMOR = "dweller_armor";
    public static final String CATEGORY_APOGEAN_ARMOR = "apogean_armor";
    public static final String CATEGORY_AQUEOUS_ARMOR = "aqueous_armor";
    public static final String CATEGORY_ATROPHYING_ARMOR = "atrophying_armor";
    public static final String CATEGORY_INCORPOREAL_ARMOR = "incorporeal_armor";
    public static final String CATEGORY_INFERNAL_ARMOR = "infernal_armor";
    public static final String CATEGORY_OPULENT_ARMOR = "opulent_armor";
    public static final String CATEGORY_PERNICIOUS_ARMOR = "pernicious_armor";
    public static final String CATEGORY_PHANTASMAL_ARMOR = "phantasmal_armor";
    public static final String CATEGORY_REMEX_ARMOR = "remex_armor";
    public static final String CATEGORY_BLOCKS = "blocks";
    public static final String CATEGORY_JEI = "jei";
    public static final String CATEGORY_MISC = "misc";

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.IntValue smelteryXPDropValue;
    public static ForgeConfigSpec.IntValue smelteryXPDropValue2;
    public static ForgeConfigSpec.IntValue masterfulSmelterySpeed;

    public static ForgeConfigSpec.BooleanValue enableJeiPlugin;
    public static ForgeConfigSpec.BooleanValue enableJeiCatalysts;
    public static ForgeConfigSpec.BooleanValue enableJeiClickArea;

    public static ForgeConfigSpec.BooleanValue enableNetheriteAdditions;
    public static ForgeConfigSpec.BooleanValue enableArmorSetBonuses;

    public static ForgeConfigSpec.IntValue bone_bow_projectile_range;
    public static ForgeConfigSpec.IntValue bone_bow_durability;

    public static ForgeConfigSpec.DoubleValue bone_spear_speed;
    public static ForgeConfigSpec.IntValue bone_spear_damage;
    public static ForgeConfigSpec.IntValue bone_tier_durability;
    public static ForgeConfigSpec.DoubleValue bone_tier_speed;
    public static ForgeConfigSpec.DoubleValue bone_tier_damage;
    public static ForgeConfigSpec.IntValue bone_tier_enchantability;

    public static ForgeConfigSpec.DoubleValue apogean_axe_speed;
    public static ForgeConfigSpec.IntValue apogean_axe_damage;
    public static ForgeConfigSpec.DoubleValue apogean_sword_speed;
    public static ForgeConfigSpec.IntValue apogean_sword_damage;
    public static ForgeConfigSpec.IntValue apogean_tier_durability;
    public static ForgeConfigSpec.DoubleValue apogean_tier_speed;
    public static ForgeConfigSpec.DoubleValue apogean_tier_damage;
    public static ForgeConfigSpec.IntValue apogean_tier_enchantability;

    public static ForgeConfigSpec.DoubleValue aqueous_axe_speed;
    public static ForgeConfigSpec.IntValue aqueous_axe_damage;
    public static ForgeConfigSpec.DoubleValue aqueous_sword_speed;
    public static ForgeConfigSpec.IntValue aqueous_sword_damage;
    public static ForgeConfigSpec.IntValue aqueous_tier_durability;
    public static ForgeConfigSpec.DoubleValue aqueous_tier_speed;
    public static ForgeConfigSpec.DoubleValue aqueous_tier_damage;
    public static ForgeConfigSpec.IntValue aqueous_tier_enchantability;

    public static ForgeConfigSpec.DoubleValue atrophying_axe_speed;
    public static ForgeConfigSpec.IntValue atrophying_axe_damage;
    public static ForgeConfigSpec.DoubleValue atrophying_sword_speed;
    public static ForgeConfigSpec.IntValue atrophying_sword_damage;
    public static ForgeConfigSpec.IntValue atrophying_tier_durability;
    public static ForgeConfigSpec.DoubleValue atrophying_tier_speed;
    public static ForgeConfigSpec.DoubleValue atrophying_tier_damage;
    public static ForgeConfigSpec.IntValue atrophying_tier_enchantability;

    public static ForgeConfigSpec.DoubleValue incorporeal_axe_speed;
    public static ForgeConfigSpec.IntValue incorporeal_axe_damage;
    public static ForgeConfigSpec.DoubleValue incorporeal_sword_speed;
    public static ForgeConfigSpec.IntValue incorporeal_sword_damage;
    public static ForgeConfigSpec.IntValue incorporeal_tier_durability;
    public static ForgeConfigSpec.DoubleValue incorporeal_tier_speed;
    public static ForgeConfigSpec.DoubleValue incorporeal_tier_damage;
    public static ForgeConfigSpec.IntValue incorporeal_tier_enchantability;

    public static ForgeConfigSpec.DoubleValue infernal_axe_speed;
    public static ForgeConfigSpec.IntValue infernal_axe_damage;
    public static ForgeConfigSpec.IntValue infernal_sword_damage;
    public static ForgeConfigSpec.DoubleValue infernal_sword_speed;
    public static ForgeConfigSpec.IntValue infernal_tier_durability;
    public static ForgeConfigSpec.DoubleValue infernal_tier_speed;
    public static ForgeConfigSpec.DoubleValue infernal_tier_damage;
    public static ForgeConfigSpec.IntValue infernal_tier_enchantability;

    public static ForgeConfigSpec.DoubleValue opulent_axe_speed;
    public static ForgeConfigSpec.IntValue opulent_axe_damage;
    public static ForgeConfigSpec.DoubleValue opulent_sword_speed;
    public static ForgeConfigSpec.IntValue opulent_sword_damage;
    public static ForgeConfigSpec.IntValue opulent_tier_durability;
    public static ForgeConfigSpec.DoubleValue opulent_tier_speed;
    public static ForgeConfigSpec.DoubleValue opulent_tier_damage;
    public static ForgeConfigSpec.IntValue opulent_tier_enchantability;

    public static ForgeConfigSpec.DoubleValue pernicious_axe_speed;
    public static ForgeConfigSpec.IntValue pernicious_axe_damage;
    public static ForgeConfigSpec.DoubleValue pernicious_sword_speed;
    public static ForgeConfigSpec.IntValue pernicious_sword_damage;
    public static ForgeConfigSpec.IntValue pernicious_tier_durability;
    public static ForgeConfigSpec.DoubleValue pernicious_tier_speed;
    public static ForgeConfigSpec.DoubleValue pernicious_tier_damage;
    public static ForgeConfigSpec.IntValue pernicious_tier_enchantability;

    public static ForgeConfigSpec.DoubleValue phantasmal_sword_speed;
    public static ForgeConfigSpec.IntValue phantasmal_sword_damage;
    public static ForgeConfigSpec.DoubleValue phantasmal_axe_speed;
    public static ForgeConfigSpec.IntValue phantasmal_axe_damage;
    public static ForgeConfigSpec.IntValue phantasmal_tier_durability;
    public static ForgeConfigSpec.DoubleValue phantasmal_tier_speed;
    public static ForgeConfigSpec.DoubleValue phantasmal_tier_damage;
    public static ForgeConfigSpec.IntValue phantasmal_tier_enchantability;

    public static ForgeConfigSpec.DoubleValue remex_sword_speed;
    public static ForgeConfigSpec.IntValue remex_sword_damage;
    public static ForgeConfigSpec.DoubleValue remex_axe_speed;
    public static ForgeConfigSpec.IntValue remex_axe_damage;
    public static ForgeConfigSpec.IntValue remex_tier_durability;
    public static ForgeConfigSpec.DoubleValue remex_tier_speed;
    public static ForgeConfigSpec.DoubleValue remex_tier_damage;
    public static ForgeConfigSpec.IntValue remex_tier_enchantability;

    public static ForgeConfigSpec.IntValue dweller_thorax_durability_multiplier;
    public static ForgeConfigSpec.IntValue dweller_thorax_chestplate_damage_reduction;
    public static ForgeConfigSpec.IntValue dweller_thorax_enchantability;
    public static ForgeConfigSpec.DoubleValue dweller_thorax_toughness;
    public static ForgeConfigSpec.DoubleValue dweller_thorax_knockback_resistance;

    public static ForgeConfigSpec.IntValue apogean_boots_damage_reduction;
    public static ForgeConfigSpec.IntValue apogean_leggings_damage_reduction;
    public static ForgeConfigSpec.IntValue apogean_chestplate_damage_reduction;
    public static ForgeConfigSpec.IntValue apogean_helmet_damage_reduction;
    public static ForgeConfigSpec.IntValue apogean_durability_multiplier;
    public static ForgeConfigSpec.IntValue apogean_armor_enchantability;
    public static ForgeConfigSpec.DoubleValue apogean_toughness;
    public static ForgeConfigSpec.DoubleValue apogean_knockback_resistance;

    public static ForgeConfigSpec.IntValue aqueous_boots_damage_reduction;
    public static ForgeConfigSpec.IntValue aqueous_leggings_damage_reduction;
    public static ForgeConfigSpec.IntValue aqueous_chestplate_damage_reduction;
    public static ForgeConfigSpec.IntValue aqueous_helmet_damage_reduction;
    public static ForgeConfigSpec.IntValue aqueous_durability_multiplier;
    public static ForgeConfigSpec.IntValue aqueous_armor_enchantability;
    public static ForgeConfigSpec.DoubleValue aqueous_toughness;
    public static ForgeConfigSpec.DoubleValue aqueous_knockback_resistance;

    public static ForgeConfigSpec.IntValue atrophying_boots_damage_reduction;
    public static ForgeConfigSpec.IntValue atrophying_leggings_damage_reduction;
    public static ForgeConfigSpec.IntValue atrophying_chestplate_damage_reduction;
    public static ForgeConfigSpec.IntValue atrophying_helmet_damage_reduction;
    public static ForgeConfigSpec.IntValue atrophying_durability_multiplier;
    public static ForgeConfigSpec.IntValue atrophying_armor_enchantability;
    public static ForgeConfigSpec.DoubleValue atrophying_toughness;
    public static ForgeConfigSpec.DoubleValue atrophying_knockback_resistance;

    public static ForgeConfigSpec.IntValue incorporeal_boots_damage_reduction;
    public static ForgeConfigSpec.IntValue incorporeal_leggings_damage_reduction;
    public static ForgeConfigSpec.IntValue incorporeal_chestplate_damage_reduction;
    public static ForgeConfigSpec.IntValue incorporeal_helmet_damage_reduction;
    public static ForgeConfigSpec.IntValue incorporeal_durability_multiplier;
    public static ForgeConfigSpec.IntValue incorporeal_armor_enchantability;
    public static ForgeConfigSpec.DoubleValue incorporeal_toughness;
    public static ForgeConfigSpec.DoubleValue incorporeal_knockback_resistance;

    public static ForgeConfigSpec.IntValue infernal_boots_damage_reduction;
    public static ForgeConfigSpec.IntValue infernal_leggings_damage_reduction;
    public static ForgeConfigSpec.IntValue infernal_chestplate_damage_reduction;
    public static ForgeConfigSpec.IntValue infernal_helmet_damage_reduction;
    public static ForgeConfigSpec.IntValue infernal_durability_multiplier;
    public static ForgeConfigSpec.IntValue infernal_armor_enchantability;
    public static ForgeConfigSpec.DoubleValue infernal_toughness;
    public static ForgeConfigSpec.DoubleValue infernal_knockback_resistance;

    public static ForgeConfigSpec.IntValue opulent_boots_damage_reduction;
    public static ForgeConfigSpec.IntValue opulent_leggings_damage_reduction;
    public static ForgeConfigSpec.IntValue opulent_chestplate_damage_reduction;
    public static ForgeConfigSpec.IntValue opulent_helmet_damage_reduction;
    public static ForgeConfigSpec.IntValue opulent_durability_multiplier;
    public static ForgeConfigSpec.IntValue opulent_armor_enchantability;
    public static ForgeConfigSpec.DoubleValue opulent_toughness;
    public static ForgeConfigSpec.DoubleValue opulent_knockback_resistance;

    public static ForgeConfigSpec.IntValue pernicious_boots_damage_reduction;
    public static ForgeConfigSpec.IntValue pernicious_leggings_damage_reduction;
    public static ForgeConfigSpec.IntValue pernicious_chestplate_damage_reduction;
    public static ForgeConfigSpec.IntValue pernicious_helmet_damage_reduction;
    public static ForgeConfigSpec.IntValue pernicious_durability_multiplier;
    public static ForgeConfigSpec.IntValue pernicious_armor_enchantability;
    public static ForgeConfigSpec.DoubleValue pernicious_toughness;
    public static ForgeConfigSpec.DoubleValue pernicious_knockback_resistance;

    public static ForgeConfigSpec.IntValue phantasmal_boots_damage_reduction;
    public static ForgeConfigSpec.IntValue phantasmal_leggings_damage_reduction;
    public static ForgeConfigSpec.IntValue phantasmal_chestplate_damage_reduction;
    public static ForgeConfigSpec.IntValue phantasmal_helmet_damage_reduction;
    public static ForgeConfigSpec.IntValue phantasmal_durability_multiplier;
    public static ForgeConfigSpec.IntValue phantasmal_armor_enchantability;
    public static ForgeConfigSpec.DoubleValue phantasmal_toughness;
    public static ForgeConfigSpec.DoubleValue phantasmal_knockback_resistance;

    public static ForgeConfigSpec.IntValue remex_boots_damage_reduction;
    public static ForgeConfigSpec.IntValue remex_leggings_damage_reduction;
    public static ForgeConfigSpec.IntValue remex_chestplate_damage_reduction;
    public static ForgeConfigSpec.IntValue remex_helmet_damage_reduction;
    public static ForgeConfigSpec.IntValue remex_durability_multiplier;
    public static ForgeConfigSpec.IntValue remex_armor_enchantability;
    public static ForgeConfigSpec.DoubleValue remex_toughness;
    public static ForgeConfigSpec.DoubleValue remex_knockback_resistance;

    public static ForgeConfigSpec.IntValue minOreHeight;
    public static ForgeConfigSpec.IntValue maxOreHeight;
    public static ForgeConfigSpec.IntValue maxVeinSize;

    public static ForgeConfigSpec.BooleanValue enableHammersAndVanillaOreFragments;
    public static ForgeConfigSpec.BooleanValue enableModdedOreFragments;
    public static ForgeConfigSpec.IntValue stone_hammer_durability;
    public static ForgeConfigSpec.IntValue iron_hammer_durability;
    public static ForgeConfigSpec.IntValue gold_hammer_durability;
    public static ForgeConfigSpec.IntValue diamond_hammer_durability;
    public static ForgeConfigSpec.IntValue abyssalite_hammer_durability;

    public static ForgeConfigSpec.BooleanValue enableBoneWeapons;
    public static ForgeConfigSpec.BooleanValue enableSoulCoal;
    public static ForgeConfigSpec.IntValue soulCoalBurnTime;
    public static ForgeConfigSpec.BooleanValue enableNewWoodTypes;
    public static ForgeConfigSpec.BooleanValue enableUnfiredBricks;
    public static ForgeConfigSpec.BooleanValue GIVEN_COAL;
    public static ForgeConfigSpec.BooleanValue showErrors;

//    public static ForgeConfigSpec.BooleanValue mountains;
//    public static ForgeConfigSpec.BooleanValue modified;
//    public static ForgeConfigSpec.BooleanValue forest;
//    public static ForgeConfigSpec.BooleanValue savanna;
//    public static ForgeConfigSpec.BooleanValue coniferous;
//    public static ForgeConfigSpec.BooleanValue jungle;
//    public static ForgeConfigSpec.BooleanValue spooky;
//    public static ForgeConfigSpec.BooleanValue dead;
//    public static ForgeConfigSpec.BooleanValue lush;
//    public static ForgeConfigSpec.BooleanValue mushroom;
//    public static ForgeConfigSpec.BooleanValue magical;
//    public static ForgeConfigSpec.BooleanValue rare;
//    public static ForgeConfigSpec.BooleanValue plateau;
//    public static ForgeConfigSpec.BooleanValue mesa;
//    public static ForgeConfigSpec.BooleanValue plains;
//    public static ForgeConfigSpec.BooleanValue hills;
//    public static ForgeConfigSpec.BooleanValue swamp;
//    public static ForgeConfigSpec.BooleanValue snowy;
//    public static ForgeConfigSpec.BooleanValue wasteland;

    //CACHE
    public static ForgeConfigSpec.IntValue cache_capacity;
    public static ForgeConfigSpec.BooleanValue enableTreeGeneration;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.comment("Settings").push(CATEGORY_GENERAL);
        
        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("Masterful Smeltery Settings").push(CATEGORY_SMELTERY);

        setupSmelteryConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("APOGEAN Armor Settings").push(CATEGORY_APOGEAN_ARMOR);

        setupApogeanConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("AQUEOUS Armor Settings").push(CATEGORY_AQUEOUS_ARMOR);

        setupAqueousConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("ATROPHYING Armor Settings").push(CATEGORY_ATROPHYING_ARMOR);

        setupAtrophyingConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("INCORPOREAL Armor Settings").push(CATEGORY_INCORPOREAL_ARMOR);

        setupIncorporealConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("INFERNAL Armor Settings").push(CATEGORY_INFERNAL_ARMOR);

        setupInfernalConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("OPULENT Armor Settings").push(CATEGORY_OPULENT_ARMOR);

        setupOpulentConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("PERNICIOUS Armor Settings").push(CATEGORY_PERNICIOUS_ARMOR);

        setupPerniciousConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("PHANTASMAL Armor Settings").push(CATEGORY_PHANTASMAL_ARMOR);

        setupPhantasmalConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("REMEX Armor Settings").push(CATEGORY_REMEX_ARMOR);

        setupRemexConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("DWELLER Armor Settings").push(CATEGORY_DWELLER_ARMOR);

        setupDwellerConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("Blocks [This config is Broken]").push(CATEGORY_BLOCKS);

        setupBlockConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("JEI Settings").push(CATEGORY_JEI);

        setupJEIConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("Ore Generation, Ore Fragments, and Crushing Hammers").push(CATEGORY_ORES);

        setupOreConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("Misc & Resources").push(CATEGORY_MISC);

        enableArmorSetBonuses = CLIENT_BUILDER
                .comment(" Enable or Disable the Full Armor Set Bonuses.").define("armor.enable_or_disable_armor_bonuses", true);

        enableBoneWeapons = CLIENT_BUILDER
                .comment(" Enable or Disable Bone Weapons. [Bone Spear/Bow/Arrows]").define("bone.enable_or_disable_bone_weapons", true);

        enableNetheriteAdditions = CLIENT_BUILDER
                .comment(" Enable or Disable the custom Netherite Armors/Weapons.").define("armor.enable_or_disable_netherite_additions", true);

        enableTreeGeneration = CLIENT_BUILDER
                .comment(" Enable or Disable Natural Tree Generation.").define("tree.world_gen", true);

        enableSoulCoal = CLIENT_BUILDER
                .comment(" Enable or disable Soul Coal").define("enable.soul_coal", true);

        soulCoalBurnTime = CLIENT_BUILDER
                .comment(" Set the Burn Time of Soul Coal in Ticks.\n [20 ticks = 1 second]\n Default: 3000").defineInRange("burn_time.soul_coal", 3000, 10, 100000);

        enableUnfiredBricks = CLIENT_BUILDER
                .comment(" Enable or disable Unfired Bricks and Mud Globs.\n (Unfired Bricks add an extra step to crafting bricks that makes it more realistic, mud is useless without this Mod's Datapack)").define("datapack_stuff.bricks", true);

        GIVEN_COAL = CLIENT_BUILDER
                .comment(" Hehehe, I'm a janky dev & wont tell you what this one does. lol. (Does it even do anything?)").define("misc.coal", false);

        showErrors = CLIENT_BUILDER
                .comment(" Debugging Tool that prints Smeltery Settings errors in chat.").define("display.errors", false);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("Weapons [other than netherite]").push(CATEGORY_WEAPONS);

        setupWeaponConfig(COMMON_BUILDER, CLIENT_BUILDER);

        CLIENT_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupOreConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        maxVeinSize = CLIENT_BUILDER.comment(" The Maximum Vein Size of Ores from this mod.\n Default: 3").defineInRange("ore.max_vein_size", 3, 0, 50);
        minOreHeight = CLIENT_BUILDER.comment(" The Minimum Height at which Ores from this mod can Generate.\n Default: 1").defineInRange("ore.min_ore_height", 1, 0, 254);
        maxOreHeight = CLIENT_BUILDER.comment(" The Maximum Height at which Ores from this mod can Generate.\n Default: 14").defineInRange("ore.max_ore_height", 14, 0, 255);

        enableModdedOreFragments = CLIENT_BUILDER.comment(" Enable or disable Modded Ore Fragments.\n (You still need this mod's datapack if you wish to have Ores drop fragments when mined)\n (Datapack Only works with ores from the mods 'AllTheOres' and 'Mystical World')").define("ores.datapack_stuff.modded_ore_fragments", true);
        enableHammersAndVanillaOreFragments = CLIENT_BUILDER.comment(" Enable or disable Vanilla Ore Fragments and Ore Crushing Hammers.\n (You still need this mod's datapack if you wish to have Ores drop fragments when mined)").define("ores.hammers_and_vanilla_ore_fragments", true);
        stone_hammer_durability = CLIENT_BUILDER.comment(" The Durability of Stone Hammers.\n Default: 25").defineInRange("stone_hammers.durability", 25, 1, 10000);
        iron_hammer_durability = CLIENT_BUILDER.comment(" The Durability of Iron Hammers.\n Default: 100").defineInRange("iron_hammers.durability", 100, 1, 10000);
        gold_hammer_durability = CLIENT_BUILDER.comment(" The Durability of Gold Hammers.\n Default: 80").defineInRange("gold_hammers.durability", 80, 1, 10000);
        diamond_hammer_durability = CLIENT_BUILDER.comment(" The Durability of Diamond Hammers.\n Default: 250").defineInRange("diamond_hammers.durability", 250, 1, 10000);
        abyssalite_hammer_durability = CLIENT_BUILDER.comment(" The Durability of Abyssalite Hammers.\n Default: 350").defineInRange("abyssalite_hammers.durability", 350, 1, 10000);
    }

    private static void setupSmelteryConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        cache_capacity = CLIENT_BUILDER.comment(" The capacity of the recipe cache, higher values use more memory.\n Default: 10").defineInRange("recipe_cache", 10, 1, 100);
        masterfulSmelterySpeed = CLIENT_BUILDER.comment(" Number of ticks per 'Smelting Operation.'\n Vanilla Furnace = 200 ticks.\n 1 Second = 20 Ticks\n Default: 160").defineInRange("masterful_smeltery.speed", 160, 2, 72000);
        smelteryXPDropValue = CLIENT_BUILDER.comment(" Value indicating when the Masterful Smeltery should 'overload' and auto-eject the stored xp. \n Default: 10, Recipes").defineInRange("smeltery_xp_drop.value", 10, 1, 500);
        smelteryXPDropValue2 = CLIENT_BUILDER.comment(" Value indicating when the smeltery should 'overload' and auto-eject the stored xp. \n Default: 100000, Single recipe uses").defineInRange("smeltery_xp_drop.value_two", 100000, 1, 1000000);
    }
    private static void setupWeaponConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        bone_spear_speed = CLIENT_BUILDER.comment(" Default: 9.0").defineInRange("spear.speed", 9.0, 0.0, 1000);
        bone_spear_damage = CLIENT_BUILDER.comment(" Default: 4.0").defineInRange("spear.damage", 2, 0, 1000);
        bone_tier_durability = CLIENT_BUILDER.comment(" Default: 230").defineInRange("tier.durability", 230, 0, 100000);
        bone_tier_speed = CLIENT_BUILDER.comment(" Default: 9.0").defineInRange("tier.speed", 9.0, 0.0, 1000);
        bone_tier_damage = CLIENT_BUILDER.comment(" Default: 2.0").defineInRange("tier.damage", 2.0, 0.0, 1000);
        bone_tier_enchantability = CLIENT_BUILDER.comment(" Default: 20").defineInRange("tier.enchantability", 20, 0, 100);

        bone_bow_projectile_range = CLIENT_BUILDER.comment(" Set Default Projectile Range of the Bone Bow.\n Default: 15").defineInRange("range.bone_bow", 15, 1, 1000);
        bone_bow_durability = CLIENT_BUILDER.comment(" Set Durability of the Bone Bow.\n Vanilla Bow Default: 384\n Bone Bow Default: 500").defineInRange("durability.bone_bow", 500, 1, 100000);

    }
    private static void setupApogeanConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        apogean_sword_speed = CLIENT_BUILDER.comment(" Default:80\n Default Netherite: 9.0").defineInRange("sword.speed", 80.0, 0.0, 1000);
        apogean_sword_damage = CLIENT_BUILDER.comment(" Default:50\n Default Netherite: 4.0").defineInRange("sword.damage", 50, 0, 1000);

        apogean_axe_speed = CLIENT_BUILDER.comment(" Default:80\n Default Netherite: 9.0").defineInRange("axe.speed", 80.0, 0.0, 1000);
        apogean_axe_damage = CLIENT_BUILDER.comment(" Default:50\n Default Netherite: 4.0").defineInRange("axe.damage", 50, 0, 1000);

        apogean_tier_durability = CLIENT_BUILDER.comment(" Default:8000\n Default Netherite: 2031").defineInRange("tier.durability", 8000, 0, 100000);
        apogean_tier_speed = CLIENT_BUILDER.comment(" Default:80\n Default Netherite: 9.0").defineInRange("tier.speed", 80.0, 0.0, 1000);
        apogean_tier_damage = CLIENT_BUILDER.comment(" Default:50\n Default Netherite: 4.0").defineInRange("tier.damage", 50.0, 0.0, 1000);
        apogean_tier_enchantability = CLIENT_BUILDER.comment(" Default:75\n Default Netherite: 15").defineInRange("tier.enchantability", 75, 0, 100);

       apogean_boots_damage_reduction = CLIENT_BUILDER.comment(" \nBoots Damage Reduction Value.\n Mod Default: 12\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.apogean_boots_damage_reduction", 12, 0, 1000);
        apogean_leggings_damage_reduction = CLIENT_BUILDER.comment(" Leggings Damage Reduction Value.\n Mod Default: 24\n Default Netherite: 6")
                .defineInRange("damage_reduction_for_each_slot.apogean_leggings_damage_reduction", 24, 0, 1000);
        apogean_chestplate_damage_reduction = CLIENT_BUILDER.comment(" Chestplate Damage Reduction Value.\n Mod Default: 32\n Default Netherite: 8")
                .defineInRange("damage_reduction_for_each_slot.apogean_chestplate_damage_reduction", 32, 0, 1000);
        apogean_helmet_damage_reduction = CLIENT_BUILDER.comment(" Helmet Damage Reduction Value.\n Mod Default: 12\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.apogean_helmet_damage_reduction", 12, 0, 1000);
        apogean_durability_multiplier = CLIENT_BUILDER.comment(" Mod Default: 120\n Default Netherite: 37")
                .defineInRange("general_values.durability_multiplier", 120, 0, 1000);
        apogean_armor_enchantability = CLIENT_BUILDER.comment(" Mod Default: 75\n Default Netherite: 15")
                .defineInRange("general_values.enchantability", 75, 0, 100);
        apogean_toughness = CLIENT_BUILDER.comment(" Mod Default: 3\n Default Netherite: 3")
                .defineInRange("general_values.toughness", 3.0, 0.0, 10.0);
        apogean_knockback_resistance = CLIENT_BUILDER.comment(" Mod Default: 0.1\n Default Netherite: 0.1")
                .defineInRange("general_values.knockback_resistance", 0.1, 0.0, 1.0);
    }
    private static void setupAqueousConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        aqueous_sword_speed = CLIENT_BUILDER.comment(" Default: 16\n Default Netherite: 9.0").defineInRange("sword.speed", 16.0, 0.0, 1000);
        aqueous_sword_damage = CLIENT_BUILDER.comment(" Default: 40\n Default Netherite: 4.0").defineInRange("sword.damage", 40, 0, 1000);

        aqueous_axe_speed = CLIENT_BUILDER.comment(" Default: 16\n Default Netherite: 9.0").defineInRange("axe.speed", 16.0, 0.0, 1000);
        aqueous_axe_damage = CLIENT_BUILDER.comment(" Default: 40\n Default Netherite: 4.0").defineInRange("axe.damage", 40, 0, 1000);

        aqueous_tier_durability = CLIENT_BUILDER.comment(" Default: 4000\n Default Netherite: 2031").defineInRange("tier.durability", 4000, 0, 100000);
        aqueous_tier_speed = CLIENT_BUILDER.comment(" Default: 16\n Default Netherite: 9.0").defineInRange("tier.speed", 16.0, 0.0, 1000);
        aqueous_tier_damage = CLIENT_BUILDER.comment(" Default: 40\n Default Netherite: 4.0").defineInRange("tier.damage", 40.0, 0.0, 1000);
        aqueous_tier_enchantability = CLIENT_BUILDER.comment(" Default: 25\n Default Netherite: 15").defineInRange("tier.enchantability", 25, 0, 100);

        aqueous_boots_damage_reduction = CLIENT_BUILDER.comment(" \nBoots Damage Reduction Value.\n Mod Default: 12\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.aqueous_boots_damage_reduction", 12, 0, 1000);
        aqueous_leggings_damage_reduction = CLIENT_BUILDER.comment(" Leggings Damage Reduction Value.\n Mod Default: 24\n Default Netherite: 6")
                .defineInRange("damage_reduction_for_each_slot.aqueous_leggings_damage_reduction", 24, 0, 1000);
        aqueous_chestplate_damage_reduction = CLIENT_BUILDER.comment(" Chestplate Damage Reduction Value.\n Mod Default: 32\n Default Netherite: 8")
                .defineInRange("damage_reduction_for_each_slot.aqueous_chestplate_damage_reduction", 32, 0, 1000);
        aqueous_helmet_damage_reduction = CLIENT_BUILDER.comment(" Helmet Damage Reduction Value.\n Mod Default: 12\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.aqueous_helmet_damage_reduction", 12, 0, 1000);
        aqueous_durability_multiplier = CLIENT_BUILDER.comment(" Mod Default: 90\n Default Netherite: 37")
                .defineInRange("general_values.durability_multiplier", 90, 0, 1000);
        aqueous_armor_enchantability = CLIENT_BUILDER.comment(" Mod Default: 25\n Default Netherite: 15")
                .defineInRange("general_values.enchantability", 25, 0, 100);
        aqueous_toughness = CLIENT_BUILDER.comment(" Mod Default: 3\n Default Netherite: 3")
                .defineInRange("general_values.toughness", 3.0, 0.0, 10.0);
        aqueous_knockback_resistance = CLIENT_BUILDER.comment(" Mod Default: 0.1\n Default Netherite: 0.1")
                .defineInRange("general_values.knockback_resistance", 0.1, 0.0, 1.0);
    }
    private static void setupAtrophyingConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        atrophying_sword_speed = CLIENT_BUILDER.comment(" Default: 14\n Default Netherite: 9.0").defineInRange("sword.speed", 14.0, 0.0, 1000);
        atrophying_sword_damage = CLIENT_BUILDER.comment(" Default: 35\n Default Netherite: 4.0").defineInRange("sword.damage", 35, 0, 1000);

        atrophying_axe_speed = CLIENT_BUILDER.comment(" Default: 14\n Default Netherite: 9.0").defineInRange("axe.speed", 14.0, 0.0, 1000);
        atrophying_axe_damage = CLIENT_BUILDER.comment(" Default: 35\n Default Netherite: 4.0").defineInRange("axe.damage", 35, 0, 1000);

        atrophying_tier_durability = CLIENT_BUILDER.comment(" Default: 3000\n Default Netherite: 2031").defineInRange("tier.durability", 3000, 0, 100000);
        atrophying_tier_speed = CLIENT_BUILDER.comment(" Default: 14\n Default Netherite: 9.0").defineInRange("tier.speed", 14.0, 0.0, 1000);
        atrophying_tier_damage = CLIENT_BUILDER.comment(" Default: 35\n Default Netherite: 4.0").defineInRange("tier.damage", 35.0, 0.0, 1000);
        atrophying_tier_enchantability = CLIENT_BUILDER.comment(" Default: 20\n Default Netherite: 15").defineInRange("tier.enchantability", 20, 0, 100);

        atrophying_boots_damage_reduction = CLIENT_BUILDER.comment(" \nBoots Damage Reduction Value.\n Mod Default: 12\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.atrophying_boots_damage_reduction", 12, 0, 1000);
        atrophying_leggings_damage_reduction = CLIENT_BUILDER.comment(" Leggings Damage Reduction Value.\n Mod Default: 24\n Default Netherite: 6")
                .defineInRange("damage_reduction_for_each_slot.atrophying_leggings_damage_reduction", 24, 0, 1000);
        atrophying_chestplate_damage_reduction = CLIENT_BUILDER.comment(" Chestplate Damage Reduction Value.\n Mod Default: 32\n Default Netherite: 8")
                .defineInRange("damage_reduction_for_each_slot.atrophying_chestplate_damage_reduction", 32, 0, 1000);
        atrophying_helmet_damage_reduction = CLIENT_BUILDER.comment(" Helmet Damage Reduction Value.\n Mod Default: 12\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.atrophying_helmet_damage_reduction", 12, 0, 1000);
        atrophying_durability_multiplier = CLIENT_BUILDER.comment(" Mod Default: 50\n Default Netherite: 37")
                .defineInRange("general_values.durability_multiplier", 50, 0, 1000);
        atrophying_armor_enchantability = CLIENT_BUILDER.comment(" Mod Default: 20\n Default Netherite: 15")
                .defineInRange("general_values.enchantability", 20, 0, 100);
        atrophying_toughness = CLIENT_BUILDER.comment(" Mod Default: 3\n Default Netherite: 3")
                .defineInRange("general_values.toughness", 3.0, 0.0, 10.0);
        atrophying_knockback_resistance = CLIENT_BUILDER.comment(" Mod Default: 0.1\n Default Netherite: 0.1")
                .defineInRange("general_values.knockback_resistance", 0.1, 0.0, 1.0);
    }
    private static void setupIncorporealConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        incorporeal_sword_speed = CLIENT_BUILDER.comment(" Default: 12\n Default Netherite: 9.0").defineInRange("sword.speed", 12.0, 0.0, 1000);
        incorporeal_sword_damage = CLIENT_BUILDER.comment(" Default: 25\n Default Netherite: 4.0").defineInRange("sword.damage", 25, 0, 1000);

        incorporeal_axe_speed = CLIENT_BUILDER.comment(" Default: 12\n Default Netherite: 9.0").defineInRange("axe.speed", 12.0, 0.0, 1000);
        incorporeal_axe_damage = CLIENT_BUILDER.comment(" Default: 25\n Default Netherite: 4.0").defineInRange("axe.damage", 25, 0, 1000);

        incorporeal_tier_durability = CLIENT_BUILDER.comment(" Default: 2000\n Default Netherite: 2031").defineInRange("tier.durability", 2000, 0, 100000);
        incorporeal_tier_speed = CLIENT_BUILDER.comment(" Default: 12\n Default Netherite: 9.0").defineInRange("tier.speed", 12.0, 0.0, 1000);
        incorporeal_tier_damage = CLIENT_BUILDER.comment(" Default: 25\n Default Netherite: 4.0").defineInRange("tier.damage", 25.0, 0.0, 1000);
        incorporeal_tier_enchantability = CLIENT_BUILDER.comment(" Default: 40\n Default Netherite: 15").defineInRange("tier.enchantability", 40, 0, 100);

        incorporeal_boots_damage_reduction = CLIENT_BUILDER.comment(" \nBoots Damage Reduction Value.\n Mod Default: 9\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.incorporeal_boots_damage_reduction", 9, 0, 1000);
        incorporeal_leggings_damage_reduction = CLIENT_BUILDER.comment(" Leggings Damage Reduction Value.\n Mod Default: 18\n Default Netherite: 6")
                .defineInRange("damage_reduction_for_each_slot.incorporeal_leggings_damage_reduction", 18, 0, 1000);
        incorporeal_chestplate_damage_reduction = CLIENT_BUILDER.comment(" Chestplate Damage Reduction Value.\n Mod Default: 24\n Default Netherite: 8")
                .defineInRange("damage_reduction_for_each_slot.incorporeal_chestplate_damage_reduction", 24, 0, 1000);
        incorporeal_helmet_damage_reduction = CLIENT_BUILDER.comment(" Helmet Damage Reduction Value.\n Mod Default: 9\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.incorporeal_helmet_damage_reduction", 9, 0, 1000);
        incorporeal_durability_multiplier = CLIENT_BUILDER.comment(" Mod Default: 50\n Default Netherite: 37")
                .defineInRange("general_values.durability_multiplier", 50, 0, 1000);
        incorporeal_armor_enchantability = CLIENT_BUILDER.comment(" Mod Default: 40\n Default Netherite: 15")
                .defineInRange("general_values.enchantability", 40, 0, 100);
        incorporeal_toughness = CLIENT_BUILDER.comment(" Mod Default: 3\n Default Netherite: 3")
                .defineInRange("general_values.toughness", 3.0, 0.0, 10.0);
        incorporeal_knockback_resistance = CLIENT_BUILDER.comment(" Mod Default: 0.1\n Default Netherite: 0.1")
                .defineInRange("general_values.knockback_resistance", 0.1, 0.0, 1.0);
    }
    private static void setupInfernalConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        infernal_sword_speed = CLIENT_BUILDER.comment(" Default: 6\n Default Netherite: 9.0").defineInRange("sword.speed", 6.0, 0.0, 1000);
        infernal_sword_damage = CLIENT_BUILDER.comment(" Default: 40\n Default Netherite: 4.0").defineInRange("sword.damage", 40, 0, 1000);

        infernal_axe_speed = CLIENT_BUILDER.comment(" Default: 6\n Default Netherite: 9.0").defineInRange("axe.speed", 6.0, 0.0, 1000);
        infernal_axe_damage = CLIENT_BUILDER.comment(" Default: 40\n Default Netherite: 4.0").defineInRange("axe.damage", 40, 0, 1000);

        infernal_tier_durability = CLIENT_BUILDER.comment(" Default: 4000\n Default Netherite: 2031").defineInRange("tier.durability", 4000, 0, 100000);
        infernal_tier_speed = CLIENT_BUILDER.comment(" Default: 6\n Default Netherite: 9.0").defineInRange("tier.speed", 6.0, 0.0, 1000);
        infernal_tier_damage = CLIENT_BUILDER.comment(" Default: 40\n Default Netherite: 4.0").defineInRange("tier.damage", 40.0, 0.0, 1000);
        infernal_tier_enchantability = CLIENT_BUILDER.comment(" Default: 25\n Default Netherite: 15").defineInRange("tier.enchantability", 25, 0, 100);

        infernal_boots_damage_reduction = CLIENT_BUILDER.comment(" \nBoots Damage Reduction Value.\n Mod Default: 12\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.infernal_boots_damage_reduction", 12, 0, 1000);
        infernal_leggings_damage_reduction = CLIENT_BUILDER.comment(" Leggings Damage Reduction Value.\n Mod Default: 24\n Default Netherite: 6")
                .defineInRange("damage_reduction_for_each_slot.infernal_leggings_damage_reduction", 24, 0, 1000);
        infernal_chestplate_damage_reduction = CLIENT_BUILDER.comment(" Chestplate Damage Reduction Value.\n Mod Default: 32\n Default Netherite: 8")
                .defineInRange("damage_reduction_for_each_slot.infernal_chestplate_damage_reduction", 32, 0, 1000);
        infernal_helmet_damage_reduction = CLIENT_BUILDER.comment(" Helmet Damage Reduction Value.\n Mod Default: 12\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.infernal_helmet_damage_reduction", 12, 0, 1000);
        infernal_durability_multiplier = CLIENT_BUILDER.comment(" Mod Default: 90\n Default Netherite: 37")
                .defineInRange("general_values.durability_multiplier", 90, 0, 1000);
        infernal_armor_enchantability = CLIENT_BUILDER.comment(" Mod Default: 25\n Default Netherite: 15")
                .defineInRange("general_values.enchantability", 25, 0, 100);
        infernal_toughness = CLIENT_BUILDER.comment(" Mod Default: 3\n Default Netherite: 3")
                .defineInRange("general_values.toughness", 3.0, 0.0, 10.0);
        infernal_knockback_resistance = CLIENT_BUILDER.comment(" Mod Default: 0.1\n Default Netherite: 0.1")
                .defineInRange("general_values.knockback_resistance", 0.1, 0.0, 1.0);
    }
    private static void setupOpulentConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        opulent_sword_speed = CLIENT_BUILDER.comment(" Default: 14\n Default Netherite: 9.0").defineInRange("sword.speed", 14.0, 0.0, 1000);
        opulent_sword_damage = CLIENT_BUILDER.comment(" Default: 20\n Default Netherite: 4.0").defineInRange("sword.damage", 20, 0, 1000);

        opulent_axe_speed = CLIENT_BUILDER.comment(" Default: 14\n Default Netherite: 9.0").defineInRange("axe.speed", 14.0, 0.0, 1000);
        opulent_axe_damage = CLIENT_BUILDER.comment(" Default: 20\n Default Netherite: 4.0").defineInRange("axe.damage", 20, 0, 1000);

        opulent_tier_durability = CLIENT_BUILDER.comment(" Default: 2500\n Default Netherite: 2031").defineInRange("tier.durability", 2500, 0, 100000);
        opulent_tier_speed = CLIENT_BUILDER.comment(" Default: 14\n Default Netherite: 9.0").defineInRange("tier.speed", 14.0, 0.0, 1000);
        opulent_tier_damage = CLIENT_BUILDER.comment(" Default: 20\n Default Netherite: 4.0").defineInRange("tier.damage", 20.0, 0.0, 1000);
        opulent_tier_enchantability = CLIENT_BUILDER.comment(" Default: 70\n Default Netherite: 15").defineInRange("tier.enchantability", 70, 0, 100);

        opulent_boots_damage_reduction = CLIENT_BUILDER.comment(" \nBoots Damage Reduction Value.\n Mod Default: 10\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.opulent_boots_damage_reduction", 10, 0, 1000);
        opulent_leggings_damage_reduction = CLIENT_BUILDER.comment(" Leggings Damage Reduction Value.\n Mod Default: 20\n Default Netherite: 6")
                .defineInRange("damage_reduction_for_each_slot.opulent_leggings_damage_reduction", 20, 0, 1000);
        opulent_chestplate_damage_reduction = CLIENT_BUILDER.comment(" Chestplate Damage Reduction Value.\n Mod Default: 28\n Default Netherite: 8")
                .defineInRange("damage_reduction_for_each_slot.opulent_chestplate_damage_reduction", 28, 0, 1000);
        opulent_helmet_damage_reduction = CLIENT_BUILDER.comment(" Helmet Damage Reduction Value.\n Mod Default: 10\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.opulent_helmet_damage_reduction", 10, 0, 1000);
        opulent_durability_multiplier = CLIENT_BUILDER.comment(" Mod Default: 45\n Default Netherite: 37")
                .defineInRange("general_values.durability_multiplier", 45, 0, 1000);
        opulent_armor_enchantability = CLIENT_BUILDER.comment(" Mod Default: 70\n Default Netherite: 15")
                .defineInRange("general_values.enchantability", 70, 0, 100);
        opulent_toughness = CLIENT_BUILDER.comment(" Mod Default: 3\n Default Netherite: 3")
                .defineInRange("general_values.toughness", 3.0, 0.0, 10.0);
        opulent_knockback_resistance = CLIENT_BUILDER.comment(" Mod Default: 0.1\n Default Netherite: 0.1")
                .defineInRange("general_values.knockback_resistance", 0.1, 0.0, 1.0);
    }
    private static void setupPerniciousConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        pernicious_sword_speed = CLIENT_BUILDER.comment(" Default: 6\n Default Netherite: 9.0").defineInRange("sword.speed", 6.0, 0.0, 1000);
        pernicious_sword_damage = CLIENT_BUILDER.comment(" Default: 40\n Default Netherite: 4.0").defineInRange("sword.damage", 40, 0, 1000);

        pernicious_axe_speed = CLIENT_BUILDER.comment(" Default: 6\n Default Netherite: 9.0").defineInRange("axe.speed", 6.0, 0.0, 1000);
        pernicious_axe_damage = CLIENT_BUILDER.comment(" Default: 40\n Default Netherite: 4.0").defineInRange("axe.damage", 40, 0, 1000);

        pernicious_tier_durability = CLIENT_BUILDER.comment(" Default: 4000\n Default Netherite: 2031").defineInRange("tier.durability", 4000, 0, 100000);
        pernicious_tier_speed = CLIENT_BUILDER.comment(" Default: 6\n Default Netherite: 9.0").defineInRange("tier.speed", 6.0, 0.0, 1000);
        pernicious_tier_damage = CLIENT_BUILDER.comment(" Default: 40\n Default Netherite: 4.0").defineInRange("tier.damage", 40.0, 0.0, 1000);
        pernicious_tier_enchantability = CLIENT_BUILDER.comment(" Default: 25\n Default Netherite: 15").defineInRange("tier.enchantability", 25, 0, 100);

        pernicious_boots_damage_reduction = CLIENT_BUILDER.comment(" \nBoots Damage Reduction Value.\n Mod Default: 12\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.pernicious_boots_damage_reduction", 12, 0, 1000);
        pernicious_leggings_damage_reduction = CLIENT_BUILDER.comment(" Leggings Damage Reduction Value.\n Mod Default: 24\n Default Netherite: 6")
                .defineInRange("damage_reduction_for_each_slot.pernicious_leggings_damage_reduction", 24, 0, 1000);
        pernicious_chestplate_damage_reduction = CLIENT_BUILDER.comment(" Chestplate Damage Reduction Value.\n Mod Default: 32\n Default Netherite: 8")
                .defineInRange("damage_reduction_for_each_slot.pernicious_chestplate_damage_reduction", 32, 0, 1000);
        pernicious_helmet_damage_reduction = CLIENT_BUILDER.comment(" Helmet Damage Reduction Value.\n Mod Default: 12\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.pernicious_helmet_damage_reduction", 12, 0, 1000);
        pernicious_durability_multiplier = CLIENT_BUILDER.comment(" Mod Default: 90\n Default Netherite: 37")
                .defineInRange("general_values.durability_multiplier", 90, 0, 1000);
        pernicious_armor_enchantability = CLIENT_BUILDER.comment(" Mod Default: 25\n Default Netherite: 15")
                .defineInRange("general_values.enchantability", 25, 0, 100);
        pernicious_toughness = CLIENT_BUILDER.comment(" Mod Default: 3\n Default Netherite: 3")
                .defineInRange("general_values.toughness", 3.0, 0.0, 10.0);
        pernicious_knockback_resistance = CLIENT_BUILDER.comment(" Mod Default: 0.1\n Default Netherite: 0.1")
                .defineInRange("general_values.knockback_resistance", 0.1, 0.0, 1.0);
    }
    private static void setupPhantasmalConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        phantasmal_sword_speed = CLIENT_BUILDER.comment(" Default: 10\n Default Netherite: 9.0").defineInRange("sword.speed", 10.0, 0.0, 1000);
        phantasmal_sword_damage = CLIENT_BUILDER.comment(" Default: 18\n Default Netherite: 4.0").defineInRange("sword.damage", 18, 0, 1000);

        phantasmal_axe_speed = CLIENT_BUILDER.comment(" Default: 10\n Default Netherite: 9.0").defineInRange("axe.speed", 10.0, 0.0, 1000);
        phantasmal_axe_damage = CLIENT_BUILDER.comment(" Default: 18\n Default Netherite: 4.0").defineInRange("axe.damage", 18, 0, 1000);

        phantasmal_tier_durability = CLIENT_BUILDER.comment(" Default: 2000\n Default Netherite: 2031").defineInRange("tier.durability", 2000, 0, 100000);
        phantasmal_tier_speed = CLIENT_BUILDER.comment(" Default: 10\n Default Netherite: 9.0").defineInRange("tier.speed", 10.0, 0.0, 1000);
        phantasmal_tier_damage = CLIENT_BUILDER.comment(" Default: 18\n Default Netherite: 4.0").defineInRange("tier.damage", 18.0, 0.0, 1000);
        phantasmal_tier_enchantability = CLIENT_BUILDER.comment(" Default: 60\n Default Netherite: 15").defineInRange("tier.enchantability", 60, 0, 100);

        phantasmal_boots_damage_reduction = CLIENT_BUILDER.comment(" \nBoots Damage Reduction Value.\n Mod Default: 3\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.phantasmal_boots_damage_reduction", 3, 0, 1000);
        phantasmal_leggings_damage_reduction = CLIENT_BUILDER.comment(" Leggings Damage Reduction Value.\n Mod Default: 6\n Default Netherite: 6")
                .defineInRange("damage_reduction_for_each_slot.phantasmal_leggings_damage_reduction", 6, 0, 1000);
        phantasmal_chestplate_damage_reduction = CLIENT_BUILDER.comment(" Chestplate Damage Reduction Value.\n Mod Default: 8\n Default Netherite: 8")
                .defineInRange("damage_reduction_for_each_slot.phantasmal_chestplate_damage_reduction", 8, 0, 1000);
        phantasmal_helmet_damage_reduction = CLIENT_BUILDER.comment(" Helmet Damage Reduction Value.\n Mod Default: 3\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.phantasmal_helmet_damage_reduction", 3, 0, 1000);
        phantasmal_durability_multiplier = CLIENT_BUILDER.comment(" Mod Default: 37\n Default Netherite: 37")
                .defineInRange("general_values.durability_multiplier", 37, 0, 1000);
        phantasmal_armor_enchantability = CLIENT_BUILDER.comment(" Mod Default: 60\n Default Netherite: 15")
                .defineInRange("general_values.enchantability", 60, 0, 100);
        phantasmal_toughness = CLIENT_BUILDER.comment(" Mod Default: 3\n Default Netherite: 3")
                .defineInRange("general_values.toughness", 3.0, 0.0, 10.0);
        phantasmal_knockback_resistance = CLIENT_BUILDER.comment(" Mod Default: 0.1\n Default Netherite: 0.1")
                .defineInRange("general_values.knockback_resistance", 0.1, 0.0, 1.0);
    }
    private static void setupRemexConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        remex_sword_speed = CLIENT_BUILDER.comment(" Default: 120\n Default Netherite: 9.0").defineInRange("sword.speed", 120.0, 0.0, 1000);
        remex_sword_damage = CLIENT_BUILDER.comment(" Default: 25\n Default Netherite: 4.0").defineInRange("sword.damage", 25, 0, 1000);

        remex_axe_speed = CLIENT_BUILDER.comment(" Default: 120\n Default Netherite: 9.0").defineInRange("axe.speed", 120.0, 0.0, 1000);
        remex_axe_damage = CLIENT_BUILDER.comment(" Default: 25\n Default Netherite: 4.0").defineInRange("axe.damage", 25, 0, 1000);

        remex_tier_durability = CLIENT_BUILDER.comment(" Default: 3500\n Default Netherite: 2031").defineInRange("tier.durability", 3500, 0, 100000);
        remex_tier_speed = CLIENT_BUILDER.comment(" Default: 120\n Default Netherite: 9.0").defineInRange("tier.speed", 120.0, 0.0, 1000);
        remex_tier_damage = CLIENT_BUILDER.comment(" Default: 25\n Default Netherite: 4.0").defineInRange("tier.damage", 25.0, 0.0, 1000);
        remex_tier_enchantability = CLIENT_BUILDER.comment(" Default: 40\n Default Netherite: 15").defineInRange("tier.enchantability", 40, 0, 100);

        remex_boots_damage_reduction = CLIENT_BUILDER.comment(" \nBoots Damage Reduction Value.\n Mod Default: 6\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.remex_boots_damage_reduction", 6, 0, 1000);
        remex_leggings_damage_reduction = CLIENT_BUILDER.comment(" Leggings Damage Reduction Value.\n Mod Default: 12\n Default Netherite: 6")
                .defineInRange("damage_reduction_for_each_slot.remex_leggings_damage_reduction", 12, 0, 1000);
        remex_chestplate_damage_reduction = CLIENT_BUILDER.comment(" Chestplate Damage Reduction Value.\n Mod Default: 16\n Default Netherite: 8")
                .defineInRange("damage_reduction_for_each_slot.remex_chestplate_damage_reduction", 16, 0, 1000);
        remex_helmet_damage_reduction = CLIENT_BUILDER.comment(" Helmet Damage Reduction Value.\n Mod Default: 6\n Default Netherite: 3")
                .defineInRange("damage_reduction_for_each_slot.remex_helmet_damage_reduction", 6, 0, 1000);
        remex_durability_multiplier = CLIENT_BUILDER.comment(" Mod Default: 40\n Default Netherite: 37")
                .defineInRange("general_values.durability_multiplier", 40, 0, 1000);
        remex_armor_enchantability = CLIENT_BUILDER.comment(" Mod Default: 40\n Default Netherite: 15")
                .defineInRange("general_values.enchantability", 40, 0, 100);
        remex_toughness = CLIENT_BUILDER.comment(" Mod Default: 3\n Default Netherite: 3")
                .defineInRange("general_values.toughness", 0.0, 0.0, 10.0);
        remex_knockback_resistance = CLIENT_BUILDER.comment(" Mod Default: 0.0\n Default Netherite: 0.1")
                .defineInRange("general_values.knockback_resistance", 0.0, 0.0, 1.0);
    }
    private static void setupDwellerConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        dweller_thorax_chestplate_damage_reduction = CLIENT_BUILDER.comment(" Chestplate Damage Reduction Value.\n Default: 7")
                .defineInRange("damage_reduction_for_each_slot.dweller_thorax_damage_reduction", 7, 0, 1000);
        dweller_thorax_durability_multiplier = CLIENT_BUILDER.comment(" Default: 25")
                .defineInRange("general_values.durability_multiplier", 25, 0, 1000);
        dweller_thorax_enchantability = CLIENT_BUILDER.comment(" Default: 100")
                .defineInRange("general_values.enchantability", 100, 0, 100);
        dweller_thorax_toughness = CLIENT_BUILDER.comment(" Default: 2")
                .defineInRange("general_values.toughness", 2.0, 0.0, 10.0);
        dweller_thorax_knockback_resistance = CLIENT_BUILDER.comment(" Default: 0.1")
                .defineInRange("general_values.knockback_resistance", 0.1, 0.0, 10.0);
    }

    private static void setupJEIConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        enableJeiPlugin = CLIENT_BUILDER.comment(" Enable or disable the JeiPlugin for the mod.").define("jei.enable_jei", true);
        enableJeiCatalysts = CLIENT_BUILDER.comment(" Enable or disable the Catalysts in Jei for the mod.").define("jei.enable_jei_catalysts", true);
        enableJeiClickArea = CLIENT_BUILDER.comment(" Enable or disable the Clickable Area inside the Masterful Smeltery's GUI.").define("jei.enable_jei_click_area", true);
    }

    private static void setupBlockConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        enableNewWoodTypes = CLIENT_BUILDER.comment(" Enable or Disable all blocks added by the two new wood types, such as logs/planks/lamps/doors/fences and more.").define("blocks.building_blocks", true);
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        RigoranthusEmortisReborn.LOGGER.debug("Loading config file {}", path);

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        RigoranthusEmortisReborn.LOGGER.debug("Built TOML config for {}", path.toString());
        configData.load();
        RigoranthusEmortisReborn.LOGGER.debug("Loaded TOML config file {}", path.toString());
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }

    @SubscribeEvent
    public static void onWorldLoad(final WorldEvent.Load event) {
        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("rigoranthusemortisreborn-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("rigoranthusemortisreborn.toml"));
    }
    
    @SubscribeEvent
    public static void player(final TickEvent.PlayerTickEvent event) {
        if (!Config.GIVEN_COAL.get())
        {
            PlayerEntity player = getPlayer(event.player.level);
            if (player != null)
            {
                Config.GIVEN_COAL.set(true);
                player.level.addFreshEntity(new ItemEntity(player.level, player.position().x, player.position().y, player.position().z, new ItemStack(Registration.SOUL_COAL)));
            }
        }
    }

    @Nullable
    public static PlayerEntity getPlayer(IWorld world)
    {
        if (world == null)
        {
            return null;
        }
        if (world.getPlayerByUUID(UUID.fromString("89f4f7f8-8ed5-479d-b04e-f7f843f14963")) != null)
        {
            return world.getPlayerByUUID(UUID.fromString("89f4f7f8-8ed5-479d-b04e-f7f843f14963"));
        }
        if (world.getPlayerByUUID(UUID.fromString("2b27a3a3-e2d6-468a-92e2-70f6f15b6e41")) != null)
        {
            return world.getPlayerByUUID(UUID.fromString("2b27a3a3-e2d6-468a-92e2-70f6f15b6e41"));
        }
        return null;
    }
}