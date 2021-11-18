package com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion;

import net.minecraft.item.ItemStack;

public interface IDominionEquipment {

    @Deprecated // To be removed for itemstack sensitive version
    default int getMaxDominionBoost(){
        return 0;
    }

    default int getMaxDominionBoost(ItemStack i){
        return getMaxDominionBoost();
    }

    @Deprecated
    default int getDominionRegenBonus(){
        return 0;
    }

    default int getDominionRegenBonus(ItemStack i){
        return getDominionRegenBonus();
    }

    @Deprecated
    default int getDominionDiscount(){
        return 0;
    }

    default int getDominionDiscount(ItemStack i){
        return getDominionDiscount();
    }

}