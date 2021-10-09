package com.platinumg17.rigoranthusemortisreborn.items.armor.bonuses;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class PhantasmalSetBonus extends Effect {

    public PhantasmalSetBonus() {
        super(EffectType.BENEFICIAL, 0X51FFAF);
        this.setRegistryName(RigoranthusEmortisReborn.MOD_ID, "phantasmal_set_bonus");

    }
    private static int effectTimer = 400;
    public void applyEffectTick(LivingEntity player, int amplifier) {
        if ((player.level.getMaxLocalRawBrightness(player.getEntity().blockPosition()) < 5) || player.level.isNight()) {
            player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 400, 1));
            if (player.isShiftKeyDown()) {
                player.addEffect(new EffectInstance(Effects.LEVITATION, 20, 2));
            }
            effectTimer--;
            if (effectTimer < 0) {
                player.addEffect(new EffectInstance(Effects.INVISIBILITY, 400, 1));
                player.addEffect(new EffectInstance(Effects.JUMP, 400, 6));
                player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 400, 2));
                player.addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 400, 6));
                effectTimer = 400;
            }
        }
        if ((player.level.dimension() == (World.END))) {
            this.addAttributeModifier(Attributes.LUCK, "03C3C89D-7037-4B42-869F-B146BCB64D2E", 2.0F, AttributeModifier.Operation.MULTIPLY_BASE);
            if (player.isShiftKeyDown()) {
                player.addEffect(new EffectInstance(Effects.LEVITATION, 20, 2));
            }
            effectTimer--;
            if (effectTimer < 0) {
                player.addEffect(new EffectInstance(Effects.JUMP, 400, 10));
                player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 400, 2));
                player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 100, 2));
                player.addEffect(new EffectInstance(Effects.SATURATION, 100, 2));
                effectTimer = 400;
            }
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }

    public String getName() {
        return "rigoranthusemortisreborn.potion.phantasmal_set_bonus";
    }
}

