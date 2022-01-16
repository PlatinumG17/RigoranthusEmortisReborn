package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.AbstractDominionTile;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.DominionUtil;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.IchorUtil;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketOneShotAnimation;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class IchorCrystallizerTile extends AbstractDominionTile implements IInventory, IAnimatable, IAnimationListener {
    private final LazyOptional<IItemHandler> itemHandler = LazyOptional.of(() -> new InvWrapper(this));
    private final AnimationFactory animationFactory = new AnimationFactory(this);
    public String engageController = "engageController";
    public String engageAnim = "press";
    public ItemStack stack = ItemStack.EMPTY;
    public ItemEntity entity;
    public boolean draining = false;
    public int timeAnimating = 0;
    public int timeWaiting = 0;

    public IchorCrystallizerTile() {
        super(BlockRegistry.ICHOR_CRYSTALLIZER_TILE);
    }

    @Override
    public int getTransferRate() {
        return 0;
    }

    @Override
    public void tick() {
        if(level.isClientSide)
            return;

        if (draining) {
            timeAnimating++;
            if(timeAnimating >= 100) {
                this.draining = false;
                update();
            }
            return;
        }
        if (this.stack.isEmpty() && this.level.getGameTime() % 100 == 0) {
            if(isDrainingIchorPossible()) {
                if(attemptDrainIchor())
                    this.addDominion(100);
                update();
            } else if (isDrainingDominionPossible()) {
                if(attemptDrainDominion())
                    this.addDominion(200);
                update();
            }
        }
        else if (this.level.getGameTime() % 100 == 0) {
            if (draining) {
                draining = false;
            }
        }
        if (this.getCurrentDominion() >= 1000 && (stack == null || stack.isEmpty())) {
//            Item foundItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.CRYSTALLIZER_ITEM.get()));
//            if (foundItem == null) {
//                System.out.println("NULL ICHOR CRYSTALLIZER ITEM.");
//                foundItem = MagicItemsRegistry.dominionGem;
//            }
            this.stack = new ItemStack(MagicItemsRegistry.dominionGem);
            this.setDominion(0);
        }
    }
    @Override
    public boolean update() {
        if(this.worldPosition != null && this.level != null){
            BlockState state = level.getBlockState(worldPosition);
            level.sendBlockUpdated(worldPosition, state, state, 2);
            return true;
        }
        return false;
    }

    public boolean isDrainingDominionPossible() {
        if(draining)
            return false;
        return DominionUtil.hasDominionNearby(worldPosition.below(), level, 0, 200);
    }

    public boolean isDrainingIchorPossible() {
        if(draining)
            return false;
        return IchorUtil.hasIchorNearby(worldPosition.below(), level, 0, 200);
    }

    public boolean attemptDrainDominion() {
        BlockPos jar = DominionUtil.takeDominionNearby(worldPosition.below(), level, 0, 200);
        if (jar != null) {
            if (!draining) {
                draining = true;
                Networking.sendToNearby(level, worldPosition, new PacketOneShotAnimation(worldPosition, NeedleAnimations.NEEDLE_ENGAGE.ordinal()));
            }
            update();
            return true;
        }
        return false;
    }

    public boolean attemptDrainIchor() {
        BlockPos jar = IchorUtil.takeIchorNearby(worldPosition.below(), level, 0, 200);
        if (jar != null) {
            if (!draining) {
                draining = true;
                Networking.sendToNearby(level, worldPosition, new PacketOneShotAnimation(worldPosition, NeedleAnimations.NEEDLE_ENGAGE.ordinal()));
            }
            update();
            return true;
        }
        return false;
    }

    @Override public int getMaxDominion() { return 1000; }
    @Override public int getContainerSize() { return 1; }
    @Override public boolean isEmpty() { return this.stack == null || this.stack.isEmpty(); }
    @Override public ItemStack getItem(int index) { return stack; }
    @Override public void setItem(int index, ItemStack stack) { this.stack = stack; }
    @Override public boolean stillValid(PlayerEntity player) { return true; }
    @Override public void clearContent() { this.stack = ItemStack.EMPTY; }

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

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, final @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) { return itemHandler.cast(); }
        return super.getCapability(cap, side);
    }

    @Override
    protected void invalidateCaps() {
        itemHandler.invalidate();
        super.invalidateCaps();
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, engageController, 1, this::engagePredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animationFactory;
    }
    private <E extends TileEntity & IAnimatable > PlayState engagePredicate(AnimationEvent<E> event) { return PlayState.CONTINUE; }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        if(stack != null) {
            CompoundNBT reagentTag = new CompoundNBT();
            stack.save(reagentTag);
            tag.put("itemStack", reagentTag);
        }
        tag.putInt("counter", this.timeAnimating);
        tag.putInt("waiting", this.timeWaiting);
        tag.putBoolean("draining", draining);
        return super.save(tag);
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        stack = ItemStack.of((CompoundNBT)compound.get("itemStack"));
        timeAnimating = compound.getInt("counter");
        timeWaiting = compound.getInt("waiting");
        draining = compound.getBoolean("draining");
        super.load(state, compound);
    }

    @Override
    public void startAnimation(int arg) {
        AnimationData engageData = this.animationFactory.getOrCreateAnimationData(this.hashCode());
        AnimationController engageCont = engageData.getAnimationControllers().get(engageController);
        try{
            if (arg == NeedleAnimations.NEEDLE_ENGAGE.ordinal()) {
                engageCont.markNeedsReload();
                engageCont.setAnimation(new AnimationBuilder().addAnimation(engageAnim, false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void onAnimationSync(int id, int state) {
//        final AnimationController<?> controllerE = GeckoLibUtil.getControllerForID(this.animationFactory, id, engageController);
//        final AnimationController<?> controllerR = GeckoLibUtil.getControllerForID(this.animationFactory, id, retractController);
//        if (draining) {
//            controllerE.markNeedsReload();
//            controllerE.setAnimation(new AnimationBuilder().addAnimation(engageAnim, false));
//        } else {
//            controllerR.markNeedsReload();
//            controllerR.setAnimation(new AnimationBuilder().addAnimation(retractAnim, false));
//        }
//    }

    public enum NeedleAnimations{ NEEDLE_ENGAGE, NEEDLE_RETRACT }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 3, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tag = new CompoundNBT();
        if(stack != null) {
            CompoundNBT reagentTag = new CompoundNBT();
            stack.save(reagentTag);
            tag.put("itemStack", reagentTag);
        }
        tag.putInt("counter", this.timeAnimating);
        tag.putInt("waiting", this.timeWaiting);
        tag.putBoolean("draining", this.draining);
        return this.save(tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(level.getBlockState(worldPosition), pkt.getTag());
    }
}