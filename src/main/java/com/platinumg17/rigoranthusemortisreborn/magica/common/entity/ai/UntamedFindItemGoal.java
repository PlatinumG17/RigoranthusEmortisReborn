package com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class UntamedFindItemGoal extends Goal {

    MobEntity mobEntity;
    Predicate<ItemEntity> itemSelector;
    Supplier<Boolean> canRun;
    int timeFinding;
    boolean itemStuck;
    ItemEntity pathingEntity;

    public List<ItemEntity> nearbyItems(){
        return mobEntity.level.getLoadedEntitiesOfClass(ItemEntity.class, new AxisAlignedBB(mobEntity.blockPosition()).inflate(8), itemSelector);
    }

    public UntamedFindItemGoal(MobEntity mobEntity, Supplier<Boolean> canRun, Predicate<ItemEntity> itemSelector){
        this.mobEntity = mobEntity;
        this.setFlags(EnumSet.of(Flag.MOVE));
        this.canRun = canRun;
        this.itemSelector = itemSelector;
    }

    @Override
    public void tick() {
        super.tick();
        timeFinding += 1;
        if(pathingEntity == null || pathingEntity.removed)
            return;
        pathToTarget(pathingEntity, 1.2f);

    }

    @Override
    public boolean canContinueToUse() {
        return timeFinding <= 20 * 30 && !itemStuck  && !(pathingEntity == null || pathingEntity.removed || pathingEntity.getItem().isEmpty()) && canUse();
    }

    @Override
    public boolean canUse() {
        return canRun.get() && !nearbyItems().isEmpty();
    }

    @Override
    public void stop() {
        super.stop();
        timeFinding = 0;
        itemStuck = false;
    }

    @Override
    public void start() {
        super.start();
        timeFinding = 0;
        itemStuck = false;

        List<ItemEntity> list = nearbyItems();
        if (!list.isEmpty() && !itemStuck) {
            for(ItemEntity item : list){
                Path path = mobEntity.getNavigation().createPath(item, 0);
                if(path != null && path.canReach()) {
                    this.pathingEntity = item;
                    pathToTarget(pathingEntity, 1.2f);
                    break;
                }
            }
        }
        if(pathingEntity == null)
            itemStuck = true;
    }

    public void pathToTarget(Entity entity, double speed){
        Path path = mobEntity.getNavigation().createPath(entity, 0);
        if(path != null && path.canReach()) {
            mobEntity.getNavigation().moveTo(path, speed);
        }
        if(path != null && !path.canReach()) {
            itemStuck = true;
        }
    }
}