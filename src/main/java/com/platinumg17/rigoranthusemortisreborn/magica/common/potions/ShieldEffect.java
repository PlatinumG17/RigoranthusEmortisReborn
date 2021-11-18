package com.platinumg17.rigoranthusemortisreborn.magica.common.potions;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class ShieldEffect extends Effect {
    public ShieldEffect() {
        super(EffectType.BENEFICIAL, 2039587);
        setRegistryName(EmortisConstants.MOD_ID, "shield");
    }
}
