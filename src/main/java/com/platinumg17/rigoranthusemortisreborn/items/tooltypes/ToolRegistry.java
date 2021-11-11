package com.platinumg17.rigoranthusemortisreborn.items.tooltypes;

import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraftforge.common.ToolType;

public class ToolRegistry {

    ///_______________________  B A S E   T O O L S  _______________________///
    public static final REToolTypes SICKLE_TOOL = new REToolTypes(ToolType.get("sickle"), Material.WEB, Material.LEAVES, Material.PLANT, Material.REPLACEABLE_PLANT).addEnchantments(EnchantmentType.WEAPON);
    public static final REToolTypes CLAWS_TOOL = new REToolTypes(ToolType.get("claw"), Material.REPLACEABLE_PLANT, Material.PLANT, Material.WEB).addEnchantments(EnchantmentType.WEAPON);
    public static final REToolTypes PICKAXE_TOOL = new REToolTypes(ToolType.PICKAXE, Material.METAL, Material.HEAVY_METAL, Material.STONE).addEnchantments(EnchantmentType.DIGGER);
    public static final REToolTypes HAMMER_TOOL = new REToolTypes(PICKAXE_TOOL).addEnchantments(EnchantmentType.WEAPON).addToolType(ToolType.get("hammer"));
    public static final REToolTypes AXE_TOOL = new REToolTypes(ToolType.AXE, Material.WOOD, Material.PLANT, Material.REPLACEABLE_PLANT).addEnchantments(EnchantmentType.DIGGER, EnchantmentType.WEAPON);
    public static final REToolTypes SHOVEL_TOOL = new REToolTypes(ToolType.SHOVEL, Material.TOP_SNOW, Material.SNOW, Material.CLAY, Material.GRASS, Material.DIRT, Material.SAND).addEnchantments(EnchantmentType.DIGGER);
    public static final REToolTypes SWORD_TOOL = new REToolTypes(ToolType.get("sword"), Material.WEB).addEnchantments(EnchantmentType.WEAPON);
    public static final REToolTypes MISC_TOOL = new REToolTypes().addEnchantments(EnchantmentType.WEAPON);

    ///_______________________  M U L T I   T O O L S  _______________________///
    public static final REToolTypes AXE_PICK_TOOL = new REToolTypes(PICKAXE_TOOL, AXE_TOOL);
    public static final REToolTypes AXE_HAMMER_TOOL = new REToolTypes(AXE_TOOL, HAMMER_TOOL);
    public static final REToolTypes SHOVEL_PICK_TOOL = new REToolTypes(PICKAXE_TOOL, SHOVEL_TOOL);
    public static final REToolTypes SHOVEL_AXE_TOOL = new REToolTypes(AXE_TOOL, SHOVEL_TOOL);
    public static final REToolTypes MULTI_TOOL = new REToolTypes(PICKAXE_TOOL, AXE_TOOL, SHOVEL_TOOL);

    ///_______________________  U N U S E D  _______________________///
    public static final REToolTypes GAUNTLET_TOOL = new REToolTypes(ToolType.get("gauntlet"), Material.GLASS, Material.ICE, Material.ICE_SOLID).addEnchantments(EnchantmentType.WEAPON).addEnchantments(Enchantments.SILK_TOUCH).addToolType(ToolType.get("fist"));
}
