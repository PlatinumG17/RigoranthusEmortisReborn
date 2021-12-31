package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.GravelBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FragmentedBlock extends GravelBlock {
    public List<ITextComponent> tooltip;
    public FragmentedBlock(AbstractBlock.Properties properties) {
        super(properties);
        setRegistryName(LibBlockNames.FRAGMENTED_COBBLESTONE);
        withTooltip(new TranslationTextComponent("tooltip.block.rigoranthusemortisreborn.fragmented_cobblestone"));
    }

    public FragmentedBlock withTooltip(ITextComponent tip){
        tooltip = new ArrayList<>();
        tooltip.add(tip);
        return this;
    }
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip2, ITooltipFlag flagIn) {
        if(tooltip != null && !tooltip.isEmpty()){
            tooltip2.addAll(tooltip);
        }
    }
}