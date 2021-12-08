package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class REBlockTagsProvider extends BlockTagsProvider {
    public REBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, EmortisConstants.MOD_ID, existingFileHelper);
    }

//    public static final ITag.INamedTag<Block> MASTERFUL = BlockTags.bind("forge:furnaces/masterful");
//    public static final ITag.INamedTag<Block> JESSIC_LOG = BlockTags.bind("forge:logs/jessic");
//    public static final ITag.INamedTag<Block> AZULOREAL_LOG = BlockTags.bind("forge:logs/azuloreal");
//
//    public static final ITag.INamedTag<Block> JESSIC_LOGS = BlockTags.bind("jessic_logs");
//    public static final ITag.INamedTag<Block> JESSIC = BlockTags.bind("jessic");
//    public static final ITag.INamedTag<Block> AZULOREAL_LOGS = BlockTags.bind("azuloreal_logs");
//    public static final ITag.INamedTag<Block> AZULOREAL = BlockTags.bind("azuloreal");

    @Override
    public String getName() {
        return "RigoranthusEmortisReborn Block Tags";
    }

    @Override
    protected void addTags() {
//        tag(MASTERFUL);
//        tag(JESSIC_LOG);
//        tag(AZULOREAL_LOG);
//        tag(JESSIC_LOGS);
//        tag(AZULOREAL_LOGS);
//        tag(JESSIC);
//        tag(AZULOREAL);
//
//        tag(MASTERFUL).add(BlockRegistry.MASTERFUL_SMELTERY);
//        tag(JESSIC_LOG).add(BlockRegistry.JESSIC_LOG).add(BlockRegistry.JESSIC_WOOD).add(BlockRegistry.STRIPPED_JESSIC_LOG).add(BlockRegistry.STRIPPED_JESSIC_WOOD);
//        tag(AZULOREAL_LOG).add(BlockRegistry.AZULOREAL_LOG).add(BlockRegistry.AZULOREAL_WOOD).add(BlockRegistry.STRIPPED_AZULOREAL_LOG).add(BlockRegistry.STRIPPED_AZULOREAL_WOOD);
//        tag(JESSIC_LOGS).add(BlockRegistry.JESSIC_LOG).add(BlockRegistry.JESSIC_WOOD).add(BlockRegistry.STRIPPED_JESSIC_LOG).add(BlockRegistry.STRIPPED_JESSIC_WOOD);
//        tag(AZULOREAL_LOGS).add(BlockRegistry.AZULOREAL_LOG).add(BlockRegistry.AZULOREAL_WOOD).add(BlockRegistry.STRIPPED_AZULOREAL_LOG).add(BlockRegistry.STRIPPED_AZULOREAL_WOOD);
//        tag(JESSIC).add(BlockRegistry.JESSIC_LOG).add(BlockRegistry.JESSIC_WOOD).add(BlockRegistry.STRIPPED_JESSIC_LOG).add(BlockRegistry.STRIPPED_JESSIC_WOOD);
//        tag(AZULOREAL).add(BlockRegistry.AZULOREAL_LOG).add(BlockRegistry.AZULOREAL_WOOD).add(BlockRegistry.STRIPPED_AZULOREAL_LOG).add(BlockRegistry.STRIPPED_AZULOREAL_WOOD);
//        tag(BlockTags.CLIMBABLE).add(DecorativeOrStorageBlocks.AZULOREAL_LADDER.get()).add(DecorativeOrStorageBlocks.JESSIC_LADDER.get());
//        tag(BlockTags.LOGS_THAT_BURN).addTags(REItemTagsProvider.AZULOREAL_LOGS).addTags(JESSIC_LOGS);
//        tag(BlockTags.LOGS)
//                .add(BlockRegistry.AZULOREAL_LOG)
//                .add(BlockRegistry.AZULOREAL_WOOD)
//                .add(BlockRegistry.STRIPPED_AZULOREAL_LOG)
//                .add(BlockRegistry.STRIPPED_AZULOREAL_WOOD)
//                .add(BlockRegistry.JESSIC_LOG)
//                .add(BlockRegistry.JESSIC_WOOD)
//                .add(BlockRegistry.STRIPPED_JESSIC_LOG)
//                .add(BlockRegistry.STRIPPED_JESSIC_WOOD);
//        tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).addTags(AZULOREAL_LOGS);
//        tag(BlockTags.SAPLINGS).add(BlockRegistry.AZULOREAL_SAPLING).add(BlockRegistry.JESSIC_SAPLING);
//        tag(BlockTags.VALID_SPAWN).add(BlockRegistry.FRAGMENTED_COBBLESTONE);
//        tag(BlockTags.MUSHROOM_GROW_BLOCK).add(BlockRegistry.FRAGMENTED_COBBLESTONE).add(BlockInit.FRAGMENTED_NETHERRACK.get());
    }
}
