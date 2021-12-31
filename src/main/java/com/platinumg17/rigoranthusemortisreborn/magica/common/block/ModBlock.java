package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ModBlock extends Block {
    public List<ITextComponent> tooltip;
    public ModBlock(Properties properties, String registry) {
        super(properties);
        setRegistryName(registry);
    }

    public ModBlock(String registryName){
        this(defaultProperties(), registryName);
    }

    public static Block.Properties defaultProperties(){
        return Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0f, 6.0f);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, IBlockDisplayReader world, BlockPos pos, FluidState fluidState) {
        return true;
    }

    public ModBlock withTooltip(ITextComponent tip){
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