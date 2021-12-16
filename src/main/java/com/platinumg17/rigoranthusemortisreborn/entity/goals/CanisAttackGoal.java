package com.platinumg17.rigoranthusemortisreborn.entity.goals;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.BlockUtil;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.FeralCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketAnimEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;

import java.util.EnumSet;

public class CanisAttackGoal extends Goal {

    protected final FeralCanisEntity mob;
    private final double speedModifier;
    private final boolean followingTargetEvenIfNotSeen;
    private Path path;
    private double pathedTargetX;
    private double pathedTargetY;
    private double pathedTargetZ;
    private int ticksUntilNextPathRecalculation;
    private int ticksUntilNextAttack;
    private final int attackInterval = 20;
    private long lastCanUseCheck;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;

    public int timeAnimating = 0;
    public boolean arrived = false;
    public boolean done = false;

    public CanisAttackGoal(FeralCanisEntity p_i1636_1_, boolean p_i1636_4_) {
        this.mob = p_i1636_1_;
        this.speedModifier = 1.2f;
        this.followingTargetEvenIfNotSeen = p_i1636_4_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean isInterruptable() {
        return true;
    }

    public boolean canUse() {
        long i = this.mob.level.getGameTime();
        if(!this.mob.canAttack())
            return false;
        if (i - this.lastCanUseCheck < 20L) {
            return false;
        } else {
            this.lastCanUseCheck = i;
            LivingEntity livingentity = this.mob.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                if (canPenalize) {
                    if (--this.ticksUntilNextPathRecalculation <= 0) {
                        this.path = this.mob.getNavigation().createPath(livingentity, 0);
                        this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                        return this.path != null;
                    } else {
                        return true;
                    }
                }
                this.path = this.mob.getNavigation().createPath(livingentity, 0);
                if (this.path != null) {
                    return true;
                } else {
                    return this.getAttackReachSqr(livingentity) >= this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                }
            }
        }
    }

    public boolean canContinueToUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if(!this.mob.canAttack())
            return false;
        if (livingentity == null || done) {
            return false;
        } else if (!livingentity.isAlive()) {
            return false;
        } else if (!this.followingTargetEvenIfNotSeen) {
            return !this.mob.getNavigation().isDone();
        } else if (!this.mob.isWithinRestriction(livingentity.blockPosition())) {
            return false;
        } else {
            return !(livingentity instanceof PlayerEntity) || !livingentity.isSpectator() && !((PlayerEntity)livingentity).isCreative();
        }
    }

    public void start() {
        this.mob.getNavigation().moveTo(this.path, this.speedModifier);
        this.mob.setAggressive(true);
        this.ticksUntilNextPathRecalculation = 0;
        this.ticksUntilNextAttack = 0;
        timeAnimating = 0;
        arrived = false;
        done = false;
    }

    public void stop() {
    }

    public void tick() {
        LivingEntity livingentity = this.mob.getTarget();
        this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
        if(arrived){
            timeAnimating++;
            if(timeAnimating == 18){
                this.attack(livingentity);
            }
            if(timeAnimating >= 26){
                this.attack(livingentity);
                this.done = true;
            }
            return;
        }

        double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
        this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
        if(BlockUtil.distanceFrom(this.mob.position, livingentity.position) <= 3){
            this.arrived = true;
            Networking.sendToNearby(mob.level, mob, new PacketAnimEntity(mob.getId(), FeralCanisEntity.Animations.BITING.ordinal()));
        }
        if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().canSee(livingentity)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
            this.pathedTargetX = livingentity.getX();
            this.pathedTargetY = livingentity.getY();
            this.pathedTargetZ = livingentity.getZ();
            this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
            if (this.canPenalize) {
                this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
                if (this.mob.getNavigation().getPath() != null) {
                    net.minecraft.pathfinding.PathPoint finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
                    if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                        failedPathFindingPenalty = 0;
                    else
                        failedPathFindingPenalty += 10;
                } else {
                    failedPathFindingPenalty += 10;
                }
            }
            if (d0 > 1024.0D) {
                this.ticksUntilNextPathRecalculation += 10;
            } else if (d0 > 256.0D) {
                this.ticksUntilNextPathRecalculation += 5;
            }

            if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
                this.ticksUntilNextPathRecalculation += 15;
            }
        }

        this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
    }

    protected void attack(LivingEntity target) {
        double d0 = 3;
        if (BlockUtil.distanceFrom(target.position, this.mob.position) <= d0 ) {
            this.ticksUntilNextAttack = 20;
            this.mob.doHurtTarget(target);
        }
    }

    protected double getAttackReachSqr(LivingEntity targetEntity) {
        return this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + targetEntity.getBbWidth();
    }
}

