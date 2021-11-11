package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.ai;

import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import com.platinumg17.rigoranthusemortisreborn.api.feature.EnumMode;

public class MasterHurtByTargetGoal extends OwnerHurtByTargetGoal {
    private CanisEntity canis;

    public MasterHurtByTargetGoal(CanisEntity canisIn) {
        super(canisIn);
        this.canis = canisIn;
    }

    @Override
    public boolean canUse() {
        return this.canis.isMode(EnumMode.AGGRESIVE, EnumMode.BERSERKER) && super.canUse();
    }
}
