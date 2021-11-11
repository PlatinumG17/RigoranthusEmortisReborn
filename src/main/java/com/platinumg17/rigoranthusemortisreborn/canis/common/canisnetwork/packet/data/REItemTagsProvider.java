package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisItems;
import com.platinumg17.rigoranthusemortisreborn.canis.specialized.tags.CanisTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Arrays;
import java.util.function.Supplier;

public class REItemTagsProvider extends ItemTagsProvider {

    public REItemTagsProvider(DataGenerator generatorIn, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(generatorIn, blockTagProvider, EmortisConstants.MOD_ID, existingFileHelper);
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
        createTag(CanisTags.TREATS, CanisItems.TRAINING_TREAT, CanisItems.SUPER_TREAT, CanisItems.MASTER_TREAT, CanisItems.HOMINI_TREAT);
    }

    @SafeVarargs
    private final void createTag(ITag.INamedTag<Item> tag, Supplier<? extends IItemProvider>... items) {
        tag(tag).add(Arrays.stream(items).map(Supplier::get).map(IItemProvider::asItem).toArray(Item[]::new));
    }

    @SafeVarargs
    private final void appendToTag(ITag.INamedTag<Item> tag, ITag.INamedTag<Item>... toAppend) {
        tag(tag).addTags(toAppend);
    }
}
