package com.platinumg17.rigoranthusemortisreborn.canis.common.block.tileentity;

import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisTileEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container.FoodBowlContainer;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.InventoryUtil;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.FoodHandler;

public class FoodBowlTileEntity extends PlacedTileEntity implements INamedContainerProvider, ITickableTileEntity {

    private final ItemStackHandler inventory = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            // When contents change mark needs save to disc
            FoodBowlTileEntity.this.setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return FoodHandler.isFood(stack).isPresent();
        }
    };
    private final LazyOptional<ItemStackHandler> itemStackHandler = LazyOptional.of(() -> this.inventory);

    public int timeoutCounter;

    public FoodBowlTileEntity() {
        super(CanisTileEntityTypes.FOOD_BOWL.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        this.inventory.deserializeNBT(compound);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        compound.merge(this.inventory.serializeNBT());
        return compound;
    }

    @Override
    public void tick() {
        //Only run update code every 5 ticks (0.25s)
        if (++this.timeoutCounter < 5) { return; }
        List<CanisEntity> canisList = this.level.getEntitiesOfClass(CanisEntity.class, new AxisAlignedBB(this.worldPosition).inflate(5, 5, 5));
        for (CanisEntity canis : canisList) {
            //TODO make canis bowl remember who placed and only their cani can attach to the bowl
            UUID placerId = this.getPlacerId();
            if (placerId != null && placerId.equals(canis.getOwnerUUID()) && !canis.getBowlPos().isPresent()) {
                canis.setBowlPos(this.worldPosition);
            }
            if (canis.getCanisHunger() < canis.getMaxHunger() / 2) {
                InventoryUtil.feedCanisFrom(canis, null, this.inventory);
            }
        }
        this.timeoutCounter = 0;
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (LazyOptional<T>) this.itemStackHandler;
        }
        return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.rigoranthusemortisreborn.food_bowl");
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerIn) {
        return new FoodBowlContainer(windowId, this.level, this.worldPosition, playerInventory, playerIn);
    }
}