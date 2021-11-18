package com.platinumg17.rigoranthusemortisreborn.magica.common.armor;

import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import com.platinumg17.rigoranthusemortisreborn.items.armor.RigoranthusArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;

public class ApprenticeArmor extends MagicArmor {
    public ApprenticeArmor(EquipmentSlotType slot) {
        super(RigoranthusArmorMaterial.apprentice, slot, MagicItemsRegistry.defaultItemProperties());
    }

    @Override
    public int getMaxDominionBoost() {
        return 40;
    }

    @Override
    public int getDominionRegenBonus() {
        return 4;
    }
}