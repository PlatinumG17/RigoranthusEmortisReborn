package com.platinumg17.rigoranthusemortisreborn.items.ingots;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit.IngotProp;

public class InfernalIngotItem extends Item {
    public InfernalIngotItem() {
        super(IngotProp);
        setRegistryName("infernal_netherite_ingot");
    }

    public static IFormattableTextComponent newTip(String tip) {
        return new TranslationTextComponent("tooltip.rigoranthusemortisreborn" + tip).setStyle(Style.EMPTY);
    }

    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tip, flagIn);
        if (Screen.hasShiftDown()) {
            tip.add(newTip(".infernal_ingot"));
            tip.add(newTip(".infernal_ingot2"));
            tip.add(newTip(".infernal_ingot3"));
            tip.add(newTip(".infernal_ingot4"));
            tip.add(newTip(".infernal_ingot5"));
            tip.add(newTip(".infernal_ingot6"));
            tip.add(newTip(".infernal_ingot7"));
            tip.add(newTip(".infernal_ingot8"));
            tip.add(newTip(".infernal_ingot9"));
            tip.add(newTip(".infernal_ingot10"));
            tip.add(newTip(".infernal_ingot11"));
            tip.add(newTip(".infernal_ingot12"));
            tip.add(newTip(".infernal_ingot13"));
            tip.add(newTip(".infernal_ingot14"));
            tip.add(newTip(".infernal_ingot15"));
            tip.add(newTip(".infernal_ingot16"));
            tip.add(newTip(".infernal_ingot17"));
            tip.add(newTip(".infernal_ingot18"));
        } else {
            tip.add(newTip(".hold_shift"));
        }
    }
}