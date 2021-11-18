package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import net.minecraft.util.ActionResult;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.SkillInstance;

public class RapidRecoverySkill extends SkillInstance {
    public RapidRecoverySkill(Skill SkillIn, int levelIn) {
        super(SkillIn, levelIn);
    }

    @Override
    public ActionResult<Integer> healingTick(AbstractCanisEntity canisIn, int healingTick) {
        if (this.level() > 0) {
            if (canisIn.isInSittingPose() && this.level() >= 5) {
                if (canisIn.getNoActionTime() > 100) {
                    healingTick *= 15;
                } else {
                    healingTick *= 10;
                }
            } else {
                healingTick *= this.level();
            }
            return ActionResult.success(healingTick);
        }
        return ActionResult.pass(healingTick);
    }
}