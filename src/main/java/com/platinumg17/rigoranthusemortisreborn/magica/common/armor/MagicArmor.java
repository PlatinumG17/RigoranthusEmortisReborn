package com.platinumg17.rigoranthusemortisreborn.magica.common.armor;

import com.platinumg17.rigoranthusemortisreborn.magica.common.capability.DominionCapability;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.IDominionEquipment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class MagicArmor extends ArmorItem implements IDominionEquipment {

    public MagicArmor(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if(world.isClientSide() || world.getGameTime() % 200 !=  0 || stack.getDamageValue() == 0)
            return;

        DominionCapability.getDominion(player).ifPresent(dominion -> {
            if(dominion.getCurrentDominion() > 20){
                dominion.removeDominion(20);
                stack.setDamageValue(stack.getDamageValue() - 1);
            }
        });
    }
}