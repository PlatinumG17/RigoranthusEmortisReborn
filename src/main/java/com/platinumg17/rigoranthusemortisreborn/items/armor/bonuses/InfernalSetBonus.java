package com.platinumg17.rigoranthusemortisreborn.items.armor.bonuses;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class InfernalSetBonus extends Effect {

    public InfernalSetBonus() {
        super(EffectType.BENEFICIAL, 0X51FFAF);
        this.setRegistryName(EmortisConstants.MOD_ID, "infernal_set_bonus");

    }
    private static int healTimer = 80;
    private static int damageTimer = 10;
    private static int effectTimer = 400;
    public void applyEffectTick(LivingEntity player, int amplifier) {
        if (player.isOnFire() || player.isInLava()) {
            player.clearFire();
            if(player.level.isClientSide) {
                ClientPlayerEntity playerClient = Minecraft.getInstance().player;
                if (playerClient != null && (playerClient.input.up || playerClient.input.down || playerClient.input.left || playerClient.input.right)) {
                    player.setDeltaMovement(player.getDeltaMovement().multiply(1.1, 1.1, 1.1));
                }
            }
            healTimer--;
            if (healTimer < 0) {
                player.heal(1f);
                healTimer = 40;
            }
        }
        if (player.isInWaterRainOrBubble()) {
            if(player.level.isClientSide) {
                ClientPlayerEntity playerClient = Minecraft.getInstance().player;
                if (playerClient != null && (playerClient.input.up || playerClient.input.down || playerClient.input.left || playerClient.input.right)) {
                    player.setDeltaMovement(player.getDeltaMovement().multiply(0.8, 0.8, 0.8));
                }
            }
            damageTimer--;
            if (damageTimer < 0) {
                player.hurt(DamageSource.GENERIC,  1f);
                damageTimer = 20;
            }
        }
        if ((player.level.dimension() == (World.NETHER))) {
            this.addAttributeModifier(Attributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 2.0F, AttributeModifier.Operation.ADDITION);
            this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 2.0F, AttributeModifier.Operation.ADDITION);
            this.addAttributeModifier(Attributes.LUCK, "03C3C89D-7037-4B42-869F-B146BCB64D2E", 2.0F, AttributeModifier.Operation.MULTIPLY_BASE);
            effectTimer--;
            if (effectTimer < 0) {
                player.addEffect(new EffectInstance(Effects.JUMP, 400, 2));
                player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 400, 2));
                // List as many other effects as you want here. This is for effects that do not have Conditions that need to be met before the effect is applied.
                effectTimer = 400;
            }
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }

    public String getName() {
        return "rigoranthusemortisreborn.potion.infernal_set_bonus";
    }
}
