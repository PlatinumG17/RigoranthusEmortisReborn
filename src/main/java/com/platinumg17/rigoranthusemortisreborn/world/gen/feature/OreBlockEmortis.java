package com.platinumg17.rigoranthusemortisreborn.world.gen.feature;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;
import java.util.Random;

public class OreBlockEmortis extends OreBlock {
    private final int minExp, maxExp;

    public OreBlockEmortis(Properties properties) {
        this(0, 0, properties);
    }

    public OreBlockEmortis(int minExp, int maxExp, Properties properties) {
        super(properties);
        this.minExp = minExp;
        this.maxExp = maxExp;
    }

    @Override
    protected int xpOnDrop(Random random) {
        return MathHelper.nextInt(random, minExp, maxExp);
    }
}
