package com.platinumg17.rigoranthusemortisreborn.entity.mobs.canis;

import com.mojang.serialization.Codec;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.IExtensibleEnum;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CanisStatsByEvolutionLevel implements ICanisStats, IStringSerializable, IExtensibleEnum {
    CHORDATA("chordata", 90, 0.27, 3, 3, 0.4, 0.7),
    KYPHOS("kyphos", 100, 0.26, 7, 5, 0.6, 0.8),
    CAVALIER("cavalier", 110, 0.27, 10, 8, 0.8, 0.9),
    HOMINI("homini", 120, 0.28, 15, 12, 1.0, 1);


    private final String name;
    private final double maxHealth;
    private final double movementSpeed;
    private final double attackDamage;
    private final double armor;
    private final double attackKnockback;
    private final double knockbackResistance;
    private static final CanisStatsByEvolutionLevel[] STATS_BY_EVOLUTION = values();
    public static final Codec<CanisStatsByEvolutionLevel> CODEC = IExtensibleEnum.createCodecForExtensibleEnum(CanisStatsByEvolutionLevel::values, CanisStatsByEvolutionLevel::byEvolutionName);
    private static final Map<String, CanisStatsByEvolutionLevel> BY_EVOLUTION_NAME = Arrays.stream(STATS_BY_EVOLUTION).collect(Collectors.toMap(CanisStatsByEvolutionLevel::getName, (byEvolutionName) -> byEvolutionName));

    public static CanisStatsByEvolutionLevel byEvolutionName(String name) {
        return BY_EVOLUTION_NAME.get(name);
    }

    public static CanisStatsByEvolutionLevel create(String name, double health, double speed, double damage, double armor, double knockback, double resistance) {
        throw new IllegalStateException("Enum Dingus did not extend liek asposed to");
    }

    @Override @Deprecated public void init() {
        BY_EVOLUTION_NAME.put(this.getName(), this);
    }

    private CanisStatsByEvolutionLevel(String name,
         double health, double speed, double damage,
         double armor, double knockback, double resistance) {
        this.name = name;
        this.maxHealth = health;
        this.movementSpeed = speed;
        this.attackDamage = damage;
        this.armor = armor;
        this.attackKnockback = knockback;
        this.knockbackResistance = resistance;
    }

    public CanisStatsByEvolutionLevel getNextEvolution() {
        switch(this) {
            case CHORDATA:
                return KYPHOS;
            case KYPHOS:
                return CAVALIER;
            case CAVALIER:
                return HOMINI;
            case HOMINI:
                return null;
            default:
                throw new IllegalStateException("Unable to get next Evolution of " + this);
        }
    }
    public CanisStatsByEvolutionLevel evolveToNextLevel() {
        switch(this) {
            case CHORDATA:
                return KYPHOS;
            case KYPHOS:
                return CAVALIER;
            case CAVALIER:
                return HOMINI;
            case HOMINI:
                return null;
            default:
                throw new IllegalStateException("Unable to get next Evolution of " + this);
        }
    }


    public double getMaxHealthForLevel(CanisEvolutionLevels levels) {
        return this.maxHealth;
    }

    public double getMovementSpeedForLevel(CanisEvolutionLevels levels) {
        return this.movementSpeed;
    }

    public double getAttackDamageForLevel(CanisEvolutionLevels levels) {
        return this.attackDamage;
    }

    public double getArmorForLevel(CanisEvolutionLevels levels) {
        return this.armor;
    }

    public double getAttackKnockbackForLevel(CanisEvolutionLevels levels) {
        return this.attackKnockback;
    }

    public double getKnockbackResistanceForLevel(CanisEvolutionLevels levels) {
        return this.knockbackResistance;
    }

    public String getName() {
        return "canis_" + this.name;
    }

    public String getSerializedName() {
        return "canis_" + this.name;
    }
}
