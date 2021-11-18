package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item.curios;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.item.RigoranthusEmortisRebornCurio;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.IDominionEquipment;
import net.minecraft.entity.LivingEntity;

public abstract class AbstractDominionCurio extends RigoranthusEmortisRebornCurio implements IDominionEquipment {
    public AbstractDominionCurio(String reg){
        super(reg);
    }

    @Override
    public void wearableTick(LivingEntity wearer) { }
}