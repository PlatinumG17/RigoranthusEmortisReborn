package com.platinumg17.rigoranthusemortisreborn.canis.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.ICanisFoodHandler;

public class ChewStickItem extends Item implements ICanisFoodHandler {

    public ChewStickItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFood(ItemStack stackIn) {
        return stackIn.getItem() == this;
    }

    @Override
    public boolean canConsume(AbstractCanisEntity canisIn, ItemStack stackIn, Entity entityIn) {
        return true;
    }

    @Override
    public ActionResultType consume(AbstractCanisEntity canisIn, ItemStack stackIn, Entity entityIn) {
        if (!canisIn.level.isClientSide) {
            canisIn.addEffect(new EffectInstance(Effects.GLOWING, 100, 1, false, true));
            canisIn.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 200, 6, false, true));
            canisIn.addEffect(new EffectInstance(Effects.REGENERATION, 100, 2, false, true));
            canisIn.consumeItemFromStack(entityIn, stackIn);
        }
        return ActionResultType.SUCCESS;
    }
}
