package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractAugment;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import javax.annotation.Nullable;

public class AugmentAOE extends AbstractAugment {
    public static AugmentAOE INSTANCE = new AugmentAOE();

    private AugmentAOE() {
        super(GlyphLib.AugmentAOEID, "AOE");
    }

    @Override
    public int getDominionCost() {
        return 35;
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return Items.FIREWORK_STAR;
    }

    @Override
    public Tier getTier() {
        return Tier.TWO;
    }

    @Override
    public String getBookDescription() {
        return "Spells will affect a larger area around a targeted block.";
    }
}