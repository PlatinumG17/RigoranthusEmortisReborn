package com.platinumg17.rigoranthusemortisreborn.util.climbing;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class PathingTarget {
    public final BlockPos pos;
    public final Direction side;

    public PathingTarget(BlockPos pos, Direction side) {
        this.pos = pos;
        this.side = side;
    }
}