package com.platinumg17.rigoranthusemortisreborn.magica.common.armor;

import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import com.platinumg17.rigoranthusemortisreborn.items.armor.RigoranthusArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;

public class NoviceArmor extends MagicArmor{

    public NoviceArmor(EquipmentSlotType slot) {
        super(RigoranthusArmorMaterial.novice, slot, MagicItemsRegistry.defaultItemProperties());
    }

    @Override
    public int getMaxDominionBoost() {
        return 25;
    }

    @Override
    public int getDominionRegenBonus() {
        return 2;
    }
}