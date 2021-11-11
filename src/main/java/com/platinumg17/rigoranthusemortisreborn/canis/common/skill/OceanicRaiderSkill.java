package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import com.platinumg17.rigoranthusemortisreborn.canis.CanisSkills;
import net.minecraft.item.Items;
import com.platinumg17.rigoranthusemortisreborn.api.enums.WetSource;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.registry.SkillInstance;

public class OceanicRaiderSkill extends SkillInstance {
    public OceanicRaiderSkill(Skill SkillIn, int levelIn) {
        super(SkillIn, levelIn);
    }

    @Override
    public void onShakingDry(AbstractCanisEntity canisIn, WetSource source) {
        if (canisIn.level.isClientSide) { // On client do nothing
            return;
        }

        if (source.isWaterBlock()) {
            if (canisIn.getRandom().nextInt(15) < this.level() * 2) {
                int lvlCerberus = canisIn.getLevel(CanisSkills.CERBERUS);
                canisIn.spawnAtLocation(canisIn.getRandom().nextInt(15) < lvlCerberus * 2 ? Items.COOKED_COD : Items.COD);
            }
        }
    }
}