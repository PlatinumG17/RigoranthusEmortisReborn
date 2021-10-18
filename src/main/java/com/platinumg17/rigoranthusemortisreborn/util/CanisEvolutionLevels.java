package com.platinumg17.rigoranthusemortisreborn.util;

import java.util.Arrays;
import java.util.Comparator;

public enum CanisEvolutionLevels {
    CHORDATA(0),
    KYPHOS(1),
    CAVALIER(2),
    HOMINI(3);

    private static final CanisEvolutionLevels[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(CanisEvolutionLevels::getId)).toArray(CanisEvolutionLevels[]::new);

    private final int id;

    private CanisEvolutionLevels(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static CanisEvolutionLevels byId(int id) {
        if (id < 0 || id >= VALUES.length) {
            id = 0;
        }
        return VALUES[id];
    }
}