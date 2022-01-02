package com.platinumg17.rigoranthusemortisreborn.items.pacts;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PactOfServitudeItem extends Item {
    public PactOfServitudeItem(Properties properties) { super(properties); }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.pact." + RigoranthusEmortisReborn.MOD_ID + ".servitude").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip.pact." + RigoranthusEmortisReborn.MOD_ID + ".servitude2").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip.pact." + RigoranthusEmortisReborn.MOD_ID + ".servitude3").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip.pact." + RigoranthusEmortisReborn.MOD_ID + ".servitude4").setStyle(Style.EMPTY));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY));
        }
    }
}