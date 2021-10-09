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

public class AqueousSetBonus extends Effect {

    public AqueousSetBonus() {
        super(EffectType.BENEFICIAL, 0X51FFAF);
        this.setRegistryName(RigoranthusEmortisReborn.MOD_ID, "aqueous_set_bonus");
    }
    private static int healTimer = 80;
    public void applyEffectTick(LivingEntity player, int amplifier) {
        player.fireImmune();
        if (player.isInWaterRainOrBubble()) {
            this.addAttributeModifier(Attributes.LUCK, "03C3C89D-7037-4B42-869F-B146BCB64D2E", 2.0F, AttributeModifier.Operation.MULTIPLY_BASE);
            this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", 1.0F, AttributeModifier.Operation.MULTIPLY_BASE);
            this.addAttributeModifier(Attributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 2.0F, AttributeModifier.Operation.MULTIPLY_BASE);
            player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 200, 3));
            if (player.isInWater()) {
                player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 200, 4));
                player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 200, 1));
                healTimer--;
                if (healTimer < 0) {
                    player.heal(1f);
                    healTimer = 40;
                }
            }
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }

    public String getName() {
        return "rigoranthusemortisreborn.potion.aqueous_set_bonus";
    }
}
