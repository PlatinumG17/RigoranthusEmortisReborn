package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments;

import net.minecraft.util.IItemProvider;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.AccoutrementInstance;

import java.util.function.Supplier;

public class LeatherHelmet extends DyeableAccoutrement {
    public LeatherHelmet(Supplier<? extends IItemProvider> itemIn) {
        super(CanisAccoutrementTypes.HEAD, itemIn);
    }

    @Override
    public byte getRenderLayer() {
        return AccoutrementInstance.RENDER_TOP;
    }
}
