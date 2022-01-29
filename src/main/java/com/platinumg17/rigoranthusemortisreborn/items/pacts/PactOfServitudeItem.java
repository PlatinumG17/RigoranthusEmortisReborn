package com.platinumg17.rigoranthusemortisreborn.items.pacts;

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

public class PactOfServitudeItem extends Item {
    public PactOfServitudeItem(Properties properties) { super(properties); }

    public static IFormattableTextComponent newTip(String tip) {
        return new TranslationTextComponent("tooltip.pact.rigoranthusemortisreborn" + tip).setStyle(Style.EMPTY);
    }
    public static IFormattableTextComponent holdShift(String tip) {
        return new TranslationTextComponent("tooltip.rigoranthusemortisreborn" + tip).setStyle(Style.EMPTY);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tip.add(newTip(".servitude"));
            tip.add(newTip(".servitude2"));
            tip.add(newTip(".servitude3"));
            tip.add(newTip(".servitude4"));
        } else {
            tip.add(holdShift(".hold_shift"));
        }
    }
}