package com.platinumg17.rigoranthusemortisreborn.items.armor.bonuses;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class PerniciousSetBonus extends Effect {

    public PerniciousSetBonus() {
        super(EffectType.BENEFICIAL, 0X51FFAF);
        this.setRegistryName(EmortisConstants.MOD_ID, "pernicious_set_bonus");

    }
    private static int effectTimer = 400;
    public void applyEffectTick(LivingEntity player, int amplifier) {
        if (player.hasEffect(Effects.HUNGER)) {
            player.removeEffect(Effects.HUNGER);
        }
        if (player.hasEffect(Effects.HARM)) {
            player.removeEffect(Effects.HARM);
        }
        if (player.hasEffect(Effects.WEAKNESS)) {
            player.removeEffect(Effects.WEAKNESS);
        }
        if (player.hasEffect(Effects.DIG_SLOWDOWN)) {
            player.removeEffect(Effects.DIG_SLOWDOWN);
        }
        if (player.hasEffect(Effects.MOVEMENT_SLOWDOWN)) {
            player.removeEffect(Effects.MOVEMENT_SLOWDOWN);
        }
        effectTimer--;
        if (effectTimer < 0) {
            player.addEffect(new EffectInstance(Effects.SATURATION, 400, 2));
            // List as many other effects as you want here. This is for effects that do not have Conditions that need to be met before the effect is applied.
            effectTimer = 400;
        }
        if (player.hasEffect(Effects.POISON)) {
            player.removeEffect(Effects.POISON);
            player.addEffect(new EffectInstance(Effects.REGENERATION, 100, 2));
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }

    public String getName() {
        return "rigoranthusemortisreborn.potion.pernicious_set_bonus";
    }
}