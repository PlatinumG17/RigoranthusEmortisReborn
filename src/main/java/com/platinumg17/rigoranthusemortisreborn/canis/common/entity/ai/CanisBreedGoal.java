package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.ai;

import com.platinumg17.rigoranthusemortisreborn.canis.common.util.EntityUtil;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class CanisBreedGoal extends Goal {
    private static final EntityPredicate breedPredicate = (new EntityPredicate()).range(8.0D).allowInvulnerable().allowSameTeam().allowUnseeable(); // TODO check this works
    private final AnimalEntity animal;
    private final Class<? extends AnimalEntity> mateClass;
    private final World world;
    private final double moveSpeed;

    protected AnimalEntity targetMate;
    private int spawnBabyDelay;

    public CanisBreedGoal(AnimalEntity canis, double speedIn) {
        this(canis, speedIn, canis.getClass());
    }

    public CanisBreedGoal(AnimalEntity canis, double moveSpeed, Class<? extends AnimalEntity> mateClass) {
        this.animal = canis;
        this.world = canis.level;
        this.mateClass = mateClass;
        this.moveSpeed = moveSpeed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!this.animal.isInLove()) {
            return false;
        } else {
            this.targetMate = this.getNearbyMate();
            return this.targetMate != null;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetMate.isAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60;
    }

    @Override
    public void stop() {
        this.targetMate = null;
        this.spawnBabyDelay = 0;
    }

    @Override
    public void tick() {
        this.animal.getLookControl().setLookAt(this.targetMate, 10.0F, this.animal.getMaxHeadXRot());
        this.animal.getNavigation().moveTo(this.targetMate, this.moveSpeed);
        ++this.spawnBabyDelay;
        if (this.spawnBabyDelay >= 60 && this.animal.distanceToSqr(this.targetMate) < 9.0D) {
            this.animal.spawnChildFromBreeding((ServerWorld) this.world, this.targetMate);
        }
    }

    @Nullable
    private AnimalEntity getNearbyMate() {
        List<? extends AnimalEntity> entities = this.world.getEntitiesOfClass(
                this.mateClass, this.animal.getBoundingBox().inflate(8.0D), this::filterEntities
        );
        return EntityUtil.getClosestTo(this.animal, entities);
    }

    private boolean filterEntities(AnimalEntity entity) {
        return breedPredicate.test(this.animal, entity) && this.animal.canMate(entity);
    }
}