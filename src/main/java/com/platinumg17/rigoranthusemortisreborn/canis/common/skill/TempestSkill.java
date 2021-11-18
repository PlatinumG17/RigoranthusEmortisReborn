package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.DataKey;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.SkillInstance;

public class TempestSkill extends SkillInstance {
    public static DataKey<Integer> COOLDOWN = DataKey.make();

    public TempestSkill(Skill SkillIn, int levelIn) {
        super(SkillIn, levelIn);
    }
}