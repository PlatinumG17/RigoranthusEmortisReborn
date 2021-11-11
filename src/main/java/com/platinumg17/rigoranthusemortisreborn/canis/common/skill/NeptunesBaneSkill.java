package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.registry.SkillInstance;

public class NeptunesBaneSkill extends SkillInstance {
    public NeptunesBaneSkill(Skill SkillIn, int levelIn) {
        super(SkillIn, levelIn);
    }

    @Override
    public void livingTick(AbstractCanisEntity canisIn) {
        if (this.level() >= 5 && canisIn.isVehicle() && canisIn.canBeControlledByRider()) {
            // canBeSteered checks entity is LivingEntity
            LivingEntity rider = (LivingEntity) canisIn.getControllingPassenger();
            if (rider.isInWater()) {
                rider.addEffect(new EffectInstance(Effects.NIGHT_VISION, 80, 1, true, false));
            }
        }
    }

    @Override
    public ActionResultType canBeRiddenInWater(AbstractCanisEntity canisIn, Entity rider) {
        return this.level() >= 5 ? ActionResultType.SUCCESS : ActionResultType.PASS;
    }

    @Override
    public ActionResultType canBreatheUnderwater(AbstractCanisEntity canisIn) {
        return this.level() >= 5 ? ActionResultType.SUCCESS : ActionResultType.PASS;
    }

    @Override
    public ActionResult<Integer> decreaseAirSupply(AbstractCanisEntity canisIn, int air) {
        if (this.level() > 0 && canisIn.getRandom().nextInt(this.level() + 1) > 0) {
            return ActionResult.success(air);
        }
        return ActionResult.pass(air);
    }

    @Override
    public ActionResult<Integer> determineNextAir(AbstractCanisEntity canisIn, int currentAir) {
        if (this.level() > 0) {
            return ActionResult.pass(currentAir + this.level());
        }
        return ActionResult.pass(currentAir);
    }
}