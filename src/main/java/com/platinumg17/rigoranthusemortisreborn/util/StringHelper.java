package com.platinumg17.rigoranthusemortisreborn.util;

import com.google.common.collect.Lists;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;

import net.minecraft.util.text.*;

import java.util.List;

public class StringHelper {

    public static List<ITextComponent> getShiftInfoGui()
    {
        List<ITextComponent> list = Lists.newArrayList();
        list.add(new TranslationTextComponent("tooltip.rigoranthusemortisreborn.gui_close"));
        IFormattableTextComponent tooltip1 = new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".gui_hold_shift");
        IFormattableTextComponent shift = new StringTextComponent("[Shift]");
        IFormattableTextComponent tooltip2 = new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".gui_shift_more_options");
        tooltip1.withStyle(TextFormatting.GRAY);
        shift.withStyle(TextFormatting.GOLD, TextFormatting.ITALIC);
        tooltip2.withStyle(TextFormatting.GRAY);
        list.add(tooltip1.append(shift).append(tooltip2));
        return list;
    }

    public static ITextComponent getShiftInfoText()
    {
        IFormattableTextComponent tooltip1 = new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold");
        IFormattableTextComponent shift = new StringTextComponent("[Shift]");
        IFormattableTextComponent tooltip2 = new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".for_details");
        tooltip1.withStyle(TextFormatting.GRAY);
        shift.withStyle(TextFormatting.GOLD, TextFormatting.ITALIC);
        tooltip2.withStyle(TextFormatting.GRAY);
        return tooltip1.append(shift).append(tooltip2);
    }
}