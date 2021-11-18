package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments;

import com.platinumg17.rigoranthusemortisreborn.canis.common.util.ColorCache;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.IColoredObject;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.AccoutrementInstance;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.AccoutrementType;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.util.Constants;

import java.util.function.Supplier;

public class LeatherArmorAccoutrement extends ArmorAccoutrement {
    public LeatherArmorAccoutrement(Supplier<? extends AccoutrementType> typeIn, Supplier<? extends IItemProvider> itemIn) {
        super(typeIn, itemIn);
    }

    @Override
    public AccoutrementInstance create(ItemStack armorStack) {
        if (armorStack.isEmpty()) {
            if (this.of(CanisAccouterments.LEATHER_HELMET)) {
                armorStack = new ItemStack(Items.LEATHER_HELMET);
            } else {
                throw new IllegalArgumentException();
            }
        }
        return new LeatherArmorAccoutrement.Instance(armorStack.copy());
    }

    @Override
    public AccoutrementInstance read(CompoundNBT compound) {
        AccoutrementInstance inst = super.read(compound);
        if (this.of(CanisAccouterments.LEATHER_HELMET)) {
            // Backwards compatibility
            if (compound.contains("color", Constants.NBT.TAG_ANY_NUMERIC)) {
                int color = compound.getInt("color");
                Instance def = inst.cast(Instance.class);
                if (def.armorStack.getItem() instanceof IDyeableArmorItem) {
                    ((IDyeableArmorItem) def.armorStack.getItem()).setColor(def.armorStack, color);
                }
                def.color = ColorCache.make(color);
            }
        }
        return inst;
    }

    public class Instance extends ArmorAccoutrement.Instance implements IColoredObject {

        private ColorCache color = ColorCache.WHITE;
        public Instance(ItemStack armorStack) {
            super(armorStack);
            if (armorStack.getItem() instanceof IDyeableArmorItem) {
                this.color = ColorCache.make(((IDyeableArmorItem) armorStack.getItem()).getColor(armorStack));
            }
        }

        @Override
        public AccoutrementInstance copy() {
            return new LeatherArmorAccoutrement.Instance(this.armorStack.copy());
        }

        @Override
        public float[] getColor() {
            return this.color.getFloatArray();
        }
    }
}