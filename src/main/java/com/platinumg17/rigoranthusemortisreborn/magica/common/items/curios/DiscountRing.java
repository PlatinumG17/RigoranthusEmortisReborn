package com.platinumg17.rigoranthusemortisreborn.magica.common.items.curios;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.item.RigoranthusEmortisRebornCurio;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.IDominionEquipment;
import net.minecraft.entity.LivingEntity;

public abstract class DiscountRing extends RigoranthusEmortisRebornCurio implements IDominionEquipment {

    public DiscountRing(String registry) {
        super(registry);
    }

    @Override
    public void wearableTick(LivingEntity wearer) { }

    public abstract int getDominionDiscount();

    @Override
    public int getMaxDominionBoost() {
        return 10;
    }

    @Override
    public int getDominionRegenBonus() {
        return 1;
    }
}