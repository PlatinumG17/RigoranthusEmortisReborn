package com.platinumg17.rigoranthusemortisreborn.magica.common.enchantment;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class DominionRegenEnchantment extends Enchantment {

    protected DominionRegenEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentType.ARMOR, new EquipmentSlotType[]{EquipmentSlotType.CHEST, EquipmentSlotType.FEET, EquipmentSlotType.HEAD, EquipmentSlotType.LEGS});
        setRegistryName(EmortisConstants.MOD_ID, "dominion_regen");
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 1+11*(enchantmentLevel-1);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
