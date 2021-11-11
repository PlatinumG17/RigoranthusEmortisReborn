package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments;

import net.minecraft.util.IItemProvider;

import java.util.function.Supplier;

public class Helmet extends ArmorAccoutrement {

    public Helmet(Supplier<? extends IItemProvider> itemIn) {
        super(CanisAccoutrementTypes.HEAD, itemIn);
    }
}
