package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.ai;

import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.EntityUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.EnumMode;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.IThrowableItem;

import java.util.EnumSet;

public class CanisFollowMasterGoal extends Goal {

    private final CanisEntity canis;
    private final PathNavigator navigator;
    private final World world;
    private final double followSpeed;
    private final float stopDist; // If closer than stopDist stop moving towards owner
    private final float startDist; // If further than startDist moving towards owner

    private LivingEntity owner;
    private int timeToRecalcPath;
    private float oldWaterCost;

    public CanisFollowMasterGoal(CanisEntity canisIn, double speedIn, float minDistIn, float maxDistIn) {
        this.canis = canisIn;
        this.world = canisIn.level;
        this.followSpeed = speedIn;
        this.navigator = canisIn.getNavigation();
        this.startDist = minDistIn;
        this.stopDist = maxDistIn;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity owner = this.canis.getOwner();
        if (owner == null) {
            return false;
        } else if (this.canis.getMode() == EnumMode.PATROL) {
            return false;
        } else if (owner.isSpectator()) {
            return false;
        } else if (this.canis.isInSittingPose()) {
            return false;
        } else if (!this.canis.hasBone() && this.canis.distanceToSqr(owner) < this.getMinStartDistanceSq()) {
            return false;
        } else {
            this.owner = owner;
            return true;
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.navigator.isDone()) {
            return false;
        } else if (this.canis.isInSittingPose()) {
            return false;
        } else {
            return this.canis.distanceToSqr(this.owner) > this.stopDist * this.stopDist;
        }
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.canis.getPathfindingMalus(PathNodeType.WATER);
        this.canis.setPathfindingMalus(PathNodeType.WATER, 0.0F);
    }

    @Override
    public void stop() {
        if (this.canis.hasBone()) {
            double distanceToOwner = this.owner.distanceToSqr(this.canis);
            if (distanceToOwner <= this.stopDist * this.stopDist) {
                IThrowableItem throwableItem = this.canis.getThrowableItem();
                ItemStack fetchItem = throwableItem != null ? throwableItem.getReturnStack(this.canis.getBoneVariant()) : this.canis.getBoneVariant();

                this.canis.spawnAtLocation(fetchItem, 0.0F);
                this.canis.setBoneVariant(ItemStack.EMPTY);
            }
        }
        this.owner = null;
        this.navigator.stop();
        this.canis.setPathfindingMalus(PathNodeType.WATER, this.oldWaterCost);
    }

    @Override
    public void tick() {
        this.canis.getLookControl().setLookAt(this.owner, 10.0F, this.canis.getMaxHeadXRot());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!this.canis.isLeashed() && !this.canis.isPassenger()) { // Is not leashed and is not a passenger
                if (this.canis.distanceToSqr(this.owner) >= 144.0D) { // Further than 12 blocks away teleport
                    EntityUtil.tryToTeleportNearEntity(this.canis, this.navigator, this.owner, 4);
                } else {
                    this.navigator.moveTo(this.owner, this.followSpeed);
                }
            }
        }
    }

    public float getMinStartDistanceSq() {
        if (this.canis.isMode(EnumMode.GUARD)) {
            return 4F;
        }
        return this.startDist * this.startDist;
    }
}