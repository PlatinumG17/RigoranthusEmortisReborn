package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.GravelBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FragmentedBlock extends GravelBlock {
    public List<ITextComponent> tooltip;
    public FragmentedBlock(Properties properties, String registry) {
        super(properties);
        setRegistryName(registry);
    }

    public FragmentedBlock(String registryName){
        this(defaultProperties(), registryName);
    }

    public static AbstractBlock.Properties defaultProperties() {
        return AbstractBlock.Properties.of(Material.SAND, MaterialColor.STONE).strength(0.8f, 0.8f)
                .harvestTool(ToolType.SHOVEL).harvestLevel(0).sound(SoundType.GRAVEL);
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