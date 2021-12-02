package com.platinumg17.rigoranthusemortisreborn.entity.goals;

import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.World;

public class SunderedCadaverAttackGoal extends MeleeAttackGoal {

    protected final SunderedCadaverEntity entity;
    private final double speedModifier;
    private int ticksUntilNextAttack;
    private int ticksUntilNextPathRecalculation;
    private double pathedTargetX;
    private double pathedTargetY;
    private double pathedTargetZ;

    public SunderedCadaverAttackGoal(SunderedCadaverEntity zombieIn, double speedIn, boolean longMemoryIn) {
        super(zombieIn, speedIn, longMemoryIn);
        this.entity = zombieIn;
        this.speedModifier = speedIn;
    }

    public void start() {
        super.start();
        World world = entity.getCommandSenderWorld();
    }

    public boolean canUse() {return super.canUse();}

    public void stop() {
        super.stop();
        this.entity.setAggressive(false);
//        this.entity.setState(SunderedCadaverEntity.State.IDLE);
    }

    public void tick() {
        LivingEntity livingentity = this.entity.getTarget();
        if (livingentity != null) {
            this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
            double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
            this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
            if ((this.mob.getSensing().canSee(livingentity))
                    && this.ticksUntilNextPathRecalculation <= 0
                    && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D
                    || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY,
                    this.pathedTargetZ) >= 1.0D
                    || this.mob.getRandom().nextFloat() < 0.05F)) {
                this.pathedTargetX = livingentity.getX();
                this.pathedTargetY = livingentity.getY();
                this.pathedTargetZ = livingentity.getZ();
                this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                if (d0 > 1024.0D) {
                    this.ticksUntilNextPathRecalculation += 10;
                }
                else if (d0 > 256.0D) {
                    this.ticksUntilNextPathRecalculation += 5;
                }
                if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
                    this.ticksUntilNextPathRecalculation += 15;
                }
            }
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack, 0);
            this.checkAndPerformAttack(livingentity, d0);
        }
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity livingentity, double squaredDistance) {
        double d0 = this.getAttackReachSqr(livingentity);
        World world = entity.getCommandSenderWorld();
        if (squaredDistance <= d0 && this.getTicksUntilNextAttack() <= 0) {
            this.resetAttackCooldown();
//            Networking.sendToNearby(world, entity, new PacketAnimEntity(entity.getId(), SunderedCadaverEntity.Animations.ATTACKING.ordinal()));
//            this.entity.setState(SunderedCadaverEntity.State.ATTACKING);
            this.mob.doHurtTarget(livingentity);
        }
    }

    @Override
    protected int getAttackInterval() {
        return 50;
    }

    @Override
    protected double getAttackReachSqr(LivingEntity attackTarget) {
        return this.mob.getBbWidth() * 1.0F * this.mob.getBbWidth() * 1.0F + attackTarget.getBbWidth();
    }
}