package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.google.common.collect.ImmutableList;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.IIchoricRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.DominionUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketOneShotAnimation;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class IchorCrystallizerTile extends AnimatedTile implements ITickableTileEntity, IAnimatable, IAnimationListener, ISidedInventory {

    private final Map<Direction, LazyOptional<IItemHandler>> itemHandlers = new HashMap<>();
    public long frames = 0;
    public boolean isCrafting;
    public ItemStack reagentItem = ItemStack.EMPTY;
    public ItemStack baseMaterial = ItemStack.EMPTY;
    public ItemStack oldBaseMat = ItemStack.EMPTY;
    public ItemEntity entity;
    private int craftingLength = 154;
    private NonNullList<ItemStack> items = NonNullList.withSize(10, ItemStack.EMPTY);

    public IchorCrystallizerTile() {
        super(BlockRegistry.ICHOR_CRYSTALLIZER_TILE);
        ImmutableList.of(Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST).forEach(this::addItemHandler);
        addItemHandler(null);
//        counter = 1;
    }

    @Override
    public void tick() {
        if(level.isClientSide)
            return;
        if(isCrafting) {
            if(this.getRecipe(reagentItem, baseMaterial, null) == null)
                this.isCrafting = false;
            counter += 1;
        }
        if(counter == 70) {
            IIchoricRecipe recipe = this.getRecipe(reagentItem, baseMaterial, null);
            oldBaseMat = this.baseMaterial.copy();
            this.baseMaterial = recipe.getResult(reagentItem, oldBaseMat, this).copy();
            updateBlock();
        }
        if(counter >= craftingLength) {
            if (this.isCrafting) {
                IIchoricRecipe recipe = this.getRecipe(reagentItem, baseMaterial, null);
                if (recipe != null) {
                    this.reagentItem = recipe.getResult(this.baseMaterial, this.reagentItem, this);
                    this.clearContent();
                }
                this.isCrafting = false;
            }
            counter = 1;
        }
        updateBlock();
    }

    public IIchoricRecipe getRecipe(ItemStack reagent, ItemStack base, @Nullable PlayerEntity playerEntity){
        return RigoranthusEmortisRebornAPI.getInstance().getIchorCrystallizerRecipes(level).stream().filter(r -> r.isMatch(base, reagent, this, playerEntity)).findFirst().orElse(null);
    }

    public boolean craftingPossible(ItemStack stack, ItemStack reagent, PlayerEntity playerEntity) {
        if(isCrafting || stack.isEmpty())
            return false;
        IIchoricRecipe recipe = this.getRecipe(stack, reagent, playerEntity);
        return recipe != null && (!recipe.consumesDominion() || (recipe.consumesDominion() && DominionUtil.hasDominionNearby(worldPosition, level, 5, recipe.dominionCost())));
    }

    public void updateBlock() {
        if(counter == 0 || !isCrafting)
            counter = 1;
        BlockState state = level.getBlockState(worldPosition);
        level.sendBlockUpdated(worldPosition, state, state, 2);
    }

    public boolean attemptCraft(ItemStack reagent, ItemStack base, @Nullable PlayerEntity playerEntity) {
        if(isCrafting)
            return false;
        if (!craftingPossible(reagent, base, playerEntity)) {
            return false;
        }
        IIchoricRecipe recipe = this.getRecipe(reagent, base,  playerEntity);
        if(recipe == null)
            return false;
        int dominionCost = recipe.dominionCost();
        BlockPos jar = DominionUtil.takeDominionNearbyWithParticles(worldPosition, level, 5, dominionCost);

        if(jar != null){
            isCrafting = true;
            Networking.sendToNearby(level, worldPosition, new PacketOneShotAnimation(worldPosition ));
            updateBlock();
            return true;
        }
        playerEntity.sendMessage(new TranslationTextComponent("rigoranthusemortisreborn.ichor_crystallizer.no_dominion"), Util.NIL_UUID);
        return false;
    }

    @Override
    public void registerControllers(AnimationData animationData) { animationData.addAnimationController(new AnimationController(this, "controller", 1, this::idlePredicate)); }
    AnimationFactory manager = new AnimationFactory(this);
    @Override public AnimationFactory getFactory() { return manager; }
    private <E extends TileEntity & IAnimatable > PlayState idlePredicate(AnimationEvent<E> event) { return PlayState.CONTINUE; }
    @Override public int getContainerSize() { return 1; }
    @Override public boolean isEmpty() { return reagentItem.isEmpty() && baseMaterial.isEmpty(); }

    @Override
    public ItemStack getItem(int index) { return index == 0 ? reagentItem : index == 1 ? baseMaterial : ItemStack.EMPTY; }
    public Item getItemInSlot(int slot) { return this.items.get(slot).getItem(); }

    @Override
    public ItemStack removeItem(int index, int count) {
        if(index == 0){
            ItemStack stack = reagentItem.copy();
            stack.setCount(count);
            reagentItem.shrink(count);
            updateBlock();
            return stack;
        }else if(index == 1){
            ItemStack stack = baseMaterial.copy();
            stack.shrink(count);
            baseMaterial.shrink(count);
            updateBlock();
            return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack stack = reagentItem.copy();
        reagentItem.setCount(0);
        return stack;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        if(isCrafting)
            return;
        if(index == 0)
            reagentItem = stack;
        if(index == 1)
            baseMaterial = stack;
        updateBlock();
        attemptCraft(this.reagentItem, this.baseMaterial, null);
    }

    @Override public int[] getSlotsForFace(Direction side) { return side == Direction.UP ? new int[]{0} : side != Direction.DOWN ? new int[]{1} : new int[0]; }
    @Override public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) { return false; }
    @Override public int getMaxStackSize() { return 1; }
    @Override public boolean stillValid(PlayerEntity player) { return true; }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        if(isCrafting)
            return false;
        if(index == 0 && reagentItem.isEmpty() && direction == Direction.UP){
            return canCraft(itemStackIn, baseMaterial);
        }else if(index == 1 && direction != Direction.UP && direction != Direction.DOWN && baseMaterial.isEmpty()){
            return baseMaterial.getItem() != null;
        }
        return false;
    }

    public boolean canCraft(ItemStack reagent, ItemStack base) {
        if(reagent.isEmpty() || base.isEmpty())
            return false;
        IIchoricRecipe recipe = this.getRecipe(reagentItem.getStack(), baseMaterial.getStack(), null);
        if(recipe == null)
            return false;
        int dominionCost = recipe.dominionCost();
        AtomicBoolean valid = new AtomicBoolean(false);
        BlockPos.betweenClosedStream(this.getBlockPos().offset(5, -3, 5), this.getBlockPos().offset(-5, 3, -5)).forEach(blockPos -> {
            if(!valid.get() && level.getBlockEntity(blockPos) instanceof DominionJarTile && ((DominionJarTile) level.getBlockEntity(blockPos)).getCurrentDominion() >= dominionCost) {
                valid.set(true);
            }
        });
        return valid.get();
    }

    @Override
    public void clearContent() {
        reagentItem = ItemStack.EMPTY;
        baseMaterial = ItemStack.EMPTY;
        oldBaseMat = ItemStack.EMPTY;
    }

    private void addItemHandler(@Nullable Direction side) { itemHandlers.put(side, LazyOptional.of(() -> new SidedInvWrapper(this, side))); }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        reagentItem = ItemStack.of((CompoundNBT)compound.get("itemStack"));
        baseMaterial = ItemStack.of((CompoundNBT)compound.get("baseMat"));
        oldBaseMat = ItemStack.of((CompoundNBT)compound.get("oldBase"));
        isCrafting = compound.getBoolean("crafting");
        counter = compound.getInt("counter");
        super.load(state, compound);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        if(reagentItem != null) {
            CompoundNBT reagentTag = new CompoundNBT();
            reagentItem.save(reagentTag);
            compound.put("itemStack", reagentTag);
        }
        if(baseMaterial != null){
            CompoundNBT baseMatTag = new CompoundNBT();
            baseMaterial.save(baseMatTag);
            compound.put("baseMat", baseMatTag);
        }
        if(oldBaseMat != null){
            CompoundNBT baseMatTag = new CompoundNBT();
            oldBaseMat.save(baseMatTag);
            compound.put("oldBase", baseMatTag);
        }
        compound.putBoolean("crafting", isCrafting);
        compound.putInt("counter", counter);
        return super.save(compound);
    }

    @Override
    public void startAnimation(int arg) {
        AnimationData data = this.manager.getOrCreateAnimationData(this.hashCode());
        data.setResetSpeedInTicks(0.0);
        AnimationController controller = data.getAnimationControllers().get("controller");
        controller.markNeedsReload();
        controller.setAnimation(new AnimationBuilder().addAnimation("press", false));
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 3, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("counter", this.counter);
        tag.putBoolean("is_crafting", this.isCrafting);
        return this.save(tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(level.getBlockState(worldPosition), pkt.getTag());
    }
}