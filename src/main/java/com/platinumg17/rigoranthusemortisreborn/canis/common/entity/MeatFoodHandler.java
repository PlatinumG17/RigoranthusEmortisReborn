package com.platinumg17.rigoranthusemortisreborn.canis.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.ICanisFoodHandler;

public class MeatFoodHandler implements ICanisFoodHandler {

    @Override
    public boolean isFood(ItemStack stackIn) {
        return stackIn.isEdible() && stackIn.getItem().getFoodProperties().isMeat() && stackIn.getItem() != Items.ROTTEN_FLESH;
    }

    @Override
    public boolean canConsume(AbstractCanisEntity canisIn, ItemStack stackIn, Entity entityIn) {
        return this.isFood(stackIn);
    }

    @Override
    public ActionResultType consume(AbstractCanisEntity canisIn, ItemStack stackIn, Entity entityIn) {

        if (canisIn.getCanisHunger() < canisIn.getMaxHunger()) {
            if (!canisIn.level.isClientSide) {
                int heal = stackIn.getItem().getFoodProperties().getNutrition() * 5;

                canisIn.setCanisHunger(canisIn.getCanisHunger() + heal);
                canisIn.consumeItemFromStack(entityIn, stackIn);
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }
}
