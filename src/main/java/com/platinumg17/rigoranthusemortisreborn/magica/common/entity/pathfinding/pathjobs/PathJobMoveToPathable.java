package com.platinumg17.rigoranthusemortisreborn.magica.common.entity.pathfinding.pathjobs;

import net.minecraft.entity.LivingEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class PathJobMoveToPathable extends PathJobMoveToLocation{

    public List<BlockPos> destinations;

    /**
     * Prepares the PathJob for the path finding system.
     *
     * @param world  world the entity is in.
     * @param start  starting location.
     * @param destinations list of acceptable destinations
     * @param range  max search range.
     * @param entity the entity.
     */
    public PathJobMoveToPathable(World world, BlockPos start, List<BlockPos> destinations, int range, LivingEntity entity) {
        super(world, start, destinations.size() == 0 ? start : destinations.get(0), range, entity);
        this.destinations = destinations;
    }

    @Override
    protected Path search() {
        Path path = null;
        for(BlockPos p : destinations){
            this.destination = p;
            totalNodesVisited = 0;
            nodesOpen =  new PriorityQueue<>(500);
            nodesVisited = new HashMap<>();
            path = super.search();
            if(path.canReach())
                return path;
        }
        destination = destinations.get(0);
        return super.search();
    }
}