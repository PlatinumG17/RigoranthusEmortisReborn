package com.platinumg17.rigoranthusemortisreborn.canis.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.ICanisItem;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Accoutrement;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.AccoutrementInstance;

import java.util.function.Supplier;

public class AccoutrementItem extends Item implements ICanisItem {

    public Supplier<? extends Accoutrement> type;

    public AccoutrementItem(Supplier<? extends Accoutrement> type, Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public ActionResultType processInteract(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (canisIn.canInteract(playerIn) && canisIn.addAccoutrement(this.createInstance(canisIn, playerIn.getItemInHand(handIn), playerIn))) {
            canisIn.consumeItemFromStack(playerIn, playerIn.getItemInHand(handIn));
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    public AccoutrementInstance createInstance(AbstractCanisEntity canisIn, ItemStack stack, PlayerEntity playerIn) {
        return this.type.get().getDefault();
    }
}
