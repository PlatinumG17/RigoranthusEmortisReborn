package com.platinumg17.rigoranthusemortisreborn.core.other;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.platinumg17.rigoranthusemortisreborn.blocks.custom.DecorativeOrStorageBlocks;
import com.platinumg17.rigoranthusemortisreborn.core.init.BuildingBlockInit;

public class VanillaCompatRigoranthus {

    public static void registerCompostables() {
        // Compostable
        DataUtil.registerCompostable(DecorativeOrStorageBlocks.AZULOREAL_LEAVES.get(), 0.3F);
        DataUtil.registerCompostable(DecorativeOrStorageBlocks.AZULOREAL_SAPLING.get(), 0.3F);
        DataUtil.registerCompostable(DecorativeOrStorageBlocks.AZULOREAL_LEAF_CARPET.get(), 0.3F);

        DataUtil.registerCompostable(DecorativeOrStorageBlocks.JESSIC_LEAVES.get(), 0.3F);
        DataUtil.registerCompostable(DecorativeOrStorageBlocks.JESSIC_SAPLING.get(), 0.3F);
        DataUtil.registerCompostable(DecorativeOrStorageBlocks.JESSIC_LEAF_CARPET.get(), 0.3F);

        DataUtil.registerCompostable(DecorativeOrStorageBlocks.AZULOREAL_ORCHID.get(), 0.65F);
        DataUtil.registerCompostable(DecorativeOrStorageBlocks.IRIDESCENT_SPROUTS.get(), 0.65F);
        DataUtil.registerCompostable(BuildingBlockInit.LISIANTHUS.get(), 0.65F);
    }
    public static void registerFlammables() {
        // Flammability
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_LEAVES.get(), 30, 60);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_LOG.get(), 5, 5);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.STRIPPED_AZULOREAL_LOG.get(), 5, 5);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.STRIPPED_AZULOREAL_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_FENCE.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_FENCE_GATE.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.VERTICAL_AZULOREAL_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.VERTICAL_AZULOREAL_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_POST.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.STRIPPED_AZULOREAL_POST.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_BOOKSHELF.get(), 30, 20);

        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_LEAVES.get(), 30, 60);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_LOG.get(), 5, 5);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.STRIPPED_JESSIC_LOG.get(), 5, 5);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.STRIPPED_JESSIC_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_FENCE.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_FENCE_GATE.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.VERTICAL_JESSIC_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.VERTICAL_JESSIC_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_POST.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.STRIPPED_JESSIC_POST.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_BOOKSHELF.get(), 30, 20);
    }
}
