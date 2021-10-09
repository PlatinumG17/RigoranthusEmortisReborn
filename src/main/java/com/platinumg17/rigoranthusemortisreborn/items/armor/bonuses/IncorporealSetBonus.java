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
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IncorporealSetBonus extends Effect {

    public IncorporealSetBonus() {
        super(EffectType.BENEFICIAL, 0X51FFAF);
        this.setRegistryName(RigoranthusEmortisReborn.MOD_ID, "incorporeal_set_bonus");

    }
    private static int healTimer = 80;
    private static int damageTimer = 10;
    public void applyEffectTick(LivingEntity player, int amplifier) {
        if ((player.level.getMaxLocalRawBrightness(player.getEntity().blockPosition()) < 5) || player.level.isNight()) {
            player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 400, 1));
            if (player.level.isNight()) {
                player.addEffect(new EffectInstance(Effects.INVISIBILITY, 400, 1));
                if (player.isCrouching()) {
                    healTimer--;
                    if (healTimer < 0) {
                        player.heal(1f);
                        healTimer = 40;
                    }
                }
            }
        }
        if (player.isInWaterRainOrBubble()) {
            this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890",-0.3F, AttributeModifier.Operation.MULTIPLY_BASE);
            damageTimer--;
            if (damageTimer < 0) {
                player.hurt(DamageSource.GENERIC,  1f);
                damageTimer = 20;
            }
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }

    public String getName() {
        return "rigoranthusemortisreborn.potion.incorporeal_set_bonus";
    }
}
