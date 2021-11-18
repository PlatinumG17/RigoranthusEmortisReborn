package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.SkillInstance;
import net.minecraft.entity.Entity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;

public class CerberusSkill extends SkillInstance {
    public CerberusSkill(Skill SkillIn, int levelIn) {
        super(SkillIn, levelIn);
    }

    @Override
    public ActionResult<Integer> setFire(AbstractCanisEntity canisIn, int second) {
        return ActionResult.success(this.level() > 0 ? second / this.level() : second);
    }

    @Override
    public ActionResultType isImmuneToFire(AbstractCanisEntity canisIn) {
        return this.level() >= 5 ? ActionResultType.SUCCESS : ActionResultType.PASS;
    }

    @Override
    public ActionResultType isInvulnerableTo(AbstractCanisEntity canisIn, DamageSource source) {
        if (source.isFire()) {
            return this.level() >= 5 ? ActionResultType.SUCCESS : ActionResultType.PASS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public ActionResultType attackEntityAsMob(AbstractCanisEntity canisIn, Entity entity) {
        if (this.level() > 0) {
            entity.setSecondsOnFire(this.level());
            return ActionResultType.PASS;
        }
        return ActionResultType.PASS;
    }
}