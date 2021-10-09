package com.platinumg17.rigoranthusemortisreborn.items.armor.bonuses;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class ApogeanSetBonus extends Effect {

    public ApogeanSetBonus() {
        super(EffectType.BENEFICIAL, 0X51FFAF);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", 1.0F, AttributeModifier.Operation.MULTIPLY_BASE);
        this.addAttributeModifier(Attributes.LUCK, "03C3C89D-7037-4B42-869F-B146BCB64D2E", 2.0F, AttributeModifier.Operation.MULTIPLY_BASE);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 2.0F, AttributeModifier.Operation.MULTIPLY_BASE);
        this.addAttributeModifier(Attributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 1.0F, AttributeModifier.Operation.MULTIPLY_BASE);
        // These act like constant potion effects, and are essentially just recreations of existing vanilla potion effects
        this.setRegistryName(RigoranthusEmortisReborn.MOD_ID, "apogean_set_bonus");
    }
    private static int healTimer = 80;
    private static int effectTimer = 400;
    private static int bubbleTimer = 200;
    public void applyEffectTick(LivingEntity player, int amplifier) {
//        effectTimer--;
//        if (effectTimer < 0) {
//            player.addEffect(new EffectInstance(Effects.JUMP, 400, 2));
//            // List as many other effects as you want here. This is for effects that do not have Conditions that need to be met before the effect is applied.
//            effectTimer = 400;
//        }
        if (player.isOnFire() || player.isInLava()) {
            healTimer--;
            if (healTimer < 0) {
                player.heal(1f);
                healTimer = 40;
            }
        }
        if (player.isInWaterOrBubble()) {
            player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 200, 1));
            bubbleTimer--;
            if (bubbleTimer < 0) {
                //player.getAirSupply();
                player.setAirSupply((player.getAirSupply()) + 1);
                // Im trying to make a system where it adds 1 air bubble to the player's air supply in set intervals (Instead of giving Water Breathing)
                bubbleTimer = 400;
            }
        }
        if ((player.level.getMaxLocalRawBrightness(player.getEntity().blockPosition()) < 5) || player.level.isNight()) {
             player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 400, 1));
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }
    public String getName() {
        return "rigoranthusemortisreborn.potion.apogean_set_bonus";
    }
}