package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.item.IScribeable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class ItemScroll extends ModItem implements IScribeable {

    public ItemScroll(String reg) {
        super(reg);
    }

    public ItemScroll(Properties properties, String reg) {
        super(properties, reg);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(!stack.hasTag())
            stack.setTag(new CompoundNBT());
    }

    public abstract SortPref getSortPref(ItemStack stackToStore, CompoundNBT scrollTag, IItemHandler inventory);

    public enum SortPref {
        INVALID,
        LOW,
        HIGH,
        HIGHEST
    }

    public static String ITEM_PREFIX = "item_";

    public List<ItemStack> getItems(ItemStack stack){
        CompoundNBT tag = stack.getTag();
        List<ItemStack> stacks = new ArrayList<>();
        if(tag == null)
            return stacks;

        for(String s : tag.getAllKeys()){
            if(s.contains(ITEM_PREFIX)){
                stacks.add(ItemStack.of(tag.getCompound(s)));
            }
        }
        return stacks;
    }

    public static boolean addItem(ItemStack itemToAdd, CompoundNBT tag){
        CompoundNBT itemTag = new CompoundNBT();
        itemToAdd.save(itemTag);
        tag.put(getItemKey(itemToAdd), itemTag);
        return true;
    }

    public static boolean removeItem(ItemStack itemToRemove, CompoundNBT tag){
        tag.remove(getItemKey(itemToRemove));
        return true;
    }

    public static boolean containsItem(ItemStack stack, CompoundNBT tag){
        return tag != null && tag.contains(getItemKey(stack));
    }

    public static String getItemKey(ItemStack stack){
        return ITEM_PREFIX + stack.getItem().getRegistryName().toString();
    }

    @Override
    public boolean onScribe(World world, BlockPos pos, PlayerEntity player, Hand handIn, ItemStack thisStack) {
        return ItemScroll.scribe(world, pos, player, handIn, thisStack);
    }

    public static boolean scribe(World world, BlockPos pos, PlayerEntity player, Hand handIn, ItemStack thisStack){
        ItemStack stackToWrite = player.getItemInHand(handIn);
        CompoundNBT tag = thisStack.getTag();
        if(stackToWrite == ItemStack.EMPTY || tag == null)
            return false;

        if(containsItem(stackToWrite, tag)) {
            PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.scribe.item_removed"));
            return removeItem(stackToWrite, tag);
        }
        PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.scribe.item_added"));
        return addItem(stackToWrite, tag);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip2, ITooltipFlag flagIn) {
        CompoundNBT tag = stack.getTag();
        if(tag == null)
            return;
        List<ItemStack> stacks = new ArrayList<>();
        for(String s : tag.getAllKeys()){
            if(s.contains(ITEM_PREFIX)){
                stacks.add(ItemStack.of(tag.getCompound(s)));
            }
        }
        for(ItemStack s : stacks){
            tooltip2.add(s.getHoverName());
        }
    }
}