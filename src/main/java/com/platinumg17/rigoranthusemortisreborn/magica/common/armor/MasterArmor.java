package com.platinumg17.rigoranthusemortisreborn.magica.common.armor;

import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import com.platinumg17.rigoranthusemortisreborn.items.armor.RigoranthusArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;

public class MasterArmor  extends MagicArmor{
    public MasterArmor(EquipmentSlotType slot) {
        super(RigoranthusArmorMaterial.master, slot, MagicItemsRegistry.defaultItemProperties());
    }

    @Override
    public int getMaxDominionBoost() {
        return 80;
    }

    @Override
    public int getDominionRegenBonus() {
        return 6;
    }
}