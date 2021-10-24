package com.platinumg17.rigoranthusemortisreborn.entity.mobs.canis;

import java.util.Arrays;
import java.util.Comparator;

public enum CanisEvolutionLevels {
    CHORDATA(1, "chordata"),
    KYPHOS(2, "kyphos"),
    CAVALIER(3, "cavalier"),
    HOMINI(4, "homini");

    private final int level;
    private final String name;
    private static final CanisEvolutionLevels[] VALUES =
            Arrays.stream(values()).sorted(Comparator.comparingInt(CanisEvolutionLevels::getEvolutionLevel)).toArray(CanisEvolutionLevels[]::new);


    private CanisEvolutionLevels(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public int getEvolutionLevel() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }

    public static CanisEvolutionLevels byName(String name) {
        for(CanisEvolutionLevels level : values()) {
            if (level.getName().equals(name)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid level for'" + name + "'");
    }

    public static int getId(CanisEvolutionLevels currentLevel) {
        if (currentLevel == CanisEvolutionLevels.CHORDATA) {
            return 1;
        } else if (currentLevel == CanisEvolutionLevels.KYPHOS) {
            return 2;
        } else if (currentLevel == CanisEvolutionLevels.CAVALIER) {
            return 3;
        } else if (currentLevel == CanisEvolutionLevels.HOMINI) {
            return 4;
        }
        return 1;
    }

    public static CanisEvolutionLevels fromId(int id) {
        switch (id) {
            case 1:
                return CanisEvolutionLevels.CHORDATA;
            case 2:
                return CanisEvolutionLevels.KYPHOS;
            case 3:
                return CanisEvolutionLevels.CAVALIER;
            case 4:
                return CanisEvolutionLevels.HOMINI;
            default:
                throw new IllegalStateException("Unable to get next Evolution of " + id);
        }
    }

    public static CanisEvolutionLevels byLevel(int level) {
        if (level < 0 || level >= VALUES.length) {
            level = 0;
        }
        return VALUES[level];
    }
}