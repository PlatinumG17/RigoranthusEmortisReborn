package com.platinumg17.rigoranthusemortisreborn.magica.client.gui;

//import com.mojang.blaze3d.matrix.MatrixStack;
//import com.platinumg17.rigoranthusemortisreborn.magica.common.items.ItemScroll;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.AbstractGui;
//import net.minecraft.entity.item.ItemFrameEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.CompoundNBT;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.platinumg17.rigoranthusemortisreborn.magica.common.items.ItemScroll.ITEM_PREFIX;
//
//public class GuiScrollHUD extends AbstractGui {
//
//    private static final Minecraft minecraft = Minecraft.getInstance();
//
//    public void drawHUD(MatrixStack matrixStack, ItemFrameEntity entity){
//        if(entity.getItem().getItem() instanceof ItemScroll){
//            ItemStack stack = entity.getItem();
//            CompoundNBT tag = stack.getTag();
//            if(tag == null)
//                return;
//            List<ItemStack> stacks = new ArrayList<>();
//            for(String s : tag.getAllKeys()){
//                if(s.contains(ITEM_PREFIX)){
//                    stacks.add(ItemStack.of(tag.getCompound(s)));
//                }
//            }
//            int offsetLeft = 5;
//            fill(matrixStack, offsetLeft, 50, 100+ offsetLeft, 0, 300000);
//            int counter = 0;
//            for(ItemStack s : stacks){
//                minecraft.font.drawShadow(matrixStack, s.getHoverName().getString(), offsetLeft, 5f + 10 * counter, 0xFFFFFF);
//                counter++;
//            }
//        }
//    }
//}