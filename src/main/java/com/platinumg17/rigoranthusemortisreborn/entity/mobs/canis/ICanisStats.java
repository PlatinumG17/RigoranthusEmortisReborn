package com.platinumg17.rigoranthusemortisreborn.entity.mobs.canis;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface ICanisStats {
    double getMaxHealthForLevel(CanisEvolutionLevels levels);
    double getMovementSpeedForLevel(CanisEvolutionLevels levels);
    double getAttackDamageForLevel(CanisEvolutionLevels levels);
    double getArmorForLevel(CanisEvolutionLevels levels);
    double getAttackKnockbackForLevel(CanisEvolutionLevels levels);
    double getKnockbackResistanceForLevel(CanisEvolutionLevels levels);

    @OnlyIn(Dist.CLIENT)
    String getName();
}