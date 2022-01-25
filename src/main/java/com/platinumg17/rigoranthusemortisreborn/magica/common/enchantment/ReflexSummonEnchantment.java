package com.platinumg17.rigoranthusemortisreborn.magica.common.enchantment;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class ReflexSummonEnchantment extends Enchantment {
    protected ReflexSummonEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentType.ARMOR, new EquipmentSlotType[]{EquipmentSlotType.CHEST, EquipmentSlotType.FEET, EquipmentSlotType.HEAD, EquipmentSlotType.LEGS});
        setRegistryName(EmortisConstants.MOD_ID, "reflex_summon");
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 1+11*(enchantmentLevel-1);
    }

//    @Override
//    public int getMaxCost(int enchantmentLevel) {
//        return 10;
//    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

//    @Override
//    public boolean canEnchant(ItemStack stack) {
//        return true;
//    }
//
//    @Override
//    public boolean canApplyAtEnchantingTable(ItemStack stack) {
//        return true;
//    }
//
//    @Override
//    public boolean isAllowedOnBooks() {
//        return false;
//    }
}
