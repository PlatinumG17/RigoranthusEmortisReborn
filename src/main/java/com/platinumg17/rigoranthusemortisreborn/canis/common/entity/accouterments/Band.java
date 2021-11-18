package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments;

import net.minecraft.util.IItemProvider;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Accoutrement;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.AccoutrementInstance;

import java.util.function.Supplier;

public class Band extends Accoutrement {
    public Band(Supplier<? extends IItemProvider> itemIn) {
        super(CanisAccoutrementTypes.BAND, itemIn);
    }

    @Override
    public byte getRenderLayer() {
        return AccoutrementInstance.RENDER_TOP;
    }
}
