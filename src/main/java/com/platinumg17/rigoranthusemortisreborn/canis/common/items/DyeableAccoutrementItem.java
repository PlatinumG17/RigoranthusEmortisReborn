package com.platinumg17.rigoranthusemortisreborn.canis.common.items;

import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.DyeableAccoutrement;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.registry.AccoutrementInstance;

import java.util.function.Supplier;

public class DyeableAccoutrementItem extends AccoutrementItem implements IDyeableArmorItem {

    private Supplier<? extends DyeableAccoutrement> accoutrement;

    public DyeableAccoutrementItem(Supplier<? extends DyeableAccoutrement> accoutrementIn, Properties properties) {
        super(accoutrementIn, properties);
        this.accoutrement = accoutrementIn;
    }

    @Override
    public AccoutrementInstance createInstance(AbstractCanisEntity canisIn, ItemStack stack, PlayerEntity playerIn) {
        return this.accoutrement.get().create(this.getColor(stack));
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            ItemStack stack = new ItemStack(this);
            this.setColor(stack, this.getDefaultColor(stack));
            items.add(stack);
        }
    }
}
