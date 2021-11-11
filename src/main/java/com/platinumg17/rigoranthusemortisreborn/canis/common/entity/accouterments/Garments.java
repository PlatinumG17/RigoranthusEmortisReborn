package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments;

import java.util.function.Supplier;

import net.minecraft.util.IItemProvider;
import com.platinumg17.rigoranthusemortisreborn.api.registry.Accoutrement;

public class Garments extends Accoutrement {
    public Garments(Supplier<? extends IItemProvider> itemIn) {
        super(CanisAccoutrementTypes.GARMENTS, itemIn);
    }
}
