package com.platinumg17.rigoranthusemortisreborn.items.armor.bonuses;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class AtrophyingSetBonus extends Effect {

    public AtrophyingSetBonus() {
        super(EffectType.BENEFICIAL, 0X51FFAF);
        this.setRegistryName(EmortisConstants.MOD_ID, "atrophying_set_bonus");
    }

    public void applyEffectTick(LivingEntity player, int amplifier) {
        if (player.hasEffect(Effects.WITHER)) {
            player.removeEffect(Effects.WITHER);
        }
        if ((player.level.dimension() == (World.NETHER))) {
            if (player.isOnFire()) {
                player.clearFire();
            }
            this.addAttributeModifier(Attributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 2.0F, AttributeModifier.Operation.ADDITION);
            this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 2.0F, AttributeModifier.Operation.ADDITION);
            this.addAttributeModifier(Attributes.LUCK, "03C3C89D-7037-4B42-869F-B146BCB64D2E", 2.0F, AttributeModifier.Operation.MULTIPLY_BASE);
            //player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 100, 3));
            //player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 100, 2));
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }

    public String getName() {
        return "rigoranthusemortisreborn.potion.atrophying_set_bonus";
    }
}
