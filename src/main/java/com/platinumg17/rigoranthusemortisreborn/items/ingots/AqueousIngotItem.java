package com.platinumg17.rigoranthusemortisreborn.items.ingots;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class AqueousIngotItem extends Item {
    public AqueousIngotItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }
    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot2").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot3").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot4").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot5").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot6").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot7").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot8").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot9").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot10").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot11").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot12").setStyle(Style.EMPTY));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot_shift").setStyle(Style.EMPTY));
        }
    }

}
