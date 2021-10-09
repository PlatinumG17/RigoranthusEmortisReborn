package com.platinumg17.rigoranthusemortisreborn.items.armor;

import com.google.common.collect.ImmutableMap;
import com.platinumg17.rigoranthusemortisreborn.items.RigoranthusArmorMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;

import java.util.Map;
import java.util.Objects;
import java.util.Random;
/*
public class RigoranthusArmorItem extends ArmorItem {
    private static final Map<IArmorMaterial, Effect, AttributeModifier> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<IArmorMaterial, Effect, AttributeModifier>())
                    .put(RigoranthusArmorMaterial.AQUEOUS_NETHERITE, Effects.FIRE_RESISTANCE, AttributeModifier.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", (double)-1.0F, AttributeModifier.Operation.MULTIPLY_BASE);
                    .put(RigoranthusArmorMaterial.APOGEAN_NETHERITE, Effects.INVISIBILITY, AttributeModifier.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", (double)-1.0F, AttributeModifier.Operation.MULTIPLY_BASE).build();

    public RigoranthusArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties settings) {
        super(material, slot, settings);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<IArmorMaterial, Effect, AttributeModifier> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            IArmorMaterial mapArmorMaterial = entry.getKey();
            Effect mapStatusEffect = entry.getValue();
            AttributeModifier mapAttributeModifier = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect, mapAttributeModifier);
            }
        }
    }

    private void addAttributeModifierForMaterial(PlayerEntity player, IArmorMaterial mapArmorMaterial, AttributeModifier mapAttributeModifier) {
        boolean hasPlayerAttributeModifier = !Objects.equals(player.getAttributes(mapAttributeModifier), null);
        if(hasCorrectArmorOn(mapAttributeModifier, player) && !hasPlayerAttributeModifier) {
            player.getAttributes(AttributeModifier.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", (double)-1.0F, AttributeModifier.Operation.MULTIPLY_BASE);

        }
    }
//
//    @Override
//    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
//        if(!world.isClientSide()) {
//            if(entity instanceof PlayerEntity) {
//                PlayerEntity player = (PlayerEntity)entity;
//
//                if(hasFullSuitOfArmorOn(player)) {
//                    evaluateArmorEffects(player);
//                }
//            }
//        }
//
//        super.inventoryTick(stack, world, entity, slot, selected);
//    }
//
//    private void addStatusEffectForMaterial(PlayerEntity player, IArmorMaterial mapArmorMaterial, Effect mapStatusEffect) {
//        boolean hasPlayerEffect = !Objects.equals(player.getEffect(mapStatusEffect), null);
//
//        if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
//            player.addEffect(new EffectInstance(mapStatusEffect, 200));
//
//            // If Statement could be deleted if you don't want to damage the Armor
//            if(new Random().nextFloat() > 0.6f) { // 40% of damaging the armor! Possibly!
//                // Uncomment if you wanna damage armor
//                // player.inventory.func_234563_a_(DamageSource.MAGIC, 1f);
//            }
//        }
//    }
//
//    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
//        ItemStack boots = player.inventory.getArmor(0);
//        ItemStack leggings = player.inventory.getArmor(1);
//        ItemStack breastplate = player.inventory.getArmor(2);
//        ItemStack helmet = player.inventory.getArmor(3);
//
//        return !helmet.isEmpty() && !breastplate.isEmpty()
//                && !leggings.isEmpty() && !boots.isEmpty();
//    }
//
//    private boolean hasCorrectArmorOn(IArmorMaterial material, PlayerEntity player) {
//        ArmorItem boots = ((ArmorItem)player.inventory.getArmor(0).getItem());
//        ArmorItem leggings = ((ArmorItem)player.inventory.getArmor(1).getItem());
//        ArmorItem breastplate = ((ArmorItem)player.inventory.getArmor(2).getItem());
//        ArmorItem helmet = ((ArmorItem)player.inventory.getArmor(3).getItem());
//
//        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
//                leggings.getMaterial() == material && boots.getMaterial() == material;
//    }
 */