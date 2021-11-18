package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.ColorCache;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.ICanisTransmogrification;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Accoutrement;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.AccoutrementInstance;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.AccoutrementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;
import java.util.function.Supplier;

public class DyeableAccoutrement extends Accoutrement {

    public DyeableAccoutrement(Supplier<? extends AccoutrementType> typeIn, Supplier<? extends IItemProvider> itemIn) {
        super(typeIn, itemIn);
    }

    @Override
    public AccoutrementInstance createInstance(PacketBuffer buf) {
        return this.create(buf.readInt());
    }

    @Override
    public void write(AccoutrementInstance instance, PacketBuffer buf) {
        DyeableAccoutrementInstance exact = instance.cast(DyeableAccoutrementInstance.class);
        buf.writeInt(exact.getColor());
    }

    @Override
    public void write(AccoutrementInstance instance, CompoundNBT compound) {
        DyeableAccoutrementInstance exact = instance.cast(DyeableAccoutrementInstance.class);
        compound.putInt("color", exact.getColor());
    }
    @Override public AccoutrementInstance read(CompoundNBT compound) {return this.create(compound.getInt("color"));}

    @Override
    public AccoutrementInstance getDefault() {return this.create(0);}

    @Override
    public ItemStack getReturnItem(AccoutrementInstance instance) {
        DyeableAccoutrementInstance exact = instance.cast(DyeableAccoutrementInstance.class);
        ItemStack returnStack = super.getReturnItem(instance);
        if (returnStack.getItem() instanceof IDyeableArmorItem) {
            ((IDyeableArmorItem) returnStack.getItem()).setColor(returnStack, exact.getColor());
        } else {
            RigoranthusEmortisReborn.LOGGER.info("Unable to set set dyeable accoutrement color.");
        }
        return returnStack;
    }
    public AccoutrementInstance create(int color) {return new DyeableAccoutrementInstance(color);}

    @Override
    public AccoutrementInstance createFromStack(ItemStack stackIn) {
        Item item = stackIn.getItem();
        if (item instanceof IDyeableArmorItem) {
            return this.create(((IDyeableArmorItem) item).getColor(stackIn));
        }
        return this.getDefault();
    }

    public class DyeableAccoutrementInstance extends AccoutrementInstance implements ICanisTransmogrification {
        private ColorCache color;
        public DyeableAccoutrementInstance(int colorIn) {this(ColorCache.make(colorIn));}
        public DyeableAccoutrementInstance(ColorCache colorIn) {
            super(DyeableAccoutrement.this);
            this.color = colorIn;
        }
        public int getColor() {return this.color.get();}
        public float[] getFloatArray() {return this.color.getFloatArray();}
        @Override public AccoutrementInstance copy() {return new DyeableAccoutrementInstance(this.color);}
        @Override
        public ActionResultType processInteract(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn) {
            ItemStack stack = playerIn.getItemInHand(handIn);
            DyeColor dyeColor = DyeColor.getColor(stack);
            if (dyeColor != null) {
                int colorNew = REUtil.colorDye(this.color.get(), dyeColor);
                // No change
                if (this.color.is(colorNew)) {
                    return ActionResultType.FAIL;
                }
                this.color = ColorCache.make(colorNew);
                canisIn.consumeItemFromStack(playerIn, stack);
                // Make sure to sync change with client
                canisIn.markAccoutermentsDirty();
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.PASS;
        }
    }
}