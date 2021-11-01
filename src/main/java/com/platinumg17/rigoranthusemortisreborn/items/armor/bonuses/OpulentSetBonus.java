package com.platinumg17.rigoranthusemortisreborn.items.armor.bonuses;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class OpulentSetBonus extends Effect {

    public OpulentSetBonus() {
        super(EffectType.BENEFICIAL, 0X51FFAF);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", 1.0F, AttributeModifier.Operation.MULTIPLY_BASE);
        this.addAttributeModifier(Attributes.LUCK, "03C3C89D-7037-4B42-869F-B146BCB64D2E", 2.0F, AttributeModifier.Operation.MULTIPLY_BASE);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3",2.0F, AttributeModifier.Operation.MULTIPLY_BASE);
        this.setRegistryName(EmortisConstants.MOD_ID, "opulent_set_bonus");

    }
    private static int effectTimer = 400;
    public void applyEffectTick(LivingEntity player, int amplifier) {
        effectTimer--;
        if (effectTimer < 0) {
            player.addEffect(new EffectInstance(Effects.JUMP, 400, 2));
            player.addEffect(new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 400, 1));
            player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 400, 1));
            // List as many other effects as you want here. This is for effects that do not have Conditions that need to be met before the effect is applied.
            effectTimer = 400;
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }

    public String getName() {
        return "rigoranthusemortisreborn.potion.opulent_set_bonus";
    }
}
