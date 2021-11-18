package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.ai;

import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.EnumMode;

public class SentinelModeGoal extends NearestAttackableTargetGoal<MonsterEntity> {

    private final CanisEntity canis;
    private LivingEntity owner;

    public SentinelModeGoal(CanisEntity canisIn, boolean checkSight) {
        super(canisIn, MonsterEntity.class, 0, checkSight, false, null);
        this.canis = canisIn;
    }

    @Override
    public boolean canUse() {
        LivingEntity owner = this.canis.getOwner();
        if (owner == null) {return false;}
        if (!this.canis.isMode(EnumMode.GUARD)) {return false;}
        this.owner = owner;
        if (super.canUse()) {
            this.owner = owner;
            return true;
        }
        return false;
    }
    @Override protected double getFollowDistance() {return 6D;}

    @Override
    protected void findTarget() {
        this.target = this.canis.level.getNearestLoadedEntity(this.targetType, this.targetConditions, this.canis, this.owner.getX(), this.owner.getEyeY(), this.owner.getZ(), this.getTargetSearchArea(this.getFollowDistance()));
    }
}