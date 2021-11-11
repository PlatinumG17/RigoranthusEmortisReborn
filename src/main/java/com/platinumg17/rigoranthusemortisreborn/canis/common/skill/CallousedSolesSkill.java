package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import java.util.UUID;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.ForgeMod;
import com.platinumg17.rigoranthusemortisreborn.api.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.registry.SkillInstance;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;

public class CallousedSolesSkill extends SkillInstance {

    private static final UUID CALLOUSED_SOLES_BOOST_ID = UUID.fromString("1f002df0-9d35-49c6-a863-b8945caa4af4");

    public CallousedSolesSkill(Skill SkillIn, int levelIn) {
        super(SkillIn, levelIn);
    }

    @Override
    public void init(AbstractCanisEntity canisIn) {
        canisIn.setAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), CALLOUSED_SOLES_BOOST_ID, this::createSpeedModifier);
    }

    @Override
    public void set(AbstractCanisEntity canisIn, int level) {
        canisIn.setAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), CALLOUSED_SOLES_BOOST_ID, this::createSpeedModifier);
    }

    public AttributeModifier createSpeedModifier(AbstractCanisEntity canisIn, UUID uuidIn) {
        if (this.level() >= 5) {
            return new AttributeModifier(uuidIn, "Calloused Soles", -0.065D, AttributeModifier.Operation.ADDITION);
        }
        return null;
    }

    @Override
    public ActionResultType canTrample(AbstractCanisEntity canisIn, BlockState state, BlockPos pos, float fallDistance) {
        return this.level() >= 5 ? ActionResultType.FAIL : ActionResultType.PASS;
    }

    @Override
    public ActionResultType onLivingFall(AbstractCanisEntity canisIn, float distance, float damageMultiplier) {
        return this.level() >= 5 ? ActionResultType.SUCCESS : ActionResultType.PASS;
    }

    @Override
    public ActionResult<Float> calculateFallDistance(AbstractCanisEntity canisIn, float distance) {
        if (this.level() > 0) {
            return ActionResult.success(distance - this.level() * 3);
        }
        return ActionResult.pass(0F);
    }
}