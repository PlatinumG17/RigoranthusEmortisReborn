package com.platinumg17.rigoranthusemortisreborn.items.itemeffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.function.Supplier;

public interface FinishUseItemEffect {
    ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityIn);

    static FinishUseItemEffect foodEffect(int healAmount, float saturationModifier) {
        return foodEffect(healAmount, saturationModifier, 50);
    }

    static FinishUseItemEffect foodEffect(int healAmount, float saturationModifier, int damageTaken) {
        return (stack, worldIn, entityIn) -> {
            stack.hurtAndBreak(damageTaken, entityIn, entity -> entity.broadcastBreakEvent(Hand.MAIN_HAND));
            if(entityIn instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entityIn;
                player.getFoodData().eat(healAmount, saturationModifier);
                worldIn.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.random.nextFloat() * 0.1F + 0.9F);
            }
            return stack;
        };
    }

    static FinishUseItemEffect potionEffect(Supplier<EffectInstance> effect, float probability) {
        return (stack, worldIn, entityLiving) -> {
            if (!worldIn.isClientSide && worldIn.random.nextFloat() < probability)
                entityLiving.addEffect(effect.get());
            return stack;
        };
    }
}