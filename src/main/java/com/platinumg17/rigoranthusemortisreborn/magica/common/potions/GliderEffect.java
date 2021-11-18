package com.platinumg17.rigoranthusemortisreborn.magica.common.potions;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class GliderEffect extends Effect {
    protected GliderEffect() {
        super(EffectType.BENEFICIAL, 8080895);
        setRegistryName(EmortisConstants.MOD_ID, "glide");
    }
}
