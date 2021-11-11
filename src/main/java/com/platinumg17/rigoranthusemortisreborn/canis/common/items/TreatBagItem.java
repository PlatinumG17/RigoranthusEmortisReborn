package com.platinumg17.rigoranthusemortisreborn.canis.common.items;

import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.TreatBagItemHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.screens.CanisScreens;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.Cache;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.InventoryUtil;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.ItemUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.EmptyHandler;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.ICanisFoodHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TreatBagItem extends Item implements ICanisFoodHandler {

    private Cache<String> contentsTranslationKey = Cache.make(() -> this.getDescriptionId() + ".contents");

    public TreatBagItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);

        if (worldIn.isClientSide) {
            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
        }
        else {
            if (playerIn instanceof ServerPlayerEntity && !(playerIn instanceof FakePlayer)) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;

                CanisScreens.openTreatBagScreen(serverPlayer, stack, playerIn.inventory.selected);
            }

            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        IItemHandler bagInventory = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(EmptyHandler.INSTANCE);
        List<ItemStack> condensedContents = ItemUtil.getContentOverview(bagInventory);

        condensedContents.forEach((food) -> {
            tooltip.add(new TranslationTextComponent(this.contentsTranslationKey.get(), food.getCount(), new TranslationTextComponent(food.getDescriptionId())));
        });
    }

    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundNBT nbt) {
        // https://github.com/MinecraftForge/MinecraftForge/issues/5989
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY == null) {
            return null;
        }

        return new ICapabilityProvider() {
            final LazyOptional<IItemHandler> itemHandlerInstance = LazyOptional.of(() -> new TreatBagItemHandler(stack));

            @Override
            @Nonnull
            public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable Direction side) {
                if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                    return (LazyOptional<T>) this.itemHandlerInstance;
                }
                return LazyOptional.empty();
            }
        };
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return !ItemStack.isSame(oldStack, newStack);
    }

    @Override
    public boolean isFood(ItemStack stackIn) {
        return false;
    }

    @Override
    public boolean canConsume(AbstractCanisEntity canisIn, ItemStack stackIn, Entity entityIn) {
        return entityIn instanceof LivingEntity ? canisIn.canInteract((LivingEntity) entityIn) : false;
    }

    @Override
    public ActionResultType consume(AbstractCanisEntity canisIn, ItemStack stackIn, Entity entityIn) {
        IItemHandlerModifiable treatBag = (IItemHandlerModifiable) stackIn.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(EmptyHandler.INSTANCE);
        return InventoryUtil.feedCanisFrom(canisIn, entityIn, treatBag);
    }
}