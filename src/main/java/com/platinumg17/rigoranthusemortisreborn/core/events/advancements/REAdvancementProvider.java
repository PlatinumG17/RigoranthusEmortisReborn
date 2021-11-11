package com.platinumg17.rigoranthusemortisreborn.core.events.advancements;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

import com.platinumg17.rigoranthusemortisreborn.canis.CanisItems;
import com.platinumg17.rigoranthusemortisreborn.canis.common.SpecializedEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.DisplayInfoBuilder;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.TameAnimalTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.AdvancementProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class REAdvancementProvider extends AdvancementProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final DataGenerator generator;

    public REAdvancementProvider(DataGenerator generatorIn) {
        super(generatorIn);
        this.generator = generatorIn;
    }

    @Override
    public String getName() {
        return "Rigoranthus Emortis Reborn Advancements";
    }

    @Override
    public void run(DirectoryCache cache) throws IOException {
        Path path = this.generator.getOutputFolder();
        Set<ResourceLocation> set = Sets.newHashSet();
        Consumer<Advancement> consumer = (advancement) -> {
            if (!set.add(advancement.getId())) {
                throw new IllegalStateException("Duplicate advancement " + advancement.getId());
            } else {
                Path path1 = getPath(path, advancement);

                try {
                    IDataProvider.save(GSON, cache, advancement.deconstruct().serializeToJson(), path1);
                } catch (IOException ioexception) {
                    LOGGER.error("Couldn't save advancement {}", path1, ioexception);
                }
            }
        };

        // Disable advancements for now
        if (true) return;

        Advancement advancement = Advancement.Builder.advancement()
                .display(DisplayInfoBuilder.create().icon(CanisItems.TRAINING_TREAT).frame(FrameType.TASK).translate("canis.root").background("stone.png").noToast().noAnnouncement().build())
                .addCriterion("tame_canis", TameAnimalTrigger.Instance.tamedAnimal(EntityPredicate.Builder.entity().of(SpecializedEntityTypes.CANIS.get()).build()))
                .requirements(IRequirementsStrategy.OR)
                .save(consumer, REUtil.getResourcePath("default/tame_canis"));

        Advancement advancement1 = Advancement.Builder.advancement()
                .parent(advancement)
                .display(DisplayInfoBuilder.create().icon(Items.WOODEN_PICKAXE).frame(FrameType.TASK).translate("canis.level_skill").build())
                .addCriterion("level_skill", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                .save(consumer, REUtil.getResourcePath("default/level_skill"));
//        Advancement advancement2 = Advancement.Builder.advancement()
//                .parent(advancement1)
//                .display(DisplayInfoBuilder.create().icon(CanisItems.CAPE).frame(FrameType.TASK).translate("canis.accumulating_accoutrements").build())
//                .addCriterion("accumulating_accoutrements", InventoryChangeTrigger.Instance.hasItems(Items.STONE_PICKAXE))
//                .save(consumer, REUtil.getResourcePath("default/accumulating_accoutrements"));
//
//        Advancement advancement3 = Advancement.Builder.advancement()
//                .parent(advancement2)
//                .display(DisplayInfoBuilder.create().icon(CanisItems.RADIO_COLLAR).frame(FrameType.TASK).translate("canis.radio_collar").build())
//                .addCriterion("iron", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT))
//                .save(consumer, REUtil.getResourcePath("default/radio_collar"));
    }

    private static Path getPath(Path pathIn, Advancement advancementIn) {
        return pathIn.resolve("data/" + advancementIn.getId().getNamespace() + "/advancements/" + advancementIn.getId().getPath() + ".json");
    }
}
