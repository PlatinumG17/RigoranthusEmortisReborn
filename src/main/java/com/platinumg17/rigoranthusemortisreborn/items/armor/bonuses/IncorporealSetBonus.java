package com.platinumg17.rigoranthusemortisreborn.items.armor.bonuses;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;

public class IncorporealSetBonus extends Effect {

    public IncorporealSetBonus() {
        super(EffectType.BENEFICIAL, 0X51FFAF);
        this.setRegistryName(EmortisConstants.MOD_ID, "incorporeal_set_bonus");
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
            if(player.level.isClientSide) {
                ClientPlayerEntity playerClient = Minecraft.getInstance().player;
                if (playerClient != null && playerClient.input.jumping) {
                    player.setDeltaMovement(player.getDeltaMovement().multiply(0.8, 0.8, 0.8));
                } else player.setDeltaMovement(player.getDeltaMovement().multiply(0.8, 2.2, 0.8));
            }
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