//public class CanisAttackGoal extends Goal {
//    private final FeralCanisEntity mob;
//
//    private final double speedModifier;
//    private final boolean followingTargetEvenIfNotSeen;
//    private Path path;
//    private double pathedTargetX;
//    private double pathedTargetY;
//    private double pathedTargetZ;
//    private int ticksUntilNextPathRecalculation;
//    private int ticksUntilNextAttack;
//    private final int attackInterval = 26;
//    private long lastCanUseCheck;
//    private int failedPathFindingPenalty = 0;
//    private boolean canPenalize = false;
//
//    public CanisAttackGoal(FeralCanisEntity canis, double speed, boolean followUnseen) {
//        this.mob = canis;
//        this.speedModifier = speed;
//        this.followingTargetEvenIfNotSeen = followUnseen;
//        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
//    }
//
//    public boolean canUse() {
//        long i = this.mob.level.getGameTime();
//        if (i - this.lastCanUseCheck < 20L) {
//            return false;
//        } else {
//            this.lastCanUseCheck = i;
//            LivingEntity livingentity = this.mob.getTarget();
//            if (livingentity == null) {
//                return false;
//            } else if (!livingentity.isAlive()) {
//                return false;
//            } else {
//                if (canPenalize) {
//                    if (--this.ticksUntilNextPathRecalculation <= 0) {
//                        this.path = this.mob.getNavigation().createPath(livingentity, 0);
//                        this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
//                        return this.path != null;
//                    } else {
//                        return true;
//                    }
//                }
//                this.path = this.mob.getNavigation().createPath(livingentity, 0);
//                if (this.path != null) {
//                    return true;
//                } else {
//                    return this.getAttackReachSqr(livingentity) >= this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
//                }
//            }
//        }
//    }
//
//    public boolean canContinueToUse() {
//        LivingEntity livingentity = this.mob.getTarget();
//        if (livingentity == null) {
//            return false;
//        } else if (!livingentity.isAlive()) {
//            return false;
//        } else if (!this.followingTargetEvenIfNotSeen) {
//            return !this.mob.getNavigation().isDone();
//        } else if (!this.mob.isWithinRestriction(livingentity.blockPosition())) {
//            return false;
//        } else {
//            return !(livingentity instanceof PlayerEntity) || !livingentity.isSpectator() && !((PlayerEntity) livingentity).isCreative();
//        }
//    }
//
//    public void start() {
//        this.mob.getNavigation().moveTo(this.path, this.speedModifier);
//        this.mob.setAggressive(true);
//        this.ticksUntilNextPathRecalculation = 0;
//        this.ticksUntilNextAttack = 0;
//    }
//
//    public void stop() {
//        LivingEntity livingentity = this.mob.getTarget();
//        if (!EntityPredicates.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
//            this.mob.setTarget((LivingEntity) null);
//        }
//
//        this.mob.setAggressive(false);
//        this.mob.getNavigation().stop();
//    }
//
//    public void tick() {
//        LivingEntity livingentity = this.mob.getTarget();
//        this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
//        double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
//        this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
//        if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().canSee(livingentity)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
//            this.pathedTargetX = livingentity.getX();
//            this.pathedTargetY = livingentity.getY();
//            this.pathedTargetZ = livingentity.getZ();
//            this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
//            if (this.canPenalize) {
//                this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
//                if (this.mob.getNavigation().getPath() != null) {
//                    net.minecraft.pathfinding.PathPoint finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
//                    if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
//                        failedPathFindingPenalty = 0;
//                    else
//                        failedPathFindingPenalty += 10;
//                } else {
//                    failedPathFindingPenalty += 10;
//                }
//            }
//            if (d0 > 1024.0D) {
//                this.ticksUntilNextPathRecalculation += 10;
//            } else if (d0 > 256.0D) {
//                this.ticksUntilNextPathRecalculation += 5;
//            }
//
//            if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
//                this.ticksUntilNextPathRecalculation += 15;
//            }
//        }
//
//        this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
//        this.checkAndPerformAttack(livingentity, d0);
//    }
//
//    protected void checkAndPerformAttack(LivingEntity target, double p_190102_2_) {
//        double d0 = this.getAttackReachSqr(target);
//        if (p_190102_2_ <= d0 && this.ticksUntilNextAttack <= 0) {
//            this.resetAttackCooldown();
//            this.mob.swing(Hand.MAIN_HAND);
//            this.mob.doHurtTarget(target);
//        }
//
//    }
//
//    protected void resetAttackCooldown() {
//        this.ticksUntilNextAttack = 26;
//    }
//
//    protected boolean isTimeToAttack() {
//        return this.ticksUntilNextAttack <= 0;
//    }
//
//    protected int getTicksUntilNextAttack() {
//        return this.ticksUntilNextAttack;
//    }
//
//    protected int getAttackInterval() {
//        return 26;
//    }
//
//    protected double getAttackReachSqr(LivingEntity attackTarget) {
//        return this.mob.getBbWidth() * 1.0F * this.mob.getBbWidth() * 1.0F + attackTarget.getBbWidth();
//    }
//}




