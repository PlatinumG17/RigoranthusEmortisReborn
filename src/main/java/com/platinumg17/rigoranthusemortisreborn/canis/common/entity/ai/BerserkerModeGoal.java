package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.ai;

import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import com.platinumg17.rigoranthusemortisreborn.api.feature.EnumMode;

public class BerserkerModeGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

    private final CanisEntity canis;

    public BerserkerModeGoal(CanisEntity canisIn, Class<T> targetClassIn, boolean checkSight) {
        super(canisIn, targetClassIn, checkSight, false);
        this.canis = canisIn;
    }

    @Override
    public boolean canUse() {
        return this.canis.isMode(EnumMode.BERSERKER) && super.canUse();
    }
}