package com.platinumg17.rigoranthusemortisreborn.items;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForgottenRecord extends MusicDiscItem {

    private static final Map<SoundEvent, MusicDiscItem> RECORDS = new HashMap<>();
    public ForgottenRecord(int comparatorValueIn, SoundEvent soundIn, Properties builder) {
        super(comparatorValueIn, soundIn, builder);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".forgotten_record").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".forgotten_record2").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".forgotten_record3").setStyle(Style.EMPTY));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".forgotten_record_shift").setStyle(Style.EMPTY));
        }
    }

    @Nullable
//    @OnlyIn(Dist.CLIENT)
    public static MusicDiscItem getBySound(SoundEvent soundIn) {
        return RECORDS.get(soundIn);
    }
}