package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data;

import com.platinumg17.rigoranthusemortisreborn.canis.CanisItems;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.CanisTags;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Arrays;
import java.util.function.Supplier;

public class REItemTagsProvider extends ItemTagsProvider {

    public REItemTagsProvider(DataGenerator generatorIn, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(generatorIn, blockTagsProvider, EmortisConstants.MOD_ID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "RigoranthusEmortisReborn Item Tags";
    }

    @Override
    public void addTags() {
        createTag(CanisTags.SNACK_ITEMS_TAMED, CanisItems.BREEDING_BONE, CanisItems.THROW_STICK, CanisItems.THROW_BONE, Items.BONE.delegate);
        appendToTag(CanisTags.TREATS);
        createTag(CanisTags.SNACK_ITEMS_UNTAMED, CanisItems.TRAINING_TREAT, Items.BONE.delegate);
        createTag(CanisTags.BREEDING_ITEMS, CanisItems.BREEDING_BONE);
        createTag(CanisTags.WAYWARD_TRAVELLER_BLACKLIST, CanisItems.THROW_BONE, CanisItems.THROW_BONE_WET, CanisItems.THROW_STICK, CanisItems.THROW_STICK_WET);
        createTag(CanisTags.TREATS, CanisItems.TRAINING_TREAT, CanisItems.REGULAR_TREAT, CanisItems.MASTER_TREAT, CanisItems.HOMINI_TREAT);

        tag(ItemTags.ARROWS).add(MagicItemsRegistry.BONE_ARROW);
        tag(ItemTags.SMALL_FLOWERS).add(BlockRegistry.AZULOREAL_ORCHID.asItem()).add(BlockRegistry.IRIDESCENT_SPROUTS.asItem());
        tag(ItemTags.TALL_FLOWERS).add(BlockRegistry.LISIANTHUS.asItem());

        appendToTag(CanisTags.MASTERFUL);
        appendToTag(CanisTags.JESSIC_LOG);
        appendToTag(CanisTags.AZULOREAL_LOG);
        appendToTag(CanisTags.JESSIC_LOGS);
        appendToTag(CanisTags.AZULOREAL_LOGS);
        appendToTag(CanisTags.JESSIC);
        appendToTag(CanisTags.AZULOREAL);

        createBlockTag(CanisTags.MASTERFUL, Registration.MASTERFUL_SMELTERY.get());
        createBlockTag(CanisTags.JESSIC_LOG, BlockRegistry.JESSIC_LOG, BlockRegistry.JESSIC_WOOD, BlockRegistry.STRIPPED_JESSIC_LOG, BlockRegistry.STRIPPED_JESSIC_WOOD);
        createBlockTag(CanisTags.AZULOREAL_LOG, BlockRegistry.AZULOREAL_LOG, BlockRegistry.AZULOREAL_WOOD, BlockRegistry.STRIPPED_AZULOREAL_LOG, BlockRegistry.STRIPPED_AZULOREAL_WOOD);
        createBlockTag(CanisTags.JESSIC_LOGS, BlockRegistry.JESSIC_LOG, BlockRegistry.JESSIC_WOOD, BlockRegistry.STRIPPED_JESSIC_LOG, BlockRegistry.STRIPPED_JESSIC_WOOD);
        createBlockTag(CanisTags.AZULOREAL_LOGS, BlockRegistry.AZULOREAL_LOG, BlockRegistry.AZULOREAL_WOOD, BlockRegistry.STRIPPED_AZULOREAL_LOG, BlockRegistry.STRIPPED_AZULOREAL_WOOD);
        createBlockTag(CanisTags.JESSIC, BlockRegistry.JESSIC_LOG, BlockRegistry.JESSIC_WOOD, BlockRegistry.STRIPPED_JESSIC_LOG, BlockRegistry.STRIPPED_JESSIC_WOOD);
        createBlockTag(CanisTags.AZULOREAL, BlockRegistry.AZULOREAL_LOG, BlockRegistry.AZULOREAL_WOOD, BlockRegistry.STRIPPED_AZULOREAL_LOG, BlockRegistry.STRIPPED_AZULOREAL_WOOD);
        appendToTag(ItemTags.LOGS_THAT_BURN, CanisTags.AZULOREAL_LOGS, CanisTags.JESSIC_LOGS);
        tag(ItemTags.LOGS)
                .add(BlockRegistry.AZULOREAL_LOG.asItem())
                .add(BlockRegistry.AZULOREAL_WOOD.asItem())
                .add(BlockRegistry.STRIPPED_AZULOREAL_LOG.asItem())
                .add(BlockRegistry.STRIPPED_AZULOREAL_WOOD.asItem())
                .add(BlockRegistry.JESSIC_LOG.asItem())
                .add(BlockRegistry.JESSIC_WOOD.asItem())
                .add(BlockRegistry.STRIPPED_JESSIC_LOG.asItem())
                .add(BlockRegistry.STRIPPED_JESSIC_WOOD.asItem());
        appendToTag(ItemTags.SOUL_FIRE_BASE_BLOCKS, CanisTags.AZULOREAL_LOGS);
        tag(ItemTags.SAPLINGS).add(BlockRegistry.AZULOREAL_SAPLING.asItem()).add(BlockRegistry.JESSIC_SAPLING.asItem());
    }

    @SafeVarargs
    private final void createTag(ITag.INamedTag<Item> tag, Supplier<? extends IItemProvider>... items) {
        tag(tag).add(Arrays.stream(items).map(Supplier::get).map(IItemProvider::asItem).toArray(Item[]::new));
    }


    private void createBlockTag(ITag.INamedTag<Item> tag, Block... items) {
        tag(tag).add(Arrays.stream(items).map(Block::asItem).toArray(Item[]::new));
    }

    @SafeVarargs
    private final void appendToTag(ITag.INamedTag<Item> tag, ITag.INamedTag<Item>... toAppend) {
        tag(tag).addTags(toAppend);
    }
}
