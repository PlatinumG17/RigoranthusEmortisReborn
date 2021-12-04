package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.AbstractDominionTile;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.DominionUtil;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrystallizerTile extends AbstractDominionTile implements IInventory {
    private final LazyOptional<IItemHandler> itemHandler = LazyOptional.of(() -> new InvWrapper(this));
    public ItemStack stack = ItemStack.EMPTY;
    public ItemEntity entity;
    public boolean draining;

    public CrystallizerTile() {
        super(BlockRegistry.CRYSTALLIZER_TILE);
    }

    @Override
    public int getTransferRate() {
        return 0;
    }

    @Override
    public void tick() {
        if(level.isClientSide)
            return;

        if(this.stack.isEmpty() && this.level.getGameTime() % 20 == 0 && DominionUtil.takeDominionNearby(worldPosition, level, 1, 200) != null){
            this.addDominion(500);
            if(!draining) {
                draining = true;
                update();
            }
        }
//        else if(this.level.getGameTime() % 20 == 0){     <- <- <- Passive generation of Dominion
//            this.addDominion(5);
//            if(draining){
//                draining = false;
//                update();
//            }
//        }
        if(this.getCurrentDominion() >= 5000 && (stack == null || stack.isEmpty())){
            Item foundItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.CRYSTALLIZER_ITEM.get()));
            if(foundItem == null){
                System.out.println("NULL CRYSTALLIZER ITEM.");
                foundItem = MagicItemsRegistry.dominionGem;
            }
            this.stack = new ItemStack(foundItem);

            this.setDominion(0);
        }
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        stack = ItemStack.of((CompoundNBT)tag.get("itemStack"));
        draining = tag.getBoolean("draining");
        super.load(state, tag);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        if(stack != null) {
            CompoundNBT reagentTag = new CompoundNBT();
            stack.save(reagentTag);
            tag.put("itemStack", reagentTag);
        }
        tag.putBoolean("draining", draining);
        return super.save(tag);
    }

    @Override
    public int getMaxDominion() {
        return 5000;
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return this.stack == null || this.stack.isEmpty();
    }

    @Override
    public ItemStack getItem(int index) {
        return stack;
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack copy = stack.copy();
        stack.shrink(count);
        return copy;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack stack = this.stack.copy();
        this.stack = ItemStack.EMPTY;
        return stack;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return true;
    }

    @Override
    public void clearContent() {
        this.stack = ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, final @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    protected void invalidateCaps() {
        itemHandler.invalidate();
        super.invalidateCaps();
    }
}