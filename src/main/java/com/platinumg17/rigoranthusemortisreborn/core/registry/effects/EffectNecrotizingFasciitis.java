package com.platinumg17.rigoranthusemortisreborn.core.registry.effects;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class EffectNecrotizingFasciitis extends Effect {

//    private int lastDuration = -1;

    protected EffectNecrotizingFasciitis() {
        super(EffectType.HARMFUL, 0XED5151);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890",-0.3F, AttributeModifier.Operation.MULTIPLY_BASE);
        this.addAttributeModifier(Attributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC",-3.0F, AttributeModifier.Operation.ADDITION);
        this.setRegistryName(RigoranthusEmortisReborn.MOD_ID, "necrotizing_fasciitis");
    }
    private static int damageTimer = 20;
    public void applyEffectTick(LivingEntity player, int amplifier) {
//        player.hurt(DamageSource.GENERIC, Math.min(amplifier + 1, Math.round(lastDuration / 20F)));
        for(int i = 0; i < 3; i++){
            player.level.addParticle(ParticleTypes.FALLING_HONEY, player.getRandomX(1.0), player.getRandomY(), player.getRandomZ(1.0), 0, 0, 0);
        }
        if(player.getDeltaMovement().y > 0 && !player.isInWaterOrBubble()){
            player.setDeltaMovement(player.getDeltaMovement().multiply(1, 0, 1));
        }
        damageTimer--;
        if (damageTimer < 0) {
            player.hurt(DamageSource.GENERIC,  0.5f);
            damageTimer = 20;
        }
        if(player.getDeltaMovement().y > 0 && !player.isInWaterOrBubble()){
            player.setDeltaMovement(player.getDeltaMovement().multiply(1, 0, 1));
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
//        lastDuration = duration;
        return duration > 0;// && duration % 20 == 0;
    }

    public String getName() {
        return "rigoranthusemortisreborn.potion.necrotizing_fasciitis";
    }
}