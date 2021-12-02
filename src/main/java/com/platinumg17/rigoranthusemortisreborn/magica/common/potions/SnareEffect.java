package com.platinumg17.rigoranthusemortisreborn.magica.common.potions;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class SnareEffect extends Effect {

    public SnareEffect() {
        super(EffectType.HARMFUL, 2039587);
        setRegistryName(EmortisConstants.MOD_ID, "snared");
        addAttributeModifier(Attributes.MOVEMENT_SPEED, "0dee8a21-f182-42c8-8361-1ad6186cac30", -1, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}