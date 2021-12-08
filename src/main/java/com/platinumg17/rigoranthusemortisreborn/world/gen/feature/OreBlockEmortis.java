package com.platinumg17.rigoranthusemortisreborn.world.gen.feature;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class OreBlockEmortis extends OreBlock {
    private final int minExp, maxExp;

    public OreBlockEmortis() {
        super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(10f, 12f).harvestTool(ToolType.PICKAXE).harvestLevel(3).requiresCorrectToolForDrops().sound(SoundType.STONE));
        this.minExp = 8;
        this.maxExp = 15;
    }

    @Override
    protected int xpOnDrop(Random random) {
        return MathHelper.nextInt(random, minExp, maxExp);
    }
}
