package com.platinumg17.rigoranthusemortisreborn.core.registry.effects.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Supplier;

public interface InventoryTickEffect {
    void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected);

//    InventoryTickEffect SOFT_LANDING = passiveAspectEffect(EnumAspect.CALLOUSED, () -> new EffectInstance(Effects.SLOW_FALLING, 2, 2));

    InventoryTickEffect DROP_WHEN_IN_WATER = (stack, worldIn, entityIn, itemSlot, isSelected) -> {
        if(isSelected && entityIn.isInWater() && entityIn instanceof LivingEntity) {
            stack.hurtAndBreak(70, ((LivingEntity) entityIn), entity -> entity.broadcastBreakEvent(Hand.MAIN_HAND));
            ItemEntity weapon = new ItemEntity(entityIn.level, entityIn.getX(), entityIn.getY(), entityIn.getZ(), stack.copy());
            weapon.getItem().setCount(1);
            weapon.setPickUpDelay(40);
            entityIn.level.addFreshEntity(weapon);
            stack.shrink(1);

            entityIn.hurt(DamageSource.LIGHTNING_BOLT, 5);
        }
    };

    //TODO divide into useful components similarly to in OnHitEffect, such as requireAspect(aspect, tickEffect), whenSelected(tickEffect), potionEffect(effect)
//    static InventoryTickEffect passiveAspectEffect(EnumAspect aspect, Supplier<EffectInstance> effect) {
//        return (stack, worldIn, entityIn, itemSlot, isSelected) -> {
//            if(isSelected && entityIn instanceof ServerPlayerEntity) {
//                Title title = PlayerSavedData.getData((ServerPlayerEntity) entityIn).getTitle();
//                if(title != null && title.getPlayerAspect() == aspect)
//                    ((ServerPlayerEntity) entityIn).addEffect(effect.get());
//            }
//        };
//    }
}