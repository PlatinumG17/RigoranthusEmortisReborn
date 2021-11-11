package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.ai;

import com.platinumg17.rigoranthusemortisreborn.canis.common.util.EntityUtil;
import net.minecraft.block.material.Material;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class FindWaterGoal extends Goal {

    private final CreatureEntity creature;
    private final PathNavigator navigator;
    private final World world;

    private final int waterSearchRange = 12;
    private final int safeSearchRange = 6;

    @Nullable
    private BlockPos waterPos;
    private int timeToRecalcPath;


    public FindWaterGoal(CreatureEntity creatureIn) {
        this.creature = creatureIn;
        this.navigator = creatureIn.getNavigation();
        this.world = creatureIn.level;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!this.creature.isOnGround() || (this.creature.tickCount % 5) != 0) {
            return false;
        }
        if (this.creature.fireImmune()) {
            return false;
        }
        boolean isInFire = this.isInDangerSpot(this.creature);
        boolean isOnFire = this.creature.isOnFire();
        if (!isInFire && !isOnFire) {
            return false;
        }
        BlockPos entityPos = this.creature.blockPosition();

        for (BlockPos pos : BlockPos.betweenClosed(entityPos.offset(-this.waterSearchRange, -4, -this.waterSearchRange), entityPos.offset(this.waterSearchRange, 4, this.waterSearchRange))) {
            if (this.getBlockType(pos) == BlockType.WATER) {
                this.waterPos = pos;
                break;
            }
        }
        boolean waterBlockNearBy = this.waterPos != null;
        return waterBlockNearBy || isInFire;
    }

    @Override
    public boolean canContinueToUse() {
        // If there is some water nearby
        if (this.waterPos != null) {
            // Check it is still a water block
            BlockType safety = this.getBlockType(this.waterPos);
            if (safety != BlockType.WATER) {
                return false;
            }
            // If entity is burning continue
            if (this.creature.isOnFire()) {
                return true;
            }
        }
        return this.isInDangerSpot(this.creature);
    }


    @Override
    public void start() {
        this.timeToRecalcPath = 0;
    }

    @Override
    public void tick() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            BlockPos targetPos = null;

            if (this.waterPos != null) {
                targetPos = this.waterPos;
            } else if (!this.creature.isPathFinding()) {
                BlockPos.Mutable mutablePos = new BlockPos.Mutable();
                for (int i = 0; i < 10; ++i) {
                    int j = EntityUtil.getRandomNumber(this.creature, -this.safeSearchRange, this.safeSearchRange);
                    int k = EntityUtil.getRandomNumber(this.creature, -4, 4);
                    int l = EntityUtil.getRandomNumber(this.creature, -this.safeSearchRange, this.safeSearchRange);

                    mutablePos.set(this.creature.getX() + j, this.creature.getY() + k, this.creature.getZ() + l);
                    boolean flag = this.getBlockType(mutablePos).isSafe();
                    if (flag) {
                        targetPos = mutablePos;
                        break;
                    }
                }
            }
            if (targetPos != null) {
                this.navigator.moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 1.2D);
            }
        }
    }

    @Override
    public void stop() {
        this.navigator.stop();
        this.waterPos = null;
    }

    /**
     *
     * @param entityIn The entity
     * @return
     */
    public boolean isInDangerSpot(Entity entityIn) {
        AxisAlignedBB bb = entityIn.getBoundingBox();
        int minX = MathHelper.floor(bb.minX);
        int minY = MathHelper.floor(bb.minY);
        int minZ = MathHelper.floor(bb.minZ);

        int maxX = MathHelper.ceil(bb.maxX);
        int maxY = MathHelper.ceil(bb.maxY);
        int maxZ = MathHelper.ceil(bb.maxZ);

        for (BlockPos pos : BlockPos.betweenClosed(minX, minY, minZ, maxX, maxY, maxZ)) {
            BlockType safety = this.getBlockType(pos);
            if (safety == BlockType.FIRE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returning {@link BlockType#FIRE} indicates the entity is actively in danger
     * Returning {@link BlockType#WATER} indicates the entity is on fire but not in source
     * Returning {@link BlockType#SAFE} indicates the entity is fine
     *
     * @param posIn
     * @return
     */
    public BlockType getBlockType(BlockPos posIn) {
        // If the block is fire or lava
        Material material = this.world.getBlockState(posIn).getMaterial();
        if (material == Material.FIRE || material == Material.LAVA) {
            return BlockType.FIRE;
        }
        // If it is water
        if (this.world.getFluidState(posIn).is(FluidTags.WATER)) {
            return BlockType.WATER;
        }
        // If it is raining and is not fire or lava
        if (this.world.isRainingAt(posIn)) {
            return BlockType.WATER;
        }
        // Otherwise the block is ok to go to
        return BlockType.SAFE;
    }

    public static enum BlockType {
        SAFE,
        FIRE,
        WATER;
        public boolean isSafe() {
            return this == SAFE || this == WATER;
        }
    }
}