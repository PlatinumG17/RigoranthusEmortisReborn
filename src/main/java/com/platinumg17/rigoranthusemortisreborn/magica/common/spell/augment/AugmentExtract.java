package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractAugment;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import javax.annotation.Nullable;

public class AugmentExtract extends AbstractAugment {
    public static AugmentExtract INSTANCE = new AugmentExtract();

    private AugmentExtract() {
        super(GlyphLib.AugmentExtractID, "Extract");
    }

    @Override
    public int getDominionCost() {
        return 30;
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return Items.EMERALD_BLOCK;
    }

    @Override
    public Tier getTier() {
        return Tier.TWO;
    }

    @Override
    public String getBookDescription() {
        return "Applies a silk-touch effect to Break and causes Explosion to not destroy blocks that drop. Cannot be combined with Fortune.";
    }
}