package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments;

import net.minecraft.util.IItemProvider;
import com.platinumg17.rigoranthusemortisreborn.api.registry.Accoutrement;
import com.platinumg17.rigoranthusemortisreborn.api.registry.AccoutrementInstance;

import java.util.function.Supplier;

public class Glasses extends Accoutrement {

    public Glasses(Supplier<? extends IItemProvider> itemIn) {
        super(CanisAccoutrementTypes.GLASSES, itemIn);
    }

    @Override
    public byte getRenderLayer() {
        return AccoutrementInstance.RENDER_TOP;
    }
}
