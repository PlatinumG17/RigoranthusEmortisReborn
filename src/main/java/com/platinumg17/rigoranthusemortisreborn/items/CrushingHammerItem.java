package com.platinumg17.rigoranthusemortisreborn.items;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class CrushingHammerItem extends Item {
    public CrushingHammerItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if(container.hurt(1, random, null)) {
            return ItemStack.EMPTY;
        } else {
            return container;
        }
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".crushing_hammer").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".crushing_hammer2").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".crushing_hammer3").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".crushing_hammer4").setStyle(Style.EMPTY));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".crushing_hammer_shift").setStyle(Style.EMPTY));
        }
    }
}