//public class CanisAttackGoal extends Goal {
//
//    protected final FeralCanisEntity canis;
//    private final double speedModifier;
//    private final boolean followingTargetEvenIfNotSeen;
//    private Path path;
//    private double pathedTargetX;
//    private double pathedTargetY;
//    private double pathedTargetZ;
//    private int ticksUntilNextPathRecalculation;
//    private int ticksUntilNextAttack;
//    private final int attackInterval = 26;
//    private long lastCanUseCheck;
//    private int failedPathFindingPenalty = 0;
//    private boolean canPenalize = false;
//
//    public int timeAnimating = 0;
//    public boolean arrived = false;
//    public boolean done = false;
//    int animationID;
//    int animationLength = 26;
//    int attackRange;
//
//    public CanisAttackGoal(FeralCanisEntity canisEntity, boolean followUnseen, int animationID, int attackRange, double speedModifier) {
//        this.canis = canisEntity;
//        this.speedModifier = speedModifier;
//        this.followingTargetEvenIfNotSeen = followUnseen;
//        this.animationID = animationID;
//        this.attackRange = attackRange;
//        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
//    }
//
//    @Override
//    public boolean isInterruptable() {
//        return true;
//    }
//
//    public boolean canUse() {
//        long i = this.canis.level.getGameTime();
//        if (i - this.lastCanUseCheck < 20L) {
//            return false;
//        } else {
//            this.lastCanUseCheck = i;
//            LivingEntity livingentity = this.canis.getTarget();
//            if (livingentity == null) {
//                return false;
//            } else if (!livingentity.isAlive()) {
//                return false;
//            } else {
//                if (canPenalize) {
//                    if (--this.ticksUntilNextPathRecalculation <= 0) {
//                        this.path = this.canis.getNavigation().createPath(livingentity, 0);
//                        this.ticksUntilNextPathRecalculation = 4 + this.canis.getRandom().nextInt(7);
//                        return this.path != null;
//                    } else {
//                        return true;
//                    }
//                }
//                this.path = this.canis.getNavigation().createPath(livingentity, 0);
//                if (this.path != null) {
//                    return true;
//                } else {
//                    return this.getAttackReachSqr(livingentity) >= this.canis.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
//                }
//            }
//        }
//    }
//
//    public boolean canContinueToUse() {
//        LivingEntity livingentity = this.canis.getTarget();
//        if (livingentity == null || done) {
//            return false;
//        } else if (!livingentity.isAlive()) {
//            return false;
//        } else if (!this.followingTargetEvenIfNotSeen) {
//            return !this.canis.getNavigation().isDone();
//        } else if (!this.canis.isWithinRestriction(livingentity.blockPosition())) {
//            return false;
//        } else {
//            return !(livingentity instanceof PlayerEntity) || !livingentity.isSpectator() && !((PlayerEntity)livingentity).isCreative();
//        }
//    }
//
//    public void start() {
//        this.canis.getNavigation().moveTo(this.path, this.speedModifier);
//        this.canis.setAggressive(true);
//        this.ticksUntilNextPathRecalculation = 0;
//        this.ticksUntilNextAttack = 0;
//        timeAnimating = 0;
//        arrived = false;
//        done = false;
//    }
//
//    public void stop() {
//
//    }
//
//    public void arrivedTick(){
//        timeAnimating++;
//        if(timeAnimating >= animationLength){
//            if(this.canis.getTarget() != null)
//                this.attack(this.canis.getTarget());
//            this.done = true;
//        }
//    }
//
//    public void look(LivingEntity entity){
//        if(entity != null)
//            this.canis.getLookControl().setLookAt(entity, 30.0F, 30.0F);
//    }
//
//    public void onArrive(){
//        this.arrived = true;
//        Networking.sendToNearby(canis.level, canis, new PacketAnimEntity(canis.getId(), animationID));
//    }
//
//    public void tick() {
//        LivingEntity livingentity = this.canis.getTarget();
//        look(livingentity);
//        if(arrived){
//            arrivedTick();
//            return;
//        }
//
//        double d0 = this.canis.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
//        this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
//        if(BlockUtil.distanceFrom(this.canis.position, livingentity.position) <= attackRange){
//            onArrive();
//        }
//        if ((this.followingTargetEvenIfNotSeen || this.canis.getSensing().canSee(livingentity)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.canis.getRandom().nextFloat() < 0.05F)) {
//            this.pathedTargetX = livingentity.getX();
//            this.pathedTargetY = livingentity.getY();
//            this.pathedTargetZ = livingentity.getZ();
//            this.ticksUntilNextPathRecalculation = 4 + this.canis.getRandom().nextInt(7);
//            if (this.canPenalize) {
//                this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
//                if (this.canis.getNavigation().getPath() != null) {
//                    net.minecraft.pathfinding.PathPoint finalPathPoint = this.canis.getNavigation().getPath().getEndNode();
//                    if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
//                        failedPathFindingPenalty = 0;
//                    else
//                        failedPathFindingPenalty += 10;
//                } else {
//                    failedPathFindingPenalty += 10;
//                }
//            }
//            if (d0 > 1024.0D) {
//                this.ticksUntilNextPathRecalculation += 10;
//            } else if (d0 > 256.0D) {
//                this.ticksUntilNextPathRecalculation += 5;
//            }
//
//            if (!this.canis.getNavigation().moveTo(livingentity, this.speedModifier)) {
//                this.ticksUntilNextPathRecalculation += 15;
//            }
//        }
//
//        this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
//    }
//
//    protected void attack(LivingEntity target) {
//        if (BlockUtil.distanceFrom(target.position, this.canis.position) <= attackRange) {
//            this.ticksUntilNextAttack = 20;
//            this.canis.doHurtTarget(target);
//        }
//    }
//
//    protected double getAttackReachSqr(LivingEntity attackTarget) {
//        return (double)(this.canis.getBbWidth() * 2.0F * this.canis.getBbWidth() * 2.0F + attackTarget.getBbWidth());
//    }
//}
