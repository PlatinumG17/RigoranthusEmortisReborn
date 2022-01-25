package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractAugment;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import javax.annotation.Nullable;

public class AugmentSplit extends AbstractAugment {
    public static AugmentSplit INSTANCE = new AugmentSplit();

    private AugmentSplit() {
        super(GlyphLib.AugmentSplitID, "Split");
    }

    @Override
    public int getDominionCost() {
        return 20;
    }

    @Override
    public Tier getTier() {
        return  Tier.THREE;
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return Items.STONECUTTER;
    }

    @Override
    public String getBookDescription() {
        return "Causes multiple projectiles to be cast at once. Each projectile applies a set of effects.";
    }
}
