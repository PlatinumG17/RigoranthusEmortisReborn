package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.ai;

import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import com.platinumg17.rigoranthusemortisreborn.api.feature.EnumMode;

public class MasterHurtTargetGoal extends OwnerHurtTargetGoal {
    private CanisEntity canis;

    public MasterHurtTargetGoal(CanisEntity canisIn) {
        super(canisIn);
        this.canis = canisIn;
    }

    @Override
    public boolean canUse() {
        return this.canis.isMode(EnumMode.AGGRESIVE, EnumMode.BERSERKER, EnumMode.TACTICAL) && super.canUse();
    }
}
