package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.client.ITooltipProvider;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.ritual.AbstractRitual;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellContext;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellStats;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ILightable;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.DominionUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.GlowParticleData;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.RitualVesselBlock;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RitualTile extends AnimatedTile implements ITickableTileEntity, ITooltipProvider, IAnimatable, ILightable, IInventory {
    private final LazyOptional<IItemHandler> itemHandler = LazyOptional.of(() -> new InvWrapper(this));
    public float frames;
    public ItemEntity entity;
    public ItemStack stack;

    public AbstractRitual ritual;
    AnimationFactory manager = new AnimationFactory(this);
    public boolean isDecorative;
    int red;
    int blue;
    int green;
    public boolean isOff;

    public RitualTile() {
        super(BlockRegistry.RITUAL_TILE);
    }

    public void makeParticle(ParticleColor centerColor, ParticleColor outerColor, int intensity){
        World world = getLevel();
        BlockPos pos = getBlockPos();
        Random rand = world.random;
        double xzOffset = 0.25;
        for(int i =0; i < intensity; i++){
            world.addParticle(
                    GlowParticleData.createData(centerColor),
                    pos.getX() +0.5 + ParticleUtil.inRange(-xzOffset/2, xzOffset/2)  , pos.getY() + 1 + ParticleUtil.inRange(-0.05, 0.2) , pos.getZ() +0.5 + ParticleUtil.inRange(-xzOffset/2, xzOffset/2),
                    0, ParticleUtil.inRange(0.0, 0.05f),0);
        }
        for(int i =0; i < intensity; i++){
            world.addParticle(
                    GlowParticleData.createData(outerColor),
                    pos.getX() +0.5 + ParticleUtil.inRange(-xzOffset, xzOffset)  , pos.getY() +1 + ParticleUtil.inRange(0, 0.7) , pos.getZ() +0.5 + ParticleUtil.inRange(-xzOffset, xzOffset),
                    0, ParticleUtil.inRange(0.0, 0.05f),0);
        }
    }

    @Override
    public void tick() {
        if(isDecorative && level.isClientSide){
            makeParticle(ParticleColor.makeRandomColor(red, green, blue, level.random), ParticleColor.makeRandomColor(red, green, blue, level.random), 50);
            return;
        }
        if(level.isClientSide && ritual != null){
            makeParticle(ritual.getCenterColor(), ritual.getOuterColor(), ritual.getParticleIntensity());
        }
        if(isOff)
            return;
        if(ritual != null){
            if(ritual.getContext().isDone){
                ritual.onEnd();
                ritual = null;
                getLevel().playSound(null, getBlockPos(), SoundEvents.FIRE_EXTINGUISH, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                getLevel().setBlock(getBlockPos(), getLevel().getBlockState(getBlockPos()).setValue(RitualVesselBlock.LIT, false), 3);
                return;
            }
            if(!ritual.isRunning() && !level.isClientSide){
                level.getEntitiesOfClass(ItemEntity.class, new AxisAlignedBB(getBlockPos()).inflate(1)).forEach(i ->{
                    if(ritual.canConsumeItem(i.getItem())){
                        ritual.onItemConsumed(i.getItem());
                        ParticleUtil.spawnPoof((ServerWorld) level, i.blockPosition());
                    }
                });
            }
            if(ritual.consumesDominion() && ritual.needsDominionNow()){
                int cost = ritual.getDominionCost();
                if(DominionUtil.takeDominionNearbyWithParticles(getBlockPos(), getLevel(), 6, cost) != null){
                    ritual.setNeedsDominion(false);
                }else{
                    return;
                }
            }
            ritual.tryTick();
        }
    }
    public boolean isRitualDone(){
        return ritual != null && ritual.getContext().isDone;
    }

    public boolean canRitualStart(){
        return ritual.canStart();
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 3, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(level.getBlockState(worldPosition),pkt.getTag());
    }

    public void startRitual(){
        if(ritual == null || !ritual.canStart() || ritual.isRunning())
            return;
        getLevel().playSound(null, getBlockPos(), SoundEvents.ILLUSIONER_CAST_SPELL, SoundCategory.NEUTRAL, 1.0f, 1.0f);
        ritual.onStart();
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        stack = ItemStack.of((CompoundNBT)tag.get("itemStack"));
        super.load(state, tag);
        String ritualID = tag.getString("ritualID");
        if(!ritualID.isEmpty()){
            ritual = RigoranthusEmortisRebornAPI.getInstance().getRitual(ritualID);
            if(ritual != null) {
                ritual.read(tag);
                ritual.tile = this;
            }
        }else{
            ritual = null;
        }
        this.red = tag.getInt("red");
        this.red = red > 0 ? red : 255;
        this.green = tag.getInt("green");
        green = this.green > 0 ? green : 125;
        this.blue = tag.getInt("blue");
        blue = this.blue > 0 ? blue : 255;
        isDecorative = tag.getBoolean("decorative");
        isOff = tag.getBoolean("off");
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        if(stack != null) {
            CompoundNBT item = new CompoundNBT();
            stack.save(item);
            tag.put("itemStack", item);
        }
        if(ritual != null){
            tag.putString("ritualID", ritual.getID());
            ritual.write(tag);
        }else{
            tag.remove("ritualID");
        }
        tag.putInt("red", red);
        tag.putInt("green", green);
        tag.putInt("blue", blue);
        tag.putBoolean("decorative", isDecorative);
        tag.putBoolean("off", isOff);
        return super.save(tag);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return stack == null || stack.isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return stack == null ? ItemStack.EMPTY : stack;
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack toReturn = getItem(0).copy();
        stack.shrink(1);
        updateBlock();
        return toReturn;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack toReturn = getItem(0).copy();
        stack.shrink(1);
        updateBlock();
        return toReturn;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack s) {
        return stack == null || stack.isEmpty();
    }

    @Override
    public void setItem(int index, ItemStack s) {
        if(stack == null || stack.isEmpty()) {
            stack = s;
            updateBlock();
        }
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

    public void setRitual(String selectedRitual) {
        this.ritual = RigoranthusEmortisRebornAPI.getInstance().getRitual(selectedRitual);
        if(ritual != null){
            this.ritual.tile = this;
            World world = getLevel();
            BlockState state = world.getBlockState(getBlockPos());
            world.setBlock(getBlockPos(), state.setValue(RitualVesselBlock.LIT, true), 3);
        }
        this.isDecorative = false;
        level.playSound(null, getBlockPos(), SoundEvents.FLINTANDSTEEL_USE, SoundCategory.NEUTRAL, 1.0f, 1.0f);
    }

    @Override
    public List<String> getTooltip() {
        List<String> tooltips = new ArrayList<>();
        if(ritual != null){
            tooltips.add(ritual.getName());
            if(isOff) {
                tooltips.add(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.turned_off").getString());
                return tooltips;
            }
            if(!ritual.isRunning()){
                if(!ritual.canStart()){
                    tooltips.add(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.conditions_unmet").getString());
                }else
                    tooltips.add(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.waiting").getString());
            }else{

                tooltips.add(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.running").getString());
            }
            if(ritual.getConsumedItems().size() != 0) {
                tooltips.add(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.consumed").getString());
                for (ItemStack i : ritual.getConsumedItems()) {
                    tooltips.add(i.getHoverName().getString());
                }
            }
            if(ritual.needsDominionNow())
                tooltips.add(new TranslationTextComponent("rigoranthusemortisreborn.ritual.need_dominion").getString());
        }
        return tooltips;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "idle", 0, this::idlePredicate));
        animationData.addAnimationController(new AnimationController(this, "gripping", 0, this::gripPredicate));
    }
    private <P extends IAnimatable> PlayState gripPredicate(AnimationEvent<P> pAnimationEvent) {
        if(stack != null)
            pAnimationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("grip", false));
        return PlayState.CONTINUE;
    }
    private <P extends IAnimatable> PlayState idlePredicate(AnimationEvent<P> pAnimationEvent) {
        pAnimationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("gem_float", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return manager;
    }

    @Override
    public void onLight(RayTraceResult rayTraceResult, World world, LivingEntity shooter, SpellStats stats, SpellContext spellContext) {
        this.red = spellContext.colors.r;
        this.green = spellContext.colors.g;
        this.blue = spellContext.colors.b;
        this.isDecorative = true;
        BlockState state = world.getBlockState(getBlockPos());
        world.setBlock(getBlockPos(), state.setValue(RitualVesselBlock.LIT, true), 3);
    }
}